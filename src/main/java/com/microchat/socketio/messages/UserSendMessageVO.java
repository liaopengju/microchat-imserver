package com.microchat.socketio.messages;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * 消息体对象
 *
 * @author pengju.liao
 * @since 2019年03月14日
 */
public class UserSendMessageVO implements Serializable {

    private static final long serialVersionUID = -1168798885824673379L;

    /** 系统ID */
    private String appId;
    /** 客户端类型 */
    private String clientType;
    /** 消息发送方 */
    private String fromUser;
    /** 消息接收方 */
    private String target;
    /** 消息接收方类型 */
    private String targetType;
    /** 客户端消息Id */
    private String clientMsgId;
    /**用户自定义事件*/
    private String userEvent;
    /** 是否重复发送 */
    private Boolean isResend;
    /** 业务数据 */
    private JSONObject businessData;
    /** 消息内容 */
    private JSONObject message;


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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getClientMsgId() {
        return clientMsgId;
    }

    public void setClientMsgId(String clientMsgId) {
        this.clientMsgId = clientMsgId;
    }

    public Boolean getResend() {
        return isResend;
    }

    public void setResend(Boolean resend) {
        isResend = resend;
    }

    public String getUserEvent() {
        return userEvent;
    }

    public void setUserEvent(String userEvent) {
        this.userEvent = userEvent;
    }

    public JSONObject getBusinessData() {
        return businessData;
    }

    public void setBusinessData(JSONObject businessData) {
        this.businessData = businessData;
    }

    public JSONObject getMessage() {
        return message;
    }

    public void setMessage(JSONObject message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
