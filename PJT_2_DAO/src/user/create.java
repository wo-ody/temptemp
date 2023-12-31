package user;

import java.sql.*;

// DBManager class
public class create {

    public static void main(String[] args){
        
        // SQL
        String insertSql = "insert into user (username, email, password, createdAt, updatedAt )values (?, ?, ?, ?, ?);";
     
        
        // 자료구조 및 객체
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int ret = -1;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        try {
            con = DBManager.getConnection();

            pstmt = con.prepareStatement(insertSql);   
            pstmt.setString(1, "이길동");
            pstmt.setString(2, "이@naver.com");
            pstmt.setString(3, "이password");
            pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis())); // 현재 시간으로 createdAt 값을 설정
            
            ret = pstmt.executeUpdate();
            
            
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.releaseConnection(rs, pstmt, con);
        }
    }
}
