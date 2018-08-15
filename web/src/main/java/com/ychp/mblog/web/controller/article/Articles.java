package com.ychp.mblog.web.controller.article;

import com.ychp.async.publisher.AsyncPublisher;
import com.ychp.blog.bean.query.ArticleCriteria;
import com.ychp.blog.bean.response.ArticleBaseInfoVO;
import com.ychp.blog.bean.response.ArticleDetailVO;
import com.ychp.blog.enums.LikeLogTypeEnum;
import com.ychp.blog.model.LikeLog;
import com.ychp.blog.service.ArticleReadService;
import com.ychp.blog.service.LikeLogReadService;
import com.ychp.common.model.paging.Paging;
import com.ychp.ip.component.IPServer;
import com.ychp.mblog.web.async.article.ArticleVisitEvent;
import com.ychp.cache.annontation.DataCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yingchengpeng
 * @date 2018-08-10
 */
@Api(description = "文章")
@RestController
@RequestMapping("/api/article")
public class Articles {

    @Autowired
    private ArticleReadService articleReadService;

    @Autowired
    private AsyncPublisher publisher;

    @Autowired
    private IPServer ipServer;

    @Autowired
    private LikeLogReadService likeLogReadService;

    @ApiOperation("文章详情接口")
    @GetMapping("{id}/detail")
    @DataCache("article:{{id}}")
    public ArticleDetailVO detail(@ApiParam(example = "1") @PathVariable Long id, HttpServletRequest request) {
        ArticleDetailVO detailVO = articleReadService.findDetailById(id);
        String ip = ipServer.getIp(request);
        LikeLog likeLog = likeLogReadService.findByAimAndIp(id, LikeLogTypeEnum.ARTICLE.getValue(), ip);
        detailVO.setHasLiked(likeLog != null);
        publisher.post(new ArticleVisitEvent(id));
        return detailVO;
    }

    @ApiOperation("文章分页接口")
    @GetMapping("paging")
    public Paging<ArticleBaseInfoVO> paging(ArticleCriteria criteria) {
        criteria.setVisible(true);
        return articleReadService.paging(criteria);
    }

}
