package com.wuzz.demo.iterator;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/3/23 19:23
 * @since 1.0
 **/
public interface ICourseAggregate {

    void add(Course course);

    void remove(Course course);

    Iterator<Course> iterator();
}
