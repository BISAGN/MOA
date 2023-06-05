package com.AyushEdu.dao.Registration.Postgraduate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class DocumentUpload_PGDaoImpl implements DocumentUpload_PGDao{

	@Autowired
	private DataSource dataSource;

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public ArrayList<ArrayList<String>> DataTableDocData_PGList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, Integer doc_name,int userid) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search, doc_name, userid);
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
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

q = " select distinct ROW_NUMBER() OVER(order by d.id) as sr_no, d.id, m.doc_name,d.upload_document \n"
					+ "from edu_pg_reg_other_doc_upload d \n"
					+ "inner join edu_pg_reg_personal_details tpd on tpd.id = d.p_id\n"
					+ "inner join logininformation mp2 on  mp2.userid =  tpd.p_id\n"
					+"inner join edu_pg_doc_upload_mstr m on m.id = d.doc_name::int\n"
					+ "where d.id!=0 \n"
					+ SearchValue + " ORDER BY d.id " + orderType + " limit " + pageL + " OFFSET "
					+ startPage; // "+orderColunm +"
				

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search,doc_name, userid);
			
			System.err.println("stmt------->   "+stmt);
			
			ResultSet rs = stmt.executeQuery();

			
			
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int i = 1;
			
			
			  int countDownload=1;
	    	  int countFunctionDelete=1;
			
			 while (rs.next()) {
		    	  ArrayList<String> list = new ArrayList<String>();
		    	  
					String f = "";
					String action = "";
					String f1 = "";
					String f2 = "";

					String Download = "onclick=\"  if (confirm('Are You Sure You Want to Download This Category ?') )"
							+ "{getDownloadPdfDoc('" + rs.getInt("id") + "')}else{ return false;}\"";
					/* f1 = "<i class='lni lni-download' "+Download+" title='Downlaod'></i>"; */

					f1 = "<ul class='buttons-group mainbtn action'>"
							+ "<li><a class='main-btn info-btn btn-hover btn-sm Downdoc' value='download' "
							+ " title='Downlaod' ><i class='lni lni-download'>"
							+ "<input type='hidden' id='apDOWN"+countDownload+"' value="+rs.getString("id")+"></i></a></li></ul>";

					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Data?') )"
							+ "{deleteData(" + rs.getString("id") + ")}else{ return false;}\"";


					f2 = "<ul class='buttons-group mainbtn action'>"
							+ "<li><a class='main-btn danger-btn btn-hover btn-sm Deledoc' value='delete' " 
							+ " title='Delete' >"
							+ "<input type='hidden' id='deleteID"+countFunctionDelete+"' value="+rs.getString("id")+"><i class='lni lni-trash-can'></i></a></li></ul>";

					action = f + f2;
					countDownload+=1;
					countFunctionDelete+=1;
		    	  
		    	  
		    	  
//					String f = "";
//					String action = "";
//					String f1 = "";
//					String f2 = "";
//
//					String Download = "onclick=\"  if (confirm('Are You Sure You Want to Download This Category ?') )"
//							+ "{getDownloadPdfDoc('" + rs.getInt("id") + "')}else{ return false;}\"";
//					/* f1 = "<i class='lni lni-download' "+Download+" title='Downlaod'></i>"; */
//
//					f1 = "<ul class='buttons-group mainbtn action'>"
//							+ "<li><a class='main-btn info-btn btn-hover btn-sm' value='download' " + Download
//							+ " title='Downlaod' ><i class='lni lni-download'></i></a></li></ul>";
//
//					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Data?') )"
//							+ "{deleteData(" + rs.getString("id") + ")}else{ return false;}\"";
//
//					/*
//					 * f2 = "<i class='action_icons action_delete' " + Delete1 +
//					 * " title='Delete Data'></i>";
//					 */
//
//					f2 = "<ul class='buttons-group mainbtn action'>"
//							+ "<li><a class='main-btn danger-btn btn-hover btn-sm' value='delete' " + Delete1
//							+ " title='Delete' ><i class='lni lni-trash-can'></i></a></li></ul>";
//
//					action = f + f2;
				
				list.add(rs.getString("sr_no"));
				list.add(rs.getString("doc_name"));//1
//				list.add(rs.getString("upload_document"));//2
				
				list.add(f1);
				list.add(action);
				
				i++;
					alist.add(list);
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
		
		return alist;
	}
	
	@Override
	public long DataTableDocData_PGTotalCount(String Search, Integer doc_name,int userid) {

		String SearchValue = GenerateQueryWhereClause_SQL(Search,doc_name, userid);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			q = "select count(*) from (\n"
					+" select distinct ROW_NUMBER() OVER(order by d.id) as sr_no, d.id,m.doc_name,d.upload_document \n"
					+ "from edu_pg_reg_other_doc_upload d \n"
					+ "inner join edu_pg_reg_personal_details tpd on tpd.id = d.p_id\n"
					+ "inner join logininformation mp2 on  mp2.userid =  tpd.p_id\n"
					+"inner join edu_pg_doc_upload_mstr m on m.id = d.doc_name::int\n"
					+ "where d.id!=0\n"
					+ SearchValue + ") ab";
			
			//q = "select count(*) \n" + "from edu_pg_reg_other_doc_upload d where id!=0 " + SearchValue;
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search,doc_name,userid);

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
	
	public String GenerateQueryWhereClause_SQL(String Search, Integer doc_name,int userid) {
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += "and upper(m.doc_name) like ? ";
//					+ " or upper(d.upload_document) like ?)";
		}

		if (userid != 0) {
			SearchValue += " and mp2.userid = ? ";

		}

		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, Integer doc_name,int userid) {
		
		int flag = 0;
		try {
			if (Search != null && !Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}

//			if (doc_name != 0 && doc_name != null) {
//				flag += 1;
//				stmt.setInt(flag,  doc_name);
//			}

//			if (!upload_document.equals("") && upload_document != null) {
//				flag += 1;
//				stmt.setString(flag, "%" + upload_document.toUpperCase() + "%");
//			}
			
			if (userid != 0) {
				flag += 1;
				stmt.setInt(flag, userid);
			}

		} catch (Exception e) {
		}

		return stmt;
	}
	
	public String getFilePathQuery_PG(int id) {

		String whr = "";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = null;
			query = "select upload_document from edu_pg_reg_other_doc_upload where id = ?";

			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				whr = rs.getString("upload_document");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return whr;
	}

	public String getSignatureImagePath_PG(String p_id) {
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL
					.createQuery("SELECT signature FROM EDU_PG_REG_DOCUMENT_UPLOAD where p_id=:p_id order by id DESC");
			q1.setParameter("p_id", Integer.parseInt(p_id));
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();
			String path=list.get(0);
			tx.commit();
			return path;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}
	
	public String getPhotographImagePath_PG(String p_id) {
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL
					.createQuery("SELECT photograph FROM EDU_PG_REG_DOCUMENT_UPLOAD where p_id=:p_id order by id DESC");
			q1.setParameter("p_id", Integer.parseInt(p_id));
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();
			String path=list.get(0);
			tx.commit();
			return path;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}

	@Override
	public ArrayList<ArrayList<String>> get_uploded_imgthumb_data_PG(String p_id) {
		

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
       Connection conn = null;
       try{          
       	conn = dataSource.getConnection();
           String sq1=" select id,photograph,signature from edu_pg_reg_document_upload  WHERE p_id = ?";
           
           
           PreparedStatement stmt = conn.prepareStatement(sq1);
           stmt.setInt(1, Integer.parseInt(p_id));
          
           ResultSet rs = stmt.executeQuery();  
           
           String str1="";
           while(rs.next()){
           	ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("photograph"));// 0
				list.add(rs.getString("signature"));// 1
				list.add(rs.getString("id"));// 2
				alist.add(list);                           	  
           }
           rs.close();
           stmt.close();
           conn.close();
      }catch(SQLException e){
   	   e.printStackTrace();
      }  
       
      return alist;
		
		
	}
}
