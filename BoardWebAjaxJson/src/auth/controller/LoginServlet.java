package auth.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import auth.service.LoginService;
import auth.service.LoginServiceImpl;
import user.dto.UserDto;

@WebServlet({"/login", "/logout"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    LoginService service = LoginServiceImpl.getInstance();
    
    // POST 는 login 처리
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        
        String userEmail = request.getParameter("userEmail");
        String userPassword = request.getParameter("userPassword");

        UserDto userDto = service.login(userEmail, userPassword);
        
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        String jsonStr = null;
        
        if( userDto != null ) { // 성공
            
            // 세션에 사용자 정보를 담는다.
            HttpSession session = request.getSession();
            session.setAttribute("userDto", userDto);
            
            // result            
            jsonObject.addProperty("result", "success");
            jsonStr = gson.toJson(jsonObject); // json 문자열

        }else { // 실패

            jsonObject.addProperty("result", "fail");
            jsonStr = gson.toJson(jsonObject); // json 문자열
        }
        
        response.getWriter().write(jsonStr);    
    }
    
    // GET 은 logout 처리
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 필요한 작업 수행 ( Log, 장바구니 DB 저장 등... ) 후
        HttpSession session = request.getSession();
        session.invalidate();
        
        Gson gson = new Gson();
        
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("result", "success");
        
        String jsonStr = gson.toJson(jsonObject);
        response.getWriter().write(jsonStr);
        
        System.out.println("LoginServlet - logout success");
    }
}