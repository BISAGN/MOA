package com.AyushEdu.dao.Degree_recognition_mstr;


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

import com.AyushEdu.Models.Degree_recognition_mstr.DG_REC_UNIVERSITY_CODE_MSTR;


@Repository

public  class Degree_reco_university_code_mstr_impl implements Degree_reco_university_code_mstr_Dao{

	@Autowired
	private DataSource dataSource;
	@Autowired
	private SessionFactory sessionFactory;
	
	

	public List<Map<String, Object>> DataTableuniversity_codeDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String state, String name_of_university, String university_id,String status) {

		String SearchValue = GenerateQueryWhereClause_SQL(search,state, name_of_university, university_id, status);
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

			if(search.equals("") && state.equals("0") && name_of_university.equals("0") && university_id.equals("0") &&status=="1") {
				q = "select id,state,name_of_university,university_id,status from dg_rec_university_code_mstr where status='1'"  + SearchValue + " ORDER BY id " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}else {
			q = "select id,state,name_of_university,university_id,status from dg_rec_university_code_mstr where status='1'" + SearchValue + " ORDER BY id " + orderType + " limit "
					+ pageL + " OFFSET " + startPage;
			}
	
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, search, state,name_of_university,university_id,status);
		
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
				
			
				String f = "";
				String action = "";
//				String f1 = "";
//				
//				
//				String ul="";
//				ul+="<ul class='buttons-group mainbtn action daobtn'>";
//				
//				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDDesignation' value='ADD' title='Edit Data' >" 
//								+"<i class='lni lni-pencil-alt'>"
//								+"<input type='hidden' id='apIdAGE"+countFunction+"' value="+rs.getString("id")+">"
//								+"<input type='hidden' id='approfAGE"+countFunction+"' value="+rs.getString("designation")+">"
//								+"<input type='hidden' id='apstatusAGE"+countFunction+"' value="+rs.getString("status")+"></i></a> </li>";
//				
//				 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >" 
//						 +"<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("id")+"><i class='lni lni-trash-can'></i></a> </li>";
//
//	
//				ul+=f + " " + f1 ;
//				ul+="</ul>";
//				
//				action = ul;
//				countFunction+=1;
//				countFunctionDelete+=1;
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
	public long DataTableuniversity_codeDataTotalCount(String search, String state, String name_of_university,
			String university_id ,String status) {
	
		String SearchValue = GenerateQueryWhereClause_SQL(search,state, name_of_university, university_id, status);

		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			q = "select count(*) from (select id, state,name_of_university,university_id from dg_rec_university_code_mstr  where status='1'\n"
					+ SearchValue + ") a  ";

			PreparedStatement stmt = conn.prepareStatement(q);

			 stmt = setQueryWhereClause_SQL(stmt, search,state, name_of_university, university_id, status);
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
	public String GenerateQueryWhereClause_SQL(String search, String state, String name_of_university,
			String university_id ,String status) {
		String SearchValue = "";
		
		if (search!=null && !search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(state) like ? )";
		}
		if (search!=null && !search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(name_of_university) like ? )";
		}
		if (search!=null && !search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(university_id) like ? )";
		}
		
		
		
		if (state != null && !state.equals("")) {
			SearchValue += " and upper(state) like ? ";

		}
		if (name_of_university != null && !name_of_university.equals("")) {
			SearchValue += " and upper(name_of_university) like ? ";
		}
		if (university_id != null && !university_id.equals("")) {
			SearchValue += " and upper(university_id) like ? ";
		}
		
		
		return SearchValue;
	
	}
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String search, String state, String name_of_university,
			String university_id ,String status) {
		int flag = 0;
		try {

			if (search != null && !search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
			}


			if (search.equals("") &&  state != null && !state.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + state.toString() + "%");
			}

			if (search.equals("") &&  name_of_university != null && !name_of_university.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + name_of_university.toString() + "%");
			}
			if (search.equals("") &&  university_id != null && !university_id.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + university_id.toString() + "%");
			}

		} catch (Exception e) {
		}

		return stmt;
	}
	
}
