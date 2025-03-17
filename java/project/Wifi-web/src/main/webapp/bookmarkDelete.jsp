<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.utils.RdbmsConnector" %>
<%@ page import="com.bookmark.model.Bookmark" %>
<%@ page import="com.bookmark.service.BookmarkService" %>
<!DOCTYPE html>
<html>
	<%
		int	id = Integer.parseInt(request.getParameter("id"));
		RdbmsConnector rConn = new RdbmsConnector("localhost", 23306, "wifi", "wifi1234", "wifi");
		Connection conn = rConn.getRdbmsConnection();
		BookmarkService bmService = new BookmarkService();
		Bookmark bm = bmService.getBookmarkDetail(conn, id);		
	%>
	<head>
		<meta charset="UTF-8">
		<title>북마크 삭제</title>
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
		<h1>북마크 삭제</h1>
		<div>
			<a href="home.jsp">홈</a> | <a href="locationHistory.jsp">위치 히스토리 목록</a> | <a href="openApiWifiInfo.jsp">Open API 와이파이 정보 가져오기</a> | <a href="bookmarkList.jsp">북마크 보기</a> | <a href="bookmarkGroupList.jsp">북마크 그룹 관리</a>
		</div><br>		
		
		<div>
			<label>북마크를 삭제 하시겠습니까?</label>
		</div><br>
		
		<div>
			<form action="bookmarkCUD.jsp?method=delete" method="post" accept-charset="UTF-8">
				<input type="hidden" name="id" value="<%=bm.getId()%>"></input>
				<table>
					<colgroup class="title-td" width=30%></colgroup>
					<colgroup width=70%></colgroup>
					<tbody>
						<tr>
							<th>북마크 이름</th>
							<td><%=bm.getName()%></td>
						</tr>
						<tr>
							<th>와이파이명</th>
							<td><%=bm.getxSwifiMainNm()%></td>
						</tr>
						<tr>
							<th>등록일자</th>
							<td><%=bm.getCreatedAt()%></td>
						</tr>
						<tr>
							<td class="bottom-td" colspan='2'>
								<a href="bookmarkList.jsp">돌아가기</a> | 
								<button>삭제</button>
							</td>
						</tr>
					</tbody>
				</table>	
			</form>
		</div>
	</body>
</html>