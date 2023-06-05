package com.AyushEdu.dao.Examination;

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
public class View_Internal_assessment_MarksDaoImpl implements View_Internal_assessment_MarksDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public ArrayList<ArrayList<String>> GetviewInternal_ass_marks(String course_id, String professional_id,
			String userid, String exam_type, String exam_seral, String role) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if (role.trim().equals("Student_NCISM")) {

				q = "select c.marks,tm.term,et.exam_type,esm.exam_serial\n"
						+ "from edu_exam_tb_add_marks_of_pa_child c\n"
						+ "inner join edu_exam_tb_add_marks_of_pa_parent p on p.id=c.p_id\n"
						+ "inner join edu_exam_tb_exam_serial_mstr esm on esm.id=p.exam_serial\n"
						+ "inner join edu_cc_tb_exam_type_mstr et on et.id=p.exam_type_id\n"
						+ "inner join edu_cc_tb_i3_term_mstr tm on tm.id=p.term_id\n"
						+ "inner join edu_lms_student_details sd on sd.id=c.student_name_id\n"
						+ "inner join logininformation l on l.username = sd.email\n"
						+ "where l.userid=? and p.course_id=? and p.professional_id=? \n"
						+ "--and et.exam_type like ? and esm.exam_serial = ?";
			}

			if (role.trim().equals("Student_NCH")) {

				q = "select c.marks,tm.term,et.exam_type,esm.exam_serial\n"
						+ "from edu_exam_tb_add_marks_of_pa_child c\n"
						+ "inner join edu_exam_tb_add_marks_of_pa_parent p on p.id=c.p_id\n"
						+ "inner join edu_exam_tb_exam_serial_mstr esm on esm.id=p.exam_serial\n"
						+ "inner join edu_cc_tb_exam_type_mstr et on et.id=p.exam_type_id\n"
						+ "inner join edu_cc_tb_i3_term_mstr tm on tm.id=p.term_id\n"
						+ "inner join edu_lms_nch_student_details sd on sd.id=c.student_name_id\n"
						+ "inner join logininformation l on l.username = sd.email\n"
						+ "where l.userid=? and p.course_id=? and p.professional_id=? \n"
						+ "--and et.exam_type like ? and esm.exam_serial = ?";
			}
			stmt = conn.prepareStatement(q);

			stmt.setInt(1, Integer.parseInt(userid));
			stmt.setInt(2, Integer.parseInt(course_id));
			stmt.setInt(3, Integer.parseInt(professional_id));

			System.err.println("stmt====GetviewInternal_ass_marks=====" + stmt);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("marks")); // 0
				alist.add(rs.getString("term")); // 1
				alist.add(rs.getString("exam_serial")); // 2
				alist.add(rs.getString("exam_type")); // 3

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
	public ArrayList<ArrayList<String>> Getdegreeid_fetch(String userid) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;

		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			String qry = "";

			q = "select m.id,a.degree,m.degree_name,a.semester from (select ayush_id,aadhar_card,degree,semester,email from edu_lms_student_details\n"
					+ "UNION ALL \n"
					+ "select ayush_id,aadhar_card,degree,semester,email from edu_lms_nch_student_details) a \n"
					+ "inner join logininformation l on l.email_id = a.email\n"
					+ "inner join edu_lms_degree_mstr m on m.id = a.degree\n" + "where userid = ?";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(userid));
			System.err.println("------Getdegreeid_fetch----" + stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id")); // 0
				alist.add(rs.getString("degree_name")); // 1
				alist.add(rs.getString("semester")); // 2
				alist.add(rs.getString("degree")); // 3

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
