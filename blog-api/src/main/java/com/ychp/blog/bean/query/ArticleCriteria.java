package com.ychp.blog.bean.query;

import com.ychp.common.model.paging.PagingCriteria;
import com.ychp.common.util.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author yingchengpeng
 * @date: 2018/08/10
 */
@ApiModel(description = "查询类型")
@ToString
public class ArticleCriteria extends PagingCriteria {

    private static final long serialVersionUID = 4888887277023945934L;

    @Getter
    @Setter
    @ApiModelProperty(value = "主键", example = "1")
    private Long id;

    @Getter
    @Setter
    @ApiModelProperty("标题")
    private String title;

    @Getter
    @Setter
    @ApiModelProperty(value = "类目ID", example = "1")
    private Long categoryId;

    @Getter
    @Setter
    @ApiModelProperty(value = "作者Id", example = "1")
    private Long userId;

    @Getter
    @Setter
    @ApiModelProperty("作者")
    private String author;

    @Getter
    @Setter
    @ApiModelProperty("状态：0.私有，1.公开，-1.撤下，-99.删除")
    private Integer status;

    @Getter
    @Setter
    @ApiModelProperty("发布时间")
    private String publishAt;

    @Getter
    @Setter
    @ApiModelProperty("发布开始时间")
    private String publishAtStart;

    @Getter
    @Setter
    @ApiModelProperty("发布结束时间")
    private String publishAtEnd;

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = super.toMap();
        if(!StringUtils.isEmpty(publishAtEnd)) {
            map.put("publishAtEnd", DateUtils.parse2EndDate(publishAtEnd));
        }

        if(!StringUtils.isEmpty(publishAtStart)) {
            map.put("publishAtStart", DateUtils.parse2Date(publishAtStart));
        }

        if(!StringUtils.isEmpty(publishAt)) {
            map.put("publishAtStart", DateUtils.parse2Date(publishAt));
            map.put("publishAtEnd", DateUtils.parse2EndDate(publishAt));
        }

        return map;
    }
}