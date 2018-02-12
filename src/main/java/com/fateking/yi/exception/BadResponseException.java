package com.fateking.yi.exception;

import org.springframework.http.HttpStatus;

public class BadResponseException extends BaseException {

    public BadResponseException() {
    }

    public BadResponseException(HttpStatus httpStatus) {
        super(httpStatus);
    }

    public BadResponseException(Integer code) {
        super(code);
    }

    public BadResponseException(Integer code, Object[] args) {
        super(code, args);
    }

    public BadResponseException(Integer code, HttpStatus httpStatus) {
        super(code, httpStatus);
    }

    public BadResponseException(Integer code, String message) {
        super(code, message);
    }

    public BadResponseException(Integer code, String message, HttpStatus httpStatus) {
        super(code, message, httpStatus);
    }

    public BadResponseException(String message) {
        super(message);
    }

    public BadResponseException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public BadResponseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadResponseException(String message, HttpStatus httpStatus, Throwable cause) {
        super(message, httpStatus, cause);
    }

    public BadResponseException(Integer code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public BadResponseException(Integer code, String message, HttpStatus httpStatus, Throwable cause) {
        super(code, message, httpStatus, cause);
    }

    public BadResponseException(Throwable cause) {
        super(cause);
    }

    public BadResponseException(Throwable cause, HttpStatus httpStatus) {
        super(cause, httpStatus);
    }

    public BadResponseException(Integer code, Throwable cause) {
        super(code, cause);
    }

    public BadResponseException(Integer code, HttpStatus httpStatus, Throwable cause) {
        super(code, httpStatus, cause);
    }
}
