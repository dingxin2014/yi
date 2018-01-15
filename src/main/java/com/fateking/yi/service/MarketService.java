package com.fateking.yi.service;

import com.fateking.yi.enums.Period;
import com.fateking.yi.enums.Symbol;

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
    Object getKLine(Symbol symbol, Period period, Integer size);


    /**
     * 获取marketdepth
     *
     * @param symbol
     * @param period
     * @return
     */
    Object getDepth(Symbol symbol, Period period);


    /**
     * 获取某个币种的详情
     *
     * @param symbol
     * @return
     */
    Object getTrade(Symbol symbol);


    /**
     * merge ticker
     *
     * @param symbol
     * @return
     */
    Object getTicker(Symbol symbol);


    /**
     * 获取 Market Detail 24小时成交量数据
     *
     * @param symbol
     * @return
     */
    Object getDetail(Symbol symbol);


    /**
     * 获取支持的交易对
     *
     * @return
     */
    Object getSymbols();


}
