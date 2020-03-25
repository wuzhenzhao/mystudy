package com.wuzz.demo.composite.safe;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/3/24 19:32
 * @since 1.0
 **/
public class File extends Directory {

    public File(String name) {
        super(name);
    }

    @Override
    public void show() {
        System.out.println(this.name);
    }

}
