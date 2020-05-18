package com.wuzz.demo.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/5/15 20:11
 * @since 1.0
 **/
@Data
public class SearchResult {
    private Long total;//记录总数
    private Float useTime;//搜索花费时间(毫秒)
    private String distance;//距离单位(米)

    private List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();//数据集合
}
