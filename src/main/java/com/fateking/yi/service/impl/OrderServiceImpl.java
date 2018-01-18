package com.fateking.yi.service.impl;

import com.alibaba.fastjson.JSON;
import com.fateking.yi.config.HuobiConfig;
import com.fateking.yi.dto.HuobiStandardResponse;
import com.fateking.yi.dto.Order;
import com.fateking.yi.enums.Direct;
import com.fateking.yi.enums.State;
import com.fateking.yi.enums.Symbol;
import com.fateking.yi.enums.TradeType;
import com.fateking.yi.service.OrderService;
import com.fateking.yi.utils.HttpClientUtil;
import com.fateking.yi.utils.SpElUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.fateking.yi.utils.Assert.notNull;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    HuobiConfig huobiConfig;


    @Override
    public Long sendOrder(Long accountId, BigDecimal amount, BigDecimal price, String source, Symbol symbol, TradeType tradeType) {
        notNull(source, "source must not be null!");
        Map<String, String> params = Maps.newHashMap();
        params.put("account-id", String.valueOf(accountId));
        params.put("amount", String.valueOf(amount));
        params.put("price", String.valueOf(price));
        params.put("source", source);
        params.put("symbol", symbol.getCode());
        params.put("type", tradeType.getCode());
        return (Long) HttpClientUtil.post(huobiConfig.getPlace(), params, HuobiStandardResponse.class).getData();
    }

    @Override
    public Long cancelOrder(Long orderId) {
        notNull(orderId, "order-id must not be null!");
        Map<String, Object> context = Maps.newHashMap();
        context.put("order-id", String.valueOf(orderId));
        return (Long) HttpClientUtil.post(SpElUtil.parse(huobiConfig.getCancel(), context), null, HuobiStandardResponse.class).getData();
    }

    @Override
    public Map<String, List> batchCancelOrder(List<Long> orderIdList) {
        notNull(orderIdList, "order-ids must not be null!");
        Map<String, String> params = Maps.newHashMap();
        params.put("order-ids", JSON.toJSONString(orderIdList));
        return (Map<String, List>) HttpClientUtil.post(huobiConfig.getBatchCancel(), params, HuobiStandardResponse.class).getData();
    }

    @Override
    public Order getOrder(Long orderId) {
        notNull(orderId, "order-id must not be null!");
        Map<String, Object> context = Maps.newHashMap();
        context.put("order-id", orderId);
        return (Order) HttpClientUtil.get(SpElUtil.parse(huobiConfig.getOrder(), context), null, HuobiStandardResponse.class).getData();
    }

    @Override
    public Object getOrderMatchResults(Long orderId) {
        return null;
    }

    @Override
    public List<Order> getOrderList(Symbol symbol, State state, TradeType tradeType, Date startDate, Date endDate, Direct direct, Integer size) {
        return null;
    }

    @Override
    public List<Order> getOrderMatchResult(Symbol symbol, TradeType tradeType, Date startDate, Date endDate, Direct direct, Integer size) {
        return null;
    }
}
