package com.ychp.user.model;

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
* @date 2018/10/01
*/
@ApiModel(description = "关注关系")
@ToString
@EqualsAndHashCode(of = { "followerId", "followeeId", "isSubscribe" })
public class FollowRelation implements Serializable {

    private static final long serialVersionUID = -2484046100429591799L;
    @Getter
    @Setter
    @ApiModelProperty(value = "主键", example = "1")
    private Long id;

    @Getter
    @Setter
    @ApiModelProperty(value = "关注人", example = "1")
    private Long followerId;

    @Getter
    @Setter
    @ApiModelProperty(value = "被关注人", example = "1")
    private Long followeeId;

    @Getter
    @Setter
    @ApiModelProperty("是否订阅")
    private Boolean isSubscribe;

    @Getter
    @Setter
    private Date createdAt;

    @Getter
    @Setter
    private Date updatedAt;

}