package com.AyushEdu.dao.Degree_recognition_form_B;

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

import com.AyushEdu.Models.LMS_Institute.EDU_LMS_INSTITUTE_REGISTRATION;
@Repository

public class Degree_reco_council_Pg_DaoImpl implements Degree_reco_council_pg_Dao{

	@Autowired
	private DataSource dataSource;
	@Autowired
	private SessionFactory sessionFactory;
	
	//---------------------- PG Form A ---------------------//
	
	public List<Map<String, Object>> getFilter_PG_alist(int startPage, int pageLength, String search,
			String orderColunm, String orderType, String university_id, String uni_id, String institute_id, String institute_status,String name_of_applicant_university,String postgraduate_course, String abbre_postgraduate_course,String academic_session,String name_of_college,
			String country,String state,String district,String city,String postal_address,String email,String website,
			String academic_year_applied_for,String permission_from_central_gov,String admission_intake,String num_of_student_admitted,
			String remarks,String reject_remarks) {
		
		String SearchValue = GenerateQueryWhereClause_SQL(search,  name_of_applicant_university, postgraduate_course,  abbre_postgraduate_course, academic_session,
				 name_of_college, country, state, district, city, postal_address, email, website,
				 academic_year_applied_for, permission_from_central_gov, admission_intake, num_of_student_admitted,
				 remarks, reject_remarks);
		
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Connection conn = null;
				String q = "";
				
				try {
						conn = dataSource.getConnection();
			
						 q="select u.university_name,a.id,a.name_of_applicant_university,a.postgraduate_course,a.abbre_postgraduate_course,a.academic_session,a.name_of_college,a.country,a.state,a.district,\r\n"
						 		+ "a.city,a.postal_address,a.email,a.website,a.academic_year_applied_for,a.permission_from_central_gov,a.admission_intake,a.num_of_student_admitted,a.remarks\r\n"
						 		+ "from dg_rec_pg_form_b_parent a \r\n"
						 		+ "inner join edu_lms_university_mstr u on u.id=a.university_id \r\n"
						 		+ "inner join logininformation li on li.userid=a.user_id\r\n"
						 		+ "where a.university_id=? and a.council_approved_status=?" + SearchValue + " ORDER BY a.id "+ orderType + " limit "
								+ pageLength + " OFFSET " + startPage;
						 
						PreparedStatement stmt = conn.prepareStatement(q);
						stmt = setQueryWhereClause_SQL(stmt,search,name_of_applicant_university, postgraduate_course,  abbre_postgraduate_course, academic_session,
								 name_of_college, country, state, district, city, postal_address, email, website,
								 academic_year_applied_for, permission_from_central_gov, admission_intake, num_of_student_admitted,
								 remarks, reject_remarks);
						stmt.setInt(1, Integer.parseInt(uni_id));
						stmt.setInt(2, Integer.parseInt(institute_status));
						ResultSet rs = stmt.executeQuery();
					
						ResultSetMetaData metaData = rs.getMetaData();
						int j = startPage;
						int columnCount = metaData.getColumnCount();
						
						int countFunction = 1;
						int countFunctionrej = 1;
						
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
						
						f ="<li><a class='main-btn active-btn-outline btn-hover btn-sm ADDPG' data-bs-toggle='modal' data-bs-target='#pgamodal' value='VIEW DETAILS'  title='VIEW DETAILS' >" 
								+ "<i class='lni lni-eye'>"
								+"<input type='hidden' id='apIdpg"+countFunction+"' value="+rs.getString("id")+">"
							    +"</i></a></li>";
						
						
//						f1  ="<li><a class='main-btn danger-btn-outline btn-hover btn-sm clarificationformaData' data-bs-toggle='modal' data-bs-target='myModal'  value='ADD' title='Clarification Data' >" //id='id_add_attHospital1'
//								 +"<input type='hidden' id='RejectformaId"+countFunctionrej+"' value="+rs.getString("id")+"></i></a></li>"
//							    +"<i class='lni lni-close'></i></a></li>";
						
						f1 = "<li><a class='main-btn danger-btn-outline btn-hover btn-sm clarificationformaData' value='REJECT' title='Clarification Data'><i class='lni lni-close'>"
								+"<input type='hidden' id='RejectformaId"+countFunctionrej+"' value="+rs.getString("id")+">"
								+ "</i></a></li>";
						
						ul+=f + " " ;
						ul+="</ul>";
						
						action = ul;
						countFunction+=1;
						countFunctionrej+=1;
						columns.put("f1", f1);
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
	public long getFilter_PG_aListCount(String search, String orderColunm,
			String orderType, String university_id, String uni_id, String institute_id, String institute_status,String name_of_applicant_university,String postgraduate_course, String abbre_postgraduate_course,String academic_session,String name_of_college,
			String country,String state,String district,String city,String postal_address,String email,String website,
			String academic_year_applied_for,String permission_from_central_gov,String admission_intake,String num_of_student_admitted,
			String remarks,String reject_remarks) {
		
//		String SearchValue = GenerateQueryWhereClause_SQL(search,  name_of_applicant_university, postgraduate_course,  abbre_postgraduate_course, academic_session,
//				 name_of_college, country, state, district, city, postal_address, email, website,
//				 academic_year_applied_for, permission_from_central_gov, admission_intake, num_of_student_admitted,
//				 remarks, reject_remarks);
		
		int total = 0;
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();			
			
			q="select count(*) from (select u.university_name,a.id,a.name_of_applicant_university,a.postgraduate_course,a.abbre_postgraduate_course,a.academic_session,a.name_of_college,a.country,a.state,a.district,\r\n"
					+ "a.city,a.postal_address,a.email,a.website,a.academic_year_applied_for,a.permission_from_central_gov,a.admission_intake,a.num_of_student_admitted,a.remarks\r\n"
					+ "from dg_rec_pg_form_b_parent a \r\n"
					+ "inner join edu_lms_university_mstr u on u.id=a.university_id \r\n"
					+ "inner join logininformation li on li.userid=a.user_id\r\n"
					+ "where a.university_id=? and a.council_approved_status=?) a";
					

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,search,name_of_applicant_university, postgraduate_course,  abbre_postgraduate_course, academic_session,
					 name_of_college, country, state, district, city, postal_address, email, website,
					 academic_year_applied_for, permission_from_central_gov, admission_intake, num_of_student_admitted,
					 remarks, reject_remarks);
			    System.err.println("---acount-"+stmt);
			    stmt.setInt(1, Integer.parseInt(uni_id));
				stmt.setInt(2, Integer.parseInt(institute_status));
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
	
//	@SuppressWarnings("null")
	public String GenerateQueryWhereClause_SQL(String Search,String name_of_applicant_university,String postgraduate_course, String abbre_postgraduate_course,String academic_session,String name_of_college,
			String country,String state,String district,String city,String postal_address,String email,String website,
			String academic_year_applied_for,String permission_from_central_gov,String admission_intake,String num_of_student_admitted,
			String remarks,String reject_remarks) {
		String SearchValue="" ;
		
		if (Search!=null && !Search.equals("")) { // for Input Filter
			SearchValue += " and (upper(name_of_applicant_university) Like ? "
					+ " or upper(postgraduate_course) Like ? "
					+ " or upper(abbre_postgraduate_course) Like ? "
					+ " or upper(academic_session) Like ?"
					+ " or upper(name_of_college) Like ? "
					+ " or upper(country) Like ? "
					+ " or upper(state) Like ? "
					+ " or upper(district) Like ?"
					+ " or upper(city) Like ? "
					+ " or upper(postal_address) Like ? "
					+ " or upper(email) Like ?"
					+ " or upper(website) Like ? "
					+ " or upper(academic_year_applied_for) Like ? "
					+ " or upper(permission_from_central_gov) Like ? "
					+ " or admission_intake::character varying like ? "
					+ " or num_of_student_admitted::character varying like ? "
					+ " or upper(remarks) Like ? "
					+ " or upper(reject_remarks) Like ?) ";
		}
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search,String name_of_applicant_university,String postgraduate_course,
			String abbre_postgraduate_course,String academic_session,String name_of_college,
			String country,String state,String district,String city,String postal_address,String email,String website,
			String academic_year_applied_for,String permission_from_central_gov,String admission_intake,String num_of_student_admitted,
			String remarks,String reject_remarks) {
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
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				
			}
			if (name_of_applicant_university != null && !name_of_applicant_university.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+name_of_applicant_university.toUpperCase()+"%");
			}
			if (postgraduate_course != null && !postgraduate_course.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+postgraduate_course.toUpperCase()+"%");
			}
			if (abbre_postgraduate_course != null && !abbre_postgraduate_course.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+abbre_postgraduate_course.toUpperCase()+"%");
			}
			if (academic_session != null && !academic_session.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+academic_session.toUpperCase()+"%");
			}
			if (name_of_applicant_university != null && !name_of_applicant_university.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+name_of_applicant_university.toUpperCase()+"%");
			}
			if (name_of_college != null && !name_of_college.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+name_of_college.toUpperCase()+"%");
			}
			if (country != null && !country.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+country.toUpperCase()+"%");
			}
			if (state != null && !state.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+state.toUpperCase()+"%");
			}
			if (district != null && !district.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+district.toUpperCase()+"%");
			}
			if (city != null && !city.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+city.toUpperCase()+"%");
			}
			if (postal_address != null && !postal_address.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+postal_address.toUpperCase()+"%");
			}
			if (email != null && !email.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+email.toUpperCase()+"%");
			}
			if (website != null && !website.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+website.toUpperCase()+"%");
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
				stmt.setInt(flag,Integer.parseInt(admission_intake));
			}
			if (num_of_student_admitted != null && !num_of_student_admitted.equals("")) {
				flag += 1;
				stmt.setInt(flag,Integer.parseInt(num_of_student_admitted));
			}
			if (remarks != null && !remarks.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+remarks.toUpperCase()+"%");
			}
			if (reject_remarks != null && !reject_remarks.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+reject_remarks.toUpperCase()+"%");
			}

		} catch (Exception e) {
		}

		return stmt;
	}
	
	//---------------------- PG Form B---------------------//
	
		public List<Map<String, Object>> getFilter_PG_blist(int startPage, int pageLength, String search,
				String orderColunm, String orderType, String university_id, String uni_id, String institute_id,
				String institute_status,String student_name,String date_of_admission,
				String date_of_registration,String course_name,String rank,String marks,String all_india,String state,String admission_authority,String court_order,
				String qualification_name,String year_of_award_admission,String date_of_registration_state,String date_of_completion_md_part1,
				String date_of_completion_md_part2,String date_of_declaration_of_md,String date_of_completion_internship,String remarks,
				String reject_remarks) {
			
			String SearchValue = GenerateQueryWhereClause_SQL1(search,  student_name, date_of_admission,
					 date_of_registration, course_name, rank, marks, all_india, state, admission_authority, court_order,
					 qualification_name, year_of_award_admission, date_of_registration_state, date_of_completion_md_part1,
					 date_of_completion_md_part2, date_of_declaration_of_md, date_of_completion_internship, remarks,
					 reject_remarks);
			
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					Connection conn = null;
					String q = "";
					
					try {
							conn = dataSource.getConnection();
				
							q="select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.student_name,to_char(a.date_of_admission,'DD/MON/YYYY') as date_of_admission,"
							 		+ "to_char(a.date_of_registration,'DD/MON/YYYY') as date_of_registration,a.course_name,a.rank,a.marks,a.all_india,a.state,\r\n"
							 		+ "a.admission_authority,a.court_order,a.qualification_name,a.year_of_award_admission,"
							 		+ "to_char(a.date_of_registration_state,'DD/MON/YYYY') as date_of_registration_state,"
							 		+ "to_char(a.date_of_completion_md_part1,'DD/MON/YYYY') as date_of_completion_md_part1,"
							 		+ "to_char(a.date_of_completion_md_part2,'DD/MON/YYYY') as date_of_completion_md_part2,"
							 		+ "to_char(a.date_of_declaration_of_md,'DD/MON/YYYY') as date_of_declaration_of_md,"
							 		+ "to_char(a.date_of_completion_internship,'DD/MON/YYYY') as date_of_completion_internship,a.remarks \r\n"
							 		+ "from dg_rec_student_admitted_child_b a \r\n"
							 		+ "inner join edu_lms_university_mstr u on u.id=a.university_id \r\n"
							 		+ "inner join logininformation li on li.userid=a.user_id\r\n"
							 		+ "where a.university_id=? and a.council_approved_status=? and li.institute_id=?"+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
									+ pageLength + " OFFSET " + startPage;
							 
							PreparedStatement stmt = conn.prepareStatement(q);
							stmt = setQueryWhereClause_SQL1(stmt,search, student_name, date_of_admission,
									 date_of_registration, course_name, rank, marks, all_india, state, admission_authority, court_order,
									 qualification_name, year_of_award_admission, date_of_registration_state, date_of_completion_md_part1,
									 date_of_completion_md_part2, date_of_declaration_of_md, date_of_completion_internship, remarks,
									 reject_remarks);
							stmt.setInt(1, Integer.parseInt(uni_id));
							stmt.setInt(2, Integer.parseInt(institute_status));
							stmt.setInt(3, Integer.parseInt(institute_id));
							ResultSet rs = stmt.executeQuery();
						
							ResultSetMetaData metaData = rs.getMetaData();
							int j = startPage;
							int columnCount = metaData.getColumnCount();
							
							int countFunction = 1;
							int countFunctionrej = 1;
							
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
							
							f ="<li><a class='main-btn active-btn-outline btn-hover btn-sm ADDPGb' data-bs-toggle='modal' data-bs-target='#pgbmodal' value='VIEW DETAILS'  title='VIEW DETAILS' >" 
									+ "<i class='lni lni-eye'>"
									+"<input type='hidden' id='apIdbpg"+countFunction+"' value="+rs.getString("id")+">"
								    +"</i></a></li>";
							
							
//							f1  ="<li><a class='main-btn danger-btn-outline btn-hover btn-sm clarificationformaData' data-bs-toggle='modal' data-bs-target='myModal'  value='ADD' title='Clarification Data' >" //id='id_add_attHospital1'
//									 +"<input type='hidden' id='RejectformaId"+countFunctionrej+"' value="+rs.getString("id")+"></i></a></li>"
//								    +"<i class='lni lni-close'></i></a></li>";
							
							f1 = "<li><a class='main-btn danger-btn-outline btn-hover btn-sm clarificationformbData' value='REJECT' title='Clarification Data'><i class='lni lni-close'>"
									+"<input type='hidden' id='RejectformbId"+countFunctionrej+"' value="+rs.getString("id")+">"
									+ "</i></a></li>";
							
							ul+=f + " " ;
							ul+="</ul>";
							
							action = ul;
							countFunction+=1;
							countFunctionrej+=1;
							columns.put("f1", f1);
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
		public long getFilter_PG_bListCount(String search, String orderColunm,
				String orderType, String university_id, String uni_id, String institute_id, String institute_status,String student_name,String date_of_admission,
				String date_of_registration,String course_name,String rank,String marks,String all_india,String state,String admission_authority,String court_order,
				String qualification_name,String year_of_award_admission,String date_of_registration_state,String date_of_completion_md_part1,
				String date_of_completion_md_part2,String date_of_declaration_of_md,String date_of_completion_internship,String remarks,
				String reject_remarks) {
			
			String SearchValue = GenerateQueryWhereClause_SQL1(search,  student_name, date_of_admission,
					 date_of_registration, course_name, rank, marks, all_india, state, admission_authority, court_order,
					 qualification_name, year_of_award_admission, date_of_registration_state, date_of_completion_md_part1,
					 date_of_completion_md_part2, date_of_declaration_of_md, date_of_completion_internship, remarks,
					 reject_remarks);
			
			int total = 0;
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();			
				
				q="select count(*) from (select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.student_name,to_char(a.date_of_admission,'DD/MON/YYYY') as date_of_admission,\r\n"
						+ "to_char(a.date_of_registration,'DD/MON/YYYY') as date_of_registration,a.course_name,a.rank,a.marks,a.all_india,a.state,\r\n"
						+ "a.admission_authority,a.court_order,a.qualification_name,a.year_of_award_admission,\r\n"
						+ "to_char(a.date_of_registration_state,'DD/MON/YYYY') as date_of_registration_state,\r\n"
						+ "to_char(a.date_of_completion_md_part1,'DD/MON/YYYY') as date_of_completion_md_part1,\r\n"
						+ "to_char(a.date_of_completion_md_part2,'DD/MON/YYYY') as date_of_completion_md_part2,\r\n"
						+ "to_char(a.date_of_declaration_of_md,'DD/MON/YYYY') as date_of_declaration_of_md,\r\n"
						+ "to_char(a.date_of_completion_internship,'DD/MON/YYYY') as date_of_completion_internship,a.remarks \r\n"
						+ "from dg_rec_student_admitted_child_b a \r\n"
						+ "inner join edu_lms_university_mstr u on u.id=a.university_id \r\n"
						+ "inner join logininformation li on li.userid=a.user_id\r\n"
						+ "where a.university_id=? and a.council_approved_status=?  and li.institute_id=?) a";
						

				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL1(stmt,search, student_name, date_of_admission,
						 date_of_registration, course_name, rank, marks, all_india, state, admission_authority, court_order,
						 qualification_name, year_of_award_admission, date_of_registration_state, date_of_completion_md_part1,
						 date_of_completion_md_part2, date_of_declaration_of_md, date_of_completion_internship, remarks,
						 reject_remarks);
				    System.err.println("---bcount-"+stmt);
				    stmt.setInt(1, Integer.parseInt(uni_id));
					stmt.setInt(2, Integer.parseInt(institute_status));
					stmt.setInt(3, Integer.parseInt(institute_id));
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
		public String GenerateQueryWhereClause_SQL1(String Search,String student_name,String date_of_admission,
				String date_of_registration,String course_name,String rank,String marks,String all_india,String state,String admission_authority,String court_order,
				String qualification_name,String year_of_award_admission,String date_of_registration_state,String date_of_completion_md_part1,
				String date_of_completion_md_part2,String date_of_declaration_of_md,String date_of_completion_internship,String remarks,
				String reject_remarks) {
			
			String SearchValue="" ;
			
			if (Search!=null && !Search.equals("")) { // for Input Filter
				SearchValue += " and (upper(student_name) Like ? or TO_CHAR(date_of_admission , 'dd/MM/yyyy') like ? "
						+" or TO_CHAR(date_of_registration , 'dd/MM/yyyy') like ? or upper(course_name) Like ? "
						+ " or rank::character varying like ? or marks::character varying like ? or upper(all_india) Like ? "
						+ " or upper(state) Like ? or upper(admission_authority) Like ? or upper(court_order) Like ? or upper(qualification_name) Like ? "
						+ " or upper(year_of_award_admission) Like ? or TO_CHAR(date_of_registration_state , 'dd/MM/yyyy') like ? "
						+ " or TO_CHAR(date_of_completion_md_part1 , 'dd/MM/yyyy') like ? or TO_CHAR(date_of_completion_md_part2 , 'dd/MM/yyyy') like ? "
						+ " or TO_CHAR(date_of_declaration_of_md , 'dd/MM/yyyy') like ? or TO_CHAR(date_of_completion_internship , 'dd/MM/yyyy') like ? "
						+ " or upper(remarks) Like ? or upper(reject_remarks) Like ?) ";
			}
			return SearchValue;
		}
		
		public PreparedStatement setQueryWhereClause_SQL1(PreparedStatement stmt, String Search,String student_name,String date_of_admission,
				String date_of_registration,String course_name,String rank,String marks,String all_india,String state,String admission_authority,String court_order,
				String qualification_name,String year_of_award_admission,String date_of_registration_state,String date_of_completion_md_part1,
				String date_of_completion_md_part2,String date_of_declaration_of_md,String date_of_completion_internship,String remarks,
				String reject_remarks) {
			int flag = 3;
			try {
				if (Search!=null &&  !Search.equals("")) {
					
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search + "%");
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
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					
				}
				if (student_name != null && !student_name.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+student_name.toUpperCase()+"%");
				}
				if( date_of_admission != null && !date_of_admission.equals("") && !date_of_admission.equals("DD/MM/YYYY")) {
					flag += 1;
					stmt.setString(flag, date_of_admission );
				}
				if( date_of_registration != null && !date_of_registration.equals("") && !date_of_registration.equals("DD/MM/YYYY")) {
					flag += 1;
					stmt.setString(flag, date_of_registration );
				}
				if (course_name != null && !course_name.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+course_name.toUpperCase()+"%");
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
				if (qualification_name != null && !qualification_name.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+qualification_name.toUpperCase()+"%");
				}
				if (year_of_award_admission != null && !year_of_award_admission.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+year_of_award_admission.toUpperCase()+"%");
				}
				if( date_of_registration_state != null && !date_of_registration_state.equals("") && !date_of_registration_state.equals("DD/MM/YYYY")) {
					flag += 1;
					stmt.setString(flag, date_of_registration_state );
				}
				if( date_of_completion_md_part1 != null && !date_of_completion_md_part1.equals("") && !date_of_completion_md_part1.equals("DD/MM/YYYY")) {
					flag += 1;
					stmt.setString(flag, date_of_completion_md_part1 );
				}
				if( date_of_completion_md_part2 != null && !date_of_completion_md_part2.equals("") && !date_of_completion_md_part2.equals("DD/MM/YYYY")) {
					flag += 1;
					stmt.setString(flag, date_of_completion_md_part2 );
				}
				if( date_of_declaration_of_md != null && !date_of_declaration_of_md.equals("") && !date_of_declaration_of_md.equals("DD/MM/YYYY")) {
					flag += 1;
					stmt.setString(flag, date_of_declaration_of_md );
				}
				if( date_of_completion_internship != null && !date_of_completion_internship.equals("") && !date_of_completion_internship.equals("DD/MM/YYYY")) {
					flag += 1;
					stmt.setString(flag, date_of_completion_internship );
				}
				if (remarks != null && !remarks.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+remarks.toUpperCase()+"%");
				}
				if (reject_remarks != null && !reject_remarks.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+reject_remarks.toUpperCase()+"%");
				}

			} catch (Exception e) {
			}

			return stmt;
		}
		
	//---------------------- PG Form C ---------------------//
	
		public List<Map<String, Object>> getFilter_PG_clist(int startPage, int pageLength, String search,
				String orderColunm, String orderType, String university_id, String uni_id, String institute_id, String institute_status,String name_of_college_pg,String name_of_teaching_staff, String phone,String email_id,String designation,String department,
				String registration_no,String date_of_reg,String date_of_birth,String qualification_awarding_authority,String year_of_award,String date_of_appointment,
				String fulltime_parttime,String post_teaching,String exp_from,String exp_to,String total_teaching_exp_in_year,String reject_remarks) {
			
			String SearchValue = GenerateQueryWhereClause_SQL2(search,  name_of_college_pg, name_of_teaching_staff,  phone, email_id, designation, department,
					 registration_no, date_of_reg, date_of_birth, qualification_awarding_authority, year_of_award, date_of_appointment,
					 fulltime_parttime, post_teaching, exp_from, exp_to, total_teaching_exp_in_year, reject_remarks);
			
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					Connection conn = null;
					String q = "";
					
					try {
							conn = dataSource.getConnection();
				
							 q="select u.university_name,a.id,a.name_of_college_pg,a.name_of_teaching_staff,a.phone,a.email_id,a.designation,a.department,a.registration_no,a.date_of_reg,\r\n"
							 		+ "a.date_of_birth,a.qualification_awarding_authority,a.year_of_award,a.date_of_appointment,a.fulltime_parttime,a.post_teaching,a.exp_from,a.exp_to,a.total_teaching_exp_in_year\r\n"
							 		+ "from dg_rec_teaching_staff_child_b a \r\n"
							 		+ "inner join edu_lms_university_mstr u on u.id=a.university_id \r\n"
							 		+ "inner join logininformation li on li.userid=a.user_id\r\n"
							 		+ "where a.university_id=? and a.council_approved_status=?"+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
									+ pageLength + " OFFSET " + startPage;
							
							 
							 PreparedStatement stmt = conn.prepareStatement(q);
							 stmt = setQueryWhereClause_SQL2(stmt,search,name_of_college_pg, name_of_teaching_staff,  phone, email_id, designation, department,
									 registration_no, date_of_reg, date_of_birth, qualification_awarding_authority, year_of_award, date_of_appointment,
									 fulltime_parttime, post_teaching, exp_from, exp_to, total_teaching_exp_in_year, reject_remarks);
								stmt.setInt(1, Integer.parseInt(uni_id));
								stmt.setInt(2, Integer.parseInt(institute_status));
								ResultSet rs = stmt.executeQuery();
							
								ResultSetMetaData metaData = rs.getMetaData();
								int j = startPage;
								int columnCount = metaData.getColumnCount();
								
								int countFunction = 1;
								int countFunctionrej = 1;
								
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
							
							f ="<li><a class='main-btn active-btn-outline btn-hover btn-sm ADDPGteaching' data-bs-toggle='modal' data-bs-target='#pgcmodel' value='VIEW DETAILS'  title='VIEW DETAILS' >" 
									+ "<i class='lni lni-eye'>"
									+"<input type='hidden' id='apIdTeaching"+countFunction+"' value="+rs.getString("id")+">"
								    +"</i></a></li>";
							
							f1 = "<li><a class='main-btn danger-btn-outline btn-hover btn-sm clarificationformcData' value='REJECT' title='Clarification Data'><i class='lni lni-close'>"
									+"<input type='hidden' id='RejectformcId"+countFunctionrej+"' value="+rs.getString("id")+">"
									+ "</i></a></li>";
							
							ul+=f + " " ;
							ul+="</ul>";
							
							action = ul;
							countFunction+=1;
							countFunctionrej+=1;
							columns.put("f1", f1);
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
		public long getFilter_PG_cListCount(String search,
				String orderColunm, String orderType, String university_id, String uni_id, String institute_id, String institute_status,String name_of_college_pg,String name_of_teaching_staff, String phone,String email_id,String designation,String department,
				String registration_no,String date_of_reg,String date_of_birth,String qualification_awarding_authority,String year_of_award,String date_of_appointment,
				String fulltime_parttime,String post_teaching,String exp_from,String exp_to,String total_teaching_exp_in_year,String reject_remarks) {
			
			String SearchValue = GenerateQueryWhereClause_SQL2(search,  name_of_college_pg, name_of_teaching_staff,  phone, email_id, designation, department,
					 registration_no, date_of_reg, date_of_birth, qualification_awarding_authority, year_of_award, date_of_appointment,
					 fulltime_parttime, post_teaching, exp_from, exp_to, total_teaching_exp_in_year, reject_remarks);
			
			int total = 0;
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();			
				
				q="select count(*) from (select u.university_name,a.id,a.name_of_applicant_university,a.postgraduate_course,a.abbre_postgraduate_course,a.academic_session,a.name_of_college,a.country,a.state,a.district,\r\n"
						+ "a.city,a.postal_address,a.email,a.website,a.academic_year_applied_for,a.permission_from_central_gov,a.admission_intake,a.num_of_student_admitted,a.remarks\r\n"
						+ "from dg_rec_pg_form_b_parent a \r\n"
						+ "inner join edu_lms_university_mstr u on u.id=a.university_id \r\n"
						+ "where a.university_id=? and a.council_approved_status=?) a";
						

				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL2(stmt,search,name_of_college_pg, name_of_teaching_staff,  phone, email_id, designation, department,
						 registration_no, date_of_reg, date_of_birth, qualification_awarding_authority, year_of_award, date_of_appointment,
						 fulltime_parttime, post_teaching, exp_from, exp_to, total_teaching_exp_in_year, reject_remarks);
				
				   stmt.setInt(1, Integer.parseInt(uni_id));
				  stmt.setInt(2, Integer.parseInt(institute_status));
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
		public String GenerateQueryWhereClause_SQL2(String Search,String name_of_college_pg,String name_of_teaching_staff, String phone,String email_id,String designation,String department,
				String registration_no,String date_of_reg,String date_of_birth,String qualification_awarding_authority,String year_of_award,String date_of_appointment,
				String fulltime_parttime,String post_teaching,String exp_from,String exp_to,String total_teaching_exp_in_year,String reject_remarks) {
			String SearchValue="" ;
			
			if (Search!=null && !Search.equals("")) { // for Input Filter
				SearchValue += " and (upper(name_of_applicant_university) Like ? "
						+ " or upper(postgraduate_course) Like ? "
						+ " or upper(abbre_postgraduate_course) Like ? "
						+ " or upper(academic_session) Like ?"
						+ " or upper(name_of_college) Like ? "
						+ " or upper(country) Like ? "
						+ " or upper(state) Like ? "
						+ " or upper(district) Like ?"
						+ " or upper(city) Like ? "
						+ " or upper(postal_address) Like ? "
						+ " or upper(email) Like ?"
						+ " or upper(website) Like ? "
						+ " or upper(academic_year_applied_for) Like ? "
						+ " or upper(permission_from_central_gov) Like ? "
						+ " or admission_intake::character varying like ? "
						+ " or num_of_student_admitted::character varying like ? "
						+ " or upper(remarks) Like ? "
						+ " or upper(reject_remarks) Like ?) ";
			}
			return SearchValue;
		}
		
		public PreparedStatement setQueryWhereClause_SQL2(PreparedStatement stmt, String Search,String name_of_college_pg,String name_of_teaching_staff,
				String phone,String email_id,String designation,String department,
				String registration_no,String date_of_reg,String date_of_birth,String qualification_awarding_authority,String year_of_award,String date_of_appointment,
				String fulltime_parttime,String post_teaching,String exp_from,String exp_to,String total_teaching_exp_in_year,String reject_remarks) {
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
					flag += 1;
					stmt.setString(flag, "%" + Search + "%");
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
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					
				}
				if (name_of_college_pg != null && !name_of_college_pg.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+name_of_college_pg.toUpperCase()+"%");
				}
				if (name_of_teaching_staff != null && !name_of_teaching_staff.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+name_of_teaching_staff.toUpperCase()+"%");
				}
				if (phone != null && !phone.equals("")) {
					flag += 1;
					stmt.setInt(flag,Integer.parseInt(phone));
				}
				if (email_id != null && !email_id.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+email_id.toUpperCase()+"%");
				}
				if (designation != null && !designation.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+designation.toUpperCase()+"%");
				}
				if (department != null && !department.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+department.toUpperCase()+"%");
				}
				if (registration_no != null && !registration_no.equals("")) {
					flag += 1;
					stmt.setInt(flag,Integer.parseInt(registration_no));
				}
				if( date_of_reg != null && !date_of_reg.equals("") && !date_of_reg.equals("DD/MM/YYYY")) {
					flag += 1;
					stmt.setString(flag, date_of_reg );
				}
				if( date_of_birth != null && !date_of_birth.equals("") && !date_of_birth.equals("DD/MM/YYYY")) {
					flag += 1;
					stmt.setString(flag, date_of_birth );
				}
				if (qualification_awarding_authority != null && !qualification_awarding_authority.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+qualification_awarding_authority.toUpperCase()+"%");
				}
				if (year_of_award != null && !year_of_award.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+year_of_award.toUpperCase()+"%");
				}
				if( date_of_appointment != null && !date_of_appointment.equals("") && !date_of_appointment.equals("DD/MM/YYYY")) {
					flag += 1;
					stmt.setString(flag, date_of_reg );
				}
				if (fulltime_parttime != null && !fulltime_parttime.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+fulltime_parttime.toUpperCase()+"%");
				}
				if (post_teaching != null && !post_teaching.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+post_teaching.toUpperCase()+"%");
				}
				if( exp_from != null && !exp_from.equals("") && !exp_from.equals("DD/MM/YYYY")) {
					flag += 1;
					stmt.setString(flag, exp_from );
				}
				if( exp_to != null && !exp_to.equals("") && !exp_to.equals("DD/MM/YYYY")) {
					flag += 1;
					stmt.setString(flag, exp_to );
				}
				if (total_teaching_exp_in_year != null && !total_teaching_exp_in_year.equals("")) {
					flag += 1;
					stmt.setInt(flag,Integer.parseInt(total_teaching_exp_in_year));
				}
				if (reject_remarks != null && !reject_remarks.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+reject_remarks.toUpperCase()+"%");
				}

			} catch (Exception e) {
			}

			return stmt;
		}

		//---------------------- PG Form D---------------------//
		
			public List<Map<String, Object>> getFilter_PG_dlist(int startPage, int pageLength, String search,
					String orderColunm, String orderType, String university_id, String uni_id, String institute_id,
					String institute_status,String name_of_homoeopathic_medical_college,
					String name_of_guide,String name_of_student_for_guide,String topic_of_dissertation,String whether_guide_participated_in_examination,
					String date_of_submission,String date_of_acceptance,String whether_student_published_article,String mention_details,
					String article_title,String month_year_exam,String reject_remarks) {
				
				String SearchValue = GenerateQueryWhereClause_SQL3(search,  name_of_homoeopathic_medical_college,
						 name_of_guide, name_of_student_for_guide, topic_of_dissertation, whether_guide_participated_in_examination,
						 date_of_submission, date_of_acceptance, whether_student_published_article, mention_details,
						 article_title, month_year_exam, reject_remarks);
				
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						Connection conn = null;
						String q = "";
						
						try {
								conn = dataSource.getConnection();
					
								 
								q="select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.name_of_homoeopathic_medical_college,a.name_of_guide,"
								 		+ "a.name_of_student_for_guide,a.topic_of_dissertation,a.whether_guide_participated_in_examination,\r\n"
								 		+ "to_char(a.date_of_submission,'DD/MON/YYYY') as date_of_submission,"
								 		+ "to_char(a.date_of_acceptance,'DD/MON/YYYY') as date_of_acceptance,"
								 		+ "a.whether_student_published_article,a.mention_details,a.article_title,a.month_year_exam \r\n"
								 		+ "from dg_rec_examiners_md_child_b a \r\n"
								 		+ "inner join edu_lms_university_mstr u on u.id=a.university_id \r\n"
								 		+ "inner join logininformation li on li.userid=a.user_id\r\n"
								 		+ "where a.university_id=? and a.council_approved_status=? and li.institute_id=?"+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
										+ pageLength + " OFFSET " + startPage;
								
								
								PreparedStatement stmt = conn.prepareStatement(q);
								stmt = setQueryWhereClause_SQL3(stmt,search,name_of_homoeopathic_medical_college,
										 name_of_guide, name_of_student_for_guide, topic_of_dissertation, whether_guide_participated_in_examination,
										 date_of_submission, date_of_acceptance, whether_student_published_article, mention_details,
										 article_title, month_year_exam, reject_remarks);
								stmt.setInt(1, Integer.parseInt(uni_id));
								stmt.setInt(2, Integer.parseInt(institute_status));
								stmt.setInt(3, Integer.parseInt(institute_id));
								ResultSet rs = stmt.executeQuery();
							
								ResultSetMetaData metaData = rs.getMetaData();
								int j = startPage;
								int columnCount = metaData.getColumnCount();
								
								int countFunction = 1;
								int countFunctionrej = 1;
								
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
								
								f ="<li><a class='main-btn active-btn-outline btn-hover btn-sm ADDPGd' data-bs-toggle='modal' data-bs-target='#pgdmodal' value='VIEW DETAILS'  title='VIEW DETAILS' >" 
										+ "<i class='lni lni-eye'>"
										+"<input type='hidden' id='apIddpg"+countFunction+"' value="+rs.getString("id")+">"
									    +"</i></a></li>";
								
								
//								f1  ="<li><a class='main-btn danger-btn-outline btn-hover btn-sm clarificationformaData' data-bs-toggle='modal' data-bs-target='myModal'  value='ADD' title='Clarification Data' >" //id='id_add_attHospital1'
//										 +"<input type='hidden' id='RejectformaId"+countFunctionrej+"' value="+rs.getString("id")+"></i></a></li>"
//									    +"<i class='lni lni-close'></i></a></li>";
								
								f1 = "<li><a class='main-btn danger-btn-outline btn-hover btn-sm clarificationformdData' value='REJECT' title='Clarification Data'><i class='lni lni-close'>"
										+"<input type='hidden' id='RejectformdId"+countFunctionrej+"' value="+rs.getString("id")+">"
										+ "</i></a></li>";
								
								ul+=f + " " ;
								ul+="</ul>";
								
								action = ul;
								countFunction+=1;
								countFunctionrej+=1;
								columns.put("f1", f1);
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
			public long getFilter_PG_dListCount(String search, String orderColunm,
					String orderType, String university_id, String uni_id, String institute_id, String institute_status,String name_of_homoeopathic_medical_college,
					String name_of_guide,String name_of_student_for_guide,String topic_of_dissertation,String whether_guide_participated_in_examination,
					String date_of_submission,String date_of_acceptance,String whether_student_published_article,String mention_details,
					String article_title,String month_year_exam,String reject_remarks) {
				
				String SearchValue = GenerateQueryWhereClause_SQL3(search,  name_of_homoeopathic_medical_college,
						 name_of_guide, name_of_student_for_guide, topic_of_dissertation, whether_guide_participated_in_examination,
						 date_of_submission, date_of_acceptance, whether_student_published_article, mention_details,
						 article_title, month_year_exam, reject_remarks);
				
				int total = 0;
				Connection conn = null;
				String q = "";
				try {
					conn = dataSource.getConnection();			
					
					q="select count(*) from (select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.student_name,to_char(a.date_of_admission,'DD/MON/YYYY') as date_of_admission,\r\n"
							+ "to_char(a.date_of_registration,'DD/MON/YYYY') as date_of_registration,a.course_name,a.rank,a.marks,a.all_india,a.state,\r\n"
							+ "a.admission_authority,a.court_order,a.qualification_name,a.year_of_award_admission,\r\n"
							+ "to_char(a.date_of_registration_state,'DD/MON/YYYY') as date_of_registration_state,\r\n"
							+ "to_char(a.date_of_completion_md_part1,'DD/MON/YYYY') as date_of_completion_md_part1,\r\n"
							+ "to_char(a.date_of_completion_md_part2,'DD/MON/YYYY') as date_of_completion_md_part2,\r\n"
							+ "to_char(a.date_of_declaration_of_md,'DD/MON/YYYY') as date_of_declaration_of_md,\r\n"
							+ "to_char(a.date_of_completion_internship,'DD/MON/YYYY') as date_of_completion_internship,a.remarks \r\n"
							+ "from dg_rec_student_admitted_child_b a \r\n"
							+ "inner join edu_lms_university_mstr u on u.id=a.university_id \r\n"
							+ "inner join logininformation li on li.userid=a.user_id\r\n"
							+ "where a.university_id=? and a.council_approved_status=?  and li.institute_id=?) a";
							

					PreparedStatement stmt = conn.prepareStatement(q);
					stmt = setQueryWhereClause_SQL3(stmt,search,name_of_homoeopathic_medical_college,
							 name_of_guide, name_of_student_for_guide, topic_of_dissertation, whether_guide_participated_in_examination,
							 date_of_submission, date_of_acceptance, whether_student_published_article, mention_details,
							 article_title, month_year_exam, reject_remarks);
					    System.err.println("---bcount-"+stmt);
					    stmt.setInt(1, Integer.parseInt(uni_id));
						stmt.setInt(2, Integer.parseInt(institute_status));
						stmt.setInt(3, Integer.parseInt(institute_id));
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
			public String GenerateQueryWhereClause_SQL3(String Search,String name_of_homoeopathic_medical_college,
					String name_of_guide,String name_of_student_for_guide,String topic_of_dissertation,String whether_guide_participated_in_examination,
					String date_of_submission,String date_of_acceptance,String whether_student_published_article,String mention_details,
					String article_title,String month_year_exam,String reject_remarks) {
				
				String SearchValue="" ;
				
				if (Search!=null && !Search.equals("")) { // for Input Filter
					SearchValue += " and (upper(name_of_homoeopathic_medical_college) Like ? "
							+ " or upper(name_of_guide) Like ? "
							+ " or upper(name_of_student_for_guide) Like ? "
							+ " or upper(topic_of_dissertation) Like ? or upper(whether_guide_participated_in_examination) Like ? "
							+ " or TO_CHAR(date_of_submission , 'dd/MM/yyyy') like ? "
							+ " or TO_CHAR(date_of_acceptance , 'dd/MM/yyyy') like ? "
							+ " or upper(whether_student_published_article) Like ? or upper(mention_details) Like ? "
							+ " or upper(month_year_exam) Like ? or upper(reject_remarks) Like ?) ";
				}
				return SearchValue;
			}
			
			public PreparedStatement setQueryWhereClause_SQL3(PreparedStatement stmt, String Search,String name_of_homoeopathic_medical_college,
					String name_of_guide,String name_of_student_for_guide,String topic_of_dissertation,String whether_guide_participated_in_examination,
					String date_of_submission,String date_of_acceptance,String whether_student_published_article,String mention_details,
					String article_title,String month_year_exam,String reject_remarks) {
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
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search + "%");
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
						
					}
					if (name_of_homoeopathic_medical_college != null && !name_of_homoeopathic_medical_college.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+name_of_homoeopathic_medical_college.toUpperCase()+"%");
					}
					if (name_of_guide != null && !name_of_guide.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+name_of_guide.toUpperCase()+"%");
					}
					if (name_of_student_for_guide != null && !name_of_student_for_guide.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+name_of_student_for_guide.toUpperCase()+"%");
					}
					if (topic_of_dissertation != null && !topic_of_dissertation.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+topic_of_dissertation.toUpperCase()+"%");
					}
					if (whether_guide_participated_in_examination != null && !whether_guide_participated_in_examination.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+whether_guide_participated_in_examination.toUpperCase()+"%");
					}
					if( date_of_submission != null && !date_of_submission.equals("") && !date_of_submission.equals("DD/MM/YYYY")) {
						flag += 1;
						stmt.setString(flag, date_of_submission );
					}
					if( date_of_acceptance != null && !date_of_acceptance.equals("") && !date_of_acceptance.equals("DD/MM/YYYY")) {
						flag += 1;
						stmt.setString(flag, date_of_acceptance );
					}
					if (whether_student_published_article != null && !whether_student_published_article.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+whether_student_published_article.toUpperCase()+"%");
					}
					if (mention_details != null && !mention_details.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+mention_details.toUpperCase()+"%");
					}
					if (article_title != null && !article_title.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+article_title.toUpperCase()+"%");
					}
					if (month_year_exam != null && !month_year_exam.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+month_year_exam.toUpperCase()+"%");
					}
					if (reject_remarks != null && !reject_remarks.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+reject_remarks.toUpperCase()+"%");
					}

				} catch (Exception e) {
				}

				return stmt;
			}
			
			//---------------------- PG Form E-A ---------------------//
			
			public List<Map<String, Object>> getFilter_PG_ealists(int startPage, int pageLength, String search,
					String orderColunm, String orderType, String university_id, String uni_id, String institute_id,
					String institute_status,String student_name_pg,String from_date,
					String to_date,String name_of_topic,String conclusion_obtain,String date_of_submission_pg,String reject_remarks,
					String publication,String mention_article) {
				
				String SearchValue = GenerateQueryWhereClause_SQL4(search,  student_name_pg, from_date,
						 to_date, name_of_topic, conclusion_obtain, date_of_submission_pg, reject_remarks,
						 publication, mention_article);
				
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						Connection conn = null;
						String q = "";
						
						try {
								conn = dataSource.getConnection();
					
								 
								q="select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.student_name_pg,"
								 		+ "to_char(a.from_date,'DD/MON/YYYY') as from_date,"
								 		+ "to_char(a.to_date,'DD/MON/YYYY') as to_date,"
								 		+ "a.name_of_topic,a.conclusion_obtain,to_char(a.date_of_submission_pg,'DD/MON/YYYY') as date_of_submission_pg,a.publication,a.mention_article \r\n"
								 		+ "from dg_rec_work_done_pg_student_child_b a \r\n"
								 		+ "inner join edu_lms_university_mstr u on u.id=a.university_id \r\n"
								 		+ "inner join logininformation li on li.userid=a.user_id\r\n"
								 		+ "where a.university_id=? and a.council_approved_status=? and li.institute_id=?"+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
										+ pageLength + " OFFSET " + startPage;
								
								PreparedStatement stmt = conn.prepareStatement(q);
								stmt = setQueryWhereClause_SQL4(stmt,search,student_name_pg, from_date,
										 to_date, name_of_topic, conclusion_obtain, date_of_submission_pg, reject_remarks,
										 publication, mention_article);
								stmt.setInt(1, Integer.parseInt(uni_id));
								stmt.setInt(2, Integer.parseInt(institute_status));
								stmt.setInt(3, Integer.parseInt(institute_id));
								ResultSet rs = stmt.executeQuery();
							
								ResultSetMetaData metaData = rs.getMetaData();
								int j = startPage;
								int columnCount = metaData.getColumnCount();
								
								int countFunction = 1;
								int countFunctionrej = 1;
								
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
								
								f ="<li><a class='main-btn active-btn-outline btn-hover btn-sm ADDPGea' data-bs-toggle='modal' data-bs-target='#pgeamodal' value='VIEW DETAILS'  title='VIEW DETAILS' >" 
										+ "<i class='lni lni-eye'>"
										+"<input type='hidden' id='apIdeapg"+countFunction+"' value="+rs.getString("id")+">"
									    +"</i></a></li>";
								
								
//								f1  ="<li><a class='main-btn danger-btn-outline btn-hover btn-sm clarificationformaData' data-bs-toggle='modal' data-bs-target='myModal'  value='ADD' title='Clarification Data' >" //id='id_add_attHospital1'
//										 +"<input type='hidden' id='RejectformaId"+countFunctionrej+"' value="+rs.getString("id")+"></i></a></li>"
//									    +"<i class='lni lni-close'></i></a></li>";
								
								f1 = "<li><a class='main-btn danger-btn-outline btn-hover btn-sm clarificationformeaData' value='REJECT' title='Clarification Data'><i class='lni lni-close'>"
										+"<input type='hidden' id='RejectformeaId"+countFunctionrej+"' value="+rs.getString("id")+">"
										+ "</i></a></li>";
								
								ul+=f + " " ;
								ul+="</ul>";
								
								action = ul;
								countFunction+=1;
								countFunctionrej+=1;
								columns.put("f1", f1);
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
			public long getFilter_PG_eaListCount(String search, String orderColunm,
					String orderType, String university_id, String uni_id, String institute_id, String institute_status,String student_name_pg,String from_date,
					String to_date,String name_of_topic,String conclusion_obtain,String date_of_submission_pg,String reject_remarks,
					String publication,String mention_article) {
				
				String SearchValue = GenerateQueryWhereClause_SQL4(search,  student_name_pg, from_date,
						 to_date, name_of_topic, conclusion_obtain, date_of_submission_pg, reject_remarks,
						 publication, mention_article);
				
				int total = 0;
				Connection conn = null;
				String q = "";
				try {
					conn = dataSource.getConnection();			
					
					q="select count(*) from (select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.student_name_pg,to_char(a.from_date,'DD/MON/YYYY') as from_date,\r\n"
							+ "to_char(a.to_date,'DD/MON/YYYY') as to_date,a.name_of_topic,a.conclusion_obtain,\r\n"
							+ "to_char(a.date_of_submission_pg,'DD/MON/YYYY') as date_of_submission_pg,\r\n"
							+ "a.publication,a.mention_article \r\n"
							+ "from dg_rec_work_done_pg_student_child_b a \r\n"
							+ "inner join edu_lms_university_mstr u on u.id=a.university_id \r\n"
							+ "inner join logininformation li on li.userid=a.user_id\r\n"
							+ "where a.university_id=? and a.council_approved_status=?  and li.institute_id=?) a";
							

					PreparedStatement stmt = conn.prepareStatement(q);
					stmt = setQueryWhereClause_SQL4(stmt,search,student_name_pg, from_date,
							 to_date, name_of_topic, conclusion_obtain, date_of_submission_pg, reject_remarks,
							 publication, mention_article);
					    System.err.println("---bcount-"+stmt);
					    stmt.setInt(1, Integer.parseInt(uni_id));
						stmt.setInt(2, Integer.parseInt(institute_status));
						stmt.setInt(3, Integer.parseInt(institute_id));
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
			public String GenerateQueryWhereClause_SQL4(String Search,String student_name_pg,String from_date,
					String to_date,String name_of_topic,String conclusion_obtain,String date_of_submission_pg,String reject_remarks,
					String publication,String mention_article) {
				
				String SearchValue="" ;
				
				if (Search!=null && !Search.equals("")) { // for Input Filter
					SearchValue += " and (upper(student_name_pg) Like ? "
							+ " or TO_CHAR(from_date , 'dd/MM/yyyy') like ? "
							+ " or TO_CHAR(to_date , 'dd/MM/yyyy') like ? "
							+ " or upper(name_of_topic) Like ? "
							+ " or upper(conclusion_obtain) Like ? "
							+ " or TO_CHAR(date_of_submission_pg , 'dd/MM/yyyy') like ? "
							+ " or upper(reject_remarks) Like ? "
							+ " or upper(publication) Like ? "
							+ " or upper(mention_article) Like ?) ";
				}
				return SearchValue;
			}
			
			public PreparedStatement setQueryWhereClause_SQL4(PreparedStatement stmt, String Search,String student_name_pg,String from_date,
					String to_date,String name_of_topic,String conclusion_obtain,String date_of_submission_pg,String reject_remarks,
					String publication,String mention_article) {
				int flag = 3;
				try {
					if (Search!=null &&  !Search.equals("")) {
						
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search + "%");
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
					if (student_name_pg != null && !student_name_pg.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+student_name_pg.toUpperCase()+"%");
					}
					if( from_date != null && !from_date.equals("") && !from_date.equals("DD/MM/YYYY")) {
						flag += 1;
						stmt.setString(flag, from_date );
					}
					if( to_date != null && !to_date.equals("") && !to_date.equals("DD/MM/YYYY")) {
						flag += 1;
						stmt.setString(flag, to_date );
					}
					if (name_of_topic != null && !name_of_topic.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+name_of_topic.toUpperCase()+"%");
					}
					if (conclusion_obtain != null && !conclusion_obtain.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+conclusion_obtain.toUpperCase()+"%");
					}
					if( date_of_submission_pg != null && !date_of_submission_pg.equals("") && !date_of_submission_pg.equals("DD/MM/YYYY")) {
						flag += 1;
						stmt.setString(flag, date_of_submission_pg );
					}
					if (reject_remarks != null && !reject_remarks.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+reject_remarks.toUpperCase()+"%");
					}
					if (publication != null && !publication.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+publication.toUpperCase()+"%");
					}
					if (mention_article != null && !mention_article.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+mention_article.toUpperCase()+"%");
					}

				} catch (Exception e) {
				}

				return stmt;
			}
			
