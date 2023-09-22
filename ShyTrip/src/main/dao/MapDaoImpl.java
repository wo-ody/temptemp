package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import common.DBManager;
import dto.MapDto;
import dto.RegisterDto;

public class MapDaoImpl implements MapDao{

	@Override
	public List<MapDto> getPlace(String keyword) {
		RegisterDto resultDto = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int ret = -1;

		List<MapDto> result = new ArrayList<>();
		
		try {

			con = DBManager.getConnection();
			StringBuilder sb = new StringBuilder();
			sb.append("select content_id, title, addr1, addr2, zipcode, tel, first_image, first_image2, latitude, longitude from attraction_info where addr1 like ?;");

			// Statement 객체 - select
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, "%" + keyword + "%");

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
                // ResultSet을 통해서 조회 결과를 넘겨받는다.
                // 조회 결과를 이용해 User 객체를 생성하고 list에 담는다.
				MapDto mapdto = new MapDto(
						rs.getInt("content_id"),
						rs.getString("title"), 
						rs.getString("addr1"),
						rs.getString("addr2"),
						rs.getString("zipcode"),
						rs.getString("tel"),
						rs.getString("first_image"),
						rs.getString("first_image2"),
						rs.getDouble("latitude"), 
						rs.getDouble("longitude"));
                result.add(mapdto);
            }

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}

		return result;
	}

	@Override
	public String getDescription(int content_id) {
		String result = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int ret = -1;
		
		try {

			con = DBManager.getConnection();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT overview FROM attraction_description where content_id = ?;");

			// Statement 객체 - select
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, content_id);

			rs = pstmt.executeQuery();
			
			if (rs.next()) {
                
                result = rs.getString("overview");
            }

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}
		
		return result;
	}

}
