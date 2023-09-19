package loginout.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dto.UserDto;
import loginout.service.LoginoutService;
import loginout.service.LoginoutServiceImpl;


@WebServlet("/loginout")
public class LoginOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private LoginoutService loginoutService = new LoginoutServiceImpl();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String job = request.getParameter("job");
		switch(job) {
			case "login" : login(request, response); break;
			case "logout" : logout(request, response); break;
			case "login_ajax" : loginAjax(request, response); break;
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("login");
		String userEmail = request.getParameter("userEmail");
		String userPassword = request.getParameter("userPassword");
		System.out.println("userEmail" + userEmail);
		System.out.println("userPassword" + userPassword);
		// DB 로그인 성공
		// 사용자정보를 DB 에서 가져와서 UserDto 객체를 만든 후 session 에 저장
		UserDto userDto = loginoutService.login(userEmail, userPassword);
		
		// 성공
		if( userDto != null ) {
			// session 에 로그인한 사용자 정보를 저장
			request.getSession().setAttribute("userDto", userDto);
			
			//화면
			request.getRequestDispatcher("/main").forward(request, response);
		}
		//실패
		else {
			request.getRequestDispatcher("/login/loginFail.jsp").forward(request, response);
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("logout");
		request.getSession().invalidate(); // 세션 무효화
		
		request.getRequestDispatcher("/main").forward(request, response);
	}

	private void loginAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("login_ajax");
		String userEmail = request.getParameter("userEmail");
		String userPassword = request.getParameter("userPassword");
		System.out.println("userEmail" + userEmail);
		System.out.println("userPassword" + userPassword);
		// DB 로그인 성공
		// 사용자정보를 DB 에서 가져와서 UserDto 객체를 만든 후 session 에 저장
		UserDto userDto = loginoutService.login(userEmail, userPassword);
		System.out.println(userDto);
		
		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();
		
		// 성공
		if(userDto != null) {
			request.getSession().setAttribute("userDto", userDto);
			jsonObject.addProperty("result", "success");
		}else {
			jsonObject.addProperty("result", "fail");
		}
		
		String jsonStr = gson.toJson(jsonObject);
		System.out.println(jsonStr);
		
		response.getWriter().write(jsonStr);
	}
}
