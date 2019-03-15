package com.microchat.socketio.enums;

/**
 * netty-socketIO NameSpace
 *
 * @author pengju.liao
 * @since 2018年09月26日
 */
public enum NameSpaceEnum {
    /**IM*/
    MESSAGE("/im/message");

    /**namespace*/
    private String namespace;

    public String getNamespace() {
        return namespace;
    }

    NameSpaceEnum(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
