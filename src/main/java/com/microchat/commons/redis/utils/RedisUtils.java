package com.microchat.commons.redis.utils;

/**
 * 请填写类注释
 *
 * @author pengju.liao
 * @since 2019年03月19日
 */

import com.microchat.commons.spring.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * key-value
     * @param key key
     * @param val value
     */
    public void setValue(String key, Object val) {
        redisTemplate.opsForValue().set(key, val);
    }

    /**
     * key-value 带过期时间
     * @param key key
     * @param val value
     * @param time 时间
     * @param unit 单位
     */
    public void setValue(String key, Object val, int time, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, val, time, unit);
    }

    /**
     * 获取value
     * @param key key
     * @return value
     */
    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }


    public void multiSet(Map<String, Object> map) {
        redisTemplate.opsForValue().multiSet(map);
    }

    public List<Object> multiGet(Collection<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    public long incr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    public void lpush(String key, String value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    public List<Object> range(String key, int start, int end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    public Object rpop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    public void setHash(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    public Object getHash(String key, String prop) {
        return redisTemplate.opsForHash().get(key, prop);
    }

    public Map getHashAll(String key) {
        Map map = new HashMap();
        map.put("keys", redisTemplate.opsForHash().keys(key));
        map.put("vals", redisTemplate.opsForHash().values(key));
        return map;
    }

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
     * 发布消息
     *
     * @param channel 通道
     * @param message 消息内容
     */
    public void publish(String channel, Object message) {
        redisTemplate.convertAndSend(channel, message);
    }
}

