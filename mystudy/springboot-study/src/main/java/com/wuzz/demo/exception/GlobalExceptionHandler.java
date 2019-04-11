package com.wuzz.demo.exception;

import com.wuzz.demo.core.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/11
 * Time: 14:39
 * Description 描述:
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(GlobalExceptionHandler.class);



    @ExceptionHandler(ValidateException.class)
    @ResponseBody
    public Result handleValidateExp(ValidateException e){
        LOGGER.fatal("数据校验错误:",e.getErrs().get(0), e);
        Result result = new Result(false,"003", e.getErrs().get(0));

        return result;
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleExp(Exception e){
        LOGGER.fatal("系统异常:" ,e.getMessage(), e );
        Result result = new Result(false,"001","系统异常");

        return result;
    }


    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result handleExp(BusinessException e){
        LOGGER.fatal(e.getCommonErrorEnum().getMessage());
        Result result = new Result(false,e.getCommonErrorEnum().getCode(),e.getCommonErrorEnum().getMessage());

        return result;
    }



}

