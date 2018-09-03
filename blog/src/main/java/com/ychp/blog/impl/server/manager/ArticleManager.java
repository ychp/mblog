package com.ychp.blog.impl.server.manager;

import com.ychp.blog.impl.server.repository.ArticleDetailRepository;
import com.ychp.blog.impl.server.repository.ArticleRepository;
import com.ychp.blog.impl.server.repository.ArticleSummaryRepository;
import com.ychp.blog.model.Article;
import com.ychp.blog.model.ArticleDetail;
import com.ychp.blog.model.ArticleSummary;
import com.ychp.common.exception.ResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
	private ArticleSummaryRepository articleSummaryRepository;

	@Transactional(rollbackFor = Exception.class)
	public Long create(Article article, ArticleDetail detail) {
		article.setDeleted(false);
		Boolean result = articleRepository.create(article);
		if (!result) {
			throw new ResponseException("article.create.fail");
		}

		detail.setArticleId(article.getId());
		result = articleDetailRepository.create(detail);
		if (!result) {
			throw new ResponseException("article.detail.create.fail");
		}

		ArticleSummary summary = ArticleSummary.empty();
		summary.setArticleId(article.getId());
		summary.setIsValid(article.getVisible() && article.getDeleted());
		result = articleSummaryRepository.create(summary);
		if (!result) {
			throw new ResponseException("article.summary.create.fail");
		}
		return article.getId();
	}

	@Transactional(rollbackFor = Exception.class)
	public Boolean update(Article article, ArticleDetail detail) {
		Boolean result = articleRepository.update(article);
		if (!result) {
			throw new ResponseException("article.update.fail");
		}

		detail.setArticleId(article.getId());
		result = articleDetailRepository.update(detail);
		if (!result) {
			throw new ResponseException("article.detail.create.fail");
		}

		Article newArticle = articleRepository.findById(article.getId());
		result = articleSummaryRepository.updateValid(article.getId(),
				newArticle.getVisible() && newArticle.getDeleted());
		if (!result) {
			throw new ResponseException("article.summary.refresh.fail");
		}
		return Boolean.TRUE;
	}

	@Transactional(rollbackFor = Exception.class)
	public Boolean delete(Long articleId) {
		Boolean result = articleRepository.delete(articleId);
		if (!result) {
			throw new ResponseException("article.delete.fail");
		}

		result = articleSummaryRepository.updateValid(articleId, false);
		if (!result) {
			throw new ResponseException("article.summary.refresh.fail");
		}
		return Boolean.TRUE;
	}

}
