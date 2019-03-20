package com.microchat.event;

import com.corundumstudio.socketio.SocketIOClient;

/**
 * 连接事件处理接口
 *
 * @author pengju.liao
 * @since 2019年03月19日
 */
public interface ConnectEventService {
    /**
     * 处理client 连接事件
     *
     * @param client
     */
    void handler(SocketIOClient client);
}
