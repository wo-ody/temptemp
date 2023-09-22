package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import user.dto.UserDto;
import user.service.UserService;
import user.service.UserServiceImpl;

@WebServlet("/user/*")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    UserService userService = UserServiceImpl.getInstance();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)    throws ServletException, IOException {        
        process(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)    throws ServletException, IOException {        
        process(request, response);
    }
    
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // encoding
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        
        String contextPath = request.getContextPath();
        String path = request.getRequestURI().substring(contextPath.length());
        
        System.out.println(path);
        
        switch( path ) {
            case "/user/register" : register(request, response); break;
            case "/user/isEmailUnique" : isEmailUnique(request, response); break;
        }
        
    }
    
    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        String userPassword = request.getParameter("userPassword");

        UserDto userDto = new UserDto();
        userDto.setUserName(userName);
        userDto.setUserEmail(userEmail);
        userDto.setUserPassword(userPassword);

        int ret = userService.userRegister(userDto);

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
    
    protected void isEmailUnique(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        String userEmail = request.getParameter("userEmail");

        boolean ret = userService.isEmailUnique(userEmail);

        Gson gson = new Gson();

        JsonObject jsonObject = new JsonObject();

        if (ret) {
            jsonObject.addProperty("result", "success");
        } else {
            jsonObject.addProperty("result", "fail");
        }

        String jsonStr = gson.toJson(jsonObject);
        response.getWriter().write(jsonStr);        
    }

}