package com.AyushEdu.controller.Degree_recognition_form_B;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_INSTITUTE_REGISTRATION;
import com.AyushEdu.controller.Degree_recognition_PG_PDF.Download_PDF_Admitted_Students_Report_PG;
import com.AyushEdu.controller.Degree_recognition_PG_PDF.Download_PDF_Assignment_Report_PG;
import com.AyushEdu.controller.Degree_recognition_PG_PDF.Download_PDF_Dissertation_Report_PG;
import com.AyushEdu.controller.Degree_recognition_PG_PDF.Download_PDF_Examiner_List_Report_PG;
import com.AyushEdu.controller.Degree_recognition_PG_PDF.Download_PDF_Examiners_MD_Report_PG;
import com.AyushEdu.controller.Degree_recognition_PG_PDF.Download_PDF_Postgraduate_Course_Report;
import com.AyushEdu.controller.Degree_recognition_PG_PDF.Download_PDF_Presentation_Report_PG;
import com.AyushEdu.controller.Degree_recognition_PG_PDF.Download_PDF_Teaching_Staff_Report_PG;
import com.AyushEdu.controller.Degree_recognition_PG_PDF.Download_PDF_lecture_Report_PG;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Degree_recognition_form_B.Degree_reco_council_pg_Dao;


@Controller
@RequestMapping(value = {"admin", "/", "user"})

public class Degree_recognition_contoller_council_pg {
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	Degree_reco_council_pg_Dao pgdao;
	@Autowired
	CommonController common;
	@Autowired
	private SessionFactory sessionFactory;
	//==========================================OPEN PAGE DEGREE RECOGNITION========================================== 
	
		@RequestMapping(value = "/Degree_reco_council_pg", method = RequestMethod.GET)
		public ModelAndView Degree_reco_council_pg(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
			//SECURITY ABHISHEK
			if(request.getHeader("Referer") == null ) { 
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Degree_reco_council_pg", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String roleid = session.getAttribute("roleid").toString();
			Mmap.put("getMedUniversityName", common.getMedUniversityName(sessionFactory));
			return new ModelAndView("dr_council_pg_Tiles");
		}
		
		@RequestMapping(value = "/getInstituteUrlPG", method = RequestMethod.POST)
		public @ResponseBody List<EDU_LMS_INSTITUTE_REGISTRATION> getInstituteUrlPG(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "selval", required = false) Integer selval, HttpServletRequest request) {
			
			System.err.println("selval------>>>"+selval);

			List<EDU_LMS_INSTITUTE_REGISTRATION> list = pgdao.getuniversitylistUrl(selval);

			return list;
		}
		//-------------------------------------------View table PG Form A-------------------------------------------

		 @PostMapping("/getFilter_PG_a")	
		
		public @ResponseBody List<Map<String, Object>> getFilter_PG_alists(HttpSession session,int startPage, int pageLength,
				String Search, String orderColunm, String orderType,int id,String uni_id,String institute_id,String institute_status,String name_of_applicant_university,String postgraduate_course, String abbre_postgraduate_course,String academic_session,String name_of_college,
				String country,String state,String district,String city,String postal_address,String email,String website,
				String academic_year_applied_for,String permission_from_central_gov,String admission_intake,String num_of_student_admitted,
				String remarks,String reject_remarks) throws ParseException {
				
			 String university_id = session.getAttribute("university_id").toString();

		     return pgdao.getFilter_PG_alist(startPage, pageLength, Search, orderColunm, orderType,university_id,uni_id,institute_id,institute_status,
		    		 name_of_applicant_university, postgraduate_course,  abbre_postgraduate_course, academic_session,
					 name_of_college, country, state, district, city, postal_address, email, website,
					 academic_year_applied_for, permission_from_central_gov, admission_intake, num_of_student_admitted,
					 remarks, reject_remarks);

	    }
		 @PostMapping("/getFilter_PG_aListCount")	
		 public @ResponseBody long getFilter_PG_aListCount(HttpSession session,
					String Search, String orderColunm, String orderType,int id,String uni_id,String institute_id,String institute_status,String name_of_applicant_university,String postgraduate_course, String abbre_postgraduate_course,String academic_session,String name_of_college,
					String country,String state,String district,String city,String postal_address,String email,String website,
					String academic_year_applied_for,String permission_from_central_gov,String admission_intake,String num_of_student_admitted,
					String remarks,String reject_remarks) throws ParseException {
			 String university_id = session.getAttribute("university_id").toString();

		return pgdao.getFilter_PG_aListCount(Search, orderColunm, orderType,university_id,uni_id,institute_id,institute_status,name_of_applicant_university, postgraduate_course,  abbre_postgraduate_course, academic_session,
				 name_of_college, country, state, district, city, postal_address, email, website,
				 academic_year_applied_for, permission_from_central_gov, admission_intake, num_of_student_admitted,
				 remarks, reject_remarks);
	    } 
		//-------------------------------------------View table PG Form B-------------------------------------------

