package com.fateking.yi.service.impl;

import com.fateking.yi.config.HuobiConfig;
import com.fateking.yi.dto.HuobiStandardResponse;
import com.fateking.yi.dto.SymbolDTO;
import com.fateking.yi.enums.SymbolPartition;
import com.fateking.yi.service.CommonService;
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
    public SymbolDTO getSymbols(String baseCurrency, String quoteCurrency, Integer pricePrecision,
                                Integer amountPrecision, SymbolPartition symbolPartition) {
        Map<String, String> params = Maps.newHashMap();
        params.put("base-currency", baseCurrency);
        params.put("quote-currency", quoteCurrency);
        params.put("price-precision", String.valueOf(pricePrecision));
        params.put("amount-precision", String.valueOf(amountPrecision));
        params.put("symbol-partition", symbolPartition.getCode());
        return (SymbolDTO) HttpClientUtil.get(huobiConfig.getSymbols(), params, HuobiStandardResponse.class).getData();
    }

    @Override
    public Long getTimestamp() {
        return (Long) HttpClientUtil.get(huobiConfig.getTimestamp(), null, HuobiStandardResponse.class).getData();
    }

    @Override
    public List<String> getCurrencys() {
        return (List<String>) HttpClientUtil.get(huobiConfig.getCurrencys(), null, HuobiStandardResponse.class).getData();
    }


}
