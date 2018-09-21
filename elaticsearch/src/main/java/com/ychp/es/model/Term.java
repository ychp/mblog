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
public class Term implements Serializable {
	private static final long serialVersionUID = -6251768266106336845L;

	public Term(String name, Object value) {
		this.name = name;
		this.value = value;
		this.isMulti = false;
	}

	public Term(Object value, String ... names) {
		this.multiNames = names;
		this.value = value;
		this.isMulti = true;
	}

	private String name;

	private String[] multiNames;

	private Object value;

	private Boolean isMulti;
}
