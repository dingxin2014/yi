package com.fateking.yi.support;

import org.springframework.context.ApplicationContext;

/**
 * @author dingxin
 */
public class SpringObjectFactory {

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        if (SpringObjectFactory.applicationContext == null) {
            SpringObjectFactory.applicationContext = applicationContext;
        }
    }

    public final static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    public final static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

}
