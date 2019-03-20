package com.microchat.listener;

import com.corundumstudio.socketio.store.pubsub.PubSubListener;
import org.springframework.stereotype.Service;

/**
 * 请填写类注释
 *
 * @author pengju.liao
 * @since 2019年03月20日
 */
@Service
public class MessageListener {


    public void onMessage(Object o) {
        System.out.println("接收到消息：++++++" + o);
    }
}
