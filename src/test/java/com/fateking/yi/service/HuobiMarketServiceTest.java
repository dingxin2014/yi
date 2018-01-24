package com.fateking.yi.service;


import com.fateking.yi.YiApplicationTests;
import com.fateking.yi.dto.KTick;
import com.fateking.yi.enums.Period;
import com.fateking.yi.enums.Symbol;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static org.junit.Assert.assertTrue;


public class HuobiMarketServiceTest extends YiApplicationTests {

    @Autowired
    MarketService marketService;

    @Test
    public void testGetKLine() {
        List<KTick> list = marketService.getKLine(Symbol.XRP_USDT, Period._1min, 100);
        assertTrue(!CollectionUtils.isEmpty(list));
    }


}
