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

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Regulation.REG_NCH_FORM_A_P;


@Repository
public class Search_Insti_PracDAOImpl  implements Search_Insti_PracDAO {
  
	 
	private static final Session sessionHQL = null;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;


	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	 
	@Override
	public List<Map<String, Object>> DataTableEdu_Reg_masterDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,  String first_name, String status,String gender ,
			  String registration_state ,String dob,String date_of_reg ,String institute_name,HttpSession session) {
		 
		String SearchValue = GenerateQueryWhereClause_SQL(Search,first_name,status,gender, registration_state, dob,date_of_reg,institute_name);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			//KAJAL
			String role = session.getAttribute("role").toString();
			conn = dataSource.getConnection();
			String pageL = "";
			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
		   
			
				q= "select distinct e.id,e.nrh_en_no,e.first_name,e.gender,e.photo_path ,d.district_name as pre_district,s.state_name as pre_state,e.pre_pincode, \n"
						+ "dd.district_name as per_district ,ss.state_name as per_state,e.per_pincode,e.aadhaar_no,e.email_id, to_char(e.dob , 'DD/MM/YYYY') as dob,\n"
						+ "n.nationality, to_char(e.date_of_reg , 'DD/MM/YYYY') as date_of_reg,e.ayush_id,e.abha_no,\n"
						+ " e.father_name, e.status,  e.reject_remarks, ss.state_name as registration_state   ,pr.name_of_institute  from reg_nch_form_a_p  e \n"
						+ " inner join \n"
						+ " recr_nationality_mst n on  (n.nationality_id)  = e.nationality\n"
						+ " inner join edu_lms_district_mstr d on (d.district_id )  = e.pre_district\n"
						+ " inner join edu_lms_state_mstr s on  (s.state_id )  = e.pre_state\n"
						+ " inner join edu_lms_district_mstr dd on  (dd.district_id )  = e.per_district\n"
						+ " inner join edu_lms_state_mstr ss on  (ss.state_id )  = e.per_state\n"
						+ "inner join reg_nch_med_degree_dtl_a_ch pr on pr.regulation_p_id=e.id \n"
 				+ " where e.id!= 0 and role!=25  and e.del_status='0' and e.status=1 and e.clg_status= 1  "+ SearchValue +" order by id " + orderType
 			+ " limit " + pageL + " OFFSET " + startPage;	;
		
			

				
				
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, first_name,status,gender,registration_state,   dob,date_of_reg,institute_name);
		 	 System.err.println("search---institute ----"+stmt);
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
				 vd = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn-outline btn-hover btn-sm' value='ADD' title='View Data' >\n"
								+ "		<i class='lni lni-eye practview_degree'>"
								+ "<input type='hidden' id='prac_degree"+countFunction2+"' value="+rs.getString("id")+">"
							   + "<input type='hidden' id='name_of_institute"+countFunction2+"' value="+rs.getString("name_of_institute")+">"
								+ "</i></a> </li></ul>";

						String VIEWIOCH = "onclick=\" if (confirm('Are You Sure You Want to Show Detail ?') ){Pop_Up_IOCH('"
						+ rs.getString("id") + "') }else{ return false;}\"";
						vioch = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn-outline btn-hover btn-sm' value='ADD' title='View Data' >\n"
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
			
			vmp= "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn-outline btn-hover btn-sm' value='ADD' title='Download Data' >\n"
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
			SearchValue += " and pr.status = ? ";
		}
		if (institute_status.equals("1")) {
			SearchValue += " and pr.status = ? ";
		}
		if (institute_status.equals("2")) {
			SearchValue += "and pr.status  = ? ";
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
			SearchValue += " and pr.name_of_institute  = ?   ";
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
	public long DataTableEdu_Reg_masterDataTotalCount(String Search, String first_name, String institute_status,String gender, 
			String registration_state,String dob,String date_of_reg,String institute_name) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, first_name, institute_status,gender,registration_state,  dob,date_of_reg ,institute_name);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
	
			 
		
			q = " select count(*) \n"
					+ " from (select distinct e.id,e.nrh_en_no,e.first_name,e.gender,e.photo_path ,d.district_name as pre_district,s.state_name as pre_state,e.pre_pincode, \n"
					+ "dd.district_name as per_district ,ss.state_name as per_state,e.per_pincode,e.aadhaar_no,e.email_id, to_char(e.dob , 'DD/MM/YYYY') as dob,\n"
					+ "n.nationality, to_char(e.date_of_reg , 'DD/MM/YYYY') as date_of_reg,e.ayush_id,e.abha_no,\n"
					+ " e.father_name, e.status,  e.reject_remarks, ss.state_name as registration_state   ,pr.name_of_institute  from reg_nch_form_a_p  e \n"
					+ " inner join \n"
					+ " recr_nationality_mst n on  (n.nationality_id)  = e.nationality\n"
					+ " inner join edu_lms_district_mstr d on (d.district_id )  = e.pre_district\n"
					+ " inner join edu_lms_state_mstr s on  (s.state_id )  = e.pre_state\n"
					+ " inner join edu_lms_district_mstr dd on  (dd.district_id )  = e.per_district\n"
					+ " inner join edu_lms_state_mstr ss on  (ss.state_id )  = e.per_state\n"
					+ "inner join reg_nch_med_degree_dtl_a_ch pr on pr.regulation_p_id=e.id \n"
				+ " where e.id!= 0   and e.del_status='0'  and e.status=1 and e.clg_status= 1   "+ SearchValue +") a  ";
		 

				 
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, first_name,institute_status,gender,registration_state,  dob,date_of_reg,institute_name);
			 System.out.println("search count -------------- institute ---"+stmt);
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
	
	
	public String approve_INSregData(String a,String status,String b) {
		String[] id_list = a.split(":");

		Connection conn = null;
		Integer out = 0;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
	
			
				for (int i = 0; i < id_list.length; i++) {
					int id = Integer.parseInt(id_list[i]);
					Session sessionHQL = this.sessionFactory.openSession();
					REG_NCH_FORM_A_P assetupd =(REG_NCH_FORM_A_P)sessionHQL.get(REG_NCH_FORM_A_P.class, id);
					//stmt = conn.prepareStatement(" update reg_nch_form_a_p set institute_status=? ,state_status=0  where id=?");
					stmt = conn.prepareStatement(" update reg_nch_med_degree_dtl_a_ch set status=?    where regulation_p_id=? and name_of_institute=?");
					//System.err.println("st------"+stmt);
					stmt.setInt(1, Integer.parseInt(status));
					stmt.setInt(2, id);
					stmt.setString(3, b);
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
			return "Your details have been forwarded to the state council successfully.";
			}
			else
				return "Your details have not been forwarded to the state council successfully.";
		} else {
			if(status.equals("1")) {
				return "Your details have not been forwarded to the state council successfully.";
				}
			else
			return "UnSuccessfully";
		}
	}



