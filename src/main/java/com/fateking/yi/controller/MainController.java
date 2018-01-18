package com.fateking.yi.controller;

import com.fateking.yi.enums.Symbol;
import com.fateking.yi.main.MainService;
import com.fateking.yi.support.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {



    @GetMapping("symbol")
    public Result setSymbol(Symbol symbol) {

        return Result.success(String.format("设置%s交易对成功!", symbol));
    }

}
