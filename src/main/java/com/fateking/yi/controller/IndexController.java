package com.fateking.yi.controller;

import com.fateking.yi.utils.HttpClientUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author dingxin
 */
@RestController
public class IndexController {


    @GetMapping({"", "/"})
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("ok");
    }

    //https://api.huobipro.com/v1/order/orders/?
    // symbol=xrpusdt&size=11&states=partial-canceled,
    // filled,canceled&quote=usdt&
    // coin=xrp&account-id=1437644

    @GetMapping("/huobi")
    public ResponseEntity huobi() {
        return ResponseEntity.ok(HttpClientUtil.fetchXrp("MGREx5HFvD8uSB8FkGdQsXt8akQEURFY2iV9u7BZ8e8Y-uOP2m0-gvjE57ad1qDF"));
    }


}
