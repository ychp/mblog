package com.ychp.blog.impl.server.repository;

import com.ychp.blog.model.ArticleDetail;
import com.ychp.mybatis.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
* @author yingchengpeng
* @date 2018/08/10
*/
@Repository
public class ArticleDetailRepository extends BaseRepository<ArticleDetail, Long> {

	public ArticleDetail findByArticleId(Long articleId) {
		return getSqlSession().selectOne(sqlId("findByArticleId"), articleId);
	}
}