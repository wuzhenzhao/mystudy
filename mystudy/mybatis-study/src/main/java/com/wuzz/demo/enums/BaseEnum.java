package com.wuzz.demo.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/2/8 0:20
 * @since 1.0
 **/
//解决接口返回时只显示枚举Name
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public interface BaseEnum<E extends Enum<E>, T> {

    /**
     * 功能描述: <br>
     * 真正与数据库进行映射的值
     *
     * @Param: []
     * @Return: T
     * @Author: wuzhenzhao
     * @Date: 2020/2/8 15:37
     */
    T getValue();

    /***
     * 功能描述: <br>
     * 枚举描述
     * @Param: []
     * @Return: T
     * @Author: wuzhenzhao
     * @Date: 2020/2/8 15:37
     */
    T getMessage();

}


