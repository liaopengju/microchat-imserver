package com.microchat.socketio.handler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.microchat.pubevent.service.MessageEventService;
import com.microchat.socketio.messages.OptMessage;
import com.microchat.socketio.messages.StatusNoticeMessage;
import com.microchat.socketio.messages.UserSendMessageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息事件处理类
 *
 * @author pengju.liao
 * @since 2019年03月14日
 */
@Component
public class MessageEventHandler {
    /** 日志记录器 */
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageEventHandler.class);

    @Autowired
    private MessageEventService connectEventServiceImpl;
    @Autowired
    private MessageEventService disConnectEventServiceImpl;
    @Autowired
    private MessageEventService sendMessageEventServiceImpl;


    /**
     * 连接事件监听
     *
     * @param client
     */
    @OnConnect
    public void onConnect(SocketIOClient client) {
        connectEventServiceImpl.handler(client, null, null);
    }

    /**
     * 断开连接监听
     *
     * @param client
     */
    @OnDisconnect
    public void onDisConnect(SocketIOClient client) {
        disConnectEventServiceImpl.handler(client, null, null);
    }

    /**
     * 消息事件监听
     *
     * @param client
     * @param request
     */
    @OnEvent(value = "message")
    public void onMessageEvent(SocketIOClient client, AckRequest request, UserSendMessageVO message) {
        sendMessageEventServiceImpl.handler(client, request, message);
    }

    /**
     * 消息操作监听
     *
     * @param client
     * @param request
     * @param optMessage
     */
    @OnEvent(value = "optMessage")
    public void onOptMessageEvent(SocketIOClient client, AckRequest request, OptMessage optMessage) {
        LOGGER.info("操作消息开始");

    }

    /**
     * 状态通知监听
     *
     * @param client
     * @param request
     * @param statusNoticeMessage
     */
    @OnEvent(value = "statusNotice")
    public void onStatusNoticeEvent(SocketIOClient client, AckRequest request, StatusNoticeMessage statusNoticeMessage) {
        LOGGER.info("状态通知消息开始");
    }
}
