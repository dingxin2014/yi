package com.fateking.yi.service.impl;

import com.alibaba.fastjson.JSON;
import com.fateking.yi.config.HuobiConfig;
import com.fateking.yi.dto.HuobiStandardResponse;
import com.fateking.yi.dto.Order;
import com.fateking.yi.dto.OrderMatch;
import com.fateking.yi.enums.Direct;
import com.fateking.yi.enums.State;
import com.fateking.yi.enums.Symbol;
import com.fateking.yi.enums.TradeType;
import com.fateking.yi.service.OrderService;
import com.fateking.yi.support.HuobiHttpClient;
import com.fateking.yi.utils.SpElUtil;
import com.fateking.yi.utils.StringUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.fateking.yi.utils.Assert.notNull;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    HuobiHttpClient huobiHttpClient;
    @Autowired
    HuobiConfig huobiConfig;

    @Override
    public Long placeOrder(Long accountId, BigDecimal amount, BigDecimal price, String source, Symbol symbol, TradeType tradeType) {
        notNull(source, "source must not be null!");
        Map<String, String> params = Maps.newHashMap();
        params.put("account-id", String.valueOf(accountId));
        params.put("amount", String.valueOf(amount));
        params.put("price", String.valueOf(price));
        params.put("source", source);
        params.put("symbol", symbol.getCode());
        params.put("type", tradeType.getCode());
        HuobiStandardResponse response = huobiHttpClient.post(huobiConfig.getPlace(), params, HuobiStandardResponse.class);
        if (response == null) {
            return null;
        }
        return (Long) response.getData();
    }

    @Override
    public Long cancelOrder(Long orderId) {
        notNull(orderId, "order-id must not be null!");
        Map<String, Object> context = Maps.newHashMap();
        context.put("order-id", String.valueOf(orderId));
        HuobiStandardResponse response = huobiHttpClient.post(SpElUtil.parse(huobiConfig.getCancel(), context), null, HuobiStandardResponse.class);
        if (response == null) {
            return null;
        }
        return (Long) response.getData();
    }

    @Override
    public Map<String, List> batchCancelOrder(List<Long> orderIdList) {
        notNull(orderIdList, "order-ids must not be null!");
        Map<String, String> params = Maps.newHashMap();
        params.put("order-ids", JSON.toJSONString(orderIdList));
        HuobiStandardResponse response = huobiHttpClient.post(huobiConfig.getBatchCancel(), params, HuobiStandardResponse.class);
        if (response == null) {
            return null;
        }
        return (Map<String, List>) response.getData();
    }

    @Override
    public Order getOrder(Long orderId) {
        notNull(orderId, "order-id must not be null!");
        Map<String, Object> context = Maps.newHashMap();
        context.put("order-id", orderId);
        HuobiStandardResponse response = huobiHttpClient.get(SpElUtil.parse(huobiConfig.getOrder(), context), null, HuobiStandardResponse.class);
        if (response == null) {
            return null;
        }
        return (Order) response.getData();
    }

    @Override
    @Retryable
    public OrderMatch getOrderMatchResults(Long orderId) {
        notNull(orderId, "order-id must not be null!");
        Map<String, Object> context = Maps.newHashMap();
        context.put("order-id", orderId);
        HuobiStandardResponse response = huobiHttpClient.get(SpElUtil.parse(huobiConfig.getOrderMatch(), context), null, HuobiStandardResponse.class);
        if (response == null) {
            return null;
        }
        return (OrderMatch) response.getData();
    }

    @Override
    @Retryable
    public List<Order> getDelegations(Symbol symbol, State[] states, TradeType[] types, Date startDate, Date endDate, Direct direct, Integer size, Long from) {
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", symbol.getCode());
        params.put("states", StringUtil.join(",", Arrays.asList(states), State::getCode));
        if (types != null) {
            params.put("types", StringUtil.join(",", Arrays.asList(states), State::getCode));
        }
        if (startDate != null) {
            params.put("start-date", DateFormatUtils.format(startDate, "yyyy-MM-dd"));
        }
        if (endDate != null) {
            params.put("end-date", DateFormatUtils.format(endDate, "yyyy-MM-dd"));
        }
        if (direct != null) {
            params.put("direct", direct.getCode());
        }
        if (from != null) {
            params.put("from", String.valueOf(from));
        }
        if (size != null) {
            params.put("size", String.valueOf(size));
        }
        HuobiStandardResponse response = huobiHttpClient.get(huobiConfig.getDelegate(), params, HuobiStandardResponse.class);
        if (response == null) {
            return null;
        }
        return (List<Order>) response.getData();
    }

    @Override
    @Retryable
    public List<OrderMatch> getOrderMatchResult(Symbol symbol, TradeType[] types, Date startDate, Date endDate, Direct direct, Integer size, Long from) {
        Map<String, String> params = Maps.newHashMap();
        params.put("symbol", symbol.getCode());
        if (types != null) {
            params.put("types", StringUtil.join(",", Arrays.asList(types), TradeType::getCode));
        }
        if (startDate != null) {
            params.put("start-date", DateFormatUtils.format(startDate, "yyyy-MM-dd"));
        }
        if (endDate != null) {
            params.put("end-date", DateFormatUtils.format(endDate, "yyyy-MM-dd"));
        }
        if (from != null) {
            params.put("from", String.valueOf(from));
        }
        if (direct != null) {
            params.put("direct", direct.getCode());
        }
        if (size != null) {
            params.put("size", String.valueOf(size));
        }
        HuobiStandardResponse response = huobiHttpClient.get(huobiConfig.getMatch(), params, HuobiStandardResponse.class);
        if (response == null) {
            return null;
        }
        return (List<OrderMatch>) response.getData();
    }


}
