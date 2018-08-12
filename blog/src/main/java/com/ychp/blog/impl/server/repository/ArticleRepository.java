package com.ychp.blog.impl.server.repository;

import com.google.common.collect.ImmutableMap;
import com.ychp.blog.model.Article;
import com.ychp.mybatis.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
* @author yingchengpeng
* @date: 2018/08/10
*/
@Repository
public class ArticleRepository extends BaseRepository<Article, Long> {

	public Boolean updateCategoryName(Long categoryId, String categoryName) {
		return getSqlSession().update(sqlId("updateCategoryName"),
				ImmutableMap.of("categoryId", categoryId, "categoryName", categoryName)) >= 0;
	}
}