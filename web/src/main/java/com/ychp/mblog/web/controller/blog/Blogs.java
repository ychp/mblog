package com.ychp.mblog.web.controller.blog;

import com.ychp.blog.bean.query.ArticleCriteria;
import com.ychp.blog.bean.response.ArticleBaseInfoVO;
import com.ychp.blog.bean.response.ArticleDetailVO;
import com.ychp.blog.service.ArticleReadService;
import com.ychp.common.model.paging.Paging;
import com.ychp.mblog.web.async.blog.BlogVisitAsync;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yingchengpeng
 * @date 2018-08-10
 */
@Api(description = "文章")
@RestController
@RequestMapping("/api/blog")
public class Blogs {

    @Autowired
    private ArticleReadService articleReadService;

    @Autowired
    private BlogVisitAsync blogVisitAsync;
    @ApiOperation("文章详情接口")
    @GetMapping("{id}/detail")
    public ArticleDetailVO detail(@ApiParam(example = "1") @PathVariable Long id) {
        ArticleDetailVO detailVO = articleReadService.findDetailById(id);
        blogVisitAsync.visit(id);
        return detailVO;
    }

    @ApiOperation("文章分页接口")
    @GetMapping("paging")
    public Paging<ArticleBaseInfoVO> paging(ArticleCriteria criteria) {
        return articleReadService.paging(criteria);
    }

}
