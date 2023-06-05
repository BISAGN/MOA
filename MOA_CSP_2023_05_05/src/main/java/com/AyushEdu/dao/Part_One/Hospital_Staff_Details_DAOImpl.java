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

import com.AyushEdu.dao.Mentor_Mentee.Approve_Request_Dao;

@Repository
public class Hospital_Staff_Details_DAOImpl implements Hospital_Staff_Details_DAO{

	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	

	public ArrayList<ArrayList<String>> Gethospostdata() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

			q =  "select id, post from hosp_post_mstr where status = 1 order by id\n";
			

			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt=====Post=Devang========"+stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("post"));// 1

				list.add(alist);
				
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
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	
	
	public List<Map<String,Object>> gethospitalstaffdetails(int institute_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();

	    	String sq1="select id,post,type,no_of_av_staff from hosp_staff_details where institute_id =?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq1);
	        stmt.setInt(1, institute_id);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT---11 Apr--"+stmt);
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
	   }catch(SQLException e){
		   e.printStackTrace();
	   }        
	   return list;
	}
	
	
	//GET INSTITUTE_ID
	public ArrayList<ArrayList<String>> getInstitute_id(String user_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

			q =  "select institute_id from logininformation where userid = ? ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(user_id));
			System.err.println("stmt=====INSTITUTE ID========"+stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("institute_id"));// 0

				list.add(alist);
				
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
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	
	public ArrayList<ArrayList<String>> GetTypedata() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

			q =  "select id, type from hosp_type_mstr where status = 1 order by id\n";
			

			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt=====Type===Devang========"+stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("type"));// 1

				list.add(alist);
				
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
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
		

}