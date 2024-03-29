package com.microchat.pubevent.service.impl;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.microchat.client.service.ClientService;
import com.microchat.client.utils.NettyClients;
import com.microchat.commons.redis.utils.RedisPubSubUtil;
import com.microchat.pubevent.enums.HandshakeParamEnum;
import com.microchat.pubevent.enums.PubTypeEnum;
import com.microchat.pubevent.model.PubSubMessage;
import com.microchat.pubevent.service.MessageEventService;
import com.microchat.socketio.messages.ForcedOffNotifyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 连接事件处理实现类
 *
 * @author pengju.liao
 * @since 2019年03月19日
 */
@Service
public class ConnectEventServiceImpl implements MessageEventService {
    /**
     * 日志记录器
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectEventServiceImpl.class);


    @Autowired
    private RedisPubSubUtil redisPubSubUtil;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ClientService clientServiceImpl;

    @Autowired
    private SocketIONamespace messageSocketNameSpace;

    @Override
    public void handler(SocketIOClient client, AckRequest ackRequest, Object object) {
        HandshakeData handData = client.getHandshakeData();
        // 系统标识
        String appId = handData.getSingleUrlParam(HandshakeParamEnum.APP_KEY.getParam());
        // 消息发送方
        String fromUser = handData.getSingleUrlParam(HandshakeParamEnum.FROM_USER_PARAM.getParam());
        //客户端类型
        String clientType = handData.getSingleUrlParam(HandshakeParamEnum.CLIENT_TYPE.getParam());
        LOGGER.info("连接时用户输入参数：appId:{},fromUser:{},clientType:{}", appId, fromUser, clientType);
        /**客户端业务ID*/
        String clientId = new StringBuffer(appId).append("_").append(fromUser).toString();
        /**异地登陆强制下线*/
        forcedOff(client, clientId, clientType);
        /**获取用户所在分组*/
        List<String> rooms = new ArrayList();
        /**上线客户端*/
        onlineClient(client, appId, clientId, clientType, rooms);
    }

    /**
     * 强制下线老客户端
     *
     * @param client   客户端对象
     * @param clientId 客户端Id
     * @param clientId 客户端类型
     */
    private void forcedOff(SocketIOClient client, String clientId, String clientType) {
        String oldSessionId = (String) redisTemplate.opsForHash().get(clientId, clientType);
        if(oldSessionId == null || client.getSessionId().toString().equals(oldSessionId)) {
            LOGGER.info("用户{}不存在在线的客户端或者本次连接的客户端sessionId:{}和在线客户端sessionId:{}是同一个连接。", clientId, client.getSessionId(), oldSessionId);
            return;
        }
        LOGGER.info("用户{}存在在线的客户端sessionId:{}", clientId, client.getSessionId());
        SocketIOClient localClient = NettyClients.getClient(clientId, clientType);
        if(localClient != null && localClient.getSessionId().toString().equals(oldSessionId)) {
            LOGGER.info("本机上找到该用户{}在线客户端sessionId:{}，强制下线客户端。", clientId, oldSessionId);
            clientServiceImpl.forcedOff(client, clientId, clientType);
        } else {
            LOGGER.info("通知集群中连接在其他服务器上该用户{}的客户端sessionId:{},强制下线", clientId, oldSessionId);
            PubSubMessage pubSubMessage = new PubSubMessage();
            pubSubMessage.setPubType(PubTypeEnum.FORCED_OFF.getClassName());
            ForcedOffNotifyMessage forcedOffNotifyMessage = new ForcedOffNotifyMessage();
            forcedOffNotifyMessage.setClientId(clientId);
            forcedOffNotifyMessage.setClientType(clientType);
            forcedOffNotifyMessage.setSessionId(client.getSessionId());
            pubSubMessage.setMessage(forcedOffNotifyMessage);
            redisPubSubUtil.publish(clientId, pubSubMessage);
        }
    }

    /**
     * 上线客户端
     *
     * @param client   客户端对象
     * @param appId    系统Id
     * @param clientId 客户端Id
     * @param rooms    群组集合
     */
    private void onlineClient(SocketIOClient client, String appId, String clientId, String clientType, List<String> rooms) {
        LOGGER.info("本机缓存中保存对应关系clientId:{}", clientId);
        NettyClients.putClient(clientId, clientType, client);
        LOGGER.info("redis中保存在线用户信息。clientId:{}，clientType:{},sessionId{}", clientId, clientType, client.getSessionId());
        redisTemplate.opsForHash().put(clientId, clientType, client.getSessionId().toString());
        LOGGER.info("将用户clientId{},添加到分组中", clientId);
        NettyClients.addClientToRoom(appId, clientId, clientType, rooms);
        LOGGER.info("用户clientId{}开始订阅通道消息", clientId);
        subscribeClientId(appId, clientId, rooms);
    }

    /**
     * 订阅客户端和客户端所在群组消息
     *
     * @param appId    系统Id
     * @param clientId 客户端Id
     * @param rooms    群组Id集合
     */
    private void subscribeClientId(String appId, String clientId, List<String> rooms) {
        List<String> channels = new ArrayList();
        for(String room : rooms) {
            channels.add(appId + "_" + room);
        }
        channels.add(clientId);
        redisPubSubUtil.subscribe(channels);
    }
}
