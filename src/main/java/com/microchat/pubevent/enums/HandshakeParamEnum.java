package com.microchat.pubevent.enums;

/**
 * client  请求参数
 *
 * @author pengju.liao
 * @since 2019年03月27日
 */
public enum HandshakeParamEnum {

    /** 系统Id */
    APP_KEY("appId"),
    /** 客户端类型 */
    CLIENT_TYPE("clientType"),
    /** 消息发送方 */
    FROM_USER_PARAM("fromUser");

    /** 参数名称 */
    private String param;

    public String getParam() {
        return param;
    }

    HandshakeParamEnum(String param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
