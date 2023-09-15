package basic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/MaintainDataServlet")
public class MaintainDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MaintainDataServlet");
		
		// parameter 처리
		// DB 처리
		
		request.setAttribute("attr1", "value1");
		getServletContext().setAttribute("attr2", "value2");
		request.getSession().setAttribute("attr3", "value3");
		
		// forward 처리
//		request.getRequestDispatcher("/MaintainDataServlet2").forward(request, response);
//		request.getRequestDispatcher("MaintaimData.jsp").forward(request, response);
		// sendRedirect 처리
		response.sendRedirect("/WebBasic/MaintainDataServlet2");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