		 @PostMapping("/getFilter_PG_b")	
		
		public @ResponseBody List<Map<String, Object>> getFilter_PG_blists(HttpSession session,int startPage, int pageLength,
				String Search, String orderColunm, String orderType,int id,String uni_id,String institute_id,String institute_status,String student_name,String date_of_admission,
				String date_of_registration,String course_name,String rank,String marks,String all_india,String state,String admission_authority,String court_order,
				String qualification_name,String year_of_award_admission,String date_of_registration_state,String date_of_completion_md_part1,
				String date_of_completion_md_part2,String date_of_declaration_of_md,String date_of_completion_internship,String remarks,
				String reject_remarks) throws ParseException {
				
			 String university_id = session.getAttribute("university_id").toString();

		     return pgdao.getFilter_PG_blist(startPage, pageLength, Search, orderColunm, orderType,university_id,uni_id,institute_id,institute_status,student_name, date_of_admission,
					 date_of_registration, course_name, rank, marks, all_india, state, admission_authority, court_order,
					 qualification_name, year_of_award_admission, date_of_registration_state, date_of_completion_md_part1,
					 date_of_completion_md_part2, date_of_declaration_of_md, date_of_completion_internship, remarks,
					 reject_remarks);

	    }
		 @PostMapping("/getFilter_PG_bListCount")	
		 public @ResponseBody long getFilter_PG_bListCount(HttpSession session,
					String Search, String orderColunm, String orderType,int id,String uni_id,String institute_id,String institute_status,String student_name,String date_of_admission,
					String date_of_registration,String course_name,String rank,String marks,String all_india,String state,String admission_authority,String court_order,
					String qualification_name,String year_of_award_admission,String date_of_registration_state,String date_of_completion_md_part1,
					String date_of_completion_md_part2,String date_of_declaration_of_md,String date_of_completion_internship,String remarks,
					String reject_remarks) throws ParseException {
			 String university_id = session.getAttribute("university_id").toString();

		return pgdao.getFilter_PG_bListCount(Search, orderColunm, orderType,university_id,uni_id,institute_id,institute_status,student_name, date_of_admission,
				 date_of_registration, course_name, rank, marks, all_india, state, admission_authority, court_order,
				 qualification_name, year_of_award_admission, date_of_registration_state, date_of_completion_md_part1,
				 date_of_completion_md_part2, date_of_declaration_of_md, date_of_completion_internship, remarks,
				 reject_remarks);
	    } 
		//-------------------------------------------View table PG Form C-------------------------------------------

		 @PostMapping("/getFilter_PG_c")	
		
		public @ResponseBody List<Map<String, Object>> getFilter_PG_clists(HttpSession session,int startPage, int pageLength,
				String Search, String orderColunm, String orderType,int id,String uni_id,String institute_id,String institute_status,String name_of_college_pg,String name_of_teaching_staff, String phone,String email_id,String designation,String department,
				String registration_no,String date_of_reg,String date_of_birth,String qualification_awarding_authority,String year_of_award,String date_of_appointment,
				String fulltime_parttime,String post_teaching,String exp_from,String exp_to,String total_teaching_exp_in_year,String reject_remarks) throws ParseException {
			 String university_id = session.getAttribute("university_id").toString();

		     return pgdao.getFilter_PG_clist(startPage, pageLength, Search, orderColunm, orderType,university_id,uni_id,institute_id,institute_status,
		    		 name_of_college_pg, name_of_teaching_staff,  phone, email_id, designation, department,
					 registration_no, date_of_reg, date_of_birth, qualification_awarding_authority, year_of_award, date_of_appointment,
					 fulltime_parttime, post_teaching, exp_from, exp_to, total_teaching_exp_in_year, reject_remarks);

	    }
		 @PostMapping("/getFilter_PG_cListCount")	
		 public @ResponseBody long getFilter_PG_cListCount(HttpSession session,
					String Search, String orderColunm, String orderType,int id,String uni_id,String institute_id,String institute_status,String name_of_college_pg,String name_of_teaching_staff, String phone,String email_id,String designation,String department,
					String registration_no,String date_of_reg,String date_of_birth,String qualification_awarding_authority,String year_of_award,String date_of_appointment,
					String fulltime_parttime,String post_teaching,String exp_from,String exp_to,String total_teaching_exp_in_year,String reject_remarks) throws ParseException {
			 String university_id = session.getAttribute("university_id").toString();
		 
		return pgdao.getFilter_PG_cListCount(Search, orderColunm, orderType,university_id,uni_id,institute_id,institute_status, name_of_college_pg, name_of_teaching_staff,  phone, email_id, designation, department,
				 registration_no, date_of_reg, date_of_birth, qualification_awarding_authority, year_of_award, date_of_appointment,
				 fulltime_parttime, post_teaching, exp_from, exp_to, total_teaching_exp_in_year, reject_remarks);
	    } 
		//-------------------------------------------View table PG Form D-------------------------------------------

