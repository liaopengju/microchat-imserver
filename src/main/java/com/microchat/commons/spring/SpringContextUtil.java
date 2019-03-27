package com.microchat.commons.spring;

import org.springframework.context.ApplicationContext;

/**
 * springContextUtil
 *
 * @author pengju.liao
 * @since 2019年03月19日
 */
public class SpringContextUtil {

    private static ApplicationContext applicationContext;

    private SpringContextUtil() {
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<?> clz) {
        return (T) applicationContext.getBean(clz);
    }
}