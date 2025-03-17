<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.utils.RdbmsConnector"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.bookmark.service.GroupBookmarkService"%>
<%@page import="com.bookmark.model.GroupBookmark"%>
<!DOCTYPE html>
<html>
	<%
		String id = request.getParameter("id");
		RdbmsConnector rConn = new RdbmsConnector("localhost", 23306, "wifi", "wifi1234", "wifi");
		Connection conn = rConn.getRdbmsConnection();
		GroupBookmarkService groupBmService = new GroupBookmarkService();
		GroupBookmark gb = groupBmService.getGroupBookmarkDetail(conn, Integer.parseInt(id));
	%>
	<head>
		<meta charset="UTF-8">
		<title>북마크 그룹 수정</title>
		<style type="text/css">
					table, th, td {
				border: 1px solid;
			}
	
	 		table {
				border: 1px solid;
	  			width: 100%;
	  			border-collapse: collapse;
			}
	
			.title-td {
				text-align: center;
				color: #FFFFFF;	
				background-color: #088A08;
			}
			
			.bottom-td {
				background-color: #FFFFFF;
				text-align: center;
			}
		</style>
	</head>
	<body>
		<h1>북마크 그룹 수정</h1>
		<div>
			<a href="home.jsp">홈</a> | <a href="locationHistory.jsp">위치 히스토리 목록</a> | <a href="openApiWifiInfo.jsp">Open API 와이파이 정보 가져오기</a> | <a href="bookmarkList.jsp">북마크 보기</a> | <a href="bookmarkGroupList.jsp">북마크 그룹 관리</a>
		</div><br>	
		<div>
			<form action="bookmarkGroupCUD.jsp?method=modify&id=<%=id%>" method="post">
				<input type="hidden" name="id" value=<%=id%>/>
				<table>
					<colgroup class="title-td" width=30%></colgroup>
					<colgroup width=70%></colgroup>
					<tbody>
						<tr>
							<th>북마크 이름</th>
							<td>
								<input type="text" id="bookmarkName" name="bookmarkName" value=<%=gb.getName()%>></input>
							</td>
						</tr>
						<tr>
							<th>순서</th>
							<td>
								<input type="text" id="bookmarkOrder" name="bookmarkOrder" value=<%=gb.getOrder()%>></input>
							</td>
						</tr>
						<tr>
							<td class="bottom-td" colspan='2'>
								<a href="bookmarkGroupList.jsp">돌아가기</a> | 
								<button>수정</button>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</body>
</html>