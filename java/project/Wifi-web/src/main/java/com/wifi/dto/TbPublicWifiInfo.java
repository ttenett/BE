package com.wifi.dto;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class TbPublicWifiInfo {
	@SerializedName(value = "list_total_count")
	private int listTotalCount;

	@SerializedName(value = "RESULT")
	private Result result;

	@SerializedName(value = "row")
	private List<Row> row;

	public int getListTotalCount() {
		return this.listTotalCount;
	}

	public List<Row> getRow() {
		return this.row;
	}

	public Result getResult() {
		return this.result;
	}
}
