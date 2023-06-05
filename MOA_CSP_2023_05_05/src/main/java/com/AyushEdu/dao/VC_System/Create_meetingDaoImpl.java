package com.AyushEdu.dao.VC_System;

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
public class Create_meetingDaoImpl implements Create_MeetingDao{

	@Autowired
	private DataSource dataSource;
	@Autowired
	private SessionFactory sessionFactory;
	
	public long DataTableCreateMeetingDataTotalCount(String search, String meeting_id, String name, String attendeepw_id,
			String moderatorpw_id, String welcome, String record, String autostartrecording,
			String allowstartstoprecording) {

		
		String SearchValue = GenerateQueryWhereClause_SQL(search, meeting_id, name, attendeepw_id, moderatorpw_id, welcome,record,autostartrecording,allowstartstoprecording);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();			
			
			q="select count(*) from (select * from tb_meeting_dtl where id!=0 "
					+ SearchValue + ") ab  ";
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, search, meeting_id, name, attendeepw_id, moderatorpw_id, welcome,record,autostartrecording,allowstartstoprecording);
			
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
	public String GenerateQueryWhereClause_SQL(String search, String meeting_id, String name, String attendeepw_id,
			String moderatorpw_id, String welcome, String record, String autostartrecording,
			String allowstartstoprecording) {
		String SearchValue = "";
		
		if (search != null && !search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(meeting_id) like ? or upper(name) like ? or upper(attendeepw_id) like ?  or upper(moderatorpw_id) like ? or upper(welcome) like ? )";
				
		}
		if (meeting_id != null && !meeting_id.equals("")) {
			SearchValue += "and upper(meeting_id) Like ? ";
		}
		if (name != null && !name.equals("")) {
			SearchValue += "and upper(name) Like ? ";
		}
		if( attendeepw_id!= null && !attendeepw_id.equals("")) {
			SearchValue += "and upper(name) Like ? ";
		
	     }
		if( moderatorpw_id != null && !moderatorpw_id.equals("")) {
			SearchValue += "and upper(attendeepw_id) Like ? ";
		
	     }
		if( welcome != null && !welcome.equals("")) {
			SearchValue += "and upper(welcome) Like ? ";
		
	     }
		if( record != null && !record.equals("0")) {
			SearchValue += " and record = ? ";
		
	     }
		if( autostartrecording != null && !autostartrecording.equals("0")) {
			SearchValue += " and autostartrecording = ? ";
		
	     }
		if( allowstartstoprecording != null && !allowstartstoprecording.equals("0")) {
			SearchValue += " and allowstartstoprecording = ? ";
		
	     }

		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String search, String meeting_id, String name, String attendeepw_id,
			String moderatorpw_id, String welcome, String record, String autostartrecording,
			String allowstartstoprecording) {
		int flag = 0;
		try {
			
			if (search != null &&  !search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				
			}
			if (meeting_id != null && !meeting_id.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+meeting_id.toUpperCase()+"%");
			}
			if (name != null && !name.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+name.toUpperCase()+"%");
			}
			if (attendeepw_id != null && !attendeepw_id.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+attendeepw_id.toUpperCase()+"%");
			}
			if (moderatorpw_id != null && !moderatorpw_id.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+moderatorpw_id.toUpperCase()+"%");
			}
			if (welcome != null && !welcome.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+welcome.toUpperCase()+"%");
			}
			if (record != null && !record.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(record));
			}
			if (autostartrecording != null && !autostartrecording.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(autostartrecording));
			}
			if (allowstartstoprecording != null && !allowstartstoprecording.equals("0")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(allowstartstoprecording));
			}
			
		} catch (Exception e) {
		}
		return stmt;
	}

	public List<Map<String,Object>> DataTableCreateMeetingDataList(int startPage, int pageLength, String orderColunm, String orderType, String search, String meeting_id, String name, String attendeepw_id,
			String moderatorpw_id, String welcome, String record, String autostartrecording,String allowstartstoprecording ) {
		
		
		String SearchValue = GenerateQueryWhereClause_SQL(search, meeting_id, name, attendeepw_id, moderatorpw_id, welcome,record,autostartrecording,allowstartstoprecording);
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

			
			q = "select * from tb_meeting_dtl where id!=0 "  
					+ SearchValue +" limit "
					+ pageL + " OFFSET " + startPage;;			
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, search, meeting_id, name, attendeepw_id, moderatorpw_id, welcome,record,autostartrecording,allowstartstoprecording);
			System.err.println("stmt-------"+stmt);
			ResultSet rs = stmt.executeQuery();
		
			ResultSetMetaData metaData = rs.getMetaData();
			
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			
			int countFunctionView=1;
			int countFunctionDelete=1;
			
			while (rs.next()) {

				String rec="";
				String autostartreco="";
				String allowstartstoprec="";
				
				System.err.println("-------record - "+rs.getInt("record"));
				System.err.println("-------autostartrecording - "+rs.getInt("autostartrecording"));
				System.err.println("-------allowstartstoprecording - "+rs.getInt("allowstartstoprecording"));
				if(rs.getInt("record") == 1){
					rec = "YES";
				}else {
					rec="NO";
				}
				if(rs.getInt("autostartrecording") == 1) {
					autostartreco = "YES";
				}else {
					autostartreco="NO";
				}
				if (rs.getInt("allowstartstoprecording") == 1) {
					allowstartstoprec = "YES ";
				}else {
					allowstartstoprec = "NO ";
				}
				
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				
				String link = "";
				String action = "";
				String f1 = "";
				String f = "";
				
				String ul="";
				ul+="<ul class='buttons-group mainbtn action daobtn'>";
				 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >" 
						 +"<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("id")+"><i class='lni lni-trash-can'></i></a> </li>";
				 
				String ul1="";
				ul1+="<ul class='buttons-group mainbtn action daobtn'>";
				 f ="<label>"+"join_Meeting/"+rs.getString("link")+"<label>"
				 		+ "<a class='main-btn dark-btn btn-hover btn-iconic-icon copy' value='copy' title='copy' >"+ 
						 "<input type='hidden' id='viewID"+countFunctionView+"' value="+"join_Meeting/"+rs.getString("link")+"><i class='lni lni-files'></i></a>";
	
				ul+= f1 ;
				ul1+=f ;
				ul1+="</ul1>";
				ul+="</ul>";
				
				link = ul1;
				action = ul;
				countFunctionView+=1;
				countFunctionDelete+=1;
				
				columns.put("record", rec);
				columns.put("autostartrecording", autostartreco);
				columns.put("allowstartstoprecording", allowstartstoprec);
				columns.put("action", action);
				columns.put("link", f);

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
	
