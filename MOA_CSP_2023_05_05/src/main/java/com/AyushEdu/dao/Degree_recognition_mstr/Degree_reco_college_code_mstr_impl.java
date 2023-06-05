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
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Degree_recognition_mstr.DG_REC_COLLEGE_CODE_MSTR;

@Repository

public class Degree_reco_college_code_mstr_impl  implements Degree_reco_college_code_mstr_Dao{
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	public List<Map<String, Object>> DataTablecCollegeDataList(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String name_of_college, String state, String college_id,
			String status) {
		// TODO Auto-generated method stub
		//return null;
		
		String SearchValue = GenerateQueryWhereClause_SQL(search, name_of_college,state,college_id);
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

			if(search.equals("") && name_of_college.equals("0") && state.equals("0") && college_id.equals("0") &&status=="1") {
				q = "select id,name_of_college,state,college_id,status from dg_rec_college_code_mstr where status='1'"  + SearchValue + " ORDER BY id " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
			}else {
			q = "select id,name_of_college,state,college_id,status from dg_rec_college_code_mstr where status='1'" + SearchValue + " ORDER BY id " + orderType + " limit "
					+ pageL + " OFFSET " + startPage;
			}
	
	
			PreparedStatement stmt = conn.prepareStatement(q);
			System.err.println("stmt--xxxx->"+stmt);
			stmt = setQueryWhereClause_SQL(stmt, search, name_of_college,state,college_id);
		
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
				String f1 = "";
				
				
				String ul="";
				ul+="<ul class='buttons-group mainbtn action daobtn'>";
				
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDcollege_code' value='ADD' title='Edit Data' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='apIdAGE"+countFunction+"' value="+rs.getString("id")+">"
								+"<input type='hidden' id='approfAGE"+countFunction+"' value="+rs.getString("designation")+">"
								+"<input type='hidden' id='apstatusAGE"+countFunction+"' value="+rs.getString("status")+"></i></a> </li>";
				
				 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >" 
						 +"<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("id")+"><i class='lni lni-trash-can'></i></a> </li>";

	
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
	public long DataTableCollegeDataTotalCount(String search, String name_of_college, String state, String college_id) {
		// TODO Auto-generated method stub
		//return 0;

		String SearchValue = GenerateQueryWhereClause_SQL(search, name_of_college,state,college_id);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			//Query for count page in data-table....by ruler
			//q = "select count(*) \n" + " from edu_lms_system_mstr where id!=0 and status='1' " + SearchValue;
			
			
			q="select count(*) from (select id,name_of_college,state,college_id,status  from dg_rec_college_code_mstr where id!=0 and status='1' \n"
					+ SearchValue + ") ab  ";
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, search, name_of_college,state,college_id);

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
	public String GenerateQueryWhereClause_SQL(String Search, String name_of_college,String state,String college_id) {
		String SearchValue = "";
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(name_of_college) like ? )";
			//System.err.println("globalllll search"+SearchValue);
		}if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(state) like ? )";
			//System.err.println("globalllll search"+SearchValue);
		}
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(college_id) like ? )";
			//System.err.println("globalllll search"+SearchValue);
		}
		///advance search
		if( name_of_college != null && !name_of_college.equals("")) {
			SearchValue += " and upper(name_of_college) like ? ";
		
	     }
		///advance search
				if( state != null && !state.equals("")) {
					SearchValue += " and upper(state) like ? ";
				
			     }if( college_id != null && !college_id.equals("")) {
					SearchValue += " and upper(college_id) like ? ";
				
			     }
//	
//	   if(!SearchValue.contains("and") && !professional.equals("")){
//			SearchValue += " and upper(professional) like ? ";
//			System.err.println("parameter search"+SearchValue);
//	
//	    }


		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String name_of_college ,String state,String college_id) {
		int flag = 0;
		try {
			if (Search!=null &&  !Search.equals("")) {
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			
				
			}
			
			if (name_of_college != null && !name_of_college.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+name_of_college.toUpperCase()+"%");
			}
			if (state != null && !state.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+state.toUpperCase()+"%");
			}
			if (college_id != null && !college_id.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+college_id.toUpperCase()+"%");
			}
			
			
			/*
			 * if (!status.equals("") && status != null) { flag += 1; stmt.setInt(flag,
			 * Integer.parseInt(status)); }
			 */

		} catch (Exception e) {
		}

		return stmt;
		}

	
	
}

	

	
	