//---------------------- PG Form E-B ---------------------//
			
			public List<Map<String, Object>> getFilter_PG_eblists(int startPage, int pageLength, String search,
					String orderColunm, String orderType, String university_id, String uni_id, String institute_id,
					String institute_status,String student_name,String student_class,
					String lecture_date,String lecture_day,String lecture_time,String topic,String reject_remarks) {
				
				String SearchValue = GenerateQueryWhereClause_SQL5(search,  student_name, student_class,
						 lecture_date, lecture_day, lecture_time, topic, reject_remarks);
				
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						Connection conn = null;
						String q = "";
						
						try {
								conn = dataSource.getConnection();
					
								 
								q="select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.student_name,a.student_class,"
								 		+ "to_char(a.lecture_date,'DD/MON/YYYY') as lecture_date,"
								 		+ "a.lecture_day,"
								 		+ "a.lecture_time,a.topic \r\n"
								 		+ "from dg_rec_lecture_pg_student_child_b a \r\n"
								 		+ "inner join edu_lms_university_mstr u on u.id=a.university_id \r\n"
								 		+ "inner join logininformation li on li.userid=a.user_id\r\n"
								 		+ "where a.university_id=? and a.council_approved_status=? and li.institute_id=?"+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
										+ pageLength + " OFFSET " + startPage;
								
								PreparedStatement stmt = conn.prepareStatement(q);
								stmt = setQueryWhereClause_SQL5(stmt,search,student_name, student_class, lecture_date, lecture_day, lecture_time, topic, reject_remarks);

								stmt.setInt(1, Integer.parseInt(uni_id));
								stmt.setInt(2, Integer.parseInt(institute_status));
								stmt.setInt(3, Integer.parseInt(institute_id));
								ResultSet rs = stmt.executeQuery();
							
								ResultSetMetaData metaData = rs.getMetaData();
								int j = startPage;
								int columnCount = metaData.getColumnCount();
								
								int countFunction = 1;
								int countFunctionrej = 1;
								
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
								
								f ="<li><a class='main-btn active-btn-outline btn-hover btn-sm ADDPGeb' data-bs-toggle='modal' data-bs-target='#pgebmodal' value='VIEW DETAILS'  title='VIEW DETAILS' >" 
										+ "<i class='lni lni-eye'>"
										+"<input type='hidden' id='apIdebpg"+countFunction+"' value="+rs.getString("id")+">"
									    +"</i></a></li>";
								
								
//								f1  ="<li><a class='main-btn danger-btn-outline btn-hover btn-sm clarificationformaData' data-bs-toggle='modal' data-bs-target='myModal'  value='ADD' title='Clarification Data' >" //id='id_add_attHospital1'
//										 +"<input type='hidden' id='RejectformaId"+countFunctionrej+"' value="+rs.getString("id")+"></i></a></li>"
//									    +"<i class='lni lni-close'></i></a></li>";
								
								f1 = "<li><a class='main-btn danger-btn-outline btn-hover btn-sm clarificationformebData' value='REJECT' title='Clarification Data'><i class='lni lni-close'>"
										+"<input type='hidden' id='RejectformebId"+countFunctionrej+"' value="+rs.getString("id")+">"
										+ "</i></a></li>";
								
								ul+=f + " " ;
								ul+="</ul>";
								
								action = ul;
								countFunction+=1;
								countFunctionrej+=1;
								columns.put("f1", f1);
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
			public long getFilter_PG_ebListCount(String search, String orderColunm,
					String orderType, String university_id, String uni_id, String institute_id, String institute_status,String student_name,String student_class,
					String lecture_date,String lecture_day,String lecture_time,String topic,String reject_remarks) {
				
				String SearchValue = GenerateQueryWhereClause_SQL5(search, student_name, student_class,
						 lecture_date, lecture_day, lecture_time, topic, reject_remarks);
				
				int total = 0;
				Connection conn = null;
				String q = "";
				try {
					conn = dataSource.getConnection();			
					
					q="select count(*) from (select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.student_name,a.student_class,\r\n"
							+ "to_char(a.lecture_date,'DD/MON/YYYY') as lecture_date,a.lecture_day,a.lecture_time,\r\n"
							+ "a.topic \r\n"
							+ "from dg_rec_lecture_pg_student_child_b a \r\n"
							+ "inner join edu_lms_university_mstr u on u.id=a.university_id \r\n"
							+ "inner join logininformation li on li.userid=a.user_id\r\n"
							+ "where a.university_id=? and a.council_approved_status=?  and li.institute_id=?) a";
							

					PreparedStatement stmt = conn.prepareStatement(q);
					stmt = setQueryWhereClause_SQL5(stmt,search,student_name, student_class, lecture_date, lecture_day, lecture_time, topic, reject_remarks);

					    System.err.println("---bcount-"+stmt);
					    stmt.setInt(1, Integer.parseInt(uni_id));
						stmt.setInt(2, Integer.parseInt(institute_status));
						stmt.setInt(3, Integer.parseInt(institute_id));
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
			public String GenerateQueryWhereClause_SQL5(String Search,String student_name,String student_class,
					String lecture_date,String lecture_day,String lecture_time,String topic,String reject_remarks) {
				
				String SearchValue="" ;
				
				if (Search!=null && !Search.equals("")) { // for Input Filter
					SearchValue += " and (upper(student_name) Like ? "
							+ " or upper(student_class) Like ? "
							+ " or TO_CHAR(lecture_date , 'dd/MM/yyyy') like ? "
							+ " or upper(lecture_day) Like ? "
							+ " or upper(lecture_time) Like ? "
							+ " or upper(topic) Like ? "
							+ " or upper(reject_remarks) Like ?) ";
				}
				return SearchValue;
			}
			
			public PreparedStatement setQueryWhereClause_SQL5(PreparedStatement stmt, String Search,String student_name,String student_class,
					String lecture_date,String lecture_day,String lecture_time,String topic,String reject_remarks) {
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
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						
					}
					if (student_name != null && !student_name.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+student_name.toUpperCase()+"%");
					}
					if (student_class != null && !student_class.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+student_class.toUpperCase()+"%");
					}
					if( lecture_date != null && !lecture_date.equals("") && !lecture_date.equals("DD/MM/YYYY")) {
						flag += 1;
						stmt.setString(flag, lecture_date );
					}
					if (lecture_day != null && !lecture_day.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+lecture_day.toUpperCase()+"%");
					}
					if (lecture_time != null && !lecture_time.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+lecture_time.toUpperCase()+"%");
					}
					if (topic != null && !topic.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+topic.toUpperCase()+"%");
					}
					if (reject_remarks != null && !reject_remarks.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+reject_remarks.toUpperCase()+"%");
					}
					

				} catch (Exception e) {
				}

				return stmt;
			}
			
