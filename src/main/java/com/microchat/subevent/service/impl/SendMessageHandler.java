package com.microchat.subevent.service.impl;

import com.microchat.client.service.ClientService;
import com.microchat.socketio.messages.UserSendMessageVO;
import com.microchat.subevent.enums.TargetTypeEnum;
import com.microchat.subevent.service.SubMessageHandler;
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
        UserSendMessageVO msg = (UserSendMessageVO) message;
        if((TargetTypeEnum.CHAT.getTargetType()).equals(msg.getTargetType())) {
            LOGGER.info("单聊消息msg:{}", msg);
            clientServiceImpl.sendMessageToClient(msg);
        } else if((TargetTypeEnum.GROUP_CHAT.getTargetType()).equals(msg.getTargetType())) {
            LOGGER.info("群聊消息msg:{}", msg);
            clientServiceImpl.sendMessageToRoom(msg);
        } else {
            LOGGER.info("未知消息sendType,不处理。message:{}", message);
        }
    }
}
