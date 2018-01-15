package com.fateking.yi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "huobi")
@Getter
@Setter
public class HuobiConfig {

    private String prefix;

    @Setter
    @Getter
    @Configuration
    @ConfigurationProperties(prefix = "market")
    public static class Url {

        private String trade;
    }
}
