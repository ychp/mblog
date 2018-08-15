package com.ychp.cache;

import com.ychp.cache.aop.CacheAdvice;
import com.ychp.cache.aop.CacheInvalidAdvice;
import com.ychp.cache.manager.CacheManager;
import com.ychp.cache.properties.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yingchengpeng
 * @date 2018/8/15
 */
@Configuration
@ConditionalOnProperty(name = "cache.type", havingValue = "redis")
@EnableConfigurationProperties(CacheProperties.class)
public class CacheAutoConfiguration {

	@Bean
	public CacheManager cacheManager() {
		return new CacheManager();
	}

	@Bean
	public CacheAdvice cacheAdvice() {
		return new CacheAdvice();
	}

	@Bean
	public CacheInvalidAdvice cacheInvalidAdvice() {
		return new CacheInvalidAdvice();
	}

}
