package com.wuzz.demo.strategy;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/21
 * Time: 16:19
 * Description 描述:
 */
public class AliPay implements  Payment {

    public String pay() {
        System.out.println("欢迎使用支付宝");
        System.out.println("查询账户余额，开始扣款");
        return "支付成功";
    }
}
