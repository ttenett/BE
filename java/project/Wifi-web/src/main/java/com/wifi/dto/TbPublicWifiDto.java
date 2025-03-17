package com.wifi.dto;

import com.google.gson.annotations.SerializedName;

public class TbPublicWifiDto {
	@SerializedName(value = "TbPublicWifiInfo")
	private TbPublicWifiInfo tbPublicWifiInfo;

	public TbPublicWifiInfo getTbPublicWifiInfo() {
		return tbPublicWifiInfo;
	}
}
