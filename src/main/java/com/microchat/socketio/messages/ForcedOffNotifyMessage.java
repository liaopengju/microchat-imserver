package com.microchat.socketio.messages;

import com.corundumstudio.socketio.store.pubsub.PubSubMessage;

import java.util.UUID;

/**
 * 强制下线通知消息实体
 *
 * @author pengju.liao
 * @since 2019年03月19日
 */
public class ForcedOffNotifyMessage extends PubSubMessage {

    private static final long serialVersionUID = 2268917122709317368L;

    /**系统Id*/
    private String appId;
    /** 客户端Id */
    private String clientId;
    /**客户端类型*/
    private String clientType;
    /** 客户端会话Id */
    private UUID sessionId;
    /** 强制下线通知内容 */
    private String message;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public UUID getSessionId() {
        return sessionId;
    }

    public void setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
