package com.fateking.yi.exception;

import org.springframework.http.HttpStatus;

/**
 * @author dingxin
 */
public class IllegalArgumentException extends BaseException {

    public IllegalArgumentException() {
    }

    public IllegalArgumentException(HttpStatus httpStatus) {
        super(httpStatus);
    }

    public IllegalArgumentException(Integer code) {
        super(code);
    }

    public IllegalArgumentException(Integer code, Object[] args) {
        super(code, args);
    }

    public IllegalArgumentException(Integer code, HttpStatus httpStatus) {
        super(code, httpStatus);
    }

    public IllegalArgumentException(Integer code, String message) {
        super(code, message);
    }

    public IllegalArgumentException(Integer code, String message, HttpStatus httpStatus) {
        super(code, message, httpStatus);
    }

    public IllegalArgumentException(String message) {
        super(message);
    }

    public IllegalArgumentException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public IllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalArgumentException(String message, HttpStatus httpStatus, Throwable cause) {
        super(message, httpStatus, cause);
    }

    public IllegalArgumentException(Integer code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public IllegalArgumentException(Integer code, String message, HttpStatus httpStatus, Throwable cause) {
        super(code, message, httpStatus, cause);
    }

    public IllegalArgumentException(Throwable cause) {
        super(cause);
    }

    public IllegalArgumentException(Throwable cause, HttpStatus httpStatus) {
        super(cause, httpStatus);
    }

    public IllegalArgumentException(Integer code, Throwable cause) {
        super(code, cause);
    }

    public IllegalArgumentException(Integer code, HttpStatus httpStatus, Throwable cause) {
        super(code, httpStatus, cause);
    }
}
