package com.fateking.yi.service.impl;

import com.fateking.yi.dto.HuobiResponse;
import com.fateking.yi.enums.SymbolPartition;
import com.fateking.yi.service.CommonService;
import com.google.common.collect.Maps;

import java.util.Map;

public class CommonServiceImpl implements CommonService {

    @Override
    public HuobiResponse getSymbols(String baseCurrency, String quoteCurrency, Integer pricePrecision,
                                    Integer amountPrecision, SymbolPartition symbolPartition) {
        Map<String, String> params = Maps.newHashMap();
        params.put("base-currency", baseCurrency);
        params.put("quote-currency", quoteCurrency);
        params.put("price-precision", String.valueOf(pricePrecision));
        params.put("amount-precision", String.valueOf(amountPrecision));
        params.put("symbol-partition", symbolPartition.getCode());
        return null;
    }

}
