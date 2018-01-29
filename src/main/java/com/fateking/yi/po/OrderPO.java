package com.fateking.yi.po;

import com.fateking.yi.enums.State;
import com.fateking.yi.enums.Symbol;
import com.fateking.yi.enums.TradeType;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "T_YI_ORDER")
@Data
public class OrderPO {

    @Enumerated(EnumType.STRING)
    private Symbol symbol;              //交易对

    @Id
    @GeneratedValue
    private Long id;                    //订单ID
    private Long accountId;             //账户 ID
    private BigDecimal amount;          //订单数量
    private Date canceledAt;            //订单撤销时间
    private Date createdAt;             //订单创建时间
    private BigDecimal fieldAmount;     //已成交数量
    private BigDecimal fieldCashAmount; //已成交总金额
    private BigDecimal fieldFees;       //已成交手续费（买入为币，卖出为钱）
    private Date finishedAt;            //最后成交时间
    private BigDecimal price;           //订单价格
    private String source;              //订单来源  api
    private State state;                //订单状态  pre-submitted 准备提交, submitting , submitted 已提交, partial-filled 部分成交, partial-canceled 部分成交撤销, filled 完全成交, canceled 已撤销
    private TradeType type;             //订单类型

    private String exchange;
    private String batch;


}

