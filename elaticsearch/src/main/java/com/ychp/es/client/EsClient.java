package com.ychp.es.client;

import com.ychp.es.bean.request.DocumentDeleteRequest;
import com.ychp.es.bean.request.IndexDocRequest;
import com.ychp.es.bean.request.IndexTypeRequest;
import com.ychp.es.bean.request.QueryRequest;
import org.elasticsearch.action.search.SearchResponse;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * @author yingchengpeng
 * @date 2018/8/17
 */
public interface EsClient {


	/**
	 * 创建类型
	 * @param request 请求数据
	 * @throws IOException 异常
	 */
	Boolean createType(IndexTypeRequest request) throws IOException;

	/**
	 * 索引文档
	 * @param request 请求数据
	 * @throws ExecutionException 异常
	 * @throws InterruptedException 异常
	 */
	void index(IndexDocRequest request)
			throws ExecutionException, InterruptedException;

	/**
	 * 删除文档
	 * @param request 请求数据
	 * @throws ExecutionException 异常
	 * @throws InterruptedException 异常
	 */
	void delete(DocumentDeleteRequest request)
			throws ExecutionException, InterruptedException;

	/**
	 * 搜索数据
	 * @param request 请求数据
	 * @return 搜索结果
	 */
	SearchResponse query(QueryRequest request);

}
