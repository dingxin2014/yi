package com.fateking.yi.main.strategy;

import com.fateking.yi.enums.Symbol;

public interface Strategy {

    StrategyResult parse(Symbol symbol);

}
