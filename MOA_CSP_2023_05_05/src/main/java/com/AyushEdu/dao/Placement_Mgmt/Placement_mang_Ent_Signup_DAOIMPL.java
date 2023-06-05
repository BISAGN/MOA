package com.AyushEdu.dao.Placement_Mgmt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
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

import com.AyushEdu.Models.Placement_Mgmt.EDU_PLACEMENT_COMPANY_REG;
import com.AyushEdu.Models.Placement_Mgmt.EDU_PLACEMENT_STUDENT_TO_ENTERPRISE;
@Repository
public class Placement_mang_Ent_Signup_DAOIMPL implements Placement_mang_Ent_Signup_DAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	@Override
	public List<Map<String, Object>> DataTableEdu_Reg_Report_placement_mang_enterprise(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String company_name,String name,String email_id,String mobile_no,String ph_no,String address ,String state ,
			String per_district,String pincode,String hours_from ,String hours_to,String web_url,String photo_path,String photo_path_pic,String userid) throws ParseException {
		String Status = "1";
		String SearchValue = GenerateQueryWhereClause_SQL(Search,company_name,name,email_id,mobile_no,ph_no ,address ,state ,per_district ,pincode ,hours_from,hours_to,web_url,photo_path,photo_path_pic,Status);
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
			
			q= "select e.id,e.company_name,e.name,e.email_id,e.notified,e.mobile_no,e.ph_no,e.address,ss.state_name as state,sd.district_name as per_district,e.pincode,e.hours_from,e.hours_to,\n"
					+ "e.web_url,e.photo_path,e.photo_path_pic from edu_placement_company_reg e inner join edu_lms_state_mstr ss on (ss.state_id)  = e.state\n"
					+ "inner join edu_lms_district_mstr sd on (sd.district_id)  = e.per_district\n"
					+ "where id not in \n"
					+ "(select enterprise_id from edu_placement_student_to_enterprise where student_id = ?) and e.id!= 0"
					 +SearchValue+ "  group by 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15 order by id " + orderType
		                + " limit " + pageL + " OFFSET " + startPage;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,company_name,name,email_id,mobile_no,ph_no ,address ,state ,per_district ,pincode ,hours_from,hours_to,web_url,photo_path,photo_path_pic,Status,userid);
			System.err.println("sstmt - "+stmt);
			ResultSet rs = stmt.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			
			while (rs.next()) {
				
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
//				String fname = rs.getString("first_name");
				columns.put("img","<img class='d-block img5050 imageZomm' alt='No Image' id='myImg"+rs.getString("id")+"' src='MedicalImagePathplace1?i_id="+rs.getString("id")+"' onclick='imageView("+rs.getString("id")+");' />");//	
				columns.put("img1","<img class='d-block img5050 imageZomm' alt='No Image' id='myImg6"+rs.getString("id")+"' src='MedicalImagePathplace3?i_id6="+rs.getString("id")+"' onclick='imageView("+rs.getString("id")+");' />");//				 
				
			//start pdf
				 
				String Download = "";
				String chekboxaction="";

				Download = "<i class='fa fa-file-pdf-o' " + Download + " title='Download PDF' ></i>";
				 String Downloadnote = "onclick=\"  if (confirm('Are You Sure You Want to Download Notes ?') ){downloadnote_file("
										+ rs.getInt("id") + ", 'other_note' )}else{ return false;}\"";
					
				 	String st =	rs.getString(10) ;
					String Checkbox="<input class='nrCheckBox' type='checkbox' id='" + rs.getObject(1)//13
					+ "' name='cbox'  /> <input type='hidden' id='nrCHid"+rs.getObject(1)+"' value='"+st+"'> <input type='hidden' id='email"+rs.getObject(1)+"' value='"+rs.getObject(4)+"'> <input type='hidden' id='notif"+rs.getObject(1)+"' value='"+rs.getObject(5)+"'>" ;
					
					 String CheckboxId="<input  type='hidden' id='id" + rs.getObject(1) + "' name='id" + rs.getObject(1)//14
						+ "' value='" + rs.getObject(1) + "'   />";
				     chekboxaction+=Checkbox; 
			//end
				columns.put("chekboxaction", chekboxaction);
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
	public long DataTableEdu_Reg_Report_masterDataTotalCount_placement_mang_enterprise(String Search, String company_name,String name,String email_id,String mobile_no,String ph_no,String address ,String state ,
			String per_district,String pincode,String hours_from ,String hours_to,String web_url,String photo_path,String photo_path_pic,String userid) throws ParseException {
		String Status = "1";
		String SearchValue = GenerateQueryWhereClause_SQL(Search, company_name,name,email_id,mobile_no,ph_no ,address ,state ,per_district ,pincode ,hours_from,hours_to,web_url,photo_path,photo_path_pic,Status);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q="select count(*)  from ("
					+"	select e.id,e.company_name,e.name,e.email_id,e.mobile_no,e.ph_no,e.address,e.state,e.per_district,e.pincode,e.hours_from,e.hours_to,\n"
					+ "e.web_url,e.photo_path,e.photo_path_pic from edu_placement_company_reg e where id not in (select enterprise_id from edu_placement_student_to_enterprise where student_id = ?) and e.id!= 0  \n"
						+ " "+SearchValue+" group by 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15) a ";
				 
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,company_name,name,email_id,mobile_no,ph_no,address,state,per_district,pincode,hours_from,hours_to,web_url,photo_path,photo_path_pic,Status,userid);
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
	
	@SuppressWarnings("null")
	public String GenerateQueryWhereClause_SQL(String Search,String company_name,String name,String email_id,String mobile_no,String ph_no,String address ,String state,
			String per_district,String pincode,String hours_from ,String hours_to,String web_url,String photo_path,String photo_path_pic, String Status) throws ParseException {
	
		String SearchValue = "";
		
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (upper(e.company_name) like ?"
					+ " or upper(e.name) like ?  "
					+ " or upper(e.email_id) like ? "
					+ " or e.mobile_no like ?  "
					+ " or upper(e.address) like ?  "
					+ " or  cast(e.state as character varying)  like ? "
					+ "  or cast( e.per_district as character varying)   like ?  "
					+ " or  e.pincode  like ? "
//					+ "  or upper(e.product)  like ? "
					+ ")";
		}
		
		if (!company_name.trim().equals("")) {  
			SearchValue += " and  (company_name) like  upper(?)  ";
		}
		if (!name.equals("") &&  name!= null && !name.trim().equals("")) {  
			SearchValue += " and  (name) like  upper(?)  ";
		}
		if (!email_id.equals("") &&  email_id!= null && !email_id.trim().equals("")) {  
			SearchValue += " and  (email_id) like  upper(?)  ";
		}
	 
		if (!mobile_no.equals("") && mobile_no !=null && !mobile_no.trim().equals(" ") ) {
			SearchValue += " and (mobile_no) like ? ";
		}
		
		if (!address.equals("") && address !=null && !address.trim().equals(" ") ) {  
			SearchValue += " and  (address) like  upper(?)  ";
		}
		if (!state.equals("0") && state != null && !state.trim().equals(" ")) {
			SearchValue += " and e.state  = ?::integer  ";
		}
		if (!per_district.equals("0") && per_district != null  && !per_district.trim().equals(" ")) {
			SearchValue += " and e.per_district  = ?::integer  ";
		}
		if ( !pincode.equals("") && pincode !=null && !pincode.trim().equals(" ") ) {
			SearchValue += " and (pincode) like ? ";
		}
//		if (!product.equals("") && product !=null && !product.trim().equals(" ")) {  
//			SearchValue += " and  (product) like  upper(?)  ";
//		}
		if (!web_url.equals("") && web_url !=null && !web_url.trim().equals(" ")) {  
			SearchValue += " and  (web_url) like  upper(?)  ";
		}
		
		if (!Status.equals("") && Status !=null && !Status.trim().equals(" ")) {  
			SearchValue += " and  e.status = ?  ";
		}
 
		
				
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search,String company_name,String name,String email_id,String mobile_no,String ph_no,String address ,String state,
			String per_district,String pincode,String hours_from ,String hours_to,String web_url,String photo_path,String photo_path_pic, String Status,String userid) {
		int flag = 0;
		try {
			if (!userid.equals("")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(userid));
			}
			
			if (Search!=null &&  !Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search  + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search  + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search  + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search  + "%");
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}
			
			if (!company_name.equals("") && company_name != null) {
				flag += 1;
				stmt.setString(flag,  "%" + company_name.toUpperCase() + "%" );
			}
			
			if (!name.equals("") &&  name!= null && !name.trim().equals("")) {  
				flag += 1;
				stmt.setString(flag,  "%" + name.toUpperCase() + "%" );
			}
			
			if (!name.equals("") &&  name!= null && !email_id.trim().equals("")) {
				flag += 1;
				stmt.setString(flag,  "%" + email_id.toUpperCase() + "%" );
			}
			
			if (!mobile_no.equals("") &&  mobile_no!= null && !mobile_no.trim().equals("")) {  
				flag += 1;
				stmt.setString(flag,  "%" + mobile_no + "%" );
			}
			
			if (!address.equals("") && address != null && !address.trim().equals("")) {
				flag += 1;
				stmt.setString(flag,  "%" + address.toUpperCase() + "%" );
			}
			
			if (!state.equals("0") && state != null && !state.trim().equals(" ")) {
				flag += 1;
				stmt.setString(flag,  state);
			}
			if (!per_district.equals("0") && per_district != null  && !per_district.trim().equals(" ")) {
				flag += 1;
				stmt.setString(flag,  per_district);
			}

			if (!pincode.equals("") &&  pincode!= null && !pincode.trim().equals("")) { 
				flag += 1;
				stmt.setString(flag,  "%" + pincode + "%" );
			}
//			if (!product.equals("") && product != null && !product.trim().equals("")) {
//				flag += 1;
//				stmt.setString(flag,  "%" + product.toUpperCase() + "%" );
//			}
			if (!web_url.equals("") && web_url != null && !web_url.trim().equals("")){
				flag += 1;
				stmt.setString(flag,  "%" + web_url.toUpperCase() + "%" );
			}
				

			if (!Status.equals("") && Status !=null && !Status.trim().equals(" ")) {  
				flag += 1;
				stmt.setInt(flag,  Integer.parseInt(Status) );
			}
 

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
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
				query="select photo_path from edu_placement_company_reg where id=? ";	
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
	  
	  @Override
		public String getImagePath3(String id) {
			String whr="";
			Connection conn = null;
			try {	
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
		 		String query = null;
				query="select photo_path_pic from edu_placement_company_reg where id=? ";	
				stmt = conn.prepareStatement(query);
				stmt.setInt(1,Integer.parseInt(id));
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
	 	          whr=rs.getString("photo_path_pic");    
	 	        }
	 		    rs.close();
	 	    	stmt.close();
	 			conn.close();
	     	} catch (SQLException e) {
	     			e.printStackTrace();
	     	}	
			return whr;
		}
	  
	  public String approve_StudentPracData(String a,String username,String userId) {
			String[] id_list = a.split(":");
			Connection conn = null;
			int out = 0;
			String q="";
			String freg = "";
			
		 //end
		 	try {
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction  tx= sessionHQL.beginTransaction();
				conn = dataSource.getConnection();
				 
				PreparedStatement stmt = null;
			 
					for (int i = 0; i < id_list.length; i++) {
						int id = Integer.parseInt(id_list[i]);
					  	
						EDU_PLACEMENT_STUDENT_TO_ENTERPRISE sd =  new EDU_PLACEMENT_STUDENT_TO_ENTERPRISE();
							sd.setEnterprise_id(id);
							sd.setStudent_id(Integer.parseInt(userId));
							sd.setStatus(0);
							sessionHQL.save(sd);
							
							sessionHQL.flush();
							sessionHQL.clear();
							tx.commit();
							out = 1;
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
//			if (out > 0) {
//					if(status.equals("1")) {
//					return "Your details have been forwarded to the NCH council successfully.";
//					}
//					else
//						return "Your details have not been forwarded to the NCH council successfully.";
//				} else {
//					if(status.equals("1")) {
//						return "Your details have not been forwarded to the NCH council successfully.";
//						}
//					else
//					return "UnSuccessfully";
//				}
			return freg;
		}
		
		public String reject_StudentPracData(String a,String username,String userId_reject) {
			String[] id_list = a.split(":");
			Connection conn = null;
			int out = 0;
			String q="";
			
			 
			try {
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction  tx= sessionHQL.beginTransaction();
				conn = dataSource.getConnection();
				 
				PreparedStatement stmt = null;
			 
					for (int i = 0; i < id_list.length; i++) {
						int id = Integer.parseInt(id_list[i]);
					  	
						EDU_PLACEMENT_STUDENT_TO_ENTERPRISE sd =  new EDU_PLACEMENT_STUDENT_TO_ENTERPRISE();
							sd.setEnterprise_id(id);
							sd.setStudent_id(Integer.parseInt(userId_reject));
							sd.setStatus(2);
							sessionHQL.save(sd);
							
							sessionHQL.flush();
							sessionHQL.clear();
							tx.commit();
							out = 1;
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
//			if (out > 0) {
////					if(status.equals("1")) {
////					return "Approved Successfully";
////					}
//					  if(status.equals("2")) {
//						return "Rejected Successfully";
//						}
//					else
//						return "UnSuccessfully";
//				} else {
////					if(status.equals("1")) {
////						return "Approved not Successfully";
////						}
//					  if(status.equals("2")) {
//						return "Rejected not Successfully";
//						}
//					else
//					return "UnSuccessfully";
//				}
			return q;
			
		}
		
		
		public String approve_EnterprisePracData(String a,String username,String userId) {
			String[] id_list = a.split(":");
			Connection conn = null;
			int out = 0;
			String q="";
			String freg = "";
			
		 //end
		 	try {
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction  tx= sessionHQL.beginTransaction();
				conn = dataSource.getConnection();
				 
				PreparedStatement stmt = null;
			 
					for (int i = 0; i < id_list.length; i++) {
						int id = Integer.parseInt(id_list[i]);
					  	
						EDU_PLACEMENT_COMPANY_REG sd =  (EDU_PLACEMENT_COMPANY_REG)sessionHQL.get(EDU_PLACEMENT_COMPANY_REG.class, id);
						sd.setStatus(1);
						sessionHQL.update(sd);
							
							sessionHQL.flush();
							sessionHQL.clear();
							
							out = 1;
						}
					tx.commit();
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
			return freg;
		}
		
		public String reject_EnterprisePracData(String a,String username,String userId_reject) {
			String[] id_list = a.split(":");
			Connection conn = null;
			int out = 0;
			String q="";
			
			 
			try {
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction  tx= sessionHQL.beginTransaction();
				conn = dataSource.getConnection();
				 
				PreparedStatement stmt = null;
			 
					for (int i = 0; i < id_list.length; i++) {
						int id = Integer.parseInt(id_list[i]);
					  	
						EDU_PLACEMENT_COMPANY_REG sd =  (EDU_PLACEMENT_COMPANY_REG)sessionHQL.get(EDU_PLACEMENT_COMPANY_REG.class, id);
						sd.setStatus(2);
						sessionHQL.update(sd);
							
							sessionHQL.flush();
							sessionHQL.clear();
							
							out = 1;
						}
					tx.commit();
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
			return q;
			
		}
		
		
		@Override
		public List<Map<String, Object>> DataTableEdu_Reg_Report_placement_mang_enterprise_FOR_HOD(int startPage, int pageLength, String Search,
				String orderColunm, String orderType,String company_name,String name,String email_id,String mobile_no,String ph_no,String address ,String state ,
				String per_district,String pincode,String hours_from ,String hours_to,String web_url,String photo_path,String photo_path_pic, String Status) throws ParseException {
			String SearchValue = GenerateQueryWhereClause_SQL(Search,company_name,name,email_id,mobile_no,ph_no ,address ,state ,per_district ,pincode ,hours_from,hours_to,web_url,photo_path,photo_path_pic, Status);
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
				
				q= "select e.id,e.company_name,e.name,e.email_id,e.notified,e.mobile_no,e.ph_no,e.address,ss.state_name as state,sd.district_name as per_district,e.pincode,e.hours_from,e.hours_to,\n"
						+ "e.web_url,e.photo_path,e.photo_path_pic from edu_placement_company_reg e inner join edu_lms_state_mstr ss on (ss.state_id)  = e.state\n"
						+ "inner join edu_lms_district_mstr sd on (sd.district_id)  = e.per_district\n"
						+ "where "
//						+ " id not in \n"
//						+ "(select enterprise_id from edu_placement_student_to_enterprise) and"
						+ " e.id!= 0"
						 +SearchValue+ "  group by 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15 order by id " + orderType
			                + " limit " + pageL + " OFFSET " + startPage;
				
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL(stmt,Search,company_name,name,email_id,mobile_no,ph_no ,address ,state ,per_district ,pincode ,hours_from,hours_to,web_url,photo_path,photo_path_pic,Status,"");
				System.err.println("dstmt - "+stmt);
				ResultSet rs = stmt.executeQuery();
				
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				int j = startPage;
				int countFunction=1;
				while (rs.next()) {
					
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					columns.put("ser", ++j);
					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}
//					String fname = rs.getString("first_name");
					columns.put("img","<img class='d-block img5050 imageZomm' alt='No Image' id='myImg"+rs.getString("id")+"' src='MedicalImagePathplace1?i_id="+rs.getString("id")+"' onclick='imageView("+rs.getString("id")+");' />");//	
					columns.put("img1","<img class='d-block img5050 imageZomm' alt='No Image' id='myImg6"+rs.getString("id")+"' src='MedicalImagePathplace3?i_id6="+rs.getString("id")+"' onclick='imageView("+rs.getString("id")+");' />");//				 
					
				//start pdf
					 
					String Download = "";
					String chekboxaction="";
					String f = "";
					String action = "";

					String ul="";
					ul+="<ul class='buttons-group mainbtn action daobtn'>";
					
					Download = "<i class='fa fa-file-pdf-o' " + Download + " title='Download PDF' ></i>";
//					 String Downloadnote = "onclick=\"  if (confirm('Are You Sure You Want to Download Notes ?') ){downloadnote_file("
//											+ rs.getInt("id") + ", 'other_note' )}else{ return false;}\"";
						
					 	String st =	rs.getString(10) ;
						String Checkbox="<input class='nrCheckBox' type='checkbox' id='" + rs.getObject(1)//13
						+ "' name='cbox'  /> <input type='hidden' id='nrCHid"+rs.getObject(1)+"' value='"+st+"'> <input type='hidden' id='email"+rs.getObject(1)+"' value='"+rs.getObject(4)+"'> <input type='hidden' id='notif"+rs.getObject(1)+"' value='"+rs.getObject(5)+"'>" ;
						
//						 String CheckboxId="<input  type='hidden' id='id" + rs.getObject(1) + "' name='id" + rs.getObject(1)//14
//							+ "' value='" + rs.getObject(1) + "'   />";
					     chekboxaction+=Checkbox;
					     
//					     String viewStudents="<input class='nrCheckBox' type='checkbox' id='" + rs.getObject(1)//13
//							+ "' name='cbox'  /> <input type='hidden' id='nrCHid"+rs.getObject(1)+"' value='"+st+"'> <input type='hidden' id='email"+rs.getObject(1)+"' value='"+rs.getObject(4)+"'> <input type='hidden' id='notif"+rs.getObject(1)+"' value='"+rs.getObject(5)+"'>" ;
//					     String viewStudents_f = "onclick=\" if (confirm('Are You Sure You Want to View Detail ?') ){ViewData('"
//									+ rs.getObject(1) + "') }else{ return false;}\"";
//					     
					     String viewStudents = "<a data-bs-toggle=\"modal\" data-bs-target=\"#ViewStudentsModel\" class='main-btn active-btn btn-hover btn-sm ADDSystem' value='ADD' title='View Data' >" + // id='id_add_attHospital1'
									"<i class='lni lni-eye' >" + "<input type='hidden' id='sid"+countFunction+"'"
									+ "value=" + rs.getObject(1) + "></i></a> ";
					     
					     
				//end
					if (Status.equals("0")) {
						columns.put("chekboxaction", chekboxaction);
					}
					else if (Status.equals("1")) {
						columns.put("chekboxaction", viewStudents);
					}
					else {
						columns.put("chekboxaction", "");
					}
					
					ul+=f ;
					ul+="</ul>";
					
					action = ul;
					countFunction+=1;
					
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
		public long DataTableEdu_Reg_Report_masterDataTotalCount_placement_mang_enterprise_ForHOD(String Search, String company_name,String name,String email_id,String mobile_no,String ph_no,String address ,String state ,
				String per_district,String pincode,String hours_from ,String hours_to,String web_url,String photo_path,String photo_path_pic, String Status) throws ParseException {
			String SearchValue = GenerateQueryWhereClause_SQL(Search, company_name,name,email_id,mobile_no,ph_no ,address ,state ,per_district ,pincode ,hours_from,hours_to,web_url,photo_path,photo_path_pic, Status);
			int total = 0;
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				q="select count(*)  from ("
						+"	select e.id,e.company_name,e.name,e.email_id,e.mobile_no,e.ph_no,e.address,e.state,e.per_district,e.pincode,e.hours_from,e.hours_to,\n"
						+ "e.web_url,e.photo_path,e.photo_path_pic from edu_placement_company_reg e where "
//						+ "id not in (select enterprise_id from edu_placement_student_to_enterprise) and"
						+ " e.id!= 0  \n"
							+ " "+SearchValue+" group by 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15) a ";
					 
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL(stmt,Search,company_name,name,email_id,mobile_no,ph_no,address,state,per_district,pincode,hours_from,hours_to,web_url,photo_path,photo_path_pic,Status,"");
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
		
		
		@Override
		public List<Map<String, Object>> GetIntrested_Students_Data(String id) throws ParseException {
			String SearchValue = "";
	 		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			try {
				
				conn = dataSource.getConnection();
				
				q= "select se.id,sdtl.name,sdtl.email, sdtl.mobile_no,se.status from edu_placement_student_to_enterprise se\n"
						+ "inner join logininformation li on li.userid = se.student_id\n"
						+ "inner join reg_nch_details_a sdtl on sdtl.aadhar_card = li.aadhar_no where se.enterprise_id=? ";
				
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt.setInt(1, Integer.parseInt(id));
				System.err.println("sstmt - "+stmt);
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
		
		
		
		public String ApproveIntrested_Students_Data(String id) {
			String[] id_list = id.split(",");
			Connection conn = null;
			int out = 0;
			String q="";
			
			 
			try {
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction  tx= sessionHQL.beginTransaction();
				conn = dataSource.getConnection();
				 
				PreparedStatement stmt = null;
			 
					for (int i = 0; i < id_list.length; i++) {
						int mainid = Integer.parseInt(id_list[i]);
					  	
						EDU_PLACEMENT_STUDENT_TO_ENTERPRISE sd =  (EDU_PLACEMENT_STUDENT_TO_ENTERPRISE)sessionHQL.get(EDU_PLACEMENT_STUDENT_TO_ENTERPRISE.class, mainid);
						sd.setStatus(1);
						sessionHQL.update(sd);
							
							sessionHQL.flush();
							sessionHQL.clear();
							
							out += 1;
						}
					tx.commit();
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
			return String.valueOf(out);
			
		}
		
		
		@Override
		public List<Map<String, Object>> DataTableEdu_Reg_Report_placement_mang_enterprise_fromId(String id) throws ParseException {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			try {
				
				conn = dataSource.getConnection();
				String pageL = "";
				
				
				q= "select e.id,e.company_name,e.name,e.email_id,e.notified,e.mobile_no,e.ph_no,e.address,ss.state_name as state,sd.district_name as per_district,e.pincode,e.hours_from,e.hours_to,\n"
						+ "e.web_url,e.photo_path,e.photo_path_pic from edu_placement_company_reg e inner join edu_lms_state_mstr ss on (ss.state_id)  = e.state\n"
						+ "inner join edu_lms_district_mstr sd on (sd.district_id)  = e.per_district\n"
						+ "where id "
						//+ "not "
						+ "in \n"
						+ "(select enterprise_id from edu_placement_student_to_enterprise) and e.id!= 0 and e.id = "+id+" group by 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15 order by id  limit 10 OFFSET 0";
				
				PreparedStatement stmt = conn.prepareStatement(q);
				System.out.println("stmt---"+stmt);
				
				ResultSet rs = stmt.executeQuery();
				
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				
				while (rs.next()) {
					
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					//columns.put("ser", ++j);
					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}
//					String fname = rs.getString("first_name");
					columns.put("img","<img class='d-block img5050 imageZomm' alt='No Image' id='myImg"+rs.getString("id")+"' src='MedicalImagePathplace1?i_id="+rs.getString("id")+"' onclick='imageView("+rs.getString("id")+");' />");//	
					columns.put("img1","<img class='d-block img5050 imageZomm' alt='No Image' id='myImg6"+rs.getString("id")+"' src='MedicalImagePathplace3?i_id6="+rs.getString("id")+"' onclick='imageView("+rs.getString("id")+");' />");//				 
					
				//start pdf
					 
					String Download = "";
					String chekboxaction="";

					Download = "<i class='fa fa-file-pdf-o' " + Download + " title='Download PDF' ></i>";
					 String Downloadnote = "onclick=\"  if (confirm('Are You Sure You Want to Download Notes ?') ){downloadnote_file("
											+ rs.getInt("id") + ", 'other_note' )}else{ return false;}\"";
						
					 	String st =	rs.getString(10) ;
						String Checkbox="<input class='nrCheckBox' type='checkbox' id='" + rs.getObject(1)//13
						+ "' name='cbox'  /> <input type='hidden' id='nrCHid"+rs.getObject(1)+"' value='"+st+"'> <input type='hidden' id='email"+rs.getObject(1)+"' value='"+rs.getObject(4)+"'> <input type='hidden' id='notif"+rs.getObject(1)+"' value='"+rs.getObject(5)+"'>" ;
						
						 String CheckboxId="<input  type='hidden' id='id" + rs.getObject(1) + "' name='id" + rs.getObject(1)//14
							+ "' value='" + rs.getObject(1) + "'   />";
					     chekboxaction+=Checkbox; 
				//end
					columns.put("chekboxaction", chekboxaction);
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

		
		
	
}
