package com.ychp.blog.bean.query;

import com.ychp.common.model.paging.PagingCriteria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yingchengpeng
 * @date: 2018/08/10
 */
@ApiModel(description = "查询类型")
@ToString
public class LabelCriteria extends PagingCriteria {

    private static final long serialVersionUID = -6958493921726719604L;

    /**
     * 名称
     */
    @Getter
    @Setter
    @ApiModelProperty("名称")
    private String name;

    /**
     * 是否可见，0.不可见，1.可见
     */
    @Getter
    @Setter
    @ApiModelProperty("是否可见，0.不可见，1.可见")
    private Boolean visible;

}