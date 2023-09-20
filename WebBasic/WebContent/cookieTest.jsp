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
	Cookie[] cookies = request.getCookies();
	for(Cookie cookie : cookies){
		if(cookie != null ){
%>
			<h4> 쿠기 : <%=cookie.getName() %> <%=cookie.getValue() %></h4>
<% 
		}
	}
%>

<button onclick = "makeCookie();">쿠기 만들기</button>
<script>
	function makeCookie(){
		document.cookie = "c3=v3";
		
	}
</script>
</body>
</html>