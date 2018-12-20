package com.ychp.msg.email;

import com.ychp.code.builder.BaseBuilder;
import com.ychp.code.builder.impl.DefaultBuilder;
import com.ychp.msg.email.impl.DefaultEmailSender;
import com.ychp.msg.email.properties.EmailProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Desc:
 * @author yingchengpeng
 * @date 2016/08/27
 */
@Configuration
@EnableConfigurationProperties(EmailProperties.class)
public class EmailAutoConfiguration {

    @Bean
    public BaseBuilder builder() {
        return new DefaultBuilder();
    }

    @Bean
    public EmailSender emailSender(EmailProperties emailProperties, BaseBuilder builder) {
        return new DefaultEmailSender(emailProperties, builder);
    }
}
