package com.ychp.blog.service;

import com.ychp.blog.bean.response.ArticleBaseInfoVO;
import com.ychp.blog.bean.response.ArticleDetailVO;
import com.ychp.blog.bean.query.ArticleCriteria;
import com.ychp.blog.model.Article;
import com.ychp.blog.model.ArticleSummary;
import com.ychp.common.model.paging.Paging;
import com.ychp.common.model.paging.PagingCriteria;

import java.util.List;

/**
* @author yingchengpeng
* @date 2018/08/10
*/
public interface ArticleReadService {

    /**
     * 根据id查询详情数据
     *
     * @param id 主键id
     * @return 查询结果
     */
    ArticleDetailVO findDetailById(Long id);

    /**
     * 根据id查询统计数据
     *
     * @param id 主键id
     * @return 查询结果
     */
    ArticleSummary findSummaryById(Long id);

    /**
     * 根据id查询需要编辑的详情数据
     *
     * @param id 主键id
     * @return 查询结果
     */
    ArticleDetailVO findEditById(Long id);

    /**
     * 根据条件获取分页
     *
     * @param criteria 条件
     * @return 分页结果
     */
    Paging<ArticleBaseInfoVO> paging(ArticleCriteria criteria);

    /**
     * 分页获取日期分页
     * @param criteria 分页信息
     * @return 日期数据
     */
    Paging<String> pagingPublishDates(PagingCriteria criteria);

    /**
     * 获取浏览数最高的几篇文章
     *
     * @param size 数量
     * @return 分页结果
     */
    List<ArticleBaseInfoVO> popular(Integer size);

    /**
     * 根据id集合获取文章
     * @param ids id集合
     * @return 文章集合
     */
    List<Article> findByIds(List<Long> ids);

    /**
     * 根据id获取文章
     * @param id id
     * @return 文章集合
     */
    Article findById(Long id);

}