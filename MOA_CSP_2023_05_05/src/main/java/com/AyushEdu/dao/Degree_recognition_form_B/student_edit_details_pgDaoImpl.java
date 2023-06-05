package com.AyushEdu.dao.Degree_recognition_form_B;

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

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Degree_recognition_form_B.DG_REC_EXAMINERS_MD_B;
import com.AyushEdu.Models.Degree_recognition_form_B.DG_REC_STUDENTS_ADMITTED_B;

@Repository
public class student_edit_details_pgDaoImpl implements student_edit_details_pgDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	// ---------------------- Admitted  PG---------------------//

	public List<Map<String, Object>> getFilter_Admitted_Student_pg_list(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, int university_id, int user_id,String institute_status,String student_name,String date_of_admission,
			String date_of_registration,String course_name,String rank,String marks,String all_india,String state,String admission_authority,String court_order,
			String qualification_name,String year_of_award_admission,String date_of_registration_state,String date_of_completion_md_part1,
			String date_of_completion_md_part2,String date_of_declaration_of_md,String date_of_completion_internship,String remarks,
			String reject_remarks) {
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search,  student_name, date_of_admission,
				 date_of_registration, course_name, rank, marks, all_india, state, admission_authority, court_order,
				 qualification_name, year_of_award_admission, date_of_registration_state, date_of_completion_md_part1,
				 date_of_completion_md_part2, date_of_declaration_of_md, date_of_completion_internship, remarks,
				 reject_remarks);
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();

			q = "select a.id,a.user_id,a.student_name,to_char(a.date_of_admission,'DD/MM/YYYY') as date_of_admission,to_char(a.date_of_registration,'DD/MM/YYYY') as date_of_registration,"
					+ "a.course_name,a.rank,a.marks,a.all_india,a.state,a.admission_authority,a.court_order,\r\n"
					+ "a.qualification_name,a.year_of_award_admission,to_char(a.date_of_registration_state,'DD/MM/YYYY') as date_of_registration_state,\r\n"
					+ "to_char(a.date_of_completion_md_part1,'DD/MM/YYYY') as date_of_completion_md_part1,to_char(a.date_of_completion_md_part2,'DD/MM/YYYY') as date_of_completion_md_part2, \r\n"
					+ "to_char(a.date_of_declaration_of_md,'DD/MM/YYYY') as date_of_declaration_of_md,to_char(a.date_of_completion_internship,'DD/MM/YYYY') as date_of_completion_internship,a.remarks,a.reject_remarks\r\n"
					+ "from dg_rec_student_admitted_child_b  a where a.university_id=? and a.user_id=? and a.inst_status=? "+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
					+ pageLength + " OFFSET " + startPage;

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search, student_name, date_of_admission,
					 date_of_registration, course_name, rank, marks, all_india, state, admission_authority, court_order,
					 qualification_name, year_of_award_admission, date_of_registration_state, date_of_completion_md_part1,
					 date_of_completion_md_part2, date_of_declaration_of_md, date_of_completion_internship, remarks,
					 reject_remarks);
			 stmt.setInt(1, university_id);
			 stmt.setInt(2, user_id);
			 stmt.setInt(3, Integer.parseInt(institute_status));
			
			 System.err.println("----Admitted student pg----"+stmt);
			 
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;

			int countFunction = 1;

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				String f = "";
				String action = "";
				String f1 = "";

				String ul = "";
				ul += "<ul class='buttons-group mainbtn action daobtn'>";

				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDStudent' value='ADD' title='Edit Data' >"
						+ "<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='apIdAGE" + countFunction
						+ "' value=" + rs.getString("id") + ">" + "</i></a></li>";

				ul += f + " " + f1;
				ul += "</ul>";

				action = ul;
				countFunction += 1;
				columns.put("action", action);

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

	// ---------------- Admitted PG List Count -----------------//
	@Override
	public long getFilter_Admitted_StudentList_pg_Count(String search, int user_id,int university_id,String institute_status,String student_name,String date_of_admission,
			String date_of_registration,String course_name,String rank,String marks,String all_india,String state,String admission_authority,String court_order,
			String qualification_name,String year_of_award_admission,String date_of_registration_state,String date_of_completion_md_part1,
			String date_of_completion_md_part2,String date_of_declaration_of_md,String date_of_completion_internship,String remarks,
			String reject_remarks) {
		
		String SearchValue = GenerateQueryWhereClause_SQL(search,  student_name, date_of_admission,
				 date_of_registration, course_name, rank, marks, all_india, state, admission_authority, court_order,
				 qualification_name, year_of_award_admission, date_of_registration_state, date_of_completion_md_part1,
				 date_of_completion_md_part2, date_of_declaration_of_md, date_of_completion_internship, remarks,
				 reject_remarks);
		
		int total = 0;
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select a.id,a.user_id,a.student_name,to_char(a.date_of_admission,'DD/MM/YYYY') as date_of_admission,to_char(a.date_of_registration,'DD/MM/YYYY') as date_of_registration,a.course_name,a.rank,a.marks,a.all_india,a.state,a.admission_authority,a.court_order,\n"
					+ "a.qualification_name,a.year_of_award_admission,to_char(a.date_of_registration_state,'DD/MM/YYYY') as date_of_registration_state,\n"
					+ "to_char(a.date_of_completion_md_part1,'DD/MM/YYYY') as date_of_completion_md_part1,to_char(a.date_of_completion_md_part2,'DD/MM/YYYY') as date_of_completion_md_part2, \n"
					+ "to_char(a.date_of_declaration_of_md,'DD/MM/YYYY') as date_of_declaration_of_md,to_char(a.date_of_completion_internship,'DD/MM/YYYY') as date_of_completion_internship,a.remarks\n"
					+ "from dg_rec_student_admitted_child_b  a where a.university_id=? and a.user_id=? and a.inst_status=? ) a  ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,search, student_name, date_of_admission,
					 date_of_registration, course_name, rank, marks, all_india, state, admission_authority, court_order,
					 qualification_name, year_of_award_admission, date_of_registration_state, date_of_completion_md_part1,
					 date_of_completion_md_part2, date_of_declaration_of_md, date_of_completion_internship, remarks,
					 reject_remarks);
			 stmt.setInt(1, university_id);
			 stmt.setInt(2, user_id);
			 stmt.setInt(3, Integer.parseInt(institute_status));
			 System.err.println("---admitted--pg--count----"+stmt);
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
	public String GenerateQueryWhereClause_SQL(String Search,String student_name,String date_of_admission,
			String date_of_registration,String course_name,String rank,String marks,String all_india,String state,String admission_authority,String court_order,
			String qualification_name,String year_of_award_admission,String date_of_registration_state,String date_of_completion_md_part1,
			String date_of_completion_md_part2,String date_of_declaration_of_md,String date_of_completion_internship,String remarks,
			String reject_remarks) {
		
		String SearchValue="" ;
		
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (upper(student_name) Like ? or TO_CHAR(date_of_admission , 'dd/MM/yyyy') like ? "
					+" or TO_CHAR(date_of_registration , 'dd/MM/yyyy') like ? or upper(course_name) Like ? "
					+ " or rank::character varying like ? or marks::character varying like ? or upper(all_india) Like ? "
					+ " or upper(state) Like ? or upper(admission_authority) Like ? or upper(court_order) Like ? or upper(qualification_name) Like ? "
					+ " or upper(year_of_award_admission) Like ? or TO_CHAR(date_of_registration_state , 'dd/MM/yyyy') like ? "
					+ " or TO_CHAR(date_of_completion_md_part1 , 'dd/MM/yyyy') like ? or TO_CHAR(date_of_completion_md_part2 , 'dd/MM/yyyy') like ? "
					+ " or TO_CHAR(date_of_declaration_of_md , 'dd/MM/yyyy') like ? or TO_CHAR(date_of_completion_internship , 'dd/MM/yyyy') like ? "
					+ " or upper(remarks) Like ? or upper(reject_remarks) Like ?) ";
		}
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search,String student_name,String date_of_admission,
			String date_of_registration,String course_name,String rank,String marks,String all_india,String state,String admission_authority,String court_order,
			String qualification_name,String year_of_award_admission,String date_of_registration_state,String date_of_completion_md_part1,
			String date_of_completion_md_part2,String date_of_declaration_of_md,String date_of_completion_internship,String remarks,
			String reject_remarks) {
		int flag = 3;
		try {
			if (Search!=null &&  !Search.equals("")) {
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search + "%");
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
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
			}
			if (student_name != null && !student_name.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+student_name.toUpperCase()+"%");
			}
			if( date_of_admission != null && !date_of_admission.equals("") && !date_of_admission.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, date_of_admission );
			}
			if( date_of_registration != null && !date_of_registration.equals("") && !date_of_registration.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, date_of_registration );
			}
			if (course_name != null && !course_name.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+course_name.toUpperCase()+"%");
			}
			if (rank != null && !rank.equals("")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(rank));
			}
			if (marks != null && !marks.equals("")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(marks));
			}
			if (all_india != null && !all_india.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+all_india.toUpperCase()+"%");
			}
			if (state != null && !state.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+state.toUpperCase()+"%");
			}
			if (admission_authority != null && !admission_authority.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+admission_authority.toUpperCase()+"%");
			}
			if (court_order != null && !court_order.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+court_order.toUpperCase()+"%");
			}
			if (qualification_name != null && !qualification_name.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+qualification_name.toUpperCase()+"%");
			}
			if (year_of_award_admission != null && !year_of_award_admission.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+year_of_award_admission.toUpperCase()+"%");
			}
			if( date_of_registration_state != null && !date_of_registration_state.equals("") && !date_of_registration_state.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, date_of_registration_state );
			}
			if( date_of_completion_md_part1 != null && !date_of_completion_md_part1.equals("") && !date_of_completion_md_part1.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, date_of_completion_md_part1 );
			}
			if( date_of_completion_md_part2 != null && !date_of_completion_md_part2.equals("") && !date_of_completion_md_part2.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, date_of_completion_md_part2 );
			}
			if( date_of_declaration_of_md != null && !date_of_declaration_of_md.equals("") && !date_of_declaration_of_md.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, date_of_declaration_of_md );
			}
			if( date_of_completion_internship != null && !date_of_completion_internship.equals("") && !date_of_completion_internship.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, date_of_completion_internship );
			}
			if (remarks != null && !remarks.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+remarks.toUpperCase()+"%");
			}
			if (reject_remarks != null && !reject_remarks.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+reject_remarks.toUpperCase()+"%");
			}

		} catch (Exception e) {
		}

		return stmt;
	}
	
	public DG_REC_STUDENTS_ADMITTED_B getadmittedPGByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		DG_REC_STUDENTS_ADMITTED_B eid = (DG_REC_STUDENTS_ADMITTED_B) session
				.get(DG_REC_STUDENTS_ADMITTED_B.class, id);
		session.getTransaction().commit();
		session.close();
		return eid;
	}
	
	// ---------------------- Examiners PG  ---------------------//

	public List<Map<String, Object>> getFilter_Examiners_Student_pg_List(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, int university_id, int user_id,String institute_status,String name_of_homoeopathic_medical_college,
			String name_of_guide,String name_of_student_for_guide,String topic_of_dissertation,String whether_guide_participated_in_examination,
			String date_of_submission,String date_of_acceptance,String whether_student_published_article,String mention_details,
			String article_title,String month_year_exam,String reject_remarks) {

		String SearchValue = GenerateQueryWhereClause_SQL1(Search,  name_of_homoeopathic_medical_college,
				 name_of_guide, name_of_student_for_guide, topic_of_dissertation, whether_guide_participated_in_examination,
				 date_of_submission, date_of_acceptance, whether_student_published_article, mention_details,
				 article_title, month_year_exam, reject_remarks);
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();

			q = "select a.id,a.user_id,a.name_of_homoeopathic_medical_college,a.name_of_guide,a.name_of_student_for_guide,\n"
					+ "a.topic_of_dissertation,a.whether_guide_participated_in_examination,\n"
					+ "to_char(a.date_of_submission,'DD/MM/YYYY') as date_of_submission,\n"
					+ "to_char(a.date_of_acceptance,'DD/MM/YYYY') as date_of_acceptance,\n"
					+ "a.whether_student_published_article,a.mention_details,a.article_title,\n"
					+ "a.month_year_exam,a.reject_remarks \n"
					+ "from dg_rec_examiners_md_child_b  a where a.university_id=? and a.user_id=? and a.inst_status=?" + SearchValue + " ORDER BY a.id "+ orderType + " limit "
					+ pageLength + " OFFSET " + startPage; // where a.id=?

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL1(stmt,Search,name_of_homoeopathic_medical_college,
					 name_of_guide, name_of_student_for_guide, topic_of_dissertation, whether_guide_participated_in_examination,
					 date_of_submission, date_of_acceptance, whether_student_published_article, mention_details,
					 article_title, month_year_exam, reject_remarks);
			 stmt.setInt(1, university_id);
			 stmt.setInt(2, user_id);
			 stmt.setInt(3, Integer.parseInt(institute_status));
			System.err.println("----exam--pg----"+stmt);
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;

			int countFunction = 1;

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				String f = "";
				String action = "";
				String f1 = "";

				String ul = "";
				ul += "<ul class='buttons-group mainbtn action daobtn'>";

				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDExaminerspg' value='ADD' title='Edit Data' >"
						+ "<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='apIdExaminpg" + countFunction
						+ "' value=" + rs.getString("id") + ">" + "</i></a></li>";

				ul += f + " " + f1;
				ul += "</ul>";

				action = ul;
				countFunction += 1;
				columns.put("action", action);

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

	// ---------------- Examiners Pg List Count -----------------//
	@Override
	public long getFilter_Examiners_Student_pgListCount(String search, int user_id,int university_id,String institute_status,String name_of_homoeopathic_medical_college,
			String name_of_guide,String name_of_student_for_guide,String topic_of_dissertation,String whether_guide_participated_in_examination,
			String date_of_submission,String date_of_acceptance,String whether_student_published_article,String mention_details,
			String article_title,String month_year_exam,String reject_remarks) {
		
		String SearchValue = GenerateQueryWhereClause_SQL1(search,  name_of_homoeopathic_medical_college,
				 name_of_guide, name_of_student_for_guide, topic_of_dissertation, whether_guide_participated_in_examination,
				 date_of_submission, date_of_acceptance, whether_student_published_article, mention_details,
				 article_title, month_year_exam, reject_remarks);
		
		int total = 0;
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select a.id,a.user_id,a.name_of_homoeopathic_medical_college,a.name_of_guide,a.name_of_student_for_guide,\n"
					+ "a.topic_of_dissertation,a.whether_guide_participated_in_examination,\n"
					+ "to_char(a.date_of_submission,'DD/MM/YYYY') as date_of_submission,\n"
					+ "to_char(a.date_of_acceptance,'DD/MM/YYYY') as date_of_acceptance,\n"
					+ "a.whether_student_published_article,a.mention_details,a.article_title,a.month_year_exam,a.reject_remarks \n"
					+ "from dg_rec_examiners_md_child_b  a where a.university_id=? and a.user_id=? and a.inst_status=?) a ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL1(stmt,search,name_of_homoeopathic_medical_college,
					 name_of_guide, name_of_student_for_guide, topic_of_dissertation, whether_guide_participated_in_examination,
					 date_of_submission, date_of_acceptance, whether_student_published_article, mention_details,
					 article_title, month_year_exam, reject_remarks);
			 stmt.setInt(1, university_id);
			 stmt.setInt(2, user_id);
			 stmt.setInt(3, Integer.parseInt(institute_status));
			 System.err.println("examiners---pggg--list----"+stmt);
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
	public String GenerateQueryWhereClause_SQL1(String Search,String name_of_homoeopathic_medical_college,
			String name_of_guide,String name_of_student_for_guide,String topic_of_dissertation,String whether_guide_participated_in_examination,
			String date_of_submission,String date_of_acceptance,String whether_student_published_article,String mention_details,
			String article_title,String month_year_exam,String reject_remarks) {
		
		String SearchValue="" ;
		
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (upper(name_of_homoeopathic_medical_college) Like ? "
					+ " or upper(name_of_guide) Like ? "
					+ " or upper(name_of_student_for_guide) Like ? "
					+ " or upper(topic_of_dissertation) Like ? or upper(whether_guide_participated_in_examination) Like ? "
					+ " or TO_CHAR(date_of_submission , 'dd/MM/yyyy') like ? "
					+ " or TO_CHAR(date_of_acceptance , 'dd/MM/yyyy') like ? "
					+ " or upper(whether_student_published_article) Like ? or upper(mention_details) Like ? "
					+ " or upper(month_year_exam) Like ? or upper(reject_remarks) Like ?) ";
		}
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL1(PreparedStatement stmt, String Search,String name_of_homoeopathic_medical_college,
			String name_of_guide,String name_of_student_for_guide,String topic_of_dissertation,String whether_guide_participated_in_examination,
			String date_of_submission,String date_of_acceptance,String whether_student_published_article,String mention_details,
			String article_title,String month_year_exam,String reject_remarks) {
		int flag = 3;
		try {
			if (Search!=null &&  !Search.equals("")) {
				
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
				stmt.setString(flag, "%" + Search + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search + "%");
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
			if (name_of_homoeopathic_medical_college != null && !name_of_homoeopathic_medical_college.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+name_of_homoeopathic_medical_college.toUpperCase()+"%");
			}
			if (name_of_guide != null && !name_of_guide.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+name_of_guide.toUpperCase()+"%");
			}
			if (name_of_student_for_guide != null && !name_of_student_for_guide.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+name_of_student_for_guide.toUpperCase()+"%");
			}
			if (topic_of_dissertation != null && !topic_of_dissertation.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+topic_of_dissertation.toUpperCase()+"%");
			}
			if (whether_guide_participated_in_examination != null && !whether_guide_participated_in_examination.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+whether_guide_participated_in_examination.toUpperCase()+"%");
			}
			if( date_of_submission != null && !date_of_submission.equals("") && !date_of_submission.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, date_of_submission );
			}
			if( date_of_acceptance != null && !date_of_acceptance.equals("") && !date_of_acceptance.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, date_of_acceptance );
			}
			if (whether_student_published_article != null && !whether_student_published_article.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+whether_student_published_article.toUpperCase()+"%");
			}
			if (mention_details != null && !mention_details.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+mention_details.toUpperCase()+"%");
			}
			if (article_title != null && !article_title.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+article_title.toUpperCase()+"%");
			}
			if (month_year_exam != null && !month_year_exam.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+month_year_exam.toUpperCase()+"%");
			}
			if (reject_remarks != null && !reject_remarks.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+reject_remarks.toUpperCase()+"%");
			}

		} catch (Exception e) {
		}

		return stmt;
	}
	
	public DG_REC_EXAMINERS_MD_B getmigratedPGByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		DG_REC_EXAMINERS_MD_B expg_id = (DG_REC_EXAMINERS_MD_B) session
				.get(DG_REC_EXAMINERS_MD_B.class, id);
		session.getTransaction().commit();
		session.close();
		return expg_id;
	}
	
	// ---------------------- Dissertation PG  ---------------------//

		public List<Map<String, Object>> getFilter_Dissertation_Student_pg_List(int startPage, int pageLength, String Search,
				String orderColunm, String orderType, int university_id, int user_id,String institute_status,String student_name_pg,String from_date,
				String to_date,String name_of_topic,String conclusion_obtain,String date_of_submission_pg,String reject_remarks,
				String publication,String mention_article) {
			
			String SearchValue = GenerateQueryWhereClause_SQL2(Search,  student_name_pg, from_date,
					 to_date, name_of_topic, conclusion_obtain, date_of_submission_pg, reject_remarks,
					 publication, mention_article);
			
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			try {

				conn = dataSource.getConnection();

				q = "select a.id,a.user_id,a.student_name_pg,"
						+ "to_char(a.from_date,'DD/MM/YYYY') as from_date,\n"
						+ "to_char(a.to_date,'DD/MM/YYYY') as to_date,\n"
						+ "a.name_of_topic,a.conclusion_obtain,\n"
						+ "to_char(a.date_of_submission_pg,'DD/MM/YYYY') as date_of_submission_pg,a.reject_remarks,\n"
						+ "a.publication,a.mention_article\n"
						+ "from dg_rec_work_done_pg_student_child_b  a where a.university_id=? and a.user_id=? and a.inst_status=?" + SearchValue + " ORDER BY a.id "+ orderType + " limit "
						+ pageLength + " OFFSET " + startPage; // where a.id=?

				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL2(stmt,Search,student_name_pg, from_date,
						 to_date, name_of_topic, conclusion_obtain, date_of_submission_pg, reject_remarks,
						 publication, mention_article);
				 stmt.setInt(1, university_id);
				 stmt.setInt(2, user_id);
				 stmt.setInt(3, Integer.parseInt(institute_status));
				System.err.println("----Dissertation--pg----"+stmt);
				ResultSet rs = stmt.executeQuery();

				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				int j = startPage;

				int countFunction = 1;

				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					columns.put("ser", ++j);
					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}
					String f = "";
					String action = "";
					String f1 = "";
					
					String ul="";
					ul+="<ul class='buttons-group mainbtn action daobtn'>";
					
					f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDDissertation' data-bs-toggle='modal' data-bs-target='#Dissertationmodal' value='ADD' title='Edit Data' >" 
							+"<i class='lni lni-pencil-alt'>"
							+"<input type='hidden' id='apIdDissertation"+countFunction+"' value="+rs.getString("id")+">"
						    +"</i></a></li>";
					
					ul+=f + " " + f1 ;
					ul+="</ul>";
					
					action = ul;
					countFunction+=1;
					columns.put("action", action);

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

		// ---------------- Dissertation Pg List Count -----------------//
		@Override
		public long getFilter_Dissertation_Student_pgListCount(String search, int user_id,int university_id,String institute_status,String student_name_pg,String from_date,
				String to_date,String name_of_topic,String conclusion_obtain,String date_of_submission_pg,String reject_remarks,
				String publication,String mention_article) {
			
			String SearchValue = GenerateQueryWhereClause_SQL2(search,  student_name_pg, from_date,
					 to_date, name_of_topic, conclusion_obtain, date_of_submission_pg, reject_remarks,
					 publication, mention_article);
			
			int total = 0;
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();

				q = "select count(*) from (select a.id,a.user_id,a.student_name_pg,\n"
						+ "to_char(a.from_date,'DD/MM/YYYY') as from_date,\n"
						+ "to_char(a.to_date,'DD/MM/YYYY') as to_date,\n"
						+ "a.name_of_topic,a.conclusion_obtain,\n"
						+ "to_char(a.date_of_submission_pg,'DD/MM/YYYY') as date_of_submission_pg,a.reject_remarks,\n"
						+ "a.publication,a.mention_article\n"
						+ "from dg_rec_work_done_pg_student_child_b  a where a.university_id=? and a.user_id=? and a.inst_status=?) a";

				
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL2(stmt,search,student_name_pg, from_date,
						 to_date, name_of_topic, conclusion_obtain, date_of_submission_pg, reject_remarks,
						 publication, mention_article);
				 stmt.setInt(1, university_id);
				 stmt.setInt(2, user_id);
				 stmt.setInt(3, Integer.parseInt(institute_status));
				 System.err.println("Dissertation---pggg--list----"+stmt);
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
		public String GenerateQueryWhereClause_SQL2(String Search,String student_name_pg,String from_date,
				String to_date,String name_of_topic,String conclusion_obtain,String date_of_submission_pg,String reject_remarks,
				String publication,String mention_article) {
			
			String SearchValue="" ;
			
			if (Search!=null && !Search.equals("")) { // for Input Filter
				SearchValue += " and (upper(student_name_pg) Like ? "
						+ " or TO_CHAR(from_date , 'dd/MM/yyyy') like ? "
						+ " or TO_CHAR(to_date , 'dd/MM/yyyy') like ? "
						+ " or upper(name_of_topic) Like ? "
						+ " or upper(conclusion_obtain) Like ? "
						+ " or TO_CHAR(date_of_submission_pg , 'dd/MM/yyyy') like ? "
						+ " or upper(reject_remarks) Like ? "
						+ " or upper(publication) Like ? "
						+ " or upper(mention_article) Like ?) ";
			}
			return SearchValue;
		}
		
		public PreparedStatement setQueryWhereClause_SQL2(PreparedStatement stmt, String Search,String student_name_pg,String from_date,
				String to_date,String name_of_topic,String conclusion_obtain,String date_of_submission_pg,String reject_remarks,
				String publication,String mention_article) {
			int flag = 3;
			try {
				if (Search!=null &&  !Search.equals("")) {
					
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					
				}
				if (student_name_pg != null && !student_name_pg.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+student_name_pg.toUpperCase()+"%");
				}
				if( from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) {
					flag += 1;
					stmt.setString(flag, from_date );
				}
				if( to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY")) {
					flag += 1;
					stmt.setString(flag, to_date );
				}
				if (name_of_topic != null && !name_of_topic.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+name_of_topic.toUpperCase()+"%");
				}
				if (conclusion_obtain != null && !conclusion_obtain.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+conclusion_obtain.toUpperCase()+"%");
				}
				if( date_of_submission_pg != null && !date_of_submission_pg.equals("") && !date_of_submission_pg.equals("DD/MM/YYYY")) {
					flag += 1;
					stmt.setString(flag, date_of_submission_pg );
				}
				if (reject_remarks != null && !reject_remarks.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+reject_remarks.toUpperCase()+"%");
				}
				if (publication != null && !publication.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+publication.toUpperCase()+"%");
				}
				if (mention_article != null && !mention_article.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+mention_article.toUpperCase()+"%");
				}

			} catch (Exception e) {
			}

			return stmt;
		}
		
		
		@Override
		public List<Map<String, Object>> getdissertationdatafromByid(String id) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			try {
				
				conn = dataSource.getConnection();
				
				q="select *,to_char(from_date,'dd/MM/yyyy') as from_date,to_char(to_date,'dd/MM/yyyy') as to_date,to_char(date_of_submission_pg,'dd/MM/yyyy') as date_of_submission_pg from dg_rec_work_done_pg_student_child_b where id=? ";
						
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt.setInt(1,Integer.parseInt(id));
					ResultSet rs = stmt.executeQuery();
					System.err.println("---getdissertationdatafromByid-----"+stmt);
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

		
		// ---------------------- Lecture PG  ---------------------//

				public List<Map<String, Object>> getFilter_Lecture_Student_pg_List(int startPage, int pageLength, String Search,
						String orderColunm, String orderType, int university_id, int user_id,String institute_status,String student_name,String student_class,
						String lecture_date,String lecture_day,String lecture_time,String topic,String reject_remarks) {
					
					String SearchValue = GenerateQueryWhereClause_SQL3(Search,  student_name, student_class,
							 lecture_date, lecture_day, lecture_time, topic, reject_remarks);
					
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					Connection conn = null;
					String q = "";
					try {

						conn = dataSource.getConnection();

						q = "select a.id,a.user_id,a.student_name_lec,"
								+ "a.student_class_lec,\n"
								+ "to_char(a.lecture_date,'DD/MM/YYYY') as lecture_date,\n"
								+ "a.lecture_day,a.lecture_time,\n"
								+ "a.topic,a.reject_remarks\n"
								+ "from dg_rec_lecture_pg_student_child_b  a where a.university_id=? and a.user_id=? and a.inst_status=?" + SearchValue + " ORDER BY a.id "+ orderType + " limit "
								+ pageLength + " OFFSET " + startPage; // where a.id=?

						PreparedStatement stmt = conn.prepareStatement(q);
						stmt = setQueryWhereClause_SQL3(stmt,Search,student_name, student_class, lecture_date, lecture_day, lecture_time, topic, reject_remarks);
						
						 stmt.setInt(1, university_id);
						 stmt.setInt(2, user_id);
						 stmt.setInt(3, Integer.parseInt(institute_status));
						System.err.println("----Lecture--pg----"+stmt);
						ResultSet rs = stmt.executeQuery();

						ResultSetMetaData metaData = rs.getMetaData();
						int columnCount = metaData.getColumnCount();
						int j = startPage;

						int countFunction = 1;

						while (rs.next()) {
							Map<String, Object> columns = new LinkedHashMap<String, Object>();
							columns.put("ser", ++j);
							for (int i = 1; i <= columnCount; i++) {
								columns.put(metaData.getColumnLabel(i), rs.getObject(i));
							}
							String f = "";
							String action = "";
							String f1 = "";
							
							String ul="";
							ul+="<ul class='buttons-group mainbtn action daobtn'>";
							
							f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDlecture' data-bs-toggle='modal' data-bs-target='#Dissertationmodal' value='ADD' title='Edit Data' >" 
									+"<i class='lni lni-pencil-alt'>"
									+"<input type='hidden' id='apIdlecture"+countFunction+"' value="+rs.getString("id")+">"
								    +"</i></a></li>";
							
							ul+=f + " " + f1 ;
							ul+="</ul>";
							
							action = ul;
							countFunction+=1;
							columns.put("action", action);

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

				// ---------------- Lecture Pg List Count -----------------//
				@Override
				public long getFilter_Lecture_Student_pgListCount(String search, int user_id,int university_id,String institute_status,String student_name,String student_class,
						String lecture_date,String lecture_day,String lecture_time,String topic,String reject_remarks) {
					
					String SearchValue = GenerateQueryWhereClause_SQL3(search, student_name, student_class,
							 lecture_date, lecture_day, lecture_time, topic, reject_remarks);
					
					int total = 0;
					Connection conn = null;
					String q = "";
					try {
						conn = dataSource.getConnection();

						q = "select count(*) from (select a.id,a.user_id,a.student_name_lec,\n"
								+ "a.student_class_lec,\n"
								+ "to_char(a.lecture_date,'DD/MM/YYYY') as lecture_date,\n"
								+ "a.lecture_day,a.lecture_time,\n"
								+ "a.topic,a.reject_remarks\n"
								+ "from dg_rec_lecture_pg_student_child_b a where a.university_id=? and a.user_id=? and a.inst_status=?) a";

						
						PreparedStatement stmt = conn.prepareStatement(q);
						stmt = setQueryWhereClause_SQL3(stmt,search,student_name, student_class, lecture_date, lecture_day, lecture_time, topic, reject_remarks);
						 stmt.setInt(1, university_id);
						 stmt.setInt(2, user_id);
						 stmt.setInt(3, Integer.parseInt(institute_status));
						 System.err.println("Lecture---pggg--list----"+stmt);
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
				public String GenerateQueryWhereClause_SQL3(String Search,String student_name,String student_class,
						String lecture_date,String lecture_day,String lecture_time,String topic,String reject_remarks) {
					
					String SearchValue="" ;
					
					if (Search!=null && !Search.equals("")) { // for Input Filter
						SearchValue += " and (upper(student_name) Like ? "
								+ " or upper(student_class) Like ? "
								+ " or TO_CHAR(lecture_date , 'dd/MM/yyyy') like ? "
								+ " or upper(lecture_day) Like ? "
								+ " or upper(lecture_time) Like ? "
								+ " or upper(topic) Like ? "
								+ " or upper(reject_remarks) Like ?) ";
					}
					return SearchValue;
				}
				
				public PreparedStatement setQueryWhereClause_SQL3(PreparedStatement stmt, String Search,String student_name,String student_class,
						String lecture_date,String lecture_day,String lecture_time,String topic,String reject_remarks) {
					int flag = 3;
					try {
						if (Search!=null &&  !Search.equals("")) {
							
							flag += 1;
							stmt.setString(flag, "%" + Search.toUpperCase() + "%");
							flag += 1;
							stmt.setString(flag, "%" + Search.toUpperCase() + "%");
							flag += 1;
							stmt.setString(flag, "%" + Search + "%");
							flag += 1;
							stmt.setString(flag, "%" + Search.toUpperCase() + "%");
							flag += 1;
							stmt.setString(flag, "%" + Search.toUpperCase() + "%");
							flag += 1;
							stmt.setString(flag, "%" + Search.toUpperCase() + "%");
							flag += 1;
							stmt.setString(flag, "%" + Search.toUpperCase() + "%");
							
						}
						if (student_name != null && !student_name.equals("")) {
							flag += 1;
							stmt.setString(flag,"%"+student_name.toUpperCase()+"%");
						}
						if (student_class != null && !student_class.equals("")) {
							flag += 1;
							stmt.setString(flag,"%"+student_class.toUpperCase()+"%");
						}
						if( lecture_date != null && !lecture_date.equals("") && !lecture_date.equals("DD/MM/YYYY")) {
							flag += 1;
							stmt.setString(flag, lecture_date );
						}
						if (lecture_day != null && !lecture_day.equals("")) {
							flag += 1;
							stmt.setString(flag,"%"+lecture_day.toUpperCase()+"%");
						}
						if (lecture_time != null && !lecture_time.equals("")) {
							flag += 1;
							stmt.setString(flag,"%"+lecture_time.toUpperCase()+"%");
						}
						if (topic != null && !topic.equals("")) {
							flag += 1;
							stmt.setString(flag,"%"+topic.toUpperCase()+"%");
						}
						if (reject_remarks != null && !reject_remarks.equals("")) {
							flag += 1;
							stmt.setString(flag,"%"+reject_remarks.toUpperCase()+"%");
						}
						

					} catch (Exception e) {
					}

					return stmt;
				}
				
				public List<Map<String, Object>> getlacturetakenpgtoByid(String id) {
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					Connection conn = null;
					String q = "";
					System.err.println("--C-in daoimpl-dddd--ddddddddddddd-");
					try {
						
						conn = dataSource.getConnection();
						
						q="select *,to_char(lecture_date,'dd/MM/yyyy') as lecture_date from dg_rec_lecture_pg_student_child_b where id=?";
								
						PreparedStatement stmt = conn.prepareStatement(q);
						stmt.setInt(1,Integer.parseInt(id));
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
				// ---------------------- Assignment PG ---------------------//

				public List<Map<String, Object>> getFilter_assignment_pg_List(int startPage, int pageLength, String Search,
						String orderColunm, String orderType, int university_id, int user_id,String institute_status,String student_name_formc,
						String list_of_assignment_formc, String reject_remarks ) {
					
					String SearchValue = GenerateQueryWhereClause_SQL4(Search,  student_name_formc,
							 list_of_assignment_formc,  reject_remarks);
					
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					Connection conn = null;
					String q = "";
					try {

						conn = dataSource.getConnection();

						q = "select a.id,a.user_id,a.student_name_formc,"
								+ "a.list_of_assignment_formc,a.reject_remarks\n"
								+ "from dg_rec_assignment_pg_student_child_b  a where a.university_id=? and a.user_id=? and a.inst_status=?" + SearchValue + " ORDER BY a.id "+ orderType + " limit "
								+ pageLength + " OFFSET " + startPage; // where a.id=?
						
						PreparedStatement stmt = conn.prepareStatement(q);
						
						stmt = setQueryWhereClause_SQL4(stmt,Search, student_name_formc, list_of_assignment_formc,  reject_remarks);
						
						 stmt.setInt(1, university_id);
						 stmt.setInt(2, user_id);
						 stmt.setInt(3, Integer.parseInt(institute_status));
						 
							 System.err.println("----assignment--"+stmt);
						ResultSet rs = stmt.executeQuery();

						ResultSetMetaData metaData = rs.getMetaData();
						int columnCount = metaData.getColumnCount();
						int j = startPage;

						int countFunction = 1;

						while (rs.next()) {
							Map<String, Object> columns = new LinkedHashMap<String, Object>();
							columns.put("ser", ++j);
							for (int i = 1; i <= columnCount; i++) {
								columns.put(metaData.getColumnLabel(i), rs.getObject(i));
							}
							String f = "";
							String action = "";
							String f1 = "";

							String ul = "";
							ul += "<ul class='buttons-group mainbtn action daobtn'>";


							f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDAssignment' data-bs-toggle='modal' data-bs-target='#Dissertationmodal' value='ADD' title='Edit Data' >" 
									+"<i class='lni lni-pencil-alt'>"
									+"<input type='hidden' id='apIdAss"+countFunction+"' value="+rs.getString("id")+">"
								    +"</i></a></li>";
							
							ul += f + " " + f1;
							ul += "</ul>";

							action = ul;
							countFunction += 1;
							columns.put("action", action);

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
					// ---------------- Assignment  PG List Count -----------------//
				@Override
				public long getFilter_assignment_pgListCount(String search, int user_id,int university_id,String institute_status,String student_name_formc,
						String list_of_assignment_formc, String reject_remarks ) {
					
					String SearchValue = GenerateQueryWhereClause_SQL4(search,  student_name_formc,
							 list_of_assignment_formc,  reject_remarks);

					int total = 0;
					Connection conn = null;
					String q = "";
					try {
						conn = dataSource.getConnection();

						q = "select count(*) from (select a.id,a.user_id,a.student_name_formc,a.list_of_assignment_formc,a.reject_remarks\n"
								+ "from dg_rec_assignment_pg_student_child_b  a where a.university_id=? and a.user_id=? and a.inst_status=?) a  ";

						PreparedStatement stmt = conn.prepareStatement(q);
						
						stmt = setQueryWhereClause_SQL4(stmt,search, student_name_formc, list_of_assignment_formc,  reject_remarks);
						
						 stmt.setInt(1, university_id);
						 stmt.setInt(2, user_id);
						 stmt.setInt(3, Integer.parseInt(institute_status));
//						    System.err.println("INST_EDIT_Count_1----"+stmt);
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
				public String GenerateQueryWhereClause_SQL4(String Search,String student_name_formc,
						String list_of_assignment_formc, String reject_remarks) {
					
					String SearchValue="" ;
					
					if (Search!=null && !Search.equals("")) { // for Input Filter
						SearchValue += " and (upper(student_name_formc) Like ? "
								+ " or upper(list_of_assignment_formc) Like ? "
								+ " or upper(reject_remarks) Like ?) ";
					}
					return SearchValue;
				}
				
				public PreparedStatement setQueryWhereClause_SQL4(PreparedStatement stmt, String Search,String student_name_formc,
						String list_of_assignment_formc, String reject_remarks) {
					int flag = 3;
					try {
						if (Search!=null &&  !Search.equals("")) {
							
							flag += 1;
							stmt.setString(flag, "%" + Search.toUpperCase() + "%");
							flag += 1;
							stmt.setString(flag, "%" + Search.toUpperCase() + "%");
							flag += 1;
							stmt.setString(flag, "%" + Search.toUpperCase() + "%");
							
						}
						if (student_name_formc != null && !student_name_formc.equals("")) {
							flag += 1;
							stmt.setString(flag,"%"+student_name_formc.toUpperCase()+"%");
						}
						if (list_of_assignment_formc != null && !list_of_assignment_formc.equals("")) {
							flag += 1;
							stmt.setString(flag,"%"+list_of_assignment_formc.toUpperCase()+"%");
						}
						if (reject_remarks != null && !reject_remarks.equals("")) {
							flag += 1;
							stmt.setString(flag,"%"+reject_remarks.toUpperCase()+"%");
						}
						

					} catch (Exception e) {
					}

					return stmt;
				}
				
				@Override
				public List<Map<String, Object>> getassignmentpgtoByid(String id) {
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					Connection conn = null;
					String q = "";
					System.err.println("--C-in daoimpl-dddd--pppppppppppppp-");
					try {
						
						conn = dataSource.getConnection();
						
						q="select * from dg_rec_assignment_pg_student_child_b where id=? ";
								
						PreparedStatement stmt = conn.prepareStatement(q);
						stmt.setInt(1,Integer.parseInt(id));
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
				
				// ---------------------- Presentation PG ---------------------//

				public List<Map<String, Object>> getFilter_presentation_pg_List(int startPage, int pageLength, String Search,
						String orderColunm, String orderType, int university_id, int user_id,String institute_status,String student_name_presen,
						String list_of_presentations,String title_of_seminar_attended, String reject_remarks) {
					
					String SearchValue = GenerateQueryWhereClause_SQL5(Search,  student_name_presen,
							list_of_presentations, title_of_seminar_attended, reject_remarks);
					
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					Connection conn = null;
					String q = "";
					try {

						conn = dataSource.getConnection();

						q = "select a.id,a.user_id,a.student_name_presen,"
								+ "a.list_of_presentations,a.title_of_seminar_attended,a.reject_remarks\n"
								+ "from dg_rec_presentation_seminar_student_child_b  a where a.university_id=? and a.user_id=? and a.inst_status=?" + SearchValue + " ORDER BY a.id "+ orderType + " limit "
								+ pageLength + " OFFSET " + startPage; 
						
						PreparedStatement stmt = conn.prepareStatement(q);
						stmt = setQueryWhereClause_SQL5(stmt,Search, student_name_presen, list_of_presentations,title_of_seminar_attended , reject_remarks);
						 stmt.setInt(1, university_id);
						 stmt.setInt(2, user_id);
						 stmt.setInt(3, Integer.parseInt(institute_status));
									 System.err.println("----presentation----"+stmt);
						ResultSet rs = stmt.executeQuery();

						ResultSetMetaData metaData = rs.getMetaData();
						int columnCount = metaData.getColumnCount();
						int j = startPage;

						int countFunction = 1;

						while (rs.next()) {
							Map<String, Object> columns = new LinkedHashMap<String, Object>();
							columns.put("ser", ++j);
							for (int i = 1; i <= columnCount; i++) {
								columns.put(metaData.getColumnLabel(i), rs.getObject(i));
							}
							String f = "";
							String action = "";
							String f1 = "";

							String ul = "";
							ul += "<ul class='buttons-group mainbtn action daobtn'>";


							f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDPresentation' data-bs-toggle='modal' data-bs-target='#Dissertationmodal' value='ADD' title='Edit Data' >" 
									+"<i class='lni lni-pencil-alt'>"
									+"<input type='hidden' id='apIdPres"+countFunction+"' value="+rs.getString("id")+">"
								    +"</i></a></li>";
							ul += f + " " + f1;
							ul += "</ul>";

							action = ul;
							countFunction += 1;
							columns.put("action", action);

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
					// ---------------- Presentation  PG List Count -----------------//
				@Override
				public long getFilter_presentation_pgListCount(String search, int user_id,int university_id,String institute_status,String student_name_presen,
						String list_of_presentations,String title_of_seminar_attended, String reject_remarks) {
					
					String SearchValue = GenerateQueryWhereClause_SQL5(search,  student_name_presen,
							list_of_presentations, title_of_seminar_attended, reject_remarks);

					int total = 0;
					Connection conn = null;
					String q = "";
					try {
						conn = dataSource.getConnection();

						q = "select count(*) from (select a.id,a.user_id,a.student_name_presen,a.list_of_presentations,a.title_of_seminar_attended,a.reject_remarks \n"
								+ "from dg_rec_presentation_seminar_student_child_b  a where a.university_id=? and a.user_id=? and a.inst_status=?) a  ";

						PreparedStatement stmt = conn.prepareStatement(q);
						stmt = setQueryWhereClause_SQL5(stmt,search, student_name_presen, list_of_presentations,title_of_seminar_attended , reject_remarks);
						 stmt.setInt(1, university_id);
						 stmt.setInt(2, user_id);
						 stmt.setInt(3, Integer.parseInt(institute_status));
//						    System.err.println("INST_EDIT_Count_1----"+stmt);
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
				public String GenerateQueryWhereClause_SQL5(String Search,String student_name_presen,
						String list_of_presentations,String title_of_seminar_attended, String reject_remarks) {
					
					String SearchValue="" ;
					
					if (Search!=null && !Search.equals("")) { // for Input Filter
						SearchValue += " and (upper(student_name_presen) Like ? "
								+ " or upper(list_of_presentations) Like ? "
								+ " or upper(title_of_seminar_attended) Like ? "
								+ " or upper(reject_remarks) Like ?) ";
					}
					return SearchValue;
				}
				
				public PreparedStatement setQueryWhereClause_SQL5(PreparedStatement stmt, String Search,String student_name_presen,
						String list_of_presentations,String title_of_seminar_attended, String reject_remarks) {
					int flag = 3;
					try {
						if (Search!=null &&  !Search.equals("")) {
							
							flag += 1;
							stmt.setString(flag, "%" + Search.toUpperCase() + "%");
							flag += 1;
							stmt.setString(flag, "%" + Search.toUpperCase() + "%");
							flag += 1;
							stmt.setString(flag, "%" + Search.toUpperCase() + "%");
							flag += 1;
							stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						}
						if (student_name_presen != null && !student_name_presen.equals("")) {
							flag += 1;
							stmt.setString(flag,"%"+student_name_presen.toUpperCase()+"%");
						}
						if (list_of_presentations != null && !list_of_presentations.equals("")) {
							flag += 1;
							stmt.setString(flag,"%"+list_of_presentations.toUpperCase()+"%");
						}
						if (title_of_seminar_attended != null && !title_of_seminar_attended.equals("")) {
							flag += 1;
							stmt.setString(flag,"%"+title_of_seminar_attended.toUpperCase()+"%");
						}
						if (reject_remarks != null && !reject_remarks.equals("")) {
							flag += 1;
							stmt.setString(flag,"%"+reject_remarks.toUpperCase()+"%");
						}
						

					} catch (Exception e) {
					}

					return stmt;
				}
				
				@Override
				public List<Map<String, Object>> getpresentationpgtoByid(String id) {
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					Connection conn = null;
					String q = "";
					System.err.println("--C-in daoimpl-dddd--pppppppppppppp-");
					try {
						
						conn = dataSource.getConnection();
						
						q="select * from dg_rec_presentation_seminar_student_child_b where id=? ";
								
						PreparedStatement stmt = conn.prepareStatement(q);
						stmt.setInt(1,Integer.parseInt(id));
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
