package com.wuzz.demo.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2021/7/1 10:27
 * @since 1.0
 **/
public class CHMDemo {

    private static final ConcurrentHashMap<String,Integer> concurrentHashMap = new ConcurrentHashMap<String,Integer>(16);

    public static void main(String[] args) {

        concurrentHashMap.put("wuzz", 1);
//        computeIfAbsent
//        computeIfPresent
//        compute（computeIfAbsent和computeIfPresent两者的结合）
//        merge（合并数据）
        System.out.println(concurrentHashMap.get("wuzz"));
        //如果key 不存在，则调用计算，返回值作为value
        concurrentHashMap.computeIfAbsent("wuzz",v->{
            return 1;
        });
        System.out.println(concurrentHashMap.get("wuzz"));
        //存在则修改，不存在返回null
        concurrentHashMap.computeIfPresent("wuzz",(k,v)->v+1);

        System.out.println(concurrentHashMap.get("wuzz"));


        ConcurrentHashMap<Integer,Integer> chm = new ConcurrentHashMap<Integer,Integer>(16);
        Stream.of(1,2,4,5,3,2,1,5,4,6,8).forEach(v->{
            chm.merge(v,5,Integer::sum);
        });
        System.out.println(chm);
    }
}
