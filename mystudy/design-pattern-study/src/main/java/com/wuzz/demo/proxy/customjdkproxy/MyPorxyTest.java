package com.wuzz.demo.proxy.customjdkproxy;

import com.wuzz.demo.proxy.staticproxy.Person;
import com.wuzz.demo.proxy.staticproxy.Programmer;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/21
 * Time: 14:53
 * Description 描述:
 */
public class MyPorxyTest {

    public static void main(String[] args) {

        try {
            Person obj = (Person)new MyMeiPo().getInstance(new Programmer());
            System.out.println(obj.getClass());
            obj.findLove();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
