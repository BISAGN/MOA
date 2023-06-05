package com.AyushEdu.dao.Registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Common_Controller.CommonController;

@Repository
public class E_Form_Student_Dtl_DAOImpl implements E_Form_Student_Dtl_DAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	@Autowired 
	CommonController common;
	
	@Override
	public ArrayList<ArrayList<String>>E_Form_Student_DtlData(String role,String id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
				
				
				if(role.toUpperCase().contains("NCISM")) {
					q = "select code,institute_name,sm.state_name,ir.id as inst_id,ir.state_id,ir.total_sanctioned_seat,ir.dashboard_status,\n"
							+ " (select count(*) from edu_lms_student_details sd\n"
							+ " inner join edu_lms_degree_mstr dm on dm.id=sd.degree\n"
							+ " where institude_userid = ? and dm.type_of_degree = 15) as total_admitted_seat \n"
							+ " from edu_lms_institute_reg ir\n"
							+ " inner join edu_lms_state_mstr sm on sm.state_id=ir.state_id\n"
							+ " where ir.id=?";
				}
				if(role.toUpperCase().contains("NCH")) {
					q = "select code,institute_name,sm.state_name,ir.id as inst_id,ir.state_id,ir.total_sanctioned_seat,ir.dashboard_status,\n"
							+" (select count(*) from edu_lms_nch_student_details sd \n"
							+" inner join edu_lms_degree_mstr dm on dm.id=sd.degree \n"
							+" where institude_userid = ? and dm.type_of_degree = 15) as total_admitted_seat \n"
							+" from edu_lms_institute_reg ir\n"
							+" inner join edu_lms_state_mstr sm on sm.state_id=ir.state_id\n"
							+" where ir.id=? ";
				}

				stmt = conn.prepareStatement(q);
				stmt.setInt(1, Integer.parseInt(id));
				stmt.setInt(2, Integer.parseInt(id));
				System.err.println("\n\nquey print------>"+stmt+"\n\n");
				ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("code"));//0
				list.add(rs.getString("institute_name"));//1
				list.add(rs.getString("state_name"));//2
				list.add(rs.getString("inst_id"));//3
				list.add(rs.getString("state_id"));//4
				list.add(rs.getString("total_sanctioned_seat"));//5
				list.add(rs.getString("total_admitted_seat"));//6
				list.add(String.valueOf(rs.getInt("total_sanctioned_seat")-rs.getInt("total_admitted_seat")));//7
				list.add(rs.getString("dashboard_status"));//8
				
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
//		System.err.println("Alist--"+alist);
		return alist;
	}
	
	@Override
	public ArrayList<ArrayList<String>>FilledDataofStudents(String inst_id,String role) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			if(role.toUpperCase().contains("NCISM")) {
				q = "select sd.id,sd.institude_userid,sd.inst_code,sd.state,sd.name as first_name,sd.last_name,sd.mother_name,sd.father_name,\n"
						+ "sd.dob,sd.email,sd.neet_application_no,sd.neet_roll_no,sd.neet_rank,sd.neet_percentile,sd.neet_marks,\n"
						+ "sd.quota,sd.category,sd.counc_auth,sd.degree ,l.userid \n,sd.pg_subject,to_char(sd.created_date,'yyyy-mm-dd') as created_date\n"
						
						///////   Rajdip Change
						+ ",sd.fees_date,sd.fees_receipt\n"
						
						//////////
						
						+ "from edu_lms_student_details sd \n"
						+ "left join logininformation l on l.email_id=sd.email \n"
						+ "inner join edu_lms_degree_mstr dm on dm.id=sd.degree\n"
						+ "where sd.institude_userid=? and dm.type_of_degree = 15 order by sd.id asc";
			}
			if(role.toUpperCase().contains("NCH")) {
				q = "select sd.id,sd.institude_userid,sd.inst_code,sd.state,sd.name as first_name,sd.last_name,sd.mother_name,sd.father_name,\n"
						+ "sd.dob,sd.email,sd.neet_application_no,sd.neet_roll_no,sd.neet_rank,sd.neet_percentile,sd.neet_marks,\n"
						+ "sd.quota,sd.category,sd.counc_auth,sd.degree--,l.userid \n,sd.pg_subject,to_char(sd.created_date,'yyyy-mm-dd') as created_date\n"
						+ "from edu_lms_nch_student_details sd \n"
						+ "--left join logininformation l on l.email_id=sd.email \n"
						+ "inner join edu_lms_degree_mstr dm on dm.id=sd.degree\n"
						+ "where sd.institude_userid=? and dm.type_of_degree = 15 order by sd.id asc";
			}
				
			stmt = conn.prepareStatement(q);
				stmt.setInt(1, Integer.parseInt(inst_id));
				System.err.println("\n\nFilledDataofStudents------>"+stmt+"\n\n");
				ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("institude_userid"));//0
				list.add(rs.getString("inst_code"));//1
				list.add(rs.getString("state"));//2
				list.add(rs.getString("first_name"));//3
				list.add(rs.getString("last_name"));//4
				list.add(rs.getString("mother_name"));//5
				list.add(rs.getString("father_name"));//6
				list.add(rs.getString("dob"));//7
				list.add(rs.getString("email"));//8
				list.add(rs.getString("neet_application_no"));//9
				list.add(rs.getString("neet_roll_no"));//10
				list.add(rs.getString("neet_rank"));//11
				list.add(rs.getString("neet_percentile"));//12
				list.add(rs.getString("neet_marks"));//13
				list.add(rs.getString("quota"));//14
				list.add(rs.getString("category"));//15
				list.add(rs.getString("counc_auth"));//16
				list.add(rs.getString("id"));//17
				list.add(rs.getString("degree"));//18
				if(role.toUpperCase().contains("NCISM")) {
					list.add(rs.getString("userid"));//19 
				}
				if(role.toUpperCase().contains("NCH")) {
					list.add("0");//19 userid
				}
				list.add(rs.getString("pg_subject"));//20
				list.add(rs.getString("created_date"));//21
				
				//////////  Rajdip Change
				if(role.toUpperCase().contains("NCISM")) {
					list.add(rs.getString("fees_date"));//22
					list.add(rs.getString("fees_receipt"));//23
				}
				if(role.toUpperCase().contains("NCH")) {
					list.add("");//22 fees_date
					list.add("");//23 fees_receipt
				}
				
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
	public ArrayList<ArrayList<String>>getSeatcountsbyPGSubject(String degree,String subject,String inst_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			q = "select seat as total_sanctioned_seat,(select count(*) from edu_lms_student_details where institude_userid = ? and degree=? and pg_subject=?) as total_admitted_seat\n"
					+ "from edu_lms_subjects_wise_pg_seats a\n"
					+ "where a.institute_id=? and a.degree=? and a.pg_subject=? and a.status=1";
				
			stmt = conn.prepareStatement(q);
			
				stmt.setInt(1, Integer.parseInt(inst_id));
				stmt.setInt(2, Integer.parseInt(degree));
				stmt.setInt(3, Integer.parseInt(subject));
				stmt.setInt(4, Integer.parseInt(inst_id));
				stmt.setInt(5, Integer.parseInt(degree));
				stmt.setInt(6, Integer.parseInt(subject));
				
				System.err.println("\n\ngetSeatcountsbyPGSubject------>"+stmt+"\n\n");
				
				ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("total_sanctioned_seat"));//0
				list.add(rs.getString("total_admitted_seat"));//1
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
	public ArrayList<ArrayList<String>>E_Form_Student_DtlDataPG(String role,String id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
				
				
				if(role.toUpperCase().contains("NCISM")) {
					q = "select code,institute_name,sm.state_name,ir.id as inst_id,ir.state_id\n"
							+" from edu_lms_institute_reg ir\n"
							+ "inner join edu_lms_state_mstr sm on sm.state_id=ir.state_id\n"
							+ "where ir.id=? ";
				}
				if(role.toUpperCase().contains("NCH")) {
					q = "select code,institute_name,sm.state_name,ir.id as inst_id,ir.state_id\n"
							+" from edu_lms_institute_reg ir\n"
							+ "inner join edu_lms_state_mstr sm on sm.state_id=ir.state_id\n"
							+ "where ir.id=? ";
				}

				stmt = conn.prepareStatement(q);
				stmt.setInt(1, Integer.parseInt(id));
				System.err.println("\n\nquey print------>"+stmt+"\n\n");
				ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("code"));//0
				list.add(rs.getString("institute_name"));//1
				list.add(rs.getString("state_name"));//2
				list.add(rs.getString("inst_id"));//3
				list.add(rs.getString("state_id"));//4
