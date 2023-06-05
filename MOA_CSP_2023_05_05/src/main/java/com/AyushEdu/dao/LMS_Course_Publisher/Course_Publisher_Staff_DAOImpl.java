package com.AyushEdu.dao.LMS_Course_Publisher;

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


@Repository
public class Course_Publisher_Staff_DAOImpl implements Course_Publisher_Staff_DAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	
	public List<Map<String,Object>> DataTablePubli_Staff_Act_InactDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String name, String status) {
		
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, name, status);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String qry="";
		try {

			conn = dataSource.getConnection();
			String pageL = "";
			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}

			
				   q = "select distinct ROW_NUMBER() OVER(order by login_name ASC) as ser,userid,login_name as name from logininformation l\n"
				   		+ "inner join userroleinformation ro on ro.user_id= l.userid\n"
				   		+ "inner join  roleinformation r on r.role_id=ro.role_id where role='Course_Publisher_Staff_NCISM' " + SearchValue + " ORDER BY login_name " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
          
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, name, status);
			ResultSet rs = stmt.executeQuery();
			System.err.println("stmt------------shivali-->>>  "+stmt);

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			
	//		int countFunction=1;
			int countFunctionactive = 1;
			int countFunctioninactive = 1;
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				
			
				String f = "";
				String action = "";
				String f1 = "";
				String f2 = "";
				String f3 = "";

				
				String ul="";
				ul+="<ul class='buttons-group mainbtn action daobtn'>";
				
//				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){editData('"+ rs.getString("id") +"') }else{ return false;}\"";
//				f = "<li><a class='main-btn active-btn btn-hover btn-sm ADDCoursepublisher' title='Edit Data'>"+
//						"<i class='lni lni-pencil-alt'>"
//						+ "<input type='hidden' id='Course_publisherId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
				
				
               if (status.equals("2")) {
            	   
            	   String ADD2 = "onclick=\" if (confirm('Are You Sure Want to Active This User ?') ){ActiveUserData1('"+ rs.getString("userid") + "') }else{ return false;}\"";
   				         f2 = "<li><a class='main-btn active-btn btn-hover btnactive ActiveOnclick1' title='Active User'>"+
   						"<i class='fa fa-user-o'>Active"
   						+ "<input type='hidden' id='ActiveID"+countFunctionactive+"' value="+rs.getString("userid")+"></i></a> </li>"; 
				}
               else {
            	   String ADD3 = "onclick=\" if (confirm('Are You Sure Want to Inactive This User ?') ){InactiveUserData1('"+ rs.getString("userid") + "') }else{ return false;}\"";
 				     f3 = "<li><a class='main-btn light-btn btn-hover btninact InactiveOnclick1' title='Inactive User'>"+
 						  "<i class='fa fa-user-o'>Inactive"
 						  + "<input type='hidden' id='InactiveID"+countFunctioninactive+"' value="+rs.getString("userid")+"></i></a> </li>";
               }
				
				 
//				 if (status.equals("2")) {
					 ul+=f + " " + f2 ;
					 countFunctionactive+=1;
//				 }else {
					 ul+=f + " " + f3 ;
					 countFunctioninactive+=1;
//				 }
				 ul+="</ul>";
		
			//	 countFunction += 1;
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
	public long DataTablePubli_Staff_Act_InactDataTotalCount(String Search, String name, String status) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, name, status);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			

			q = "select count(*) from (select distinct userid,login_name as name from logininformation l\n"
					+ "inner join userroleinformation ro on ro.user_id= l.userid\n"
					+ "inner join  roleinformation r on r.role_id=ro.role_id where role='Course_Publisher_Staff_NCISM' and l.enabled='1' "+SearchValue+") a ";
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, name, status);
    
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
	public String GenerateQueryWhereClause_SQL(String Search, String name,String status) {
		String SearchValue = "";
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (upper(login_name) like ? )";
		}

		if (!name.trim().equals("")) {
			SearchValue += " and (upper(login_name) like ? ) ";
		}
		
		if (!status.trim().equals("")) {
			SearchValue += " and l.enabled = ? ";
		}
		
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String name, String status) {
		int flag = 0;
		try {
			if (Search!=null &&  !Search.equals("")) {
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}
			
			if (!name.equals("") && name != null) {
				flag += 1;
				stmt.setString(flag,"%"+name.toUpperCase()+"%");
			}
			 
			if (!status.trim().equals("")) {
				  String s = "";
				if(status.equals("2")) {
					s= "0";
				}else {
					s=status;
				}
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(s));
			}
			
		} catch (Exception e) {
		}

		return stmt;
	}

	


}
