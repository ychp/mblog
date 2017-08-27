package com.ychp.blog.user.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 2017/8/27
 */
@EqualsAndHashCode(of = { "nickName",  "mobile",  "email",  "homePage",  "ip",  "avatar",  "password",  "salt",  "status"})
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 6348462167146628656L;
    @Getter
    @Setter
    private Long id;


    /**
     * 用户名
     */
    @Getter
    @Setter
    private String nickName;

    /**
     * 手机号码
     */
    @Getter
    @Setter
    private String mobile;

    /**
     * 邮箱
     */
    @Getter
    @Setter
    private String email;

    /**
     * 主页
     */
    @Getter
    @Setter
    private String homePage;

    /**
     * ip地址
     */
    @Getter
    @Setter
    private String ip;

    /**
     * 头像
     */
    @Getter
    @Setter
    private String avatar;

    /**
     * 密码
     */
    @Getter
    @Setter
    private String password;

    /**
     * 秘钥
     */
    @Getter
    @Setter
    private String salt;

    /**
     * 状态
     */
    @Getter
    @Setter
    private Integer status;

    @Getter
    @Setter
    private Date createdAt;

    @Getter
    @Setter
    private Date updatedAt;

}