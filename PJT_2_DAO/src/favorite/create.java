package favorite;

import java.sql.*;

// DBManager class
public class create {

    public static void main(String[] args){
        
        // SQL
        String insertSql = "insert into favorite (title, addr, contentid, userId )values (?, ?, ?, ?);";
     
        
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
            pstmt.setString(1, "김하영");
            pstmt.setString(2, "김하영동");
            pstmt.setInt(3, 125412);
            pstmt.setInt(4, 3);
            
            ret = pstmt.executeUpdate();
            
            
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.releaseConnection(rs, pstmt, con);
        }
    }
}
