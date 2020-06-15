package com.wuzz.demo.model;

import lombok.Data;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/6/12 19:39
 * @since 1.0
 **/
@Data
public class Permissions {

    private String id;

    private String permissionsName;

    public Permissions(String id, String permissionsName) {
        this.id = id;
        this.permissionsName = permissionsName;
    }
}
