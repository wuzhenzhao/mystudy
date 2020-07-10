package com.wuzz.demo.moudle.entity;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/7/10 14:32
 * @since 1.0
 **/
public class UserRole {

    private int id;

    private int user_id;

    private int role_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }
}
