package com.fateking.yi.utils;

import com.fateking.yi.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.http.HttpStatus;

@Slf4j
public class Assert {

    private static Class<? extends BaseException> defaultThrowExceptionClass = BaseException.class;

    private static final Object lock = new Object();

    /**
     * 设定缺省抛出异常
     *
     * @param defaultThrowExceptionClass
     */
    public static void resetDefaultThrowExceptionClass(Class<? extends BaseException> defaultThrowExceptionClass) {
        if (defaultThrowExceptionClass == null) {
            return;
        }
        synchronized (lock) {
            Assert.defaultThrowExceptionClass = defaultThrowExceptionClass;
        }
    }

    private static final BaseException newException(Class<? extends BaseException> clazz) {
        try {
            BaseException exception = clazz.newInstance();
            return exception;
        } catch (InstantiationException ex) {
            log.error(ex.getMessage(), ex);
        } catch (IllegalAccessException ex) {
            log.error(ex.getMessage(), ex);
        }
        return new BaseException(5000, "Illegal exception without no args constructor!");
    }

    public static final void notNull(Object target) {
        notNull(target, defaultThrowExceptionClass);
    }

    public static final void notNull(Object target, HttpStatus httpStatus) {
        notNull(target, httpStatus, defaultThrowExceptionClass);
    }

    public static final void notNull(Object target, Integer code) {
        notNull(target, code, defaultThrowExceptionClass);
    }

    public static final void notNull(Object target, Integer code, Object[] args) {
        notNull(target, code, args, defaultThrowExceptionClass);
    }

    public static final void notNull(Object target, Integer code, HttpStatus httpStatus) {
        notNull(target, code, httpStatus, defaultThrowExceptionClass);
    }

    public static final void notNull(Object target, String message) {
        notNull(target, message, defaultThrowExceptionClass);
    }

    public static final void notNull(Object target, String message, HttpStatus httpStatus) {
        notNull(target, message, httpStatus, defaultThrowExceptionClass);
    }

    public static final void notNull(Object target, Integer code, String message) {
        notNull(target, code, message, defaultThrowExceptionClass);
    }

    public static final void notNull(Object target, Integer code, String message, HttpStatus httpStatus) {
        notNull(target, code, message, httpStatus, defaultThrowExceptionClass);
    }

    public static final void notNull(Object target, Integer code, String message, Throwable cause) {
        notNull(target, code, message, cause, defaultThrowExceptionClass);
    }

    public static final void notNull(Object target, Integer code, String message, HttpStatus httpStatus, Throwable cause) {
        notNull(target, code, message, httpStatus, cause, defaultThrowExceptionClass);
    }

    public static final void notEmpty(Object target) {
        notEmpty(target, defaultThrowExceptionClass);
    }

    public static final void notEmpty(Object target, HttpStatus httpStatus) {
        notEmpty(target, httpStatus, defaultThrowExceptionClass);
    }

    public static final void notEmpty(Object target, Integer code) {
        notEmpty(target, code, defaultThrowExceptionClass);
    }

    public static final void notEmpty(Object target, Integer code, Object[] args) {
        notEmpty(target, code, args, defaultThrowExceptionClass);
    }

    public static final void notEmpty(Object target, Integer code, HttpStatus httpStatus) {
        notEmpty(target, code, httpStatus, defaultThrowExceptionClass);
    }

    public static final void notEmpty(Object target, String message) {
        notEmpty(target, message, defaultThrowExceptionClass);
    }

    public static final void notEmpty(Object target, String message, HttpStatus httpStatus) {
        notEmpty(target, message, httpStatus, defaultThrowExceptionClass);
    }

    public static final void notEmpty(Object target, Integer code, String message) {
        notEmpty(target, code, message, defaultThrowExceptionClass);
    }

    public static final void notEmpty(Object target, Integer code, String message, HttpStatus httpStatus) {
        notEmpty(target, code, message, httpStatus, defaultThrowExceptionClass);
    }

    public static final void notEmpty(Object target, Integer code, String message, Throwable cause) {
        notEmpty(target, code, message, cause, defaultThrowExceptionClass);
    }

    public static final void notEmpty(Object target, Integer code, String message, HttpStatus httpStatus, Throwable cause) {
        notEmpty(target, code, message, httpStatus, cause, defaultThrowExceptionClass);
    }

    public static final void notTrue(Boolean target) {
        notTrue(target, defaultThrowExceptionClass);
    }

