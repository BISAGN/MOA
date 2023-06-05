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
public class Clg_Reg_College_Staff_List_DAOImpl implements Clg_Reg_College_Staff_List_DAO{

	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
public String getFull_time_teacher_details(String institute_id){

	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	String TEMP="";
	try {
		
		conn = dataSource.getConnection();

		q =  "select id,institute_id,s_id,CONCAT(first_name,' ',middle_name,' ',last_name) as full_name,\n"
				+ "pan_no,emp_designation,emp_department\n"
				+ "from clg_reg_add_staff_teaching_faculty where institute_id = ? order by id";

		PreparedStatement stmt = conn.prepareStatement(q);
		stmt.setInt(1, Integer.parseInt(institute_id));
		System.err.println("stmt=====Department========="+stmt);
		ResultSet rs = stmt.executeQuery();
		
	
		
		ArrayList<String> alist = new ArrayList<String>();
		while (rs.next()) {
			
			
			TEMP+="<tr>"+
			"<td class='sr-no'><p></p></td>"+
			"<td><p>"+rs.getString("full_name")+"</p></td>"+
			"<td><p>"+rs.getString("pan_no")+"</p></td>"+
			"<td><p>"+rs.getString("emp_designation")+"</p></td>"+
			"<td><p>"+rs.getString("emp_department")+"</p></td>"+
			"<td>"+
				"<div class='input-style-form-check_block'>"+
					"<div class='form-check radio-style'>"+
						"<input type='radio' id='ug"+rs.getString("id")+"' name='ugpgcheck"+rs.getString("id")+"' class='form-check-input' value='UG'> "+
						"<label for='ug' class='form-check-label'>UG</label>"+
					"</div>"+
					"<div class='form-check radio-style'>"+
						"<input type='radio' id='pg"+rs.getString("id")+"' name='ugpgcheck"+rs.getString("id")+"' class='form-check-input' value='PG'> "+
						"<label for='pg' class='form-check-label'>PG</label>"+
					"</div>"+
				"</div>"+
			"</td>"+
			"<td>"+
			"<div class='input-style-1'>"+
				"<input type='text'"+
					"id='remark"+rs.getString("id")+"' name='remark"+rs.getString("id")+"' class='form-control'"+
					"placeholder='Remark' maxlength='100'>"+
			"</div>"+
			"<input type='hidden' id='hid_teacher_details"+rs.getString("id")+"' name='hid_teacher_details"+rs.getString("id")+"' value='0'>"+
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


//GET FULL TIME TECHER DETAILS
public ArrayList<ArrayList<String>> getAllFull_Time_Teacher_Details(String institute_id){

	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	try {
		
		conn = dataSource.getConnection();

		q =  "select id,institute_id,prefix_id,s_id,first_name,middle_name,last_name,\n"
				+ "TO_CHAR(date_of_appoinment,'DD/MM/YYYY') as date_of_appoinment,nature_of_appoinment,\n"
				+ "emp_id,emp_department,emp_qualification,emp_pay_scale,emp_designation,\n"
				+ "pan_no,emp_designation,emp_department\n"
				+ "from clg_reg_add_staff_teaching_faculty where institute_id = ? order by id";

		PreparedStatement stmt = conn.prepareStatement(q);
		stmt.setInt(1, Integer.parseInt(institute_id));
		System.err.println("stmt=====Department========="+stmt);
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			ArrayList<String> alist = new ArrayList<String>();
			alist.add(rs.getString("id"));// 0
			alist.add(rs.getString("institute_id"));// 1
			alist.add(rs.getString("prefix_id"));// 2
			alist.add(rs.getString("s_id"));// 3

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


public String getGuest_teacher_details(String institute_id){

	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	String TEMP="";
	try {
		
		conn = dataSource.getConnection();

		q =  "select id,institute_id,s_id,CONCAT(guest_first_name,' ',guest_middle_name,' ',guest_last_name) as full_name,\n"
				+ "guest_pan_no,guest_emp_designation,guest_emp_department\n"
				+ "from clg_reg_add_staff_guest_faculty where institute_id = ? order by id";

		PreparedStatement stmt = conn.prepareStatement(q);
		stmt.setInt(1, Integer.parseInt(institute_id));
		ResultSet rs = stmt.executeQuery();
		
	
		
		ArrayList<String> alist = new ArrayList<String>();
		while (rs.next()) {
			
			
			
			TEMP+="<tr>"+
			"<td class='sr-no'><p></p</td>"+
			"<td><p>"+rs.getString("full_name")+"</p></td>"+
			"<td><p>"+rs.getString("guest_pan_no")+"</p></td>"+
			"<td><p>"+rs.getString("guest_emp_designation")+"</p></td>"+
			"<td><p>"+rs.getString("guest_emp_department")+"</p></td>"+
			"<td>"+
				"<div class='input-style-1'>"+
					"<input type='file' id='guest_teacher_doc"+rs.getString("id")+"' name='guest_teacher_doc"+rs.getString("id")+"' class='form-control' accept='.pdf,.zip'>"+
					"<input type='hidden' id='hid_guest_teacher_doc"+rs.getString("id")+"' name='hid_guest_teacher_doc"+rs.getString("id")+"' value=''>"+
					"<div class='note-text'>"+
						"<span class='errorClass' id='guest_teacher_doc_lbl"+rs.getString("id")+"'></span>"+
						"<span class='tikClass' id='guest_teacher_doc_lbltik"+rs.getString("id")+"'></span>"+
					"</div>"+
				"</div>"+
			"</td>"+
			"<td>"+
			"<div class='input-style-1'>"+
			"<input type='text'"+
				"id='guest_teacher_remark"+rs.getString("id")+"' name='guest_teacher_remark"+rs.getString("id")+"' class='form-control'"+
				"placeholder='Remark' maxlength='100'>"+
				"</div>"+
					"<input type='hidden' id='hid_guest_teacher_details"+rs.getString("id")+"' name='hid_guest_teacher_details"+rs.getString("id")+"' value='0'>"+
			"</td>"+
		"</tr>";
			

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
	
	return TEMP;
}



//GET GUEST TECHER DETAILS
public ArrayList<ArrayList<String>> getAllGuest_teacher_details(String institute_id){

	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	try {
		
		conn = dataSource.getConnection();

		q =  "select id,institute_id,guest_prefix_id,s_id,guest_first_name,guest_middle_name,guest_last_name,\n"
				+ "TO_CHAR(guest_date_of_appoinment,'DD/MM/YYYY') as guest_date_of_appoinment,guest_nature_of_appoinment,\n"
				+ "guest_emp_id,guest_emp_department,guest_emp_qualification,guest_emp_pay_scale,guest_emp_designation,\n"
				+ "guest_pan_no,guest_emp_designation,guest_emp_department\n"
				+ "from clg_reg_add_staff_guest_faculty where institute_id = ? order by id";

		PreparedStatement stmt = conn.prepareStatement(q);
		stmt.setInt(1, Integer.parseInt(institute_id));
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			ArrayList<String> alist = new ArrayList<String>();
			alist.add(rs.getString("id"));// 0
			alist.add(rs.getString("institute_id"));// 1
			alist.add(rs.getString("s_id"));// 3

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

//GET TEACHING FACULTY DETAILS
public String getTeaching_Faculty_details(String institute_id){

	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	String TEMP="";
	try {
		
		conn = dataSource.getConnection();

		q =  "select id,institute_id,s_id,CONCAT(first_name,' ',middle_name,' ',last_name) as full_name,\n"
				+ "pan_no,emp_designation,emp_department\n"
				+ "from clg_reg_add_staff_teaching_faculty where institute_id = ? order by id";

		PreparedStatement stmt = conn.prepareStatement(q);
		stmt.setInt(1, Integer.parseInt(institute_id));
		ResultSet rs = stmt.executeQuery();
		
	
		
		ArrayList<String> alist = new ArrayList<String>();
		while (rs.next()) {
			
			
			
			TEMP+="<tr>"+
			"<td class='sr-no'><p></p</td>"+
			"<td><p>"+rs.getString("full_name")+"</p></td>"+
			"<td><p>"+rs.getString("pan_no")+"</p></td>"+
			"<td><p>"+rs.getString("emp_designation")+"</p></td>"+
			"<td><p>"+rs.getString("emp_department")+"</p></td>"+
			"<td>"+
				"<div class='input-style-1'>"+
					"<input type='file' id='teacher_doc"+rs.getString("id")+"' name='teacher_doc"+rs.getString("id")+"' class='form-control' accept='.pdf,.zip'>"+
					"<input type='hidden' id='hid_teacher_doc"+rs.getString("id")+"' name='hid_teacher_doc"+rs.getString("id")+"' value=''>"+
					"<div class='note-text'>"+
						"<span class='errorClass' id='teacher_doc_lbl"+rs.getString("id")+"'></span>"+
						"<span class='tikClass' id='teacher_doc_lbltik"+rs.getString("id")+"'></span>"+
					"</div>"+
				"</div>"+
			"</td>"+
			"<td>"+
			"<div class='input-style-1'>"+
			"<input type='text'"+
				"id='teacher_remark"+rs.getString("id")+"' name='teacher_remark"+rs.getString("id")+"' class='form-control'"+
				"placeholder='Remark' maxlength='100'>"+
				"</div>"+
					"<input type='hidden' id='hid_teaching_faculty_details"+rs.getString("id")+"' name='hid_teaching_faculty_details"+rs.getString("id")+"' value='0'>"+
			"</td>"+
		"</tr>";
			

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
	
	return TEMP;
}



//GET NON TEACHING FACULTY DETAILS
public String getNon_Teaching_Faculty_details(String institute_id){

	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	String TEMP="";
	try {
		
		conn = dataSource.getConnection();

		q =  "select id,institute_id,s_id,CONCAT(non_first_name,' ',non_middle_name,' ',non_last_name) as full_name,\n"
				+ "non_pan_no,non_emp_designation,non_emp_department\n"
				+ "from clg_reg_add_staff_non_teaching_staff where institute_id = ? order by id";

		PreparedStatement stmt = conn.prepareStatement(q);
		stmt.setInt(1, Integer.parseInt(institute_id));
		ResultSet rs = stmt.executeQuery();
		
	
		
		ArrayList<String> alist = new ArrayList<String>();
		while (rs.next()) {
			
			
			
			TEMP+="<tr>"+
			"<td class='sr-no'><p></p</td>"+
			"<td><p>"+rs.getString("full_name")+"</p></td>"+
			"<td><p>"+rs.getString("non_pan_no")+"</p></td>"+
			"<td><p>"+rs.getString("non_emp_designation")+"</p></td>"+
			"<td><p>"+rs.getString("non_emp_department")+"</p></td>"+
			"<td>"+
				"<div class='input-style-1'>"+
					"<input type='file' id='non_teacher_doc"+rs.getString("id")+"' name='non_teacher_doc"+rs.getString("id")+"' class='form-control' accept='.pdf,.zip'>"+
					"<input type='hidden' id='hid_non_teacher_doc"+rs.getString("id")+"' name='hid_non_teacher_doc"+rs.getString("id")+"' value=''>"+
					"<div class='note-text'>"+
						"<span class='errorClass' id='non_teacher_doc_lbl"+rs.getString("id")+"'></span>"+
						"<span class='tikClass' id='non_teacher_doc_lbltik"+rs.getString("id")+"'></span>"+
					"</div>"+
				"</div>"+
			"</td>"+
			"<td>"+
			"<div class='input-style-1'>"+
			"<input type='text'"+
				"id='non_teacher_remark"+rs.getString("id")+"' name='non_teacher_remark"+rs.getString("id")+"' class='form-control'"+
				"placeholder='Remark' maxlength='100'>"+
				"</div>"+
					"<input type='hidden' id='hid_non_teaching_faculty_details"+rs.getString("id")+"' name='hid_non_teaching_faculty_details"+rs.getString("id")+"' value='0'>"+
			"</td>"+
		"</tr>";
			

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
	
	return TEMP;
}


//GET NON TEACHING FACULTY DETAILS
public ArrayList<ArrayList<String>> getAllNon_Teaching_Faculty_details(String institute_id){

	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	try {
		
		conn = dataSource.getConnection();

		q =  "select id,institute_id,non_prefix_id,s_id,non_first_name,non_middle_name,non_last_name,\n"
				+ "TO_CHAR(non_date_of_appoinment,'DD/MM/YYYY') as non_date_of_appoinment,non_nature_of_appoinment,\n"
				+ "non_emp_id,non_emp_department,non_emp_qualification,non_emp_pay_scale,non_emp_designation,\n"
				+ "non_pan_no,non_emp_designation,non_emp_department\n"
				+ "from clg_reg_add_staff_non_teaching_staff where institute_id = ? order by id";

		PreparedStatement stmt = conn.prepareStatement(q);
		stmt.setInt(1, Integer.parseInt(institute_id));
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			ArrayList<String> alist = new ArrayList<String>();
			alist.add(rs.getString("id"));// 0
			alist.add(rs.getString("institute_id"));// 1
			alist.add(rs.getString("s_id"));// 3

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


//GET NON TEACHING FACULTY DETAILS
public ArrayList<ArrayList<String>> GetDocument_Details(String institute_id){

	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	try {
		
		conn = dataSource.getConnection();

		q =  "select id,s_id,institute_id,declaration_check \n"
				+ "from clg_reg_college_staff_list_upload_doc where institute_id = ? ";

		PreparedStatement stmt = conn.prepareStatement(q);
		stmt.setInt(1, Integer.parseInt(institute_id));
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			ArrayList<String> alist = new ArrayList<String>();
			alist.add(rs.getString("id"));// 0
			alist.add(rs.getString("institute_id"));// 1
			alist.add(rs.getString("s_id"));// 2
			alist.add(rs.getString("declaration_check"));// 3

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


public String getAttachmentFilePath(String id,String doc_id){
	String whr = "";
	Connection conn = null;
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		String query = null;
		System.err.println("ID------------================"+id);
		query = "select full_time_teacher_affidavit,guest_teacher_affidavit from clg_reg_college_staff_list_upload_doc where id = ? ";
		stmt = conn.prepareStatement(query);
		stmt.setInt(1, Integer.parseInt(id));
		System.err.println("IMAGE PATH===="+stmt);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
				if(Integer.parseInt(doc_id) == 1) {
				whr = rs.getString("full_time_teacher_affidavit");
				}
				if(Integer.parseInt(doc_id) == 2) {
					whr = rs.getString("guest_teacher_affidavit");
				}
				
		}
		rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return whr;
}


//GET PRINACIPAL NAME
public ArrayList<ArrayList<String>> GetPrinacipal_Name(String institute_id){

	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	try {
		
		conn = dataSource.getConnection();

		q =  "select login_name\n"
				+ "from logininformation li\n"
				+ "inner join userroleinformation uri on uri.user_id = li.userid\n"
				+ "inner join roleinformation ri on ri.role_id = uri.role_id\n"
				+ "where ri.role='Principal_NCH' and li.institute_id = ? ";

		PreparedStatement stmt = conn.prepareStatement(q);
		stmt.setInt(1, Integer.parseInt(institute_id));
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			ArrayList<String> alist = new ArrayList<String>();
			alist.add(rs.getString("login_name"));// 0

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