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
public class Clg_Reg_College_Staff_List_ReportDAOImpl implements Clg_Reg_College_Staff_List_ReportDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<Map<String, Object>> getClg_Staff_List_Teacher_View(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			String sq2 = "select pt.*,ctf.id as teach_fac_id,ctf.*,TO_CHAR(ctf.date_of_appoinment,'DD/MM/YYYY') as date_of_appoinment from clg_reg_college_staff_list_teacher pt\n"
					+ "inner join clg_reg_add_staff_teaching_faculty ctf on ctf.id=pt.teacher_id where pt.institute_id=?";

			PreparedStatement stmt = conn.prepareStatement(sq2);
			stmt.setInt(1, inst_id);
			ResultSet rs = stmt.executeQuery();
			System.err.println("STMT--Teacher_View---" + stmt);
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
	
	@Override
	public List<Map<String, Object>> getClg_Staff_List_Guest_View(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

//			String sq2 = "select pt.*,ctf.* from clg_reg_college_staff_list_guest_teacher pt\n"
//					+ "inner join clg_reg_add_staff_guest_faculty ctf on ctf.id=pt.guest_teacher_id where pt.institute_id=?";
			
			String sq2 = "select p.id,p.s_id,institute_id,guest_prefix_id,guest_first_name,guest_middle_name,guest_last_name,\n"
					+ "TO_CHAR(guest_date_of_appoinment,'DD/MM/YYYY') as guest_date_of_appoinment,appoinment_letter,exe_certi,\n"
					+ "c.nature_of_appoinment,guest_emp_id,guest_emp_department,guest_emp_qualification,guest_emp_pay_scale,guest_emp_designation,guest_pan_no,guest_aadhar_no\n"
					+ "from clg_reg_add_staff_guest_faculty p\n"
					+ "inner join clg_reg_nature_of_appoinment_mstr c on c.id=p.guest_nature_of_appoinment::int\n"
					+ "where institute_id = ? order by id";

			PreparedStatement stmt = conn.prepareStatement(sq2);
			stmt.setInt(1, inst_id);
			ResultSet rs = stmt.executeQuery();
			System.err.println("STMT---Guest_View--" + stmt);
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
	
	@Override
	public List<Map<String, Object>> getClg_Staff_List_Non_Teaching_View(int id, int inst_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

//			String sq2 = "select pt.*,ctf.* from clg_reg_college_staff_list_non_teaching_faculty pt\n"
//					+ "inner join clg_reg_add_staff_non_teaching_staff ctf on ctf.id=pt.non_teacher_id where pt.institute_id=?";
			
			String sq2 = "select p.id,s_id,institute_id,non_prefix_id,non_first_name,non_middle_name,non_last_name,\n"
					+ "TO_CHAR(non_date_of_appoinment,'DD/MM/YYYY') as non_date_of_appoinment,non_appoinment_letter,non_exe_certi,\n"
					+ "c.nature_of_appoinment,non_emp_id,non_emp_department,non_emp_qualification,non_emp_pay_scale,non_emp_designation,non_pan_no,non_aadhar_no\n"
					+ "from clg_reg_add_staff_non_teaching_staff p\n"
					+ "inner join clg_reg_nature_of_appoinment_mstr c on c.id=p.non_nature_of_appoinment::int\n"
					+ "where institute_id = ? order by p.id";

			PreparedStatement stmt = conn.prepareStatement(sq2);
			stmt.setInt(1, inst_id);
			ResultSet rs = stmt.executeQuery();
			System.err.println("STMT---Non_Teaching_View--" + stmt);
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
	
	
	@Override
	public List<Map<String,Object>> getClg_Staff_List_Upload_Doc_View(int id, int institute_id, String role) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    Connection conn = null;
	    String sq2 = null;
	    try{          
	    	conn = dataSource.getConnection();
		    	sq2="select * from clg_reg_college_staff_list_upload_doc \n"
		    			+ "where institute_id=? ";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq2);
	        	stmt.setInt(1, institute_id);
	        ResultSet rs = stmt.executeQuery();  
	        System.err.println("STMT---getClg_camera_locationinfoReport--"+stmt);
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
