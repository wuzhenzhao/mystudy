package com.wuzz.demo.resteasy;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/8/24 15:52
 * @since 1.0
 **/
public class Result implements Serializable {

    private LocalDateTime time;

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
