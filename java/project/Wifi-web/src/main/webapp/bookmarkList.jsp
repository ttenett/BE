<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.List" %>
<%@ page import="com.utils.RdbmsConnector" %>
<%@ page import="com.bookmark.model.Bookmark" %>
<%@ page import="com.bookmark.service.BookmarkService" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>북마크 목록</title>
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
			BookmarkService bmService = new BookmarkService();
			List<Bookmark> bmList = bmService.getBookmarkList(conn);
		%>
		<h1>북마크 목록</h1>
		<div>
			<a href="home.jsp">홈</a> | <a href="locationHistory.jsp">위치 히스토리 목록</a> | <a href="openApiWifiInfo.jsp">Open API 와이파이 정보 가져오기</a> | <a href="bookmarkList.jsp">북마크 보기</a> | <a href="bookmarkGroupList.jsp">북마크 그룹 관리</a>
		</div><br>
		<div>
			<table>
				<thead>
					<tr class="title-tr">
						<td>ID</td>
						<td>북마크 이름</td>
						<td>와이파이명</td>
						<td>등록일자</td>
						<td>비고</td>
					</tr>	
				</thead>
				<tbody>
					<%
						for(Bookmark bm : bmList) {	
					%>
					<tr>
						<td><%=bm.getGroupBookmarkId()%></td>
						<td><%=bm.getName()%></td>
						<td><a href="#"><%=bm.getxSwifiMainNm()%></a></td>
						<td><%=bm.getCreatedAt()%></td>
						<td><a href="bookmarkDelete.jsp?id=<%=bm.getId()%>">삭제</a></td>
					</tr>
					<% } %>
				</tbody>	
			</table>
		</div>
	</body>
</html>