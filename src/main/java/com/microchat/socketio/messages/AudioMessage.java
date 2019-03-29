package com.microchat.socketio.messages;

/**
 * 语音类型消息(其他客户端通讯时消息格式--项目中不用)
 *
 * @author pengju.liao
 * @since 2019年03月27日
 */
public class AudioMessage extends FileMessage {

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
