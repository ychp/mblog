package com.ychp.blog.impl.server.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ychp.es.bean.request.IndexTypeRequest;
import com.ychp.es.client.EsClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Map;

/**
 * @author yingchengpeng
 * @date 2018/8/17
 */
@Slf4j
@Component
public class SearchInitializer {

	@Autowired
	private EsClient client;

	@Autowired
	private ObjectMapper objectMapper;

	public void initIndices() throws IOException {
		File file = ResourceUtils.getFile("classpath*:search/article.json");
		FileInputStream fis = new FileInputStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

		StringBuilder stringBuffer = new StringBuilder();
		String tmp = reader.readLine();
		while (tmp != null) {
			stringBuffer.append(tmp);
			tmp = reader.readLine();
		}

		IndexTypeRequest request = new IndexTypeRequest();
		request.setIndex("articles");
		request.setType("article");
		request.setMapping(stringBuffer.toString());
		Boolean result = client.createType(request);
		if(result) {
			Map map = objectMapper.readValue(request.getMapping(), Map.class);
			log.info("init type success, \n {}",
					objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(map));

		}
	}

}
