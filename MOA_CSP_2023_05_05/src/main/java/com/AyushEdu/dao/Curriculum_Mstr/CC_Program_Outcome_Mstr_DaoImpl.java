package com.AyushEdu.dao.Curriculum_Mstr;

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
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PROGRAM_OUTCOME_MSTR;

@Repository
public class CC_Program_Outcome_Mstr_DaoImpl implements CC_Program_Outcome_Mstr_Dao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Map<String, Object>> DataTableProgram_OutcomeDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String code, String program_outcome, String status,
			String degree_id, String professional_id, String course_id, String role) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_id, code, program_outcome, status, degree_id,
				professional_id, course_id);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

			String sd = "";

			if (role.contains("NCISM")) {
				sd = " and po.system_id != 45 ";
			}
			if (role.contains("NCH")) {
				sd = " and po.system_id = 45 ";
			}

			conn = dataSource.getConnection();
			String pageL = "";
			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			if (Search.equals("") && program_outcome.equals("0") && status == "1") {
				q = "select distinct ROW_NUMBER() OVER(order by program_outcome ) as ser,po.id,po.code,po.program_outcome,po.status, po.system_id,s.system_name,d.degree_name,pm.professional,po.degree_id,po.professional_id,cm.course_name,po.course_id \n"
						+ "from edu_cc_tb_program_outcome_mstr po\n"
						+ "inner join edu_lms_degree_mstr d on d.id = po.degree_id\n"
						+ "inner join edu_cc_tb_professional_mstr pm on pm.id = po.professional_id\n"
						+ "inner join edu_lms_course_mstr cm on cm.id=po.course_id \n"
						+ "inner join edu_lms_system_mstr s on s.id= po.system_id where po.status='1'" + sd
						+ SearchValue + " ORDER BY " + orderColunm + " " + orderType + " limit " + pageL + " OFFSET "
						+ startPage;
			} else {
				q = "select distinct ROW_NUMBER() OVER(order by program_outcome ) as ser,po.id,po.code,po.program_outcome,po.status, po.system_id,s.system_name,d.degree_name,pm.professional,po.degree_id,po.professional_id,cm.course_name,po.course_id\n"
				  + "from edu_cc_tb_program_outcome_mstr po\n" 
				  +"inner join edu_lms_degree_mstr d on d.id = po.degree_id\n" 
				  +"inner join edu_cc_tb_professional_mstr pm on pm.id = po.professional_id\n" 
				  +"inner join edu_lms_course_mstr cm on cm.id=po.course_id\n" 
				  +"inner join edu_lms_system_mstr s on s.id= po.system_id where po.status='" + status +"'" + sd + SearchValue + " ORDER BY "+orderColunm+" " + orderType + " limit " 
				  + pageL + " OFFSET " + startPage;
				 
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, system_id, code, program_outcome, status, degree_id,
					professional_id, course_id);
			System.err.println("queryyyyyyyyyyyyyyyy================" + stmt);
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

				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDProgram_Outcome' value='ADD' title='Edit Data' >"
						+ "<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='apIdAGE" + countFunction
						+ "' value='" + rs.getString("id") + "'>" + "<input type='hidden' id='approfsys" + countFunction
						+ "' value='" + rs.getString("system_id") + "'>" + "<input type='hidden' id='hprodegree"
						+ countFunction + "' value='" + rs.getString("degree_id") + "' >"
						+ "<input type='hidden' id='hidprofessional" + countFunction + "' value='"
						+ rs.getString("professional_id") + "' >" + "<input type='hidden' id='apcoofCO" + countFunction
						+ "' value='" + rs.getString("course_id") + "'>" + "<input type='hidden' id='apcodeAGE"
						+ countFunction + "' value='" + rs.getString("code") + "'>"
						+ "<input type='hidden' id='approfAGE" + countFunction + "' value='"
						+ rs.getString("program_outcome") + "'>" + "<input type='hidden' id='apstatusAGE"
						+ countFunction + "' value='" + rs.getString("status") + "'></i></a> </li>";

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

	@SuppressWarnings("null")
	public String GenerateQueryWhereClause_SQL(String Search, String system_id, String code, String program_outcome,
			String status, String degree_id, String professional_id, String course_id) {
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (upper(s.system_name) like ? or upper(code) like ? or upper(program_outcome) like ?"
					+ "or upper(d.degree_name) like ? or upper(pm.professional) like ? or upper(cm.course_name) like ?)";
		}

		if (system_id != null && !system_id.equals("0")) {
			SearchValue += " and po.system_id  = ? ";
		}
		if (code != null && !code.equals("")) {
			SearchValue += " and code like ? ";
		}
		if (program_outcome != null && !program_outcome.equals("")) {
			SearchValue += " and upper(program_outcome) like ? ";
		}
		if (degree_id != null && !degree_id.equals("0")) {
			SearchValue += " and (po.degree_id ) = ? ";

		}
		if (professional_id != null && !professional_id.equals("0")) {
			SearchValue += " and (po.professional_id ) = ? ";

		}
		if (!course_id.trim().equals("0")) {
			SearchValue += " and ( po.course_id )= ? ";
		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String system_id,
			String code, String program_outcome, String status, String degree_id, String professional_id,
			String course_id) {
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

			}
			if (system_id != null && !system_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(system_id));
			}
			if (code != null && !code.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + code.toUpperCase() + "%");
			}
			if (program_outcome != null && !program_outcome.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + program_outcome.toUpperCase() + "%");
			}
			if (degree_id != null && !degree_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(degree_id));
			}
			if (professional_id != null && !professional_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(professional_id));
			}
			if (!course_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(course_id));
			}
		} catch (Exception e) {
		}
		return stmt;
	}

	@Override
	public long DataTableProgram_OutcomeDataTotalCount(String Search, String system_id, String code,
			String program_outcome, String status, String degree_id, String professional_id,
			String course_id,String role) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_id, code, program_outcome, status, degree_id,
				professional_id, course_id);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {

			String sd = "";

			if (role.contains("NCISM")) {
				sd = " and po.system_id != 45 ";
			}
			if (role.contains("NCH")) {
				sd = " and po.system_id = 45 ";
			}

			conn = dataSource.getConnection();

			if (Search.equals("") && program_outcome.equals("0") && status == "1") {
				q = "select count(*) from (select distinct ROW_NUMBER() OVER(order by program_outcome ) as ser,po.id,po.code,po.program_outcome,po.status, po.system_id,s.system_name,d.degree_name,pm.professional,po.degree_id,po.professional_id,cm.course_name,po.course_id \n"
						+ "from edu_cc_tb_program_outcome_mstr po\n"
						+ "inner join edu_lms_degree_mstr d on d.id = po.degree_id\n"
						+ "inner join edu_cc_tb_professional_mstr pm on pm.id = po.professional_id\n"
						+ "inner join edu_lms_course_mstr cm on cm.id=po.course_id \n"
						+ "inner join edu_lms_system_mstr s on s.id= po.system_id where po.status='1'  \n" + sd
						+ SearchValue + ") ab  ";
			} else {
				q = "select count(*) from (select distinct ROW_NUMBER() OVER(order by program_outcome ) as ser,po.id,po.code,po.program_outcome,po.status, po.system_id,s.system_name,d.degree_name,pm.professional,po.degree_id,po.professional_id,cm.course_name,po.course_id \n"
						+ "from edu_cc_tb_program_outcome_mstr po\n"
						+ "inner join edu_lms_degree_mstr d on d.id = po.degree_id\n"
						+ "inner join edu_cc_tb_professional_mstr pm on pm.id = po.professional_id\n"
						+ "inner join edu_lms_course_mstr cm on cm.id=po.course_id \n"
						+ "inner join edu_lms_system_mstr s on s.id= po.system_id where po.id!=0 and po.status='"
						+ status + "' \n" + sd + SearchValue + ") ab  ";
			}

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, system_id, code, program_outcome, status, degree_id,professional_id,course_id);
			System.err.println("count===========================" + stmt);
//		//	System.err.println("STMT--------count-----------"+stmt);
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

	@Override
	public String updateProgram_Outcome(CC_TB_PROGRAM_OUTCOME_MSTR obj) {
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		String msg = "";
		try {
			String hql = "update CC_TB_PROGRAM_OUTCOME_MSTR set system_id=:system_id, code=:code,program_outcome=:program_outcome,status=:status,degree_id=:degree_id,"
					+ " professional_id=:professional_id,course_id=:course_id,modified_by=:modified_by,modified_date=:modified_date"
					+ " where id=:id";

			Query query = session1.createQuery(hql).setParameter("system_id", obj.getSystem_id())
					.setParameter("code", obj.getCode()).setParameter("program_outcome", obj.getProgram_outcome())
					.setParameter("status", (obj.getStatus())).setParameter("degree_id", obj.getDegree_id())
					.setParameter("professional_id", obj.getProfessional_id())
					.setParameter("course_id", obj.getCourse_id()).setParameter("modified_by", obj.getModified_by())
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
