<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="user.dto.*, java.util.*"%>
<%
    String contextPath = request.getContextPath();
    UserDto userDto = (UserDto) session.getAttribute("userDto");
%>
<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8">
<title>Board</title>
<!-- Bootstrap 5 버전 -->
<!-- https://getbootstrap.com/docs/5.0/getting-started/introduction/ -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

<script src="//cdn.jsdelivr.net/npm/alertifyjs@1.12.0/build/alertify.min.js"></script>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.12.0/build/css/alertify.min.css" />
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.12.0/build/css/themes/default.min.css" />
</head>
<body>

	<jsp:include page="../navBar.jsp" flush="true" />
	
	<div class="container mt-3">
        <!-- mt-3 추가 -->

        <h4 class="text-center">게시판 - Main</h4>

        <div class="input-group mb-3 mt-3">
            <!-- mb-3 mt-3  추가 -->
            <input id="inputSearchWord" type="text" class="form-control" placeholder="Search">
            <button id="btnSearchWord" class="btn btn-success" type="button">Search</button>
        </div>

        <table class="table table-hover">
            <thead>
                <tr>
                    <th>#</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>작성일시</th>
                    <th>조회수</th>
                </tr>
            </thead>
            <tbody id="boardTbody">
            </tbody>
        </table>

        <div id="paginationWrapper"></div>

        <button class="btn btn-sm btn-primary" id="btnBoardInsertUI">글쓰기</button>
    </div>
    
    <!-- modal begin ------------------------------------------------------------------------->

    <!-- Modal insert-->
    <div class="modal fade" id="boardInsertModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">글 쓰기</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">

                    <div class="mb-3">
                        <label for="titleInsert" class="form-label">제목</label> <input type="text" class="form-control" id="titleInsert">
                    </div>
                    <div class="mb-3">
                        <label for="contentInsert" class="form-label">내용</label>
                        <textarea class="form-control" id="contentInsert" rows="5"></textarea>
                    </div>
                    <button id="btnBoardInsert" class="btn btn-sm btn-primary btn-outline float-end" data-bs-dismiss="modal" type="button">등록</button>

                </div>
            </div>
        </div>
    </div>
    <!-- End Modal -->

    <!-- Modal detail-->
    <div class="modal fade" id="boardDetailModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">글 상세</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">

                    <table class="table">
                        <tbody>
                            <tr>
                                <td>글번호</td>
                                <td id="boardIdDetail">#</td>
                            </tr>
                            <tr>
                                <td>제목</td>
                                <td id="titleDetail">#</td>
                            </tr>
                            <tr>
                                <td>내용</td>
                                <td id="contentDetail">#</td>
                            </tr>
                            <tr>
                                <td>작성자</td>
                                <td id="userNameDetail">#</td>
                            </tr>
                            <tr>
                                <td>작성일시</td>
                                <td id="regDtDetail">#</td>
                            </tr>
                            <tr>
                                <td>조회수</td>
                                <td id="readCountDetail">#</td>
                            </tr>
                        </tbody>
                    </table>
                    <button id="btnBoardUpdateUI" class="btn btn-sm btn-primary btn-outline" data-bs-dismiss="modal" type="button">글 수정하기</button>
                    <button id="btnBoardDeleteUI" class="btn btn-sm btn-warning btn-outline" data-bs-dismiss="modal" type="button">글 삭제하기</button>

                </div>
            </div>
        </div>
    </div>
    <!-- End Modal -->

    <!-- Modal update-->
    <div class="modal fade" id="boardUpdateModal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">글 수정</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">

                    <div class="mb-3">
                        <label for="titleUpdate" class="form-label">제목</label> <input type="text" class="form-control" id="titleUpdate">
                    </div>
                    <div class="mb-3">
                        <label for="contentUpdate" class="form-label">내용</label>
                        <textarea class="form-control" id="contentUpdate" rows="5"></textarea>
                    </div>
                    <button id="btnBoardUpdate" class="btn btn-sm btn-primary btn-outline float-end" data-bs-dismiss="modal" type="button">수정</button>

                </div>
            </div>
        </div>
    </div>
    <!-- End Modal -->
    <!-- modal end --------------------------------------------------------------------------->
    
   <script src="<%=contextPath%>/js/util.js"></script>
    <script>
var LIST_ROW_COUNT = 10;    //limit
var OFFSET = 0;   // limit 10 offet 10
var SEARCH_WORD = "";

var PAGE_LINK_COUNT = 10;    // pagination link 갯수
var TOTAL_LIST_ITEM_COUNT = 0;
var CURRENT_PAGE_INDEX = 1;

