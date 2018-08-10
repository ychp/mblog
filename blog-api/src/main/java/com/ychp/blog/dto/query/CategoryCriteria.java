package com.ychp.blog.dto.query;

import com.ychp.common.model.paging.PagingCriteria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yingchengpeng
 * @date: 2018/08/10
 */
@ApiModel(description = "查询类型")
@ToString
public class CategoryCriteria extends PagingCriteria {

    private static final long serialVersionUID = 7346127129736911979L;
    /**
     * 主键
     */
    @Getter
    @Setter
    @ApiModelProperty("主键")
    private Long id;


    /**
     * 名称
     */
    @Getter
    @Setter
    @ApiModelProperty("名称")
    private String name;


}