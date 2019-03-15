package com.microchat.socketio.server;

import com.corundumstudio.socketio.SocketIOServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * netty-socketIo 启动类
 *
 * @author pengju.liao
 * @since 2018年09月26日
 */
@Component
public class ServerRunner {
    /** 日志记录器 */
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerRunner.class);
    /**
     * IM 服务对象
     */
    private final SocketIOServer server;
    @Autowired
    public ServerRunner(SocketIOServer server) {
        this.server = server;
    }
    /**
     * 启动IMServer
     */
    @PostConstruct
    public void run() {
        LOGGER.info("====================开始启动IM服务==========================");
        server.start();
        LOGGER.info("====================启动IM服务成功==========================");
    }
}
