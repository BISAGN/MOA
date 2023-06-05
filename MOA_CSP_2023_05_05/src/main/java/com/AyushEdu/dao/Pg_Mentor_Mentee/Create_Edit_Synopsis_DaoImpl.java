package com.AyushEdu.dao.Pg_Mentor_Mentee;

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

import com.AyushEdu.Models.Pg_Mentor_Mentee.PG_CREATE_EDIT_SYNOPSIS;

@Repository
public class Create_Edit_Synopsis_DaoImpl implements Create_Edit_Synopsis_Dao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@SuppressWarnings("null")
	public String GenerateQueryWhereClause_SQL(String Search, String title, String userid) {
		String SearchValue = "";
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(title) like ? )";
			//System.err.println("globalllll search"+SearchValue);
		}
		if( userid != null && !userid.equals("")) {
			SearchValue += " and userid = ? ";
	    }
		if( title != null && !title.equals("")) {
			SearchValue += " and upper(title) like ? ";
	    }


		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String title, String userid) {
		int flag = 0;
		try {
			if (Search!=null &&  !Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}
			if (userid != null && !userid.equals("")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(userid));
			}
			if (title != null && !title.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+title.toUpperCase()+"%");
			}

		} catch (Exception e) {
		}

		return stmt;
	}
	
	@Override
	public long DataTableSynopsisDataTotalCount(String Search, String title,String userid) {

		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, title,userid);
		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			q="select count(*) from (select id,title,synopsis from edu_men_pg_synopsis where id!=0 "
					+ SearchValue + ") ab  ";
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, title,userid);

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
	
	public List<Map<String,Object>> DataTableSynopsisDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String title,String userid ) {
		
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, title,userid);
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

				q = "select id,title,synopsis from edu_men_pg_synopsis where id!=0 "  + SearchValue + " ORDER BY title " + orderType + " limit "
						+ pageL + " OFFSET " + startPage;
	
	
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, title,userid);
			System.err.println("stmt---synopsis---"+stmt);
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
				String download = "";
				
//				System.err.println("\n"+rs.getString("synopsis")+"\n");
				
				String ul="";
				ul+="<ul class='buttons-group mainbtn action daobtn'>";
				
				 f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDC3_Domain' value='ADD' title='Edit Data' >" 
								+"<i class='lni lni-pencil-alt'>"
								+"<input type='hidden' id='EId"+countFunction+"' value='"+rs.getString("id")+"'>"
								+"<input type='hidden' id='ETitle"+countFunction+"' value='"+rs.getString("title")+"' >"
								+"<input type='hidden' id='ESynopsis"+countFunction+"' value='"+rs.getString("synopsis")+"'></i></a> </li>";
				
				 f1 ="<li><a class='main-btn danger-btn btn-hover btn-sm deleteOnclick' value='ADD' title='Delete Data' >" 
						 +"<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("id")+"><i class='lni lni-trash-can'></i></a> </li>";
				 
				 download = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn secondary-btn-outline  btn-hover btn-iconic-icon downloadBtn' class='downloadBtn'>"
						 +"<input type='hidden' id='dwldSynID"+countFunctionDelete+"' value='"+rs.getString("synopsis")+"'><i class='bi bi-file-pdf'></i></a></li></ul>";

	
				ul+=f + " " + f1 ;
				ul+="</ul>";
				
				action = ul;
				countFunction+=1;
				countFunctionDelete+=1;
				columns.put("action", action);
				columns.put("download", download);

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
	
	public String updateSynopsis(PG_CREATE_EDIT_SYNOPSIS obj){
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
		
		 String msg = "";
		try{
			String hql = "update PG_CREATE_EDIT_SYNOPSIS set title=:title,synopsis=:synopsis,userid=:userid,modified_by=:modified_by,modified_date=:modified_date"
					+ " where id=:id";

			Query query = session1.createQuery(hql).setParameter("title", obj.getTitle()).setParameter("synopsis", (obj. getSynopsis()))
					.setParameter("userid", (obj. getUserid())).setParameter("modified_by", obj.getModified_by()).setParameter("modified_date", obj.getModified_date())
					.setParameter("id", obj.getId());
			
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully" :"Data Not Updated";
//			msg = query.executeUpdate() > 0 ? "1" : "0";
			tx.commit();
		}
		catch (Exception e) {
			msg = "Data Not Updated";
			tx.rollback();
		}
		finally {
			session1.close();
		}
		return msg;
	}

}
