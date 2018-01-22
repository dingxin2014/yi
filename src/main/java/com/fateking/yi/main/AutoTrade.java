package com.fateking.yi.main;

import com.fateking.yi.config.CronConfig;
import com.fateking.yi.enums.Symbol;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author dingxin
 */
@Component
@Slf4j
public class AutoTrade {

    @Autowired
    private CronConfig cronConfig;
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private String syncCron;    //同步数据
    private String mainCron;    //主线程

    @PostConstruct
    public void init() {
        this.mainCron = cronConfig.getMain();
        this.syncCron = cronConfig.getSync();
    }

    @PostConstruct
    public void autoTrade() {
        threadPoolTaskScheduler.schedule(new SyncKLine(Symbol.XRP_USDT), new CronTrigger(syncCron));
        threadPoolTaskScheduler.schedule(new MainService(Symbol.XRP_USDT), new CronTrigger(mainCron));
    }


}
