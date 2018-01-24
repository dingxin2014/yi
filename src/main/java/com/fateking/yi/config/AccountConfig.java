package com.fateking.yi.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "yi.account")
@Data
public class AccountConfig {

    @Value("${access_key :#{null}}")
    private String accessKey;
    @Value("${private_key :#{null}}")
    private String privateKey;

}
