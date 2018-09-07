package com.ychp.mblog.web.controller.user;

import com.ychp.common.model.paging.Paging;
import com.ychp.user.bean.response.UserLoginLogVO;
import com.ychp.user.bean.response.UserVO;
import com.ychp.user.bean.query.UserCriteria;
import com.ychp.user.bean.query.UserLoginLogCriteria;
import com.ychp.user.model.User;
import com.ychp.user.service.UserLoginLogReadService;
import com.ychp.user.service.UserReadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yingchengpeng
 * @date 2018-08-09
 */
@Api(description = "运营管理账户用的数据接口")
@RestController
@RequestMapping("/api/admin/user")
public class AdminUsers {

    @Autowired
    private UserReadService userReadService;

    @Autowired
    private UserLoginLogReadService userLoginLogReadService;

    @ApiOperation("用户详情接口")
    @GetMapping("{id}/findDetail")
    public UserVO detail(@ApiParam(example = "1") @PathVariable Long id) {
        return userReadService.findDetailById(id);
    }

    @ApiOperation("用户分页接口")
    @GetMapping("paging")
    public Paging<User> paging(UserCriteria criteria) {
        return userReadService.paging(criteria);
    }

    @ApiOperation("用户登录日志分页接口")
    @GetMapping("login-log/paging")
    public Paging<UserLoginLogVO> pagingLoginLog(UserLoginLogCriteria criteria) {
        return userLoginLogReadService.paging(criteria);
    }
}
