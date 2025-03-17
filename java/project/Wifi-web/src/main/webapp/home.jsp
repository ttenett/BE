<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.List" %>
<%@ page import="com.utils.RdbmsConnector" %>
<%@ page import="com.wifi.model.Wifi" %>
<%@ page import="com.wifi.service.TbPublicWifiInfoService" %>
<%@ page import="com.wifi.service.LocationHistoryService" %>
<!DOCTYPE html>
<html>
	<%
		String lat = request.getParameter("lat");
		String lnt = request.getParameter("lnt");
		int count = 0;
		List<Wifi> wifiList = null;

		if (request.getParameter("lat") != null && request.getParameter("lnt") != null) {
 			RdbmsConnector rConn = new RdbmsConnector("localhost", 23306, "wifi", "wifi1234", "wifi");
			Connection conn = rConn.getRdbmsConnection();
			TbPublicWifiInfoService wifiService = new TbPublicWifiInfoService();
			wifiList = wifiService.getTbPublicWifiList(conn, Double.parseDouble(lat), Double.parseDouble(lnt));
			count = wifiList.size();
			
			// 위치 히스토리 목록 저장
			conn = rConn.getRdbmsConnection();
			LocationHistoryService lcHistory = new LocationHistoryService();
			lcHistory.createLocationHistory(conn, Double.parseDouble(lat), Double.parseDouble(lnt));
		} else {
			lat = "0.0";
			lnt = "0.0";
		}

	%>
	<head>
		<meta charset="UTF-8">
		<title>와이파이 정보 구하기</title>
		<style type="text/css">
			table, th, td {
				border: 1px solid;
			}
	
	 		table {
				border: 1px solid;
	  			width: 100%;
	  			border-collapse: collapse;
			}
	
			.title-tr {
				text-align: center;
				color: #FFFFFF;	
				background-color: #088A08;
			}
		</style>
		<script>
			function successLocation(position) {
				document.getElementById('lat').value = position.coords.latitude;
				document.getElementById('lnt').value = position.coords.longitude;
			}
			
			function getMyLocation() {
				if (!navigator.geolocation) {
					alert("위치 정보가 지원되지 않습니다.");
					return;
				}
		
				navigator.geolocation.getCurrentPosition(successLocation);
			}
			
			// 근처 WIFI 정보 보기
			function getClosedWifiInfo() {
				const lat = document.getElementById("lat").value;
				const lnt = document.getElementById("lnt").value;
				
				if (lat == 0.0 && lnt == 0.0) {
					alert("위치 정보를 입력한 후에 조회해 주세요.")
					return;
				}
				
				location.href="home.jsp?lat=" + lat + "&lnt=" + lnt;  
			}
		
		</script>
	</head>
	<body>
		<h1>와이파이 정보 구하기</h1>
		<div>
			<a href="home.jsp">홈</a> | <a href="locationHistory.jsp">위치 히스토리 목록</a> | <a href="openApiWifiInfo.jsp">Open API 와이파이 정보 가져오기</a> | <a href="bookmarkList.jsp">북마크 보기</a> | <a href="bookmarkGroupList.jsp">북마크 그룹 관리</a>
		</div>
		<br>
		<div>
			<label>LAT:</label>
			<input id="lat" value=<%=lat%>></input>&nbsp;,
			<label>LNT:</label>
			<input id="lnt" value=<%=lnt%>></input>&nbsp;,
			<button onClick="getMyLocation()">내 위치 가져오기</button>
			<button onClick="getClosedWifiInfo()">근처 WIFI 정보 보기</button>
		</div>
		<br>
		<div>
			<table>
				<thead>
					<tr class="title-tr">
						<td>거리(Km)</td>
						<td>관리번호</td>
						<td>자치구</td>
						<td>와이파이명</td>
						<td>도로명주소</td>
						<td>상세주소</td>
						<td>설치위치(층)</td>
						<td>설치유형</td>
						<td>설치기관</td>
						<td>서비스구분</td>
						<td>망종류</td>
						<td>설치년도</td>
						<td>실내외구분</td>
						<td>WIFI접속환경</td>
						<td>X좌표</td>
						<td>Y좌표</td>
						<td>작업일자</td>
					</tr>
				</thead>
				<tbody id="wifiList">
					<%
						if (count > 0) {
							for(Wifi wifi : wifiList) {
					%>
					<tr>
						<td><%=wifi.getDistance()%></td>
						<td><%=wifi.getxSwifiMergNo().replace("-", "")%></td>
						<td><%=wifi.getxSwifiWrdofc()%></td>
						<td>
							<a href="wifiInfoDetail.jsp?xSwifiMergNo=<%=wifi.getxSwifiMergNo()%>"><%=wifi.getxSwifiMainNm()%></a>
						</td>
						<td><%=wifi.getxSwifiAdres1()%></td>
						<td><%=wifi.getxSwifiAdres2()%></td>
						<td><%=wifi.getxSwifiInstlFloor()%></td>
						<td><%=wifi.getxSwifiInstlTy()%></td>
						<td><%=wifi.getxSwifiInstlMby()%></td>
						<td><%=wifi.getxSwifiSvcSe()%></td>
						<td><%=wifi.getxSwifiCmcwr()%></td>
						<td><%=wifi.getxSwifiCnstcYear()%></td>
						<td><%=wifi.getxSwifiInoutDoor()%></td>
						<td><%=wifi.getxSwifiRemars3()%></td>
						<td><%=wifi.getLnt()%></td>
						<td><%=wifi.getLat()%></td>
						<td><%=wifi.getWorkDttm()%></td>
					</tr>
					<%
							}
						} else {
					%>
					<tr>
						<td colspan='17' style="text-align:center;">위치 정보를 입력한 후에 조회해 주세요.</td>
					</tr>
					<%
						}
					%>
				</tbody>		
			</table>
		</div>
	</body>
</html>