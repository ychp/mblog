package com.ychp.blog.search.bean.response;

import com.ychp.blog.model.ArticleSummary;
import com.ychp.blog.model.Label;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author yingchengpeng
 * @date 2018/8/10
 */
@ToString
@EqualsAndHashCode
@ApiModel(description = "文章搜索数据对象")
public class ArticleSearchVO implements Serializable {
	private static final long serialVersionUID = 3071305056978555710L;

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
	 * 类目名称
	 */
	@Getter
	@Setter
	@ApiModelProperty("类目名称")
	private String categoryName;

	/**
	 * 预览图
	 */
	@Getter
	@Setter
	@ApiModelProperty("预览图")
	private String image;

	/**
	 * 简介
	 */
	@Getter
	@Setter
	@ApiModelProperty("简介")
	private String synopsis;

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

	@Getter
	@Setter
	@ApiModelProperty("发布时间")
	private Date publishAt;

	@Getter
	@Setter
	@ApiModelProperty("文章标签数据")
	private List<Label> labels;

	@Getter
	@Setter
	@ApiModelProperty("文章标签数据")
	private List<Long> labelIds;

	@Getter
	@Setter
	@ApiModelProperty("文章数据汇总")
	private ArticleSummary summary;

}
