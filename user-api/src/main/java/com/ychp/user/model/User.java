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
* @date: 2018/08/08
*/
@ApiModel(description = "用户表")
@ToString
@EqualsAndHashCode(of = { "name", "nickName", "mobile", "email", "homePage", "avatar", "password", "salt", "status" })
public class User implements Serializable {

    private static final long serialVersionUID = -2796900462003393210L;
    /**
     * 主键
     */
    @Getter
    @Setter
    @ApiModelProperty("主键")
    private String id;

    /**
     * 用户名
     */
    @Getter
    @Setter
    @ApiModelProperty("用户名")
    private String name;

    /**
     * 昵称
     */
    @Getter
    @Setter
    @ApiModelProperty("昵称")
    private String nickName;

    /**
     * 手机号码
     */
    @Getter
    @Setter
    @ApiModelProperty("手机号码")
    private String mobile;

    /**
     * 邮箱
     */
    @Getter
    @Setter
    @ApiModelProperty("邮箱")
    private String email;

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
     * 密码
     */
    @Getter
    @Setter
    @ApiModelProperty("密码")
    private String password;

    /**
     * 秘钥
     */
    @Getter
    @Setter
    @ApiModelProperty("秘钥")
    private String salt;

    /**
     * 状态
     */
    @Getter
    @Setter
    @ApiModelProperty("状态")
    private String status;

    @Getter
    @Setter
    private Date createdAt;

    @Getter
    @Setter
    private Date updatedAt;

}