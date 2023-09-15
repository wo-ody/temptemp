package ajax;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.UserDto;


@WebServlet("/ILOVEHY")
public class SimpleFetchGetLIstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("외쳐 하영 ");
	
		// data 준비
		ArrayList<UserDto> userList = new ArrayList<>();
		userList.add(new UserDto(1111,"홍길동","hong@gildong.com"));
		userList.add(new UserDto(2222,"이길동","lee@gildong.com"));
		userList.add(new UserDto(3333,"삼길동","sam@gildong.com"));
		
		// userList 를 http로 client에 보내야 함.
		// java object => json 으로 변경해서 내려주어야 한다.
		Gson gson = new Gson();
		String jsonStr = gson.toJson(userList);
		System.out.println(jsonStr);
		
		// cors 이슈 해결
		response.addHeader("Access-Control-Allow-Origin", "*");
		
		// 내려준다. (response)
		response.setContentType("application/json; charset=utf-8"); // json 을 내려준다. utf-8 처리
		response.getWriter().write(jsonStr);
		
		
	}

	

}