		 @PostMapping("/getFilter_PG_d")	
		
		public @ResponseBody List<Map<String, Object>> getFilter_PG_dlists(HttpSession session,int startPage, int pageLength,
				String Search, String orderColunm, String orderType,int id,String uni_id,String institute_id,String institute_status,String name_of_homoeopathic_medical_college,
				String name_of_guide,String name_of_student_for_guide,String topic_of_dissertation,String whether_guide_participated_in_examination,
				String date_of_submission,String date_of_acceptance,String whether_student_published_article,String mention_details,
				String article_title,String month_year_exam,String reject_remarks) throws ParseException {
			 String university_id = session.getAttribute("university_id").toString();

		     return pgdao.getFilter_PG_dlist(startPage, pageLength, Search, orderColunm, orderType,university_id,uni_id,institute_id,institute_status,
		    		 name_of_homoeopathic_medical_college,
					 name_of_guide, name_of_student_for_guide, topic_of_dissertation, whether_guide_participated_in_examination,
					 date_of_submission, date_of_acceptance, whether_student_published_article, mention_details,
					 article_title, month_year_exam, reject_remarks);

	    }
		 @PostMapping("/getFilter_PG_dListCount")	
		 public @ResponseBody long getFilter_PG_dListCount(HttpSession session,
					String Search, String orderColunm, String orderType,int id,String uni_id,String institute_id,String institute_status,String name_of_homoeopathic_medical_college,
					String name_of_guide,String name_of_student_for_guide,String topic_of_dissertation,String whether_guide_participated_in_examination,
					String date_of_submission,String date_of_acceptance,String whether_student_published_article,String mention_details,
					String article_title,String month_year_exam,String reject_remarks) throws ParseException {
			 String university_id = session.getAttribute("university_id").toString();
		 
		return pgdao.getFilter_PG_dListCount(Search, orderColunm, orderType,university_id,uni_id,institute_id,institute_status,name_of_homoeopathic_medical_college,
				 name_of_guide, name_of_student_for_guide, topic_of_dissertation, whether_guide_participated_in_examination,
				 date_of_submission, date_of_acceptance, whether_student_published_article, mention_details,
				 article_title, month_year_exam, reject_remarks);
	    } 
		 
		//-------------------------------------------View table PG Form E-A -------------------------------------------

		 @PostMapping("/getFilter_PG_ea")	
		
		public @ResponseBody List<Map<String, Object>> getFilter_PG_ealists(HttpSession session,int startPage, int pageLength,
				String Search, String orderColunm, String orderType,int id,String uni_id,String institute_id,String institute_status,String student_name_pg,String from_date,
				String to_date,String name_of_topic,String conclusion_obtain,String date_of_submission_pg,String reject_remarks,
				String publication,String mention_article) throws ParseException {
			 String university_id = session.getAttribute("university_id").toString();

		     return pgdao.getFilter_PG_ealists(startPage, pageLength, Search, orderColunm, orderType,university_id,uni_id,institute_id,institute_status,student_name_pg, from_date,
					 to_date, name_of_topic, conclusion_obtain, date_of_submission_pg, reject_remarks,
					 publication, mention_article);

	    }
		 @PostMapping("/getFilter_PG_eaListCount")	
		 public @ResponseBody long getFilter_PG_eaListCount(HttpSession session,
					String Search, String orderColunm, String orderType,int id,String uni_id,String institute_id,String institute_status,String student_name_pg,String from_date,
					String to_date,String name_of_topic,String conclusion_obtain,String date_of_submission_pg,String reject_remarks,
					String publication,String mention_article) throws ParseException {
			 String university_id = session.getAttribute("university_id").toString();
		 
		return pgdao.getFilter_PG_eaListCount(Search, orderColunm, orderType,university_id,uni_id,institute_id,institute_status,student_name_pg, from_date,
				 to_date, name_of_topic, conclusion_obtain, date_of_submission_pg, reject_remarks,
				 publication, mention_article);
	    } 
		 
		//-------------------------------------------View table PG Form E-B -------------------------------------------

		 @PostMapping("/getFilter_PG_eb")	
		
