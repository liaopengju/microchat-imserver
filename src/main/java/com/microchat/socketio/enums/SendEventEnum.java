package com.microchat.socketio.enums;

/**
 * 发送消息枚举
 *
 * @author pengju.liao
 * @since 2019年03月27日
 */
public enum SendEventEnum {
    /** 消息事件 */
    MESSAGE("message");

    /** 事件 */
    private String event;

    public String getEvent() {
        return event;
    }

    SendEventEnum(String event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
