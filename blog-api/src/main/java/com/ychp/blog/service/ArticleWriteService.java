package com.ychp.blog.service;

import com.ychp.blog.bean.request.ArticleCreateRequest;
import com.ychp.blog.bean.request.ArticleUpdateRequest;

/**
* @author yingchengpeng
* @date: 2018/08/10
*/
public interface ArticleWriteService {

    /**
     * 创建
     * @param request 需要创建的数据
     * @return 主键
     */
    Long create(ArticleCreateRequest request);

    /**
     * 更新
     * @param request 需要更新的数据
     * @return 操作结果
     */
    Boolean update(ArticleUpdateRequest request);

    /**
     * 根据主键id删除数据
     * @param id 主键
     * @return 操作结果
     */
    Boolean delete(Long id);


    /**
     * 累计浏览次数
     * @param id 文章ID
     * @return 操作结果
     */
    Boolean increasePopular(Long id);

    /**
     * 累计点赞次数
     * @param id 文章ID
     * @return 操作结果
     */
    Boolean increaseLike(Long id);

    /**
     * 扣减点赞次数
     * @param id 文章ID
     * @return 操作结果
     */
    Boolean decreaseLike(Long id);

    /**
     * 更新类目名称
     * @param categoryId 类目id
     * @param categoryName 类目名称
     * @return 操作结果
     */
    Boolean updateCategoryName(Long categoryId, String categoryName);

    /**
     * 更新状态
     * @param id id
     * @param status 状态
     * @return 操作结果
     */
    Boolean updateStatus(Long id, Integer status);

    /**
     * 累计评价次数
     * @param id 文章ID
     * @return 操作结果
     */
    Boolean increaseComment(Long id);

    /**
     * 扣减评价次数
     * @param id 文章ID
     * @return 操作结果
     */
    Boolean decreaseComment(Long id);
}