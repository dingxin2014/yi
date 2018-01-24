package com.fateking.yi.main.strategy.impl;

import com.fateking.yi.enums.Symbol;
import com.fateking.yi.main.strategy.Strategy;
import com.fateking.yi.main.strategy.StrategyResult;
import com.fateking.yi.support.GlobalContext;
import com.fateking.yi.support.KTickStack;
import org.springframework.stereotype.Component;

/**
 * 波动策略
 */
@Component
public class WaveStrategy implements Strategy {

    @Override
    public StrategyResult parse(Symbol symbol) {
        StrategyResult strategyResult = new StrategyResult();

        KTickStack stack = GlobalContext.stack.get(symbol);
        if (stack == null) {
            strategyResult.setResultType(StrategyResult.StrategyResultType.WAIT);
            return strategyResult;
        }
        

        return strategyResult;
    }

}
