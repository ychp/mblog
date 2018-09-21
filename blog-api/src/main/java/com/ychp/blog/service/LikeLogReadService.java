package com.ychp.blog.service;

import com.ychp.blog.bean.query.LikeLogCriteria;
import com.ychp.blog.model.LikeLog;
import com.ychp.common.model.paging.Paging;

/**
* @author yingchengpeng
* @date: 2018/08/11
*/
public interface LikeLogReadService {

    /**
     * 获取点赞记录
     * @param aimId 目标
     * @param type 目标类型
     * @param ip ip地址
     * @return 点赞记录
     */
    LikeLog findByAimAndIp(Long aimId, Integer type, String ip);

    /**
     * 获取点赞记录
     * @param aimId 目标
     * @param type 目标类型
     * @param userId 用户ID
     * @return 点赞记录
     */
    LikeLog findByAimAndUserId(Long aimId, Integer type, Long userId);

    /**
     * 根据条件获取分页
     *
     * @param criteria 条件
     * @return 分页结果
     */
    Paging<LikeLog> paging(LikeLogCriteria criteria);

}