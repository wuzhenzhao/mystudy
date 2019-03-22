package com.wuzz.demo.proxy.customjdkproxy;

import java.lang.reflect.Method;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/21
 * Time: 14:45
 * Description 描述:
 */
public interface MyInvocationHandler {

    /* @Author Wuzhenzhao
    * @Param [proxy, method, args]
    * @return java.lang.Object
    * @Date 14:46 2019/3/21
    * @Description
    **/
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
