<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String attr1 = (String) request.getAttribute("attr1");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Maintain Data JSP</h1>
	<h2>attr1 : <%= attr1%></h2>
</body>
</html>