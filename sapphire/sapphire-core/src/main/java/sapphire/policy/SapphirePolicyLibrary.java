package sapphire.policy;

import java.lang.annotation.Annotation;
import java.net.InetSocketAddress;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.harmony.rmi.common.RMIUtil;
import org.graalvm.polyglot.*;
import sapphire.common.AppObject;
import sapphire.common.AppObjectStub;
import sapphire.common.Language;
import sapphire.common.SapphireObjectID;
import sapphire.common.SapphireObjectNotFoundException;
import sapphire.common.SapphireObjectReplicaNotFoundException;
import sapphire.common.SapphireReplicaID;
import sapphire.compiler.GlobalStubConstants;
import sapphire.kernel.common.GlobalKernelReferences;
import sapphire.kernel.common.KernelOID;
import sapphire.kernel.common.KernelObjectFactory;
import sapphire.kernel.common.KernelObjectNotCreatedException;
import sapphire.kernel.common.KernelObjectNotFoundException;
import sapphire.kernel.common.KernelObjectStub;
import sapphire.kernel.server.KernelServerImpl;
import sapphire.oms.OMSServer;
import sapphire.policy.SapphirePolicy.SapphireServerPolicy;
import sapphire.runtime.EventHandler;
import sapphire.runtime.Sapphire;

public abstract class SapphirePolicyLibrary implements SapphirePolicyUpcalls {
    public abstract static class SapphireClientPolicyLibrary
            implements SapphireClientPolicyUpcalls {
        /*
         * INTERNAL FUNCTIONS (Used by sapphire runtime system)
         */
    }

