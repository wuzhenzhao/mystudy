package com.wuzz.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Wuzhenzhao
 * Date: 2019/12/7
 * Time: 9:51
 * Description:
 * ClassPath:com.wuzz.demo.RedisController
 */
@RestController
public class RedisController {

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/redisTest" ,method = RequestMethod.GET)
    public String redisTest(@RequestParam String name){
        String value = redisUtil.get(name);
        if(value == null){
            redisUtil.set(name,"wuzz");
            return "Redis 获取无效";
        }
        return value;

    }
}
