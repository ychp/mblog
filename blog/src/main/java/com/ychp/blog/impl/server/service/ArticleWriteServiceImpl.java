package com.ychp.blog.impl.server.service;

import com.google.common.base.Throwables;
import com.ychp.blog.bean.request.ArticleCreateRequest;
import com.ychp.blog.bean.request.ArticleUpdateRequest;
import com.ychp.blog.enums.ArticleStatusEnum;
import com.ychp.blog.impl.server.converter.ArticleConverter;
import com.ychp.blog.impl.server.manager.ArticleManager;
import com.ychp.blog.impl.server.repository.ArticleRepository;
import com.ychp.blog.impl.server.repository.ArticleSummaryRepository;
import com.ychp.blog.impl.server.repository.CategoryRepository;
import com.ychp.blog.model.Article;
import com.ychp.blog.model.ArticleDetail;
import com.ychp.blog.model.Category;
import com.ychp.blog.service.ArticleWriteService;
import com.ychp.common.exception.ResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yingchengpeng
 * @date 2018/8/10
 */
@Slf4j
@Service
public class ArticleWriteServiceImpl implements ArticleWriteService {

	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private ArticleSummaryRepository articleSummaryRepository;
	@Autowired
	private ArticleManager articleManager;
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Long create(ArticleCreateRequest request) {
		try {
			Article article = request.getArticle();
			Long categoryId = article.getCategoryId();
			Category category = categoryRepository.findById(categoryId);
			article.setCategoryName(category.getName());
			ArticleDetail detail = request.getDetail();
			ArticleConverter.parse(article, detail);
			if(article.getStatus() == null) {
				article.setStatus(ArticleStatusEnum.PRIVATE.getValue());
			}
			return articleManager.create(article, detail);
		} catch (ResponseException e) {
			log.error("fail to create article = {}, case {}", request, Throwables.getStackTraceAsString(e));
			throw e;
		} catch (Exception e) {
			log.error("fail to create article = {}, case {}", request, Throwables.getStackTraceAsString(e));
			throw new ResponseException("article.create.fail", e.getMessage(), e.getCause());
		}
	}

	@Override
	public Boolean update(ArticleUpdateRequest request) {
		try {
			return articleManager.update(request.getArticle(), request.getDetail());
		} catch (ResponseException e) {
			log.error("fail to update article = {}, case {}", request, Throwables.getStackTraceAsString(e));
			throw e;
		} catch (Exception e) {
			log.error("fail to update article = {}, case {}", request, Throwables.getStackTraceAsString(e));
			throw new ResponseException("article.update.fail", e.getMessage(), e.getCause());
		}
	}

	@Override
	public Boolean delete(Long id) {
		try {
			return articleManager.delete(id);
		} catch (Exception e) {
			log.error("fail to delete article = {}, case {}", id, Throwables.getStackTraceAsString(e));
			throw new ResponseException("article.delete.fail", e.getMessage(), e.getCause());
		}
	}

	@Override
	public Boolean increasePopular(Long id) {
		try {
			return articleSummaryRepository.increasePopular(id);
		} catch (Exception e) {
			throw new ResponseException("article.summary.increase.popular.fail", e.getMessage(), e.getCause());
		}
	}

	@Override
	public Boolean increaseLike(Long id) {
		try {
			return articleSummaryRepository.increaseLike(id);
		} catch (Exception e) {
			throw new ResponseException("article.summary.increase.like.fail", e.getMessage(), e.getCause());
		}
	}

	@Override
	public Boolean decreaseLike(Long id) {
		try {
			return articleSummaryRepository.decreaseLike(id);
		} catch (Exception e) {
			throw new ResponseException("article.summary.decrease.like.fail", e.getMessage(), e.getCause());
		}
	}

	@Override
	public Boolean updateCategoryName(Long categoryId, String categoryName) {
		try {
			return articleRepository.updateCategoryName(categoryId, categoryName);
		} catch (Exception e) {
			throw new ResponseException("article.update.category.fail", e.getMessage(), e.getCause());
		}
	}

	@Override
	public Boolean updateStatus(Long id, Integer status) {
		try {
			Article article = new Article();
			article.setId(id);
			article.setStatus(status);
			return articleRepository.update(article);
		} catch (Exception e) {
			throw new ResponseException("article.update.status.fail", e.getMessage(), e.getCause());
		}
	}

	@Override
	public Boolean increaseComment(Long id) {
		try {
			return articleSummaryRepository.increaseComment(id);
		} catch (Exception e) {
			throw new ResponseException("article.summary.increase.comment.fail", e.getMessage(), e.getCause());
		}
	}

	@Override
	public Boolean decreaseComment(Long id) {
		try {
			return articleSummaryRepository.decreaseComment(id);
		} catch (Exception e) {
			throw new ResponseException("article.summary.decrease.comment.fail", e.getMessage(), e.getCause());
		}
	}
}
