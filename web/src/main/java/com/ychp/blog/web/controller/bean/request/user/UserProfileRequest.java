package com.ychp.blog.web.controller.bean.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yingchengpeng
 * @date 2018/8/9
 */
@ToString
@ApiModel(description = "需要保存的用户信息")
public class UserProfileRequest implements Serializable {

	private static final long serialVersionUID = -3132782891889934402L;

	/**
	 * 昵称
	 */
	@Getter
	@Setter
	@ApiModelProperty("昵称")
	private String nickName;

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
}
