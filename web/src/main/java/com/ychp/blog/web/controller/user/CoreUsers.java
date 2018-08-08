package com.ychp.blog.web.controller.user;

import com.ychp.blog.web.util.SkyUserMaker;
import com.ychp.common.model.SkyUser;
import com.ychp.user.model.User;
import com.ychp.user.service.UserReadService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 2017/8/27
 */
@Api(description = "公共用户接口")
@RestController
@RequestMapping("/api/user")
public class CoreUsers {

    @Autowired
    private UserReadService userReadService;

    @GetMapping("{id}")
    public SkyUser detail(@PathVariable Long id) {
        return SkyUserMaker.make(userReadService.findById(id));
    }

    @ApiOperation(value = "登录", httpMethod = "POST")
    @PostMapping("login")
    public void login(@ApiParam @RequestParam String name,
                      @ApiParam @RequestParam String password,
                      HttpServletRequest request) {
        User user = userReadService.login(name, password);
        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getId());
    }

}
