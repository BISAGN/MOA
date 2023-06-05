
package com.AyushEdu.dao.Regulation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class Practitioner_RegistrationDAOIMPL implements Practitioner_RegistrationDAO {
	
	@Autowired
	private DataSource dataSource;

	@Autowired
	private SessionFactory sessionFactory;
	

	@Autowired
	private SessionFactory sessionFactory2;


//	-------------------------------urmik 
	
	public ArrayList<ArrayList<String>> Getaayushid_fetch(String ayush_id) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		
		String q = "";
	
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			String qry = "";
	
				q = "\n"
						+ "select distinct sd.name,sd.email,l.institute_id,l.state_id from edu_lms_student_details sd\n"
						+ "inner join logininformation l on l.userid=sd.institude_id::int\n"
						+ "inner join edu_lms_institute_reg ir on ir.id=l.institute_id\n"
						+ "inner join edu_lms_state_mstr sm on sm.state_id=l.state_id where sd.ayush_id=?";
			
				stmt = conn.prepareStatement(q);
				stmt.setString(1,ayush_id);
//				stmt.setString(2,course_id1);
				
			    System.err.println("-stmt---shra -----------"+stmt);
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {				
				  
				  
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("name")); //0
				alist.add(rs.getString("email")); //1
				alist.add(rs.getString("institute_id")); //2
				alist.add(rs.getString("state_id")); //3
				alist.add(rs.getString("state_id")); //4
			
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
				}
			}
		}
		return list;
	}

	
	
//	-------------------------nrh_no_urmik
	
	
	public ArrayList<ArrayList<String>> Getnrhno_fetch(String nrh_no) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		
		String q = "";
	
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			String qry = "";
	
//				q = "\n"
//						+ "select distinct sd.name,sd.email,l.state_id from edu_lms_student_details sd\n"
//						+ "inner join logininformation l on l.userid=sd.institude_id::int\n"
//						+ "inner join edu_lms_institute_reg ir on ir.id=l.institute_id\n"
//						+ "inner join edu_lms_state_mstr sm on sm.state_id=l.state_id where sd.ayush_id='AU000121'";
			
			q="select sd.first_name,sd.email_id,l.state_id,l.institute_id \n"
					+ "from reg_nch_form_a_p sd inner join logininformation l on l.userid=sd.user_id where sd.nrh_en_no=?";
			
				stmt = conn.prepareStatement(q);
				stmt.setString(1,nrh_no);
