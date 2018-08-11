package com.ychp.blog.impl.server.service;

import com.ychp.blog.impl.server.repository.LikeLogRepository;
import com.ychp.blog.model.LikeLog;
import com.ychp.blog.service.LikeLogWriteService;
import com.ychp.common.exception.ResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* @author yingchengpeng
* @date 2018-08-09
*/
@Service
public class LikeLogWriteServiceImpl implements LikeLogWriteService {

    @Autowired
    private LikeLogRepository likeLogRepository;

    @Override
    public Long create(LikeLog likeLog) {
        try {
            likeLogRepository.create(likeLog);
            return likeLog.getId();
        } catch (Exception e) {
            throw new ResponseException("likeLog.create.fail", e.getMessage(), e.getCause());
        }
    }

    @Override
    public Boolean delete(Long id) {
        try {
            return likeLogRepository.delete(id);
        } catch (Exception e) {
            throw new ResponseException("likeLog.delete.fail", e.getMessage(), e.getCause());
        }
    }

}
