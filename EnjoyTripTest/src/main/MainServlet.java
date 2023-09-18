package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.SidoDto;
import dto.StudentDto;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/ request");
		
		// DB ACCESS
		// 뿌려줄 데이터를 추출
		List<StudentDto> studentList = new ArrayList<>();
		studentList.add(new StudentDto(1, "홍길동"));
		studentList.add(new StudentDto(2, "이길동"));
		studentList.add(new StudentDto(3, "삼길동"));
		
		List<SidoDto> sidoList = new ArrayList<>();
		sidoList.add(new SidoDto(1, "서울"));
		sidoList.add(new SidoDto(2, "인천"));
		sidoList.add(new SidoDto(3, "대전"));
		sidoList.add(new SidoDto(4, "대구"));
		sidoList.add(new SidoDto(5, "광주"));
		sidoList.add(new SidoDto(6, "부산"));
		
		
		request.setAttribute("studentList",studentList);
		request.setAttribute("sidoList",sidoList);
		// index.jsp
		// forward
		request.getRequestDispatcher("/main.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
