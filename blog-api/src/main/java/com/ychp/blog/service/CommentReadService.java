package com.ychp.blog.service;

import com.ychp.blog.bean.query.CommentCriteria;
import com.ychp.blog.bean.response.CommentWithChildrenInfo;
import com.ychp.blog.model.Comment;
import com.ychp.common.model.paging.Paging;

import java.util.List;

/**
 * @author yingchengpeng
 * @date 2018-09-10
 */
public interface CommentReadService {

    /**
     * 根据id获取评价
     * @param id 评价id
     * @return 评价
     */
    Comment findById(Long id);

    /**
     * 获取一个目标下所有评价
     * @param type 类型
     * @param aimId 目标对象id
     * @return 评价数据
     */
    List<CommentWithChildrenInfo> list(Integer type, Long aimId);

    /**
     * 分页获取一个目标下评论
     * @param criteria 条件
     * @return 评价数据
     */
    Paging<CommentWithChildrenInfo> pagingCommentWithChildren(CommentCriteria criteria);

    /**
     * 分页获取一个评论下的子节点
     * @param pid 父节点id
     * @return 评价数据
     */
    List<Comment> findByPid(Long pid);

    /**
     * 分页获取一级评价
     * @param criteria 条件
     * @return 评价数据
     */
    Paging<Comment> paging(CommentCriteria criteria);
}
