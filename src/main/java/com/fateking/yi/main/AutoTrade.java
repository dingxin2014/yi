package com.fateking.yi.main;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dingxin
 */
@Component
public class AutoTrade {

    private static final ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    @PostConstruct
    public void autoTrade() {
        //初始化扫描主线程
        singleThreadExecutor.submit(new ScanService());
    }


}
