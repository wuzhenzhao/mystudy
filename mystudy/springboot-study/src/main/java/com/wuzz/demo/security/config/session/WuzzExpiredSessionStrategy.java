/**
 *
 */
package com.wuzz.demo.security.config.session;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
public class WuzzExpiredSessionStrategy implements SessionInformationExpiredStrategy {


    /* (non-Javadoc)
     * @see org.springframework.security.web.session.SessionInformationExpiredStrategy#onExpiredSessionDetected(org.springframework.security.web.session.SessionInformationExpiredEvent)
     */
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        String message = "session过期处理。";

        event.getResponse().setStatus(HttpStatus.UNAUTHORIZED.value());
        event.getResponse().setContentType("application/json;charset=UTF-8");
        event.getResponse().getWriter().write(JSON.toJSONString(message));
    }


}
