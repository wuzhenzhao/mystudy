package com.wuzz.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/6/10 11:14
 * @since 1.0
 **/
@ApiModel(description= "request")
public class RestRequest implements Serializable {

    //这种一般用在post创建的时候，使用@RequestBody这样的场景，
    //请求参数无法使用@ApiImplicitParam注解进行描述的时候）
    //@ApiModelProperty：用在属性上，描述响应类的属性
    @ApiModelProperty(value = "是否成功")
    private boolean enabled=true;

    @ApiModelProperty(value = "返回对象")
    private Object data;
    //实体类中，Integer类型的属性加@ApiModelProperty时，必须要给example参数赋值，且值必须为数字类型。
    @ApiModelProperty(value = "错误编号",example = "123456")
    private String username;

    @ApiModelProperty(value = "错误信息")
    private String age;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
