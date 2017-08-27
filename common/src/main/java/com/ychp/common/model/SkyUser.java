package com.ychp.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 2017/8/27
 */
@Data
public class SkyUser implements Serializable {

    private static final long serialVersionUID = -6041819789771031794L;
    private Long id;
    private String nickName;
    private String mobile;
    private String email;
    private String homePage;
    private String ip;
    private String avatar;
    private Integer status;

}
