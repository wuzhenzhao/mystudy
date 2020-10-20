package com.wuzz.demo.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/3/23 19:24
 * @since 1.0
 **/
public class CourseAggregateImpl implements ICourseAggregate {
    private List courseList;

    public CourseAggregateImpl() {
        this.courseList = new ArrayList();
    }

    public void add(Course course) {
        courseList.add(course);
    }

    public void remove(Course course) {
        courseList.remove(course);
    }

    public Iterator<Course> iterator() {
        return new IteratorImpl<Course>(courseList);
    }
}

