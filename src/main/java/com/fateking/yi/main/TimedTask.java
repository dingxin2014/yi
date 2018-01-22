package com.fateking.yi.main;

import com.fateking.yi.service.CommonService;
import com.fateking.yi.utils.OSUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TimedTask {

    @Autowired
    private CommonService commonService;

    /**
     * 检查与设置操作系统时间
     */
    @Scheduled(cron = "${yi.schedule.cron.os: #{'0 0/30 * * * ?'}}")
    public void checkOSTime() {
        Long time;
        Long shift;
        if ((shift = Math.abs(System.currentTimeMillis() - (time = commonService.getTimestamp()))) > 500) {
            OSUtil.setOSTime(time + 50);
            log.info("更新系统时间鉴于存在误差" + shift + "毫秒");
        }
    }
}
