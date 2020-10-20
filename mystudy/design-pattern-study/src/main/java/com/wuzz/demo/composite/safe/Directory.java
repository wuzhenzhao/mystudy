package com.wuzz.demo.composite.safe;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/3/24 19:31
 * @since 1.0
 **/
public abstract class Directory {

    protected String name;

    public Directory(String name) {
        this.name = name;
    }

    public abstract void show();

}
