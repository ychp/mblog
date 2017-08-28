package com.ychp.blog.web.front.user;

import com.ychp.blog.web.component.captcha.CaptchaGenerator;
import com.ychp.common.exception.ResponseException;
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
import java.util.Objects;

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

    @GetMapping
    public ResponseEntity<byte[]> getCaptcha(HttpServletRequest request) {
        byte[] imgCache;
        HttpSession session = request.getSession();
        imgCache = captchaGenerator.captcha(session);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imgCache, headers, HttpStatus.CREATED);
    }

    @PostMapping
    public void sendEmail(String email, String captcha, HttpServletRequest request) {
        String captchaToken = captchaGenerator.getGeneratedKey(request.getSession());
        if(!Objects.equals(captchaToken, captcha)) {
            throw new ResponseException("captcha.mismatch");
        }

    }

}
