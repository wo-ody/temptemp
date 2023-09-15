package favorite;

import java.sql.*;



// DBManager class
public class read {

    public static void main(String[] args){
    
        
        // SQL
    	String selectSql = "select * from favorite;";
     
        
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
            
            // Statement 객체 - select
            pstmt = con.prepareStatement(selectSql);        
            rs = pstmt.executeQuery();
            while( rs.next() ) {
                int id = rs.getInt("id");
                String username = rs.getString("title");
                String addr = rs.getString("addr");
                int contentid = rs.getInt("contentid");
                int userId = rs.getInt("userId");
                System.out.println(id + " / " + username+ " / " + addr+ " / " +contentid+ " / " + userId );            
            }
            
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.releaseConnection(rs, pstmt, con);
        }
    }
}
