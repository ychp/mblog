package com.ychp.mblog.web.interceptors;

import com.google.common.collect.Lists;
import com.ychp.common.exception.ResponseException;
import com.ychp.common.model.SkyUser;
import com.ychp.common.util.SessionContextUtils;
import com.ychp.mblog.web.util.SkyUserMaker;
import com.ychp.user.cache.UserCacher;
import com.ychp.user.model.User;
import com.ychp.web.ip.component.IpServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author yingchengpeng
 * @date 2018-08-08
 */
@Slf4j
public class SessionInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private IpServer ipServer;

    @Autowired
    private UserCacher userCacher;

    @Value("${cache.expire.time:60}")
    private Long expiredTime;
    private List<String> whiteList;

    @PostConstruct
    public void init() {
        whiteList = Lists.newArrayList(
                                "/api/address/.*",
                                "/api/article/.*",
                                "/api/comment/.*",
                                "/api/category/.*",
                                "/api/label/.*",
                                "/api/friend-link/visible",
                                "/api/see-log",
                                "/api/file/.*",
                                "/api/user/captcha",
                                "/api/user/register",
                                "/api/user/login",
                                "/api/search/.*",
                                "/api/v2/api-docs",
                                "/v2/api-docs",
                                "/swagger.*",
                                "/webjars.*",
                                "/error",
                                "/csrf",
                                "/index");

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object userId = session.getAttribute("userId");
        String uri = request.getRequestURI();

        if(userId != null) {
            User user = userCacher.findById(Long.valueOf(userId.toString()));
            SkyUser skyUser = SkyUserMaker.make(user);
            String ip = ipServer.getIp(request);
            skyUser.setIp(ip);
            SessionContextUtils.put(skyUser);
            return true;
        }

        if(contains(whiteList, uri)) {
            return true;
        }

        throw new ResponseException(HttpStatus.UNAUTHORIZED.value(), "user.not.login");
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
