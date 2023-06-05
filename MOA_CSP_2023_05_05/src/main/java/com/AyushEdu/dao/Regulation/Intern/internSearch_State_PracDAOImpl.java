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

import com.AyushEdu.Models.UserRole;
import com.AyushEdu.Models.Regulation.REG_NCH_FORM_A_P;

@Repository
public class internSearch_State_PracDAOImpl implements internSearch_State_PracDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public List<Map<String, Object>> DataTableSeacrh_State_internDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,  String first_name, String status,String gender ,
			  String registration_state,String per_state, String per_district,String type_of_degree,String  degree_name,String place_of_working,
			 String dob,String date_of_reg,String institute_name,String type_status) {
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search,first_name,status,gender,registration_state,  per_state,   per_district,  type_of_degree,   degree_name,  
				place_of_working,   dob,date_of_reg,institute_name,type_status);
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
			
  
q= "select distinct e.id,e.nrh_en_no,e.first_name,e.gender,e.photo_path ,d.district_name as pre_district,s.state_name as pre_state,e.pre_pincode,\n"
		+ "dd.district_name as per_district,ss.state_name as per_state,e.per_pincode,e.aadhaar_no,e.email_id,\n"
		+ "to_char(e.dob , 'DD/MM/YYYY') as dob,n.nationality, to_char(e.date_of_reg , 'DD/MM/YYYY') as date_of_reg,e.state_reg_no,e.ayush_id,e.abha_no,\n"
		+ "e.father_name, e.status,  e.reject_remarks, \n"
		+ "ss.state_name as registration_state,to_char(e.created_date+interval '5 year','yyyy-MM-dd') as valid_upto ,e.nrh_status \n"
		+ "from reg_nch_form_a_p  e inner join recr_nationality_mst n on  (n.nationality_id )  = e.nationality\n"
		+ "inner join edu_lms_district_mstr d on  (d.district_id)  = e.pre_district\n"
		+ "inner join edu_lms_state_mstr s on  (s.state_id )  = e.pre_state\n"
		+ "inner join edu_lms_district_mstr dd on  (dd.district_id)  = e.per_district\n"
		+ "inner join edu_lms_state_mstr ss on  (ss.state_id)  = e.per_state\n"
		+ "inner join reg_nch_med_degree_dtl_a_ch pr on pr.regulation_p_id=e.id \n"
  		+ " where e.id!=0 and exists(Select id from reg_nch_med_degree_dtl_a_ch chh where  chh.regulation_p_id=e.id and chh.status=1)  "+ SearchValue +" order by id " + orderType
 	    + " limit " + pageL + " OFFSET " + startPage;
		
	 		
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, first_name,status,gender,  registration_state,  per_state ,  per_district,type_of_degree,   degree_name,  
					place_of_working,   dob,date_of_reg,institute_name,type_status);
 		 	 System.err.println("stmt---state----Student prob----"+stmt);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			
			int countFunction=1;
			int countFunctionDelete=1;
			int countFunction1=1;
			int countFunction2=1;
			int countFunction3=1;
			int countFunction4=1;
			int countFunction5=1;
			
			while (rs.next()) {
				
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
//				 onclick='imageView("+rs.getString("id")+");'
				columns.put("img","<img class='d-block img5050 imageZomm imgcsp' alt='No Image' id='myImg"+rs.getString("id")+"' src='MedicalImagePath1?i_id="+rs.getString("id")+"' />"
						+ "<input type='hidden' id='imghid"+countFunction5+"' value="+rs.getString("id")+">");
 
				String f = "";
				String action = "";
				String f1 = "";
				String chekboxaction="";
				String vd = "";
				String vioch = "";
				String dp = "";
				String dp1 = "";
				String vm1 ="";
				String vmp = "";
				
				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){EditData('"
						+ rs.getString("id") + "','" + rs.getString("nrh_en_no") + "','" + rs.getString("status")
						+ "') }else{ return false;}\"";
				f = "<i class='fa fa-pencil-square-o editstate' title='Edit Data'>"
						+ "<input type='hidden' id='CounId"+countFunction+"' value="+rs.getString("id")+">"
						+ "<input type='hidden' id='Counnrh_en_no"+countFunction+"' value="+rs.getString("nrh_en_no")+">"
						+ "<input type='hidden' id='Counstatus"+countFunction+"' value="+rs.getString("status")+"></i>";

			String st =	rs.getString(10) ;
			
				String Checkbox="<input class='nrCheckBox' type='checkbox' id='" + rs.getObject(1)//13
				+ "' name='cbox'  /> <input type='hidden' id='nrCHid"+rs.getObject(1)+"' value='"+st+"'>";
				
//				onclick='checkCKBT()'
//				onchange='checkbox_count(this," + rs.getObject(1) + ");' 
		     String CheckboxId="<input  type='hidden' id='id" + rs.getObject(1) + "' name='id" + rs.getObject(1)//14
				+ "' value='" + rs.getObject(1) + "'   />";
		     chekboxaction+=Checkbox; 
				
				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<i class='fa fa-trash statedlt'  title='Delete Data'>"
						+ "<input type='hidden' id='DCounId"+countFunctionDelete+"' value="+rs.getString("id")+"></i>";
				
//				String VIEWDEGREE = "onclick=\" if (confirm('Are You Sure You Want to Show Detail ?') ){Pop_Up_Degree('"
//						+ rs.getString("id") + "') }else{ return false;}\"";
//				
//				vd = "<ul class='buttons-group mainbtn action daobtn' id='dgpbtn"+countFunction1+"'><li><a class='main-btn dark-btn-outline btn-hover btn-sm' value='ADD' title='View Data' >\n"
//						+ "		<i class='lni lni-eye dgpop'>"
//						+ "<input type='hidden' name='degreeId"+countFunction1+"' id='degreeId"+countFunction1+"' value="+rs.getString("id")+"></i></a> </li></ul>";
//				// columns.put("cnt",countFunction1);	
//				
				
				String VIEWDEGREE = "onclick=\" if (confirm('Are You Sure You Want to Show Detail ?') ){Pop_Up_Degree('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				
				vd = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn-outline btn-hover btn-sm' value='ADD' title='View Data' >\n"
						+ " <i class='lni lni-eye dgpop'>"
						+ "<input type='hidden' id='degreeId"+countFunction1+"' value="+rs.getString("id")+"></i></a> </li></ul>";
						
				
				String VIEWIOCH = "onclick=\" if (confirm('Are You Sure You Want to Show Detail ?') ){Pop_Up_IOCH('"
				+ rs.getString("id") + "') }else{ return false;}\"";
				
				vioch = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn-outline btn-hover btn-sm' value='ADD' title='View Data' >\n"
					+ "		<i class='lni lni-eye popview'>"
					+ "<input type='hidden' id='pop_ioch"+countFunction2+"' value="+rs.getString("id")+"></i></a> </li></ul>";
			
				String VIEWDate = "onclick=\" if (confirm('Are You Sure You Want to Show Detail  ?') ){valid_upto('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				
				dp = "<input type='date' value='"+rs.getString("valid_upto")+"' name='valid_upto"+rs.getString("id")+"' id='valid_upto"+rs.getString("id")+"' class='form-control validup' >";
				
				String VIEWDatelab = "onclick=\" if (confirm('Are You Sure You Want to Show Detail ?') ){valid_upto('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				
				dp1 = "<lable>"+rs.getString("valid_upto") +"</label>";
							 
//				String ps = "";
//								
//				String perst = "onclick=\" if (confirm('Are You Sure You Want to Show Detail ?') ){fn_perst('"
//						+ rs.getString("id") + "') }else{ return false;}\"";
//						
//				ps = "<input type='text' value='"+rs.getString("per_state")+"' name='per_state"+rs.getString("id")+"' id='per_state"+rs.getString("id")+"' class='form-control' >";
//						
		//download 

				String VIEWDEGREEP = "onclick=\" if (confirm('Are You Sure You Want to View Detail?') ){Pop_Up_DegreeP('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				
				vm1 = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn-outline btn-hover btn-sm' value='ADD' title='View Data' >\n"
						+ "<i class='lni lni-eye popdegreep'>"
						+ "<input type='hidden' id='popdegreepId"+countFunction3+"' value="+rs.getString("id")+"></i></a> </li></ul>";
						
				columns.put("vm1",vm1);	
				 //	 Integer nrhs =   (int) rs.getObject(25);
				 	 String nrh_en_no =   (String) rs.getObject(2);
				 	 System.err.println("nrh_en_no-------"+nrh_en_no);
			//if(!nrh_en_no.equals(null) && nrh_en_no!="" ) { 
				
				String download1 = "onclick=\" if (confirm('Are You Sure You Want to download File ?') ){getPDF('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				vmp= "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn-outline btn-hover btn-sm' value='ADD' title='Download Data' >\n"
						+ "<i class='lni lni-download degreepdf'>"
						+ "<input type='hidden' id='degreepdfId"+countFunction4+"' value="+rs.getString("id")+"></i></a> </li></ul>";
				
			//}
				
				action =f+" "+  f1  ;
				 
				columns.put("chekboxaction", chekboxaction);
				columns.put("action", action);
  				columns.put("vd", vd);
				columns.put("vioch", vioch);
				columns.put("VIEWDate", dp);
				columns.put("VIEWDatelab", dp1);
				columns.put("vmp", vmp);
//				if(status.equals("0")) {
//						columns.put("chekboxaction", chekboxaction);
//						columns.put("vd", vd);
//						 columns.put("vioch", vioch);
// 					}
//					if(status.equals("1") || status.equals("2")) {
//						 columns.put("vd", vd);
//						 columns.put("vioch", vioch);
//					}
				
				countFunction+=1;
				countFunctionDelete+=1;
				countFunction1+=1;
				countFunction2+=1;
				countFunction3+=1;
				countFunction4+=1;
				countFunction5+=1;
				System.err.println("list size------------=-="+columns.size());
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
	public long DataTableSeacrh_State_internDataTotalCount(String Search, String nrh_en_no,String first_name,String institute_status,String gender, 
			String registration_state,String per_state, String per_district,String type_of_degree ,String  degree_name ,String place_of_working,
			 String dob,String date_of_reg,String institute_name,String type_status) {
	 	
		String SearchValue = GenerateQueryWhereClause_SQL(Search, first_name, institute_status,gender, registration_state,  per_state,   per_district,type_of_degree,   
				degree_name,  place_of_working,   dob,date_of_reg,institute_name,  type_status);
		int total = 0;
		String q = null;
		Connection conn = null;
	
		try {
			conn = dataSource.getConnection();
			
			q=" select count(*) \n"
					+ " from (select distinct e.id,e.nrh_en_no,e.first_name,e.gender,e.photo_path ,d.district_name as pre_district,s.state_name as pre_state,e.pre_pincode, "
					+ "					  dd.district_name as per_district,ss.state_name as per_state,e.per_pincode,e.aadhaar_no,e.email_id, "
					+ "					 to_char(e.dob , 'DD/MM/YYYY') as dob,n.nationality, to_char(e.date_of_reg , 'DD/MM/YYYY') as date_of_reg,e.state_reg_no,e.ayush_id,e.abha_no, "
					+ "					 e.father_name, e.status,  e.reject_remarks,  "
					+ "					 ss.state_name as registration_state,to_char(e.created_date+interval '5 year','yyyy-MM-dd') as valid_upto ,e.nrh_status  "
					+ "				 from reg_nch_form_a_p  e inner join recr_nationality_mst n on  (n.nationality_id )  = e.nationality "
					+ "					 inner join edu_lms_district_mstr d on  (d.district_id)  = e.pre_district "
					+ "					 inner join edu_lms_state_mstr s on  (s.state_id )  = e.pre_state "
					+ "					 inner join edu_lms_district_mstr dd on  (dd.district_id)  = e.per_district "
					+ "					 inner join edu_lms_state_mstr ss on  (ss.state_id)  = e.per_state "
					+ "					 inner join reg_nch_med_degree_dtl_a_ch pr on pr.regulation_p_id=e.id  "
					+ "			  		  where e.id!=0   "+SearchValue+") a  ";
	 
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, first_name,institute_status,gender, registration_state,  per_state ,  per_district,type_of_degree,   degree_name,  
					place_of_working,   dob,date_of_reg,institute_name,type_status);
		 	  System.out.println("stmt-----state--COUNTt ---"+stmt);
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
	public String GenerateQueryWhereClause_SQL(String Search,  String first_name,String institute_status,String gender, String registration_state,
			String per_state, String per_district,String type_of_degree,String  degree_name,String place_of_working, String dob,
			String date_of_reg,String institute_name,String type_status) {
		
		String SearchValue = "";
	
		if (!first_name.trim().equals("")) {
			SearchValue += " and upper(first_name) like ? ";

		}
//		and e.status_level_1  = 1 and e.status_level_2  = 1 
		if (institute_status.equals("0")  ) {
			SearchValue += " and e.status=1  and e.role=25  and e.institute_status != 2  and  e.state_status in (-1,0)   and  e.pay_status = 1 ";
		}
		if (institute_status.equals("1")  ) {
			SearchValue += " and e.status=1  and e.role=35 and e.institute_status != 2  and e.state_status = 0  and pay_status = 1   ";
		}
		if (institute_status.equals("2")  ) {
			SearchValue += " and e.status=2 and e.institute_status = 2  and e.state_status = ?      ";
		}
//		if (institute_status.equals("0") && type_status.equals("1")) {
//			SearchValue += " and  e.institute_status = 1  and e.state_status = ? and EXISTS (SELECT id FROM reg_nch_form_a_history h WHERE   e.id = h.p_id  and h.status = 6) ";
//		}
//		if (institute_status.equals("1") && type_status.equals("1")) {
//			SearchValue += " and  e.institute_status = 1  and e.state_status = ? and EXISTS (SELECT id FROM reg_nch_form_a_history h WHERE  e.id = h.p_id  and h.status = 6) ";
//		}
//		if (institute_status.equals("2") && type_status.equals("1")) {
//			SearchValue += " and e.institute_status = 2  and e.state_status = ? and EXISTS (SELECT id FROM reg_nch_form_a_history h WHERE  e.id = h.p_id  and h.status = 6) ";
//		}
		if (!gender.equals(" ") && gender != null) {
			SearchValue += " and e.gender = ?::integer ";
		} 
//		if (!reg_no.equals("") && reg_no != null) {
//			SearchValue += " and e.reg_no = ?::integer ";
//		}
		if (!registration_state.equals("0") && registration_state != null) {
			SearchValue += " and e.reg_state  = ?::integer  ";
		}
		if (!per_state.equals("0") && per_state != null) {
			SearchValue += " and e.per_state::integer = ?::integer ";
		}
		if (per_district.equals("5") && per_district != null) {
			SearchValue += " and e.per_district::integer = ?::integer ";
		}
		if (!type_of_degree.equals("0") && per_district != null) {
			SearchValue += " and pc.type_of_degree::integer = ?::integer ";
		}
		if (!degree_name.equals("0") && degree_name != null) {
			SearchValue += " and pc.degree_name::integer = ?::integer ";
		}
		if (!place_of_working.equals("0") && place_of_working != null) {
			SearchValue += " and mc.place_of_working::integer = ?::integer ";
		}
 
		if (!dob.equals("DD/MM/YYYY") && dob != "DD/MM/YYYY" && !dob.equals("") && dob != null   ) {
			SearchValue += " and to_char(e.dob , 'DD/MM/YYYY') = ? ";
		}
		if (!date_of_reg.equals("") && date_of_reg != null ) {
			SearchValue += " and to_char(e.date_of_reg , 'YYYY-MM-DD') = ? ";
		}
		if (!institute_name.equals("0") && institute_name != null) {
			SearchValue += " and pr.name_of_institute = ? ";
		}
		if (Search != null && !Search.equals("")) {
			Search = Search.toLowerCase();
			SearchValue += " and ";
			SearchValue += "  (lower(e.first_name) like ? or lower(e.father_name) like ?) ";
		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String first_name, String institute_status,String gender, String registration_state
			,String per_state ,String per_district,String type_of_degree,String  degree_name,String place_of_working ,
			String dob,String date_of_reg,String institute_name,String type_status) {
		int flag = 0;
		try {

			if (!first_name.equals("") && first_name != null) {
				flag += 1;
				stmt.setString(flag, "%" + first_name.toUpperCase() + "%");
			}
			//type 0
//			if (institute_status.equals("0")  ) {
////				flag += 1;
////				stmt.setInt(flag,0);
// 				flag += 1;
////				stmt.setString(flag,type_status);
//			}
//			if (institute_status.equals("1")  ) {
//				flag += 1;
//				stmt.setInt(flag,1);
// 			}
			if (institute_status.equals("2") ) {
				flag += 1;
				stmt.setInt(flag,2);
			 
			}
//			//type 1
//			if (institute_status.equals("0") && type_status.equals("1")) {
//				flag += 1;
//				stmt.setInt(flag,0);
//			}
//			if (institute_status.equals("1") && type_status.equals("1")) {
//				flag += 1;
//				stmt.setInt(flag,1);
//			}
//			if (institute_status.equals("2") && type_status.equals("1")) {
//				flag += 1;
//				stmt.setInt(flag,2);
//				 
//			}
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
			if (!per_state.equals("0") && per_state != null) {
				flag += 1;
				stmt.setString(flag,  per_state);
			}
			if (per_district.equals("5") && per_district != null) {
				flag += 1;
				stmt.setString(flag,  per_district);
			}
			if (!type_of_degree.equals("0") && type_of_degree != null) {
				flag += 1;
				stmt.setString(flag,  type_of_degree);
			}
			if (!degree_name.equals("0") && degree_name != null) {
				flag += 1;
				stmt.setString(flag,  degree_name);
			}
			if (!place_of_working.equals("0") && place_of_working != null) {
				flag += 1;
				stmt.setString(flag,place_of_working);
			}
//			if (!registration_for_type.equals("0") && registration_for_type != null) {
//				flag += 1;
//				stmt.setString(flag,registration_for_type);
//			}
		//	if (!dob.equals("") && dob != null) {
			if (!dob.equals("DD/MM/YYYY") && dob != "DD/MM/YYYY" && !dob.equals("") && dob != null   ) {
			flag += 1;
			stmt.setString(flag,dob);
			}
//			if (!dob.equals("DD/MM/YYYY") && dob != "DD/MM/YYYY" && !dob.equals("") && dob != null   ) {
//				SearchValue += " and to_char(e.dob , 'DD/MM/YYYY') = ? ";
//			}
			if (!date_of_reg.equals("") && date_of_reg != null) {
				flag += 1;
				stmt.setString(flag,date_of_reg);
			}
			if (!institute_name.equals("0") && institute_name != null) {
				flag += 1;
				stmt.setString(flag,institute_name);
			}
			if (Search != null && !Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
				
				flag += 1;
				stmt.setString(flag, "%" + Search.toLowerCase() + "%");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}
	 public String getMaxAID() {
			Connection conn = null;
			String state_reg_no = " ";

			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				String query = "";
				query = "select max(RIGHT(state_reg_no,7)) as state_reg_no from reg_nch_form_a_p ";
				stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					
				if(rs.getString("state_reg_no")== null || rs.getString("state_reg_no")== "null" || rs.getString("state_reg_no")== " ") {
					state_reg_no ="0";
				}
				else {
					state_reg_no = rs.getString("state_reg_no");
				}
				}
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return state_reg_no;
		}
	
	public String approve_StateinternData(String a, String status,String username,String state_reg_no,String per_state  ) {
		String[] id_list = a.split(":");
		Connection conn = null;
		int out = 0;
		String q="";
		String freg = "";
		
		int reg = Integer.parseInt(state_reg_no)+1;
 		int lengthOfInt = String.valueOf(reg).length();
 		String test = String.valueOf(per_state);
		 
	 //end
	 	try {
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction  tx= sessionHQL.beginTransaction();
			conn = dataSource.getConnection();
			 
			PreparedStatement stmt = null;
		 
				for (int i = 0; i < id_list.length; i++) {
					int id = Integer.parseInt(id_list[i]);
					REG_NCH_FORM_A_P SS = (REG_NCH_FORM_A_P) sessionHQL .get(REG_NCH_FORM_A_P.class, id);
					int user_id= 	SS.getUser_id() ;
					 System.err.println("user_id ====="+user_id);
				//	UserRole u = (UserRole) sessionHQL .get(UserRole.class, user_id);
 				  	SS.setStatus(Integer.parseInt(status));
				  	SS.setInstitute_status(0);
				  	SS.setState_status(0);
				  	SS.setNrh_status(0);
				  	SS.setRole(35);
				  
//				  
//				  	UserRole assetupd =(UserRole)sessionHQL.get(UserRole.class, id);
					 stmt = conn.prepareStatement("update userroleinformation set role_id=?  where user_id=?");
					 System.err.println("st------"+stmt);
					 stmt.setInt(1,  (35));
					 stmt.setInt(2, user_id);
				      out = stmt.executeUpdate();
				  	 
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
		if (out > 0) {
				if(status.equals("1")) {
				return "Data approved successfully.";
				}
				else
					return "Data approved successfully.";
			} else {
				if(status.equals("1")) {
					return "Data not approved successfully.";
					}
				else
				return "UnSuccessfully";
			}
	}
	
	public String reject_StateinternData(String a,String status,String username,String reject_remarks) {
		String[] id_list = a.split(":");
		Connection conn = null;
		int out = 0;
		String q="";
		 
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
				for (int i = 0; i < id_list.length; i++) {
					int id = Integer.parseInt(id_list[i]);
					stmt = conn.prepareStatement(" update reg_nch_form_a_p set state_status=? , status=2 ,reject_remarks = ?,  institute_status = 2    where id=?");
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
//				if(status.equals("1")) {
//				return "Approved Successfully";
//				}
				  if(status.equals("2")) {
					return "Revert Back Successfully";
					}
				else
					return "UnSuccessfully";
			} else {
//				if(status.equals("1")) {
//					return "Approved not Successfully";
//					}
				  if(status.equals("2")) {
					return "Revert Back not Successfully";
					}
				else
				return "UnSuccessfully";
			}
	}
	
//	public String reject_name_StatePracData(String a,String first_name,String username) {
//		String[] id_list = a.split(":");
//		Connection conn = null;
//		String q="";
//		 
//		try {
//			conn = dataSource.getConnection();
//			PreparedStatement stmt = null;
//				for (int i = 0; i < id_list.length; i++) {
//					int id = Integer.parseInt(id_list[i]);
//					stmt = conn.prepareStatement(" SELECT first_name FROM reg_nch_form_a_p where id=?");
//					stmt.setInt(1, id);
//					}
//				System.err.println("first_name---dao-------------"+first_name);
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//				}
//			}
//		}
//		return q;
//	}

	@Override
	public ArrayList<ArrayList<String>> get_internname_by_Reject_idata(String id) {
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
//	public ArrayList<ArrayList<String>> getstatusfrommedDegree(String regulation_p_id) {
//		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
//	    Connection conn = null;
//	    try{          
//	    	conn = dataSource.getConnection();
//	    	
//	    	String 	sq1="select count(id) as count from reg_nch_med_degree_dtl_a_ch where status=1 and regulation_p_id = ?";
//	    	
//	        PreparedStatement stmt = conn.prepareStatement(sq1);
//	        stmt.setInt(1, Integer.parseInt(regulation_p_id));
//	       
//	        ResultSet rs = stmt.executeQuery();  
//	        
//	        String str1="";
//	        while(rs.next()){
//	        	ArrayList<String> list = new ArrayList<String>();
//					 list.add(rs.getString("count"));// 0
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
