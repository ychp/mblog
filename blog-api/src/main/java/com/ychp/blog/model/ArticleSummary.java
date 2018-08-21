package com.ychp.blog.model;

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
* @date 2018/08/10
*/
@ApiModel(description = "文章数据汇总")
@ToString
@EqualsAndHashCode(of = {  "articleId", "popular", "like", "favorite", "comments", "isValid", })
public class ArticleSummary implements Serializable {

    private static final long serialVersionUID = 5345893833254732669L;

    public static ArticleSummary empty() {
        ArticleSummary summary = new ArticleSummary();
        summary.setComments(0L);
        summary.setFavorite(0L);
        summary.setLike(0L);
        summary.setPopular(0L);
        return summary;
    }
    /**
     * 主键
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "主键", example = "1")
    private Long id;

    /**
     * 文章ID
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "文章ID", example = "1")
    private Long articleId;

    /**
     * 浏览量
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "浏览量", example = "0")
    private Long popular;

    /**
     * 点赞数
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "点赞数", example = "0")
    private Long like;

    /**
     * 收藏数
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "收藏数", example = "0")
    private Long favorite;

    /**
     * 评论数量
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "评论数量", example = "0")
    private Long comments;

    @Getter
    @Setter
    @ApiModelProperty("是否有效")
    private Boolean isValid;

    @Getter
    @Setter
    private Date createdAt;

    @Getter
    @Setter
    private Date updatedAt;

}