package com.microchat.messageevent.impl;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.microchat.client.service.ClientService;
import com.microchat.commons.redis.utils.RedisPubSubUtil;
import com.microchat.messageevent.MessageEventService;
import com.microchat.pubsubevent.enums.PubSubTypeEnum;
import com.microchat.pubsubevent.model.PubSubMessage;
import com.microchat.socketio.messages.Message;
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

    private static final String APP_KEY = "appId";

    private static final String FROM_USER_PARAM = "fromUser";

    private static final String CLIENT_TYPE = "clientType";

    @Autowired
    private RedisPubSubUtil redisPubSubUtil;

    @Override
    public void handler(SocketIOClient client, AckRequest ackRequest, Object object) {
        // 系统标识
        String appId = client.getHandshakeData().getSingleUrlParam(APP_KEY);
        // 消息发送方
        String fromUser = client.getHandshakeData().getSingleUrlParam(FROM_USER_PARAM);
        //客户端类型
        String clientType = client.getHandshakeData().getSingleUrlParam(CLIENT_TYPE);
        Message message = (Message) object;
        message.setFromUser(fromUser);
        message.setAppId(appId);
        PubSubMessage pubSubMessage = new PubSubMessage();
        pubSubMessage.setPubType(PubSubTypeEnum.SEND_MESSAGE.getClassName());
        pubSubMessage.setMessage(message);
        redisPubSubUtil.publish(message.getAppId() + "_" + message.getToUser(), pubSubMessage);
        ackRequest.sendAckData("发送成功");
    }
}
