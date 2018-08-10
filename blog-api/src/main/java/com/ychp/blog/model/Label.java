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
@EqualsAndHashCode(of = {  "name", "visible", })
public class Label implements Serializable {

    private static final long serialVersionUID = 7891359682240407213L;
    /**
     * 主键
     */
    @Getter
    @Setter
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 名称
     */
    @Getter
    @Setter
    @ApiModelProperty("名称")
    private String name;

    /**
     * 是否可见，0.不可见，1.可见
     */
    @Getter
    @Setter
    @ApiModelProperty("是否可见，0.不可见，1.可见")
    private Boolean visible;

    @Getter
    @Setter
    private Date createdAt;

    @Getter
    @Setter
    private Date updatedAt;

}