package com.ychp.blog.enums;

import lombok.Getter;

import java.util.Objects;

/**
 * @author yingchengpeng
 * @date 2018/8/12
 */
public enum AimTypeEnum {
	// 文章
	ARTICLE(1, "文章"),
	// 说说
	TALK(2, "说说"),
	// 照片
	PHOTO(3, "照片");


	AimTypeEnum(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	@Getter
	private int value;

	@Getter
	private String desc;

	public static AimTypeEnum fromValue(int value) {
		for (AimTypeEnum item : values()) {
			if(Objects.equals(item.getValue(), value)) {
				return item;
			}
		}
		return null;
	}
}
