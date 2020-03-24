package com.wuzz.demo.iterator;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/3/23 19:21
 * @since 1.0
 **/
public interface Iterator<E> {

    E next();

    boolean hasNext();

}
