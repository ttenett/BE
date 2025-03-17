package com.wifi.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.utils.SqliteConnector;
import com.wifi.model.LocationHistory;

public class LocationHistoryService {

	public LocationHistoryService() {
	}

	/**
	 * 위치 히스토리 목록 조회
	 * 
	 * @param conn
	 * @return
	 */
	public List<LocationHistory> getLocationHistoryList(Connection conn) {
		List<LocationHistory> lhList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT id, lat, lnt, created_at FROM location_history ORDER BY id DESC";

		try {
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				LocationHistory lh = new LocationHistory();
				lh.setId(rs.getInt("id"));
				lh.setLat(rs.getDouble("lat"));
				lh.setLnt(rs.getDouble("lnt"));
				lh.setCreatedAt(rs.getString("created_at"));

				lhList.add(lh);
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

		return lhList;
	}

	/**
	 * 위치 히스토리 생성
	 * 
	 * @param conn
	 * @param lat
	 * @param lnt
	 * @return
	 */
	public int createLocationHistory(Connection conn, double lat, double lnt) {
		PreparedStatement pstmt = null;
		int affected = 0;
		String sql = "INSERT INTO location_history (lat, lnt, created_at) VALUES (?, ?, ?)";

		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);

			pstmt.setDouble(1, lat);
			pstmt.setDouble(2, lnt);
			pstmt.setString(3, SqliteConnector.getDbmsTime());

			affected = pstmt.executeUpdate();
			if (affected > 0) {
				System.out.println("Success insert location history");
			} else {
				System.out.println("Fail insert location history");
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
	 * 위치 히스토리 삭제
	 * 
	 * @param conn
	 * @param id
	 * @return
	 */
	public int deleteLocationHistory(Connection conn, int id) {
		PreparedStatement pstmt = null;
		int affected = 0;
		String sql = "DELETE FROM location_history WHERE id = ?";

		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, id);

			affected = pstmt.executeUpdate();
			if (affected > 0) {
				System.out.println("Success delete location history");
			} else {
				System.out.println("Fail delete location history");
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
