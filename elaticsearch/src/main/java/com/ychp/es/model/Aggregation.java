package com.ychp.es.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yingchengpeng
 * @date 2018/8/19
 */
@Data
@NoArgsConstructor
public class Aggregation implements Serializable {
	private static final long serialVersionUID = -6251768266106336845L;

	public Aggregation(String name, String field, Integer size) {
		this.name = name;
		this.field = field;
		this.size = size;
	}

	/**
	 * 返回名称
	 */
	private String name;

	/**
	 * 字段名
	 */
	private String field;

	/**
	 * 聚合数量
	 */
	private Integer size;

}
