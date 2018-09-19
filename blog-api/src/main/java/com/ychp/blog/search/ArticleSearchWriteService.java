package com.ychp.blog.search;

/**
 * @author yingchengpeng
 * @date 2018/8/19
 */
public interface ArticleSearchWriteService {

	/**
	 * 同步文章
	 * @param articleId 文章id
	 */
	void index(Long articleId);

	/**
	 * 从搜索中删除文章
	 * @param articleId 文章id
	 */
	void remove(Long articleId);

	/**
	 * 全量同步文章
	 */
	void fullDump();

	/**
	 * 增量同步文章
	 * @param deltaMin 时间间隔：分
	 */
	void deltaDump(Integer deltaMin);

	/**
	 * 同步一条数据
	 * @param id
	 */
	void dumpOne(Long id);

}
