package com.wifi.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.wifi.dto.TbPublicWifiDto;
import com.wifi.dto.TbPublicWifiInfo;
import com.wifi.model.Wifi;

public class TbPublicWifiInfoService {

	/**
	 * 생성자
	 */
	public TbPublicWifiInfoService() {
	}

	/**
	 * 공용 총 레코드 수 조회
	 * 
	 * @return result
	 * @throws SQLException
	 */
	public int getTbPublicWifiAllCount(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "SELECT COUNT(*) as count FROM tb_public_wifi;";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				result = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
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
				return -2;
			}
		}

		return result;
	}

	/**
	 * 내 위치 근처 와이파이 정보 목록 조회
	 * 
	 * @param conn
	 * @param lat
	 * @param lnt
	 * @return list<Wifi>
	 */
	public List<Wifi> getTbPublicWifiList(Connection conn, double lat, double lnt) {
		List<Wifi> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT"
				+ "	ROUND((6371 * acos( cos( radians(?) ) * cos( radians( lat ) ) * cos( radians( lnt ) - radians(?) ) + sin( radians(?) ) * sin( radians( lat ))) / 1000), 4) AS distance,"
				+ "	x_swifi_merg_no, x_swifi_wrdofc, x_swifi_main_nm, x_swifi_adres1, x_swifi_adres2, x_swifi_instl_floor, x_swifi_instl_ty, x_swifi_instl_mby, x_swifi_svc_se,"
				+ " x_swifi_cmcwr, x_swifi_cnstc_year, x_swifi_inout_door, x_swifi_remars3, lnt, lat, work_dttm"
				+ " FROM tb_public_wifi ORDER BY distance ASC LIMIT 20";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, lat);
			pstmt.setDouble(2, -lnt);
			pstmt.setDouble(3, lat);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Wifi wifi = new Wifi();
				wifi.setDistance(rs.getDouble("distance"));
				wifi.setxSwifiMergNo(rs.getString("x_swifi_merg_no"));
				wifi.setxSwifiWrdofc(rs.getString("x_swifi_wrdofc"));
				wifi.setxSwifiMainNm(rs.getString("x_swifi_main_nm"));
				wifi.setxSwifiAdres1(rs.getString("x_swifi_adres1"));
				wifi.setxSwifiAdres2(rs.getString("x_swifi_adres2"));
				wifi.setxSwifiInstlFloor(rs.getString("x_swifi_instl_floor"));
				wifi.setxSwifiInstlTy(rs.getString("x_swifi_instl_ty"));
				wifi.setxSwifiInstlMby(rs.getString("x_swifi_instl_mby"));
				wifi.setxSwifiSvcSe(rs.getString("x_swifi_svc_se"));
				wifi.setxSwifiCmcwr(rs.getString("x_swifi_cmcwr"));
				wifi.setxSwifiCnstcYear(rs.getInt("x_swifi_cnstc_year"));
				wifi.setxSwifiInoutDoor(rs.getString("x_swifi_inout_door"));
				wifi.setxSwifiRemars3(rs.getString("x_swifi_remars3"));
				wifi.setLnt(rs.getDouble("lnt"));
				wifi.setLat(rs.getDouble("lat"));
				wifi.setWorkDttm(rs.getString("work_dttm"));

				list.add(wifi);
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

		return list;
	}

	/**
	 * 공용 와이파이 상세 정보 조회
	 * 
	 * @param conn
	 * @param xSwifiMergNo
	 * @return
	 */
	public Wifi getTbPublicWifiDetail(Connection conn, String xSwifiMergNo) {
		Wifi wifi = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT 0.00000 as distance, x_swifi_merg_no, x_swifi_wrdofc, x_swifi_main_nm, x_swifi_adres1,"
				+ " x_swifi_adres2, x_swifi_instl_floor, x_swifi_instl_ty, x_swifi_instl_mby, x_swifi_svc_se, x_swifi_cmcwr,"
				+ " x_swifi_cnstc_year,	x_swifi_inout_door, x_swifi_remars3, lnt, lat, work_dttm"
				+ "  FROM tb_public_wifi" + " WHERE x_swifi_merg_no = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, xSwifiMergNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				wifi = new Wifi();
				wifi.setDistance(rs.getDouble("distance"));
				wifi.setxSwifiMergNo(rs.getString("x_swifi_merg_no"));
				wifi.setxSwifiWrdofc(rs.getString("x_swifi_wrdofc"));
				wifi.setxSwifiMainNm(rs.getString("x_swifi_main_nm"));
				wifi.setxSwifiAdres1(rs.getString("x_swifi_adres1"));
				wifi.setxSwifiAdres2(rs.getString("x_swifi_adres2"));
				wifi.setxSwifiInstlFloor(rs.getString("x_swifi_instl_floor"));
				wifi.setxSwifiInstlTy(rs.getString("x_swifi_instl_ty"));
				wifi.setxSwifiInstlMby(rs.getString("x_swifi_instl_mby"));
				wifi.setxSwifiSvcSe(rs.getString("x_swifi_svc_se"));
				wifi.setxSwifiCmcwr(rs.getString("x_swifi_cmcwr"));
				wifi.setxSwifiCnstcYear(rs.getInt("x_swifi_cnstc_year"));
				wifi.setxSwifiInoutDoor(rs.getString("x_swifi_inout_door"));
				wifi.setxSwifiRemars3(rs.getString("x_swifi_remars3"));
				wifi.setLnt(rs.getDouble("lnt"));
				wifi.setLat(rs.getDouble("lat"));
				wifi.setWorkDttm(rs.getString("work_dttm"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return wifi;
	}

	/**
	 * 공용 와이파이 Open API HTTP 요청
	 * 
	 * @param startIndex : 시작 인덱스
	 * @param endIndex   : 끝 인덱스
	 * @return 와이파이 응답 JSON string 데이터
	 */
	private String requestTbPublicWifi(int startIndex, int endIndex) {
		String tbPublicWifiUrl = "http://openapi.seoul.go.kr:8088";
		StringBuilder urlBuilder = new StringBuilder(tbPublicWifiUrl);
		BufferedReader rd = null;
		HttpURLConnection conn = null;
		StringBuilder sb = new StringBuilder();
		try {
			urlBuilder.append("/" + URLEncoder.encode("4d6b415a616a6a6b313238534c474a47", "UTF-8"));
			urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8"));
			urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8"));

			urlBuilder.append("/" + URLEncoder.encode(Integer.toString(startIndex), "UTF-8"));
			urlBuilder.append("/" + URLEncoder.encode(Integer.toString(endIndex), "UTF-8"));

			URL url = new URL(urlBuilder.toString());

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");

			if (conn.getResponseCode() == 200) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}

			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
			try {
				rd.close();
			} catch (IOException e) {
				e.printStackTrace();
				return "";
			}
			conn.disconnect();
		}

		return sb.toString();
	}

	/**
	 * 공용 WIFI DB Insert Batch
	 * 
	 * @return 총 데이터 row 수
	 */
	public int createTbPublicWifiBatch(Connection conn) {
		PreparedStatement pstmt = null;
		int maxQueryIndex = 1000;
		int startIndex = 1;
		int endIndex = maxQueryIndex;
		int totalRowCount = 0;

		String sql = "INSERT INTO tb_public_wifi (x_swifi_merg_no, " + "x_swifi_wrdofc, " + "x_swifi_main_nm,"
				+ " x_swifi_adres1, " + "x_swifi_adres2, " + "x_swifi_instl_floor, " + "x_swifi_instl_ty, "
				+ "x_swifi_instl_mby, " + "x_swifi_svc_se, " + "x_swifi_cmcwr, " + "x_swifi_cnstc_year, "
				+ "x_swifi_inout_door, " + "x_swifi_remars3, " + "lnt, " + "lat, "
				+ "work_dttm) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);

			while (true) {
				String wifiString = this.requestTbPublicWifi(startIndex, endIndex);
				Gson gson = new Gson();
				TbPublicWifiDto tbPublicWifiDto = gson.fromJson(wifiString, TbPublicWifiDto.class);

				totalRowCount = tbPublicWifiDto.getTbPublicWifiInfo().getListTotalCount();

				if (totalRowCount > startIndex && totalRowCount < endIndex) {
					maxQueryIndex = totalRowCount - startIndex + 1;
				}

				for (int i = 0; i < maxQueryIndex; i++) {

					pstmt.setString(1, tbPublicWifiDto.getTbPublicWifiInfo().getRow().get(i).getxSwifiMgrNo());
					pstmt.setString(2, tbPublicWifiDto.getTbPublicWifiInfo().getRow().get(i).getxSwifiWrdofc());
					pstmt.setString(3, tbPublicWifiDto.getTbPublicWifiInfo().getRow().get(i).getxSwifiMainNm());
					pstmt.setString(4, tbPublicWifiDto.getTbPublicWifiInfo().getRow().get(i).getxSwifiAdres1());
					pstmt.setString(5, tbPublicWifiDto.getTbPublicWifiInfo().getRow().get(i).getxSwifiAdres2());
					pstmt.setString(6, tbPublicWifiDto.getTbPublicWifiInfo().getRow().get(i).getxSwifiInstlFloor());
					pstmt.setString(7, tbPublicWifiDto.getTbPublicWifiInfo().getRow().get(i).getxSwifiInstlTy());
					pstmt.setString(8, tbPublicWifiDto.getTbPublicWifiInfo().getRow().get(i).getxSwifiInstlMby());
					pstmt.setString(9, tbPublicWifiDto.getTbPublicWifiInfo().getRow().get(i).getxSwifiSvcSe());
					pstmt.setString(10, tbPublicWifiDto.getTbPublicWifiInfo().getRow().get(i).getxSwifiCmcwr());

					if (tbPublicWifiDto.getTbPublicWifiInfo().getRow().get(i).getxSwifiCnstcYear().length() == 0) {
						pstmt.setInt(11, 0);
					} else {
						pstmt.setInt(11, Integer
								.parseInt(tbPublicWifiDto.getTbPublicWifiInfo().getRow().get(i).getxSwifiCnstcYear()));
					}

					pstmt.setString(12, tbPublicWifiDto.getTbPublicWifiInfo().getRow().get(i).getxSwifiInoutDoor());
					pstmt.setString(13, tbPublicWifiDto.getTbPublicWifiInfo().getRow().get(i).getxSwifiRemars3());
					pstmt.setDouble(14,
							Double.parseDouble(tbPublicWifiDto.getTbPublicWifiInfo().getRow().get(i).getLat()));
					pstmt.setDouble(15,
							Double.parseDouble(tbPublicWifiDto.getTbPublicWifiInfo().getRow().get(i).getLnt()));
					pstmt.setString(16, tbPublicWifiDto.getTbPublicWifiInfo().getRow().get(i).getWorkDttm());

					pstmt.addBatch();
				}

				pstmt.executeBatch();
				conn.commit();

				startIndex += maxQueryIndex;
				endIndex += maxQueryIndex;

				System.out.println(
						"startIndex:" + startIndex + ", endIndex: " + endIndex + " totalRowCount: " + totalRowCount);
				if (startIndex >= totalRowCount) {
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				e.printStackTrace();
				System.out.println("Insert Fail..");
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

//				if (conn != null && !conn.isClosed()) {
//					conn.close();
//					System.out.println("success connection close");
//				}
			} catch (SQLException e) {
				e.printStackTrace();
				return -2;
			}
		}

		return totalRowCount;
	}

	/**
	 * 공용 WIFI 정보 print
	 * 
	 * @param wifi
	 * @param index
	 */
	public void printTbPublicWifiInfo(TbPublicWifiInfo wifi, int index) {
		System.out.println("-------------------------------");
		System.out.println(wifi.getRow().get(index).getxSwifiMgrNo());
		System.out.println(wifi.getRow().get(index).getxSwifiWrdofc());
		System.out.println(wifi.getRow().get(index).getxSwifiMainNm());
		System.out.println(wifi.getRow().get(index).getxSwifiAdres1());
		System.out.println(wifi.getRow().get(index).getxSwifiAdres2());
		System.out.println(wifi.getRow().get(index).getxSwifiInstlFloor());
		System.out.println(wifi.getRow().get(index).getxSwifiInstlTy());
		System.out.println(wifi.getRow().get(index).getxSwifiInstlMby());
		System.out.println(wifi.getRow().get(index).getxSwifiSvcSe());
		System.out.println(wifi.getRow().get(index).getxSwifiCmcwr());
		System.out.println(wifi.getRow().get(index).getxSwifiCnstcYear());
		System.out.println(wifi.getRow().get(index).getxSwifiInoutDoor());
		System.out.println(wifi.getRow().get(index).getxSwifiRemars3());
		System.out.println(wifi.getRow().get(index).getLat());
		System.out.println(wifi.getRow().get(index).getLnt());
		System.out.println(wifi.getRow().get(index).getWorkDttm());
	}

}
