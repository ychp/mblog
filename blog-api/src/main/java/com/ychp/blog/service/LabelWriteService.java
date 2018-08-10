package com.ychp.blog.service;

import com.ychp.blog.model.Label;

/**
* @author yingchengpeng
* @date: 2018/08/10
*/
public interface LabelWriteService {

    /**
     * 创建
     * @param label 需要创建的数据
     * @return 主键
     */
    Long create(Label label);

    /**
     * 更新
     * @param label 需要更新的数据
     * @return 操作结果
     */
    Boolean update(Label label);

    /**
     * 根据主键id删除数据
     * @param id 主键
     * @return 操作结果
     */
    Boolean delete(Long id);
}