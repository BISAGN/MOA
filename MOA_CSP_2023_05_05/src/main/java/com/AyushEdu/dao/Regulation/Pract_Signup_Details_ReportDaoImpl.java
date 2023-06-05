package com.AyushEdu.dao.Regulation;
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



@Repository
public class Pract_Signup_Details_ReportDaoImpl implements Pract_Signup_Details_ReportDao {
	
	@Autowired
	private DataSource dataSource;

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Map<String, Object>> DataTablePract_Signup_Details_ReportDataList(int startPage, int pageLength,
			 String orderColunm, String orderType, String Search,String name,String dob, String aadhar_card ,String email,
			 String mobile_no ,String upload_date, String gender,String internship_completion_date,String reg_state 
			 ,String state_reg_num,String status)  {
	 
		String SearchValue = GenerateQueryWhereClause_SQL(Search,name, dob ,aadhar_card,email,mobile_no,upload_date,gender,
				internship_completion_date,reg_state,state_reg_num,status);
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
			
  
q= "select ROW_NUMBER() OVER(order by p.id ASC) as sr_no,p.id,p.name,TO_CHAR(p.dob , 'dd/MON/YYYY') as dob, p.aadhar_card,p.email,p.mobile_no,TO_CHAR(p.upload_date , 'dd/MON/YYYY') as upload_date, gm.gender_name,TO_CHAR(p.internship_completion_date , 'dd/MON/YYYY') as internship_completion_date,s.state_name,p.state_reg_num,p.gender,p.reg_state,p.reject_remarks \n"
		+ "from reg_nch_practitioner_signup_details p\n"
		+ "inner join edu_lms_state_mstr s on s.state_id  = cast(p.reg_state as integer)\n"
		+ "inner join edu_lms_gender_mstr gm on gm.id=cast(p.gender as integer) where  p.id != 0  \n"
		+ SearchValue +" ORDER BY p.id " + orderType + " limit " + pageL + " OFFSET " + startPage;
		
	 		
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, name, dob ,aadhar_card,email,mobile_no,upload_date,gender, internship_completion_date,reg_state,state_reg_num,status);
			
			
 		 	 System.err.println("stmt---Pract signup report--------"+stmt);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			int j = startPage;
			
