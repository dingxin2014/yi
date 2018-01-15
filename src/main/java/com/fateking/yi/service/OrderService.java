package com.fateking.yi.service;

import com.fateking.yi.dto.Order;
import com.fateking.yi.enums.Direct;
import com.fateking.yi.enums.State;
import com.fateking.yi.enums.Symbol;
import com.fateking.yi.enums.TradeType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单信息
 *
 * @author dingxin
 */
public interface OrderService {

    /**
     * 下单
     *
     * @param amount
     * @param symbol
     * @param tradeType
     * @param price
     * @return
     */
    Object sendOrder(BigDecimal amount, Symbol symbol, TradeType tradeType, BigDecimal price);


    /**
     * 取消订单
     *
     * @param orderId
     * @return
     */
    Object cancelOrder(Long orderId);

    /**
     * 查询某个订单
     *
     * @param orderId
     * @return
     */
    Order getOrder(Long orderId);


    /**
     * 获取某个订单的成交明细
     *
     * @param orderId
     * @return
     */
    Object getOrderMatchResults(Long orderId);

    /**
     * 查询当前委托 历史委托
     *
     * @param symbol
     * @param state
     * @param tradeType
     * @param startDate
     * @param endDate
     * @param direct
     * @param size
     * @return
     */
    List<Order> getOrderList(Symbol symbol, State state, TradeType tradeType,
                             Date startDate, Date endDate, Direct direct, Integer size);


    /**
     * 查询当前成交 历史成交
     *
     * @param symbol
     * @param tradeType
     * @param startDate
     * @param endDate
     * @param direct
     * @param size
     * @return
     */
    List<Order> getOrderMatchResult(Symbol symbol, TradeType tradeType,
                                    Date startDate, Date endDate, Direct direct, Integer size);
}
