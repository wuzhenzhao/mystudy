package com.wuzz.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;
import java.util.Set;

/**
 * User: Wuzhenzhao
 * Date: 2019/12/7
 * Time: 9:48
 * Description:
 * ClassPath:com.wuzz.demo.util.RedisUtil
 */
@Component
public class RedisUtil {

    @Autowired
    private JedisPool jedisPool;

    //计算两个坐标距离
    public Double geodist(String key, String member1, String member2, GeoUnit unit) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.geodist(key, member1, member2, unit);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnResource(jedisPool, jedis);
        }
    }

    // 添加一个或多个元素元素
    public Long geoadd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.geoadd(key, memberCoordinateMap);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnResource(jedisPool, jedis);
        }
    }

    // 获取全部元素
    public Set<String> zrange(String key, long start, long end) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zrange(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnResource(jedisPool, jedis);
        }
    }

    // 添加一个或多个元素元素
    public Long zadd(String key, Map<String, Double> scoreMembers) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zadd(key, scoreMembers);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnResource(jedisPool, jedis);
        }
    }


    // 随机弹出一个元素
    public String srandmember(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.srandmember(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnResource(jedisPool, jedis);
        }
    }

    // 添加一个或多个元素元素
    public Long sadd(String key, String... value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sadd(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnResource(jedisPool, jedis);
        }
    }

    // 从左弹出一个元素
    public String lpop(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.lpop(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnResource(jedisPool, jedis);
        }
    }

    // 从左添加元素
    public Long lpush(String key, String... value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.lpush(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnResource(jedisPool, jedis);
        }
    }

    // hash 类型存储
    public Long hset(String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hset(key, field, value);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnResource(jedisPool, jedis);
        }
    }

    // hash 类型获取
    public String hget(String key, String field) {
        Jedis jedis = null;
        String value;
        try {
            jedis = jedisPool.getResource();
            value = jedis.hget(key, field);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnResource(jedisPool, jedis);
        }
        return value;
    }

    /**
     * 原子递增
     */
    public Long incr(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.incr(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnResource(jedisPool, jedis);
        }
    }

    /**
     * 原子递减
     */
    public Long decr(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.decr(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnResource(jedisPool, jedis);
        }
    }

    /**
     * 向Redis中存值，原子操作，带过期时间 3秒
     */
    public String setAndExpire(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(key, value, "NX", "PX", 3000);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnResource(jedisPool, jedis);
        }
    }

    /**
     * 向Redis中存值，永久有效
     */
    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnResource(jedisPool, jedis);
        }
    }

    /**
     * 根据传入Key获取指定Value
     */
    public String get(String key) {
        Jedis jedis = null;
        String value;
        try {
            jedis = jedisPool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnResource(jedisPool, jedis);
        }
        return value;
    }

    /**
     * 校验Key值是否存在
     */
    public Boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnResource(jedisPool, jedis);
        }
    }

    /**
     * 删除指定Key-Value
     */
    public Long delete(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        } finally {
            returnResource(jedisPool, jedis);
        }
    }

    // 以上为常用方法，更多方法自行百度

    /**
     * 释放连接
     */
    private static void returnResource(JedisPool jedisPool, Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }
}
