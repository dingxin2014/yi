package com.fateking.yi.main.strategy.impl;

import com.fateking.yi.enums.Symbol;
import com.fateking.yi.main.strategy.Strategy;
import com.fateking.yi.main.strategy.StrategyResult;
import org.springframework.stereotype.Component;

/**
 * 网格策略
 */
@Component
public class GridStrategy implements Strategy {

    @Override
    public StrategyResult parse(Symbol symbol) {
        return null;
    }

}
