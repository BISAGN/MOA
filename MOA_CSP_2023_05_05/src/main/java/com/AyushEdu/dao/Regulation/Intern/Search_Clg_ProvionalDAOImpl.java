package com.AyushEdu.dao.Regulation.Intern;

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
public class Search_Clg_ProvionalDAOImpl  implements Search_Clg_ProvionalDAO {
  
	 
	private static final Session sessionHQL = null;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	 
	@Override
	public List<Map<String, Object>> DataTablegetFilter_Edu_clg_provisional_data(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,  String first_name, String status,String gender ,
			  String registration_state ,String dob,String date_of_reg ,String institute_name) {
		 
		String SearchValue = GenerateQueryWhereClause_SQL(Search,first_name,status,gender, registration_state, dob,date_of_reg,institute_name);
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
		   
				
//				q= "select distinct e.id,e.nrh_en_no,e.first_name,e.gender,e.photo_path ,d.district_name as pre_district,s.state_name as pre_state,e.pre_pincode, \n"
//						+ "dd.district_name as per_district ,ss.state_name as per_state,e.per_pincode,e.aadhaar_no,e.email_id, to_char(e.dob , 'DD/MM/YYYY') as dob,\n"
//						+ "n.nationality, to_char(e.date_of_reg , 'DD/MM/YYYY') as date_of_reg,e.ayush_id,e.abha_no,\n"
//						+ " e.father_name, e.status,  e.reject_remarks, ss.state_name as registration_state   ,pr.name_of_institute  from reg_nch_form_a_p  e \n"
//						+ " inner join \n"
//						+ " recr_nationality_mst n on  (n.nationality_id)  = e.nationality\n"
//						+ " inner join edu_lms_district_mstr d on (d.district_id )  = e.pre_district\n"
//						+ " inner join edu_lms_state_mstr s on  (s.state_id )  = e.pre_state\n"
//						+ " inner join edu_lms_district_mstr dd on  (dd.district_id )  = e.per_district\n"
//						+ " inner join edu_lms_state_mstr ss on  (ss.state_id )  = e.reg_state\n"
//						+ "inner join reg_nch_med_degree_dtl_a_ch pr on pr.regulation_p_id=e.id \n"
// 				+ " where e.id!= 0  and role=25 and e.del_status='0'   "+ SearchValue +" order by id " + orderType
// 			+ " limit " + pageL + " OFFSET " + startPage;	;

 			
 			
 			q= " select distinct e.id,e.nrh_en_no,e.first_name,e.gender,e.photo_path ,d.district_name as pre_district,s.state_name as pre_state,e.pre_pincode, \n"
 					+ "dd.district_name as per_district ,ss.state_name as per_state,e.per_pincode,e.aadhaar_no,e.email_id, to_char(e.dob , 'DD/MM/YYYY') as dob,\n"
 					+ "n.nationality, to_char(e.date_of_reg , 'DD/MM/YYYY') as date_of_reg,e.ayush_id,e.abha_no,\n"
 					+ " e.father_name, e.status,  e.reject_remarks, ss.state_name as registration_state   ,pr.name_of_institute \n"
 					+ " from reg_nch_form_a_p  e \n"
 					+ "  inner join reg_nch_details_a de on  (de.aadhar_card)  = e.aadhaar_no\n"
 					+ " inner join   recr_nationality_mst n on  (n.nationality_id)  = e.nationality\n"
 					+ " inner join edu_lms_district_mstr d on (d.district_id )  = e.pre_district\n"
 					+ " inner join edu_lms_state_mstr s on  (s.state_id )  = e.pre_state\n"
 					+ " inner join edu_lms_district_mstr dd on  (dd.district_id )  = e.per_district\n"
 					+ " inner join edu_lms_state_mstr ss on  (ss.state_id )  = e.reg_state\n"
 					+ "inner join reg_nch_med_degree_dtl_a_ch pr on pr.regulation_p_id=e.id \n"
 					+ " where e.id!= 0  and role=35 and e.del_status='0'      "+ SearchValue +" order by id " + orderType
 				//	+ "  and e.clg_status = 0  and de.institude_userid  = '146'    order by id asc limit 10 OFFSET 0\n"
 				+ " limit " + pageL + " OFFSET " + startPage;	;
 			
 			
 			
				
				
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, first_name,status,gender,registration_state,   dob,date_of_reg,institute_name);
		    System.err.println("search---cloolage provistional  ----"+stmt);
			ResultSet rs = stmt.executeQuery();
 			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			
			int countFunction=1;
			int countFunction2=1;
			int countFunction3=1;
			int countFunction4=1;
			int countFunction5=1;
			int countFunction6=1;
			
