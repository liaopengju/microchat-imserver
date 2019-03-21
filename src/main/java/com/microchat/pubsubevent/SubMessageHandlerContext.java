package com.microchat.pubsubevent;

import com.microchat.pubsubevent.service.SubMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 获取订阅消息处理对象类
 *
 * @author pengju.liao
 * @since 2019年03月21日
 */
@Component
public class SubMessageHandlerContext {

    @Autowired
    ApplicationContext applicationContext;

    /**
     * 获取订阅消息处理对象
     *
     * @param subType 订阅类型
     * @return
     */
    public SubMessageHandler getSubMessageHandler(String subType) throws Exception {
        return (SubMessageHandler) applicationContext.getBean(Class.forName(subType));
    }
}
