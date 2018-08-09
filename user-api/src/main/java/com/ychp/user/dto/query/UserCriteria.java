package com.ychp.user.dto.query;

import com.ychp.common.model.PagingCriteria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author yingchengpeng
 * @date 2018-08-08
 */
@ApiModel(description = "用户查询类型")
@ToString
public class UserCriteria extends PagingCriteria {

    private static final long serialVersionUID = 8815059999975666033L;

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
     * 状态
     */
    @Getter
    @Setter
    @ApiModelProperty("状态")
    private String status;

}