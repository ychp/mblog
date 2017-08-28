package com.ychp.msg.email;

import com.ychp.common.handlebar.Builder;
import com.ychp.common.handlebar.impl.DefaultBuilder;
import com.ychp.msg.email.impl.DefaultEmailSender;
import com.ychp.msg.email.properties.EmailProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 2017/8/28
 */
@Configuration
@EnableConfigurationProperties(EmailProperties.class)
public class EmailAutoConfiguration {

    @Bean
    public Builder builder() {
        return new DefaultBuilder();
    }

    @Bean
    public EmailSender emailSender(EmailProperties emailProperties, Builder builder) {
        return new DefaultEmailSender(emailProperties, builder);
    }
}
