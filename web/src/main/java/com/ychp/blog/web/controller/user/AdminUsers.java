package com.ychp.blog.web.controller.user;

import com.ychp.common.model.Paging;
import com.ychp.user.dto.UserVO;
import com.ychp.user.dto.query.UserCriteria;
import com.ychp.user.model.User;
import com.ychp.user.service.UserReadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("用户详情接口")
    @GetMapping("{id}/detail")
    public UserVO detail(@PathVariable Long id) {
        return userReadService.findDetailById(id);
    }

    @ApiOperation("用户分页接口")
    @GetMapping("paging")
    public Paging<User> paging(UserCriteria criteria) {
        return userReadService.paging(criteria);
    }
}
