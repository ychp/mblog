package com.ychp.es.client.impl;

import com.ychp.es.bean.request.DocumentDeleteRequest;
import com.ychp.es.bean.request.IndexDocRequest;
import com.ychp.es.bean.request.IndexTypeRequest;
import com.ychp.es.bean.request.QueryRequest;
import com.ychp.es.client.EsClient;
import com.ychp.es.model.Aggregation;
import com.ychp.es.model.Filter;
import com.ychp.es.model.Highlighter;
import com.ychp.es.model.Term;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.exists.types.TypesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.types.TypesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * @author yingchengpeng
 * @date 2018/8/17
 */
@Slf4j
public class EsClientImpl implements EsClient {

	private TransportClient transportClient;

	public EsClientImpl(TransportClient transportClient) {
		this.transportClient = transportClient;
	}

	@Override
	public Boolean createType(IndexTypeRequest request) throws IOException {
		// 创建索引
		IndicesExistsResponse indicesExistsResponse = transportClient.admin().indices()
				.prepareExists(request.getIndex()).execute().actionGet();
		if(!indicesExistsResponse.isExists()) {
			transportClient.admin().indices().prepareCreate(request.getIndex()).execute().actionGet();
		}

		// 判断类型是否存在
		TypesExistsResponse typesExistsResponse = transportClient.admin().indices()
				.typesExists(new TypesExistsRequest(new String[]{request.getIndex()}, request.getType()))
				.actionGet();
		if(typesExistsResponse.isExists()) {
			return false;
		}

		PutMappingRequest mappingRequest = Requests.putMappingRequest(request.getIndex())
				.type(request.getType()).source(request.getMapping(), XContentType.JSON);
		transportClient.admin().indices().putMapping(mappingRequest).actionGet();
		return true;
	}

	@Override
	public void index(IndexDocRequest request) throws ExecutionException, InterruptedException {
		String index = request.getIndex();
		String type = request.getType();
		String id = request.getId();
		String document = request.getDocument();
		GetResponse getResponse = transportClient.prepareGet(index, type, id).get();
		if(!getResponse.isExists()) {
			transportClient.prepareIndex(index, type)
					.setId(id)
					.setSource(document, XContentType.JSON).execute().get();
			return;
		}
		transportClient.prepareUpdate(index, type, id)
				.setDoc(document, XContentType.JSON).execute().get();
	}

	@Override
	public void delete(DocumentDeleteRequest request) throws ExecutionException, InterruptedException {
		transportClient.prepareDelete(request.getIndex(), request.getType(), request.getId()).execute().get();
	}

	@Override
	public SearchResponse query(QueryRequest request) {
		SearchRequestBuilder builder = transportClient.prepareSearch(request.getIndex())
				.setTypes(request.getType())
				.setSearchType(request.getSearchType());

		if(request.getTerms() != null) {
			BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
			Boolean isMulti = false;
			for (Term term : request.getTerms()) {
				if(term.getIsMulti()) {
					isMulti= true;
					builder = builder.setQuery(QueryBuilders.multiMatchQuery(term.getValue(), term.getMultiNames()));
				} else {
					queryBuilder.must(QueryBuilders.matchQuery(term.getName(), term.getValue()));
				}
			}
			if(!isMulti) {
				builder = builder.setQuery(queryBuilder);
			}
		}

		if(request.getFilters() != null) {
			for (Filter filter : request.getFilters()) {
				builder = builder.setPostFilter(QueryBuilders.rangeQuery(filter.getName())
						.from(filter.getFrom()).to(filter.getTo()));
			}
		}

		if(request.getHighlighter() != null) {
			Highlighter highlighter = request.getHighlighter();
			HighlightBuilder highlightBuilder = new HighlightBuilder().field(highlighter.getField());
			if("*".equals(highlighter.getField())) {
				highlightBuilder = highlightBuilder.requireFieldMatch(true);
			}
			if(!StringUtils.isEmpty(highlighter.getPrefix()) && !StringUtils.isEmpty(highlighter.getSuf())) {
				highlightBuilder = highlightBuilder.preTags(highlighter.getPrefix())
						.postTags(highlighter.getSuf());
			}
			builder = builder.highlighter(highlightBuilder);
		}

		if(request.getAggregations() != null) {
			for (Aggregation aggregation : request.getAggregations()) {
				builder = builder.addAggregation(AggregationBuilders.terms(aggregation.getName())
						.field(aggregation.getField()).size(aggregation.getSize()));

			}
		}

		// 根据匹配度排序
		builder = builder.setExplain(true);
		builder = builder.setFrom(request.getOffset()).setSize(request.getLimit());

		return builder.get();
	}


}
