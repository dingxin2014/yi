package com.fateking.yi.support;

import com.fateking.yi.dto.Account;
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

    public static final Map<Symbol, KTickStack> stack = Maps.newConcurrentMap();

    public static final Account account = new Account();

}
