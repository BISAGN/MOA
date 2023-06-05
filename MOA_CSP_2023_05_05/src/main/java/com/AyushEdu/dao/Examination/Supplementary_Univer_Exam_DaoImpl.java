package com.AyushEdu.dao.Examination;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Supplementary_Univer_Exam_DaoImpl implements Supplementary_Univer_Exam_Dao {

	@Autowired
	private DataSource dataSource;
	
	public ArrayList<ArrayList<String>> getstu_Supplementary_declare_data(String system_id,String degree_id,String professional_id,String institute_id,String role,String uni_id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;
			String searchvalue = "";
			
			String sd = "";
			if (role.contains("NCISM")) {
				sd = " and s.system != 45 ";
			}
			if (role.contains("NCH")) {
				sd = " and s.system = 45 ";
			}
			if(!system_id.equals("0")){
				searchvalue+=" and system=" + Integer.parseInt(system_id);
			}
			if(!degree_id.equals("0")){
				searchvalue+=" and degree=" + Integer.parseInt(degree_id);
			}
			if(!professional_id.equals("0")){
				searchvalue+=" and s.semester::int=" + professional_id;
			}
			if(!institute_id.equals("0")){
				searchvalue+=" and institude_userid=" + Integer.parseInt(institute_id);
			}
			if(!uni_id.equals("0")){
				searchvalue+=" and r.university_id=" + Integer.parseInt(uni_id);
			}
			if (role.trim().equals("University_NCISM")) {
				
			sql = "select distinct ROW_NUMBER() OVER(order by s.id ) as ser,s.id,s.ayush_id,\n"
					+ "s.name,to_char(ss.date_of_exam,'DD-MON-YYYY') as date_of_exam,sm.system_name,d.degree_name,t.term,r.institute_name,"
					+ "(select count(*) from edu_exam_tb_supplementary_student where student_id = s.id ) as count \n"
					+ " from edu_lms_student_details s\n"
					+ "inner join edu_lms_system_mstr sm ON sm.id = s.system\n"
					+ "inner join edu_lms_degree_mstr d on d.id=s.degree\n"
					+ "inner join edu_lms_term_mstr t on t.term =s.semester\n"
					+ "inner join edu_exam_tb_supplementary_student ss on ss.student_id = s.id\n"
					+ "inner join edu_lms_institute_reg r on r.id = s.institude_userid\n"
					+ "where s.id!=0 and ss.status=1" + sd + searchvalue
					+ " group by 2,3,4,5,6,7,8,9  ";
			}
			if (role.trim().equals("University_NCH")) {
				
				sql = "select distinct ROW_NUMBER() OVER(order by s.id ) as ser,s.id,s.ayush_id,\n"
						+ "s.name,to_char(ss.date_of_exam,'DD-MON-YYYY') as date_of_exam,sm.system_name,d.degree_name,t.term,r.institute_name,"
						+ "(select count(*) from edu_exam_tb_supplementary_student where student_id = s.id ) as count \n"
						+ " from edu_lms_nch_student_details s\n"
						+ "inner join edu_lms_system_mstr sm ON sm.id = s.system\n"
						+ "inner join edu_lms_degree_mstr d on d.id=s.degree\n"
						+ "inner join edu_lms_term_mstr t on t.term =s.semester\n"
						+ "inner join edu_exam_tb_supplementary_student ss on ss.student_id = s.id\n"
						+ "inner join edu_lms_institute_reg r on r.id = s.institude_userid\n"
						+ "where s.id!=0 and  ss.status=1 "  + sd + searchvalue
						+ " group by 2,3,4,5,6,7,8,9  ";
				}
			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

//			stmt.setInt(1, Integer.parseInt(system_id));
//			stmt.setInt(2, Integer.parseInt(degree_id));
//			stmt.setString(3, professional_id);
//			stmt.setInt(4, Integer.parseInt(institute_id));
			
			System.err.println("---------getstu_res_declare_data------------"+stmt);
			ResultSet rs = stmt.executeQuery();

			int i = 0;
			while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("ayush_id"));//0
				list.add(rs.getString("name"));// 1
				list.add(rs.getString("date_of_exam"));// 2
				list.add(rs.getString("ser"));// 3
				list.add(rs.getString("id"));//4
				list.add(rs.getString("system_name"));//5
				list.add(rs.getString("degree_name"));//6
				list.add(rs.getString("term"));//7
				list.add(rs.getString("institute_name"));//8
				list.add(rs.getString("count"));//9
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
	
	public List<ArrayList<String>>  currentTerm(int stuId,String role) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;
			
			if (role.trim().equals("University_NCISM")) {
				sql = "select semester \n"
						+ "from edu_lms_student_details \n"
						+ "where id=?";
			}
			if (role.trim().equals("University_NCH")) {
				sql = "select semester \n"
						+ "from edu_lms_nch_student_details \n"
						+ "where id=?";
			}
			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			stmt.setInt(1, stuId);
			
			System.err.println("---------getstu_res_declare_data------------"+stmt);
			ResultSet rs = stmt.executeQuery();

			int i = 0;
			while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("semester"));//0
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
