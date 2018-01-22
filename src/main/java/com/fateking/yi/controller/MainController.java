package com.fateking.yi.controller;

import com.fateking.yi.enums.Symbol;
import com.fateking.yi.support.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {


    @GetMapping("/symbol/init/{symbol}")
    public Result initSymbol(@PathVariable Symbol symbol) {
        return Result.ok(String.format("设置%s交易对成功!", symbol));
    }

    @GetMapping("/symbol/destroy/{symbol}")
    public Result destroySymbol(@PathVariable Symbol symbol) {
        return Result.ok(String.format("退出%s交易对成功!", symbol));
    }

}
