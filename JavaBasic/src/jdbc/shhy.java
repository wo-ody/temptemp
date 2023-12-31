package jdbc;

import java.sql.*;

// DBManager class
public class shhy {

    public static void main(String[] args){
        
        // SQL
        String insertSql = "insert into customer values (?, ?);";
        String updateSql = "update customer set customer_nm = ? where customer_id=?;";
        String deleteSql = "delete from customer where customer_id=?;";
        String selectSql = "select * from attraction_info where addr1 like ?;";
        
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

            // insert
//            pstmt = con.prepareStatement(insertSql);    
//            pstmt.setInt(1, 4);
//            pstmt.setString(2, "사길동");
//            ret = pstmt.executeUpdate();
            
            
            // update
//            pstmt = con.prepareStatement(updateSql);    
//            pstmt.setString(1, "사길동2");
//            pstmt.setInt(2, 4);            
//            ret = pstmt.executeUpdate();
            
            // delete
//            pstmt = con.prepareStatement(deleteSql);    
//            pstmt.setInt(1, 4);                        
//            ret = pstmt.executeUpdate();
//            
//            System.out.println(ret);
            
            // Statement 객체 - select
            pstmt = con.prepareStatement(selectSql);    
            pstmt.setString(1, "%대구%");    
            rs = pstmt.executeQuery();
            while( rs.next() ) {
                int content_id = rs.getInt("content_id");
                String title = rs.getString("title");
                String addr1 = rs.getString("addr1");
                String first_image = rs.getString("first_image");
                Double latitude = rs.getDouble("latitude");
                Double longitude = rs.getDouble("longitude");
                System.out.println(title + " / " + addr1+ " / " + first_image+ " / " +latitude+ " / " + longitude);            
            }
            
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.releaseConnection(rs, pstmt, con);
        }
    }
}
