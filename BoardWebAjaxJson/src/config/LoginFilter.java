package config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.dto.UserDto;

/* 
 * @@@@@@@@@@@@@ 조심해 !!!!!!!!!!!!!!!!!!!!!!!
 * 너무 편해서
 * 이것이 있는지 모르고 사용한다 !
 * 
 * */

@WebFilter("/*")
public class LoginFilter implements Filter{

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) res;
        
        String contextPath = httpServletRequest.getContextPath();
        String path = httpServletRequest.getRequestURI();
        
        System.out.println("BoardWebAjaxJson - doFilter path : " + path ); 

        HttpSession session = httpServletRequest.getSession();
        
        // exclude
        if( 
                ! path.contains("/img/") 
                && ! path.contains("/css/") 
                && ! path.contains("/js/") 
                && ! path.contains("/register") 
                && ! path.contains("/isEmailUnique") 
                && ! path.contains("/login") 
        ) {
            UserDto userDto = (UserDto) session.getAttribute("userDto");

            if(userDto == null) {
                httpServletResponse.sendRedirect(contextPath + "/jsp/login.jsp");
                return;
            }
        }

        chain.doFilter(req, res);
    }

}