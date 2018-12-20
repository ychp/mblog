package com.ychp.blog.impl.server.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;
import com.ychp.es.bean.request.IndexTypeRequest;
import com.ychp.es.client.EsClient;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Objects;

/**
 * @author yingchengpeng
 * @date 2018/8/17
 */
@Slf4j
public class SearchInitializer {

	private final EsClient client;

	private final ObjectMapper objectMapper;

	@Getter
	private final String articleIndex;

	@Getter
	private final String articleType;

	public SearchInitializer(String articleIndex, String articleType, ObjectMapper objectMapper, EsClient client) {
		this.articleIndex = articleIndex;
		this.articleType = articleType;
		this.objectMapper = objectMapper;
		this.client = client;
		this.initIndices();
	}

	void initIndices() {
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("search/article.json"))));

			StringBuilder stringBuffer = new StringBuilder();
			String tmp = reader.readLine();
			while (tmp != null) {
				stringBuffer.append(tmp);
				tmp = reader.readLine();
			}

			IndexTypeRequest request = new IndexTypeRequest();
			request.setIndex(articleIndex);
			request.setType(articleType);
			request.setMapping(stringBuffer.toString());
			Boolean result = client.createType(request);
			if(result) {
				Map map = objectMapper.readValue(request.getMapping(), Map.class);
				log.info("init type success, \n {}",
						objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(map));

			}
		} catch (Exception e) {
			log.error("fail to connect es, case {}", Throwables.getStackTraceAsString(e));
		}
	}

}
