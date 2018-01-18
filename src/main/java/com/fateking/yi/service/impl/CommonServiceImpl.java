package com.fateking.yi.service.impl;

import com.fateking.yi.config.HuobiConfig;
import com.fateking.yi.enums.SymbolPartition;
import com.fateking.yi.service.CommonService;
import com.fateking.yi.support.Result;
import com.fateking.yi.utils.HttpClientUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CommonServiceImpl implements CommonService {

    @Autowired
    HuobiConfig huobiConfig;

    @Override
    public Result getSymbols(String baseCurrency, String quoteCurrency, Integer pricePrecision,
                             Integer amountPrecision, SymbolPartition symbolPartition) {
        Map<String, String> params = Maps.newHashMap();
        params.put("base-currency", baseCurrency);
        params.put("quote-currency", quoteCurrency);
        params.put("price-precision", String.valueOf(pricePrecision));
        params.put("amount-precision", String.valueOf(amountPrecision));
        params.put("symbol-partition", symbolPartition.getCode());
        return HttpClientUtil.get(huobiConfig.getSymbols(), params, Result.class);
    }

    @Override
    public Result getTimestamp() {
        return HttpClientUtil.get(huobiConfig.getTimestamp(), null, Result.class);
    }

    @Override
    public Result<List<String>> getCurrencys() {
        return HttpClientUtil.get(huobiConfig.getCurrencys(), null, Result.class);
    }


}
