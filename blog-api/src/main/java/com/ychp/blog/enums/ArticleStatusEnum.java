package com.ychp.blog.enums;

import lombok.Getter;

import java.util.Objects;

/**
 * @author yingchengpeng
 * @date 2018/8/12
 */
public enum ArticleStatusEnum {
	PRIVATE(0, "私有"),
	PUBLIC(1, "公开"),
	FROZEN(-1, "撤下"),
	DELETE(-99, "删除");


	ArticleStatusEnum(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	@Getter
	private int value;

	@Getter
	private String desc;

	public static ArticleStatusEnum fromValue(int value) {
		for (ArticleStatusEnum item : values()) {
			if(Objects.equals(item.getValue(), value)) {
				return item;
			}
		}
		return null;
	}
}
