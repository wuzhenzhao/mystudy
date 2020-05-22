package com.wuzz.demo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TestTypeEnum {
    PRIMARY(1, "小学"),
    SECONDORY(2, "中学"),
    HIGH(3, "高中");

    TestTypeEnum(int code, String descp) {
        this.code = code;
        this.descp = descp;
    }

    @EnumValue//标记数据库存的值是code
    private final int code;

    private String descp;

    public int getCode() {
        return code;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }


    @Override
    @JsonValue// 这个就是返回的序列化以后的值,不一定非要toString，或者用@JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public String toString() {
        return "TestTypeEnum{" +
                "code=" + code +
                ", descp='" + descp + '\'' +
                '}';
    }
}
