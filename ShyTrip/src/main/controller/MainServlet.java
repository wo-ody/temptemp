package main.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.MapDto;
import main.dao.MapDao;
import main.dao.MapDaoImpl;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	/* TODO
	 * 1. 로그인 WITH SESSION
	 * 2. 로그아웃 SESSION INVALIDATED
	 * 3. 회원 수정에 SESSION 반영
	 * 4. 회원 탈퇴
	 * 5. 게시판
	 * 6. 즐겨찾기
	 * 7. 지도에 뿌리기(마커)
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
        process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "map":
                doMap(request, response);
                break;
            case "detail":
                doDetail(request, response);
                break;
        }
    }

	private void doMap(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword"); // 요청에서 keyword 파라미터를 받아옵니다.
		System.out.println("keyword : " + keyword + " 들어옴");
        
        // keyword를 이용하여 데이터베이스에서 데이터를 조회합니다.
        MapDao mapdao = new MapDaoImpl();
        List<MapDto> places = mapdao.getPlace(keyword);
        
        System.out.println(places.toString());

        // 가져온 데이터를 JSON으로 변환
        Gson gson = new Gson();
        String json = gson.toJson(places);

        // JSON 응답
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(json);
		// main.jsp로 forward
		//request.getRequestDispatcher("/html/main.jsp").forward(request, response);
	}
	
	private void doDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("detail 로 들어옴");
		
		String content_id = request.getParameter("content_id");
		String title = request.getParameter("title");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		String zipcode = request.getParameter("zipcode");
		String tel = request.getParameter("tel");
		String first_image = request.getParameter("first_image");
		String first_image2 = request.getParameter("first_image2");
    
		System.out.println("들어온애" + title + " " + addr1 + " " + addr2 + " " + zipcode + " " + tel + " " + first_image + " " + first_image2 );
		
		MapDto mapdto = new MapDto(Integer.parseInt(content_id), title, addr1, addr2 , zipcode, tel, first_image, first_image2);
		System.out.println("content_id " + content_id);
		MapDao mapdao = new MapDaoImpl();
		String overview = mapdao.getDescription(Integer.parseInt(content_id));
		
		
		request.setAttribute("mapdto", mapdto);
		request.setAttribute("overview", overview);
		request.getRequestDispatcher("/html/detail.jsp").forward(request, response);
	}
	

}

