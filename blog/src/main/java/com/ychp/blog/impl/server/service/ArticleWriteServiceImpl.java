package com.ychp.blog.impl.server.service;

import com.ychp.blog.bean.request.ArticleCreateRequest;
import com.ychp.blog.bean.request.ArticleUpdateRequest;
import com.ychp.blog.impl.server.repository.ArticleDetailRepository;
import com.ychp.blog.impl.server.repository.ArticleLabelRepository;
import com.ychp.blog.impl.server.repository.ArticleRepository;
import com.ychp.blog.impl.server.repository.ArticleSummaryRepository;
import com.ychp.blog.model.Article;
import com.ychp.blog.model.ArticleDetail;
import com.ychp.blog.model.ArticleLabel;
import com.ychp.blog.model.ArticleSummary;
import com.ychp.blog.service.ArticleWriteService;
import com.ychp.common.exception.ResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yingchengpeng
 * @date 2018/8/10
 */
@Service
public class ArticleWriteServiceImpl implements ArticleWriteService {

	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private ArticleDetailRepository articleDetailRepository;
	@Autowired
	private ArticleLabelRepository articleLabelRepository;
	@Autowired
	private ArticleSummaryRepository articleSummaryRepository;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Long create(ArticleCreateRequest request) {
		Article article = request.getArticle();
		try {
			Boolean result = articleRepository.create(article);
			if (!result) {
				throw new ResponseException("article.create.fail");
			}

			ArticleDetail detail = request.getDetail();
			detail.setArticleId(article.getId());
			result = articleDetailRepository.create(detail);
			if (!result) {
				throw new ResponseException("article.detail.create.fail");
			}

			List<ArticleLabel> labels = request.getLabels();
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
		} catch (ResponseException e) {
			throw e;
		} catch (Exception e) {
			throw new ResponseException("article.summary.find.fail", e.getMessage(), e.getCause());
		}
		return article.getId();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean update(ArticleUpdateRequest request) {
		Article article = request.getArticle();
		try {
			Boolean result = articleRepository.update(article);
			if (!result) {
				throw new ResponseException("article.update.fail");
			}

			ArticleDetail detail = request.getDetail();
			detail.setArticleId(article.getId());
			result = articleDetailRepository.update(detail);
			if (!result) {
				throw new ResponseException("article.detail.create.fail");
			}

			List<ArticleLabel> existLabels = articleLabelRepository.findByArticleId(article.getId());
			List<Long> labelIds = existLabels.stream().map(ArticleLabel::getLabelId).collect(Collectors.toList());
			List<ArticleLabel> labels = request.getLabels();
			for(ArticleLabel label : labels) {
				if(labelIds.contains(label.getId())) {
					continue;
				}
				label.setArticleId(article.getId());
				result = articleLabelRepository.update(label);
				if (!result) {
					throw new ResponseException("article.label.create.fail");
				}
			}

		} catch (ResponseException e) {
			throw e;
		} catch (Exception e) {
			throw new ResponseException("article.summary.find.fail", e.getMessage(), e.getCause());
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean delete(Long id) {
		try {
			return articleRepository.delete(id);
		} catch (Exception e) {
			throw new ResponseException("article.summary.find.fail", e.getMessage(), e.getCause());
		}
	}

	@Override
	public Boolean deleteLabel(Long id, Long labelId) {
		try {
			ArticleLabel label = articleLabelRepository.findByArticleIdAndLabelId(id, labelId);
			if(label == null) {
				throw new ResponseException("article.label.not.exist");
			}
			return articleLabelRepository.delete(label.getId());
		} catch (ResponseException e) {
			throw e;
		} catch (Exception e) {
			throw new ResponseException("article.summary.find.fail", e.getMessage(), e.getCause());
		}
	}
}