//---------------------- PG Form E-C ---------------------//
			
			public List<Map<String, Object>> getFilter_PG_eclists(int startPage, int pageLength, String search,
					String orderColunm, String orderType, String university_id, String uni_id, String institute_id,
					String institute_status,String student_name_formc,
					String list_of_assignment_formc, String reject_remarks) {
				
				String SearchValue = GenerateQueryWhereClause_SQL6(search,  student_name_formc,
						 list_of_assignment_formc,  reject_remarks);
				
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						Connection conn = null;
						String q = "";
						
						try {
								conn = dataSource.getConnection();
					
								 
								q="select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.student_name_formc,a.list_of_assignment_formc \r\n"
								 		+ "from dg_rec_assignment_pg_student_child_b a \r\n"
								 		+ "inner join edu_lms_university_mstr u on u.id=a.university_id \r\n"
								 		+ "inner join logininformation li on li.userid=a.user_id\r\n"
								 		+ "where a.university_id=? and a.council_approved_status=? and li.institute_id=?"+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
										+ pageLength + " OFFSET " + startPage;
								
								PreparedStatement stmt = conn.prepareStatement(q);
								stmt = setQueryWhereClause_SQL6(stmt,search, student_name_formc, list_of_assignment_formc,  reject_remarks);

								stmt.setInt(1, Integer.parseInt(uni_id));
								stmt.setInt(2, Integer.parseInt(institute_status));
								stmt.setInt(3, Integer.parseInt(institute_id));
								ResultSet rs = stmt.executeQuery();
							
								ResultSetMetaData metaData = rs.getMetaData();
								int j = startPage;
								int columnCount = metaData.getColumnCount();
								
								int countFunction = 1;
								int countFunctionrej = 1;
								
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
								
								f ="<li><a class='main-btn active-btn-outline btn-hover btn-sm ADDPGec' data-bs-toggle='modal' data-bs-target='#pgecmodal' value='VIEW DETAILS'  title='VIEW DETAILS' >" 
										+ "<i class='lni lni-eye'>"
										+"<input type='hidden' id='apIdecpg"+countFunction+"' value="+rs.getString("id")+">"
									    +"</i></a></li>";
								
								
//								f1  ="<li><a class='main-btn danger-btn-outline btn-hover btn-sm clarificationformaData' data-bs-toggle='modal' data-bs-target='myModal'  value='ADD' title='Clarification Data' >" //id='id_add_attHospital1'
//										 +"<input type='hidden' id='RejectformaId"+countFunctionrej+"' value="+rs.getString("id")+"></i></a></li>"
//									    +"<i class='lni lni-close'></i></a></li>";
								
								f1 = "<li><a class='main-btn danger-btn-outline btn-hover btn-sm clarificationformecData' value='REJECT' title='Clarification Data'><i class='lni lni-close'>"
										+"<input type='hidden' id='RejectformecId"+countFunctionrej+"' value="+rs.getString("id")+">"
										+ "</i></a></li>";
								
								ul+=f + " " ;
								ul+="</ul>";
								
								action = ul;
								countFunction+=1;
								countFunctionrej+=1;
								columns.put("f1", f1);
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
			public long getFilter_PG_ecListCount(String search, String orderColunm,
					String orderType, String university_id, String uni_id, String institute_id, String institute_status,String student_name_formc,
					String list_of_assignment_formc, String reject_remarks) {
				
				String SearchValue = GenerateQueryWhereClause_SQL6(search,  student_name_formc,
						 list_of_assignment_formc,  reject_remarks);
				
				int total = 0;
				Connection conn = null;
				String q = "";
				try {
					conn = dataSource.getConnection();			
					
					q="select count(*) from (select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.student_name_formc,a.list_of_assignment_formc\r\n"
							+ "from dg_rec_assignment_pg_student_child_b a \r\n"
							+ "inner join edu_lms_university_mstr u on u.id=a.university_id \r\n"
							+ "inner join logininformation li on li.userid=a.user_id\r\n"
							+ "where a.university_id=? and a.council_approved_status=?  and li.institute_id=?) a";
							

					PreparedStatement stmt = conn.prepareStatement(q);
					stmt = setQueryWhereClause_SQL6(stmt,search, student_name_formc, list_of_assignment_formc,  reject_remarks);

					    System.err.println("---bcount-"+stmt);
					    stmt.setInt(1, Integer.parseInt(uni_id));
						stmt.setInt(2, Integer.parseInt(institute_status));
						stmt.setInt(3, Integer.parseInt(institute_id));
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
			public String GenerateQueryWhereClause_SQL6(String Search,String student_name_formc,
					String list_of_assignment_formc, String reject_remarks) {
				
				String SearchValue="" ;
				
				if (Search!=null && !Search.equals("")) { // for Input Filter
					SearchValue += " and (upper(student_name_formc) Like ? "
							+ " or upper(list_of_assignment_formc) Like ? "
							+ " or upper(reject_remarks) Like ?) ";
				}
				return SearchValue;
			}
			
			public PreparedStatement setQueryWhereClause_SQL6(PreparedStatement stmt, String Search,String student_name_formc,
					String list_of_assignment_formc, String reject_remarks) {
				int flag = 3;
				try {
					if (Search!=null &&  !Search.equals("")) {
						
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						flag += 1;
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
						
					}
					if (student_name_formc != null && !student_name_formc.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+student_name_formc.toUpperCase()+"%");
					}
					if (list_of_assignment_formc != null && !list_of_assignment_formc.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+list_of_assignment_formc.toUpperCase()+"%");
					}
					if (reject_remarks != null && !reject_remarks.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+reject_remarks.toUpperCase()+"%");
					}
					

				} catch (Exception e) {
				}

				return stmt;
			}
			
			
//---------------------- PG Form E-D ---------------------//
			
			public List<Map<String, Object>> getFilter_PG_edlists(int startPage, int pageLength, String search,
					String orderColunm, String orderType, String university_id, String uni_id, String institute_id,
					String institute_status,String student_name_presen,
					String list_of_presentations,String title_of_seminar_attended, String reject_remarks) {
				
				String SearchValue = GenerateQueryWhereClause_SQL7(search,  student_name_presen,
						list_of_presentations, title_of_seminar_attended, reject_remarks);
				
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						Connection conn = null;
						String q = "";
						
						try {
								conn = dataSource.getConnection();
					
								 
								q="select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.student_name_presen,a.list_of_presentations,a.title_of_seminar_attended \r\n"
								 		+ "from dg_rec_presentation_seminar_student_child_b a \r\n"
								 		+ "inner join edu_lms_university_mstr u on u.id=a.university_id \r\n"
								 		+ "inner join logininformation li on li.userid=a.user_id\r\n"
								 		+ "where a.university_id=? and a.council_approved_status=? and li.institute_id=?"+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
										+ pageLength + " OFFSET " + startPage;
								
								PreparedStatement stmt = conn.prepareStatement(q);
								stmt = setQueryWhereClause_SQL7(stmt,search, student_name_presen, list_of_presentations,title_of_seminar_attended , reject_remarks);

								stmt.setInt(1, Integer.parseInt(uni_id));
								stmt.setInt(2, Integer.parseInt(institute_status));
								stmt.setInt(3, Integer.parseInt(institute_id));
								ResultSet rs = stmt.executeQuery();
							
								ResultSetMetaData metaData = rs.getMetaData();
								int j = startPage;
								int columnCount = metaData.getColumnCount();
								
								int countFunction = 1;
								int countFunctionrej = 1;
								
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
								
								f ="<li><a class='main-btn active-btn-outline btn-hover btn-sm ADDPGed' data-bs-toggle='modal' data-bs-target='#pgedmodal' value='VIEW DETAILS'  title='VIEW DETAILS' >" 
										+ "<i class='lni lni-eye'>"
										+"<input type='hidden' id='apIdedpg"+countFunction+"' value="+rs.getString("id")+">"
									    +"</i></a></li>";
								
								
//								f1  ="<li><a class='main-btn danger-btn-outline btn-hover btn-sm clarificationformaData' data-bs-toggle='modal' data-bs-target='myModal'  value='ADD' title='Clarification Data' >" //id='id_add_attHospital1'
//										 +"<input type='hidden' id='RejectformaId"+countFunctionrej+"' value="+rs.getString("id")+"></i></a></li>"
//									    +"<i class='lni lni-close'></i></a></li>";
								
								f1 = "<li><a class='main-btn danger-btn-outline btn-hover btn-sm clarificationformedData' value='REJECT' title='Clarification Data'><i class='lni lni-close'>"
										+"<input type='hidden' id='RejectformedId"+countFunctionrej+"' value="+rs.getString("id")+">"
										+ "</i></a></li>";
								
								ul+=f + " " ;
								ul+="</ul>";
								
								action = ul;
								countFunction+=1;
								countFunctionrej+=1;
								columns.put("f1", f1);
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
			public long getFilter_PG_edListCount(String search, String orderColunm,
					String orderType, String university_id, String uni_id, String institute_id, String institute_status,String student_name_presen,
					String list_of_presentations,String title_of_seminar_attended, String reject_remarks) {
				
				String SearchValue = GenerateQueryWhereClause_SQL7(search,  student_name_presen,
						list_of_presentations, title_of_seminar_attended, reject_remarks);
				
				int total = 0;
				Connection conn = null;
				String q = "";
				try {
					conn = dataSource.getConnection();			
					
					q="select count(*) from (select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.student_name_presen,a.list_of_presentations,a.title_of_seminar_attended\r\n"
							+ "from dg_rec_presentation_seminar_student_child_b a \r\n"
							+ "inner join edu_lms_university_mstr u on u.id=a.university_id \r\n"
							+ "inner join logininformation li on li.userid=a.user_id\r\n"
							+ "where a.university_id=? and a.council_approved_status=?  and li.institute_id=?) a";
							

					PreparedStatement stmt = conn.prepareStatement(q);
					stmt = setQueryWhereClause_SQL7(stmt,search, student_name_presen, list_of_presentations,title_of_seminar_attended , reject_remarks);

					    System.err.println("---bcount-"+stmt);
					    stmt.setInt(1, Integer.parseInt(uni_id));
						stmt.setInt(2, Integer.parseInt(institute_status));
						stmt.setInt(3, Integer.parseInt(institute_id));
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
			public String GenerateQueryWhereClause_SQL7(String Search,String student_name_presen,
					String list_of_presentations,String title_of_seminar_attended, String reject_remarks) {
				
				String SearchValue="" ;
				
				if (Search!=null && !Search.equals("")) { // for Input Filter
					SearchValue += " and (upper(student_name_presen) Like ? "
							+ " or upper(list_of_presentations) Like ? "
							+ " or upper(title_of_seminar_attended) Like ? "
							+ " or upper(reject_remarks) Like ?) ";
				}
				return SearchValue;
			}
			
			public PreparedStatement setQueryWhereClause_SQL7(PreparedStatement stmt, String Search,String student_name_presen,
					String list_of_presentations,String title_of_seminar_attended, String reject_remarks) {
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
						stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					}
					if (student_name_presen != null && !student_name_presen.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+student_name_presen.toUpperCase()+"%");
					}
					if (list_of_presentations != null && !list_of_presentations.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+list_of_presentations.toUpperCase()+"%");
					}
					if (title_of_seminar_attended != null && !title_of_seminar_attended.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+title_of_seminar_attended.toUpperCase()+"%");
					}
					if (reject_remarks != null && !reject_remarks.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+reject_remarks.toUpperCase()+"%");
					}
					

				} catch (Exception e) {
				}

				return stmt;
			}
			
		//---------------------- PG Form F --------------------//
		
			public List<Map<String, Object>> getFilter_PG_flist(int startPage, int pageLength, String search,
					String orderColunm, String orderType, String university_id, String uni_id, String institute_id, String institute_status,String subject,String name_of_examiners,
					String date_of_examination,String reject_remarks) {
				
				String SearchValue = GenerateQueryWhereClause_SQL8(search,  subject, name_of_examiners, date_of_examination, reject_remarks);

				
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						Connection conn = null;
						String q = "";
						
						try {
								conn = dataSource.getConnection();
					
								 q="select u.university_name,a.id,a.subject,a.name_of_examiners,to_char(date_of_examination,'dd/MON/yyyy') as date_of_examination \r\n"
								 		+ "from dg_rec_examiner_list_pg_course_child_b a \r\n"
								 		+ "inner join edu_lms_university_mstr u on u.id=a.university_id \r\n"
								 		+ "inner join logininformation li on li.userid=a.user_id\r\n"
								 		+ "where a.university_id=? and a.council_approved_status=?"+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
										+ pageLength + " OFFSET " + startPage;
								 
								 
								PreparedStatement stmt = conn.prepareStatement(q);
								stmt = setQueryWhereClause_SQL8(stmt,search,subject, name_of_examiners, date_of_examination, reject_remarks);

								stmt.setInt(1, Integer.parseInt(uni_id));
								stmt.setInt(2, Integer.parseInt(institute_status));
								ResultSet rs = stmt.executeQuery();
							
								ResultSetMetaData metaData = rs.getMetaData();
								int j = startPage;
								int columnCount = metaData.getColumnCount();
								
								int countFunction = 1;
								int countFunctionrej = 1;
								
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
								
								f ="<li><a class='main-btn active-btn-outline btn-hover btn-sm ADDExaminers' data-bs-toggle='modal' data-bs-target='#pgfmodel' value='VIEW DETAILS'  title='VIEW DETAILS' >" 
										+ "<i class='lni lni-eye'>"
										+"<input type='hidden' id='apIdExaminers"+countFunction+"' value="+rs.getString("id")+">"
									    +"</i></a></li>";
								
								f1 = "<li><a class='main-btn danger-btn-outline btn-hover btn-sm clarificationformfData' value='REJECT' title='Clarification Data'><i class='lni lni-close'>"
										+"<input type='hidden' id='RejectformfId"+countFunctionrej+"' value="+rs.getString("id")+">"
										+ "</i></a></li>";
								
								ul+=f + " "  ;
								ul+="</ul>";
								
								action = ul;
								countFunction+=1;
								countFunctionrej+=1;
								columns.put("f1", f1);
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
			public long getFilter_PG_fListCount(String search,
					String orderColunm, String orderType, String university_id, String uni_id, String institute_id, String institute_status,String subject,String name_of_examiners,
					String date_of_examination,String reject_remarks) {
				
				String SearchValue = GenerateQueryWhereClause_SQL8(search,  subject, name_of_examiners, date_of_examination, reject_remarks);

				
				int total = 0;
				Connection conn = null;
				String q = "";
				try {
					conn = dataSource.getConnection();			
					
					q="select count(*) from (select u.university_name,a.id,a.subject,a.name_of_examiners,to_char(date_of_examination,'dd/MON/yyyy') as date_of_examination \r\n"
							+ "from dg_rec_examiner_list_pg_course_child_b a \r\n"
							+ "inner join edu_lms_university_mstr u on u.id=a.university_id \r\n"
							+ "where a.university_id=? and a.council_approved_status=?) a";
							

					PreparedStatement stmt = conn.prepareStatement(q);
					stmt = setQueryWhereClause_SQL8(stmt,search,subject, name_of_examiners, date_of_examination, reject_remarks);

					   stmt.setInt(1, Integer.parseInt(uni_id));
					stmt.setInt(2, Integer.parseInt(institute_status));
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
			public String GenerateQueryWhereClause_SQL8(String Search,String subject,String name_of_examiners,String date_of_examination,String reject_remarks) {
				String SearchValue="" ;
				
				if (Search!=null && !Search.equals("")) { // for Input Filter
					SearchValue += " and (upper(name_of_applicant_university) Like ? "
							+ " or upper(postgraduate_course) Like ? "
							+ " or upper(abbre_postgraduate_course) Like ? "
							+ " or upper(academic_session) Like ?"
							+ " or upper(name_of_college) Like ? "
							+ " or upper(country) Like ? "
							+ " or upper(state) Like ? "
							+ " or upper(district) Like ?"
							+ " or upper(city) Like ? "
							+ " or upper(postal_address) Like ? "
							+ " or upper(email) Like ?"
							+ " or upper(website) Like ? "
							+ " or upper(academic_year_applied_for) Like ? "
							+ " or upper(permission_from_central_gov) Like ? "
							+ " or admission_intake::character varying like ? "
							+ " or num_of_student_admitted::character varying like ? "
							+ " or upper(remarks) Like ? "
							+ " or upper(reject_remarks) Like ?) ";
				}
				return SearchValue;
			}
			
			public PreparedStatement setQueryWhereClause_SQL8(PreparedStatement stmt, String Search,String subject,String name_of_examiners,
					String date_of_examination,String reject_remarks) {
				
				int flag = 2;
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
						
					}
					
					if (subject != null && !subject.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+subject.toUpperCase()+"%");
					}
					if (name_of_examiners != null && !name_of_examiners.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+name_of_examiners.toUpperCase()+"%");
					}
					if( date_of_examination != null && !date_of_examination.equals("") && !date_of_examination.equals("DD/MM/YYYY")) {
						flag += 1;
						stmt.setString(flag, date_of_examination );
					}
					if (reject_remarks != null && !reject_remarks.equals("")) {
						flag += 1;
						stmt.setString(flag,"%"+reject_remarks.toUpperCase()+"%");
					}

				} catch (Exception e) {
				}

				return stmt;
			}
				

			/////////////////// Council View Data University And Institute   ///////////////////////////
			
												// Form A PG//
			
			@Override
			public List<Map<String, Object>> getviewdatapgaid(String id) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			System.err.println("--C-in daoimpl-dddd---");
			try {
				
				conn = dataSource.getConnection();
				
				q="select * from dg_rec_pg_form_b_parent where id=? ";
				
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
														// Form B PG//
			
						@Override
						public List<Map<String, Object>> getviewdatapgdid(String id) {
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						Connection conn = null;
						String q = "";
						System.err.println("--C-in daoimpl-dddd---");
						try {
							
							conn = dataSource.getConnection();
							
							q="select *,to_char(date_of_admission,'dd/MON/yyyy') as date_of_admission,"
									+"to_char(date_of_registration,'dd/MON/yyyy') as date_of_registration,"
									+"to_char(date_of_registration_state,'dd/MON/yyyy') as date_of_registration_state,"
									+"to_char(date_of_completion_md_part1,'dd/MON/yyyy') as date_of_completion_md_part1,"
									+"to_char(date_of_completion_md_part2,'dd/MON/yyyy') as date_of_completion_md_part2,"
									+"to_char(date_of_declaration_of_md,'dd/MON/yyyy') as date_of_declaration_of_md,"
									+"to_char(date_of_completion_internship,'dd/MON/yyyy') as date_of_completion_internship"
									+ " from dg_rec_student_admitted_child_b where id=? ";
							
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
													// Form C PG//
			
						@Override
						public List<Map<String, Object>> getviewdatapgbid(String id) {
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						Connection conn = null;
						String q = "";
						System.err.println("--C-in daoimpl-dddd---");
						try {
							
							conn = dataSource.getConnection();
						
							q="select *,to_char(date_of_reg,'dd/MON/yyyy') as date_of_reg,"
									+"to_char(date_of_birth,'dd/MON/yyyy') as date_of_birth,"
									+"to_char(date_of_appointment,'dd/MON/yyyy') as date_of_appointment,"
									+"to_char(exp_from,'dd/MON/yyyy') as exp_from,"
									+"to_char(exp_to,'dd/MON/yyyy') as exp_to"
									+ " from dg_rec_teaching_staff_child_b where id=? ";
							
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
						// Form D PG//
						
						@Override
						public List<Map<String, Object>> getviewdatapgeid(String id) {
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						Connection conn = null;
						String q = "";
						System.err.println("-dd--in daoimpl-dddd---");
						try {
							
							conn = dataSource.getConnection();
							
//							q="select *,to_char(date_of_submission,'dd/MON/yyyy') as date_of_submission,"
//									+"to_char(date_of_acceptance,'dd/MON/yyyy') as date_of_acceptance"
//									+"from dg_rec_examiners_md_child_b where id=? ";
							
							q="select *,to_char(date_of_submission,'dd/MON/yyyy') as date_of_submission,"
									+"to_char(date_of_acceptance,'dd/MON/yyyy') as date_of_acceptance"
									+ " from dg_rec_examiners_md_child_b where id=? ";
							
							PreparedStatement stmt = conn.prepareStatement(q);
							stmt.setInt(1,Integer.parseInt(id));
								ResultSet rs = stmt.executeQuery();
								
								System.err.println("--stmt-dd--"+stmt);

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
						
										// Form E-A PG//
						
						@Override
						public List<Map<String, Object>> getviewdatapgeaid(String id) {
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						Connection conn = null;
						String q = "";
						System.err.println("-dd--in daoimpl-dddd---");
						try {
							
							conn = dataSource.getConnection();
							
//							q="select *,to_char(date_of_submission,'dd/MON/yyyy') as date_of_submission,"
//									+"to_char(date_of_acceptance,'dd/MON/yyyy') as date_of_acceptance"
//									+"from dg_rec_examiners_md_child_b where id=? ";
							
							q="select *,to_char(from_date,'dd/MON/yyyy') as from_date,"
									+"to_char(to_date,'dd/MON/yyyy') as to_date,"
									+"to_char(date_of_submission_pg,'dd/MON/yyyy') as date_of_submission_pg"
									+ " from dg_rec_work_done_pg_student_child_b where id=? ";
							
							PreparedStatement stmt = conn.prepareStatement(q);
							stmt.setInt(1,Integer.parseInt(id));
								ResultSet rs = stmt.executeQuery();
								
								System.err.println("--stmt-dd--"+stmt);

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
						
												// Form E-B PG//
						
						@Override
						public List<Map<String, Object>> getviewdatapgebid(String id) {
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						Connection conn = null;
						String q = "";
						System.err.println("-dd--in daoimpl-dddd---");
						try {
							
							conn = dataSource.getConnection();
							

							
							q="select *,to_char(lecture_date,'dd/MON/yyyy') as lecture_date"
									+ " from dg_rec_lecture_pg_student_child_b where id=? ";
							
							PreparedStatement stmt = conn.prepareStatement(q);
							stmt.setInt(1,Integer.parseInt(id));
								ResultSet rs = stmt.executeQuery();
								
								System.err.println("--stmt-dd--"+stmt);

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
						
												// Form E-C PG//
						
						@Override
						public List<Map<String, Object>> getviewdatapgecid(String id) {
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						Connection conn = null;
						String q = "";
						System.err.println("-dd--in daoimpl-dddd---");
						try {
							
							conn = dataSource.getConnection();
							
							q="select * from dg_rec_assignment_pg_student_child_b where id=? ";
							
							PreparedStatement stmt = conn.prepareStatement(q);
							stmt.setInt(1,Integer.parseInt(id));
								ResultSet rs = stmt.executeQuery();
								
								System.err.println("--stmt-dd--"+stmt);

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
						
							// Form E-C PG//
						
						@Override
						public List<Map<String, Object>> getviewdatapgedid(String id) {
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						Connection conn = null;
						String q = "";
						System.err.println("-dd--in daoimpl-dddd---");
						try {
							
							conn = dataSource.getConnection();
							
							q="select * from dg_rec_presentation_seminar_student_child_b where id=? ";
							
							PreparedStatement stmt = conn.prepareStatement(q);
							stmt.setInt(1,Integer.parseInt(id));
								ResultSet rs = stmt.executeQuery();
								
								System.err.println("--stmt-dd--"+stmt);

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
													// Form F PG//
						
						@Override
						public List<Map<String, Object>> getviewdatapgcid(String id) {
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						Connection conn = null;
						String q = "";
						System.err.println("--C-in daoimpl-dddd---");
						try {
							
							conn = dataSource.getConnection();
							
							
							q="select *,to_char(date_of_examination,'dd/MON/yyyy') as date_of_examination \r\n"
									+ " from dg_rec_examiner_list_pg_course_child_b where id=? ";
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
						
						   ////////////  PDF ////////////////
						
						@Override
						public ArrayList<ArrayList<String>> getviewdatapgaidPDF(String id) {
							ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
						Connection conn = null;
						String q = "";
						System.err.println("--C-in daoimpl-dddd---");
						try {
							
							conn = dataSource.getConnection();
							
							q="select id,name_of_applicant_university,postgraduate_course,abbre_postgraduate_course,academic_session,name_of_college,"
									+ "country,state,district,city,\r\n"
									+ "postal_address,email,website,academic_year_applied_for,permission_from_central_gov,admission_intake,"
									+ "num_of_student_admitted,remarks\r\n"
									+ "from dg_rec_pg_form_b_parent \r\n"
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
									alist.add(rs.getString("postgraduate_course"));// 2
									alist.add(rs.getString("abbre_postgraduate_course"));// 3
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
						public ArrayList<ArrayList<String>> getviewdatapgbidPDF(String id) {
							ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
						Connection conn = null;
						String q = "";
						System.err.println("--C-in daoimpl-dddd---");
						try {
							
							conn = dataSource.getConnection();
							
							q="select id,student_name,to_char(date_of_admission,'dd/MON/yyyy') as date_of_admission,"
									+ "to_char(date_of_registration,'dd/MON/yyyy') as date_of_registration,course_name,rank,marks,"
									+ "all_india,state,admission_authority,court_order,\r\n"
									+ "qualification_name,year_of_award_admission,to_char(date_of_registration_state,'dd/MON/yyyy') as date_of_registration_state,"
									+ "to_char(date_of_completion_md_part1,'dd/MON/yyyy') as date_of_completion_md_part1,"
									+ "to_char(date_of_completion_md_part2,'dd/MON/yyyy') as date_of_completion_md_part2,"
									+ "to_char(date_of_declaration_of_md,'dd/MON/yyyy') as date_of_declaration_of_md, "
									+ "to_char(date_of_completion_internship,'dd/MON/yyyy') as date_of_completion_internship,remarks\r\n"
									+ "from dg_rec_student_admitted_child_b \r\n"
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
									alist.add(rs.getString("student_name"));// 1
									alist.add(rs.getString("date_of_admission"));// 2
									alist.add(rs.getString("date_of_registration"));// 3
									alist.add(rs.getString("course_name"));// 4
									alist.add(rs.getString("rank"));// 5
									alist.add(rs.getString("marks"));// 6
									alist.add(rs.getString("all_india"));// 7
									alist.add(rs.getString("state"));// 8
									alist.add(rs.getString("admission_authority"));// 9
									alist.add(rs.getString("court_order"));// 10
									alist.add(rs.getString("qualification_name"));// 11
									alist.add(rs.getString("year_of_award_admission"));// 12
									alist.add(rs.getString("date_of_registration_state"));// 13
									alist.add(rs.getString("date_of_completion_md_part1"));// 14
									alist.add(rs.getString("date_of_completion_md_part2"));// 15
									alist.add(rs.getString("date_of_declaration_of_md"));// 16
									alist.add(rs.getString("date_of_completion_internship"));// 17
									alist.add(rs.getString("remarks"));// 18
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
						public ArrayList<ArrayList<String>> getviewdatapgcidPDF(String id) {
							ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
						Connection conn = null;
						String q = "";
						System.err.println("--C-in daoimpl-dddd---");
						try {
							
							conn = dataSource.getConnection();
							
							q="select id,name_of_college_pg,name_of_teaching_staff,phone,email_id,designation,department,"
									+ "registration_no,to_char(date_of_reg,'dd/MON/yyyy') as date_of_reg,to_char(date_of_birth,'dd/MON/yyyy') as date_of_birth,qualification_awarding_authority,\r\n"
									+ "year_of_award,to_char(date_of_appointment,'dd/MON/yyyy') as date_of_appointment,fulltime_parttime,post_teaching,"
									+ "to_char(exp_from,'dd/MON/yyyy') as exp_from,to_char(exp_to,'dd/MON/yyyy') as exp_to,"
									+ "total_teaching_exp_in_year\r\n"
									+ "from dg_rec_teaching_staff_child_b \r\n"
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
									alist.add(rs.getString("name_of_college_pg"));// 1
									alist.add(rs.getString("name_of_teaching_staff"));// 2
									alist.add(rs.getString("phone"));// 3
									alist.add(rs.getString("email_id"));// 4
									alist.add(rs.getString("designation"));// 5
									alist.add(rs.getString("department"));// 6
									alist.add(rs.getString("registration_no"));// 7
									alist.add(rs.getString("date_of_reg"));// 8
									alist.add(rs.getString("date_of_birth"));// 9
									alist.add(rs.getString("qualification_awarding_authority"));// 10
									alist.add(rs.getString("year_of_award"));// 11
									alist.add(rs.getString("date_of_appointment"));// 12
									alist.add(rs.getString("fulltime_parttime"));// 13
									alist.add(rs.getString("post_teaching"));// 14
									alist.add(rs.getString("exp_from"));// 15
									alist.add(rs.getString("exp_to"));// 16
									alist.add(rs.getString("total_teaching_exp_in_year"));// 17
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
						public ArrayList<ArrayList<String>> getviewdatapgdidPDF(String id) {
							ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
						Connection conn = null;
						String q = "";
						System.err.println("--C-in daoimpl-dddd---");
						try {
							
							conn = dataSource.getConnection();
							
							q="select id,name_of_homoeopathic_medical_college,name_of_guide,name_of_student_for_guide,"
									+ "topic_of_dissertation,whether_guide_participated_in_examination,to_char(date_of_submission,'dd/MON/yyyy') as date_of_submission,\r\n"
									+ "to_char(date_of_acceptance,'dd/MON/yyyy') as date_of_acceptance,whether_student_published_article,mention_details,"
									+ "article_title,month_year_exam\r\n"
									+ "from dg_rec_examiners_md_child_b \r\n"
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
									alist.add(rs.getString("name_of_homoeopathic_medical_college"));// 1
									alist.add(rs.getString("name_of_guide"));// 2
									alist.add(rs.getString("name_of_student_for_guide"));// 3
									alist.add(rs.getString("topic_of_dissertation"));// 4
									alist.add(rs.getString("whether_guide_participated_in_examination"));// 5
									alist.add(rs.getString("date_of_submission"));// 6
									alist.add(rs.getString("date_of_acceptance"));// 7
									alist.add(rs.getString("whether_student_published_article"));// 8
									alist.add(rs.getString("mention_details"));// 9
									alist.add(rs.getString("article_title"));// 10
									alist.add(rs.getString("month_year_exam"));// 11
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
						public ArrayList<ArrayList<String>> getviewdatapgeaidPDF(String id) {
							ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
						Connection conn = null;
						String q = "";
						System.err.println("--C-in daoimpl-dddd---");
						try {
							
							conn = dataSource.getConnection();
							
							q="select id,student_name_pg,from_date,to_date,"
									+ "name_of_topic,conclusion_obtain,date_of_submission_pg,\r\n"
									+ "publication,mention_article\r\n"
									+ "from dg_rec_work_done_pg_student_child_b \r\n"
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
									alist.add(rs.getString("student_name_pg"));// 1
									alist.add(rs.getString("from_date"));// 2
									alist.add(rs.getString("to_date"));// 3
									alist.add(rs.getString("name_of_topic"));// 4
									alist.add(rs.getString("conclusion_obtain"));// 5
									alist.add(rs.getString("date_of_submission_pg"));// 6
									alist.add(rs.getString("publication"));// 7
									alist.add(rs.getString("mention_article"));// 8
									
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
						public ArrayList<ArrayList<String>> getviewdatapgebidPDF(String id) {
							ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
						Connection conn = null;
						String q = "";
						System.err.println("--C-in daoimpl-dddd---");
						try {
							
							conn = dataSource.getConnection();
							
							q="select id,student_name,student_class,to_char(lecture_date,'dd/MON/yyyy') as lecture_date,"
									+ "lecture_day,lecture_time,topic \r\n"
									+ "from dg_rec_lecture_pg_student_child_b \r\n"
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
									alist.add(rs.getString("student_name"));// 1
									alist.add(rs.getString("student_class"));// 2
									alist.add(rs.getString("lecture_date"));// 3
									alist.add(rs.getString("lecture_day"));// 4
									alist.add(rs.getString("lecture_time"));// 5
									alist.add(rs.getString("topic"));// 6
								
									
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
						public ArrayList<ArrayList<String>> getviewdatapgecidPDF(String id) {
							ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
						Connection conn = null;
						String q = "";
						System.err.println("--C-in daoimpl-dddd---");
						try {
							
							conn = dataSource.getConnection();
							
							q="select id,student_name_formc,list_of_assignment_formc \r\n"
									+ "from dg_rec_assignment_pg_student_child_b \r\n"
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
									alist.add(rs.getString("student_name_formc"));// 1
									alist.add(rs.getString("list_of_assignment_formc"));// 2
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
						public ArrayList<ArrayList<String>> getviewdatapgedidPDF(String id) {
							ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
						Connection conn = null;
						String q = "";
						System.err.println("--C-in daoimpl-dddd---");
						try {
							
							conn = dataSource.getConnection();
							
							q="select id,student_name_presen,list_of_presentations,title_of_seminar_attended \r\n"
									+ "from dg_rec_presentation_seminar_student_child_b \r\n"
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
									alist.add(rs.getString("student_name_presen"));// 1
									alist.add(rs.getString("list_of_presentations"));// 2
									alist.add(rs.getString("title_of_seminar_attended"));// 3
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
						public ArrayList<ArrayList<String>> getviewdatapgfidPDF(String id) {
							ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
						Connection conn = null;
						String q = "";
						System.err.println("--C-in daoimpl-dddd---");
						try {
							
							conn = dataSource.getConnection();
							
							q="select id,subject,name_of_examiners,date_of_examination \r\n"
									+ "from dg_rec_examiner_list_pg_course_child_b \r\n"
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
									alist.add(rs.getString("subject"));// 1
									alist.add(rs.getString("name_of_examiners"));// 2
									alist.add(rs.getString("date_of_examination"));// 3
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
}
