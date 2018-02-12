package com.fateking.yi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fateking.yi.exception.BaseException;
import com.fateking.yi.exception.handler.AbstractExceptionAdviceHandler;
import com.fateking.yi.support.SpringObjectFactory;
import com.fateking.yi.utils.Assert;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.RetryConfiguration;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.util.ErrorHandler;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * @author dingxin
 */
@Slf4j
public abstract class AbstractBaseConfig {

    @Autowired
    PropertiesConfig propertiesConfig;

    /**
     * 配置校验器
     *
     * @return
     */
    @Bean(name = "yi-validator")
    public SpringValidatorAdapter validator() {
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setProviderClass(org.hibernate.validator.HibernateValidator.class);
        validatorFactoryBean.setValidationMessageSource(this.messageSource());
        return validatorFactoryBean;
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        org.springframework.validation.beanvalidation.MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        processor.setValidator(this.validator());
        return processor;
    }

    @Bean
    public MessageSource messageSource() {
        org.springframework.context.support.ResourceBundleMessageSource messageSource = new org.springframework.context.support.ResourceBundleMessageSource();
        messageSource.addBasenames(basename() == null ? "i18n/messages" : basename());
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(false);
        return messageSource;
    }

    @PostConstruct
    public void resetDefaultThrowExceptionClass() {
        Assert.resetDefaultThrowExceptionClass(defaultThrowExceptionClass());
    }


    @Configuration
    @ConditionalOnClass(RetryConfiguration.class)
    @ConditionalOnExpression("${yi.retry.enabled: true}")
    public class RetryConfig {

        @PostConstruct
        public void log() {
            log.info("Init Retry!");
        }

        /**
         * 根据 retry 包是否引入 参数是否启用 retryTemplate
         *
         * @return
         */
        @Bean
        public RetryTemplate retryTemplate() {
            RetryTemplate retryTemplate = new RetryTemplate();
            final SimpleRetryPolicy policy = new SimpleRetryPolicy(propertiesConfig.getMaxAttempts(), Collections.<Class<? extends Throwable>, Boolean>
                    singletonMap(Exception.class, true));
            FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
            fixedBackOffPolicy.setBackOffPeriod(100);
            retryTemplate.setRetryPolicy(policy);
            retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
            return retryTemplate;
        }

        @EnableRetry
        @Configuration
        public class EnableRetryConfig {
        }

    }

    /**
     * i18n 国际化配置文件目录
     *
     * @return
     */
    public abstract String basename();

    /**
     * 配置默认抛出异常
     *
     * @return
     */
    public abstract Class<? extends BaseException> defaultThrowExceptionClass();

    @Configuration
    @Setter
    @Getter
    public static class PropertiesConfig {

        @Value("${yi.retry.maxAttempts: #{3}}")
        private int maxAttempts;

    }

    @Configuration
    public static class SpringObjectFactoryConfig implements ApplicationContextAware {

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            SpringObjectFactory.setApplicationContext(applicationContext);
        }
    }

    @Configuration
    @ConditionalOnClass(WebMvcConfigurerAdapter.class)
    @ConditionalOnWebApplication
    public class WebConfig {

        @Configuration
        @EnableWebMvc
        public class MvcConfiguration extends WebMvcConfigurerAdapter implements ApplicationContextAware {

            private ApplicationContext applicationContext;

            @Override
            public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
                MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
                ObjectMapper objectMapper = jackson2HttpMessageConverter.getObjectMapper();
                SimpleModule simpleModule = new SimpleModule();
                simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
                simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
                simpleModule.addSerializer(BigDecimal.class, ToStringSerializer.instance);
                objectMapper.registerModule(simpleModule);
                jackson2HttpMessageConverter.setObjectMapper(objectMapper);
                converters.add(0, jackson2HttpMessageConverter);
            }

            @Override
            public Validator getValidator() {
                return (Validator) applicationContext.getBean("yi-validator");
            }

            @Override
            public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
                this.applicationContext = applicationContext;
            }

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
                registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/favicon.ico");
                super.addResourceHandlers(registry);
            }
        }

    }

    @Configuration
    @ConditionalOnMissingBean(AbstractExceptionAdviceHandler.class)
    public class ExceptionAdviceHandlerConfig {

        @RestControllerAdvice
        public class DefaultExceptionAdviceHandler extends AbstractExceptionAdviceHandler {

            @PostConstruct
            public void log() {
                log.info("Init DefaultExceptionAdviceHandler!");
            }
        }

    }

    @Value("${yi.schedule.poolSize: #{1}}")
    private int poolSize;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(poolSize);
        scheduler.setErrorHandler(new DefaultErrorHandler());
        return scheduler;
    }

    public class DefaultErrorHandler implements ErrorHandler {

        @Override
        public void handleError(Throwable e) {
            log.info(e.getMessage(), e);
        }

    }


}

