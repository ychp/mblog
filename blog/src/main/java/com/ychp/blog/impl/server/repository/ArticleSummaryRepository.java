package com.ychp.blog.impl.server.repository;

import com.google.common.collect.ImmutableMap;
import com.ychp.blog.model.Article;
import com.ychp.blog.model.ArticleSummary;
import com.ychp.mybatis.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author yingchengpeng
* @date: 2018/08/10
*/
@Repository
public class ArticleSummaryRepository extends BaseRepository<ArticleSummary, Long> {

	public ArticleSummary findByArticleId(Long articleId) {
		return getSqlSession().selectOne(sqlId("findByArticleId"), articleId);
	}

	public List<ArticleSummary> findByArticleIds(List<Long> articleIds) {
		return getSqlSession().selectList(sqlId("findByArticleIds"), articleIds);
	}

	public Boolean increasePopular(Long articleId) {
		return getSqlSession().update(sqlId("increasePopular"), articleId) == 1;
	}

	public Boolean increaseLike(Long articleId) {
		return getSqlSession().update(sqlId("increaseLike"), articleId) == 1;
	}

	public Boolean decreaseLike(Long articleId) {
		return getSqlSession().update(sqlId("decreaseLike"), articleId) == 1;
	}

	public Boolean updateValid(Long articleId, Boolean isValid) {
		return getSqlSession().update(sqlId("updateValid"),
				ImmutableMap.of("articleId", articleId, "isValid", isValid)) == 1;
	}

	public List<ArticleSummary> popular(Integer size) {
		return getSqlSession().selectList(sqlId("popular"), size);
	}
}