package com.wuzz.demo.composite.transparent;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/3/24 19:27
 * @since 1.0
 **/
public abstract class CourseComponent {

    public void addChild(CourseComponent catalogComponent) {
        throw new UnsupportedOperationException("不支持添加操作");
    }

    public void removeChild(CourseComponent catalogComponent) {
        throw new UnsupportedOperationException("不支持删除操作");
    }


    public String getName(CourseComponent catalogComponent) {
        throw new UnsupportedOperationException("不支持获取名称操作");
    }


    public double getPrice(CourseComponent catalogComponent) {
        throw new UnsupportedOperationException("不支持获取价格操作");
    }


    public void print() {
        throw new UnsupportedOperationException("不支持打印操作");
    }

}