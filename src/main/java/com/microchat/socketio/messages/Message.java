package com.microchat.socketio.messages;

import java.io.Serializable;
import java.util.Map;

/**
 * 消息体对象
 *
 * @author pengju.liao
 * @since 2019年03月14日
 */
public class Message implements Serializable {

    private static final long serialVersionUID = -1168798885824673379L;

    /**系统Id*/
    private String appId;
    /**客户端类型*/
    private String clientType;
    /** 服务器消息Id */
    private String msgId;
    /** 客户端消息Id */
    private String clientMsgId;
    /** 会话Id */
    private String sessionId;
    /**消息发送方*/
    private String fromUser;
    /** 消息接收发 */
    private String toUser;
    /** 发送类型（groupChat,chat） */
    private String sendType;
    /** 消息类型 */
    private String msgType;
    /** 消息内容 */
    private String content;
    /** 是否重复发送 */
    private Boolean isResend;
    /** 业务数据 */
    private Map businessData;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Map getBusinessData() {
        return businessData;
    }

    public void setBusinessData(Map businessData) {
        this.businessData = businessData;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getClientMsgId() {
        return clientMsgId;
    }

    public void setClientMsgId(String clientMsgId) {
        this.clientMsgId = clientMsgId;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getResend() {
        return isResend;
    }

    public void setResend(Boolean resend) {
        isResend = resend;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
