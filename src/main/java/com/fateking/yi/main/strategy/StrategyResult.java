package com.fateking.yi.main.strategy;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class StrategyResult implements Serializable {

    public enum StrategyResultType {

        CONFIRM,  //交易

        WAIT;     //不交易

    }

    private StrategyResultType resultType;      //分析结果
    private BigDecimal riskRatio;               //风险率
    private BigDecimal estimatedProfit;         //预计盈利
    private String desc;                        //描述

}
