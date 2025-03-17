package com.bookmark.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bookmark.model.Bookmark;
import com.utils.SqliteConnector;

public class BookmarkService {

	public BookmarkService() {
	}

	/**
	 * 북마크 수 조회
	 * 
	 * @param conn
	 * @param groupBookmarkId
	 * @return
	 */
	public static int getBookmarkCount(Connection conn, int groupBookmarkId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) as count FROM bookmark WHERE group_bookmark_id = ?";
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, groupBookmarkId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}

				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}

				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return -2;
			}
		}

		return result;
	}

	/**
	 * 북마크 목록 조회
	 * 
	 * @param conn
	 * @return
	 */
	public List<Bookmark> getBookmarkList(Connection conn) {
		List<Bookmark> bmList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT b.id, b.group_bookmark_id, gb.name, b.x_swifi_main_nm, b.created_at" + "  FROM bookmark b"
				+ "  LEFT JOIN group_bookmark gb" + "  ON b.group_bookmark_id = gb.id";

		try {
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Bookmark bm = new Bookmark();
				bm.setId(rs.getInt("id"));
				bm.setGroupBookmarkId(rs.getInt("group_bookmark_id"));
				bm.setName(rs.getString("name"));
				bm.setxSwifiMainNm(rs.getString("x_swifi_main_nm"));
				bm.setCreatedAt(rs.getString("created_at"));

				bmList.add(bm);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}

				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}

				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}

		return bmList;
	}

	/**
	 * 북마크 상세 조회
	 * 
	 * @param conn
	 * @param id
	 * @return
	 */
	public Bookmark getBookmarkDetail(Connection conn, int id) {
		Bookmark bm = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT b.id, b.group_bookmark_id, gb.name, b.x_swifi_main_nm, b.created_at" + "  FROM bookmark b"
				+ "  LEFT JOIN group_bookmark gb" + "  ON b.group_bookmark_id = gb.id WHERE b.id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				bm = new Bookmark();
				bm.setId(rs.getInt("id"));
				bm.setGroupBookmarkId(rs.getInt("group_bookmark_id"));
				bm.setName(rs.getString("name"));
				bm.setxSwifiMainNm(rs.getString("x_swifi_main_nm"));
				bm.setCreatedAt(rs.getString("created_at"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}

				if (pstmt != null && pstmt.isClosed()) {
					pstmt.close();
				}

				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}

		return bm;
	}

	/**
	 * 북마크 생성
	 * 
	 * @param conn
	 * @param groupBookmarkId
	 * @param name
	 * @param xSwifiMainNm
	 * @return
	 */
	public int createBookmark(Connection conn, int groupBookmarkId, String xSwifiMainNm) {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO bookmark (group_bookmark_id, x_swifi_main_nm, created_at) VALUES (?, ?, ?)";
		int affected = 0;

		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, groupBookmarkId);
			pstmt.setString(2, xSwifiMainNm);
			pstmt.setString(3, SqliteConnector.getDbmsTime());

			affected = pstmt.executeUpdate();
			conn.commit();

			if (affected > 0) {
				System.out.println("Success Insert Bookmark");
			} else {
				System.out.println("Fail Insert Bookmark");
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return -1;
		} finally {
			try {
				if (pstmt != null && pstmt.isClosed()) {
					pstmt.close();
				}

				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return -2;
			}
		}

		return affected;
	}

	/**
	 * 북마크 삭제
	 * 
	 * @param conn
	 * @param id
	 * @return
	 */
	public int deleteBookmark(Connection conn, int id) {
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM bookmark WHERE id = ?";
		int affected = 0;

		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			affected = pstmt.executeUpdate();
			conn.commit();

			if (affected > 0) {
				System.out.println("Success Delete Bookmark");
			} else {
				System.out.println("Fail Delete Bookmark");
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return -1;
		} finally {
			try {
				if (pstmt != null && pstmt.isClosed()) {
					pstmt.close();
				}

				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return -2;
			}
		}

		return affected;
	}

}
