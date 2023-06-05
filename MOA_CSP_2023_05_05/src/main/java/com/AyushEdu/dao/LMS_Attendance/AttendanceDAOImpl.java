package com.AyushEdu.dao.LMS_Attendance;

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

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class AttendanceDAOImpl implements AttendanceDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public List<Map<String, Object>> DataTableAttendanceDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String staff_name, String atten_date) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, staff_name,atten_date);
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


				q=" select id,staff_name,TO_CHAR(atten_date , 'DD-MON-YYYY') as atten_date from tb_lms_attendance_mster"+ SearchValue +" order by id "  + orderType
							+ " limit " + pageL + " OFFSET " + startPage;

			PreparedStatement stmt = conn.prepareStatement(q);
	
			stmt = setQueryWhereClause_SQL(stmt, Search, staff_name,atten_date);
			ResultSet rs = stmt.executeQuery();
			System.err.println("stmt------main-----"+stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
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

//				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"
//						+ rs.getString("id") + "','" + rs.getString("staff_name") + "','" + rs.getString("atten_date")
//						+ "') }else{ return false;}\"";
//				f = "<i class='fa fa-pencil-square-o '  " + ADD + " title='Edit Data'></i>";

//				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
//						+ rs.getString("id") + "') }else{ return false;}\"";
//				f1 = "<i class='fa fa-trash '  " + ADD1 + " title='Delete Data'></i>";

				action = f + " " + f1;
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
	public long DataTableAttendanceDataTotalCount(String Search, String staff_name, String atten_date) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, staff_name,atten_date);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
				
//			q = "select count(*) \n" + " from tb_lms_attendance_mster a where id!=0 " + SearchValue;
			q = "select count(*) \n" + " from (select distinct id,staff_name,TO_CHAR(atten_date , 'DD-MON-YYYY') as date from tb_lms_attendance_mster "+SearchValue+") a ";
			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, staff_name,atten_date);
System.err.println("stmt---count---------"+stmt);
			
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
	public String GenerateQueryWhereClause_SQL(String Search, String staff_name,String atten_date) {
		//System.err.println("policy cat --2--"+policycategory);
		
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " where (  upper(staff_name) like ? )";
			System.err.println("globalllll search" + SearchValue);

		}
		
		

		/// advance search

		if (!staff_name.trim().equals("")) {
			SearchValue += " where upper(staff_name) like ? ";
			System.err.println("parameter search" + SearchValue);

		}
		if(SearchValue.contains("where")) {
			if (!atten_date.equals("") && atten_date != "" && !atten_date.equals("DD/MM/YYYY")){

				SearchValue += " and atten_date= ?::TIMESTAMP";
			}
		}else {
			if (!atten_date.equals("") && atten_date != "" && !atten_date.equals("DD/MM/YYYY")){

				SearchValue += " where atten_date= ?::TIMESTAMP";
			}
		}
		


//		if (!atten_date.trim().equals("")) {
//			SearchValue += " and upper(atten_date) like ? ";
//			System.err.println("parameter search" + SearchValue);
//
//		}
		

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String staff_name,String atten_date) {
		int flag = 0;
		try {
			if (Search != null && !Search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}

			if (!staff_name.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + staff_name.toUpperCase() + "%");
			}
			if (!atten_date.equals("") && atten_date != "" && !atten_date.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, atten_date);
			}



			/*
			 * if (!status.equals("") && status != null) { flag += 1; stmt.setInt(flag,
			 * Integer.parseInt(status)); }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}


}
