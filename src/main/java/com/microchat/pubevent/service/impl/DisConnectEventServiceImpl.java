package com.microchat.pubevent.service.impl;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOClient;
import com.microchat.client.service.ClientService;
import com.microchat.client.utils.NettyClients;
import com.microchat.commons.redis.utils.RedisPubSubUtil;
import com.microchat.pubevent.enums.HandshakeParamEnum;
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

    @Autowired
    private RedisPubSubUtil redisPubSubUtil;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ClientService clientServiceImpl;

    @Override
    public void handler(SocketIOClient client, AckRequest ackRequest, Object object) {
        HandshakeData handshakeData = client.getHandshakeData();
        // 系统标识
        String appId = handshakeData.getSingleUrlParam(HandshakeParamEnum.APP_KEY.getParam());
        // 消息发送方
        String fromUser = handshakeData.getSingleUrlParam(HandshakeParamEnum.FROM_USER_PARAM.getParam());
        //客户端类型
        String clientType = handshakeData.getSingleUrlParam(HandshakeParamEnum.CLIENT_TYPE.getParam());
        LOGGER.info("连接时用户输入参数：appId:{},fromUser:{},clientType:{}", appId, fromUser, clientType);
        /**客户端业务ID*/
        String clientId = new StringBuffer(appId).append("_").append(fromUser).toString();
        if(clientServiceImpl.isNewClient(clientId, clientType, client)) {
            //删除redis中缓存
            redisTemplate.delete(clientId);
        }
        //删除本地缓存中的用户
        NettyClients.removeClient(clientId, clientType);
        if(NettyClients.getClients(clientId).size() == 0) {
            //取消订阅
            redisPubSubUtil.unSubscribe(clientId);
        }
    }
}
