package basic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LifeCycleServlet")
public class LifeCycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      

    public LifeCycleServlet() {
        super();
        System.out.println("LifeCycleServlet()");
    }

    @Override	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet()");
	}
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost()");
	}
	
	
	@Override
	public void init() throws ServletException{
		System.out.println("init()");
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy()!!!!!!!!!!!!!!!");
	}

}
