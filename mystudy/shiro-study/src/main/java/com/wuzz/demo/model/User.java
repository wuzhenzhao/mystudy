package com.wuzz.demo.model;

import lombok.Data;

import java.util.Set;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/6/12 19:38
 * @since 1.0
 **/
@Data
public class User {

    private String id;

    private String username;

    private String password;
    /**
     * 用户对应的角色集合
     */
    private Set<Role> roles;

    public User(String id, String username, String password, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
