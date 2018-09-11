package com.ychp.blog.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ychp.blog.impl.server.properties.SearchProperties;
import com.ychp.blog.impl.server.search.SearchInitializer;
import com.ychp.es.EsAutoConfiguration;
import com.ychp.es.client.EsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author yingchengpeng
 * @date 2018/8/10
 */
@Configuration
@Import({EsAutoConfiguration.class})
@EnableConfigurationProperties(SearchProperties.class)
public class BlogSearchAutoConfiguration {

    @Autowired
    private SearchProperties searchProperties;

    @Bean
    public SearchInitializer searchInitializer(ObjectMapper objectMapper, EsClient client) {
        return new SearchInitializer(searchProperties.getArticleIndex(), searchProperties.getArticleType(),
                objectMapper, client);
    }
}
