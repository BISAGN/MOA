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

import com.AyushEdu.Models.LMS_Master.EDU_LMS_TERM_MASTER;

@Repository
public class TermDaoImpl implements TermDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Map<String, Object>> DataTabletermDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String term, String prof_name,String status) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, term,prof_name);
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
			if (Search.equals("") && term.equals("") && prof_name.equals("") && status == "1") {

				q = "	select ROW_NUMBER() OVER(order by term ASC) as ser,id, term,prof_name,status from edu_lms_term_mstr\n" + " where status= '1' \n" + " ORDER BY term "
						+ orderType + " limit " + pageL + " OFFSET " + startPage;
			} else {
				q = "	select  ROW_NUMBER() OVER(order by term ASC) as ser,id, term,prof_name,status from edu_lms_term_mstr\n" + " where status= '" + status + "'"
						+ SearchValue + " ORDER BY term " + orderType + " limit " + pageL + " OFFSET " + startPage;
			}
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, term,prof_name);
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;

			int countEditFunction = 1;
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
				f = "<li><a class='main-btn active-btn btn-hover btn-sm addEdit'  title='Edit Data'>"
						+ "<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='apdAGE" + countEditFunction
						+ "' value=" + rs.getString("id") + "></i></a> </li>";

				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' title='Delete Data'>"
						+ "<i class='lni lni-trash-can'>" + "<input type='hidden' id='deleteID" + countFunctionDelete
						+ "' value=" + rs.getString("id") + "></i></a> </li>";

				ul += f + " " + f1;
				ul += "</ul>";

				action = ul;
				columns.put("action", action);
				countEditFunction += 1;
				countFunctionDelete += 1;

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
	public long DataTabletermDataTotalCount(String Search, String term,String prof_name) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, term,prof_name);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (	select id, term,prof_name,status from edu_lms_term_mstr\n" + " where status='1' "
					+ SearchValue + ")a where id!=0 ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, term,prof_name);

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
	public String GenerateQueryWhereClause_SQL(String Search, String term,String prof_name) {
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(term) like ? )";
		}

		if (!term.trim().equals("")) {
			SearchValue += " and upper(term) like ? ";
		}
		
		if (!prof_name.trim().equals("")) {
			SearchValue += " and upper(prof_name) like ? ";
		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String term,String prof_name) {
		int flag = 0;
		try {
			if (Search != null && !Search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}
			if (Search != null && !Search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}

			if (!term.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + term.toUpperCase() + "%");
			}
			
			if (!prof_name.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + prof_name.toUpperCase() + "%");
			}


		} catch (Exception e) {
		}
		return stmt;
	}

	public EDU_LMS_TERM_MASTER gettermByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		EDU_LMS_TERM_MASTER updateid = (EDU_LMS_TERM_MASTER) session.get(EDU_LMS_TERM_MASTER.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}

}