//				list.add(rs.getString("pg_dashboard_status"));//5
				
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
//		System.err.println("Alist--"+alist);
		return alist;
	}
	
	@Override
	public ArrayList<ArrayList<String>>FilledDataofStudentsPG(String inst_id,String role,String subject,String intake_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String searchintake = "";
		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			if(intake_id.equals("FS")) {
				searchintake = " and (sd.intake_id=? or sd.intake_id=?)";
			}else {
				searchintake = " and sd.intake_id=? ";
			}
			
			if(role.toUpperCase().contains("NCISM")) {
				q = "select sd.id,sd.institude_userid,sd.inst_code,sd.state,sd.name as first_name,sd.last_name,sd.mother_name,sd.father_name,\n"
						+ "sd.dob,sd.email,sd.neet_application_no,sd.neet_roll_no,sd.neet_rank,sd.neet_percentile,sd.neet_marks,\n"
						+ "sd.quota,sd.category,sd.counc_auth,sd.degree--,l.userid \n,sd.pg_subject,sd.intake_id\n"
						+ "from edu_lms_student_details sd \n"
						+ "--inner join logininformation l on l.email_id=sd.email \n"
						+ "inner join edu_lms_degree_mstr dm on dm.id=sd.degree\n"
						+ "where sd.institude_userid=? and sd.pg_subject=? "+searchintake+"  and dm.type_of_degree = 16 order by sd.id asc";
			}
			if(role.toUpperCase().contains("NCH")) {
				q = "select sd.id,sd.institude_userid,sd.inst_code,sd.state,sd.name as first_name,sd.last_name,sd.mother_name,sd.father_name,\n"
						+ "sd.dob,sd.email,sd.neet_application_no,sd.neet_roll_no,sd.neet_rank,sd.neet_percentile,sd.neet_marks,\n"
						+ "sd.quota,sd.category,sd.counc_auth,sd.degree--,l.userid \n,sd.pg_subject,sd.intake_id\n"
						+ "from edu_lms_nch_student_details sd \n"
						+ "--inner join logininformation l on l.email_id=sd.email \n"
						+ "inner join edu_lms_degree_mstr dm on dm.id=sd.degree\n"
						+ "where sd.institude_userid=? and sd.pg_subject=? "+searchintake+" and dm.type_of_degree = 16 order by sd.id asc";
			}
			
				
			stmt = conn.prepareStatement(q);
				stmt.setInt(1, Integer.parseInt(inst_id));
				stmt.setInt(2, Integer.parseInt(subject));
				if(intake_id.equals("FS")) {
					stmt.setInt(3, Integer.parseInt("1"));
					stmt.setInt(4, Integer.parseInt("2"));
				}else {
					stmt.setInt(3, Integer.parseInt(intake_id));
				}
				
				
				System.err.println("\n\nFilledDataofStudentsPG------>"+stmt+"\n\n");
				ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("institude_userid"));//0
				list.add(rs.getString("inst_code"));//1
				list.add(rs.getString("state"));//2
				list.add(rs.getString("first_name"));//3
				list.add(rs.getString("last_name"));//4
				list.add(rs.getString("mother_name"));//5
				list.add(rs.getString("father_name"));//6
				list.add(rs.getString("dob"));//7
				list.add(rs.getString("email"));//8
				list.add(rs.getString("neet_application_no"));//9
				list.add(rs.getString("neet_roll_no"));//10
				list.add(rs.getString("neet_rank"));//11
				list.add(rs.getString("neet_percentile"));//12
				list.add(rs.getString("neet_marks"));//13
				list.add(rs.getString("quota"));//14
				list.add(rs.getString("category"));//15
				list.add(rs.getString("counc_auth"));//16
				list.add(rs.getString("id"));//17
				list.add(rs.getString("degree"));//18
				list.add("0");//19
				list.add(rs.getString("pg_subject"));//20
				list.add(rs.getString("intake_id"));//21
				
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
	public ArrayList<ArrayList<String>>getPGDashboardstatusbySubject(String inst_id,String subject) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
				q = "select pg_dashboard_status from edu_lms_subjects_wise_pg_seats where institute_id = ? and pg_subject = ?";
				
			stmt = conn.prepareStatement(q);
				stmt.setInt(1, Integer.parseInt(inst_id));
				stmt.setInt(2, Integer.parseInt(subject));
				System.err.println("\n\ngetPGDashboardstatusbySubject------>"+stmt+"\n\n");
				ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("pg_dashboard_status"));//0
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
