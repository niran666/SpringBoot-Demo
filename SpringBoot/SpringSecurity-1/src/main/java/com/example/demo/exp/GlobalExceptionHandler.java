package com.example.demo.exp;


import com.example.demo.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author jimo
 * @func 全局异常处理
 * @date 2018/8/24 22:44
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        return new Result(false, e.getMessage());
    }
}
