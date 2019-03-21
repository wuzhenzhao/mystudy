package com.wuzz.demo.factory_pattern.abstract_factory;

import com.wuzz.demo.factory.simple.factory.Milk;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/20
 * Time: 12:29
 * Description:
 */
public abstract class AbstractFactory {



    public abstract Milk getMengNiuMilk();

    public abstract  Milk getTeLunSuMilk();
}
