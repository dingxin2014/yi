package com.fateking.yi.service.impl;

import com.fateking.yi.config.HuobiConfig;
import com.fateking.yi.dto.*;
import com.fateking.yi.enums.Period;
import com.fateking.yi.enums.Symbol;
import com.fateking.yi.enums.Type;
import com.fateking.yi.service.MarketService;
import com.fateking.yi.support.HuobiHttpClient;
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
    HuobiHttpClient huobiHttpClient;
    @Autowired
    HuobiConfig huobiConfig;

    @Override
    public List<KTick> getKLine(Symbol symbol, Period period, Integer size) {
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", symbol.getCode());
        params.put("period", period.getCode());
        params.put("size", String.valueOf(size));
        HuobiKLineResponse response = huobiHttpClient.getMarket(huobiConfig.getKline(), params, HuobiKLineResponse.class);
        if (response == null) {
            return null;
        }
        return response.getData()
                .stream().map(kTick -> {
                    kTick.setSymbol(symbol);
                    return kTick;
                }).collect(Collectors.toList());
    }

    @Override
    public Tick getMerged(Symbol symbol) {
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", symbol.getCode());
        HuobiMergedResponse response = huobiHttpClient.getMarket(huobiConfig.getMerged(), params, HuobiMergedResponse.class);
        if (response == null) {
            return null;
        }
        Tick tick = response.getTick();
        tick.setSymbol(symbol);
        return tick;
    }

    @Override
    public TradeDetail getTrade(Symbol symbol) {
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", symbol.getCode());
        HuobiTradeDetailResponse response = huobiHttpClient.getMarket(huobiConfig.getTrade(), params, HuobiTradeDetailResponse.class);
        if (response == null) {
            return null;
        }
        TradeDetail detail = response.getData();
        detail.setSymbol(symbol);
        return detail;
    }

    @Override
    public List<HistoryTradeData> getHistoryTrade(Symbol symbol, Integer size) {
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", symbol.getCode());
        params.put("size", String.valueOf(size));
        HuobiHistoryTradeDetailResponse response = huobiHttpClient.getMarket(huobiConfig.getHistoryTrade(), params, HuobiHistoryTradeDetailResponse.class);
        if (response == null) {
            return null;
        }
        List<HistoryTradeData> list = response.getData();
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
        HuobiDepthResponse response = huobiHttpClient.getMarket(huobiConfig.getDepth(), params, HuobiDepthResponse.class);
        if (response == null) {
            return null;
        }
        tick = response.getTick();
        tick.setSymbol(symbol);
        return tick;
    }

    @Override
    public Tick getDetail(Symbol symbol) {
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", symbol.getCode());
        HuobiDetailResponse response = huobiHttpClient.getMarket(huobiConfig.getDetail(), params, HuobiDetailResponse.class);
        if (response == null) {
            return null;
        }
        Tick tick = response.getTick();
        tick.setSymbol(symbol);
        return tick;
    }

}
