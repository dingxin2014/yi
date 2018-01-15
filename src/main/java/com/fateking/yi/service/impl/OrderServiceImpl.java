package com.fateking.yi.service.impl;

import com.fateking.yi.dto.Order;
import com.fateking.yi.enums.Direct;
import com.fateking.yi.enums.State;
import com.fateking.yi.enums.Symbol;
import com.fateking.yi.enums.TradeType;
import com.fateking.yi.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Object sendOrder(BigDecimal amount, Symbol symbol, TradeType tradeType, BigDecimal price) {
        return null;
    }

    @Override
    public Object cancelOrder(Long orderId) {
        return null;
    }

    @Override
    public Order getOrder(Long orderId) {
        return null;
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
