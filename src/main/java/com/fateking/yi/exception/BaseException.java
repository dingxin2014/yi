package com.fateking.yi.exception;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.http.HttpStatus;

/**
 * @author dingxin
 */
public class BaseException extends RuntimeException {

    private static final Integer DEFAULT_CODE = 500;
    private static final HttpStatus DEFAULT_STATUS = HttpStatus.OK;

    private Integer code = DEFAULT_CODE;
    private HttpStatus httpStatus = DEFAULT_STATUS;
    private Object[] args;

    public BaseException() {
    }

    public BaseException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public BaseException(Integer code) {
        this.code = code;
    }

    public BaseException(Integer code, Object[] args) {
        this.code = code;
        this.args = args;
    }

    public BaseException(Integer code, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(Integer code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, HttpStatus httpStatus) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String message, HttpStatus httpStatus, Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }

    public BaseException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BaseException(Integer code, String message, HttpStatus httpStatus, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(Throwable cause, HttpStatus httpStatus) {
        super(cause);
        this.httpStatus = httpStatus;
    }

    public BaseException(Integer code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public BaseException(Integer code, HttpStatus httpStatus, Throwable cause) {
        super(cause);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public final Integer getCode() {
        return code;
    }

    public final HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public final Object[] getArgs() {
        return args;
    }

    public final BaseException build(Integer code) {
        this.code = code;
        return this;
    }

    public final BaseException build(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public final BaseException build(Object[] args) {
        this.args = args;
        return this;
    }

    public final BaseException build(String message) {
        try {
            FieldUtils.writeField(this, "detailMessage", message, true);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return this;
    }

    public final BaseException build(Throwable cause) {
        try {
            FieldUtils.writeField(this, "cause", cause, true);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return this;
    }


}
