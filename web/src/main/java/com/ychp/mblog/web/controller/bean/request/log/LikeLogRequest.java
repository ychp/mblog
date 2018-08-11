package com.ychp.mblog.web.controller.bean.request.log;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yingchengpeng
 * @date 2018/8/11
 */
@Data
@ApiModel(description = "点赞记录信息")
public class LikeLogRequest implements Serializable {

	private static final long serialVersionUID = -3648581534475512258L;
	@ApiModelProperty("目标id")
	private Long aimId;

	@ApiModelProperty("目标类型:1.文章,2.说说,3.照片")
	private Integer type;
}
