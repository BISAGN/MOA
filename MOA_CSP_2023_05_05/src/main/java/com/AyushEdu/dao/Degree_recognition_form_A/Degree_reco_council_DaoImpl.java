package com.AyushEdu.dao.Degree_recognition_form_A;

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
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import com.AyushEdu.Models.TB_STATE;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_INSTITUTE_REGISTRATION;
@Repository
public class Degree_reco_council_DaoImpl implements Degree_reco_council_Dao {
	

	@Autowired
	private DataSource dataSource;
	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	//---------------------- UG Form A ---------------------//
	
	public List<Map<String, Object>> getFilter_UG_alist(int startPage, int pageLength,String Search, String orderColunm,
				String orderType, String university_id, String uni_id, String institute_id,
				String institute_status,String name_of_applicant_university,String name_ug_course,String abbre_undergraduate_course,
				String academic_year_applied_for, String permission_from_central_gov,String admission_intake,String num_of_student_admitted,String remarks ) {
		
			String SearchValue = GenerateQueryWhereClause_SQL3(Search, name_of_applicant_university,name_ug_course, abbre_undergraduate_course,
		    		 academic_year_applied_for,permission_from_central_gov,admission_intake,num_of_student_admitted,remarks);
		
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Connection conn = null;
				String q = "";
				
				try {
						conn = dataSource.getConnection();
			
//						 q="select u.university_name,a.id,a.name_of_applicant_university,a.undergraduate_course,a.abbre_undergraduate_course,"
//						 		+ "a.academic_session,a.name_of_college,a.country,a.state,a.district,\r\n"
//						 		+ "a.city,a.postal_address,a.email,a.website,a.academic_year_applied_for,a.permission_from_central_gov,"
//						 		+ "a.admission_intake,a.num_of_student_admitted,a.remarks\r\n"
//						 		+ "from dg_rec_ug_form_a_parent a \r\n"
//						 		+ "inner join edu_lms_university_mstr u on u.id=a.university_id \r\n"
//						 		+ "inner join logininformation li on li.userid=a.user_id\r\n"
//						 		+ "where a.university_id=? and a.council_approved_status=?"+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
//								+ pageLength + " OFFSET " + startPage; // a.university_approved_status=1 and a.council_approved_status=0 and li.institute_id=?
						 
						 q="select u.university_name,a.id,ir.institute_name,a.name_of_applicant_university,a.name_ug_course,a.abbre_undergraduate_course,\r\n"
						 		   + "a.academic_year_applied_for,a.academic_file_upload,a.permission_from_central_gov,a.admission_intake,a.num_of_student_admitted,a.postal_address,\r\n"
						 		    + "	a.email,a.website,a.remarks,a.reject_remarks\r\n"
									+"from dg_rec_ug_form_a_parent a \n"
									+"inner join edu_lms_institute_reg ir on cast(ir.id as character varying)=a.institute_name \r\n"
							 		+ "inner join edu_lms_university_mstr u on u.id=a.university_id \r\n"
							 		+ "inner join logininformation li on li.userid=a.user_id\r\n"
							 		+ "where a.university_id=? and a.council_approved_status=?"+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
									+ pageLength + " OFFSET " + startPage; // a.university_approved_status=1 and a.council_approved_status=0 and li.institute_id=?
							 

						 
						PreparedStatement stmt = conn.prepareStatement(q);
						stmt = setQueryWhereClause_SQL3(stmt,Search,name_of_applicant_university,name_ug_course, abbre_undergraduate_course,
					    		 academic_year_applied_for,permission_from_central_gov,admission_intake,num_of_student_admitted,remarks);
						//stmt.setInt(1, Integer.parseInt(university_id));
						stmt.setInt(1, Integer.parseInt(uni_id));
						stmt.setInt(2, Integer.parseInt(institute_status));
//						stmt.setInt(3, Integer.parseInt(institute_id));
						
//						 System.err.println("---uni_id--uni_id----"+uni_id);
//						 System.err.println("---institute_id--institute_id----"+institute_id);
					 System.err.println("---institute_status--institute_status----"+institute_status);
						 
						System.err.println("NCH_APP_1==="+stmt);
						ResultSet rs = stmt.executeQuery();
					
						ResultSetMetaData metaData = rs.getMetaData();
						int columnCount = metaData.getColumnCount();
						int j = startPage;
						int countFunction = 1;
						int countFunctionrej = 1;
						int countFunction2 =1;
						int countFunction3 =1;
						
						while (rs.next()) {
						Map<String, Object> columns = new LinkedHashMap<String, Object>();
						columns.put("ser", ++j);
						for (int i = 1; i <= columnCount; i++) {
							columns.put(metaData.getColumnLabel(i), rs.getObject(i));
						}
						String f = "";
						String action = "";
						String f1 = "";
						String vmp1 ="";
						String vmp2 ="";
						
						String ul="";
						ul+="<ul class='buttons-group mainbtn action daobtn'>";
						
						f ="<li><a class='main-btn active-btn-outline btn-hover btn-sm ADDuga' data-bs-toggle='modal' data-bs-target='#ugamodal' value='VIEW DETAILS'  title='VIEW DETAILS' >" 
								+ "<i class='lni lni-eye'>"
								+"<input type='hidden' id='apIdUga"+countFunction+"' value="+rs.getString("id")+">"
							    +"</i></a></li>"
							    +"<li><a class='main-btn danger-btn-outline btn-hover btn-sm clarificationformaData' data-bs-toggle='modal' data-bs-target='myModal'  value='ADD' title='Clarification Data' >" //id='id_add_attHospital1'
							    +"<i class='lni lni-close'></i></a></li>"
								+"<input type='hidden' id='RejectformaId"+countFunctionrej+"' value="+rs.getString("id")+"></i></a></li>";
						String download1 = "onclick=\" if (confirm('Are You Sure You Want to download File  ?') ){getPDF('"
								+ rs.getString("id") + "') }else{ return false;}\"";
						vmp1 = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn info-btn btn-hover btn-sm' value='ADD' title='Download File' >\n"
								+ "		<i class='lni lni-download pdfdown'>"
								+ "<input type='hidden' id='DCounpdf"+countFunction2+"' value="+rs.getString("id")+"></i></a> </li></ul>";
						
						String download2 = "onclick=\" if (confirm('Are You Sure You Want to download File  ?') ){getPDF('"
								+ rs.getString("id") + "') }else{ return false;}\"";
						vmp2 = "<ul class='buttons-group mainbtn action daobtn'><li><a class='main-btn info-btn btn-hover btn-sm' value='ADD' title='Download File' >\n"
								+ "		<i class='lni lni-download pdfdown'>"
								+ "<input type='hidden' id='DCounpdf"+countFunction3+"' value="+rs.getString("id")+"></i></a> </li></ul>";	 

						ul+=f + " " + f1 ;
						ul+="</ul>";
						
						action = ul;
						countFunction+=1;
						columns.put("action", action);
						columns.put("vmp1",vmp1);
						columns.put("vmp2",vmp2);
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
	public long getFilter_UG_aListCount(String search, String university_id, String uni_id, String institute_id,
			String institute_status,String name_of_applicant_university,String name_ug_course,String abbre_undergraduate_course,
			String academic_year_applied_for, String permission_from_central_gov,String admission_intake,String num_of_student_admitted,String remarks) {
		
		String SearchValue = GenerateQueryWhereClause_SQL3(search, name_of_applicant_university,name_ug_course, abbre_undergraduate_course,
	    		 academic_year_applied_for,permission_from_central_gov,admission_intake,num_of_student_admitted,remarks);
		
		int total = 0;
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();			
			
			q="select count(*) from (select u.university_name,a.id,dg.name_of_applicant_university,dg.name_ug_course,dg.abbre_undergraduate_course,"
					+ "a.academic_year_applied_for,a.admission_intake,a.num_of_student_admitted, \r\n"
				    + "a.remarks,a.permission_from_central_gov,a.academic_file_upload,a.reject_remarks "
					+ "from dg_rec_ug_form_a_parent a \r\n"
					+ "inner join edu_lms_university_mstr u on u.id=a.university_id \r\n"
					+"inner join dg_rec_ug dg on dg.user_id=a.user_id  \r\n"
					+ "inner join logininformation li on li.userid=a.user_id\r\n"
					+ "where a.university_id=? and a.council_approved_status=?) a"; // a.university_approved_status=? and a.council_approved_status=?
					
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL3(stmt,search,name_of_applicant_university,name_ug_course, abbre_undergraduate_course,
		    		 academic_year_applied_for,permission_from_central_gov,admission_intake,num_of_student_admitted,remarks);
//			stmt.setInt(1, Integer.parseInt(university_id));
			stmt.setInt(1, Integer.parseInt(uni_id));
			stmt.setInt(2, Integer.parseInt(institute_status));
//			stmt.setInt(3, Integer.parseInt(institute_id));
			
//			System.err.println("---uni_id--uni_id----"+uni_id);
//			 System.err.println("---institute_id--institute_id----"+institute_id);
//			 System.err.println("---institute_status--institute_status----"+institute_status);
			
			    System.err.println("NCH_APP_1_COUNT---"+stmt);
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
	public String GenerateQueryWhereClause_SQL3(String Search,String name_of_applicant_university,String name_ug_course,String abbre_undergraduate_course,
			String academic_year_applied_for, String permission_from_central_gov,String admission_intake,String num_of_student_admitted,String remarks) {
		String SearchValue="" ;
		
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (upper(name_of_applicant_university) Like ? or upper(undergraduate_course) Like ? "
					+ " or upper(abbre_undergraduate_course) Like ? or upper(academic_session) Like ? "
					+ " or upper(name_of_college) Like ? or upper(country) Like ? or upper(state) Like ? or upper(district) Like ?"
					+ " or upper(city) Like ? or upper(postal_address) Like ? or upper(email) Like ? or upper(website) Like ? "
					+ " or upper(academic_year_applied_for) Like ? or upper(permission_from_central_gov) Like ? "
					+ " or admission_intake::character varying like ? "
					+ "or num_of_student_admitted::character varying like ? or upper(remarks) Like ? )";
		}
//		if (name_of_institution != null && !name_of_institution.equals("")) {
//			SearchValue += "and upper(name_of_institution) Like ? ";
//		}
//		if (name_of_students_migrated != null && !name_of_students_migrated.equals("")) {
//			SearchValue += "and upper(name_of_students_migrated) Like ? ";
//		}
//		if( dt_of_migration != null && !dt_of_migration.equals("") && !dt_of_migration.equals("DD-MON-YYYY")) {
//			SearchValue +=  "and TO_CHAR(dt_of_migration , 'DD-MON-YYYY') = ? ";
//	     }
//		if (professional_year != null && !professional_year.equals("")) {
//			SearchValue += "and upper(professional_year) Like ? ";
//		}
		
//		if (university_enroll_num != null && !university_enroll_num.equals("")) {
//			SearchValue += "and university_enroll_num = ? ";
//		}
//		
//		if (remarks_migrated != null && !remarks_migrated.equals("")) {
//			SearchValue += "and upper(remarks_migrated) Like ? ";
//		}
		
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL3(PreparedStatement stmt, String Search,String name_of_applicant_university,String name_ug_course,String abbre_undergraduate_course,
			String academic_year_applied_for, String permission_from_central_gov,String admission_intake,String num_of_student_admitted,String remarks) {
		int flag = 2;
		try {
			if (Search!=null &&  !Search.equals("")) {
				
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
			if (name_of_applicant_university != null && !name_of_applicant_university.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+name_of_applicant_university.toUpperCase()+"%");
			}
			if (name_ug_course != null && !name_ug_course.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+name_ug_course.toUpperCase()+"%");
			}
			if (abbre_undergraduate_course != null && !abbre_undergraduate_course.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+abbre_undergraduate_course.toUpperCase()+"%");
			}
			if (academic_year_applied_for != null && !academic_year_applied_for.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+academic_year_applied_for.toUpperCase()+"%");
			}
			if (permission_from_central_gov != null && !permission_from_central_gov.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+permission_from_central_gov.toUpperCase()+"%");
			}
			if (admission_intake != null && !admission_intake.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+admission_intake.toUpperCase()+"%");
			}
			if (num_of_student_admitted != null && !num_of_student_admitted.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+num_of_student_admitted.toUpperCase()+"%");
			}
			if (remarks != null && !remarks.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+remarks.toUpperCase()+"%");
			}

		} catch (Exception e) {
		}

		return stmt;
	}
	
			//---------------------- UG Form B ---------------------//
			
			public List<Map<String, Object>> getFilter_UG_blist(int startPage, int pageLength,String Search, String orderColunm,
						String orderType, String university_id, String uni_id, String institute_id,
						String institute_status,String student_name,String date_of_admission, String neet_rank,String rank,String marks,String all_india,
						String state,String management_quota,String admission_authority,String court_order,String date_of_enroll_university,String uni_enroll_number,
						String date_of_intern_compl,String remarks_form_b) {
				
				String SearchValue = GenerateQueryWhereClause_SQL4(Search, student_name,date_of_admission,neet_rank,rank,marks,all_india,state,
						management_quota,admission_authority,court_order,date_of_enroll_university,uni_enroll_number,date_of_intern_compl,remarks_form_b);
				
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						Connection conn = null;
						String q = "";
						
						// patel
						try {
								conn = dataSource.getConnection();
					
								q="select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.student_name,to_char(a.date_of_admission,'DD-MON-YYYY') as date_of_admission, \r\n"
								 		+ "a.neet_rank,a.rank,a.marks,a.all_india,a.state,a.management_quota,a.admission_authority,a.court_order,\r\n"
								 		+ "a.uni_enroll_number,to_char(a.date_of_intern_compl,'DD-MON-YYYY') as date_of_intern_compl , \r\n"
								 		+ "to_char(a.date_of_enroll_university,'DD-MON-YYYY') as date_of_enroll_university,a.remarks_form_b \r\n"
								 		+ "from dg_rec_admitted_student_child a \r\n"
								 		+ "inner join logininformation li on li.userid=a.user_id\r\n"
								 		+ "where a.university_id=? and a.council_approved_status=? and li.institute_id=?" + SearchValue + " ORDER BY a.id "+ orderType + " limit "
										+ pageLength + " OFFSET " + startPage; // a.inst_status=1 and a.university_approved_status=1 and a.council_approved_status=0
								 
								PreparedStatement stmt = conn.prepareStatement(q);
								stmt = setQueryWhereClause_SQL4(stmt,Search,student_name,date_of_admission,neet_rank,rank,marks,all_india,state,management_quota,admission_authority,
										court_order,date_of_enroll_university,uni_enroll_number,date_of_intern_compl,remarks_form_b);
								stmt.setInt(1, Integer.parseInt(uni_id));
								stmt.setInt(2, Integer.parseInt(institute_status));
								stmt.setInt(3, Integer.parseInt(institute_id));
								
//								System.err.println("--ddd-uni_id--uni_id----"+uni_id);
//								 System.err.println("--ddd-institute_id--institute_id----"+institute_id);
//								 System.err.println("--ddd-institute_status--institute_status----"+institute_status);
								
//								System.err.println("NCH_APP_2======"+stmt);
								ResultSet rs = stmt.executeQuery();
							
								ResultSetMetaData metaData = rs.getMetaData();
								int columnCount = metaData.getColumnCount();
								int j = startPage;
								int countFunction = 1;

								while (rs.next()) {
								Map<String, Object> columns = new LinkedHashMap<String, Object>();
								columns.put("ser", ++j);
								for (int i = 1; i <= columnCount; i++) {
									columns.put(metaData.getColumnLabel(i), rs.getObject(i));
								}
								String f = "";
								String action = "";
								String f1 = "";
								
								String ul="";
								ul+="<ul class='buttons-group mainbtn action daobtn'>";
								
								f ="<li><a class='main-btn active-btn-outline btn-hover btn-sm ADDugb' data-bs-toggle='modal' data-bs-target='#ugbmodal' value='VIEW DETAILS'  title='VIEW DETAILS' >" 
										+ "<i class='lni lni-eye'>"
										+"<input type='hidden' id='apIdUgb"+countFunction+"' value="+rs.getString("id")+">"
									    +"</i></a></li>"
										+ "<li><a class='main-btn danger-btn-outline btn-hover btn-sm clarificationformbData' data-bs-toggle='modal' data-bs-target='myModal'  value='ADD' title='Clarification Data' >" //id='id_add_attHospital1'
										+"<i class='lni lni-close'></i></a></li>"
										+"<input type='hidden' id='RejectformbId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
								
								ul+=f + " " + f1 ;
								ul+="</ul>";
								
								action = ul;
								countFunction+=1;
								columns.put("action", action);
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
			public long getFilter_UG_bListCount(String search, String university_id, String uni_id, String institute_id,
					String institute_status,String student_name,String date_of_admission, String neet_rank,String rank,String marks,String all_india,
					String state,String management_quota,String admission_authority,String court_order,String date_of_enroll_university,String uni_enroll_number,
					String date_of_intern_compl,String remarks_form_b) {
				
				String SearchValue = GenerateQueryWhereClause_SQL4(search, student_name,date_of_admission,neet_rank,rank,marks,all_india,state,management_quota,admission_authority,
						court_order,date_of_enroll_university,uni_enroll_number,date_of_intern_compl,remarks_form_b);
				
				int total = 0;
				Connection conn = null;
				String q = "";
				try {
					conn = dataSource.getConnection();			
					
					q="select count(*) from (select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.student_name,to_char(a.date_of_admission,'DD-MON-YYYY') as date_of_admission, \r\n"
							+ "a.neet_rank,a.rank,a.marks,a.all_india,a.state,a.management_quota,a.admission_authority,a.court_order,\r\n"
							+ "a.uni_enroll_number,to_char(a.date_of_intern_compl,'DD-MON-YYYY') as date_of_intern_compl , \r\n"
							+ "to_char(a.date_of_enroll_university,'DD-MON-YYYY') as date_of_enroll_university,a.remarks_form_b \r\n"
							+ "from dg_rec_admitted_student_child a \r\n"
							+ "inner join logininformation li on li.userid=a.user_id\r\n"
							+ "where a.university_id=? and a.council_approved_status=? and li.institute_id=?) a"; // a.inst_status=1 and a.university_approved_status=1 and a.council_approved_status=0
							
					PreparedStatement stmt = conn.prepareStatement(q);
					stmt = setQueryWhereClause_SQL4(stmt,search,student_name,date_of_admission,neet_rank,rank,marks,all_india,state,management_quota,admission_authority,
							court_order,date_of_enroll_university,uni_enroll_number,date_of_intern_compl,remarks_form_b);
					stmt.setInt(1, Integer.parseInt(uni_id));
					stmt.setInt(2, Integer.parseInt(institute_status));
					stmt.setInt(3, Integer.parseInt(institute_id));
					
//					System.err.println("--ddd-uni_id--uni_id----"+uni_id);
//					 System.err.println("--ddd-institute_id--institute_id----"+institute_id);
//					 System.err.println("--ddd-institute_status--institute_status----"+institute_status);
					
//					System.err.println("NCH_APP_2_COUNT==="+stmt);   
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
			public String GenerateQueryWhereClause_SQL4(String Search,String student_name,String date_of_admission, String neet_rank,String rank,String marks,
					String all_india,String state,String management_quota,String admission_authority,String court_order,String date_of_enroll_university,
					String uni_enroll_number,	String date_of_intern_compl,String remarks_form_b) {
				String SearchValue="" ;
				
				if (Search!=null && !Search.equals("")) { // for Input Filter
					SearchValue += " and (upper(student_name) Like ? or TO_CHAR(date_of_admission , 'DD-MON-YYYY') like ? "
							+ " or upper(neet_rank) Like ? or or rank::character varying like ? or marks::character varying like ? or upper(all_india) Like ? "
							+ " or upper(state) Like ? or upper(management_quota) Like ? or upper(admission_authority) Like ? or upper(court_order) Like ? "
							+ " or TO_CHAR(date_of_enroll_university , 'DD-MON-YYYY') like ? "
							+ "or uni_enroll_number::character varying like ? or TO_CHAR(date_of_intern_compl , 'DD-MON-YYYY') like ?"
							+ " or upper(remarks_form_b) Like ?) ";
				}
				return SearchValue;
			}
			
			public PreparedStatement setQueryWhereClause_SQL4(PreparedStatement stmt, String Search,String student_name,String date_of_admission,
					 String neet_rank,String rank,String marks,String all_india,String state,String management_quota,String admission_authority,String court_order,String date_of_enroll_university,
					String uni_enroll_number,String date_of_intern_compl,String remarks_form_b) {
				int flag = 3;
				try {
					if (Search!=null &&  !Search.equals("")) {
						
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search + "%");
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
						stmt.setString(flag, "%" + Search + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						
						
						
						
					}
					if (student_name != null && !student_name.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+student_name.toUpperCase()+"%");
					}
					if( date_of_admission != null && !date_of_admission.equals("") && !date_of_admission.equals("DD-MON-YYYY")) {
						flag += 1;
						stmt.setString(flag, date_of_admission );
					}
					if (rank != null && !rank.equals("")) {
						flag += 1;
						stmt.setInt(flag,Integer.parseInt(rank));
					}
					if (marks != null && !marks.equals("")) {
						flag += 1;
						stmt.setInt(flag,Integer.parseInt(marks));
					}
					if (all_india != null && !all_india.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+all_india.toUpperCase()+"%");
					}
					if (state != null && !state.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+state.toUpperCase()+"%");
					}
					if (admission_authority != null && !admission_authority.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+admission_authority.toUpperCase()+"%");
					}
					if (court_order != null && !court_order.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+court_order.toUpperCase()+"%");
					}
					if( date_of_enroll_university != null && !date_of_enroll_university.equals("") && !date_of_enroll_university.equals("DD-MON-YYYY")) {
						flag += 1;
						stmt.setString(flag, date_of_enroll_university );
					}
					if (uni_enroll_number != null && !uni_enroll_number.equals("")) {
						flag += 1;
						stmt.setInt(flag,Integer.parseInt(uni_enroll_number));
					}
					if( date_of_intern_compl != null && !date_of_intern_compl.equals("") && !date_of_intern_compl.equals("DD-MON-YYYY")) {
						flag += 1;
						stmt.setString(flag, date_of_intern_compl );
					}
					if (remarks_form_b != null && !remarks_form_b.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+remarks_form_b.toUpperCase()+"%");
					}

				} catch (Exception e) {
				}

				return stmt;
			}
			
