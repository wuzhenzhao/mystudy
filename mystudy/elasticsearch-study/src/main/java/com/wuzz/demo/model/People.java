package com.wuzz.demo.model;

import lombok.Data;

/**
 * @description: 实体类
 * @author: wuzhenzhao
 * @time 2020/5/15 20:11
 * @since 1.0
 **/
@Data
public class People {

    private Double lat;        //纬度
    private Double lon;        //经度
    private String wxNo;    //微信号
    private String nickName;//昵称
    private String sex;        //性别

    public People(String wxNo, String nickName, String sex, Double lat, Double lon) {
        this.wxNo = wxNo;
        this.nickName = nickName;
        this.sex = sex;
        this.lat = lat;
        this.lon = lon;
    }
}
