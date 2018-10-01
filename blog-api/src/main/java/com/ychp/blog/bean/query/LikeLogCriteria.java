package com.ychp.blog.bean.query;

import com.ychp.common.model.paging.PagingCriteria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yingchengpeng
 * @date 2018/08/11
 */
@ApiModel(description = "查询类型")
@ToString
public class LikeLogCriteria extends PagingCriteria {

    private static final long serialVersionUID = -7478098386947005884L;

    @Getter
    @Setter
    @ApiModelProperty(value = "主键", example = "1")
    private Long id;

    @Getter
    @Setter
    @ApiModelProperty(value = "用户ID", example = "1")
    private Long userId;


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

}