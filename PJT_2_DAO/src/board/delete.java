package board;

import java.sql.*;

// DBManager class
public class delete {

	public static void main(String[] args) {

		// DB에 연걸할 수 있는 URL : jdbc url <- dbms마다 다릅니다.
		String url = "jdbc:mysql://localhost:3306/enjoytrip";
		String user = "root";
		String pwd = "1234";

		String deleteBoard = "delete from board where id= ?;";

				
		// 자료구조 및 객체
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int ret = -1;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection(url, user, pwd);

			// delete
			
			pstmt = conn.prepareStatement(deleteBoard);
			pstmt.setInt(1, 3);
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
