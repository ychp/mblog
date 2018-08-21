package com.ychp.es.bean.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yingchengpeng
 * @date 2018/8/17
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class IndexTypeRequest extends BaseRequest {

	private static final long serialVersionUID = 6521895988334247725L;

	/**
	 * 是否支持全字段搜索
	 */
	@Getter
	@Setter
	private String mapping;
}
