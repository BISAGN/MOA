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
public class Clg_Reg_Add_Staff_DAOImpl implements Clg_Reg_Add_Staff_DAO{

	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	

	//GET TEACHING FACULTY DETAILS
	public ArrayList<ArrayList<String>> getTeaching_Faculty_Details(int institute_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			
			
			
			q="select distinct td.id,\n"
					+ "CASE WHEN tf.id IS NULL THEN '0'\n"
					+ "     WHEN tf.id IS NOT NULL THEN tf.id\n"
					+ "END AS main_id,\n"
					+ "CASE WHEN tf.prefix_id IS NULL THEN td.cand_prefix::int\n"
					+ "     WHEN tf.prefix_id IS NOT NULL THEN tf.prefix_id\n"
					+ "END AS cand_prefix,\n"
					+ "CASE WHEN tf.first_name IS NULL THEN td.first_name\n"
					+ "     WHEN tf.first_name IS NOT NULL THEN tf.first_name\n"
					+ "END AS first_name,\n"
					+ "CASE WHEN tf.middle_name IS NULL THEN td.middle_name\n"
					+ "     WHEN tf.middle_name IS NOT NULL THEN tf.middle_name\n"
					+ "END AS middle_name,\n"
					+ "CASE WHEN tf.last_name IS NULL THEN td.last_name\n"
					+ "     WHEN tf.last_name IS NOT NULL THEN tf.last_name\n"
					+ "END AS last_name,\n"
					+ "CASE WHEN tf.date_of_appoinment IS NULL THEN TO_CHAR(td.date_of_joining, 'DD/MM/YYYY')\n"
					+ "     WHEN tf.date_of_appoinment IS NOT NULL THEN TO_CHAR(tf.date_of_appoinment, 'DD/MM/YYYY')\n"
					+ "END AS date_of_joining,\n"
					+ "CASE WHEN tf.nature_of_appoinment IS NULL THEN td.nature_of_appointment\n"
					+ "     WHEN tf.nature_of_appoinment IS NOT NULL THEN tf.nature_of_appoinment\n"
					+ "END AS nature_of_appointment,\n"
					+ "tf.emp_id,\n"
					+ "CASE WHEN tf.emp_department IS NULL THEN td.name_of_department\n"
					+ "     WHEN tf.emp_department IS NOT NULL THEN tf.emp_department\n"
					+ "END AS name_of_department,\n"
					+ "CASE WHEN tf.emp_qualification IS NULL THEN td.academic_quali\n"
					+ "     WHEN tf.emp_qualification IS NOT NULL THEN tf.emp_qualification\n"
					+ "END AS academic_quali,\n"
					+ "CASE WHEN tf.emp_designation IS NULL THEN ae.designation_name\n"
					+ "     WHEN tf.emp_designation IS NOT NULL THEN tf.emp_designation\n"
					+ "END AS designation_name,\n"
					+ "tf.emp_pay_scale,tf.ugpgcheck,\n"
					+ "CASE WHEN tf.pan_no IS NULL THEN td.pan_no\n"
					+ "     WHEN tf.pan_no IS NOT NULL THEN tf.pan_no\n"
					+ "END AS pan_no,\n"
					+ "CASE WHEN tf.aadhar_no IS NULL THEN td.aadhar_no\n"
					+ "     WHEN tf.aadhar_no IS NOT NULL THEN tf.aadhar_no\n"
					+ "END AS aadhar_no,\n"
					+ "CASE WHEN tf.reg_authority IS NULL THEN '0'\n"
					+ "     WHEN tf.reg_authority IS NOT NULL THEN tf.reg_authority\n"
					+ "END AS reg_authority,\n"
					+ "CASE WHEN tf.reg_type IS NULL THEN '0'\n"
					+ "     WHEN tf.reg_type IS NOT NULL THEN tf.reg_type\n"
					+ "END AS reg_type,\n"
					+ "tf.reg_no,tf.attachment\n"
					+ "from tb_nch_add_teacher_details td\n"
					+ "left join clg_reg_add_staff_teaching_faculty tf on tf.aadhar_no = td.aadhar_no\n"
					+ "inner join tb_nch_prefix_mstr pm on pm.id = td.cand_prefix::int\n"
					+ "inner join tb_nch_teacher_academic_experience_child ae on ae.p_id = td.id\n"
					+ "inner join tb_nch_teacher_quali_child tq on tq.p_id = td.id\n"
					+ "where td.institute_id = ?";
			
//			q="select distinct td.id,\n"
//					+ "CASE \n"
//					+ "    WHEN tf.id IS NULL THEN '0'\n"
//					+ "    WHEN tf.id IS NOT NULL THEN tf.id\n"
//					+ "END AS main_id,\n"
//					+ "td.cand_prefix,td.first_name,td.middle_name,td.last_name,\n"
//					+ "TO_CHAR(td.date_of_joining, 'DD/MM/YYYY') as date_of_joining,td.nature_of_appointment,tf.reg_authority,tf.reg_type,tf.reg_no,\n"
//					+ "td.name_of_department,td.aadhar_no,td.pan_no,ae.designation_name,td.academic_quali,tf.emp_pay_scale,tf.emp_id,tf.ugpgcheck,tf.attachment\n"
//					+ "from tb_nch_add_teacher_details td\n"
//					+ "left join clg_reg_add_staff_teaching_faculty tf on tf.aadhar_no = td.aadhar_no\n"
//					+ "inner join tb_nch_prefix_mstr pm on pm.id = td.cand_prefix::int\n"
//					+ "inner join tb_nch_teacher_academic_experience_child ae on ae.p_id = td.id\n"
//					+ "inner join tb_nch_teacher_quali_child tq on tq.p_id = td.id\n"
//					+ "where td.institute_id = ?  ";

//			q="select id,s_id,institute_id,prefix_id,first_name,middle_name,last_name,TO_CHAR(date_of_appoinment,'DD/MM/YYYY') as date_of_appoinment,\n"
//					+ "nature_of_appoinment,emp_id,emp_department,emp_qualification,emp_pay_scale,emp_designation,pan_no,aadhar_no\n"
//					+ "from clg_reg_add_staff_teaching_faculty where institute_id = ? order by id";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, institute_id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("cand_prefix"));// 1
				alist.add(rs.getString("first_name"));// 2
				alist.add(rs.getString("middle_name"));// 3
				alist.add(rs.getString("last_name"));// 4
				alist.add(rs.getString("date_of_joining"));// 5
				alist.add(rs.getString("nature_of_appointment"));// 6
				alist.add(rs.getString("name_of_department"));// 7
				alist.add(rs.getString("aadhar_no"));// 8
				alist.add(rs.getString("pan_no"));// 9
				alist.add(rs.getString("designation_name"));// 10
				alist.add(rs.getString("academic_quali"));// 11
				alist.add(rs.getString("emp_pay_scale"));// 12
				alist.add(rs.getString("emp_id"));// 13
				alist.add(rs.getString("main_id"));// 14
				alist.add(rs.getString("ugpgcheck"));// 15
				alist.add(rs.getString("attachment"));// 16
				alist.add(rs.getString("reg_authority"));// 17
				alist.add(rs.getString("reg_type"));// 18
				alist.add(rs.getString("reg_no"));// 19
				
//				alist.add(rs.getString("id"));// 0
//				alist.add(rs.getString("s_id"));// 1
//				alist.add(rs.getString("institute_id"));// 2
//				alist.add(rs.getString("prefix_id"));// 3
//				alist.add(rs.getString("first_name"));// 4
//				alist.add(rs.getString("middle_name"));// 5
//				alist.add(rs.getString("last_name"));// 6
//				alist.add(rs.getString("date_of_appoinment"));// 7
//				alist.add(rs.getString("nature_of_appoinment"));// 8
//				alist.add(rs.getString("emp_id"));// 9
//				alist.add(rs.getString("emp_department"));// 10
//				alist.add(rs.getString("emp_qualification"));// 11
//				alist.add(rs.getString("emp_pay_scale"));// 12
//				alist.add(rs.getString("emp_designation"));// 13
//				alist.add(rs.getString("pan_no"));// 14
//				alist.add(rs.getString("aadhar_no"));// 15
				
		
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
	
	
	
	//GET GUEST FACULTY DETAILS
	public ArrayList<ArrayList<String>> getGuest_Faculty_Details(int institute_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q="select id,s_id,institute_id,guest_prefix_id,guest_first_name,guest_middle_name,guest_last_name,\n"
					+ "TO_CHAR(guest_date_of_appoinment,'DD/MM/YYYY') as guest_date_of_appoinment,appoinment_letter,exe_certi,guest_teaching_attachment,\n"
					+ "guest_nature_of_appoinment,guest_emp_id,guest_emp_department,guest_emp_qualification,guest_emp_pay_scale,guest_emp_designation,guest_pan_no,guest_aadhar_no\n"
					+ "from clg_reg_add_staff_guest_faculty where institute_id = ? order by id";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, institute_id);
			ResultSet rs = stmt.executeQuery();
			System.err.println("Documents Details================"+stmt);
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("s_id"));// 1
				alist.add(rs.getString("institute_id"));// 2
				alist.add(rs.getString("guest_prefix_id"));// 3
				alist.add(rs.getString("guest_first_name"));// 4
				alist.add(rs.getString("guest_middle_name"));// 5
				alist.add(rs.getString("guest_last_name"));// 6
				alist.add(rs.getString("guest_date_of_appoinment"));// 7
				alist.add(rs.getString("guest_nature_of_appoinment"));// 8
				alist.add(rs.getString("guest_emp_id"));// 9
				alist.add(rs.getString("guest_emp_department"));// 10
				alist.add(rs.getString("guest_emp_qualification"));// 11
				alist.add(rs.getString("guest_emp_pay_scale"));// 12
				alist.add(rs.getString("guest_emp_designation"));// 13
				alist.add(rs.getString("guest_pan_no"));// 14
				alist.add(rs.getString("guest_aadhar_no"));// 15
				alist.add(rs.getString("appoinment_letter"));// 16
				alist.add(rs.getString("exe_certi"));// 17
				alist.add(rs.getString("guest_teaching_attachment"));// 18
				
		
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
	
	
	//GET GUEST FACULTY DETAILS
	public ArrayList<ArrayList<String>> getNon_Teaching_Staff_Details(int institute_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q="select id,s_id,institute_id,non_prefix_id,non_first_name,non_middle_name,non_last_name,\n"
					+ "TO_CHAR(non_date_of_appoinment,'DD/MM/YYYY') as non_date_of_appoinment,non_appoinment_letter,non_exe_certi,non_teaching_attachment,\n"
					+ "non_nature_of_appoinment,non_emp_id,non_emp_department,non_emp_qualification,non_emp_pay_scale,non_emp_designation,non_pan_no,non_aadhar_no\n"
					+ "from clg_reg_add_staff_non_teaching_staff where institute_id = ? order by id";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, institute_id);
			ResultSet rs = stmt.executeQuery();
			System.err.println("Documents Details================"+stmt);
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("s_id"));// 1
				alist.add(rs.getString("institute_id"));// 2
				alist.add(rs.getString("non_prefix_id"));// 3
				alist.add(rs.getString("non_first_name"));// 4
				alist.add(rs.getString("non_middle_name"));// 5
				alist.add(rs.getString("non_last_name"));// 6
				alist.add(rs.getString("non_date_of_appoinment"));// 7
				alist.add(rs.getString("non_nature_of_appoinment"));// 8
				alist.add(rs.getString("non_emp_id"));// 9
				alist.add(rs.getString("non_emp_department"));// 10
				alist.add(rs.getString("non_emp_qualification"));// 11
				alist.add(rs.getString("non_emp_pay_scale"));// 12
				alist.add(rs.getString("non_emp_designation"));// 13
				alist.add(rs.getString("non_pan_no"));// 14
				alist.add(rs.getString("non_aadhar_no"));// 15
				alist.add(rs.getString("non_appoinment_letter"));// 16
				alist.add(rs.getString("non_exe_certi"));// 17
				alist.add(rs.getString("non_teaching_attachment"));// 18
				
		
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