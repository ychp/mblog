package com.ychp.blog.web.controller.user;

import com.ychp.blog.web.controller.bean.request.user.UserProfileRequest;
import com.ychp.common.util.SessionContextUtils;
import com.ychp.user.dto.UserVO;
import com.ychp.user.model.User;
import com.ychp.user.model.UserProfile;
import com.ychp.user.service.UserReadService;
import com.ychp.user.service.UserWriteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 2017/8/27
 */
@Api(description = "面向用户的用户接口")
@RestController
@RequestMapping("/api/user")
public class Users {

    @Autowired
    private UserReadService userReadService;

    @Autowired
    private UserWriteService userWriteService;

    @ApiOperation(value = "获取用户基础信息(用户详情)", httpMethod = "GET")
    @GetMapping("profile")
    public UserVO detail() {
        Long userId = SessionContextUtils.getUserId();
        return userReadService.findDetailById(userId);
    }

    @ApiOperation(value = "更新用户基础信息(用户详情)", httpMethod = "POST")
    @PostMapping("profile")
    public Boolean updateProfile(@RequestBody UserProfileRequest request) {
        Long userId = SessionContextUtils.getUserId();

        User toUpdate = generateUser(request, userId);
        if(toUpdate != null) {
            userWriteService.update(toUpdate);
        }

        UserProfile toUpdateProfile = generateUserProfile(request, userId);
        if(toUpdateProfile != null) {
            userWriteService.saveProfile(toUpdateProfile);
        }

        return true;
    }

    private User generateUser(UserProfileRequest request, Long userId) {
        if(StringUtils.isEmpty(request.getNickName())) {
            return null;
        }
        User user = new User();
        user.setId(userId);
        user.setNickName(request.getNickName());
        return user;
    }

    private UserProfile generateUserProfile(UserProfileRequest request, Long userId) {
        if(StringUtils.isEmpty(request.getAvatar())
                && StringUtils.isEmpty(request.getHomePage())
                && StringUtils.isEmpty(request.getGender())
                && request.getBirth() == null) {
            return null;
        }
        UserProfile profile = new UserProfile();
        profile.setUserId(userId);
        profile.setBirth(request.getBirth());
        profile.setHomePage(request.getHomePage());
        profile.setGender(request.getGender());
        profile.setAvatar(request.getAvatar());
        return profile;
    }


}
