package com.wuzz.demo.entity;

import com.wuzz.demo.core.DefaultValidate;


import javax.validation.constraints.NotNull;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/11
 * Time: 15:16
 * Description 描述:
 */

public class EntityDemo extends DefaultValidate {

    @NotNull(message = "z主键不能为空")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

