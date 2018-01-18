package com.fateking.yi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 币种标志
 *
 * @author dingxin
 */
@AllArgsConstructor
@Getter
public enum Symbol {

    XRP_USDT("xrpusdt", "瑞波币USDT"),

    BTC_USDT("btcusdt", "比特币USDT"),

    ETH_USDT("ethusdt", "");

    private String code;
    private String name;

    public boolean in(Symbol... symbols) {
        if (symbols == null) {
            return false;
        }
        for (Symbol symbol : symbols) {
            if (this.equals(symbol)) {
                return true;
            }
        }
        return false;
    }

    public static Symbol parse(String symbol) {
        for (Symbol s : values()) {
            if (s.code.equals(symbol)) {
                return s;
            }
        }
        return null;
    }
}
