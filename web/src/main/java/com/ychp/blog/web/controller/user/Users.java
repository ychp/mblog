package com.ychp.blog.web.controller.user;

import com.google.common.collect.Maps;
import com.ychp.blog.web.component.captcha.CaptchaGenerator;
import com.ychp.common.exception.ResponseException;
import com.ychp.msg.email.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;

import static com.ychp.blog.web.constant.EmailConstants.REGISTER_CODE;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 2017/8/27
 */
@RestController
@RequestMapping("/api/user")
public class Users {

    @Autowired
    private CaptchaGenerator captchaGenerator;
    @Autowired(required = false)
    private EmailSender emailSender;

    @GetMapping("captcha")
    public ResponseEntity<byte[]> getCaptcha(HttpServletRequest request) {
        byte[] imgCache;
        HttpSession session = request.getSession();
        imgCache = captchaGenerator.captcha(session);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imgCache, headers, HttpStatus.CREATED);
    }

    @PostMapping("send-email")
    public void sendEmail(String email, String captcha, HttpServletRequest request) {
        String captchaToken = captchaGenerator.getGeneratedKey(request.getSession());
        if(!Objects.equals(captchaToken, captcha)) {
            throw new ResponseException("captcha.mismatch");
        }

        HttpSession session = request.getSession();
        String sessionId = session.getId();
        String code = captchaGenerator.generateCaptchaToken();

        Map<String, Object> params = Maps.newHashMap();
        params.put("code", code);
        emailSender.sendTemplate(email, REGISTER_CODE, params);
    }

    @GetMapping("send-email-test")
    public void sendEmailTest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        String code = captchaGenerator.generateCaptchaToken();

    }
}
