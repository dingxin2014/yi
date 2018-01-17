package com.fateking.yi.config;

import com.fateking.yi.autoconfigurer.EnableBaseConfig;
import com.fateking.yi.exception.BaseException;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author dingxin
 */
@Configuration
public class BaseConfiguration extends AbstractBaseConfig implements ImportAware {

    private AnnotationAttributes annotationAttributes;
    private String basename;
    private Class<? extends BaseException> defaultThrowExceptionClass;

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        this.annotationAttributes = AnnotationAttributes
                .fromMap(importMetadata.getAnnotationAttributes(EnableBaseConfig.class.getName(), false));
        if (this.annotationAttributes == null) {
            throw new IllegalArgumentException(
                    "@EnableBaseConfig is not present on importing class " + importMetadata.getClassName());
        }
        this.basename =
                annotationAttributes.getString("basename");
        this.defaultThrowExceptionClass =
                annotationAttributes.getClass("defaultThrowExceptionClass");
    }

    @Override
    public String basename() {
        return basename;
    }

    @Override
    public Class<? extends BaseException> defaultThrowExceptionClass() {
        return defaultThrowExceptionClass;
    }


}
