<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% System.out.println("index.jsp 페이지 sysout"); %>
	<h1>index.jsp</h1>
	<jsp:forward page="/testInterceptor.do"/>
</body>
</html>