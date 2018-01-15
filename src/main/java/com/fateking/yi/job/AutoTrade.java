package com.fateking.yi.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author dingxin
 */
@Component
public class AutoTrade {


    @Scheduled(cron = "")
    public void autoTrade() {

    }

    @Scheduled(cron = "")
    public void analysis() {

    }

}
