package com.AyushEdu.dao.Registration;

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

import com.AyushEdu.Models.Registration.TB_PERSONAL_DETAILS;
import com.AyushEdu.dao.HexatoAsciiDAO;
import com.AyushEdu.dao.HexatoAsciiDAOImpl;

@Repository
public class personaldetailsDAOImpl implements personaldetailsDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	@Override
	public String UpdateRegid(int reg_id,int lid) {
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
	
	public ArrayList<ArrayList<String>> getPersonaldetails(int userid,HttpSession session) {
	
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
	
	 public Integer getUsername(String username) {
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
	 
	 public Integer getUserRegId(String username) {
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
	 
	 public ArrayList<ArrayList<String>> getAllPersdetails(int userid) {
		 
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	        Connection conn = null;
	        try{          
	        	conn = dataSource.getConnection();
	            String sq1="select gm.gender_name as gender_name,cm.category as category,rm.religion as religion,mm.marital_status as marital_status,nm.name as nationality,sm.state_name as state_name,dm.district_name as district_name,* from edu_reg_gradu_personal_dtls pd "
	            		+ "INNER JOIN edu_lms_gender_mstr gm ON gm.id = cast(pd.pers_gender as INTEGER)\n"
	            		+ "INNER JOIN tb_lms_category_mstr cm ON cm.id = cast(pd.pers_category as INTEGER)\n"
	            		+ "INNER JOIN edu_lms_religion_mstr rm ON rm.id = cast(pd.pers_religion as INTEGER)\n"
	            		+ "INNER JOIN edu_lms_marital_status_mstr mm ON mm.id = cast(pd.pers_marital_status as INTEGER)\n"
	            		+ "INNER JOIN edu_lms_country_mstr nm ON nm.id = cast(pd.pers_nationality as INTEGER)\n"
	            		+ "INNER JOIN edu_lms_state_mstr sm ON sm.state_id = cast(pd.state_id as INTEGER)\n"
	            		+ "INNER JOIN edu_lms_district_mstr dm ON dm.district_id = cast(pd.district_id as INTEGER)"
	            		+ " where p_id = ?";                         
	            
	            PreparedStatement stmt = conn.prepareStatement(sq1);
	            stmt.setInt(1, userid);
	           
	            ResultSet rs = stmt.executeQuery();  
	            
	            
	            String str1="";
	            
	            while(rs.next()){
	            	
	            	ArrayList<String> list = new ArrayList<String>();
	            	
					list.add(rs.getString("pers_name"));// 0
					list.add(rs.getString("pers_father_name"));// 1
					list.add(rs.getString("pers_mother_name"));// 2
					list.add(rs.getString("pers_date_of_birth"));//3
					list.add(rs.getString("pers_gender"));// 4
					list.add(rs.getString("pers_mob_no"));//5
					list.add(rs.getString("pers_email"));//6
					list.add(rs.getString("pers_category"));//7
					list.add(rs.getString("pers_religion"));//8
					list.add(rs.getString("pers_marital_status"));//9
					list.add(rs.getString("pers_nationality"));//10
					list.add(rs.getString("state_id"));//11
					list.add(rs.getString("district_id"));//12
					list.add(rs.getString("village"));//13
					list.add(rs.getString("pers_aadhar_no"));//14
					list.add(rs.getString("pers_permanent_house_no"));//15
					list.add(rs.getString("pers_permanent_village"));//16
					list.add(rs.getString("pers_permanent_postoffice"));//17
					list.add(rs.getString("pers_permanent_tehsil"));//18
					list.add(rs.getString("pers_permanent_policestation"));//19
					list.add(rs.getString("pers_permanent_state"));//20
					list.add(rs.getString("pers_permanent_district"));//21
					list.add(rs.getString("pers_permanent_pincode"));//22
					list.add(rs.getString("pers_permanent_lendmark"));//23
					list.add(rs.getString("pers_present_house_no"));//24
					list.add(rs.getString("pers_present_village"));//25
					list.add(rs.getString("pers_present_postoffice"));//26
					list.add(rs.getString("pers_present_tehsil"));//27
					list.add(rs.getString("pers_present_policestation"));//28
					list.add(rs.getString("pers_present_district"));//29
					list.add(rs.getString("pers_present_state"));//30
					list.add(rs.getString("pers_present_pincode"));//31
					list.add(rs.getString("pers_present_lendmark"));//32
					list.add(rs.getString("id"));//33
					list.add(rs.getString("cand_prifix"));//34
					list.add(rs.getString("pers_father_title"));//35
					list.add(rs.getString("pers_mother_title"));//36
					list.add(rs.getString("neet_rank"));//37
					list.add(rs.getString("neet_marks"));//38
					list.add(rs.getString("neet_percentile"));//39
					list.add(rs.getString("pers_middel_name"));//40
					list.add(rs.getString("pers_surname"));//41
					list.add(rs.getString("cand_prifix"));//42
					list.add(rs.getString("neet_roll_no"));//43
					list.add(rs.getString("neet_application_no"));//44
					
					list.add(rs.getString("corre_house_no"));//45
					list.add(rs.getString("corre_village"));//46
					list.add(rs.getString("corre_postoffice"));//47
					list.add(rs.getString("corre_tehsil"));//48
					list.add(rs.getString("corre_policestation"));//49
					list.add(rs.getString("corre_district"));//50
					list.add(rs.getString("corre_state"));//51
					list.add(rs.getString("corre_pincode"));//52
					list.add(rs.getString("corre_lendmark"));//53
					
					// quota and counselling authority add
					
					list.add(rs.getString("quota_id"));//54
					list.add(rs.getString("counselling_authority"));//55
					
					
					list.add(rs.getString("gender_name"));//53
					list.add(rs.getString("category"));//53
					list.add(rs.getString("religion"));//53
					list.add(rs.getString("marital_status"));//53
					list.add(rs.getString("nationality"));//53
					list.add(rs.getString("state_name"));//53
					list.add(rs.getString("district_name"));//53
					
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

	 public String getUpdatePerDetails(TB_PERSONAL_DETAILS obj){
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
	 
	 public ArrayList<ArrayList<String>> getInstID(int p_id) {
		 
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
	 
	 public String getMaxAID() {

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
	 
	 
	 
	//====27-01 ====
	 
public ArrayList<ArrayList<String>> getBesicdetails(int userid) {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	        Connection conn = null;
	        
	        try{
	        	
	        	conn = dataSource.getConnection();
	            String sq1="select l.mobile_no, l.email_id,l.login_name,l.aadhar_no,\n"
	            		+ "sd.neet_roll_no,sd.neet_application_no,sd.neet_marks,sd.neet_percentile,"
	            		+ "sd.neet_rank,TO_CHAR(sd.dob,'DD/MM/YYYY') as dob,sd.father_name,sd.mother_name,sd.last_name,sd.quota,sd.counc_auth,sd.category\n"
	            		+ "from logininformation l\n"
	            		+ "inner join edu_lms_nch_student_details sd on lower(sd.email)=lower(l.email_id)\n"
	            		+ "where userid = ?";                         
	            
	            PreparedStatement stmt = conn.prepareStatement(sq1);
	            stmt.setInt(1, userid);
	            
	            System.err.println("stmt----7nov--"+stmt);
	           
	            ResultSet rs = stmt.executeQuery();  
	            
	            String str1="";
	            while(rs.next()){
	            	ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("mobile_no"));// 0
					list.add(rs.getString("email_id"));// 1
					list.add(rs.getString("login_name"));// 2
					list.add(rs.getString("aadhar_no"));// 3
					list.add(rs.getString("neet_roll_no"));// 4
					list.add(rs.getString("neet_application_no"));// 5
					list.add(rs.getString("neet_marks"));// 6
					list.add(rs.getString("neet_percentile"));// 7
					list.add(rs.getString("neet_rank"));// 8
					list.add(rs.getString("dob"));// 9
					list.add(rs.getString("father_name"));// 10
					list.add(rs.getString("mother_name"));// 11
					list.add(rs.getString("last_name"));// 12
					list.add(rs.getString("quota"));// 13
					list.add(rs.getString("counc_auth"));// 14
					list.add(rs.getString("category"));// 15
					
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



public ArrayList<ArrayList<String>> get_p_id_pers_info_data(int userid) {
	 
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
       Connection conn = null;
       try{          
       	conn = dataSource.getConnection();
           String sq1=" select id from edu_reg_gradu_personal_dtls WHERE p_id = ?";                         
           
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

public ArrayList<ArrayList<String>> get_ayush_id_data(String userid) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
    Connection conn = null;
    try{          
    	conn = dataSource.getConnection();
    	
        String sq1=" select \n"
        		+ "upper(sd.ayush_id) as ayush_id,um.university_name,ir.institute_name,sm.system_name,dm.degree_name,sm.id as system_id,sd.late_admission_status\n"
        		+ "from edu_lms_nch_student_details sd\n"
        		+ "inner join logininformation l on sd.email= l.email_id\n"
        		+ "inner join edu_lms_university_mstr um on um.id= l.university_id\n"
        		+ "inner join edu_lms_institute_reg ir on ir.id= l.institute_id \n"
        		+ "inner join edu_lms_system_mstr sm on sm.id= sd.system\n"
        		+ "inner join edu_lms_degree_mstr dm on dm.id= sd.degree\n"
        		+ "WHERE l.userid= ?";                         
        
        PreparedStatement stmt = conn.prepareStatement(sq1);
        stmt.setInt(1, Integer.parseInt(userid));
       
        System.err.println("stmt-------------->    "+   stmt);
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
				list.add(rs.getString("late_admission_status"));// 6
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
public ArrayList<ArrayList<String>> getInstituteLogin(int userid) {
	
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
    Connection conn = null;
    
    try{          
    	conn = dataSource.getConnection();
        String sq1="select distinct id, institute_name from edu_lms_institute_reg where app_status='1' and status!='2' order by id";                            
        
        PreparedStatement stmt = conn.prepareStatement(sq1);
        stmt.setInt(1, userid);
        
        ResultSet rs = stmt.executeQuery(); 
        
        String str1="";
        while(rs.next()){
        	ArrayList<String> list = new ArrayList<String>();
			list.add(rs.getString("recr_name"));// 0
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
