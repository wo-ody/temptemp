package board;

import java.sql.*;



// DBManager class
public class read {

	public static void main(String[] args) {

		// DB에 연걸할 수 있는 URL : jdbc url <- dbms마다 다릅니다.
		String url = "jdbc:mysql://localhost:3306/enjoytrip";
		String user = "root";
		String pwd = "1234";

		String selectBoard = "select * from board";
				
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
			
			// Statement 객체 - select
			pstmt = conn.prepareStatement(selectBoard);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String contents = rs.getString("contents");
				int userid = rs.getInt("userid");
				Timestamp createdAt = rs.getTimestamp("createdAt");
				Timestamp updatedAt = rs.getTimestamp("updatedAt");
				System.out.println(id + "/" + title + "/" + contents + " / " +  userid + "/" + createdAt + " / " + updatedAt);
			}

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
