package com.fateking.yi.controller;

import com.fateking.yi.enums.Period;
import com.fateking.yi.enums.Symbol;
import com.fateking.yi.enums.Type;
import com.fateking.yi.service.AccountService;
import com.fateking.yi.service.CommonService;
import com.fateking.yi.service.MarketService;
import com.fateking.yi.support.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dingxin
 */
@RestController
public class IndexController {

    @Autowired
    MarketService marketService;
    @Autowired
    AccountService accountService;
    @Autowired
    CommonService commonService;

    @GetMapping({"", "/"})
    public Result<String> index() {
        return Result.ok();
    }


    @GetMapping("/huobi/kline")
    public Result kline() {
        return Result.ok(marketService.getKLine(Symbol.XRP_USDT, Period._1min, 200));
    }

    @GetMapping("/huobi/merged")
    public Result merged() {
        return Result.ok(marketService.getMerged(Symbol.XRP_USDT));
    }

    @GetMapping("/huobi/trade")
    public Result trade() {
        return Result.ok(marketService.getTrade(Symbol.XRP_USDT));
    }

    @GetMapping("/huobi/depth")
    public Result depth() {
        return Result.ok(marketService.getDepth(Symbol.XRP_USDT, Type.Step1));
    }

    @GetMapping("/huobi/history/trade")
    public Result historyTrade() {
        return Result.ok(marketService.getHistoryTrade(Symbol.XRP_USDT, 100));
    }

    @GetMapping("/huobi/detail")
    public Result detail() {
        return Result.ok(marketService.getDetail(Symbol.XRP_USDT));
    }

    @GetMapping("/huobi/currency")
    public Result currency() {
        return Result.ok(commonService.getCurrencys());
    }


    @GetMapping("/huobi/account")
    public Result account() {
        return Result.ok(accountService.getAccount());
    }

    @GetMapping("/huobi/balance/{accountId}")
    public Result balance(@PathVariable Long accountId) {
        return Result.ok(accountService.getBalance(accountId));
    }

    @GetMapping("/huobi/order")
    public Result order() {
        return Result.ok();
    }
}
