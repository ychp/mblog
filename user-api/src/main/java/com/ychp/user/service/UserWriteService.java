package com.ychp.user.service;

import com.ychp.user.model.User;

/**
 * Created with terminus generator
 * Author:  yingchengpeng
 * Date: 2017-08-27 11:01:01
 */
public interface UserWriteService {

    /**
     * 创建用户
     */
    Long createUser(User user);

    /**
     * 更新用户
     */
    Boolean updateUser(User user);

    /**
     * 根据主键id逻辑删除用户
     */
    Boolean deleteUser(Long userId);
}