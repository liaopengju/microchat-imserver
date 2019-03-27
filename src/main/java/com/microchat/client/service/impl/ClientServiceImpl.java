package com.microchat.client.service.impl;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.microchat.client.enums.SendEventEnum;
import com.microchat.client.service.ClientService;
import com.microchat.client.utils.NettyClients;
import com.microchat.commons.redis.utils.RedisPubSubUtil;
import com.microchat.socketio.messages.UserSendMessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 客户端相关操作类
 *
 * @author pengju.liao
 * @since 2019年03月22日
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private RedisPubSubUtil redisPubSubUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SocketIONamespace messageSocketNameSpace;

    @Override
    public void forcedOff(SocketIOClient client, String clientId, String clientType) {
        //取消该用户单聊订阅
        redisPubSubUtil.unSubscribe(clientId);
        //删除该用户本地缓存的客户端
        NettyClients.removeClient(clientId, clientType);
        //删除redis该用户缓存
        redisTemplate.delete(clientId);
        //发送强制下线通知并断开连接
        client.disconnect();
    }

    @Override
    public Boolean isNewClient(String clientId, SocketIOClient client) {
        //获取最新的客户端标识
        String sessionId = (String) redisTemplate.opsForValue().get(clientId);
        // 当前客户端是最新客户端
        return client.getSessionId().toString().equals(sessionId);
    }

    @Override
    public void sendMessageToClient(UserSendMessageVO message) {
        String clientId = message.getAppId() + "_" + message.getTarget();
        SocketIOClient socketIOClient = NettyClients.getClient(clientId, message.getClientType());
        if(socketIOClient != null) {
            socketIOClient.sendEvent(SendEventEnum.MESSAGE.getEvent(), message);
        }
    }

    @Override
    public void sendMessageToRoom(UserSendMessageVO message) {
        String roomId = message.getAppId() + "_" + message.getTarget();
        String clientId = message.getAppId() + "_" + message.getFromUser();
        SocketIOClient socketIOClient = NettyClients.getClient(clientId, message.getClientType());
        if(socketIOClient != null) {
            messageSocketNameSpace.getRoomOperations(roomId).sendEvent(SendEventEnum.MESSAGE.getEvent(), socketIOClient, message);
        } else {
            messageSocketNameSpace.getRoomOperations(roomId).sendEvent(SendEventEnum.MESSAGE.getEvent(), message);
        }
    }
}
