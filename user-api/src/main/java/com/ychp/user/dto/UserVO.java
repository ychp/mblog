package com.ychp.user.dto;

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
@ApiModel(description = "页面展示用的用户数据对象")
public class UserVO implements Serializable {
	private static final long serialVersionUID = -3080156210814707416L;

	/**
	 * 主键
	 */
	@Getter
	@Setter
	@ApiModelProperty("主键")
	private Long id;

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
