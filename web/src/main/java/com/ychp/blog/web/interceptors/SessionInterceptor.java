package com.ychp.blog.web.interceptors;

import com.ychp.blog.web.session.SessionManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 2017/8/28
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {

    private final static String SESSION_KEY = "msid";

    @Autowired
    private SessionManager sessionManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String msid = getMsid(request, response);
        HttpSession session = sessionManager.getSession(request, msid);
        session.setAttribute("test", "test");
        Long userId = (Long) session.getAttribute("userId");
        if(userId == null) {

        }

        return super.preHandle(request, response, handler);
    }

    private String getMsid(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String msid = null;
        if(cookies == null) {
            msid = addMsid(response);
        } else {
            for(Cookie cookie : cookies) {
                if(SESSION_KEY.equals(cookie.getName())) {
                    msid = cookie.getValue();
                }
            }
            if(StringUtils.isEmpty(msid)) {
                msid = addMsid(response);
            }
        }
        return msid;
    }

    private String addMsid(HttpServletResponse response) {
        String msid = UUID.randomUUID().toString().replace("-", "");
        Cookie cookie = new Cookie(SESSION_KEY, msid);
        cookie.setPath("/");
        response.addCookie(cookie);
        return msid;
    }
}
