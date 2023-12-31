package user;

import java.sql.*;

// DBManager class
public class delete {

    public static void main(String[] args){
        
        // SQL
    	String deleteSql = "delete from user where id=?;";
     
        
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

            // delete
          pstmt = con.prepareStatement(deleteSql);    
          pstmt.setInt(1, 1);                        
          ret = pstmt.executeUpdate();
          
          System.out.println(ret);
            
            
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.releaseConnection(rs, pstmt, con);
        }
    }
}
