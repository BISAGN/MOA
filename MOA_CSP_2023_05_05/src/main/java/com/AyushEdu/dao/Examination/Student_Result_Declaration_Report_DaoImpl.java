package com.AyushEdu.dao.Examination;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository

public class Student_Result_Declaration_Report_DaoImpl implements Student_Result_Declaration_Report_Dao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DataSource dataSource;

	@Override
	public List<Map<String, Object>> getFilter_Student_result_report(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String degree_id, String professional_id, String date_of_exam,
			String institute_id, String result_status, String role,String institute_uid) throws ParseException {
		String SearchValue = GenerateQueryWhereClause_SQL(search, degree_id, professional_id, date_of_exam,institute_id, result_status,role,institute_uid);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			String pageL = "";
			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}

			if (role.trim().contains("University_NCISM") || role.trim().contains("Institute_NCISM")) {

				if (search.equals("") && result_status.equals("0")) {
					q = "select * from (select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_pass_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_student_details sd on sd.id= s.student_id \n" + "union all\n"
							+ "select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_detain_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_student_details sd on sd.id= s.student_id \n" + "union all\n"
							+ "select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_supplementary_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_student_details sd on sd.id= s.student_id ) a where a.id != 0 "
							+ SearchValue + " ORDER BY id " + orderType + " limit " + pageL + " OFFSET " + startPage;
				} else if (search.equals("") && result_status.equals("1")) {
					q = "select * from ( select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_pass_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_student_details sd on sd.id= s.student_id\n" + ") a  where a.id != 0 "
							+ SearchValue + " ORDER BY id " + orderType + " limit " + pageL + " OFFSET " + startPage;
				} else if (search.equals("") && result_status.equals("2")) {
					q = "select * from ( select s.id,d.degree_name,sd.semester as term,ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_detain_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_student_details sd on sd.id= s.student_id\n" + ") a  where a.id != 0 "
							+ SearchValue + " ORDER BY id " + orderType + " limit " + pageL + " OFFSET " + startPage;
				} else if (search.equals("") && result_status.equals("3")) {
					q = "select * from (select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_supplementary_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_student_details sd on sd.id= s.student_id\n" + ") a  where a.id != 0 "
							+ SearchValue + " ORDER BY id " + orderType + " limit " + pageL + " OFFSET " + startPage;
				} else {
					if (result_status.equals("0")) {
						q = "select * from (select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_pass_student s\n"
								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
								+ "inner join edu_lms_student_details sd on sd.id= s.student_id \n" + "union all\n"
								+ "select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_detain_student s\n"
								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
								+ "inner join edu_lms_student_details sd on sd.id= s.student_id \n" + "union all\n"
								+ "select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_supplementary_student s\n"
								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
								+ "inner join edu_lms_student_details sd on sd.id= s.student_id ) a  where a.id != 0 "
								+ SearchValue + " ORDER BY id " + orderType + " limit " + pageL + " OFFSET "
								+ startPage;
					} else if (result_status.equals("1")) {
						q = "select * from ( select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_pass_student s\n"
								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
								+ "inner join edu_lms_student_details sd on sd.id= s.student_id\n"
								+ ") a  where a.id != 0 " + SearchValue + " ORDER BY id " + orderType + " limit "
								+ pageL + " OFFSET " + startPage;
					} else if (result_status.equals("2")) {
						q = "select * from ( select s.id,d.degree_name,sd.semester as term,ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_detain_student s\n"
								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
								+ "inner join edu_lms_student_details sd on sd.id= s.student_id\n"
								+ ") a  where a.id != 0 " + SearchValue + " ORDER BY id " + orderType + " limit "
								+ pageL + " OFFSET " + startPage;
					} else if (result_status.equals("3")) {
						q = "select * from (select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_supplementary_student s\n"
								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
								+ "inner join edu_lms_student_details sd on sd.id= s.student_id\n"
								+ ") a  where a.id != 0 " + SearchValue + " ORDER BY id " + orderType + " limit "
								+ pageL + " OFFSET " + startPage;
					}
				}
			}

			if (role.trim().contains("University_NCH") || role.trim().contains("Institute_NCH")) {

				if (search.equals("") && result_status.equals("0")) {
					q = "select * from (select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_pass_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id \n" + "union all\n"
							+ "select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_detain_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id \n" + "union all\n"
							+ "select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_supplementary_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id ) a where a.id != 0 "
							+ SearchValue + " ORDER BY id " + orderType + " limit " + pageL + " OFFSET " + startPage;
				} else if (search.equals("") && result_status.equals("1")) {
					q = "select * from ( select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_pass_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id\n"
							+ ") a  where a.id != 0 " + SearchValue + " ORDER BY id " + orderType + " limit " + pageL
							+ " OFFSET " + startPage;
				} else if (search.equals("") && result_status.equals("2")) {
					q = "select * from ( select s.id,d.degree_name,sd.semester as term,ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_detain_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id\n"
							+ ") a  where a.id != 0 " + SearchValue + " ORDER BY id " + orderType + " limit " + pageL
							+ " OFFSET " + startPage;
				} else if (search.equals("") && result_status.equals("3")) {
					q = "select * from (select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_supplementary_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id\n"
							+ ") a  where a.id != 0 " + SearchValue + " ORDER BY id " + orderType + " limit " + pageL
							+ " OFFSET " + startPage;
				} else {
					if (result_status.equals("0")) {
						q = "select * from (select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_pass_student s\n"
								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
								+ "inner join edu_lms_student_details sd on sd.id= s.student_id \n" + "union all\n"
								+ "select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_detain_student s\n"
								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
								+ "inner join edu_lms_student_details sd on sd.id= s.student_id \n" + "union all\n"
								+ "select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_supplementary_student s\n"
								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
								+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id ) a  where a.id != 0 "
								+ SearchValue + " ORDER BY id " + orderType + " limit " + pageL + " OFFSET "
								+ startPage;
					} else if (result_status.equals("1")) {
						q = "select * from ( select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_pass_student s\n"
								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
								+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id\n"
								+ ") a  where a.id != 0 " + SearchValue + " ORDER BY id " + orderType + " limit "
								+ pageL + " OFFSET " + startPage;
					} else if (result_status.equals("2")) {
						q = "select * from ( select s.id,d.degree_name,sd.semester as term,ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_detain_student s\n"
								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
								+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id\n "
								+ ") a  where a.id != 0 " + SearchValue + " ORDER BY id " + orderType + " limit "
								+ pageL + " OFFSET " + startPage;
					} else if (result_status.equals("3")) {
						q = "select * from (select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_supplementary_student s\n"
								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
								+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id\n"
								+ ") a  where a.id != 0 " + SearchValue + " ORDER BY id " + orderType + " limit "
								+ pageL + " OFFSET " + startPage;
					}
				}
			}
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, search, degree_id, professional_id, date_of_exam, institute_id,
					result_status,role, institute_uid);
			System.err.println("\n\n *******--------------\n" + stmt + "\n\n 11111111111111111111--------------\n");
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;

			while (rs.next()) {

				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
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
	public long getTotal_Student_result_report_dataCount(String Search, String degree_id, String professional_id,
			String date_of_exam, String institute_id, String result_status, String role,String institute_id1) throws ParseException {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, degree_id, professional_id, date_of_exam,
				institute_id, result_status, role,institute_id1);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			if (role.trim().contains("University_NCISM") || role.trim().contains("Institute_NCISM")) {
				if (Search == null && result_status.equals("0")) {
					q = "select count(*) from(select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_pass_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_student_details sd on sd.id= s.student_id \n" + "union all\n"
							+ "select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_detain_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_student_details sd on sd.id= s.student_id \n" + "union all\n"
							+ "select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_supplementary_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_student_details sd on sd.id= s.student_id ) a  where a.id != 0 "
							+ SearchValue;
				} else if (result_status.equals("1")) {
					q = "select count(*) from(select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_pass_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_student_details sd on sd.id= s.student_id\n" + ") a  where a.id != 0 "
							+ SearchValue;
				} else if (result_status.equals("2")) {
					q = "select count(*) from(select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_detain_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_student_details sd on sd.id= s.student_id\n" + ") a  where a.id != 0 "
							+ SearchValue;
				} else if (result_status.equals("3")) {
					q = "select count(*) from(select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_supplementary_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_student_details sd on sd.id= s.student_id\n" + ") a  where a.id != 0 "
							+ SearchValue;
				}
			}

			if (role.trim().contains("University_NCH") || role.trim().contains("Institute_NCH")) {
				if (Search.equals("") && result_status.equals("0")) {
					q = "select count(*) from(select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_pass_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id \n" + "union all\n"
							+ "select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_detain_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id \n" + "union all\n"
							+ "select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_supplementary_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id ) a  where a.id != 0 "
							+ SearchValue;
				} else if (result_status.equals("1")) {
					q = "select count(*) from(select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_pass_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id\n"
							+ ") a  where a.id != 0 " + SearchValue;
				} else if (result_status.equals("2")) {
					q = "select count(*) from(select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_detain_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id\n"
							+ ") a  where a.id != 0 " + SearchValue;
				} else if (result_status.equals("3")) {
					q = "select count(*) from(select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_supplementary_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id\n"
							+ ") a  where a.id != 0 " + SearchValue;
				}
			}
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, degree_id, professional_id, date_of_exam, institute_id,
					result_status,role,institute_id1);

			System.err.println("---------check the statment------30---count-----" + stmt);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				total = rs.getInt(1);
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
		return (long) total;
	}

	@SuppressWarnings("null")
	public String GenerateQueryWhereClause_SQL(String Search, String degree_id, String professional_id,
			String date_of_exam, String institute_id, String result_status, String role,String institute_uid) throws ParseException {
		String SearchValue = "";

		if (Search != null && !Search.equals("")) {
			System.err.println("enter or not");
			SearchValue += " and (upper(a.degree_name) like ?" + " or upper(a.term ) like ?  "
					+ " or upper(a.institute_name) like ?  " + " or upper(a.ayush_id) like ?  "
					+ " or upper(a.name) like ?  " + " or a.date_of_exam like ?  " + ")";
		}

		if (!degree_id.equals("0")) {
			SearchValue += " and a.a_id = ? ";
		}
		if (!professional_id.equals("0")) {
			SearchValue += " and a.term::integer = ? ";
		}
		if (!institute_id.equals("0")) {
			SearchValue += " and a.i_id =? ";
		}
		if (date_of_exam != null && !date_of_exam.equals("") && !date_of_exam.equals("DD/MM/YYYY")) {
			SearchValue += "and a.date_of_exam = ? ";
		}
		if(role.toLowerCase().contains("institute") && !institute_uid.equals("")) {
			SearchValue += "and a.i_id = ? ";
		}
		

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String degree_id,
			String professional_id, String date_of_exam, String institute_id, String result_status,String role,String institute_uid) {
		int flag = 0;
		try {

			if (Search != null && !Search.equals("")) {
//				System.err.println("INSIDE=-====");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}

			if (!degree_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(degree_id));
			}
			if (!professional_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(professional_id));
			}
			if (!institute_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(institute_id));
			}
			if (date_of_exam != null && !date_of_exam.equals("") && !date_of_exam.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, date_of_exam);
			}
			if(role.toLowerCase().contains("institute") && !institute_uid.equals("")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(institute_uid));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}

	@Override
	public ArrayList<ArrayList<String>> getDegreeLFromInstituteExam(String institute_id, String userId, String role) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			String qry = "";
			if (role.trim().equals("Institute_NCISM")) {
				qry = "where lo.userid=?";
				q = "select distinct dm.id,dm.degree_name from edu_lms_degree_mstr dm \n"
						+ " inner join edu_lms_system_degree_map_inst dmi on dmi.degree_id=dm.id\n"
						+ " inner join logininformation lo on lo.institute_id = dmi.institute_id" + " " + qry;
			}
			if (role.trim().equals("Institute_NCH")) {
				qry = "where dmi.system_id=45";
				q = "select d.id,d.degree_name from edu_lms_system_degree_map_mstr dm\n"
						+ " inner join edu_lms_degree_mstr d on d.id = dm.degree_name\n" + " where system_name=45";
			}
