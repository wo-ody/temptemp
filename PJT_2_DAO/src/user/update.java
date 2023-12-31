package user;

import java.sql.*;

// DBManager class
public class update {

    public static void main(String[] args){
        
        // SQL
    	String updateSql = "update user set username = ? where id=?;";
     
        
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

         // update
          pstmt = con.prepareStatement(updateSql);    
          pstmt.setString(1, "홍길동2");
          pstmt.setInt(2, 1);            
          ret = pstmt.executeUpdate();
          
          
          
            
            
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.releaseConnection(rs, pstmt, con);
        }
    }
}
