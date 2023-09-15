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
		System.out.println("���� �Ͽ� ");
	
		// data �غ�
		ArrayList<UserDto> userList = new ArrayList<>();
		userList.add(new UserDto(1111,"ȫ�浿","hong@gildong.com"));
		userList.add(new UserDto(2222,"�̱浿","lee@gildong.com"));
		userList.add(new UserDto(3333,"��浿","sam@gildong.com"));
		
		// userList �� http�� client�� ������ ��.
		// java object => json ���� �����ؼ� �����־�� �Ѵ�.
		Gson gson = new Gson();
		String jsonStr = gson.toJson(userList);
		System.out.println(jsonStr);
		
		// cors �̽� �ذ�
		response.addHeader("Access-Control-Allow-Origin", "*");
		
		// �����ش�. (response)
		response.setContentType("application/json; charset=utf-8"); // json �� �����ش�. utf-8 ó��
		response.getWriter().write(jsonStr);
		
		
	}

	

}