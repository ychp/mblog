package com.ychp.blog.impl.server.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yingchengpeng
 * @date 2018-09-11
 */
@Data
@ConfigurationProperties(prefix = "search")
public class SearchProperties {

    private String articleIndex;

    private String articleType;
}
