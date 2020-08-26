package com.wuzz.demo.resteasy;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/8/14 14:23
 * @since 1.0
 **/
public class AppApiService implements IAppApiService {
    @Override
    public Result test(HttpServletRequest request) {
//        return "Hello  javax.ws.rs-api";
        Result result = new Result();
        result.setTime(LocalDateTime.now());
        return result;
    }
}