		public @ResponseBody List<Map<String, Object>> getFilter_PG_eblists(HttpSession session,int startPage, int pageLength,
				String Search, String orderColunm, String orderType,int id,String uni_id,String institute_id,String institute_status,String student_name,String student_class,
				String lecture_date,String lecture_day,String lecture_time,String topic,String reject_remarks) throws ParseException {
			 String university_id = session.getAttribute("university_id").toString();

		     return pgdao.getFilter_PG_eblists(startPage, pageLength, Search, orderColunm, orderType,university_id,uni_id,institute_id,institute_status,student_name, student_class,
					 lecture_date, lecture_day, lecture_time, topic, reject_remarks);

	    }
		 @PostMapping("/getFilter_PG_ebListCount")	
		 public @ResponseBody long getFilter_PG_ebListCount(HttpSession session,
					String Search, String orderColunm, String orderType,int id,String uni_id,String institute_id,String institute_status,String student_name,String student_class,
					String lecture_date,String lecture_day,String lecture_time,String topic,String reject_remarks) throws ParseException {
			 String university_id = session.getAttribute("university_id").toString();
		 
		return pgdao.getFilter_PG_ebListCount(Search, orderColunm, orderType,university_id,uni_id,institute_id,institute_status,student_name, student_class,
				 lecture_date, lecture_day, lecture_time, topic, reject_remarks);
	    } 
		//-------------------------------------------View table PG Form E-C -------------------------------------------

		 @PostMapping("/getFilter_PG_ec")	
		
		public @ResponseBody List<Map<String, Object>> getFilter_PG_eclists(HttpSession session,int startPage, int pageLength,
				String Search, String orderColunm, String orderType,int id,String uni_id,String institute_id,String institute_status,String student_name_formc,
				String list_of_assignment_formc, String reject_remarks) throws ParseException {
			 String university_id = session.getAttribute("university_id").toString();

		     return pgdao.getFilter_PG_eclists(startPage, pageLength, Search, orderColunm, orderType,university_id,uni_id,institute_id,institute_status,
		    		 student_name_formc, list_of_assignment_formc,  reject_remarks);

	    }
		 @PostMapping("/getFilter_PG_ecListCount")	
		 public @ResponseBody long getFilter_PG_ecListCount(HttpSession session,
					String Search, String orderColunm, String orderType,int id,String uni_id,String institute_id,String institute_status,String student_name_formc,
					String list_of_assignment_formc, String reject_remarks) throws ParseException {
			 String university_id = session.getAttribute("university_id").toString();
		 
		return pgdao.getFilter_PG_ecListCount(Search, orderColunm, orderType,university_id,uni_id,institute_id,institute_status,
				student_name_formc, list_of_assignment_formc,  reject_remarks);
	    } 
		 
		//-------------------------------------------View table PG Form E-D -------------------------------------------

		 @PostMapping("/getFilter_PG_ed")	
		
		public @ResponseBody List<Map<String, Object>> getFilter_PG_edlists(HttpSession session,int startPage, int pageLength,
				String Search, String orderColunm, String orderType,int id,String uni_id,String institute_id,String institute_status,String student_name_presen,
				String list_of_presentations,String title_of_seminar_attended, String reject_remarks) throws ParseException {
			 String university_id = session.getAttribute("university_id").toString();

		     return pgdao.getFilter_PG_edlists(startPage, pageLength, Search, orderColunm, orderType,university_id,uni_id,institute_id,institute_status,
		    		 student_name_presen, list_of_presentations,title_of_seminar_attended , reject_remarks);

	    }
		 @PostMapping("/getFilter_PG_edListCount")	
		 public @ResponseBody long getFilter_PG_edListCount(HttpSession session,
					String Search, String orderColunm, String orderType,int id,String uni_id,String institute_id,String institute_status,String student_name_presen,
					String list_of_presentations,String title_of_seminar_attended, String reject_remarks) throws ParseException {
			 String university_id = session.getAttribute("university_id").toString();
		 
		return pgdao.getFilter_PG_edListCount(Search, orderColunm, orderType,university_id,uni_id,institute_id,institute_status, student_name_presen, 
				list_of_presentations,title_of_seminar_attended , reject_remarks);
	    } 
		//-------------------------------------------View table PG Form F-------------------------------------------

		 @PostMapping("/getFilter_PG_f")	
		
