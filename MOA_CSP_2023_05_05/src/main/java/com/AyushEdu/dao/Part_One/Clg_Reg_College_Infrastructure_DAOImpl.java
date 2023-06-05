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
public class Clg_Reg_College_Infrastructure_DAOImpl implements Clg_Reg_College_Infrastructure_DAO{

	
	@Autowired
	private SessionFactory sessionFactory;
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


//GET P_ID
public ArrayList<ArrayList<String>> getp_idfromuser_id(String user_id) {

	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	try {
		
		conn = dataSource.getConnection();

		q =  "select cc.id,cc.institute_id \n"
				+ "from logininformation li\n"
				+ "inner join clg_reg_infra_college_council cc on cc.institute_id = li.institute_id\n"
				+ "where userid = ? ";

		PreparedStatement stmt = conn.prepareStatement(q);
		stmt.setInt(1, Integer.parseInt(user_id));
		System.err.println("stmt=====P ID========"+stmt);
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


//GET QULIFICATION DETAILS FOR PDF
public ArrayList<ArrayList<String>> getAllDepartment_name() {

	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	try {
		
		conn = dataSource.getConnection();

		q =  "select id,department from tb_nch_department_mstr where status = 1 order by id\n";

		PreparedStatement stmt = conn.prepareStatement(q);
		System.err.println("stmt=====Department========="+stmt);
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			ArrayList<String> alist = new ArrayList<String>();
			alist.add(rs.getString("id"));// 0
			alist.add(rs.getString("department"));// 1

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


//GET DOCUMENT ATTACHMENT DETAILS
public ArrayList<ArrayList<String>> getCollege_Council_Details(int institute_id) {

	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	try {
		conn = dataSource.getConnection();

		q="select id,council_check,council_document,college_website,\n"
				+ "TO_CHAR(website_update_date,'DD/MM/YYYY') as website_update_date,cctv_status,login_url,\n"
				+ "username,password,biometric_status,college_working_hours,institute_id\n"
				+ "from clg_reg_infra_college_council where institute_id = ?";

		PreparedStatement stmt = conn.prepareStatement(q);
		stmt.setInt(1, institute_id);
		ResultSet rs = stmt.executeQuery();
		System.err.println("Documents Details================"+stmt);
		while (rs.next()) {
			ArrayList<String> alist = new ArrayList<String>();
			alist.add(rs.getString("id"));// 0
			alist.add(rs.getString("council_check"));// 1
			alist.add(rs.getString("council_document"));// 2
			alist.add(rs.getString("college_website"));// 3
			alist.add(rs.getString("website_update_date"));// 4
			alist.add(rs.getString("cctv_status"));// 5
			alist.add(rs.getString("login_url"));// 6
			alist.add(rs.getString("username"));// 7
			alist.add(rs.getString("password"));// 8
			alist.add(rs.getString("biometric_status"));// 9
			alist.add(rs.getString("college_working_hours"));// 10
			alist.add(rs.getString("institute_id"));// 11
			
	
			list.add(alist);
		}
		System.err.println("DOCUMENT LIST============="+list);
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

@Override
public List<Map<String,Object>> getClg_central_lib_info(int institute_id) {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    Connection conn = null;
    try{          
    	conn = dataSource.getConnection();
    	
 //   	String sq2="select ic.id as info_conn_mainid,* from clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
    	
    	String sq2="select ccl.*,p.*,chg.*,cai.*,chd.*,cpi.*,cca.*"
    			+ " from clg_reg_infra_central_library ccl\n"
    			+ "inner join clg_reg_infra_college_council p on ccl.p_id=p.id\n"
    			+ "inner join clg_reg_infra_herbal_garden chg on chg.p_id=p.id\n"
    			+ "inner join clg_reg_infra_additional_information cai on cai.p_id=p.id\n"
    			+ "inner join clg_reg_infra_hostel_details chd on chd.p_id=p.id\n"
    			+ "inner join clg_reg_infra_progress_of_institution cpi on cpi.p_id=p.id\n"
    			+ "inner join clg_reg_infra_constructed_area cca on cca.p_id=p.id\n"
    			+ " where p.institute_id=? ";
    	
        PreparedStatement stmt = conn.prepareStatement(sq2);
        stmt.setInt(1, institute_id);
        ResultSet rs = stmt.executeQuery();  
        System.err.println("STMT-----"+stmt);
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

@Override
public List<Map<String,Object>> getDepart_dtl(int institute_id) {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    Connection conn = null;
    try{          
    	conn = dataSource.getConnection();
    	
 //   	String sq2="select ic.id as info_conn_mainid,* from clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
    	
    	String sq2="select ccad.id,ccad.department_id,ccad.department_name,ccad.area_of_department,\n"
    			+ "sum(CAST(ccad.area_of_department AS numeric)) as v\n"
    			+ "from clg_reg_infra_constructed_area_dept ccad\n"
    			+ "inner join clg_reg_infra_college_council p on p.id=ccad.p_id\n"
    			+ " where p.institute_id=? group by 1";
    	
        PreparedStatement stmt = conn.prepareStatement(sq2);
        stmt.setInt(1, institute_id);
        ResultSet rs = stmt.executeQuery();  
        System.err.println("STMT-----"+stmt);
        ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		
		float jj = 0;
		
		while (rs.next()) {
			
			
			jj += rs.getFloat("v");
			
			Map<String, Object> columns = new LinkedHashMap<String, Object>();
			for (int i = 1; i <= columnCount; i++) {
				columns.put(metaData.getColumnLabel(i), rs.getObject(i));
			}
			columns.put("jj", jj);
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
public String getAllDepartment() {

	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	String TEMP="";
	try {
		
		conn = dataSource.getConnection();

		q =  "select id,department from tb_nch_department_mstr where status = 1 order by id\n";

		PreparedStatement stmt = conn.prepareStatement(q);
		System.err.println("stmt=====Department========="+stmt);
		ResultSet rs = stmt.executeQuery();
		
	
		
		ArrayList<String> alist = new ArrayList<String>();
		while (rs.next()) {
			
			
			TEMP+="<tr>"+
			"<td class='sr-no'><p></p></td>"+
			"<td>"+
				"<div class='input-style-1'>"+
					"<input type='text' id='department_name_"+rs.getString("id")+"'"+
						"name='department_name_"+rs.getString("id")+"'"+
						"class='form-control' autocomplete='off'"+
						"value='"+rs.getString("department")+"' readonly>"+
				"</div>"+
				
				"<input type='hidden' id='hid_department_area_"+rs.getString("id")+"' name='hid_department_area_"+rs.getString("id")+"' value='0'>"+
				"<input type='hidden' id='department_id_"+rs.getString("id")+"' name='department_id_"+rs.getString("id")+"' value='"+rs.getString("id")+"'>"+
				
			"</td>"+
			"<td>"+
				"<div class='input-style-1'>"+
					"<input type='text' id='area_of_department_"+rs.getString("id")+"'"+
						"name='area_of_department_"+rs.getString("id")+"'"+
						"class='form-control grand' autocomplete='off' value='0'"+
						"placeholder='Area of Department (in Sq. mtr.)'>"+
				"</div>"+
			"</td>"+
		"</tr>";

		}
		
		System.err.println("list--PG->  "+list);
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
	
	
	
	return TEMP;
}


	//GET MAINTAINANCE RECORDS DETAILS
	@Override
	public List<Map<String,Object>> GetProgress_of_Institution_Details(String institute_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	 //   	String sq2="select ic.id as info_conn_mainid,* from clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
	    	
	    	String sq2="select * from clg_reg_infra_progress_of_institution where institute_id=?";
	    	
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
	
	//GET ADDITIONAL INFORMATION DETAILS
	@Override
	public List<Map<String,Object>> getAdd_Info_Details(String institute_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	  Connection conn = null;
	  try{          
	  	conn = dataSource.getConnection();
	  	
	//   	String sq2="select ic.id as info_conn_mainid,* from clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
	  	
	  	String sq2="select * from clg_reg_infra_additional_information where institute_id=?";
	  	
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
	
	
	//GET HERBAL GARDEN DETAILS
	@Override
	public List<Map<String,Object>> getHerbal_Garden_Details(String institute_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	try{          
		conn = dataSource.getConnection();
		
	// 	String sq2="select ic.id as info_conn_mainid,* from clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
		
		String sq2="select * from clg_reg_infra_herbal_garden where institute_id=?";
		
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
	
	
	//GET LIBRARY DETAILS DETAILS
	@Override
	public List<Map<String,Object>> getCentral_Library_Details(String institute_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	try{          
		conn = dataSource.getConnection();
		
	//	String sq2="select ic.id as info_conn_mainid,* from clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
		
		String sq2="select * from clg_reg_infra_central_library where institute_id=?";
		
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
	
	
	//GET CONSTRUCTED AREA DETAILS
	@Override
	public List<Map<String,Object>> getConstructed_Area_Details(String institute_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	try{          
		conn = dataSource.getConnection();
		
		String sq2="select * from clg_reg_infra_constructed_area where institute_id=?";
		
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


	//GET MESS DETAILS
	@Override
	public List<Map<String,Object>> getMessDetails(String institute_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	try{          
		conn = dataSource.getConnection();
		
		String sq2="select * from clg_reg_infra_mess_details where institute_id=?";
		
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


	//GET HOSTEL DETAILS DETAILS
	@Override
	public List<Map<String,Object>> getHostelDetails(String institute_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	try{          
		conn = dataSource.getConnection();
		
		String sq2="select * from clg_reg_infra_hostel_details where institute_id=?";
		
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