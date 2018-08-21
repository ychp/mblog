package com.ychp.blog.search;

import com.ychp.blog.search.bean.query.ArticleSearchCriteria;
import com.ychp.blog.search.bean.response.SearchWithAggsVO;

/**
 * @author yingchengpeng
 * @date 2018/8/19
 */
public interface ArticleSearchReadService {

	SearchWithAggsVO search(ArticleSearchCriteria criteria);

}