//				stmt.setString(2,course_id1);
				
			    System.err.println("-stmt--nnnnnnnnnnnnnnnn---------"+stmt);
				ResultSet rs = stmt.executeQuery();      
				while (rs.next()) {				
				  
				  
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("first_name")); //0
				alist.add(rs.getString("email_id")); //1
				alist.add(rs.getString("institute_id")); //2
				alist.add(rs.getString("state_id")); //3
				alist.add(rs.getString("state_id")); //4
			
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
				}
			}
		}
		return list;
	}
	
	
	 
	
	
	//janki
		@Override
		public ArrayList<ArrayList<String>> Getayus_abha_arh_data_fetch(String ayus)   {
			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
	 
			String q = "";
		
			 try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
			 
//				q="select sd.first_name,sd.email_id,l.state_id,l.institute_id \n"
//						+ "from reg_nch_form_a_p sd inner join logininformation l on l.userid=sd.user_id where sd.nrh_en_no=?";
//				'AU000140'
 
	 		
				q="    select l.ayush_id,l.aadhar_card,mobile_no ,r.user_id\n"
						+ "          from edu_lms_student_details  l  \n"
						+ "          inner join reg_nch_form_a_p r on r.ayush_id=l.ayush_id  \n"
						+ "          where l.ayush_id = ? or r.nrh_en_no=? order by  l.id desc limit 1" ;
//				 
//				
			    System.err.println("-stmt--q-s----"+q);
				
				stmt = conn.prepareStatement(q);
					stmt.setString(1,ayus);
	 			    stmt.setString(2,ayus);
				
					ResultSet rs = stmt.executeQuery();  
				    System.err.println("-stmt--jjjjj-----s----"+stmt);
					while (rs.next()) {				
					ArrayList<String> alist = new ArrayList<String>();
					alist.add(rs.getString("ayush_id")); //0
					alist.add(rs.getString("aadhar_card")); //1
					alist.add(rs.getString("mobile_no")); //2
					alist.add(rs.getString("user_id")); //3
				//	alist.add(rs.getString("aadhaar_no")); //4
//				
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
					}
				}
			}
			return list;
		}
	
		@Override
		public ArrayList<ArrayList<String>> Getnewdatavalidfetch(String newusername)   {
			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
	 
			String q = "";
		
			 try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
			 
//				q="select sd.first_name,sd.email_id,l.state_id,l.institute_id \n"
//						+ "from reg_nch_form_a_p sd inner join logininformation l on l.userid=sd.user_id where sd.nrh_en_no=?";
//				'AU000140'
				
 
				q="    select id,ayush_id,name,email,university_userid  from reg_nch_details_a where aadhar_card=? " ;
 		
			    System.err.println("-stmt-lllllllll-"+q);
				
				stmt = conn.prepareStatement(q);
					stmt.setString(1,newusername);
	 			    
					ResultSet rs = stmt.executeQuery();  
				    System.err.println("-stmt--jjjjj-----s----"+stmt);
					while (rs.next()) {				
					ArrayList<String> alist = new ArrayList<String>();
					alist.add(rs.getString("id")); //0
					alist.add(rs.getString("ayush_id")); //1
					alist.add(rs.getString("name")); //2
					alist.add(rs.getString("email")); //3
					alist.add(rs.getString("university_userid")); //4
//				
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
					}
				}
			}
			return list;
		}
		
		
		
		
		@Override
		public ArrayList<ArrayList<String>> getstatelistFromInstitute(String institute_name,String user_id ) {
			
			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			try {
				
				
				
				conn = dataSource.getConnection();

				System.err.println("institute_name-------->  "+institute_name);
				
//				q="select distinct sm.state_id,sm.state_name\n"
//						+ "FROM edu_lms_institute_reg qp\n"
//						+ "INNER JOIN edu_lms_state_mstr sm  ON sm.state_id=qp.id institute=?  ";
				
//				q="select distinct sm.state_id,sm.state_name\n"
//						+ "FROM edu_lms_institute_reg qp\n"
//						+ "INNER JOIN edu_lms_state_mstr sm  ON sm.state_id=qp.id \n"
//						+ "WHERE qp.id = ?";
				
				q= "	select distinct sm.state_id,sm.state_name \n"
						+ "	FROM edu_lms_university_mstr qp \n"
						+ "	INNER JOIN edu_lms_state_mstr sm  ON sm.state_id=qp.state_id  \n"
						+ "	WHERE qp.id = ? ";
				
				PreparedStatement stmt = conn.prepareStatement(q); 
				stmt.setInt(1, Integer.parseInt(institute_name));
				//stmt.setInt(2, Integer.parseInt(user_id));
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					ArrayList<String> alist = new ArrayList<String>();

					alist.add(rs.getString("state_id"));// 0
					alist.add(rs.getString("state_name"));// 1
					
				
					list.add(alist);
				}
				
				rs.close();
				stmt.close();
				conn.close();
			}
			catch (SQLException e) {
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
		public ArrayList<ArrayList<String>> getInstitutelistFromState(String state_name,String user_id ) {
			
			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			try {
				
				
				
				conn = dataSource.getConnection();

				System.err.println("state_name-------->  "+state_name);
				
//				q="select distinct sm.state_id,sm.state_name\n"
//						+ "FROM edu_lms_institute_reg qp\n"
//						+ "INNER JOIN edu_lms_state_mstr sm  ON sm.state_id=qp.id institute=?  ";
				
//				q="select DISTINCT qb.id,qb.institute_name\n"
//						+ "FROM edu_lms_state_mstr sm\n"
//						+ "inner join edu_lms_institute_reg qb on qb.id=sm.state_id\n"
//						+ "where qb.id=?";
				
				q="select DISTINCT qb.id,qb.university_name \n"
						+ "	  FROM edu_lms_state_mstr sm \n"
						+ "	inner join edu_lms_university_mstr qb on qb.state_id=sm.state_id \n"
						+ "	  where sm.state_id =? ";
				
				
				PreparedStatement stmt = conn.prepareStatement(q); 
				stmt.setInt(1, Integer.parseInt(state_name));
				//stmt.setInt(2, Integer.parseInt(user_id));
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					ArrayList<String> alist = new ArrayList<String>();

					alist.add(rs.getString("id"));// 0
					alist.add(rs.getString("university_name"));// 1
					
				
					list.add(alist);
				}
				System.err.println("stmtstmtstmtstmt----------"+stmt);
				rs.close();
				stmt.close();
				conn.close();
			}
			catch (SQLException e) {
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
	
	
	
}

