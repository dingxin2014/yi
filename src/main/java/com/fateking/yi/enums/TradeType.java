package com.fateking.yi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * type: 可选值 {buy-market：市价买, sell-market：市价卖, buy-limit：限价买, sell-limit：限价卖}
 *
 * @author dingxin
 */
@Getter
@AllArgsConstructor
public enum TradeType {

    BuyMarket("buy-market", "市价买"),
    SellMarket("sell-market", "市价卖"),
    BuyLimit("buy-limit", "限价买"),
    SellLimit("sell-limit", "限价卖");

    private String code;
    private String name;

    public boolean in(TradeType... tradeTypes) {
        if (tradeTypes == null) {
            return false;
        }
        for (TradeType tradeType : tradeTypes) {
            if (this.equals(tradeType)) {
                return true;
            }
        }
        return false;
    }

    public static TradeType parse(String type) {
        for (TradeType t : values()) {
            if (t.code.equals(type)) {
                return t;
            }
        }
        return null;
    }
}
