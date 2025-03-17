<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.utils.RdbmsConnector" %>
<%@ page import="com.wifi.service.LocationHistoryService" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>위치 히스토리 삭제</title>
	</head>
	<body>
		<%
			int id = Integer.parseInt(request.getParameter("id"));
			int affected = 0;
			RdbmsConnector rConn = new RdbmsConnector("localhost", 23306, "wifi", "wifi1234", "wifi");
			Connection conn = rConn.getRdbmsConnection();
			LocationHistoryService lhService = new LocationHistoryService();
			affected = lhService.deleteLocationHistory(conn, id);
		%>	
	</body>
	<script type="text/javascript">
		<% if (affected == 1) { %>
			alert("위치 히스토리 정보를 삭제 하였습니다.")
		<% } else { %>
			alert("위치 히스토리 정보를 미삭제 하였습니다.")
		<% } %>

		location.href = "locationHistory.jsp";	
	</script>
</html>