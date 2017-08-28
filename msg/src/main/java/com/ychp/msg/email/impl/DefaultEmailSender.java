/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.ychp.msg.email.impl;

import com.google.common.base.Throwables;
import com.ychp.common.exception.InvalidException;
import com.ychp.common.handlebar.Builder;
import com.ychp.msg.email.EmailSender;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Properties;

/**
 * @author langhsu on 2015/8/14.
 */
@Slf4j
public class DefaultEmailSender implements EmailSender {

    private final Builder builder;

    @Autowired
    public DefaultEmailSender(String host, String userName, String password, Builder builder) {
        this.host = host;
        this.userName = userName;
        this.password = password;
        this.builder = builder;
    }
    private boolean inited = false;

    @PostConstruct
    public void init() {
        // 如果加载完毕直接返回
        if(inited) {
            return;
        }

        if(StringUtils.isEmpty(host)) {
            throw new InvalidException("email.init.fail", "host", host);
        }

        if(StringUtils.isEmpty(userName)) {
            throw new InvalidException("email.init.fail", "userName", userName);
        }

        if(StringUtils.isEmpty(password)) {
            throw new InvalidException("email.init.fail", "password", password);
        }

        sender = new JavaMailSenderImpl();
        sender.setHost(host);
        sender.setDefaultEncoding("UTF-8");
        sender.setUsername(userName);
        sender.setPassword(password);

        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.timeout", "25000");

        sender.setJavaMailProperties(props);

        // 标记加载完毕
        inited = true;
    }

    private String host;
    private String userName;
    private String password;
    // 发送器
    private JavaMailSenderImpl sender;

    @Override
    public void sendTemplete(String address, String subject, String template, Map<String, Object> params) {
        final String html = builder.build(template, params, null, false);

        sendText(address, subject, html, true);
    }

    @Override
    public void sendText(String address, String subject, String content, boolean html) {
        init();
        MimeMessage msg = sender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(msg, true, "UTF-8");
            message.setFrom(sender.getUsername());
            message.setSubject(subject);
            message.setTo(address);
            message.setText(content, html);

            new Thread(() -> sender.send(msg)).start();

        } catch (Exception e) {
            log.warn("send email fail, address = {}, subject = {}, content = {}, case {}",
                    address, subject, content, Throwables.getStackTraceAsString(e));
        }
    }



}
