<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%--
	<%@ include file = "nav.jsp" %>
 --%>
 	<jsp:include page = "nav.jsp" flush = "true"/>
	<h2>Body2</h2>
	<div>내용2</div>
	
</body>
</html>