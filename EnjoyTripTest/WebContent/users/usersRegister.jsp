<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원가입</h1>
	<form action="/users">
		<input type ="hidden" name = "job" value="A"><br>
		<input type ="text" name = "userName"><br>
		<input type ="text" name = "userEmail"><br>
		<input type ="text" name = "userPassword"><br>
		<input type ="submit" value="등록">
	</form>
</body>
</html>