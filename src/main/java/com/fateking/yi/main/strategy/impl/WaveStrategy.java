package com.fateking.yi.main.strategy.impl;

import com.fateking.yi.config.GlobalConfig;
import com.fateking.yi.dto.KTick;
import com.fateking.yi.enums.Symbol;
import com.fateking.yi.main.strategy.Strategy;
import com.fateking.yi.main.strategy.StrategyResult;
import com.fateking.yi.service.ParseService;
import com.fateking.yi.support.KTickStack;
import com.fateking.yi.utils.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

import static com.fateking.yi.support.GlobalContext.stack;

/**
 * 波动策略
 */
@Component
public class WaveStrategy implements Strategy {

    @Autowired
    private GlobalConfig config;
    @Autowired
    private ParseService parseService;

    /**
     * 方差阈值
     */
    private BigDecimal threshold;

    public void setThreshold(BigDecimal threshold) {
        this.threshold = threshold;
    }

    @Override
    public StrategyResult parse(Symbol symbol) {
        boolean simulate = config.getSimulate();

        StrategyResult strategyResult = new StrategyResult();

        KTickStack tickStack = stack.get(symbol);
        if (tickStack == null) {
            strategyResult.setResultType(StrategyResult.StrategyResultType.WAIT);
            return strategyResult;
        }
        List<KTick> list = tickStack.values();
        Long timestamp = tickStack.getTop().getId();
        BigDecimal var = MathUtil.variance(list.stream()
                .map(KTick::mid)
                .collect(Collectors.toList()).toArray(new BigDecimal[list.size()]));

        System.err.println("方差 >>> " + var);


        //小于阈值 可以下单
        if(threshold != null && var.compareTo(threshold) <= 0) {
            strategyResult.setResultType(StrategyResult.StrategyResultType.CONFIRM);
            strategyResult.setDesc("阈值方差 " + threshold + " 实际方差" + var);
        }


        return strategyResult;
    }

}
