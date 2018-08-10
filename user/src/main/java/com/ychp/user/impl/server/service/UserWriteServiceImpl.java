package com.ychp.user.impl.server.service;

import com.ychp.common.exception.ResponseException;
import com.ychp.user.impl.server.repository.UserProfileRepository;
import com.ychp.user.impl.server.repository.UserRepository;
import com.ychp.user.model.User;
import com.ychp.user.model.UserProfile;
import com.ychp.user.service.UserWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yingchengpeng
 * @date 2018/8/9
 */
@Service
public class UserWriteServiceImpl implements UserWriteService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Override
	public Long create(User user) {
		try {
			userRepository.create(user);
			return user.getId();
		} catch (Exception e) {
			throw new ResponseException("user.create.fail", e.getMessage(), e.getCause());
		}
	}

	@Override
	public Boolean update(User user) {
		try {
			return userRepository.update(user);
		} catch (Exception e) {
			throw new ResponseException("user.update.fail", e.getMessage(), e.getCause());
		}
	}

	@Override
	public Boolean saveProfile(UserProfile profile) {
		try {
			UserProfile exist = userProfileRepository.findByUserId(profile.getUserId());
			if(exist == null) {
				userProfileRepository.create(profile);
				return true;
			}

			profile.setId(exist.getId());
			profile.setUserId(null);
			return userProfileRepository.update(profile);
		} catch (Exception e) {
			throw new ResponseException("user.update.fail", e.getMessage(), e.getCause());
		}
	}

	@Override
	public Boolean delete(Long id) {
		try {
			return userRepository.delete(id);
		} catch (Exception e) {
			throw new ResponseException("user.delete.fail", e.getMessage(), e.getCause());
		}
	}
}
