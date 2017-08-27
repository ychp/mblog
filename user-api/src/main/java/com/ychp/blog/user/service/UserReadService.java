package com.ychp.blog.user.service;

import com.ychp.blog.user.dto.UserCriteria;
import com.ychp.blog.user.model.User;
import com.ychp.common.model.Paging;

/**
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 2017/8/27
 */
public interface UserReadService {

    /**
     * 根据id查询
     *
     * @param id 主键id
     * @return 查询结果
     */
    User findById(Long id);

    /**
     * 登录
     */
    User login(String name, String password);

    /**
     * 根据条件获取分页
     *
     * @param criteria 条件
     * @return 分页结果
     */
    Paging<User> paging(UserCriteria criteria);

}