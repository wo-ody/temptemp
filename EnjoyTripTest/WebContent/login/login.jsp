<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>로그인</h1>
	<form action="/loginout" method = "post">
		<input type ="hidden" name = "job" value="login"><br>
		<input type ="text" name = "userEmail"><br>
		<input type ="password" name = "userPassword"><br>
		<input type ="submit" value="로그인">
	</form>
</body>
</html>