package com.ychp.blog.web;

import com.ychp.common.captcha.CaptchaGenerator;
import com.ychp.ip.IPServiceAutoConfiguration;
import com.ychp.user.UserAutoConfiguration;
import com.ychp.blog.web.interceptors.SessionInterceptor;
import com.ychp.blog.web.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/9/14
 */
@EnableWebMvc
@Configuration
@Import({IPServiceAutoConfiguration.class, UserAutoConfiguration.class})
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

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public CaptchaGenerator captchaGenerator() {
        return new CaptchaGenerator();
    }
}
