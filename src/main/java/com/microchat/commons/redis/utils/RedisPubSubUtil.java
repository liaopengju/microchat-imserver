package com.microchat.commons.redis.utils;


import com.microchat.commons.spring.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * redis 发布订阅
 *
 * @author pengju.liao
 * @since 2019年03月19日
 */
@Component
public class RedisPubSubUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 订阅消息
     *
     * @param channel 通道
     */
    public void subscribe(String channel) {
        RedisMessageListenerContainer container = SpringContextUtil.getBean(RedisMessageListenerContainer.class);
        MessageListenerAdapter listenerAdapter = SpringContextUtil.getBean(MessageListenerAdapter.class);
        container.addMessageListener(listenerAdapter, new PatternTopic(channel));
    }

    /**
     * 同时订阅多个消息
     *
     * @param channels
     */
    public void subscribe(List<String> channels) {
        RedisMessageListenerContainer container = SpringContextUtil.getBean(RedisMessageListenerContainer.class);
        MessageListenerAdapter listenerAdapter = SpringContextUtil.getBean(MessageListenerAdapter.class);
        for(String channel : channels) {
            container.addMessageListener(listenerAdapter, new PatternTopic(channel));
        }
    }

    /**
     * 取消订阅
     *
     * @param channel
     */
    public void unSubscribe(String channel) {
        RedisMessageListenerContainer container = SpringContextUtil.getBean(RedisMessageListenerContainer.class);
        MessageListenerAdapter listenerAdapter = SpringContextUtil.getBean(MessageListenerAdapter.class);
        container.removeMessageListener(listenerAdapter, new PatternTopic(channel));
    }

    /**
     * 取消订阅
     *
     * @param channels
     */
    public void unSubscribe(List<String> channels) {
        RedisMessageListenerContainer container = SpringContextUtil.getBean(RedisMessageListenerContainer.class);
        MessageListenerAdapter listenerAdapter = SpringContextUtil.getBean(MessageListenerAdapter.class);
        for(String channel : channels) {
            container.removeMessageListener(listenerAdapter, new PatternTopic(channel));
        }
    }

    /**
     * 发布消息
     *
     * @param channel 通道
     * @param message 消息内容
     */
    public void publish(String channel, Object message) {
        redisTemplate.convertAndSend(channel, message);
    }


}

