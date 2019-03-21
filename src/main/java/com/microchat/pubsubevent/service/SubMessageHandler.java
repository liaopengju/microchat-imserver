package com.microchat.pubsubevent.service;

/**
 * 订阅消息处理接口
 *
 * @author pengju.liao
 * @since 2019年03月21日
 */
public interface SubMessageHandler {
    /**
     * 消息处理接口
     *
     * @param message
     */
    void messageHandler(Object message);
}
