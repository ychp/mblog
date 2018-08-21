package com.ychp.blog.impl.server.service;

import com.google.common.collect.Lists;
import com.ychp.blog.bean.query.ArticleCriteria;
import com.ychp.blog.bean.response.ArticleBaseInfoVO;
import com.ychp.blog.bean.response.ArticleDetailVO;
import com.ychp.blog.impl.server.repository.ArticleDetailRepository;
import com.ychp.blog.impl.server.repository.ArticleLabelRepository;
import com.ychp.blog.impl.server.repository.ArticleRepository;
import com.ychp.blog.impl.server.repository.ArticleSummaryRepository;
import com.ychp.blog.model.Article;
import com.ychp.blog.model.ArticleSummary;
import com.ychp.blog.service.ArticleReadService;
import com.ychp.common.exception.ResponseException;
import com.ychp.common.model.paging.Paging;
import com.ychp.common.model.paging.PagingCriteria;
import com.ychp.markdown.wrapper.MarkdownWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yingchengpeng
 * @date 2018/8/10
 */
@Service
public class ArticleReadServiceImpl implements ArticleReadService {

	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private ArticleDetailRepository articleDetailRepository;
	@Autowired
	private ArticleLabelRepository articleLabelRepository;
	@Autowired
	private ArticleSummaryRepository articleSummaryRepository;

	@Override
	public ArticleDetailVO findDetailById(Long id) {
		ArticleDetailVO detailVO = findEditById(id);
		if(detailVO.getDetail().getIsMarkdown()) {
			detailVO.getDetail().setContent(MarkdownWrapper.parse(detailVO.getDetail().getContent()));
		}
		return detailVO;
	}

	@Override
	public ArticleDetailVO findEditById(Long id) {
		ArticleDetailVO detailVO = new ArticleDetailVO();
		Article article;
		try {
			article = articleRepository.findById(id);
		} catch (Exception e) {
			throw new ResponseException("article.find.fail", e.getMessage(), e.getCause());
		}

		if(article == null) {
			throw new ResponseException("article.not.exist");
		}

		detailVO.setArticle(article);

		try {
			detailVO.setDetail(articleDetailRepository.findByArticleId(id));
		} catch (Exception e) {
			throw new ResponseException("article.detail.find.fail", e.getMessage(), e.getCause());
		}

		try {
			detailVO.setLabels(articleLabelRepository.findByArticleId(id));
		} catch (Exception e) {
			throw new ResponseException("article.labels.find.fail", e.getMessage(), e.getCause());
		}

		try {
			detailVO.setSummary(articleSummaryRepository.findByArticleId(id));
		} catch (Exception e) {
			throw new ResponseException("article.summary.find.fail", e.getMessage(), e.getCause());
		}
		return detailVO;
	}

	@Override
	public Paging<ArticleBaseInfoVO> paging(ArticleCriteria criteria) {
		Paging<Article> articlePaging;
		try {
			articlePaging = articleRepository.paging(criteria.toMap());
		} catch (Exception e) {
			throw new ResponseException("article.paging.fail", e.getMessage(), e.getCause());
		}

		if(articlePaging.isEmpty()) {
			return Paging.empty();
		}

		List<Article> articles = articlePaging.getDatas();

		List<Long> articleIds = articles.stream().map(Article::getId).collect(Collectors.toList());

		List<ArticleSummary> summaries;

		try {
			summaries = articleSummaryRepository.findByArticleIds(articleIds);
		} catch (Exception e) {
			throw new ResponseException("article.summary.find.fail", e.getMessage(), e.getCause());
		}

		Map<Long, ArticleSummary> summaryByArticleId = summaries.stream()
				.collect(Collectors.toMap(ArticleSummary::getArticleId, articleSummary -> articleSummary));

		List<ArticleBaseInfoVO> result = Lists.newArrayListWithCapacity(articles.size());

		for (Article article : articles) {
			result.add(new ArticleBaseInfoVO(article, summaryByArticleId.get(article.getId())));
		}
		return new Paging<>(articlePaging.getTotal(), result);
	}

	@Override
	public Paging<String> pagingPublishDates(PagingCriteria criteria) {
		try {
			return articleRepository.pagingPublishDate(criteria.toMap());
		} catch (Exception e) {
			throw new ResponseException("article.publishAt.paging.fail", e.getMessage(), e.getCause());
		}
	}

	@Override
	public List<ArticleBaseInfoVO> popular(Integer size) {
		List<ArticleSummary> summaries;
		try {
			summaries = articleSummaryRepository.popular(size);
		} catch (Exception e) {
			throw new ResponseException("article.summary.popular.fail", e.getMessage(), e.getCause());
		}

		if(summaries.isEmpty()) {
			return Collections.emptyList();
		}

		List<Long> articleIds = summaries.stream().map(ArticleSummary::getArticleId).collect(Collectors.toList());

		List<Article> articles;

		try {
			articles = articleRepository.findByIds(articleIds);
		} catch (Exception e) {
			throw new ResponseException("article.find.fail", e.getMessage(), e.getCause());
		}

		Map<Long, ArticleSummary> summaryByArticleId = summaries.stream()
				.collect(Collectors.toMap(ArticleSummary::getArticleId, articleSummary -> articleSummary));

		List<ArticleBaseInfoVO> result = Lists.newArrayListWithCapacity(articles.size());

		for (Article article : articles) {
			result.add(new ArticleBaseInfoVO(article, summaryByArticleId.get(article.getId())));
		}
		return result;
	}
}
