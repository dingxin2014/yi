package com.fateking.yi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fateking.yi.enums.State;
import com.fateking.yi.enums.Symbol;
import com.fateking.yi.enums.TradeType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单成交明细
 */
@Data
public class OrderMatch {

    private Long id;                    //订单成交记录ID
    @JsonProperty("match-id")
    private Long matchId;               //撮合Id
    @JsonProperty("order-id")
    private Long orderId;               //订单Id
    @JsonProperty("created-at")
    private Date createdAt;             //成交时间
    @JsonProperty("filled-amount")
    private BigDecimal filledAmount;    //成交数量
    @JsonProperty("filled-fees")
    private BigDecimal filledFees;      //成交手续费
    private BigDecimal price;           //成交价格
    private String source;              //订单来源 api
    private Symbol symbol;              //交易对
    private TradeType type;             //订单类型

    public void setSymbol(String symbol) {
        this.symbol = Symbol.parse(symbol);
    }

    public void setType(String type) {
        this.type = TradeType.parse(type);
    }
}