//---------------------- UG Form C ---------------------//
			//pranav
			public List<Map<String, Object>> getFilter_UG_clist(int startPage, int pageLength,String Search, String orderColunm,
						String orderType, String university_id, String uni_id, String institute_id,
						String institute_status,String name_homoeopathic_medical_clg,String attached_homoeopath_hospital, String super_speciality_hospital,
						String mou_date,String copy_of_mou,String name_of_hospital_staff,String designation,String qualification,String fulltime_parttime,
						String remarks_form_c) {
				
				String SearchValue = GenerateQueryWhereClause_SQL5(Search, name_homoeopathic_medical_clg,attached_homoeopath_hospital,super_speciality_hospital,
						mou_date,copy_of_mou,name_of_hospital_staff,designation,qualification,fulltime_parttime,remarks_form_c);
				
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						Connection conn = null;
						String q = "";
						
						try {
								conn = dataSource.getConnection();
					
								q="select a.inst_status,li.login_name as inst_name,a.id,a.name_homoeopathic_medical_clg,a.attached_homoeopath_hospital,a.super_speciality_hospital,"
										+ " to_char(a.mou_date,'DD-MON-YYYY') as mou_date,a.copy_of_mou,a.name_of_hospital_staff,a.designation, \r\n"
										+ "a.qualification,a.remarks_form_c,a.fulltime_parttime \r\n"
										+ "from dg_rec_hospital_attached_child a \r\n"
										+ "inner join logininformation li on li.userid=a.user_id\r\n"
										+ "where a.university_id=? and a.council_approved_status=? and li.institute_id=?" + SearchValue + " ORDER BY a.id "+ orderType + " limit "
										+ pageLength + " OFFSET " + startPage; //a.university_approved_status=1 and a.council_approved_status=0
							
										
								PreparedStatement stmt = conn.prepareStatement(q);
								stmt = setQueryWhereClause_SQL5(stmt,Search,name_homoeopathic_medical_clg,attached_homoeopath_hospital,super_speciality_hospital,
										mou_date,copy_of_mou,name_of_hospital_staff,designation,qualification,fulltime_parttime,remarks_form_c);
								stmt.setInt(1, Integer.parseInt(uni_id));
								stmt.setInt(2, Integer.parseInt(institute_status));
								stmt.setInt(3, Integer.parseInt(institute_id));
								
//								System.err.println("NCH_APP_3==="+stmt);
								ResultSet rs = stmt.executeQuery();
							
								ResultSetMetaData metaData = rs.getMetaData();
								int columnCount = metaData.getColumnCount();
								int j = startPage;
								int countFunction = 1;

								while (rs.next()) {
								Map<String, Object> columns = new LinkedHashMap<String, Object>();
								columns.put("ser", ++j);
								for (int i = 1; i <= columnCount; i++) {
									columns.put(metaData.getColumnLabel(i), rs.getObject(i));
								}
								String f = "";
								String action = "";
								String f1 = "";
								
								String ul="";
								ul+="<ul class='buttons-group mainbtn action daobtn'>";
								
								f ="<li><a class='main-btn active-btn-outline btn-hover btn-sm ADDugc' data-bs-toggle='modal' data-bs-target='#ugcmodal' value='VIEW DETAILS'  title='VIEW DETAILS' >" 
										+ "<i class='lni lni-eye'>"
										+"<input type='hidden' id='apIdUgc"+countFunction+"' value="+rs.getString("id")+">"
									    +"</i></a></li>"
										+ "<li><a class='main-btn danger-btn-outline btn-hover btn-sm clarificationformcData' data-bs-toggle='modal' data-bs-target='myModal'  value='ADD' title='Clarification Data' >" //id='id_add_attHospital1'
									    +"<i class='lni lni-close'></i></a></li>"
									    +"<input type='hidden' id='RejectformcId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
								
								ul+=f + " " + f1 ;
								ul+="</ul>";
								
								action = ul;
								countFunction+=1;
								columns.put("action", action);
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
			public long getFilter_UG_cListCount(String search, String university_id, String uni_id, String institute_id,
					String institute_status,String name_homoeopathic_medical_clg,String attached_homoeopath_hospital, String super_speciality_hospital,
					String mou_date,String copy_of_mou,String name_of_hospital_staff,String designation,String qualification,String fulltime_parttime,
					String remarks_form_c) {
				
				String SearchValue = GenerateQueryWhereClause_SQL5(search, name_homoeopathic_medical_clg,attached_homoeopath_hospital,super_speciality_hospital,
						mou_date,copy_of_mou,name_of_hospital_staff,designation,qualification,fulltime_parttime,remarks_form_c);
				
				int total = 0;
				Connection conn = null;
				String q = "";
				try {
					conn = dataSource.getConnection();			
					
					q="select count(*) from (select u.university_name,a.id,a.name_homoeopathic_medical_clg,a.attached_homoeopath_hospital,a.super_speciality_hospital, to_char(a.mou_date,'DD-MON-YYYY') as mou_date,a.copy_of_mou,a.name_of_hospital_staff,a.designation, \r\n"
							+ "a.qualification,a.remarks_form_c,a.fulltime_parttime \r\n"
							+ "from dg_rec_hospital_attached_child a \r\n"
							+ "inner join edu_lms_university_mstr u on u.id=a.university_id \r\n"
							+ "inner join logininformation li on li.userid=a.user_id\r\n"
							+ "where a.university_id=? and a.council_approved_status=? and li.institute_id=?) a"; // a.inst_status=1 and a.university_approved_status=1 and a.council_approved_status=0
							
					PreparedStatement stmt = conn.prepareStatement(q);
					stmt = setQueryWhereClause_SQL5(stmt,search,name_homoeopathic_medical_clg,attached_homoeopath_hospital,super_speciality_hospital,
							mou_date,copy_of_mou,name_of_hospital_staff,designation,qualification,fulltime_parttime,remarks_form_c);
					stmt.setInt(1, Integer.parseInt(uni_id));
					stmt.setInt(2, Integer.parseInt(institute_status));
					stmt.setInt(3, Integer.parseInt(institute_id));
					
//					System.err.println("NCH_APP_3_COUNT==="+stmt);
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
			public String GenerateQueryWhereClause_SQL5(String Search,String name_homoeopathic_medical_clg,String attached_homoeopath_hospital, String super_speciality_hospital,
					String mou_date,String copy_of_mou,String name_of_hospital_staff,String designation,String qualification,String fulltime_parttime,
					String remarks_form_c) {
				String SearchValue="" ;
				
				if (Search!=null && !Search.equals("")) { // for Input Filter
					SearchValue += " and (upper(name_homoeopathic_medical_clg) Like ? or upper(attached_homoeopath_hospital) Like ? "
							+ " or upper(super_speciality_hospital) Like ? or TO_CHAR(mou_date , 'DD-MON-YYYY') like ? "
							+ " or upper(copy_of_mou) Like ? or upper(name_of_hospital_staff) Like ? "
							+ "or designation::character varying like ? or upper(qualification) Like ? "
							+ " or upper(fulltime_parttime) Like ? or upper(remarks_form_c) Like ?) ";
				}
				return SearchValue;
			}
			
			public PreparedStatement setQueryWhereClause_SQL5(PreparedStatement stmt, String Search,String name_homoeopathic_medical_clg,
					String attached_homoeopath_hospital, String super_speciality_hospital,String mou_date,String copy_of_mou,String name_of_hospital_staff,
					String designation,String qualification,String fulltime_parttime,String remarks_form_c) {
				int flag = 3;
				try {
					if (Search!=null &&  !Search.equals("")) {
						
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search + "%");
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
					
					
					if (name_homoeopathic_medical_clg != null && !name_homoeopathic_medical_clg.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+name_homoeopathic_medical_clg.toUpperCase()+"%");
					}
					if (attached_homoeopath_hospital != null && !attached_homoeopath_hospital.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+attached_homoeopath_hospital.toUpperCase()+"%");
					}
					if (super_speciality_hospital != null && !super_speciality_hospital.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+super_speciality_hospital.toUpperCase()+"%");
					}
					if( mou_date != null && !mou_date.equals("") && !mou_date.equals("DD-MON-YYYY")) {
						flag += 1;
						stmt.setString(flag, mou_date );
					}
					if (copy_of_mou != null && !copy_of_mou.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+copy_of_mou.toUpperCase()+"%");
					}
					if (name_of_hospital_staff != null && !name_of_hospital_staff.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+name_of_hospital_staff.toUpperCase()+"%");
					}
					if (designation != null && !designation.equals("")) {
						flag += 1;
						stmt.setInt(flag,Integer.parseInt(designation));
					}
					if (qualification != null && !qualification.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+qualification.toUpperCase()+"%");
					}
					if (fulltime_parttime != null && !fulltime_parttime.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+fulltime_parttime.toUpperCase()+"%");
					}
					if (remarks_form_c != null && !remarks_form_c.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+remarks_form_c.toUpperCase()+"%");
					}

				} catch (Exception e) {
				}

				return stmt;
			}
			
