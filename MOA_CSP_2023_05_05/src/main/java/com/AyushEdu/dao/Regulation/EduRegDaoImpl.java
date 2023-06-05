package com.AyushEdu.dao.Regulation;


import java.awt.Image;
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
import javax.swing.ImageIcon;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.AyushEdu.Models.Regulation.REG_NCH_FORM_A_P;


@Repository
public class EduRegDaoImpl implements EduRegDao {

	 
	private static final Session sessionHQL = null;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	 

	
	
		public REG_NCH_FORM_A_P getEdu_RegByid(int id) {
			Session session = sessionFactory.openSession();
			 Transaction tx = session.beginTransaction();
			 REG_NCH_FORM_A_P updateid = (REG_NCH_FORM_A_P) session.get(REG_NCH_FORM_A_P.class, id);
	         session.getTransaction().commit();
	         session.close();                
	        return updateid;
	  }
		
		
		
		

		  
//		  -----------------------------View_Img Path---------------------------------------------------------
		  
		  @Override
			public String getImagePath(String id) {
				String whr="";
				Connection conn = null;
				try {	
					conn = dataSource.getConnection();
					PreparedStatement stmt = null;
			 		String query = null;
					query="select photo_path from reg_nch_form_a_p where id=? ";	
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
		  
		  
//		  -------------------------------Excel download--------------------------------------
		  
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
						+ "				to_char(e.dob , 'DD/MM/YYYY') as dob,c.name as nationality,e.degree,e.university,e.month_year,to_char(e.date_of_reg , 'DD/MM/YYYY') as date_of_reg,e.name_reg,e.reg_renew_permanent,\n"
						+ "                 e.name_of_hospital_teaching,\n"
						+ "                 e.name_of_patient,e.crh_no,e.cch_no,\n"
						+ "				 e.nch_no,e.father_name, e.status\n"
						+ "from reg_nch_form_a_p  e\n"
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
//		  				list.add(rs.getString("reg_no"));//24
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

		  
		  

		@Override
		public List<Map<String, Object>> getDataByUserNameForDraft(int userid) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			try {

				conn = dataSource.getConnection();
				
				
				q="select first_name,father_name,to_char(dob ,'DD/MM/YYYY') as dob,gender,mo_no,email_id,aadhaar_no,\n"
						+ "pre_state,per_state,pre_address_details1,pre_address_details2,pre_address_details3,\n"
						+ "per_address_details1,per_address_details2,per_address_details3,per_district,pre_district,pre_pincode,\n"
						+ "per_pincode,to_char(date_of_reg ,'DD/MM/YYYY') as date_of_reg ,\n"
						+ "valid_up_to,photo_path,alt_mo_no1,alt_mo_no2,alt_email_id1,alt_email_id2,curr_address\n"
						+ ",curr_address2,curr_address3,curr_state,curr_district,curr_pincode,check_address,nrh_en_no,ayush_id,reg_state ,pay_status from reg_nch_form_a_p \n"
						+ "where  user_id = ? order by id DESC limit 1";


				PreparedStatement stmt = conn.prepareStatement(q);
				stmt.setInt(1, userid);
				ResultSet rs = stmt.executeQuery();
				System.err.println("inn-----" +stmt);
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
				query="SELECT state_name,aadhaar_no,email_id FROM public.reg_nch_registration_a r\n"
						+ "inner join edu_lms_state_mstr s on s.state_id = r.regisration_state::integer where r.user_id = ? order by r.id DESC limit 1";	
				stmt = conn.prepareStatement(query);
				stmt.setInt(1,data);
				ResultSet rs = stmt.executeQuery();
	            
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
				query="select id from reg_nch_form_a_p where user_id = ? order by id desc limit 1\n"
						+ "";	
				stmt = conn.prepareStatement(query);
				stmt.setInt(1,data);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
	 	           whr=rs.getString("id");             	
	 	        }
				 
	 		    rs.close();
	 	    	stmt.close();
	 			conn.close();
	     	} catch (SQLException e) {
	     			e.printStackTrace();
	     	}	
			return whr;
		}

		//HET
		
