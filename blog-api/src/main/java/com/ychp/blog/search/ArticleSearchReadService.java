package com.ychp.blog.search;

import com.ychp.blog.search.bean.query.ArticleSearchCriteria;
import com.ychp.blog.search.bean.response.SearchWithAggsVO;

/**
 * @author yingchengpeng
 * @date 2018/8/19
 */
public interface ArticleSearchReadService {

	/**
	 * 搜索
	 * @param criteria 条件
	 * @return 搜索结果
	 */
	SearchWithAggsVO search(ArticleSearchCriteria criteria);

}
