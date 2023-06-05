package com.AyushEdu.dao.Regulation;

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

import com.AyushEdu.Models.Regulation.REG_NCH_FORM_A_P;
import com.AyushEdu.Models.Regulation.REG_NCH_MED_DEGREE_DTL_A_CH;
@Repository
public class Regulation_University_pre_DAOImpl implements Regulation_University_pre_DAO {
	
	private static final Session sessionHQL = null;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	public REG_NCH_FORM_A_P getReg_PreByid(int id) {
		Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 REG_NCH_FORM_A_P updateid = (REG_NCH_FORM_A_P) session.get(REG_NCH_FORM_A_P.class, id);
         session.getTransaction().commit();
         session.close();                
        return updateid;
  }
	
//	  -----------------------------View_Img Path---------------------------------------------------------
	  
	  @Override
		public String getImagePath1(String id) {
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
	  
//	  -------------------------------Excel download--------------------------------------
	  
	  public ArrayList<ArrayList<String>> pdf_getAuth_and_Posted_StrenghReportPreviewDataList()
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
					+ "				to_char(e.dob , 'DD/MM/YYYY') as dob,c.name as nationality,e.degree,e.university,e.month_year, to_char(e.date_of_reg , 'DD/MM/YYYY') as date_of_reg,e.name_reg,e.reg_renew_permanent,\n"
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
	  				//list.add(rs.getString("reg_no"));//24
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
		public List<Map<String, Object>> getDataByUserNameForDraftPreview(int userid) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			try {

				conn = dataSource.getConnection();

 
				q="select e.id,n.nationality, first_name,father_name,to_char(dob ,'DD/MM/YYYY') as dob,gender,mo_no,email_id,aadhaar_no,\n"
						+ "pre_state,ss.state_name as per_state,s.state_name as pre_state,pre_address_details1,pre_address_details2,pre_address_details3,\n"
						+ "per_address_details1,per_address_details2,per_address_details3,dd.district_name as per_district,d.district_name as pre_district,pre_pincode,\n"
						+ "per_pincode,curr_address,curr_address2,curr_address3,sss.state_name as curr_state,ddd.district_name as curr_district,curr_pincode,to_char(date_of_reg ,'DD/MM/YYYY') as date_of_reg,\n"
						+ "valid_up_to,photo_path,alt_mo_no1,alt_mo_no2,alt_email_id1,alt_email_id2\n"
						+ "from reg_nch_form_a_p e\n"
						+ "inner join recr_nationality_mst n on  (n.nationality_id)  = e.nationality\n"
						+ "inner join edu_lms_district_mstr d on  (d.district_id )  = e.pre_district\n"
						+ "inner join edu_lms_state_mstr ss on (ss.state_id)  = e.per_state\n"
						+ "inner join edu_lms_state_mstr s on  (s.state_id)  = e.pre_state\n"
						+ "inner join edu_lms_state_mstr sss on  (sss.state_id)  = e.curr_state\n"
						+ "inner join edu_lms_district_mstr dd on  (dd.district_id)  = e.per_district  \n"
						+ "inner join edu_lms_district_mstr ddd on  (ddd.district_id)  = e.curr_district\n"
						+ "where  e.user_id = ? order by id DESC limit 1";


				PreparedStatement stmt = conn.prepareStatement(q);
				stmt.setInt(1, userid);
				
				ResultSet rs = stmt.executeQuery();
	             System.err.println("stmt-----getDataByUserNameForDraftPreview-----"+stmt);
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}
					
					columns.put("img","<img class='d-block img5050 imageZomm' alt='No Image' id='myImg"+rs.getString("id")+"' src='MedicalImagePath11?i_id="+rs.getString("id")+"' onclick='imageView("+rs.getString("id")+");' />");
					//list.add(rs.getString("institute_name"));//4
					
					
					
					
				
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
	  
//		@Override
//		public List<Map<String, Object>> CheckNRHPreview(int data) {
//			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//			Connection conn = null;
//			String q = "";
//			try {
//
//				conn = dataSource.getConnection();
//				
//				
//	 		
//				q="select registration from reg_nch_registration_a where user_id = ?";	
//
//				PreparedStatement stmt = conn.prepareStatement(q);
//				stmt.setInt(1, data);
//				
//				ResultSet rs = stmt.executeQuery();
//	             System.err.println("stmt-----hhhhh-----"+stmt);
//				ResultSetMetaData metaData = rs.getMetaData();
//				int columnCount = metaData.getColumnCount();
//				while (rs.next()) {
//					
//					Map<String, Object> columns = new LinkedHashMap<String, Object>();
//					for (int i = 1; i <= columnCount; i++) {
//						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
//					}
//					
//				
//					list.add(columns);
//				 
//					
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
//					}
//				}
//			}
//			return list;
//		}
		
		@Override
		public List<Map<String, Object>> RegAuthPreview(int data) {
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
		public String getUserIdPreview(int data) {
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
		
		@Override
		public ArrayList<ArrayList<String>> medicalDataPreview(int data  ,int d) {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			try {

				conn = dataSource.getConnection();
				
 
				
				q="select c.id,name_of_institute,month_and_year_of_degree,td.type_of_degree,d1.degree_name , "
						
						+ " CASE WHEN c.status = 1 THEN 'Verified'  "
						+"  WHEN c.status = 2 THEN 'Reverted'  "
						+ "  ELSE 'Not Verified' "
						+ "  END AS status  ,u.university_name "
						+ "from reg_nch_form_a_p r\n"
						+ "inner join reg_nch_med_degree_dtl_a_ch c on c.regulation_p_id = r.id\n"
						+ "inner join edu_lms_degree_mstr d1 on d1.id=c.degree_name\n"
						+ "inner join edu_lms_type_of_degree_mstr td on td.id=d1.type_of_degree "
						+" inner join edu_lms_university_mstr u on cast(u.id as character varying )=c.name_of_institute  "
						+ "where  r.id = (SELECT id from reg_nch_form_a_p where user_id = ? order by id DESC limit 1) and c.name_of_institute =? \n"
						+ "";	

				PreparedStatement stmt = conn.prepareStatement(q);
				stmt.setInt(1, data);
				stmt.setString(2, String.valueOf(d));
				
				ResultSet rs = stmt.executeQuery();
	             System.err.println("stmt-----fg-----"+stmt);
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				while (rs.next()) {

					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("id"));// 0
					list.add(rs.getString("name_of_institute"));// 1
//					list.add(rs.getString("attachment_path"));// 2
					list.add(rs.getString("month_and_year_of_degree"));// 2
					list.add(rs.getString("type_of_degree"));// 3
					list.add(rs.getString("degree_name"));// 4
					list.add(rs.getString("status"));// 5
					list.add(rs.getString("university_name"));// 5
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
		public ArrayList<ArrayList<String>> HospitalDataPreview(int data  ) {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			try {

				conn = dataSource.getConnection();
				
				
	 		
//				q="select\n"
//						+ " h.id,place_of_working,landline,email,authority_type,name_of_res_p\n"
//						+ ",place_of_working_name\n"
//						+ ",hos_address1\n"
//						+ ",hos_address2\n"
//						+ ",hos_address3\n"
//						+ ",hos_state\n"
//						+ ",hos_district\n"
//						+ ",mobile_no\n"
//						+ ",to_char(date_pract_from ,'DD/MM/YYYY') as date_pract_from,to_char(date_pract_to ,'DD/MM/YYYY') as  date_pract_to\n"
//						+ "\n"
//						+ "from reg_nch_form_a_p r\n"
//						+ "\n"
//						+ "inner join reg_nch_working_place_dtl_a_ch h on h.regulation_p_id = r.id \n"
//						+ "\n"
//						+ "where (r.status = 0 or r.status = 1) and r.id = (SELECT id from reg_nch_form_a_p where user_id = ? order by id DESC limit 1)";	
				
				
//				
				q="select h.id,ro.name_of_res_owner as authority_type,ss.state_name as hos_state,pw.place_of_working_practitioner,landline,email,authority_type,"
						+ "name_of_res_p,place_of_working_name,adjunct_place,hos_address1,hos_address2,hos_address3,dd.district_name as hos_district\n"
						+ ",mobile_no,to_char(date_pract_from ,'DD/MM/YYYY') as date_pract_from,to_char(date_pract_to ,'DD/MM/YYYY') as  date_pract_to\n"
						+ "from reg_nch_form_a_p r\n"
						+ "inner join reg_nch_working_place_dtl_a_ch h on h.regulation_p_id = r.id \n"
						+ "inner join edu_lms_regulation_place_of_working_mstr pw on pw.id = h.place_of_working\n"
						+ "inner join edu_lms_state_mstr ss on (ss.state_id)  = h.hos_state\n"
						+ "inner join edu_lms_district_mstr dd on  (dd.district_id)  = h.hos_district\n"
						+ "inner join edu_lms_regulation_name_of_res_owner_mstr ro on ro.id  = h.authority_type \n"
						+ "\n"
						+ "where (r.status = 0 or r.status = 1 or r.status = 2) and r.id = (SELECT id from reg_nch_form_a_p where user_id = ? order by id DESC limit 1)";	

				PreparedStatement stmt = conn.prepareStatement(q);
				stmt.setInt(1, data);
				
				ResultSet rs = stmt.executeQuery();
	             System.err.println("stmt-----hhhhospitalhh-----"+stmt);
				ResultSetMetaData metaData = rs.getMetaData();
				while (rs.next()) {

					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("id"));// 0
					list.add(rs.getString("place_of_working_practitioner"));// 13
				//	list.add(rs.getString("place_of_working"));// 1
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
					//list.add(rs.getString("place_of_working_practitioner"));// 13
					list.add(rs.getString("place_of_working_name"));// 1
					list.add(rs.getString("adjunct_place"));// 1
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
		public List<Map<String, Object>> getdegreedetailssPreview(String DegreeName) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			
			System.err.println("degreenamedao"+DegreeName);
			try {

				conn = dataSource.getConnection();
				
//				---------------------------------11-07-22 urmik_changes
	 		
				q="select d.id,d.degree_name\n"
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
		public List<Map<String, Object>> getayusAbhaDatalistPreview(String userId) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			try {

				conn = dataSource.getConnection();
				
				
	 		
				q="select d.id,d.aayushid,d.abha_no,d.name from reg_nch_registration_a d "
						+ " where d.user_id=?  order by id ";	
				 System.err.println("stmt-----aaddd-----"+q);
				PreparedStatement stmt = conn.prepareStatement(q);
				
				stmt.setInt(1, Integer.parseInt(userId));
				
				ResultSet rs = stmt.executeQuery();
				
	             System.err.println("stmt-----aaddd-----"+stmt);
	             
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
		public List<Map<String, Object>> medicalDataChildAttachmentPreview(int userid) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			try {

				conn = dataSource.getConnection();
					
				q="select ac.id, ac.parent_id, ac.name_of_attachment, ac.attachment from reg_nch_med_degree_dtl_a_ch md\n"
						+ "inner join reg_nch_form_a_p e on e.id = md.regulation_p_id\n"
						+ "inner join reg_nch_med_degree_dtl_a_sub_ch ac on ac.parent_id = md.id\n"
						+ "where user_id = ? order by e.id \n"
						+ "";


				PreparedStatement stmt = conn.prepareStatement(q);
				stmt.setInt(1, userid);
				
				ResultSet rs = stmt.executeQuery();
	             System.err.println("stmt-----WWW-----"+stmt);
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

/////////////////////download/////////
public String getFilePathQuery_popup1(int id) {
	System.err.println("dao id ===================="+id);

String whr = "";
Connection conn = null;
try {
conn = dataSource.getConnection();
PreparedStatement stmt = null;
String query = null;
query = "select STRING_AGG ( attachment, ',' ) as attachment_path  from reg_nch_med_degree_dtl_a_sub_ch where parent_id = ?";

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


public String University_Approve_DegreeData(String a ,String status ,String u_id,int u_id_n  ,String cp) {
	String[] id_list = a.split(",");
	Connection conn = null;
	Connection conn1 = null;
	Connection conn2 = null;
	int out = 0;
	int out1 = 0;
	int out2 = 0;
	String q="";
	String freg = "";
	
 	
	Session sessionHQL = this.sessionFactory.openSession();
	Transaction  tx= sessionHQL.beginTransaction();
	
 System.err.println("count_p-dao---"+cp);
 //end
 	try {
		
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		
		conn1 = dataSource.getConnection();
		PreparedStatement stmt1 = null;
		
		conn2 = dataSource.getConnection();
		PreparedStatement stmt2 = null;
		
			for (int i = 0; i < id_list.length; i++) {
				int id = Integer.parseInt(id_list[i]);
				System.err.println("id-------------"+u_id);
				System.err.println("a-------------"+a);
				REG_NCH_FORM_A_P SS = (REG_NCH_FORM_A_P) sessionHQL .get(REG_NCH_FORM_A_P.class, Integer.parseInt(u_id));
				int user_id= 	SS.getUser_id() ;
				 System.err.println("user_id ====="+user_id);
				 
	if(Integer.parseInt(cp) >= 4) {
 	  //main tbl 
	    stmt1 = conn1.prepareStatement(" update reg_nch_form_a_p set   institute_status= ?  , state_status=0  ,role=27   where id=?");
		stmt1.setInt(1, Integer.parseInt(status));
		stmt1.setInt(2, Integer.parseInt(u_id));
		out1 = stmt1.executeUpdate();
		
		 //parent tbl 
		stmt = conn.prepareStatement(" update reg_nch_med_degree_dtl_a_ch set   status= ?       where id=?");
		stmt.setInt(1, Integer.parseInt(status));
		stmt.setInt(2,  id);
		out = stmt.executeUpdate();
 
		//role info
		 stmt2 = conn2.prepareStatement("update userroleinformation set role_id=?  where user_id=?");
		 System.err.println("st------"+stmt);
		 stmt2.setInt(1,  (27));
		 stmt2.setInt(2, user_id);
	      out2 = stmt2.executeUpdate();
  }
	else {
		stmt1 = conn1.prepareStatement(" update reg_nch_form_a_p set   institute_status= ?  , state_status=0     where id=?");
		stmt1.setInt(1, Integer.parseInt(status));
		stmt1.setInt(2, Integer.parseInt(u_id));
		out1 = stmt1.executeUpdate();
		 stmt = conn.prepareStatement(" update reg_nch_med_degree_dtl_a_ch set   status= ?       where id=?");
		stmt.setInt(1, Integer.parseInt(status));
 		stmt.setInt(2,  id);
		out = stmt.executeUpdate();
	}
				
				//STATIC CODE PL CHANGE IT
//				if(u_id_n == 25)
//				{
//					stmt1 = conn1.prepareStatement(" update reg_nch_form_a_p set   institute_status= ?      where id=?");
//					stmt1.setInt(1, Integer.parseInt(status));
//					stmt1.setInt(2, Integer.parseInt(u_id));
//					out1 = stmt1.executeUpdate();
//	 				stmt = conn.prepareStatement(" update reg_nch_med_degree_dtl_a_ch set   status= ?       where id=?");
//					stmt.setInt(1, Integer.parseInt(status));
//			 		stmt.setInt(2,  id);
//					out = stmt.executeUpdate();
//				}
//				if(u_id_n != 25)
//				{
//					stmt1 = conn1.prepareStatement(" update reg_nch_form_a_p set   institute_status= ?  , state_status=0     where id=?");
//					stmt1.setInt(1, Integer.parseInt(status));
//					stmt1.setInt(2, Integer.parseInt(u_id));
//					out1 = stmt1.executeUpdate();
//	 				stmt = conn.prepareStatement(" update reg_nch_med_degree_dtl_a_ch set   status= ?       where id=?");
//					stmt.setInt(1, Integer.parseInt(status));
//			 		stmt.setInt(2,  id);
//					out = stmt.executeUpdate();
// 				}
			
					 
 
				}
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
	if (out > 0) {
			if(status.equals("1")) {
			return "Approved Successfully.";
			}
			else
				return "Not Approved Successfully";
		} else {
			if(status.equals("1")) {
				return "Not Approved Successfully.";
				}
			else
			return "UnSuccessfully";
		}
}

	
	public String University_Reject_DegreeData(String a,String status,String reject_remarks) {
		String[] id_list = a.split(",");
 
		Connection conn = null;
		Integer out = 0;
		Integer out1 = 0;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			PreparedStatement stmt1 = null;
				for (int i = 0; i < id_list.length; i++) {
					int id = Integer.parseInt(id_list[i]);
					Session sessionHQL = this.sessionFactory.openSession();
					REG_NCH_MED_DEGREE_DTL_A_CH assetupd =(REG_NCH_MED_DEGREE_DTL_A_CH)sessionHQL.get(REG_NCH_MED_DEGREE_DTL_A_CH.class, id); 
					
					stmt = conn.prepareStatement(" update reg_nch_med_degree_dtl_a_ch set  status=?,reject_remarks = ?  where id=?");
					stmt.setInt(1, Integer.parseInt(status));
					stmt.setString(2, reject_remarks);
					stmt.setInt(3, id);
					 
					int p_id = assetupd.getRegulation_p_id();
 				 
					stmt1 = conn.prepareStatement(" update reg_nch_form_a_p set status=? , institute_status=?,reject_remarks = ?  where id=?");
					stmt1.setInt(1, Integer.parseInt(status));
					stmt1.setInt(2, Integer.parseInt(status));
					stmt1.setString(3, reject_remarks);
					stmt1.setInt(4, p_id);
					
					out1 = stmt1.executeUpdate();
					out = stmt.executeUpdate();
					 
					
		 }

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

		if (out > 0) {
			    if(status.equals("2")) {
				return "Revert Back Successfully";
				}
			else
				return "UnSuccessfully";
		} else {
			   if(status.equals("2")) {
				return "Revert Back not Successfully";
				}
			else
			return "UnSuccessfully";
		}
	}



@Override
public ArrayList<ArrayList<String>> get_degrrename_Reject_idata(String id) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
    Connection conn = null;
    try{          
    	conn = dataSource.getConnection();
    	
//    	String 	sq1="SELECT  degree_name FROM public.reg_nch_med_degree_dtl_a_ch WHERE id = ?";
//    	
    	String sq1 = " SELECT  d1.degree_name FROM  reg_nch_med_degree_dtl_a_ch c "
    			+ " inner join edu_lms_degree_mstr d1 on d1.id=c.degree_name "
    			+ " WHERE c.id = ?";
        PreparedStatement stmt = conn.prepareStatement(sq1);
        stmt.setInt(1, Integer.parseInt(id));
       
        ResultSet rs = stmt.executeQuery();  
        
        String str1="";
        while(rs.next()){
        	ArrayList<String> list = new ArrayList<String>();
			//	list.add(rs.getString("type_of_degree"));// 0
				list.add(rs.getString("degree_name"));// 1
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


@Override
public String getFilePathQueryForDoc(String id) {

	String whr = "";
	String q1 = "";
	Connection conn = null;
	String fildname1 = "";
//	if(val.equals("1")) {
//		q1="persdetails";
//	}
//	else if(val.equals("2")){
//		q1="edudetails";
//	}
//	
//	if (fildname.equals("resumedoc")) {
//		fildname1 = "resumedoc";
//	}
//	else if (fildname.equals("identitydoc")) {
//		fildname1 = "identitydoc";
//	}
//	else if (fildname.equals("3")) {
//		fildname1 = "identitydoc";
//	}


	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
		String query = null;
		
		query="select attachment from reg_nch_med_degree_dtl_a_sub_ch where id=? or parent_id=?";
		// query = query.replace("$fildname", fildname);

		stmt = conn.prepareStatement(query);
		stmt.setInt(1, Integer.parseInt(id));
		stmt.setInt(2, Integer.parseInt(id));
System.err.println("urmik--------------"+stmt);
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

@Override
public ArrayList<ArrayList<String>> getattfilesToPreviewD(String userid,String id) {
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
    Connection conn = null;
    try{          
    	conn = dataSource.getConnection();

    	String sq1 = " select ac.id, ac.parent_id, ac.name_of_attachment, ac.attachment from reg_nch_med_degree_dtl_a_ch md\n"
    			+ "inner join reg_nch_form_a_p e on e.id = md.regulation_p_id\n"
    			+ "inner join reg_nch_med_degree_dtl_a_sub_ch ac on ac.parent_id = md.id\n"
    			+ "where parent_id=? order by e.id";
    	
        PreparedStatement stmt = conn.prepareStatement(sq1);
//        stmt.setInt(1, Integer.parseInt(userid));
        stmt.setInt(1, Integer.parseInt(id));
        
        System.err.println("STMT----getattfilesToPreviewD----"+stmt);
        
        ResultSet rs = stmt.executeQuery();  
        
        String str1="";
        while(rs.next()){
        	ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("attachment"));// 0
				list.add(rs.getString("id"));// 1
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
