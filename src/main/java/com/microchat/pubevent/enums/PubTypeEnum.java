package com.microchat.pubevent.enums;

/**
 * 发布订阅类型枚举
 *
 * @author pengju.liao
 * @since 2019年03月22日
 */
public enum PubTypeEnum {

    /**强制下线处理接口*/
    FORCED_OFF("forcedOffNotifyMessageHandler"),
    /**发送消息接口*/
    SEND_MESSAGE("sendMessageHandler");

    /**消费接口对应的类型名称*/
    private String className;

    public String getClassName() {
        return className;
    }

    PubTypeEnum(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

}
