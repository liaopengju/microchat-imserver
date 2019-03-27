package com.microchat.pubevent.service.impl;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOClient;
import com.microchat.commons.redis.utils.RedisPubSubUtil;
import com.microchat.pubevent.enums.HandshakeParamEnum;
import com.microchat.pubevent.enums.PubTypeEnum;
import com.microchat.pubevent.model.PubSubMessage;
import com.microchat.pubevent.service.MessageEventService;
import com.microchat.socketio.messages.UserSendMessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 发送消息事件处理类
 *
 * @author pengju.liao
 * @since 2019年03月22日
 */
@Service
public class SendMessageEventServiceImpl implements MessageEventService {

    @Autowired
    private RedisPubSubUtil redisPubSubUtil;

    @Override
    public void handler(SocketIOClient client, AckRequest ackRequest, Object object) {
        HandshakeData handshakeData = client.getHandshakeData();
        // 系统标识
        String appId = handshakeData.getSingleUrlParam(HandshakeParamEnum.APP_KEY.getParam());
        // 消息发送方
        String fromUser = handshakeData.getSingleUrlParam(HandshakeParamEnum.FROM_USER_PARAM.getParam());
        //客户端类型
        String clientType = handshakeData.getSingleUrlParam(HandshakeParamEnum.CLIENT_TYPE.getParam());
        UserSendMessageVO message = (UserSendMessageVO) object;
        message.setFromUser(fromUser);
        message.setAppId(appId);
        message.setClientType(clientType);
        PubSubMessage pubSubMessage = new PubSubMessage();
        pubSubMessage.setPubType(PubTypeEnum.SEND_MESSAGE.getClassName());
        pubSubMessage.setMessage(message);
        redisPubSubUtil.publish(message.getAppId() + "_" + message.getTarget(), pubSubMessage);
        ackRequest.sendAckData("发送成功");
    }
}
