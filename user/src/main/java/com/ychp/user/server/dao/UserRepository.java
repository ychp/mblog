package com.ychp.user.server.dao;

import com.ychp.user.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ychp.mybatis.repository.BaseRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

/**
* @author yingchengpeng
* @date: 2018/08/08
*/
@Repository
public class UserRepository extends BaseRepository<User, Long> {

    public UserRepository(SqlSession sqlSession, ObjectMapper objectMapper) {
        super(sqlSession, objectMapper);
    }

    public User findByName(String name) {
        return getSqlSession().selectOne(sqlId("findByName"), name);
    }
}