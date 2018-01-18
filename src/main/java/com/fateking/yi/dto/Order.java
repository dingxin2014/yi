package com.fateking.yi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fateking.yi.enums.State;
import com.fateking.yi.enums.Symbol;
import com.fateking.yi.enums.TradeType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单
 *
 * @author dingxin
 */
@Data
public class Order {

    private Long id;                    //订单ID
    @JsonProperty("account-id")
    private Long accountId;             //账户 ID
    private BigDecimal amount;          //订单数量
    @JsonProperty("canceled-at")
    private Date canceledAt;            //订单撤销时间
    @JsonProperty("created-at")
    private Date createdAt;             //订单创建时间
    @JsonProperty("field-amount")
    private BigDecimal fieldAmount;     //已成交数量
    @JsonProperty("field-cash-amount")
    private BigDecimal fieldCashAmount; //已成交总金额
    @JsonProperty("field-fees")
    private BigDecimal fieldFees;       //已成交手续费（买入为币，卖出为钱）
    @JsonProperty("finished-at")
    private Date finishedAt;            //最后成交时间
    private BigDecimal price;           //订单价格
    private String source;              //订单来源  api
    private State state;                //订单状态  pre-submitted 准备提交, submitting , submitted 已提交, partial-filled 部分成交, partial-canceled 部分成交撤销, filled 完全成交, canceled 已撤销
    private Symbol symbol;              //交易对
    private TradeType type;             //订单类型

    public void setState(String state) {
        this.state = State.parse(state);
    }

    public void setSymbol(String symbol) {
        this.symbol = Symbol.parse(symbol);
    }

    public void setType(String type) {
        this.type = TradeType.parse(type);
    }
}
