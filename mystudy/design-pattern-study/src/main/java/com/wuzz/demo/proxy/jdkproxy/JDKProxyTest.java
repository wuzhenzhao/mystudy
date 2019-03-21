package com.wuzz.demo.proxy.jdkproxy;

import com.wuzz.demo.proxy.staticproxy.Person;
import com.wuzz.demo.proxy.staticproxy.Programmer;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/21
 * Time: 14:42
 * Description 描述:
 */
public class JDKProxyTest {

    public static void main(String[] args) {

        try {
            Person obj = (Person)new JDKMeipo().getInstance(new Programmer());
            obj.findLove();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
