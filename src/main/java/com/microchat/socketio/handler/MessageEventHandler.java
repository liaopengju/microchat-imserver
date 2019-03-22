package com.microchat.socketio.handler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.microchat.commons.spring.SpringContextUtil;
import com.microchat.messageevent.MessageEventService;
import com.microchat.messageevent.impl.ConnectEventServiceImpl;
import com.microchat.socketio.messages.Message;
import com.microchat.socketio.messages.OptMessage;
import com.microchat.socketio.messages.StatusNoticeMessage;

/**
 * 消息事件处理类
 *
 * @author pengju.liao
 * @since 2019年03月14日
 */
public class MessageEventHandler {

    /** IM 服务对象 */
    protected SocketIOServer server;

    public MessageEventHandler(SocketIOServer server) {
        this.server = server;
    }

    /**
     * 连接事件监听
     *
     * @param client
     */
    @OnConnect
    public void onConnect(SocketIOClient client) {
        MessageEventService messageEventService = SpringContextUtil.getBean(ConnectEventServiceImpl.class);
        messageEventService.handler(client);
    }

    /**
     * 断开连接监听
     *
     * @param client
     */
    @OnDisconnect
    public void onDisConnect(SocketIOClient client) {
        System.out.println("断开连接开始");
    }

    /**
     * 消息事件监听
     *
     * @param client
     * @param request
     */
    @OnEvent(value = "message")
    public void onMessageEvent(SocketIOClient client, AckRequest request, Message message) {

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
        System.out.println("操作消息开始");
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
        System.out.println("状态通知消息开始");
    }
}
