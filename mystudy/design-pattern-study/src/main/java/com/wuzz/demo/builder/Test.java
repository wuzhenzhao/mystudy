package com.wuzz.demo.builder;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/3/23 16:30
 * @since 1.0
 **/
public class Test {

    public static void main(String[] args) {
        Course course=new CourseBuilder()
                .addName("课程名称")
                .addNote("笔记")
                .addPpt("PPT")
                .addVideo("录像")
                .build();
        System.out.println(course);
    }
}
