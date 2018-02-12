package com.fateking.yi.main;

import com.fateking.yi.dto.AccountBalance;
import com.fateking.yi.dto.Balance;
import com.fateking.yi.dto.DepthTick;
import com.fateking.yi.dto.Order;
import com.fateking.yi.enums.State;
import com.fateking.yi.enums.Symbol;
import com.fateking.yi.enums.Type;
import com.fateking.yi.main.strategy.Strategy;
import com.fateking.yi.main.strategy.StrategyResult;
import com.fateking.yi.main.strategy.impl.WaveStrategy;
import com.fateking.yi.service.AccountService;
import com.fateking.yi.service.CommonService;
import com.fateking.yi.service.MarketService;
import com.fateking.yi.service.OrderService;
import com.fateking.yi.support.GlobalContext;
import com.fateking.yi.support.SpringObjectFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

import static com.fateking.yi.support.GlobalContext.stack;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 主服务线程
 *
 * @author dingxin
 */
@Slf4j
public class MainService implements Runnable {

    private MarketService marketService;
    private CommonService commonService;
    private AccountService accountService;
    private OrderService orderService;
    private Strategy strategy;

    private Symbol symbol;          //交易对

    private BigDecimal hazardRate;  //风险率

    private boolean simulate = true;//模拟标志

    public MainService(Symbol symbol) {
        this.symbol = symbol;
        marketService = SpringObjectFactory.getBean(MarketService.class);
        commonService = SpringObjectFactory.getBean(CommonService.class);
        accountService = SpringObjectFactory.getBean(AccountService.class);
        orderService = SpringObjectFactory.getBean(OrderService.class);
        strategy = SpringObjectFactory.getBean(WaveStrategy.class);
    }

    @Override
    public void run() {
        if (symbol == null) {
            return;
        } else {
            log.info("交易对为[" + symbol + "]");
        }
        log.info("获取账号信息");
        AccountBalance accountBalance = accountService.getBalance(GlobalContext.account.getId());
        if (accountBalance != null) {
            log.info("账号: " + accountBalance.getId());
            log.info("账号状态: " + accountBalance.getState());
            if (!CollectionUtils.isEmpty(accountBalance.getList())) {

            }
        }

        log.info("检查是否已经存在未完成委托单");
        List<Order> orderList = orderService.getDelegations(symbol, State.inProgressStates().toArray(new State[State.inProgressStates().size()]),
                null, null, null, null, null, null);
        if (!CollectionUtils.isEmpty(orderList)) {
            orderList.stream().forEach(order -> {
                if (order.getState() == null) {
                    log.warn("Illegal State!");
                } else if (order.getState().isInProgress()) {
                    log.info("存在进行中委托单！" + order);
                    return;
                }
            });
        }

        log.info("检查盈损");
        try {
            SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("分析K线");
        StrategyResult result = strategy.parse(symbol);
        if (StrategyResult.StrategyResultType.CONFIRM.equals(result.getResultType())) {
            log.info("准备下单");

        }

        log.info("分析深度线");
        DepthTick depth = marketService.getDepth(symbol, Type.Step1);
        log.info("计算买量");
        List<List<BigDecimal>> bids = depth.getBids();
        List<List<BigDecimal>> asks = depth.getAsks();
        BigDecimal buyCount = bids.stream().map(pair -> pair.get(1)).reduce((a, b) -> a.add(b)).get();
        BigDecimal sellCount = asks.stream().map(pair -> pair.get(1)).reduce((a, b) -> a.add(b)).get();
        log.info("当前购买深度 >>> " + buyCount);
        log.info("当前出售深度 >>> " + sellCount);


        log.info("计算过去两小时曲线");
        try {
            SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("计算风险率");
        try {
            SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("计算预计盈利");
        try {
            SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("提交购买和出售委托单");

    }
}
