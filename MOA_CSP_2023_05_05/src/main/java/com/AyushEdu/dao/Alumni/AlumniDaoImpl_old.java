package com.AyushEdu.dao.Alumni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.LMS_Master.TB_COUNTRY;




@Repository
public class AlumniDaoImpl_old implements AlumniDao_old{
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}


	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	
	
//	 @Override
//	 public ArrayList<ArrayList<String>> DataTableCountryDataList(int startPage, int pageLength, String Search,
//				String orderColunm, String orderType,String country,String status,HttpSession session) {
//			
//			String  role = session.getAttribute("role").toString();
//			String ls="";
//
//			
//			String pageL = "";
//			if (pageLength == -1) {
//				pageL = "ALL";
//			} else {
//				pageL = String.valueOf(pageLength);
//			}
//
//			String SearchValue = GenerateQueryWhereCandiList(Search,country);
//
//			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
//			Connection conn = null;
//			String q = "";
//
//			try {
//				conn = dataSource.getConnection();
//
//				q = "select id,name,status from edu_lms_country_mstr where status= '1'  "
//								+ SearchValue + ""    ;
//
//
//				PreparedStatement stmt = conn.prepareStatement(q);
//				
//				stmt = setQueryWhereCandiList(stmt, Search,country);
//				ResultSet rs = stmt.executeQuery();
//				
//
//				int i = 1;
//				while (rs.next()) {
//					// alist....arrange icon column wise......by ruler
//					ArrayList<String> alist = new ArrayList<String>();
//
//					String f = "";
//					String action = "";
//					String f1 = "";
//					String f2 = "";
//					String f3 = "";
//
//					
//					
//					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Country ?') )"
//							+ "{editData('" + rs.getInt("id") + "')}else{ return false;}\"";
//					f = "<i class='fa fa-pencil-square-o'  " + Update + " title='Edit Data'></i>";
//
//					
//					
//
//
//
//					String Delete = "onclick=\" if (confirm('Are You Sure You Want to Delete Country  ?') ){deleteData('"
//							+ rs.getString("id") + "') }else{ return false;}\"";
//					f3 = "<i class='fa fa-trash '  " + Delete + " title='Delete Data'></i>";
//
//					action = f + " "  + f3;
//					
//
//					
//					
//					alist.add(rs.getString("id")); //0
//					alist.add(rs.getString("name"));//1
//					alist.add(action);
//					
//					
//
//					
//					i++;
//					list.add(alist);
//					
//				}
//				rs.close();
//				stmt.close();
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} finally {
//				if (conn != null) {
//					try {
//						conn.close();
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//			return list;
//		}
//
//		public long DataTableCountryDataTotalCount(String Search, HttpSession session,String country) {
//			String  role = session.getAttribute("role").toString();
//			String SearchValue = GenerateQueryWhereCandiList(Search,country);
//			int total = 0;
//			String q = null;
//			Connection conn = null;
//			try {
//				conn = dataSource.getConnection();
//
//				
//
//	q="select count(*) from (select id,name,status from edu_lms_country_mstr where status= '1'  "
//						+ SearchValue + ") ab  ";
//						
//
//				PreparedStatement stmt = conn.prepareStatement(q);
//				
//				stmt = setQueryWhereCandiList(stmt, Search,country);
//				ResultSet rs = stmt.executeQuery();
//				
//				while (rs.next()) {
//					total = rs.getInt(1);
//				}
//				rs.close();
//				stmt.close();
//				conn.close();
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} finally {
//				if (conn != null) {
//					try {
//						conn.close();
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//			return (long) total;
//
//		}
//
//	
//
//		public boolean checkIsIntegerValue(String Search) {
//			return Search.matches("[0-9]+");
//		}
//
//		public boolean checkIsDoubleValue(String Search) {
//			return Search.matches("[0-9.]+");
//		}
//	
//	
//	
//
//	 @SuppressWarnings("unused")
//	public TB_COUNTRY getCountryByid(int id) {
//			Session sessionHQL = sessionFactory.getSessionFactory().openSession();
//		 	Transaction tx = sessionHQL.beginTransaction();
//		 	TB_COUNTRY updateid = (TB_COUNTRY) sessionHQL.get(TB_COUNTRY.class, id);
//			sessionHQL.getTransaction().commit();
//			sessionHQL.close();		
//			return updateid;
//		}
//	 
//	 public ArrayList<ArrayList<String>> search_Country_report()
//		{
//			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
//			Connection conn = null;
//			String q="";
//			try{	  
//				conn = dataSource.getConnection();			 
//				PreparedStatement stmt=null;
//				
//				
//				q="select distinct id,name from edu_lms_country_mstr  " + 
//						"where id !=0 " ;
//					stmt=conn.prepareStatement(q);
//					
//					ResultSet rs = stmt.executeQuery(); 
//					int i = 1;
//					while (rs.next()) {
//						ArrayList<String> list = new ArrayList<String>();
//						String id = String.valueOf(i++);
//						list.add(id);
//						list.add(rs.getString("name"));
//						alist.add(list);
//		 	        }
//			      rs.close();
//			      stmt.close();
//			      conn.close();
//			   }catch (SQLException e) {
//					//throw new RuntimeException(e);
//					e.printStackTrace();
//				} finally {
//					if (conn != null) {
//						try {
//							conn.close();
//						} catch (SQLException e) {
//					  }
//					}
//				}
//			return alist;
//		}


	@Override
	public ArrayList<ArrayList<String>> DataTableAlu_urlDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String url, String description, HttpSession sessionUserId) {
		String pageL = "";
		if (pageLength == -1) {
			pageL = "ALL";
		} else {
			pageL = String.valueOf(pageLength);
		}

		String SearchValue = GenerateQueryWhereCandiList(Search,url,description);

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();

			q = "select alu_name,domain_name,url,description from edu_lms_alumni_info_urls where id IS NOT NULL "
							+ SearchValue + "";

			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereCandiList(stmt, Search,url,description);
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
				
//				String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update This Country ?') )"
//						+ "{editData('" + rs.getInt("id") + "')}else{ return false;}\"";
//				f = "<i class='fa fa-pencil-square-o'  " + Update + " title='Edit Data'></i>";
//
//				String Delete = "onclick=\" if (confirm('Are You Sure You Want to Delete Country  ?') ){deleteData('"
//						+ rs.getString("id") + "') }else{ return false;}\"";
//				f3 = "<i class='fa fa-trash '  " + Delete + " title='Delete Data'></i>";
//
//				action = f + " "  + f3;
				alist.add(rs.getString("alu_name"));//1
				alist.add(rs.getString("domain_name"));//1
				alist.add(rs.getString("description"));//1
				alist.add("<a href="+rs.getString("url")+" class=\"\">"+rs.getString("url")+"</a> "); //0
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


	@Override
	public long DataTableAlu_urlDataTotalCount(String Search, HttpSession sessionUserId, String url,
			String description) {
	//	String  role = session.getAttribute("role").toString();
		String SearchValue = GenerateQueryWhereCandiList(Search,url, description);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			

q="select count(*) from (select url,description from edu_lms_alumni_info_urls where id IS NOT NULL  "
					+ SearchValue + ") ab  ";
					

			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereCandiList(stmt, Search,url,description);
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
	 
	
	
	public String GenerateQueryWhereCandiList(String Search,String url,String description) {
		String SearchValue = "";
		
		
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (  upper(url) like ? or upper(description) like ?)";
			
		}


		if (!url.equals("") && url != null) {

			SearchValue += " and name like ? ";
		}		
		if (!description.equals("") && description != null) {

			SearchValue += " and name like ? ";
		}		
			
		return SearchValue;
	}

	public PreparedStatement setQueryWhereCandiList(PreparedStatement stmt, String Search,String url,String description) {

		int flag = 0;
		try {
			
			if (Search != "" && Search != null) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}

			if (!url.equals("") && url != null) {
				flag += 1;
				stmt.setString(flag,"%"+url+"%");
			}
			if (!description.equals("") && description != null) {

				flag += 1;
				stmt.setString(flag,"%"+description+"%");
			}			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;

	}
	 
	 
	 
	
	 
	 ///////////////////////////////
	 
	 
	

}
