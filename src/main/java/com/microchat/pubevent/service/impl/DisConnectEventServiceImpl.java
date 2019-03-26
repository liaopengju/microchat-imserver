package com.microchat.pubevent.service.impl;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.microchat.client.service.ClientService;
import com.microchat.client.utils.NettyClients;
import com.microchat.commons.redis.utils.RedisPubSubUtil;
import com.microchat.pubevent.service.MessageEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 断开连接事件服务类
 *
 * @author pengju.liao
 * @since 2019年03月22日
 */
@Service
public class DisConnectEventServiceImpl implements MessageEventService {
    /** 日志记录器 */
    private static final Logger LOGGER = LoggerFactory.getLogger(DisConnectEventServiceImpl.class);

    private static final String APP_KEY = "appId";

    private static final String FROM_USER_PARAM = "fromUser";

    private static final String CLIENT_TYPE = "clientType";

    @Autowired
    private RedisPubSubUtil redisPubSubUtil;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ClientService clientServiceImpl;

    @Override
    public void handler(SocketIOClient client, AckRequest ackRequest, Object object) {
        // 系统标识
        String appId = client.getHandshakeData().getSingleUrlParam(APP_KEY);
        // 消息发送方
        String fromUser = client.getHandshakeData().getSingleUrlParam(FROM_USER_PARAM);
        //客户端类型
        String clientType = client.getHandshakeData().getSingleUrlParam(CLIENT_TYPE);
        LOGGER.info("连接时用户输入参数：appId:{},fromUser:{},clientType:{}", appId, fromUser, clientType);
        /**客户端业务ID*/
        String clientId = new StringBuffer(appId).append("_").append(fromUser).toString();
        if(clientServiceImpl.isNewClient(clientId, client)) {
            //删除redis中缓存
            redisTemplate.delete(clientId);
        }
        //删除本地缓存中的用户
        NettyClients.removeClient(clientId,clientType);
        //取消订阅
        redisPubSubUtil.unSubscribe(clientId);
    }
}
