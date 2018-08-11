package com.ychp.blog.impl.server.repository;

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
}