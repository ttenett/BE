<%@page import="db.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		System.out.println("Hello1");
		
		out.write("Hello2");
		
		MemberService memberService = new MemberService();
        memberService.dbSelect();
	
	%>

</body>
</html>