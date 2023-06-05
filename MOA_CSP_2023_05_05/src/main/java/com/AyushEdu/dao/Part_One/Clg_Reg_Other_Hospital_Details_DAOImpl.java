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
public class Clg_Reg_Other_Hospital_Details_DAOImpl implements Clg_Reg_Other_Hospital_Details_DAO{

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	//GET P_ID
	public ArrayList<ArrayList<String>> getp_idfromuser_id(String user_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

			q =  "select cc.id,cc.institute_id \n"
					+ "from logininformation li\n"
					+ "inner join clg_reg_other_hos_dtl_maintenance_records cc on cc.institute_id = li.institute_id\n"
					+ "where userid = ? ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(user_id));
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("institute_id"));// 1

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
	
	//GET MAINTAINANCE RECORDS DETAILS
	@Override
	public List<Map<String,Object>> GetMaintance_Record_Details(String institute_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	 //   	String sq2="select ic.id as info_conn_mainid,* from clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
	    	
	    	String sq2="select * from   clg_reg_other_hos_dtl_maintenance_records where institute_id=?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, Integer.parseInt(institute_id));
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
	   }catch(SQLException e){
		   e.printStackTrace();
	   }        
	   return list;
	}
	
	//GET LABOUR ROOM DETAILS
	@Override
	public List<Map<String,Object>> getLabour_Room_Details(String institute_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	 //   	String sq2="select ic.id as info_conn_mainid,* from clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
	    	
	    	String sq2="select * from clg_reg_other_hos_dtl_labour_room where institute_id=?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, Integer.parseInt(institute_id));
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
	   }catch(SQLException e){
		   e.printStackTrace();
	   }        
	   return list;
	}
	
	//GET OPERATION THEATRE DETAILS
	@Override
	public List<Map<String,Object>> getOperation_Theatre_Details(String institute_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	 //   	String sq2="select ic.id as info_conn_mainid,* from clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
	    	
	    	String sq2="select * from clg_reg_other_hos_dtl_operation_theatre where institute_id=?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, Integer.parseInt(institute_id));
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
	   }catch(SQLException e){
		   e.printStackTrace();
	   }        
	   return list;
	}
	
	//GET INVESTIGATION DETAILS
	@Override
	public List<Map<String,Object>> getInvestigation_Details(String institute_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	 //   	String sq2="select ic.id as info_conn_mainid,* from clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
	    	
	    	String sq2="select * from clg_reg_other_hos_dtl_investigation where institute_id=?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, Integer.parseInt(institute_id));
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
	   }catch(SQLException e){
		   e.printStackTrace();
	   }        
	   return list;
	}
	
	//GET CLINICAL LABORATORY DETAILS
	@Override
	public List<Map<String,Object>> getClinical_Laboratory_Details(String institute_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	 //   	String sq2="select ic.id as info_conn_mainid,* from clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
	    	
	    	String sq2="select * from clg_reg_other_hos_dtl_clinical_laboratory where institute_id=?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, Integer.parseInt(institute_id));
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
	   }catch(SQLException e){
		   e.printStackTrace();
	   }        
	   return list;
	}
	
	//GET FUNCTIONALITY DETAILS
	@Override
	public List<Map<String,Object>> GetFunctionality_Details(String institute_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	 //   	String sq2="select ic.id as info_conn_mainid,* from clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
	    	
	    	String sq2="select *, TO_CHAR(date_of_mou_sign, 'DD/MM/YYYY') as date_of_mou_sign from clg_reg_other_hos_dtl_functionality where institute_id=?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, Integer.parseInt(institute_id));
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
	   }catch(SQLException e){
		   e.printStackTrace();
	   }        
	   return list;
	}
	
}
