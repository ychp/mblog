package com.ychp.user.enums;

import lombok.Getter;

import java.util.Objects;

/**
 * @author yingchengpeng
 * @date 2018/8/12
 */
public enum UserTypeEnum {
	NORMAL(1),
	FROZEN(-1);

	UserTypeEnum(int value) {
		this.value = value;
	}

	@Getter
	private int value;

	public static UserTypeEnum fromValue(int value) {
		for (UserTypeEnum item : values()) {
			if(Objects.equals(item.getValue(), value)) {
				return item;
			}
		}
		return null;
	}
}
