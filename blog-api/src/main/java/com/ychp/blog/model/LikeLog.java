package com.ychp.blog.model;

import com.ychp.blog.enums.LikeLogTypeEnum;
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
    /**
     * 主键
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "主键", example = "1")
    private Long id;

    /**
     * 类型:1.文章,2.说说,3.照片
     * @see LikeLogTypeEnum
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "类型:1.文章,2.说说,3.照片", example = "1")
    private Integer type;

    /**
     * 目标id
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "目标id", example = "1")
    private Long aimId;

    /**
     * ip地址
     */
    @Getter
    @Setter
    @ApiModelProperty("ip地址")
    private String ip;

    /**
     * 设备id
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "设备id", example = "1")
    private Long deviceId;

    /**
     * 访问页面
     */
    @Getter
    @Setter
    @ApiModelProperty("访问页面")
    private String url;

    /**
     * 请求
     */
    @Getter
    @Setter
    @ApiModelProperty("请求")
    private String uri;

    @Getter
    @Setter
    private Date createdAt;

    @Getter
    @Setter
    private Date updatedAt;

}