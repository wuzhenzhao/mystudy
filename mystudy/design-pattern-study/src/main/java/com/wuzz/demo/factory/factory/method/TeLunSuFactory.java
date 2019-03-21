package com.wuzz.demo.factory.factory.method;

import com.wuzz.demo.factory.simple.factory.Milk;
import com.wuzz.demo.factory.simple.factory.TeLunSuMilk;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/19
 * Time: 9:55
 * Description:
 */
public class TeLunSuFactory implements Factory {

    public Milk getMilk() {
        return new TeLunSuMilk();
    }
}
