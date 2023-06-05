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

import com.AyushEdu.Models.LMS_Master.TB_SET_MASTER;

@Repository
public class Set_MasterDAOImpl implements Set_MasterDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public long DataTablesetmasterDataTotalCount(String Search, String setname, String term_id, String prof_name) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, setname, term_id, prof_name);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) \n"
					+ " from (select sm.id,sm.setname,sm.prof_name,tm.term from edu_lms_set_mstr sm\r\n"
					+ "inner join edu_lms_term_mstr tm on tm.id = sm.term_id::integer\r\n" + "where sm.status='1'"
					+ SearchValue + ") a where id!=0 ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, setname, term_id, prof_name);
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
	public String GenerateQueryWhereClause_SQL(String Search, String setname, String term_id, String prof_name) {
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(sm.setname) like ? or upper(sm.prof_name) like ? or upper(tm.term) like ? )";
		}

		if (!setname.trim().equals("")) {
			SearchValue += " and upper(sm.setname) like ? ";
		}

		if (!term_id.equals("0") && term_id != null && !term_id.equals(" ")) {

			SearchValue += " and sm.term_id = ? ";
		}
//		if (!prof_name.trim().equals("")) {
//			SearchValue += " and upper(sm.prof_name) like ? ";
//		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String setname,
			String term_id, String prof_name) {
		int flag = 0;
		try {
			if (Search != null && !Search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}

			if (!setname.equals("") && setname != null) {
				flag += 1;
				stmt.setString(flag, "%" + setname.toUpperCase() + "%");
			}

			if (!term_id.equals("0") && term_id != null) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(term_id));
			}
//			if (!prof_name.equals("") && prof_name != null) {
//				flag += 1;
//				stmt.setString(flag, "%" + prof_name.toUpperCase() + "%");
//			}

		} catch (Exception e) {
		}

		return stmt;
	}

	public TB_SET_MASTER getSetmasterByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		TB_SET_MASTER updateid = (TB_SET_MASTER) session.get(TB_SET_MASTER.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}

	public List<Map<String, Object>> DataTablesetmasterDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String setname, String status, String term_id, String prof_name) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, setname, term_id, prof_name);
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

			if (Search.equals("") && setname.equals("0") && status == "1") {
				q = "select ROW_NUMBER() OVER(order by sm.setname ASC) as ser,sm.id,sm.setname,tm.prof_name,tm.term from edu_lms_set_mstr sm\r\n"
						+ "inner join edu_lms_term_mstr tm on tm.id = sm.term_id::integer\r\n" + "where sm.status='1'"
						+ status + "'" + SearchValue + " ORDER BY setname " + orderType + " limit " + pageL + " OFFSET "
						+ startPage;
			} else {
				q = "select ROW_NUMBER() OVER(order by sm.setname ASC) as ser,sm.id,sm.id,sm.setname,tm.prof_name,tm.term from edu_lms_set_mstr sm\r\n"
						+ "inner join edu_lms_term_mstr tm on tm.id = sm.term_id::integer\r\n" + "where sm.status='"
						+ status + "'" + SearchValue + " ORDER BY setname " + orderType + " limit " + pageL + " OFFSET "
						+ startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, setname, term_id, prof_name);

			ResultSet rs = stmt.executeQuery();
System.err.println("----------stmt prof "+stmt);
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

				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f = "<li><a class='main-btn active-btn btn-hover btn-sm EditSet' value='ADD' title='Edit Data' >" + // id='id_add_attHospital1'
						"<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='EditId" + countFunction + "' value="
						+ rs.getString("id") + "></i></a> </li>";

				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm deleteSet' value='ADD' title='Delete Data' >" + // id='id_add_attHospital1'
						"<i class='lni lni-trash-can'>" + "<input type='hidden' id='DSetId" + countFunctionDelete
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

	public TB_SET_MASTER getsetmasterByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		TB_SET_MASTER updateid = (TB_SET_MASTER) session.get(TB_SET_MASTER.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}

}