    public abstract static class SapphireServerPolicyLibrary
            implements SapphireServerPolicyUpcalls {
        protected AppObject appObject;
        protected KernelOID oid;
        protected SapphireReplicaID replicaId;
        protected Context c;

        static Logger logger = Logger.getLogger("sapphire.policy.SapphirePolicyLibrary");

        private OMSServer oms() {
            return GlobalKernelReferences.nodeServer.oms;
        }

        private KernelServerImpl kernel() {
            return GlobalKernelReferences.nodeServer;
        }

        public void SetGraalContext(Context c) {
            this.c = c;
        }

        /*
         * SAPPHIRE API FOR SERVER POLICIES
         */

        /** Creates a replica of this server and registers it with the group */
        // TODO: Also replicate the policy ??
        public SapphireServerPolicy sapphire_replicate() throws RemoteException {
            KernelObjectStub serverPolicyStub = null;
            String policyStubClassName =
                    GlobalStubConstants.getPolicyPackageName()
                            + "."
                            + RMIUtil.getShortName(this.getClass())
                            + GlobalStubConstants.STUB_SUFFIX;
            try {
                serverPolicyStub =
                        (KernelObjectStub) KernelObjectFactory.create(policyStubClassName);
                SapphireServerPolicy serverPolicy =
                        (SapphireServerPolicy)
                                kernel().getObject(serverPolicyStub.$__getKernelOID());
                serverPolicy.$__initialize(appObject);
                serverPolicy.$__setKernelOID(serverPolicyStub.$__getKernelOID());

                /* Register the handler for this replica to OMS */
                SapphireReplicaID replicaId =
                        oms().registerSapphireReplica(getGroup().getSapphireObjId());
                serverPolicy.setReplicaId(replicaId);
                ((SapphireServerPolicy) serverPolicyStub).setReplicaId(replicaId);
                ArrayList<Object> policyObjList = new ArrayList();
                policyObjList.add(serverPolicyStub);
                EventHandler replicaHandler =
                        new EventHandler(
                                GlobalKernelReferences.nodeServer.getLocalHost(), policyObjList);
                oms().setSapphireReplicaDispatcher(replicaId, replicaHandler);
                /* Here getClass() gives the Applications Object Stub class so we should use getSuperclass to get the actual Application class
                  for example getClass() gives as class sapphire.appexamples.hankstodo.app.stubs.TodoListManager_Stub
                  getClass().getSuperclass() gives as class sapphire.appexamples.hankstodo.app.TodoListManager
                */
                Class c = sapphire_getAppObject().getObject().getClass().getSuperclass();
                serverPolicy.onCreate(getGroup(), c.getAnnotations());
                getGroup().addServer((SapphireServerPolicy) serverPolicyStub);
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new Error("Could not find the class for replication!");
            } catch (KernelObjectNotCreatedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new Error("Could not create a replica!");
            } catch (KernelObjectNotFoundException e) {
                e.printStackTrace();
                throw new Error("Could not find object to replicate!");
            } catch (SapphireObjectNotFoundException e) {
                KernelObjectFactory.delete(serverPolicyStub.$__getKernelOID());
                e.printStackTrace();
                throw new Error("Could not find sapphire object on OMS");
            } catch (SapphireObjectReplicaNotFoundException e) {
                KernelObjectFactory.delete(serverPolicyStub.$__getKernelOID());
                e.printStackTrace();
                throw new Error("Could not find sapphire object replica on OMS");
            } catch (RemoteException e) {
                sapphire_remove_replica();
                e.printStackTrace();
                throw new Error("Could not create a replica of " + appObject.getObject(), e);
            }

            return (SapphireServerPolicy) serverPolicyStub;
        }

        public AppObject sapphire_getAppObject() {
            return appObject;
        }

        public void sapphire_pin(String region)
                throws RemoteException, SapphireObjectNotFoundException,
                        SapphireObjectReplicaNotFoundException {
            logger.info("Pinning Sapphire object " + oid.toString() + " to " + region);
            InetSocketAddress server = null;
            try {
                server = oms().getServerInRegion(region);
            } catch (RemoteException e) {
                throw new RemoteException("Could not contact oms.");
            }
            sapphire_pin_to_server(server);
        }

        // This function is same as sapphire_pin but pining to the server instead of region
        public void sapphire_pin_to_server(InetSocketAddress server)
                throws RemoteException, SapphireObjectNotFoundException,
                        SapphireObjectReplicaNotFoundException {
            logger.info("Pinning Sapphire object " + oid.toString() + " to " + server);
            try {
                kernel().moveKernelObjectToServer(server, oid);
            } catch (KernelObjectNotFoundException e) {
                e.printStackTrace();
                throw new Error("Could not find myself on this server!");
            }
        }

        public void sapphire_remove_replica() throws RemoteException {
            try {
                oms().unRegisterSapphireReplica(getReplicaId());
            } catch (SapphireObjectNotFoundException e) {
                /* Sapphire object not found */
                e.printStackTrace();
            }
            KernelObjectFactory.delete($__getKernelOID());
        }

        /*
         * INTERNAL FUNCTIONS
         */
        /**
         * Internal function used to initialize the App Object
         *
         * @param appObjectClassName
         * @param params
         */
        // TODO: not final (stub overrides it)
        public AppObjectStub $__initialize(Class<?> appObjectStubClass, Object[] params) {
            return $__initialize(appObjectStubClass, Language.JAVA, params);
        }

        public AppObjectStub $__initialize(String className, Language language, Object[] params) {
            try {
                return $__initialize(Class.forName(className), language, params);
            } catch (Exception ex) {
                ex.printStackTrace();
                logger.log(Level.SEVERE, ex.toString());
                return null;
            }
        }

        public AppObjectStub $__initialize(
                Class<?> appObjectStubClass, Language language, Object[] params) {
            AppObjectStub actualAppObject =
                    null; // The Actual App Object, managed by an AppObject Handler
            try {
                // Construct the list of classes of the arguments as Class[]
                if (params != null) {
                    Class<?>[] argClasses = Sapphire.getParamsClasses(params);
                    actualAppObject =
                            (AppObjectStub)
                                    appObjectStubClass
                                            .getConstructor(argClasses)
                                            .newInstance(params);
                } else actualAppObject = (AppObjectStub) appObjectStubClass.newInstance();

                if (language == Language.JAVA) {
                    actualAppObject.$__initialize(true);
                    appObject = new AppObject(actualAppObject);
                } else {
                    String className =
                            appObjectStubClass
                                    .getCanonicalName()
                                    .replace(GlobalStubConstants.STUB_SUFFIX, "");
                    logger.log(
                            Level.INFO,
                            String.format(
                                    "Try to create app object, language %s, className %s",
                                    language, className));
                    appObject = new AppObject(c.eval(language.toString(), className).newInstance());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return actualAppObject;
        }

        public String sapphire_getRegion() {
            return kernel().getRegion();
        }

        public void $__initialize(AppObject appObject) {
            this.appObject = appObject;
        }

        public void $__setKernelOID(KernelOID oid) {
            this.oid = oid;
        }

        public KernelOID $__getKernelOID() {
            return oid;
        }

        public void setReplicaId(SapphireReplicaID rid) {
            replicaId = rid;
        }

        public SapphireReplicaID getReplicaId() {
            return replicaId;
        }

        public InetSocketAddress sapphire_locate_kernel_object(KernelOID oid)
                throws RemoteException {
            InetSocketAddress addr;
            try {
                addr = oms().lookupKernelObject(oid);
            } catch (RemoteException e) {
                throw new RemoteException("Could not contact oms.");
            } catch (KernelObjectNotFoundException e) {
                e.printStackTrace();
                throw new Error("Could not find myself on this server!");
            }
            return addr;
        }
    }

    public abstract static class SapphireGroupPolicyLibrary implements SapphireGroupPolicyUpcalls {
        protected String appObjectClassName;
        protected ArrayList<Object> params;
        protected Annotation[] appConfigAnnotation;
        protected KernelOID oid;
        protected SapphireObjectID sapphireObjId;

        private OMSServer oms() {
            return GlobalKernelReferences.nodeServer.oms;
        }

        /*
         * SAPPHIRE API FOR GROUP POLICIES
         */

        public ArrayList<String> sapphire_getRegions() throws RemoteException {
            return oms().getRegions();
        }

        /**
         * Gets the list of inet sock address of the servers in the specified region
         *
         * @param region
         * @return inet socket address of the server
         * @throws RemoteException
         */
        public ArrayList<InetSocketAddress> sapphire_getServersInRegion(String region)
                throws RemoteException {
            return oms().getServersInRegion(region);
        }

        public void $__setKernelOID(KernelOID oid) {
            this.oid = oid;
        }

        public KernelOID $__getKernelOID() {
            return this.oid;
        }

        public void setAppConfigAnnotation(Annotation[] appConfigAnnotation) {
            this.appConfigAnnotation = appConfigAnnotation;
        }

        public Annotation[] getAppConfigAnnotation() {
            return appConfigAnnotation;
        }

        public void setSapphireObjId(SapphireObjectID sapphireId) {
            sapphireObjId = sapphireId;
        }

        public SapphireObjectID getSapphireObjId() {
            return sapphireObjId;
        }

        protected SapphireServerPolicy addReplica(
                SapphireServerPolicy replicaSource, InetSocketAddress dest)
                throws RemoteException, SapphireObjectNotFoundException,
                        SapphireObjectReplicaNotFoundException {
            SapphireServerPolicy replica = replicaSource.sapphire_replicate();
            try {
                replica.sapphire_pin_to_server(dest);
                updateReplicaHostName(replica, dest);
            } catch (Exception e) {
                try {
                    removeReplica(replica);
                } catch (Exception innerException) {
                }
                throw e;
            }
            return replica;
        }

        protected void removeReplica(SapphireServerPolicy server)
                throws RemoteException, SapphireObjectReplicaNotFoundException,
                        SapphireObjectNotFoundException {
            server.sapphire_remove_replica();
            removeServer(server);
        }

        protected void updateReplicaHostName(
                SapphireServerPolicy serverPolicy, InetSocketAddress host) throws RemoteException {
            ArrayList<SapphireServerPolicy> servers = getServers();
            if (servers == null) {
                return;
            }

            for (Iterator<SapphireServerPolicy> itr = servers.iterator(); itr.hasNext(); ) {
                SapphireServerPolicy server = itr.next();
                if (server.$__getKernelOID().equals(serverPolicy.$__getKernelOID())) {
                    ((KernelObjectStub) server).$__updateHostname(host);
                    ((KernelObjectStub) serverPolicy).$__updateHostname(host);
                    break;
                }
            }
        }

        public void onDestroy() throws RemoteException {
            /* Delete all the servers */
            ArrayList<SapphireServerPolicy> servers = getServers();
            if (servers == null) {
                return;
            }

            for (Iterator<SapphireServerPolicy> itr = servers.iterator(); itr.hasNext(); ) {
                SapphireServerPolicy server = itr.next();
                try {
                    server.sapphire_remove_replica();
                    itr.remove();
                } catch (Exception e) {

                }
            }

            // TODO: Need retry upon failures ??

            try {
                oms().unRegisterSapphireObject(getSapphireObjId());
            } catch (SapphireObjectNotFoundException e) {
                /* Sapphire object not found */
                e.printStackTrace();
            }

            KernelObjectFactory.delete($__getKernelOID());
        }
    }
}
