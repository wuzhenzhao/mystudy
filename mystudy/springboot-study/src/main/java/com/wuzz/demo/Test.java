package com.wuzz.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/12
 * Time: 17:52
 * Description 描述:
 */
public class Test {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(
                Arrays.asList("a", "v", "g"));

        list.stream().forEach((str) ->{
                    if(str.equals("v")){
                        str+="44444";
                    }

        });
        list.stream().forEach(p -> System.out.println(p));
    }
}
