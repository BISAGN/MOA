package com.AyushEdu.dao.Time_Table;

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
public class Academic_Schedule_DAOImpl implements Academic_Schedule_DAO {

	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	
	
	@Override
	public ArrayList<ArrayList<String>> GetviewDate_Academic_Prof(String professional, int institute_id) {
		
		
//		System.err.println("innnnnnnnnnnnnn"+professional);
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select tm.term,to_char(starting_date,'DD-MON-YYYY') as starting_date,to_char(ending_date,'DD-MON-YYYY') as ending_date\n"
					+ "from edu_tt_academic_details ad\n"
					+ "inner join edu_cc_tb_i3_term_mstr tm on tm.id=ad.term\n"
					+ "where ad.professional = ? and ad.academic_details='001' and ad.institute_id = ?";

			stmt = conn.prepareStatement(q);
			
			stmt.setInt(1, Integer.parseInt(professional));
			stmt.setInt(2, institute_id);
			
			System.err.println("stmt====GetviewInternal_ass_marks====="+stmt);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("starting_date")); // 0
				alist.add(rs.getString("ending_date")); // 1
				alist.add(rs.getString("term")); // 2
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
				}
			}
		}
		return list;
	}

	@Override
	public ArrayList<ArrayList<String>> GetviewDate_Examination_Prof(String professional, int institute_id) {
		
//		System.err.println("innnnnnnnnnnnnn"+professional);
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select tm.term,etm.exam_type,esm.exam_serial,to_char(starting_date,'DD-MON-YYYY') as starting_date,to_char(ending_date,'DD-MON-YYYY') as ending_date\n"
					+ "from edu_tt_academic_details ad\n"
					+ "inner join edu_cc_tb_i3_term_mstr tm on tm.id=ad.term\n"
					+ "inner join edu_cc_tb_exam_type_mstr etm on etm.id=ad.exam_type\n"
					+ "inner join edu_exam_tb_exam_serial_mstr esm on esm.id=ad.exam_serial\n"
					+ "where ad.professional = ? and ad.academic_details='002' and ad.institute_id = ?";

			stmt = conn.prepareStatement(q);
			
			stmt.setInt(1, Integer.parseInt(professional));;
			stmt.setInt(2, institute_id);
			
			System.err.println("stmt====GetviewInternal_ass_marks====="+stmt);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("starting_date")); // 0
				alist.add(rs.getString("ending_date")); // 1
				alist.add(rs.getString("term")); // 2
				alist.add(rs.getString("exam_type")); // 3
				alist.add(rs.getString("exam_serial")); // 4
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
				}
			}
		}
		return list;
	}
	
	@Override
	public ArrayList<ArrayList<String>> GetviewDate_Fee_Prof(String professional, int institute_id) {
		
//		System.err.println("innnnnnnnnnnnnn"+professional);
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select to_char(starting_date,'DD-MON-YYYY') as starting_date,to_char(ending_date,'DD-MON-YYYY') as ending_date\n--tm.term\n"
					+ "from edu_tt_academic_details ad\n"
					+ "--inner join edu_cc_tb_i3_term_mstr tm on tm.id=ad.term\n"
					+ "where ad.professional = ? and ad.academic_details='003' and institute_id = ?";

			stmt = conn.prepareStatement(q);
			
			stmt.setInt(1, Integer.parseInt(professional));
			stmt.setInt(2, institute_id);
			
			System.err.println("stmt====GetviewInternal_ass_marks====="+stmt);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("starting_date")); // 0
				alist.add(rs.getString("ending_date")); // 1
//				alist.add(rs.getString("term")); // 2
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
				}
			}
		}
		return list;
	}
	
	@Override
	public ArrayList<ArrayList<String>> GetviewDate_Tran_Curr_Prof(String professional, int institute_id) {
		
//		System.err.println("innnnnnnnnnnnnn"+professional);
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select to_char(starting_date,'DD-MON-YYYY') as starting_date,to_char(ending_date,'DD-MON-YYYY') as ending_date\n--tm.term\n"
					+ "from edu_tt_academic_details ad\n"
					+ "--inner join edu_cc_tb_i3_term_mstr tm on tm.id=ad.term\n"
					+ "where ad.professional = ? and ad.academic_details='004' and institute_id = ?";

			stmt = conn.prepareStatement(q);
			
			stmt.setInt(1, Integer.parseInt(professional));
			stmt.setInt(2, institute_id);
			
			System.err.println("stmt====GetviewInternal_ass_marks====="+stmt);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("starting_date")); // 0
				alist.add(rs.getString("ending_date")); // 1
//				alist.add(rs.getString("term")); // 2
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
				}
			}
		}
		return list;
	}
	
	@Override
	public ArrayList<ArrayList<String>> GetWorkLoadCalData(String degree,String prof, String role) {
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			if (role.trim().equals("Institute_NCISM")) {

			q =  "select cm.id as course_id,cm.course_name,sum(no_of_hours) as total_hours\n"
					+ "from edu_cc_summary_parent p\n"
					+ "inner join edu_cc_summary_teach_hrs_child c on c.p_id=p.id\n"
					+ "inner join edu_lms_course_mstr cm on cm.id = p.course_id\n"
					+ "where p.degree_id = ? and professional_id = ?\n"
					+ "group by 1";
			}
			if (role.trim().equals("Institute_NCH")) {
			
				q =  "select cm.id as course_id,cm.course_name,sum(theoretical_lecture + pract_tutor_semi_clinic_post) as total_hours\n"
						+ "from edu_cc_tb_nch_teaching_hours h\n"
						+ "inner join edu_lms_course_mstr cm on cm.id = h.course_id\n"
						+ "where h.degree_id = ? and h.professional_id = ?\n"
						+ "group by 1";
			}
			stmt = conn.prepareStatement(q);
			
			stmt.setInt(1, Integer.parseInt(degree));
			stmt.setInt(2, Integer.parseInt(prof));
			
			System.err.println("\n\nstmt====GetWorkLoadCalData====="+stmt+"\n");
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("course_name")); // 0
				alist.add(rs.getString("total_hours")); // 1
				alist.add(rs.getString("course_id")); // 2
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
				}
			}
		}
		return list;
	}
	
	@Override
	public ArrayList<ArrayList<String>> getAddedHoursbyCourseandInst(String course_id,String inst_id) {
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q =  "select sum(to_char((to_char((end_time::time),'HH24:MI:SS')::time)-(to_char((start_time::time),'HH24:MI:SS')::time),'HH24:MI:SS')::time::interval) as total_hours\n"
					+ "from edu_tt_time_table_details \n"
					+ "where institute_id = ? and course = ?";

			stmt = conn.prepareStatement(q);
			
			stmt.setInt(1, Integer.parseInt(inst_id));
			stmt.setInt(2, Integer.parseInt(course_id));
			
			System.err.println("\n\nstmt====getAddedHoursbyCourseandInst====="+stmt+"\n");
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("total_hours")); // 0
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
				}
			}
		}
		return list;
	}
	
}
