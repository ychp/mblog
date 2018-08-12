package com.ychp.blog.impl.server.service;

import com.ychp.blog.bean.request.ArticleCreateRequest;
import com.ychp.blog.bean.request.ArticleUpdateRequest;
import com.ychp.blog.impl.server.manager.ArticleManager;
import com.ychp.blog.impl.server.repository.ArticleLabelRepository;
import com.ychp.blog.impl.server.repository.ArticleRepository;
import com.ychp.blog.impl.server.repository.ArticleSummaryRepository;
import com.ychp.blog.model.ArticleLabel;
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
	private ArticleLabelRepository articleLabelRepository;
	@Autowired
	private ArticleSummaryRepository articleSummaryRepository;

	@Autowired
	private ArticleManager articleManager;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Long create(ArticleCreateRequest request) {
		try {
			return articleManager.create(request.getArticle(), request.getDetail(), request.getLabels());
		} catch (Exception e) {
			throw new ResponseException("article.summary.find.fail", e.getMessage(), e.getCause());
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean update(ArticleUpdateRequest request) {
		try {
			List<ArticleLabel> existLabels = articleLabelRepository.findByArticleId(request.getArticle().getId());
			List<Long> labelIds = existLabels.stream().map(ArticleLabel::getLabelId).collect(Collectors.toList());
			List<ArticleLabel> labels = request.getLabels().stream()
					.filter(label -> !labelIds.contains(label.getId())).collect(Collectors.toList());

			return articleManager.update(request.getArticle(), request.getDetail(), labels);
		} catch (Exception e) {
			throw new ResponseException("article.summary.find.fail", e.getMessage(), e.getCause());
		}
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
}
