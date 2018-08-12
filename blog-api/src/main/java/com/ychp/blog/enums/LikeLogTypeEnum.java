package com.ychp.blog.enums;

import lombok.Getter;

import java.util.Objects;

/**
 * @author yingchengpeng
 * @date 2018/8/12
 */
public enum LikeLogTypeEnum {
	ARTICLE(1, "文章"),
	TALK(2, "说说"),
	PHOTO(3, "照片");


	LikeLogTypeEnum(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	@Getter
	private int value;

	@Getter
	private String desc;

	public static LikeLogTypeEnum fromValue(int value) {
		for (LikeLogTypeEnum item : values()) {
			if(Objects.equals(item.getValue(), value)) {
				return item;
			}
		}
		return null;
	}
}
