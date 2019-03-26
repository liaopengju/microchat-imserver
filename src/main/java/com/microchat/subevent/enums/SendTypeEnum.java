package com.microchat.subevent.enums;

/**
 * 发送类型
 *
 * @author pengju.liao
 * @since 2019年03月25日
 */
public enum SendTypeEnum {
    /** 单聊 */
    CHAT("chat"),
    /** 群聊 */
    GROUP_CHAT("groupChat");

    /** 发送类型 */
    private String sendType;

    public String getSendType() {
        return sendType;
    }

    SendTypeEnum(String sendType) {
        this.sendType = sendType;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
