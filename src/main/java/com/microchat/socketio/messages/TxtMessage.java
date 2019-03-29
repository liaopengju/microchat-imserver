package com.microchat.socketio.messages;

/**
 * 文件消息(其他客户端通讯时消息格式--项目中不用)
 *
 * @author pengju.liao
 * @since 2019年03月27日
 */
public class TxtMessage extends Message {

    /** 消息内容 */
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
