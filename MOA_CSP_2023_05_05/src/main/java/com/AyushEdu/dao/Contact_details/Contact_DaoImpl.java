package com.AyushEdu.dao.Contact_details;

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
import org.springframework.stereotype.Service;

import com.AyushEdu.Models.Teacher_Master.TB_NCH_DEPARTMENT_MSTR;

@Repository
public class Contact_DaoImpl implements Contact_Dao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	

	public long DataTableSearch_contactus_count(String Search, String name, String email, String subject, String message,String date) {

		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, name, email,subject, message, date);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			q="select count(*) from (select name, email,subject,message,to_char(created_date,'DD/MON/YYYY') as date\n"
					+ "FROM edu_contact_details\n"
					+ "where id != 0 ORDER BY id asc limit 10 OFFSET 0\n"
					+ SearchValue + ") ab  ";
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, name, email,subject, message, date);
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
	public String GenerateQueryWhereClause_SQL(String Search, String name, String email, String subject, String message,String date) {
		String SearchValue = "";
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(name) like ? or upper(email) like ? or upper(subject) like ? or upper(message) like ? )";
			//System.err.println("globalllll search"+SearchValue);
		}
		
		///advance search
		if( name != null && !name.equals("")) {
			SearchValue += " and upper(name) like ? ";
	     }
	
		if( email != null && !email.equals("")) {
			SearchValue += " and upper(email) like ? ";
	     }
		
		if( subject != null && !subject.equals("")) {
			SearchValue += " and upper(subject) like ? ";
	     }
		
		if( message != null && !message.equals("")) {
			SearchValue += " and upper(message) like ? ";
	     }

		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String name, String email, String subject, String message,String date) {
		int flag = 0;
		try {
			if (Search!=null &&  !Search.equals("")) {
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
			}
			
			if (name != null && !name.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+name.toUpperCase()+"%");
			}
			if (email != null && !email.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+email.toUpperCase() + "%");
			}
			
			if (subject != null && !subject.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+subject.toUpperCase() + "%");
			}
			
			if (message != null && !message.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+message.toUpperCase() + "%");
			}

		} catch (Exception e) {
		}

		return stmt;
	}
	@Override
	public List<Map<String, Object>> DataTableSearch_contactusList(int startPage, int pageLength, String Search,String orderColunm,
			String orderType, String name, String email, String subject, String message, String date) {
		
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, name, email,subject, message, date);
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

//			if(Search.equals("") && name.equals("0")) {
//				q = "select name, email,subject,message \n"
//						+ "FROM edu_contact_details\n"
//						+ "where id != 0" + ""+ SearchValue +" order by id " + orderType
//						+ 	 " limit " + pageL + " OFFSET " + startPage;
//			}else {
			q = "select name, email,subject,message,to_char(created_date,'DD/MON/YYYY') as date\n"
					+ "FROM edu_contact_details\n"
					+ "where id != 0"  + SearchValue + " ORDER BY id " + orderType + " limit "
					+ pageL + " OFFSET " + startPage;
//			}
	System.err.println("====================="+q);
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, name, email,subject, message, date);
			System.err.println("stmt--->"+stmt);
			ResultSet rs = stmt.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			
			int countFunction=1;
			int countFunctionDelete=1;
			
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}

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
	

}