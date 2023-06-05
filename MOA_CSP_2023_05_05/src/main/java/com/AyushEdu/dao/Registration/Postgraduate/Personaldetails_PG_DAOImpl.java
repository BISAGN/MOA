package com.AyushEdu.dao.Registration.Postgraduate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_REG_PERSONAL_DETAILS;
import com.AyushEdu.dao.HexatoAsciiDAO;
import com.AyushEdu.dao.HexatoAsciiDAOImpl;

@Repository
public class Personaldetails_PG_DAOImpl implements Personaldetails_PG_DAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	@Override
	public String UpdateRegid_pg(int reg_id,int lid) {
		Session sessionHQL =this.sessionFactory.openSession();
		 Transaction	tx = sessionHQL.beginTransaction();
		
		String msg = "";
		try{

			msg = sessionHQL.createQuery("update UserLogin set reg_id=:reg_id where userId=:id")
					.setParameter("reg_id",reg_id).setParameter("id",lid).executeUpdate() > 0 ? "Data Updated Successfully." :"Data Not Updated.";
			tx.commit();
		}
		catch (Exception e) {
			msg = "Data Not Updated.";
			tx.rollback();
		}
		finally {
			sessionHQL.close();
		}
		return msg;
	
	}
	
	public ArrayList<ArrayList<String>> getPersonaldetails_pg(int userid,HttpSession session) {
	
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
        Connection conn = null;
        
        try{          
        	conn = dataSource.getConnection();
            String sq1="select i.recr_id,i.recr_name,i.recr_father_name,i.recr_mother_name,i.recr_dob,i.recr_mobileno,i.recr_email,i.recr_cadidate_title,i.recr_father_title,i.recr_mother_title,mp.aadhar_no\n"
            		+ "from tb_registration_dtl i \n"
            		+ "inner join logininformation mp on mp.username =  i.recr_email \n"
            		+ "where userid =?";                            
            
            PreparedStatement stmt = conn.prepareStatement(sq1);
            stmt.setInt(1, userid);
            
            ResultSet rs = stmt.executeQuery(); 
            
            String str1="";
            while(rs.next()){
            	ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("recr_name"));// 0
				list.add(rs.getString("recr_mobileno"));// 1
				list.add(rs.getString("recr_mother_name"));// 2
				list.add(rs.getString("recr_dob"));//3
				list.add(rs.getString("recr_email"));// 4
				list.add(rs.getString("aadhar_no"));//5
				list.add(rs.getString("recr_father_name"));//6
				list.add(rs.getString("recr_cadidate_title"));//7
				list.add(rs.getString("recr_father_title"));//8
				list.add(rs.getString("recr_mother_title"));//9
				list.add(rs.getString("recr_id"));//10

				String base64EncodedDcryptedad="";
				String enckey = "commonPwdEncKeys";
				base64EncodedDcryptedad = hex_asciiDao.decrypt(rs.getString("aadhar_no"), enckey, session);
				list.add(base64EncodedDcryptedad);
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
	
	 public Integer getUsername_pg(String username) {
			Session sessionHQL =this.sessionFactory.openSession();
			 Transaction	tx = sessionHQL.beginTransaction();
			
			int uid = 0;
			try{

				Query q2 = sessionHQL.createQuery("select userId from UserLogin Where userName=:userName").setParameter("userName", username);
				@SuppressWarnings("unchecked")
				List<Integer> list2 = (List<Integer>) q2.list();				
				uid = list2.get(0);
				
			}
			catch (Exception e) {
				tx.rollback();
			}
			finally {
				sessionHQL.close();
			}
			return uid;
		
		}
	 
	 public Integer getUserRegId_pg(String username) {
			Session sessionHQL =this.sessionFactory.openSession();
			 Transaction	tx = sessionHQL.beginTransaction();
			
			int uid = 0;
			try{

				Query q2 = sessionHQL.createQuery("select recr_id from TB_REGISTRATION_DTL Where recr_name=:recr_email").setParameter("recr_email", username.toUpperCase());
				@SuppressWarnings("unchecked")
				List<Integer> list2 = (List<Integer>) q2.list();				
				uid = list2.get(0);
			}
			catch (Exception e) {
				tx.rollback();
			}
			finally {
				sessionHQL.close();
			}
			return uid;
		}
	 
	 public ArrayList<ArrayList<String>> getAllPersdetails_pg(int userid) {
		 
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	        Connection conn = null;
	        try{          
	        	conn = dataSource.getConnection();
	            String sq1="select * from edu_pg_reg_personal_details where p_id = ?";                         
	            
	            PreparedStatement stmt = conn.prepareStatement(sq1);
	            stmt.setInt(1, userid);
	           
	            ResultSet rs = stmt.executeQuery();  
	            
	            System.err.println("stmt------>   "+ stmt);
	            
	            
	            String str1="";
	            
	            while(rs.next()){
	            	
	            	ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("first_name"));// 0
					list.add(rs.getString("father_name"));// 1
					list.add(rs.getString("mother_name"));// 2
					list.add(rs.getString("date_of_birth"));//3
					list.add(rs.getString("gender"));// 4
					list.add(rs.getString("mob_no"));//5
					list.add(rs.getString("email"));//6
					list.add(rs.getString("category"));//7
					list.add(rs.getString("religion"));//8
					list.add(rs.getString("marital_status"));//9
					list.add(rs.getString("nationality"));//10
					list.add("");//11
					list.add("");//12
					list.add("");//13
					list.add(rs.getString("aadhar_no"));//14
					list.add(rs.getString("permanent_house_no"));//15
					list.add(rs.getString("permanent_village"));//16
					list.add(rs.getString("permanent_add_line1"));//17
					list.add(rs.getString("permanent_add_line2"));//18
					list.add("");//19
					list.add(rs.getString("permanent_state"));//20
					list.add(rs.getString("permanent_district"));//21
					list.add(rs.getString("permanent_pincode"));//22
					list.add(rs.getString("permanent_lendmark"));//23
					list.add(rs.getString("present_house_no"));//24
					list.add(rs.getString("present_village"));//25
					list.add(rs.getString("present_add_line1"));//26
					list.add(rs.getString("present_add_line2"));//27
					list.add("");//28
					list.add(rs.getString("present_district"));//29
					list.add(rs.getString("present_state"));//30
					list.add(rs.getString("present_pincode"));//31
					list.add(rs.getString("present_lendmark"));//32
					list.add(rs.getString("id"));//33
					list.add("");//34
					list.add("");//35
					list.add("");//36
					list.add("");//37
					list.add("");//38
					list.add("");//39
					list.add(rs.getString("middel_name"));//40
					list.add(rs.getString("surname"));//41
					list.add("");//42
					list.add("");//43
					list.add("");//44
					
					list.add(rs.getString("corre_house_no"));//45
					list.add(rs.getString("corre_add_line1"));//46
					list.add(rs.getString("corre_add_line2"));//47
					list.add(rs.getString("corre_state"));//48
					list.add(rs.getString("corre_district"));//49
					list.add(rs.getString("corre_village"));//50
					list.add(rs.getString("corre_pincode"));//51
					list.add(rs.getString("corre_lendmark"));//52
					list.add("");//53
//					list.add(rs.getString("quota_id"));//54
//					list.add(rs.getString("counselling_authority"));//55
					
					
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

	 public String getUpdatePerDetails_pg(EDU_PG_REG_PERSONAL_DETAILS obj){
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();

			String msg = "";
			 sessionHQL.update(obj);
			 msg = "Data Updated Successfully";
				sessionHQL.flush();
				sessionHQL.clear();
				tx.commit();
//			}
//			catch (Exception e) {
//				msg = "Data Not Updated";
//				tx.rollback();
//			}
//			finally {
//				sessionHQL.close();
//			}
			return msg;
		}
	 
	 public ArrayList<ArrayList<String>> getInstID_pg(int p_id) {
		 
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			
	        Connection conn = null;
	        try{          
	        	
	        	conn = dataSource.getConnection();
	            String sq1="select clg.institute_name,clg.id  from tb_reshuffling_dtl rd \n"
	            		+ "inner join edu_lms_institute_reg clg on clg.id=rd.value::integer where p_id = ? ORDER BY rd.id ASC";                            
	            
	            PreparedStatement stmt = conn.prepareStatement(sq1);
	            stmt.setInt(1, p_id);
	            ResultSet rs = stmt.executeQuery();  
	            
	            String str1="";
	            while(rs.next()){
	            	ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("id"));// 0
					list.add(rs.getString("institute_name"));// 1
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
	 
	 public String getMaxAID_pg() {

			Connection conn = null;
			String reg_no = "";

			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				String query = "";
				query = "select max(substring(ayush_id,3,10))::int from edu_lms_student_details";
				stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					reg_no = rs.getString("max");
				}
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		//System.err.println("MAXXAID----------"+reg_no);
			return reg_no;
		}
	 
	 ////////// rajdip 29/06
public ArrayList<ArrayList<String>> getBesicdetails_pg(int userid,String staff_lvl) {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	        Connection conn = null;
	        try{  
	        	
	        	String  q="";
	        	if (staff_lvl.equals("NCH") ) {
	    			q = "edu_lms_nch_student_details";
	    		}
	        	if (staff_lvl.equals("NCISM") ) {
	    			q = "edu_lms_student_details";
	    		}
	        	
	        	
	        	conn = dataSource.getConnection();
//	            String sq1="select mobile_no,email_id,login_name,aadhar_no from logininformation where userid = ?";                         
	            
	        	 String sq1="select l.mobile_no,lower(l.email_id) as email_id,sd.name,l.aadhar_no,\n"
		            		+ "        		TO_CHAR(sd.dob,'DD/MM/YYYY') as dob,\n"
		            		+ "sd.neet_roll_no,sd.neet_application_no,sd.neet_marks,sd.neet_percentile,"
		            		+ "sd.neet_rank,sd.father_name,sd.mother_name,sd.last_name,sd.quota,sd.counc_auth,sd.category,sd.intake_id\n"
		            		+ "        		from logininformation l\n"
		            		+ "        		inner join  $tablereplace sd on lower(sd.email)=lower(l.email_id)\n"
		            		+ "        		where userid = ?";
	            
	            sq1=sq1.replace("$tablereplace",q);
	            
	            PreparedStatement stmt = conn.prepareStatement(sq1);
	            stmt.setInt(1, userid);
	            
	            
	            System.err.println("stmt---besic pg --------->    "+stmt);
	           
	            ResultSet rs = stmt.executeQuery();  
	            
	            String str1="";
	            while(rs.next()){
	            	ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("mobile_no"));// 0
					list.add(rs.getString("email_id"));// 1
					list.add(rs.getString("name"));// 2
					list.add(rs.getString("aadhar_no"));// 3
					list.add(rs.getString("dob"));// 4
					
					list.add(rs.getString("neet_roll_no"));// 5
					list.add(rs.getString("neet_application_no"));// 6
					list.add(rs.getString("neet_marks"));// 7
					list.add(rs.getString("neet_percentile"));// 8
					list.add(rs.getString("neet_rank"));// 9
					
					list.add(rs.getString("father_name"));// 10
					list.add(rs.getString("mother_name"));// 11
					list.add(rs.getString("last_name"));// 12
					list.add(rs.getString("quota"));// 13
					list.add(rs.getString("counc_auth"));// 14
					list.add(rs.getString("category"));// 15
					
					list.add(rs.getString("intake_id"));// 16
					
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



public ArrayList<ArrayList<String>> get_p_id_pers_info_data_pg(int userid) {
	 
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
       Connection conn = null;
       try{          
       	conn = dataSource.getConnection();
           String sq1=" select id from edu_pg_reg_personal_details WHERE p_id = ?";                         
           
           PreparedStatement stmt = conn.prepareStatement(sq1);
           stmt.setInt(1, userid);
          
           ResultSet rs = stmt.executeQuery();  
           
           String str1="";
           while(rs.next()){
           	ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
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

public ArrayList<ArrayList<String>> get_ayush_id_data_pg(String userid,String staff_lvl) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
    Connection conn = null;
    try{      
    	
    	String  q="";
    	if (staff_lvl.equals("NCH") ) {
			q = "edu_lms_nch_student_details";
		}
    	if (staff_lvl.equals("NCISM") ) {
			q = "edu_lms_student_details";
		}
    	
    	conn = dataSource.getConnection();
    	
        String sq1=" select \n"
        		+ "upper(sd.ayush_id) as ayush_id,um.university_name,ir.institute_name,sm.system_name,dm.degree_name,sm.id as system_id,dm.id as degree_id\n"
        		+ "from $tablereplace sd\n"
        		+ "inner join logininformation l on sd.email= l.email_id\n"
        		+ "inner join edu_lms_university_mstr um on um.id= l.university_id\n"
        		+ "inner join edu_lms_institute_reg ir on ir.id= l.institute_id \n"
        		+ "inner join edu_lms_system_mstr sm on sm.id= sd.system\n"
        		+ "inner join edu_lms_degree_mstr dm on dm.id= sd.degree\n"
        		+ "WHERE l.userid= ?";                         
        
        sq1=sq1.replace("$tablereplace",q);
        
        PreparedStatement stmt = conn.prepareStatement(sq1);
        stmt.setInt(1, Integer.parseInt(userid));
       
        System.err.println("stmt -------->    \n"+stmt);
        
        ResultSet rs = stmt.executeQuery();  
        
        String str1="";
        while(rs.next()){
        	ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("ayush_id"));// 0
				list.add(rs.getString("university_name"));// 1
				list.add(rs.getString("institute_name"));// 2
				list.add(rs.getString("system_name"));// 3
				list.add(rs.getString("degree_name"));// 4
				list.add(rs.getString("system_id"));// 5
				list.add(rs.getString("degree_id"));// 6
				
				alist.add(list);                           	  
        }
        rs.close();
        stmt.close();
        conn.close();
   }catch(SQLException e){
	   e.printStackTrace();
   }       
    
    System.err.println("alist-------->   "+alist);
   return alist;
}


	 
}
