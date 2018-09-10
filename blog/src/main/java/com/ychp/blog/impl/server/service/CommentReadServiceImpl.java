package com.ychp.blog.impl.server.service;

import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ychp.blog.bean.query.CommentCriteria;
import com.ychp.blog.bean.response.CommentWithChildrenInfo;
import com.ychp.blog.enums.CommentStatusEnum;
import com.ychp.blog.impl.server.repository.CommentRepository;
import com.ychp.blog.model.Comment;
import com.ychp.blog.service.CommentReadService;
import com.ychp.common.exception.ResponseException;
import com.ychp.common.model.paging.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/8/27
 */
@Slf4j
@Service
public class CommentReadServiceImpl implements CommentReadService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment findById(Long id) {
        try {
            return commentRepository.findById(id);
        } catch (Exception e){
            log.error("find comment fail, id = {}, error = {}", id, Throwables.getStackTraceAsString(e));
            throw new ResponseException("comment.find.fail");
        }
    }

    @Override
    public List<CommentWithChildrenInfo> list(Integer type, Long aimId) {
        try {
            List<CommentWithChildrenInfo> result = Lists.newArrayList();
            List<Comment> comments = commentRepository.findBy(type, aimId);

            List<Comment> oneLevel = Lists.newArrayList();

            Map<Long,List<Comment>> commentsMap = Maps.newHashMap();
            List<Comment> commentList;
            for(Comment comment : comments){
                if(comment.getPid() == null && comment.getLevel() == 1){
                    oneLevel.add(comment);
                    continue;
                }
                commentList = commentsMap.get(comment.getPid());
                if(commentList == null){
                    commentList = Lists.newArrayList();
                }
                commentList.add(comment);
                commentsMap.put(comment.getPid(), commentList);
            }

            CommentWithChildrenInfo commentWithChildrenInfo;
            for(Comment comment : oneLevel){
                commentWithChildrenInfo = new CommentWithChildrenInfo();
                commentWithChildrenInfo.setComment(comment);
                setChildren(commentWithChildrenInfo, commentsMap);
                result.add(commentWithChildrenInfo);
            }
            return result;
        } catch (Exception e){
            log.error("find comment fail, type = {}, aimId = {}, error = {}", type, aimId, Throwables.getStackTraceAsString(e));
            throw new ResponseException("comment.find.fail");
        }
    }

    @Override
    public Paging<CommentWithChildrenInfo> pagingCommentWithChildren(CommentCriteria criteria) {
        Paging<Comment> paging;
        try {
            paging = commentRepository.paging(criteria.toMap());
        } catch (Exception e){
            log.error("paging comment fail, params = {}, error = {}", criteria, Throwables.getStackTraceAsString(e));
            throw new ResponseException("comment.paging.fail");
        }
        if(paging.isEmpty()) {
            return Paging.empty();
        }
        try {
            List<Comment> oneLevel = paging.getDatas();
            List<Long> pids = oneLevel.stream().map(Comment::getId).collect(Collectors.toList());
            List<CommentWithChildrenInfo> result = Lists.newArrayList();
            List<Comment> comments = commentRepository.findByPids(pids);

            Map<Long,List<Comment>> commentsMap = Maps.newHashMap();
            List<Comment> commentList;
            for(Comment comment : comments){
                if(!Objects.equals(comment.getStatus(), CommentStatusEnum.SHOW.getValue())) {
                    continue;
                }
                commentList = commentsMap.get(comment.getPid());
                if(commentList == null){
                    commentList = Lists.newArrayList();
                }
                commentList.add(comment);
                commentsMap.put(comment.getPid(), commentList);
            }

            CommentWithChildrenInfo commentWithChildrenInfo;
            for(Comment comment : oneLevel){
                commentWithChildrenInfo = new CommentWithChildrenInfo();
                commentWithChildrenInfo.setComment(comment);
                setChildren(commentWithChildrenInfo, commentsMap);
                result.add(commentWithChildrenInfo);
            }
            return new Paging<>(paging.getTotal(), result);
        } catch (Exception e){
            log.error("find comment fail, criteria = {}, error = {}", criteria, Throwables.getStackTraceAsString(e));
            throw new ResponseException("comment.find.fail");
        }

    }

    @Override
    public Paging<Comment> paging(CommentCriteria criteria) {
        try {
            return commentRepository.paging(criteria.toMap());
        } catch (Exception e){
            log.error("paging comment fail, params = {}, error = {}", criteria, Throwables.getStackTraceAsString(e));
            throw new ResponseException("comment.paging.fail");
        }
    }

    private void setChildren(CommentWithChildrenInfo commentWithChildrenInfo, Map<Long, List<Comment>> commentMap) {
        List<Comment> children = commentMap.get(commentWithChildrenInfo.getComment().getId());
        if(children == null){
            return;
        }

        List<Long> pids = Lists.transform(children, Comment::getId);

        List<Comment> values = commentWithChildrenInfo.getChildren();
        if(values == null){
            values = Lists.newArrayList();
        }
        values.addAll(children);
        commentWithChildrenInfo.setChildren(children);
        while(true){
            if(!setChildren(pids, commentWithChildrenInfo, commentMap)){
                break;
            }
        }

    }

    private Boolean setChildren(List<Long> pids, CommentWithChildrenInfo commentWithChildrenInfo, Map<Long, List<Comment>> commentMap) {
        List<Comment> children;
        List<Long> childrenPids = Lists.newArrayList();
        List<Comment> values = commentWithChildrenInfo.getChildren();
        if (values == null) {
            values = Lists.newArrayList();
        }
        List<Long> items = Lists.newArrayList(pids);
        for(Long pid : items) {
            children = commentMap.get(pid);
            if (children == null) {
                continue;
            }
            values.addAll(children);
            childrenPids.addAll(children.stream().map(input -> {
                assert input != null;
                return input.getId();
            }).collect(Collectors.toList()));
            commentWithChildrenInfo.setChildren(values);
        }
        if(childrenPids.isEmpty()){
            sortByCreatedAt(commentWithChildrenInfo.getChildren());
            return false;
        }
        return setChildren(childrenPids, commentWithChildrenInfo, commentMap);
    }

    private void sortByCreatedAt(List<Comment> children) {
        if(children == null){
            return;
        }
        children.sort((o1, o2) -> (int) (o2.getCreatedAt().getTime() - o1.getCreatedAt().getTime()));
    }

}
