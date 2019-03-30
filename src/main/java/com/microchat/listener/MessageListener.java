package com.microchat.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microchat.pubsubevent.SubMessageHandlerContext;
import com.microchat.pubsubevent.model.PubSubMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

/**
 * 订阅消息监听类
 *
 * @author pengju.liao
 * @since 2019年03月20日
 */
@Service
public class MessageListener {
    /**
     * 日志记录器
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);

    @Autowired
    private SubMessageHandlerContext subMessageHandlerContext;

    /**
     * 订阅消息入口
     *
     * @param message
     */
    public void onMessage(String message) {
        LOGGER.info("redis 消费消息：{}", message);
        PubSubMessage pubSubMessage = JSON.parseObject(message, PubSubMessage.class);
        try {
            subMessageHandlerContext.getSubMessageHandler(pubSubMessage.getPubType()).messageHandler(pubSubMessage.getMessage());
        } catch (Exception e) {
            LOGGER.error("redis 消费失败。e:{}", e);
        }
    }
}
