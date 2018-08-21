package com.ychp.blog.service;

import com.ychp.blog.bean.response.ArticleBaseInfoVO;
import com.ychp.blog.bean.response.ArticleDetailVO;
import com.ychp.blog.bean.query.ArticleCriteria;
import com.ychp.common.model.paging.Paging;
import com.ychp.common.model.paging.PagingCriteria;

/**
* @author yingchengpeng
* @date: 2018/08/10
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

}