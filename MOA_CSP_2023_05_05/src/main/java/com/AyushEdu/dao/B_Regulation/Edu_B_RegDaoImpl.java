package com.AyushEdu.dao.B_Regulation;

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

import com.AyushEdu.Models.B_Regulation.TB_EDU_B_REGULATION;


@Repository
public class Edu_B_RegDaoImpl implements Edu_B_RegDao {

	private static final Session sessionHQL = null;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public List<Map<String, Object>> getDataByUserNameForDraft(int userid) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			
			
 		
			q=" select first_name,father_name,to_char(dob ,'DD/MM/YYYY') as dob,gender,mo_no,email_id,aadhaar_no,pre_state,per_state,pre_address_details1,pre_address_details2,pre_address_details3,per_address_details1,per_address_details2,per_address_details3,per_district,pre_district,pre_pincode,per_pincode,reg_no,to_char(date_of_reg ,'DD/MM/YYYY') as date_of_reg,registration_for_type,valid_up_to,photo_path from edu_b_regulation \n"
					+ " \n"
					+ "where status = 0 and user_id = ? order by id DESC limit 1  ";	

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, userid);
			
			ResultSet rs = stmt.executeQuery();
             System.err.println("stmt-----hhhhh-----"+stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				
			
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
	public ArrayList<ArrayList<String>> medicalData(int data) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			
			
 		
			q="select c.id,name_of_institute,attachment_path,month_and_year_of_degree,type_of_degree,degree_name\n"
					+ "from edu_b_regulation r\n"
					+ "inner join edu_b_regulation_medical_degree_child c on c.regulation_p_id = r.id\n"
					+ "where (r.status = 0 or r.status = 1) and r.id = (SELECT id from edu_b_regulation where user_id = ? order by id DESC limit 1)\n"
					+ "";	

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, data);
			
			ResultSet rs = stmt.executeQuery();
             System.err.println("stmt-----hhhhh-----"+stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("name_of_institute"));// 1
				list.add(rs.getString("attachment_path"));// 2
				list.add(rs.getString("month_and_year_of_degree"));// 3
				list.add(rs.getString("type_of_degree"));// 4
				list.add(rs.getString("degree_name"));// 5

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
	public ArrayList<ArrayList<String>> HospitalData(int data) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			
			
 		
			q="select\n"
					+ " h.id,place_of_working,landline,email,authority_type,name_of_res_p\n"
					+ ",place_of_working_name\n"
					+ ",hos_address1\n"
					+ ",hos_address2\n"
					+ ",hos_address3\n"
					+ ",hos_state\n"
					+ ",hos_district\n"
					+ ",mobile_no\n"
					+ ",to_char(date_pract_from ,'DD/MM/YYYY') as date_pract_from,to_char(date_pract_to ,'DD/MM/YYYY') as  date_pract_to\n"
					+ "\n"
					+ "from edu_b_regulation r\n"
					+ "\n"
					+ "inner join edu_b_regulation_hospital_child h on h.regulation_p_id = r.id \n"
					+ "\n"
					+ "where (r.status = 0 or r.status = 1) and r.id = (SELECT id from edu_b_regulation where user_id = ? order by id DESC limit 1)";	

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, data);
			
			ResultSet rs = stmt.executeQuery();
             System.err.println("stmt-----hhhhh-----"+stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("place_of_working"));// 1
				list.add(rs.getString("landline"));// 2
				list.add(rs.getString("email"));// 3
				list.add(rs.getString("authority_type"));// 4
				list.add(rs.getString("name_of_res_p"));// 5
				list.add(rs.getString("hos_address1"));// 6
				list.add(rs.getString("hos_address2"));// 7
				list.add(rs.getString("hos_address3"));// 8
				list.add(rs.getString("hos_state"));// 9
				list.add(rs.getString("hos_district"));// 10
				list.add(rs.getString("date_pract_from"));// 11
				list.add(rs.getString("date_pract_to"));// 12
				list.add(rs.getString("place_of_working_name"));// 13
				list.add(rs.getString("mobile_no"));// 14
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
	public List<Map<String, Object>> CheckNRH(int data) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			
			
 		
			q="select registration from edu_practitioner_registration where user_id = ?";	

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, data);
			
			ResultSet rs = stmt.executeQuery();
             System.err.println("stmt-----hhhhh-----"+stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				
			
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
	public List<Map<String, Object>> RegAuth(int data) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Connection conn = null;
		try {	
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
	 		String query = null;
			query="SELECT state_name,aadhaar_no,email_id FROM public.edu_practitioner_registration r\n"
					+ "inner join edu_lms_state_mstr s on s.state_id = r.regisration_state::integer where r.user_id = ? order by r.id DESC limit 1";	
			stmt = conn.prepareStatement(query);
			stmt.setInt(1,data);
			ResultSet rs = stmt.executeQuery();
             System.err.println("stmt-----hhhhh-----"+stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				
			
				list.add(columns);
			 
				

			}
 		    rs.close();
 	    	stmt.close();
 			conn.close();
     	} catch (SQLException e) {
     			e.printStackTrace();
     	}	
		return list;
	}
	
	@Override
	public String getUserId(int data) {
		String whr="";
		Connection conn = null;
		try {	
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
	 		String query = null;
			query="select id from edu_b_regulation where user_id = ? order by id desc limit 1\n"
					+ "";	
			stmt = conn.prepareStatement(query);
			stmt.setInt(1,data);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
 	           whr=rs.getString("id");             	
 	        }
			System.err.println("sysyy-------"+whr);
 		    rs.close();
 	    	stmt.close();
 			conn.close();
     	} catch (SQLException e) {
     			e.printStackTrace();
     	}	
		return whr;
	}
	
	public TB_EDU_B_REGULATION getEdu_b_RegByid(int id) {
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 TB_EDU_B_REGULATION updateid = (TB_EDU_B_REGULATION) session.get(TB_EDU_B_REGULATION.class, id);
         session.getTransaction().commit();
         session.close();                
        return updateid;
  }
	
	
	@Override
	public List<Map<String, Object>> getdegreedetailsb(String DegreeName) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			
			
 		
			q="select d.id,d.degree_name\n"
					+ "from edu_lms_degree_mstr d \n"
					+ "left join edu_lms_system_degree_map_mstr s on  s.degree_name   = d.id\n"
					+ "where d.type_of_degree=? and s.system_name='43' order by id ";	

			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt.setInt(1, Integer.parseInt(DegreeName));
			
			ResultSet rs = stmt.executeQuery();
			
             System.err.println("stmt-----hhhhh-----"+stmt);
             
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				
			
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

	
//	  -----------------------------View_Img Path---------------------------------------------------------
	  
	  @Override
		public String getImagePath(String id) {
			String whr="";
			Connection conn = null;
			try {	
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
		 		String query = null;
				query="select photo_path from edu_b_regulation where id=? ";	
				stmt = conn.prepareStatement(query);
				stmt.setInt(1,Integer.parseInt(id));
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
	 	           whr=rs.getString("photo_path");             	
	 	        }
	 		    rs.close();
	 	    	stmt.close();
	 			conn.close();
	     	} catch (SQLException e) {
	     			e.printStackTrace();
	     	}	
			return whr;
		}
	  
