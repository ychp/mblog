/*
 * Copyright (c) 2016. 杭州端点网络科技有限公司.  All rights reserved.
 */

package com.ychp.blog.web.component.captcha;

import com.github.cage.Cage;
import com.github.cage.IGenerator;
import com.github.cage.image.EffectConfig;
import com.github.cage.image.Painter;
import com.github.cage.token.RandomTokenGenerator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 2017/8/27
 */
@Component
public class CaptchaGenerator {

    private static final String CAPTCHA_TOKEN = "captchaToken";

    private Cage cage;

    private IGenerator<String> tokenGenerator;

    @PostConstruct
    public void init() {
        Painter painter = new Painter(150, 70, null, null, new EffectConfig(true, true, true, true, null), null);
        tokenGenerator = new RandomTokenGenerator(null, 4, 0);
        cage = new Cage(painter, null, null, null, Cage.DEFAULT_COMPRESS_RATIO, tokenGenerator, null);
    }

    public byte[] captcha(HttpSession session) {
        String token = generateCaptchaToken();
        session.setAttribute(CAPTCHA_TOKEN, token);
        return cage.draw(token);
    }


    public String generateCaptchaToken() {
        return tokenGenerator.next();
    }

    public String getGeneratedKey(HttpSession session) {
        String token = (String) session.getAttribute(CAPTCHA_TOKEN);
        session.removeAttribute(CAPTCHA_TOKEN);
        return token;
    }
}
