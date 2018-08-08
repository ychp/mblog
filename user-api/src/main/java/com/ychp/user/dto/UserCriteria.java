package com.ychp.user.dto;

import com.ychp.common.model.PagingCriteria;
import lombok.Data;

import java.io.Serializable;

/**
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 2017/8/27
 */
@Data
public class UserCriteria extends PagingCriteria implements Serializable {


    private String nickName;
    private String mobile;
    private String email;
    private String homePage;
    private String ip;
    private String header;
    private String password;
    private String salt;
    private Integer status;

}