package com.ychp.mblog.web.controller.article;

import com.ychp.blog.bean.query.ArticleCriteria;
import com.ychp.blog.bean.response.ArticleBaseInfoVO;
import com.ychp.blog.service.ArticleReadService;
import com.ychp.common.model.paging.Paging;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yingchengpeng
 * @date 2018-08-10
 */
@Api(description = "文章管理-用于管理")
@RestController
@RequestMapping("/api/admin/article")
public class AdminArticles {

    @Autowired
    private ArticleReadService articleReadService;

    @ApiOperation("文章分页接口")
    @GetMapping("paging")
    public Paging<ArticleBaseInfoVO> paging(ArticleCriteria criteria) {
        return articleReadService.paging(criteria);
    }

}
