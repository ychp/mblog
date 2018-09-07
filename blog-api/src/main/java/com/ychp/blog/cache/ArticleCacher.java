package com.ychp.blog.cache;

import com.ychp.blog.bean.response.ArticleDetailVO;
import com.ychp.blog.service.ArticleReadService;
import com.ychp.cache.annontation.DataCache;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yingchengpeng
 * @date 2018-09-07
 */
public class ArticleCacher {

    @Autowired
    private ArticleReadService articleReadService;

    @DataCache("article:{{id}}")
    public ArticleDetailVO findDetail(Long id) {
        return articleReadService.findDetailById(id);
    }
}
