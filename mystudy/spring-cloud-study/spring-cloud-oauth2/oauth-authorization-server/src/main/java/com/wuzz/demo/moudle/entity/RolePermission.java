package com.wuzz.demo.moudle.entity;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/7/10 14:51
 * @since 1.0
 **/
public class RolePermission {

    private int id;

    private int permission_id;

    private int role_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(int permission_id) {
        this.permission_id = permission_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }
}
