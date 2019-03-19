package com.wuzz.demo.factory_pattern.factory_method;

import com.wuzz.demo.factory_pattern.simple_factory.MengNiuMilk;
import com.wuzz.demo.factory_pattern.simple_factory.Milk;

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
