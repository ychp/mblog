package com.ychp.blog.impl.server.repository;

import com.google.common.collect.ImmutableMap;
import com.ychp.blog.model.ArticleDetail;
import com.ychp.blog.model.ArticleLabel;
import com.ychp.mybatis.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author yingchengpeng
* @date: 2018/08/10
*/
@Repository
public class ArticleLabelRepository extends BaseRepository<ArticleLabel, Long> {

	public List<ArticleLabel> findByArticleId(Long articleId) {
		return getSqlSession().selectList(sqlId("findByArticleId"), articleId);
	}

	public ArticleLabel findByArticleIdAndLabelId(Long articleId, Long labelId) {
		return getSqlSession().selectOne(sqlId("findByArticleIdAndLabelId"),
				ImmutableMap.of("articleId", articleId, "labelId", labelId));
	}
}