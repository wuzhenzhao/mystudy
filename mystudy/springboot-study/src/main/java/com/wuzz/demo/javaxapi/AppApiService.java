package com.wuzz.demo.javaxapi;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/8/14 14:23
 * @since 1.0
 **/
public class AppApiService implements IAppApiService {
    @Override
    public String test() {
        return "Hello  javax.ws.rs-api";
    }
}