//	  -------------------------------Excel download--------------------------------------
	  
	  public ArrayList<ArrayList<String>> pdf_getAuth_and_Posted_StrenghReportDataList()
	  {
	  	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	  	Connection conn = null;
	  	String q="";
	  	String SearchValue="";

	  	try{	  
	  		conn = dataSource.getConnection();			 
	  		PreparedStatement stmt=null;
	  		
	      
	  	
	      	
	      	q=" select e.id,e.nrh_en_no,e.first_name,e.gender,e.photo_path,e.pre_address,d.district_name as pre_district,s.state_name as pre_state,e.pre_pincode, \n"
					+ "			 e.per_address,dd.district_name as per_district,ss.state_name as per_state,e.per_pincode,e.aadhaar_no,e.mo_no,e.mo_no,e.alt_mo_no1,e.email_id,\n"
					+ "				to_char(e.dob , 'DD/MM/YYYY') as dob,c.name as nationality,e.degree,e.university,e.month_year,e.reg_no,to_char(e.date_of_reg , 'DD/MM/YYYY') as date_of_reg,e.name_reg,e.reg_renew_permanent,\n"
					+ "                 e.name_of_hospital_teaching,\n"
					+ "                 e.name_of_patient,e.crh_no,e.cch_no,\n"
					+ "				 e.nch_no,e.father_name, e.status\n"
					+ "from edu_b_regulation  e\n"
					+ "inner join edu_lms_country_mstr c on cast (c.id as character varying)  = e.nationality\n"
					+ "inner join edu_lms_district_mstr d on cast (d.district_id as character varying)  = e.pre_district\n"
					+ "inner join edu_lms_state_mstr s on cast (s.state_id as character varying)  = e.pre_state\n"
					+ "inner join edu_lms_district_mstr dd on cast (dd.district_id as character varying)  = e.per_district\n"
					+ "inner join edu_lms_state_mstr ss on cast (ss.state_id as character varying)  = e.per_state\n"
					+ "where e.status='0' "+ SearchValue ;
	  			stmt=conn.prepareStatement(q);
	  			ResultSet rs = stmt.executeQuery();   
	  			int i =1;  
	  			while (rs.next()) {
	  				ArrayList<String> list = new ArrayList<String>();
	  				list.add(String.valueOf(i++)); //0
	  				list.add(rs.getString("nrh_en_no"));//1
	  				list.add(rs.getString("first_name"));//2
	  				list.add(rs.getString("gender"));//3
	  				list.add(rs.getString("photo_path"));//4
	  				list.add(rs.getString("father_name"));//5
	  				list.add(rs.getString("pre_address"));//6
	  				list.add(rs.getString("pre_district"));//7
	  				list.add(rs.getString("pre_state"));//8
	  				list.add(rs.getString("pre_pincode"));//9
	  				list.add(rs.getString("per_address"));//10
	  				list.add(rs.getString("per_district"));//11
	  				list.add(rs.getString("per_state"));//12
	  				list.add(rs.getString("per_pincode"));//13
	  				list.add(rs.getString("aadhaar_no"));//14
	  				//list.add(rs.getString("fax_no"));//15
	  				list.add(rs.getString("mo_no"));//16
	  				list.add(rs.getString("alt_mo_no1"));//17
	  				list.add(rs.getString("email_id"));//18
	  				list.add(rs.getString("dob"));//19
	  				list.add(rs.getString("nationality"));//20
	  				list.add(rs.getString("degree"));//21
	  				list.add(rs.getString("university"));//22
	  				list.add(rs.getString("month_year"));//23
	  				list.add(rs.getString("reg_no"));//24
	  				list.add(rs.getString("date_of_reg"));//25
	  				list.add(rs.getString("name_reg"));//26
	  				list.add(rs.getString("reg_renew_permanent"));//27
	  				list.add(rs.getString("name_of_hospital_teaching"));//28
	  				list.add(rs.getString("name_of_patient"));//29
	  				list.add(rs.getString("crh_no"));//30
	  				list.add(rs.getString("cch_no"));//31
	  				list.add(rs.getString("nch_no"));//32
	  				alist.add(list);
	  			
	   	        }
	  	      rs.close();
	  	      stmt.close();
	  	      conn.close();
	  	   }catch (SQLException e) {
	  			//throw new RuntimeException(e);
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
