package com.ychp.es.bean.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yingchengpeng
 * @date 2018/8/17
 */
@Data
public class BaseRequest implements Serializable {
	private static final long serialVersionUID = -3165509921912485623L;

	/**
	 * 索引
	 */
	private String index;

	/**
	 * 类型
	 */
	private String type;

}
