package com.wuzz.demo.proxy.staticproxy;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/21
 * Time: 14:35
 * Description 描述:
 */
public class StaticProxyTest {

    public static void main(String[] args) {
        //只能帮程序员找对象，不能帮销售，会计等人
        //因为我所代理的对象很明确
        //静态代理毫无拓展性可言
        MeiPo meipo = new MeiPo(new Programmer());
        meipo.findLove();
    }
}
