package com.microchat.socketio.listener;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ExceptionListener;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 异常监听
 *
 * @author pengju.liao
 * @since 2019年03月14日
 */
public class ExceptionListenerImpl implements ExceptionListener {
    /** 日志记录器 */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionListenerImpl.class);

    /**
     * 事件异常
     *
     * @param e
     * @param list
     * @param socketIOClient
     */
    @Override
    public void onEventException(Exception e, List<Object> list, SocketIOClient socketIOClient) {
        LOGGER.info("事件时出现异常。e:{}", e);
    }

    /***
     * 断开连接异常
     * @param e
     * @param socketIOClient
     */
    @Override
    public void onDisconnectException(Exception e, SocketIOClient socketIOClient) {
        LOGGER.info("断开连接时出现异常。e:{}", e);
    }

    /***
     * 连接异常
     * @param e
     * @param socketIOClient
     */
    @Override
    public void onConnectException(Exception e, SocketIOClient socketIOClient) {
        LOGGER.info("连接时出现异常。e:{}", e);
    }

    /**
     * ping 异常
     *
     * @param e
     * @param socketIOClient
     */
    @Override
    public void onPingException(Exception e, SocketIOClient socketIOClient) {
        LOGGER.info("ping时出现异常。e:{}", e);
    }

    /**
     * 通道读写异常
     *
     * @param channelHandlerContext
     * @param throwable
     * @return
     * @throws Exception
     */
    @Override
    public boolean exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) {
        LOGGER.info("通道读写异常。throwable:{}");
        return false;
    }
}