    public static final void notTrue(Boolean target, HttpStatus httpStatus) {
        notTrue(target, httpStatus, defaultThrowExceptionClass);
    }

    public static final void notTrue(Boolean target, Integer code) {
        notTrue(target, code, defaultThrowExceptionClass);
    }

    public static final void notTrue(Boolean target, Integer code, Object[] args) {
        notTrue(target, code, args, defaultThrowExceptionClass);
    }

    public static final void notTrue(Boolean target, Integer code, HttpStatus httpStatus) {
        notTrue(target, code, httpStatus, defaultThrowExceptionClass);
    }

    public static final void notTrue(Boolean target, String message) {
        notTrue(target, message, defaultThrowExceptionClass);
    }

    public static final void notTrue(Boolean target, String message, HttpStatus httpStatus) {
        notTrue(target, message, httpStatus, defaultThrowExceptionClass);
    }

    public static final void notTrue(Boolean target, Integer code, String message) {
        notTrue(target, code, message, defaultThrowExceptionClass);
    }

    public static final void notTrue(Boolean target, Integer code, String message, HttpStatus httpStatus) {
        notTrue(target, code, message, httpStatus, defaultThrowExceptionClass);
    }

    public static final void notTrue(Boolean target, Integer code, String message, Throwable cause) {
        notTrue(target, code, message, cause, defaultThrowExceptionClass);
    }

    public static final void notTrue(Boolean target, Integer code, String message, HttpStatus httpStatus, Throwable cause) {
        notTrue(target, code, message, httpStatus, cause, defaultThrowExceptionClass);
    }

    public static final void isTrue(Boolean target) {
        isTrue(target, defaultThrowExceptionClass);
    }

    public static final void isTrue(Boolean target, HttpStatus httpStatus) {
        isTrue(target, httpStatus, defaultThrowExceptionClass);
    }

    public static final void isTrue(Boolean target, Integer code) {
        isTrue(target, code, defaultThrowExceptionClass);
    }

    public static final void isTrue(Boolean target, Integer code, Object[] args) {
        isTrue(target, code, args, defaultThrowExceptionClass);
    }

    public static final void isTrue(Boolean target, Integer code, HttpStatus httpStatus) {
        isTrue(target, code, httpStatus, defaultThrowExceptionClass);
    }

    public static final void isTrue(Boolean target, String message) {
        isTrue(target, message, defaultThrowExceptionClass);
    }

    public static final void isTrue(Boolean target, String message, HttpStatus httpStatus) {
        isTrue(target, message, httpStatus, defaultThrowExceptionClass);
    }

    public static final void isTrue(Boolean target, Integer code, String message) {
        isTrue(target, code, message, defaultThrowExceptionClass);
    }

    public static final void isTrue(Boolean target, Integer code, String message, HttpStatus httpStatus) {
        isTrue(target, code, message, httpStatus, defaultThrowExceptionClass);
    }

    public static final void isTrue(Boolean target, Integer code, String message, Throwable cause) {
        isTrue(target, code, message, cause, defaultThrowExceptionClass);
    }

    public static final void isTrue(Boolean target, Integer code, String message, HttpStatus httpStatus, Throwable cause) {
        isTrue(target, code, message, httpStatus, cause, defaultThrowExceptionClass);
    }

    public static final void notNull(Object target, Class<? extends BaseException> clazz) {
        if (target == null) {
            throw newException(clazz);
        }
    }

    public static final void notNull(Object target, HttpStatus httpStatus, Class<? extends BaseException> clazz) {
        if (target == null) {
            throw newException(clazz).build(httpStatus);
        }
    }

    public static final void notNull(Object target, Integer code, Class<? extends BaseException> clazz) {
        if (target == null) {
            throw newException(clazz).build(code);
        }
    }

    public static final void notNull(Object target, Integer code, Object[] args, Class<? extends BaseException> clazz) {
        if (target == null) {
            throw newException(clazz).build(code).build(args);
        }
    }

    public static final void notNull(Object target, Integer code, HttpStatus httpStatus, Class<? extends BaseException> clazz) {
        if (target == null) {
            throw newException(clazz).build(code).build(httpStatus);
        }
    }

    public static final void notNull(Object target, String message, Class<? extends BaseException> clazz) {
        if (target == null) {
            throw newException(clazz).build(message);
        }
    }

    public static final void notNull(Object target, String message, HttpStatus httpStatus, Class<? extends BaseException> clazz) {
        if (target == null) {
            throw newException(clazz).build(message).build(httpStatus);
        }
    }

