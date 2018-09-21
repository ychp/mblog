package com.ychp.blog.bean.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yingchengpeng
 * @date 2018/9/10
 */
@Data
@ApiModel(description = "评价创建数据对象")
public class CommentRequest {

    @ApiModelProperty(value = "父节点", example = "0")
    private Long pid;

    @ApiModelProperty(value = "类型:1.文章,2.说说,3.照片", example = "1")
    private Integer type;

    @ApiModelProperty(value = "对象编号", example = "1")
    private Long aimId;

    @ApiModelProperty(value = "回复者", example = "1")
    private Long replier;

    @ApiModelProperty("评论内容")
    private String content;

}
