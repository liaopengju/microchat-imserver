package com.microchat.messageevent.impl;

import com.corundumstudio.socketio.SocketIOClient;
import com.microchat.client.NettyClients;
import com.microchat.commons.redis.utils.RedisPubSubUtil;
import com.microchat.messageevent.ConnectEventService;
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
public class ConnectEventServiceImpl implements ConnectEventService {
    /** 日志记录器 */
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectEventServiceImpl.class);

    private static final String APP_KEY = "appId";

    private static final String FROM_USER_PARAM = "fromUser";

    @Autowired
    private RedisPubSubUtil redisPubSubUtil;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void handler(SocketIOClient client) {
        // 系统标识
        String appId = client.getHandshakeData().getSingleUrlParam(APP_KEY);
        // 消息发送方
        String fromUser = client.getHandshakeData().getSingleUrlParam(FROM_USER_PARAM);
        /**客户端业务ID*/
        String clientId = new StringBuffer(appId).append("_").append(fromUser).toString();
        LOGGER.info("客户端{}上线", clientId);
        /**获取用户所在分组*/
        List<String> rooms = new ArrayList();
        /**本机缓存中保存对应关系*/
        NettyClients.putClient(clientId, client);
        /**redis中缓存在线状态并实现异地登陆强制下线*/
        updateClientOnlineStatus(client, clientId);
        /**将客户端添加到分组中*/
        NettyClients.addClientToRoom(appId, clientId, rooms);
        /**订阅客户端消息*/
        subscribeClientId(appId, clientId, rooms);
    }

    /**
     * 更新客户端在线状态
     *
     * @param client
     * @param clientId 客户端业务Id
     */
    private void updateClientOnlineStatus(SocketIOClient client, String clientId) {
        String oldSessionId = (String) redisTemplate.opsForValue().get(clientId);
        if(oldSessionId != null && !client.getSessionId().toString().equals(oldSessionId)) {
            //通知老客户端被异地登陆,需要强制下线
            ForcedOffNotifyMessage forcedOffNotifyMessage = new ForcedOffNotifyMessage();
            forcedOffNotifyMessage.setClientId(clientId);
            forcedOffNotifyMessage.setSessionId(client.getSessionId());
            redisPubSubUtil.publish(clientId, forcedOffNotifyMessage);
        }
        redisTemplate.opsForValue().set(clientId, client.getSessionId().toString());
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
        //订阅群组和单聊消息
        redisPubSubUtil.subscribe(channels);
    }
}
