package com.wuzz.demo.strategy;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/21
 * Time: 16:41
 * Description 描述:
 */
public enum PayType {

    ALI_PAY(new AliPay()),
    UNION_PAY(new UnionPay()),
    JD_PAY(new JDPay());

    private Payment payment;
    PayType(Payment payment){
        this.payment = payment;
    }

    public Payment get(){ return  this.payment;}
}
