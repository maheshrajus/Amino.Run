// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: KernelServerApiToRuntime.proto

package sapphire.kernel.runtime;

public interface CreateChildSObjRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:sapphire.kernel.runtime.CreateChildSObjRequest)
    com.google.protobuf.MessageLiteOrBuilder {

  /**
   * <code>optional string sObjName = 1;</code>
   */
  java.lang.String getSObjName();
  /**
   * <code>optional string sObjName = 1;</code>
   */
  com.google.protobuf.ByteString
      getSObjNameBytes();

  /**
   * <code>optional string sObjParentSObjId = 2;</code>
   */
  java.lang.String getSObjParentSObjId();
  /**
   * <code>optional string sObjParentSObjId = 2;</code>
   */
  com.google.protobuf.ByteString
      getSObjParentSObjIdBytes();

  /**
   * <code>optional .sapphire.kernel.runtime.DMInfo sObjDMInfo = 3;</code>
   */
  boolean hasSObjDMInfo();
  /**
   * <code>optional .sapphire.kernel.runtime.DMInfo sObjDMInfo = 3;</code>
   */
  sapphire.kernel.runtime.DMInfo getSObjDMInfo();

  /**
   * <code>optional bytes sObjReplicaObject = 4;</code>
   */
  com.google.protobuf.ByteString getSObjReplicaObject();
}