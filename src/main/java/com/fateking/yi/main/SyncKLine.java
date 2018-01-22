package com.fateking.yi.main;

import com.fateking.yi.dto.KTick;
import com.fateking.yi.enums.Period;
import com.fateking.yi.enums.Symbol;
import com.fateking.yi.service.AccountService;
import com.fateking.yi.service.CommonService;
import com.fateking.yi.service.MarketService;
import com.fateking.yi.service.OrderService;
import com.fateking.yi.support.GlobalContext;
import com.fateking.yi.support.SpringObjectFactory;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import static com.fateking.yi.utils.BeanUtils.isNull;

@Slf4j
public class SyncKLine implements Runnable {

    private MarketService marketService;
    private CommonService commonService;
    private AccountService accountService;
    private OrderService orderService;

    private Symbol symbol;          //交易对

    public SyncKLine(Symbol symbol) {
        this.symbol = symbol;
        marketService = SpringObjectFactory.getBean(MarketService.class);
        commonService = SpringObjectFactory.getBean(CommonService.class);
        accountService = SpringObjectFactory.getBean(AccountService.class);
        orderService = SpringObjectFactory.getBean(OrderService.class);
    }

    @Override
    public void run() {
        if (symbol == null) {
            return;
        } else {
            log.info("交易对为[" + symbol + "]");
        }

        log.info("更新K线");
        List<KTick> KLineList = marketService.getKLine(symbol, Period._1min, 180);
        GlobalContext.stack.get(symbol).parseKList(KLineList);
    }
}
