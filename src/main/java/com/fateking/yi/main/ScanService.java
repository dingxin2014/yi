package com.fateking.yi.main;

import com.fateking.yi.service.MarketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dingxin
 */
@Component
@Slf4j
public class ScanService implements Runnable {

    @Autowired
    private MarketService marketService;

    @Override
    public void run() {
        while (true) {
            log.info("抓取数据");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
