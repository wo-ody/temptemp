package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager {
	public static Connection getConnection() {
		Connection con = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/enjoytrip");
			
			con = ds.getConnection();
		
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
			if( con != null ) con.close(); // ConnectionPool에 반납.
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
