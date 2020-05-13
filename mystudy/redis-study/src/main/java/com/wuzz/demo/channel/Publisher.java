package com.wuzz.demo.channel;

import com.wuzz.demo.lock.JedisConnectionUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/5/12 20:12
 * @since 1.0
 **/
public class Publisher {
    public static final String CHANNEL_KEY = "channel:message";


    public void publishMessage(String message) {
        if (StringUtils.isEmpty(message)) {
            return;
        }
        Jedis jedis = JedisConnectionUtils.getJedis();
        jedis.publish(CHANNEL_KEY, message);
    }

    public static void main(String[] args) {
        Publisher publisher = new Publisher();
        publisher.publishMessage("Hello Redis!");
    }
}
