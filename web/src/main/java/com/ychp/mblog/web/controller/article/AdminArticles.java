package com.ychp.mblog.web.controller.article;

import com.ychp.blog.bean.query.ArticleCriteria;
import com.ychp.blog.bean.request.ArticleCreateRequest;
import com.ychp.blog.bean.request.ArticleUpdateRequest;
import com.ychp.blog.bean.response.ArticleBaseInfoVO;
import com.ychp.blog.bean.response.ArticleDetailVO;
import com.ychp.blog.service.ArticleReadService;
import com.ychp.blog.service.ArticleWriteService;
import com.ychp.cache.annontation.DataInvalidCache;
import com.ychp.common.model.SkyUser;
import com.ychp.common.model.paging.Paging;
import com.ychp.common.util.SessionContextUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yingchengpeng
 * @date 2018-08-10
 */
@Api(description = "文章")
@RestController
@RequestMapping("/api/admin/article")
public class AdminArticles {

    @Autowired
    private ArticleReadService articleReadService;

    @Autowired
    private ArticleWriteService articleWriteService;

    @ApiOperation(value = "文章创建接口", httpMethod = "POST")
    @PostMapping
    public Long create(@RequestBody ArticleCreateRequest request) {
        SkyUser user = SessionContextUtils.currentUser();
        request.getArticle().setUserId(user.getId());
        request.getArticle().setAuthor(user.getNickName());
        return articleWriteService.create(request);
    }

    @ApiOperation("文章详情接口")
    @GetMapping("{id}/for-edit")
    public ArticleDetailVO detail(@ApiParam(example = "1") @PathVariable Long id) {
        return articleReadService.findEditById(id);
    }

    @ApiOperation(value = "文章编辑接口", httpMethod = "PUT")
    @PutMapping
    @DataInvalidCache("article:{{request.article.id}}")
    public Boolean update(@RequestBody ArticleUpdateRequest request) {
        return articleWriteService.update(request);
    }

    @ApiOperation("文章分页接口")
    @GetMapping("paging")
    public Paging<ArticleBaseInfoVO> paging(ArticleCriteria criteria) {
        return articleReadService.paging(criteria);
    }

    @ApiOperation(value = "删除文章", httpMethod = "DELETE")
    @DeleteMapping
    @DataInvalidCache("article:{{id}}")
    public Boolean delete(@ApiParam(example = "1") @RequestParam Long id) {
        return articleWriteService.delete(id);
    }

}
