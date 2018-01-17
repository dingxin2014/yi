package com.fateking.yi.exception.handler;

import com.fateking.yi.exception.BaseException;
import com.fateking.yi.support.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author dingxin
 */
@Slf4j
public abstract class AbstractExceptionAdviceHandler {


    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e, HttpServletResponse response) {
        log.info("Handle exception >>> " + e.getMessage());

        Integer code = null;
        String msg = e.getMessage();
        if (e instanceof BaseException) {
            BaseException ex = (BaseException) e;
            code = ex.getCode();
            Object[] args = ex.getArgs();
            if (StringUtils.isEmpty(e.getMessage())) {
                msg = messageSource.getMessage(String.valueOf(code), args, LocaleContextHolder.getLocale());
            }
            response.setStatus(ex.getHttpStatus().value());
        }
        return Result.fail().msg(msg);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        StringBuilder stringBuilder = new StringBuilder();
        fieldErrors.stream().forEach(fieldError ->
                stringBuilder.append(fieldError.getDefaultMessage()).append("\n"));
        return Result.fail().msg(stringBuilder.toString());
    }
}
