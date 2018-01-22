package com.fateking.yi.service;

import com.fateking.yi.dto.*;
import com.fateking.yi.enums.Period;
import com.fateking.yi.enums.Symbol;
import com.fateking.yi.enums.Type;

import java.util.List;

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
    List<KTick> getKLine(Symbol symbol, Period period, Integer size);


    /**
     * 获取聚合行情
     *
     * @param symbol
     * @return
     */
    Tick getMerged(Symbol symbol);

    /**
     * 获取marketdepth
     *
     * @param symbol
     * @param type
     * @return
     */
    DepthTick getDepth(Symbol symbol, Type type);


    /**
     * 获取某个币种的详情
     *
     * @param symbol
     * @return
     */
    TradeDetail getTrade(Symbol symbol);


    /**
     * 批量获取最近的交易记录
     *
     * @param symbol
     * @param size
     * @return
     */
    List<HistoryTradeData> getHistoryTrade(Symbol symbol, Integer size);


    /**
     * 获取 Market Detail 24小时成交量数据
     *
     * @param symbol
     * @return
     */
    Tick getDetail(Symbol symbol);


}
