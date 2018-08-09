package com.ychp.user.service;

import com.ychp.user.model.UserLoginLog;

/**
* @author yingchengpeng
* @date: 2018/08/09
*/
public interface UserLoginLogWriteService {

    /**
     * 创建
     * @param userLoginLog 需要创建的数据
     * @return 主键
     */
    Long create(UserLoginLog userLoginLog);

    /**
     * 更新
     * @param userLoginLog 需要更新的数据
     * @return 操作结果
     */
    Boolean update(UserLoginLog userLoginLog);

    /**
     * 根据主键id删除数据
     * @param id 主键
     * @return 操作结果
     */
    Boolean delete(Long id);
}