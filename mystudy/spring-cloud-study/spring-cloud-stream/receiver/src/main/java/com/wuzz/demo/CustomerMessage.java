package com.wuzz.demo;

import java.io.Serializable;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/4/23 10:47
 * @since 1.0
 **/
public class CustomerMessage implements Serializable {

    private String id;

    private String body;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
