package com.fateking.yi;

import com.fateking.yi.autoconfigurer.EnableBaseConfig;
import com.fateking.yi.exception.YiException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author dingxin
 */
@Configuration
@EnableTransactionManagement
@EnableAsync
@SpringBootApplication
@ComponentScan("com.fateking.yi")
@EnableAutoConfiguration
@EnableBaseConfig(defaultThrowExceptionClass = YiException.class)
public class YiApplication {

    public static void main(String[] args) {
        SpringApplication.run(YiApplication.class, args);
    }

}
