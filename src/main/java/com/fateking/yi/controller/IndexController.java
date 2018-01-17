package com.fateking.yi.controller;

import com.fateking.yi.enums.Period;
import com.fateking.yi.enums.Symbol;
import com.fateking.yi.enums.Type;
import com.fateking.yi.service.MarketService;
import com.fateking.yi.support.GlobalContext;
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

    //https://api.huobipro.com/v1/order/orders/?
    // symbol=xrpusdt&size=11&states=partial-canceled,
    // filled,canceled&quote=usdt&
    // coin=xrp&account-id=1437644


    @GetMapping("/huobi/kline")
    public Result kline() {
        GlobalContext.setToken("9r3Ixnx-IW4GnRwaeI_M-G9vwKGNl2uoZD5zEy5SVSgY-uOP2m0-gvjE57ad1qDF");
        return Result.success(SpringObjectFactory.getBean(MarketService.class).getKLine(Symbol.XRP_USDT, Period._1min, 200));
    }

    @GetMapping("/huobi/merged")
    public Result merged() {
        GlobalContext.setToken("9r3Ixnx-IW4GnRwaeI_M-G9vwKGNl2uoZD5zEy5SVSgY-uOP2m0-gvjE57ad1qDF");
        return Result.success(SpringObjectFactory.getBean(MarketService.class).getMerged(Symbol.XRP_USDT));
    }

    @GetMapping("/huobi/trade")
    public Result trade() {
        GlobalContext.setToken("9r3Ixnx-IW4GnRwaeI_M-G9vwKGNl2uoZD5zEy5SVSgY-uOP2m0-gvjE57ad1qDF");
        return Result.success(SpringObjectFactory.getBean(MarketService.class).getTrade(Symbol.XRP_USDT));
    }

    @GetMapping("/huobi/depth")
    public Result depth() {
        GlobalContext.setToken("9r3Ixnx-IW4GnRwaeI_M-G9vwKGNl2uoZD5zEy5SVSgY-uOP2m0-gvjE57ad1qDF");
        return Result.success(SpringObjectFactory.getBean(MarketService.class).getDepth(Symbol.ETH_USDT, Type.Step1));
    }

    @GetMapping("/huobi/history/trade")
    public Result historyTrade() {
        GlobalContext.setToken("9r3Ixnx-IW4GnRwaeI_M-G9vwKGNl2uoZD5zEy5SVSgY-uOP2m0-gvjE57ad1qDF");
        return Result.success(SpringObjectFactory.getBean(MarketService.class).getHistoryTrade(Symbol.XRP_USDT, 100));
    }

    @GetMapping("/huobi/detail")
    public Result detail() {
        GlobalContext.setToken("9r3Ixnx-IW4GnRwaeI_M-G9vwKGNl2uoZD5zEy5SVSgY-uOP2m0-gvjE57ad1qDF");
        return Result.success(SpringObjectFactory.getBean(MarketService.class).getDetail(Symbol.XRP_USDT));
    }



}
