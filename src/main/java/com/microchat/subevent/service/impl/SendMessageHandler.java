package com.microchat.subevent.service.impl;

import com.microchat.client.service.ClientService;
import com.microchat.subevent.enums.SendTypeEnum;
import com.microchat.subevent.service.SubMessageHandler;
import com.microchat.socketio.messages.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订阅消息处理类
 *
 * @author pengju.liao
 * @since 2019年03月22日
 */
@Service
public class SendMessageHandler implements SubMessageHandler {
    /** 日志记录器 */
    private static final Logger LOGGER = LoggerFactory.getLogger(ForcedOffNotifyMessageHandler.class);
    @Autowired
    private ClientService clientServiceImpl;

    /**
     * redis  订阅消息处理方法
     *
     * @param message 消息内容
     */
    @Override
    public void messageHandler(Object message) {
        Message msg = (Message) message;
        if((SendTypeEnum.CHAT.getSendType()).equals(msg.getSendType())) {
            LOGGER.info("单聊消息msg:{}", msg);
            clientServiceImpl.sendMessageToClient(msg);
        } else if((SendTypeEnum.GROUP_CHAT.getSendType()).equals(msg.getSendType())) {
            LOGGER.info("群聊消息msg:{}", msg);
            clientServiceImpl.sendMessageToRoom(msg);
        } else {
            LOGGER.info("未知消息sendType,不处理。message:{}", message);
        }
    }
}