public String reject_INSregData(String a,String status,String reject_remarks) {
	String[] id_list = a.split(":");

	Connection conn = null;
	Integer out = 0;
	String q = "";
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
			for (int i = 0; i < id_list.length; i++) {
				int id = Integer.parseInt(id_list[i]);
				Session sessionHQL = this.sessionFactory.openSession();
				REG_NCH_FORM_A_P assetupd =(REG_NCH_FORM_A_P)sessionHQL.get(REG_NCH_FORM_A_P.class, id);
				stmt = conn.prepareStatement(" update reg_nch_form_a_p set institute_status=? ,status=2,reject_remarks = ?  where id=?");
				//System.err.println("st------"+stmt);
				stmt.setInt(1, Integer.parseInt(status));
				stmt.setString(2, reject_remarks);
				stmt.setInt(3, id);
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

//--------------------------------------------download-------------------------

  public ArrayList<ArrayList<String>> DataTableEdu_Reg_masterDataList()
  {
  	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
  	Connection conn = null;
  	String q="";
  	String SearchValue="";
  	try{	  
  		conn = dataSource.getConnection();			 
  		PreparedStatement stmt=null;
  		
      
  		q="select e.id,e.nrh_en_no,e.first_name,e.gender,e.photo_path,e.pre_address,d.district_name as pre_district,s.state_name as pre_state,e.pre_pincode, \\n\"\n"
  				+ "					+ \"			 e.per_address,d.district_name as per_district,s.state_name as per_state,e.per_pincode,e.aadhaar_no,e.ph_no,e.mo_no,e.alt_mo_no1,e.email_id,\\n\"\n"
  				+ "					+ \"				 e.dob,c.name as nationality,e.degree,e.university,e.month_year, e.date_of_reg,e.name_reg,e.reg_renew_permanent,\\n\"\n"
  				+ "					+ \"                 e.name_of_hospital_teaching,\\n\"\n"
  				+ "					+ \"                 e.name_of_patient,e.crh_no,e.cch_no,\\n\"\n"
  				+ "					+ \"				 e.nch_no,e.father_name, e.status\\n\"\n"
  				+ "					+ \"from reg_nch_form_a_p  e\\n\"\n"
  				+ "					+ \"inner join edu_lms_country_mstr c on cast (c.id as character varying)  = e.nationality\\n\"\n"
  				+ "					+ \"inner join edu_lms_district_mstr d on cast (d.district_id as character varying)  = e.pre_district\\n\"\n"
  				+ "					+ \"inner join edu_lms_state_mstr s on cast (s.state_id as character varying)  = e.pre_state\\n\"\n"
  				+ "					+ \"where e.status='0' \"+ SearchValue +\" order by id" + SearchValue;
  			
  			
  			ResultSet rs = stmt.executeQuery();   
  			int i =1;  
  			while (rs.next()) {
  				ArrayList<String> list = new ArrayList<String>();
  				list.add(String.valueOf(i++)); //0

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
	public ArrayList<ArrayList<String>> get_Parctname_by_InstiReject_idata(String id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	String 	sq1="SELECT first_name FROM public.reg_nch_form_a_p WHERE id = ?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq1);
	        stmt.setInt(1, Integer.parseInt(id));
	       
	        ResultSet rs = stmt.executeQuery();  
	        
	        String str1="";
	        while(rs.next()){
	        	ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("first_name"));// 0
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
//	@Override
//	public ArrayList<ArrayList<String>> get_medicalcildID(String id) {
//		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
//	    Connection conn = null;
//	    try{          
//	    	conn = dataSource.getConnection();
//	    	
//	    	String 	sq1="SELECT name_of_institute FROM reg_nch_med_degree_dtl_a_ch WHERE regulation_p_id = ?";
//	    	
//	        PreparedStatement stmt = conn.prepareStatement(sq1);
//	        stmt.setInt(1, Integer.parseInt(id));
//	       
//	        ResultSet rs = stmt.executeQuery();  
//	        System.err.println("stmt------"+stmt);
//	        String str1="";
//	        while(rs.next()){
//	        	ArrayList<String> list = new ArrayList<String>();
//					list.add(rs.getString("name_of_institute"));// 0
//					alist.add(list);                           	  
//	        }
//	        rs.close();
//	        stmt.close();
//	        conn.close();
//	   }catch(SQLException e){
//		   e.printStackTrace();
//	   }       
//	    
//	    System.err.println("alist-------->   "+alist);
//	   return alist;
//	}  
}
