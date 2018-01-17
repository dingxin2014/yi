package com.fateking.yi.service;

import com.fateking.yi.dto.HuobiResponse;
import com.fateking.yi.enums.Period;
import com.fateking.yi.enums.Symbol;
import com.fateking.yi.enums.Type;

/**
 * @author dingxin
 */
public interface MarketService {


    /**
     * 获取K线
     *
     * @param symbol
     * @param period
     * @param size
     * @return
     */
    HuobiResponse getKLine(Symbol symbol, Period period, Integer size);


    /**
     * 获取聚合行情
     *
     * @param symbol
     * @return
     */
    HuobiResponse getMerged(Symbol symbol);

    /**
     * 获取marketdepth
     *
     * @param symbol
     * @param type
     * @return
     */
    HuobiResponse getDepth(Symbol symbol, Type type);


    /**
     * 获取某个币种的详情
     *
     * @param symbol
     * @return
     */
    HuobiResponse getTrade(Symbol symbol);


    /**
     * 批量获取最近的交易记录
     *
     * @param symbol
     * @param size
     * @return
     */
    HuobiResponse getHistoryTrade(Symbol symbol, Integer size);


    /**
     * merge ticker
     *
     * @param symbol
     * @return
     */
    HuobiResponse getTicker(Symbol symbol);


    /**
     * 获取 Market Detail 24小时成交量数据
     *
     * @param symbol
     * @return
     */
    HuobiResponse getDetail(Symbol symbol);


}
