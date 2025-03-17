package com.wifi.model;

public class LocationHistory {
	private int id; // 순번
	private double lnt; // x좌표
	private double lat; // y좌표
	private String createdAt; // 생성일자

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLnt() {
		return lnt;
	}

	public void setLnt(double lnt) {
		this.lnt = lnt;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}