//			if (role.trim().equals("University_NCISM")) {
//				q = "select id,degree_name from edu_lms_degree_mstr where status='1'";
//			}
			if (role.trim().equals("University_NCISM")) {
				qry = "and lo.userid=?";
				q = "select distinct d.id,d.degree_name from edu_lms_university_mstr um \n"
						+ "inner join edu_lms_system_degree_map_mstr sdm on sdm.system_name = um.system_id \n"
						+ "inner join edu_lms_degree_mstr d on d.id=sdm.degree_name\n"
						+ "inner join logininformation lo on lo.university_id=um.id where sdm.status='1'" + " " + qry;
			}
			
			if (role.trim().equals("University_NCH")) {
				qry = "where dm.system_id=45";
				q = "select d.id,d.degree_name from edu_lms_system_degree_map_mstr dm\n"
						+ "inner join edu_lms_degree_mstr d on d.id = dm.degree_name\n" + " where system_name=45";
			}
			PreparedStatement stmt = conn.prepareStatement(q);

			if (role.trim().equals("Institute_NCISM") || role.trim().equals("University_NCISM") ) {
				stmt.setObject(1, Integer.parseInt(userId));
			}
			System.err.println("-------getDegreeListFromInstitute--------" + stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));// 0
				alist.add(rs.getString("degree_name"));// 1

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

