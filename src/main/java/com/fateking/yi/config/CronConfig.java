package com.fateking.yi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "yi.schedule.cron")
@Getter
@Setter
public class CronConfig {

    @Value("${main: #{'0/1 * * * * ?'}}")
    private String main;
    @Value("${sync: #{'0/1 * * * * ?'}}")
    private String sync;

}
