package com.fateking.yi.service;

import com.fateking.yi.dto.Order;
import com.fateking.yi.dto.OrderMatch;
import com.fateking.yi.enums.Direct;
import com.fateking.yi.enums.State;
import com.fateking.yi.enums.Symbol;
import com.fateking.yi.enums.TradeType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
     * @param amount    限价单表示下单数量，市价买单时表示买多少钱，市价卖单时表示卖多少币
     * @param price     下单价格，市价单不传该参数
     * @param source    订单来源 api，如果使用借贷资产交易，请填写‘margin-api’
     * @param symbol    交易对
     * @param tradeType 订单类型
     * @return 订单ID
     */
    Long placeOrder(Long accountId, BigDecimal amount, BigDecimal price, String source,
                    Symbol symbol, TradeType tradeType);


    /**
     * 取消订单
     *
     * @param orderId
     * @return
     */
    Long cancelOrder(Long orderId);


    /**
     * 批量取消订单
     *
     * @param orderIdList
     * @return
     */
    Map<String, List> batchCancelOrder(List<Long> orderIdList);

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
    OrderMatch getOrderMatchResults(Long orderId);

    /**
     * 查询当前委托 历史委托
     *
     * @param symbol    交易对
     * @param states    查询的订单状态组合，使用','分割
     * @param types     查询的订单类型组合，使用','分割 非必需
     * @param startDate 查询开始日期, 日期格式yyyy-mm-dd 非必需
     * @param endDate   查询结束日期, 日期格式yyyy-mm-dd 非必需
     * @param direct    查询方向 非必需
     * @param size      查询记录大小 非必需
     * @param from      查询起始 ID 非必需
     * @return
     */
    List<Order> getDelegations(Symbol symbol, State[] states, TradeType[] types,
                               Date startDate, Date endDate, Direct direct, Integer size, Long from);


    /**
     * 查询当前成交 历史成交
     *
     * @param symbol        交易对
     * @param types         查询的订单类型组合，使用','分割 非必需
     * @param startDate     查询开始日期, 日期格式yyyy-mm-dd 非必需
     * @param endDate       查询结束日期, 日期格式yyyy-mm-dd 非必需
     * @param direct        查询方向	    非必需
     * @param size          查询记录大小	非必需
     * @param from          查询起始 ID   非必需
     * @return
     */
    List<OrderMatch> getOrderMatchResult(Symbol symbol, TradeType[] types,
                                    Date startDate, Date endDate, Direct direct, Integer size, Long from);
}
