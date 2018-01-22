package com.fateking.yi.service.impl;

import com.fateking.yi.config.HuobiConfig;
import com.fateking.yi.dto.*;
import com.fateking.yi.enums.Period;
import com.fateking.yi.enums.Symbol;
import com.fateking.yi.enums.Type;
import com.fateking.yi.service.MarketService;
import com.fateking.yi.utils.HttpClientUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
        return HttpClientUtil.get(huobiConfig.getKline(), params, HuobiKLineResponse.class).getData();
    }

    @Override
    public Tick getMerged(Symbol symbol) {
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", symbol.getCode());
        return HttpClientUtil.get(huobiConfig.getMerged(), params, HuobiMergedResponse.class).getTick();
    }

    @Override
    public TradeDetail getTrade(Symbol symbol) {
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", symbol.getCode());
        return HttpClientUtil.get(huobiConfig.getTrade(), params, HuobiTradeDetailResponse.class).getData();
    }

    @Override
    public List<HistoryTradeData> getHistoryTrade(Symbol symbol, Integer size) {
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", symbol.getCode());
        params.put("size", String.valueOf(size));
        return HttpClientUtil.get(huobiConfig.getHistoryTrade(), params, HuobiHistoryTradeDetailResponse.class).getData();
    }

    @Override
    public DepthTick getDepth(Symbol symbol, Type type) {
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", symbol.getCode());
        params.put("type", type.getCode());
        return HttpClientUtil.get(huobiConfig.getDepth(), params, HuobiDepthResponse.class).getTick();
    }

    @Override
    public Tick getDetail(Symbol symbol) {
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", symbol.getCode());
        return HttpClientUtil.get(huobiConfig.getDetail(), params, HuobiDetailResponse.class).getTick();
    }

}
