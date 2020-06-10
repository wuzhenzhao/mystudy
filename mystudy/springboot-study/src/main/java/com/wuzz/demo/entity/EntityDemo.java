package com.wuzz.demo.entity;

import com.wuzz.demo.core.validate.DefaultValidate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.io.Serializable;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/11
 * Time: 15:16
 * Description 描述:
 */
@ApiModel(value = "入参类",description = "实体入参")
public class EntityDemo extends DefaultValidate implements Serializable {

    @ApiModelProperty(value = "主键",example = "123456")
    private String id;

    public String name;

    private Double age;

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

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }
}

