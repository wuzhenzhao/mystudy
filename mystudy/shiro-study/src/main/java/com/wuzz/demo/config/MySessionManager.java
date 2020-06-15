package com.wuzz.demo.config;


import com.wuzz.demo.util.ApplicationContextUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/6/12 19:37
 * @since 1.0
 **/
public class MySessionManager extends DefaultWebSessionManager {

    public static final String AUTHORIZATION = "token";

    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    @Autowired
    private RedisSessionDao redisSessionDao;

    public MySessionManager() {
        super();
    }

    /**
     * 获取session
     * 优化单次请求需要多次访问redis的问题
     * @param sessionKey
     * @return
     * @throws UnknownSessionException
     */
    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Serializable sessionId = getSessionId(sessionKey);

        ServletRequest request = null;
        if (sessionKey instanceof WebSessionKey) {
            request = ((WebSessionKey) sessionKey).getServletRequest();
        }

        if (request != null && null != sessionId) {
            Object sessionObj = request.getAttribute(sessionId.toString());
            if (sessionObj != null) {
                System.out.println("read session from request");
                return (Session) sessionObj;
            }
        }
        Session session = super.retrieveSession(sessionKey);
        if (request != null && null != sessionId) {
            request.setAttribute(sessionId.toString(), session);
        }
        return session;
    }


    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String id = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        ShiroFilterFactoryBean bean = ApplicationContextUtils.getBean(ShiroFilterFactoryBean.class);
        String loginUrl = bean.getLoginUrl();
        String requestURI = req.getRequestURI();
        if (StringUtils.isEmpty(id) && !requestURI.equals(loginUrl)) {
            //可以使用全局异常捕获返回到前端
            throw new RuntimeException("请登录");
        }
        //避免sessionId过期自动创建session问题
        if(!StringUtils.isEmpty(id)) {
            Session session = redisSessionDao.doReadSession(id);
            if (session ==null) {
                throw new RuntimeException("There is no session with id [" + id + "]");
            }
        }

        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
        return id;
    }
}
