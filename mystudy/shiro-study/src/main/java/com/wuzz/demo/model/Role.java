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
public class Role {

    private String id;

    private String roleName;
    /**
     * 角色对应权限集合
     */
    private Set<Permissions> permissions;

    public Role(String id, String roleName, Set<Permissions> permissions) {
        this.id = id;
        this.roleName = roleName;
        this.permissions = permissions;
    }
}
