package com.fateking.yi.service.impl;

import com.fateking.yi.config.HuobiConfig;
import com.fateking.yi.dto.*;
import com.fateking.yi.enums.Period;
import com.fateking.yi.enums.Symbol;
import com.fateking.yi.enums.Type;
import com.fateking.yi.service.MarketService;
import com.fateking.yi.utils.HttpClientUtil;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author dingxin
 */
@Service
public class HuobiMarketServiceImpl implements MarketService {

    @Autowired
    HuobiConfig huobiConfig;

    @Override
    public HuobiResponse getKLine(Symbol symbol, Period period, Integer size) {
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", symbol.getCode());
        params.put("period", period.getCode());
        params.put("size", String.valueOf(size));
        return HttpClientUtil.get(huobiConfig.getKline(), HuobiKLineResponse.class);
    }

    @Override
    public HuobiResponse getMerged(Symbol symbol) {
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", symbol.getCode());
        return HttpClientUtil.get(huobiConfig.getMerged(), HuobiMergedResponse.class);
    }

    @Override
    public HuobiResponse getTrade(Symbol symbol) {
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", symbol.getCode());
        return HttpClientUtil.get(huobiConfig.getTrade(), HuobiTradeDetailResponse.class);
    }

    @Override
    public HuobiResponse getHistoryTrade(Symbol symbol, Integer size) {
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", symbol.getCode());
        params.put("size", String.valueOf(size));
        return HttpClientUtil.get(huobiConfig.getHistoryTrade(), HuobiHistoryTradeDetailResponse.class);
    }

    @Override
    public HuobiResponse getDepth(Symbol symbol, Type type) {
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", symbol.getCode());
        params.put("type", type.getCode());
        return HttpClientUtil.get(huobiConfig.getDepth(), HuobiDepthResponse.class);
    }

    @Override
    public HuobiResponse getTicker(Symbol symbol) {
        return null;
    }

    @Override
    public HuobiResponse getDetail(Symbol symbol) {
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", symbol.getCode());
        return HttpClientUtil.get(huobiConfig.getDetail(), HuobiDetailResponse.class);
    }

}