//---------------------- UG Form D To---------------------//
			
			public List<Map<String, Object>> getFilter_UG_dtolist(int startPage, int pageLength,String Search, String orderColunm,
						String orderType, String university_id, String uni_id, String institute_id,
						String institute_status,String name_of_inst,String student_name_to_migrated, String migrated_dt_to,String professional_year_migrated,
						String university_enrollment_number,String remarks_form_d) {
				
				String SearchValue = GenerateQueryWhereClause_SQL2(Search, name_of_inst,student_name_to_migrated, migrated_dt_to, professional_year_migrated,
						university_enrollment_number, remarks_form_d);
				
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						Connection conn = null;
						String q = "";
						
						try {
								conn = dataSource.getConnection();
								
								 
								 q="select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.name_of_inst, \r\n"
									 		+ "a.student_name_to_migrated,to_char(a.migrated_dt_to,'DD-MON-YYYY') as migrated_dt_to, \r\n"
									 		+ "a.professional_year_migrated,a.university_enrollment_number,a.remarks_form_d \r\n"
									 		+ "from dg_rec_migrated_student_sub_child a \r\n"
									 		+ "inner join logininformation li on li.userid=a.user_id\r\n"
									 		+ "where a.university_id=? and a.council_approved_status=? and li.institute_id=?"+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
											+ pageLength + " OFFSET " + startPage; //a.university_approved_status=1 and a.council_approved_status=0 
								 
								PreparedStatement stmt = conn.prepareStatement(q);
								stmt = setQueryWhereClause_SQL2(stmt,Search,name_of_inst,student_name_to_migrated, migrated_dt_to, professional_year_migrated,
										university_enrollment_number, remarks_form_d);
								stmt.setInt(1, Integer.parseInt(uni_id));
								stmt.setInt(2, Integer.parseInt(institute_status));
								stmt.setInt(3, Integer.parseInt(institute_id));
								
//								System.err.println("NCH_APP_4==="+stmt);
								ResultSet rs = stmt.executeQuery();
							
								ResultSetMetaData metaData = rs.getMetaData();
								int columnCount = metaData.getColumnCount();
								int j = startPage;
								int countFunction = 1;
								while (rs.next()) {
								Map<String, Object> columns = new LinkedHashMap<String, Object>();
								columns.put("ser", ++j);
								for (int i = 1; i <= columnCount; i++) {
									columns.put(metaData.getColumnLabel(i), rs.getObject(i));
								}
								String f = "";
								String action = "";
								String f1 = "";
								
								String ul="";
								ul+="<ul class='buttons-group mainbtn action daobtn'>";
								
								f ="<li><a class='main-btn active-btn-outline btn-hover btn-sm ADDugd' data-bs-toggle='modal' data-bs-target='#ugdmodal' value='VIEW DETAILS'  title='VIEW DETAILS' >" 
										+ "<i class='lni lni-eye'>"
										+"<input type='hidden' id='apIdUgd"+countFunction+"' value="+rs.getString("id")+">"
									    +"</i></a></li>"
										+ "<li><a class='main-btn danger-btn-outline btn-hover btn-sm clarificationformdData' data-bs-toggle='modal' data-bs-target='myModal'  value='ADD' title='Clarification Data' >" //id='id_add_attHospital1'
									    +"<i class='lni lni-close'></i></a></li>"
									    +"<input type='hidden' id='RejectformdId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
								
								ul+=f + " " + f1 ;
								ul+="</ul>";
								
								action = ul;
								countFunction+=1;
								columns.put("action", action);
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
			public long getFilter_UG_dtoListCount(String search, String university_id, String uni_id, String institute_id,
					String institute_status,String name_of_inst,String student_name_to_migrated, String migrated_dt_to,String professional_year_migrated,
					String university_enrollment_number,String remarks_form_d) {
				
				String SearchValue = GenerateQueryWhereClause_SQL2(search,name_of_inst,student_name_to_migrated, migrated_dt_to, professional_year_migrated,
						university_enrollment_number, remarks_form_d);
				
				int total = 0;
				Connection conn = null;
				String q = "";
				try {
					conn = dataSource.getConnection();			
					
					q="select count(*) from (select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.name_of_inst, \r\n"
							+ "a.student_name_to_migrated,to_char(a.migrated_dt_to,'DD-MON-YYYY') as migrated_dt_to, \r\n"
							+ "a.professional_year_migrated,a.university_enrollment_number,a.remarks_form_d \r\n"
							+ "from dg_rec_migrated_student_sub_child a \r\n"
							+ "inner join logininformation li on li.userid=a.user_id\r\n"
							+ "where a.university_id=? and a.council_approved_status=? and li.institute_id=?) a"; // a.inst_status=1 and a.university_approved_status=1 and a.council_approved_status=0
							
					PreparedStatement stmt = conn.prepareStatement(q);
					stmt = setQueryWhereClause_SQL2(stmt,search,name_of_inst,student_name_to_migrated, migrated_dt_to, professional_year_migrated,
							university_enrollment_number, remarks_form_d);
					stmt.setInt(1, Integer.parseInt(uni_id));
					stmt.setInt(2, Integer.parseInt(institute_status));
					stmt.setInt(3, Integer.parseInt(institute_id));
					
//					System.err.println("NCH_AP_4_COUNT==="+stmt);
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
			public String GenerateQueryWhereClause_SQL2(String Search,String name_of_inst,String student_name_to_migrated, String migrated_dt_to,String professional_year_migrated,
					String university_enrollment_number,String remarks_form_d) {
				String SearchValue="" ;
				
				if (Search!=null && !Search.equals("")) { // for Input Filter
					SearchValue += " and (upper(name_of_inst) Like ? or upper(student_name_to_migrated) Like ? or "
							+ "TO_CHAR(migrated_dt_to , 'dd/MON/yyyy') like ? or upper(professional_year_migrated)  Like ? "
							+ "or university_enrollment_number::character varying like ? or upper(remarks_form_d) Like ? )";
				}
				return SearchValue;
			}
			
			public PreparedStatement setQueryWhereClause_SQL2(PreparedStatement stmt, String Search, String name_of_inst,String student_name_to_migrated, String migrated_dt_to,String professional_year_migrated,
					String university_enrollment_number,String remarks_form_d) {
				int flag = 3;
				try {
					if (Search!=null &&  !Search.equals("")) {
						
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						
					}
					if (name_of_inst != null && !name_of_inst.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+name_of_inst.toUpperCase()+"%");
					}
					if (student_name_to_migrated != null && !student_name_to_migrated.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+student_name_to_migrated.toUpperCase()+"%");
					}
					if( migrated_dt_to != null && !migrated_dt_to.equals("") && !migrated_dt_to.equals("DD-MON-YYYY")) {
						flag += 1;
						stmt.setString(flag, migrated_dt_to );
					}
					if (professional_year_migrated != null && !professional_year_migrated.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+professional_year_migrated.toUpperCase()+"%");
					}
					if (university_enrollment_number != null && !university_enrollment_number.equals("")) {
						flag += 1;
						stmt.setInt(flag,Integer.parseInt(university_enrollment_number));
					}
					if (remarks_form_d != null && !remarks_form_d.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+remarks_form_d.toUpperCase()+"%");
					}

				} catch (Exception e) {
				}

				return stmt;
			}
//---------------------- UG Form D  From---------------------//
			
			public List<Map<String, Object>> getFilter_UG_dfromlist(int startPage, int pageLength,String Search, String orderColunm,
						String orderType, String university_id, String uni_id, String institute_id,
						String institute_status,String name_of_institution,String name_of_students_migrated, String dt_of_migration,String professional_year,
						String university_enroll_num,String remarks_migrated) {
				
				String SearchValue = GenerateQueryWhereClause_SQL(Search, name_of_institution,name_of_students_migrated, dt_of_migration, professional_year, university_enroll_num, remarks_migrated);
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						Connection conn = null;
						String q = "";
						
						try {
								conn = dataSource.getConnection();
								 
								 q="select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.name_of_institution, \r\n"
								 		+ "a.name_of_students_migrated,to_char(a.dt_of_migration,'DD-MON-YYYY') as dt_of_migration, \r\n"
								 		+ "a.professional_year,a.university_enroll_num,a.remarks_migrated \r\n"
								 		+ "from dg_rec_migrated_student_from_child a \r\n"
								 		+ "inner join logininformation li on li.userid=a.user_id\r\n"
								 		+ "where a.university_id=? and a.council_approved_status=? and li.institute_id=?"+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
										+ pageLength + " OFFSET " + startPage; //a.university_approved_status=1 and a.council_approved_status=0 
								 
								PreparedStatement stmt = conn.prepareStatement(q);
								stmt = setQueryWhereClause_SQL(stmt,Search,name_of_institution,name_of_students_migrated, dt_of_migration, professional_year, university_enroll_num, remarks_migrated);
								stmt.setInt(1, Integer.parseInt(uni_id));
								stmt.setInt(2, Integer.parseInt(institute_status));
								stmt.setInt(3, Integer.parseInt(institute_id));
								
//								System.err.println("NCH_APP_5==="+stmt);
								ResultSet rs = stmt.executeQuery();
							
								ResultSetMetaData metaData = rs.getMetaData();
								int columnCount = metaData.getColumnCount();
								int j = startPage;
								int countFunction = 1;
								while (rs.next()) {
								Map<String, Object> columns = new LinkedHashMap<String, Object>();
								columns.put("ser", ++j);
								for (int i = 1; i <= columnCount; i++) {
											
									columns.put(metaData.getColumnLabel(i), rs.getObject(i));
								}
								String f = "";
								String action = "";
								String f1 = "";
								
								String ul="";
								ul+="<ul class='buttons-group mainbtn action daobtn'>";
								
								f ="<li><a class='main-btn active-btn-outline btn-hover btn-sm ADDuge' data-bs-toggle='modal' data-bs-target='#ugemodal' value='VIEW DETAILS'  title='VIEW DETAILS' >" 
										+ "<i class='lni lni-eye'>"
										+"<input type='hidden' id='apIdUge"+countFunction+"' value="+rs.getString("id")+">"
									    +"</i></a></li>"
										+ "<li><a class='main-btn danger-btn-outline btn-hover btn-sm clarificationformdfromData' data-bs-toggle='modal' data-bs-target='myModal'  value='ADD' title='Clarification Data' >" //id='id_add_attHospital1'
									    +"<i class='lni lni-close'></i></a></li>"
									    +"<input type='hidden' id='RejectformdfromId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
								
								ul+=f + " " + f1 ;
								ul+="</ul>";
								
								action = ul;
								countFunction+=1;
								columns.put("action", action);
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
			public long getFilter_UG_dfromListCount(String search, String university_id, String uni_id, String institute_id,
					String institute_status, String name_of_institution,String name_of_students_migrated, String dt_of_migration,String professional_year,
					String university_enroll_num,String remarks_migrated) {
				
				String SearchValue = GenerateQueryWhereClause_SQL(search,name_of_institution,name_of_students_migrated, dt_of_migration, professional_year, university_enroll_num, remarks_migrated);
				
				int total = 0;
				Connection conn = null;
				String q = "";
				try {
					conn = dataSource.getConnection();			
					
					q="select count(*) from (select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.name_of_institution, \r\n"
							+ "a.name_of_students_migrated,to_char(a.dt_of_migration,'DD-MON-YYYY') as dt_of_migration, \r\n"
							+ "a.professional_year,a.university_enroll_num,a.remarks_migrated \r\n"
							+ "from dg_rec_migrated_student_from_child a \r\n"
							+ "inner join logininformation li on li.userid=a.user_id\r\n"
							+ "where a.university_id=? and a.council_approved_status=? and li.institute_id=?) a"; // a.inst_status=1 and a.university_approved_status=1 and a.council_approved_status=0
							
					PreparedStatement stmt = conn.prepareStatement(q);
					stmt = setQueryWhereClause_SQL(stmt,search,name_of_institution,name_of_students_migrated, dt_of_migration, professional_year,
							university_enroll_num, remarks_migrated);
					stmt.setInt(1, Integer.parseInt(uni_id));
					stmt.setInt(2, Integer.parseInt(institute_status));
					stmt.setInt(3, Integer.parseInt(institute_id));
					
//					System.err.println(" NCH_APP_5_COUNT==="+stmt);
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
			public String GenerateQueryWhereClause_SQL(String Search, String name_of_institution, String name_of_students_migrated,String dt_of_migration, String professional_year,
					 String university_enroll_num,String remarks_migrated ) {
				String SearchValue="" ;
				
				if (Search!=null && !Search.equals("")) { // for Input Filter
					SearchValue += " and (upper(name_of_institution) Like ? or upper(name_of_students_migrated) Like ? or "
							+ "TO_CHAR(dt_of_migration , 'dd/MON/yyyy') like ? or upper(professional_year)  Like ? "
							+ "or university_enroll_num::character varying like ? or upper(remarks_migrated) Like ? )";
				}
				
				return SearchValue;
			}
			
			public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String name_of_institution, String name_of_students_migrated,String dt_of_migration, String professional_year,
					 String university_enroll_num,String remarks_migrated) {
				int flag = 3;
				try {
					if (Search!=null &&  !Search.equals("")) {
						
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						
					}
					if (name_of_students_migrated != null && !name_of_students_migrated.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+name_of_students_migrated.toUpperCase()+"%");
					}
					if (name_of_institution != null && !name_of_institution.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+name_of_institution.toUpperCase()+"%");
					}
					if( dt_of_migration != null && !dt_of_migration.equals("") && !dt_of_migration.equals("DD-MON-YYYY")) {
						flag += 1;
						stmt.setString(flag, dt_of_migration );
					}
					if (professional_year != null && !professional_year.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+professional_year.toUpperCase()+"%");
					}
					if (university_enroll_num != null && !university_enroll_num.equals("")) {
						flag += 1;
						stmt.setInt(flag,Integer.parseInt(university_enroll_num));
					}
					if (remarks_migrated != null && !remarks_migrated.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+remarks_migrated.toUpperCase()+"%");
					}

				} catch (Exception e) {
				}

				return stmt;
			}
			
			//---------------------- UG Form E ---------------------//
			
			public List<Map<String, Object>> getFilter_UG_elist(int startPage, int pageLength,String Search, String orderColunm,
						String orderType, String university_id, String uni_id, String institute_id,
						String institute_status,String name_of_examiners,String subject_examiners, String year_examiners,String qualification_examiners,
						String teaching_experience,String teacher_code, String reg_number,String d_university_appointment ) {
				
				String SearchValue = GenerateQueryWhereClause_SQL6(Search, name_of_examiners,subject_examiners, year_examiners, qualification_examiners, 
						teaching_experience,teacher_code,reg_number,d_university_appointment);
				
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						Connection conn = null;
						String q = "";
						
						try {
								conn = dataSource.getConnection();
					
								 q="select u.university_name,a.id,a.name_of_examiners,a.subject_examiners,a.year_examiners,a.qualification_examiners,"
								 		+ "a.teaching_experience,a.teacher_code,a.reg_number, \r\n"
								 		+ "to_char(a.d_university_appointment,'DD-MON-YYYY') as d_university_appointment \r\n"
								 		+ "from dg_rec_examiners_appointed_child a \r\n"
								 		+ "inner join edu_lms_university_mstr u on u.id=a.university_id \r\n"
								 		+ "inner join logininformation li on li.userid=a.user_id\r\n"
								 		+ "where a.university_id=? and a.council_approved_status=? "+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
										+ pageLength + " OFFSET " + startPage; //a.university_approved_status=1 and a.council_approved_status=0 and li.institute_id=?

									
								PreparedStatement stmt = conn.prepareStatement(q);
								stmt = setQueryWhereClause_SQL6(stmt,Search,name_of_examiners,subject_examiners, year_examiners, qualification_examiners, 
										teaching_experience,teacher_code,reg_number,d_university_appointment);
								stmt.setInt(1, Integer.parseInt(uni_id));
								stmt.setInt(2, Integer.parseInt(institute_status));
								
//								System.err.println("NCH_APP_6==="+stmt);
								ResultSet rs = stmt.executeQuery();
							
								ResultSetMetaData metaData = rs.getMetaData();
								int columnCount = metaData.getColumnCount();
								int j = startPage;
								int countFunction = 1;
								while (rs.next()) {
								Map<String, Object> columns = new LinkedHashMap<String, Object>();
								columns.put("ser", ++j);
								for (int i = 1; i <= columnCount; i++) {
										
									columns.put(metaData.getColumnLabel(i), rs.getObject(i));
								}
								String f = "";
								String action = "";
								String f1 = "";
								
								String ul="";
								ul+="<ul class='buttons-group mainbtn action daobtn'>";
								
								f ="<li><a class='main-btn active-btn-outline btn-hover btn-sm ADDugf' data-bs-toggle='modal' data-bs-target='#ugfmodal' value='VIEW DETAILS'  title='VIEW DETAILS' >" 
										+ "<i class='lni lni-eye'>"
										+"<input type='hidden' id='apIdUgf"+countFunction+"' value="+rs.getString("id")+">"
									    +"</i></a></li>"
										+ "<li><a class='main-btn danger-btn-outline btn-hover btn-sm clarificationformeData' data-bs-toggle='modal' data-bs-target='myModal'  value='ADD' title='Clarification Data' >" //id='id_add_attHospital1'
									    +"<i class='lni lni-close'></i></a></li>"
									    +"<input type='hidden' id='RejectformeId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
								
								ul+=f + " " + f1 ;
								ul+="</ul>";
								
								action = ul;
								countFunction+=1;
								columns.put("action", action);
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
			public long getFilter_UG_eListCount(String search, String university_id, String uni_id, String institute_id,
					String institute_status,String name_of_examiners,String subject_examiners, String year_examiners,String qualification_examiners,
					String teaching_experience,String teacher_code, String reg_number,String d_university_appointment) {
				
				String SearchValue = GenerateQueryWhereClause_SQL6(search, name_of_examiners,subject_examiners, year_examiners, qualification_examiners, 
						teaching_experience,teacher_code,reg_number,d_university_appointment);
				
				int total = 0;
				Connection conn = null;
				String q = "";
				try {
					conn = dataSource.getConnection();			
					
					q="select count(*) from (select u.university_name,a.id,a.name_of_examiners,a.subject_examiners,a.year_examiners,a.qualification_examiners,a.teaching_experience,a.teacher_code,a.reg_number, \r\n"
							+ "to_char(a.d_university_appointment,'DD-MON-YYYY') as d_university_appointment \r\n"
							+ "from dg_rec_examiners_appointed_child a \r\n"
							+ "inner join edu_lms_university_mstr u on u.id=a.university_id \r\n"
							+ "inner join logininformation li on li.userid=a.user_id\r\n"
							+ "where a.university_id=? and a.council_approved_status=?) a"; // a.inst_status=1 and a.university_approved_status=1 and a.council_approved_status=0
							

					PreparedStatement stmt = conn.prepareStatement(q);
					stmt = setQueryWhereClause_SQL6(stmt,search,name_of_examiners,subject_examiners, year_examiners, qualification_examiners, 
							teaching_experience,teacher_code,reg_number,d_university_appointment);
					stmt.setInt(1, Integer.parseInt(uni_id));
					stmt.setInt(2, Integer.parseInt(institute_status));
					
//					    System.err.println("NCH_AP_6_COUNT==="+stmt);
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
			public String GenerateQueryWhereClause_SQL6(String Search, String name_of_examiners,String subject_examiners, String year_examiners,
					String qualification_examiners,String teaching_experience,String teacher_code, String reg_number,String d_university_appointment ) {
				
				String SearchValue="" ;
				
				if (Search!=null && !Search.equals("")) { // for Input Filter
					SearchValue += " and (upper(name_of_examiners) Like ? or upper(subject_examiners) Like ? "
							+ "or upper(year_examiners) Like ? or upper(qualification_examiners)  Like ? "
							+ "or upper(teaching_experience) Like ? or teacher_code::character varying like ? "
							+ "or reg_number::character varying like ? or TO_CHAR(d_university_appointment , 'DD-MON-YYYY') like ?) ";
				}
				
				return SearchValue;
			}
			
			public PreparedStatement setQueryWhereClause_SQL6(PreparedStatement stmt, String Search, String name_of_examiners,String subject_examiners,
					String year_examiners,String qualification_examiners,String teaching_experience,String teacher_code, String reg_number,
					String d_university_appointment) {
				
				int flag = 2;
				try {
					if (Search!=null &&  !Search.equals("")) {
						
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
						stmt.setString(flag, "%" + Search + "%");
						
					}
					if (name_of_examiners != null && !name_of_examiners.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+name_of_examiners.toUpperCase()+"%");
					}
					if (subject_examiners != null && !subject_examiners.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+subject_examiners.toUpperCase()+"%");
					}
					if (year_examiners != null && !year_examiners.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+year_examiners.toUpperCase()+"%");
					}
					if (qualification_examiners != null && !qualification_examiners.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+qualification_examiners.toUpperCase()+"%");
					}
					if (teaching_experience != null && !teaching_experience.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+teaching_experience.toUpperCase()+"%");
					}
					if (teacher_code != null && !teacher_code.equals("")) {
						flag += 1;
						stmt.setInt(flag,Integer.parseInt(teacher_code));
					}
					if (reg_number != null && !reg_number.equals("")) {
						flag += 1;
						stmt.setInt(flag,Integer.parseInt(reg_number));
					}
					if( d_university_appointment != null && !d_university_appointment.equals("") && !d_university_appointment.equals("DD-MON-YYYY")) {
						flag += 1;
						stmt.setString(flag, d_university_appointment );
					}

				} catch (Exception e) {
				}

				return stmt;
			}
//---------------------- UG Form F ---------------------//
			
			public List<Map<String, Object>> getFilter_UG_flist(int startPage, int pageLength,String Search, String orderColunm,
						String orderType, String university_id, String uni_id, String institute_id,
						String institute_status,String name_of_students,String year_of_admission, String date_of_result_final_year,
						String provisional_reg_no,String year_of_provisional_reg,String date_of_starting_internship, String date_of_completion_internship,
						String remark_form_f) {
				
				String SearchValue = GenerateQueryWhereClause_SQL7(Search, name_of_students,year_of_admission, date_of_result_final_year, provisional_reg_no, 
						year_of_provisional_reg,date_of_starting_internship,date_of_completion_internship,remark_form_f);
				
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						Connection conn = null;
						String q = "";
						
						try {
								conn = dataSource.getConnection();
					
								q="select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.name_of_students, \r\n"
								 		+ "a.year_of_admission,to_char(a.date_of_result_final_year,'DD-MON-YYYY') as date_of_result_final_year, \r\n"
								 		+ "a.provisional_reg_no,a.year_of_provisional_reg, \r\n"
								 		+ "to_char(a.date_of_starting_internship,'DD-MON-YYYY') as date_of_starting_internship, \r\n"
								 		+ "to_char(a.date_of_completion_internship,'DD-MON-YYYY') as date_of_completion_internship,a.remark_form_f \r\n"
								 		+ "from dg_rec_intern_student_course_child a \r\n"
								 		+ "inner join logininformation li on li.userid=a.user_id\r\n"
								 		+ "where a.university_id=? and a.council_approved_status=? and li.institute_id=?" + SearchValue + " ORDER BY a.id "+ orderType + " limit "
										+ pageLength + " OFFSET " + startPage; //a.university_approved_status=1 and a.council_approved_status=0 
								 
								PreparedStatement stmt = conn.prepareStatement(q);
								stmt = setQueryWhereClause_SQL7(stmt,Search,name_of_students,year_of_admission, date_of_result_final_year, provisional_reg_no, 
										year_of_provisional_reg,date_of_starting_internship,date_of_completion_internship,remark_form_f);
								stmt.setInt(1, Integer.parseInt(uni_id));
								stmt.setInt(2, Integer.parseInt(institute_status));
								stmt.setInt(3, Integer.parseInt(institute_id));
								
//								System.err.println("\n NCH_APP_7==="+stmt);
								ResultSet rs = stmt.executeQuery();
							
								ResultSetMetaData metaData = rs.getMetaData();
								int columnCount = metaData.getColumnCount();
								int j = startPage;
								int countFunction = 1;

								while (rs.next()) {
								Map<String, Object> columns = new LinkedHashMap<String, Object>();
								columns.put("ser", ++j);
								for (int i = 1; i <= columnCount; i++) {
									String checkbox="";
										
									columns.put(metaData.getColumnLabel(i), rs.getObject(i));
								}
								String f = "";
								String action = "";
								String f1 = "";
								
								String ul="";
								ul+="<ul class='buttons-group mainbtn action daobtn'>";
								
								f ="<li><a class='main-btn active-btn-outline btn-hover btn-sm ADDugg' data-bs-toggle='modal' data-bs-target='#uggmodal' value='VIEW DETAILS'  title='VIEW DETAILS' >" 
										+ "<i class='lni lni-eye'>"
										+"<input type='hidden' id='apIdUgg"+countFunction+"' value="+rs.getString("id")+">"
									    +"</i></a></li>"
										+ "<li><a class='main-btn danger-btn-outline btn-hover btn-sm clarificationformfData' data-bs-toggle='modal' data-bs-target='myModal'  value='ADD' title='Clarification Data' >" //id='id_add_attHospital1'
									    +"<i class='lni lni-close'></i></a></li>"
									    +"<input type='hidden' id='RejectformfId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
								
								ul+=f + " " + f1 ;
								ul+="</ul>";
								
								action = ul;
								countFunction+=1;
								columns.put("action", action);
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
			public long getFilter_UG_fListCount(String search, String university_id, String uni_id, String institute_id,
					String institute_status,String name_of_students,String year_of_admission, String date_of_result_final_year,
					String provisional_reg_no,String year_of_provisional_reg,String date_of_starting_internship, String date_of_completion_internship,
					String remark_form_f) {
				
				String SearchValue = GenerateQueryWhereClause_SQL7(search, name_of_students,year_of_admission, date_of_result_final_year, provisional_reg_no, 
						year_of_provisional_reg,date_of_starting_internship,date_of_completion_internship,remark_form_f);
				
				int total = 0;
				Connection conn = null;
				String q = "";
				try {
					conn = dataSource.getConnection();			
					
					q="select count(*) from (select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.name_of_students, \r\n"
							+ "a.year_of_admission,to_char(a.date_of_result_final_year,'DD-MON-YYYY') as date_of_result_final_year, \r\n"
							+ "a.provisional_reg_no,a.year_of_provisional_reg, \r\n"
							+ "to_char(a.date_of_starting_internship,'DD-MON-YYYY') as date_of_starting_internship, \r\n"
							+ "to_char(a.date_of_completion_internship,'DD-MON-YYYY') as date_of_completion_internship,a.remark_form_f \r\n"
							+ "from dg_rec_intern_student_course_child a \r\n"
							+ "inner join logininformation li on li.userid=a.user_id\r\n"
							+ "where a.university_id=? and a.council_approved_status=? and li.institute_id=?) a"; // a.inst_status=1 and a.university_approved_status=1 and a.council_approved_status=0
							
					PreparedStatement stmt = conn.prepareStatement(q);
					stmt = setQueryWhereClause_SQL7(stmt,search,name_of_students,year_of_admission, date_of_result_final_year, provisional_reg_no, 
							year_of_provisional_reg,date_of_starting_internship,date_of_completion_internship,remark_form_f);
					stmt.setInt(1, Integer.parseInt(uni_id));
					stmt.setInt(2, Integer.parseInt(institute_status));
					stmt.setInt(3, Integer.parseInt(institute_id));
					stmt.setInt(3, Integer.parseInt(institute_id));
					
//					System.err.println("NCH_APP_7_COUNT==="+stmt);
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
			public String GenerateQueryWhereClause_SQL7(String Search, String name_of_students,String year_of_admission, String date_of_result_final_year,
					String provisional_reg_no,String year_of_provisional_reg,String date_of_starting_internship, String date_of_completion_internship,
					String remark_form_f) {
				
				String SearchValue="" ;
				
				if (Search!=null && !Search.equals("")) { // for Input Filter
					SearchValue += " and (upper(name_of_students) Like ? or upper(year_of_admission) Like ? "
							+ "or TO_CHAR(date_of_result_final_year , 'DD-MON-YYYY') like ? or provisional_reg_no::character varying like ? "
							+ "or upper(year_of_provisional_reg) Like ? or TO_CHAR(date_of_starting_internship , 'DD-MON-YYYY') like ? "
							+ "or TO_CHAR(date_of_completion_internship , 'DD-MON-YYYY') like ? or upper(remark_form_f) Like ? )";
				}
				
				return SearchValue;
			}
			
			public PreparedStatement setQueryWhereClause_SQL7(PreparedStatement stmt, String Search,String name_of_students,String year_of_admission, String date_of_result_final_year,
					String provisional_reg_no,String year_of_provisional_reg,String date_of_starting_internship, String date_of_completion_internship,
					String remark_form_f) {
				
				int flag = 3;
				try {
					if (Search!=null &&  !Search.equals("")) {
						
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						
						
					}
					if (name_of_students != null && !name_of_students.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+name_of_students.toUpperCase()+"%");
					}
					if (year_of_admission != null && !year_of_admission.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+year_of_admission.toUpperCase()+"%");
					}
					if( date_of_result_final_year != null && !date_of_result_final_year.equals("") && !date_of_result_final_year.equals("DD-MON-YYYY")) {
						flag += 1;
						stmt.setString(flag, date_of_result_final_year );
					}
					if (provisional_reg_no != null && !provisional_reg_no.equals("")) {
						flag += 1;
						stmt.setInt(flag,Integer.parseInt(provisional_reg_no));
					}
					if (year_of_provisional_reg != null && !year_of_provisional_reg.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+year_of_provisional_reg.toUpperCase()+"%");
					}
					if( date_of_starting_internship != null && !date_of_starting_internship.equals("") && !date_of_starting_internship.equals("DD-MON-YYYY")) {
						flag += 1;
						stmt.setString(flag, date_of_starting_internship );
					}
					if( date_of_completion_internship != null && !date_of_completion_internship.equals("") && !date_of_completion_internship.equals("DD-MON-YYYY")) {
						flag += 1;
						stmt.setString(flag, date_of_completion_internship );
					}
					if (remark_form_f != null && !remark_form_f.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+remark_form_f.toUpperCase()+"%");
					}
					

				} catch (Exception e) {
				}

				return stmt;
			}
			
			/////////////////// Council View Data University And Institute   ///////////////////////////
			
												// Form A UG//
			
			@Override
			public List<Map<String, Object>> getviewdataugaid(String id) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			System.err.println("--C-in daoimpl-dddd---");
			try {
				
				conn = dataSource.getConnection();
				
				q="select * from dg_rec_ug_form_a_parent where id=? ";
				
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt.setInt(1,Integer.parseInt(id));
					ResultSet rs = stmt.executeQuery();
					
					System.err.println("--stmt---"+stmt);

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
													// Form B Institute//
			@Override
			public List<Map<String, Object>> getviewdataugbid(String id) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			System.err.println("--C-in daoimpl-bbbbbbbbbbbbb---");
			try {
				
				conn = dataSource.getConnection();
				
						
				q="select * from dg_rec_admitted_student_child where id=? ";
				q="select *,to_char(date_of_admission,'DD-MON-YYYY') as date_of_admission,"
						+ "to_char(date_of_intern_compl,'DD-MON-YYYY')as date_of_intern_compl,"
						+ "to_char(date_of_enroll_university,'DD-MON-YYYY')as date_of_enroll_university"
						+ " from dg_rec_admitted_student_child where id=? ";

				PreparedStatement stmt = conn.prepareStatement(q);
				stmt.setInt(1,Integer.parseInt(id));
					ResultSet rs = stmt.executeQuery();
					
					System.err.println("--stmt---"+stmt);

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
														// Form C UG//
			@Override
			public List<Map<String, Object>> getviewdataugcid(String id) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			System.err.println("--C-in daoimpl-dddd---");
			try {
				
				conn = dataSource.getConnection();
				
						
				q="select *,to_char(mou_date,'DD-MON-YYYY') as mou_date from dg_rec_hospital_attached_child where id=? ";

				PreparedStatement stmt = conn.prepareStatement(q);
				stmt.setInt(1,Integer.parseInt(id));
					ResultSet rs = stmt.executeQuery();
					
					System.err.println("--stmt---"+stmt);

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
														// Form D Institute//
			@Override
			public List<Map<String, Object>> getviewdataugdid(String id) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			System.err.println("--C-in daoimpl-mmmm---");
			try {
				
				conn = dataSource.getConnection();
				
				q="select *,to_char(migrated_dt_to,'DD-MON-YYYY') as migrated_dt_to from dg_rec_migrated_student_sub_child where id=? ";
				
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt.setInt(1,Integer.parseInt(id));
					ResultSet rs = stmt.executeQuery();
					
					System.err.println("--stmt---"+stmt);

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
															// Form E Institute//
			@Override
			public List<Map<String, Object>> getviewdataugeid(String id) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			System.err.println("--C-in daoimpl-dddd---");
			try {
				
				conn = dataSource.getConnection();
				
				q="select *,to_char(dt_of_migration,'DD-MON-YYYY') as dt_of_migration from dg_rec_migrated_student_from_child where id=? ";
						
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt.setInt(1,Integer.parseInt(id));
					ResultSet rs = stmt.executeQuery();
					
					System.err.println("--stmt---"+stmt);

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
														// Form F UG//
			@Override
			public List<Map<String, Object>> getviewdataugfid(String id) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			System.err.println("--C-in daoimpl-dddd---");
			try {
				
				conn = dataSource.getConnection();
				
//				q="select *,to_char(migrated_dt_to,'DD-MON-YYYY') as migrated_dt_to2 from dg_rec_migrated_student_sub_child where id=? ";
						
				q="select * from dg_rec_examiners_appointed_child where id=? ";
				
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt.setInt(1,Integer.parseInt(id));
					ResultSet rs = stmt.executeQuery();
					
					System.err.println("--stmt---"+stmt);

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
														// Form G Institute//
			@Override
			public List<Map<String, Object>> getviewdatauggid(String id) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			System.err.println("--C-in daoimpl-dddd---");
			try {
				
				conn = dataSource.getConnection();
				
				q="select *,to_char(date_of_result_final_year,'DD-MON-YYYY') as date_of_result_final_year,"
						+"to_char(date_of_starting_internship,'DD-MON-YYYY') as date_of_starting_internship,"
						+"to_char(date_of_completion_internship,'DD-MON-YYYY') as date_of_completion_internship"
						+ " from dg_rec_intern_student_course_child where id=? ";
						
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt.setInt(1,Integer.parseInt(id));
					ResultSet rs = stmt.executeQuery();
					
					System.err.println("--stmt---"+stmt);

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
			
			//----------------------	getInstituteListbyUserID--------------------------
			
//			public List<Map<String, Object>> getinstitutelistbyuniversity() {
//						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//						Connection conn = null;
//						String q = "";
//						try {
//
//								conn = dataSource.getConnection();
//					
//								 q= "select university_id,institute_name,university_name from edu_lms_institute_reg ins\r\n"
//								 		+ "inner join edu_lms_university_mstr un on un.id=ins.university_id ";
//								
//								PreparedStatement stmt = conn.prepareStatement(q);
//								
//								ResultSet rs = stmt.executeQuery();
//							System.out.println("stmt---"+stmt);
//								ResultSetMetaData metaData = rs.getMetaData();
//								int columnCount = metaData.getColumnCount();
//						
//								while (rs.next()) {
//								Map<String, Object> columns = new LinkedHashMap<String, Object>();
//								for (int i = 1; i <= columnCount; i++) {
//									columns.put(metaData.getColumnLabel(i), rs.getObject(i));
//								}
//								list.add(columns);
//						}
//					rs.close();
//					stmt.close();
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				} finally {
//					if (conn != null) {
//						try {
//							conn.close();
//						} catch (SQLException e) {
//					  }
//					}
//				}
//				return list;
//			}
			
			
			@SuppressWarnings("deprecation")
			public @ResponseBody List<EDU_LMS_INSTITUTE_REGISTRATION> getuniversitylistUrl(Integer selval) {

				System.err.println("selval------>>>>" + selval);

				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				try {

					Query q1 = sessionHQL
							.createQuery("from UserLogin lo,UserRole ur, Role ro,EDU_LMS_INSTITUTE_REGISTRATION ins where "
									+ "lo.userId = ur.userId and "
									+ "ro.roleId=ur.roleId and role='Institute_NCH' "
									+ "and lo.institute_id=ins.id and ins.university_id=:university_id");
					
					q1.setInteger("university_id", selval);
					@SuppressWarnings("unchecked")
					List<EDU_LMS_INSTITUTE_REGISTRATION> list = (List<EDU_LMS_INSTITUTE_REGISTRATION>) q1.list();
					tx.commit();
					System.err.println(q1+"----list------>>>"+list);
					return list;
					
				} catch (RuntimeException e) {
					e.printStackTrace();
					return null;
				} finally {
					if (sessionHQL != null) {
						sessionHQL.close();
					}
				}
				
			}
			//----------------------end	getInstituteListbyUserID--------------------------
			
                                   ////////////  PDF ////////////////
			
			@Override
			public ArrayList<ArrayList<String>> getviewdataugaidPDF(String id) {
				ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			System.err.println("--C-in daoimpl-dddd---");
			try {
				
				conn = dataSource.getConnection();
				
				q="select id,name_of_applicant_university,undergraduate_course,abbre_undergraduate_course,academic_session,name_of_college,"
						+ "country,state,district,city,\r\n"
						+ "postal_address,email,website,academic_year_applied_for,permission_from_central_gov,admission_intake,"
						+ "num_of_student_admitted,remarks\r\n"
						+ "from dg_rec_ug_form_a_parent \r\n"
						+ "where id=? ";
				
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt.setInt(1,Integer.parseInt(id));
					ResultSet rs = stmt.executeQuery();
					
					System.err.println("-aaa-stmt---"+stmt);

					ResultSetMetaData metaData = rs.getMetaData();
					int columnCount = metaData.getColumnCount();

					while (rs.next()) {
						
						ArrayList<String> alist = new ArrayList<String>();
						alist.add(rs.getString("id"));// 0
						alist.add(rs.getString("name_of_applicant_university"));// 1
						alist.add(rs.getString("undergraduate_course"));// 2
						alist.add(rs.getString("abbre_undergraduate_course"));// 3
						alist.add(rs.getString("academic_session"));// 4
						alist.add(rs.getString("name_of_college"));// 5
						alist.add(rs.getString("country"));// 6
						alist.add(rs.getString("state"));// 7
						alist.add(rs.getString("district"));// 8
						alist.add(rs.getString("city"));// 9
						alist.add(rs.getString("postal_address"));// 10
						alist.add(rs.getString("email"));// 11
						alist.add(rs.getString("website"));// 12
						alist.add(rs.getString("academic_year_applied_for"));// 13
						alist.add(rs.getString("permission_from_central_gov"));// 14
						alist.add(rs.getString("admission_intake"));// 15
						alist.add(rs.getString("num_of_student_admitted"));// 16
						alist.add(rs.getString("remarks"));// 17
						list.add(alist);
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
			public ArrayList<ArrayList<String>> getviewdataugbidPDF(String id) {
				ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			System.err.println(list+"--C-in daoimpl-ppppdddd---"+id);
			try {
				
				conn = dataSource.getConnection();
				
				q="select id,student_name,date_of_admission,rank,marks,all_india,"
						+ "state,admission_authority,court_order,date_of_enroll_university,\r\n"
						+ "uni_enroll_number,date_of_intern_compl,remarks_form_b\r\n"
						+ " from dg_rec_admitted_student_child \r\n"
						+ "where id=? ";
				
				PreparedStatement stmt = conn.prepareStatement(q);
				System.err.println("-pppp-stmt---"+stmt);
				stmt.setInt(1,Integer.parseInt(id));
		
					ResultSet rs = stmt.executeQuery();
					
				

					ResultSetMetaData metaData = rs.getMetaData();
					int columnCount = metaData.getColumnCount();

					while (rs.next()) {
						
						ArrayList<String> alist = new ArrayList<String>();
						alist.add(rs.getString("id"));// 0
						alist.add(rs.getString("student_name"));// 1
						alist.add(rs.getString("date_of_admission"));// 2
						alist.add(rs.getString("rank"));// 3
						alist.add(rs.getString("marks"));// 4
						alist.add(rs.getString("all_india"));// 5
						alist.add(rs.getString("state"));// 6
						alist.add(rs.getString("admission_authority"));// 7
						alist.add(rs.getString("court_order"));// 8
						alist.add(rs.getString("date_of_enroll_university"));// 9
						alist.add(rs.getString("uni_enroll_number"));// 10
						alist.add(rs.getString("date_of_intern_compl"));// 11
						alist.add(rs.getString("remarks_form_b"));// 12
						list.add(alist);
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
			public ArrayList<ArrayList<String>> getviewdataugcidPDF(String id) {
				ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			System.err.println(list+"--C-in daoimpl-prnv---"+id);
			try {
				
				conn = dataSource.getConnection();
				
				q="select id,name_homoeopathic_medical_clg,attached_homoeopath_hospital,super_speciality_hospital,mou_date,\r\n"
						+ "copy_of_mou,name_of_hospital_staff,designation,qualification,fulltime_parttime,remarks_form_c from dg_rec_hospital_attached_child \r\n"
						+ "where id=? ";
				
				PreparedStatement stmt = conn.prepareStatement(q);
				System.err.println("-dp-stmt---"+stmt);
				stmt.setInt(1,Integer.parseInt(id));
		
					ResultSet rs = stmt.executeQuery();
					
					System.err.println("-dpaaa-stmt---"+stmt);

					ResultSetMetaData metaData = rs.getMetaData();
					int columnCount = metaData.getColumnCount();

					while (rs.next()) {
						
						ArrayList<String> alist = new ArrayList<String>();
						alist.add(rs.getString("id"));// 0
						alist.add(rs.getString("name_homoeopathic_medical_clg"));// 1
						alist.add(rs.getString("attached_homoeopath_hospital"));// 2
						alist.add(rs.getString("super_speciality_hospital"));// 3
						alist.add(rs.getString("mou_date"));// 4
						alist.add(rs.getString("copy_of_mou"));// 5
						alist.add(rs.getString("name_of_hospital_staff"));// 6
						alist.add(rs.getString("designation"));// 7
						alist.add(rs.getString("qualification"));// 8
						alist.add(rs.getString("fulltime_parttime"));// 9
						alist.add(rs.getString("remarks_form_c"));// 10
						list.add(alist);
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
			public ArrayList<ArrayList<String>> getviewdataugdtoidPDF(String id) {
				ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			System.err.println(list+"--C-in daoimpl-abcd---"+id);
			try {
				
				conn = dataSource.getConnection();
				
				q="select id,student_name_to_migrated,migrated_dt_to,professional_year_migrated,\r\n"
						+ "university_enrollment_number,remarks_form_d from dg_rec_migrated_student_sub_child \r\n"
						+ "where id=? ";
				
				PreparedStatement stmt = conn.prepareStatement(q);
				System.err.println("-dddpppp-stmt---"+stmt);
				stmt.setInt(1,Integer.parseInt(id));
		
					ResultSet rs = stmt.executeQuery();
					

					ResultSetMetaData metaData = rs.getMetaData();
					int columnCount = metaData.getColumnCount();

					while (rs.next()) {
						
						ArrayList<String> alist = new ArrayList<String>();
						alist.add(rs.getString("id"));// 0
						alist.add(rs.getString("student_name_to_migrated"));// 1
						alist.add(rs.getString("migrated_dt_to"));// 2
						alist.add(rs.getString("professional_year_migrated"));// 3
						alist.add(rs.getString("university_enrollment_number"));// 4
						alist.add(rs.getString("remarks_form_d"));// 5
						list.add(alist);
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
			public ArrayList<ArrayList<String>> getviewdataugdfromidPDF(String id) {
				ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			System.err.println(list+"--C-in daoimpl-abcd---"+id);
			try {
				
				conn = dataSource.getConnection();
				
				q="select id,name_of_students_migrated,dt_of_migration,professional_year,\r\n"
						+ "university_enroll_num,remarks_migrated from dg_rec_migrated_student_from_child \r\n"
						+ "where id=? ";
				
				PreparedStatement stmt = conn.prepareStatement(q);
				System.err.println("-dddpppp-stmt---"+stmt);
				stmt.setInt(1,Integer.parseInt(id));
		
					ResultSet rs = stmt.executeQuery();
					

					ResultSetMetaData metaData = rs.getMetaData();
					int columnCount = metaData.getColumnCount();

					while (rs.next()) {
						
						ArrayList<String> alist = new ArrayList<String>();
						alist.add(rs.getString("id"));// 0
						alist.add(rs.getString("name_of_students_migrated"));// 1
						alist.add(rs.getString("dt_of_migration"));// 2
						alist.add(rs.getString("professional_year"));// 3
						alist.add(rs.getString("university_enroll_num"));// 4
						alist.add(rs.getString("remarks_migrated"));// 5
						list.add(alist);
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
			public ArrayList<ArrayList<String>> getviewdataugeidPDF(String id) {
				ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			System.err.println(list+"--C-in daoimpl-abcdefghi---"+id);
			try {
				
				conn = dataSource.getConnection();
				
				q="select id,name_of_examiners,subject_examiners,year_examiners,qualification_examiners,\r\n"
						+ "teaching_experience,teacher_code,reg_number,d_university_appointment from dg_rec_examiners_appointed_child \r\n"
						+ "where id=? ";
				
				PreparedStatement stmt = conn.prepareStatement(q);
				System.err.println("-dddppppaaaa-stmt---"+stmt);
				stmt.setInt(1,Integer.parseInt(id));
		
					ResultSet rs = stmt.executeQuery();
					

					ResultSetMetaData metaData = rs.getMetaData();
					int columnCount = metaData.getColumnCount();

					while (rs.next()) {
						
						ArrayList<String> alist = new ArrayList<String>();
						alist.add(rs.getString("id"));// 0
						alist.add(rs.getString("name_of_examiners"));// 1
						alist.add(rs.getString("subject_examiners"));// 2
						alist.add(rs.getString("year_examiners"));// 3
						alist.add(rs.getString("qualification_examiners"));// 4
						alist.add(rs.getString("teaching_experience"));// 5
						alist.add(rs.getString("teacher_code"));// 6
						alist.add(rs.getString("reg_number"));// 7
						alist.add(rs.getString("d_university_appointment"));// 8
						list.add(alist);
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
			public ArrayList<ArrayList<String>> getviewdataufeidPDF(String id) {
				ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			System.err.println(list+"--C-in daoimpl-abcd---"+id);
			try {
				
				conn = dataSource.getConnection();
				
				q="select id,name_of_students,year_of_admission,date_of_result_final_year,provisional_reg_no,\r\n"
						+ "year_of_provisional_reg,date_of_starting_internship,date_of_completion_internship,remark_form_f from dg_rec_intern_student_course_child \r\n"
						+ "where id=? ";
				
				PreparedStatement stmt = conn.prepareStatement(q);
				System.err.println("-dddpppp-stmt---"+stmt);
				stmt.setInt(1,Integer.parseInt(id));
		
					ResultSet rs = stmt.executeQuery();
					

					ResultSetMetaData metaData = rs.getMetaData();
					int columnCount = metaData.getColumnCount();

					while (rs.next()) {
						
						ArrayList<String> alist = new ArrayList<String>();
						alist.add(rs.getString("id"));// 0
						alist.add(rs.getString("name_of_students"));// 1
						alist.add(rs.getString("year_of_admission"));// 2
						alist.add(rs.getString("date_of_result_final_year"));// 3
						alist.add(rs.getString("provisional_reg_no"));// 4
						alist.add(rs.getString("year_of_provisional_reg"));// 5
						alist.add(rs.getString("date_of_starting_internship"));// 6
						alist.add(rs.getString("date_of_completion_internship"));// 7
						alist.add(rs.getString("remark_form_f"));// 8
						list.add(alist);
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
//----------------------------------TRSCK STATUS-------------------------------------
			
			@Override
			public List<Map<String, Object>> DataTable_All_DataList(int startPage, int pageLength, String search,
					String orderColunm, String orderType, String userId, String institute_status) {//String uni_status,String commi_status
				
//				String SearchValue = GenerateQueryWhereClause_SQL(Search,userId, state_status);
				
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Connection conn = null;
				String q = "";
				try {
					
					conn = dataSource.getConnection();
					
				        q="select ts.month_year,li.login_name as inst_name,ls.university_name as uni_name  from  dg_rec_track_status ts\r\n"
				        		+ "inner join logininformation li on li.userid= ts.institute_id\r\n"
				        		+ "inner join edu_lms_university_mstr ls on ls.id= ts.university_id\r\n"
				        		+ " Where inst_status=?";
				        		
				
					
					PreparedStatement stmt = conn.prepareStatement(q);
					
					System.err.println("=-----------------hhhhh--stmt==--jjjjjj--"+stmt);
					
//					stmt = setQueryWhereClause_SQL(stmt,Search,userId,state_status);
				
				    stmt.setInt(1, Integer.parseInt(institute_status));
					
//					 stmt.setInt(2, Integer.parseInt(uni_status)); 
//					 stmt.setInt(3,Integer.parseInt(commi_status));
					 
				    
					ResultSet rs = stmt.executeQuery();
					ResultSetMetaData metaData = rs.getMetaData();
					System.err.println("=-------------------stmt==--jjjjjj--"+stmt);
					int columnCount = metaData.getColumnCount();
					int j = startPage;
					
					while (rs.next()) {
						
//			String status="";
//			if(rs.getInt("inst_status") == 0 ){
//				 status = "SAVE AS DRAFT";
//			}else if(rs.getInt("inst_status") == 1 ) {
//				 status = "APPROVED";
//			}else if (rs.getInt("inst_status") == -1 ) {
//				 status = "REJECTED ";
//			}
//						
						Map<String, Object> columns = new LinkedHashMap<String, Object>();
						columns.put("ser", ++j);
//						columns.put("status",status);
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
			
//			@Override
//			public long DataTable_Student_DataTotalCount(String search, String userId, String institute_status,
//					String choose_role) {
//				// TODO Auto-generated method stub
//				return 0;
//			}
			
			@Override
			public long DataTable_Student_DataTotalCount(String search, String userId, String institute_status) {
				
				
				int total = 0;
				Connection conn = null;
				String q = "";
				try {
					conn = dataSource.getConnection();			
					
					q="select count(*) from (select ts.month_year,li.login_name as inst_name,ls.university_name as uni_name  from  dg_rec_track_status ts\r\n"
							+ "inner join logininformation li on li.userid= ts.institute_id\r\n"
							+ "inner join edu_lms_university_mstr ls on ls.id= ts.university_id\r\n"
							+ "Where inst_status=?) a"; // a.inst_status=1 and a.university_approved_status=1 and a.council_approved_status=0
							

					PreparedStatement stmt = conn.prepareStatement(q);
					
					  stmt.setInt(1, Integer.parseInt(institute_status));
						/*
						 * stmt.setInt(2, Integer.parseInt(uni_status)); stmt.setInt(3,
						 * Integer.parseInt(commi_status));
						 */
					
//					    System.err.println("NCH_AP_6_COUNT==="+stmt);
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
			public ArrayList<ArrayList<String>> data_get_track_status(String userId, String institute_status,
					String choose_role) {
				ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();	
				Connection conn = null;
				String q="";
				try{
						conn = dataSource.getConnection();			 
						PreparedStatement stmt=null;
						q= "select dg.inst_status from dg_rec_track_status dg where dg.university_id=?";
						stmt=conn.prepareStatement(q);
						 stmt.setInt(1, Integer.parseInt(userId));
						 					  
						 ResultSet rs = stmt.executeQuery();  
						 while (rs.next()) {
							 ArrayList<String> alist = new ArrayList<String>();
						    	  alist.add(rs.getString("inst_status")); //0 
//						    	  alist.add(rs.getString("state_status")); //1
//						    	  alist.add(rs.getString("p_id")); //2
						    	 
						    	  list.add(alist);
			 	        }
						 System.err.println("---q---kkk--"+stmt);
				      rs.close();
				      stmt.close();
				      conn.close();
				   }catch (SQLException e) {
						
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
