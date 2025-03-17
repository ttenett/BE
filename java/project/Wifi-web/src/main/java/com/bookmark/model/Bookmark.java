package com.bookmark.model;

public class Bookmark {
	private int id; // 순번
	private int groupBookmarkId; // 그룹 북마크 ID
	private String name; // 북마크그룹이름
	private String xSwifiMainNm; // 와이파이명
	private String createdAt; // 등록일자

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGroupBookmarkId() {
		return groupBookmarkId;
	}

	public void setGroupBookmarkId(int groupBookmarkId) {
		this.groupBookmarkId = groupBookmarkId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getxSwifiMainNm() {
		return xSwifiMainNm;
	}

	public void setxSwifiMainNm(String xSwifiMainNm) {
		this.xSwifiMainNm = xSwifiMainNm;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}
