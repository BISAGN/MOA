package com.AyushEdu.dao.Curriculum;

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
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Curriculum.EDU_CC_TB_PAPER_LAYOUT;

//import com.AyushEdu.Models.Teacher_Master.EDU_DOC_ATTACHMENTS_MSTR;
//import com.AyushEdu.Models.Teacher_Master.TB_NCH_DEPARTMENT_MSTR;

//import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PROFESSIONAL_MSTR;

@Repository
public class Paper_Layout_DaoImpl implements Paper_Layout_Dao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public long DataTablePaperLayoutDataTotalCount(String Search, String system_id, String degree_id,
			String professional_id, String course_id, String paper_id, String time, String instructions,
			String question_type_id, String num_questions, String marks_questions, String status, String role) {

		String sd = "";

		if (role.contains("NCISM")) {
			sd = " and pl.system_id != 45 ";
		}
		if (role.contains("NCH")) {
			sd = " and pl.system_id = 45 ";
		}

		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_id, degree_id, professional_id, course_id,
				paper_id, time, instructions, question_type_id, num_questions, marks_questions, status);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			// Query for count page in data-table....by ruler
			// q = "select count(*) \n" + " from edu_lms_system_mstr where id!=0 and
			// status='1' " + SearchValue;

			q = "select count(*) from (select pl.id,pl.time,pl.instructions,pl.num_questions,pl.marks_questions,pl.paper_id,"
					+ "pm.paper,pl.question_type_id,qm.question_type,pl.status,s.system_name,d.degree_name,p.professional,"
					+ "cc.course_name,pl.system_id,pl.degree_id,pl.professional_id,pl.course_id from edu_cc_tb_paper_layout pl\n"
					+ "inner join edu_lms_system_mstr s on s.id = pl.system_id\n"
					+ "inner join edu_lms_degree_mstr d on d.id = pl.degree_id\n"
					+ "inner join edu_cc_tb_professional_mstr p on p.id = pl.professional_id\n"
					+ "inner join edu_lms_course_mstr cc on cc.id = pl.course_id::int\n"
					+ "inner join edu_cc_tb_paper_mstr pm on pm.id = pl.paper_id\n"
					+ "inner join edu_cc_tb_question_type_mstr qm on qm.id = pl.question_type_id\n"
					+ "where pl.status='" + status + "' \n" + sd + SearchValue + ") ab";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, system_id, degree_id, professional_id, course_id, paper_id,
					time, instructions, question_type_id, num_questions, marks_questions);

			System.err.println("stmt" + stmt);

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
	public String GenerateQueryWhereClause_SQL(String Search, String system_id, String degree_id,
			String professional_id, String course_id, String paper_id, String time, String instructions,
			String question_type_id, String num_questions, String marks_questions, String status) {
		String SearchValue = "";

		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and ( upper(s.system_name) like ?or upper(d.degree_name) like ? or "
					+ "upper(p.professional) like ?  or upper(cc.course_name) like ? or "
					+ " paper like ? or  cast(time as text) like ? or  upper(instructions) like ? or  question_type like ? or  cast(num_questions as text) like ? or  cast(marks_questions as text) like ?)";
			// System.err.println("globalllll search"+SearchValue);
		}

		/// advance search
		if (!system_id.equals("0")) {
			SearchValue += " and pl.system_id =? ";
		}
		if (!degree_id.equals("0")) {
			SearchValue += " and pl.degree_id =? ";
		}
		if (!professional_id.equals("0")) {
			SearchValue += " and pl.professional_id =? ";
		}
		if (!course_id.equals("0")) {
			SearchValue += " and pl.course_id =? ";
		}
		if (time != null && !time.equals("")) {
			SearchValue += " and cast(time as text) like ? ";

		}
		if (instructions != null && !instructions.equals("")) {
			SearchValue += " and upper(instructions) like ? ";

		}
		if (num_questions != null && !num_questions.equals("")) {
			SearchValue += " and cast (num_questions as text) like ? ";

		}
		if (marks_questions != null && !marks_questions.equals("")) {
			SearchValue += " and cast (marks_questions as text) like ? ";

		}

		if (!paper_id.equals("0")) {
			SearchValue += " and paper_id = ? ";
		}

		if (!question_type_id.equals("0")) {
			SearchValue += " and question_type_id = ? ";
		}
//	
//	   if(!SearchValue.contains("and") && !professional.equals("")){
//			SearchValue += " and upper(professional) like ? ";
//			System.err.println("parameter search"+SearchValue);
//	
//	    }

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String system_id,
			String degree_id, String professional_id, String course_id, String paper_id, String time,
			String instructions, String question_type_id, String num_questions, String marks_questions) {
		int flag = 0;
		try {
			if (Search != null && !Search.equals("")) {

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
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}
			if (!system_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(system_id));
			}
			if (!degree_id.equals("0") && degree_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(degree_id));
			}
			if (!professional_id.equals("0") && professional_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(professional_id));
			}
			if (!course_id.equals("0") && course_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(course_id));
			}
			if (time != null && !time.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Integer.parseInt(time) + "%");
			}

			if (instructions != null && !instructions.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + instructions.toUpperCase() + "%");
			}

			if (num_questions != null && !num_questions.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Integer.parseInt(num_questions) + "%");
			}

			if (marks_questions != null && !marks_questions.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Integer.parseInt(marks_questions) + "%");
			}

			if (!paper_id.equals("0") && paper_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(paper_id));
			}

			if (!question_type_id.equals("0") && question_type_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(question_type_id));
			}

			/*
			 * if (!status.equals("") && status != null) { flag += 1; stmt.setInt(flag,
			 * Integer.parseInt(status)); }
			 */

		} catch (Exception e) {
		}

		return stmt;
	}

	public List<Map<String, Object>> DataTablePaperLayoutDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String degree_id, String professional_id,
			String course_id, String paper_id, String time, String instructions, String question_type_id,
			String num_questions, String marks_questions, String status, String role) {
		String sd = "";

		if (role.contains("NCISM")) {
			sd = " and pl.system_id != 45 ";
		}
		if (role.contains("NCH")) {
			sd = " and pl.system_id = 45 ";
		}

		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_id, degree_id, professional_id, course_id,
				paper_id, time, instructions, question_type_id, num_questions, marks_questions, status);
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

			if (Search.equals("") && instructions.equals("0") && status == "1") {
				q = "select distinct ROW_NUMBER() OVER(order by pl.instructions ) as ser,  pl.id,pl.time,pl.instructions,pl.num_questions,pl.marks_questions,pl.paper_id,pm.paper,"
						+ "pl.question_type_id,qm.question_type,pl.status,s.system_name,d.degree_name,p.professional,"
						+ "cc.course_name,pl.system_id,pl.degree_id,pl.professional_id,pl.course_id"
						+ " from edu_cc_tb_paper_layout pl\n"
						+ "inner join edu_lms_system_mstr s on s.id = pl.system_id\n"
						+ "inner join edu_lms_degree_mstr d on d.id = pl.degree_id\n"
						+ "inner join edu_cc_tb_professional_mstr p on p.id = pl.professional_id\n"
						+ "inner join edu_lms_course_mstr cc on cc.id = pl.course_id::int\n"
						+ "inner join edu_cc_tb_paper_mstr pm on pm.id = pl.paper_id\n"
						+ "inner join edu_cc_tb_question_type_mstr qm on qm.id = pl.question_type_id\n"
						+ "where pl.status='1'" + sd + SearchValue + "ORDER BY " + orderColunm + " " + orderType
						+ " limit " + pageL + " OFFSET " + startPage;
			} else {
				q = "select distinct ROW_NUMBER() OVER(order by pl.instructions ) as ser, pl.id,pl.time,pl.instructions,pl.num_questions,pl.marks_questions,pl.paper_id,"
						+ "pm.paper,pl.question_type_id,qm.question_type,pl.status,s.system_name,d.degree_name,"
						+ "p.professional,cc.course_name,pl.system_id,pl.degree_id,pl.professional_id,"
						+ "pl.course_id from edu_cc_tb_paper_layout pl\n"
						+ "inner join edu_lms_system_mstr s on s.id = pl.system_id\n"
						+ "inner join edu_lms_degree_mstr d on d.id = pl.degree_id\n"
						+ "inner join edu_cc_tb_professional_mstr p on p.id = pl.professional_id\n"
						+ "inner join edu_lms_course_mstr cc on cc.id = pl.course_id::int\n"
						+ "inner join edu_cc_tb_paper_mstr pm on pm.id = pl.paper_id\n"
						+ "inner join edu_cc_tb_question_type_mstr qm on qm.id = pl.question_type_id\n"
						+ "where pl.status='" + status + "'" + sd + SearchValue + "ORDER BY " + orderColunm + " "
						+ orderType + " limit " + pageL + " OFFSET " + startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, system_id, degree_id, professional_id, course_id, paper_id,
					time, instructions, question_type_id, num_questions, marks_questions);
			System.err.println("stmt--->" + stmt);
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();

			int columnCount = metaData.getColumnCount();
			int j = startPage;

			int countFunction = 1;
			int countFunctionDelete = 1;

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

				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDpaper_layout' value='ADD' title='Edit Data' >"
						+ "<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='apIdAGE" + countFunction
						+ "' value=" + rs.getString("id") + ">" + "<input type='hidden' id='aps" + countFunction
						+ "' value=" + rs.getString("system_id") + ">" + "<input type='hidden' id='apd" + countFunction
						+ "' value=" + rs.getString("degree_id") + ">" + "<input type='hidden' id='app" + countFunction
						+ "' value=" + rs.getString("professional_id") + ">" + "<input type='hidden' id='apc"
						+ countFunction + "' value=" + rs.getString("course_id") + ">"
						+ "<input type='hidden' id='appaperAGE" + countFunction + "' value=" + rs.getString("paper_id")
						+ ">" + "<input type='hidden' id='aptimeAGE" + countFunction + "' value=" + rs.getString("time")
						+ ">" + "<input type='hidden' id='apinsAGE" + countFunction + "' value="
						+ rs.getString("instructions") + ">" + "<input type='hidden' id='apqtypeAGE" + countFunction
						+ "' value=" + rs.getString("question_type_id") + ">" + "<input type='hidden' id='apnumqAGE"
						+ countFunction + "' value=" + rs.getString("num_questions") + ">"
						+ "<input type='hidden' id='apmarqAGE" + countFunction + "' value="
						+ rs.getString("marks_questions") + ">" + "<input type='hidden' id='apstatusAGE" + countFunction
						+ "' value=" + rs.getString("status") + "></i></a> </li>";

				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >"
						+ "<input type='hidden' id='deleteID" + countFunctionDelete + "' value=" + rs.getString("id")
						+ "><i class='lni lni-trash-can'></i></a> </li>";

				ul += f + " " + f1;
				ul += "</ul>";

				action = ul;
				countFunction += 1;
				countFunctionDelete += 1;
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

	public EDU_CC_TB_PAPER_LAYOUT getPaperLayoutByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		EDU_CC_TB_PAPER_LAYOUT updateid = (EDU_CC_TB_PAPER_LAYOUT) session.get(EDU_CC_TB_PAPER_LAYOUT.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}

	public String updatePaperLayout(EDU_CC_TB_PAPER_LAYOUT obj) {
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();

		String msg = "";
		try {
			String hql = "update EDU_CC_TB_PAPER_LAYOUT set system_id=:system_id,degree_id=:degree_id,"
					+ "course_id=:course_id,professional_id=:professional_id,paper_id=:paper_id,time=:time,instructions=:instructions,"
					+ "question_type_id=:question_type_id,num_questions=:num_questions,marks_questions=:marks_questions,status=:status,modified_by=:modified_by,modified_date=:modified_date"
					+ " where id=:id";

			Query query = session1.createQuery(hql).setParameter("system_id", obj.getSystem_id())
					.setParameter("degree_id", obj.getDegree_id())
					.setParameter("professional_id", obj.getProfessional_id())
					.setParameter("course_id", obj.getCourse_id()).setParameter("paper_id", obj.getPaper_id())
					.setParameter("time", obj.getTime()).setParameter("instructions", obj.getInstructions())
					.setParameter("question_type_id", obj.getQuestion_type_id())
					.setParameter("num_questions", obj.getNum_questions())
					.setParameter("marks_questions", (obj.getMarks_questions())).setParameter("status", obj.getStatus())
					.setParameter("modified_by", obj.getModified_by())
					.setParameter("modified_date", obj.getModified_date()).setParameter("id", obj.getId());

			msg = query.executeUpdate() > 0 ? "1" : "0";
			tx.commit();
		} catch (Exception e) {
			msg = "Data Not Updated";
			tx.rollback();
		} finally {
			session1.close();
		}
		return msg;
	}
}