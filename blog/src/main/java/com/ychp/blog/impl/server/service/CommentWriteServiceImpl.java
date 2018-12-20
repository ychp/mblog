package com.ychp.blog.impl.server.service;

import com.google.common.base.Throwables;
import com.ychp.blog.impl.server.repository.CommentRepository;
import com.ychp.blog.model.Comment;
import com.ychp.blog.service.CommentWriteService;
import com.ychp.common.exception.ResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Desc:
 * @author yingchengpeng
 * @date 2016/08/27
 */
@Slf4j
@Service
public class CommentWriteServiceImpl implements CommentWriteService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Boolean create(Comment comment) {
        try {
            checkArgument(comment != null, "comment.not.empty");
            return commentRepository.create(comment);
        }catch (IllegalArgumentException e){
            log.error("check comment argument fail, comment={}, error={}", comment, Throwables.getStackTraceAsString(e));
            throw new ResponseException(e.getMessage());
        } catch (Exception e){
            log.error("create comment fail, comment={}, error={}", comment, Throwables.getStackTraceAsString(e));
            throw new ResponseException("comment.create.fail");
        }
    }

    @Override
    public Comment updateStatus(Long id, Integer status) {
        try {
            checkArgument(id != null, "comment.id.not.empty");
            Comment comment = commentRepository.findById(id);
            Comment update = new Comment();
            update.setId(comment.getId());
            update.setStatus(status);
            commentRepository.update(update);
            return comment;
        }catch (IllegalArgumentException e){
            log.error("check comment argument fail, commentId={}, error={}", id, Throwables.getStackTraceAsString(e));
            throw new ResponseException(e.getMessage());
        } catch (Exception e){
            log.error("update comment status fail, commentId={}, error={}", id, Throwables.getStackTraceAsString(e));
            throw new ResponseException("comment.update.status.fail");
        }
    }
}
