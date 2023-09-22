package board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import board.dto.BoardDto;
import board.service.BoardService;
import board.service.BoardServiceImpl;
import user.dto.UserDto;

/*
 * contextPath 고려
 */
@WebServlet("/board/*")
public class BoardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    BoardService service = BoardServiceImpl.getInstance();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        
        // Back Forward Cache ( bfCache )
        // logout 후 뒤로 가기 클릭하면 캐쉬된 boardMain 이 보인다. 그 뒤 totalCnt 가져오는 부분에서 ajax 로 요청하다가 login.jsp 페이지 결과 받는 오류 발생
        response.setHeader("Cache-Control", "no-store");
                
        String contextPath = request.getContextPath();
        String path = request.getRequestURI().substring(contextPath.length());

        System.out.println(path);
        
        switch(path) {
            case "/board/boardMain"                : boardMain(request, response); break;
            case "/board/boardList"                : boardList(request, response); break;
            case "/board/boardListTotalCnt"        : boardListTotalCnt(request, response); break;
            case "/board/boardInsert"        : boardInsert(request, response); break;
            case "/board/boardDetail"        : boardDetail(request, response); break;
            case "/board/boardUpdate"        : boardUpdate(request, response); break;
            case "/board/boardDelete"        : boardDelete(request, response); break;
//            default : notValidUrl();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        doPost(request, response);
    }
    
    private void boardMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	
    	//DB access
    	//request setAttribute
    	
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/board/boardMain.jsp"); 
       
        dispatcher.forward(request, response);
    }
    
    private void boardList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String strLimit = request.getParameter("limit");
        int limit = Integer.parseInt(strLimit);
        String strOffset = request.getParameter("offset");
        int offset = Integer.parseInt(strOffset);
        String searchWord = request.getParameter("searchWord");
        
        List<BoardDto> boardList;
        
        if( "".equals(searchWord) ) {
            boardList = service.boardList(limit, offset);
        }else {
            boardList = service.boardListSearchWord(limit, offset, searchWord);
        }

        Gson gson = new Gson();
        String jsonStr = gson.toJson(boardList);
        
        //response.setContentType("text/html; charset=utf-8");
        response.getWriter().write(jsonStr);
        System.out.println("BoardServlet boardList jsonStr : " + jsonStr);
    }
    
    private void boardListTotalCnt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        String searchWord = request.getParameter("searchWord");
        
        int totalCnt;
        if( "".equals(searchWord) ) {
            totalCnt = service.boardListTotalCnt();
        }else {
            totalCnt = service.boardListSearchWordTotalCnt(searchWord);
        }
        
        //response.setContentType("text/html; charset=utf-8");
        Gson gson = new Gson();
        
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("totalCnt", totalCnt);
        
        String jsonStr = gson.toJson(jsonObject);
        
        response.getWriter().write(jsonStr);
        System.out.println("BoardServlet boardListTotalCnt totalCnt : " + totalCnt);
    }

    private void boardInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        BoardDto boardDto = new BoardDto();
        // LoginFilter 먼저 적용 필요!!
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute("userDto");
        
        System.out.println("request.getParameter(\"title\") " + request.getParameter("title"));
        
        boardDto.setUserSeq(userDto.getUserSeq());
        boardDto.setTitle(request.getParameter("title"));
        boardDto.setContent(request.getParameter("content"));
        
        int ret = service.boardInsert(boardDto);
        
        // if cnt != 1 Exception ...
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        
        if (ret == 1) {
            jsonObject.addProperty("result", "success");
        } else {
            jsonObject.addProperty("result", "fail");
        }
        
        String jsonStr = gson.toJson(jsonObject);
        response.getWriter().write(jsonStr);
    }
    
    private void boardDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute("userDto");        
        int userSeq = userDto.getUserSeq();
        
        String strBoardId = request.getParameter("boardId");
        int boardId = Integer.parseInt(strBoardId);
        BoardDto boardDto = service.boardDetail(boardId, userSeq);

        Gson gson = new Gson();

        String jsonStr = gson.toJson(boardDto, BoardDto.class);
        response.getWriter().write(jsonStr);
    }
    
    private void boardUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        BoardDto boardDto = new BoardDto();
        
        String strBoardId = request.getParameter("boardId");
        int boardId = Integer.parseInt(strBoardId);
        
        boardDto.setBoardId(boardId);
        boardDto.setTitle(request.getParameter("title"));
        boardDto.setContent(request.getParameter("content"));
        
        int ret = service.boardUpdate(boardDto);

        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        
        if (ret == 1) {
            jsonObject.addProperty("result", "success");
        } else {
            jsonObject.addProperty("result", "fail");
        }
        
        String jsonStr = gson.toJson(jsonObject);
        response.getWriter().write(jsonStr);
    }
    
    private void boardDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        String strBoardId = request.getParameter("boardId");
        // if strBoardId == null or "" Exception ...
        int boardId = Integer.parseInt(strBoardId);
        int ret = service.boardDelete(boardId);
System.out.println(strBoardId);
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        
        if (ret == 1) {
            jsonObject.addProperty("result", "success");
        } else {
            jsonObject.addProperty("result", "fail");
        }
        
        String jsonStr = gson.toJson(jsonObject);
        response.getWriter().write(jsonStr);
    }
}