package com.ychp.msg.email.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Desc:
 * @author yingchengpeng
 * @date 2016/08/27
 */
@Data
public class EmailTemplateDto implements Serializable {

    private static final long serialVersionUID = -5120575502775783484L;
    private String key;

    private String title;

    private String content;
}
