package com.microchat.socketio.config;

import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.microchat.socketio.enums.NameSpaceEnum;
import com.microchat.socketio.handler.MessageEventHandler;
import com.microchat.socketio.listener.AuthorizationListenerImpl;
import com.microchat.socketio.listener.ExceptionListenerImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * socket.io 配置
 *
 * @author pengju.liao
 * @since 2019年03月14日
 */
@Configuration
public class SocketIoConfig {
    /**
     * 服务器IP
     */
    @Value("${socket.host}")
    private String host;
    /**
     * 端口号
     */
    @Value("${socket.port}")
    private Integer port;

    /***
     * ssl 协议
     */
    @Value("${socket.sslProtocol}")
    private String sslProtocol;
    /**
     * 线程数
     */
    @Value("${socket.bossCount}")
    private int bossCount;
    /**
     * 工作线程数
     */
    @Value("${socket.workCount}")
    private int workCount;
    /**
     * 是否允许自定义请求
     */
    @Value("${socket.allowCustomRequests}")
    private boolean allowCustomRequests;
    /**
     * 协议升级时间
     */
    @Value("${socket.upgradeTimeout}")
    private int upgradeTimeout;
    /**
     * ping 超时事件
     */
    @Value("${socket.pingTimeout}")
    private int pingTimeout;
    /***
     * ping 间隔时间
     */
    @Value("${socket.pingInterval}")
    private int pingInterval;

    /**
     * SocketIONamespace  对象
     */
    private SocketIONamespace messageSocketNameSpace;

    /***
     * 配置socketIo服务
     * @return socketIo服务
     */
    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname(host);
        config.setPort(port);
        config.setSSLProtocol(sslProtocol);
        config.setWorkerThreads(workCount);
        config.setBossThreads(bossCount);
        config.setAllowCustomRequests(allowCustomRequests);
        config.setPingInterval(pingInterval);
        config.setUpgradeTimeout(upgradeTimeout);
        config.setPingTimeout(pingTimeout);
        /**权限校验*/
        config.setAuthorizationListener(new AuthorizationListenerImpl());
        /**异常捕获*/
        config.setExceptionListener(new ExceptionListenerImpl());
        config.getSocketConfig().setReuseAddress(true);
        config.getSocketConfig().setSoLinger(0);
        config.getSocketConfig().setTcpNoDelay(true);
        config.getSocketConfig().setTcpKeepAlive(true);
        SocketIOServer server = new SocketIOServer(config);
        /**添加nameSpace*/
        this.messageSocketNameSpace = server.addNamespace(NameSpaceEnum.MESSAGE.getNamespace());
        return server;
    }

    /**
     * @param server IM 服务对象
     * @return {@link SocketIONamespace}
     */
    @Bean(name = "messageSocketNameSpace")
    public SocketIONamespace getIMSocketIONameSpace(SocketIOServer server) {
        messageSocketNameSpace.addListeners(new MessageEventHandler(server));
        return messageSocketNameSpace;
    }
}
