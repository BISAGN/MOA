package com.AyushEdu.dao.Alumni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Edit_Profile_DaoImpl implements Edit_Profile_Dao {

	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<Map<String, Object>> GetTechnical_Details_Data(int userid) {
		// TODO Auto-generated method stub
		System.err.println("userid====="+userid);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
					
			q = "select alum_photo,* from edu_alum_register_alumni_clg ar\n"
					+ "inner join logininformation l on l.userid=ar.user_id\n"
					+ "where l.userid=?";
					
			stmt = conn.prepareStatement(q);
			stmt.setInt(1, userid);
			System.err.println("---pppppppppppppp-----------"+stmt);
			ResultSet rs = stmt.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				list.add(columns);
		    }
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return list;
	}
	
	@Override
	public String getImagePathC(String id) {
		String whr = "";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = null;
			query = "select alum_photo from edu_alum_register_alumni_clg ar\n"
//					+ "inner join logininformation l on l.username=ar.alum_name\n"
					+ "where ar.id=? ";
		
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, Integer.parseInt(id));
			System.err.println("sssssssssss"+stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				whr = rs.getString("alum_photo");
			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return whr;
	}
}
