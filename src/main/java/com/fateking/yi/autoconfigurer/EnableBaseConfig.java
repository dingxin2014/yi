package com.fateking.yi.autoconfigurer;

import com.fateking.yi.config.BaseConfiguration;
import com.fateking.yi.exception.BaseException;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(BaseConfiguration.class)
public @interface EnableBaseConfig {


    /**
     * default thrown exception class
     *
     * @return
     */
    Class<? extends BaseException> defaultThrowExceptionClass();

    /**
     * message source properties file path
     *
     * @return
     */
    String basename() default "i18n/messages";

}
