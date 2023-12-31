package basic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ConnectionPoolTestServlet
 */
@WebServlet("/connection")
public class ConnectionPoolTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// ConnectionPool 로부터 Connection 객체를 얻는다.
			// ConnectionPool 을 먼저 java 실행환경 (Tomcat)으로 부터 이름을 전달하고 그 객체를 얻는다.
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
			
			con = ds.getConnection();
			
			// select
			pstmt = con.prepareStatement("select * from customer where customer_id > ?;");
			pstmt.setInt(1, 1);
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				// 테이블 컬럼의 타입에 맞게 ResultSet 객체의 getXXX() 호출하고 결과를 받는다.
				int customer_id = rs.getInt("customer_id");
				String customer_nm = rs.getString("customer_nm");
				System.out.println(customer_id + " / " + customer_nm);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				// 귀한 자원 역순으로, null 체크하면서 close()
				if( rs != null ) rs.close();
				if( pstmt != null ) pstmt.close();
				if( con != null ) con.close(); // 반납 ?? 맞습니다 ~
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}
}
