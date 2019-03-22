package com.wuzz.demo.strategy;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/21
 * Time: 16:41
 * Description 描述:
 */
public class Checkstand {

    //这个参数，完全可以用Payment这个接口来代替
    //完美地解决了switch的过程，不需要在代码逻辑中写switch了
    //更不需要写if    else if
    public String pay(PayType payType){
        return payType.get().pay();
    }
}
