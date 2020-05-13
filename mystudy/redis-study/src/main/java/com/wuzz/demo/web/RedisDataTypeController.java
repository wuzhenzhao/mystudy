package com.wuzz.demo.web;

import com.wuzz.demo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoUnit;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * User: Wuzhenzhao
 * Date: 2019/12/7
 * Time: 9:51
 * Description:  redis五大数据类型基础操作及应用场景
 * ClassPath:com.wuzz.demo.RedisController
 */
@RestController
public class RedisDataTypeController {

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/set", method = RequestMethod.GET)
    public String set(@RequestParam String key, @RequestParam String value) {
        return redisUtil.set(key, value);

    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String get(@RequestParam String key) {
        String s = redisUtil.get(key);
        return s;

    }

    /**
     * 功能描述: 原子递增
     * 文章的阅读量，微博点赞数，允许一定的延迟，先写入 Redis 再定时同步到数据库。
     *
     * @Param: [key]
     * @Return: java.lang.Long
     * @Author: wuzhenzhao
     * @Date: 2020/5/12 16:00
     */
    @RequestMapping(value = "/incr", method = RequestMethod.GET)
    public Long incr(@RequestParam String key) {
        return redisUtil.incr(key);

    }

    /**
     * 功能描述: 原子递减
     * 文章的阅读量，微博点赞数，允许一定的延迟，先写入 Redis 再定时同步到数据库。
     *
     * @Param: [key]
     * @Return: java.lang.Long
     * @Author: wuzhenzhao
     * @Date: 2020/5/12 16:01
     */
    @RequestMapping(value = "/decr", method = RequestMethod.GET)
    public Long decr(@RequestParam String key) {
        return redisUtil.decr(key);

    }

    /**
     * 功能描述: <br>
     * hash 的结构可以任意添加或删除‘字段名’，更加高效灵活。String 可以做的事情，Hash 基本都可以做（位运算除外）
     *
     * @Param: [key, field, value]
     * @Return: java.lang.Long
     * @Author: wuzhenzhao
     * @Date: 2020/5/12 16:27
     */
    @RequestMapping(value = "/hset", method = RequestMethod.GET)
    public Long hset(@RequestParam String key, @RequestParam String field, @RequestParam String value) {
        return redisUtil.hset(key, field, value);

    }

    @RequestMapping(value = "/hget", method = RequestMethod.GET)
    public String hget(@RequestParam String key, @RequestParam String field) {
        return redisUtil.hget(key, field);

    }

    /**
     * 功能描述: 存储有序的字符串（从左到右），元素可以重复。可以充当队列和栈的角色。
     * 消息队列
     *
     * @Param: [key]
     * @Return: java.lang.Long
     * @Author: wuzhenzhao
     * @Date: 2020/5/12 19:29
     */
    @RequestMapping(value = "/lpush", method = RequestMethod.GET)
    public Long lpush(@RequestParam String key) {
        return redisUtil.lpush(key, new String[]{"a", "b", "c"});

    }

    @RequestMapping(value = "/lpop", method = RequestMethod.GET)
    public String lpop(@RequestParam String key) {
        return redisUtil.lpop(key);

    }

    /**
     * 功能描述: 抽奖：随机获取元素。
     * 点赞 、签到、打卡\维护商品所有的标签。某个商品有的一些标签都可以维护到set里。
     *
     * @Param: [key]
     * @Return: java.lang.Long
     * @Author: wuzhenzhao
     * @Date: 2020/5/12 19:38
     */
    @RequestMapping(value = "/sadd", method = RequestMethod.GET)
    public Long sadd(@RequestParam String key) {
        return redisUtil.sadd(key, new String[]{"jack", "mic", "james", "wuzz"});

    }

    @RequestMapping(value = "/srandmember", method = RequestMethod.GET)
    public String srandmember(@RequestParam String key) {
        return redisUtil.srandmember(key);
    }

    /**
     * 功能描述: 排行榜  zincrby 基于这个原子递增
     *
     * @Param: [key]
     * @Return: java.lang.Long
     * @Author: wuzhenzhao
     * @Date: 2020/5/12 19:48
     */
    @RequestMapping(value = "/zadd", method = RequestMethod.GET)
    public Long zadd(@RequestParam String key) {
        Map<String, Double> map = new HashMap<>();
        map.put("wuzz", 10.0);
        map.put("jack", 20.0);
        map.put("james", 30.0);
        map.put("tom", 40.0);
        return redisUtil.zadd(key, map);

    }

    @RequestMapping(value = "/zrange", method = RequestMethod.GET)
    public Set<String> zrange(@RequestParam String key) {
        return redisUtil.zrange(key, 0, -1);
    }

    /**
     * 功能描述:
     *
     * @Param: [key]
     * @Return: java.lang.Long
     * @Author: wuzhenzhao
     * @Date: 2020/5/12 20:07
     */
    @RequestMapping(value = "/geoadd", method = RequestMethod.GET)
    public Long geoadd(@RequestParam String key) {
        Map<String, GeoCoordinate> map = new HashMap<>();
        map.put("hangzhou", new GeoCoordinate(120.21201, 30.2084));
        map.put("cangnan", new GeoCoordinate(120.34484748480224, 27.520736008793268));
        return redisUtil.geoadd(key, map);

    }

    //计算两个坐标之间的距离
    @RequestMapping(value = "/geodist", method = RequestMethod.GET)
    public Double geodist(@RequestParam String key) {
        return redisUtil.geodist(key, "hangzhou", "cangnan", GeoUnit.KM);

    }
}
