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
@ApiModel(description = "文章")
@ToString
@EqualsAndHashCode(of = {  "title", "categoryId", "categoryName", "synopsis", "userId", "author", "visible", })
public class Article implements Serializable {

    private static final long serialVersionUID = -4642009075814450402L;

    @Getter
    @Setter
    @ApiModelProperty(value = "主键", example = "1")
    private Long id;

    @Getter
    @Setter
    @ApiModelProperty("标题")
    private String title;

    @Getter
    @Setter
    @ApiModelProperty(value = "类目ID", example = "1")
    private Long categoryId;

    @Getter
    @Setter
    @ApiModelProperty("类目名称")
    private String categoryName;

    @Getter
    @Setter
    @ApiModelProperty("预览图")
    private String image;

    @Getter
    @Setter
    @ApiModelProperty("简介")
    private String synopsis;

    @Getter
    @Setter
    @ApiModelProperty(value = "作者Id", example = "1")
    private Long userId;

    @Getter
    @Setter
    @ApiModelProperty("作者")
    private String author;

    @Getter
    @Setter
    @ApiModelProperty(value = "状态：0.私有，1.公开，-1.撤下，-99.删除", example = "1")
    private Integer status;

    @Getter
    @Setter
    @ApiModelProperty("发布时间")
    private Date publishAt;

    @Getter
    @Setter
    private Date createdAt;

    @Getter
    @Setter
    private Date updatedAt;

}