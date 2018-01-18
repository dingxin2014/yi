package com.fateking.yi.support;

import com.fateking.yi.enums.Symbol;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NamedThreadLocal;

import java.util.Map;

/**
 * @author dingxin
 */
@Slf4j
public class GlobalContext {

    private static final NamedThreadLocal<String> tokenContext = new NamedThreadLocal<>("Token存储");

    public static String getToken() {
        return tokenContext.get();
    }

    public static void setToken(String token) {
        tokenContext.set(token);
    }

    private static final Map<String, Runnable> monitorRunnable = Maps.newConcurrentMap();

    public static void monitor(Symbol symbol) {
        if (monitorRunnable.containsKey(symbol.getCode())) {
            log.info(symbol + "has been monitored!");
        }
    }

}
