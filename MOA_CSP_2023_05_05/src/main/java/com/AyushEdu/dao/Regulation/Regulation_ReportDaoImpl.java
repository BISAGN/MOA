package com.AyushEdu.dao.Regulation;

import java.io.File;
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

@Repository
public class Regulation_ReportDaoImpl implements Regulation_ReportDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	
	
	@Override
	public List<Map<String, Object>> DataTableEdu_Reg_Report_masterDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String nrh_en_no, String first_name, String status,String per_state,String from_date,String to_date  ,String gender ,
			String state_reg_no ,String dob ,String institute_name ,String type_status) throws ParseException {
		String SearchValue = GenerateQueryWhereClause_SQL(Search,nrh_en_no,first_name,status,per_state,from_date,to_date  ,gender ,state_reg_no ,dob ,institute_name ,type_status);
 		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {

			
			System.err.println("dob----"+dob + "from date"+from_date);
			conn = dataSource.getConnection();
			String pageL = "";
			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			
 			
			q="select e.ayush_id,e.abha_no,e.id,e.nrh_en_no,e.first_name,e.photo_path,concat(d.district_name ||' </br> '|| s.state_name ||' </br> '|| e.pre_pincode) as pre_corre_add, \n"
					+ "concat(dd.district_name ||' </br> '|| ss.state_name ||' </br> '|| e.per_pincode) as permanent_add,\n"
					+ "e.email_id,\n"
					+ "concat(to_char(e.dob , 'DD/MM/YYYY') ||' </br> '|| n.nationality) as dob_nationality,\n"
					+ "concat(e.ayush_id,e.abha_no) as ayu_abha,concat(e.state_reg_no ||' </br> '|| to_char(e.created_date , 'DD/MM/YYYY') ||' </br> '|| ss.state_name) as no_date_name_renew,\n"
					+ "e.father_name, e.status,e.mo_no,\n"
					+ "String_agg(distinct m.degree_name ||' '|| c.month_and_year_of_degree ||' '|| c.name_of_institute , '</br>')  as degree_my_inst,\n"
					+ " String_agg( distinct h.place_of_working_name ||' '||  h.hos_address1 ||' '|| h.hos_address2 ||' '|| h.hos_address3 , '</br>')  as hos_name_addr,\n"
					+ "String_agg(distinct h.name_of_res_p,'</br>') as name_of_res_p\n"
					+ "from reg_nch_form_a_p  e\n"
					+ "inner join recr_nationality_mst n on  (n.nationality_id)  = e.nationality\n"
					+ "inner join edu_lms_district_mstr d on  (d.district_id)  = e.pre_district\n"
					+"inner join edu_lms_gender_mstr ee on ee.id=e.gender\n"
					+ "inner join edu_lms_state_mstr s on  (s.state_id)  = e.pre_state\n"
					+ "inner join edu_lms_district_mstr dd on  (dd.district_id )  = e.per_district\n"
					+ "--inner join reg_nch_registration_a pr on pr.user_id=e.user_id\n"
					+ "inner join edu_lms_state_mstr ss on    ss.state_id    = e.per_state\n"
					+ "inner join reg_nch_med_degree_dtl_a_ch c on c.regulation_p_id = e.id\n"
					+ "inner join edu_lms_type_of_degree_mstr tdm on tdm.id=c.type_of_degree\n"
					+ "inner join edu_lms_degree_mstr m on m.id=c.degree_name\n"
					+ "inner join reg_nch_working_place_dtl_a_ch h on h.regulation_p_id = e.id \n"
					+ "where e.nrh_en_no!='null'   "+SearchValue+ "  group by 1,2,3,4,5,6,7,8,9,10,11,12,13,14 order by id " + orderType
	                + " limit " + pageL + " OFFSET " + startPage;
			
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, nrh_en_no,first_name,status,per_state,from_date,to_date ,gender ,state_reg_no ,dob ,institute_name ,type_status);
			System.err.println("stmt--fdgdfg---reg report-----"+stmt);
			ResultSet rs = stmt.executeQuery();
			
            System.err.println("stmt-----reg report-----"+stmt);
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
 				columns.put("img",   fname   +  "  <img class='d-block img5050 imageZomm' alt='No Image' id='myImg"+rs.getString("id")+"' src='MedicalImagePath2?i_id="+rs.getString("id")+"' /> ");
				 
				 
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
	public long DataTableEdu_Reg_Report_masterDataTotalCount(String Search, String nrh_en_no,String first_name, String status,String per_state,String from_date,String to_date ,
			String gender ,String state_reg_no ,String dob ,String institute_name  ,String type_status) throws ParseException {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, nrh_en_no,first_name, status,per_state,from_date,to_date ,gender ,state_reg_no ,dob ,institute_name ,type_status);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
 	
			q="select count(*)  from ("
				+"	select e.ayush_id,e.abha_no,e.id,e.nrh_en_no,e.first_name,e.photo_path,concat(d.district_name ||' </br> '|| s.state_name ||' </br> '|| e.pre_pincode) as pre_corre_add, \n"
							+ "concat(dd.district_name ||' </br> '|| ss.state_name ||' </br> '|| e.per_pincode) as permanent_add,\n"
							+ "e.email_id,\n"
							+ "concat(to_char(e.dob , 'DD/MM/YYYY') ||' </br> '|| n.nationality) as dob_nationality,\n"
							+ "concat(e.ayush_id,e.abha_no) as ayu_abha,concat(e.state_reg_no ||' </br> '|| to_char(e.created_date , 'DD/MM/YYYY') ||' </br> '|| ss.state_name) as no_date_name_renew,\n"
							+ "e.father_name, e.status,e.mo_no,\n"
							+ "String_agg(distinct m.degree_name ||' '|| c.month_and_year_of_degree ||' '|| c.name_of_institute , '</br>')  as degree_my_inst,\n"
							+ " String_agg( distinct h.place_of_working_name ||' '||  h.hos_address1 ||' '|| h.hos_address2 ||' '|| h.hos_address3 , '</br>')  as hos_name_addr,\n"
							+ "String_agg(distinct h.name_of_res_p,'</br>') as name_of_res_p\n"
							+ "from reg_nch_form_a_p  e\n"
							+ "inner join recr_nationality_mst n on  (n.nationality_id)  = e.nationality\n"
							+ "inner join edu_lms_district_mstr d on  (d.district_id)  = e.pre_district\n"
							+ "inner join edu_lms_state_mstr s on  (s.state_id)  = e.pre_state\n"
							+ "inner join edu_lms_district_mstr dd on  (dd.district_id )  = e.per_district\n"
							+ "--inner join reg_nch_registration_a pr on pr.user_id=e.user_id\n"
							+ "inner join edu_lms_state_mstr ss on    ss.state_id    = e.per_state\n"
							+ "inner join reg_nch_med_degree_dtl_a_ch c on c.regulation_p_id = e.id\n"
							+ "inner join edu_lms_type_of_degree_mstr tdm on tdm.id=c.type_of_degree\n"
							+ "inner join edu_lms_degree_mstr m on m.id=c.degree_name\n"
							+ "inner join reg_nch_working_place_dtl_a_ch h on h.regulation_p_id = e.id \n"
							+ "where e.nrh_en_no!='null'    "
					+ " "+SearchValue+" group by 1,2,3,4,5,6,7,8,9,10,11,12,13,14 ) a ";
			 
			 
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, nrh_en_no,first_name,status,per_state,from_date,to_date ,gender ,state_reg_no ,dob ,institute_name ,type_status);
			System.out.println("stmt-----reg report count-----"+stmt);
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
	public String GenerateQueryWhereClause_SQL(String Search, String nrh_en_no,String first_name,String status,String per_state,String from_date,String to_date ,String gender ,
			String state_reg_no ,String dob ,String institute_name ,String type_status) throws ParseException {
		String SearchValue = "";
 
		if (!nrh_en_no.trim().equals("") && nrh_en_no != null) {
			SearchValue += " and upper(nrh_en_no) like ? ";
		}
		
		if (!first_name.trim().equals("") && first_name != null) {
			SearchValue += " and upper(first_name) like ? ";
  		} 
		if (!status.equals("0")) {
			SearchValue += " and e.status = ? ";
 		}
		if (!per_state.equals("0")) {
			SearchValue += " and cast(reg_state as character varying )  = ? ";
			}
		
		if (!from_date.equals("DD/MM/YYYY") && from_date != "DD/MM/YYYY" && !from_date.equals("") && from_date != null  && 
				!to_date.equals("DD/MM/YYYY") && to_date != "DD/MM/YYYY" && !to_date.equals("") && to_date != null 
				) {	
			SearchValue += " and e.created_date between CAST(? as timestamp without time zone) and CAST(? as timestamp without time zone) ";
			  }
		
		if (!gender.equals("0") && gender != null) {
			SearchValue += " and e.gender = ?::integer ";
		} 
 		
		if (!state_reg_no.trim().equals("")&& state_reg_no != null) {
			SearchValue += " and upper(state_reg_no) like ? ";
		}
		if (!dob.equals("DD/MM/YYYY") && dob != "DD/MM/YYYY" && !dob.equals("") && dob != null  ) {	
			SearchValue += " and e.dob = CAST(? as timestamp without time zone)   ";
			  }
		
		
		if (!institute_name.equals("0")) {
			SearchValue += " and cast(c.name_of_institute as character varying )  = ? ";
			}
		
		
		if (!type_status.equals("0")) {
			SearchValue += " and e.pract_type = ? ";
 		}
	  	
 				//end
				
//				 
//				if (!qtr.equals("0")) {
//					SearchValue += " and EXTRACT (QUARTER FROM e.created_date) = ? ";
//					System.err.println("qtr" + SearchValue);
//
//				}
//				 
				
				if (Search != null && !Search.equals("")) {
					Search = Search.toLowerCase();
 					SearchValue += " and ";
					SearchValue += "  (lower(e.first_name) like ? or lower(e.father_name) like ? or lower(d.district_name) like ? or lower(nrh_en_no) like ? or lower(s.state_name) like ? or "
							     + "  CAST(e.pre_pincode as character varying) like ? or lower(dd.district_name) like ? or lower(ss.state_name) like ? or CAST(e.per_pincode as character varying) like ?"
							     + "  or lower(e.email_id) like ? or CAST(e.mo_no as character varying) like ? or to_char(e.dob , 'DD/MM/YYYY') like ? or lower(n.nationality) like ? "
							     + "  or lower(m.degree_name) like ? or lower(e.state_reg_no) like ? or to_char(e.created_date , 'DD/MM/YYYY') like ? or lower(h.place_of_working_name) like ?"
							     + " or lower(h.hos_address1) like ? or lower(h.hos_address2) like ? or lower(h.hos_address3) like ? or lower(h.name_of_res_p) like ?  or lower(e.ayush_id) like ?"
							     + " or lower(e.abha_no) like ?) ";
				}

	
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String nrh_en_no,String first_name, String status,
			String per_state,String from_date,String to_date ,String gender ,String state_reg_no ,String dob ,String institute_name ,String type_status) {
		int flag = 0;
		try {
 		
			if (!nrh_en_no.equals("") && nrh_en_no != null) {
				flag += 1;
				stmt.setString(flag, "%" + nrh_en_no.toUpperCase() + "%");
			}
			if (!first_name.equals("") && first_name != null) {
				flag += 1;
				stmt.setString(flag, "%" + first_name.toUpperCase() + "%");
			}
			if (!status.equals("0")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(status));
				}
			
			if (!per_state.equals("0")) {
				flag += 1;
				stmt.setString(flag, per_state);
				}
			if (!from_date.equals("DD/MM/YYYY") && from_date != "DD/MM/YYYY" && !from_date.equals("") && from_date != null  && 
					!to_date.equals("DD/MM/YYYY") && to_date != "DD/MM/YYYY" && !to_date.equals("") && to_date != null 
					) {	
				flag += 1;
				stmt.setString(flag,from_date);
				flag += 1;
				stmt.setString(flag,to_date);
			}
			if (!gender.equals("0") && gender != null) {
				flag += 1;
				stmt.setString(flag,  gender);
			}
			if (!state_reg_no.equals("") && state_reg_no != null) {
				flag += 1;
				stmt.setString(flag, "%" + state_reg_no.toUpperCase() + "%");
			}
			if (!dob.equals("DD/MM/YYYY") && dob != "DD/MM/YYYY" && !dob.equals("") && dob != null  ) {	
				flag += 1;
				stmt.setString(flag,dob);
				 
			}
			if (!institute_name.equals("0")) {
				flag += 1;
				stmt.setString(flag, institute_name);
				}
			
			if (!type_status.equals("0")) {
				flag += 1;
				stmt.setString(flag,  type_status);
				}
 
			
 			
//			if (!qtr.equals("0")) {
//				flag += 1;
// 			     stmt.setInt(flag,2);
//			}
			System.err.println("search-----------"+Search);
			if (Search != null && !Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
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
	  		query = "select $fildname from reg_nch_form_a_p where id=?";
	  		query = query.replace("$fildname", fildname);
	  		
	  		stmt = conn.prepareStatement(query);
	  		stmt.setInt(1, Integer.parseInt(id));
	  		
	  		System.out.println("stmt====pdf====="+stmt);
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
//	  																						--04-03-2023
		public ArrayList<ArrayList<String>> DataTableEdu_Reg_Report_masterDataList_pdf(String Search,String nrh_en_no, String first_name, 
				String status,String per_state,String from_date,String to_date  ,String gender ,
				String state_reg_no ,String dob ,String institute_name ,String type_status) throws ParseException {
			
	
		  ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		  String SearchValue = GenerateQueryWhereClause_SQL(Search,nrh_en_no,first_name,status,per_state,from_date,to_date 
					,gender ,state_reg_no ,dob ,institute_name ,type_status);
			Connection conn = null;
			String q = "";
			try {

				conn = dataSource.getConnection();
				String pageL = "";
				
	 		 
				q="select e.ayush_id,e.abha_no,e.id,e.nrh_en_no,e.first_name,e.photo_path,concat(d.district_name ,s.state_name ,e.pre_pincode) as pre_corre_add, \n"
						+ "concat(dd.district_name ,ss.state_name ,e.per_pincode) as permanent_add,\n"
						+ "concat(e.email_id,e.mo_no) as email_mo,\n"
						+ "concat(to_char(e.dob , 'DD/MM/YYYY') ,n.nationality) as dob_nationality,\n"
						+ "concat(e.ayush_id,e.abha_no) as ayu_abha,concat(e.state_reg_no,to_char(e.created_date , 'DD/MM/YYYY') ,ss.state_name) as no_date_name_renew,\n"
						+ "e.father_name, e.status,e.mo_no,\n"
						+ "String_agg(distinct m.degree_name ||' '|| c.month_and_year_of_degree ||' '|| c.name_of_institute , ',')  as degree_my_inst,\n"
						+ " String_agg( distinct h.place_of_working_name ||' '||  h.hos_address1 ||' '|| h.hos_address2 ||' '|| h.hos_address3 , ',')  as hos_name_addr,\n"
						+ "String_agg(distinct h.name_of_res_p,',') as name_of_res_p\n"
						+ "from reg_nch_form_a_p  e\n"
						+ "inner join recr_nationality_mst n on  (n.nationality_id)  = e.nationality\n"
						+ "inner join edu_lms_district_mstr d on  (d.district_id)  = e.pre_district\n"
						+ "inner join edu_lms_state_mstr s on  (s.state_id)  = e.pre_state\n"
						+ "inner join edu_lms_district_mstr dd on  (dd.district_id )  = e.per_district\n"
						+ " -- inner join reg_nch_registration_a pr on pr.user_id=e.user_id\n"
						+ "inner join edu_lms_state_mstr ss on   ss.state_id  = e.reg_state\n"
						+ "inner join reg_nch_med_degree_dtl_a_ch c on c.regulation_p_id = e.id\n"
						+ "inner join edu_lms_type_of_degree_mstr tdm on tdm.id=c.type_of_degree\n"
						+ "inner join edu_lms_degree_mstr m on m.id=c.degree_name\n"
						+ "inner join reg_nch_working_place_dtl_a_ch h on h.regulation_p_id = e.id \n"
						+ "where e.nrh_en_no is not null   "+SearchValue+"   group by 1,2,3,4,5,6,7,8,9,10,11,12,13,14 order by id";
				
				
				
				PreparedStatement stmt = conn.prepareStatement(q);
//				--04-03-2023
				stmt = setQueryWhereClause_SQL(stmt, Search, nrh_en_no,first_name,status,per_state,from_date,to_date ,gender ,state_reg_no ,dob ,institute_name ,type_status);
				System.out.println("DataTableEdu_Reg_Report_masterDataList_pdf --------------"+stmt);
				ResultSet rs = stmt.executeQuery();
	//			stmt.setInt(1,status123);
			
				ResultSetMetaData metaData = rs.getMetaData();
				
				int i=1;
				while (rs.next()) {
					
					ArrayList<String> alist = new ArrayList<String>();
//					alist.add(String.valueOf(i)); // 1
//					alist.add(rs.getString("ayu_abha"));
//					alist.add(rs.getString("first_name"));//3
//					alist.add(rs.getString("nrh_en_no")); // 2
//					alist.add(rs.getString("father_name"));//4
//					alist.add(rs.getString("pre_corre_add"));//5
//					alist.add(rs.getString("permanent_add"));//6
//	//				alist.add(rs.getString("aadhaar_no"));//7
//					alist.add(rs.getString("email_mo"));//8
//					alist.add(rs.getString("dob_nationality"));//9
//	//				alist.add(rs.getString("deg_uni_my"));//10
//	//				alist.add(rs.getString("no_date_name_renew"));//11
//	//				alist.add(rs.getString("name_of_hospital_teaching"));//12
//	//				alist.add(rs.getString("name_of_res_p"));//13
//					
//					alist.add(rs.getString("degree_my_inst"));//14
//					alist.add(rs.getString("no_date_name_renew"));//14
//					alist.add(rs.getString("hos_name_addr"));//14
//					alist.add(rs.getString("name_of_res_p"));//14
//					alist.add(rs.getString("photo_path"));//14
					
					alist.add(String.valueOf(i)); // 1
				
					alist.add(rs.getString("first_name"));//2
//					alist.add(rs.getString("photo_path"));//3
 					alist.add(rs.getString("father_name"));//4
					alist.add(rs.getString("pre_corre_add"));//5
					alist.add(rs.getString("permanent_add"));//6
 					alist.add(rs.getString("email_mo"));//7
					alist.add(rs.getString("dob_nationality"));//8
					alist.add(rs.getString("degree_my_inst"));//9
					alist.add(rs.getString("no_date_name_renew"));//10
					alist.add(rs.getString("hos_name_addr"));//11
					alist.add(rs.getString("name_of_res_p"));//12
// 					alist.add(rs.getString("ayu_abha")); //13
//					alist.add(rs.getString("nrh_en_no")); // 14
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
	  
	  public ArrayList<ArrayList<String>>  getRegulation_Report_Excel( String Search,String nrh_en_no, String first_name, String status,String per_state,
			  String from_date,String to_date  ,String gender ,	String state_reg_no ,String dob ,String institute_name ,String type_status)
				throws ParseException {
			
			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			String SearchValue = GenerateQueryWhereClause_SQL(Search,nrh_en_no,first_name,status,per_state,from_date,to_date  ,gender ,state_reg_no ,dob ,institute_name ,type_status);
			Connection conn = null;
			String q = "";
			
			try {
				conn = dataSource.getConnection();

				String qry = "";

						
				q="select e.ayush_id,e.abha_no,e.id,e.nrh_en_no,e.first_name,e.photo_path,concat(d.district_name ,' ',s.state_name ,' ',e.pre_pincode) as pre_corre_add, \n"
						+ "concat(dd.district_name ,' ',ss.state_name,' ' ,e.per_pincode) as permanent_add,\n"
						+ "concat(e.email_id,' ',e.mo_no) as email_mo,\n"
						+ "concat(to_char(e.dob , 'DD/MM/YYYY'),' ' ,n.nationality) as dob_nationality,\n"
						+ "concat(e.ayush_id,' ',e.abha_no) as ayu_abha,concat(e.state_reg_no,' ',to_char(e.created_date , 'DD/MM/YYYY'),' ' ,ss.state_name) as no_date_name_renew,\n"
						+ "e.father_name, e.status,e.mo_no,\n"
						+ "String_agg(distinct m.degree_name ||' '|| c.month_and_year_of_degree ||' '|| c.name_of_institute , ',')  as degree_my_inst,\n"
						+ " String_agg( distinct h.place_of_working_name ||' '||  h.hos_address1 ||' '|| h.hos_address2 ||' '|| h.hos_address3 , ',')  as hos_name_addr,\n"
						+ "String_agg(distinct h.name_of_res_p,',') as name_of_res_p,e.ayush_id,e.nrh_en_no\n"
						+ "from reg_nch_form_a_p  e\n"
						+ "inner join recr_nationality_mst n on  (n.nationality_id)  = e.nationality\n"
						+ "inner join edu_lms_district_mstr d on  (d.district_id)  = e.pre_district\n"
						+ "inner join edu_lms_state_mstr s on  (s.state_id)  = e.pre_state\n"
						+ "inner join edu_lms_district_mstr dd on  (dd.district_id )  = e.per_district\n"
						+ " -- inner join reg_nch_registration_a pr on pr.user_id=e.user_id\n"
						+ "inner join edu_lms_state_mstr ss on   ss.state_id  = e.reg_state\n"
						+ "inner join reg_nch_med_degree_dtl_a_ch c on c.regulation_p_id = e.id\n"
						+ "inner join edu_lms_type_of_degree_mstr tdm on tdm.id=c.type_of_degree\n"
						+ "inner join edu_lms_degree_mstr m on m.id=c.degree_name\n"
						+ "inner join reg_nch_working_place_dtl_a_ch h on h.regulation_p_id = e.id \n"
						+ "where e.nrh_en_no is not null " +SearchValue+ " group by 1,2,3,4,5,6,7,8,9,10,11,12,13,14 order by id";
				
					
					PreparedStatement stmt = conn.prepareStatement(q);
					stmt = setQueryWhereClause_SQL(stmt, Search, nrh_en_no,first_name,status,per_state,from_date,to_date ,gender ,state_reg_no ,dob ,institute_name ,type_status);
					ResultSet rs = stmt.executeQuery();
					
					System.err.println("-----------EXCEL Export Query----------"+stmt);
					
					int i = 1;
					while (rs.next()) {
					ArrayList<String> alist = new ArrayList<String>();
					alist.add(String.valueOf(i)); // 1
					

					alist.add(rs.getString("first_name"));
 					alist.add(rs.getString("father_name"));
					alist.add(rs.getString("pre_corre_add"));
					alist.add(rs.getString("permanent_add"));
 					alist.add(rs.getString("email_mo"));
					alist.add(rs.getString("dob_nationality"));
					alist.add(rs.getString("degree_my_inst"));
					alist.add(rs.getString("no_date_name_renew"));
					alist.add(rs.getString("hos_name_addr"));
					alist.add(rs.getString("name_of_res_p"));
					alist.add(rs.getString("ayush_id"));
					alist.add(rs.getString("nrh_en_no"));
					
					list.add(alist);
					i++;
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
						e.printStackTrace();
					}
				}
			}
			return list;
		}
	  



	 
}
