package com.ychp.es.bean.request;

import com.google.common.base.MoreObjects;
import com.ychp.es.model.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.elasticsearch.action.search.SearchType;

import java.util.List;

/**
 * @author yingchengpeng
 * @date 2018/8/17
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class QueryRequest extends BaseRequest {

	private static final long serialVersionUID = 6521895988334247725L;

	/**
	 * 搜索类型
	 */
	@Getter
	@Setter
	private SearchType searchType = SearchType.DFS_QUERY_THEN_FETCH;

	/**
	 * 搜索条件
	 */
	@Getter
	@Setter
	private List<Term> terms;

	/**
	 * 过滤器
	 */
	@Getter
	@Setter
	private List<Filter> filters;

	/**
	 * 高亮词
	 */
	@Getter
	@Setter
	private Highlighter highlighter;

	/**
	 * 数据聚合
	 */
	@Getter
	@Setter
	private List<Aggregation> aggregations;

	@Getter
	@Setter
	private List<Sorter> sorters;

	/**
	 * 分页号, 从1开始
	 */
	@Getter
	@Setter
	private Integer pageNo = 1;

	/**
	 * 分页大小
	 */
	@Getter
	@Setter
	private Integer pageSize = 20;

	/**
	 * 分页大小, 默认20, 用于数据库
	 */
	public Integer getLimit() {
		Integer limit = MoreObjects.firstNonNull(pageSize, 20);
		return limit > 0 ? limit : 20;
	}

	/**
	 * 分页起始, 从0开始, 用于数据库
	 */
	public Integer getOffset() {
		pageNo = MoreObjects.firstNonNull(pageNo, 1);
		pageSize = MoreObjects.firstNonNull(pageSize, 20);
		Integer offset = (pageNo - 1) * pageSize;
		return offset > 0 ? offset : 0;
	}
}
