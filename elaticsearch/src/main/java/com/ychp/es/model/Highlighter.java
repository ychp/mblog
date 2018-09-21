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
public class Highlighter implements Serializable {
	private static final long serialVersionUID = -6251768266106336845L;

	public Highlighter(String field) {
		this.field = field;
	}

	public Highlighter(String field, String prefix, String suf) {
		this.field = field;
		this.prefix = prefix;
		this.suf = suf;
	}

	/**
	 * 字段名
	 */
	private String field;

	/**
	 * 样式前缀
	 */
	private String prefix;

	/**
	 * 样式后缀
	 */
	private String suf;

}
