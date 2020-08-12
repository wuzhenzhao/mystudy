package com.wuzz.demo.entity;


import lombok.Data;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/8/10 19:59
 * @since 1.0
 **/
@Data
public class User {

    private long userId;
    private String name;
    private int age;

    public User() {
    }

    public User(long userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
    }
}
