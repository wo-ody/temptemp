package basic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/MaintainDataServlet2")
public class MaintainDataServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MaintainDataServlet2");
		
		String attr1 = (String) request.getAttribute("attr1");
		String attr2 = (String) getServletContext().getAttribute("attr2");
		String attr3 = (String) request.getSession().getAttribute("attr3");
		
		System.out.println(attr1);
		System.out.println(attr2);
		System.out.println(attr3);
		
		response.getWriter().write("MaintainDataServlet2");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
