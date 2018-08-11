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
@ApiModel(description = "类目")
@ToString
@EqualsAndHashCode(of = {  "name", })
public class Category implements Serializable {

    private static final long serialVersionUID = 2884451715066153633L;
    /**
     * 主键
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "主键", example = "1")
    private Long id;

    /**
     * 名称
     */
    @Getter
    @Setter
    @ApiModelProperty("名称")
    private String name;

    @Getter
    @Setter
    private Date createdAt;

    @Getter
    @Setter
    private Date updatedAt;

}