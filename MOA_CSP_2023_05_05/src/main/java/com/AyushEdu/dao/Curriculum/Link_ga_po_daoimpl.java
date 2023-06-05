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
public class Link_ga_po_daoimpl implements Link_ga_po_dao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DataSource dataSource;

	public ArrayList<ArrayList<String>> getGAbyDegree(String degree_id, String user_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select gm.graduate_attributes,dm.degree_name from cc_tb_link_graduate_attribute_and_system gs\n"
					+ "inner join edu_lms_degree_mstr dm on dm.id = gs.degree\n"
					+ "inner join cc_tb_graduate_attributes_mstr gm on gm.id = gs.graduate_attribute\n"
					+ "where dm.id=? ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(degree_id));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("graduate_attributes"));// 0
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

	@Override
	public ArrayList<ArrayList<String>> getPObyDegree(String degree_id, String user_id) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "\n" + "SELECT dm.degree_name,pm.program_outcome FROM public.edu_cc_tb_link_program_outcome_and_system_and_degree psd\n"
					+ "inner join edu_lms_degree_mstr dm on dm.id = psd.degree\n"
					+ "inner join edu_cc_tb_program_outcome_mstr pm on pm.id = psd.program_outcome\n"
					+ "where dm.id=?\n" + "";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(degree_id));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("degree_name"));// 0
				alist.add(rs.getString("program_outcome"));// 1

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

	@Override
	public String updatega_po(String po, String id, String ga, String degree_id) {

		System.err.println("ga---" + ga);
		System.err.println("po---" + po);
		System.err.println("id---" + id);
		System.err.println("degree_id---" + degree_id);

		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();

		String msg = "";
		try {
			String hql = "update EDU_CC_TB_LINK_GA_AND_PO set degree_id=:degree_id,graduateattribute_id=:graduateattribute_id,programoutcome_id=:programoutcome_id,status=:status"
					+ " where id=:id ";

			Query query = session1.createQuery(hql).setParameter("id", Integer.parseInt(id))
					.setParameter("degree_id", Integer.parseInt(degree_id))
					.setParameter("graduateattribute_id", Integer.parseInt(ga))
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
	public List<Map<String, Object>> DataTableGa_PoDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String degree_id, String graduateattribute_id,
			String programoutcome_id, String role,String status) {

		String sd = "";

		if (role.contains("NCISM")) {
			sd = " and p.system_id != 45 ";
		}
		if (role.contains("NCH")) {
			sd = " and p.system_id = 45 ";
		}

		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_id, degree_id, graduateattribute_id,
				programoutcome_id);
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
			System.err.println("status-----------------------"+status);

			if (Search.equals("") && degree_id.equals("0") && graduateattribute_id.equals("0")
					&& programoutcome_id.equals("") && status == "1") {

				q = "select distinct ROW_NUMBER() OVER(order by s.system_name) as ser,string_agg(p.id::character varying, ':') as id,d.degree_name,p.system_id,s.system_name,\n"
						+ "concat(g.code,'-',g.graduate_attributes) as graduate_attributes,string_agg(p.programoutcome_id::character varying, ':') as programoutcome_id,\n"
						+ "	string_agg(po.program_outcome::character varying, ':') as program_outcome,p.degree_id,graduateattribute_id,p.status  from edu_cc_tb_link_ga_and_po p\n"
						+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
						+ "inner join edu_cc_tb_graduate_attributes_mstr g on g.id = p.graduateattribute_id\n"
						+ "inner join edu_cc_tb_program_outcome_mstr po on po.id = p.programoutcome_id "
						+ "inner join edu_lms_system_mstr s on s.id = p.system_id\n" + "where p.status='1'"
						+ sd + SearchValue + "group by 3,4,5,6,9,10,11 " + "ORDER BY " + orderColunm + " "
						+ orderType + " limit \n" + pageL + " OFFSET " + startPage;

			} else {

				q = " select distinct ROW_NUMBER() OVER(order by s.system_name) as ser,string_agg(p.id::character varying, ':') as id,d.degree_name,p.system_id,s.system_name,\n"
						+ "	concat(g.code,'-',g.graduate_attributes) as graduate_attributes,string_agg(p.programoutcome_id::character varying, ':') as programoutcome_id,\n"
						+ "string_agg(po.program_outcome::character varying, ':') as program_outcome,p.degree_id,graduateattribute_id,p.status  from edu_cc_tb_link_ga_and_po p\n"
						+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
						+ "inner join edu_cc_tb_graduate_attributes_mstr g on g.id = p.graduateattribute_id\n"
						+ "inner join edu_cc_tb_program_outcome_mstr po on po.id = p.programoutcome_id \n"
						+ "inner join edu_lms_system_mstr s on s.id = p.system_id where p.status ='" + status + "'" + sd
						+ SearchValue + "group by 3,4,5,6,9,10,11 " + "ORDER BY " + orderColunm + " "
						+ orderType + " limit \n" + pageL + " OFFSET " + startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, system_id, degree_id, graduateattribute_id, programoutcome_id);

			System.err.println("---------stmt---------" + stmt);

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
				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDGa' value='ADD' title='Edit Data' >"
						+ "<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='apId" + countFunction
						+ "' value=" + ids + ">" + "<input type='hidden' id='apsys" + countFunction + "' value="
						+ rs.getString("system_id") + ">" + "<input type='hidden' id='apd" + countFunction + "' value="
						+ rs.getString("degree_id") + ">" + "<input type='hidden' id='apga" + countFunction + "' value="
						+ rs.getString("graduateattribute_id") + ">" + "<input type='hidden' id='appo" + countFunction
						+ "' value=" + rs.getString("programoutcome_id") + ">" + "<input type='hidden' id='apstatus"
						+ countFunction + "' value=" + rs.getString("status") + "></i></a> </li>";

				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >"
						+ "<input type='hidden' id='deleteID" + countFunctionDelete + "' value=" + ids
						+ "><i class='lni lni-trash-can'></i></a> </li>";

				vd = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn btn-hover btn-sm btnview  VIEWdetails' value='ADD' title='View Data' >\n"
						+ "<i class='lni lni-eye'> <input type='hidden' id='viewId" + countview + "' value="
						+ rs.getString("programoutcome_id") + "></i></a> </li></ul>";

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
	public long DataTableGa_Po_DataTotalCount(String Search, String system_id, String degree_id,
			String graduateattribute_id, String programoutcome_id, String role,String status) {

		String sd = "";

		if (role.contains("NCISM")) {
			sd = " and p.system_id != 45 ";
		}
		if (role.contains("NCH")) {
			sd = " and p.system_id = 45 ";
		}

		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_id, degree_id, graduateattribute_id,
				programoutcome_id);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

//			q = "select count(*) from ( select string_agg(p.id::character varying, ':') as id,d.degree_name,p.system_id,s.system_name,\\n\"\n"
//					+ "	g.graduate_attributes,string_agg(p.programoutcome_id::character varying, ':') as programoutcome_id,\n"
//					+ "	string_agg(po.program_outcome::character varying, ':') as program_outcome,degree_id,graduateattribute_id,p.status  from edu_cc_tb_link_ga_and_po p\n"
//					+ "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
//					+ "inner join edu_cc_tb_graduate_attributes_mstr g on g.id = p.graduateattribute_id\n"
//					+ "inner join edu_cc_tb_program_outcome_mstr po on po.id = p.programoutcome_id \n"
//					+ "inner join edu_lms_system_mstr s on s.id = p.system_id\n"
//					+ "where p.status = '1'  \n"
//					+ SearchValue + "\n"
//					+ "group by 2,3,4,5,8,9,10) ab ";
			if (Search.equals("") && degree_id.equals("0") && graduateattribute_id.equals("0")
					&& programoutcome_id.equals("") && status == "1") {
				q = "select count(*) from (select string_agg(p.id::character varying, ':') as id,d.degree_name,p.system_id,s.system_name,\n"
						+ "concat(g.code,'-',g.graduate_attributes) as graduate_attributes,string_agg(p.programoutcome_id::character varying, ':') as programoutcome_id,\n"
						+ "string_agg(po.program_outcome::character varying, ':') as program_outcome,p.degree_id,graduateattribute_id,p.status"
						+ " from edu_cc_tb_link_ga_and_po p\n" + "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
						+ "inner join edu_cc_tb_graduate_attributes_mstr g on g.id = p.graduateattribute_id \n"
						+ "inner join edu_cc_tb_program_outcome_mstr po on po.id = p.programoutcome_id \n"
						+ "inner join edu_lms_system_mstr s on s.id = p.system_id where p.status =1" + sd
						+ SearchValue + "\n" + "group by 2,3,4,5,8,9,10) ab  ";
			}else {
				q = "select count(*) from (select string_agg(p.id::character varying, ':') as id,d.degree_name,p.system_id,s.system_name,\n"
						+ "concat(g.code,'-',g.graduate_attributes) as graduate_attributes,string_agg(p.programoutcome_id::character varying, ':') as programoutcome_id,\n"
						+ "string_agg(po.program_outcome::character varying, ':') as program_outcome,p.degree_id,graduateattribute_id,p.status"
						+ " from edu_cc_tb_link_ga_and_po p\n" + "inner join edu_lms_degree_mstr d on d.id = p.degree_id\n"
						+ "inner join edu_cc_tb_graduate_attributes_mstr g on g.id = p.graduateattribute_id \n"
						+ "inner join edu_cc_tb_program_outcome_mstr po on po.id = p.programoutcome_id \n"
						+ "inner join edu_lms_system_mstr s on s.id = p.system_id where p.status='"+ status +"'  \n" + sd
						+ SearchValue + "\n" + "group by 2,3,4,5,8,9,10) ab  ";
			}
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, system_id, degree_id, graduateattribute_id, programoutcome_id);

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
			String graduateattribute_id, String programoutcome_id) {
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and ( upper(s.system_name) like ? or upper(d.degree_name) like ? or concat(g.code,'-',g.graduate_attributes) like ? or upper(po.program_outcome) like ? )";
			// System.err.println("global search"+SearchValue);
		}

		// advance search-->>
		if (!system_id.trim().equals("0")) {
			SearchValue += " and p.system_id = ? ";
		}
		if (!degree_id.trim().equals("0")) {
			SearchValue += " and p.degree_id = ? ";
		}
		if (!graduateattribute_id.trim().equals("0")) {
			SearchValue += " and p.graduateattribute_id = ? ";
		}
		if (!programoutcome_id.equals("0") && !programoutcome_id.equals("")) {
			SearchValue += " and p.programoutcome_id = ? ";
		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String system_id,
			String degree_id, String graduateattribute_id, String programoutcome_id) {
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
			}
			if (!system_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(system_id));
			}
			if (!degree_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(degree_id));
			}
			if (!graduateattribute_id.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(graduateattribute_id));
			}
			if (!programoutcome_id.equals("0") && !programoutcome_id.equals("")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(programoutcome_id));
			}
		} catch (Exception e) {
		}
		return stmt;
	}

}
