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
* @date 2018/09/10
*/
@ApiModel(description = "评价")
@ToString
@EqualsAndHashCode(of = { "pid", "ownerId", "level", "type", "aimId", "replier", "replierName", "replierAvatar", "receiver", "receiverName", "content", "status",})
public class Comment implements Serializable {

    private static final long serialVersionUID = 7752718701477507331L;
    @Getter
    @Setter
    @ApiModelProperty(value = "主键", example = "1")
    private Long id;

    @Getter
    @Setter
    @ApiModelProperty(value = "父节点", example = "0")
    private Long pid;

    @Getter
    @Setter
    @ApiModelProperty(value = "拥有者", example = "0")
    private Long ownerId;

    @Getter
    @Setter
    @ApiModelProperty(value = "级别", example = "1")
    private Integer level;

    @Getter
    @Setter
    @ApiModelProperty(value = "类型:1.文章,2.说说,3.照片", example = "1")
    private Integer type;

    @Getter
    @Setter
    @ApiModelProperty(value = "对象编号", example = "1")
    private Long aimId;

    @Getter
    @Setter
    @ApiModelProperty(value = "回复者", example = "1")
    private Long replier;

    @Getter
    @Setter
    @ApiModelProperty("回复者昵称")
    private String replierName;

    @Getter
    @Setter
    @ApiModelProperty("回复者头像")
    private String replierAvatar;

    @Getter
    @Setter
    @ApiModelProperty(value = "接收者", example = "1")
    private Long receiver;

    @Getter
    @Setter
    @ApiModelProperty("接收者昵称")
    private String receiverName;

    @Getter
    @Setter
    @ApiModelProperty("评论内容")
    private String content;

    @Getter
    @Setter
    @ApiModelProperty(value = "状态：0.隐藏，1.显示，-1.删除", example = "1")
    private Integer status;

    @Getter
    @Setter
    private Date createdAt;

    @Getter
    @Setter
    private Date updatedAt;

}