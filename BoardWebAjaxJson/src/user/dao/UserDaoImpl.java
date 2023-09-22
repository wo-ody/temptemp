package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import common.DBManager;
import user.dto.UserDto;

public class UserDaoImpl implements UserDao{
    
    private static UserDaoImpl instance = new UserDaoImpl();
    
    private UserDaoImpl() {}

    public static UserDaoImpl getInstance() {
        return instance;
    }
    
    @Override
    public int userRegister(UserDto userDto) {

        Connection con = null;
        PreparedStatement pstmt = null;
        
        int ret = -1;
        
        try {
            con = DBManager.getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append(" insert into users ")
                .append(" ( user_name, user_password, user_email, user_register_date ) ")
                .append(" values( ?, ?, ?, now() )");
            
            pstmt = con.prepareStatement(sb.toString());
            pstmt.setString(1,  userDto.getUserName());
            pstmt.setString(2,  userDto.getUserPassword());
            pstmt.setString(3,  userDto.getUserEmail());

            ret = pstmt.executeUpdate();

        }catch(Exception e) {            
            e.printStackTrace();
        }finally {
            DBManager.releaseConnection(pstmt, con);
        }
        
        return ret;
    }

    @Override
    public boolean isEmailUnique(String userEmail) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        boolean ret = false;
        
        try {
            con = DBManager.getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append(" select count(*) cnt ")
                .append(" from users ")
                .append(" where user_email = ? ");
            
            pstmt = con.prepareStatement(sb.toString());
            pstmt.setString(1, userEmail);

            rs = pstmt.executeQuery();
            
            if( rs.next() ) {
                int cnt = rs.getInt("cnt");
                if( cnt == 0 ) ret = true;
            }

        }catch(Exception e) {            
            e.printStackTrace();
        }finally {
            DBManager.releaseConnection(rs, pstmt, con);
        }
        
        return ret;
    }

}