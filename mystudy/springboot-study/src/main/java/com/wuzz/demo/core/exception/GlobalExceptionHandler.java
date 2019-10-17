package com.wuzz.demo.core.exception;

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
 *
 * ## 异常处理
 *
 * ### 传统的Servlet web.xml 错误页面
 * * 优点：统一处理，业界标准
 * * 不足：灵活度不够，只能定义 web.xml文件里面
 * <error-page> 处理逻辑：
 *
 *  * 处理状态码 <error-code>
 *  * 处理异常类型 <exception-type>
 *  * 处理服务：<location>

 * ### Spring Web MVC 异常处理
 *  * @ExceptionHandler
 *     * 优点：易于理解，尤其是全局异常处理
 *     * 不足：很难完全掌握所有的异常类型
 *  * @RestControllerAdvice = @ControllerAdvice + @ResponseBody
 *  * @ControllerAdvice 专门拦截（AOP） @Controller
 *
 * ### Spring Boot 错误处理页面
 *  * 实现 ErrorPageRegistrar
 *     * 状态码：比较通用，不需要理解Spring WebMVC 异常体系
 *     * 不足：页面处理的路径必须固定
 *  * 注册 ErrorPage 对象
 *  * 实现 ErrorPage 对象中的Path 路径Web服务
 */
@ControllerAdvice(basePackages = "com.wuzz.demo")
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

