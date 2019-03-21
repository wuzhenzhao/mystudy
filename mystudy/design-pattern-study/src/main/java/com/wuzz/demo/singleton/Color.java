package com.wuzz.demo.singleton;


/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/20
 * Time: 14:53
 * Description://常量中去使用，常量不就是用来大家都能够共用吗？
 * //通常在通用API中使用
 */
public enum Color {
    RED(){
        private int r = 255;
        private int g = 0;
        private int b = 0;

    },BLACK(){
        private int r = 0;
        private int g = 0;
        private int b = 0;
    },WHITE(){
        private int r = 255;
        private int g = 255;
        private int b = 255;
    };
}
