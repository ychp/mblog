package com.ychp.blog.impl.server.repository;

import com.google.common.collect.ImmutableMap;
import com.ychp.blog.model.Category;
import com.ychp.mybatis.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
* @author yingchengpeng
* @date 2018/08/10
*/
@Repository
public class CategoryRepository extends BaseRepository<Category, Long> {

    public Category findByUserIdAndName(Long userId, String name) {
        return getSqlSession().selectOne(sqlId("findByUserIdAndName"),
                ImmutableMap.of("userId", userId, "name", name));
    }
}