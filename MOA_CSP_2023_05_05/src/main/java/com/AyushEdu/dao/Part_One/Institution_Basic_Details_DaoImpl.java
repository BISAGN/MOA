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
public class Institution_Basic_Details_DaoImpl implements Institution_Basic_Details_Dao {
	
	@Autowired
	private DataSource dataSource;
	
	
	public List<Map<String,Object>> getAllPersdetails(int inst_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();

	    	String sq1="select bd.id as mainid,TO_CHAR(bd.dop_state_govn , 'DD/MM/YYYY') as date_of_permission,"
	    			+ "TO_CHAR(bd.dop_central_govn , 'DD/MM/YYYY') as date_of_central,TO_CHAR(bd.doa_university , 'DD/MM/YYYY') as date_of_first_affilia,"
	    			+ "TO_CHAR(bd.doc_affilation_university , 'DD/MM/YYYY') as date_of_consent_affilia,TO_CHAR(bd.doc_last_aff_university , 'DD/MM/YYYY') as date_last_consent_affilia,* from clg_reg_inst_info_institution_basic_details bd where inst_id=?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq1);
	        stmt.setInt(1, inst_id);
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
	public List<Map<String,Object>> getAllinfo_connectivity(int inst_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	String sq2="select ic.id as info_conn_mainid,* from clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, inst_id);
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
	public List<Map<String,Object>> getAllinfo_police_st(int inst_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	 //   	String sq2="select ic.id as info_conn_mainid,* from clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
	    	
	    	String sq2="select * from   clg_reg_inst_info_police_station_details where inst_id=?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, inst_id);
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
	public List<Map<String,Object>> getAllinfo_inst_dtl(int inst_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	 //   	String sq2="select ic.id as info_conn_mainid,* from clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
	    	
	    	String sq2="select TO_CHAR(bd.dob , 'DD/MM/YYYY') as date_of_birth,TO_CHAR(bd.date_of_join_princi , 'DD/MM/YYYY') as date_of_join,* from   clg_reg_inst_info_head_of_institution_details bd where inst_id=?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, inst_id);
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
	public List<Map<String,Object>> getAllinfo_dtl_land(int inst_id){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	String sq2="select ic.id as info_conn_mainid,* from clg_reg_inst_info_details_of_land ic where inst_id=?";
	    	
	 //   	String sq2="select TO_CHAR(bd.dob , 'DD/MM/YYYY') as date_of_birth,TO_CHAR(bd.date_of_join_princi , 'DD/MM/YYYY') as date_of_join,* from   clg_reg_inst_info_head_of_institution_details bd where inst_id=?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, inst_id);
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
	public List<Map<String,Object>> getInstname(int inst_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	 //   	String sq2="select ic.id as info_conn_mainid,* from clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
	    	
	    	String sq2="select * from edu_lms_institute_reg where id=?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, inst_id);
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
	public List<Map<String,Object>> getAllinfo_undertaling_repo(int inst_id){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	String sq2="select ic.id as info_conn_mainid,* from clg_reg_inst_info_undertaking_reports ic where inst_id=?";
	    	
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, inst_id);
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
	public List<Map<String,Object>> getAllinfo_intake_cap_child(int inst_id){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	String sq2="select ic.id as info_conn_mainid,* from clg_reg_inst_info_course_intake_capacity_child ic where inst_id=?";
	    	
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, inst_id);
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
	public List<Map<String,Object>> getAllinfo_quali_inst(int inst_id){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	String sq2="select * from clg_reg_inst_info_head_of_institution_details_child ic where inst_id=?";
	    	
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        stmt.setInt(1, inst_id);
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
	
	//GET COURSE FOR UG
	public ArrayList<ArrayList<String>> getAllCourse_UG(int inst_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

		//	q =  "select id,department_name from clg_reg_department_mstr where status = 1 order by id\n";
			
		//	q="select id,system_name from edu_lms_system_mstr where id=45";
			
			
			q="select dm.id,dm.degree_name,ir.total_sanctioned_seat from  edu_lms_system_degree_map_inst smdi\n"
					+ "inner join edu_lms_institute_reg ir on ir.id = smdi.institute_id\n"
					+ "inner join edu_lms_degree_mstr dm on dm.id = smdi.degree_id\n"
					+ "inner join edu_lms_type_of_degree_mstr tdm on tdm.id = dm.type_of_degree\n"
					+ "where ir.id=?";

			PreparedStatement stmt = conn.prepareStatement(q);
			 stmt.setInt(1, inst_id);
			System.err.println("stmt=====Department========="+stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("degree_name"));// 1
				alist.add(rs.getString("total_sanctioned_seat"));// 1

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
	
	
	
	
	public String getAllSubjectforUG(int inst_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String TEMP="";
		try {
			
			conn = dataSource.getConnection();

		
			
		//	q="select id,system_name from edu_lms_system_mstr where id=45";
			
			
			q="select dm.id,dm.degree_name,ir.total_sanctioned_seat from  edu_lms_system_degree_map_inst smdi\n"
					+ "inner join edu_lms_institute_reg ir on ir.id = smdi.institute_id\n"
					+ "inner join edu_lms_degree_mstr dm on dm.id = smdi.degree_id\n"
					+ "inner join edu_lms_type_of_degree_mstr tdm on tdm.id = dm.type_of_degree\n"
					+ "where ir.id=?";

			PreparedStatement stmt = conn.prepareStatement(q);
			 stmt.setInt(1, inst_id);
			System.err.println("stmt=====Subject========="+stmt);
			ResultSet rs = stmt.executeQuery();
			
		
			
			ArrayList<String> alist = new ArrayList<String>();
			while (rs.next()) {
				
				
				TEMP+="<tr>"+
				"<td class='sr-no'><p></p></td>"+
				"<td>"+
					"<div class='input-style-1'>"+
						"<input type='text' id='course_name_ug_"+rs.getString("id")+"'"+
							"name='course_name_ug_"+rs.getString("id")+"'"+
							"class='form-control' autocomplete='off'"+
							"value='"+rs.getString("degree_name")+"' readonly>"+
					"</div>"+
					
					"<input type='hidden' id='hid_course_ug_"+rs.getString("id")+"' name='hid_course_ug_"+rs.getString("id")+"' value='0'>"+
					"<input type='hidden' id='course_id_ug"+rs.getString("id")+"' name='course_id_ug"+rs.getString("id")+"' value='"+rs.getString("id")+"'>"+
					
				"</td>"+
				"<td>"+
					"<div class='input-style-1'>"+
						"<input type='text' id='intake_cap_course_ug_"+rs.getString("id")+"'"+
							"name='intake_cap_course_ug_"+rs.getString("id")+"'"+
							"class='form-control' autocomplete='off' "+
							"placeholder='Intake Capacity' value='"+rs.getString("total_sanctioned_seat")+"'>"+
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
	
	
	//GET COURSE FOR UG
		public ArrayList<ArrayList<String>> getAllCourse_PG() {

			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			try {
				
				conn = dataSource.getConnection();

				q="select id,subject_name from edu_faculty_subject_mstr where fac_course_id=1 and status=1 \n";
				
			

				PreparedStatement stmt = conn.prepareStatement(q);
				
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					ArrayList<String> alist = new ArrayList<String>();
					alist.add(rs.getString("id"));// 0
					alist.add(rs.getString("subject_name"));// 1

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
	
	

	
	
	
	public String getAllSubjectforPG() {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String TEMP="";
		try {
			
			conn = dataSource.getConnection();

		//	q =  "select id,department_name from clg_reg_department_mstr where status = 1 order by id\n";
			
			q="select id,subject_name from edu_faculty_subject_mstr where fac_course_id=1 and status=1 \n";

			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt=====Subject========="+stmt);
			ResultSet rs = stmt.executeQuery();
			
		
			
			ArrayList<String> alist = new ArrayList<String>();
			while (rs.next()) {
				
				
				TEMP+="<tr>"+
				"<td class='sr-no'><p></p></td>"+
				"<td>"+
					"<div class='input-style-1'>"+
						"<input type='text' id='course_name_pg_"+rs.getString("id")+"'"+
							"name='course_name_pg_"+rs.getString("id")+"'"+
							"class='form-control' autocomplete='off'"+
							"value='"+rs.getString("subject_name")+"' readonly>"+
					"</div>"+
					
					"<input type='hidden' id='hid_course_pg_"+rs.getString("id")+"' name='hid_course_pg_"+rs.getString("id")+"' value='0'>"+
					"<input type='hidden' id='course_id_pg"+rs.getString("id")+"' name='course_id_pg"+rs.getString("id")+"' value='"+rs.getString("id")+"'>"+
					
				"</td>"+
				"<td>"+
					"<div class='input-style-1'>"+
						"<input type='text' id='intake_cap_course_pg_"+rs.getString("id")+"'"+
							"name='intake_cap_course_pg_"+rs.getString("id")+"'"+
							"class='form-control' autocomplete='off' value='0'"+
							"placeholder='Intake Capacity'>"+
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
	
	
	
	//GET PID FROM USERID
	public ArrayList<ArrayList<String>> getpid_from_userid(String userid) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();

			
			q="select id,inst_id from clg_reg_inst_info_institution_basic_details where userid=?";
		

			PreparedStatement stmt = conn.prepareStatement(q);
			 stmt.setInt(1, Integer.parseInt(userid));
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("inst_id"));// 1

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
	
	
	@Override
	public ArrayList<ArrayList<String>> getinfofromteacher_code(String teacher_code) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			
			
//			q="select id, present_add_line1,present_add_line2,present_village,present_state,present_pincode,present_phn_no,\n"
//					+ "email,mobile_no from tb_nch_add_teacher_details where teacher_code=? ";
			
			
//			q="select p.id, p.present_add_line1,p.present_add_line2,p.present_village,p.present_state,p.present_pincode,p.present_phn_no,\n"
//					+ "p.email,p.mobile_no,dm.designation from tb_nch_add_teacher_details p\n"
//					+ "inner join tb_nch_teacher_exp_child ec on ec.p_id=p.id\n"
//					+ "inner join tb_nch_designation_mstr dm on dm.id=ec.depart_name::int where p.teacher_code=? ";
			
			q="select p.id, p.present_add_line1,p.present_add_line2,p.present_village,p.present_state,p.present_pincode,p.present_phn_no,\n"
					+ "p.email,p.mobile_no,dm.designation,p.state_board_name,p.state_reg_no,p.first_name,to_char(p.date_of_birth,'DD/MM/YYYY') as dob,to_char(p.date_of_joining,'DD/MM/YYYY')as doj,p.central_reg_no from tb_nch_add_teacher_details p\n"
					+ "inner join tb_nch_teacher_exp_child ec on ec.p_id=p.id\n"
					+ "inner join tb_nch_designation_mstr dm on dm.id=ec.depart_name::int where p.teacher_code=?";
			
			
			stmt = conn.prepareStatement(q);
			stmt.setString(1, (teacher_code) );
			System.err.println("stmt-------"+stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("present_add_line1"));// 1
				list.add(rs.getString("present_add_line2"));// 2
				list.add(rs.getString("present_village"));// 3
				list.add(rs.getString("present_state"));// 4
				list.add(rs.getString("present_pincode"));// 5
				list.add(rs.getString("present_phn_no"));// 6
				list.add(rs.getString("email"));// 7
				list.add(rs.getString("mobile_no"));// 8
				list.add(rs.getString("designation"));// 9
				list.add(rs.getString("state_board_name"));// 10
				list.add(rs.getString("state_reg_no"));// 11
				list.add(rs.getString("first_name"));// 12
				list.add(rs.getString("dob"));// 13
				list.add(rs.getString("doj"));// 14
				list.add(rs.getString("central_reg_no"));// 15
				alist.add(list);
			}
			
			System.err.println("teacher-------"+rs);
			
			
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
		return alist;
	}
	
	
	
	
	
	
	//GET DATE FOR VALIDATION
	
	public ArrayList<ArrayList<String>> getdate(int inst_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();


			
		//	q="select id,to_char(doc_last_aff_university,'YYYY') as date_last  from clg_reg_inst_info_institution_basic_details where inst_id=? ";
			
			q="select id,to_char((year_of_establish_college||'-01')::timestamp without time zone,'YYYY') as date_last  from clg_reg_inst_info_institution_basic_details where inst_id=?";

			PreparedStatement stmt = conn.prepareStatement(q);
			 stmt.setInt(1, inst_id);
			System.err.println("stmt=====Department==2/05======="+stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("date_last"));// 0
//				alist.add(rs.getString("date_last"));// 1
//				alist.add(rs.getString("total_sanctioned_seat"));// 1

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
	
	
	//GET DATE FOR VALIDATION
	
	public ArrayList<ArrayList<String>> getinstName_Code(int inst_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			
			conn = dataSource.getConnection();


			
		//	q="select id,to_char(doc_last_aff_university,'YYYY') as date_last  from clg_reg_inst_info_institution_basic_details where inst_id=? ";
			
			q="select institute_name,code from edu_lms_institute_reg where id=?";

			PreparedStatement stmt = conn.prepareStatement(q);
			 stmt.setInt(1, inst_id);
			System.err.println("stmt=====Department========="+stmt);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("institute_name"));// 0
				alist.add(rs.getString("code"));// 1
//				alist.add(rs.getString("total_sanctioned_seat"));// 1

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
	
	
	//GET Intake Capacity UG DETAILS
	@Override
	public List<Map<String,Object>> getIntake_Cap(String institute_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	 //   	String sq2="select ic.id as info_conn_mainid,* from clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
	    	
	    	String sq2="select * ,TO_CHAR(last_stu_add_date,'DD/MM/YYYY') as last_stu_add_date from clg_reg_inst_info_intake_capacity where inst_id=?";
	    	
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
	
	//GET Intake Capacity PG DETAILS
	@Override
	public List<Map<String,Object>> getIntake_Cap_pg(String institute_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	 //   	String sq2="select ic.id as info_conn_mainid,* from clg_reg_inst_info_information_of_connectivity ic where inst_id=?";
	    	
	    	String sq2="select * ,TO_CHAR(date_stu_admitted,'DD/MM/YYYY') as date_stu_admitted from clg_reg_inst_info_intake_capacity_pg where inst_id=?";
	    	
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
