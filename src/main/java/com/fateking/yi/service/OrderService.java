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
     * @param accountId 账户 ID，使用accounts方法获得。币币交易使用‘spot’账户的accountid；借贷资产交易，请使用‘margin’账户的accountid
     * @param amount 限价单表示下单数量，市价买单时表示买多少钱，市价卖单时表示卖多少币
     * @param price  下单价格，市价单不传该参数
     * @param source 订单来源 api，如果使用借贷资产交易，请填写‘margin-api’
     * @param symbol 交易对
     * @param tradeType 订单类型
     * @return  订单ID
     */
    Long sendOrder(Long accountId, BigDecimal amount, BigDecimal price, String source,
                     Symbol symbol, TradeType tradeType);


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