		//HET
		
		
				@Override
				public ArrayList<ArrayList<String>> medicalData(int data) {
					ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
					Connection conn = null;
					String q = "";
					try {

						conn = dataSource.getConnection();
						
						
			 		
						q="select c.id,name_of_institute,month_and_year_of_degree,type_of_degree,degree_name ,c.status ,r.pay_status \n"
								+ "from reg_nch_form_a_p r\n"
								+ "inner join reg_nch_med_degree_dtl_a_ch c on c.regulation_p_id = r.id\n"
								+ "where (r.status = 0 or r.status = 1 or r.status = 2) and r.id = (SELECT id from reg_nch_form_a_p where user_id = ? order by id DESC limit 1)\n"
								+ "";	
							System.err.println("q-------"+q);
						PreparedStatement stmt = conn.prepareStatement(q);
						stmt.setInt(1, data);
						 System.err.println("q-med------"+stmt);
						ResultSet rs = stmt.executeQuery();
			            
						ResultSetMetaData metaData = rs.getMetaData();
						int columnCount = metaData.getColumnCount();
						while (rs.next()) {

							ArrayList<String> list = new ArrayList<String>();
							list.add(rs.getString("id"));// 0
							list.add(rs.getString("name_of_institute"));// 1
							//list.add(rs.getString("attachment_path"));// 2
							list.add(rs.getString("month_and_year_of_degree"));// 2
							list.add(rs.getString("type_of_degree"));// 3
							list.add(rs.getString("degree_name"));// 4
							list.add(rs.getString("status"));// 5
							list.add(rs.getString("pay_status"));// 5

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
						
						
			 		
//						q="select\n"
//								+ " h.id,place_of_working,landline,email,authority_type,name_of_res_p\n"
//								+ ",place_of_working_name\n"
//								+ ",hos_address1\n"
//								+ ",hos_address2\n"
//								+ ",hos_address3\n"
//								+ ",hos_state\n"
//								+ ",hos_district\n"
//								+ ",mobile_no\n"
//								+ ",to_char(date_pract_from ,'DD/MM/YYYY') as date_pract_from,to_char(date_pract_to ,'DD/MM/YYYY') as  date_pract_to\n"
//								+ "\n"
//								+ "from reg_nch_form_a_p r\n"
//								+ "\n"
//								+ "inner join reg_nch_working_place_dtl_a_ch h on h.regulation_p_id = r.id \n"
//								+ "\n"
//								+ "where (r.status = 0 or r.status = 1) and r.id = (SELECT id from reg_nch_form_a_p where user_id = ? order by id DESC limit 1)";	
						
						
						q="select h.id,place_of_working,adjunct_place,landline,email,authority_type,name_of_res_p,place_of_working_name,hos_address1,hos_address2,hos_address3,hos_state\n"
								+ ",hos_district\n"
								+ ",mobile_no\n"
								+ ",to_char(date_pract_from ,'DD/MM/YYYY') as date_pract_from,to_char(date_pract_to ,'DD/MM/YYYY') as  date_pract_to\n"
								+ "\n"
								+ "from reg_nch_form_a_p r\n"
								+ "\n"
								+ "inner join reg_nch_working_place_dtl_a_ch h on h.regulation_p_id = r.id \n"
								+ "\n"
								+ "where (r.status = 0 or r.status = 1 or r.status = 2) and r.id = (SELECT id from reg_nch_form_a_p where user_id = ? order by id DESC limit 1)";

						PreparedStatement stmt = conn.prepareStatement(q);
						stmt.setInt(1, data);
						   
						ResultSet rs = stmt.executeQuery();
			           
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
							list.add(rs.getString("adjunct_place"));// 15
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
				public List<Map<String, Object>> getdegreedetailss(String DegreeName) {
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					Connection conn = null;
					String q = "";
					try {

						conn = dataSource.getConnection();
						
//						---------------------------------11-07-22 urmik_changes
			 		
						q="select d.id,d.degree_name "
								+ "from edu_lms_degree_mstr d \n"
								+ "left join edu_lms_system_degree_map_mstr s on  s.degree_name   = d.id\n"
								+ "where d.type_of_degree=? and s.system_name='45' order by id ";	

						PreparedStatement stmt = conn.prepareStatement(q);
						
						stmt.setInt(1, Integer.parseInt(DegreeName));
						
						ResultSet rs = stmt.executeQuery();
						
			             System.err.println("stmt-----urmik check1-----"+stmt);
			             
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
				public List<Map<String, Object>> getayusAbhaDatalistStudent(String userId) {
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					Connection conn = null;
					String q = "";
					try {

						conn = dataSource.getConnection();
						
						
//			 		
//						q="select d.id,d.ayush_id,d.abha_no,d.name from reg_nch_details_a d "
//								+ " where d.user_id=?  order by id ";	

						 //q="select d.id,d.ayush_id,d.abha_no,d.name,* from reg_nch_details_a d  where d.aadhar_card=?  order by id ";
						
						q=" select d.id,d.ayush_id,d.abha_no,d.name,to_char(date(d.admission_date),'yyyy') as fire, dd.degree_name,t.type_of_degree, i.institute_name, u .university_name  , t.id as tid, d.degree ,* \n"
								+ " from reg_nch_details_a d \n"
								+ " inner join edu_lms_degree_mstr dd on  (dd.id )  =  d.degree\n"
								+ " inner join edu_lms_institute_reg i on  (i.id )  =  d.institude_userid \n"
								+ "  inner join edu_lms_type_of_degree_mstr t on dd.type_of_degree  =  t.id\n"
								+ "  inner join edu_lms_university_mstr u on u.id  =  d.university_userid\n"
								+ " where d.aadhar_card=? order by d.id  " ;
							
						
						
					
						
						
						PreparedStatement stmt = conn.prepareStatement(q);
						stmt.setString(1, userId);
						ResultSet rs = stmt.executeQuery();
						System.err.println("stt--dsds---"+stmt);
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
				public List<Map<String, Object>> getayusAbhaDatalist(String userId) {
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					Connection conn = null;
					String q = "";
					try {

						conn = dataSource.getConnection();
						
						
			 		
//						q="select d.id,d.aayushid,d.abha_no,d.name from reg_nch_details_a d "
//								+ " where d.user_id=?  order by id ";	
//--01-03-2023
						q="select d.id,d.ayush_id,d.abha_no,d.name,to_char(TO_DATE(d.admission_date,'dd/mm/yyyy'),'yyyy/mm/dd') as fire2,d.state_reg_num as statenum,d.reg_state,* from reg_nch_details_a d  where d.aadhar_card=?  order by id ";
						
						PreparedStatement stmt = conn.prepareStatement(q);
						stmt.setString(1, userId);
						System.err.println( "urmikkkkkkk-admisson date---"+stmt);
						ResultSet rs = stmt.executeQuery();
						
						
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
				public List<Map<String, Object>> medicalDataChildAttachment(int userid) {
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					Connection conn = null;
					String q = "";
					try {

						conn = dataSource.getConnection();
						
						
			  	
						
						
//						------------read only in renew 04-05-22 time 
						
						q="select ac.id, ac.parent_id, ac.name_of_attachment, ac.attachment from reg_nch_med_degree_dtl_a_ch md\n"
								+ "inner join reg_nch_form_a_p e on e.id = md.regulation_p_id\n"
								+ "inner join reg_nch_med_degree_dtl_a_sub_ch ac on ac.parent_id = md.id\n"
								+ "where user_id = ? order by e.id \n"
								+ "";


						PreparedStatement stmt = conn.prepareStatement(q);
						stmt.setInt(1, userid);
						
						ResultSet rs = stmt.executeQuery();
			            // System.err.println("stmt-----hhhhh-----"+stmt);
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

				           
				
				//--download individual attachment 
				public String getIndivisualAttachmentPath(int id) {
				 String whr = "";
				Connection conn = null;
				try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				String query = null;
				query = "select STRING_AGG ( attachment, ',' ) as attachment_path  from reg_nch_med_degree_dtl_a_sub_ch where id = ?";
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
				whr = rs.getString("attachment_path");
				}
				rs.close();
				stmt.close();
				conn.close();
				} catch (SQLException e) {
				e.printStackTrace();
				}
				return whr;
				}
				
				
				public String get_AttachmentIndividual(int id) {

					String whr = "";
					Connection conn = null;
					try {
						conn = dataSource.getConnection();
						PreparedStatement stmt = null;
						String query = null;
						query = "select STRING_AGG ( attachment, ',' ) as attachment  from reg_nch_med_degree_dtl_a_sub_ch where id = ?";

						stmt = conn.prepareStatement(query);
						stmt.setInt(1, id);
						ResultSet rs = stmt.executeQuery();

						while (rs.next()) {
							whr = rs.getString("attachment");
						}
						rs.close();
						stmt.close();
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return whr;
				}
				
				
				
				
				
				
				
}
