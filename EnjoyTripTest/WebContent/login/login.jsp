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
	
	<h1>ajax 로그인</h1>
	<form><!-- design 이 깨지면 form tag 만 유지한다. hidden 도 삭제 javascript 에서 처리-->
		<input id = "userEmail" type ="text" name = "userEmail"><br>
		<input id = "userPassword" type ="password" name = "userPassword"><br>
	</form>
	<input id = "btnLogin" type ="button" value="로그인"> <!-- 전송 버튼은 form tag 밖으로, type =button -->
<script>
	window.onload = function(){
		document.querySelector("#btnLogin").onclick = login;
	};
	
	async function login(){
		// 사용자가 입력한 파라미터 준비
		// 백엔드 url 요청 - fetch post
		// 결과 : json
		// 성공 / 실패
		
		let userEmail = document.querySelector("#userEmail").value;
		let userPassword = document.querySelector("#userPassword").value;
		
		let urlParams = new URLSearchParams({
			job : 'login_ajax',
			userEmail : userEmail,
			userPassword : userPassword
		});
		let fetchOptions = {
			method : "POST",
			body : urlParams
		};
		
		let response = await fetch("/loginout", fetchOptions);
		let data = await response.json();
		console.log(data);
		
		if( data.result == "success"){
			window.location.href = "/main";
		}else if(data.result == "fail"){
			alert("아이디 또는 비밀번호가 틀립니다.")
		}
	};
</script>

</body>
</html>