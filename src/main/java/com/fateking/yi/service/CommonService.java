package com.fateking.yi.service;

import com.fateking.yi.dto.Symbol;
import com.fateking.yi.enums.SymbolPartition;
import com.fateking.yi.support.Result;

import java.util.List;

public interface CommonService {

    /**
     * 查询系统支持的所有交易对及精度
     *
     * @param baseCurrency    基础币种
     * @param quoteCurrency   计价币种
     * @param pricePrecision  价格精度位数（0为个位）
     * @param amountPrecision 数量精度位数（0为个位）
     * @param symbolPartition 交易区
     * @return
     */
    Result<Symbol> getSymbols(String baseCurrency, String quoteCurrency, Integer pricePrecision,
                              Integer amountPrecision, SymbolPartition symbolPartition);

    /**
     * 获取系统时间戳
     *
     * @return
     */
    Result<Long> getTimestamp();


    /**
     * 支持的币种
     *
     * @return
     */
    Result<List<String>> getCurrencys();
}
