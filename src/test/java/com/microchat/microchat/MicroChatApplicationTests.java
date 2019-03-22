package com.microchat.microchat;

import com.microchat.commons.spring.SpringContextUtil;
import com.microchat.listener.MessageListener;
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
    @Autowired
    private MessageListener connectMessageListener;


    @Test
    public void contextLoads() {
        RedisMessageListenerContainer container = SpringContextUtil.getBean(RedisMessageListenerContainer.class);
        MessageListenerAdapter listenerAdapter = SpringContextUtil.getBean(MessageListenerAdapter.class);
        container.addMessageListener(listenerAdapter, new PatternTopic("java.1"));
        PubSubMessage pubSubMessage = new PubSubMessage();
        pubSubMessage.setPubType("connect");
        redisTemplate.convertAndSend("java.1", pubSubMessage);
    }

}
