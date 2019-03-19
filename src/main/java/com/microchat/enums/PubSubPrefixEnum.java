package com.microchat.enums;

/**
 * 发布订阅前缀
 *
 * @author pengju.liao
 * @since 2019年03月19日
 */
public enum PubSubPrefixEnum {

    /**强制下线消息*/
    FORCEDOFF("forcedOff_"),
    /**单聊消息*/
    ALONEMESSAGE("alonMessage_"),
    /**群聊消息*/
    GROUPMESSAGE("groupMessage_");

    /**前缀*/
    private String prefix;

    public String getPrefix() {
        return prefix;
    }

    PubSubPrefixEnum(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
