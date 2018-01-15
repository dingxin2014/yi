package com.fateking.yi.service.impl;

import com.fateking.yi.enums.Period;
import com.fateking.yi.enums.Symbol;
import com.fateking.yi.service.MarketService;
import org.springframework.stereotype.Service;

/**
 *
 * @author dingxin
 */
@Service
public class HuobiMarketServiceImpl implements MarketService {


    @Override
    public Object getKLine(Symbol symbol, Period period, Integer size) {
        return null;
    }

    @Override
    public Object getDepth(Symbol symbol, Period period) {
        return null;
    }

    @Override
    public Object getTrade(Symbol symbol) {
        return null;
    }

    @Override
    public Object getTicker(Symbol symbol) {
        return null;
    }

    @Override
    public Object getDetail(Symbol symbol) {
        return null;
    }

    @Override
    public Object getSymbols() {
        return null;
    }
}
