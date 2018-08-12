package com.ychp.blog.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
* @author yingchengpeng
* @date 2018/08/10
*/
@ApiModel(description = "文章详情")
@ToString
@EqualsAndHashCode(of = {  "articleId", "content", })
public class ArticleDetail implements Serializable {

    private static final long serialVersionUID = 41956923893272101L;

    /**
     * 主键
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "主键", example = "1")
    private Long id;

    /**
     * 文章ID
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "文章ID", example = "1")
    private Long articleId;

    /**
     * 是否为markdown
     */
    @Getter
    @Setter
    @ApiModelProperty("是否为markdown")
    private Boolean isMarkdown;

    /**
     * 内容
     */
    @Getter
    @Setter
    @ApiModelProperty("内容")
    private String content;

    @Getter
    @Setter
    private Date createdAt;

    @Getter
    @Setter
    private Date updatedAt;

}