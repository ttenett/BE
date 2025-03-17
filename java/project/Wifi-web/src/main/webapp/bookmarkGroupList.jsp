<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.utils.RdbmsConnector" %>
<%@ page import="com.bookmark.service.GroupBookmarkService" %>
<%@ page import="com.bookmark.model.GroupBookmark" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>북마크 그룹</title>
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
			GroupBookmarkService groupBmService = new GroupBookmarkService();
			List<GroupBookmark> gbList = groupBmService.getGroupBookmarkList(conn);
		%>
		<h1>북마크 그룹</h1>
		<div>
			<a href="home.jsp">홈</a> | <a href="locationHistory.jsp">위치 히스토리 목록</a> | <a href="openApiWifiInfo.jsp">Open API 와이파이 정보 가져오기</a> | <a href="bookmarkList.jsp">북마크 보기</a> | <a href="bookmarkGroupList.jsp">북마크 그룹 관리</a>
		</div><br>
		<div>
			<button onClick="location.href='bookmarkGroupAdd.jsp'">북마크 그룹 추가</button>
			<table>
				<thead>
					<tr class="title-tr">
						<td>ID</td>
						<td>북마크 이름</td>
						<td>순서</td>
						<td>등록일자</td>
						<td>수정일자</td>
						<td>비고</td>
					</tr>
				</thead>
				<tbody>
					<% for (GroupBookmark gb : gbList) { %>
					<tr>
						<td><%=gb.getId()%></td>	
						<td><%=gb.getName()%></td>	
						<td><%=gb.getOrder()%></td>	
						<td><%=gb.getCreatedAt()%></td>	
						<td><%=gb.getUpdatedAt()%></td>	
						<td>
							<a href="bookmarkGroupModify.jsp?id=<%=gb.getId()%>">수정</a>
							<a href="bookmarkGroupDelete.jsp?method=delete&id=<%=gb.getId()%>">삭제</a>
						</td>	
					</tr>	
					<% } %>
				</tbody>
			</table>	
		</div>
	</body>
</html>