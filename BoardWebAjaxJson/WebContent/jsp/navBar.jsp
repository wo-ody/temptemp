<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "user.dto.*, java.util.*" %>
<%
    String contextPath = request.getContextPath();
    UserDto userDto = (UserDto) session.getAttribute("userDto");
%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container">
    <a class="navbar-brand" href="#"><img src="<%= contextPath + userDto.getUserProfileImageUrl() %>" alt="Logo" style="width:24px; height: 24px; border-radius: 50%;"></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                <%= userDto.getUserName() %>
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="<%= contextPath%>/jsp/user/userMain.jsp">내 정보 수정하기</a></li><!-- jsp 직접 링크  -->
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="javascript:logout('<%= contextPath%>')">Logout</a></li><!-- util.js 에 -->
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link" aria-current="page" href="<%= contextPath%>/board/boardMain">게시판</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Link</a>
        </li>
      </ul>
      <!-- 오른쪽에 붙게 된다.  -->
      <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" id="btnLogout">right Link</a>
        </li>
      </ul>
    </div>
  </div>
</nav>