		public @ResponseBody List<Map<String, Object>> getFilter_PG_flists(HttpSession session,int startPage, int pageLength,
				String Search, String orderColunm, String orderType,int id,String uni_id,String institute_id,String institute_status,String subject,String name_of_examiners,
				String date_of_examination,String reject_remarks) throws ParseException {
			 String university_id = session.getAttribute("university_id").toString();

		     return pgdao.getFilter_PG_flist(startPage, pageLength, Search, orderColunm, orderType,university_id,uni_id,institute_id,institute_status,
		    		 subject, name_of_examiners, date_of_examination, reject_remarks);

	    }
		 @PostMapping("/getFilter_PG_fListCount")	
		 public @ResponseBody long getFilter_PG_fListCount(HttpSession session,
					String Search, String orderColunm, String orderType,int id,String uni_id,String institute_id,String institute_status,String subject,String name_of_examiners,
					String date_of_examination,String reject_remarks) throws ParseException {
			 String university_id = session.getAttribute("university_id").toString();
	 
		return pgdao.getFilter_PG_fListCount(Search, orderColunm, orderType,university_id,uni_id,institute_id,institute_status,
				subject, name_of_examiners, date_of_examination, reject_remarks);
	    } 
		 
/////////////////// Reject Council ///////////////
			
@RequestMapping(value = "reject_action_council_pg", method = RequestMethod.POST)
	public @ResponseBody String reject_action_council_pg(ModelMap Mmap, String id,String reject_remarks,String actind,
			HttpSession session, HttpServletRequest request)
			throws ParseException {
					
 	String msg = "";
	Session sessionHQL = this.sessionFactory.openSession();
	Transaction tx = sessionHQL.beginTransaction();
			        
					try {
						
						
						
						String hqlUpdate1 = "update DG_REC_PG_FORM_B set university_approved_status=:university_approved_status,council_approved_status=:council_approved_status,reject_remarks=:reject_remarks where id=:id";
						int app1 = sessionHQL.createQuery(hqlUpdate1)
							.setInteger("university_approved_status",-1)
							.setInteger("council_approved_status",-1)
							.setString("reject_remarks",reject_remarks)
							.setInteger("id", Integer.parseInt(id))
						    .executeUpdate();
						
						String hqlUpdate2 = "update DG_REC_STUDENTS_ADMITTED_B set inst_status=:inst_status,council_approved_status=:council_approved_status,reject_remarks=:reject_remarks where id=:id";
						int app2 = sessionHQL.createQuery(hqlUpdate2)
							.setInteger("inst_status",-1)
							.setInteger("council_approved_status",-1)
							.setString("reject_remarks",reject_remarks)
							.setInteger("id", Integer.parseInt(id))
						    .executeUpdate();
						
						String hqlUpdate3 = "update DG_REC_TEACHING_STAFF_B set university_approved_status=:university_approved_status,council_approved_status=:council_approved_status,reject_remarks=:reject_remarks where id=:id";
						int app3 = sessionHQL.createQuery(hqlUpdate3)
							.setInteger("university_approved_status",-1)
							.setInteger("council_approved_status",-1)
							.setString("reject_remarks",reject_remarks)
							.setInteger("id", Integer.parseInt(id))
						    .executeUpdate();
						
						String hqlUpdate4 = "update DG_REC_EXAMINERS_MD_B set inst_status=:inst_status,council_approved_status=:council_approved_status,reject_remarks=:reject_remarks where id=:id";
						int app4 = sessionHQL.createQuery(hqlUpdate4)
							.setInteger("inst_status",-1)
							.setInteger("council_approved_status",-1)
							.setString("reject_remarks",reject_remarks)
							.setInteger("id", Integer.parseInt(id))
						    .executeUpdate();
						
						String hqlUpdate5 = "update DG_REC_WORK_DONE_PG_STUDENT_B set inst_status=:inst_status,council_approved_status=:council_approved_status,reject_remarks=:reject_remarks where id=:id";
						int app5 = sessionHQL.createQuery(hqlUpdate5)
							.setInteger("inst_status",-1)
							.setInteger("council_approved_status",-1)
							.setString("reject_remarks",reject_remarks)
							.setInteger("id", Integer.parseInt(id))
						    .executeUpdate();
						
						String hqlUpdate5b = "update DG_REC_LECTURE_PG_STUDENT_B set inst_status=:inst_status,council_approved_status=:council_approved_status,reject_remarks=:reject_remarks where id=:id";
						int app5b = sessionHQL.createQuery(hqlUpdate5b)
							.setInteger("inst_status",-1)
							.setInteger("council_approved_status",-1)
							.setString("reject_remarks",reject_remarks)
							.setInteger("id", Integer.parseInt(id))
						    .executeUpdate();
						
						String hqlUpdate5c = "update DG_REC_ASSIGNMENT_PG_STUDENT_B set inst_status=:inst_status,council_approved_status=:council_approved_status,reject_remarks=:reject_remarks where id=:id";
						int app5c = sessionHQL.createQuery(hqlUpdate5c)
							.setInteger("inst_status",-1)
							.setInteger("council_approved_status",-1)
							.setString("reject_remarks",reject_remarks)
							.setInteger("id", Integer.parseInt(id))
						    .executeUpdate();
						
						String hqlUpdate5d = "update DG_REC_PRESENTATION_SEMINAR_STUDENT_B set inst_status=:inst_status,council_approved_status=:council_approved_status,reject_remarks=:reject_remarks where id=:id";
						int app5d = sessionHQL.createQuery(hqlUpdate5d)
							.setInteger("inst_status",-1)
							.setInteger("council_approved_status",-1)
							.setString("reject_remarks",reject_remarks)
							.setInteger("id", Integer.parseInt(id))
						    .executeUpdate();
						
						String hqlUpdate6 = "update DG_REC_EXAMINER_LIST_PG_COURSE_B set university_approved_status=:university_approved_status,council_approved_status=:council_approved_status,reject_remarks=:reject_remarks where id=:id";
						int app6 = sessionHQL.createQuery(hqlUpdate6)
							.setInteger("university_approved_status",-1)
							.setInteger("council_approved_status",-1)
							.setString("reject_remarks",reject_remarks)
							.setInteger("id", Integer.parseInt(id))
						    .executeUpdate();
						

						tx.commit();
						sessionHQL.close();
					

						if ( (app1 > 0) || (app2 > 0) || (app3 > 0) || (app4 > 0) || (app5 > 0) || (app5b > 0) || (app5c > 0) || (app5d > 0) || (app6 > 0)) {
							msg = "Rejected Successfully";
						} else {
							msg = "Rejection Unsuccessfull";
						}
						
						sessionHQL.close();
					  } catch (Exception e) {
						  e.printStackTrace();
					}
					return msg;
				}		
//--------------------------------------------Submit for approval-----institute------------------------------------

@RequestMapping(value = "/Final_Submit_Council_PG", method = RequestMethod.POST)
public @ResponseBody String Final_Submit_Council_PG(ModelMap Mmap, HttpSession session, HttpServletRequest request)
		throws ParseException {
				
				//System.err.println("-----in submit-----");
				
				String msg = "";
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
		        
				Calendar c = Calendar.getInstance();
				int year = c.get(Calendar.YEAR);
				int mnt = c.get(Calendar.MONTH);
				
				String cmoyr = (mnt+1)+"-"+year;
				
				System.err.println("cur mo yr ---"+cmoyr);
				
				try {

					String hqlUpdate = "update from DG_REC_PG_FORM_B set council_approved_status=:council_approved_status where current_month_year=:current_month_year";
					int app = sessionHQL.createQuery(hqlUpdate)
							.setInteger("council_approved_status",1).setString("current_month_year",cmoyr)
						    .executeUpdate();
					
					String hqlUpdate1 = "update from DG_REC_TEACHING_STAFF_B set council_approved_status=:council_approved_status where current_month_year=:current_month_year";
					int app1 = sessionHQL.createQuery(hqlUpdate1)
							.setInteger("council_approved_status",1).setString("current_month_year",cmoyr)
						    .executeUpdate();
					
					String hqlUpdate2 = "update from DG_REC_EXAMINER_LIST_PG_COURSE_B set council_approved_status=:council_approved_status where current_month_year=:current_month_year";
					int app2 = sessionHQL.createQuery(hqlUpdate2)
							.setInteger("council_approved_status",1).setString("current_month_year",cmoyr)
						    .executeUpdate();
				
					if ((app > 0) && (app1 > 0) && (app2 > 0)) {
						msg = "Data Approved Successfully";
						tx.commit();
					} else {
						msg = "Something Went Wrong !!!";
					}
					sessionHQL.close();
				  } catch (Exception e) {
					  e.printStackTrace();
				}
				return msg;
			}		
			
