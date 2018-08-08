package com.ychp.blog.web.controller.user;

import com.ychp.common.model.Paging;
import com.ychp.user.dto.UserCriteria;
import com.ychp.user.model.User;
import com.ychp.user.service.UserReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 2017/8/27
 */
@RestController
@RequestMapping("/api/admin/user")
public class AdminUsers {

    @Resource
    private UserReadService userReadService;

    @GetMapping("{id}/detail")
    public User detail(@PathVariable Long id) {
        return userReadService.findById(id);
    }

    @GetMapping("paging")
    public Paging<User> paging(UserCriteria criteria) {
        return userReadService.paging(criteria);
    }
}
