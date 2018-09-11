package com.ychp.blog.impl.server.search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.ychp.blog.bean.query.ArticleCriteria;
import com.ychp.blog.enums.ArticleStatusEnum;
import com.ychp.blog.impl.server.repository.ArticleDetailRepository;
import com.ychp.blog.impl.server.repository.ArticleRepository;
import com.ychp.blog.model.Article;
import com.ychp.blog.model.ArticleDetail;
import com.ychp.blog.search.ArticleSearchWriteService;
import com.ychp.blog.search.bean.request.IndexArticle;
import com.ychp.common.model.paging.Paging;
import com.ychp.es.bean.request.DocumentDeleteRequest;
import com.ychp.es.bean.request.IndexDocRequest;
import com.ychp.es.client.EsClient;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

/**
 * @author yingchengpeng
 * @date 2018/8/19
 */
@Slf4j
@Service
public class ArticleSearchWriteServiceImpl implements ArticleSearchWriteService {

	private final SearchInitializer searchInitializer;
	private final ArticleRepository articleRepository;
	private final ArticleDetailRepository articleDetailRepository;
	private final EsClient esClient;
	private final ObjectMapper objectMapper;

	@Autowired
	public ArticleSearchWriteServiceImpl(SearchInitializer searchInitializer,
	                                     ArticleRepository articleRepository,
	                                     ArticleDetailRepository articleDetailRepository,
	                                     EsClient esClient, ObjectMapper objectMapper) {
		this.searchInitializer = searchInitializer;
		this.articleRepository = articleRepository;
		this.articleDetailRepository = articleDetailRepository;
		this.esClient = esClient;
		this.objectMapper = objectMapper;
	}

	@PostConstruct
	public void init() {
		try {
			searchInitializer.initIndices();
		} catch (Exception e) {
			log.error("fail to connect es, case {}", Throwables.getStackTraceAsString(e));
		}
	}

	@Override
	public void index(Long articleId) {
		Article article;
		try {
			article = articleRepository.findById(articleId);
		} catch (Exception e) {
			log.error("fail to find article by {}, case {}", articleId, Throwables.getStackTraceAsString(e));
			return;
		}

		index(article);
	}

	private void index(Article article) {
		Long articleId = article.getId();
		ArticleDetail articleDetail;
		try {
			articleDetail = articleDetailRepository.findByArticleId(articleId);
		} catch (Exception e) {
			log.error("fail to find article detail by {}, case {}", articleId, Throwables.getStackTraceAsString(e));
			return;
		}

		IndexArticle indexArticle = new IndexArticle(article, articleDetail);

		IndexDocRequest request = new IndexDocRequest();
		request.setIndex(searchInitializer.getArticleIndex());
		request.setType(searchInitializer.getArticleType());
		request.setId(articleId.toString());
		try {
			request.setDocument(objectMapper.writeValueAsString(indexArticle));
		} catch (JsonProcessingException e) {
			log.error("fail to form article by {}, case {}", indexArticle, Throwables.getStackTraceAsString(e));
			return;
		}

		try {
			esClient.index(request);
			log.info("index article[{}] success", articleId);
		} catch (ExecutionException | InterruptedException e) {
			log.error("fail to index article by {}, case {}", request, Throwables.getStackTraceAsString(e));
		}
	}

	@Override
	public void remove(Long articleId) {
		DocumentDeleteRequest request = new DocumentDeleteRequest();
		request.setIndex(searchInitializer.getArticleIndex());
		request.setType(searchInitializer.getArticleType());
		request.setId(articleId.toString());

		try {
			esClient.delete(request);
			log.info("remove article[{}] success", articleId);
		} catch (ExecutionException | InterruptedException e) {
			log.error("fail to index article by {}, case {}", request, Throwables.getStackTraceAsString(e));
		}
	}

	@Override
	public void fullDump() {
		Paging<Article> articlePaging;
		int pageNo = 1;
		ArticleCriteria criteria = new ArticleCriteria();
		criteria.setPageSize(20);
		criteria.setPageNo(pageNo);
		try {
			articlePaging = articleRepository.paging(criteria.toMap());
		} catch (Exception e) {
			log.error("fail to paging article by {}, case {}", criteria, Throwables.getStackTraceAsString(e));
			return;
		}

		while (!articlePaging.isEmpty()) {
			dump(articlePaging.getDatas());
			try {
				pageNo ++;
				criteria.setPageNo(pageNo);
				articlePaging = articleRepository.paging(criteria.toMap());
			} catch (Exception e) {
				log.error("fail to paging article by {}, case {}", criteria, Throwables.getStackTraceAsString(e));
				break;
			}
		}

	}

	@Override
	public void deltaDump(Integer deltaMin) {
		Paging<Article> articlePaging;
		int pageNo = 1;
		ArticleCriteria criteria = new ArticleCriteria();
		criteria.setPageSize(20);
		criteria.setPageNo(pageNo);

		Map<String, Object> dateMap = Maps.newHashMap();
		dateMap.put("publishAtStart", new DateTime().minusMinutes(deltaMin).toDate());
		dateMap.put("publishAtEnd", new Date());

		Map<String, Object> map = criteria.toMap();
		map.putAll(dateMap);
		try {
			articlePaging = articleRepository.paging(map);
		} catch (Exception e) {
			log.error("fail to paging article by {}, case {}", criteria, Throwables.getStackTraceAsString(e));
			return;
		}

		while (!articlePaging.isEmpty()) {
			dump(articlePaging.getDatas());
			try {
				pageNo ++;
				criteria.setPageNo(pageNo);
				map = criteria.toMap();
				map.putAll(dateMap);
				articlePaging = articleRepository.paging(map);
			} catch (Exception e) {
				log.error("fail to paging article by {}, case {}", criteria, Throwables.getStackTraceAsString(e));
				break;
			}
		}
	}

	private void dump(List<Article> articles) {
		for(Article article : articles) {
			if(Objects.equals(article.getStatus(), ArticleStatusEnum.PUBLIC.getValue())) {
				index(article);
			} else {
				remove(article.getId());
			}
		}
	}
}
