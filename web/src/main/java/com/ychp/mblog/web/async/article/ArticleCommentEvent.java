package com.ychp.mblog.web.async.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yingchengpeng
 * @date 2018/8/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleCommentEvent implements Serializable {

	private static final long serialVersionUID = 8573237704554018571L;
	private Long id;
	private Boolean isSub;
	private Boolean isAdd;
}
