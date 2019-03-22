package com.microchat.pubsubevent.service.impl;

import com.microchat.client.service.ClientService;
import com.microchat.pubsubevent.service.SubMessageHandler;
import com.microchat.socketio.messages.Message;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 请填写类注释
 *
 * @author pengju.liao
 * @since 2019年03月22日
 */
public class SendMessageHandler implements SubMessageHandler {

    @Autowired
    private ClientService clientServiceImpl;

    @Override
    public void messageHandler(Object message) {
        Message msg = (Message) message;
        if(("chat").equals(msg.getSendType())){
            clientServiceImpl.sendMessageToClient(msg);
        }else{

        }
    }
}
