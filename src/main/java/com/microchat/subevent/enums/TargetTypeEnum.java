package com.microchat.subevent.enums;

/**
 * 消息接受方类型
 *
 * @author pengju.liao
 * @since 2019年03月25日
 */
public enum TargetTypeEnum {
    /** 单聊 */
    CHAT("userChat"),
    /** 群聊 */
    GROUP_CHAT("groupChat");

    /** 消息接受方类型 */
    private String targetType;

    public String getTargetType() {
        return targetType;
    }

    TargetTypeEnum(String targetType) {
        this.targetType = targetType;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
