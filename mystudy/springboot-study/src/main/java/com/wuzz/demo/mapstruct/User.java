package com.wuzz.demo.mapstruct;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/11/10 13:54
 * @since 1.0
 **/

public class User {
    private Long id;
    private String username;
    private String password;
    private String phoneNum;
    private String email;
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User(Long id, String username, String password, String phoneNum, String email, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phoneNum = phoneNum;
        this.email = email;
        this.role = role;
    }

    public User() {
    }
}

