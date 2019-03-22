package com.microchat.client.service;

import com.corundumstudio.socketio.SocketIOClient;

import java.util.List;

/**
 * 客户端操作服务
 *
 * @author pengju.liao
 * @since 2019年03月22日
 */
public interface ClientService {
    /**
     * 强制下线客户端
     * @param client 客户端对象
     * @param clientId 客户端Id
     * @param appId 系统编号
     */
    void forcedOff(SocketIOClient client, String clientId, String appId);
}
