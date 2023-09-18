package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/users")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String job = request.getParameter("job");
		switch(job) {
			case "job" : goUsers(request, response); break;
			case "A" : addUsers(request, response); break;
			case "M" : modifyUsers(request, response); break;
			case "L" : listUsers(request, response); break;
			case "D" : detailUsers(request, response); break;
			case "R" : removeUsers(request, response); break;
		}
		
		//request.getRequestDispatcher("/users/users.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void goUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("goUsers");
		request.getRequestDispatcher("/users/users.jsp").forward(request, response);
	}
	
	private void addUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("addUsers");
		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("userEmail");
		String userPassword = request.getParameter("userPassword");
		
		System.out.println(userName);
		System.out.println(userEmail);
		System.out.println(userPassword);
		
		request.getRequestDispatcher("/users/userRegisterSuccess.jsp").forward(request, response);
	}
	
	private void modifyUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("modifyUsers");
	}
	
	private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("listUsers");	
	}
	
	private void detailUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("detailUsers");
	}
	
	private void removeUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("removeUsers");
	}

}
