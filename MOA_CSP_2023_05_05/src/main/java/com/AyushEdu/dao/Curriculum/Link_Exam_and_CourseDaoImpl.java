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

import com.AyushEdu.Models.Curriculum.CC_TB_LINK_EXAM_AND_COURSE;

@Repository
public class Link_Exam_and_CourseDaoImpl implements Link_Exam_and_CourseDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Map<String, Object>> DataTableLink_Exam_and_CourseDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String degree_id, String professional_id,
			String course_id, String term_id, String exam_type_id, String no_of_exam, String status, String role) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_id, degree_id, professional_id, course_id,
				term_id, exam_type_id, no_of_exam);

		String sd = "";

		if (role.contains("NCISM")) {
			sd = " and p.system_id != 45 ";
		}
		if (role.contains("NCH")) {
			sd = " and p.system_id = 45 ";
		}
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

			if (Search.equals("") && status == "1") {
				q = "select distinct ROW_NUMBER() OVER(order by s.system_name ) as ser, p.id,s.system_name,d.degree_name,pm.professional,cc.course_name,t.term,e.exam_type,"
						+ "p.no_of_exam,p.status,p.system_id,p.degree_id,p.professional_id,p.course_id,p.exam_type_id,p.term_id\n"
						+ "from edu_cc_tb_link_exam_and_course p\n"
						+ "inner join edu_lms_system_mstr s on s.id = p.system_id\n"
						+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
						+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
						+ "inner join edu_lms_course_mstr cc on cc.id = p.course_id::int\n"
						+ "inner join edu_cc_tb_i3_term_mstr t on t.id = p.term_id\n"
						+ "inner join edu_cc_tb_exam_type_mstr e on e.id = p.exam_type_id\n" + "where p.status='1' "
						+ sd + SearchValue + "ORDER BY " + orderColunm + " " + orderType + " limit " + pageL
						+ " OFFSET " + startPage;
			} else {
				q = "select distinct ROW_NUMBER() OVER(order by s.system_name ) as ser, p.id,s.system_name,d.degree_name,pm.professional,cc.course_name,t.term,e.exam_type,"
						+ "p.no_of_exam,p.status,p.system_id,p.degree_id,p.professional_id,p.course_id,p.exam_type_id,p.term_id\n"
						+ "from edu_cc_tb_link_exam_and_course p\n"
						+ "inner join edu_lms_system_mstr s on s.id = p.system_id\n"
						+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
						+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
						+ "inner join edu_lms_course_mstr cc on cc.id = p.course_id::int\n"
						+ "inner join edu_cc_tb_i3_term_mstr t on t.id = p.term_id\n"
						+ "inner join edu_cc_tb_exam_type_mstr e on e.id = p.exam_type_id\n" + "where p.status='"
						+ status + "'" + sd + SearchValue + "ORDER BY " + orderColunm + " " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, system_id, degree_id, professional_id, course_id, term_id,
					exam_type_id, no_of_exam);
			System.err.println("stmt----Topic---------------------------->  " + stmt);
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			int countFunction = 1;
			int countFunctionDelete = 1;
			int countview = 1;

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String f = "";
				String action = "";
				String f1 = "";
				String vd = "";

				String ul = "";
				ul += "<ul class='buttons-group mainbtn action daobtn'>";

				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDLinkExamCourse' value='ADD' title='Edit Data' >"
						+ "<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='apId" + countFunction
						+ "' value=" + rs.getString("id") + ">" + "<input type='hidden' id='aps" + countFunction
						+ "' value=" + rs.getString("system_id") + ">" + "<input type='hidden' id='apd" + countFunction
						+ "' value=" + rs.getString("degree_id") + ">" + "<input type='hidden' id='app" + countFunction
						+ "' value=" + rs.getString("professional_id") + ">" + "<input type='hidden' id='apc"
						+ countFunction + "' value=" + rs.getString("course_id") + ">" + "<input type='hidden' id='apt"
						+ countFunction + "' value=" + rs.getString("term_id") + ">" + "<input type='hidden' id='ape"
						+ countFunction + "' value=" + rs.getString("exam_type_id") + ">"
						+ "<input type='hidden' id='apne" + countFunction + "' value=" + rs.getString("no_of_exam")
						+ ">" + "<input type='hidden' id='apstatus" + countFunction + "' value="
						+ rs.getString("status") + "></i></a> </li>";

				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >"
						+ "<input type='hidden' id='deleteID" + countFunctionDelete + "' value=" + rs.getString("id")
						+ "><i class='lni lni-trash-can'></i></a> </li>";

				ul += f + " " + f1;
				ul += "</ul>";

				countFunction += 1;
				countFunctionDelete += 1;
				countview += 1;

				action = ul;
				columns.put("vd", vd);
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

	@Override
	public long DataTableLink_Exam_and_CourseDataTotalCount(String Search, String system_id, String degree_id,
			String professional_id, String course_id, String term_id, String exam_type_id, String no_of_exam,String status,
			String role) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_id, degree_id, professional_id, course_id,
				term_id, exam_type_id, no_of_exam);

		String sd = "";

		if (role.contains("NCISM")) {
			sd = " and p.system_id != 45 ";
		}
		if (role.contains("NCH")) {
			sd = " and p.system_id = 45 ";
		}
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			if(Search.equals("") && system_id.equals("0") && status=="1") {
			q = "select count(*) from (select  p.id,s.system_name,d.degree_name,pm.professional,cc.course_name,t.term,"
					+ "e.exam_type,p.no_of_exam,p.status,p.system_id,p.degree_id,p.professional_id,p.course_id,p.exam_type_id,p.term_id\n"
					+ "from edu_cc_tb_link_exam_and_course p\n"
					+ "inner join edu_lms_system_mstr s on s.id = p.system_id\n"
					+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
					+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
					+ "inner join edu_lms_course_mstr cc on cc.id = p.course_id::int\n"
					+ "inner join edu_cc_tb_i3_term_mstr t on t.id = p.term_id\n"
					+ "inner join edu_cc_tb_exam_type_mstr e on e.id = p.exam_type_id\n" + "where p.status='1' \n" + sd
					+ SearchValue + ") ab  ";
			}else {
				q = "select count(*) from (select  p.id,s.system_name,d.degree_name,pm.professional,cc.course_name,t.term,"
						+ "e.exam_type,p.no_of_exam,p.status,p.system_id,p.degree_id,p.professional_id,p.course_id,p.exam_type_id,p.term_id\n"
						+ "from edu_cc_tb_link_exam_and_course p\n"
						+ "inner join edu_lms_system_mstr s on s.id = p.system_id\n"
						+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
						+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
						+ "inner join edu_lms_course_mstr cc on cc.id = p.course_id::int\n"
						+ "inner join edu_cc_tb_i3_term_mstr t on t.id = p.term_id\n"
						+ "inner join edu_cc_tb_exam_type_mstr e on e.id = p.exam_type_id\n" + "where p.status='"+ status +"' \n" + sd
						+ SearchValue + ") ab  ";
			}

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, system_id, degree_id, professional_id, course_id, term_id,
					exam_type_id, no_of_exam);
			System.err.println("stmt---Topic---------count---->    " + stmt);
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
			String professional_id, String course_id, String term_id, String exam_type_id, String no_of_exam) {

		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (upper(s.system_name) like ?or upper(d.degree_name) like ? "
					+ "or upper(pm.professional) like ?  or upper(cc.course_name) like ? or upper(t.term) like ? "
					+ " or upper(e.exam_type ) like ? or cast(p.no_of_exam as character varying) like ?)";
		}
		/// advance search
		if (!system_id.equals("0")) {
			SearchValue += " and p.system_id =? ";
		}
		if (!degree_id.equals("0")) {
			SearchValue += " and p.degree_id =? ";
		}
		if (!professional_id.equals("0")) {
			SearchValue += " and p.professional_id =? ";
		}
		if (!course_id.equals("0")) {
			SearchValue += " and p.course_id =? ";
		}
		if (!term_id.equals("0")) {
			SearchValue += " and p.term_id =? ";
		}
		if (!exam_type_id.equals("0")) {
			SearchValue += " and p.exam_type_id =? ";
		}
		if (!no_of_exam.equals("")) {
			SearchValue += " and p.no_of_exam =? ";
		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String system_id,
			String degree_id, String professional_id, String course_id, String term_id, String exam_type_id,
			String no_of_exam) {
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
				stmt.setString(flag, Search + "%");
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
			if (!term_id.equals("0") && term_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(term_id));
			}
			if (!exam_type_id.equals("0") && exam_type_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(exam_type_id));
			}
			if (!no_of_exam.equals("") && no_of_exam != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(no_of_exam));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}

	@Override
	public String updateLink_Exam_and_Course(CC_TB_LINK_EXAM_AND_COURSE obj) {
		// TODO Auto-generated method stub
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();

		String msg = "";
		try {
			String hql = "update CC_TB_LINK_EXAM_AND_COURSE set system_id=:system_id,degree_id=:degree_id,professional_id=:professional_id, "
					+ " course_id=:course_id,term_id=:term_id,exam_type_id=:exam_type_id, no_of_exam=:no_of_exam,status=:status,modified_by=:modified_by, "
					+ "modified_date=:modified_date where id=:id ";

			Query query = session1.createQuery(hql).setParameter("system_id", obj.getSystem_id())
					.setParameter("degree_id", obj.getDegree_id())
					.setParameter("professional_id", obj.getProfessional_id())
					.setParameter("course_id", obj.getCourse_id()).setParameter("term_id", obj.getTerm_id())
					.setParameter("exam_type_id", obj.getExam_type_id()).setParameter("no_of_exam", obj.getNo_of_exam())
					.setParameter("status", (obj.getStatus())).setParameter("modified_by", obj.getModified_by())
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
