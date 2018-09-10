package com.ychp.blog.service;

import com.ychp.blog.model.Comment;

/**
 * @author yingchengpeng
 * @date 2018-09-10
 */
public interface CommentWriteService {

    /**
     * 评论
     */
    Boolean create(Comment comment);

    /**
     * 显示评论
     */
    Comment updateStatus(Long id, Integer status);

}
