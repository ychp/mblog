package com.ychp.blog.search.bean.response;

import com.ychp.common.model.paging.Paging;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author yingchengpeng
 * @date 2018/8/10
 */
@Data
@ApiModel(description = "文章搜索结果")
public class SearchWithAggsVO implements Serializable {
	private static final long serialVersionUID = 3071305056978555710L;

	/**
	 * 数据
	 */
	@Getter
	@Setter
	@ApiModelProperty("数据")
	private Paging<ArticleSearchVO> paging;


}
