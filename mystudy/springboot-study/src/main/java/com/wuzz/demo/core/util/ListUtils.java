package com.wuzz.demo.core.util;

import java.util.List;

/**
 * @description: 集合工具类
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/2/9 15:06
 * @since 1.0
 **/
public class ListUtils {

    /*
     * 功能描述: <br>
     * 判断集合是否为空
     * @Param: [list]
     * @Return: boolean
     * @Author: wuzhenzhao
     * @Date: 2020/2/9 15:07
     */
    public static boolean isEmpty(List list) {
        if (list != null && list.size() > 0) {
            return false;
        } else {
            return true;
        }
    }
}
