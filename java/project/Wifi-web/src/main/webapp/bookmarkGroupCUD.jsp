<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.utils.RdbmsConnector" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.bookmark.service.GroupBookmarkService" %>
<!DOCTYPE html>
<html>
	<%
		String method = request.getParameter("method");
	%>
	<head>
		<meta charset="UTF-8">
		<title>북마크 그룹 <%= method %></title>
	</head>
	<body>
		<%
			request.setCharacterEncoding("UTF-8");
			RdbmsConnector rConn = new RdbmsConnector("localhost", 23306, "wifi", "wifi1234", "wifi");
			Connection conn = rConn.getRdbmsConnection();
			GroupBookmarkService groupBmService = new GroupBookmarkService();
			String name = "";
			String bookmarkOrder = "";
			int id = 0;
			int order = 0;
			int affected = 0;

			if (method.equals("add")) {
				name = request.getParameter("bookmarkName");
				bookmarkOrder = request.getParameter("bookmarkOrder");
				System.out.println("bookmarkName: " + name + ", bookmarkOrder: " + bookmarkOrder);
				order = Integer.parseInt(bookmarkOrder);
				affected = groupBmService.createGroupBookmark(conn, name, order);
			} else if (method.equals("modify")) {
				id = Integer.parseInt(request.getParameter("id"));
				name = request.getParameter("bookmarkName");
				bookmarkOrder = request.getParameter("bookmarkOrder");
				order = Integer.parseInt(bookmarkOrder);
				System.out.println("id: " + id + ", bookmarkName: " + name + ", bookmarkOrder: " + bookmarkOrder);
				affected = groupBmService.updateGroupBookmark(conn, id, name, order);
			} else if (method.equals("delete")) {
				id = Integer.parseInt(request.getParameter("id"));
				affected = groupBmService.deleteGroupBookmark(conn, id);
			}
		%>	
	</body>
	<script type="text/javascript">
		<% if(method.equals("add") && affected == 1) { %>
			alert("북마크 그룹 정보를 추가하였습니다.");
			location.href = "bookmarkGroupList.jsp";
		<% } else if (method.equals("modify") && affected == 1) { %>
			alert("북마크 그룹 정보를 수정하였습니다.");
			location.href = "bookmarkGroupList.jsp";
		<% } else if (method.equals("delete") && affected == 1) { %>
			alert("북마크 그룹 정보를 삭제하였습니다.");
			location.href = "bookmarkGroupList.jsp";
		<% } else { %>
			alert("북마크 그룹에 북마크가 존재하여 삭제가 불가능 합니다.");
			location.href = "bookmarkGroupList.jsp";
		<% } %>
	</script>
</html>