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

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYS_DEG_MAP_MASTER;

@Repository
public class Sys_Deg_Map_Mstr_DAOimpl implements Sys_Deg_Map_Mstr_DAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	@Override
	public List<Map<String, Object>> DataTablesystem_degree_mappingDataList(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String system_name, String degree_name,
			String status) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_name, degree_name, status);
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

			if (Search.equals("") && degree_name.equals("") && status == "1") {

				q = " select ROW_NUMBER() OVER(order by sys.system_name ASC) as sr_no,sdm.id,drg.degree_name,sys.system_name,sdm.status \n"
						+ " from edu_lms_system_degree_map_mstr sdm\n"
						+ " inner join edu_lms_degree_mstr drg on drg.id = sdm.degree_name::integer\n"
						+ " inner join edu_lms_system_mstr sys on sys.id = sdm.system_name::integer\n"
						+ " where sdm.status='1'  " + SearchValue + " sys.system_name " + orderType + " limit " + pageL
						+ " OFFSET " + startPage;
			} else {
				q = " select ROW_NUMBER() OVER(order by sys.system_name ASC) as sr_no,sdm.id,drg.degree_name,sys.system_name,sdm.status \n"
						+ " from edu_lms_system_degree_map_mstr sdm\n"
						+ " inner join edu_lms_degree_mstr drg on drg.id = sdm.degree_name::integer\n"
						+ " inner join edu_lms_system_mstr sys on sys.id = sdm.system_name::integer\n"
						+ " where sdm.status='" + status + "'  " + SearchValue + " order by sys.system_name " + orderType
						+ " limit " + pageL + " OFFSET " + startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, system_name, degree_name, status);

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

				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){EditData('"
						+ rs.getString("id") + "','" + rs.getString("degree_name") + "','" + rs.getString("status")
						+ "') }else{ return false;}\"";
				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDSys_deg' title='Edit Data'>"
						+ "<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='SysDegId" + countFunction
						+ "' value=" + rs.getString("id") + ">" + "<input type='hidden' id='SysDegName" + countFunction
						+ "' value=" + rs.getString("degree_name") + ">" + "<input type='hidden' id='SysDegStatus"
						+ countFunction + "' value=" + rs.getString("status") + "></i></a> </li>";

				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm DeleteSys_deg' title='Delete Data'>"
						+ "<i class='lni lni-trash-can'>" + "<input type='hidden' id='DESysDegId" + countFunctionDelete
						+ "' value=" + rs.getString("id") + "></i></a> </li>";

				ul += f + " " + f1;
				ul += "</ul>";

				countFunction += 1;
				countFunctionDelete += 1;

				action = ul;

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
	public long DataTablesystem_degree_mappingDataTotalCount(String Search, String system_name, String degree_name,
			String status) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, system_name, degree_name, status);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select sdm.id,drg.degree_name,sys.system_name,sdm.status \n"
					+ " from edu_lms_system_degree_map_mstr sdm \n"
					+ "	 inner join edu_lms_degree_mstr drg on drg.id = sdm.degree_name::integer\n"
					+ "	inner join edu_lms_system_mstr sys on sys.id = sdm.system_name::integer\n"
					+ "	where sdm.status='1'" + SearchValue + ")a where id!=0 ";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, system_name, degree_name, status);
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
	public String GenerateQueryWhereClause_SQL(String Search, String system_name, String degree_name, String status) {
		String SearchValue = "";

		if (Search != null && Search != "") { // for Input Filter
			SearchValue += "and (upper(drg.degree_name) like ? or upper(sys.system_name) like ? )";
			System.err.println("globalllll search" + SearchValue);
		}

		if (!system_name.trim().equals("0")) {
			SearchValue += " and sdm.system_name = ? ";
		}

		if (!degree_name.trim().equals("0")) {
			SearchValue += " and sdm.degree_name = ? ";

		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String system_name,
			String degree_name, String status) {
		int flag = 0;
		try {
			if (Search != null && Search != "") {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}

			if (!system_name.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(system_name));
			}
			if (!degree_name.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(degree_name));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}

	public EDU_LMS_SYS_DEG_MAP_MASTER get_system_degree_mappingByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		EDU_LMS_SYS_DEG_MAP_MASTER updateid = (EDU_LMS_SYS_DEG_MAP_MASTER) session.get(EDU_LMS_SYS_DEG_MAP_MASTER.class,
				id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}

}
