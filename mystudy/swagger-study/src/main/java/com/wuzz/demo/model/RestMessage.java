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
@ApiModel(description= "返回响应消息试题描述")
public class RestMessage implements Serializable {

    //这种一般用在post创建的时候，使用@RequestBody这样的场景，
    //请求参数无法使用@ApiImplicitParam注解进行描述的时候）
    //@ApiModelProperty：用在属性上，描述响应类的属性
    @ApiModelProperty(value = "是否成功")
    private boolean success=true;

    @ApiModelProperty(value = "返回对象")
    private Object data;
    //实体类中，Integer类型的属性加@ApiModelProperty时，必须要给example参数赋值，且值必须为数字类型。
    @ApiModelProperty(value = "错误编号",example = "123456")
    private Integer errCode;

    @ApiModelProperty(value = "错误信息")
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
