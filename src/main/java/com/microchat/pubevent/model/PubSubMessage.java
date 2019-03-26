package com.microchat.pubevent.model;

/**
 * 发布订阅消息体
 *
 * @author pengju.liao
 * @since 2019年03月21日
 */
public class PubSubMessage<T> {

    /***
     * 发布的消息类型
     */
    private String pubType;

    /**
     * 消息类型对应的消息体
     */
    private T message;

    public String getPubType() {
        return pubType;
    }

    public void setPubType(String pubType) {
        this.pubType = pubType;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }
}
