<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String value = (String) request.getParameter("name");
	
	List<String> strList = new ArrayList<>();
	strList.add("A");
	strList.add("B");
	strList.add("C");
%>
<h4>parameter : <%= value %> parameter : <c:out value="${param.name2}"/></h4>

<%
	if( value.equals("JSTL") ){

%>
	<p>JSTL</p>
<%
	}
%>

<c:if test="${ param.name2 eq 'JSTL2'}">
	<p>JSTL2</p>
</c:if>


</body>
</html>