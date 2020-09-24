package com.wuzz.demo.custom.hystrix;

import java.lang.annotation.*;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WuzzHystrixCommand {

    /**
     * 默认超时时间
     *
     * @return
     */
    int timeout() default 1000;

    /**
     * 回退方法
     *
     * @return
     */
    String fallback() default "";

}
