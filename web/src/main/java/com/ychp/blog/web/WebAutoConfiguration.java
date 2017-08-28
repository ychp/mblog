package com.ychp.blog.web;

import com.ychp.blog.user.impl.UserAutoConfiguration;
import com.ychp.blog.web.interceptors.SessionInterceptor;
import com.ychp.blog.web.session.SessionManager;
import com.ychp.msg.MsgAutoConfiguration;
import com.ychp.redis.RedisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/9/14
 */
@Configuration
@Import({UserAutoConfiguration.class,
        MsgAutoConfiguration.class,
        RedisAutoConfiguration.class})
public class WebAutoConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public SessionManager sessionManager() {
        return new SessionManager();
    }

    @Bean
    public SessionInterceptor sessionInterceptor() {
        return new SessionInterceptor();
    }

    @Autowired
    private SessionInterceptor sessionInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(sessionInterceptor);
    }
}
