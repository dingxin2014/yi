package com.fateking.yi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author dingxin
 */
@EnableRetry
@Configuration
//@EnableTransactionManagement
@EnableAsync
@SpringBootApplication
@ComponentScan("com.fateking.yi")
@EnableAutoConfiguration(exclude = {
        //禁止数据源自动配置
        DataSourceAutoConfiguration.class,
        //禁止事务管理器自动配置
        DataSourceTransactionManagerAutoConfiguration.class,
        //禁止JPA自动配置
        HibernateJpaAutoConfiguration.class})
public class YiApplication {

    public static void main(String[] args) {
        SpringApplication.run(YiApplication.class, args);
    }

}
