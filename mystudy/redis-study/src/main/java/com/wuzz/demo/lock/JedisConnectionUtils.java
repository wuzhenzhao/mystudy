package com.wuzz.demo.lock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/5/12 15:31
 * @since 1.0
 **/
public class JedisConnectionUtils {

    private static JedisPool
            pool = null;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(100);
        pool = new JedisPool(jedisPoolConfig, "192.168.1.101", 6379, 5000, "wuzhenzhao");
    }

    public static Jedis getJedis() {
        return pool.getResource();
    }
}
