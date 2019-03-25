package com.microchat.pubsubevent.service.impl;

import com.corundumstudio.socketio.SocketIOClient;
import com.microchat.client.service.ClientService;
import com.microchat.client.utils.NettyClients;
import com.microchat.pubsubevent.service.SubMessageHandler;
import com.microchat.socketio.messages.ForcedOffNotifyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 强制下线通知消息处理类
 *
 * @author pengju.liao
 * @since 2019年03月21日
 */
@Service
public class ForcedOffNotifyMessageHandler implements SubMessageHandler {
    /** 日志记录器 */
    private static final Logger LOGGER = LoggerFactory.getLogger(ForcedOffNotifyMessageHandler.class);
    @Autowired
    private ClientService clientServiceImpl;

    @Override
    public void messageHandler(Object message) {
        LOGGER.info("强制下线接口收到消息message:{}", message);
        ForcedOffNotifyMessage forcedOffNotifyMessage = (ForcedOffNotifyMessage) message;
        SocketIOClient socketIOClient = NettyClients.getClient(forcedOffNotifyMessage.getClientId(),forcedOffNotifyMessage.getClientType());
        clientServiceImpl.forcedOff(socketIOClient, forcedOffNotifyMessage.getClientId(), forcedOffNotifyMessage.getClientType());
    }
}
