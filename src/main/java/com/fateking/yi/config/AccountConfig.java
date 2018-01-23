package com.fateking.yi.config;

import com.fateking.yi.utils.HuobiHttpClientUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ConfigurationProperties(prefix = "yi.account")
@Data
public class AccountConfig {

    @Value("${access_key :#{null}}")
    private String accessKey;
    @Value("${private_key :#{null}}")
    private String privateKey;


    @PostConstruct
    public void init() {
        HuobiHttpClientUtil.setKey(accessKey, privateKey);
    }

}
