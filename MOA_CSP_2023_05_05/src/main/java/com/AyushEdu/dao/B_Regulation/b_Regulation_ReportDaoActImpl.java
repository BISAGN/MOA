package com.AyushEdu.dao.B_Regulation;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.B_Regulation.TB_EDU_B_REGULATION;
 

@Repository
public class b_Regulation_ReportDaoActImpl implements b_Regulation_ReportDaoAct {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	
	@Override
	public List<Map<String, Object>> DataTable_b_Edu_Reg_Report_masterDataListAct(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String nrh_en_no,String first_name, String pre_state,String from_date,String to_date, String status) throws ParseException {
		String SearchValue = GenerateQueryWhereClause_SQLAct(Search,nrh_en_no,first_name,pre_state,from_date,to_date,status);
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
			
			
 
			

			
			q="select e.id,e.nrh_en_no,e.first_name,e.photo_path,d.district_name as pre_district,s.state_name as pre_state,e.pre_pincode, \n"
					+ "	dd.district_name as per_district,ss.state_name as per_state,e.per_pincode,e.email_id,\n"
					+ "	to_char(e.dob , 'DD/MM/YYYY') as dob ,n.nationality as nationality, \n"
					+ "e.father_name, e.status\n"
					+ "from edu_b_regulation  e\n"
					+ " inner join recr_nationality_mst n on  (n.nationality_id )  = e.nationality\n"
					+ " inner join edu_lms_district_mstr d on  (d.district_id )  = e.pre_district\n"
					+ " inner join edu_lms_state_mstr s on  (s.state_id )  = e.pre_state\n"
					+ " inner join edu_lms_district_mstr dd on  (dd.district_id )  = e.per_district\n"
					+ " inner join edu_lms_state_mstr ss on  (ss.state_id )  = e.per_state "+ SearchValue +" order by id " + orderType
 					+ " limit " + pageL + " OFFSET " + startPage;	
					
			 
			PreparedStatement stmt = conn.prepareStatement(q);
			 System.err.println("stmt-Urmik----"+stmt);
			stmt = setQueryWhereClause_SQLAct(stmt, Search, nrh_en_no,first_name, pre_state,from_date,to_date, status);
			
			ResultSet rs = stmt.executeQuery();
            //System.err.println("stmt-----hhhhh-----"+stmt);
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
				columns.put("img",   fname   +  "  <img class='d-block img5050 imageZomm' alt='No Image' id='myImg"+rs.getString("id")+"' src='MedicalImagePath2Act1?i_id="+rs.getString("id")+"' /> ");
				
				 
				
				
				
				String f = "";
				String action = "";
				String f1 = "";
				String chekboxaction="";

				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){EditData('"
						+ rs.getString("id") + "','" + rs.getString("nrh_en_no") + "','" + rs.getString("status")
						+ "') }else{ return false;}\"";
				f = "<i class='fa fa-pencil-square-o '  " + ADD + " title='Edit Data'></i>";
//
//				
//
				String Checkbox="<input class='nrCheckBox' type='checkbox' id='" + rs.getObject(1)//13
				+ "' name='cbox' onchange='checkbox_count(this," + rs.getObject(1) + ");' />";
//		  
		     String CheckboxId="<input  type='hidden' id='id" + rs.getObject(1) + "' name='id" + rs.getObject(1)//14
				+ "' value='" + rs.getObject(1) + "'   />";
		     chekboxaction+=Checkbox;
		     System.err.println("chekboxaction----------"+chekboxaction);
//				
//				
				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<i class='fa fa-trash '  " + ADD1 + " title='Delete Data'></i>";
//				
//				
				action = f + " " + f1  ;
//				 
				columns.put("chekboxaction", chekboxaction);
				columns.put("action", action);
			
				
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
	public long DataTable_b_Edu_Reg_Report_masterDataTotalCountAct(String Search, String nrh_en_no,String first_name,String pre_state,
			String from_date,String to_date, String status) throws ParseException {
		String SearchValue = GenerateQueryWhereClause_SQLAct(Search, nrh_en_no, first_name, pre_state,from_date,to_date, status);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
				
	
			
			
			q="select count(*) \n"
					+ " from (select e.id,e.nrh_en_no,e.first_name,e.photo_path,d.district_name as pre_district,s.state_name as pre_state,e.pre_pincode, \n"
					+ "	dd.district_name as per_district,ss.state_name as per_state,e.per_pincode,e.email_id,\n"
					+ "	concat(to_char(e.dob , 'DD/MM/YYYY') ,n.nationality ) as dob_nationality , e.reg_no,to_char(e.date_of_reg , 'DD/MM/YYYY') as date_of_reg,e.reg_auth,\n"
					+ "e.father_name, e.status\n"
					+ "from edu_b_regulation  e\n"
					+ "inner join recr_nationality_mst n on  (n.nationality_id )  = e.nationality\n"
					+ "inner join edu_lms_district_mstr d on  (d.district_id )  = e.pre_district\n"
					+ "inner join edu_lms_state_mstr s on  (s.state_id )  = e.pre_state\n"
					+ "inner join edu_lms_district_mstr dd on  (dd.district_id )  = e.per_district\n"
					+ "inner join edu_lms_state_mstr ss on  (ss.state_id)  = e.per_state"+SearchValue+") a";  
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQLAct(stmt, Search, nrh_en_no,first_name,pre_state,from_date,to_date,status);
			System.err.println("stmt-----urmikkkkkk-----"+stmt);
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
			String from_date,String to_date,String status) throws ParseException {
		
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
			SearchValue += " and pre_state = ? ";
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
		
		
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
		Date from_date2 = null;
		Date to_date2 = null;
		if (!from_date.equals("") && !to_date.equals("")) {
//			String l[] =from_date.split("-");
//			String l1[] =to_date.split("/");
			
			
//			System.err.println("l1    :::::"++ "l1    :::::"++"=========="+);
			String from_date_i = from_date.substring(8,10)+"-"+from_date.substring(5,7)+"-"+from_date.substring(0,4);
			String to_date_i =to_date.substring(8,10)+"-"+to_date.substring(5,7)+"-"+to_date.substring(0,4);
			from_date2 = format.parse(from_date);
			to_date2 = format.parse(to_date);
		SearchValue += " and e.created_date between CAST(? as timestamp without time zone) and CAST(? as timestamp without time zone) ";
		System.err.println("000searchValue ---------------"+SearchValue);
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
			String from_date2,String to_date2, String status) {
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
			if (!from_date2.equals("") && !to_date2.equals("")) {
				flag += 1;
				stmt.setString(flag,from_date2);
				flag += 1;
				stmt.setString(flag,to_date2);
			}
		

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
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
	  		query = "select $fildname from edu_b_regulation where id=?";
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
		public ArrayList<ArrayList<String>> DataTable_b_Edu_Reg_Report_masterDataList_pdfAct() {
			
	
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
						+ "concat(e.reg_no,to_char(e.date_of_reg , 'DD/MM/YYYY') ,e.name_reg,e.reg_renew_permanent) as no_date_name_renew,\r\n"
						+ "                 e.name_of_hospital_teaching,\r\n"
						+ "                 e.name_of_patient,e.crh_no,e.cch_no,\r\n"
						+ "				 e.nch_no,e.father_name, e.status\r\n"
						+ "from edu_b_regulation  e\r\n"
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
	  
	  
	  public String b_active_inact_user(String a,String status) {
			String[] id_list = a.split(":");

			Connection conn = null;
			Integer out = 0;
			String q = "";
		

			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
		
				
					for (int i = 0; i < id_list.length; i++) {
						int id = Integer.parseInt(id_list[i]);
		
						// Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
						Session sessionHQL = this.sessionFactory.openSession();
						TB_EDU_B_REGULATION assetupd =(TB_EDU_B_REGULATION)sessionHQL.get(TB_EDU_B_REGULATION.class, id);
						stmt = conn.prepareStatement("update edu_b_regulation set status=? where id=?");
						
						
						stmt.setInt(1, Integer.parseInt(status));
						stmt.setInt(2, id);
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
				return "Activate User Successfully";
				}
				else if(status.equals("5")) {
					return "Inactivate User Successfully";
					}
				else if(status.equals("4")) {
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
	
}
