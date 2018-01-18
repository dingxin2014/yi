package com.fateking.yi.main;

import com.fateking.yi.enums.Symbol;
import com.fateking.yi.support.SpringObjectFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dingxin
 */
@Component
public class AutoTrade {

    private static final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(8);

    @PostConstruct
    public void autoTrade() {
        //初始化扫描主线程
        fixedThreadPool.submit(new MainService());
    }

    public void addMonitor(Symbol symbol) {
        MainService mainService = new MainService();
        fixedThreadPool.submit(mainService);
        mainService.resetSymbol(symbol);
    }


}
