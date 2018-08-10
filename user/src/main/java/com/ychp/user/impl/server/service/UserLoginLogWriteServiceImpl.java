package com.ychp.user.impl.server.service;

import com.ychp.common.exception.ResponseException;
import com.ychp.user.impl.server.repository.UserLoginLogRepository;
import com.ychp.user.model.UserLoginLog;
import com.ychp.user.service.UserLoginLogWriteService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


/**
* @author yingchengpeng
* @date 2018-08-09
*/
@Service
public class UserLoginLogWriteServiceImpl implements UserLoginLogWriteService {

    @Autowired
    private UserLoginLogRepository userLoginLogRepository;

    @Override
    public Long create(UserLoginLog userLoginLog) {
        try {
            userLoginLogRepository.create(userLoginLog);
            return userLoginLog.getId();
        } catch (Exception e) {
            throw new ResponseException("userLoginLog.create.fail", e.getMessage(), e.getCause());
        }
    }

    @Override
    public Boolean update(UserLoginLog userLoginLog) {
        try {
            return userLoginLogRepository.update(userLoginLog);
        } catch (Exception e) {
            throw new ResponseException("userLoginLog.update.fail", e.getMessage(), e.getCause());
        }
    }

    @Override
    public Boolean delete(Long id) {
        try {
            return userLoginLogRepository.delete(id);
        } catch (Exception e) {
            throw new ResponseException("userLoginLog.delete.fail", e.getMessage(), e.getCause());
        }
    }

}
