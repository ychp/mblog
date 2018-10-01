package com.ychp.blog.impl.server.repository;

import com.google.common.collect.ImmutableMap;
import com.ychp.blog.model.Comment;
import com.ychp.mybatis.repository.BaseRepository;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author yingchengpeng
* @date 2018/09/10
*/
@Repository
public class CommentRepository extends BaseRepository<Comment, Long> {

    public List<Comment> findBy(Integer type, Long aimId) {
        return getSqlSession().selectList(sqlId("findBy"), ImmutableMap.of("type", type, "aimId", aimId));
    }

    public List<Comment> findByPids(List<Long> pids) {
        return getSqlSession().selectList(sqlId("findByPids"), pids);
    }

}