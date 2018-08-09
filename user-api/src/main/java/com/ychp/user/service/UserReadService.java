package com.ychp.user.service;

import com.ychp.user.dto.UserVO;
import com.ychp.user.dto.query.UserCriteria;
import com.ychp.user.model.User;
import com.ychp.common.model.Paging;

/**
 * @author yingchengpeng
 * @date 2018-08-09
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
     * 根据id查询用户基础信息
     *
     * @param id 主键id
     * @return 查询结果
     */
    UserVO findDetailById(Long id);

    /**
     * 登录
     * @param name 用户名
     * @param password 密码
     * @return 用户
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