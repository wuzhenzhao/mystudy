package com.wuzz.demo.builder;

/**
 * @description: 建造者模式，比如mybatis的SqlSessionFactoryBuilser，还有Quartz
 * @author: Wuzhenzhao
 * @time 2020/3/23 16:13
 * @since 1.0
 **/
public class CourseBuilder {

    private Course course =new Course();

    public CourseBuilder addName(String name){
        course.setName(name);
        return this;
    }
    public CourseBuilder addPpt(String ppt){
        course.setPpt(ppt);
        return this;
    }
    public CourseBuilder addVideo(String video){
        course.setVideo(video);
        return this;
    }
    public CourseBuilder addNote(String note){
        course.setNote(note);
        return this;
    }
    public Course build(){
        return this.course;
    }
}
