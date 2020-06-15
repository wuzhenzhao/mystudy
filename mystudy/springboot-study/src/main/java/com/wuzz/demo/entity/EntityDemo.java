package com.wuzz.demo.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.wuzz.demo.core.Result;
import com.wuzz.demo.core.validate.DefaultValidate;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/11
 * Time: 15:16
 * Description 描述:
 */
public class EntityDemo extends DefaultValidate implements Serializable {


    private String id;

    @NotNull(message = "名称不能为空")
    public String name;


    private Double age;

    @JsonView(Result.BaseJsonView.class)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonView(Result.SimpleJsonView.class)
    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }
}

