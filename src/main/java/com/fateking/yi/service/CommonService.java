package com.fateking.yi.service;

import com.fateking.yi.dto.HuobiResponse;
import com.fateking.yi.enums.SymbolPartition;

public interface CommonService {

    /**
     * 查询系统支持的所有交易对及精度
     *
     * @param baseCurrency      基础币种
     * @param quoteCurrency     计价币种
     * @param pricePrecision    价格精度位数（0为个位）
     * @param amountPrecision   数量精度位数（0为个位）
     * @param symbolPartition   交易区
     * @return
     */
    HuobiResponse getSymbols(String baseCurrency, String quoteCurrency, Integer pricePrecision,
                             Integer amountPrecision, SymbolPartition symbolPartition);
}
