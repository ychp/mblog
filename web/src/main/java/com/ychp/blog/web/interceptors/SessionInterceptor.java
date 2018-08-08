package com.ychp.blog.web.interceptors;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.ychp.blog.web.session.SessionManager;
import com.ychp.user.model.User;
import com.ychp.user.service.UserReadService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 2017/8/28
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {

    private final static String SESSION_KEY = "msid";

    @Autowired
    private SessionManager sessionManager;
    @Autowired
    private UserReadService userReadService;

    @Value("${cache.expire.time:60}")
    private Long expiredTime;
    private LoadingCache<Long, User> userById;
    private LoadingCache<String, List<String>> white;

    @PostConstruct
    public void init() {
        userById = CacheBuilder.newBuilder()
                .expireAfterWrite(expiredTime, TimeUnit.MINUTES)
                .initialCapacity(100)
                .maximumSize(1000)
                .build(new CacheLoader<Long, User>() {
                    @Override
                    public User load(Long id) throws Exception {
                        return userReadService.findById(id);
                    }
                });

        white = CacheBuilder.newBuilder()
                .expireAfterWrite(expiredTime, TimeUnit.MINUTES)
                .initialCapacity(100)
                .maximumSize(1000)
                .build(new CacheLoader<String, List<String>>() {
                    @Override
                    public List<String> load(String s) throws Exception {
                        return Lists.newArrayList("/", "/login", "/api/user/login",
                                "/api/v2/api-docs", "/swagger.*");
                    }
                });

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String msid = getMsid(request, response);
//        HttpSession session = sessionManager.getSession(request, msid);

        HttpSession session = request.getSession();
        Object userId = session.getAttribute("userId");
        String uri = request.getRequestURI();

        if(userId != null) {
            User user = userById.get(Long.valueOf(userId.toString()));
            return true;
        }

        if(contains(white.get("all"), uri)) {
            return true;
        }

        return false;
    }

    private boolean contains(List<String> uris, String uri) {
        for(String uriReg : uris) {
            if(uri.matches(uriReg)) {
                return true;
            }
        }
        return false;
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
