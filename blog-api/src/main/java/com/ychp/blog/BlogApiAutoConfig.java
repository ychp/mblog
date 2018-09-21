package com.ychp.blog;

import com.ychp.blog.cache.ArticleCacher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author yingchengpeng
 * @date 2018/8/10
 */
@ComponentScan
@Configuration
public class BlogApiAutoConfig {

	@Bean
	public ArticleCacher articleCacher() {
		return new ArticleCacher();
	}
}
