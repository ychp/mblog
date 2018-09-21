package com.ychp.blog.enums;

import lombok.Getter;

import java.util.Objects;

/**
 * @author yingchengpeng
 * @date 2018/9/10
 */
public enum CommentStatusEnum {
	HIDE(0, "隐藏"),
	SHOW(1, "显示"),
	DELETED(-1, "删除");


	CommentStatusEnum(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	@Getter
	private int value;

	@Getter
	private String desc;

	public static CommentStatusEnum fromValue(int value) {
		for (CommentStatusEnum item : values()) {
			if(Objects.equals(item.getValue(), value)) {
				return item;
			}
		}
		return null;
	}
}
