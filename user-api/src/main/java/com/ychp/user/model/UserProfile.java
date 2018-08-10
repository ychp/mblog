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
     * 真实姓名
     */
    @Getter
    @Setter
    @ApiModelProperty("真实姓名")
    private String realName;

    /**
     * 出生日期
     */
    @Getter
    @Setter
    @ApiModelProperty("出生日期")
    private Date birth;

    /**
     * 国家ID
     */
    @Getter
    @Setter
    @ApiModelProperty("国家ID")
    private Long countryId;

    /**
     * 省份ID
     */
    @Getter
    @Setter
    @ApiModelProperty("省份ID")
    private Long provinceId;

    /**
     * 城市ID
     */
    @Getter
    @Setter
    @ApiModelProperty("城市ID")
    private Long cityId;

    /**
     * 国家
     */
    @Getter
    @Setter
    @ApiModelProperty("国家")
    private String country;

    /**
     * 省份
     */
    @Getter
    @Setter
    @ApiModelProperty("省份")
    private String province;

    /**
     * 城市
     */
    @Getter
    @Setter
    @ApiModelProperty("城市")
    private String city;

    /**
     * 简介
     */
    @Getter
    @Setter
    @ApiModelProperty("简介")
    private String synopsis;

    /**
     * 职业
     */
    @Getter
    @Setter
    @ApiModelProperty("职业")
    private String profile;

    @Getter
    @Setter
    private Date createdAt;

    @Getter
    @Setter
    private Date updatedAt;

}