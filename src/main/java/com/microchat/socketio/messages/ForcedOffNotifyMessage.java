package com.microchat.socketio.messages;

import java.io.Serializable;
import java.util.UUID;

/**
 * 强制下线通知消息实体
 *
 * @author pengju.liao
 * @since 2019年03月19日
 */
public class ForcedOffNotifyMessage implements Serializable {

    private static final long serialVersionUID = 2268917122709317368L;

    /** 客户端Id */
    private String clientId;
    /** 客户端会话Id */
    private UUID sessionId;
    /** 强制下线通知内容 */
    private String message;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
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
