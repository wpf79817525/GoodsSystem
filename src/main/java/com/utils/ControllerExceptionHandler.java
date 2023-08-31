package com.utils;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result doOtherException(Exception e){
        e.printStackTrace();
        Result r = new Result();
        r.setMessage("服务器出现异常...请重试");
        return r;
    }
}
