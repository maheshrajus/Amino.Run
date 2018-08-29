// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: RuntimeApiToKernelServer.proto

package sapphire.runtime.kernel;

/**
 * Protobuf type {@code sapphire.runtime.kernel.DMInfo}
 */
public  final class DMInfo extends
    com.google.protobuf.GeneratedMessageLite<
        DMInfo, DMInfo.Builder> implements
    // @@protoc_insertion_point(message_implements:sapphire.runtime.kernel.DMInfo)
    DMInfoOrBuilder {
  private DMInfo() {
    clientPolicy_ = "";
    serverPolicy_ = "";
    groupPolicy_ = "";
  }
  public static final int CLIENTPOLICY_FIELD_NUMBER = 1;
  private java.lang.String clientPolicy_;
  /**
   * <code>optional string clientPolicy = 1;</code>
   */
  public java.lang.String getClientPolicy() {
    return clientPolicy_;
  }
  /**
   * <code>optional string clientPolicy = 1;</code>
   */
  public com.google.protobuf.ByteString
      getClientPolicyBytes() {
    return com.google.protobuf.ByteString.copyFromUtf8(clientPolicy_);
  }
  /**
   * <code>optional string clientPolicy = 1;</code>
   */
  private void setClientPolicy(
      java.lang.String value) {
    if (value == null) {
    throw new NullPointerException();
  }
  
    clientPolicy_ = value;
  }
  /**
   * <code>optional string clientPolicy = 1;</code>
   */
  private void clearClientPolicy() {
    
    clientPolicy_ = getDefaultInstance().getClientPolicy();
  }
  /**
   * <code>optional string clientPolicy = 1;</code>
   */
  private void setClientPolicyBytes(
      com.google.protobuf.ByteString value) {
    if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
    
    clientPolicy_ = value.toStringUtf8();
  }

  public static final int SERVERPOLICY_FIELD_NUMBER = 2;
  private java.lang.String serverPolicy_;
  /**
   * <code>optional string serverPolicy = 2;</code>
   */
  public java.lang.String getServerPolicy() {
    return serverPolicy_;
  }
  /**
   * <code>optional string serverPolicy = 2;</code>
   */
  public com.google.protobuf.ByteString
      getServerPolicyBytes() {
    return com.google.protobuf.ByteString.copyFromUtf8(serverPolicy_);
  }
  /**
   * <code>optional string serverPolicy = 2;</code>
   */
  private void setServerPolicy(
      java.lang.String value) {
    if (value == null) {
    throw new NullPointerException();
  }
  
    serverPolicy_ = value;
  }
  /**
   * <code>optional string serverPolicy = 2;</code>
   */
  private void clearServerPolicy() {
    
    serverPolicy_ = getDefaultInstance().getServerPolicy();
  }
  /**
   * <code>optional string serverPolicy = 2;</code>
   */
  private void setServerPolicyBytes(
      com.google.protobuf.ByteString value) {
    if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
    
    serverPolicy_ = value.toStringUtf8();
  }

  public static final int GROUPPOLICY_FIELD_NUMBER = 3;
  private java.lang.String groupPolicy_;
  /**
   * <code>optional string groupPolicy = 3;</code>
   */
  public java.lang.String getGroupPolicy() {
    return groupPolicy_;
  }
  /**
   * <code>optional string groupPolicy = 3;</code>
   */
  public com.google.protobuf.ByteString
      getGroupPolicyBytes() {
    return com.google.protobuf.ByteString.copyFromUtf8(groupPolicy_);
  }
  /**
   * <code>optional string groupPolicy = 3;</code>
   */
  private void setGroupPolicy(
      java.lang.String value) {
    if (value == null) {
    throw new NullPointerException();
  }
  
    groupPolicy_ = value;
  }
  /**
   * <code>optional string groupPolicy = 3;</code>
   */
  private void clearGroupPolicy() {
    
    groupPolicy_ = getDefaultInstance().getGroupPolicy();
  }
  /**
   * <code>optional string groupPolicy = 3;</code>
   */
  private void setGroupPolicyBytes(
      com.google.protobuf.ByteString value) {
    if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
    
    groupPolicy_ = value.toStringUtf8();
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!clientPolicy_.isEmpty()) {
      output.writeString(1, getClientPolicy());
    }
    if (!serverPolicy_.isEmpty()) {
      output.writeString(2, getServerPolicy());
    }
    if (!groupPolicy_.isEmpty()) {
      output.writeString(3, getGroupPolicy());
    }
  }

  public int getSerializedSize() {
    int size = memoizedSerializedSize;
    if (size != -1) return size;

    size = 0;
    if (!clientPolicy_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeStringSize(1, getClientPolicy());
    }
    if (!serverPolicy_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeStringSize(2, getServerPolicy());
    }
    if (!groupPolicy_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeStringSize(3, getGroupPolicy());
    }
    memoizedSerializedSize = size;
    return size;
  }

  public static sapphire.runtime.kernel.DMInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static sapphire.runtime.kernel.DMInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static sapphire.runtime.kernel.DMInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static sapphire.runtime.kernel.DMInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static sapphire.runtime.kernel.DMInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static sapphire.runtime.kernel.DMInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static sapphire.runtime.kernel.DMInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input);
  }
  public static sapphire.runtime.kernel.DMInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static sapphire.runtime.kernel.DMInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static sapphire.runtime.kernel.DMInfo parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }

  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(sapphire.runtime.kernel.DMInfo prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }

  /**
   * Protobuf type {@code sapphire.runtime.kernel.DMInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageLite.Builder<
        sapphire.runtime.kernel.DMInfo, Builder> implements
      // @@protoc_insertion_point(builder_implements:sapphire.runtime.kernel.DMInfo)
      sapphire.runtime.kernel.DMInfoOrBuilder {
    // Construct using sapphire.runtime.kernel.DMInfo.newBuilder()
    private Builder() {
      super(DEFAULT_INSTANCE);
    }


    /**
     * <code>optional string clientPolicy = 1;</code>
     */
    public java.lang.String getClientPolicy() {
      return instance.getClientPolicy();
    }
    /**
     * <code>optional string clientPolicy = 1;</code>
     */
    public com.google.protobuf.ByteString
        getClientPolicyBytes() {
      return instance.getClientPolicyBytes();
    }
    /**
     * <code>optional string clientPolicy = 1;</code>
     */
    public Builder setClientPolicy(
        java.lang.String value) {
      copyOnWrite();
      instance.setClientPolicy(value);
      return this;
    }
    /**
     * <code>optional string clientPolicy = 1;</code>
     */
    public Builder clearClientPolicy() {
      copyOnWrite();
      instance.clearClientPolicy();
      return this;
    }
    /**
     * <code>optional string clientPolicy = 1;</code>
     */
    public Builder setClientPolicyBytes(
        com.google.protobuf.ByteString value) {
      copyOnWrite();
      instance.setClientPolicyBytes(value);
      return this;
    }

    /**
     * <code>optional string serverPolicy = 2;</code>
     */
    public java.lang.String getServerPolicy() {
      return instance.getServerPolicy();
    }
    /**
     * <code>optional string serverPolicy = 2;</code>
     */
    public com.google.protobuf.ByteString
        getServerPolicyBytes() {
      return instance.getServerPolicyBytes();
    }
    /**
     * <code>optional string serverPolicy = 2;</code>
     */
    public Builder setServerPolicy(
        java.lang.String value) {
      copyOnWrite();
      instance.setServerPolicy(value);
      return this;
    }
    /**
     * <code>optional string serverPolicy = 2;</code>
     */
    public Builder clearServerPolicy() {
      copyOnWrite();
      instance.clearServerPolicy();
      return this;
    }
    /**
     * <code>optional string serverPolicy = 2;</code>
     */
    public Builder setServerPolicyBytes(
        com.google.protobuf.ByteString value) {
      copyOnWrite();
      instance.setServerPolicyBytes(value);
      return this;
    }

    /**
     * <code>optional string groupPolicy = 3;</code>
     */
    public java.lang.String getGroupPolicy() {
      return instance.getGroupPolicy();
    }
    /**
     * <code>optional string groupPolicy = 3;</code>
     */
    public com.google.protobuf.ByteString
        getGroupPolicyBytes() {
      return instance.getGroupPolicyBytes();
    }
    /**
     * <code>optional string groupPolicy = 3;</code>
     */
    public Builder setGroupPolicy(
        java.lang.String value) {
      copyOnWrite();
      instance.setGroupPolicy(value);
      return this;
    }
    /**
     * <code>optional string groupPolicy = 3;</code>
     */
    public Builder clearGroupPolicy() {
      copyOnWrite();
      instance.clearGroupPolicy();
      return this;
    }
    /**
     * <code>optional string groupPolicy = 3;</code>
     */
    public Builder setGroupPolicyBytes(
        com.google.protobuf.ByteString value) {
      copyOnWrite();
      instance.setGroupPolicyBytes(value);
      return this;
    }

    // @@protoc_insertion_point(builder_scope:sapphire.runtime.kernel.DMInfo)
  }
  protected final Object dynamicMethod(
      com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
      Object arg0, Object arg1) {
    switch (method) {
      case NEW_MUTABLE_INSTANCE: {
        return new sapphire.runtime.kernel.DMInfo();
      }
      case IS_INITIALIZED: {
        return DEFAULT_INSTANCE;
      }
      case MAKE_IMMUTABLE: {
        return null;
      }
      case NEW_BUILDER: {
        return new Builder();
      }
      case VISIT: {
        Visitor visitor = (Visitor) arg0;
        sapphire.runtime.kernel.DMInfo other = (sapphire.runtime.kernel.DMInfo) arg1;
        clientPolicy_ = visitor.visitString(!clientPolicy_.isEmpty(), clientPolicy_,
            !other.clientPolicy_.isEmpty(), other.clientPolicy_);
        serverPolicy_ = visitor.visitString(!serverPolicy_.isEmpty(), serverPolicy_,
            !other.serverPolicy_.isEmpty(), other.serverPolicy_);
        groupPolicy_ = visitor.visitString(!groupPolicy_.isEmpty(), groupPolicy_,
            !other.groupPolicy_.isEmpty(), other.groupPolicy_);
        if (visitor == com.google.protobuf.GeneratedMessageLite.MergeFromVisitor
            .INSTANCE) {
        }
        return this;
      }
      case MERGE_FROM_STREAM: {
        com.google.protobuf.CodedInputStream input =
            (com.google.protobuf.CodedInputStream) arg0;
        com.google.protobuf.ExtensionRegistryLite extensionRegistry =
            (com.google.protobuf.ExtensionRegistryLite) arg1;
        try {
          boolean done = false;
          while (!done) {
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                break;
              default: {
                if (!input.skipField(tag)) {
                  done = true;
                }
                break;
              }
              case 10: {
                String s = input.readStringRequireUtf8();

                clientPolicy_ = s;
                break;
              }
              case 18: {
                String s = input.readStringRequireUtf8();

                serverPolicy_ = s;
                break;
              }
              case 26: {
                String s = input.readStringRequireUtf8();

                groupPolicy_ = s;
                break;
              }
            }
          }
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw new RuntimeException(e.setUnfinishedMessage(this));
        } catch (java.io.IOException e) {
          throw new RuntimeException(
              new com.google.protobuf.InvalidProtocolBufferException(
                  e.getMessage()).setUnfinishedMessage(this));
        } finally {
        }
      }
      case GET_DEFAULT_INSTANCE: {
        return DEFAULT_INSTANCE;
      }
      case GET_PARSER: {
        if (PARSER == null) {    synchronized (sapphire.runtime.kernel.DMInfo.class) {
            if (PARSER == null) {
              PARSER = new DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            }
          }
        }
        return PARSER;
      }
    }
    throw new UnsupportedOperationException();
  }


  // @@protoc_insertion_point(class_scope:sapphire.runtime.kernel.DMInfo)
  private static final sapphire.runtime.kernel.DMInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new DMInfo();
    DEFAULT_INSTANCE.makeImmutable();
  }

  public static sapphire.runtime.kernel.DMInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static volatile com.google.protobuf.Parser<DMInfo> PARSER;

  public static com.google.protobuf.Parser<DMInfo> parser() {
    return DEFAULT_INSTANCE.getParserForType();
  }
}
