package com.ychp.blog.user.impl.service;

import com.ychp.blog.user.dto.UserCriteria;
import com.ychp.blog.user.impl.dao.UserDao;
import com.ychp.blog.user.model.User;
import com.ychp.blog.user.service.UserReadService;
import com.ychp.common.exception.InvalidException;
import com.ychp.common.exception.ResponseException;
import com.ychp.common.model.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 2017/8/27
 */
@Service
public class UserReadServiceImpl implements UserReadService {

    private final UserDao userDao;

    @Autowired
    public UserReadServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findById(Long id) {
        if(id == null) {
            throw new InvalidException("user.id.empty", "id", id);
        }
        try {
            return userDao.findById(id);
        } catch (Exception e) {
            throw new ResponseException("user.find.fail", e.getMessage(), e.getCause());
        }
    }

    @Override
    public Paging<User> paging(UserCriteria criteria) {
        try {
            return userDao.paging(criteria.toMap());
        } catch (Exception e) {
            throw new ResponseException("user.paging.fail", e.getMessage(), e.getCause());
        }
    }
}
