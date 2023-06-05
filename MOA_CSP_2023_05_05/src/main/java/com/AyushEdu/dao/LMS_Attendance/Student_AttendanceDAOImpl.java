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
public class Student_AttendanceDAOImpl implements Student_AttendanceDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Map<String, Object>> DataTableSearch_Student_AttenDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String name,String date) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, name,date);
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

			q=" select distinct id, name,TO_CHAR(date::TIMESTAMP , 'DD-MON-YYYY') as date from edu_lms_student_attendance"+ SearchValue +" order by name " + orderType
					+ " limit " + pageL + " OFFSET " + startPage;	

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, name, date);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

				String f = "";
				String action = "";
				String f1 = "";

//				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"
//						+ rs.getString("id") + "','" + rs.getString("module_name") + "','" + rs.getString("status")
//						+ "') }else{ return false;}\"";
//				f = "<i class='fa fa-pencil-square-o '  " + ADD + " title='Edit Data'></i>";
//
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
	public long DataTableSearch_Student_AttenDataTotalCount(String Search, String name,String date) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, name, date);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			q = "select count(*) \n" + " from (select distinct id,name,TO_CHAR(date::TIMESTAMP , 'DD-MON-YYYY') as date from edu_lms_student_attendance "+SearchValue+") a ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, name, date);
			
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
	public String GenerateQueryWhereClause_SQL(String Search, String name,String date) {
		
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " where (upper(name) like ? "
					+ "or to_char(date,'dd-mm-yyyy') like ?)";

		}
		/// advance search

		if (!name.trim().equals("")) {
			SearchValue += " where upper(name) like ? ";
			System.err.println("parameter search" + SearchValue);

		}
		if (!date.equals("") && date != "" && !date.equals("DD/MM/YYYY")){

			SearchValue += " where date= ?";
		}	
		
		return SearchValue;
	}
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String name,String date) {
		int flag = 0;
		try {
			if (Search != null && !Search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");

			}

			if (!name.equals("") && name != null) {
				flag += 1;
				stmt.setString(flag, "%" + name.toUpperCase() + "%");
			}

			if (!date.equals("") && date != "" && !date.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, date);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}
}