						//////////////////////////////Council View Data PG //////////////////////
						
						@PostMapping(value="/getviewdatapga")
						@ResponseBody public List<Map<String, Object>> getviewdatapga(String id) {
						return pgdao.getviewdatapgaid(id);
						}
						
						@PostMapping(value="/getviewdatapgb")
						@ResponseBody public List<Map<String, Object>> getviewdatapgb(String id) {
						return pgdao.getviewdatapgbid(id);
						}
						
						@PostMapping(value="/getviewdatapgc")
						@ResponseBody public List<Map<String, Object>> getviewdatapgc(String id) {
						return pgdao.getviewdatapgcid(id);
						}
						
						@PostMapping(value="/getviewdatapgd")
						@ResponseBody public List<Map<String, Object>> getviewdatapgd(String id) {
						return pgdao.getviewdatapgdid(id);
						}

						@PostMapping(value="/getviewdatapge")
						@ResponseBody public List<Map<String, Object>> getviewdatapge(String id) {
						return pgdao.getviewdatapgeid(id);
						}
						
						@PostMapping(value="/getviewdatapgea")
						@ResponseBody public List<Map<String, Object>> getviewdatapgea(String id) {
						return pgdao.getviewdatapgeaid(id);
						}
						
						@PostMapping(value="/getviewdatapgeb")
						@ResponseBody public List<Map<String, Object>> getviewdatapgeb(String id) {
						return pgdao.getviewdatapgebid(id);
						}
						
