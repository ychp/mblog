package com.ychp.msg.email.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 2017/8/28
 */
@Data
public class EmailTemplateDto implements Serializable {

    private static final long serialVersionUID = -5120575502775783484L;
    private String key;

    private String title;

    private String content;
}
