package com.wuzz.demo.entity;

import com.wuzz.demo.core.DefaultValidate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/11
 * Time: 15:16
 * Description 描述:
 */
@ApiModel
public class EntityDemo extends DefaultValidate {
    @ApiModelProperty("主键")
    @NotNull(message = "z主键不能为空")
    private String id;
    @ApiModelProperty("名字")
    private String name;
    @ApiModelProperty("年纪")
    private Integer age;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

