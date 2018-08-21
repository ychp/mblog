package com.ychp.es.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yingchengpeng
 * @date 2018/8/17
 */
@Data
@ConfigurationProperties(prefix = "es")
public class EsProperties {

	private String host;

	private Integer port;

	private Boolean isCluster = false;

	private String[] addresses;

	private String clusterName;

	private Integer timeout;
}
