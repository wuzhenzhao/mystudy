package com.wuzz.demo.strategy;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/21
 * Time: 16:41
 * Description 描述:
 */
public class PayStrategyTest {

    public static void main(String[] args) {
        //省略把商品添加到购物车，再从购物车下单
        //直接从支付开始
        Checkstand checkstand= new Checkstand();

        //开始支付，选择微信支付、支付宝、银联卡、京东白条、财付通
        //每个渠道它支付的具体算法是不一样的
        //基本算法固定的

        //这个值是在支付的时候才决定用哪个值
        System.out.println(checkstand.pay(PayType.ALI_PAY));
    }
}
