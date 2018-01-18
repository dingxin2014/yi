package com.fateking.yi.controller;

import com.fateking.yi.enums.Period;
import com.fateking.yi.enums.Symbol;
import com.fateking.yi.enums.Type;
import com.fateking.yi.service.CommonService;
import com.fateking.yi.service.MarketService;
import com.fateking.yi.support.Result;
import com.fateking.yi.support.SpringObjectFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dingxin
 */
@RestController
public class IndexController {


    @GetMapping({"", "/"})
    public Result<String> index() {
        return Result.success();
    }


    @GetMapping("/huobi/kline")
    public Result kline() {
        return Result.success(SpringObjectFactory.getBean(MarketService.class).getKLine(Symbol.XRP_USDT, Period._1min, 200));
    }

    @GetMapping("/huobi/merged")
    public Result merged() {
        return Result.success(SpringObjectFactory.getBean(MarketService.class).getMerged(Symbol.XRP_USDT));
    }

    @GetMapping("/huobi/trade")
    public Result trade() {
        return Result.success(SpringObjectFactory.getBean(MarketService.class).getTrade(Symbol.XRP_USDT));
    }

    @GetMapping("/huobi/depth")
    public Result depth() {
        return Result.success(SpringObjectFactory.getBean(MarketService.class).getDepth(Symbol.ETH_USDT, Type.Step1));
    }

    @GetMapping("/huobi/history/trade")
    public Result historyTrade() {
        return Result.success(SpringObjectFactory.getBean(MarketService.class).getHistoryTrade(Symbol.XRP_USDT, 100));
    }

    @GetMapping("/huobi/detail")
    public Result detail() {
        return Result.success(SpringObjectFactory.getBean(MarketService.class).getDetail(Symbol.XRP_USDT));
    }

    @GetMapping("/huobi/currency")
    public Result currency() {
        return Result.success(SpringObjectFactory.getBean(CommonService.class).getCurrencys());
    }


}
