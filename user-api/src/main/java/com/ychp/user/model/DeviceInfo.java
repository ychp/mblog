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
* @date 2018/08/11
*/
@ApiModel(description = "设备信息")
@ToString
@EqualsAndHashCode(of = {  "os", "browser", "browserVersion", "device", })
public class DeviceInfo implements Serializable {

    private static final long serialVersionUID = -3731231214596849793L;

    /**
     * 主键
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "主键", example = "1")
    private Long id;

    /**
     * 系统
     */
    @Getter
    @Setter
    @ApiModelProperty("系统")
    private String os;

    /**
     * 浏览器
     */
    @Getter
    @Setter
    @ApiModelProperty("浏览器")
    private String browser;

    /**
     * 浏览器版本
     */
    @Getter
    @Setter
    @ApiModelProperty("浏览器版本")
    private String browserVersion;

    /**
     * 设备
     */
    @Getter
    @Setter
    @ApiModelProperty("设备")
    private String device;

    @Getter
    @Setter
    private Date createdAt;

    @Getter
    @Setter
    private Date updatedAt;

}