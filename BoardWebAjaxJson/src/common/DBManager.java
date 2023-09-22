package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager {
    public static Connection getConnection() {
        Connection con = null;
        try {
            Context context = (Context) new InitialContext().lookup("java:comp/env/");
            DataSource datasource = (DataSource) context.lookup("jdbc/board");
            con = datasource.getConnection();
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        return con;
    }
    
    public static void releaseConnection(PreparedStatement pstmt, Connection con) {
        try {
            if( pstmt != null ) pstmt.close();
            if( con != null ) con.close(); // overriding 되어 있어서 객체를 종료하지 않고, 반납!
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void releaseConnection(ResultSet rs, PreparedStatement pstmt, Connection con) {

        try {
            if(rs != null ) { rs.close(); }
            if(pstmt != null ) { pstmt.close(); }
            if(con != null ) { con.close(); }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void releaseConnection(AutoCloseable... closeables) {
        for (AutoCloseable c : closeables) {
            if (c != null) {
                try {
                    c.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}