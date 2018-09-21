package com.ychp.blog.bean.response;

import com.ychp.blog.model.Article;
import com.ychp.blog.model.ArticleSummary;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yingchengpeng
 * @date 2018/8/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "文章列表数据对象")
public class ArticleBaseInfoVO implements Serializable {
	private static final long serialVersionUID = 3071305056978555710L;

	@ApiModelProperty("文章基础数据")
	private Article article;

	@ApiModelProperty("文章数据汇总")
	private ArticleSummary summary;
}
