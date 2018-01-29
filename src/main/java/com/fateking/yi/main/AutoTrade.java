package com.fateking.yi.main;

import com.fateking.yi.config.CronConfig;
import com.fateking.yi.dto.Account;
import com.fateking.yi.enums.Symbol;
import com.fateking.yi.service.AccountService;
import com.fateking.yi.support.GlobalContext;
import com.fateking.yi.support.KTickStack;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author dingxin
 */
@Component
@Slf4j
public class AutoTrade {

    @Autowired
    private CronConfig cronConfig;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private String syncCron;    //同步数据
    private String mainCron;    //主线程

    @PostConstruct
    public void autoTrade() {
//        syncAccount();

//        this.mainCron = cronConfig.getMain();
//        this.syncCron = cronConfig.getSync();
//        GlobalContext.stack.put(Symbol.XRP_USDT, new KTickStack(180, 60));
//        threadPoolTaskScheduler.schedule(new DaemonManagement(Symbol.XRP_USDT), new CronTrigger(syncCron));
//        threadPoolTaskScheduler.schedule(new MainService(Symbol.XRP_USDT), new CronTrigger(mainCron));
    }

    @Async
    public void syncAccount() {
        for (; ; ) {
            log.info("抓取账号信息");
            List<Account> accounts = accountService.getAccount();
            if (accounts != null) {
                BeanUtils.copyProperties(accounts.get(0), GlobalContext.account);
                log.info("抓取账号信息成功 >>>> " + accounts.toString());
                break;
            }

            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
