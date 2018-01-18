package com.fateking.yi.main;

import com.fateking.yi.dto.Order;
import com.fateking.yi.enums.State;
import com.fateking.yi.enums.Symbol;
import com.fateking.yi.service.AccountService;
import com.fateking.yi.service.CommonService;
import com.fateking.yi.service.MarketService;
import com.fateking.yi.service.OrderService;
import com.fateking.yi.support.SpringObjectFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

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

    private Symbol symbol;

    public MainService() {
        marketService = SpringObjectFactory.getBean(MarketService.class);
        commonService = SpringObjectFactory.getBean(CommonService.class);
        accountService = SpringObjectFactory.getBean(AccountService.class);
        orderService = SpringObjectFactory.getBean(OrderService.class);
    }

    public void resetSymbol(Symbol symbol) {
        if (symbol != null) {
            log.warn("不允许切换监控Symbol!");
            return;
        }
        this.symbol = symbol;
        synchronized (this) {
            notify();
        }
    }


    @Override
    public void run() {
        while (true) {
            if (symbol == null) {
                try {
                    log.info("交易对SYMBOL为空，等待设置交易对");
                    synchronized (this) {
                        wait();
                    }
                    continue;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                log.info("交易对为[" + symbol + "]");
            }

            log.info("检查是否已经存在未完成委托单");
            List<Order> orderList = orderService.getDelegations(symbol, State.inProgressStates().toArray(new State[State.inProgressStates().size()]),
                    null, null, null, null, null, null);
            orderList.stream().forEach(order -> {
                if (order.getState() == null) {
                    log.warn("Illegal State!");
                } else if (order.getState().isInProgress()) {
                    log.info("存在进行中委托单！" + order);
                }
            });


            try {
                SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("检查盈损");
            try {
                SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            log.info("抓取K线");
            try {
                SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

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
}
