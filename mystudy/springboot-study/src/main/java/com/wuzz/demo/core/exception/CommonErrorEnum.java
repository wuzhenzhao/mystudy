package com.wuzz.demo.core.exception;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/11
 * Time: 15:31
 * Description 描述:
 */
public enum CommonErrorEnum {
    PARAMER_ERROR("001","参数错误")
    ;
    private String code ;

    private String message ;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    CommonErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }}
