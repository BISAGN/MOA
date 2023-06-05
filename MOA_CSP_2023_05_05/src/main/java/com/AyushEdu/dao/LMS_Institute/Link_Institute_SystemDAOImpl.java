package com.AyushEdu.dao.LMS_Institute;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Link_Institute_SystemDAOImpl implements Link_Institute_SystemDAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;
	
	public ArrayList<ArrayList<String>> DataTableLink_Institute_SystemDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, HttpSession sessionUserId,String institute_id) {
		String pageL = "";
		if (pageLength == -1) {
			pageL = "ALL";
		} else {
			pageL = String.valueOf(pageLength);
		}

		String SearchValue = GenerateQueryWhereCandiList(Search,institute_id);

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();

			q = "select ir.institute_name,isl.institute_id,string_agg(sm.system_name, ', ') as system_name\n"
					+ "from edu_lms_institute_link isl\n"
					+ "inner join edu_lms_institute_reg ir on ir.id=isl.institute_id::int\n"
					+ "inner join edu_lms_system_mstr sm on sm.id=isl.system_id::int\n"
					+ "where isl.id!=0 "+SearchValue 
					+ "  group by 1,2 ";

			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereCandiList(stmt, Search,institute_id);
			ResultSet rs = stmt.executeQuery();

			int i = 1;
			while (rs.next()) {
				// alist....arrange icon column wise......by ruler
				ArrayList<String> alist = new ArrayList<String>();

				String f = "";
				String action = "";
				String f1 = "";
				String f2 = "";
				String f3 = "";
				
								alist.add(rs.getString("institute_name"));//0
								alist.add(rs.getString("system_name"));//1

//				 alist.add("<a href="+rs.getString("url")+" class=\"\">"+rs.getString("url")+"</a> "); //0
			//	alist.add(action);
				

				i++;
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
					e.printStackTrace();
				}
			}
		}
		return list;
	}





	public long DataTableLink_Institute_SystemDataTotalCount(String Search, HttpSession sessionUserId,String institute_id) {
	//	String  role = session.getAttribute("role").toString();
		String SearchValue = GenerateQueryWhereCandiList(Search,institute_id);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();


q="select count(*) from (select ir.institute_name,isl.institute_id,string_agg(sm.system_name, ', ') as system_name\n"
		+ "from edu_lms_institute_link isl\n"
		+ "inner join edu_lms_institute_reg ir on ir.id=isl.institute_id::int\n"
		+ "inner join edu_lms_system_mstr sm on sm.id=isl.system_id::int " + SearchValue + " \n"
		+ " group by 1,2 )ab " ;		

        PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereCandiList(stmt, Search,institute_id);
			System.err.println("stmt=====count====="+stmt);
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
					e.printStackTrace();
				}
			}
		}
		return (long) total;
	}
	 
	
	
	public String GenerateQueryWhereCandiList(String Search,String institute_id) {
		String SearchValue = "";
		
		
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and ( upper(ir.institute_name) like ? or upper(sm.system_name) like ? )";
		}

		if (!institute_id.equals("0") && institute_id != null) {

			SearchValue += " and isl.institute_id like ? ";
		}
			
		return SearchValue;
	}

	public PreparedStatement setQueryWhereCandiList(PreparedStatement stmt,String Search,String institute_id) {
		int flag = 0;
		try {
			
			if (Search != "" && Search != null) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				

			}
			if (!institute_id.equals("0") ) {
				flag += 1;
				stmt.setString(flag, "%" + institute_id.toUpperCase() + "%");
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;

	}
	
	
	
}
