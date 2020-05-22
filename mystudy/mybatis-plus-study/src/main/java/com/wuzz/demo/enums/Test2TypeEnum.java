package com.wuzz.demo.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Test2TypeEnum implements IEnum<Integer> {
    PRIMARY(1, "小学2"),
    SECONDORY(2, "中学2"),
    HIGH(3, "高中2");

    Test2TypeEnum(int code, String descp) {
        this.code = code;
        this.descp = descp;
    }

    private int code;

    private String descp;

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    @Override
    public Integer getValue() {
        return this.code;
    }

    @Override
    @JsonValue // 这个就是返回的序列化以后的值
    public String toString() {
        return "Test2TypeEnum{" +
                "code=" + code +
                ", descp='" + descp + '\'' +
                '}';
    }
}
