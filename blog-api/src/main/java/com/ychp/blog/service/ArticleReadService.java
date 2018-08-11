package com.ychp.blog.service;

import com.ychp.blog.bean.response.ArticleBaseInfoVO;
import com.ychp.blog.bean.response.ArticleDetailVO;
import com.ychp.blog.bean.query.ArticleCriteria;
import com.ychp.common.model.paging.Paging;

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
     * 根据条件获取分页
     *
     * @param criteria 条件
     * @return 分页结果
     */
    Paging<ArticleBaseInfoVO> paging(ArticleCriteria criteria);

}