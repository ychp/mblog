package com.ychp.blog.impl.server.manager;

import com.ychp.blog.impl.server.repository.ArticleDetailRepository;
import com.ychp.blog.impl.server.repository.ArticleLabelRepository;
import com.ychp.blog.impl.server.repository.ArticleRepository;
import com.ychp.blog.impl.server.repository.ArticleSummaryRepository;
import com.ychp.blog.model.Article;
import com.ychp.blog.model.ArticleDetail;
import com.ychp.blog.model.ArticleLabel;
import com.ychp.blog.model.ArticleSummary;
import com.ychp.common.exception.ResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yingchengpeng
 * @date 2018/8/10
 */
@Component
public class ArticleManager {

	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private ArticleDetailRepository articleDetailRepository;
	@Autowired
	private ArticleLabelRepository articleLabelRepository;
	@Autowired
	private ArticleSummaryRepository articleSummaryRepository;

	@Transactional(rollbackFor = Exception.class)
	public Long create(Article article, ArticleDetail detail,
	                   List<ArticleLabel> labels) {
		Boolean result = articleRepository.create(article);
		if (!result) {
			throw new ResponseException("article.create.fail");
		}

		detail.setArticleId(article.getId());
		result = articleDetailRepository.create(detail);
		if (!result) {
			throw new ResponseException("article.detail.create.fail");
		}

		for(ArticleLabel label : labels) {
			label.setArticleId(article.getId());
			result = articleLabelRepository.create(label);
			if (!result) {
				throw new ResponseException("article.label.create.fail");
			}
		}

		ArticleSummary summary = ArticleSummary.empty();
		summary.setArticleId(article.getId());
		result = articleSummaryRepository.create(summary);
		if (!result) {
			throw new ResponseException("article.summary.create.fail");
		}
		return article.getId();
	}

	@Transactional(rollbackFor = Exception.class)
	public Boolean update(Article article, ArticleDetail detail, List<ArticleLabel> labels) {
		Boolean result = articleRepository.update(article);
		if (!result) {
			throw new ResponseException("article.update.fail");
		}

		detail.setArticleId(article.getId());
		result = articleDetailRepository.update(detail);
		if (!result) {
			throw new ResponseException("article.detail.create.fail");
		}

		for(ArticleLabel label : labels) {
			label.setArticleId(article.getId());
			result = articleLabelRepository.update(label);
			if (!result) {
				throw new ResponseException("article.label.create.fail");
			}
		}
		return Boolean.TRUE;
	}

}
