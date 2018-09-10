package com.ychp.blog.model;

import com.ychp.blog.enums.AimTypeEnum;
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
* @date 2018/08/11
*/
@ApiModel(description = "点赞明细")
@ToString
@EqualsAndHashCode(of = {  "type", "aimId", "ip", "deviceId", "url", "uri", })
public class LikeLog implements Serializable {

    private static final long serialVersionUID = -1447612463185769113L;
    @Getter
    @Setter
    @ApiModelProperty(value = "主键", example = "1")
    private Long id;

    @Getter
    @Setter
    @ApiModelProperty(value = "用户ID", example = "1")
    private Long userId;

    /**
     * 类型:1.文章,2.说说,3.照片
     * @see AimTypeEnum
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "类型:1.文章,2.说说,3.照片", example = "1")
    private Integer type;

    @Getter
    @Setter
    @ApiModelProperty(value = "目标id", example = "1")
    private Long aimId;

    @Getter
    @Setter
    @ApiModelProperty("ip地址")
    private String ip;

    @Getter
    @Setter
    @ApiModelProperty(value = "设备id", example = "1")
    private Long deviceId;

    @Getter
    @Setter
    private Date createdAt;

    @Getter
    @Setter
    private Date updatedAt;

}