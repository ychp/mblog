package com.ychp.blog.bean.query;

import com.ychp.common.model.paging.PagingCriteria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yingchengpeng
 * @date: 2018/08/11
 */
@ApiModel(description = "查询类型")
@ToString
public class LikeLogCriteria extends PagingCriteria {

    private static final long serialVersionUID = -7478098386947005884L;
    /**
     * 主键
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "主键", example = "1")
    private Long id;


    /**
     * 类型:1.文章,2.说说,3.照片
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

}