package com.fateking.yi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author dingxin
 */
@Configuration
@ConfigurationProperties(prefix = "huobi")
@Getter
@Setter
public class HuobiConfig {

    @Value("${tokenKey: #{null}}")
    private String tokenKey;

    //行情API GET
    @Value("${kline: #{null}}")
    private String kline;
    @Value("${merged: #{null}}")
    private String merged;
    @Value("${depth: #{null}}")
    private String depth;
    @Value("${trade: #{null}}")
    private String trade;
    @Value("${historyTrade: #{null}")
    private String historyTrade;
    @Value("${detail: #{null}")
    private String detail;

    //基本信息API GET
    @Value("${symbols: #{null}")
    private String symbols;
    @Value("${timestamp: #{null}")
    private String timestamp;
    @Value("${currencys: #{null}")
    private String currencys;

    //账户API GET
    @Value("${accounts: #{null}")
    private String accounts;
    @Value("${balance: #{null}")
    private String balance;

    //交易API
    @Value("${place: #{null}")
    private String place;
    @Value("${cancel: #{null}")
    private String cancel;
    @Value("${batch_cancel: #{null}")
    private String batchCancel;
    @Value("${order: #{null}")
    private String order;
    @Value("${delegate: #{null}")
    private String delegate;
    @Value("${match: #{null}")
    private String match;

}
