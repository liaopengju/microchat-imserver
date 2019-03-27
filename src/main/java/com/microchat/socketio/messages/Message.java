package com.microchat.socketio.messages;

import java.io.Serializable;

/**
 * 基础消息体
 *
 * @author pengju.liao
 * @since 2019年03月27日
 */
public class Message implements Serializable {

    private static final long serialVersionUID = 4462944056534393652L;

    /** 消息类型 */
    private String msgType;

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
