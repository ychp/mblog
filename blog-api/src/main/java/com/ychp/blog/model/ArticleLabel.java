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
@ApiModel(description = "文章标签")
@ToString
@EqualsAndHashCode(of = {  "articleId", "labelId", "labelName", })
public class ArticleLabel implements Serializable {

    private static final long serialVersionUID = -9213868987504359678L;
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
     * 标签ID
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "标签ID", example = "1")
    private Long labelId;

    /**
     * 标签
     */
    @Getter
    @Setter
    @ApiModelProperty("标签")
    private String labelName;

    @Getter
    @Setter
    private Date createdAt;

    @Getter
    @Setter
    private Date updatedAt;

}