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
import com.AyushEdu.Models.Curriculum.EDU_CC_TB_EVALUATION_METHOD_PA;

@Repository
public class Evaluation_method_PADaoImpl implements Evaluation_method_PADao {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Map<String, Object>> DataTableEvalu_Method_PADataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String degree_id, String professional_id,
			String course_id, String evaluation_method_pa_id, String status, String role) {

		String sd = "";

		if (role.contains("NCISM")) {
			sd = " and p.system_id != 45 ";
		}
		if (role.contains("NCH")) {
			sd = " and p.system_id = 45 ";
		}

		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_id, degree_id, professional_id, course_id,
				evaluation_method_pa_id);
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
				q = "select distinct ROW_NUMBER() OVER(order by s.system_name ) as ser, p.id,s.system_name,d.degree_name,pm.professional,cc.course_name,m.evaluation_method_pa,p.status,p.system_id, "
						+ "p.degree_id,p.professional_id,p.course_id,p.evaluation_method_pa_id\n"
						+ "from edu_cc_tb_evaluation_method_pa p\n"
						+ "inner join edu_lms_system_mstr s on s.id = p.system_id\n"
						+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
						+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
						+ "inner join edu_lms_course_mstr cc on cc.id = p.course_id::int\n"
						+ "inner join edu_cc_tb_evaluation_method_pa_mstr m on m.id = p.evaluation_method_pa_id\n"
						+ "where p.status='1' " + sd + SearchValue + "ORDER BY " + orderColunm + " " + orderType
						+ " limit " + pageL + " OFFSET " + startPage;
			} else {
				q = "select distinct ROW_NUMBER() OVER(order by s.system_name ) as ser,p.id,s.system_name,d.degree_name,pm.professional,cc.course_name,m.evaluation_method_pa,p.status,p.system_id, "
						+ "p.degree_id,p.professional_id,p.course_id,p.evaluation_method_pa_id \n"
						+ "from edu_cc_tb_evaluation_method_pa p\n"
						+ "inner join edu_lms_system_mstr s on s.id = p.system_id\n"
						+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
						+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
						+ "inner join edu_lms_course_mstr cc on cc.id = p.course_id::int\n"
						+ "inner join edu_cc_tb_evaluation_method_pa_mstr m on m.id = p.evaluation_method_pa_id\n"
						+ "where p.status='" + status + "'" + sd + SearchValue + "ORDER BY " + orderColunm + " "
						+ orderType + " limit " + pageL + " OFFSET " + startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, system_id, degree_id, professional_id, course_id,
					evaluation_method_pa_id);
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

				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDEvalPa' value='ADD' title='Edit Data' >"
						+ "<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='apId" + countFunction
						+ "' value=" + rs.getString("id") + ">" + "<input type='hidden' id='aps" + countFunction
						+ "' value=" + rs.getString("system_id") + ">" + "<input type='hidden' id='apd" + countFunction
						+ "' value=" + rs.getString("degree_id") + ">" + "<input type='hidden' id='app" + countFunction
						+ "' value=" + rs.getString("professional_id") + ">" + "<input type='hidden' id='apc"
						+ countFunction + "' value=" + rs.getString("course_id") + ">"
						+ "<input type='hidden' id='appid" + countFunction + "' value="
						+ rs.getString("evaluation_method_pa_id") + ">" + "<input type='hidden' id='apstatus"
						+ countFunction + "' value=" + rs.getString("status") + "></i></a> </li>";

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
	public long DataTableEvalu_Method_PADataTotalCount(String Search, String system_id, String degree_id,
			String professional_id, String course_id, String evaluation_method_pa_id,String status, String role) {
		String sd = "";

		if (role.contains("NCISM")) {
			sd = " and p.system_id != 45 ";
		}
		if (role.contains("NCH")) {
			sd = " and p.system_id = 45 ";
		}

		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_id, degree_id, professional_id, course_id,
				evaluation_method_pa_id);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			if (Search.equals("") && status == "1") {
				
			q = "select count(*) from (select p.id,s.system_name,d.degree_name,pm.professional,cc.course_name,m.evaluation_method_pa,\n"
					+ "p.status,p.system_id, p.degree_id,p.professional_id,p.course_id,p.evaluation_method_pa_id \n"
					+ "from edu_cc_tb_evaluation_method_pa p\n"
					+ "inner join edu_lms_system_mstr s on s.id = p.system_id\n"
					+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
					+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
					+ "inner join edu_lms_course_mstr cc on cc.id = p.course_id::int\n"
					+ "inner join edu_cc_tb_evaluation_method_pa_mstr m on m.id = p.evaluation_method_pa_id\n"
					+ "where p.status='1' \n" + sd + SearchValue + ") ab  ";
			}else {
				q = "select count(*) from (select p.id,s.system_name,d.degree_name,pm.professional,cc.course_name,m.evaluation_method_pa,\n"
						+ "p.status,p.system_id, p.degree_id,p.professional_id,p.course_id,p.evaluation_method_pa_id \n"
						+ "from edu_cc_tb_evaluation_method_pa p\n"
						+ "inner join edu_lms_system_mstr s on s.id = p.system_id\n"
						+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
						+ "inner join edu_cc_tb_professional_mstr pm on pm.id = p.professional_id\n"
						+ "inner join edu_lms_course_mstr cc on cc.id = p.course_id::int\n"
						+ "inner join edu_cc_tb_evaluation_method_pa_mstr m on m.id = p.evaluation_method_pa_id\n"
						+ "where p.status='"+ status +"' \n" + sd + SearchValue + ") ab  ";
			}
			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, system_id, degree_id, professional_id, course_id,
					evaluation_method_pa_id);
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
			String professional_id, String course_id, String evaluation_method_pa_id) {

		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (upper(s.system_name) like ? or upper(d.degree_name) like ? "
					+ "or upper(pm.professional) like ?  or upper(cc.course_name) like ? or upper(m.evaluation_method_pa) like ?) ";
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
		if (!evaluation_method_pa_id.equals("0")) {
			SearchValue += " and p.evaluation_method_pa_id =? ";
		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String system_id,
			String degree_id, String professional_id, String course_id, String evaluation_method_pa_id) {
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
			if (!evaluation_method_pa_id.equals("0") && evaluation_method_pa_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(evaluation_method_pa_id));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}

	@Override
	public String updateEvalu_Method_PA(EDU_CC_TB_EVALUATION_METHOD_PA obj) {
		// TODO Auto-generated method stub
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();

		String msg = "";
		try {
			String hql = "update EDU_CC_TB_EVALUATION_METHOD_PA set system_id=:system_id,degree_id=:degree_id,professional_id=:professional_id, "
					+ " course_id=:course_id,evaluation_method_pa_id=:evaluation_method_pa_id,status=:status,modified_by=:modified_by, "
					+ "modified_date=:modified_date where id=:id ";

			Query query = session1.createQuery(hql).setParameter("system_id", obj.getSystem_id())
					.setParameter("degree_id", obj.getDegree_id())
					.setParameter("professional_id", obj.getProfessional_id())
					.setParameter("course_id", obj.getCourse_id())
					.setParameter("evaluation_method_pa_id", obj.getEvaluation_method_pa_id())
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
