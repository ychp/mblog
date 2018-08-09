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
* @date: 2018/08/09
*/
@ApiModel(description = "用户信息")
@ToString
@EqualsAndHashCode(of = {  "userId", "homePage", "avatar", "gender", "birth",   })
public class UserProfile implements Serializable {

    private static final long serialVersionUID = 457843353048085690L;
    /**
     * 主键
     */
    @Getter
    @Setter
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 用户id
     */
    @Getter
    @Setter
    @ApiModelProperty("用户id")
    private Long userId;

    /**
     * 主页
     */
    @Getter
    @Setter
    @ApiModelProperty("主页")
    private String homePage;

    /**
     * 头像
     */
    @Getter
    @Setter
    @ApiModelProperty("头像")
    private String avatar;

    /**
     * 性别：male,female
     */
    @Getter
    @Setter
    @ApiModelProperty("性别：male,female")
    private String gender;

    /**
     * 出生日期
     */
    @Getter
    @Setter
    @ApiModelProperty("出生日期")
    private Date birth;

    @Getter
    @Setter
    private Date createdAt;

    @Getter
    @Setter
    private Date updatedAt;

}