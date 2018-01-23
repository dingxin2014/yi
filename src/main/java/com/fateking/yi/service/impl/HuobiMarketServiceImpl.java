package com.fateking.yi.service.impl;

import com.fateking.yi.config.HuobiConfig;
import com.fateking.yi.dto.*;
import com.fateking.yi.enums.Period;
import com.fateking.yi.enums.Symbol;
import com.fateking.yi.enums.Type;
import com.fateking.yi.service.MarketService;
import com.fateking.yi.utils.HuobiHttpClientUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author dingxin
 */
@Service
@Slf4j
@Retryable
public class HuobiMarketServiceImpl implements MarketService {

    @Autowired
    HuobiConfig huobiConfig;

    @Override
    public List<KTick> getKLine(Symbol symbol, Period period, Integer size) {
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", symbol.getCode());
        params.put("period", period.getCode());
        params.put("size", String.valueOf(size));
        return HuobiHttpClientUtil.getMarket(huobiConfig.getKline(), params, HuobiKLineResponse.class).getData()
                .stream().map(kTick -> {
                    kTick.setSymbol(symbol);
                    return kTick;
                }).collect(Collectors.toList());
    }

    @Override
    public Tick getMerged(Symbol symbol) {
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", symbol.getCode());
        Tick tick = HuobiHttpClientUtil.getMarket(huobiConfig.getMerged(), params, HuobiMergedResponse.class).getTick();
        tick.setSymbol(symbol);
        return tick;
    }

    @Override
    public TradeDetail getTrade(Symbol symbol) {
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", symbol.getCode());
        TradeDetail detail = HuobiHttpClientUtil.getMarket(huobiConfig.getTrade(), params, HuobiTradeDetailResponse.class).getData();
        detail.setSymbol(symbol);
        return detail;
    }

    @Override
    public List<HistoryTradeData> getHistoryTrade(Symbol symbol, Integer size) {
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", symbol.getCode());
        params.put("size", String.valueOf(size));
        List<HistoryTradeData> list = HuobiHttpClientUtil.getMarket(huobiConfig.getHistoryTrade(), params, HuobiHistoryTradeDetailResponse.class).getData();
        if (!CollectionUtils.isEmpty(list)) {
            list.stream().forEach(historyTradeData -> historyTradeData.getData().stream().forEach(e -> e.setSymbol(symbol)));
        }
        return list;
    }

    @Override
    public DepthTick getDepth(Symbol symbol, Type type) {
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", symbol.getCode());
        params.put("type", type.getCode());
        DepthTick tick = null;
        try {
            tick = HuobiHttpClientUtil.getMarket(huobiConfig.getDepth(), params, HuobiDepthResponse.class).getTick();
        } catch (Exception e) {
            System.err.println(e);
        }
        tick.setSymbol(symbol);
        return tick;
    }

    @Override
    public Tick getDetail(Symbol symbol) {
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", symbol.getCode());
        Tick tick = HuobiHttpClientUtil.getMarket(huobiConfig.getDetail(), params, HuobiDetailResponse.class).getTick();
        tick.setSymbol(symbol);
        return tick;
    }

}
