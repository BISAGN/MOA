package com.AyushEdu.dao.Part_One;

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

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Clg_reg_College_Declaration_Report_DAOImpl implements Clg_reg_College_Declaration_Report_DAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	//GET PRINACIPAL NAME
	
	@Override
	public List<Map<String, Object>> GetPrinacipal_Name(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			String sq2 = "select login_name\n"
					+ "from logininformation li\n"
					+ "inner join userroleinformation uri on uri.user_id = li.userid\n"
					+ "inner join roleinformation ri on ri.role_id = uri.role_id\n"
					+ "where ri.role='Principal_NCH' and li.institute_id = ? ";

			PreparedStatement stmt = conn.prepareStatement(sq2);
			stmt.setInt(1, inst_id);
			ResultSet rs = stmt.executeQuery();
			System.err.println("STMT-----" + stmt);
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
		}
		return list;
	}
	
	
	
	//getDeclarationUploadDocumentsFetch
	@Override
	public List<Map<String, Object>> getDeclaration_UploadDocumentsFetch(String institute_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			String sq2 = "select * from  clg_reg_college_declaration where institute_id = ? ";

			PreparedStatement stmt = conn.prepareStatement(sq2);
			stmt.setInt(1, Integer.parseInt(institute_id));
			ResultSet rs = stmt.executeQuery();
			System.err.println("STMT-----" + stmt);
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
		}
		return list;
	}
	

}
