package com.wuzz.demo.factory_pattern.abstract_factory;

import com.wuzz.demo.factory.factory.method.MengNiuFactory;
import com.wuzz.demo.factory.factory.method.TeLunSuFactory;
import com.wuzz.demo.factory.simple.factory.Milk;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/20
 * Time: 12:31
 * Description:
 */
public class MilkFactory  extends com.wuzz.demo.factory_pattern.abstract_factory.AbstractFactory {
    @Override
    public Milk getMengNiuMilk() {
        return new MengNiuFactory().getMilk();
    }

    @Override
    public Milk getTeLunSuMilk() {
        return new TeLunSuFactory().getMilk();
    }
}
