package com.ychp.blog.impl.server.service;

import com.ychp.blog.bean.query.LikeLogCriteria;
import com.ychp.blog.impl.server.repository.LikeLogRepository;
import com.ychp.blog.model.LikeLog;
import com.ychp.blog.service.LikeLogReadService;
import com.ychp.common.exception.ResponseException;
import com.ychp.common.model.paging.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* @author yingchengpeng
* @date 2018-08-09
*/
@Service
public class LikeLogReadServiceImpl implements LikeLogReadService {

    @Autowired
    private LikeLogRepository likeLogRepository;

    @Override
    public LikeLog findByAimAndIp(Long aimId, Integer type, String ip) {
        try {
            return likeLogRepository.findByAimAndIp(aimId, type, ip);
        } catch (Exception e) {
            throw new ResponseException("like.log.find.fail", e.getMessage(), e.getCause());
        }
    }

    @Override
    public Paging<LikeLog> paging(LikeLogCriteria criteria) {
        try {
            return likeLogRepository.paging(criteria.toMap());
        } catch (Exception e) {
            throw new ResponseException("like.log.paging.fail", e.getMessage(), e.getCause());
        }
    }

}