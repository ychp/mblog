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
     * 删除对应文章中对应的标签
     * @param id 文章ID
     * @param labelId 标签Id
     * @return 操作结果
     */
    Boolean deleteLabel(Long id, Long labelId);

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
}