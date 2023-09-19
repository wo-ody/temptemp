<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <title>키워드로 장소검색하고 목록으로 표출하기</title>

    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
      crossorigin="anonymous"
    />
    <link href="../css/main.css" rel="stylesheet" />
  </head>
  <body>
    <!-- Grey with black text -->
    <nav class="navbar navbar-expand-sm bg-light navbar-light">
      <div class="container">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a
              class="nav-link active"
              style="font-weight: bold"
              href="./main.html"
              >ShyTrip</a
            >
          </li>
          <li class="nav-item">
            <a class="nav-link" href="./login.html">로그인</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="./register.jsp">회원가입</a>
          </li>
        </ul>
      </div>
    </nav>
    <div class="map_wrap">
      <div
        id="map"
        style="width: 100%; height: 100%; position: relative; overflow: hidden"
      ></div>

      <div id="menu_wrap" class="bg_white">
        <div class="option">
          <div>
            <form onsubmit="searchPlaces(); return false;">
              키워드 :
              <input type="text" value="이태원 맛집" id="keyword" size="15" />
              <button type="submit">검색하기</button>
            </form>
          </div>
        </div>
        <hr />
        <ul id="placesList"></ul>
        <div id="pagination"></div>
      </div>
    </div>
    <script></script>
    <script
      type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f3960318c3e2bbc089cb48c0822daeb7&libraries=services"
    ></script>
    <script src="../js/map.js"></script>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
