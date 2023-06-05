package com.AyushEdu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@Repository
public class ChangePasswordDAOImpl implements ChangePasswordDAO {
	@Autowired
	private DataSource dataSource;

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public long getUsernamevaliddata(String username) {
		
		int whr = 0;
		String q1 = "";
		Connection conn = null;
		String fildname1 = "";
//		if(val.equals("1")) {
//			q1="persdetails";
//		}
//		else if(val.equals("2")){
//			q1="edudetails";
//		}
//		
//		if (fildname.equals("resumedoc")) {
//			fildname1 = "resumedoc";
//		}
//		else if (fildname.equals("identitydoc")) {
//			fildname1 = "identitydoc";
//		}
//		else if (fildname.equals("3")) {
//			fildname1 = "identitydoc";
//		}


		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = null;
			query = "select count(userid) from logininformation where upper(username) = ?";
			// query = query.replace("$fildname", fildname);

			stmt = conn.prepareStatement(query);
			stmt.setString(1,  username.toUpperCase() );
			
			System.err.println("stmt----------->    "+stmt);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				whr = rs.getInt("count");
			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.err.println("whr----->     "+whr);
		
		return whr;

	}
	
	 @Override
		public @ResponseBody ArrayList<ArrayList<String>> getForgotPassworduserdataList(String aadhaar_no) {
			
			System.out.println("userid-----25/08------"+aadhaar_no);

			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			String whr = "";
			Connection conn = null;
			String q = "";
			
			try {
				
				conn = dataSource.getConnection();
				
				
				String hql ="select count(ul.userId) from AYUSH_ID_DIRECTORY_PARENT t, UserLogin ul WHERE ul.userId = t.user_id and t.aadhaar_no=:aadhaar_no";
				
				Session sessionHQL= this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query qr = sessionHQL.createQuery(hql);
				qr.setParameter("aadhaar_no",aadhaar_no);
				@SuppressWarnings("unchecked")
				Long count = (Long) qr.list().get(0);
				
				
				
				 ArrayList<String> list = new ArrayList<String>();
				if (count>0) {
					
				
				q = " select li.userid, li.username,li.aadhar_no from ayush_id_directory_parent dp \n"
						+ " inner join logininformation li  on li.userid=dp.user_id \n"
						+ " where dp.aadhaar_no=?" ; 
						
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt.setString(1, aadhaar_no);
				
				ResultSet rs = stmt.executeQuery();

				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				
				 while (rs.next()) {
					  list.add("0");//0
			    	  list.add(rs.getString("userid"));//1
			    	  list.add(rs.getString("username"));//2
			    	  list.add(rs.getString("aadhar_no"));//3
			    	  alist.add(list);
				}
				
				rs.close();
				stmt.close();
				conn.close();
				
				
				}
				
				else {
					  list.add("1");//0
			    	  list.add("Your Aadhaar Number is Not Associated With Any Account");//1
			    	  list.add("");//2
			    	  alist.add(list);
				}
				
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
			System.err.println("list------" + alist);
			return alist;
		}
	 
	 @Override
		public @ResponseBody ArrayList<ArrayList<String>> getForgetPassInstName(String username,String email,String cat) {
			

			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			String whr = "";
			Connection conn = null;
			String q = "";
			
			try {
				
				conn = dataSource.getConnection();
				
				
				String hql ="select count(ul.userId) from  UserLogin ul WHERE ul.userName=:username and ul.email_id=:email_id ";
				
				Session sessionHQL= this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query qr = sessionHQL.createQuery(hql);
				qr.setParameter("username",username);
				qr.setParameter("email_id",email);
				@SuppressWarnings("unchecked")
				Long count = (Long) qr.list().get(0);
				
				
				
				 ArrayList<String> list = new ArrayList<String>();
				if (count>0) {
					
				if(cat.equals("institute")) {
					q = " select lo.userid,ir.institute_name \n"
							+ "from logininformation lo\n"
							+ "inner join edu_lms_institute_reg ir on ir.id=lo.institute_id\n"
							+ "where lo.username = ? and lo.email_id = ?" ;
				}
				if(cat.equals("ncism") || cat.equals("nch") || cat.equals("24") || cat.equals("25")) {
					q = " select lo.userid,lo.login_name \n"
							+ "from logininformation lo\n"
							+ "where lo.username = ? and lo.email_id = ?" ;
				}
				 
						
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt.setString(1, username);
				stmt.setString(2, email);
				
				System.err.println("ForgetPassSTMT---"+stmt);
				
				ResultSet rs = stmt.executeQuery();

				 while (rs.next()) {
					 
					 if(cat.equals("institute")) {
						  list.add("1");
				    	  list.add(rs.getString("institute_name"));
				    	  list.add(rs.getString("userid"));
						}
						if(cat.equals("ncism") || cat.equals("nch") || cat.equals("24") || cat.equals("25")) {
						  list.add("1");
				    	  list.add(rs.getString("login_name"));
				    	  list.add(rs.getString("userid"));
						}
					 
					 
			    	  alist.add(list);
				}
				
				rs.close();
				stmt.close();
				conn.close();
				
				
				}
				
				else {
					  list.add("0");
			    	  list.add("User Not Found With this Username and Email Id");
			    	  alist.add(list);
				}
				
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
	
	
}
