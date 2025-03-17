<%@page import="com.utils.RdbmsConnector"%>
<%@page import="com.wifi.service.TbPublicWifiInfoService"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Open API 와이파이 정보 가져오기</title>
		<style>
			.container {
				text-align: center;
			}
		</style>
	</head>
	<body>
	 	<%
			RdbmsConnector rdbmsConnector = new RdbmsConnector("localhost", 23306, "wifi", "wifi1234", "wifi");
			Connection conn = rdbmsConnector.getRdbmsConnection();
	 		TbPublicWifiInfoService wifiService = new TbPublicWifiInfoService();
	 		int count = wifiService.getTbPublicWifiAllCount(conn);	
	 		
	 		if (count == 0) {
				conn = rdbmsConnector.getRdbmsConnection();
				wifiService.createTbPublicWifiBatch(conn);
				count = wifiService.getTbPublicWifiAllCount(conn);
	 		}
	 	%>
	
		<div class="container">
			<h1><%=count%>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
			<a href="home.jsp">홈 으로 가기</a>
		</div>	
	</body>
</html>