package com.ychp.msg.email.impl;

import com.google.common.base.Throwables;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.ychp.code.builder.BaseBuilder;
import com.ychp.common.exception.ResponseException;
import com.ychp.common.util.ParameterChecker;
import com.ychp.msg.email.EmailSender;
import com.ychp.msg.email.dto.EmailTemplateDto;
import com.ychp.msg.email.properties.EmailProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author yingchengpeng
 * @date 2018-09-11
 */
@Slf4j
public class DefaultEmailSender implements EmailSender {

    private final BaseBuilder builder;
    private ExecutorService executor;
    private boolean inited = false;

    @Autowired
    public DefaultEmailSender(EmailProperties properties, BaseBuilder builder) {
        this.host = properties.getHost();
        this.userName = properties.getUserName();
        this.password = properties.getPassword();
        List<EmailTemplateDto> templateDtoList = properties.getTemplates();
        if(!CollectionUtils.isEmpty(templateDtoList)) {
            this.templateDtos = templateDtoList.stream().collect(Collectors.toMap(EmailTemplateDto::getKey, emailTemplateDto -> emailTemplateDto));
        }
        this.builder = builder;
    }

    @PostConstruct
    public void init() {
        // 如果加载完毕直接返回
        if(inited) {
            return;
        }

        ParameterChecker.notNull(host,"host", "email.init.fail");
        ParameterChecker.notNull(userName,"userName", "email.init.fail");
        ParameterChecker.notNull(password,"password", "email.init.fail");

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
        executor = new ThreadPoolExecutor(10, 1000, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(50),
                new ThreadFactoryBuilder().setNameFormat("email-sender").build());
    }

    private String host;
    private String userName;
    private String password;
    private Map<String, EmailTemplateDto> templateDtos;
    /**
     * 发送器
     */
    private JavaMailSenderImpl sender;

    @Override
    public void sendTemplate(String address, String templateKey, Map<String, Object> params) {
        EmailTemplateDto emailTemplate = getTemplate(templateKey);
        if(emailTemplate == null) {
            throw new ResponseException("email.template.not.exists");
        }
        final List<String> contents = builder.build(emailTemplate.getContent(), null, null, params, false, false);
        sendText(address, emailTemplate.getTitle(), contents.get(0), false);
    }

    @Override
    public void sendTemplate(String address, String subject, String template, Map<String, Object> params) {
        final List<String> htmls = builder.build(template, null, null, params, false, false);
        sendText(address, subject, htmls.get(0), true);
    }

    private EmailTemplateDto getTemplate(String templateKey) {
        return templateDtos.get(templateKey);
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

            executor.execute(() -> sender.send(msg));

        } catch (Exception e) {
            log.warn("send email fail, address = {}, subject = {}, content = {}, case {}",
                    address, subject, content, Throwables.getStackTraceAsString(e));
        }
    }

}
