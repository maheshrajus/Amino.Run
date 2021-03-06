package amino.run.policy.cache.explicitcaching;

import amino.run.common.AppObject;
import amino.run.policy.DefaultPolicy;
import java.io.Serializable;
import java.util.ArrayList;

// caching with explicit push and pull calls from the application
// The guarded SO MUST provide pull/push method body (placeholder) of ExplicitCacher interface
// TODO: stub generator to generate related methods
// TODO: inherit from a common caching base class which has getCopy/syncCopy methods
public class ExplicitCachingPolicy extends DefaultPolicy {
    public static class ClientPolicy extends DefaultClientPolicy {
        private AppObject cachedCopy = null;

        private void pull() throws Exception {
            AppObject appObject = ((ServerPolicy) this.getServer()).getCopy();
            if (!(appObject.getObject() instanceof ExplicitCacher)) {
                throw new IllegalArgumentException("should be subclass of ExplicitCacher");
            }

            this.cachedCopy = appObject;
        }

        private void push() throws Exception {
            if (this.cachedCopy != null) {
                ((ServerPolicy) this.getServer()).syncCopy(this.cachedCopy.getObject());
                this.cachedCopy = null;
            }
        }

        // for unit test use
        AppObject getCachedCopy() {
            return cachedCopy;
        }

        private Boolean isPull(String method) {
            // todo: stringent check more than simple base name
            return method.endsWith(".pull()");
        }

        private Boolean isPush(String method) {
            // todo: stringent check more than simple base name
            return method.endsWith(".push()");
        }

        @Override
        public Object onRPC(String method, ArrayList<Object> params) throws Exception {
            // Extract app method name and params from input parameters
            AppContext context = extractAppContext(method, params);

            // All the operations between pull/push calls goes against local cache
            if (this.isPull(context.getAppMethod())) {
                this.pull();
                return null;
            } else if (this.isPush(context.getAppMethod())) {
                this.push();
                return null;
            } else if (this.cachedCopy != null) {
                return this.cachedCopy.invoke(method, params);
            }

            /* When app object is explicitly not pulled, rpc calls are directed to server */
            return this.getServer().onRPC(method, params);
        }
    }

    public static class ServerPolicy extends DefaultServerPolicy {
        public AppObject getCopy() {
            return this.appObject;
        }

        public void syncCopy(Serializable object) {
            appObject.setObject(object);
        }
    }

    public static class GroupPolicy extends DefaultGroupPolicy {}
}
