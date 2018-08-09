package com.ychp.blog.web.controller.user;

import com.ychp.blog.web.async.user.LoginLogAsync;
import com.ychp.blog.web.constant.SessionConstants;
import com.ychp.blog.web.util.SkyUserMaker;
import com.ychp.common.captcha.CaptchaGenerator;
import com.ychp.common.exception.ResponseException;
import com.ychp.common.model.SkyUser;
import com.ychp.common.util.Encryption;
import com.ychp.common.util.SessionContextUtils;
import com.ychp.ip.component.IPServer;
import com.ychp.user.model.User;
import com.ychp.user.service.UserReadService;
import com.ychp.user.service.UserWriteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    private CaptchaGenerator captchaGenerator;

    @Autowired
    private UserReadService userReadService;

    @Autowired
    private UserWriteService userWriteService;

    @Autowired
    private IPServer ipServer;

    @Autowired
    private LoginLogAsync loginLogAsync;

    @ApiOperation(value = "获取用户信息", httpMethod = "GET")
    @GetMapping("{id}")
    public SkyUser detail(@PathVariable Long id) {
        return SkyUserMaker.make(userReadService.findById(id));
    }

    @ApiOperation(value = "登录", httpMethod = "POST")
    @PostMapping("login")
    public SkyUser login(@ApiParam("用户名") @RequestParam String name,
                      @ApiParam("密码") @RequestParam String password,
                      @ApiParam("验证码TODO") @RequestParam(required = false) String captcha,
                      HttpServletRequest request) {
        User user = userReadService.login(name, password);
        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getId());

        SkyUser skyUser = SkyUserMaker.make(user);
        skyUser.setIp(ipServer.getIp(request));

        loginLogAsync.log(skyUser);
        return skyUser;
    }

    @ApiOperation(value = "修改密码",httpMethod = "PUT")
    @PutMapping("change-password")
    public Boolean changePassword(String oldPassword, String newPassword) {
        Long userId = SessionContextUtils.getUserId();
        User user = userReadService.findById(userId);
        if(!Encryption.checkPassword(oldPassword, user.getSalt(), user.getPassword())) {
            throw new ResponseException("user.password.mismatch");
        }

        User toUpdate = new User();
        toUpdate.setId(userId);
        toUpdate.setPassword(Encryption.encrypt3DES(newPassword, user.getSalt()));

        return userWriteService.update(toUpdate);
    }

    @ApiOperation(value = "获取图形验证码", httpMethod = "GET")
    @GetMapping("captcha")
    public ResponseEntity<byte[]> getCaptcha(HttpServletRequest request) {
        byte[] imgCache;
        HttpSession session = request.getSession();
        String token = captchaGenerator.generateCaptchaToken();
        session.setAttribute(SessionConstants.CAPTCHA_TOKEN, token);
        imgCache = captchaGenerator.captcha(token);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imgCache, headers, HttpStatus.CREATED);
    }

}
