package com.microchat.microchat;

import com.microchat.commons.spring.SpringContextUtil;
import com.microchat.pubsubevent.model.PubSubMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MicroChatApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {

        PubSubMessage pubSubMessage = new PubSubMessage();
        pubSubMessage.setPubType("sendMessageHandler");
        redisTemplate.convertAndSend("systemNotification", pubSubMessage);
    }

}
