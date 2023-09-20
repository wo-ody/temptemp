<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>깔끔한 에러 페이지 입니다.</h1>
<%
	if( exception == null ){
		
%>
	<h4><%= request.getAttribute("exception") %></h4>
<% 
	}else{
%>
	<h4><%= exception %></h4>
<%
	}
%>
	<p>죄송 주절 나불</p>
</body>
</html>