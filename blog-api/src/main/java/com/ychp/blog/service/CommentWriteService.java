package com.ychp.blog.service;

import com.ychp.blog.model.Comment;

/**
 * @author yingchengpeng
 * @date 2018-09-10
 */
public interface CommentWriteService {

    /**
     * 评论
     * @param comment 评论数据
     * @return 创建结果
     */
    Boolean create(Comment comment);

    /**
     * 显示评论
     * @param id 评论ID
     * @param status 状态
     * @return 评价
     */
    Comment updateStatus(Long id, Integer status);

}
