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

@Repository
public class Link_Co_and_Po_DaoImpl implements Link_Co_and_Po_Dao {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DataSource dataSource;

	@Override
	public String updatega_po(String po, String id, String co, String degree_id, String system_id) {

		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();

		String msg = "";
		try {
			String hql = "update CC_TB_LINK_COURSE_OUTCOME_TO_PROGRAM_OUTCOME_CHILD set programoutcome_id=:programoutcome_id,status=:status"
					+ " where id=:id ";

			Query query = session1.createQuery(hql).setParameter("id", Integer.parseInt(id))
					.setParameter("programoutcome_id", Integer.parseInt(po)).setParameter("status", 1);

			msg = query.executeUpdate() > 0 ? "1" : "0";
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Data Not Updated";
			tx.rollback();
		} finally {
			session1.close();
		}
		return msg;
	}

	@Override
	public List<Map<String, Object>> DataTableCo_PoDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String degree_id, String professional_id,
			String course_id, String course_outcome_id, String programoutcome_name, String role) {

		String sd = "";

		if (role.contains("NCISM")) {
			sd = " and op.system_id != 45 ";
		}
		if (role.contains("NCH")) {
			sd = " and op.system_id = 45 ";
		}

		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_id, degree_id, professional_id, course_id,
				course_outcome_id, programoutcome_name);
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

			q = "select distinct ROW_NUMBER() OVER(order by degree_name ) as ser, op.id,degree_name,professional,course_name,concat(co.co_code,'-',co.course_outcome) as course_outcome,op.system_id,s.system_name,\n"
					+ "op.degree_id,op.course_outcome_id,op.professional_id,op.course_id,op.status,\n"
					+ "string_agg(cast(oc.programoutcome_id as character varying),',') as poid,\n"
					+ "string_agg(program_outcome,',') as po \n"
					+ "from edu_cc_tb_link_course_outcome_and_program_outcome_parent op\n"
					+ "inner join edu_cc_tb_link_course_outcome_and_program_outcome_child oc on oc.p_id=op.id \n"
					+ "inner join edu_lms_degree_mstr dm on dm.id=op.degree_id\n"
					+ "inner join edu_cc_tb_professional_mstr p on p.id= op.professional_id\n"
					+ "inner join edu_lms_course_mstr c on c.id = op.course_id\n"
					+ "inner join edu_cc_tb_add_course_outcome_mstr co on co.id=op.course_outcome_id\n"
					+ "inner join edu_cc_tb_program_outcome_mstr po on po.id= oc.programoutcome_id \n"
					+ "inner join edu_lms_system_mstr s on s.id = op.system_id\n"
					+ "where op.status=0 and oc.status=0\n" + sd + SearchValue + " group by 2,3,4,5,6,7,8,9,10,11,12,13"
					+ " ORDER BY " + orderColunm + " " + orderType + " limit " + pageL + " OFFSET " + startPage;

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, system_id, degree_id, professional_id, course_id,
					course_outcome_id, programoutcome_name);

			System.err.println("---------stmt---------" + stmt);
			System.err.println("-------------------------------------------" + professional_id);

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
				String ids = rs.getString("id");
				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDCo' value='ADD' title='Edit Data' >"
						+ "<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='apId" + countFunction
						+ "' value=" + ids + ">" + "<input type='hidden' id='apsys" + countFunction + "' value="
						+ rs.getString("system_id") + ">" + "<input type='hidden' id='apd" + countFunction + "' value="
						+ rs.getString("degree_id") + ">" + "<input type='hidden' id='apco" + countFunction + "' value="
						+ rs.getString("course_outcome_id") + ">" + "<input type='hidden' id='appi" + countFunction
						+ "' value=" + rs.getString("professional_id") + ">" + "<input type='hidden' id='apcid"
						+ countFunction + "' value=" + rs.getString("course_id") + ">" + "<input type='hidden' id='appo"
						+ countFunction + "' value=" + rs.getString("poid") + "></i></a> </li>";

				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >"
						+ "<input type='hidden' id='deleteID" + countFunctionDelete + "' value=" + ids
						+ "><i class='lni lni-trash-can'></i></a> </li>";

				vd = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn btn-hover btn-sm btnview  VIEWdetails' value='ADD' title='View Data' >\n"
						+ "		<i class='lni lni-eye'> <input type='hidden' id='viewId" + countview + "' value="
						+ rs.getString("poid") + "></i></a> </li></ul>";

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
	public long DataTableCo_Po_DataTotalCount(String Search, String system_id, String degree_id, String professional_id,
			String course_id, String course_outcome_id, String programoutcome_name, String role) {

		String sd = "";

		if (role.contains("NCISM")) {
			sd = " and op.system_id != 45 ";
		}
		if (role.contains("NCH")) {
			sd = " and op.system_id = 45 ";
		}

		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_id, degree_id, professional_id, course_id,
				course_outcome_id, programoutcome_name);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select distinct op.id,degree_name,professional,course_name,co.course_outcome,op.system_id,s.system_name,\n"
					+ "op.degree_id,op.course_outcome_id,op.professional_id,op.course_id,op.status,\n"
					+ "string_agg(cast(oc.programoutcome_id as character varying),',') as poid,\n"
					+ "string_agg(program_outcome,',') as po \n"
					+ "from edu_cc_tb_link_course_outcome_and_program_outcome_parent op\n"
					+ "inner join edu_cc_tb_link_course_outcome_and_program_outcome_child oc on oc.p_id=op.id \n"
					+ "inner join edu_lms_degree_mstr dm on dm.id=op.degree_id\n"
					+ "inner join edu_cc_tb_professional_mstr p on p.id= op.professional_id\n"
					+ "inner join edu_lms_course_mstr c on c.id = op.course_id\n"
					+ "inner join edu_cc_tb_add_course_outcome_mstr co on co.id=op.course_outcome_id\n"
					+ "inner join edu_cc_tb_program_outcome_mstr po on po.id= oc.programoutcome_id \n"
					+ "inner join edu_lms_system_mstr s on s.id = op.system_id\n"
					+ "where op.status=0 and oc.status=0 \n" + sd + SearchValue + "\n" + " group by 1,2,3,4,5,6,7)ab \n"
					+ "";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, system_id, degree_id, professional_id, course_id,
					course_outcome_id, programoutcome_name);

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
			String professional_id, String course_id, String course_outcome_id, String programoutcome_name) {
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (upper(s.system_name) like ? or upper(dm.degree_name) like ? or  upper(p.professional) like ? or upper(c.course_name) like ? or concat(co.co_code,'-',co.course_outcome) like ?  )";
			// System.err.println("global search"+SearchValue);
		}

		// advance search-->>
		if (!system_id.trim().equals("0")) {
			SearchValue += " and op.system_id = ? ";
		}
		if (!degree_id.trim().equals("0")) {
			SearchValue += " and op.degree_id = ? ";
		}
		if (!professional_id.equals("0")) {
			SearchValue += " and op.professional_id =? ";
		}
		if (!course_id.equals("0")) {
			SearchValue += " and op.course_id =? ";
		}
		if (!course_outcome_id.trim().equals("0")) {
			SearchValue += " and op.course_outcome_id = ? ";
		}
		if (!programoutcome_name.equals("0") && !programoutcome_name.equals("")) {
			SearchValue += " and oc.programoutcome_id = ? ";
		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String system_id,
			String degree_id, String professional_id, String course_id, String course_outcome_id,
			String programoutcome_name) {
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
			if (!system_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(system_id));
			}
			if (!degree_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(degree_id));
			}
			if (!professional_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(professional_id));
			}
			if (!course_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(course_id));
			}
			if (!course_outcome_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(course_outcome_id));
			}
			if (!programoutcome_name.equals("0") && !programoutcome_name.equals("")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(programoutcome_name));
			}
		} catch (Exception e) {
		}
		return stmt;
	}
}
