package com.AyushEdu.dao.LMS_Attendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Exp_Excel_Student_Attendance_DaoImpl implements Exp_Excel_Student_Attendance_Dao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public ArrayList<ArrayList<String>>Exp_Excel_Student_attendance(String institute_id,String userid,String role,String role_id,String professional_hid) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			int lastDay =0;
			
			String tb_name = "";
			if(role.contains("NCISM")) {
				tb_name = " edu_lms_student_details ";
			}
			if(role.contains("NCH")) {
				tb_name = "edu_lms_nch_student_details";
			}
//			q="select lo.username,"+temp+" from logininformation lo left join tb_lms_attendance_mster at on at.department=lo.username group by extract(mon from current_timestamp ) ,lo.username";
			 
//			 q="select l.login_name,atd.ayush_id,atd.attendance,TO_CHAR(current_date,'DD/MM/YYYY')  as attendance_date from logininformation l\n"
//			 		+ "inner join tb_nch_add_teacher_details atd on atd.user_id=l.userid where l.institute_id=? ";
			 
//			 q="select l.login_name,atd.ayush_id from logininformation l\n"
//				 		+ "inner join tb_nch_add_teacher_details atd on atd.user_id=l.userid where l.institute_id=? ";
			 
//			 q="select l.login_name,'' as ayush_id,u.role_id,l.institute_id from logininformation l\n"
//			 		+ "inner join userroleinformation u on u.user_id=l.userid\n"
//			 		+ "where u.role_id=26";
			String semester="";
			 
			q="select l.login_name,sd.ayush_id,u.role_id,l.institute_id \n"
			 		+ "from logininformation l\n"
			 		+ "inner join userroleinformation u on u.user_id=l.userid\n"
			 		+ "inner join roleinformation r on r.role_id=u.role_id\n"
			 		+ "inner join "+tb_name+" sd on sd.email=l.email_id\n"
			 		+ "inner join edu_cc_tb_professional_mstr p on p.id=sd.semester::int\n"
			 		+ "where r.role=?\n"
			 		+ "and l.institute_id=(select institute_id from logininformation where userid = ?)  and sd.verified_status=1 and semester= ? ";
			
			 role="Student_"+role;
			
				stmt = conn.prepareStatement(q);
				stmt.setString(1, role);
				stmt.setInt(2, Integer.parseInt(userid));
				if(professional_hid.equals("15")){
					professional_hid = "1";
				}
				if(professional_hid.equals ("16")){
					professional_hid = "2";
				}
				if(professional_hid.equals ("17")){
					professional_hid = "3";
				}
				stmt.setString(3, professional_hid);
				
				
				System.err.println("quey print------>"+stmt);
				ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int p_count=0;
				ArrayList<String> list = new ArrayList<String>();
//				System.err.println("DAOLIST"+list);
				list.add(rs.getString("login_name"));
				list.add(rs.getString("ayush_id"));

				for(int i=1; i<=lastDay; i++) {
					if (rs.getString("_"+i).equals("A")) {
						list.add("A");
					}else if (rs.getString("_"+i).equals("P")){
						list.add("P");
						p_count++;
					}
				}
				list.add(p_count+"");
				alist.add(list);
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
		return alist;
	}
	
	@Override
	public ArrayList<ArrayList<String>>systemofstud_excel(String userid) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = "";
		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select ir.system_id\n"
					+ "from edu_lms_institute_reg ir\n"
					+ "inner join logininformation l on l.institute_id=ir.id\n"
					+ "where l.userid=? ";
			
			
				stmt = conn.prepareStatement(q);
				stmt.setInt(1, Integer.parseInt(userid));
				System.err.println("quey print------>"+stmt);
				ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int p_count=0;
				ArrayList<String> list = new ArrayList<String>();
//				System.err.println("DAOLIST"+list);
				list.add(rs.getString("system_id"));

				alist.add(list);
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
		return alist;
	}
	@Override
	public ArrayList<ArrayList<String>> getCourseidByCoursename(String course_sheet) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q="select id from edu_lms_course_mstr where upper(course_name) like ?";
			
			stmt = conn.prepareStatement(q);
			stmt.setString(1, "%" + course_sheet.toUpperCase() +"%");
			System.err.println("Child Table Data======29====="+stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(rs.getString("id"));// 0
				
				alist.add(list);
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
		return alist;
	}
	
	
	
}
