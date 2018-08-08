package com.ychp.blog.user.impl.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ychp.blog.user.model.User;
import com.ychp.mybatis.repository.BaseRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 2017/8/27
 */
@Repository
public class UserDao extends BaseRepository<User> {

    public UserDao(SqlSession sqlSession, ObjectMapper objectMapper) {
        super(sqlSession, objectMapper);
    }

    public User login(String name) {
        return getSqlSession().selectOne(sqlId("login"), name);
    }
}