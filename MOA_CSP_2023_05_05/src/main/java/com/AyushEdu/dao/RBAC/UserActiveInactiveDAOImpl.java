package com.AyushEdu.dao.RBAC;

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
import org.springframework.stereotype.Service;

@Service
public class UserActiveInactiveDAOImpl implements UserActiveInactiveDAO {

	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<Map<String, Object>> DataTableSearch_User_ActiveDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String role_id, String username, String loginname, String email_id, String enable_id) {
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, role_id,  username,  loginname,  email_id,enable_id);
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
			
			q = "select ROW_NUMBER() OVER(order by userid ASC) as sr_no,userid,username,email_id,enabled,login_name from logininformation li\n"
					+ "inner join userroleinformation uri on uri.user_id = li.userid\n"
					+"inner join roleinformation ri on ri.role_id = uri.role_id\n"
					+ "where userid is not null"
					+ SearchValue + " ORDER BY userid " + orderType + " limit " + pageL + " OFFSET " + startPage;
				
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, role_id, username,  loginname, email_id,enable_id);
			
			System.err.println("stmt----->    "+stmt);
			
			
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

				
				String ul = "";
				String f = "";
				String action = "";
				String f1 = "";
				
				if (rs.getString("enabled").equals("1")) {
					 f = "<li><a href='#' class='main-btn light-btn btn-hover btninact'>Inactive</a>"
							  +"<input type='hidden' id='InActiveID" + countFunction+ "' value=" + rs.getString("userid") + "></li>";
					 countFunction += 1;
				}
				
				 if (rs.getString("enabled").equals("0")) {
					 f1 = "<li><a href='#' class='main-btn active-btn btn-hover btnactive'>Active</a>"
							  +"<input type='hidden' id='ActiveID" + countFunctionDelete+ "' value=" + rs.getString("userid") + "></li>";
					 countFunctionDelete += 1;
				}
				 
				ul += "<ul class='buttons-group mainbtn action daobtn'>";
				ul += f +""+f1 ;
				ul += "</ul>";
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
	public long DataTableSearch_User_ActiveDataTotalCount(String Search, String role_id, String username, String loginname, String email_id, String enable_id) {
	
		String SearchValue = GenerateQueryWhereClause_SQL(Search, role_id,  username,  loginname,  email_id,enable_id);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select ROW_NUMBER() OVER(order by userid ASC) as sr_no,userid,username,email_id,enabled,login_name from logininformation li\n"
					+ "inner join userroleinformation uri on uri.user_id = li.userid\n"
					+"inner join roleinformation ri on ri.role_id = uri.role_id\n"
					+ "where userid is not null \n"
					+ SearchValue + ") ab  ";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL(stmt, Search, role_id,  username,  loginname,  email_id,enable_id);

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
	public String GenerateQueryWhereClause_SQL(String Search, String role_id, String username, String loginname, String email_id,String enable_id) {
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (upper(username) like ? or upper(login_name) like ? or upper(email_id) like ?)";
		}
		
		if (role_id != null && !role_id.equals("0") && !role_id.equals("")) {
			SearchValue += " and ri.role_id = ? ";
		}
		if (username != null && !username.equals("")) {
			SearchValue += " and upper(li.username) like ? ";
		}
		if (loginname != null && !loginname.equals("")) {
			SearchValue += " and upper(li.login_name) like ? ";
		}
		if (email_id != null && !email_id.equals("")) {
			SearchValue += " and upper(li.email_id) like ? ";
		}
		if (enable_id != null && !enable_id.equals("")) {
			SearchValue += " and li.enabled = ? ";
		}
		
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String role_id, String username, String loginname, String email_id ,String enable_id
			) {
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
				
				if (role_id != null && !role_id.equals("0") && !role_id.equals("")) {
					flag += 1;
					stmt.setInt(flag, Integer.parseInt(role_id));
				}
				if (username != null && !username.equals("")) {
					flag += 1;
					stmt.setString(flag, "%" + username.toUpperCase() + "%");
				}
				if (loginname != null && !loginname.equals("")) {
					flag += 1;
					stmt.setString(flag, "%" + loginname.toUpperCase() + "%");
				}
				if (email_id != null && !email_id.equals("")) {
					flag += 1;
					stmt.setString(flag, "%" + email_id.toUpperCase() + "%");
				}
				if (enable_id != null && !enable_id.equals("")) {
					flag += 1;
					stmt.setInt(flag, Integer.parseInt(enable_id));
				}

			} catch (Exception e) {
		}

		return stmt;
	}
}
