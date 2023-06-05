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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class Clg_Reg_Hospital_Staff_Dtls_DaoImpl implements Clg_Reg_Hospital_Staff_Dtls_Dao {
	@Autowired
	private DataSource dataSource;
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
	public List<Map<String,Object>> getAllhospitaldetails(int institute_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    
	    	String sq1="select id as mainid,name,post,department,document,remarks from clg_reg_hospital_medical_staff_details where institute_id=?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq1);
	        stmt.setInt(1, institute_id);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT--Medical-"+stmt);
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
	
	
	public List<Map<String,Object>> getAllhospital_Paramedical_details(int institute_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    
	    	String sq1="select id as  mainid,name,post,department,document,remarks from clg_reg_hospital_paramedical_staff_details where institute_id=?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq1);
	        stmt.setInt(1, institute_id);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT-Para-Medical-"+stmt);
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
	
	public List<Map<String,Object>> getAllhospital_auxillarystaff_details(int institute_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    
	    	String sq1="select id as  mainid,name,post,document,remarks  from clg_reg_hospital_auxillary_medical_staff_details where institute_id=?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq1);
	        stmt.setInt(1, institute_id);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT-AUXI-Medical-"+stmt);
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
	//GET P_ID
	public ArrayList<ArrayList<String>> getp_idfromuser_id(String user_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

			q =  "select cc.id,cc.institute_id \n"
					+ "from logininformation li\n"
					+ "inner join clg_reg_hospital_paramedical_staff_details cc on cc.institute_id = li.institute_id\n"
					+ "where userid = ? ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(user_id));
			System.err.println("stmt=====P ID=of Para Medical=tb====="+stmt);
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
	
	//GET MEDICAL POST LIST
	public List<Map<String,Object>> getMedicalPostList() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    
	    	String sq1="select * from hosp_post_mstr where medical_status = 1 order by id";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq1);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT--Medical-"+stmt);
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
	
	//GET PARA-MEDICAL POST LIST
		public List<Map<String,Object>> getParaMedicalPostList() {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		    Connection conn = null;
		    try{          
		    	conn = dataSource.getConnection();
		    
		    	String sq1="select * from hosp_post_mstr where para_med_status = 1 order by id";
		    	
		        PreparedStatement stmt = conn.prepareStatement(sq1);
		        ResultSet rs = stmt.executeQuery();  
		        System.err.println("STMT--Medical-"+stmt);
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

		//GET Auxillary POST LIST
				public List<Map<String,Object>> getAuxillaryPostList() {
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				    Connection conn = null;
				    try{          
				    	conn = dataSource.getConnection();
				    
				    	String sq1="select * from hosp_post_mstr where auxi_status = 1 order by id";
				    	
				        PreparedStatement stmt = conn.prepareStatement(sq1);
				        ResultSet rs = stmt.executeQuery();  
				        System.err.println("STMT--Medical-"+stmt);
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
