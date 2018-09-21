package com.ychp.user.service;

import com.ychp.common.model.paging.Paging;
import com.ychp.user.bean.query.SeeLogCriteria;
import com.ychp.user.bean.response.SeeLogVO;

/**
* @author yingchengpeng
* @date: 2018/08/11
*/
public interface SeeLogReadService {

    /**
     * 根据条件获取分页
     *
     * @param criteria 条件
     * @return 分页结果
     */
    Paging<SeeLogVO> paging(SeeLogCriteria criteria);

}