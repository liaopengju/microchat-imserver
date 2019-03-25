package com.microchat.pubsubevent.service.impl;

import com.microchat.client.service.ClientService;
import com.microchat.pubsubevent.enums.SendTypeEnum;
import com.microchat.pubsubevent.service.SubMessageHandler;
import com.microchat.socketio.messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * 订阅消息处理类
 * @author pengju.liao
 * @since 2019年03月22日
 */
@Service
public class SendMessageHandler implements SubMessageHandler {

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
            clientServiceImpl.sendMessageToClient(msg);
        } else {
            clientServiceImpl.sendMessageToRoom(msg);
        }
    }
}
