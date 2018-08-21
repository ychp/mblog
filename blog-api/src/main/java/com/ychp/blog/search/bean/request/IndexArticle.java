package com.ychp.blog.search.bean.request;

import com.ychp.blog.model.Article;
import com.ychp.blog.model.ArticleDetail;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author yingchengpeng
 * @date 2018/8/19
 */
@Data
public class IndexArticle implements Serializable {
	private static final long serialVersionUID = 4951089160538506949L;

	@Getter
	@Setter
	@ApiModelProperty("主键")
	private String id;

	@Getter
	@Setter
	@ApiModelProperty("标题")
	private String title;

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
	@ApiModelProperty(value = "类目ID", example = "1")
	private Long categoryId;

	@Getter
	@Setter
	@ApiModelProperty("类目名称")
	private String categoryName;

	@Getter
	@Setter
	@ApiModelProperty("预览图")
	private String image;

	@Getter
	@Setter
	@ApiModelProperty("简介")
	private String synopsis;

	@Getter
	@Setter
	@ApiModelProperty("内容")
	private String content;

	@Getter
	@Setter
	@ApiModelProperty("发布时间")
	private Date publishAt;

	@Getter
	@Setter
	@ApiModelProperty("发布日期")
	private String publishDate;

	@Getter
	@Setter
	@ApiModelProperty("标签")
	private List<Long> labelIds;

	public IndexArticle(Article article, ArticleDetail articleDetail) {
		this.id = article.getId().toString();
		this.userId = article.getUserId();
		this.author = article.getAuthor();
		this.categoryId = article.getCategoryId();
		this.categoryName = article.getCategoryName();
		this.title = article.getTitle();
		this.image = article.getImage();
		this.synopsis = article.getSynopsis();
		this.publishAt = article.getPublishAt();
		this.publishDate = new DateTime(article.getPublishAt()).toString("yyyy-MM-dd");
		this.content = articleDetail.getContent();
	}
}