			int countFunction=1;
			int countFunctionDelete=1;
//			int countFunction1=1;
//			int countFunction2=1;
//			int countFunction3=1;
//			int countFunction4=1;
//			int countFunction5=1;
			System.err.println("aaaaaaaaaaaaaaaaaaaaaa");
			while (rs.next()) {
				
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				columns.put("ser", ++j);
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
//				 onclick='imageView("+rs.getString("id")+");'
//				columns.put("img","<img class='d-block img5050 imageZomm imgcsp' alt='No Image' id='myImg"+rs.getString("id")+"' src='MedicalImagePath1?i_id="+rs.getString("id")+"' />"
//						+ "<input type='hidden' id='imghid"+countFunction5+"' value="+rs.getString("id")+">");
 
				String f = "";
//				String action = "";
				String f1 = "";
				String chekboxaction="";
//				String vd = "";
//				String vioch = "";
//				String dp = "";
//				String dp1 = "";
//				String vm1 ="";
//				String vmp = "";
				
//				String ADD = "onclick=\" if (confirm('Are You Sure You Want to Edit Detail ?') ){EditData('"
//						+ rs.getString("id") + "','" + rs.getString("nrh_en_no") + "','" + rs.getString("status")
//						+ "') }else{ return false;}\"";
//				f = "<i class='fa fa-pencil-square-o editstate' title='Edit Data'>"
//						+ "<input type='hidden' id='CounId"+countFunction+"' value="+rs.getString("id")+">"
//						+ "<input type='hidden' id='Counnrh_en_no"+countFunction+"' value="+rs.getString("nrh_en_no")+">"
//						+ "<input type='hidden' id='Counstatus"+countFunction+"' value="+rs.getString("status")+"></i>";

			String st =	rs.getString(10) ;
			
			String Checkbox="<input class='nrCheckBox' type='checkbox' id='" + rs.getString("id")//13
			+ "' name='cbox'  /> <input type='hidden' id='nrCHid"+rs.getObject(1)+"' value='"+st+"'>";
			
//			onclick='checkCKBT()'
//			onchange='checkbox_count(this," + rs.getObject(1) + ");' 
	     String CheckboxId="<input  type='hidden' id='id" + rs.getString("id") + "' name='id" + rs.getString("id")//14
			+ "' value='" + rs.getString("id") + "'   />";
	     chekboxaction+=Checkbox;  
				
//				String ADD1 = "onclick=\" if (confirm('Are You Sure You Want to Delete Detail ?') ){deleteData('"
//						+ rs.getString("id") + "') }else{ return false;}\"";
//				f1 = "<i class='fa fa-trash statedlt'  title='Delete Data'>"
//						+ "<input type='hidden' id='DCounId"+countFunctionDelete+"' value="+rs.getString("id")+"></i>";
				
//				String VIEWDEGREE = "onclick=\" if (confirm('Are You Sure You Want to Show Detail ?') ){Pop_Up_Degree('"
//						+ rs.getString("id") + "') }else{ return false;}\"";
//				
//				vd = "<ul class='buttons-group mainbtn action daobtn' id='dgpbtn"+countFunction1+"'><li><a class='main-btn dark-btn-outline btn-hover btn-sm' value='ADD' title='View Data' >\n"
//						+ "		<i class='lni lni-eye dgpop'>"
//						+ "<input type='hidden' name='degreeId"+countFunction1+"' id='degreeId"+countFunction1+"' value="+rs.getString("id")+"></i></a> </li></ul>";
//				// columns.put("cnt",countFunction1);	
//				
				
//				String VIEWDEGREE = "onclick=\" if (confirm('Are You Sure You Want to Show Detail ?') ){Pop_Up_Degree('"
//						+ rs.getString("id") + "') }else{ return false;}\"";
				
//				vd = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn-outline btn-hover btn-sm' value='ADD' title='View Data' >\n"
//						+ " <i class='lni lni-eye dgpop'>"
//						+ "<input type='hidden' id='degreeId"+countFunction1+"' value="+rs.getString("id")+"></i></a> </li></ul>";
						
				
//				String VIEWIOCH = "onclick=\" if (confirm('Are You Sure You Want to Show Detail ?') ){Pop_Up_IOCH('"
//				+ rs.getString("id") + "') }else{ return false;}\"";
				
//				vioch = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn-outline btn-hover btn-sm' value='ADD' title='View Data' >\n"
//					+ "		<i class='lni lni-eye popview'>"
//					+ "<input type='hidden' id='pop_ioch"+countFunction2+"' value="+rs.getString("id")+"></i></a> </li></ul>";
			
//				String VIEWDate = "onclick=\" if (confirm('Are You Sure You Want to Show Detail  ?') ){valid_upto('"
//						+ rs.getString("id") + "') }else{ return false;}\"";
				
//				dp = "<input type='date' value='"+rs.getString("valid_upto")+"' name='valid_upto"+rs.getString("id")+"' id='valid_upto"+rs.getString("id")+"' class='form-control validup' >";
				
//				String VIEWDatelab = "onclick=\" if (confirm('Are You Sure You Want to Show Detail ?') ){valid_upto('"
//						+ rs.getString("id") + "') }else{ return false;}\"";
				
//				dp1 = "<lable>"+rs.getString("valid_upto") +"</label>";
							 
//				String ps = "";
//								
//				String perst = "onclick=\" if (confirm('Are You Sure You Want to Show Detail ?') ){fn_perst('"
//						+ rs.getString("id") + "') }else{ return false;}\"";
//						
//				ps = "<input type='text' value='"+rs.getString("per_state")+"' name='per_state"+rs.getString("id")+"' id='per_state"+rs.getString("id")+"' class='form-control' >";
//						
		//download 

//				String VIEWDEGREEP = "onclick=\" if (confirm('Are You Sure You Want to View Detail?') ){Pop_Up_DegreeP('"
//						+ rs.getString("id") + "') }else{ return false;}\"";
				
//				vm1 = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn-outline btn-hover btn-sm' value='ADD' title='View Data' >\n"
//						+ "<i class='lni lni-eye popdegreep'>"
//						+ "<input type='hidden' id='popdegreepId"+countFunction3+"' value="+rs.getString("id")+"></i></a> </li></ul>";
						
//				columns.put("vm1",vm1);	
				 //	 Integer nrhs =   (int) rs.getObject(25);
//				 	 String nrh_en_no =   (String) rs.getObject(2);
//				 	 System.err.println("nrh_en_no-------"+nrh_en_no);
			//if(!nrh_en_no.equals(null) && nrh_en_no!="" ) { 
				
//				String download1 = "onclick=\" if (confirm('Are You Sure You Want to download File ?') ){getPDF('"
//						+ rs.getString("id") + "') }else{ return false;}\"";
//				vmp= "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn dark-btn-outline btn-hover btn-sm' value='ADD' title='Download Data' >\n"
//						+ "<i class='lni lni-download degreepdf'>"
//						+ "<input type='hidden' id='degreepdfId"+countFunction4+"' value="+rs.getString("id")+"></i></a> </li></ul>";
//				
			//}
				
//				action =f+" "+  f1  ;
				 
				columns.put("chekboxaction", chekboxaction);
//				columns.put("action", action);
//  				columns.put("vd", vd);
//				columns.put("vioch", vioch);
//				columns.put("VIEWDate", dp);
//				columns.put("VIEWDatelab", dp1);
//				columns.put("vmp", vmp);
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
//				countFunction1+=1;
//				countFunction2+=1;
//				countFunction3+=1;
//				countFunction4+=1;
//				countFunction5+=1;
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
	public long DataTablePract_Signup_Details_ReportTotalCount(String Search, String orderColunm, String orderType, 
			String name,String dob, String aadhar_card ,String email,String mobile_no ,String upload_date, 
			String gender,String internship_completion_date,String reg_state 
			 ,String state_reg_num,String status) {
		
	 	
		String SearchValue = GenerateQueryWhereClause_SQL(Search, name, dob ,aadhar_card,email,mobile_no,upload_date,gender,
				internship_completion_date,reg_state,state_reg_num,status);
		int total = 0;
		String q = null;
		Connection conn = null;
	
		try {
			conn = dataSource.getConnection();
			
			q="select count (*) from (select ROW_NUMBER() OVER(order by p.id ASC) as sr_no,p.id,p.name,TO_CHAR(p.dob , 'dd/MON/YYYY') as dob, p.aadhar_card,p.email,p.mobile_no,TO_CHAR(p.upload_date , 'dd/MON/YYYY') as upload_date, gm.gender_name,TO_CHAR(p.internship_completion_date , 'dd/MON/YYYY') as internship_completion_date,s.state_name,p.state_reg_num,p.gender,p.reg_state,p.reject_remarks \n"
					+ "from reg_nch_practitioner_signup_details p\n"
					+ "inner join edu_lms_state_mstr s on s.state_id  = cast(p.reg_state as integer)\n"
					+ "inner join edu_lms_gender_mstr gm on gm.id=cast(p.gender as integer)     \n"
					+  SearchValue + " )ab";
	 
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			
			System.err.println("---Pract Sign up Report COUNT Query-->"+q);
		
			stmt = setQueryWhereClause_SQL(stmt, Search, name, dob ,aadhar_card,email,mobile_no,upload_date,gender, 
					internship_completion_date,reg_state,state_reg_num,status);

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
	

	public String GenerateQueryWhereClause_SQL(String Search, String name,String dob, String aadhar_card ,String email,
			String mobile_no ,String upload_date,String gender,String internship_completion_date,String reg_state,String state_reg_num,String status) {
		
		String SearchValue = "";
		if (Search != null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (upper(name) like ? or  to_char(dob , 'DD/MON/YYYY') like ? or aadhar_card like ? or upper(email) like ?"
					+ "or upper(p.mobile_no) like ? "
					+ "or to_char(upload_date , 'DD/MON/YYYY') like ? or gender_name  like ? or state_name  like ? "
					+ " or to_char(internship_completion_date , 'DD/MON/YYYY') like ? or state_reg_num like ? ) "
					;
		}
		
		   if (name != null && !name.equals("")) {
				SearchValue += " and upper(name) like ? ";
			}
			if (!dob.equals("DD/MM/YYYY") && dob != "DD/MM/YYYY" && !dob.equals("") && dob != null   ) {
				SearchValue += " and to_char(dob , 'DD/MM/YYYY') = ? ";
			}
			if (aadhar_card != "" && aadhar_card != null && aadhar_card != "null" && !aadhar_card.equals("")) {
				SearchValue += " and aadhar_card like  ? ";
			}
			if (email != null && !email.equals("")) {
				SearchValue += " and email like ? ";
			}
			if (!mobile_no.equals("") && mobile_no != null) {
				SearchValue += " and upper(mobile_no) like ? ";
			}
			if (!upload_date.equals("DD/MM/YYYY") && upload_date != "DD/MM/YYYY" && !upload_date.equals("") && upload_date != null) {
				SearchValue += " and to_char(upload_date , 'DD/MM/YYYY') = ? ";
			}
			if (!gender.equals("0") && gender != "0") {
				SearchValue += " and gender like ? ";
			}
			if (!internship_completion_date.equals("DD/MM/YYYY") && internship_completion_date != "DD/MM/YYYY" && !internship_completion_date.equals("") && internship_completion_date != null) {
				SearchValue += " and to_char(internship_completion_date , 'DD/MM/YYYY') = ? ";
			}
			if (!reg_state.equals("0") && reg_state != "0") {
				SearchValue += " and reg_state = ? ";
			}
			if (state_reg_num != null && !state_reg_num.equals("")) {
				SearchValue += " and upper(state_reg_num) like ? ";
			}
			
			if(status.equals("0")) {
				SearchValue += " and p.status = ? ";
			}
			if(status.equals("1")) {
				SearchValue += " and  p.status = ? ";
			}
			if(status.equals("2")) {
				SearchValue += " and p.status = ? ";
			}
			System.err.println("SearchValue===="+SearchValue);
		return SearchValue;
		
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String name,String dob, String aadhar_card ,String email,
			 String mobile_no,String upload_date, String gender,String internship_completion_date,String reg_state 
			 ,String state_reg_num,String status) {
		int flag = 0;
		try {
				
			if (Search != null && !Search.equals("")) {				
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
			

			}
			if (name != null && !name.equals("")){
				flag += 1;
				stmt.setString(flag,"%" + name.toUpperCase() + "%");
			}
			if (dob != null && !dob.equals("") && !dob.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, dob );
			}
			if (aadhar_card != "" && aadhar_card != null && aadhar_card != "null" && !aadhar_card.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + aadhar_card + "%");
			}
			if (email != null && !email.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + email + "%" );
			}
			if (!mobile_no.equals("") && mobile_no != null) {
				flag += 1;
				stmt.setString(flag, "%" + mobile_no + "%");
			}
			if (upload_date != null && !upload_date.equals("") && !upload_date.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, upload_date );
			}
			if (!gender.equals("0") && gender != "0") {
				flag += 1;
				stmt.setString(flag,"%"+ gender+"%");
			
			}
			if (internship_completion_date != null && !internship_completion_date.equals("") && !internship_completion_date.equals("DD/MM/YYYY")) {
				flag += 1;
				stmt.setString(flag, internship_completion_date );
			}
			if (!reg_state.equals("0") && reg_state != "0") {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(reg_state));
			
			}
			if (state_reg_num != null && !state_reg_num.equals("")) {
				flag += 1;
				stmt.setString(flag, "%" + state_reg_num.toUpperCase() + "%" );
			}
			if (status.equals("0")) {
				flag += 1;
				stmt.setInt(flag,0);
			}
			if (status.equals("1")) {
				flag += 1;
				stmt.setInt(flag,1);
			}
			if (status.equals("2")) {
				flag += 1;
				stmt.setInt(flag,2);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}

	
//	public String Approve_Pract_SignUp_ReportData(int id,String a,String status, HttpSession session)  {
//		String[] id_list = a.split(":");
//		Connection conn = null;
//		int out = 0;
//		
//	 //end
//	 	try {
//			Session sessionHQL = this.sessionFactory.openSession();
//			Transaction  tx= sessionHQL.beginTransaction();
//			conn = dataSource.getConnection();
//			 
//			PreparedStatement stmt = null;
//		 
//				for (int i = 0; i < id_list.length; i++) {
////					 String id = id_list[i];
//					System.err.println("idddddddddddddddddddddddddddddaoimp"+id);
//					REG_NCH_PRACTITIONER_Signup_Details SS = (REG_NCH_PRACTITIONER_Signup_Details) sessionHQL .get(REG_NCH_PRACTITIONER_Signup_Details.class, id);
//// 				  	SS.setStatus(Integer.parseInt(status));
// 				  	SS.setStatus(1);
// 					sessionHQL.update(SS);
////				  
////					 stmt = conn.prepareStatement("update reg_nch_practitioner_signup_details set status= 1");
////					 System.err.println("st--Pract----"+stmt);
////				      out = stmt.executeUpdate();
//				  	 
//						sessionHQL.flush();
//						sessionHQL.clear();
//						tx.commit();
////						out = 1;
//				  	
//				 
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
//				return "Data approved successfully.";
//				}
//				else
//					return "Data approved successfully.";
//			} else {
//				if(status.equals("1")) {
//					return "Data not approved successfully.";
//					}
//				else
//				return "UnSuccessfully";
//			}
//	}
	  public String Approve_Pract_SignUp_ReportData(String a,String username,String userId,int id) {
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
			 
//					for (int i = 0; i < id_list.length; i++) {
//						int id = Integer.parseInt(id_list[i]);
//						REG_NCH_PRACTITIONER_Signup_Details sd =  new REG_NCH_PRACTITIONER_Signup_Details();
						 stmt = conn.prepareStatement("update reg_nch_practitioner_signup_details set status=1 where id=?");
						 stmt.setInt(1, id);
					 System.err.println("st--Pract----"+stmt);
				      out = stmt.executeUpdate();
//							sd.setStatus(1);
//							sessionHQL.save(sd);
							
							sessionHQL.flush();
							sessionHQL.clear();
							tx.commit();
							out = 1;
//						}
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
	
	
//	public String Reject_Pract_SignUp_ReportData(String a, String userId_reject,HttpSession session) {
//		String[] id_list = a.split(":");
//		Connection conn = null;
//		String q = "";
//
//		try {
//			Session sessionHQL = this.sessionFactory.openSession();
//			Transaction tx = sessionHQL.beginTransaction();
//			
//
//			PreparedStatement stmt = null;
//
//			for (int i = 0; i < id_list.length; i++) {
//				int id = Integer.parseInt(id_list[i]);
//
//				REG_NCH_PRACTITIONER_Signup_Details sd = (REG_NCH_PRACTITIONER_Signup_Details) sessionHQL
//						.get(REG_NCH_PRACTITIONER_Signup_Details.class, id);
//				sd.setId(id);
//				sd.setStatus(2);
//
//				sessionHQL.update(sd);
//				sessionHQL.flush();
//				sessionHQL.clear();
//				tx.commit();
//
//				
//				conn = dataSource.getConnection();
//				
//				q = "Rejected is Successfully";
//				
//				
//			}
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			q = "Data is not Rejected Successfully";
//
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//				}
//			}
//		}
//		return q;
//
//	}

	public String Reject_Pract_SignUp_ReportData(String a,String username,String userId,int id) {{
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
			 
//					for (int i = 0; i < id_list.length; i++) {
//						int id = Integer.parseInt(id_list[i]);
//						REG_NCH_PRACTITIONER_Signup_Details sd =  new REG_NCH_PRACTITIONER_Signup_Details();
						 stmt = conn.prepareStatement("update reg_nch_practitioner_signup_details set status=2 where id=?");
						 stmt.setInt(1, id);
					 System.err.println("st--Pract----"+stmt);
				      out = stmt.executeUpdate();
//							sd.setStatus(1);
//							sessionHQL.save(sd);
							
							sessionHQL.flush();
							sessionHQL.clear();
							tx.commit();
							out = 1;
//						}
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
}
	
	public ArrayList<ArrayList<String>> getPract_Signup_Details_Report_Excel(String Search, String name,String dob, String aadhar_card ,String email,
			String mobile_no ,String upload_date,String gender,String internship_completion_date,String reg_state,String state_reg_num,String institute_status){
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		String SearchValue = GenerateQueryWhereClause_SQL(Search,name, dob ,aadhar_card,email,mobile_no,upload_date,gender,
				internship_completion_date,reg_state,state_reg_num,institute_status);
		
		Connection conn = null;
		String q = "";
		
		try {
			conn = dataSource.getConnection();

			String 	qry = "";

					
			q= "select ROW_NUMBER() OVER(order by p.id ASC) as sr_no,p.id,p.name,TO_CHAR(p.dob , 'dd/MON/YYYY') as dob, "
					+ "p.aadhar_card,p.email,p.mobile_no,TO_CHAR(p.upload_date , 'dd/MON/YYYY') as upload_date, "
					+ "TO_CHAR(p.internship_completion_date , 'dd/MON/YYYY') as internship_completion_date,s.state_name as reg_state,p.state_reg_num \n"
					+",case when p.gender = '1' then 'MALE'\n"
					+ " when p.gender = '7' then 'FEMALE' when p.gender = '10' then 'TRANSGENDER'\n"
					+ " else 'OTHER' end as gender \n"
					+ "from reg_nch_practitioner_signup_details p\n"
					+ "inner join edu_lms_state_mstr s on s.state_id  = cast(p.reg_state as integer)\n"
					+ SearchValue +" ORDER BY p.id ";
				
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL(stmt, Search, name, dob ,aadhar_card,email,mobile_no,upload_date,gender, 
						internship_completion_date,reg_state,state_reg_num,institute_status);
				ResultSet rs = stmt.executeQuery();
				
				System.err.println("-----------Pract Signup Report Excel----------"+stmt);
				
				int i = 1;
				while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(String.valueOf(i)); // 1
				
				alist.add(rs.getString("name"));//2
				alist.add(rs.getString("dob"));//4
				alist.add(rs.getString("aadhar_card"));//5
				alist.add(rs.getString("email"));//6
				alist.add(rs.getString("mobile_no"));//7
				alist.add(rs.getString("upload_date"));//8
				alist.add(rs.getString("gender"));//9
				alist.add(rs.getString("internship_completion_date"));//10
				alist.add(rs.getString("reg_state"));//11
				alist.add(rs.getString("state_reg_num"));//12
//				alist.add(rs.getString("institute_status"));//13
				
				
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
	
	@Override
	public ArrayList<ArrayList<String>> get_Parctname_reports_by_Reject_idata(String id) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	    Connection conn = null;
	    try{          
	    	conn = dataSource.getConnection();
	    	
	    	String 	sq1="SELECT name FROM reg_nch_practitioner_signup_details WHERE id = ?";
	    	
	        PreparedStatement stmt = conn.prepareStatement(sq1);
	        stmt.setInt(1, Integer.parseInt(id));
	       System.err.println("check queryyyyyyy"+stmt);
	        ResultSet rs = stmt.executeQuery();  
	        
	        String str1="";
	        while(rs.next()){
	        	ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("name"));// 0
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
	
	
	public String Approve_Pract_SignUp_ReportData33(String a,String status,String username,String reject_remarks) {
		String[] id_list = a.split(":");
		Connection conn = null;
		int out = 0;
		String q="";
		 
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
				for (int i = 0; i < id_list.length; i++) {
					int id = Integer.parseInt(id_list[i]);
					System.err.println("check 888"+id);
					stmt = conn.prepareStatement("update reg_nch_practitioner_signup_details set status=2,reject_remarks=? where id=?");
					stmt.setString(1, reject_remarks);
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
	

}
