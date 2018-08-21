package com.ychp.blog.impl;

import com.ychp.es.EsAutoconfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author yingchengpeng
 * @date 2018/8/10
 */
@ComponentScan
@Configuration
@Import({EsAutoconfiguration.class})
public class BlogAutoConfiguration {
}
