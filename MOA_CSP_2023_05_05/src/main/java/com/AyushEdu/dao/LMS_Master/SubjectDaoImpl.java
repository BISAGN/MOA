package com.AyushEdu.dao.LMS_Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_SUBJECT_MSTR;

@Repository
public class SubjectDaoImpl implements SubjectDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory rc) {
		this.sessionFactory = rc;
	}

	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Map<String, Object>> search_Subject_name(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system, String course, String subject, String status) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, system, course, subject);
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

			if (Search.equals("") && subject.equals("") && status == "1") {

				q = " select d.id as id,d.subject_name,b.system_name,cm.course_name,d.status from edu_lms_subject_mstr d \n"
						+ "left join edu_lms_system_mstr b on b.id = d.system_id \n"
						+ "left join edu_lms_ele_course_mstr e on e.course_name = d.course_id \n"
						+ "inner join edu_lms_course_mstr cm on cm.id=e.course_name::int \n" + " where d.status='1' "
						+ SearchValue + " order by  d.subject_name " + orderType + " limit " + pageL + " OFFSET "
						+ startPage;
			} else {
				q = " select d.id as id,d.subject_name,b.system_name,cm.course_name,d.status from edu_lms_subject_mstr d \n"
						+ "left join edu_lms_system_mstr b on b.id = d.system_id \n"
						+ "left join edu_lms_ele_course_mstr e on e.course_name = d.course_id \n"
						+ "inner join edu_lms_course_mstr cm on cm.id=e.course_name::int \n" + "where d.status='"
						+ status + "' " + SearchValue + " order by  d.subject_name " + orderType + " limit " + pageL
						+ " OFFSET " + startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, system, course, subject);
			System.err.println("stmt-----hhhhh-----" + stmt);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String f = "";
				String action = "";
				String f1 = "";

				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"
						+ rs.getString("id") + "','" + rs.getString("subject_name") + "','" + rs.getString("status")
						+ "') }else{ return false;}\"";
				f = "<i class='fa fa-pencil-square-o '  " + ADD + " title='Edit Data'></i>";

				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<i class='fa fa-trash '  " + ADD1 + " title='Delete Data'></i>";

				action = f + " " + f1;
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

	public long DataTablesubjectDataTotalCount(HttpSession sessionUserId, String Search, String system, String course,
			String subject) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, system, course, subject);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select d.id as id,d.subject_name,b.system_name,cm.course_name,d.status from edu_lms_subject_mstr d \n"
					+ "left join edu_lms_system_mstr b on b.id = d.system_id \n"
					+ "left join edu_lms_ele_course_mstr e on e.course_name = d.course_id \n"
					+ "inner join edu_lms_course_mstr cm on cm.id=e.course_name::int \n" + "where d.status='1'  "
					+ SearchValue + ") ab  ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, system, course, subject);
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
					e.printStackTrace();
				}
			}
		}
		return (long) total;

	}

	@SuppressWarnings("null")
	public String GenerateQueryWhereClause_SQL(String Search, String system, String course, String subject) {

		String SearchValue = "";

		if (!Search.equals("") && Search != null) { // for Input Filter
			SearchValue += " and ( upper(b.system_name) like ? or upper(course_name) like ? or upper(subject_name) like ?)";
		}

		if (!system.equals("0") && system != "0") {
			SearchValue += " and d.system_id = ? ";
		}
		if (!course.equals("0") && course != "0") {
			SearchValue += " and d.course_id = ? ";
		}
		if (!subject.trim().equals("")) {
			SearchValue += " and upper(d.subject_name) like ? ";
		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String system,
			String course, String subject) {
		int flag = 0;
		try {
			if (!Search.equals("") && Search != null) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}

			if (!system.equals("0") && system != "0") {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(system));
			}
			if (!course.equals("0") && course != "0") {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(course));
			}
			if (!subject.trim().equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + subject.toUpperCase() + "%");
			}

		} catch (Exception e) {
		}

		return stmt;
	}

	@Override
	public EDU_LMS_SUBJECT_MSTR getSubjectByid(int id) {
		Session sessionHQL = sessionFactory.getSessionFactory().openSession();
		@SuppressWarnings("unused")
		Transaction tx = sessionHQL.beginTransaction();
		EDU_LMS_SUBJECT_MSTR updateid = (EDU_LMS_SUBJECT_MSTR) sessionHQL.get(EDU_LMS_SUBJECT_MSTR.class, id);
		sessionHQL.getTransaction().commit();
		sessionHQL.close();
		return updateid;
	}

}
