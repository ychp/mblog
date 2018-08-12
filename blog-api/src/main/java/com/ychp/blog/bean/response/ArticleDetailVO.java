package com.ychp.blog.bean.response;

import com.ychp.blog.model.Article;
import com.ychp.blog.model.ArticleDetail;
import com.ychp.blog.model.ArticleLabel;
import com.ychp.blog.model.ArticleSummary;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yingchengpeng
 * @date 2018/8/10
 */
@Data
@ApiModel(description = "文章详情数据对象")
public class ArticleDetailVO implements Serializable {
	private static final long serialVersionUID = 3071305056978555710L;

	@ApiModelProperty("文章基础数据")
	private Article article;

	@ApiModelProperty("文章内容数据")
	private ArticleDetail detail;

	@ApiModelProperty("文章标签数据")
	private List<ArticleLabel> labels;

	@ApiModelProperty("文章数据汇总")
	private ArticleSummary summary;

	@ApiModelProperty("是否点赞")
	private Boolean hasLiked;
}
