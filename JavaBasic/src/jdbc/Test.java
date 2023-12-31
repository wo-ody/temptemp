package jdbc;

import java.sql.*;


// java <--> DB(MySql) 연동 방법 : JDBC
// Connection 객체
//	아주 아주 귀한 자원 => 한번 얻었으면 최대한 활용하고 닫는다.
//	만약 DBMS 에 반납 안하면 큰일난다.
// Statement 객체 <= static 한 문자열 SQL 처리
// PreparedStatement 객체 <= 변수를 뺀 SQL을 미리 parse, compile 한 후, 반복적인 작업은 parameter 만 변경 처리
// 	=> 속도가 빠르고 보안에도 유리하다.
// ResultSet은 grid 형태의 자료구조를 가지고 있다.
//  next() 를 이용해서 다음 행으로 이동처리
public class Test {

	public static void main(String[] args) throws Exception {
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
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		con = DriverManager.getConnection(url, user, pwd);
		System.out.println(con);
		
		// Statement 객체 생성 및 SQL 실행
		stmt = con.createStatement();
		// insert
//		ret = stmt.executeUpdate(insertSql);
		// update
//		ret = stmt.executeUpdate(updateSql);
		// delete
//		ret = stmt.executeUpdate(deleteSql);
		
		System.out.println(ret);
		
		// Statement 객체 - select
		rs = stmt.executeQuery(selectSql);
		while( rs.next() ) {
			// 테이블 컬럼의 타입에 맞게 ResultSet 객체의 getXXX() 호출하고 결과를 받는다.
			int customer_id = rs.getInt("customer_id");
			String customer_nm = rs.getString("customer_nm");
			System.out.println(customer_id + " / " + customer_nm);
		}
		
		// 귀한 자원 역순으로, null 체크하면서 close()
		if( rs != null ) rs.close();
		if( stmt != null ) stmt.close();
		if( con != null ) con.close();
		
	}

}
