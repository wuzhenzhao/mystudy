package com.wuzz.demo.core.util;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/6/4 16:48
 * @since 1.0
 **/
public class LambdaUtils {

    // 工具方法
    public static <T> Consumer<T> consumerWithIndex(BiConsumer<T, Integer> consumer) {
        class Obj {
            int i;
        }
        Obj obj = new Obj();
        return t -> {
            int index = obj.i++;
            consumer.accept(t, index);
        };
    }

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("1", "2", "3");
        strings.stream().forEach(LambdaUtils.consumerWithIndex((item, index) -> {
            System.out.println("list[" + index + "]=" + item);
        }));
    }
}
