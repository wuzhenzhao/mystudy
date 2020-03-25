package com.wuzz.demo.state;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/3/25 11:34
 * @since 1.0
 **/
public class Test {
    public static void main(String[] args) {
        AppContext context = new AppContext();
        context.favorite();
        context.comment("评论：好文章，360个赞");
    }
}
