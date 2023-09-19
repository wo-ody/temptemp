package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import common.DBManager;
import dto.SidoDto;

public class MainDaoImpl implements MainDao{

	@Override
	public List<SidoDto> sidoList() {
		
		List<SidoDto> list = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = DBManager.getConnection();
			
			// select
			pstmt = con.prepareStatement("SELECT sido_code, sido_name FROM enjoytrip.sido;");
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				// 테이블 컬럼의 타입에 맞게 ResultSet 객체의 getXXX() 호출하고 결과를 받는다.
				int sido_code = rs.getInt("sido_code");
				String sido_name = rs.getString("sido_name");
				
				// version #2
				SidoDto dto2 = new SidoDto();
				dto2.setSidoCode(sido_code);
				dto2.setSidoName(sido_name);
				
				// version #1
				list.add(new SidoDto(sido_code, sido_name));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}
		
		return list;
	}
	
}
