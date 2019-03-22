package com.microchat.client.utils;

import com.corundumstudio.socketio.SocketIOClient;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * netty 客户端工具类
 *
 * @author pengju.liao
 * @since 2019年03月19日
 */
public class NettyClients {

    /** 在线客户端集合类 */
    private static ConcurrentHashMap<String, SocketIOClient> clientsMap = new ConcurrentHashMap();

    /**
     * 缓存中添加客户端
     *
     * @param clientId 客户端Id
     * @param client   客户端对象
     */
    public static void putClient(String clientId, SocketIOClient client) {
        clientsMap.put(clientId, client);
    }

    /**
     * 获取缓存中的客户端
     *
     * @param clientId 客户端Id
     */
    public static SocketIOClient getClient(String clientId) {
        return clientsMap.get(clientId);
    }


    /**
     * 删除缓存中的客户端
     *
     * @param clientId 客户端Id
     */
    public static void removeClient(String clientId) {
        clientsMap.remove(clientId);
    }


    /**
     * 添加客户端到rooms
     *
     * @param appId    系统编号
     * @param clientId 客户端编号
     * @param rooms    群组列表
     */
    public static void addClientToRoom(String appId, String clientId, List<String> rooms) {
        for(String roomId : rooms) {
            SocketIOClient userClient = getClient(clientId);
            if(userClient != null) {
                userClient.joinRoom(appId + "_" + roomId);
            }
        }
    }

    /**
     * 删除分组中用户
     *
     * @param appId    系统Id
     * @param clientId 客户端Id
     * @param roomId   群组Id
     * @return
     */
    public static void deleteRoomUser(String appId, String clientId, String roomId) {
        SocketIOClient userClient = getClient(clientId);
        if(userClient != null) {
            userClient.leaveRoom(appId + "_" + roomId);
        }
    }
}