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

import com.AyushEdu.Models.LMS_Master.TB_LMS_MARITAL_STATUS_MSTR;

@Repository
public class Marital_StatusDaoImpl implements Marital_StatusDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public List<Map<String, Object>> DataTablemarital_statusDataList1(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String marital_status, String status) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, marital_status);
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

			q = "select id,marital_status from edu_lms_marital_status_mstr where status='1' " + SearchValue
					+ " ORDER BY marital_status " + orderType + " limit " + pageL + " OFFSET " + startPage;

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, marital_status);
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int countFunction=1;
			int countFunctionDelete=1;
			int j = startPage;
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String f = "";
				String action = "";
				String f1 = "";
				
				
				String ul="";
				ul+="<ul class='buttons-group mainbtn action daobtn'>";
				
				String ADD = "onclick=\" if () ){editData('"+ rs.getString("id") +"') }else{ return false;}\"";
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm Editmarital_status' value='ADD' title='Edit Data'>"+ //id='id_add_attHospital1'
								"<i class='lni lni-pencil-alt'>"
								+ "<input type='hidden' id='apIdAGE"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
				
				String ADD1 = "onclick=\" if () ){deleteData('"+ rs.getString("id") + "') }else{ return false;}\"";
				 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm deletemarital_status' value='ADD' title='Delete Data'>"+ //id='id_add_attHospital1'
						 "<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("id")+"><i class='lni lni-trash-can'></i></a> </li>";

				
				ul+=f + " " + f1 ;
				ul+="</ul>";
				
				action = ul;
				countFunction+=1;
				countFunctionDelete+=1;
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
	public long DataTablemarital_statusDataTotalCount1(String Search, String marital_status) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, marital_status);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) \n"
					+ " from (select id,marital_status from edu_lms_marital_status_mstr where status='1' " + SearchValue
					+ ") a where id!=0 ";
			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, marital_status);
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
	public String GenerateQueryWhereClause_SQL(String Search, String marital_status) {
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and ( upper(marital_status) like ? )";
			System.err.println("globalllll search" + SearchValue);
		}

		if (!marital_status.trim().equals("")) {
			SearchValue += " and upper(marital_status) like ? ";
		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String marital_status) {
		int flag = 0;
		try {
			if (Search != null && !Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}
			if (!marital_status.equals("") && marital_status != null) {
				flag += 1;
				stmt.setString(flag, "%" + marital_status.toUpperCase() + "%");
			}
		} catch (Exception e) {
		}
		return stmt;
	}

	public TB_LMS_MARITAL_STATUS_MSTR getmarital_statusByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		TB_LMS_MARITAL_STATUS_MSTR updateid = (TB_LMS_MARITAL_STATUS_MSTR) session.get(TB_LMS_MARITAL_STATUS_MSTR.class,
				id);
		session.getTransaction().commit();
		session.close();
		return updateid;

	}

}
