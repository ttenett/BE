package com.bookmark.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bookmark.model.GroupBookmark;
import com.utils.RdbmsConnector;
import com.utils.SqliteConnector;

public class GroupBookmarkService {
	public GroupBookmarkService() {
	}

	/**
	 * 북마크 그룹 목록 조회
	 * 
	 * @param conn
	 * @return
	 */
	public List<GroupBookmark> getGroupBookmarkList(Connection conn) {
		List<GroupBookmark> list = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT id, name, `order`, created_at, updated_at FROM group_bookmark ORDER BY id DESC";

		try {
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				GroupBookmark gb = new GroupBookmark();
				gb.setId(rs.getInt("id"));
				gb.setName(rs.getString("name"));
				gb.setOrder(rs.getInt("order"));
				gb.setCreatedAt(rs.getString("created_at"));
				gb.setUpdatedAt(rs.getString("updated_at"));

				list.add(gb);
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

		return list;
	}

	/**
	 * 북마크 그룹 상세 조회
	 * 
	 * @param conn
	 * @param id
	 * @return
	 */
	public GroupBookmark getGroupBookmarkDetail(Connection conn, int id) {
		GroupBookmark gb = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT id, name, `order` FROM group_bookmark WHERE id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				gb = new GroupBookmark();

				gb.setId(rs.getInt("id"));
				gb.setName(rs.getString("name"));
				gb.setOrder(rs.getInt("order"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
				return null;
			}
		}

		return gb;
	}

	/**
	 * 북마크 그룹 생성
	 * 
	 * @param conn
	 * @param name
	 * @param order
	 * @return
	 */
	public int createGroupBookmark(Connection conn, String name, int order) {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO group_bookmark (`order`, name, updated_at, created_at) VALUES (?, ?, ?, ?)";
		int affected = 0;

		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);

			System.out.println("order: " + order);
			pstmt.setInt(1, order);
			pstmt.setString(2, name);
			pstmt.setString(3, SqliteConnector.getDbmsTime());
			pstmt.setString(4, SqliteConnector.getDbmsTime());

			affected = pstmt.executeUpdate();
			if (affected > 0) {
				System.out.println("Success Insert Group Bookmark");
			} else {
				System.out.println("Fail Insert Group Bookmark");
			}

			conn.commit();
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

		return affected;
	}

	/**
	 * 북마크 그룹 갱신
	 * 
	 * @param conn
	 * @param name
	 * @param order
	 * @return
	 */
	public int updateGroupBookmark(Connection conn, int id, String name, int order) {
		PreparedStatement pstmt = null;
		String sql = "UPDATE group_bookmark SET `order` = ?, name = ?, updated_at = ? WHERE id = ?";
		int affected = 0;

		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, order);
			pstmt.setString(2, name);
			pstmt.setString(3, SqliteConnector.getDbmsTime());
			pstmt.setInt(4, id);

			affected = pstmt.executeUpdate();
			if (affected > 0) {
				System.out.println("Success Update");
			} else {
				System.out.println("Fail Update");
			}

			conn.commit();
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

		return affected;
	}

	/**
	 * 북마크 그룹 삭제
	 * 
	 * @param conn
	 * @param id
	 * @return
	 */
	public int deleteGroupBookmark(Connection conn, int id) {
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM group_bookmark WHERE id = ?";
		int affected = 0;

		try {
			int count = BookmarkService.getBookmarkCount(conn, id);
			if (count > 0) {
				System.out.println("그룹에 해당하는 북마크 존재하여 삭제 불가 : " + id);
				return -1;
			}

			RdbmsConnector rdbmsConnector = new RdbmsConnector("localhost", 23306, "wifi", "wifi1234", "wifi");
			conn = rdbmsConnector.getRdbmsConnection();

			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, id);

			affected = pstmt.executeUpdate();
			if (affected > 0) {
				System.out.println("Success Delete");
			} else {
				System.out.println("Fail Delete");
			}

			conn.commit();
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

		return affected;
	}
}
