package com.ychp.es;

import com.google.common.collect.Lists;
import com.ychp.es.client.impl.EsClientImpl;
import com.ychp.es.properties.EsProperties;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * @author yingchengpeng
 * @date 2018/8/17
 */
@ComponentScan
@Configuration
@EnableConfigurationProperties(EsProperties.class)
public class EsAutoConfiguration {

	@Autowired
	private EsProperties esProperties;

	@Bean
	public Settings settings() {
		return Settings.builder()
				.put("cluster.name", esProperties.getClusterName())
				//客户端嗅探整个集群的状态，把集群中其它机器的ip地址自动添加到客户端中，并且自动发现新加入集群的机器
				.put("client.transport.sniff", true)
				.put("client.transport.ping_timeout", esProperties.getTimeout() + "s")
				.build();
	}

	@Bean
	public TransportClient client(Settings settings) throws UnknownHostException {
		List<TransportAddress> addresses = Lists.newArrayList();
		if(esProperties.getIsCluster()) {
			for(String address : esProperties.getAddresses()) {
				String[] hostAndPort = address.split(":");
				addresses.add(new TransportAddress(InetAddress.getByName(hostAndPort[0]),
						Integer.valueOf(hostAndPort[1])));
			}
		} else {
			addresses.add(new TransportAddress(InetAddress.getByName(esProperties.getHost()),
					esProperties.getPort()));
		}
		return new PreBuiltTransportClient(settings).addTransportAddresses(addresses.toArray(new TransportAddress[]{}));
	}

	@Bean
	public EsClientImpl esClient(TransportClient client) {
		return new EsClientImpl(client);
	}
}
