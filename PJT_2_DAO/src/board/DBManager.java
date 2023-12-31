package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {
	// DB 연결
	// Connection 객체 생성, 제공
	// 자원 종료
	
	private static String url = "jdbc:mysql://localhost:3306/enjoytrip";
	private static String user = "root";
	private static String pwd = "1234";
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, pwd);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void releaseConnection(ResultSet rs, PreparedStatement pstmt, Connection con) {
		try {
			// 귀한 자원 역순으로, null 체크하면서 close()
			if( rs != null ) rs.close();
			if( pstmt != null ) pstmt.close();
			if( con != null ) con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
//	public static void releaseConnection(AutoCloseable... params) {
//		for (AutoCloseable ac : params) {
//			if( ac != null ) {
//				try {
//					ac.close();
//				}catch(Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}	
//	}
}
