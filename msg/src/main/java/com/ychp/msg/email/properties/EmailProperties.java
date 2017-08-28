package com.ychp.msg.email.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 2017/8/28
 */
@Data
@ConfigurationProperties(prefix = EmailProperties.EMAIL_PREFIX)
public class EmailProperties {

    public final static String EMAIL_PREFIX = "email";

    private String host;
    private String userName;
    private String password;

}