    public static final void notNull(Object target, Integer code, String message, Class<? extends BaseException> clazz) {
        if (target == null) {
            throw newException(clazz).build(code).build(message);
        }
    }

    public static final void notNull(Object target, Integer code, String message, HttpStatus httpStatus, Class<? extends BaseException> clazz) {
        if (target == null) {
            throw newException(clazz).build(code).build(message).build(httpStatus);
        }
    }

    public static final void notNull(Object target, Integer code, String message, Throwable cause, Class<? extends BaseException> clazz) {
        if (target == null) {
            throw newException(clazz).build(code).build(message).build(cause);
        }
    }

    public static final void notNull(Object target, Integer code, String message, HttpStatus httpStatus, Throwable cause, Class<? extends BaseException> clazz) {
        if (target == null) {
            throw newException(clazz).build(code).build(message).build(httpStatus).build(cause);
        }
    }

    public static final void notEmpty(Object target, Class<? extends BaseException> clazz) {
        if (org.springframework.util.ObjectUtils.isEmpty(target)) {
            throw newException(clazz);
        }
    }

    public static final void notEmpty(Object target, HttpStatus httpStatus, Class<? extends BaseException> clazz) {
        if (org.springframework.util.ObjectUtils.isEmpty(target)) {
            throw newException(clazz).build(httpStatus);
        }
    }

    public static final void notEmpty(Object target, Integer code, Class<? extends BaseException> clazz) {
        if (org.springframework.util.ObjectUtils.isEmpty(target)) {
            throw newException(clazz).build(code);
        }
    }

    public static final void notEmpty(Object target, Integer code, Object[] args, Class<? extends BaseException> clazz) {
        if (org.springframework.util.ObjectUtils.isEmpty(target)) {
            throw newException(clazz).build(code).build(args);
        }
    }

    public static final void notEmpty(Object target, Integer code, HttpStatus httpStatus, Class<? extends BaseException> clazz) {
        if (org.springframework.util.ObjectUtils.isEmpty(target)) {
            throw newException(clazz).build(code).build(httpStatus);
        }
    }

    public static final void notEmpty(Object target, String message, Class<? extends BaseException> clazz) {
        if (org.springframework.util.ObjectUtils.isEmpty(target)) {
            throw newException(clazz).build(message);
        }
    }

    public static final void notEmpty(Object target, String message, HttpStatus httpStatus, Class<? extends BaseException> clazz) {
        if (org.springframework.util.ObjectUtils.isEmpty(target)) {
            throw newException(clazz).build(message).build(httpStatus);
        }
    }

    public static final void notEmpty(Object target, Integer code, String message, Class<? extends BaseException> clazz) {
        if (org.springframework.util.ObjectUtils.isEmpty(target)) {
            throw newException(clazz).build(code).build(message);
        }
    }

    public static final void notEmpty(Object target, Integer code, String message, HttpStatus httpStatus, Class<? extends BaseException> clazz) {
        if (org.springframework.util.ObjectUtils.isEmpty(target)) {
            throw newException(clazz).build(code).build(message).build(httpStatus);
        }
    }

    public static final void notEmpty(Object target, Integer code, String message, Throwable cause, Class<? extends BaseException> clazz) {
        if (org.springframework.util.ObjectUtils.isEmpty(target)) {
            throw newException(clazz).build(code).build(message).build(cause);
        }
    }

    public static final void notEmpty(Object target, Integer code, String message, HttpStatus httpStatus, Throwable cause, Class<? extends BaseException> clazz) {
        if (org.springframework.util.ObjectUtils.isEmpty(target)) {
            throw newException(clazz).build(code).build(message).build(httpStatus).build(cause);
        }
    }

    public static final void notTrue(Boolean target, Class<? extends BaseException> clazz) {
        if (BooleanUtils.isTrue(target)) {
            throw newException(clazz);
        }
    }

    public static final void notTrue(Boolean target, HttpStatus httpStatus, Class<? extends BaseException> clazz) {
        if (BooleanUtils.isTrue(target)) {
            throw newException(clazz).build(httpStatus);
        }
    }

    public static final void notTrue(Boolean target, Integer code, Class<? extends BaseException> clazz) {
        if (BooleanUtils.isTrue(target)) {
            throw newException(clazz).build(code);
        }
    }

