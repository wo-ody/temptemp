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
            <a class="nav-link" href="./html/register.jsp ">회원가입</a>
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
              <input type="text" value="이태원 맛집" id="keyword" size="15" name="keyword"/>
              <button type="submit">검색하기</button>
            </form>
          </div>
        </div>
        <hr />
        <ul id="placesList"></ul>
        <div id="pagination"></div>
      </div>
    </div>
    
    
    <script
      type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f3960318c3e2bbc089cb48c0822daeb7&libraries=services"
    ></script>
    <script>
 // 마커를 담을 배열입니다
 
 	

    var markers = [];

    var mapContainer = document.getElementById("map"), // 지도를 표시할 div
      mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 3, // 지도의 확대 레벨
      };
    // 지도를 생성합니다
    var map = new kakao.maps.Map(mapContainer, mapOption);

    // 장소 검색 객체를 생성합니다
    var ps = new kakao.maps.services.Places();

    // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
    var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });

    // 키워드로 장소를 검색합니다
    //searchPlaces();

    // 키워드 검색을 요청하는 함수입니다
    async function searchPlaces() {
    try {
        var keyword = document.getElementById('keyword').value;
        let response = await fetch('/main?action=map&keyword=' + keyword);
        
        console.log(response);
        
        if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);
        
        let places = await response.json();
        console.log("장소들 출력 --------------")
        //console.log(places);
       // places.forEach(place=> {
        	//console.log("하나씩 장소 찍기");
        	//console.log(place);
           // addMarker(new kakao.maps.LatLng(place.latitude, place.longitude));
       // });
        
        displayPlaces(places);
        map.relayout();
    } catch (error) {
        console.error('Fetching or processing data failed', error);
    }
}

    // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
    function placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        // 정상적으로 검색이 완료됐으면
        // 검색 목록과 마커를 표출합니다
        displayPlaces(data);

        // 페이지 번호를 표출합니다
        displayPagination(pagination);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        alert("검색 결과가 존재하지 않습니다.");
        return;
      } else if (status === kakao.maps.services.Status.ERROR) {
        alert("검색 결과 중 오류가 발생했습니다.");
        return;
      }
    }

    // 검색 결과 목록과 마커를 표출하는 함수입니다
    function displayPlaces(places) {
    console.log("Hello");
      var listEl = document.getElementById("placesList"),
        menuEl = document.getElementById("menu_wrap"),
        fragment = document.createDocumentFragment(),
        bounds = new kakao.maps.LatLngBounds(),
        listStr = "";

      // 검색 결과 목록에 추가된 항목들을 제거합니다
      removeAllChildNods(listEl);

      // 지도에 표시되고 있는 마커를 제거합니다
      removeMarker();

      console.log("장소들 길이 " + places.length);
      const limit = places.length < 15 ? places.length : 15;
      for (var i = 0; i < limit; i++) {
        // 마커를 생성하고 지도에 표시합니다
        console.log(places[i].longitude, places[i].latitude);
        var placePosition = new kakao.maps.LatLng(places[i].latitude, places[i].longitude),
          marker = addMarker(placePosition, i),
          itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        //console.log("지도 범위 설정 : placePosition " + placePosition);
        bounds.extend(placePosition);

        // 마커와 검색결과 항목에 mouseover 했을때
        // 해당 장소에 인포윈도우에 장소명을 표시합니다
        // mouseout 했을 때는 인포윈도우를 닫습니다
        (function (marker, title) {
          kakao.maps.event.addListener(marker, "mouseover", function () {
            displayInfowindow(marker, title);
          });

          //   // 마커 클릭 시 디테일 페이지로 이동
          //   kakao.maps.event.addListener(marker, "click", function () {
          //     moveToDetail(marker, title);
          //   });

          //   kakao.maps.event.addListener(marker, "mouseout", function () {
          //     infowindow.close();
          //   });

          itemEl.onmouseover = function () {
            displayInfowindow(marker, title);
          };

          //   itemEl.onmouseout = function () {
          //     infowindow.close();
          //   };
        })(marker, places[i].title);

        fragment.appendChild(itemEl);
      }

      // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
      listEl.appendChild(fragment);
      menuEl.scrollTop = 0;

      // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
      //console.log("지도 범위 설정 : bounds " + bounds);
      //map.setBounds(bounds);
      setCenter(places[0].latitude, places[0].longitude);
    }

    // 검색결과 항목을 Element로 반환하는 함수입니다
    function getListItem(index, places) {
      console.log(places);
      var el = document.createElement("li"),
      itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
      '<div class="info">' + '<a href="/main?action=detail&content_id=' + places.content_id + '&title=' + places.title + '&addr1=' + places.addr1 + '&addr2=' + places.addr2 + '&zipcode=' + places.zipcode + '&tel=' + places.tel + '&first_image=' + places.first_image + '&first_image2=' + places.first_image2 + '">' +
      '   <h5>' + places.title + '</h5>';

      if (places.addr1) {
        itemStr +=
          "    <span>" +
          places.addr1 +
          "</span>" +
          '   <span class="jibun gray">' +
          places.addr2 +
          "</span>";
      } else {
        itemStr += "    <span>" + places.addr2 + "</span>";
      }

      itemStr += '  <span class="tel">' + places.tel + "</span>" + "</div>";

      el.innerHTML = itemStr;
      el.className = "item";

      return el;
    }

    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    function addMarker(position, idx, title) {
      var imageSrc =
          "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
        imgOptions = {
          spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
          spriteOrigin: new kakao.maps.Point(0, idx * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
          offset: new kakao.maps.Point(13, 37), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
        marker = new kakao.maps.Marker({
          position: position, // 마커의 위치
          image: markerImage,
        });

      marker.setMap(map); // 지도 위에 마커를 표출합니다
      markers.push(marker); // 배열에 생성된 마커를 추가합니다

      return marker;
    }

    // 지도 위에 표시되고 있는 마커를 모두 제거합니다
    function removeMarker() {
      for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
      }
      markers = [];
    }

    // 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
    function displayPagination(pagination) {
      var paginationEl = document.getElementById("pagination"),
        fragment = document.createDocumentFragment(),
        i;

      // 기존에 추가된 페이지번호를 삭제합니다
      while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild(paginationEl.lastChild);
      }

      for (i = 1; i <= pagination.last; i++) {
        var el = document.createElement("a");
        el.href = "#";
        el.innerHTML = i;

        if (i === pagination.current) {
          el.className = "on";
        } else {
          el.onclick = (function (i) {
            return function () {
              pagination.gotoPage(i);
            };
          })(i);
        }

        fragment.appendChild(el);
      }
      paginationEl.appendChild(fragment);
    }

    // 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
    // 인포윈도우에 장소명을 표시합니다
    function displayInfowindow(marker, title) {
      var content =
        '<div style="padding:5px;z-index:1;">' +
        `<a href="./detail.html?title=${title}">` +
        title +
        "</a>" +
        "</div>";

      infowindow.setContent(content);
      infowindow.open(map, marker);
    }

    // 검색결과 목록의 자식 Element를 제거하는 함수입니다
    function removeAllChildNods(el) {
      while (el.hasChildNodes()) {
        el.removeChild(el.lastChild);
      }
    }
    function setCenter(latitude, longitude) {            
        // 이동할 위도 경도 위치를 생성합니다 
        var moveLatLon = new kakao.maps.LatLng(latitude, longitude);
        
        // 지도 중심을 이동 시킵니다
        map.setCenter(moveLatLon);
    }
    


    </script>
   
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
