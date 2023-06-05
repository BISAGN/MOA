package com.AyushEdu.dao.Regulation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Regulation.REG_NCH_FORM_A_P;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Repository
public class Regulation_ReportDaoActImpl implements Regulation_ReportDaoAct {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	
	
	@Override
	public List<Map<String, Object>> DataTableEdu_Reg_Report_masterDataListAct(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String nrh_en_no,String first_name, String pre_state,String from_date,String to_date, String status,
			String institute_name) throws ParseException {
		String SearchValue = GenerateQueryWhereClause_SQLAct(Search,nrh_en_no,first_name,pre_state,from_date,to_date,status,institute_name);
	//	Image image = new ImageIcon(this.getClass().getResource(photo_path)).getImage();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {


			System.err.println("dao in  status"+status);
			
			
			conn = dataSource.getConnection();
			String pageL = "";
			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			

			q="select distinct e.id,e.nrh_en_no,e.first_name,e.photo_path,d.district_name as pre_district,s.state_name as pre_state,e.pre_pincode, \n"
					+ "	dd.district_name as per_district,ss.state_name as per_state,e.per_pincode,e.email_id,\n"
					+ "	to_char(e.dob , 'DD/MM/YYYY') as dob ,n.nationality as nationality,e.ayush_id,e.abha_no, \n"
					+ "e.father_name, e.status,e.valid_up_to,to_char(e.suspend_up_to,'yyyy-MM-dd') as suspend_upto,case when e.reason is null then '' else e.reason end as suspend_reason\n"
					+ "from reg_nch_form_a_p  e\n"
					+ " inner join recr_nationality_mst n on  (n.nationality_id )  = e.nationality\n"
					+ " inner join edu_lms_district_mstr d on  (d.district_id )  = e.pre_district\n"
					+ " inner join edu_lms_state_mstr s on  (s.state_id )  = e.pre_state\n"
					+ " inner join edu_lms_district_mstr dd on  (dd.district_id )  = e.per_district\n"
					+ " inner join edu_lms_state_mstr ss on  (ss.state_id )  = e.per_state " 
					+ " inner join reg_nch_med_degree_dtl_a_ch pr on pr.regulation_p_id=e.id " 
//					+" inner join reg_nch_registration_a pr on pr.user_id=e.user_id \n"
					+ "where e.id!=0  "+ SearchValue +" order by id " + orderType
 					+ " limit " + pageL + " OFFSET " + startPage;	
					
			 
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQLAct(stmt, Search, nrh_en_no,first_name, pre_state,from_date,to_date, status,institute_name);
			
			ResultSet rs = stmt.executeQuery();
             System.err.println("stmt-----act in serch-----"+stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			while (rs.next()) {
				
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				
				String fname = rs.getString("first_name");
				//System.err.println("fname---------"+fname);
				columns.put("img",   fname   +  "  <img class='d-block img5050 imageZomm' alt='No Image' id='myImg"+rs.getString("id")+"' src='MedicalImagePath2Act?i_id="+rs.getString("id")+"' /> ");
				
			 
				
				String f = "";
				String action = "";
				String f1 = "";
				String chekboxaction="";

				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){EditData('"
						+ rs.getString("id") + "','" + rs.getString("nrh_en_no") + "','" + rs.getString("status")
						+ "') }else{ return false;}\"";
				f = "<i class='fa fa-pencil-square-o '  " + ADD + " title='Edit Data'></i>";

				String Checkbox="<input class='nrCheckBox' type='checkbox' id='" + rs.getObject(1)//13
				+ "' name='cbox'     onchange='checkbox_count(this," + rs.getObject(1) + ");' />";
				
		     String CheckboxId="<input  type='hidden' id='id" + rs.getObject(1) + "' name='id" + rs.getObject(1)//14
				+ "' value='" + rs.getObject(1) + "'   />";
		     chekboxaction+=Checkbox;
		     System.err.println("chekboxaction----------"+chekboxaction);
				
				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<i class='fa fa-trash '  " + ADD1 + " title='Delete Data'></i>";
				
				String sd="";
				String VIEW_sus_Date = "onclick=\" if (confirm('Enter Suspend Detail') ){suspend_upto('"
						+ rs.getString("id") + "') }else{ return false;}\"";
						sd = "<input type='date' value='"+rs.getString("suspend_upto")+"' name='suspend_upto"+rs.getString("id")+"' id='suspend_upto"+rs.getString("id")+"' class='form-control' >";
						
						String sr="";
						String VIEW_sus_reason = "onclick=\" if (confirm('Enter Suspend Detail') ){suspend_reason('"
								+ rs.getString("id") + "') }else{ return false;}\"";
								sr = "<input type='text' value='"+rs.getString("suspend_reason")+"' name='suspend_reason"+rs.getString("id")+"' id='suspend_reason"+rs.getString("id")+"' class='form-control' >";

				
				action = f + " " + f1  ;
			 
				columns.put("chekboxaction", chekboxaction);
				columns.put("action", action);
				columns.put("VIEW_sus_Date", sd);
				columns.put("VIEW_sus_reason", sr);
			
				
//				list.add(chekboxaction);
			 	
				String Download = "";

				Download = "<i class='fa fa-file-pdf-o' " + Download + " title='Download PDF' ></i>";
								
								String Downloadnote = "onclick=\"  if (confirm('Are You Sure You Want to Download Notes ?') ){downloadnote_file("
										+ rs.getInt("id") + ", 'other_note' )}else{ return false;}\"";
				
			//end
								
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
	public long DataTableEdu_Reg_Report_masterDataTotalCountAct(String Search, String nrh_en_no,String first_name,String pre_state,
			String from_date,String to_date, String status,String institute_name) throws ParseException {
		String SearchValue = GenerateQueryWhereClause_SQLAct(Search, nrh_en_no, first_name, pre_state,from_date,to_date, status,institute_name);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		 	
			
			q="select count(*) \n"
					+ " from (select e.id,e.nrh_en_no,e.first_name,e.photo_path,d.district_name as pre_district,s.state_name as pre_state,e.pre_pincode, \n"
					+ "	dd.district_name as per_district,ss.state_name as per_state,e.per_pincode,e.email_id,\n"
					+ "	concat(to_char(e.dob , 'DD/MM/YYYY') ,n.nationality ) as dob_nationality ,e.ayush_id,e.abha_no,"
					+ "  to_char(e.date_of_reg , 'DD/MM/YYYY') as date_of_reg,\n"
					+ "e.father_name, e.status,e.valid_up_to\n"
					+ "from reg_nch_form_a_p  e\n"
					+ "inner join recr_nationality_mst n on  (n.nationality_id )  = e.nationality\n"
					+ "inner join edu_lms_district_mstr d on  (d.district_id )  = e.pre_district\n"
					+ "inner join edu_lms_state_mstr s on  (s.state_id )  = e.pre_state\n"
					+ "inner join edu_lms_district_mstr dd on  (dd.district_id )  = e.per_district\n"
					+ "inner join edu_lms_state_mstr ss on  (ss.state_id)  = e.per_state "
					+ " inner join reg_nch_med_degree_dtl_a_ch pr on pr.regulation_p_id=e.id "
					+ " where e.id!=0"+SearchValue+") a";  
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQLAct(stmt, Search, nrh_en_no,first_name,pre_state,from_date,to_date,status,institute_name);
			System.err.println("stmt-----count-----"+stmt);
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
	public String GenerateQueryWhereClause_SQLAct(String Search, String nrh_en_no,String first_name,String pre_state,
			String from_date,String to_date,String status,String institute_name) throws ParseException {
		
		System.err.println("statu-s------------"+status);
		String SearchValue = "";
		if (Search!=null) { // for Input Filter
			if(!Search.equals("")){
			SearchValue += " and (  upper(nrh_en_no) like ? or upper(s.state_name) like ?)";
			System.err.println("globalllll search" + SearchValue);
			}

		}

		/// advance search

		if (!nrh_en_no.trim().equals("")) {
			SearchValue += " and upper(nrh_en_no) like ? ";
			System.err.println("parameter search" + SearchValue);

		}
		if (!pre_state.equals("0")) {
			SearchValue += " and cast(reg_state as character varying) = ? ";
			}
		if (status.equals("1")) {
			SearchValue += " and e.status = ? ";

		}
		if (status.equals("5")) {
			SearchValue += " and e.status = ? ";

		}
		if (status.equals("4")) {
			SearchValue += " and e.status = ? ";

		}
		if (!institute_name.equals("0") && institute_name != null) {
			SearchValue += " and pr.name_of_institute = ? ";
		} 
		
//		DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
//		Date from_date2 = null;
//		Date to_date2 = null;
//		if (!from_date.equals("") && !to_date.equals("")) {
//			String from_date_i = from_date.substring(8,10)+"-"+from_date.substring(5,7)+"-"+from_date.substring(0,4);
//			String to_date_i =to_date.substring(8,10)+"-"+to_date.substring(5,7)+"-"+to_date.substring(0,4);
//			from_date2 = format.parse(from_date);
//			to_date2 = format.parse(to_date);
//		SearchValue += " and e.created_date between CAST(? as timestamp without time zone) and CAST(? as timestamp without time zone) ";
//		System.err.println("000searchValue ---------------"+SearchValue);
//		}
		
		
		if (!from_date.equals("DD/MM/YYYY") && from_date != "DD/MM/YYYY" && !from_date.equals("") && from_date != null  && 
				!to_date.equals("DD/MM/YYYY") && to_date != "DD/MM/YYYY" && !to_date.equals("") && to_date != null 
				) {	
			
		SearchValue += " and e.created_date between CAST(? as timestamp without time zone) and CAST(? as timestamp without time zone) ";
		 
		}
		
	
//		
//		if (!nrh_en_no.trim().equals("")) {
//			SearchValue += " and upper(nrh_en_no) like ? ";
//			System.err.println("parameter search" + SearchValue);
//
//		}
	
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQLAct(PreparedStatement stmt, String Search, String nrh_en_no,String first_name,String pre_state,
			String from_date,String to_date, String status,String institute_name) {
		int flag = 0;
		try {
			if (Search != null && !Search.equals("")) {

				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}

			if (!nrh_en_no.equals("") && nrh_en_no != null) {
				flag += 1;
				stmt.setString(flag, "%" + nrh_en_no.toUpperCase() + "%");
			}
			if (!pre_state.equals("0")) {
				flag += 1;
				stmt.setString(flag, pre_state);
				}
			
		
			if (status.equals("1")) {
				flag += 1;
				stmt.setInt(flag,1);
			}
			if (status.equals("5")) {
				flag += 1;
				stmt.setInt(flag,5);
			}
			if (status.equals("4")) {
				flag += 1;
				stmt.setInt(flag,4);
			}
//			if (!from_date2.equals("") && !to_date2.equals("")) {
//				flag += 1;
//				stmt.setString(flag,from_date2);
//				flag += 1;
//				stmt.setString(flag,to_date2);
//			}
			
			if (!from_date.equals("DD/MM/YYYY") && from_date != "DD/MM/YYYY" && !from_date.equals("") && from_date != null  && 
					!to_date.equals("DD/MM/YYYY") && to_date != "DD/MM/YYYY" && !to_date.equals("") && to_date != null 
					) {	
				flag += 1;
				stmt.setString(flag,from_date);
				flag += 1;
				stmt.setString(flag,to_date);
			}
			
			if (!institute_name.equals("0") && institute_name != null) {
				flag += 1;
				stmt.setString(flag,institute_name);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}
	
	public String suspend_NCHPracData(String a,String sus_upto,String status,String username) {
		String[] id_list = a.split(":");
		Connection conn = null;
		int out = 0;
		String q="";

		try {
			conn = dataSource.getConnection();
			System.err.println("check the sus_upto uptoooooooooo"+sus_upto);

			PreparedStatement stmt = null;
				for (int i = 0; i < id_list.length; i++) {
					int id = Integer.parseInt(id_list[i]);
					stmt = conn.prepareStatement("update reg_nch_form_a_p set  nrh_status=? , sus_upto=?  where id=?");
					stmt.setInt(1, Integer.parseInt(status));
					stmt.setObject(2, sus_upto);
					stmt.setInt(3,id);
					//System.err.println("check the statment--"+stmt);
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
				if(status.equals("1")) {
				return "Approved Successfully";
				}
				else
					return "UnSuccessfully";
			} else {
				if(status.equals("1")) {
					return "Approved not Successfully";
					}
				else
				return "UnSuccessfully";
			}
	}
	
	
//	  -----------------------------View_Img Path---------------------------------------------------------
	  
	  @Override
		public String getImagePath1Act(String id) {
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

	  
	  //------------------pdf---------------------------------------
	  //start pdf
	  
	  @Override
	  public String getFilePathQueryForDocFileAct(String id,String fildname) {
	  System.err.println("id---->>>"+id);
	  	String whr = "";
	  	String q1 = "";
	  	Connection conn = null;
	  	String fildname1 = "";
//	  	if(val.equals("1")) {
//	  		q1="persdetails";
//	  	}
//	  	else if(val.equals("2")){
//	  		q1="edudetails";
//	  	}
	  	
	  	if (fildname.equals("upload_file")) {
	  		fildname1 = "resumedoc";
	  	}
//	  	else if (fildname.equals("other_note")) {
//	  		fildname1 = "identitydoc";
//	  	}
//	  	else if (fildname.equals("upload_ppt")) {
//	  		fildname1 = "identitydoc";
//	  	}


	  	try {
	  		
	  		
	  		
	  		conn = dataSource.getConnection();
	  		PreparedStatement stmt = null;
	  		String query = null;
	  		query = "select $fildname from reg_nch_form_a_p where id=?";
	  		query = query.replace("$fildname", fildname);
	  		
	  		stmt = conn.prepareStatement(query);
	  		stmt.setInt(1, Integer.parseInt(id));
	  		
	  		System.out.println("stmt========="+stmt);
	  		ResultSet rs = stmt.executeQuery();

	  		while (rs.next()) {
	  			whr = rs.getString(fildname);
	  		}
	  		rs.close();
	  		stmt.close();
	  		conn.close();
	  		
	  	} catch (SQLException e) {
	  		e.printStackTrace();
	  	}
	  	return whr;
	  
	  }
	  
	  //end
	  
	  
	  
	  //start new pdf
	  @Override
		public ArrayList<ArrayList<String>> DataTableEdu_Reg_Report_masterDataList_pdfAct() {
			
	
		  ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			try {

				conn = dataSource.getConnection();
				String pageL = "";
				
				
				q="select e.id,e.nrh_en_no,e.first_name,e.gender,e.photo_path,concat(d.district_name ,s.state_name ,e.pre_pincode) as pre_corre_add, \r\n"
						+ "			 concat(dd.district_name ,ss.state_name ,e.per_pincode) as premanent_add,e.aadhaar_no,"
						+ "concat(e.email_id) as fax_mo_email,e.alt_mo_no1,e.alt_mo_no2,\r\n"
						+ "				concat(to_char(e.dob , 'DD/MM/YYYY') ,c.name) as dob_nationality,"
						+ "concat(e.degree,e.university,e.month_year) as deg_uni_my,"
						+ "concat( to_char(e.date_of_reg , 'DD/MM/YYYY') ,e.name_reg,e.reg_renew_permanent) as no_date_name_renew,\r\n"
						+ "                 e.name_of_hospital_teaching,\r\n"
						+ "                 e.name_of_patient,e.crh_no,e.cch_no,\r\n"
						+ "				 e.nch_no,e.father_name, e.status\r\n"
						+ "from reg_nch_form_a_p  e\r\n"
						+ "inner join edu_lms_country_mstr c on cast (c.id as character varying)  = e.nationality\r\n"
						+ "inner join edu_lms_district_mstr d on cast (d.district_id as character varying)  = e.pre_district\r\n"
						+ "inner join edu_lms_state_mstr s on cast (s.state_id as character varying)  = e.pre_state\r\n"
						+ "inner join edu_lms_district_mstr dd on cast (dd.district_id as character varying)  = e.per_district\r\n"
						+ "inner join edu_lms_state_mstr ss on cast (ss.state_id as character varying)  = e.per_state\r\n"
						+ "where e.status='1' ";
				
				
				
				
				PreparedStatement stmt = conn.prepareStatement(q);
				
				System.err.println("urmik================pdf==============");
				ResultSet rs = stmt.executeQuery();
				
				ResultSetMetaData metaData = rs.getMetaData();
				
				int i=1;
				while (rs.next()) {
					
					ArrayList<String> alist = new ArrayList<String>();
					alist.add(String.valueOf(i)); // 1
					alist.add(rs.getString("nrh_en_no")); // 2
					alist.add(rs.getString("first_name")+"/n");//3
					alist.add(rs.getString("father_name"));//4
					alist.add(rs.getString("pre_corre_add"));//5
					alist.add(rs.getString("premanent_add"));//6
					alist.add(rs.getString("aadhaar_no"));//7
					alist.add(rs.getString("fax_mo_email"));//8
					alist.add(rs.getString("dob_nationality"));//9
					alist.add(rs.getString("deg_uni_my"));//10
					alist.add(rs.getString("no_date_name_renew"));//11
					alist.add(rs.getString("name_of_hospital_teaching"));//12
					alist.add(rs.getString("name_of_patient"));//13
					alist.add(rs.getString("photo_path"));//14
					list.add(alist);
					i+=1;
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
	  
	//end

	  public String active_inact_user(String a,String status,String sus_upto, String sus_reason) throws ParseException {
			

		  System.err.println("sus_upto---------"+sus_upto);
		  
		  String[] id_list = a.split(":");
		  

			Session sessionHQL = sessionFactory.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Integer out = 0;
			String q = "";
			
			try {
			
				
					for (int i = 0; i < id_list.length; i++) {
						int id = Integer.parseInt(id_list[i]);
//						Object sus_upto3 = (sus_upto_list[i]);
						
						// Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
						REG_NCH_FORM_A_P assetupd =(REG_NCH_FORM_A_P)sessionHQL.get(REG_NCH_FORM_A_P.class, id);
					
						assetupd.setStatus(Integer.parseInt(status));
						if(sus_upto != null && !sus_upto.equals("")) {
							  String[] sus_upto_list = sus_upto.split(":");
							  
							  
							  Date date = new SimpleDateFormat("dd/MM/yyyy").parse(sus_upto_list[i]);
								assetupd.setSuspend_up_to(date);
						  }
						
						if(sus_reason != null && !sus_reason.equals("")) {
							 String[] sus_reason_list = sus_reason.split(":");
						assetupd.setReason(sus_reason_list[i]);
						}
						sessionHQL.update(assetupd);
						sessionHQL.flush();
						sessionHQL.clear();
						out =1;
						
						}

			} finally {
				tx.commit();
			}

			if (out > 0) {
				
				System.err.println("out========="+out);
				if(status.equals("1")) {
					String sta = "Activated";
					SendactiveMsgPractitioner(sta);
				return "Activate User Successfully";
				}
				else if(status.equals("5")) {
					String sta = "Inactivated";
					SendactiveMsgPractitioner(sta);
					return "Inactivate User Successfully";
					}
				else if(status.equals("4")) {
					String sta = "Suspended";
					SendactiveMsgPractitioner(sta);
					return "Suspended User Successfully";
					}
				else
					return "UnSuccessfully";
			} else {
				if(status.equals("1")) {
					return "Activate User not Successfully";
					}
				else if(status.equals("5")) {
					return "Inactivate User not Successfully";
					}
				else if(status.equals("4")) {
					return "Suspended User not Successfully";
					}
				else
				return "UnSuccessfully";
			}
		}
	  
	// FOR WHATSAPP
		
			public static void SendactiveMsgPractitioner(String sta) throws ParseException {
				
				final String ACCOUNT_SID = "AC76781bb1c6c8c4d871a664b55a19da36";
			    final String AUTH_TOKEN = "74cd413e0fbe76bb1d52ddf2730b1c52";
			    
				Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
				
		        Message message = Message.creator(
		                new com.twilio.type.PhoneNumber("whatsapp:+918200818918"),
		                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
		                "Your Application has been  "+sta)
		          
		        		.create();
		//        System.out.println("message++++++>>>>>>>>>"+message);
		        System.out.println("staaaaaaaaaaaaaaaa "+sta);
		        
			}
}
