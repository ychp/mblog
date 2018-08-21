package com.ychp.blog.impl.server.repository;

import com.google.common.collect.ImmutableMap;
import com.ychp.blog.model.Article;
import com.ychp.common.model.paging.Paging;
import com.ychp.mybatis.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

	public Paging<String> pagingPublishDate(Map<String, Object> params) {
		Long total = getSqlSession().selectOne(sqlId("countPublishDate"), params);
		if (total <= 0){
			return new Paging<>(0L, Collections.<String>emptyList());
		}

		List<String> datas = getSqlSession().selectList(sqlId("pagingPublishDate"), params);
		return new Paging<>(total, datas);
	}
}