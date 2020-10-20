package com.wuzz.demo.composite.transparent;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/3/24 19:28
 * @since 1.0
 **/
public class Course extends CourseComponent {
    private String name;
    private double price;

    public Course(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName(CourseComponent catalogComponent) {
        return this.name;
    }

    @Override
    public double getPrice(CourseComponent catalogComponent) {
        return this.price;
    }

    @Override
    public void print() {
        System.out.println(name + " (￥" + price + "元)");
    }

}
