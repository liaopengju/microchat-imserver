package com.microchat.messageevent;

import com.corundumstudio.socketio.SocketIOClient;

/**
 * 消息事件处理接口
 *
 * @author pengju.liao
 * @since 2019年03月19日
 */
public interface MessageEventService {
    /**
     * 处理client事件
     *
     * @param client
     */
    void handler(SocketIOClient client);
}
