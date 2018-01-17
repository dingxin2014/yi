package com.fateking.yi.support;

import org.springframework.core.NamedThreadLocal;

/**
 * @author dingxin
 */
public class GlobalContext {

    private static final NamedThreadLocal<String> tokenContext = new NamedThreadLocal<>("Token存储");

    public static String getToken() {
        return tokenContext.get();
    }

    public static void setToken(String token) {
        tokenContext.set(token);
    }

}
