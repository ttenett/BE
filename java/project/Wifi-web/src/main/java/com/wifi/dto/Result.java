package com.wifi.dto;

import com.google.gson.annotations.SerializedName;

public class Result {
	@SerializedName(value = "CODE")
	private String code;

	@SerializedName(value = "MESSAGE")
	private String message;

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
