<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Connection" %>
<%@	page import="com.utils.RdbmsConnector" %>
<%@	page import="com.wifi.model.LocationHistory" %>
<%@ page import="com.wifi.service.LocationHistoryService" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>위치 히스토리 목록</title>
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
	</head>
	<body>
		<%
			RdbmsConnector rConn = new RdbmsConnector("localhost", 23306, "wifi", "wifi1234", "wifi");	
			Connection conn = rConn.getRdbmsConnection();
			LocationHistoryService lcHistory = new LocationHistoryService();
			List<LocationHistory> lhList = lcHistory.getLocationHistoryList(conn);
		%>
		<h1>위치 히스토리 목록</h1>
		<div>
			<a href="home.jsp">홈</a> | <a href="locationHistory.jsp">위치 히스토리 목록</a> | <a href="openApiWifiInfo.jsp">Open API 와이파이 정보 가져오기</a> | <a href="bookmarkList.jsp">북마크 보기</a> | <a href="bookmarkGroupList.jsp">북마크 그룹 관리</a>
		</div>
		<br>
		<div>
			<table>
				<thead>
					<tr class="title-tr">
						<td>ID</td>
						<td>X좌표</td>
						<td>Y좌표</td>
						<td>조회일자</td>
						<td>비고</td>
					</tr>
				</thead>
				<tbody>
					<% for (LocationHistory lh : lhList) { %>
					<tr>
						<td><%= lh.getId() %></td>
						<td><%= lh.getLat() %></td>
						<td><%= lh.getLnt() %></td>
						<td><%= lh.getCreatedAt() %></td>
						<td align="center">
							<button onclick="location.href='locationHistoryDelete.jsp?id=<%=lh.getId()%>'">삭제</button>
						</td>
					</tr>
					<% } %>
				</tbody>	
			</table>
		</div>
	</body>
</html>