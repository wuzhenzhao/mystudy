package com.wuzz.demo.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

public class JacksonTestModel {

    @JsonProperty(value = "json_field0", index = 1)
    private Date field0 = new Date();

    @JsonProperty(value = "json_field1", index = 3)
    private String field1 = null;

    @JsonProperty(value = "json_field2", index = 2)
//    @JsonFormat(pattern = "MM-dd HH:mm:ss")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime field2 = LocalDateTime.now();

    @JsonProperty(value = "json_field3", index = 4)
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
