package com.ychp.blog.bean.request;

import com.ychp.blog.model.Article;
import com.ychp.blog.model.ArticleDetail;
import com.ychp.blog.model.ArticleLabel;
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
@ApiModel(description = "文章创建数据对象")
public class ArticleUpdateRequest implements Serializable {
	private static final long serialVersionUID = -3166059556166230756L;

	@ApiModelProperty("文章基础数据")
	private Article article;

	@ApiModelProperty("文章内容数据")
	private ArticleDetail detail;

	@ApiModelProperty("文章标签数据")
	private List<ArticleLabel> labels;

}
