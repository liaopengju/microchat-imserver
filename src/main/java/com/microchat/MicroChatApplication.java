package com.microchat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 项目启动类
 *
 * @author pengju.liao
 * @since 2019年03月14日
 */
@EnableRabbit
@EnableCaching
@SpringBootApplication
public class MicroChatApplication {
    /** 日志记录器 */
    private static final Logger LOGGER = LoggerFactory.getLogger(MicroChatApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MicroChatApplication.class, args);
        LOGGER.info("服务启动成功");
    }
}
