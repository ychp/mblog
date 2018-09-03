package com.ychp.blog.impl.server.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.ychp.blog.impl.server.converter.search.ArticleSearchRequestConverter;
import com.ychp.blog.impl.server.repository.ArticleSummaryRepository;
import com.ychp.blog.model.ArticleSummary;
import com.ychp.blog.search.ArticleSearchReadService;
import com.ychp.blog.search.bean.query.ArticleSearchCriteria;
import com.ychp.blog.search.bean.response.ArticleSearchVO;
import com.ychp.blog.search.bean.response.SearchWithAggsVO;
import com.ychp.common.exception.ResponseException;
import com.ychp.common.model.paging.Paging;
import com.ychp.es.bean.request.QueryRequest;
import com.ychp.es.client.EsClient;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yingchengpeng
 * @date 2018/8/19
 */
@Slf4j
@Service
public class ArticleSearchReadServiceImpl implements ArticleSearchReadService {

	@Autowired
	private EsClient esClient;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ArticleSummaryRepository articleSummaryRepository;

	@Override
	public SearchWithAggsVO search(ArticleSearchCriteria criteria) {
		QueryRequest request = ArticleSearchRequestConverter.buildRequest(criteria);
		SearchResponse response = esClient.query(request);

		SearchWithAggsVO searchWithAggsVO = new SearchWithAggsVO();

		Paging<ArticleSearchVO> paging = new Paging<>();
		paging.setTotal(response.getHits().getTotalHits());

		SearchHit[] hits = response.getHits().getHits();
		List<ArticleSearchVO> datas = Lists.newArrayListWithCapacity(hits.length);
		ArticleSearchVO articleSearchVO;
		List<Long> labelIds = Lists.newArrayList();
		for(SearchHit hit : hits) {
			String source = hit.getSourceAsString();
			try {
				articleSearchVO = objectMapper.readValue(source, ArticleSearchVO.class);
			} catch (IOException e) {
				throw new ResponseException("search.data.parse.fail");
			}

			fillHighlighters(articleSearchVO, hit.getHighlightFields());

			if(!CollectionUtils.isEmpty(articleSearchVO.getLabelIds())) {
				labelIds.addAll(articleSearchVO.getLabelIds());
			}
			datas.add(articleSearchVO);
		}

		List<Long> articleIds = datas.stream().map(ArticleSearchVO::getId).collect(Collectors.toList());
		List<ArticleSummary> summaries;
		try {
			summaries = articleSummaryRepository.findByArticleIds(articleIds);
		} catch (Exception e) {
			throw new ResponseException("article.summary.find.fail", e.getMessage(), e.getCause());
		}
		Map<Long, ArticleSummary> summaryByArticleId = summaries.stream()
				.collect(Collectors.toMap(ArticleSummary::getArticleId, articleSummary -> articleSummary));


		for(ArticleSearchVO articleSearch : datas) {
			articleSearch.setSummary(summaryByArticleId.get(articleSearch.getId()));
		}

		paging.setDatas(datas);

		searchWithAggsVO.setPaging(paging);
		return searchWithAggsVO;
	}

	private void fillHighlighters(ArticleSearchVO articleSearchVO,
	                              Map<String, HighlightField> highlightFields) {
		HighlightField title = highlightFields.get("title");
		if(title != null) {
			Text[] texts = title.getFragments();
			if(texts != null && texts.length > 0) {
				articleSearchVO.setTitle(texts[0].toString());
			}
		}

		HighlightField synopsis = highlightFields.get("synopsis");
		if(title != null) {
			Text[] texts = synopsis.getFragments();
			if(texts != null && texts.length > 0) {
				articleSearchVO.setSynopsis(texts[0].toString());
			}
		}

	}

}
