package loginout.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import common.DBManager;
import dto.SidoDto;
import dto.UserDto;

public class LoginoutDaoImpl implements LoginoutDao{

	@Override
	public UserDto login(String userEmail) {
		UserDto userDto = null;
        
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int ret = -1;
        
        try {
            // ConnectionPool로부터 Connection 객체를 얻는다.
            // ConnectionPool을 먼저 java 실행환경(Tomcat)으로부터 이름을 전달하고 그 객체를 얻는다.
            
            con = DBManager.getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("select user_id, user_name, user_email, user_password from users ")
            	.append(" where user_email = ? "); // unique
            
            // Statement 객체 - select
            pstmt = con.prepareStatement(sb.toString());
            pstmt.setString(1, userEmail);
        
            rs = pstmt.executeQuery();
            if (rs.next()) {
                // 테이블 컬럼의 타입에 맞게 ResultSet 객체의 getXXX() 호출하고 결과를 받는다.
                int user_id = rs.getInt("user_id");
                String user_name = rs.getString("user_name");
                String user_email = rs.getString("user_email");
                String user_password = rs.getString("user_password");
                //userDto = new UserDto(user_id, user_name,user_email, user_password);
                
                userDto = new UserDto(
                		rs.getInt("user_id"),
                		rs.getString("user_name"),
                		rs.getString("user_email"),
                		rs.getString("user_password")
        		);
                
            }    
        } catch(Exception e) {
        e.printStackTrace();
        }  finally {
            DBManager.releaseConnection(rs, pstmt, con);
        }
        
        return userDto;
	}

}
