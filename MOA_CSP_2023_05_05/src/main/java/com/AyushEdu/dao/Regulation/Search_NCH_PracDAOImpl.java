package com.AyushEdu.dao.Regulation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Regulation.REG_NCH_FORM_A_P;
import com.AyushEdu.Models.Regulation.REG_NCH_FORM_A_HISTORY;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Repository
public class Search_NCH_PracDAOImpl implements Search_NCH_PracDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public List<Map<String, Object>> DataTableSeacrh_NCH_PracDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,  String first_name, String status,String gender ,
			  String registration_state,String per_state, String per_district,String type_of_degree,String  degree_name,String place_of_working,
			String dob,String date_of_reg,String institute_name,String type_status) {
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search,first_name,status,gender,registration_state,  per_state,   per_district,  type_of_degree,   degree_name,  
				place_of_working,  dob,date_of_reg,institute_name,  type_status);
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

			q=" select distinct e.id,e.nrh_en_no,e.first_name,e.gender,ee.gender_name,e.photo_path,d.district_name as pre_district,s.state_name as pre_state,e.pre_pincode, \n"
					+ "	dd.district_name as per_district,ss.state_name as per_state,e.per_pincode,e.email_id,\n"
					+ "	to_char(e.dob , 'DD/MM/YYYY') as dob,n.nationality ,to_char(e.date_of_reg , 'DD/MM/YYYY') as date_of_reg,e.state_reg_no,e.ayush_id,e.abha_no,\n"
					+ " e.father_name, e.status,\n"
					+ "  e.reject_remarks, "
					+ "ss.state_name as registration_state  ,e.valid_up_to  ,to_char(e.created_date+interval '5 year','yyyy-MM-dd') as valid_upto  \n"
					+ " from reg_nch_form_a_p  e \n"
					+"inner join edu_lms_gender_mstr ee on ee.id=e.gender\n"
					+ " inner join recr_nationality_mst n on  (n.nationality_id )  = e.nationality\n"
					+ "inner join edu_lms_district_mstr d on  (d.district_id )  = e.pre_district\n"
					+ "inner join edu_lms_state_mstr s on  (s.state_id )  = e.pre_state\n"
					+ "inner join edu_lms_district_mstr dd on  (dd.district_id )  = e.per_district\n"
					+ "inner join edu_lms_state_mstr ss on  (ss.state_id )  = e.per_state\n"
					+ " --inner join reg_nch_registration_a pr on pr.user_id=e.user_id inner join edu_lms_state_mstr sss on cast (sss.state_id as character varying)  = pr.regisration_state"
					+ " where e.id!=0 \n"
				    + SearchValue +" order by id " + orderType
					+ " limit " + pageL + " OFFSET " + startPage;	

			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search, first_name,status,gender,registration_state,  per_state ,  per_district,type_of_degree,   degree_name,  
					place_of_working,  dob,date_of_reg,institute_name,  type_status);
			
			System.err.println("============nch serch======="+stmt);
			
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
//				onclick='imageView("+rs.getString("id")+");'
				columns.put("img","<img class='d-block img5050 imageZomm imgcsp' alt='No Image' id='myImg"+rs.getString("id")+"' src='MedicalImagePath1?i_id="+rs.getString("id")+"'  />"
						+ "<input type='hidden' id='imghid"+countFunction5+"' value="+rs.getString("id")+">");
 
				String f = "";
				String action = "";
				String f1 = "";
				String chekboxaction="";
				String vd = "";
				String vmp = "";
				String vioch = "";
				String vm1 ="";
				
				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){EditData('"
						+ rs.getString("id") + "','" + rs.getString("nrh_en_no") + "','" + rs.getString("status")
						+ "') }else{ return false;}\"";
				
				f = "<i class='fa fa-pencil-square-o editstate' title='Edit Data'>"
						+ "<input type='hidden' id='CounId"+countFunction+"' value="+rs.getString("id")+">"
						+ "<input type='hidden' id='Counnrh_en_no"+countFunction+"' value="+rs.getString("nrh_en_no")+">"
						+ "<input type='hidden' id='Counstatus"+countFunction+"' value="+rs.getString("status")+"></i>";

			    String Checkbox="<input class='nrCheckBox' type='checkbox' id='" + rs.getObject(1)//13
					+ "' name='cbox'/><input type='hidden' id='nrCHid"+rs.getObject(1)+"'>";
	//           onclick='checkCKBT()'
	//			 onchange='checkbox_count(this," + rs.getObject(1) + ");'
			    String CheckboxId="<input  type='hidden' id='id" + rs.getObject(1) + "' name='id" + rs.getObject(1)//14
					+ "' value='" + rs.getObject(1) + "'   />";
			     
			     chekboxaction+=Checkbox;
				
				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				f1 = "<i class='fa fa-trash statedlt' title='Delete Data'>"
						+ "<input type='hidden' id='DCounId"+countFunctionDelete+"' value="+rs.getString("id")+"></i>";
				
				String ul="";
				
				ul+="<ul class='buttons-group mainbtn action daobtn'>";
				
				String VIEWDEGREE = "onclick=\" if (confirm('Are You Sure You Want to Show Detail ?') ){Pop_Up_Degree('"
				+ rs.getString("id") + "') }else{ return false;}\"";
				
				vd = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn btn-hover btn-sm btnview' value='ADD' title='View Data' >\n"
				+ "<i class='lni lni-eye dgpop'>"
				+ "<input type='hidden' id='popdegreeId"+countFunction1+"' value="+rs.getString("id")+"></i></a> </li></ul>";
						
				ul+="</ul>";

				ul+="<ul class='buttons-group mainbtn action daobtn'>";
						 
				String VIEWIOCH = "onclick=\" if (confirm('Are You Sure You Want to Show Detail ?') ){Pop_Up_IOCH('"
				+ rs.getString("id") + "') }else{ return false;}\"";
				
				vioch = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn btn-hover btn-sm btnview' value='ADD' title='View Data' >\n"
						+ "	<i class='lni lni-eye popview'>"
						+ "<input type='hidden' id='pop_ioch"+countFunction2+"' value="+rs.getString("id")+"></i></a> </li></ul>";
				ul+="</ul>";
 						 //download 

				String VIEWDEGREEP = "onclick=\" if (confirm('Are You Sure You Want to View Detail?') ){Pop_Up_DegreeP('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				
				vm1 = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn btn-hover btn-sm btnview' value='ADD' title='View Data' >\n"
						+ "		<i class='lni lni-eye popdegreep'>"
						+ "<input type='hidden' id='popdegreepId"+countFunction3+"' value="+rs.getString("id")+"></i></a> </li></ul>";
				
				columns.put("vm1",vm1);	
 						 
			//	if(status.equals("1")) {
				
//				--01-03-2023
				if(status.equals("1")) {
				String download1 = "onclick=\" if (confirm('Are You Sure You Want to download File  ?') ){getPDF('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				vmp= "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn info-btn btn-hover btn-sm btndownload' value='ADD' title='Download Data' >\n"
						+ "<i class='lni lni-download degreepdf'>"
						+ "<input type='hidden' id='degreepdfId"+countFunction4+"' value="+rs.getString("id")+"></i></a> </li></ul>";
				} 
				String dp = "";
				String VIEWDate = "onclick=\" if (confirm('Are You Sure You Want to Show Detail ?') ){valid_upto('"
						+ rs.getString("id") + "') }else{ return false;}\"";
				
				dp = "<input type='date' value='"+rs.getString("valid_upto")+"' name='valid_upto"+rs.getString("id")+"' id='valid_upto"+rs.getString("id")+"' class='form-control validup' >";
				
				
				
				
				
				
				
				
 				action =f+" "+  f1  ;
				 
				columns.put("chekboxaction", chekboxaction);
				columns.put("action", action);
				columns.put("vd", vd);
				columns.put("vioch", vioch);
				columns.put("vmp", vmp);
				columns.put("VIEWDate", dp);
				countFunction+=1;
				countFunctionDelete+=1;
				countFunction1+=1;
				countFunction2+=1;
				countFunction3+=1;
				countFunction4+=1;
				countFunction5+=1;
				
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
	public long DataTableSeacrh_NCH_PracDataTotalCount(String Search, String first_name, String institute_status,String gender, 
			String registration_state,String per_state, String per_district,String type_of_degree,String  degree_name,String place_of_working,
			String dob,String date_of_reg,String institute_name,String type_status) {
		
		String SearchValue = GenerateQueryWhereClause_SQL(Search, first_name, institute_status,gender,registration_state,  per_state,   per_district,type_of_degree,   
				degree_name,  place_of_working,   dob,date_of_reg,institute_name,  type_status);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
				
 			

			
			q = "select count(*) \n"
					+ " from (select distinct e.id,e.nrh_en_no,e.first_name,e.gender,e.photo_path ,d.district_name as pre_district,s.state_name as pre_state,e.pre_pincode, \n"
					+ "	 dd.district_name as per_district,ss.state_name as per_state,e.per_pincode,e.email_id,\n"
					+ "	to_char(e.dob , 'DD/MM/YYYY') as dob,n.nationality,to_char(e.date_of_reg , 'DD/MM/YYYY') as date_of_reg,e.state_reg_no,e.ayush_id,e.abha_no,\n"
					+ " e.father_name, e.status,\n"
					+ "   e.reject_remarks, \n"
					+ "ss.state_name as registration_state  \n"
					+ " from reg_nch_form_a_p  e "
					+ " inner join edu_lms_gender_mstr ee on ee.id=e.gender "
					+ "inner join recr_nationality_mst n on  (n.nationality_id )  = e.nationality\n"
					+ "inner join edu_lms_district_mstr d on  (d.district_id )  = e.pre_district\n"
					+ "inner join edu_lms_state_mstr s on  (s.state_id )  = e.pre_state\n"
					+ "inner join edu_lms_district_mstr dd on  (dd.district_id )  = e.per_district\n"
					+ "inner join edu_lms_state_mstr ss on  (ss.state_id)  = e.per_state\n"
					+ "--inner join reg_nch_registration_a pr on pr.user_id=e.user_id "
					+ " --inner join edu_lms_state_mstr sss \n"
					+ "	  -- on cast (sss.state_id as character varying)  = pr.regisration_state  \n"
					+ "	   where e.id != 0 "+SearchValue+") a  ";
 
 			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, first_name,institute_status,gender, registration_state,  per_state ,  per_district,type_of_degree,   degree_name,  
					place_of_working,   dob,date_of_reg,institute_name,  type_status);
		 	 System.out.println("stmt-----DataTableSeacrh_NCH_PracDataTotalCount--COUNT---"+stmt);
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
	public String GenerateQueryWhereClause_SQL(String Search,  String first_name,String institute_status,String gender ,String registration_state,
			String per_state, String per_district,String type_of_degree,String  degree_name,String place_of_working  ,String dob,String date_of_reg,
			String institute_name,String type_status) {
		
		String SearchValue = "";
	
		if (!first_name.trim().equals("")) {
			SearchValue += " and upper(first_name) like ? ";
			System.err.println("parameter search first_name" + SearchValue);

		}
		
//		if (institute_status.equals("0")) {
//			SearchValue += " and e.status=1 and e.institute_status = 1 and e.state_status=1 and e.nrh_status = ? ";
//		}
//		if (institute_status.equals("1")) {
//			SearchValue += "and e.status=1 and e.institute_status = 1 and e.state_status=1  and e.nrh_status = ? ";
//		}
//		if (institute_status.equals("2")) {
//			SearchValue += "and e.status=2 and e.institute_status =2 and e.state_status=2 and e.nrh_status = ? ";
//		}
		
		
		
//		type ==0
		if (institute_status.equals("0")  ) {
 			SearchValue += " and e.status=1 and e.institute_status !=2  and e.state_status = 1 and e.nrh_status = ? and e.pract_type=?  ";
		}
		
		if (institute_status.equals("1")  ) {
 			SearchValue += " and e.status=1 and e.institute_status != 2  and e.state_status = 1 and e.nrh_status = ?  and e.pract_type=? ";
		}
		if (institute_status.equals("2")  ) {
 			SearchValue += " and e.status=2 and e.institute_status = 2  and e.state_status = 2 and e.nrh_status = ?  and e.pract_type=?  ";
		}
//		type ==1
//		if (institute_status.equals("0") && type_status.equals("1")) {
//			SearchValue += " and  e.institute_status = 1  and e.state_status = 1 and e.nrh_status = ? and EXISTS (SELECT id FROM reg_nch_form_a_history h WHERE   e.id = h.p_id  and h.status = 6) ";
//		}
//		
//		if (institute_status.equals("1") && type_status.equals("1")) {
//			SearchValue += " and  e.institute_status = 1  and e.state_status = 1 and e.nrh_status = ? and EXISTS (SELECT id FROM reg_nch_form_a_history h WHERE  e.id = h.p_id  and h.status = 6) ";
//		}
//		if (institute_status.equals("2") && type_status.equals("1")) {
//			SearchValue += " and e.institute_status = 2  and e.state_status = 2 and e.nrh_status = ? and EXISTS (SELECT id FROM reg_nch_form_a_history h WHERE  e.id = h.p_id  and h.status = 6) ";
//		}
		// 
		
		
		if (!gender.equals("0") && gender != null) {
			SearchValue += " and e.gender = ?::integer ";
		} 
//		if (!reg_no.equals("") && reg_no != null) {
//			SearchValue += " and e.reg_no = ?::integer ";
//		}
		if (!registration_state.equals("0") && registration_state != null) {
			SearchValue += " and e.reg_state::integer = ?::integer ";
		}
		if (!per_state.equals("0") && per_state != null) {
			SearchValue += " and e.per_state::integer = ?::integer ";
		}
//		-04-03-2023
		if (!per_district.equals("0") && per_district != null) {
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
//		
//		if (!dob.equals("") && dob != null) {
//			SearchValue += " and to_char(e.dob , 'YYYY-MM-DD') = ? ";
//		}
		
		if (!dob.equals("DD/MM/YYYY") && dob != "DD/MM/YYYY" && !dob.equals("") && dob != null   ) {
			SearchValue += " and to_char(e.dob , 'DD/MM/YYYY') = ? ";
		}
		
		if (!date_of_reg.equals("") && date_of_reg != null && !date_of_reg.equals("DD/MM/YYYY") && date_of_reg != "DD/MM/YYYY" ) {
			SearchValue += " and to_char(e.date_of_reg , 'YYYY-MM-DD') = ? ";
		}
		if (!institute_name.equals("0") && institute_name != null) {
			SearchValue += " and pr.institute_name = ? ";
		} 
		if (Search != null && !Search.equals("")) {
			Search = Search.toLowerCase();

			SearchValue += " and ";
//			SearchValue += "  (lower(e.first_name) like ? or lower(e.father_name) like ?) ";
			SearchValue += " ( lower(e.ayush_id) like ? or lower(e.abha_no) like ? or lower(e.state_reg_no) like ? or lower(e.nrh_en_no) like ? or lower(e.first_name) like ? or lower(e.father_name) like ? "
				     + " or to_char(e.created_date+interval '5 year','yyyy-MM-dd') like ? or cast(e.gender as character varying) like ? or lower(d.district_name) like ? or lower(dd.district_name) like ? "
				     + " or lower(s.state_name) like ? or lower(ss.state_name) like ? or cast(e.pre_pincode as character varying) like ? or cast(e.per_pincode as character varying) like ? or lower(e.email_id) like ? "
				     + " or to_char(e.dob , 'DD/MM/YYYY') like ? or lower(n.nationality) like ? or lower(e.reject_remarks) like ? ) ";
		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String first_name, String institute_status,String gender, String registration_state
			,String per_state ,String per_district,String type_of_degree,String  degree_name,String place_of_working  ,String dob,String date_of_reg,
			String institute_name,String type_status) {
		int flag = 0;
		try {

			if (!first_name.equals("") && first_name != null) {
				flag += 1;
				stmt.setString(flag, "%" + first_name.toUpperCase() + "%");
			}
			
//			if (institute_status.equals("0")) {
//				flag += 1;
//				stmt.setInt(flag,0);
//			}
//			if (institute_status.equals("1")) {
//				flag += 1;
//				stmt.setInt(flag,1);
//			}
//			if (institute_status.equals("2")) {
//				flag += 1;
//				stmt.setInt(flag,2);
//			}
			
			//type 0
			if (institute_status.equals("0")  ) {
				flag += 1;
				stmt.setInt(flag,0);
				flag += 1;
				stmt.setString(flag,type_status);
			}
			if (institute_status.equals("1") ) {
				flag += 1;
				stmt.setInt(flag,1);
				flag += 1;
				stmt.setString(flag,type_status);
			}
			if (institute_status.equals("2") ) {
				flag += 1;
				stmt.setInt(flag,2);
				flag += 1;
				stmt.setString(flag,type_status);
			}
			//type 1
//			 if (institute_status.equals("0") && type_status.equals("1")) {
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
// 			}
			  
			
			if (!gender.equals("0") && gender != null) {
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
//			--04-03-2023
			if (!per_district.equals("0") && per_district != null) {
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
			
		
//			if (!dob.equals("") && dob != null) {
				if (!dob.equals("DD/MM/YYYY") && dob != "DD/MM/YYYY" && !dob.equals("") && dob != null   ) {
				flag += 1;
				stmt.setString(flag,dob);
				
			}
			
			if (!date_of_reg.equals("") && date_of_reg != null && !date_of_reg.equals("DD/MM/YYYY") && date_of_reg != "DD/MM/YYYY") {
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

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}
	
//	public String approve_NCHPracData(String a,String status,String username) {
//		String[] id_list = a.split(":");
//		Connection conn = null;
//		int out = 0;
//		String q="";
//		if(status.equals("1")) {
//			q=" nrh_status=? ";
//		}
//		if(status.equals("2")) {
//			q=" institute_status=? ";
//		}
//		try {
//			conn = dataSource.getConnection();
//			PreparedStatement stmt = null;
//				for (int i = 0; i < id_list.length; i++) {
//					int id = Integer.parseInt(id_list[i]);
//					stmt = conn.prepareStatement("update reg_nch_form_a_p set state_status=? , "+ q +" where id=?");
//					stmt.setInt(1, Integer.parseInt(status));
//					if(status.equals("1")) {
//						stmt.setInt(2,0);
//					}
//					if(status.equals("2")) {
//						stmt.setInt(2,2);
//					}
//					stmt.setInt(3,id);
//					System.err.println("check the statment--"+stmt);
//					out = stmt.executeUpdate();
//					}
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
//		if (out > 0) {
//				if(status.equals("1")) {
//				return "Approved Successfully";
//				}
//				else if(status.equals("3")) {
//					return "Rejected Successfully";
//					}
//				else
//					return "UnSuccessfully";
//			} else {
//				if(status.equals("1")) {
//					return "Approved not Successfully";
//					}
//				else if(status.equals("3")) {
//					return "Rejected not Successfully";
//					}
//				else
//				return "UnSuccessfully";
//			}
//	}

	
	
	  
	
		public String getMaxVersion(String p_id ) {
			Session sessionHQL = sessionFactory.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			System.err.println("----------p_id------"+p_id);
			//try {
			 
				Query q1 = sessionHQL
						.createQuery("select CAST(max(version) as string) as version from REG_NCH_FORM_A_HISTORY  where p_id=:p_id ");
//				select  demo_video from edu_lms_system_course_duration where course_id::int = 24
				
				q1.setParameter("p_id", Integer.parseInt(p_id));
				
				@SuppressWarnings("unchecked")
				List<String> list = (List<String>) q1.list();
				String v=list.get(0);
				System.err.println("v--------------"+v);
				if(v == null) {
					v="0";
				}
				 
				
				tx.commit();
				
				
				
 			return v;
//			} catch (RuntimeException e) {
//				return null;
//			} finally {
//				if (sessionHQL != null) {
//					sessionHQL.close();
//				}
//			}
		}
		
		
	 public String getMaxAID() {

			Connection conn = null;
			String nrh_en_no = " ";
//			if(nrh_en_no== null || nrh_en_no== "null" || nrh_en_no== " ") {
//				nrh_en_no ="0";
//			}
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				String query = "";
				query = "select max(RIGHT(nrh_en_no,7)) as nrh_en_no from reg_nch_form_a_p ";
				stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					
				if(rs.getString("nrh_en_no")== null || rs.getString("nrh_en_no")== "null" || rs.getString("nrh_en_no")== " ") {
					nrh_en_no ="0";
				}
				else {
					nrh_en_no = rs.getString("nrh_en_no");
				}
					

				}
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		System.err.println("nrh_en_no --------"+nrh_en_no);
			return nrh_en_no;
		}

	
	//approve reg
//		
//		public String approve_NCHregData(String a,String status,String username,String nrh_en_no) throws ParseException {

 public String approve_NCHregData(String a,String upto,String status,String username,String state_reg_no,String per_state,String nrh_en_no) throws ParseException {
			
	       String[] id_list = a.split(":");
			System.err.println("dao check the value approve--"+nrh_en_no);
			Connection conn = null;
			Integer out = 0;
			String q = "";
			String fnrh = "";
 			int nrh = Integer.parseInt(nrh_en_no)+1;
 			int lengthOfInt = String.valueOf(nrh).length();
 			 System.err.println(" lengthOfInt --------"+lengthOfInt);
		  if(lengthOfInt  == 7) {
			   fnrh = "NA/"+nrh;
		 }
		 if(lengthOfInt ==6) {
			 fnrh = "NA/"+"0"+nrh;
		 }	
		 if(lengthOfInt==5) {
			 fnrh = "NA/"+"00"+nrh;
		 }		
		 if(lengthOfInt==4) {
			 fnrh = "NA/"+"000"+nrh;
		 }		
		 if(lengthOfInt ==3) {
			 fnrh = "NA/"+"0000"+nrh;
		 }	
		 if(lengthOfInt ==2) {
			 fnrh = "NA/"+"00000"+nrh;
		 }	
		 if(lengthOfInt == 1 &&  nrh == 1 ) {
			 fnrh = "NA/"+"0000001";
		 }	
		 else if(lengthOfInt ==1) {
			 fnrh = "NA/"+"000000"+nrh;
		 }
			String freg = "";
			int reg = Integer.parseInt(state_reg_no)+1;
	 		int rlengthOfInt = String.valueOf(reg).length();
			 String test = String.valueOf(per_state);
			 String sta = test.length() < 2 ? test : test.substring(0, 2);
		  if(rlengthOfInt  == 7) {
			  freg = sta+"/"+reg;
		 }
		 if(rlengthOfInt ==6) {
			 freg = sta+"/"+"0"+reg;
		 }	
		 if(rlengthOfInt==5) {
			 freg = sta+"/"+"00"+reg;
		 }		
		 if(rlengthOfInt==4) {
			 freg = "ST/"+"000"+reg;
		 }		
		 if(rlengthOfInt ==3) {
			 freg = sta+"/"+"0000"+reg;
		 }	
		 if(rlengthOfInt ==2) {
			 freg = "ST/"+"00000"+reg;
		 }	
		 if(rlengthOfInt == 1 &&  reg == 1 ) {
			 freg = sta+"/"+"0000001";
		 }	
		 else if(rlengthOfInt ==1) {
			 freg = sta+"/"+"000000"+reg;
		 }
			try {
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction  tx= sessionHQL.beginTransaction();
				conn = dataSource.getConnection();
				 
				PreparedStatement stmt = null;
			 
				
					for (int i = 0; i < id_list.length; i++) {
						int id = Integer.parseInt(id_list[i]);
					  	REG_NCH_FORM_A_P SS = (REG_NCH_FORM_A_P) sessionHQL .get(REG_NCH_FORM_A_P.class, id);
					  	if(SS.getNrh_en_no() != null && !SS.equals("") && !SS.equals("null") && !SS.equals("0")) {
 					  			SS.setNrh_status(Integer.parseInt(status));
 					  			if(!upto.equals("")) {
 					  			SS.setValid_up_to(upto);
 					  			}
 					  		}	
					  		else{
 					  			 SS.setNrh_status(Integer.parseInt(status));
						  		 SS.setNrh_en_no(fnrh);
						  		if(!upto.equals("")) {
						  		SS.setValid_up_to(upto);
						  		}
//						  		SS.setState_reg_no(freg);
					  		}
					  	 
					  		sessionHQL.update(SS);
 					  		String j=	getMaxVersion(String.valueOf(id));
					  		int ver = Integer.parseInt(j)+1;
					  		//histroy save
						  	if(status.equals("1")) {
						  		REG_NCH_FORM_A_HISTORY td3 = new REG_NCH_FORM_A_HISTORY();
						  		//janki
//						  		REG_NCH_FORM_A_HISTORY d = (REG_NCH_FORM_A_HISTORY) sessionHQL .get(REG_NCH_FORM_A_HISTORY.class, id);
 //						  		Integer ver;
//						  		if(d == null) {
//						  			  ver =0;
//						  		}
//						  		else {
//						  			ver = d.getVersion();
//						  			 td3.setVersion(ver+1);
//						  		}
						  		//System.err.println("ver======="+ver);
						  
						  	System.err.println("j---------"+j);
						  	td3.setVersion(ver);
						  		td3.setPract_type(SS.getPract_type());
								td3.setNrh_en_no(SS.getNrh_en_no());
								td3.setFirst_name(SS.getFirst_name());
								td3.setGender(SS.getGender());
								td3.setPre_district(SS.getPre_district());
								td3.setPre_state(SS.getPre_state());
								td3.setPre_pincode(SS.getPre_pincode());
								td3.setPer_district(SS.getPer_district());
								td3.setPer_state(SS.getPer_state());
								td3.setPer_pincode(SS.getPer_pincode());

								td3.setAadhaar_no(SS.getAadhaar_no());
								td3.setStatus(SS.getStatus());
								td3.setMo_no(SS.getMo_no());
								td3.setAlt_mo_no1(SS.getAlt_mo_no1());

								td3.setAlt_mo_no2(SS.getAlt_mo_no2());
								td3.setEmail_id(SS.getEmail_id());
								td3.setAlt_email_id1(SS.getAlt_email_id1());
								td3.setAlt_email_id2(SS.getAlt_email_id2());

								td3.setAbha_no(SS.getAbha_no());
								td3.setAyush_id(SS.getAyush_id());
								td3.setSuspend_up_to(SS.getSuspend_up_to());
								td3.setReason(SS.getReason());

								td3.setDob(SS.getDob());
								td3.setNationality(SS.getNationality());
								td3.setDegree(SS.getDegree());
								td3.setDate_of_reg(SS.getDate_of_reg());

								//td3.setReg_no(SS.getReg_no());
								td3.setPhoto_path(SS.getPhoto_path());
								td3.setFather_name(SS.getFather_name());
								td3.setCreated_by(SS.getCreated_by());

								td3.setCreated_date(SS.getCreated_date());
								td3.setModified_by(SS.getModified_by());
								td3.setModified_date(SS.getModified_date());
								td3.setDel_status(SS.getDel_status());

								td3.setInstitute_status(SS.getInstitute_status());
								td3.setState_status(SS.getState_status());
								td3.setNrh_status(SS.getNrh_status());
								td3.setUser_id(SS.getUser_id());

								//td3.setReg_auth(SS.getReg_auth());
							//	td3.setPhoto_first_cer_path(SS.getPhoto_first_cer_path());
								//td3.setRegistration_for_type(SS.getRegistration_for_type());
								td3.setValid_up_to(SS.getValid_up_to());

								td3.setPre_address_details1(SS.getPre_address_details1());
								td3.setPre_address_details2(SS.getPre_address_details2());
								td3.setPre_address_details3(SS.getPre_address_details3());
								td3.setPer_address_details1(SS.getPer_address_details1());

								td3.setPer_address_details2(SS.getPer_address_details2());
								td3.setPer_address_details2(SS.getPer_address_details2());
								td3.setState_reg_no(SS.getState_reg_no());
								//curresponding address
								td3.setCurr_address(SS.getCurr_address());
								td3.setCurr_address2(SS.getCurr_address2());
								td3.setCurr_address3(SS.getCurr_address3());
								td3.setCurr_district(SS.getCurr_district());
								td3.setCurr_pincode(SS.getCurr_pincode());
								td3.setCurr_state(SS.getCurr_state());
								
								
								td3.setP_id(SS.getId());

								sessionHQL.save(td3);
								sessionHQL.flush();
								sessionHQL.clear();
						  	}
						  	
					  		 
					  		
					  		
					  		sessionHQL.flush();
							sessionHQL.clear();
							tx.commit();
							
							int s =	(int) NationalDataTotalCount(id); 
							 
							if(s != 0) {
 								deleteNCHdataHistory(id);
							}
 								 EncInsertdataHistory(id);
 							
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
			//System.err.println("innnn"+status);	
				if(status.equals("1")) {
				SendapproveMsgPractitioner(fnrh);
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
		
		
		public String deleteNCHdataHistory(int updateid)
		{
				Connection conn = null;
				Connection conn1 = null;
				Statement stmt;
				Statement stmt1;
				try {
					conn = dataSource.getConnection();	
					conn1 = dataSource.getConnection();		
					stmt = (Statement)conn.createStatement();	
					stmt1 = (Statement)conn1.createStatement();	
					String sql = null;
 					String sql1 = null;
					
				//	sql1= "select count(id) from reg_nch_form_a_national_record_a where id = '"+updateid+"' ";
					
					
					sql=" delete from reg_nch_form_a_national_record_a where p_id = '"+updateid+"' " ;
					 
					stmt.executeUpdate(sql);
					System.err.println("sql------"+sql);
				//	stmt1.executeUpdate(sql1);
				//	System.err.println("sql1------"+sql1);
					
					
					stmt.close();
				//	stmt1.close();
		      		conn.close();
		      	//	conn1.close();
		      		
		  	    } catch (SQLException e) {
		 			e.printStackTrace();
		 		}
				return "1";
		}
		
		public String EncInsertdataHistory(int updateid)
		{
				Connection conn = null;
				Connection conn1 = null;
				Statement stmt;
				Statement stmt1;
				try {
					conn = dataSource.getConnection();	
					conn1 = dataSource.getConnection();		
					stmt = (Statement)conn.createStatement();	
					stmt1 = (Statement)conn1.createStatement();	
					String sql = null;
 					String sql1 = null;
					
				//	sql1= "select count(id) from reg_nch_form_a_national_record_a where id = '"+updateid+"' ";
					
					
					sql=" INSERT INTO public.reg_nch_form_a_national_record_a(p_id,pre_address_details1,pre_address_details2,pre_address_details3,first_name,father_name,nrh_en_no,\n"
							+ "district_name,state_name,pre_pincode,photo_path,state_reg_no,degree_name) \n"
							+ " Select d.id,d.pre_address_details1,d.pre_address_details2,d.pre_address_details3,d.first_name,d.father_name,d.nrh_en_no,\n"
							+ "dm.district_name,sm.state_name,d.pre_pincode,d.photo_path,d.state_reg_no,string_agg(d1.degree_name,   ',') as degree_name\n"
							+ "from reg_nch_form_a_p d\n"
							+ "inner join edu_lms_state_mstr sm on sm.state_id = d.pre_state::int\n"
							+ "inner join edu_lms_district_mstr dm on dm.district_id = d.pre_district::int\n"
							+ "inner join reg_nch_med_degree_dtl_a_ch c on c.regulation_p_id = d.id\n"
							+ "inner join edu_lms_degree_mstr d1 on d1.id=c.degree_name\n"
							+ "where d.id='"+updateid+"' group by 1,2,3,4,5,6,7,8,9,10;\n"
							+ "";
					
						 
				
					stmt.executeUpdate(sql);
					System.err.println("sql------"+sql);
				//	stmt1.executeUpdate(sql1);
				//	System.err.println("sql1------"+sql1);
					
					
					stmt.close();
				//	stmt1.close();
		      		conn.close();
		      	//	conn1.close();
		      		
		  	    } catch (SQLException e) {
		 			e.printStackTrace();
		 		}
				return "1";
		}
		
		
		@Override
		public long NationalDataTotalCount(int id ) {
		//	String  role = session.getAttribute("role").toString();
			 
			int total = 0;
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				q="select count(id) from reg_nch_form_a_national_record_a where p_id ='"+id+"' ";
						

				PreparedStatement stmt = conn.prepareStatement(q);
				 ResultSet rs = stmt.executeQuery();
				System.err.println("stt------------"+stmt);
				while (rs.next()) {
					total = rs.getInt(1);
					System.err.println(total+"------total");
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
			return (long) total;
		}
		 
		public String reject_NCHPracData(String a,String status,String reject_remarks) {
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
						 stmt = conn.prepareStatement("update reg_nch_form_a_p set nrh_status=?  , status=2 , institute_status = 2,reject_remarks = ?, state_status=2 where id=?");
						 System.err.println("st------"+stmt);
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


		// FOR WHATSAPP
		
		public static void SendapproveMsgPractitioner(String NRHNO) throws ParseException {
			
			final String ACCOUNT_SID = "AC76781bb1c6c8c4d871a664b55a19da36";
		    final String AUTH_TOKEN = "74cd413e0fbe76bb1d52ddf2730b1c52";
		    
			Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
			
	        Message message = Message.creator(
	                new com.twilio.type.PhoneNumber("whatsapp:+918200818918"),
	                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
	                "Your Application has been Successfully Approved by NCH Council and Your NRH no is "+NRHNO + " And Now You Can View Your Certificate by loggin into your Account.")
	          
	        		.create();
//	        System.out.println("message++++++>>>>>>>>>"+message);
	        
		}
		
		@Override
		public ArrayList<ArrayList<String>> get_Parctname_by_NCHReject_idata(String id) {
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
		    
		   return alist;
		}
}