			while (rs.next()) {
				
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				
				columns.put("img","<img class='d-block img5050 imageZomm' alt='No Image' id='myImg"+rs.getString("id")+"' src='MedicalImagePath1?i_id="+rs.getString("id")+"' onclick='imageView("+rs.getString("id")+");' />");
 
				String f = "";
				String action = "";
				String f1 = "";
				String chekboxaction="";
				String vd = "";
				String vioch = "";
				String vm1 ="";
				String vmp = "";

				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){EditData('"
						+ rs.getString("id") + "','" + rs.getString("nrh_en_no") + "','" + rs.getString("status")
						+ "') }else{ return false;}\"";
				f = "<i class='fa fa-pencil-square-o instview' title='Edit Data'>"
						+ "<input type='hidden' id='inst1"+countFunction+"' value="+rs.getString("id")+">"
						+ "<input type='hidden' id='inst2"+countFunction+"' value="+rs.getString("nrh_en_no")+">"
						+ "<input type='hidden' id='inst3"+countFunction+"' value="+rs.getString("status")+">"
						+ "</i>";
				
				String VIEWDEGREE = "onclick=\" if (confirm('Are You Sure You Want to Show Detail ?') ){Pop_Up_Degree('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				 vd = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn btn-hover btn-sm btnview' value='ADD' title='View Data' >\n"
								+ "		<i class='lni lni-eye practview_degree'>"
								+ "<input type='hidden' id='prac_degree"+countFunction2+"' value="+rs.getString("id")+">"
							   + "<input type='hidden' id='name_of_institute"+countFunction2+"' value="+rs.getString("name_of_institute")+">"
								+ "</i></a> </li></ul>";

						String VIEWIOCH = "onclick=\" if (confirm('Are You Sure You Want to Show Detail ?') ){Pop_Up_IOCH('"
						+ rs.getString("id") + "') }else{ return false;}\"";
						vioch = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn btn-hover btn-sm btnview' value='ADD' title='View Data' >\n"
								+ "		<i class='lni lni-eye pract_hos'>"
								+ "<input type='hidden' id='prac_hoss"+countFunction3+"' value="+rs.getString("id")+">"
								+ "</i></a> </li></ul>";

				

				String Checkbox="<input class='nrCheckBox' type='checkbox' id='" + rs.getObject(1)//13
				+ "' name='cbox' onclick='checkCKBT()' onchange='checkbox_count(this," + rs.getObject(1) + ");' />";
		  
		     String CheckboxId="<input  type='hidden' id='id" + rs.getObject(1) + "' name='id" + rs.getObject(1) + "'  value='" + rs.getObject(1) + "'   />"
		     		+ "  <input  type='hidden' id='id\" + rs.getObject(1) + \"' name='id\" + rs.getObject(1) + \"'  value='\" + rs.getObject(1) + \"'   />"  ;
		    		         
		     chekboxaction+=Checkbox;
		    
		     String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<i class='fa fa-trash '  " + ADD1 + " title='Delete Data'></i>";
				
			
			String VIEWDEGREEP = "onclick=\" if (confirm('Are You Sure You Want to View Detail?') ){Pop_Up_DegreeP('"
					+ rs.getString("id") + "') }else{ return false;}\"";
			
			vm1 = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn btn-hover btn-sm btnview' value='ADD' title='View Data' >\n"
					+ "		<i class='lni lni-eye popdegreep'>"
					+ "<input type='hidden' id='popdegreepId"+countFunction4+"' value="+rs.getString("id")+"></i></a> </li></ul>";
			
			columns.put("vm1",vm1);	
			
			if(status.equals("1")) {
				
			String download1 = "onclick=\" if (confirm('Are You Sure You Want to download File  ?') ){getPDF('"
					+ rs.getString("id") + "') }else{ return false;}\"";
			
			vmp= "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn info-btn btn-hover btn-sm btndownload' value='ADD' title='Download Data' >\n"
					+ "<i class='lni lni-download degreepdf'>"
					+ "<input type='hidden' id='degreepdfId"+countFunction5+"' value="+rs.getString("id")+"></i></a> </li></ul>";
				}
			columns.put("vmp", vmp);
// 				if(status.equals("0")) {
// 					columns.put("chekboxaction", chekboxaction);
//					columns.put("vd", vd);
//					columns.put("vioch", vioch);
// 				}
//				if(status.equals("1") || status.equals("2")) {
// 					columns.put("vd", vd);
//					columns.put("vioch", vioch);
//				}
			columns.put("vd", vd);
			String p_a_u = "";

 
			
			
//			String pre_app_uni = "onclick=\" if (confirm('Are You Sure You Want to View and Verify Degree ?') ){fn_pre_app_uni('"
//					+ rs.getString("id") + "') }else{ return false;}\"";
// 
			p_a_u = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn btn-hover btn-sm btnview' value='ADD' title='View Data' >\n"
					+ "		<i class='lni lni-eye p_a_u'>"
					+ "<input type='hidden' id='p_a_uId"+countFunction6+"' value="+rs.getString("id")+"></i></a> </li></ul>";
			
			 
			columns.put("p_a_u", p_a_u);
			 
				countFunction+=1;
				countFunction2+=1;
				countFunction3+=1;
				countFunction4+=1;
				countFunction5+=1;
				countFunction6+=1;
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
	@SuppressWarnings("null")
	public String GenerateQueryWhereClause_SQL(String Search,  String first_name,String institute_status,String gender,String registration_state,
			 String dob,String date_of_reg,String institute_name) {

		String SearchValue = "";
		/// advance search
 
		if (!first_name.trim().equals("")) {
			SearchValue += " and upper(first_name) like ? ";
		}
		
		if (institute_status.equals("0")) {
			SearchValue += " and pr.clg_status = ? ";
		}
		if (institute_status.equals("1")) {
			SearchValue += " and pr.clg_status = ? ";
		}
		if (institute_status.equals("2")) {
			SearchValue += "and pr.clg_status  = ? ";
		}
		if (!gender.equals(" ") && gender != null) {
			SearchValue += " and e.gender = ?::integer ";
		} 
//		if (!reg_no.equals("") && reg_no != null) {
//			SearchValue += " and e.reg_no = ?::integer ";
//		}
		if (!registration_state.equals("0") && registration_state != null) {
			SearchValue += " and e.reg_state::integer = ?::integer ";
		}
		if (!institute_name.equals("0") && institute_name != null) {
			SearchValue += " and de.institude_userid  = ?::integer   ";
		}
		
		
		
		if (!dob.equals("DD/MM/YYYY") && dob != "DD/MM/YYYY" && !dob.equals("") && dob != null   ) {
			SearchValue += " and to_char(e.dob , 'DD/MM/YYYY') = ? ";
		}
		if (!date_of_reg.equals("") && date_of_reg != null && !date_of_reg.equals("DD/MM/YYY") ) {
			SearchValue += " and to_char(e.date_of_reg , 'DD/MM/YYYY') = ? ";
		}
		if (Search != null && !Search.equals("")) {
			Search = Search.toLowerCase();

			SearchValue += " and ";
			SearchValue += "  (lower(e.first_name) like ? ) ";
		}
	
		return SearchValue;
	}
	
	
	
	//////////////
	
	@Override
	public long DataTablegetTotalclg_provisional_dataCount(String Search, String first_name, String institute_status,String gender, 
			String registration_state,String dob,String date_of_reg,String institute_name) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, first_name, institute_status,gender,registration_state,  dob,date_of_reg ,institute_name);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
	
			 
		
 
			
			
			q = " select count(*) \n"
					+ " from (select distinct e.id,e.nrh_en_no,e.first_name,e.gender,e.photo_path ,d.district_name as pre_district,s.state_name as pre_state,e.pre_pincode, \n"
					+ "			dd.district_name as per_district ,ss.state_name as per_state,e.per_pincode,e.aadhaar_no,e.email_id, to_char(e.dob , 'DD/MM/YYYY') as dob,\n"
					+ "			n.nationality, to_char(e.date_of_reg , 'DD/MM/YYYY') as date_of_reg,e.ayush_id,e.abha_no,\n"
					+ "			 e.father_name, e.status,  e.reject_remarks, ss.state_name as registration_state   ,pr.name_of_institute \n"
					+ "			 from reg_nch_form_a_p  e \n"
					+ "			  inner join reg_nch_details_a de on  (de.aadhar_card)  = e.aadhaar_no\n"
					+ "			 inner join   recr_nationality_mst n on  (n.nationality_id)  = e.nationality\n"
					+ "			 inner join edu_lms_district_mstr d on (d.district_id )  = e.pre_district\n"
					+ "			 inner join edu_lms_state_mstr s on  (s.state_id )  = e.pre_state\n"
					+ "			 inner join edu_lms_district_mstr dd on  (dd.district_id )  = e.per_district\n"
					+ "			 inner join edu_lms_state_mstr ss on  (ss.state_id )  = e.reg_state\n"
					+ "			inner join reg_nch_med_degree_dtl_a_ch pr on pr.regulation_p_id=e.id \n"
					+ "			 where e.id!= 0  and role=35 and e.del_status='0'   "+ SearchValue +") a  ";
				 
		 
			
		 
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, first_name,institute_status,gender,registration_state,  dob,date_of_reg,institute_name);
			 System.out.println("search count -------------- clg ---"+stmt);
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
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String first_name, String institute_status,String gender,
			 String registration_state,String dob,String date_of_reg,String institute_name) {
		int flag = 0;
		try {

			
			if (!first_name.equals("") && first_name != null) {
				flag += 1;
				stmt.setString(flag, "%" + first_name.toUpperCase() + "%");
			}
			
			if (institute_status.equals("0")) {
				flag += 1;
				stmt.setInt(flag,0);
			}
			if (institute_status.equals("1")) {
				flag += 1;
				stmt.setInt(flag,1);
			}
			if (institute_status.equals("2")) {
				flag += 1;
				stmt.setInt(flag,2);
			}
			 
			
			if (!gender.equals(" ") && gender != null) {
				flag += 1;
				stmt.setString(flag,  gender);
			}
//			if (!reg_no.equals("") && reg_no != null) {
//				flag += 1;
//				stmt.setString(flag,  reg_no);
//			}
			if (!registration_state.equals("0") && registration_state != null) {
				flag += 1;
				stmt.setString(flag,  registration_state);
			}
			if (!institute_name.equals("0") && institute_name != null) {
				flag += 1;
				stmt.setString(flag,  institute_name);
			}
			
//			if (!dob.equals("") && dob != null && !dob.equals("DD/MM/YYYY")) {
				if (!dob.equals("DD/MM/YYYY") && dob != "DD/MM/YYYY" && !dob.equals("") && dob != null   ) {
				flag += 1;
				stmt.setString(flag,dob);
			}
			
			if (!date_of_reg.equals("") && date_of_reg != null && !date_of_reg.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag,date_of_reg);
			}
			
			if (Search != null && !Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				
//				flag += 1;
//				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}
	
 

	@Override
	public ArrayList<ArrayList<String>> medicalDataPreviewClgProvisional(int data  ,int d) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {

			conn = dataSource.getConnection();
			

// 
//			q= " select c.id,name_of_institute,month_and_year_of_degree,td.type_of_degree,d1.degree_name , \n"
//					+ "CASE WHEN c.clg_status = 1 THEN 'Verified'    WHEN c.clg_status = 2 THEN 'Reverted'    ELSE 'Not Verified'   END AS status  ,\n"
//					+ "u.university_name from reg_nch_form_a_p r\n"
//					+ "inner join reg_nch_med_degree_dtl_a_ch c on c.regulation_p_id = r.id\n"
//					+ "inner join edu_lms_degree_mstr d1 on d1.id=c.degree_name\n"
//					+ "inner join edu_lms_type_of_degree_mstr td on td.id=d1.type_of_degree \n"
//					+ "inner join edu_lms_university_mstr u on cast(u.id as character varying )=c.name_of_institute  \n"
//					+ "where  r.id = (SELECT id from reg_nch_form_a_p where user_id = ? order by id DESC limit 1) \n"
//					+ "and c.name_of_institute = ? "; 
			
			
			q= " select c.id,name_of_institute,month_and_year_of_degree,td.type_of_degree,d1.degree_name , \n"
					+ "CASE WHEN c.clg_status = 1 THEN 'Verified'    WHEN c.clg_status = 2 THEN 'Reverted'    ELSE 'Not Verified'   END AS status  , u.university_name\n"
					+ "from reg_nch_form_a_p r\n"
					+ "inner join reg_nch_med_degree_dtl_a_ch c on c.regulation_p_id = r.id\n"
					+ "inner join reg_nch_details_a de on  (de.aadhar_card)  = r.aadhaar_no \n"
					+ "inner join edu_lms_degree_mstr d1 on d1.id=c.degree_name\n"
					+ "inner join edu_lms_type_of_degree_mstr td on td.id=d1.type_of_degree \n"
					+ "inner join edu_lms_university_mstr u on cast(u.id as character varying )=c.name_of_institute  \n"
					+ "where  r.id = (SELECT id from reg_nch_form_a_p where user_id =  ? order by id DESC limit 1) \n"
					+ "and de.institude_userid =  ?::integer  " ;
			
			
			
			
			
			
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, data);
			stmt.setString(2, String.valueOf(d));
			
			ResultSet rs = stmt.executeQuery();
           System.err.println("stmt--medical attch preview collage--------"+stmt);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("id"));// 0
				list.add(rs.getString("name_of_institute"));// 1
//				list.add(rs.getString("attachment_path"));// 2
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
  
  
	public String Clg_Approve_DegreeData(String a ,String status ,String u_id,int u_id_n  ,String cp) {
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
					 
//		if(Integer.parseInt(cp) >= 4) {
//	 	  //main tbl 
//		    stmt1 = conn1.prepareStatement(" update reg_nch_form_a_p set   institute_status= ?  , state_status=0  ,role=27   where id=?");
//			stmt1.setInt(1, Integer.parseInt(status));
//			stmt1.setInt(2, Integer.parseInt(u_id));
//			out1 = stmt1.executeUpdate();
//			
//			 //parent tbl 
//			stmt = conn.prepareStatement(" update reg_nch_med_degree_dtl_a_ch set   status= ?       where id=?");
//			stmt.setInt(1, Integer.parseInt(status));
//			stmt.setInt(2,  id);
//			out = stmt.executeUpdate();
//	 
//			//role info
//			 stmt2 = conn2.prepareStatement("update userroleinformation set role_id=?  where user_id=?");
//			 System.err.println("st------"+stmt);
//			 stmt2.setInt(1,  (27));
//			 stmt2.setInt(2, user_id);
//		      out2 = stmt2.executeUpdate();
//	  }
		//else {
			
			stmt1 = conn1.prepareStatement(" update reg_nch_form_a_p set   clg_status= ?  , institute_status=0     where id=?");
			stmt1.setInt(1, Integer.parseInt(status));
			stmt1.setInt(2, Integer.parseInt(u_id));
			out1 = stmt1.executeUpdate();
			stmt = conn.prepareStatement(" update reg_nch_med_degree_dtl_a_ch set   clg_status= ?       where id=?");
			stmt.setInt(1, Integer.parseInt(status));
	 		stmt.setInt(2,  id);
			out = stmt.executeUpdate();
		//}
					
				 
						 
	 
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

	 
	public String Clg_Reject_DegreeData(String a,String status,String reject_remarks) {
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
					
					stmt = conn.prepareStatement(" update reg_nch_med_degree_dtl_a_ch set  clg_status=?,reject_remarks = ?  where id=?");
					stmt.setInt(1, Integer.parseInt(status));
					stmt.setString(2, reject_remarks);
					stmt.setInt(3, id);
					 
					int p_id = assetupd.getRegulation_p_id();
 				 
					stmt1 = conn.prepareStatement(" update reg_nch_form_a_p set status=? , clg_status=?,reject_remarks = ?  where id=?");
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
  
  
}
