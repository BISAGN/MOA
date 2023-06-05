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

import com.AyushEdu.Models.LMS_Master.EDU_LMS_DEGREE_MASTER;

@Repository
public class Degree_Master_DAOimpl implements Degree_Master_DAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	@Override
	public List<Map<String, Object>> DataTableDegree_masterDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String type_of_degree, String degree_name, String semester,String degree_code,
			String status) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, type_of_degree, degree_name, semester,degree_code, status);
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

			q = "  select ROW_NUMBER() OVER(order by dm.id ASC) as sr_no,dm.id,dm.type_of_degree,dm.degree_name,tm.prof_name,dm.degree_code,dm.status,td.type_of_degree from edu_lms_degree_mstr dm\n"
					+ "    inner join edu_lms_type_of_degree_mstr td on td.id=dm.type_of_degree\n"
					+ "     inner join edu_lms_term_mstr tm on tm.id::text=dm.semester::text\n"
					+ "    where dm.status='1' " + SearchValue + " order by id " + orderType + " limit " + pageL
					+ " OFFSET " + startPage;

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, type_of_degree, degree_name, semester,degree_code, status);

			ResultSet rs = stmt.executeQuery();
			System.err.println("-----stmt -- "+stmt);
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
				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDDegree' title='Edit Data'>"
						+ "<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='DegId" + countFunction
						+ "' value=" + rs.getString("id") + ">" + "<input type='hidden' id='DegName" + countFunction
						+ "' value=" + rs.getString("degree_name") + ">" + "<input type='hidden' id='DegStatus"
						+ countFunction + "' value=" + rs.getString("status") + "></i></a></li>";

				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm DeleteDeg' title='Delete Data'>"
						+ "<i class='lni lni-trash-can'>" + "<input type='hidden' id='DEDegId" + countFunctionDelete
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
	public long DataTableDegree_masterDataTotalCount(String Search, String type_of_degree, String degree_name,
			String semester,String degree_code, String status) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, type_of_degree, degree_name, semester,degree_code ,status);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from ( select dm.id,dm.type_of_degree,dm.degree_name,dm.semester,dm.degree_code,dm.status,td.type_of_degree from edu_lms_degree_mstr dm\n"
					+ "    inner join edu_lms_type_of_degree_mstr td on td.id=dm.type_of_degree\n"
					+ "    where dm.status='1'" + SearchValue + ")a";

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, type_of_degree, degree_name, semester,degree_code, status);

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
		System.err.println("total" + total);
		return (long) total;
	}

	@SuppressWarnings("null")
	public String GenerateQueryWhereClause_SQL(String Search, String type_of_degree, String degree_name,
			String semester,String degree_code, String status) {
		String SearchValue = "";

		if (Search != null && Search != "") { // for Input Filter
			SearchValue += " and (upper(dm.degree_name) like ?  or upper(dm.semester) like ? or upper(dm.degree_code) like ? or upper(td.type_of_degree) like ?)";

		}

		/// advance search
		if (!type_of_degree.equals("0")) {
			SearchValue += " and dm.type_of_degree = ? ";

		}

		if (!degree_name.trim().equals("")) {
			SearchValue += " and upper(dm.degree_name) like ? ";

		}

		
		if (!semester.equals("0")) {
			SearchValue += " and upper(dm.semester) like ? ";

		}
		
		if (!degree_code.trim().equals("")) {
			SearchValue += " and upper(dm.degree_code) like ? ";

		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String type_of_degree,
			String degree_name, String semester,String degree_code, String status) {
		int flag = 0;
		try {
			if (Search != null && Search != "") {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}

			if (!type_of_degree.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(type_of_degree));

			}

			if (!degree_name.equals("") && degree_name != null) {
				flag += 1;
				stmt.setString(flag, "%" + degree_name.toUpperCase() + "%");
			}

			if (!semester.equals("0") && semester != null) {
				flag += 1;
				stmt.setString(flag, "%" + semester.toUpperCase() + "%");
			}
			
			if (!degree_code.equals("") && degree_code != null) {
				flag += 1;
				stmt.setString(flag, "%" + degree_code.toUpperCase() + "%");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}

	public EDU_LMS_DEGREE_MASTER get_degreeByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		EDU_LMS_DEGREE_MASTER updateid = (EDU_LMS_DEGREE_MASTER) session.get(EDU_LMS_DEGREE_MASTER.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}
}
