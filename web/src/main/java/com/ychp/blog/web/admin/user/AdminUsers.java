package com.ychp.blog.web.admin.user;

import com.ychp.blog.user.model.User;
import com.ychp.blog.user.service.UserReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 2017/8/27
 */
@RestController
@RequestMapping("/api/user")
public class AdminUsers {

    @Autowired
    private UserReadService userReadService;

    @GetMapping("{id}/detail")
    public User detail(@PathVariable Long id) {
        return userReadService.findById(id);
    }

}