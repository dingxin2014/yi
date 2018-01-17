package com.fateking.yi.support;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;

/**
 * @author dingxin
 */
public class HuobiMessageConverter extends AbstractHttpMessageConverter {


    @Override
    protected boolean supports(Class clazz) {
        return false;
    }

    @Override
    protected Object readInternal(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
//        JavaType javaType = super.getJavaType(clazz, null);
//        return readJavaType(javaType, inputMessage);
        return null;
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        System.err.println(">" + o);
    }
}
