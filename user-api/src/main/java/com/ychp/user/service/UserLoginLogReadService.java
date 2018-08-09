package com.ychp.user.service;

import com.ychp.common.model.paging.Paging;
import com.ychp.user.dto.UserLoginLogVO;
import com.ychp.user.dto.query.UserLoginLogCriteria;

/**
* @author yingchengpeng
* @date: 2018/08/09
*/
public interface UserLoginLogReadService {

    /**
     * 根据条件获取分页
     *
     * @param criteria 条件
     * @return 分页结果
     */
    Paging<UserLoginLogVO> paging(UserLoginLogCriteria criteria);

}