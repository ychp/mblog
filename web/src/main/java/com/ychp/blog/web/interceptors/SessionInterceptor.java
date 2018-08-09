package com.ychp.blog.web.interceptors;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.ychp.blog.web.util.SkyUserMaker;
import com.ychp.common.model.SkyUser;
import com.ychp.common.util.SessionContextUtils;
import com.ychp.ip.component.IPServer;
import com.ychp.user.model.User;
import com.ychp.user.service.UserReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author yingchengpeng
 * @date 2018-08-08
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserReadService userReadService;

    @Autowired
    private IPServer ipServer;

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
                        return Lists.newArrayList("/api/user/login", "/api/v2/api-docs", "/swagger.*");
                    }
                });

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object userId = session.getAttribute("userId");
        String uri = request.getRequestURI();

        if(userId != null) {
            User user = userById.get(Long.valueOf(userId.toString()));
            SkyUser skyUser = SkyUserMaker.make(user);
            String ip = ipServer.getIp(request);
            skyUser.setIp(ip);
            SessionContextUtils.put(skyUser);
            return true;
        }

        return contains(white.get("all"), uri);

    }

    private boolean contains(List<String> uris, String uri) {
        for(String uriReg : uris) {
            if(uri.matches(uriReg)) {
                return true;
            }
        }
        return false;
    }

}
