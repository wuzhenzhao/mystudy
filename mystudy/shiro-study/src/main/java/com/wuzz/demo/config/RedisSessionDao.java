package com.wuzz.demo.config;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/6/12 19:37
 * @since 1.0
 **/
@Component("redisSessionDao")
public class RedisSessionDao extends AbstractSessionDAO {

    @Value("${session.expireTime}")
    private long expireTime;

    @Autowired
    private StringRedisTemplate redisTemplate;

    // 创建session，保存到数据库
    @Override
    protected Serializable doCreate(Session session) throws UnknownSessionException {
        System.out.println("创建Session.......");
        Assert.notNull(session);
        if (session.getId() == null) {
            Serializable sessionId = generateSessionId(session);
            assignSessionId(session, sessionId);
        }
        String sessionId = session.getId().toString();


        //判断session是否已经存在
        Boolean exist = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) {
                Boolean result = connection.exists(sessionId.getBytes());
                return result;
            }
        });

        if (exist) {
            throw new RuntimeException("session " + sessionId + "已经存在");
        }
        Boolean success = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) {
                Boolean result = connection.setNX(sessionId.getBytes(), sessionToByte(session));
                return result;
            }
        });

        if (!success) {
            throw new RuntimeException("session " + sessionId + "创建失败");
        }
        //设置Session超时间间
        redisTemplate.expire(sessionId, expireTime, TimeUnit.MINUTES);

        return session.getId();
    }

    // 获取session
    @Override
    protected Session doReadSession(Serializable sessionId) {
        System.out.println("查询Session.......");
        Session session = redisTemplate.execute(new RedisCallback<Session>() {
            public Session doInRedis(RedisConnection connection) {
                byte[] bytes = connection.get(sessionId.toString().getBytes());
                if (null == bytes || bytes.length == 0) {
                    return null;
                }
                return ByteToSession(bytes);

            }
        });
        return session;
    }

    // 更新session的最后一次访问时间
    @Override
    public void update(Session session) {
        Assert.notNull(session);
        if (session.getId() == null) {
            throw new RuntimeException("sessionId is null");
        }
        String sessionId = session.getId().toString();
        //判断session是否已经存在
        Boolean exist = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) {
                Boolean result = connection.exists(sessionId.getBytes());
                return result;
            }
        });

        if (!exist) {
            throw new RuntimeException("session " + sessionId + "不存在");
        }

        Boolean success = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) {
                try {
                    connection.set(sessionId.getBytes(), sessionToByte(session));
                } catch (Exception e) {
                    return false;
                }
                return true;
            }
        });

        if (!success) {
            throw new RuntimeException("session " + sessionId + "更新失败");
        }
        System.out.println("更新Session.......");
        //设置Session超时间间
        redisTemplate.expire(sessionId, expireTime, TimeUnit.MINUTES);

    }

    // 删除session
    @Override
    public void delete(Session session) {
        System.out.println("删除Session.......");
        redisTemplate.delete(session.getId().toString());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        return Collections.emptySet();
    }

    /**
     * session转成字节数组流
     *
     * @param session
     * @return
     */
    private byte[] sessionToByte(Session session) {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        byte[] bytes = null;
        try {
            ObjectOutput oo = new ObjectOutputStream(bo);
            oo.writeObject(session);
            bytes = bo.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * 获取redis中的流转session
     *
     * @param bytes
     * @return
     */
    private Session ByteToSession(byte[] bytes) {
        Session session = null;
        try {
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream oi = new ObjectInputStream(bi);

            Object o = oi.readObject();
            session = (Session) o;
            bi.close();
            oi.close();
        } catch (Exception e) {
            System.out.println("translation" + e.getMessage());
            e.printStackTrace();
        }
        return session;
    }
}
