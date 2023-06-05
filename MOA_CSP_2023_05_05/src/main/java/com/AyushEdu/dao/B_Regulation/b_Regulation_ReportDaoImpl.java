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

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class b_Regulation_ReportDaoImpl implements b_Regulation_ReportDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public List<Map<String, Object>> DataTable_b_Edu_Reg_Report_masterDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String nrh_en_no, String first_name, String status,String per_state,String from_date,String to_date) throws ParseException {
		String SearchValue = GenerateQueryWhereClause_SQL(Search,nrh_en_no,first_name,status,per_state,from_date,to_date);
	//	Image image = new ImageIcon(this.getClass().getResource(photo_path)).getImage();
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
			
			
			q="select distinct e.id,e.nrh_en_no,e.first_name,e.photo_path,d.district_name as pre_district,s.state_name as pre_state,e.pre_pincode, \n"
					+ "	 dd.district_name as per_district,ss.state_name as per_state,e.per_pincode,e.email_id,\n"
					+ "	to_char(e.dob , 'DD/MM/YYYY') as dob,n.nationality,e.reg_no,to_char(e.date_of_reg , 'DD/MM/YYYY') as date_of_reg,e.reg_auth,\n"
					+ " e.father_name, e.status,\n"
					+ "sss.state_name as registration_state  \n"
					+ " from edu_b_regulation  e\n"
					+ "inner join recr_nationality_mst n on  (n.nationality_id )  = e.nationality\n"
					+ "inner join edu_lms_district_mstr d on  (d.district_id )  = e.pre_district\n"
					+ "inner join edu_lms_state_mstr s on  (s.state_id )  = e.pre_state\n"
					+ "inner join edu_lms_district_mstr dd on  (dd.district_id )  = e.per_district\n"
					+ "inner join edu_lms_state_mstr ss on  (ss.state_id )  = e.per_state\n"
					+ "inner join edu_practitioner_registration pr on pr.user_id=e.user_id\n"
					+ "inner join edu_lms_state_mstr sss on cast (sss.state_id as character varying)  = pr.regisration_state"+ SearchValue +" order by id " + orderType
	                + " limit " + pageL + " OFFSET " + startPage;
	PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, nrh_en_no,first_name,status,per_state,from_date,to_date);
			 System.err.println("stmt--urmikkkkkkkkkkkkkkkk  serch-----"+stmt);
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
				System.err.println("fname---------"+fname);
				columns.put("img",   fname   +  "  <img class='d-block img5050 imageZomm' alt='No Image' id='myImg"+rs.getString("id")+"' src='MedicalImagePath3?i_id="+rs.getString("id")+"' /> ");

				
			//start pdf
				
				
				
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
	public long DataTable_b_Edu_Reg_Report_masterDataTotalCount(String Search, String nrh_en_no,String first_name, String status,String per_state,String from_date,String to_date) throws ParseException {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, nrh_en_no,first_name, status,per_state,from_date,to_date);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
				

			q="select count(*)  from (select distinct e.id,e.nrh_en_no,e.first_name,e.photo_path,d.district_name as pre_district,s.state_name as pre_state,e.pre_pincode, \n"
					+ "	 dd.district_name as per_district,ss.state_name as per_state,e.per_pincode,e.email_id,\n"
					+ "	to_char(e.dob , 'DD/MM/YYYY') as dob,n.nationality,e.reg_no,to_char(e.date_of_reg , 'DD/MM/YYYY') as date_of_reg,e.reg_auth,\n"
					+ " e.father_name, e.status,\n"
					+ "sss.state_name as registration_state  \n"
					+ " from edu_b_regulation  e\n"
					+ "inner join recr_nationality_mst n on  (n.nationality_id )  = e.nationality\n"
					+ "inner join edu_lms_district_mstr d on  (d.district_id )  = e.pre_district\n"
					+ "inner join edu_lms_state_mstr s on  (s.state_id )  = e.pre_state\n"
					+ "inner join edu_lms_district_mstr dd on  (dd.district_id )  = e.per_district\n"
					+ "inner join edu_lms_state_mstr ss on  (ss.state_id )  = e.per_state\n"
					+ "inner join edu_practitioner_registration pr on pr.user_id=e.user_id\n"
					+ "inner join edu_lms_state_mstr sss on cast (sss.state_id as character varying)  = pr.regisration_state"+SearchValue+") a ";
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, nrh_en_no,first_name,status,per_state,from_date,to_date);
			System.err.println("stmt-----hhuuuuhhh-----"+stmt);
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
	public String GenerateQueryWhereClause_SQL(String Search, String nrh_en_no,String first_name,String status,String per_state,String from_date,String to_date) throws ParseException {
		String SearchValue = "";

//		/// advance search
//
		if (!nrh_en_no.trim().equals("")) {
			SearchValue += " and upper(nrh_en_no) like ? ";
			System.err.println("parameter search" + SearchValue);

		}
		//start
		
		if (!per_state.equals("0")) {
			SearchValue += " and cast(per_state as character varying) = ? ";
			System.err.println("parameter search" + SearchValue);
			System.err.println("per_state"+per_state);
			}

//end
		
		//start seach from_date to to_date
		
				DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
				Date from_date2 = null;
				Date to_date2 = null;
				if (!from_date.equals("") && !to_date.equals("")) {
//					String l[] =from_date.split("-");
//					String l1[] =to_date.split("/");
					
					
//					System.err.println("l1    :::::"++ "l1    :::::"++"=========="+);
					String from_date_i = from_date.substring(8,10)+"-"+from_date.substring(5,7)+"-"+from_date.substring(0,4);
					String to_date_i =to_date.substring(8,10)+"-"+to_date.substring(5,7)+"-"+to_date.substring(0,4);
					from_date2 = format.parse(from_date);
					to_date2 = format.parse(to_date);
				SearchValue += " and e.created_date between CAST(? as timestamp without time zone) and CAST(? as timestamp without time zone) ";
				 
				}
				
				//end
				
				if (Search != null && !Search.equals("")) {
					Search = Search.toLowerCase();

					SearchValue += " and ";
					SearchValue += "  (lower(nrh_en_no) like ? or lower(s.state_name) like ? ) ";
				}

	
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String nrh_en_no,String first_name, String status,
			String per_state,String from_date2,String to_date2) {
		int flag = 0;
		try {

//
			if (!nrh_en_no.equals("") && nrh_en_no != null) {
				flag += 1;
				stmt.setString(flag, "%" + nrh_en_no.toUpperCase() + "%");
			}
			
			if (!per_state.equals("0")) {
				flag += 1;
				stmt.setString(flag, per_state);
				}

			
			if (!from_date2.equals("") && !to_date2.equals("")) {
				flag += 1;
				stmt.setString(flag,from_date2);
				flag += 1;
				stmt.setString(flag,to_date2);
			}

			
			if (Search != null && !Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
			}

//			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
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
	  public String getFilePathQueryForDocFile(String id,String fildname) {
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
		public ArrayList<ArrayList<String>> DataTable_b_Edu_Reg_Report_masterDataList_pdf() {
			
	
		  ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			try {

				conn = dataSource.getConnection();
				String pageL = "";
				

				
				q="select e.id,e.nrh_en_no,e.first_name,e.photo_path,concat(d.district_name ,s.state_name ,e.pre_pincode) as pre_corre_add, \n"
						+ "concat(dd.district_name ,ss.state_name ,e.per_pincode) as permanent_add,\n"
						+ "e.email_id,\n"
						+ "concat(to_char(e.dob , 'DD/MM/YYYY') ,n.nationality) as dob_nationality,\n"
						+ "concat(e.reg_no,to_char(e.date_of_reg , 'DD/MM/YYYY') ,e.reg_auth) as no_date_name_renew,\n"
						+ "e.father_name, e.status\n"
						+ "from edu_b_regulation  e\n"
						+ "inner join recr_nationality_mst n on  (n.nationality_id)  = e.nationality\n"
						+ "inner join edu_lms_district_mstr d on  (d.district_id)  = e.pre_district\n"
						+ "inner join edu_lms_state_mstr s on  (s.state_id)  = e.pre_state\n"
						+ "inner join edu_lms_district_mstr dd on  (dd.district_id )  = e.per_district\n"
						+ "inner join edu_lms_state_mstr ss on  (ss.state_id)  = e.per_state where e.nrh_status=1 ";
				
				PreparedStatement stmt = conn.prepareStatement(q);
	//			stmt.setInt(1,status123);
				System.out.println("urmikkkkkkkkkk"+stmt);
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();
				
				int i=1;
				while (rs.next()) {
					
					ArrayList<String> alist = new ArrayList<String>();
					alist.add(String.valueOf(i)); // 1
					alist.add(rs.getString("nrh_en_no")); // 2
					alist.add(rs.getString("first_name"));//3
					alist.add(rs.getString("father_name"));//4
					alist.add(rs.getString("pre_corre_add"));//5
					alist.add(rs.getString("permanent_add"));//6
	//				alist.add(rs.getString("aadhaar_no"));//7
					alist.add(rs.getString("email_id"));//8
					alist.add(rs.getString("dob_nationality"));//9
	//				alist.add(rs.getString("deg_uni_my"));//10
					alist.add(rs.getString("no_date_name_renew"));//11
	//				alist.add(rs.getString("name_of_hospital_teaching"));//12
	//				alist.add(rs.getString("name_of_res_p"));//13
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
	  

}
