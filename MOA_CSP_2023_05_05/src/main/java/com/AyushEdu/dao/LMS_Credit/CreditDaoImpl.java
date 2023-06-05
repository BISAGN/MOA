package com.AyushEdu.dao.LMS_Credit;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CreditDaoImpl implements CreditDao {
	
	@Autowired
	private DataSource dataSource;
	
	public ArrayList<ArrayList<String>> GetTotalDuration(String course) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		
		String q = "";
	
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			String qry = "";
	
				q = "SELECT  DISTINCT  ((TO_DATE(end_date::text, 'YYYY/MM/DD')-TO_DATE(start_date::text, 'YYYY/MM/DD')))+1 ||' days'  as duration\n"
						+ "from edu_lms_system_course_duration  \n"
						+ "where cd_uniq_id = ?";
			
				stmt = conn.prepareStatement(q);
				stmt.setString(1,course);
				
			    System.err.println("-stmt---shra -----------"+stmt);
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {				
				  
				  
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("duration")); //0
			
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
				}
			}
		}
		return list;
	}
	
	public ArrayList<ArrayList<String>> GetSystemCourse_fetch(String system_id1,String course_id1) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		
		String q = "";
	
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			String qry = "";
	
				q = "SELECT  DISTINCT cd_uniq_id, ((TO_DATE(end_date::text, 'YYYY/MM/DD')-TO_DATE(start_date::text, 'YYYY/MM/DD')))+1 ||' days'  \n"
						+ "as duration\n"
						+ "from edu_lms_system_course_duration where system_id=? and course_id=?";
			
				stmt = conn.prepareStatement(q);
				stmt.setString(1,system_id1);
				stmt.setString(2,course_id1);
				
			    System.err.println("-stmt---shra -----------"+stmt);
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {				
				  
				  
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("cd_uniq_id")); //0
				alist.add(rs.getString("duration")); //1
			
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
				}
			}
		}
		return list;
	}
	
	
	///DataTable
	@Override
	public List<Map<String, Object>> DataTableCreditDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String course, String max_duration,String no_of_days,String point) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, course, max_duration,no_of_days,point);
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

			q=" select id, course, max_duration,no_of_days,point from edu_credit_mstr where id!=0"+ SearchValue +" order by id " + orderType
					+ " limit " + pageL + " OFFSET " + startPage;
			
			
//				q = "select id, course, max_duration,no_of_days,point from edu_credit_mstr"  + SearchValue + " ORDER BY id "
//						+ orderType + " limit " + pageL + " OFFSET " + startPage;

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, course, max_duration,no_of_days,point);
			
			System.err.println("stmt----abovre rs---->  "+stmt);
			
			ResultSet rs = stmt.executeQuery();
			System.err.println("stmt----rrrrrr---->  "+stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

//				String f = "";
//				String action = "";
//				String f1 = "";
//
//				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"
//						+ rs.getString("id") + "','" + rs.getString("module_name") + "','" + rs.getString("status")
//						+ "') }else{ return false;}\"";
//				f = "<i class='fa fa-pencil-square-o '  " + ADD + " title='Edit Data'></i>";
//
//				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
//						+ rs.getString("id") + "') }else{ return false;}\"";
//				f1 = "<i class='fa fa-trash '  " + ADD1 + " title='Delete Data'></i>";
//
//				action = f + " " + f1;
//				columns.put("action", action);

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
		
		System.err.println("list"+list);
		return list;
	}

	@Override
	public long DataTableCreditDataTotalCount(String Search, String course, String max_duration,String no_of_days,String point) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, course, max_duration,no_of_days,point);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
				
//			q = "select count(*) \n" + " from edu_lms_module_mstr a where id!=0 " + SearchValue;
			
			q = "select count(*) from (select id, course, max_duration,no_of_days,point from edu_credit_mstr where id!=0" +SearchValue+ ")a where id!=0 ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, course, max_duration,no_of_days,point);
			System.err.println("stmt---count---->    "+stmt);
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
	public String GenerateQueryWhereClause_SQL(String Search, String course, String max_duration,String no_of_days,String point) {
		//System.err.println("policy cat --2--"+policycategory);
		
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(course) like ? or upper(max_duration) like ? \n"
					+ "or upper(no_of_days) like ? or upper(point) like ?)";
			System.err.println("globalllll search" + SearchValue);

		}
		
		/// advance search


		if (!course.equals("")) {
			SearchValue += " and upper(course) like ? ";

		}
		if (!max_duration.trim().equals("")) {
			SearchValue += " and upper(max_duration) like ? ";

		}
		if (!no_of_days.trim().equals("")) {
			SearchValue += " and upper(no_of_days) like ? ";

		}
		if (!point.trim().equals("")) {
			SearchValue += " and upper(point) like ? ";

		}


		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String course, String max_duration,String no_of_days,String point) {
		//System.err.println("policy cat ---3-"+policycategory);
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


			System.err.println("course"+course);
			
			if (!course.equals("") ) {
				flag += 1;
				stmt.setString(flag, "%" + course.toUpperCase() + "%");
			}
			if (!max_duration.equals("") ) {
				flag += 1;
				stmt.setString(flag, "%" + max_duration.toUpperCase() + "%");
			}
			if (!no_of_days.equals("") ) {
				flag += 1;
				stmt.setString(flag, "%" + no_of_days.toUpperCase() + "%");
			}
			if (!point.equals("") ) {
				flag += 1;
				stmt.setString(flag, "%" + point.toUpperCase() + "%");
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