						@PostMapping(value="/getviewdatapgec")
						@ResponseBody public List<Map<String, Object>> getviewdatapgec(String id) {
						return pgdao.getviewdatapgecid(id);
						}
						
						@PostMapping(value="/getviewdatapged")
						@ResponseBody public List<Map<String, Object>> getviewdatapged(String id) {
						return pgdao.getviewdatapgedid(id);
						}
						
	////////////////////View PDF ////////////////////
						
						
						//DOWNLOAD MAIN PDF
						@RequestMapping(value = "/Postgraduate_Course_Report_Url_pdf", method = RequestMethod.POST)
						public ModelAndView Postgraduate_Course_Report_Url_pdf(@RequestParam(value = "course_id1", required = false)String id,
								Authentication authentication,  HttpSession Session, HttpServletRequest request) {

							String userid = Session.getAttribute("userId_for_jnlp").toString();
				 
							ArrayList<ArrayList<String>> list = pgdao.getviewdatapgaidPDF(id);
									
									System.out.println("list  " + list);
							
									List<String> TH = new ArrayList<String>();
						

									String Heading = "\nIn Inspection";
									String username = Session.getAttribute("username").toString();
									return new ModelAndView(new Download_PDF_Postgraduate_Course_Report("P", TH, Heading, username,list),
											"userList", list);
								
					}
						
						@RequestMapping(value = "/PG_Admitted_Students_Report_Url_pdf", method = RequestMethod.POST)
						public ModelAndView PG_Admitted_Students_Report_Url_pdf(@RequestParam(value = "add_stud_pg", required = false)String id,
								Authentication authentication,  HttpSession Session, HttpServletRequest request,ModelMap Mmap) {
							//SECURITY ABHISHEK
							if(request.getHeader("Referer") == null ) { 
								Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
								 return new ModelAndView("redirect:/landingpage");
							 }
							String roleid1 = Session.getAttribute("roleid").toString();
							 Boolean val = roledao.ScreenRedirect("Degree_reco_council_pg", roleid1);		
								if(val == false) {
									return new ModelAndView("AccessTiles");
							}
							String userid = Session.getAttribute("userId_for_jnlp").toString();
				 
							ArrayList<ArrayList<String>> list = pgdao.getviewdatapgbidPDF(id);
									
									System.out.println(id+"___id____list" + list);
									
									List<String> TH = new ArrayList<String>();

									String Heading = "\nIn Inspection";
									String username = Session.getAttribute("username").toString();
									return new ModelAndView(new Download_PDF_Admitted_Students_Report_PG("P", TH, Heading, username,list),
											"userList", list);
					}
						
						@RequestMapping(value = "/PG_Teaching_Staff_Report_Url_pdf", method = RequestMethod.POST)
						public ModelAndView PG_Teaching_Staff_Report_Url_pdf(@RequestParam(value = "teach_pg", required = false)String id,
								Authentication authentication,  HttpSession Session, HttpServletRequest request,ModelMap Mmap) {
							//SECURITY ABHISHEK
							if(request.getHeader("Referer") == null ) { 
								Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
								 return new ModelAndView("redirect:/landingpage");
							 }
							String roleid1 = Session.getAttribute("roleid").toString();
							 Boolean val = roledao.ScreenRedirect("Degree_reco_council_pg", roleid1);		
								if(val == false) {
									return new ModelAndView("AccessTiles");
							}
							String userid = Session.getAttribute("userId_for_jnlp").toString();
				 
							ArrayList<ArrayList<String>> list = pgdao.getviewdatapgcidPDF(id);
									
									System.out.println(id+"___id____list" + list);
									
									List<String> TH = new ArrayList<String>();

									String Heading = "\nIn Inspection";
									String username = Session.getAttribute("username").toString();
									return new ModelAndView(new Download_PDF_Teaching_Staff_Report_PG("P", TH, Heading, username,list),
											"userList", list);
					}
						@RequestMapping(value = "/PG_Examiners_MD_Report_Url_pdf", method = RequestMethod.POST)
						public ModelAndView PG_Examiners_MD_Report_Url_pdf(@RequestParam(value = "ex_pg", required = false)String id,
								Authentication authentication,  HttpSession Session, HttpServletRequest request,ModelMap Mmap) {
							//SECURITY ABHISHEK
							if(request.getHeader("Referer") == null ) { 
								Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
								 return new ModelAndView("redirect:/landingpage");
							 }
							String roleid1 = Session.getAttribute("roleid").toString();
							 Boolean val = roledao.ScreenRedirect("Degree_reco_council_pg", roleid1);		
								if(val == false) {
									return new ModelAndView("AccessTiles");
							}
							String userid = Session.getAttribute("userId_for_jnlp").toString();
				 
							ArrayList<ArrayList<String>> list = pgdao.getviewdatapgdidPDF(id);
									
									System.out.println(id+"___id____list" + list);
									
									List<String> TH = new ArrayList<String>();

									String Heading = "\nIn Inspection";
									String username = Session.getAttribute("username").toString();
									return new ModelAndView(new Download_PDF_Examiners_MD_Report_PG("P", TH, Heading, username,list),
											"userList", list);
					}
						
