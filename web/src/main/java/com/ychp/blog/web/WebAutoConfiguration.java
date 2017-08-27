package com.ychp.blog.web;

import com.ychp.blog.user.impl.UserAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/9/14
 */
@Configuration
@Import(UserAutoConfiguration.class)
public class WebAutoConfiguration {


}
