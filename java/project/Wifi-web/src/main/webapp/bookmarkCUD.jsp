<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.utils.RdbmsConnector" %>
<%@ page import="com.bookmark.service.BookmarkService" %>
<!DOCTYPE html>
<html>
	<%
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");
		RdbmsConnector rConn = new RdbmsConnector("localhost", 23306, "wifi", "wifi1234", "wifi");
		Connection conn = rConn.getRdbmsConnection();
		BookmarkService bmService = new BookmarkService();
		int affected = 0;
		String xSwifiMainNm = "";
		int groupBookmarkId = 0;	
		
		if (method.equals("add")) {
			groupBookmarkId = Integer.parseInt(request.getParameter("bookmarkGroup"));	
			xSwifiMainNm = request.getParameter("xSwifiMainNm");
			if (groupBookmarkId != 0) {
				affected = bmService.createBookmark(conn, groupBookmarkId, xSwifiMainNm);
			}
		} else {
			int id = Integer.parseInt(request.getParameter("id"));
			affected = bmService.deleteBookmark(conn, id);
		}
	%>
	<script type="text/javascript">
		<% if (method.equals("add") && affected == 1) { %>
			alert("북마크 정보를 추가 하였습니다.");
		<% } else if (method.equals("delete") && affected == 1){ %>
			alert("북마크 정보를 삭제 하였습니다.");
		<% } else { %>
			alert("북마크 정보를 선택해 후 추가 해주세요.");
		<% } %>

		location.href = "bookmarkList.jsp";
	</script>
	<head>
		<meta charset="UTF-8">
		<title>북마크 <%= method %></title>
	</head>
	<body>
	</body>
</html>