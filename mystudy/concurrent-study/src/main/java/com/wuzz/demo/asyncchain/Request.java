package com.wuzz.demo.asyncchain;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/4/26 13:50
 * @since 1.0
 **/
public class Request {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                '}';
    }
}
