package register.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

import common.DBManager;
import dto.RegisterDto;

public class RegisterDaoImpl implements RegisterDao{

	@Override
	public int createUser(RegisterDto registerDto) {
		RegisterDto resultDto = null;
        
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int ret = -1;
        
        try {
            // ConnectionPool로부터 Connection 객체를 얻는다.
            // ConnectionPool을 먼저 java 실행환경(Tomcat)으로부터 이름을 전달하고 그 객체를 얻는다.
        	
        	System.out.println(registerDto);
            
            con = DBManager.getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("insert into user (username, email, password, createdAt) values (?, ?, ?, ?);");
            
            // Statement 객체 - select
            pstmt = con.prepareStatement(sb.toString());
            pstmt.setString(1, registerDto.getUserName());
            pstmt.setString(2, registerDto.getUserEmail());
            pstmt.setString(3, registerDto.getUserPassword());
            pstmt.setTimestamp(4, java.sql.Timestamp.valueOf(LocalDateTime.now()));
        
            ret = pstmt.executeUpdate();
            System.out.println(ret);
            
        } catch(Exception e) {
        e.printStackTrace();
        }  finally {
            DBManager.releaseConnection(rs, pstmt, con);
        }
        
        return ret;
	}
	

}
