package com.ychp.es.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.core.annotation.AliasFor;

import java.io.Serializable;

/**
 * @author yingchengpeng
 * @date 2018/8/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sorter implements Serializable {
	private static final long serialVersionUID = -6251768266106336845L;

	private String name;

	private SortOrder order;
}
