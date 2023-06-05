package com.AyushEdu.dao.Examination;

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

import com.AyushEdu.Models.Examination.EXAM_RESULT_STATUS;

@Repository

public class student_result_DaoImpl implements student_result_Dao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public long DataTableStudentresultTotalCount(String Search, String result_status,String status) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, result_status);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			if (Search.equals("") && result_status.equals("") && status == "1") {
			q = "select count(*) from (select id,result_status,status  from edu_exam_tb_result_status_mstr where id!=0 and status='1' \n"
					+ SearchValue + ") ab  ";
			}else {
				q = "select count(*) from (select id,result_status,status  from edu_exam_tb_result_status_mstr where id!=0 and status='"
						+  status + "'" + SearchValue + ") ab  ";
			}
			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, result_status);

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
	public String GenerateQueryWhereClause_SQL(String Search, String result_status) {
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(result_status) like ? )";
			// System.err.println("globalllll search"+SearchValue);
		}

		/// advance search
//		if( professional != null && !professional.equals("")) {
//			SearchValue += " and upper(attempt) like ? ";
//		
//	     }
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String result_status) {
		int flag = 0;
		try {
			if (Search != null && !Search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}

			if (result_status != null && !result_status.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + result_status.toUpperCase() + "%");
			}

		} catch (Exception e) {
		}

		return stmt;
	}

	public List<Map<String, Object>> DataTableStudentresultList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String result_status, String status) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, result_status);
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

			if (Search.equals("") && result_status.equals("") && status == "1") {
				q = "select distinct ROW_NUMBER() OVER(order by result_status ) as sr_no, id,result_status,status from edu_exam_tb_result_status_mstr where status='1'"
						+ SearchValue + " ORDER BY result_status " + orderType + " limit " + pageL + " OFFSET "
						+ startPage;
			} else {
				q = "select distinct ROW_NUMBER() OVER(order by result_status ) as sr_no,id,result_status,status from edu_exam_tb_result_status_mstr where status='"
						+ status + "'" + SearchValue + " ORDER BY result_status " + orderType + " limit " + pageL
						+ " OFFSET " + startPage;
			}

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, result_status);
			System.err.println("stmt" + stmt);
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

				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDResult_Status' value='ADD' title='Edit Data' >"
						+ "<i class='lni lni-pencil-alt'>" + "<input type='hidden' id='apIdPAP" + countFunction
						+ "' value='" + rs.getString("id") + "'>" + "<input type='hidden' id='approfPAP" + countFunction
						+ "' value='" + rs.getString("result_status") + "'>" + "<input type='hidden' id='apstatusPAP"
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

	public EXAM_RESULT_STATUS getResult_statustByid(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		EXAM_RESULT_STATUS updateid = (EXAM_RESULT_STATUS) session.get(EXAM_RESULT_STATUS.class, id);
		session.getTransaction().commit();
		session.close();
		return updateid;
	}

	@Override
	public String update_result_status(EXAM_RESULT_STATUS obj) {
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();

		String msg = "";
		try {
			String hql = "update EXAM_RESULT_STATUS set result_status=:result_status,status=:status,modified_by=:modified_by,modified_date=:modified_date"
					+ " where id=:id";

			Query query = session1.createQuery(hql).setParameter("result_status", obj.getResult_status())
					.setParameter("status", (obj.getStatus())).setParameter("modified_by", obj.getModified_by())
					.setParameter("modified_date", obj.getModified_date()).setParameter("id", obj.getId());

//			msg = query.executeUpdate() > 0 ? "Data Updated Successfully" :"Data Not Updated";
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
