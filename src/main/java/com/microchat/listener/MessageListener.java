package com.microchat.listener;

import com.microchat.pubsubevent.SubMessageHandlerContext;
import com.microchat.pubsubevent.model.PubSubMessage;
import com.microchat.pubsubevent.service.SubMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订阅消息监听类
 *
 * @author pengju.liao
 * @since 2019年03月20日
 */
@Service
public class MessageListener {
    /** 日志记录器 */
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);

    @Autowired
    private SubMessageHandlerContext subMessageHandlerContext;

    /**
     * 订阅消息入口
     *
     * @param pubSubMessage
     */
    public void onMessage(PubSubMessage pubSubMessage) {
        try {
            subMessageHandlerContext.getSubMessageHandler(pubSubMessage.getPubType()).messageHandler(pubSubMessage.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("redis 消费失败。e:{}", e);
        }
    }
}