    public static final void notTrue(Boolean target, Integer code, Object[] args, Class<? extends BaseException> clazz) {
        if (BooleanUtils.isTrue(target)) {
            throw newException(clazz).build(code).build(args);
        }
    }

    public static final void notTrue(Boolean target, Integer code, HttpStatus httpStatus, Class<? extends BaseException> clazz) {
        if (BooleanUtils.isTrue(target)) {
            throw newException(clazz).build(code).build(httpStatus);
        }
    }

    public static final void notTrue(Boolean target, String message, Class<? extends BaseException> clazz) {
        if (BooleanUtils.isTrue(target)) {
            throw newException(clazz).build(message);
        }
    }

    public static final void notTrue(Boolean target, String message, HttpStatus httpStatus, Class<? extends BaseException> clazz) {
        if (BooleanUtils.isTrue(target)) {
            throw newException(clazz).build(message).build(httpStatus);
        }
    }

    public static final void notTrue(Boolean target, Integer code, String message, Class<? extends BaseException> clazz) {
        if (BooleanUtils.isTrue(target)) {
            throw newException(clazz).build(code).build(message);
        }
    }

    public static final void notTrue(Boolean target, Integer code, String message, HttpStatus httpStatus, Class<? extends BaseException> clazz) {
        if (BooleanUtils.isTrue(target)) {
            throw newException(clazz).build(code).build(message).build(httpStatus);
        }
    }

    public static final void notTrue(Boolean target, Integer code, String message, Throwable cause, Class<? extends BaseException> clazz) {
        if (BooleanUtils.isTrue(target)) {
            throw newException(clazz).build(code).build(message).build(cause);
        }
    }

    public static final void notTrue(Boolean target, Integer code, String message, HttpStatus httpStatus, Throwable cause, Class<? extends BaseException> clazz) {
        if (BooleanUtils.isTrue(target)) {
            throw newException(clazz).build(code).build(message).build(httpStatus).build(cause);
        }
    }

    public static final void isTrue(Boolean target, Class<? extends BaseException> clazz) {
        if (BooleanUtils.isFalse(target)) {
            throw newException(clazz);
        }
    }

    public static final void isTrue(Boolean target, HttpStatus httpStatus, Class<? extends BaseException> clazz) {
        if (BooleanUtils.isFalse(target)) {
            throw newException(clazz).build(httpStatus);
        }
    }

    public static final void isTrue(Boolean target, Integer code, Class<? extends BaseException> clazz) {
        if (BooleanUtils.isFalse(target)) {
            throw newException(clazz).build(code);
        }
    }

    public static final void isTrue(Boolean target, Integer code, Object[] args, Class<? extends BaseException> clazz) {
        if (BooleanUtils.isFalse(target)) {
            throw newException(clazz).build(code).build(args);
        }
    }

    public static final void isTrue(Boolean target, Integer code, HttpStatus httpStatus, Class<? extends BaseException> clazz) {
        if (BooleanUtils.isFalse(target)) {
            throw newException(clazz).build(code).build(httpStatus);
        }
    }

    public static final void isTrue(Boolean target, String message, Class<? extends BaseException> clazz) {
        if (BooleanUtils.isFalse(target)) {
            throw newException(clazz).build(message);
        }
    }

    public static final void isTrue(Boolean target, String message, HttpStatus httpStatus, Class<? extends BaseException> clazz) {
        if (BooleanUtils.isFalse(target)) {
            throw newException(clazz).build(message).build(httpStatus);
        }
    }

    public static final void isTrue(Boolean target, Integer code, String message, Class<? extends BaseException> clazz) {
        if (BooleanUtils.isFalse(target)) {
            throw newException(clazz).build(code).build(message);
        }
    }

    public static final void isTrue(Boolean target, Integer code, String message, HttpStatus httpStatus, Class<? extends BaseException> clazz) {
        if (BooleanUtils.isFalse(target)) {
            throw newException(clazz).build(code).build(message).build(httpStatus);
        }
    }

    public static final void isTrue(Boolean target, Integer code, String message, Throwable cause, Class<? extends BaseException> clazz) {
        if (BooleanUtils.isFalse(target)) {
            throw newException(clazz).build(code).build(message).build(cause);
        }
    }

    public static final void isTrue(Boolean target, Integer code, String message, HttpStatus httpStatus, Throwable cause, Class<? extends BaseException> clazz) {
        if (BooleanUtils.isFalse(target)) {
            throw newException(clazz).build(code).build(message).build(httpStatus).build(cause);
        }
    }


}
