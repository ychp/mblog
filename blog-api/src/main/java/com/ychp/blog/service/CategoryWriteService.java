package com.ychp.blog.service;

import com.ychp.blog.model.Category;

/**
* @author yingchengpeng
* @date: 2018/08/10
*/
public interface CategoryWriteService {

    /**
     * 创建
     * @param category 需要创建的数据
     * @return 主键
     */
    Long create(Category category);

    /**
     * 更新
     * @param category 需要更新的数据
     * @return 操作结果
     */
    Boolean update(Category category);

    /**
     * 根据主键id删除数据
     * @param id 主键
     * @return 操作结果
     */
    Boolean delete(Long id);
}