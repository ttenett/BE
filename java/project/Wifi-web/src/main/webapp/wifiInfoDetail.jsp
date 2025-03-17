<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.List" %>
<%@ page import="com.utils.RdbmsConnector" %>
<%@ page import="com.wifi.service.TbPublicWifiInfoService" %>
<%@ page import="com.bookmark.service.GroupBookmarkService" %>
<%@ page import="com.wifi.model.Wifi" %>
<%@ page import="com.bookmark.model.GroupBookmark" %>
<!DOCTYPE html>
<html>
	<%
		String xSwifiMergNo = request.getParameter("xSwifiMergNo");
		RdbmsConnector rConn = new RdbmsConnector("localhost", 23306, "wifi", "wifi1234", "wifi");
		Connection conn = rConn.getRdbmsConnection();
		TbPublicWifiInfoService wifiService = new TbPublicWifiInfoService();
		Wifi wifi = wifiService.getTbPublicWifiDetail(conn, xSwifiMergNo);
		
		// 북마크 그룹 선택
		conn =  rConn.getRdbmsConnection();	
		GroupBookmarkService gbService = new GroupBookmarkService();
		List<GroupBookmark> gbList = gbService.getGroupBookmarkList(conn);
	%>	
	<head>
		<meta charset="UTF-8">
		<title>와이파이 정보 구하기</title>
		<style type="text/css">
			table, th, td {
				border: 1px solid;
			}
			table {
	  			width: 100%;
	  			border-collapse: collapse;
			}
			
			.title-td {
				text-align: center;
				color: #FFFFFF;	
				background-color: #088A08;
			}
			
			td:nth-child(even) {background-color: #f2f2f2;}
		</style>
	</head>
	<body>
		<h1>와이파이 정보 구하기</h1>
		<div>
			<a href="home.jsp">홈</a> | <a href="locationHistory.jsp">위치 히스토리 목록</a> | <a href="openApiWifiInfo.jsp">Open API 와이파이 정보 가져오기</a> | <a href="bookmarkList.jsp">북마크 보기</a> | <a href="bookmarkGroupList.jsp">북마크 그룹 관리</a>
		</div>
		<div>
			<form action="bookmarkCUD.jsp?method=add" accept-charset="UTF-8" method="post">
				<select id="bookmarkGroup" name="bookmarkGroup">
					<option value="0">북마크 그룹이름 선택</option>	
					<% for (GroupBookmark gb : gbList) { %>
						<option value="<%= gb.getId() %>"><%= gb.getName() %></option>	
					<% } %>
				</select>
				<input type="hidden" name="xSwifiMainNm" value=<%=wifi.getxSwifiMainNm()%>></input>
				<button type="submit">북마크 추가</button>
			</form>
		</div>
		<div>
			<table>
				<colgroup class="title-td" width=30%></colgroup>
				<colgroup width=70%></colgroup>
				<tbody>
					<tr>
						<th>거리(Km)</th>
						<td><%=wifi.getDistance()%></td>
					</tr>
					<tr>
						<th>관리번호</th>
						<td><%=wifi.getxSwifiMergNo().replace("-", "")%></td>
					</tr>
					<tr>
						<th>자치구</th>
						<td><%=wifi.getxSwifiWrdofc()%></td>
					</tr>
					<tr>
						<th>와이파이명</th>
						<td><a href="#"><%=wifi.getxSwifiMainNm()%></a></td>
					</tr>
					<tr>
						<th>도로명주소</th>
						<td><%=wifi.getxSwifiAdres1()%></td>
					</tr>
					<tr>
						<th>상세주소</th>
						<td><%=wifi.getxSwifiAdres2()%></td>
					</tr>
					<tr>
						<th>설치위치(층)</th>
						<td><%=wifi.getxSwifiInstlFloor()%></td>
					</tr>
					<tr>
						<th>설치유형</th>
						<td><%=wifi.getxSwifiInstlTy()%></td>
					</tr>
					<tr>
						<th>설치기관</th>
						<td><%=wifi.getxSwifiInstlMby()%></td>
					</tr>
					<tr>
						<th>서비스구분</th>
						<td><%=wifi.getxSwifiSvcSe()%></td>
					</tr>
					<tr>
						<th>망종류</th>
						<td><%=wifi.getxSwifiCmcwr()%></td>
					</tr>
					<tr>
						<th>설치년도</th>
						<td><%=wifi.getxSwifiCnstcYear()%></td>
					</tr>
					<tr>
						<th>실내외구분</th>
						<td><%=wifi.getxSwifiInoutDoor()%></td>
					</tr>
					<tr>
						<th>WIFI접속환경</th>
						<td><%=wifi.getxSwifiRemars3()%></td>
					</tr>
					<tr>
						<th>X좌표</th>
						<td><%=wifi.getLnt()%></td>
					</tr>
					<tr>
						<th>Y좌표</th>
						<td><%=wifi.getLat()%></td>
					</tr>
					<tr>
						<th>작업일자</th>
						<td><%=wifi.getWorkDttm()%></td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>