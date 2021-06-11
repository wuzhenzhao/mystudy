package com.wuzz.demo.model;


import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.time.LocalDateTime;
import java.util.Date;

public class FastJsonTestModel {

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date field0 = new Date();

    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue, name = "Id")
    private String field1 = null;

    @JSONField(format = "MM-dd HH:mm:ss")
    private LocalDateTime field2 = LocalDateTime.now();

    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue, name = "pppp")
    private Integer field3 = null;


    public Date getField0() {
        return field0;
    }

    public void setField0(Date field0) {
        this.field0 = field0;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public LocalDateTime getField2() {
        return field2;
    }

    public void setField2(LocalDateTime field2) {
        this.field2 = field2;
    }

    public Integer getField3() {
        return field3;
    }

    public void setField3(Integer field3) {
        this.field3 = field3;
    }

    @Override
    public String toString() {
        return "JacksonTestModel{" +
                "field0=" + field0 +
                ", field1='" + field1 + '\'' +
                ", field2=" + field2 +
                ", field3=" + field3 +
                '}';
    }
}
