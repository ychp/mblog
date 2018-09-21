package com.ychp.es.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yingchengpeng
 * @date 2018/8/19
 */
@Data
public class Filter implements Serializable {
	private static final long serialVersionUID = -6251768266106336845L;

	private String name;

	private Object from;

	private Object to;

	private Boolean isMulti;
}
