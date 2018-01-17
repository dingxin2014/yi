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


}
