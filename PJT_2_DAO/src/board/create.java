package board;

import java.sql.*;
import java.time.LocalDateTime;

// DBManager class
public class create {

	public static void main(String[] args) {

		// DB에 연걸할 수 있는 URL : jdbc url <- dbms마다 다릅니다.
		String url = "jdbc:mysql://localhost:3306/enjoytrip";
		String user = "root";
		String pwd = "1234";

		String insertBoard = "insert into board (title, contents, userid, createdAt ) values (?, ?, ?, ?)";
				
		// 자료구조 및 객체
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int ret = -1;

		// Connection 객체 생성
		// JDBC Driver jar 추가
		// JDBC Driver Class Memory Load(Class.forName()) <- 최근 버전은 필요 없음
		// JDBC Driver Class의 코드 안에서 static initializer를 이용해서 자기자신 객체를 JDBC Driver 객체에
		// 등록
		// JDBC Driver로 부터 Connection객체를 얻는다.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection(url, user, pwd);
			// Statement 객체 생성 및 SQL 실행 - insert, update, delete
			// insert
			
			pstmt = conn.prepareStatement(insertBoard);
			
			String title = "부산 여행지 추천 받아요";
			String contents = "안녕하세요, 제가 처음으로 부산에 여행을 가는데요, \n 혹시 추천해 주실 여행지가 있을까요??\n 고수님들의 유용한 정보 감사히 쓰겠습니다.";
			int userid = 1;
			String createdAt = String.valueOf(LocalDateTime.now());
			String updatedAt = "";
			  
			
			pstmt.setString(1, title);
			pstmt.setString(2, contents);
			pstmt.setInt(3, userid);
			pstmt.setString(4, createdAt);
			ret = pstmt.executeUpdate(); 
			
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 귀한 자원 역순으로, null체크 하면서 close()
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