window.onload = function(){
	boardList();
	
	// 검색어 검색 
    document.querySelector("#btnSearchWord").onclick = function(){

        SEARCH_WORD = document.querySelector("#inputSearchWord").value;
        // 처음 페이지로 초기화
        OFFSET = 0;
        CURRENT_PAGE_INDEX = 1;

        boardList();
    }
	
	// 글 등록 모달
    document.querySelector("#btnBoardInsertUI").onclick = function(){

        document.querySelector("#titleInsert").value = "";
        document.querySelector("#contentInsert").value = "";

        let modal = new bootstrap.Modal(document.querySelector("#boardInsertModal"));
        modal.show();
    };
    
 // 글 등록
    document.querySelector("#btnBoardInsert").onclick = function(){

        if( validateInsert() ){
            boardInsert();
        }
    }
 
 // 글 수정 모달
    document.querySelector("#btnBoardUpdateUI").onclick = function(){

        var boardId = document.querySelector("#boardDetailModal").getAttribute("data-boardId");
        document.querySelector("#boardUpdateModal").setAttribute("data-boardId", boardId);

        document.querySelector("#titleUpdate").value = document.querySelector("#titleDetail").innerHTML;
        document.querySelector("#contentUpdate").value = document.querySelector("#contentDetail").innerHTML;

        let detailModal = new bootstrap.Modal( document.querySelector("#boardDetailModal") );
        detailModal.hide();
        let updatelModal = new bootstrap.Modal( document.querySelector("#boardUpdateModal") );
        updatelModal.show();
    };
    
 // 글 수정
    document.querySelector("#btnBoardUpdate").onclick = function(){

        if( validateUpdate() ){
            boardUpdate();
        }
    };
    
 // 글 삭제
    document.querySelector("#btnBoardDeleteUI").onclick = function(){
         alertify.confirm('삭제 확인', '이 글을 삭제하시겠습니까?',
             function() {
                boardDelete();
             },
            function(){
                 console.log('cancel');
            }
        );
    };
	
}

async function boardList(){
    let url = '<%=contextPath%>/board/boardList';
    let urlParams = '?limit=' + LIST_ROW_COUNT + '&offset=' + OFFSET + "&searchWord=" + SEARCH_WORD;

    try{
        let response = await fetch(url + urlParams);
        let data = await response.json();
        console.log(data);
        makeListHtml(data);

    }catch(error){
        console.log(error);
        alertify.error('글 조회 과정에 문제가 생겼습니다.');
    }

}

function makeListHtml(list){

    console.log(list);

    let listHTML = ``;

    list.forEach( el =>{
        let boardId = el.boardId;
        let userName = el.userName;
        let title = el.title;
        let content = el.content;
        let regDt = el.regDt;    // javascript of parsed from LocalDateTime
        console.log(regDt);
        let regDtStr = makeDateStr(regDt.date.year, regDt.date.month, regDt.date.day, '.');

        let readCount = el.readCount;

        listHTML +=
            `<tr style="cursor:pointer" data-boardId=\${boardId}><td>\${boardId}</td><td>\${title}</td><td>\${userName}</td>
                <td>\${regDtStr}</td><td>\${readCount}</td></tr>`;

    } );

    console.log(listHTML);
    document.querySelector("#boardTbody").innerHTML = listHTML;


    makeListHtmlEventHandler();

    boardListTotalCnt();
}

function makeListHtmlEventHandler(){
    document.querySelectorAll("#boardTbody tr").forEach( el => {
        el.onclick = function(){
            var boardId = this.getAttribute("data-boardId");
            boardDetail(boardId);
        }
    });
}

async function boardListTotalCnt(){
    let url = '<%=contextPath%>/board/boardListTotalCnt';
    let urlParams = '?searchWord=' + SEARCH_WORD;

    try{
        let response = await fetch(url + urlParams);
        let data = await response.json();
        TOTAL_LIST_ITEM_COUNT = data.totalCnt;
        makePaginationHtml(LIST_ROW_COUNT, PAGE_LINK_COUNT, CURRENT_PAGE_INDEX, TOTAL_LIST_ITEM_COUNT, "paginationWrapper" );

    }catch(error){
        console.log(error);
        alertify.error('글 전체 수 조회 과정에 문제가 생겼습니다.');
    }
}

function movePage(pageIndex){
    OFFSET = (pageIndex - 1) * LIST_ROW_COUNT;
    CURRENT_PAGE_INDEX = pageIndex;
    boardList();
}

//insert
function validateInsert(){
    var isTitleInsertValid = false;
    var isContentInsertValid = false;

    var titleInsertValue = document.querySelector("#titleInsert").value;
    if( titleInsertValue.length > 0 ){
        isTitleInsertValid = true;
    }

    var contentInsertValue = document.querySelector("#contentInsert").value;
    if( contentInsertValue.length > 0 ){
        isContentInsertValid = true;
    }

    if(    isTitleInsertValid && isContentInsertValid ){
        return true;
    }else{
        return false;
    }
}

