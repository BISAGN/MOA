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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.AyushEdu.Models.Degree_recognition_form_B.DG_REC_EXAMINER_LIST_PG_COURSE_B;
import com.AyushEdu.Models.Degree_recognition_form_B.DG_REC_PG_FORM_B;
import com.AyushEdu.Models.Degree_recognition_form_B.DG_REC_TEACHING_STAFF_B;




@Repository
public class form_b_pg_DaoImpl implements Form_b_pg_Dao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DataSource dataSource;

	//----------------------	getInstituteListbyUserID--------------------------
	
			public List<Map<String, Object>> getInstituteListbyUserID() {
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						Connection conn = null;
						String q = "";
						try {

								conn = dataSource.getConnection();
					
								 q= " select userid,institute_name from logininformation lo inner join userroleinformation ur on lo.userid=ur.user_id\r\n"
								 		+ "inner join roleinformation ro on ro.role_id=ur.role_id and role='Institute_NCH' \r\n"
								 		+ "inner join edu_lms_institute_reg ins on lo.institute_id=ins.id ";
								
								PreparedStatement stmt = conn.prepareStatement(q);
								
								ResultSet rs = stmt.executeQuery();
							System.out.println("stmt---"+stmt);
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
								//---------------------- PG Form A ---------------------//
	
		public List<Map<String, Object>> getFilter_PG_list(int startPage, int pageLength,String Search, String orderColunm,
					String orderType,String university_id,String university_approved_status,String council_approved_status,String university_status,
					String name_of_applicant_university,String postgraduate_course, String abbre_postgraduate_course,String academic_session,String name_of_college,
					String country,String state,String district,String city,String postal_address,String email,String website,
					String academic_year_applied_for,String permission_from_central_gov,String admission_intake,String num_of_student_admitted,
					String remarks,String reject_remarks) {
			
			String SearchValue = GenerateQueryWhereClause_SQL(Search,  name_of_applicant_university, postgraduate_course,  abbre_postgraduate_course, academic_session,
					 name_of_college, country, state, district, city, postal_address, email, website,
					 academic_year_applied_for, permission_from_central_gov, admission_intake, num_of_student_admitted,
					 remarks, reject_remarks);
			
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					Connection conn = null;
					String q = "";
					
					try {
							conn = dataSource.getConnection();
				
							 q="select a.id,a.name_of_applicant_university,a.postgraduate_course,a.abbre_postgraduate_course,a.academic_session,"
							 		+ "a.name_of_college,a.country,a.state,a.district,\r\n"
									 +"a.city,a.postal_address,a.email,a.website,a.academic_year_applied_for,a.permission_from_central_gov,a.admission_intake,"
									 +"a.num_of_student_admitted,a.remarks,a.reject_remarks \r\n"
									 +"from dg_rec_pg_form_b_parent a \n"
									 +"where a.university_id=? and university_approved_status=? " + SearchValue + " ORDER BY a.id "+ orderType + " limit "
										+ pageLength + " OFFSET " + startPage;
							 
							PreparedStatement stmt = conn.prepareStatement(q);
							stmt = setQueryWhereClause_SQL(stmt,Search,name_of_applicant_university, postgraduate_course,  abbre_postgraduate_course, academic_session,
									 name_of_college, country, state, district, city, postal_address, email, website,
									 academic_year_applied_for, permission_from_central_gov, admission_intake, num_of_student_admitted,
									 remarks, reject_remarks);
							stmt.setInt(1, Integer.parseInt(university_id));
							stmt.setInt(2, Integer.parseInt(university_status));
							
							System.err.println("UNI_EDIT_1---"+stmt);
							
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
							
							f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDPG' value='ADD' title='Edit Data' >" 
									+"<i class='lni lni-pencil-alt'>"
									+"<input type='hidden' id='pgIdAGE"+countFunction+"' value="+rs.getString("id")+">"
								    +"</i></a></li>";
							
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

					////////////////////////////// Form A UG ListCount ///////////////////////////
		
		@Override
		public long getFilter_PGListCount(String search, String university_id,String university_approved_status, String council_approved_status,
				String university_status,String name_of_applicant_university,String postgraduate_course, String abbre_postgraduate_course,String academic_session,String name_of_college,
				String country,String state,String district,String city,String postal_address,String email,String website,
				String academic_year_applied_for,String permission_from_central_gov,String admission_intake,String num_of_student_admitted,
				String remarks,String reject_remarks){
			
			String SearchValue = GenerateQueryWhereClause_SQL(search,  name_of_applicant_university, postgraduate_course,  abbre_postgraduate_course, academic_session,
					 name_of_college, country, state, district, city, postal_address, email, website,
					 academic_year_applied_for, permission_from_central_gov, admission_intake, num_of_student_admitted,
					 remarks, reject_remarks);
			
			int total = 0;
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();			
				
				q="select count(*) from (select a.id,a.name_of_applicant_university,a.postgraduate_course,a.abbre_postgraduate_course,a.academic_session,a.name_of_college,a.country,a.state,a.district,\r\n"
						+ "a.city,a.postal_address,a.email,a.website,a.academic_year_applied_for,a.permission_from_central_gov,a.admission_intake,a.num_of_student_admitted,a.remarks,a.reject_remarks \r\n"
						+ "from dg_rec_pg_form_b_parent a \r\n"
						+ "where a.university_id=? and university_approved_status=?) a";
						

				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL(stmt,search,name_of_applicant_university, postgraduate_course,  abbre_postgraduate_course, academic_session,
						 name_of_college, country, state, district, city, postal_address, email, website,
						 academic_year_applied_for, permission_from_central_gov, admission_intake, num_of_student_admitted,
						 remarks, reject_remarks);
				 stmt.setInt(1,Integer.parseInt( university_id));
				 stmt.setInt(2, Integer.parseInt(university_status));
				 //stmt.setInt(2, Integer.parseInt(user_id));
				    System.err.println("UNI_EDIT_COUNT_1---"+stmt);
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
		
		public DG_REC_PG_FORM_B getpgByid(int id) {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			DG_REC_PG_FORM_B eid = (DG_REC_PG_FORM_B) session
					.get(DG_REC_PG_FORM_B.class, id);
			session.getTransaction().commit();
			session.close();
			return eid;
		}
		//---------------------- PG Form C ---------------------//
		
		public List<Map<String, Object>> getFilter_Teaching_staff_list(int startPage, int pageLength,String Search, String orderColunm,
					String orderType,String university_id,String university_approved_status,String council_approved_status,String university_status,
					String name_of_college_pg,String name_of_teaching_staff, String phone,String email_id,String designation,String department,
					String registration_no,String date_of_reg,String date_of_birth,String qualification_awarding_authority,String year_of_award,String date_of_appointment,
					String fulltime_parttime,String post_teaching,String exp_from,String exp_to,String total_teaching_exp_in_year,String reject_remarks) {
			
			String SearchValue = GenerateQueryWhereClause_SQL1(Search,  name_of_college_pg, name_of_teaching_staff,  phone, email_id, designation, department,
					 registration_no, date_of_reg, date_of_birth, qualification_awarding_authority, year_of_award, date_of_appointment,
					 fulltime_parttime, post_teaching, exp_from, exp_to, total_teaching_exp_in_year, reject_remarks);
			
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					Connection conn = null;
					String q = "";
					
					try {
							conn = dataSource.getConnection();
							 
							q="select a.id,a.name_of_college_pg,a.name_of_teaching_staff,a.phone,a.email_id,a.designation,a.department,a.registration_no,"
									+ "to_char(a.date_of_reg,'DD/MM/YYYY') as date_of_reg, \r\n"
									+ "to_char(a.date_of_birth,'DD/MM/YYYY') as date_of_birth,a.qualification_awarding_authority,a.year_of_award,"
									+ "to_char(a.date_of_appointment,'DD/MM/YYYY') as date_of_appointment,a.fulltime_parttime,a.post_teaching, \r\n"
									+ "to_char(a.exp_from,'DD/MM/YYYY') as exp_from,to_char(a.exp_to,'DD/MM/YYYY') as exp_to,a.total_teaching_exp_in_year,a.reject_remarks \r\n"
									+ "from dg_rec_teaching_staff_child_b a \r\n"
									+ "where a.university_id=? and university_approved_status=?" + SearchValue + " ORDER BY a.id "+ orderType + " limit "
									+ pageLength + " OFFSET " + startPage;
				
							PreparedStatement stmt = conn.prepareStatement(q);
							
							stmt = setQueryWhereClause_SQL1(stmt,Search,name_of_college_pg, name_of_teaching_staff,  phone, email_id, designation, department,
									 registration_no, date_of_reg, date_of_birth, qualification_awarding_authority, year_of_award, date_of_appointment,
									 fulltime_parttime, post_teaching, exp_from, exp_to, total_teaching_exp_in_year, reject_remarks);
							
							stmt.setInt(1, Integer.parseInt(university_id));
							 stmt.setInt(2, Integer.parseInt(university_status));
							 
							System.err.println("pg ---form---ccc---"+stmt);
							
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
							
							f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDTeaching' value='ADD' title='Edit Data' >" 
									+"<i class='lni lni-pencil-alt'>"
									+"<input type='hidden' id='teachingIdAGE"+countFunction+"' value="+rs.getString("id")+">"
								    +"</i></a></li>";
							
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

					////////////////////////////// Form C PG ListCount ///////////////////////////
		
		@Override
		public long getFilter_Teaching_staffListCount(String search, String university_id,String university_approved_status, String council_approved_status,String university_status,
				String name_of_college_pg,String name_of_teaching_staff, String phone,String email_id,String designation,String department,
				String registration_no,String date_of_reg,String date_of_birth,String qualification_awarding_authority,String year_of_award,String date_of_appointment,
				String fulltime_parttime,String post_teaching,String exp_from,String exp_to,String total_teaching_exp_in_year,String reject_remarks){
			
			String SearchValue = GenerateQueryWhereClause_SQL1(search,  name_of_college_pg, name_of_teaching_staff,  phone, email_id, designation, department,
					 registration_no, date_of_reg, date_of_birth, qualification_awarding_authority, year_of_award, date_of_appointment,
					 fulltime_parttime, post_teaching, exp_from, exp_to, total_teaching_exp_in_year, reject_remarks);
			
			int total = 0;
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();			
				
				q="select count(*) from (select a.id,a.name_of_college_pg,a.name_of_teaching_staff,a.phone,a.email_id,a.designation,a.department,a.registration_no,a.date_of_reg, \r\n"
						+ "a.date_of_birth,a.qualification_awarding_authority,a.year_of_award,a.date_of_appointment,a.fulltime_parttime,a.post_teaching,a.exp_from,a.exp_to,a.total_teaching_exp_in_year,a.reject_remarks,a.reject_remarks \r\n"
						+ "from dg_rec_teaching_staff_child_b a \r\n"
						+ "where a.university_id=? and university_approved_status=? ) a";
				

				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL1(stmt,search,name_of_college_pg, name_of_teaching_staff,  phone, email_id, designation, department,
						 registration_no, date_of_reg, date_of_birth, qualification_awarding_authority, year_of_award, date_of_appointment,
						 fulltime_parttime, post_teaching, exp_from, exp_to, total_teaching_exp_in_year, reject_remarks);
				 stmt.setInt(1,Integer.parseInt( university_id));
				 stmt.setInt(2, Integer.parseInt(university_status));
				    System.err.println("c list---"+stmt);
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
		public String GenerateQueryWhereClause_SQL1(String Search,String name_of_college_pg,String name_of_teaching_staff, String phone,String email_id,String designation,String department,
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
		
		public PreparedStatement setQueryWhereClause_SQL1(PreparedStatement stmt, String Search,String name_of_college_pg,String name_of_teaching_staff,
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
		
		public DG_REC_TEACHING_STAFF_B getpgteachingByid(int id) {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			DG_REC_TEACHING_STAFF_B hid = (DG_REC_TEACHING_STAFF_B) session
					.get(DG_REC_TEACHING_STAFF_B.class, id);
			session.getTransaction().commit();
			session.close();
			return hid;
		}
		//---------------------- PG Form F ---------------------//
		
				public List<Map<String, Object>> getFilter_Examiners_pg_list(int startPage, int pageLength,String Search, String orderColunm,
							String orderType,String university_id,String university_approved_status,String council_approved_status,String university_status,
							String subject,String name_of_examiners,String date_of_examination,String reject_remarks) {
					
					String SearchValue = GenerateQueryWhereClause_SQL2(Search,  subject, name_of_examiners, date_of_examination, reject_remarks);
					
							List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
							Connection conn = null;
							String q = "";
							
							try {
									conn = dataSource.getConnection();
									 
									q="select a.id,a.subject,a.name_of_examiners,to_char(a.date_of_examination,'DD/MM/YYYY') as date_of_examination,a.reject_remarks \r\n"
											+ "from dg_rec_examiner_list_pg_course_child_b a \r\n"
											+ "where a.university_id=? and university_approved_status=? " + SearchValue + " ORDER BY a.id "+ orderType + " limit "
											+ pageLength + " OFFSET " + startPage;
						
									PreparedStatement stmt = conn.prepareStatement(q);
									stmt = setQueryWhereClause_SQL2(stmt,Search,subject, name_of_examiners, date_of_examination, reject_remarks);
									stmt.setInt(1, Integer.parseInt(university_id));
									 stmt.setInt(2, Integer.parseInt(university_status));
									System.err.println("pg ---form---ffff---"+stmt);
									
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
									
									f ="<li><a class='main-btn active-btn btn-hover btn-sm ADDExam' value='ADD' title='Edit Data' >" 
											+"<i class='lni lni-pencil-alt'>"
											+"<input type='hidden' id='examIdAGE"+countFunction+"' value="+rs.getString("id")+">"
										    +"</i></a></li>";
									
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
				
				public DG_REC_EXAMINER_LIST_PG_COURSE_B getpgexaminersByid(int id) {
					Session session = sessionFactory.openSession();
					Transaction tx = session.beginTransaction();
					DG_REC_EXAMINER_LIST_PG_COURSE_B exid = (DG_REC_EXAMINER_LIST_PG_COURSE_B) session.get(DG_REC_EXAMINER_LIST_PG_COURSE_B.class, id);
					session.getTransaction().commit();
					session.close();
					return exid;
				}

							////////////////////////////// Form F PG ListCount ///////////////////////////
				
				@Override
				public long getFilter_Examiners_pgListCount(String search, String university_id,String university_approved_status, String council_approved_status,String university_status,
						String subject,String name_of_examiners,String date_of_examination,String reject_remarks){
					
					String SearchValue = GenerateQueryWhereClause_SQL2(search,  subject, name_of_examiners, date_of_examination, reject_remarks);
					
					int total = 0;
					Connection conn = null;
					String q = "";
					try {
						conn = dataSource.getConnection();			
						
						q="select count(*) from (select a.id,a.subject,a.name_of_examiners,a.date_of_examination \r\n"
								+ "from dg_rec_examiner_list_pg_course_child_b a \r\n"
								+ "where a.university_id=? and university_approved_status=? ) a";
						

						PreparedStatement stmt = conn.prepareStatement(q);
						stmt = setQueryWhereClause_SQL2(stmt,search,subject, name_of_examiners, date_of_examination, reject_remarks);
						 stmt.setInt(1,Integer.parseInt( university_id));
						 stmt.setInt(2, Integer.parseInt(university_status));
						    System.err.println("c list---"+stmt);
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
				public String GenerateQueryWhereClause_SQL2(String Search,String subject,String name_of_examiners,String date_of_examination,String reject_remarks) {
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
				
				public PreparedStatement setQueryWhereClause_SQL2(PreparedStatement stmt, String Search,String subject,String name_of_examiners,
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
		
				//---------------------- Admitted ---------------------//

				public List<Map<String, Object>> getFilter_studentAdmitted_list(int startPage, int pageLength, String search,
						String orderColunm, String orderType, String university_id, String institute_id,String status,String student_name,String date_of_admission,
						String date_of_registration,String course_name,String rank,String marks,String all_india,String state,String admission_authority,String court_order,
						String qualification_name,String year_of_award_admission,String date_of_registration_state,String date_of_completion_md_part1,
						String date_of_completion_md_part2,String date_of_declaration_of_md,String date_of_completion_internship,String remarks,
						String reject_remarks) {
					
					String SearchValue = GenerateQueryWhereClause_SQL3(search,  student_name, date_of_admission,
							 date_of_registration, course_name, rank, marks, all_india, state, admission_authority, court_order,
							 qualification_name, year_of_award_admission, date_of_registration_state, date_of_completion_md_part1,
							 date_of_completion_md_part2, date_of_declaration_of_md, date_of_completion_internship, remarks,
							 reject_remarks);
					
							List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
							Connection conn = null;
							String q = "";
							try {

									conn = dataSource.getConnection();
						
									 q="select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.student_name,to_char(a.date_of_admission,'DD/MM/YYYY') as date_of_admission, \r\n"
											+ "to_char(a.date_of_registration,'DD/MM/YYYY') as date_of_registration,a.course_name, \r\n"
											+ "a.rank,a.marks,a.all_india,a.state,a.admission_authority,a.court_order,\r\n"
									 		+ "a.qualification_name,a.year_of_award_admission, \r\n"
									 		+ "to_char(a.date_of_registration_state,'DD/MM/YYYY') as date_of_registration_state, \r\n"
									 		+ "to_char(a.date_of_completion_md_part1,'DD/MM/YYYY') as date_of_completion_md_part1, \r\n"
									 		+ "to_char(a.date_of_completion_md_part2,'DD/MM/YYYY') as date_of_completion_md_part2, \r\n"
									 		+ "to_char(a.date_of_declaration_of_md,'DD/MM/YYYY') as date_of_declaration_of_md, \r\n"
									 		+ "to_char(a.date_of_completion_internship,'DD/MM/YYYY') as date_of_completion_internship,a.remarks \r\n"
									 		+ "from dg_rec_student_admitted_child_b a \r\n"
									 		+ "inner join logininformation li on li.userid=a.user_id\r\n"
									 		+ "where a.university_id=? and a.university_approved_status=? and a.user_id=? "+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
											+ pageLength + " OFFSET " + startPage;
									 
//									System.out.println("q---"+q);
									PreparedStatement stmt = conn.prepareStatement(q);
									stmt = setQueryWhereClause_SQL3(stmt,search, student_name, date_of_admission,
											 date_of_registration, course_name, rank, marks, all_india, state, admission_authority, court_order,
											 qualification_name, year_of_award_admission, date_of_registration_state, date_of_completion_md_part1,
											 date_of_completion_md_part2, date_of_declaration_of_md, date_of_completion_internship, remarks,
											 reject_remarks);
									stmt.setInt(1, Integer.parseInt(university_id));
									stmt.setInt(2, Integer.parseInt(status));
									stmt.setInt(3, Integer.parseInt(institute_id));
									
									System.err.println("--admitted---"+stmt);
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
									
									
									f1 ="<li><a id='id_add_attHospital"+countFunction+"' class='main-btn danger-btn-outline btn-hover btn-sm clarificationAdmittedData'  value='ADD'  title='Clarification Data' >"+ //id='id_add_attHospital1'
									//data-bs-toggle='modal' data-bs-target='#myModal'
													"<i class='lni lni-close'></i></a> </li> "
													+ "<input type='hidden' id='RejectAdmittedId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
									
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
				
//											//---------------- Admitted List Count -----------------//
					
				@Override
				public long getFilter_Admitted_pg_ListCount(String search, String university_id, String institute_id,String status,String student_name,String date_of_admission,
						String date_of_registration,String course_name,String rank,String marks,String all_india,String state,String admission_authority,String court_order,
						String qualification_name,String year_of_award_admission,String date_of_registration_state,String date_of_completion_md_part1,
						String date_of_completion_md_part2,String date_of_declaration_of_md,String date_of_completion_internship,String remarks,
						String reject_remarks){
					
					String SearchValue = GenerateQueryWhereClause_SQL3(search,  student_name, date_of_admission,
							 date_of_registration, course_name, rank, marks, all_india, state, admission_authority, court_order,
							 qualification_name, year_of_award_admission, date_of_registration_state, date_of_completion_md_part1,
							 date_of_completion_md_part2, date_of_declaration_of_md, date_of_completion_internship, remarks,
							 reject_remarks);
					
					int total = 0;
					Connection conn = null;
					String q = "";
					try {
						conn = dataSource.getConnection();			
						
						q="select count(*) from (select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.student_name,to_char(a.date_of_admission,'DD/MM/YYYY') as date_of_admission,\r\n"
								+"to_char(a.date_of_registration,'DD/MM/YYYY') as date_of_registration,a.course_name,\r\n"
								+"a.rank,a.marks,a.all_india,a.state,a.admission_authority,a.court_order,\r\n"
								+"a.qualification_name,a.year_of_award_admission,\r\n"
								+"to_char(a.date_of_registration_state,'DD/MM/YYYY') as date_of_registration_state,\r\n"
								+"to_char(a.date_of_completion_md_part1,'DD/MM/YYYY') as date_of_completion_md_part1, \r\n"
								+"to_char(a.date_of_completion_md_part2,'DD/MM/YYYY') as date_of_completion_md_part2, \r\n"
								+"to_char(a.date_of_declaration_of_md,'DD/MM/YYYY') as date_of_declaration_of_md, \r\n"
								+"to_char(a.date_of_completion_internship,'DD/MM/YYYY') as date_of_completion_internship,a.remarks \r\n"
								+"from dg_rec_student_admitted_child_b a \r\n"
								+"inner join logininformation li on li.userid=a.user_id \r\n"
								+"where a.university_id=? and a.university_approved_status=? and a.user_id=? " //a.university_id=? and a.inst_status=1 and a.university_approved_status=0 and a.council_approved_status=0
								+ ") a";
						
						PreparedStatement stmt = conn.prepareStatement(q);
						stmt = setQueryWhereClause_SQL3(stmt,search, student_name, date_of_admission,
								 date_of_registration, course_name, rank, marks, all_india, state, admission_authority, court_order,
								 qualification_name, year_of_award_admission, date_of_registration_state, date_of_completion_md_part1,
								 date_of_completion_md_part2, date_of_declaration_of_md, date_of_completion_internship, remarks,
								 reject_remarks);
						 stmt.setInt(1,Integer.parseInt( university_id));
						 stmt.setInt(2, Integer.parseInt(status));
							stmt.setInt(3, Integer.parseInt(institute_id));
						 System.err.println("UNI_APP_1_Count----"+stmt);
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
				public String GenerateQueryWhereClause_SQL3(String Search,String student_name,String date_of_admission,
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
				
				public PreparedStatement setQueryWhereClause_SQL3(PreparedStatement stmt, String Search,String student_name,String date_of_admission,
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
				
				//---------------------- Examiners ---------------------//

				public List<Map<String, Object>> getFilter_examiners_md_list(int startPage, int pageLength, String search,
						String orderColunm, String orderType, String university_id, String institute_id,String status,String name_of_homoeopathic_medical_college,
						String name_of_guide,String name_of_student_for_guide,String topic_of_dissertation,String whether_guide_participated_in_examination,
						String date_of_submission,String date_of_acceptance,String whether_student_published_article,String mention_details,
						String article_title,String month_year_exam,String reject_remarks) {
					
					String SearchValue = GenerateQueryWhereClause_SQL4(search,  name_of_homoeopathic_medical_college,
							 name_of_guide, name_of_student_for_guide, topic_of_dissertation, whether_guide_participated_in_examination,
							 date_of_submission, date_of_acceptance, whether_student_published_article, mention_details,
							 article_title, month_year_exam, reject_remarks);
					
							List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
							Connection conn = null;
							String q = "";
							try {

									conn = dataSource.getConnection();
						
									 q="select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.name_of_homoeopathic_medical_college,a.name_of_guide,a.name_of_student_for_guide, \r\n"
											+ "a.topic_of_dissertation,a.whether_guide_participated_in_examination,\r\n"
									 		+ "to_char(a.date_of_submission,'DD/MM/YYYY') as date_of_submission, \r\n"
									 		+ "to_char(a.date_of_acceptance,'DD/MM/YYYY') as date_of_acceptance, \r\n"
									 		+ "a.whether_student_published_article,a.mention_details, \r\n"
									 		+ "a.article_title,a.month_year_exam \r\n"
									 		+ "from dg_rec_examiners_md_child_b a \r\n"
									 		+ "inner join logininformation li on li.userid=a.user_id\r\n"
									 		+ "where a.university_id=? and a.university_approved_status=? and a.user_id=? "+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
											+ pageLength + " OFFSET " + startPage;
									 
//									System.out.println("q---"+q);
									PreparedStatement stmt = conn.prepareStatement(q);
									stmt = setQueryWhereClause_SQL4(stmt,search,name_of_homoeopathic_medical_college,
											 name_of_guide, name_of_student_for_guide, topic_of_dissertation, whether_guide_participated_in_examination,
											 date_of_submission, date_of_acceptance, whether_student_published_article, mention_details,
											 article_title, month_year_exam, reject_remarks);
									stmt.setInt(1, Integer.parseInt(university_id));
									stmt.setInt(2, Integer.parseInt(status));
									stmt.setInt(3, Integer.parseInt(institute_id));
									
									System.err.println("-examiners--md---"+stmt);
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
									
									
									f1 ="<li><a id='id_add_attHospital"+countFunction+"' class='main-btn danger-btn-outline btn-hover btn-sm clarificationExamimdData'  value='ADD'  title='Clarification Data' >"+ //id='id_add_attHospital1'
									//data-bs-toggle='modal' data-bs-target='#myModal'
													"<i class='lni lni-close'></i></a> </li> "
													+ "<input type='hidden' id='RejectExamimdId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
									
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
				
//											//---------------- Examiners List Count -----------------//
					
				@Override
				public long getFilter_examiners_md_ListCount(String search, String university_id, String institute_id,String status,String name_of_homoeopathic_medical_college,
						String name_of_guide,String name_of_student_for_guide,String topic_of_dissertation,String whether_guide_participated_in_examination,
						String date_of_submission,String date_of_acceptance,String whether_student_published_article,String mention_details,
						String article_title,String month_year_exam,String reject_remarks){
					
					String SearchValue = GenerateQueryWhereClause_SQL4(search,  name_of_homoeopathic_medical_college,
							 name_of_guide, name_of_student_for_guide, topic_of_dissertation, whether_guide_participated_in_examination,
							 date_of_submission, date_of_acceptance, whether_student_published_article, mention_details,
							 article_title, month_year_exam, reject_remarks);
					
					int total = 0;
					Connection conn = null;
					String q = "";
					try {
						conn = dataSource.getConnection();			
						
						q="select count(*) from (select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.name_of_homoeopathic_medical_college,a.name_of_guide,\r\n"
								+"a.name_of_student_for_guide,a.topic_of_dissertation,a.whether_guide_participated_in_examination,\r\n"
								+"to_char(a.date_of_submission,'DD/MM/YYYY') as date_of_submission,\r\n"
								+"to_char(a.date_of_acceptance,'DD/MM/YYYY') as date_of_acceptance, \r\n"
								+"a.whether_student_published_article,a.mention_details,a.article_title,a.month_year_exam \r\n"
								+"from dg_rec_examiners_md_child_b a \r\n"
								+"inner join logininformation li on li.userid=a.user_id \r\n"
								+"where a.university_id=? and a.university_approved_status=? and a.user_id=? " //a.university_id=? and a.inst_status=1 and a.university_approved_status=0 and a.council_approved_status=0
								+ ") a";
						
						PreparedStatement stmt = conn.prepareStatement(q);
						stmt = setQueryWhereClause_SQL4(stmt,search,name_of_homoeopathic_medical_college,
								 name_of_guide, name_of_student_for_guide, topic_of_dissertation, whether_guide_participated_in_examination,
								 date_of_submission, date_of_acceptance, whether_student_published_article, mention_details,
								 article_title, month_year_exam, reject_remarks);
						 stmt.setInt(1,Integer.parseInt( university_id));
						 stmt.setInt(2, Integer.parseInt(status));
							stmt.setInt(3, Integer.parseInt(institute_id));
						 System.err.println("examiners--count---"+stmt);
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
				public String GenerateQueryWhereClause_SQL4(String Search,String name_of_homoeopathic_medical_college,
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
				
				public PreparedStatement setQueryWhereClause_SQL4(PreparedStatement stmt, String Search,String name_of_homoeopathic_medical_college,
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
				
				//---------------------- Dissertation ---------------------//

				public List<Map<String, Object>> getFilter_disseration_list(int startPage, int pageLength, String search,
						String orderColunm, String orderType, String university_id, String institute_id,String status,String student_name_pg,String from_date,
						String to_date,String name_of_topic,String conclusion_obtain,String date_of_submission_pg,String reject_remarks,
						String publication,String mention_article) {
					
					String SearchValue = GenerateQueryWhereClause_SQL5(search,  student_name_pg, from_date,
							 to_date, name_of_topic, conclusion_obtain, date_of_submission_pg, reject_remarks,
							 publication, mention_article);
					
							List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
							Connection conn = null;
							String q = "";
							try {

									conn = dataSource.getConnection();
						
									 q="select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.student_name_pg, \r\n"
									 		+ "to_char(a.from_date,'DD/MM/YYYY') as from_date, \r\n"
									 		+ "to_char(a.to_date,'DD/MM/YYYY') as to_date, \r\n"
									 		+ "a.name_of_topic,a.conclusion_obtain, \r\n"
									 		+ "to_char(a.date_of_submission_pg,'DD/MM/YYYY') as date_of_submission_pg,a.publication,a.mention_article \r\n"
									 		+ "from dg_rec_work_done_pg_student_child_b a \r\n"
									 		+ "inner join logininformation li on li.userid=a.user_id\r\n"
									 		+ "where a.university_id=? and a.university_approved_status=? and a.user_id=? "+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
											+ pageLength + " OFFSET " + startPage;
									 
//									System.out.println("q---"+q);
									PreparedStatement stmt = conn.prepareStatement(q);
									stmt = setQueryWhereClause_SQL5(stmt,search,student_name_pg, from_date,
											 to_date, name_of_topic, conclusion_obtain, date_of_submission_pg, reject_remarks,
											 publication, mention_article);
									stmt.setInt(1, Integer.parseInt(university_id));
									stmt.setInt(2, Integer.parseInt(status));
									stmt.setInt(3, Integer.parseInt(institute_id));
									
									System.err.println("-examiners--md---"+stmt);
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
									
									
									f1 ="<li><a id='id_add_attHospital"+countFunction+"' class='main-btn danger-btn-outline btn-hover btn-sm clarificationdissertationData'  value='ADD'  title='Clarification Data' >"+ //id='id_add_attHospital1'
									//data-bs-toggle='modal' data-bs-target='#myModal'
													"<i class='lni lni-close'></i></a> </li> "
													+ "<input type='hidden' id='RejectdissertaId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
									
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
				
//											//---------------- Dissertation List Count -----------------//
					
				@Override
				public long getFilter_disseration_ListCount(String search, String university_id, String institute_id,String status,String student_name_pg,String from_date,
						String to_date,String name_of_topic,String conclusion_obtain,String date_of_submission_pg,String reject_remarks,
						String publication,String mention_article){
					
					String SearchValue = GenerateQueryWhereClause_SQL5(search,  student_name_pg, from_date,
							 to_date, name_of_topic, conclusion_obtain, date_of_submission_pg, reject_remarks,
							 publication, mention_article);
					
					int total = 0;
					Connection conn = null;
					String q = "";
					try {
						conn = dataSource.getConnection();			
						
						q="select count(*) from (select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.student_name_pg,\r\n"
								+"to_char(a.from_date,'DD/MM/YYYY') as from_date,\r\n"
								+"to_char(a.to_date,'DD/MM/YYYY') as to_date, \r\n"
								+"a.name_of_topic,a.conclusion_obtain,to_char(a.date_of_submission_pg,'DD/MM/YYYY') as date_of_submission_pg,a.publication,a.mention_article \r\n"
								+"from dg_rec_work_done_pg_student_child_b a \r\n"
								+"inner join logininformation li on li.userid=a.user_id \r\n"
								+"where a.university_id=? and a.university_approved_status=? and a.user_id=? " //a.university_id=? and a.inst_status=1 and a.university_approved_status=0 and a.council_approved_status=0
								+ ") a";
						
						PreparedStatement stmt = conn.prepareStatement(q);
						stmt = setQueryWhereClause_SQL5(stmt,search,student_name_pg, from_date,
								 to_date, name_of_topic, conclusion_obtain, date_of_submission_pg, reject_remarks,
								 publication, mention_article);
						 stmt.setInt(1,Integer.parseInt( university_id));
						 stmt.setInt(2, Integer.parseInt(status));
							stmt.setInt(3, Integer.parseInt(institute_id));
						 System.err.println("examiners--count---"+stmt);
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
				public String GenerateQueryWhereClause_SQL5(String Search,String student_name_pg,String from_date,
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
				
				public PreparedStatement setQueryWhereClause_SQL5(PreparedStatement stmt, String Search,String student_name_pg,String from_date,
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
				
				//---------------------- Lecture ---------------------//

				public List<Map<String, Object>> getFilter_lecture_list(int startPage, int pageLength, String search,
						String orderColunm, String orderType, String university_id, String institute_id,String status,String student_name,String student_class,
						String lecture_date,String lecture_day,String lecture_time,String topic,String reject_remarks) {
					
					String SearchValue = GenerateQueryWhereClause_SQL6(search,  student_name, student_class,
							 lecture_date, lecture_day, lecture_time, topic, reject_remarks);
					
							List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
							Connection conn = null;
							String q = "";
							try {

									conn = dataSource.getConnection();
						
									 q="select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.student_name, \r\n"
									 		+ "a.student_class,\r\n"
									 		+ "to_char(a.lecture_date,'DD/MM/YYYY') as lecture_date,a.lecture_day,a.lecture_time,a.topic \r\n"
									 		+ "from dg_rec_lecture_pg_student_child_b a \r\n"
									 		+ "inner join logininformation li on li.userid=a.user_id\r\n"
									 		+ "where a.university_id=? and a.university_approved_status=? and a.user_id=? "+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
											+ pageLength + " OFFSET " + startPage;
									 
//									System.out.println("q---"+q);
									PreparedStatement stmt = conn.prepareStatement(q);
									stmt = setQueryWhereClause_SQL6(stmt,search,student_name, student_class, lecture_date, lecture_day, lecture_time, topic, reject_remarks);
									stmt.setInt(1, Integer.parseInt(university_id));
									stmt.setInt(2, Integer.parseInt(status));
									stmt.setInt(3, Integer.parseInt(institute_id));
									
									System.err.println("-examiners--md---"+stmt);
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
									
									
									f1 ="<li><a id='id_add_attHospital"+countFunction+"' class='main-btn danger-btn-outline btn-hover btn-sm clarificationlectureData'  value='ADD'  title='Clarification Data' >"+ //id='id_add_attHospital1'
									//data-bs-toggle='modal' data-bs-target='#myModal'
													"<i class='lni lni-close'></i></a> </li> "
													+ "<input type='hidden' id='RejectlectId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
									
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
				
//											//---------------- Lecture List Count -----------------//
					
				@Override
				public long getFilter_lectuture_ListCount(String search, String university_id, String institute_id,String status,String student_name,String student_class,
						String lecture_date,String lecture_day,String lecture_time,String topic,String reject_remarks){
					
					String SearchValue = GenerateQueryWhereClause_SQL6(search, student_name, student_class,
							 lecture_date, lecture_day, lecture_time, topic, reject_remarks);
					
					int total = 0;
					Connection conn = null;
					String q = "";
					try {
						conn = dataSource.getConnection();			
						
						q="select count(*) from (select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.student_name,\r\n"
								+"a.student_class,to_char(a.lecture_date,'DD/MM/YYYY') as lecture_date,a.lecture_day,a.lecture_time,a.topic \r\n"
								+"from dg_rec_lecture_pg_student_child_b a \r\n"
								+"inner join logininformation li on li.userid=a.user_id \r\n"
								+"where a.university_id=? and a.university_approved_status=? and a.user_id=? " //a.university_id=? and a.inst_status=1 and a.university_approved_status=0 and a.council_approved_status=0
								+ ") a";
						
						PreparedStatement stmt = conn.prepareStatement(q);
						stmt = setQueryWhereClause_SQL6(stmt,search,student_name, student_class, lecture_date, lecture_day, lecture_time, topic, reject_remarks);

						 stmt.setInt(1,Integer.parseInt( university_id));
						 stmt.setInt(2, Integer.parseInt(status));
							stmt.setInt(3, Integer.parseInt(institute_id));
						 System.err.println("examiners--count---"+stmt);
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
				public String GenerateQueryWhereClause_SQL6(String Search,String student_name,String student_class,
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
				
				public PreparedStatement setQueryWhereClause_SQL6(PreparedStatement stmt, String Search,String student_name,String student_class,
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
				
				//---------------------- Assignment ---------------------//

				public List<Map<String, Object>> getFilter_assignment_list(int startPage, int pageLength, String search,
						String orderColunm, String orderType, String university_id, String institute_id,String status,String student_name_formc,
						String list_of_assignment_formc, String reject_remarks) {
					
					String SearchValue = GenerateQueryWhereClause_SQL8(search,  student_name_formc,
							 list_of_assignment_formc,  reject_remarks);
					
							List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
							Connection conn = null;
							String q = "";
							try {

									conn = dataSource.getConnection();
						
									 q="select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.student_name_formc, \r\n"
									 		+ "a.list_of_assignment_formc \r\n"
									 		+ "from dg_rec_assignment_pg_student_child_b a \r\n"
									 		+ "inner join logininformation li on li.userid=a.user_id\r\n"
									 		+ "where a.university_id=? and a.university_approved_status=? and a.user_id=? "+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
											+ pageLength + " OFFSET " + startPage;
									 
//									System.out.println("q---"+q);
									PreparedStatement stmt = conn.prepareStatement(q);
									stmt = setQueryWhereClause_SQL8(stmt,search, student_name_formc, list_of_assignment_formc,  reject_remarks);

									stmt.setInt(1, Integer.parseInt(university_id));
									stmt.setInt(2, Integer.parseInt(status));
									stmt.setInt(3, Integer.parseInt(institute_id));
									
									System.err.println("-examiners--md---"+stmt);
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
									
									
									f1 ="<li><a id='id_add_attHospital"+countFunction+"' class='main-btn danger-btn-outline btn-hover btn-sm clarificationAssignData'  value='ADD'  title='Clarification Data' >"+ //id='id_add_attHospital1'
											//data-bs-toggle='modal' data-bs-target='#myModal'
															"<i class='lni lni-close'></i></a> </li> "
															+ "<input type='hidden' id='RejectassignId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
									
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
				
//											//---------------- Assignment List Count -----------------//
					
				@Override
				public long getFilter_assignment_ListCount(String search, String university_id, String institute_id,String status,String student_name_formc,
						String list_of_assignment_formc, String reject_remarks){
					
					String SearchValue = GenerateQueryWhereClause_SQL8(search,  student_name_formc,
							 list_of_assignment_formc,  reject_remarks);
					
					int total = 0;
					Connection conn = null;
					String q = "";
					try {
						conn = dataSource.getConnection();			
						
						q="select count(*) from (select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.student_name_formc,\r\n"
								+"a.list_of_assignment_formc \r\n"
								+"from dg_rec_assignment_pg_student_child_b a \r\n"
								+"inner join logininformation li on li.userid=a.user_id \r\n"
								+"where a.university_id=? and a.university_approved_status=? and a.user_id=? " //a.university_id=? and a.inst_status=1 and a.university_approved_status=0 and a.council_approved_status=0
								+ ") a";
						
						PreparedStatement stmt = conn.prepareStatement(q);
						stmt = setQueryWhereClause_SQL8(stmt,search, student_name_formc, list_of_assignment_formc,  reject_remarks);

						 stmt.setInt(1,Integer.parseInt( university_id));
						 stmt.setInt(2, Integer.parseInt(status));
							stmt.setInt(3, Integer.parseInt(institute_id));
						 System.err.println("examiners--count---"+stmt);
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
				public String GenerateQueryWhereClause_SQL8(String Search,String student_name_formc,
						String list_of_assignment_formc, String reject_remarks) {
					
					String SearchValue="" ;
					
					if (Search!=null && !Search.equals("")) { // for Input Filter
						SearchValue += " and (upper(student_name_formc) Like ? "
								+ " or upper(list_of_assignment_formc) Like ? "
								+ " or upper(reject_remarks) Like ?) ";
					}
					return SearchValue;
				}
				
				public PreparedStatement setQueryWhereClause_SQL8(PreparedStatement stmt, String Search,String student_name_formc,
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
				
				//---------------------- Presentation ---------------------//

				public List<Map<String, Object>> getFilter_presentation_list(int startPage, int pageLength, String search,
						String orderColunm, String orderType, String university_id, String institute_id,String status,String student_name_presen,
						String list_of_presentations,String title_of_seminar_attended, String reject_remarks) {
					
					String SearchValue = GenerateQueryWhereClause_SQL9(search,  student_name_presen,
							list_of_presentations, title_of_seminar_attended, reject_remarks);
					
							List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
							Connection conn = null;
							String q = "";
							try {

									conn = dataSource.getConnection();
						
									 q="select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.student_name_presen, \r\n"
									 		+ "a.list_of_presentations,a.title_of_seminar_attended \r\n"
									 		+ "from dg_rec_presentation_seminar_student_child_b a \r\n"
									 		+ "inner join logininformation li on li.userid=a.user_id\r\n"
									 		+ "where a.university_id=? and a.university_approved_status=? and a.user_id=? "+ SearchValue + " ORDER BY a.id "+ orderType + " limit "
											+ pageLength + " OFFSET " + startPage;
									 
//									System.out.println("q---"+q);
									PreparedStatement stmt = conn.prepareStatement(q);
									stmt = setQueryWhereClause_SQL9(stmt,search, student_name_presen, list_of_presentations,title_of_seminar_attended , reject_remarks);

									stmt.setInt(1, Integer.parseInt(university_id));
									stmt.setInt(2, Integer.parseInt(status));
									stmt.setInt(3, Integer.parseInt(institute_id));
									
									System.err.println("-examiners--md---"+stmt);
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
									
									
									f1 ="<li><a id='id_add_attHospital"+countFunction+"' class='main-btn danger-btn-outline btn-hover btn-sm clarificationPreseData'  value='ADD'  title='Clarification Data' >"+ //id='id_add_attHospital1'
											//data-bs-toggle='modal' data-bs-target='#myModal'
															"<i class='lni lni-close'></i></a> </li> "
															+ "<input type='hidden' id='RejectpreId"+countFunction+"' value="+rs.getString("id")+"></i></a> </li>";
									
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
				
//											//---------------- Presentation List Count -----------------//
					
				@Override
				public long getFilter_presentation_ListCount(String search, String university_id, String institute_id,String status,String student_name_presen,
						String list_of_presentations,String title_of_seminar_attended, String reject_remarks){
					
					String SearchValue = GenerateQueryWhereClause_SQL9(search,  student_name_presen,
							list_of_presentations, title_of_seminar_attended, reject_remarks);
					
					int total = 0;
					Connection conn = null;
					String q = "";
					try {
						conn = dataSource.getConnection();			
						
						q="select count(*) from (select a.inst_status,li.login_name as inst_name,a.user_id,a.id,a.student_name_presen,\r\n"
								+"a.list_of_presentations,a.title_of_seminar_attended \r\n"
								+"from dg_rec_presentation_seminar_student_child_b a \r\n"
								+"inner join logininformation li on li.userid=a.user_id \r\n"
								+"where a.university_id=? and a.university_approved_status=? and a.user_id=? " //a.university_id=? and a.inst_status=1 and a.university_approved_status=0 and a.council_approved_status=0
								+ ") a";
						
						PreparedStatement stmt = conn.prepareStatement(q);
						stmt = setQueryWhereClause_SQL9(stmt,search, student_name_presen, list_of_presentations,title_of_seminar_attended , reject_remarks);

						 stmt.setInt(1,Integer.parseInt( university_id));
						 stmt.setInt(2, Integer.parseInt(status));
							stmt.setInt(3, Integer.parseInt(institute_id));
						 System.err.println("examiners--count---"+stmt);
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
				public String GenerateQueryWhereClause_SQL9(String Search,String student_name_presen,
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
				
				public PreparedStatement setQueryWhereClause_SQL9(PreparedStatement stmt, String Search,String student_name_presen,
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
}
	 

