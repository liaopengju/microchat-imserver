package com.microchat.socketio.messages;

/**
 * 图片消息体(其他客户端通讯时消息格式--项目中不用)
 *
 * @author pengju.liao
 * @since 2019年03月27日
 */
public class ImageMessage extends FileMessage {

    /** 图片大小 */
    private String size;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
