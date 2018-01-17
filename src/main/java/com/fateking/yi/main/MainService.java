package com.fateking.yi.main;

import com.fateking.yi.service.MarketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 主服务线程
 *
 * @author dingxin
 */
@Slf4j
public class MainService implements Runnable {

    @Autowired
    private MarketService marketService;

    @Override
    public void run() {
        while (true) {
            log.info("检查是否已经存在委托单");
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