						@RequestMapping(value = "/PG_Dissertation_Report_Url_pdf", method = RequestMethod.POST)
						public ModelAndView PG_Dissertation_Report_Url_pdf(@RequestParam(value = "dis_pg", required = false)String id,
								Authentication authentication,  HttpSession Session, HttpServletRequest request) {

							String userid = Session.getAttribute("userId_for_jnlp").toString();
				 
							ArrayList<ArrayList<String>> list = pgdao.getviewdatapgeaidPDF(id);
									
									System.out.println(id+"___id____list" + list);
									
									List<String> TH = new ArrayList<String>();

									String Heading = "\nIn Inspection";
									String username = Session.getAttribute("username").toString();
									return new ModelAndView(new Download_PDF_Dissertation_Report_PG("P", TH, Heading, username,list),
											"userList", list);
					}
						
						@RequestMapping(value = "/PG_lecture_Report_Url_pdf", method = RequestMethod.POST)
						public ModelAndView PG_lecture_Report_Url_pdf(@RequestParam(value = "lec_pg", required = false)String id,
								Authentication authentication,  HttpSession Session, HttpServletRequest request) {

							String userid = Session.getAttribute("userId_for_jnlp").toString();
				 
							ArrayList<ArrayList<String>> list = pgdao.getviewdatapgebidPDF(id);
									
									System.out.println(id+"___id____list" + list);
									
									List<String> TH = new ArrayList<String>();

									String Heading = "\nIn Inspection";
									String username = Session.getAttribute("username").toString();
									return new ModelAndView(new Download_PDF_lecture_Report_PG("P", TH, Heading, username,list),
											"userList", list);
					}
						
						@RequestMapping(value = "/PG_Assignment_Report_Url_pdf", method = RequestMethod.POST)
						public ModelAndView PG_Assignment_Report_Url_pdf(@RequestParam(value = "ass_pg", required = false)String id,
								Authentication authentication,  HttpSession Session, HttpServletRequest request) {

							String userid = Session.getAttribute("userId_for_jnlp").toString();
				 
							ArrayList<ArrayList<String>> list = pgdao.getviewdatapgecidPDF(id);
									
									System.out.println(id+"___id____list" + list);
									
									List<String> TH = new ArrayList<String>();

									String Heading = "\nIn Inspection";
									String username = Session.getAttribute("username").toString();
									return new ModelAndView(new Download_PDF_Assignment_Report_PG("P", TH, Heading, username,list),
											"userList", list);
					}

						@RequestMapping(value = "/PG_Presentation_Report_Url_pdf", method = RequestMethod.POST)
						public ModelAndView PG_Presentation_Report_Url_pdf(@RequestParam(value = "pre_pg", required = false)String id,
								Authentication authentication,  HttpSession Session, HttpServletRequest request) {

							String userid = Session.getAttribute("userId_for_jnlp").toString();
				 
							ArrayList<ArrayList<String>> list = pgdao.getviewdatapgedidPDF(id);
									
									System.out.println(id+"___id____list" + list);
									
									List<String> TH = new ArrayList<String>();

									String Heading = "\nIn Inspection";
									String username = Session.getAttribute("username").toString();
									return new ModelAndView(new Download_PDF_Presentation_Report_PG("P", TH, Heading, username,list),
											"userList", list);
					}
						
						@RequestMapping(value = "/PG_Examiner_List_Report_Url_pdf", method = RequestMethod.POST)
						public ModelAndView PG_Examiner_List_Report_Url_pdf(@RequestParam(value = "exl_pg", required = false)String id,
								Authentication authentication,  HttpSession Session, HttpServletRequest request) {

							String userid = Session.getAttribute("userId_for_jnlp").toString();
				 
							ArrayList<ArrayList<String>> list = pgdao.getviewdatapgfidPDF(id);
									
									System.out.println(id+"___id____list" + list);
									
									List<String> TH = new ArrayList<String>();

									String Heading = "\nIn Inspection";
									String username = Session.getAttribute("username").toString();
									return new ModelAndView(new Download_PDF_Examiner_List_Report_PG("P", TH, Heading, username,list),
											"userList", list);
					}
}