async function boardInsert(){

    let title = document.querySelector("#titleInsert").value;
    let content = document.querySelector("#contentInsert").value;

    let urlParams = new URLSearchParams({
        title: title,
        content: content,
    });

    let fetchOptions = {
        method: "POST",
        body: urlParams,
    }

    let url = '<%=contextPath%>/board/boardInsert';

    try{
        let response = await fetch(url, fetchOptions );
        let data = await response.json();
        if( data.result == "success" ){ // 백엔드 로그인 필터에서 session timeout 이 발생하면 내려주는 json 값
            alertify.success('글이 등록되었습니다.');
            boardList();
        }else{
            alertify.error('글 등록 과정에 문제가 있습니다.')
        }
    }catch( error ){
        console.error( error );
        alertify.error('글 등록 과젱에 문제가 있습니다.')
    }
}

//detail
async function boardDetail(boardId){

    var url = '<%=contextPath%>/board/boardDetail';
    var urlParams = '?boardId=' + boardId;

    try{
        let response = await fetch(url + urlParams);
        let data = await response.json();
        console.log(data);
        makeDetailHtml(data);
    }catch( error ){
        console.error( error );
        alertify.error("글 조회과정에 문제가 생겼습니다.");
    }
}

function makeDetailHtml(detail){
    console.log(detail);
    var boardId = detail.boardId;
    var userSeq = detail.userSeq;
    var userName = detail.userName;
    var title = detail.title;
    var content = detail.content;
    var regDt = detail.regDt;

    var regDtStr = makeDateStr(regDt.date.year, regDt.date.month, regDt.date.day, '.') + ' ' + makeTimeStr(regDt.time.hour, regDt.time.minute, regDt.time.second, ':');

    var readCount = detail.readCount;
    var sameUser = detail.sameUser;
    var fileList = detail.fileList;

    document.querySelector("#boardDetailModal").setAttribute("data-boardId", boardId);
    document.querySelector("#boardIdDetail").innerHTML = "#" + boardId;
    document.querySelector("#titleDetail").innerHTML = title;
    document.querySelector("#contentDetail").innerHTML = content;
    document.querySelector("#userNameDetail").innerHTML = userName;
    document.querySelector("#regDtDetail").innerHTML = regDtStr;
    document.querySelector("#readCountDetail").innerHTML = readCount;

    if( sameUser ){
        document.querySelector("#btnBoardUpdateUI").style.display = "inline-block";
        document.querySelector("#btnBoardDeleteUI").style.display = "inline-block";
    }else{
        document.querySelector("#btnBoardUpdateUI").style.display = "none";
        document.querySelector("#btnBoardDeleteUI").style.display = "none";
    }

    let modal = new bootstrap.Modal(document.querySelector("#boardDetailModal"));
    modal.show();
}

//update
function validateUpdate(){
    var isTitleUpdateValid = false;
    var isContentUpdateValid = false;

    var titleUpdate = document.querySelector("#titleUpdate").value;
    var titleUpdateLength = titleUpdate.length;

    if( titleUpdateLength > 0 ){
        isTitleUpdateValid = true;
    }

    var contentUpdateValue = document.querySelector("#contentUpdate").value;
    var contentUpdateLength = contentUpdateValue.length;

    if( contentUpdateLength > 0 ){
        isContentUpdateValid = true;
    }

    if(    isTitleUpdateValid && isContentUpdateValid ){
        return true;
    }else{
        return false;
    }
}

async function boardUpdate(){

    let boardId = document.querySelector("#boardUpdateModal").getAttribute("data-boardId");
    let title = document.querySelector("#titleUpdate").value;
    let content = document.querySelector("#contentUpdate").value;

    let urlParams = new URLSearchParams({
        boardId: boardId,
        title: title,
        content: content,
    });

    let fetchOptions = {
        method: "POST",
        body: urlParams,
    }

    let url = '<%=contextPath%>/board/boardUpdate';

    try{
        let response = await fetch(url, fetchOptions );
        let data = await response.json();
        if( data.result == "success" ){
            alertify.success('글이 수정되었습니다.');
            boardList();
        }else{
            alertify.error('글 수정 과젱에 문제가 있습니다.');
        }
    }catch( error ){
        console.error( error );
        alertify.error('글 수정 과젱에 문제가 있습니다.');
    }
}


//delete
async function boardDelete(){

    var url = '<%=contextPath%>/board/boardDelete';
    var urlParams = '?boardId=' + document.querySelector("#boardDetailModal").getAttribute("data-boardId");

    try{
        let response = await fetch(url + urlParams);
        let data = await response.json();

        if(data.result == "success"){
            alertify.success("글이 삭제되었습니다.");
            boardList();
        }else{
            alertify.error("글 삭제 과정에 문제가 생겼습니다.");
        }
    }catch( error ){
        console.error( error );
        alertify.error("글 삭제 과정에 문제가 생겼습니다.");
    }
}

	</script>
</body>
</html>