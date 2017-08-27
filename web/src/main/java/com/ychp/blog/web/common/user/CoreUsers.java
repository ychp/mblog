package com.ychp.blog.web.common.user;

import com.ychp.blog.user.model.User;
import com.ychp.blog.user.service.UserReadService;
import com.ychp.blog.web.util.SkyUserMaker;
import com.ychp.common.model.SkyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 2017/8/27
 */
@RestController
@RequestMapping("/api/user")
public class CoreUsers {

    @Autowired
    private UserReadService userReadService;

    @GetMapping("{id}")
    public SkyUser detail(@PathVariable Long id) {
        return SkyUserMaker.make(userReadService.findById(id));
    }

    @GetMapping("login")
    public void login(@RequestParam String name,
                      @RequestParam String password,
                      HttpServletRequest request) {
        User user = userReadService.login(name, password);

    }

}
