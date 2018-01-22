package com.fateking.yi.support;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author dingxin
 */
@Getter
public class Result<T> implements Serializable {

    private static final String DEFAULT_SUCCESS_MSG = "ok";
    private static final String DEFAULT_FAIL_MSG = "fail";

    private int status = 0;
    private Integer code;
    private String message;
    private T data;

    private Result(int status, Integer code, String message, T data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> ok() {
        return ok(null);
    }

    public static <T> Result<T> ok(T data) {
        return new Result(1, null, DEFAULT_SUCCESS_MSG, data);
    }

    public static <T> Result<T> fail() {
        return fail(null);
    }

    public static <T> Result<T> fail(T data) {
        return new Result(0, null, DEFAULT_FAIL_MSG, data);
    }

    public Result code(Integer code) {
        this.code = code;
        return this;
    }

    public Result msg(String message) {
        this.message = message;
        return this;
    }

    public Result data(T data) {
        this.data = data;
        return this;
    }

}
