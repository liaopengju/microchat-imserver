package com.microchat.client.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.microchat.socketio.messages.Message;

/**
 * 客户端操作服务
 *
 * @author pengju.liao
 * @since 2019年03月22日
 */
public interface ClientService {
    /**
     * 强制下线客户端
     *
     * @param client   客户端对象
     * @param clientId 客户端Id
     * @param appId    系统编号
     */
    void forcedOff(SocketIOClient client, String clientId, String appId);

    /**
     * 判断当前客户端是否最新客户端
     *
     * @param clientId 客户端Id
     * @param client   客户端对象
     * @return
     */
    Boolean isNewClient(String clientId, SocketIOClient client);

    /**
     * 发送消息到客户端
     *
     * @param message 消息体
     */
    void sendMessageToClient(Message message);

    /**
     * 发消息到群组
     *
     * @param message 消息体
     */
    void sendMessageToRoom(Message message);
}
