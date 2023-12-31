package board;

import java.sql.*;
import java.time.LocalDateTime;

// DBManager class
public class update {

	public static void main(String[] args) {

		// DB에 연걸할 수 있는 URL : jdbc url <- dbms마다 다릅니다.
		String url = "jdbc:mysql://localhost:3306/enjoytrip";
		String user = "root";
		String pwd = "1234";
		
		String updateBoard = "update board set title =?, contents = ?, updatedAt =? where id = ?;";

				
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

			// update
			
			pstmt = conn.prepareStatement(updateBoard);
			String updatedTitle = "부산 여행지 추천";
			String updatedContents = "부산 여행지 추천 please";
			String updatedAt = String.valueOf(LocalDateTime.now());
			int boardId = 3;
			pstmt.setString(1, updatedTitle);
			pstmt.setString(2, updatedContents);
			pstmt.setString(3, updatedAt);
			pstmt.setInt(4, boardId);
			
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
