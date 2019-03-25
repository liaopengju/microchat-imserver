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

    /**
     * 在线客户端集合类
     */
    private static ConcurrentHashMap<String, ConcurrentHashMap<String, SocketIOClient>> clientsMap = new ConcurrentHashMap();

    /**
     * 缓存中添加客户端
     *
     * @param clientId   客户端Id
     * @param clientType 客户端类型
     * @param client     客户端对象
     */
    public static void putClient(String clientId, String clientType, SocketIOClient client) {
        ConcurrentHashMap<String, SocketIOClient> clientMap = clientsMap.get(clientId);
        if (clientMap == null) {
            clientMap = new ConcurrentHashMap();
            clientMap.put(clientType, client);
        }
        clientsMap.put(clientId, clientMap);
    }


    /**
     * 根据clientId获取缓存中的客户端
     *
     * @param clientId 客户端Id
     */
    public static ConcurrentHashMap<String, SocketIOClient> getClients(String clientId) {
        return clientsMap.get(clientId);
    }


    /**
     * 获取缓存中的客户端
     *
     * @param clientId   客户端Id
     * @param clientType 客户端类型
     */
    public static SocketIOClient getClient(String clientId, String clientType) {
        ConcurrentHashMap<String, SocketIOClient> clientMap = clientsMap.get(clientId);
        if (clientMap != null) {
            return clientMap.get(clientType);
        }
        return null;
    }


    /**
     * 删除缓存中的客户端
     *
     * @param clientId   客户端Id
     * @param clientType 客户端类型
     */
    public static void removeClient(String clientId, String clientType) {
        ConcurrentHashMap<String, SocketIOClient> clientMap = clientsMap.get(clientId);
        if (clientMap != null) {
            clientMap.remove(clientType);
        }
    }


    /**
     * 添加客户端到rooms
     *
     * @param appId      系统编号
     * @param clientId   客户端编号
     * @param clientType 客户端类型
     * @param rooms      群组列表
     */
    public static void addClientToRoom(String appId, String clientId, String clientType, List<String> rooms) {
        SocketIOClient userClient = getClient(clientId, clientType);
        if (userClient != null) {
            for (String roomId : rooms) {
                if (userClient != null) {
                    userClient.joinRoom(appId + "_" + roomId);
                }
            }
        }
    }


    /**
     * 删除分组中用户
     *
     * @param appId      系统Id
     * @param clientId   客户端Id
     * @param clientType 客户端类型
     * @param roomId     群组Id
     * @return
     */
    public static void deleteRoomUser(String appId, String clientId, String clientType, String roomId) {
        SocketIOClient userClient = getClient(clientId, clientType);
        if (userClient != null) {
            userClient.leaveRoom(appId + "_" + roomId);
        }
    }
}