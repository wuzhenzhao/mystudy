package com.wuzz.demo.factory.factory.method;

import com.wuzz.demo.factory.simple.factory.MengNiuMilk;
import com.wuzz.demo.factory.simple.factory.Milk;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/18
 * Time: 18:28
 * Description:
 */
public class MengNiuFactory implements Factory {
    @Override
    public Milk getMilk() {
        return new MengNiuMilk();
    }
}
