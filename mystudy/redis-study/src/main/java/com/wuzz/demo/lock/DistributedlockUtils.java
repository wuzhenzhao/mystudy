package com.wuzz.demo.lock;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.UUID;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/5/12 15:17
 * @since 1.0
 **/
public class DistributedlockUtils {

    private final String LOCK_NAME = "DISTRIBUTEDLOCK";

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    /**
     * @param acquireTimeout 获得锁的超时时间
     * @param lockTimeout    锁本身的过期时间
     * @return
     */
    public String acquireLock(long acquireTimeout, long lockTimeout) {
        String identifier = UUID.randomUUID().toString();//保证释放锁的时候是同一个持有锁的人
        String lockKey = "lock:" + LOCK_NAME;
        int lockExpire = (int) (lockTimeout / 1000);
        Jedis jedis = null;
        try {//获取连接
            jedis = JedisConnectionUtils.getJedis();
            long end = System.currentTimeMillis() + acquireTimeout;
            //获取锁的限定时间
            while (System.currentTimeMillis() < end) {
//                if (jedis.setnx(lockKey, identifier) == 1) { //设置值成功
//                    jedis.expire(lockKey, lockExpire); //设置超时时间
//                    return identifier; //获得锁成功
//                }
                String result = jedis.set(lockKey, identifier, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, lockTimeout);
                if (LOCK_SUCCESS.equals(result)) {
                    return identifier;
                }
                //表示没有超时时间
                if (jedis.ttl(lockKey) == -1) {
                    jedis.expire(lockKey, lockExpire); //设置超时时间
                }
                try {
                    //等待片刻后进行获取锁的重试
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            jedis.close(); //回收
        }
        return null;
    }

    public boolean releaseLock(String identifier) {
        System.out.println(LOCK_NAME + "开始释放锁：" + identifier);
        String lockKey = "lock:" + LOCK_NAME;
        Jedis jedis = null;
        boolean isRelease = false;
        try {
            jedis = JedisConnectionUtils.getJedis();
            while (true) {
                //Watch 命令用于监视一个(或多个) key ，如果在事务执行之前这个(或这些) key 被其他命令所改动，那么事务将被打断
                jedis.watch(lockKey);
                //判断是否为同一把锁
                if (identifier.equals(jedis.get(lockKey))) {
                    //标记事务开始
                    Transaction transaction = jedis.multi();
                    transaction.del(lockKey);
                    if (transaction.exec().isEmpty()) {
                        continue;
                    }
                    isRelease = true;
                } else {
                    //TODO 异常
                }
                jedis.unwatch();
                break;
            }
        } finally {
            jedis.close();
        }
        return isRelease;
    }
}
