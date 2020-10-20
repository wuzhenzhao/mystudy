package com.wuzz.demo.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: Wuzhenzhao
 * @time 2020/4/15 20:38
 * @since 1.0
 **/
//为请求合并的实现准备 一 个批量请求命令的实现
//批量请求命令实际上就是 一 个简单的HystrixCommand实现
public class HelloBatchCommand extends HystrixCommand<List<String>> {

    private HelloCollapseService helloCollapseService;

    private List<String> ids;

    public HelloBatchCommand(HelloCollapseService helloCollapseService, List<String> ids) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("helloBatchCommand")));
        this.helloCollapseService = helloCollapseService;
        this.ids = ids;
    }

    @Override
    protected List<String> run() {
        //这段打印用域等等测试，查看是否是调用这个接口去服务获取数据的
        System.out.println("finaAll request:---------" + ids + "Thread.currentThread().getName():-------" + Thread.currentThread().getName());
        return helloCollapseService.hi(ids);
    }

    @Override
    protected List<String> getFallback() {
        List<String> users = new ArrayList<String>();
        users.add("失败者");
        return new ArrayList<String>(users);
    }
}
