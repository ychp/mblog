package com.ychp.blog.impl.server.converter.search;

import com.google.common.collect.Lists;
import com.ychp.blog.impl.server.constant.ArticleSearchConstants;
import com.ychp.blog.search.bean.query.ArticleSearchCriteria;
import com.ychp.common.util.DateUtils;
import com.ychp.es.bean.request.QueryRequest;
import com.ychp.es.model.Aggregation;
import com.ychp.es.model.Filter;
import com.ychp.es.model.Highlighter;
import com.ychp.es.model.Term;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author yingchengpeng
 * @date 2018/8/19
 */
public class ArticleSearchRequestConverter {

	public static QueryRequest buildRequest(ArticleSearchCriteria criteria) {
		QueryRequest request = new QueryRequest();
		request.setIndex(ArticleSearchConstants.SEARCH_INDEX);
		request.setType(ArticleSearchConstants.SEARCH_TYPE);

		fillTerms(request, criteria);
		fillFilters(request, criteria);
		fillHighLighters(request);
		fillAggregations(request);

		request.setPageNo(criteria.getPageNo());
		request.setPageSize(criteria.getPageSize());
		return request;
	}

	private static void fillTerms(QueryRequest request, ArticleSearchCriteria criteria) {
		List<Term> terms = Lists.newArrayList();
		if(!StringUtils.isEmpty(criteria.getKeyword())) {
			terms.add(new Term(criteria.getKeyword(), "title", "synopsis", "content"));
		}

		if(!StringUtils.isEmpty(criteria.getAuthor())) {
			terms.add(new Term("author", criteria.getAuthor()));
		}

		if(criteria.getUserId() != null) {
			terms.add(new Term("userId", criteria.getUserId()));
		}

		if(criteria.getCategoryId() != null) {
			terms.add(new Term("categoryId", criteria.getCategoryId()));
		}

		request.setTerms(terms);
	}

	private static void fillFilters(QueryRequest request, ArticleSearchCriteria criteria) {
		if(criteria.getPublishAtStart() == null
				&& criteria.getPublishAtEnd() == null
				&& criteria.getPublishAt() == null) {
			return;
		}

		Filter filter = new Filter();
		filter.setName("publishAt");
		if(!StringUtils.isEmpty(criteria.getPublishAt())) {
			filter.setFrom(DateUtils.parse2DateTimeStr(criteria.getPublishAt()));
			filter.setTo(DateUtils.parse2EndDateTimeStr(criteria.getPublishAt()));
			request.setFilters(Lists.newArrayList(filter));
			return;
		}

		if(!StringUtils.isEmpty(criteria.getPublishAtStart())) {
			filter.setFrom(DateUtils.parse2DateTimeStr(criteria.getPublishAtStart()));
		}

		if(!StringUtils.isEmpty(criteria.getPublishAtEnd())) {
			filter.setTo(DateUtils.parse2EndDateTimeStr(criteria.getPublishAtEnd()));
		}
		request.setFilters(Lists.newArrayList(filter));
	}

	private static void fillHighLighters(QueryRequest request) {
		request.setHighlighter(new Highlighter("*", "<span style=\"color:red\">", "</span>"));
	}

	private static void fillAggregations(QueryRequest request) {
		List<Aggregation> aggregations = Lists.newArrayList();
		aggregations.add(new Aggregation("categories", "categoryId", 100));
		aggregations.add(new Aggregation("publishAt", "publishDate", 100));
		request.setAggregations(aggregations);
	}
}