//	@Override
//	public List<Map<String, Object>> getStudent_Result_Report_Excel(String degree_id, String professional_id,
//			String date_of_exam, String institute_id, String result_status, String role,String search) throws ParseException {
//		String SearchValue = GenerateQueryWhereClause_SQL(search, degree_id, professional_id, date_of_exam,
//				institute_id, result_status);
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		Connection conn = null;
//		String q = "";
//		try {
//
//			conn = dataSource.getConnection();
//
//			if (role.trim().contains("University_NCISM") || role.trim().contains("Institute_NCISM")) {
//
//				if (search.equals("") && result_status.equals("0")) {
//					q = "select * from (select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_pass_student s\n"
//							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
//							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
//							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
//							+ "inner join edu_lms_student_details sd on sd.id= s.student_id \n" + "union all\n"
//							+ "select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_detain_student s\n"
//							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
//							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
//							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
//							+ "inner join edu_lms_student_details sd on sd.id= s.student_id \n" + "union all\n"
//							+ "select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_supplementary_student s\n"
//							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
//							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
//							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
//							+ "inner join edu_lms_student_details sd on sd.id= s.student_id ) a where a.id != 0 "
//							+ SearchValue + " ORDER BY id ";
//				} else if (search.equals("") && result_status.equals("1")) {
//					q = "select * from ( select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_pass_student s\n"
//							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
//							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
//							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
//							+ "inner join edu_lms_student_details sd on sd.id= s.student_id\n" + ") a  where a.id != 0 "
//							+ SearchValue + " ORDER BY id ";
//				} else if (search.equals("") && result_status.equals("2")) {
//					q = "select * from ( select s.id,d.degree_name,sd.semester as term,ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_detain_student s\n"
//							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
//							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
//							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
//							+ "inner join edu_lms_student_details sd on sd.id= s.student_id\n" + ") a  where a.id != 0 "
//							+ SearchValue + " ORDER BY id ";
//				} else if (search.equals("") && result_status.equals("3")) {
//					q = "select * from (select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_supplementary_student s\n"
//							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
//							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
//							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
//							+ "inner join edu_lms_student_details sd on sd.id= s.student_id\n" + ") a  where a.id != 0 "
//							+ SearchValue + " ORDER BY id ";
//				} else {
//					if (result_status.equals("0")) {
//						q = "select * from (select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_pass_student s\n"
//								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
//								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
//								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
//								+ "inner join edu_lms_student_details sd on sd.id= s.student_id \n" + "union all\n"
//								+ "select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_detain_student s\n"
//								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
//								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
//								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
//								+ "inner join edu_lms_student_details sd on sd.id= s.student_id \n" + "union all\n"
//								+ "select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_supplementary_student s\n"
//								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
//								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
//								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
//								+ "inner join edu_lms_student_details sd on sd.id= s.student_id ) a  where a.id != 0 "
//								+ SearchValue + " ORDER BY id ";
//					} else if (result_status.equals("1")) {
//						q = "select * from ( select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_pass_student s\n"
//								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
//								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
//								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
//								+ "inner join edu_lms_student_details sd on sd.id= s.student_id\n"
//								+ ") a  where a.id != 0 " + SearchValue + " ORDER BY id ";
//					} else if (result_status.equals("2")) {
//						q = "select * from ( select s.id,d.degree_name,sd.semester as term,ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_detain_student s\n"
//								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
//								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
//								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
//								+ "inner join edu_lms_student_details sd on sd.id= s.student_id\n"
//								+ ") a  where a.id != 0 " + SearchValue + " ORDER BY id ";
//					} else if (result_status.equals("3")) {
//						q = "select * from (select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_supplementary_student s\n"
//								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
//								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
//								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
//								+ "inner join edu_lms_student_details sd on sd.id= s.student_id\n"
//								+ ") a  where a.id != 0 " + SearchValue + " ORDER BY id ";
//					}
//				}
//			}
//
//			if (role.trim().contains("University_NCH") || role.trim().contains("Institute_NCH")) {
//
//				if (search.equals("") && result_status.equals("0")) {
//					q = "select * from (select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_pass_student s\n"
//							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
//							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
//							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
//							+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id \n" + "union all\n"
//							+ "select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_detain_student s\n"
//							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
//							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
//							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
//							+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id \n" + "union all\n"
//							+ "select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_supplementary_student s\n"
//							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
//							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
//							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
//							+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id ) a where a.id != 0 "
//							+ SearchValue + " ORDER BY id ";
//				} else if (search.equals("") && result_status.equals("1")) {
//					q = "select * from ( select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_pass_student s\n"
//							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
//							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
//							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
//							+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id\n"
//							+ ") a  where a.id != 0 " + SearchValue + " ORDER BY id ";
//				} else if (search.equals("") && result_status.equals("2")) {
//					q = "select * from ( select s.id,d.degree_name,sd.semester as term,ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_detain_student s\n"
//							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
//							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
//							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
//							+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id\n"
//							+ ") a  where a.id != 0 " + SearchValue + " ORDER BY id ";
//				} else if (search.equals("") && result_status.equals("3")) {
//					q = "select * from (select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_supplementary_student s\n"
//							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
//							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
//							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
//							+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id\n"
//							+ ") a  where a.id != 0 " + SearchValue + " ORDER BY id ";
//				} else {
//					if (result_status.equals("0")) {
//						q = "select * from (select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_pass_student s\n"
//								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
//								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
//								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
//								+ "inner join edu_lms_student_details sd on sd.id= s.student_id \n" + "union all\n"
//								+ "select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_detain_student s\n"
//								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
//								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
//								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
//								+ "inner join edu_lms_student_details sd on sd.id= s.student_id \n" + "union all\n"
//								+ "select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_supplementary_student s\n"
//								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
//								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
//								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
//								+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id ) a  where a.id != 0 "
//								+ SearchValue + " ORDER BY id ";
//					} else if (result_status.equals("1")) {
//						q = "select * from ( select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_pass_student s\n"
//								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
//								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
//								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
//								+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id\n"
//								+ ") a  where a.id != 0 " + SearchValue + " ORDER BY id ";
//					} else if (result_status.equals("2")) {
//						q = "select * from ( select s.id,d.degree_name,sd.semester as term,ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_detain_student s\n"
//								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
//								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
//								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
//								+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id\n "
//								+ ") a  where a.id != 0 " + SearchValue + " ORDER BY id ";
//					} else if (result_status.equals("3")) {
//						q = "select * from (select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_supplementary_student s\n"
//								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
//								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
//								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
//								+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id\n"
//								+ ") a  where a.id != 0 " + SearchValue + " ORDER BY id ";
//					}
//				}
//			}
//			PreparedStatement stmt = conn.prepareStatement(q);
//			stmt = setQueryWhereClause_SQL(stmt, search, degree_id, professional_id, date_of_exam, institute_id,
//					result_status);
//			System.err.println("\n \n -------getStudent_Result_Report_Excel----" + stmt);
//			ResultSet rs = stmt.executeQuery();
//
//			ResultSetMetaData metaData = rs.getMetaData();
//			int columnCount = metaData.getColumnCount();
//
//			while (rs.next()) {
//
//				Map<String, Object> columns = new LinkedHashMap<String, Object>();
//				for (int i = 1; i <= columnCount; i++) {
//					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
//				}
//				list.add(columns);
//			}
//			rs.close();
//			stmt.close();
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//				}
//			}
//		}
//		return list;
//	}

	@Override
	public ArrayList<ArrayList<String>> getStudent_Result_Report_Excel(String degree_id, String professional_id,
			String date_of_exam, String institute_id, String result_status, String role,String search, String institute_uid)
			throws ParseException {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		String SearchValue = GenerateQueryWhereClause_SQL(search, degree_id, professional_id, date_of_exam,
				institute_id, result_status,role,institute_uid);
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			String qry = "";
			if (role.trim().contains("University_NCISM") || role.trim().contains("Institute_NCISM")) {

				if (search.equals("") && result_status.equals("0")) {
					q = "select * from (select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_pass_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_student_details sd on sd.id= s.student_id \n" + "union all\n"
							+ "select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_detain_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_student_details sd on sd.id= s.student_id \n" + "union all\n"
							+ "select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_supplementary_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_student_details sd on sd.id= s.student_id ) a where a.id != 0 "
							+ SearchValue + " ORDER BY id ";
				} else if (search.equals("") && result_status.equals("1")) {
					q = "select * from ( select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_pass_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_student_details sd on sd.id= s.student_id\n" + ") a  where a.id != 0 "
							+ SearchValue + " ORDER BY id ";
				} else if (search.equals("") && result_status.equals("2")) {
					q = "select * from ( select s.id,d.degree_name,sd.semester as term,ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_detain_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_student_details sd on sd.id= s.student_id\n" + ") a  where a.id != 0 "
							+ SearchValue + " ORDER BY id ";
				} else if (search.equals("") && result_status.equals("3")) {
					q = "select * from (select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_supplementary_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_student_details sd on sd.id= s.student_id\n" + ") a  where a.id != 0 "
							+ SearchValue + " ORDER BY id ";
				} else {
					if (result_status.equals("0")) {
						q = "select * from (select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_pass_student s\n"
								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
								+ "inner join edu_lms_student_details sd on sd.id= s.student_id \n" + "union all\n"
								+ "select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_detain_student s\n"
								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
								+ "inner join edu_lms_student_details sd on sd.id= s.student_id \n" + "union all\n"
								+ "select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_supplementary_student s\n"
								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
								+ "inner join edu_lms_student_details sd on sd.id= s.student_id ) a  where a.id != 0 "
								+ SearchValue + " ORDER BY id ";
					} else if (result_status.equals("1")) {
						q = "select * from ( select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_pass_student s\n"
								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
								+ "inner join edu_lms_student_details sd on sd.id= s.student_id\n"
								+ ") a  where a.id != 0 " + SearchValue + " ORDER BY id ";
					} else if (result_status.equals("2")) {
						q = "select * from ( select s.id,d.degree_name,sd.semester as term,ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_detain_student s\n"
								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
								+ "inner join edu_lms_student_details sd on sd.id= s.student_id\n"
								+ ") a  where a.id != 0 " + SearchValue + " ORDER BY id ";
					} else if (result_status.equals("3")) {
						q = "select * from (select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_supplementary_student s\n"
								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
								+ "inner join edu_lms_student_details sd on sd.id= s.student_id\n"
								+ ") a  where a.id != 0 " + SearchValue + " ORDER BY id ";
					}
				}
			}

			if (role.trim().contains("University_NCH") || role.trim().contains("Institute_NCH")) {

				if (search.equals("") && result_status.equals("0")) {
					q = "select * from (select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_pass_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id \n" + "union all\n"
							+ "select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_detain_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id \n" + "union all\n"
							+ "select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_supplementary_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id ) a where a.id != 0 "
							+ SearchValue + " ORDER BY id ";
				} else if (search.equals("") && result_status.equals("1")) {
					q = "select * from ( select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_pass_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id\n"
							+ ") a  where a.id != 0 " + SearchValue + " ORDER BY id ";
				} else if (search.equals("") && result_status.equals("2")) {
					q = "select * from ( select s.id,d.degree_name,sd.semester as term,ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_detain_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id\n"
							+ ") a  where a.id != 0 " + SearchValue + " ORDER BY id ";
				} else if (search.equals("") && result_status.equals("3")) {
					q = "select * from (select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_supplementary_student s\n"
							+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
							+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
							+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
							+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id\n"
							+ ") a  where a.id != 0 " + SearchValue + " ORDER BY id ";
				} else {
					if (result_status.equals("0")) {
						q = "select * from (select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_pass_student s\n"
								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
								+ "inner join edu_lms_student_details sd on sd.id= s.student_id \n" + "union all\n"
								+ "select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_detain_student s\n"
								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
								+ "inner join edu_lms_student_details sd on sd.id= s.student_id \n" + "union all\n"
								+ "select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_supplementary_student s\n"
								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
								+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id ) a  where a.id != 0 "
								+ SearchValue + " ORDER BY id ";
					} else if (result_status.equals("1")) {
						q = "select * from ( select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_pass_student s\n"
								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
								+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id\n"
								+ ") a  where a.id != 0 " + SearchValue + " ORDER BY id ";
					} else if (result_status.equals("2")) {
						q = "select * from ( select s.id,d.degree_name,sd.semester as term,ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_detain_student s\n"
								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
								+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id\n "
								+ ") a  where a.id != 0 " + SearchValue + " ORDER BY id ";
					} else if (result_status.equals("3")) {
						q = "select * from (select s.id,d.degree_name,sd.semester as term, ir.institute_name,s.ayush_id,sd.name,to_char(s.date_of_exam,'dd/mm/yyyy') as date_of_exam,d.id as a_id,ir.id as i_id from edu_exam_tb_supplementary_student s\n"
								+ "inner join edu_lms_degree_mstr d on d.id= s.degree_id\n"
								+ "inner join edu_lms_term_mstr t on t.id = s.professional_id\n"
								+ "inner join edu_lms_institute_reg ir on ir.id= s.institute_id\n"
								+ "inner join edu_lms_nch_student_details sd on sd.id= s.student_id\n"
								+ ") a  where a.id != 0 " + SearchValue + " ORDER BY id ";
					}
				}
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, search, degree_id, professional_id, date_of_exam, institute_id,
					result_status,role,institute_uid);
			System.err.println("\n \n -------getStudent_Result_Report_Excel----" + stmt);
			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(String.valueOf(i));// 0
				alist.add(rs.getString("degree_name"));// 1
				alist.add(rs.getString("term"));// 2
				alist.add(rs.getString("institute_name"));// 3
				alist.add(rs.getString("ayush_id"));// 4
				alist.add(rs.getString("name"));// 5
				alist.add(rs.getString("date_of_exam"));// 6
				list.add(alist);
				i++;
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
