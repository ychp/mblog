package com.ychp.blog.service;

import com.ychp.blog.model.LikeLog;

/**
* @author yingchengpeng
* @date: 2018/08/11
*/
public interface LikeLogWriteService {

    /**
     * 创建
     * @param likeLog 需要创建的数据
     * @return 主键
     */
    Long create(LikeLog likeLog);

    /**
     * 根据主键id删除数据
     * @param id 主键
     * @return 操作结果
     */
    Boolean delete(Long id);
}