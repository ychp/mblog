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
public class ArticleCriteria extends PagingCriteria {

    private static final long serialVersionUID = 4888887277023945934L;
    /**
     * 主键
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "主键", example = "1")
    private Long id;


    /**
     * 标题
     */
    @Getter
    @Setter
    @ApiModelProperty("标题")
    private String title;

    /**
     * 类目ID
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "类目ID", example = "1")
    private Long categoryId;

    /**
     * 作者Id
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "作者Id", example = "1")
    private Long userId;

    /**
     * 作者
     */
    @Getter
    @Setter
    @ApiModelProperty("作者")
    private String author;

    /**
     * 是否可见，0.不可见，1.可见
     */
    @Getter
    @Setter
    @ApiModelProperty("是否可见，0.不可见，1.可见")
    private Boolean visible;

}