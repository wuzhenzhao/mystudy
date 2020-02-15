package com.wuzz.demo.enums;

/**
 * @description:告警类型
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/2/7 23:50
 * @since 1.0
 **/
public enum DemoEnum implements BaseEnum {

    //value 是存到数据库里面得字段
    ONE("1", "枚举类型1"),
    TWO("2", "枚举类型2");

    private String value;

    private String message;

    DemoEnum(String value, String message) {
        this.value = value;
        this.message = message;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
