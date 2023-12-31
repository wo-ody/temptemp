package jdbc;

import java.sql.*;

public class Test2 {

	public static void main(String[] args)  {
		// DB 연결 URL <- jdbc url <- dbms 마다 다릅니다.
		String url = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String pwd = "1234";
		
		// SQL
		String insertSql = "insert into customer values (3,'삼길동');"; 
		String updateSql = "update customer set customer_nm = '홍길동2' where customer_id = 1;"; 
		String deleteSql = "delete from customer where customer_id = 1;"; 
		String selectSql = "select * from customer;";
		
		// 자료구조 및 객체
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		int ret = -1;
	
		// Connection 객체 생성
		// JDBC Driver jar 추가
		// JDBC Driver Class Memory Load ( Class.forName() ) <- 최근 버전은 필요 없음.
		// 		JDBC Driver Class 의 코드 안에서 static initializer 를 이용해서 자기 자신 객체를 JDBC Driver 객체에 등록
		// JDBC Driver 로부터 Connection 객체를 얻는다.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(selectSql);
			while( rs.next() ) {
				// 테이블 컬럼의 타입에 맞게 ResultSet 객체의 getXXX() 호출하고 결과를 받는다.
				int customer_id = rs.getInt("customer_id");
				String customer_nm = rs.getString("customer_nm");
				System.out.println(customer_id + " / " + customer_nm);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				// 귀한 자원 역순으로, null 체크하면서 close()
				if( rs != null ) rs.close();
				if( stmt != null ) stmt.close();
				if( con != null ) con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
