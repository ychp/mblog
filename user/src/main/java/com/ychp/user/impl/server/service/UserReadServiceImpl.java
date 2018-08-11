package com.ychp.user.impl.server.service;

import com.ychp.common.exception.InvalidException;
import com.ychp.common.exception.ResponseException;
import com.ychp.common.model.paging.Paging;
import com.ychp.common.util.Encryption;
import com.ychp.user.bean.response.UserVO;
import com.ychp.user.bean.query.UserCriteria;
import com.ychp.user.impl.server.repository.UserProfileRepository;
import com.ychp.user.impl.server.repository.UserRepository;
import com.ychp.user.model.User;
import com.ychp.user.model.UserProfile;
import com.ychp.user.service.UserReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yingchengpeng
 * @date 2018-08-09
 */
@Service
public class UserReadServiceImpl implements UserReadService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public User findById(Long id) {
        if(id == null) {
            throw new InvalidException("user.id.empty", "id", id);
        }
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            throw new ResponseException("user.find.fail", e.getMessage(), e.getCause());
        }
    }

    @Override
    public UserVO findDetailById(Long id) {
        if(id == null) {
            throw new InvalidException("user.id.empty", "id", id);
        }
        try {
            User user = userRepository.findById(id);
            UserVO userVO = new UserVO();
            fillUserInfo(userVO, user);
            UserProfile profile = userProfileRepository.findByUserId(id);
            fillUserProfile(userVO, profile);
            return userVO;
        } catch (Exception e) {
            throw new ResponseException("user.find.fail", e.getMessage(), e.getCause());
        }
    }

    private void fillUserInfo(UserVO userVO, User user) {
        userVO.setId(user.getId());
        userVO.setName(user.getName());
        userVO.setMobile(user.getMobile());
        userVO.setNickName(user.getNickName());
        userVO.setEmail(user.getEmail());
        userVO.setStatus(user.getStatus());
        userVO.setCreatedAt(user.getCreatedAt());
        userVO.setUpdatedAt(user.getUpdatedAt());
    }

    private void fillUserProfile(UserVO userVO, UserProfile profile) {
        if(profile == null) {
            return;
        }
        userVO.setAvatar(profile.getAvatar());
        userVO.setGender(profile.getGender());
        userVO.setHomePage(profile.getHomePage());
        userVO.setRealName(profile.getRealName());
        userVO.setBirth(profile.getBirth());
        userVO.setCountryId(profile.getCountryId());
        userVO.setProvinceId(profile.getProvinceId());
        userVO.setCityId(profile.getCityId());
        userVO.setCountry(profile.getCountry());
        userVO.setProvince(profile.getProvince());
        userVO.setCity(profile.getCity());
        userVO.setSynopsis(profile.getSynopsis());
        userVO.setProfile(profile.getProfile());
    }

    @Override
    public User login(String name, String password) {
        try {
            User user = userRepository.findByName(name);
            if(Encryption.checkPassword(password, user.getSalt(), user.getPassword())) {
                return user;
            }
        } catch (Exception e) {
            throw new ResponseException("user.login.fail", e.getMessage(), e.getCause());
        }

        throw new ResponseException("user.login.fail");
    }

    @Override
    public Paging<User> paging(UserCriteria criteria) {
        try {
            return userRepository.paging(criteria.toMap());
        } catch (Exception e) {
            throw new ResponseException("user.paging.fail", e.getMessage(), e.getCause());
        }
    }

    @Override
    public User findByName(String name) {
        try {
            return userRepository.findByName(name);
        } catch (Exception e) {
            throw new ResponseException("user.find.fail", e.getMessage(), e.getCause());
        }
    }
}
