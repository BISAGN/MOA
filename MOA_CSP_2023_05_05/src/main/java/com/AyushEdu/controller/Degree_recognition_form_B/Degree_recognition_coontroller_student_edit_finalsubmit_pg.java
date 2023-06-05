package com.AyushEdu.controller.Degree_recognition_form_B;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_ADMITTED_STUDENT;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_HOSPITAL_ATTACHED;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_INTERN_STUDENT_COURSE;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_MIGRATED_STUDENT_FROM;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_MIGRATED_STUDENT_SUB_CHILD;
import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_TRACK_STATUS;
import com.AyushEdu.Models.Degree_recognition_form_B.DG_REC_ASSIGNMENT_PG_STUDENT_B;
import com.AyushEdu.Models.Degree_recognition_form_B.DG_REC_EXAMINERS_MD_B;
import com.AyushEdu.Models.Degree_recognition_form_B.DG_REC_LECTURE_PG_STUDENT_B;
import com.AyushEdu.Models.Degree_recognition_form_B.DG_REC_PG_TRACK_STATUS;
import com.AyushEdu.Models.Degree_recognition_form_B.DG_REC_PRESENTATION_SEMINAR_STUDENT_B;
import com.AyushEdu.Models.Degree_recognition_form_B.DG_REC_STUDENTS_ADMITTED_B;
import com.AyushEdu.Models.Degree_recognition_form_B.DG_REC_WORK_DONE_PG_STUDENT_B;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Degree_recognition_form_B.Form_b_pg_Dao;
import com.AyushEdu.dao.Degree_recognition_form_B.student_edit_details_pgDao;

@Controller
@RequestMapping(value = {"admin", "/", "user"})
public class Degree_recognition_coontroller_student_edit_finalsubmit_pg {
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	student_edit_details_pgDao SDdao;
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	CommonController common;
	@Autowired
	Form_b_pg_Dao PDdao;

	//==========================================OPEN PAGE Student DataTable And FianlSubmit========================================== 

	@RequestMapping(value = "/StudentEdit_PG_Url", method = RequestMethod.GET)
	public ModelAndView StudentEdit_PG_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("StudentEdit_PG_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		Mmap.put("msg", msg);
//		Mmap.put("getInstituteList", common.getInstituteList(sessionFactory));
//		Mmap.put("getInstituteListbyUserID", PDdao.getInstituteListbyUserID());

		return new ModelAndView("Student_edit_finalsubmit_pg_Tiles");
	}	
	
	//-------------------------------------------View table Form B Admitted Student-------------------------------------------

		 @PostMapping("/getFilter_Admitted_Student_pg_list")	
		
		public @ResponseBody List<Map<String, Object>> getFilter_Admitted_Student_pg_list(HttpSession session,int startPage, int pageLength,
				String Search, String orderColunm, String orderType,int id,String institute_status,String student_name,String date_of_admission,
				String date_of_registration,String course_name,String rank,String marks,String all_india,String state,String admission_authority,String court_order,
				String qualification_name,String year_of_award_admission,String date_of_registration_state,String date_of_completion_md_part1,
				String date_of_completion_md_part2,String date_of_declaration_of_md,String date_of_completion_internship,String remarks,
				String reject_remarks) throws ParseException {
			 
			 String userId = session.getAttribute("userId").toString();
	   	     int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
		     int user_id=Integer.parseInt(userId);
		     
		     return SDdao.getFilter_Admitted_Student_pg_list(startPage, pageLength, Search, orderColunm, orderType,university_id,user_id,institute_status,student_name, date_of_admission,
					 date_of_registration, course_name, rank, marks, all_india, state, admission_authority, court_order,
					 qualification_name, year_of_award_admission, date_of_registration_state, date_of_completion_md_part1,
					 date_of_completion_md_part2, date_of_declaration_of_md, date_of_completion_internship, remarks,
					 reject_remarks);

	   }
		 @PostMapping("/getFilter_Admitted_StudentList_pg_Count")	
		 public @ResponseBody long getFilter_Admitted_StudentList_pg_Count(HttpSession session ,String Search,int id,String institute_status,String student_name,String date_of_admission,
					String date_of_registration,String course_name,String rank,String marks,String all_india,String state,String admission_authority,String court_order,
					String qualification_name,String year_of_award_admission,String date_of_registration_state,String date_of_completion_md_part1,
					String date_of_completion_md_part2,String date_of_declaration_of_md,String date_of_completion_internship,String remarks,
					String reject_remarks) throws ParseException {
				 
				 String userId = session.getAttribute("userId").toString();
				 int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
		     	 int user_id=Integer.parseInt(userId);
		     	 
			return SDdao.getFilter_Admitted_StudentList_pg_Count(Search,user_id,university_id,institute_status,student_name, date_of_admission,
					 date_of_registration, course_name, rank, marks, all_india, state, admission_authority, court_order,
					 qualification_name, year_of_award_admission, date_of_registration_state, date_of_completion_md_part1,
					 date_of_completion_md_part2, date_of_declaration_of_md, date_of_completion_internship, remarks,
					 reject_remarks);
	   }
	
	//==========================================OPEN PAGE Student Admitted Edit Data And Update==========================================

		@RequestMapping(value = "/Student_Edit_PG_Update_Url", method = RequestMethod.POST)
		public ModelAndView Student_Edit_PG_Update_Url(String eid,ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg,
				HttpServletRequest request) {
			//SECURITY ABHISHEK
			if(request.getHeader("Referer") == null ) { 
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("StudentEdit_PG_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				Mmap.put("eid", eid);
				System.err.println("----id----"+eid);
				Mmap.put("admitted_detail", SDdao.getadmittedPGByid(Integer.parseInt(eid)));
				Mmap.put("msg", msg);Mmap.put("course_name", common.getCourseNamelist(sessionFactory));
				Mmap.put("course_name", common.getCourseNamelist(sessionFactory));
			
			return new ModelAndView("Student_edit_pg_update_Tiles");
		}
		
	/////////// edit action Admitted Student PG //////////////
		
		@RequestMapping(value = "/edit_admittedstudent_pg_Action", method = RequestMethod.POST)
		public ModelAndView edit_admittedstudent_pg_Action(HttpServletRequest request, ModelMap model, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
			//SECURITY ABHISHEK
			if(request.getHeader("Referer") == null ) { 
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("StudentEdit_PG_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			if(request.getHeader("Referer") == null ) { 
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/login");
			 }
			
			 DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			 String username = session.getAttribute("username").toString();
			 String id = request.getParameter("eid");
			 String student_name = request.getParameter("student_name");
			 String date_of_admission = request.getParameter("date_of_admission");
			 String date_of_registration = request.getParameter("date_of_registration");
			 String course_name = request.getParameter("course_name");
	         String rank =request.getParameter("rank");
	         String marks = request.getParameter("marks");
	         String all_india = request.getParameter("all_india");   
	         String state = request.getParameter("state");
	         String admission_authority =request.getParameter("admission_authority");
	         String court_order = request.getParameter("court_order");
	         String qualification_name=request.getParameter("qualification_name");
	         String year_of_award_admission=request.getParameter("year_of_award_admission");
		     String date_of_registration_state = request.getParameter("date_of_registration_state");
	         String date_of_completion_md_part1 = request.getParameter("date_of_completion_md_part1");
	         String date_of_completion_md_part2 = request.getParameter("date_of_completion_md_part2");
	         String date_of_declaration_of_md = request.getParameter("date_of_declaration_of_md");
	         String date_of_completion_internship = request.getParameter("date_of_completion_internship");
	         String remarks = request.getParameter("remarks");
	         
	         System.err.println("----id----"+id);
	         
	          Date dt_adm = null;
			  Date dt_reg = null;
			  Date dt_reg_state = null;
			  Date dt_completion = null;
			  Date dt_completion_md = null;
			  Date dt_declaration = null;
			  Date dt_completion_int = null;
			
			  if (!date_of_admission.trim().equals("") && !date_of_admission.equals("DD/MM/YYYY")) {
				  dt_adm = format.parse(date_of_admission);
			  }
			  if (!date_of_registration.trim().equals("") && !date_of_registration.equals("DD/MM/YYYY")) {
				  dt_reg = format.parse(date_of_registration);
			  }
			  if (!date_of_registration_state.trim().equals("") && !date_of_registration_state.equals("DD/MM/YYYY")) {
				  dt_reg_state = format.parse(date_of_registration_state);
			  }
			  if (!date_of_completion_md_part1.trim().equals("") && !date_of_completion_md_part1.equals("DD/MM/YYYY")) {
				   dt_completion = format.parse(date_of_completion_md_part1);
			  }
			  if (!date_of_completion_md_part2.trim().equals("") && !date_of_completion_md_part2.equals("DD/MM/YYYY")) {
				  dt_completion_md = format.parse(date_of_completion_md_part2);
			  }
			  if (!date_of_declaration_of_md.trim().equals("") && !date_of_declaration_of_md.equals("DD/MM/YYYY")) {
				  dt_declaration = format.parse(date_of_declaration_of_md);
			  }
			  if (!date_of_completion_internship.trim().equals("") && !date_of_completion_internship.equals("DD/MM/YYYY")) {
				  dt_completion_int = format.parse(date_of_completion_internship);
			  }
			  
			try {
			Session session1 = this.sessionFactory.openSession();
			Transaction tx = session1.beginTransaction();
		
					String hql = "update DG_REC_STUDENTS_ADMITTED_B set modified_by=:modified_by,modified_date=:modified_date,student_name=:student_name,"
							+ "date_of_admission=:date_of_admission,date_of_registration=:date_of_registration,course_name=:course_name,"
							+ "rank=:rank,marks=:marks,all_india=:all_india,state=:state,admission_authority=:admission_authority,"
							+ "court_order=:court_order,qualification_name=:qualification_name,year_of_award_admission=:year_of_award_admission,"
							+ "date_of_registration_state=:date_of_registration_state,date_of_completion_md_part1=:date_of_completion_md_part1,"
							+ "date_of_completion_md_part2=:date_of_completion_md_part2,date_of_declaration_of_md=:date_of_declaration_of_md,"
							+ "date_of_completion_internship=:date_of_completion_internship,remarks=:remarks where id=:id";

				Query query = session1.createQuery(hql)
							.setParameter("student_name", student_name)
							.setParameter("date_of_admission", dt_adm)
							.setParameter("date_of_registration", dt_reg)
							.setParameter("course_name", course_name)
							.setParameter("rank", Integer.parseInt(rank))
							.setParameter("marks", Integer.parseInt(marks))
							.setParameter("all_india", all_india)
							.setParameter("state", state)
							.setParameter("admission_authority", admission_authority)
							.setParameter("court_order", court_order)
							.setParameter("qualification_name", qualification_name)
							.setParameter("year_of_award_admission", year_of_award_admission)
							.setParameter("date_of_registration_state", dt_reg_state)
							.setParameter("date_of_completion_md_part1", dt_completion)
							.setParameter("date_of_completion_md_part2", dt_completion_md)
							.setParameter("date_of_declaration_of_md", dt_declaration)
							.setParameter("date_of_completion_internship", dt_completion_int)
							.setParameter("remarks", remarks)
							.setParameter("modified_by", username)
							.setParameter("modified_date", new Date())
							.setParameter("id", Integer.parseInt(id));
							msg = query.executeUpdate() > 0 ? "1" : "0";
					tx.commit();

					if (msg.equals("1")) {
						ra.addAttribute("msg", "Data Updated Successfully.");
					} else {
						ra.addAttribute("msg", "Data Not Updated.");
					}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return new ModelAndView("redirect:StudentEdit_PG_Url");
		}
		
		//-------------------------------------------View table Form PG Examiners Student-------------------------------------------

		 @PostMapping("/getFilter_Examiners_Student_pg_List")	
		
		public @ResponseBody List<Map<String, Object>> getFilter_Examiners_Student_pg_List(HttpSession session,int startPage, int pageLength,
				String Search, String orderColunm, String orderType,int id,String institute_status,String name_of_homoeopathic_medical_college,
				String name_of_guide,String name_of_student_for_guide,String topic_of_dissertation,String whether_guide_participated_in_examination,
				String date_of_submission,String date_of_acceptance,String whether_student_published_article,String mention_details,
				String article_title,String month_year_exam,String reject_remarks) throws ParseException {
			 
			 String userId = session.getAttribute("userId").toString();
			 int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
		     int user_id=Integer.parseInt(userId);
		     
		     return SDdao.getFilter_Examiners_Student_pg_List(startPage, pageLength, Search, orderColunm, orderType,university_id,user_id,institute_status,
		    		 name_of_homoeopathic_medical_college,
					 name_of_guide, name_of_student_for_guide, topic_of_dissertation, whether_guide_participated_in_examination,
					 date_of_submission, date_of_acceptance, whether_student_published_article, mention_details,
					 article_title, month_year_exam, reject_remarks);

	   }
		 @PostMapping("/getFilter_Examiners_Student_pgListCount")	
		 public @ResponseBody long getFilter_Examiners_Student_pgListCount(HttpSession session ,String Search,int id,String institute_status,String name_of_homoeopathic_medical_college,
					String name_of_guide,String name_of_student_for_guide,String topic_of_dissertation,String whether_guide_participated_in_examination,
					String date_of_submission,String date_of_acceptance,String whether_student_published_article,String mention_details,
					String article_title,String month_year_exam,String reject_remarks) throws ParseException {
				 
				 String userId = session.getAttribute("userId").toString();
				 int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
		     	 int user_id=Integer.parseInt(userId);
		     	 
			return SDdao.getFilter_Examiners_Student_pgListCount(Search,user_id,university_id,institute_status,name_of_homoeopathic_medical_college,
					 name_of_guide, name_of_student_for_guide, topic_of_dissertation, whether_guide_participated_in_examination,
					 date_of_submission, date_of_acceptance, whether_student_published_article, mention_details,
					 article_title, month_year_exam, reject_remarks);
	   }
		
		 
			//==========================================OPEN PAGE Student Examiners Pg Edit Data And Update==========================================

			@RequestMapping(value = "/Student_Edit_Examiners_PG_Update_Url", method = RequestMethod.POST)
			public ModelAndView Student_Edit_Examiners_PG_Update_Url(String expg_id,ModelMap Mmap, HttpSession session,
					@RequestParam(value = "msg", required = false) String msg,
					HttpServletRequest request) {
				//SECURITY ABHISHEK
				if(request.getHeader("Referer") == null ) { 
					Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("StudentEdit_PG_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
					Mmap.put("expg_id", expg_id);
					System.err.println("----id--ex--"+expg_id);
					Mmap.put("admitted_detail", SDdao.getmigratedPGByid(Integer.parseInt(expg_id)));
					Mmap.put("msg", msg);Mmap.put("course_name", common.getCourseNamelist(sessionFactory));
					Mmap.put("getInstituteList", common.getInstituteList(sessionFactory));
				
				return new ModelAndView("Student_edit_examiners_pg_update_Tiles");
			}
			
			/////////// edit action Examiners Student PG //////////////
			
			@RequestMapping(value = "/edit_examinerstudent_pg_Action", method = RequestMethod.POST)
			public ModelAndView edit_examinerstudent_pg_Action(HttpServletRequest request, ModelMap model, HttpSession session,
					@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
				//SECURITY ABHISHEK
				if(request.getHeader("Referer") == null ) { 
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("StudentEdit_PG_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				if(request.getHeader("Referer") == null ) { 
					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/login");
				 }
				
				 DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				 String username = session.getAttribute("username").toString();
				 String id = request.getParameter("expg_id");
				 String name_of_homoeopathic_medical_college = request.getParameter("name_of_homoeopathic_medical_college");
				 String name_of_guide = request.getParameter("name_of_guide");
		         String name_of_student_for_guide =request.getParameter("name_of_student_for_guide");
		         String topic_of_dissertation = request.getParameter("topic_of_dissertation");
		         String whether_guide_participated_in_examination = request.getParameter("whether_guide_participated_in_examination");   
		         String date_of_submission = request.getParameter("date_of_submission");
		         String date_of_acceptance =request.getParameter("date_of_acceptance");
		         String whether_student_published_article = request.getParameter("whether_student_published_article");
		         String mention_details=request.getParameter("mention_details");
		         String article_title=request.getParameter("article_title");
		         String month_year_exam=request.getParameter("month_year_exam");
		         
		          Date dt_sub = null;
				  Date dt_acc = null;
				
				  if (!date_of_submission.trim().equals("") && !date_of_submission.equals("DD/MM/YYYY")) {
					  dt_sub = format.parse(date_of_submission);
				  }
				  if (!date_of_acceptance.trim().equals("") && !date_of_acceptance.equals("DD/MM/YYYY")) {
					  dt_acc = format.parse(date_of_acceptance);
				  }
				  
				  
				  
			//try {
				Session session1 = this.sessionFactory.openSession();
				Transaction tx = session1.beginTransaction();
			
				String hql = "update DG_REC_EXAMINERS_MD_B set modified_by=:modified_by,modified_date=:modified_date,name_of_homoeopathic_medical_college=:name_of_homoeopathic_medical_college,"
						+ "name_of_guide=:name_of_guide,name_of_student_for_guide=:name_of_student_for_guide,topic_of_dissertation=:topic_of_dissertation,"
						+ "whether_guide_participated_in_examination=:whether_guide_participated_in_examination,date_of_submission=:date_of_submission,"
						+ "date_of_acceptance=:date_of_acceptance,whether_student_published_article=:whether_student_published_article,mention_details=:mention_details,"
						+ "article_title=:article_title,"
						+ "month_year_exam=:month_year_exam where id=:id";

					Query query = session1.createQuery(hql)
								.setParameter("name_of_homoeopathic_medical_college", name_of_homoeopathic_medical_college)
								.setParameter("name_of_guide", name_of_guide)
								.setParameter("name_of_student_for_guide", name_of_student_for_guide)
								.setParameter("topic_of_dissertation", topic_of_dissertation)
								.setParameter("whether_guide_participated_in_examination", whether_guide_participated_in_examination)
								.setParameter("date_of_submission", dt_sub)
								.setParameter("date_of_acceptance", dt_acc)
								.setParameter("whether_student_published_article", whether_student_published_article)
								.setParameter("mention_details", mention_details)
								.setParameter("article_title", article_title)
								.setParameter("month_year_exam", month_year_exam)
								.setParameter("modified_by", username)
								.setParameter("modified_date", new Date())
								.setParameter("id", Integer.parseInt(id));
								msg = query.executeUpdate() > 0 ? "1" : "0";
						tx.commit();

						if (msg.equals("1")) {
							ra.addAttribute("msg", "Data Updated Successfully.");
						} else {
							ra.addAttribute("msg", "Data Not Updated.");
						}
//				}catch (Exception e) {
//					e.printStackTrace();
//				}
				return new ModelAndView("redirect:StudentEdit_PG_Url");
			}
			

			//-------------------------------------------View table Form PG Dissertation Student-------------------------------------------

			 @PostMapping("/getFilter_Dissertation_Student_pg_List")	
			
			public @ResponseBody List<Map<String, Object>> getFilter_Dissertation_Student_pg_List(HttpSession session,int startPage, int pageLength,
					String Search, String orderColunm, String orderType,int id,String institute_status,String student_name_pg,String from_date,
					String to_date,String name_of_topic,String conclusion_obtain,String date_of_submission_pg,String reject_remarks,
					String publication,String mention_article) throws ParseException {
				 
				 String userId = session.getAttribute("userId").toString();
		   	     int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
			     int user_id=Integer.parseInt(userId);
			     
			     return SDdao.getFilter_Dissertation_Student_pg_List(startPage, pageLength, Search, orderColunm, orderType,university_id,user_id,institute_status,
			    		 student_name_pg, from_date,
						 to_date, name_of_topic, conclusion_obtain, date_of_submission_pg, reject_remarks,
						 publication, mention_article);

		   }
			 @PostMapping("/getFilter_Dissertation_Student_pgListCount")	
			 public @ResponseBody long getFilter_Dissertation_Student_pgListCount(HttpSession session ,String Search,int id,String institute_status,String student_name_pg,String from_date,
						String to_date,String name_of_topic,String conclusion_obtain,String date_of_submission_pg,String reject_remarks,
						String publication,String mention_article) throws ParseException {
					 
					 String userId = session.getAttribute("userId").toString();
					 int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
			     	 int user_id=Integer.parseInt(userId);
			     	 
				return SDdao.getFilter_Dissertation_Student_pgListCount(Search,user_id,university_id,institute_status, student_name_pg, from_date,
						 to_date, name_of_topic, conclusion_obtain, date_of_submission_pg, reject_remarks,
						 publication, mention_article);
		   }
			 
///////////////////////////////////////// Dissertation Open Model //////////////////////////////////////////////

			@PostMapping(value="/getdissertationdatafrom")
				@ResponseBody public List<Map<String, Object>> getdissertationdatafrom(String id) 
			{
				return SDdao.getdissertationdatafromByid(id);
			}
			
				///////////////////////// Update Dissertation //////////////////////
			
	@RequestMapping(value = "/edit_dissertation_Action", method = RequestMethod.POST)
	@ResponseBody public String edit_dissertation_Action(HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
		if(request.getHeader("Referer") == null ) { 
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return "";
		 }
		 DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		 String username = session.getAttribute("username").toString();
		 String id = request.getParameter("disse_id");
		 
		 String student_name_pg = request.getParameter("student_name_pg");
         String from_date =request.getParameter("from_date");
         String to_date = request.getParameter("to_date");
         String name_of_topic = request.getParameter("name_of_topic");   
         String conclusion_obtain = request.getParameter("conclusion_obtain");
         String date_of_submission_pg =request.getParameter("date_of_submission_pg");
         String publication =request.getParameter("publication");
         String mention_article =request.getParameter("mention_article");
         
         Date dm = null;
		 Date dt = null;
		 Date dt_sub = null;
		 
		  if (!from_date.trim().equals("") && !from_date.equals("DD/MM/YYYY")) {
			  dm = format.parse(from_date);
		  }
		  if (!to_date.trim().equals("") && !to_date.equals("DD/MM/YYYY")) {
			  dt = format.parse(to_date);
		  }
		  if (!date_of_submission_pg.trim().equals("") && !date_of_submission_pg.equals("DD/MM/YYYY")) {
			  dt_sub = format.parse(date_of_submission_pg);
		  }
		
		try {
		Session session1 = this.sessionFactory.openSession();
		Transaction tx = session1.beginTransaction();
	
				
				
				String hql = "update DG_REC_WORK_DONE_PG_STUDENT_B set modified_by=:modified_by,modified_date=:modified_date,student_name_pg=:student_name_pg,"
						+ "from_date=:from_date,to_date=:to_date,name_of_topic=:name_of_topic,"
						+ "conclusion_obtain=:conclusion_obtain,date_of_submission_pg=:date_of_submission_pg,publication=:publication,"
						+ "mention_article=:mention_article where id=:id";
				
			Query query = session1.createQuery(hql)
						.setParameter("student_name_pg", student_name_pg)
						.setParameter("from_date", dm)
						.setParameter("to_date", dt)
						.setParameter("name_of_topic", name_of_topic)
						.setParameter("conclusion_obtain", conclusion_obtain)
						.setParameter("date_of_submission_pg", dt_sub)
						.setParameter("publication", publication)
						.setParameter("mention_article", mention_article)
						.setParameter("modified_by", username)
						.setParameter("modified_date", new Date())
						.setParameter("id", Integer.parseInt(id));
			
						msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
				        tx.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	//-------------------------------------------View table Form PG Lecture Student-------------------------------------------

		 @PostMapping("/getFilter_Lecture_Student_pg_List")	
		
		public @ResponseBody List<Map<String, Object>> getFilter_Lecture_Student_pg_List(HttpSession session,int startPage, int pageLength,
				String Search, String orderColunm, String orderType,int id,String institute_status,String student_name,String student_class,
				String lecture_date,String lecture_day,String lecture_time,String topic,String reject_remarks) throws ParseException {
			 
			 String userId = session.getAttribute("userId").toString();
	   	     int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
		     int user_id=Integer.parseInt(userId);
		     
		     return SDdao.getFilter_Lecture_Student_pg_List(startPage, pageLength, Search, orderColunm, orderType,university_id,user_id,institute_status, student_name, student_class,
					 lecture_date, lecture_day, lecture_time, topic, reject_remarks);

	   }
		 @PostMapping("/getFilter_Lecture_Student_pgListCount")	
		 public @ResponseBody long getFilter_Lecture_Student_pgListCount(HttpSession session ,String Search,int id,String institute_status,String student_name,String student_class,
					String lecture_date,String lecture_day,String lecture_time,String topic,String reject_remarks) throws ParseException {
				 
				 String userId = session.getAttribute("userId").toString();
				 int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
		     	 int user_id=Integer.parseInt(userId);
		     	 
			return SDdao.getFilter_Lecture_Student_pgListCount(Search,user_id,university_id,institute_status, student_name, student_class,
					 lecture_date, lecture_day, lecture_time, topic, reject_remarks);
	   }
	 
	/////////////////////////////////////// Lecture To Open Model //////////////////////////////////////////////
	
	@PostMapping(value="/getlacturetakenpgdata")
	@ResponseBody public List<Map<String, Object>> getlacturetakenpgdata(String id) {
	System.err.println("-----in controler----"+id);
	return SDdao.getlacturetakenpgtoByid(id);
	}
	
	///////////////////////// edit action Lecture//////////////////////
	
	@RequestMapping(value = "/edit_lacture_taken_Action_PG", method = RequestMethod.POST)
	@ResponseBody public String edit_lacture_taken_Action_PG(HttpServletRequest request, ModelMap model, HttpSession session,
	@RequestParam(value = "msg", required = false) String msg) throws ParseException {
	if(request.getHeader("Referer") == null ) { 
	model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
	return "";
	}
	DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
	String username = session.getAttribute("username").toString();
	String id = request.getParameter("lt_id");
	
	String student_name = request.getParameter("student_name");
	String student_class =request.getParameter("student_class");
	String lecture_date = request.getParameter("lecture_date");
	String lecture_day = request.getParameter("lecture_day");   
	String lecture_time = request.getParameter("lecture_time");
	String topic =request.getParameter("topic");
	
	System.err.println("----id----"+id);
	
	Date ld = null;
	
	
	if (!lecture_date.trim().equals("") && !lecture_date.equals("DD/MM/YYYY")) {
	ld = format.parse(lecture_date);
	}
	
	try {
	Session session1 = this.sessionFactory.openSession();
	Transaction tx = session1.beginTransaction();
	
	String hql = "update DG_REC_LECTURE_PG_STUDENT_B set modified_by=:modified_by,modified_date=:modified_date,"
			+ "student_name=:student_name,student_class=:student_class,"
			+ "lecture_date=:lecture_date,lecture_day=:lecture_day,"
			+ "lecture_time=:lecture_time,topic=:topic"
			+ " where id=:id";
	
	Query query = session1.createQuery(hql)
			.setParameter("student_name", student_name)
			.setParameter("student_class", student_class)
			.setParameter("lecture_date", ld)
			.setParameter("lecture_day", lecture_day)
			.setParameter("lecture_time", lecture_time)
			.setParameter("topic", topic)
			.setParameter("modified_by", username)
			.setParameter("modified_date", new Date())
			.setParameter("id", Integer.parseInt(id));
	
			msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
	tx.commit();
	}catch (Exception e) {
	e.printStackTrace();
	}
	return msg;
	}
	

	//-------------------------------------------View table Form PG Assignment-------------------------------------------

		 @PostMapping("/getFilter_assignment_pg_List")	
		
		public @ResponseBody List<Map<String, Object>> getFilter_assignment_pg_List(HttpSession session,int startPage, int pageLength,
				String Search, String orderColunm, String orderType,int id,String institute_status,String student_name_formc,
				String list_of_assignment_formc, String reject_remarks) throws ParseException {
			 
			 String userId = session.getAttribute("userId").toString();
	   	     int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
		     int user_id=Integer.parseInt(userId);
		     
		     return SDdao.getFilter_assignment_pg_List(startPage, pageLength, Search, orderColunm, orderType,university_id,user_id,institute_status,
		    		 student_name_formc, list_of_assignment_formc,  reject_remarks);

	   }
		 @PostMapping("/getFilter_assignment_pgListCount")	
		 public @ResponseBody long getFilter_assignment_pgListCount(HttpSession session ,String Search,int id,String institute_status,String student_name_formc,
					String list_of_assignment_formc, String reject_remarks) throws ParseException {
				 
				 String userId = session.getAttribute("userId").toString();
		   	     int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
		     	 int user_id=Integer.parseInt(userId);
		     	 
			return SDdao.getFilter_assignment_pgListCount(Search,user_id,university_id,institute_status,student_name_formc, list_of_assignment_formc,  reject_remarks);
	   }
	 
///////////////////////////////////////// Assignment To Open Model //////////////////////////////////////////////

		@PostMapping(value="/getassignmentpgdata")
		@ResponseBody public List<Map<String, Object>> getassignmentpgdata(String id) {
		System.err.println("-----in controler----");
		return SDdao.getassignmentpgtoByid(id);
		}

///////////////////////// edit action assignment To //////////////////////

			@RequestMapping(value = "/edit_assignment_Action_PG", method = RequestMethod.POST)
			@ResponseBody public String edit_assignment_Action_PG(HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {
			if(request.getHeader("Referer") == null ) { 
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return "";
			}
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			String username = session.getAttribute("username").toString();
			String id = request.getParameter("ass_id");
			
			String student_name_formc = request.getParameter("student_name_formc");
			String list_of_assignment_formc =request.getParameter("list_of_assignment_formc");
			
			System.err.println("----id----"+id);
			
			try {
			Session session1 = this.sessionFactory.openSession();
			Transaction tx = session1.beginTransaction();
			
				String hql = "update DG_REC_ASSIGNMENT_PG_STUDENT_B set modified_by=:modified_by,modified_date=:modified_date,"
						+ "student_name_formc=:student_name_formc,list_of_assignment_formc=:list_of_assignment_formc"
						+ " where id=:id";
			
			Query query = session1.createQuery(hql)
						.setParameter("student_name_formc", student_name_formc)
						.setParameter("list_of_assignment_formc", list_of_assignment_formc)
						.setParameter("modified_by", username)
						.setParameter("modified_date", new Date())
						.setParameter("id", Integer.parseInt(id));
			
						msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
				tx.commit();
			}catch (Exception e) {
			e.printStackTrace();
			}
			return msg;
			}	
				
			//-------------------------------------------View table Form PG Presentation-------------------------------------------

			 @PostMapping("/getFilter_presentation_pg_List")	
			
			public @ResponseBody List<Map<String, Object>> getFilter_presentation_pg_List(HttpSession session,int startPage, int pageLength,
					String Search, String orderColunm, String orderType,int id,String institute_status,String student_name_presen,
					String list_of_presentations,String title_of_seminar_attended, String reject_remarks) throws ParseException {
				 
				 String userId = session.getAttribute("userId").toString();
		   	     int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
			     int user_id=Integer.parseInt(userId);
			     
			     return SDdao.getFilter_presentation_pg_List(startPage, pageLength, Search, orderColunm, orderType,university_id,user_id,institute_status,
			    		 student_name_presen, list_of_presentations,title_of_seminar_attended , reject_remarks);

		   }
			 @PostMapping("/getFilter_presentation_pgListCount")	
			 public @ResponseBody long getFilter_presentation_pgListCount(HttpSession session ,String Search,int id,String institute_status,String student_name_presen,
						String list_of_presentations,String title_of_seminar_attended, String reject_remarks) throws ParseException {
					 
					 String userId = session.getAttribute("userId").toString();
			   	     int university_id = Integer.parseInt(session.getAttribute("university_id").toString());
			     	 int user_id=Integer.parseInt(userId);
			     	 
				return SDdao.getFilter_presentation_pgListCount(Search,user_id,university_id,institute_status,student_name_presen, list_of_presentations,
						title_of_seminar_attended , reject_remarks);
		   }

			 
		///////////////////////////////////////// Presentation To Open Model //////////////////////////////////////////////

				@PostMapping(value="/getpresentationpgdata")
				@ResponseBody public List<Map<String, Object>> getpresentationpgdata(String id) {
				System.err.println("-----in controler----");
				return SDdao.getpresentationpgtoByid(id);
				}

//		///////////////////////// edit action Presentation To //////////////////////
				
					
					@RequestMapping(value = "/edit_presentation_Action_PG", method = RequestMethod.POST)
					@ResponseBody public String edit_presentation_Action_PG(HttpServletRequest request, ModelMap model, HttpSession session,
					@RequestParam(value = "msg", required = false) String msg) throws ParseException {
					if(request.getHeader("Referer") == null ) { 
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					return "";
					}
					String username = session.getAttribute("username").toString();
					String id = request.getParameter("pre_id");
					
					String student_name_presen = request.getParameter("student_name_presen");
					String list_of_presentations =request.getParameter("list_of_presentations");
					String title_of_seminar_attended =request.getParameter("title_of_seminar_attended");
					
					System.err.println("----id----"+id);
					
					try {
					Session session1 = this.sessionFactory.openSession();
					Transaction tx = session1.beginTransaction();
					
						String hql = "update DG_REC_PRESENTATION_SEMINAR_STUDENT_B set modified_by=:modified_by,modified_date=:modified_date,"
								+ "student_name_presen=:student_name_presen,list_of_presentations=:list_of_presentations,title_of_seminar_attended=:title_of_seminar_attended"
								+ " where id=:id";
					
					Query query = session1.createQuery(hql)
								.setParameter("student_name_presen", student_name_presen)
								.setParameter("list_of_presentations", list_of_presentations)
								.setParameter("title_of_seminar_attended",title_of_seminar_attended)
								.setParameter("modified_by", username)
								.setParameter("modified_date", new Date())
								.setParameter("id", Integer.parseInt(id));
					
								msg = query.executeUpdate() > 0 ? "Data Updated Successfully." : "Data Not Updated.";
						tx.commit();
					}catch (Exception e) {
					e.printStackTrace();
					}
					return msg;
					}	
					
					// --------------------------------------------Submit for approval-----institute------------------------------------

					@RequestMapping(value = "/Submit_Approval_Data_institute_pg", method = RequestMethod.POST)
					public @ResponseBody String Submit_Approval_Data_institute_pg(ModelMap Mmap, HttpSession session,
							HttpServletRequest request,String current_month_year,
							@RequestParam(value = "pgad_hid", required = false) int pgad_hid,
							@RequestParam(value = "pgex_hid", required = false) int pgex_hid,
							@RequestParam(value = "pgdis_hid", required = false) int pgdis_hid,
							@RequestParam(value = "pglec_hid", required = false) int pglec_hid,
							@RequestParam(value = "pgassi_hid", required = false) int pgassi_hid,
							@RequestParam(value = "pgpre_hid", required = false) int pgpre_hid,
							String id) throws ParseException {

						// System.err.println("-----in submit-----");

						String msg = "";
						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();

//						String userId = session.getAttribute("userId").toString();
						String user_id = session.getAttribute("userId").toString();
						String university_id = session.getAttribute("university_id").toString();
						String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
					    String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
					    String current_mm_yy = month+"-"+year;
						try {
							
							if(pgad_hid > 0) 
							{
								String hqlUpdate = "update from DG_REC_STUDENTS_ADMITTED_B set inst_status=:inst_status where user_id=:user_id";
								int app = sessionHQL.createQuery(hqlUpdate)
										  .setInteger("inst_status", 1)
									      .setInteger("user_id", Integer.parseInt(user_id))
									      .executeUpdate();
						
							Query q = sessionHQL.createQuery("from DG_REC_STUDENTS_ADMITTED_B where user_id=:user_id and inst_status=:inst_status")
									.setInteger("inst_status", 1)
							        .setInteger("user_id", Integer.parseInt(user_id));
							         @SuppressWarnings("unchecked")
							         
							         List<DG_REC_STUDENTS_ADMITTED_B> clist = (List<DG_REC_STUDENTS_ADMITTED_B>) q.list();
							
										clist.forEach((n) -> {
											DG_REC_PG_TRACK_STATUS trx = new DG_REC_PG_TRACK_STATUS();
											trx.setTbl_id(n.getId());
											trx.setCommi_status(0);
											trx.setInst_status(1);
											trx.setUni_status(0);
											trx.setInstitute_id(n.getUser_id());
											trx.setUniversity_id(n.getUniversity_id());
											trx.setMonth_year(n.getCurrent_month_year());
											sessionHQL.save(trx);		
											sessionHQL.flush();
											sessionHQL.clear();
										});
						}
							
						if(pgex_hid > 0)
						{
							String hqlUpdate1 = "update from DG_REC_EXAMINERS_MD_B set inst_status=:inst_status where user_id=:user_id";
							int app1 = sessionHQL.createQuery(hqlUpdate1).setInteger("inst_status", 1)
									.setInteger("user_id", Integer.parseInt(user_id)).executeUpdate();
							
							Query q = sessionHQL.createQuery("from DG_REC_EXAMINERS_MD_B where user_id=:user_id and inst_status=:inst_status")
									.setInteger("inst_status", 1)
							        .setInteger("user_id", Integer.parseInt(user_id));
							         @SuppressWarnings("unchecked")
							         
							         List<DG_REC_EXAMINERS_MD_B> clist = (List<DG_REC_EXAMINERS_MD_B>) q.list();
							
										clist.forEach((n) -> {
											DG_REC_PG_TRACK_STATUS trx = new DG_REC_PG_TRACK_STATUS();
											trx.setTbl_id(n.getId());
											trx.setCommi_status(0);
											trx.setInst_status(1);
											trx.setUni_status(0);
											trx.setInstitute_id(n.getUser_id());
											trx.setUniversity_id(n.getUniversity_id());
											trx.setMonth_year(n.getCurrent_month_year());
											sessionHQL.save(trx);		
											sessionHQL.flush();
											sessionHQL.clear();
										});
				           	  
						}
						if(pgdis_hid > 0) 
						{
							String hqlUpdate2 = "update from DG_REC_WORK_DONE_PG_STUDENT_B set inst_status=:inst_status where user_id=:user_id";
							int app2 = sessionHQL.createQuery(hqlUpdate2).setInteger("inst_status", 1)
									.setInteger("user_id", Integer.parseInt(user_id)).executeUpdate();
							
							Query q = sessionHQL.createQuery("from DG_REC_WORK_DONE_PG_STUDENT_B where user_id=:user_id and inst_status=:inst_status")
									.setInteger("inst_status", 1)
							        .setInteger("user_id", Integer.parseInt(user_id));
							         @SuppressWarnings("unchecked")
							         
							         List<DG_REC_WORK_DONE_PG_STUDENT_B> clist = (List<DG_REC_WORK_DONE_PG_STUDENT_B>) q.list();
							
										clist.forEach((n) -> {
											DG_REC_PG_TRACK_STATUS trx = new DG_REC_PG_TRACK_STATUS();
											trx.setTbl_id(n.getId());
											trx.setCommi_status(0);
											trx.setInst_status(1);
											trx.setUni_status(0);
											trx.setInstitute_id(n.getUser_id());
											trx.setUniversity_id(n.getUniversity_id());
											trx.setMonth_year(n.getCurrent_month_year());
											sessionHQL.save(trx);		
											sessionHQL.flush();
											sessionHQL.clear();
										});
				             
						}
						if(pglec_hid > 0) 
						{
							String hqlUpdate3 = "update from DG_REC_LECTURE_PG_STUDENT_B set inst_status=:inst_status where user_id=:user_id";
							int app3 = sessionHQL.createQuery(hqlUpdate3).setInteger("inst_status", 1)
									.setInteger("user_id", Integer.parseInt(user_id)).executeUpdate();
							
							Query q = sessionHQL.createQuery("from DG_REC_LECTURE_PG_STUDENT_B where user_id=:user_id and inst_status=:inst_status")
									.setInteger("inst_status", 1)
							        .setInteger("user_id", Integer.parseInt(user_id));
							         @SuppressWarnings("unchecked")
							         
							         List<DG_REC_LECTURE_PG_STUDENT_B> clist = (List<DG_REC_LECTURE_PG_STUDENT_B>) q.list();
							
										clist.forEach((n) -> {
											DG_REC_PG_TRACK_STATUS trx = new DG_REC_PG_TRACK_STATUS();
											trx.setTbl_id(n.getId());
											trx.setCommi_status(0);
											trx.setInst_status(1);
											trx.setUni_status(0);
											trx.setInstitute_id(n.getUser_id());
											trx.setUniversity_id(n.getUniversity_id());
											trx.setMonth_year(n.getCurrent_month_year());
											sessionHQL.save(trx);		
											sessionHQL.flush();
											sessionHQL.clear();
										});
						}
						
						if(pgassi_hid > 0) 
						{
							String hqlUpdate4 = "update from DG_REC_ASSIGNMENT_PG_STUDENT_B set inst_status=:inst_status where user_id=:user_id";
							int app4 = sessionHQL.createQuery(hqlUpdate4).setInteger("inst_status", 1)
									.setInteger("user_id", Integer.parseInt(user_id)).executeUpdate();
							
							Query q = sessionHQL.createQuery("from DG_REC_ASSIGNMENT_PG_STUDENT_B where user_id=:user_id and inst_status=:inst_status")
									.setInteger("inst_status", 1)
							        .setInteger("user_id", Integer.parseInt(user_id));
							         @SuppressWarnings("unchecked")
							         
							         List<DG_REC_ASSIGNMENT_PG_STUDENT_B> clist = (List<DG_REC_ASSIGNMENT_PG_STUDENT_B>) q.list();
							
										clist.forEach((n) -> {
											DG_REC_PG_TRACK_STATUS trx = new DG_REC_PG_TRACK_STATUS();
											trx.setTbl_id(n.getId());
											trx.setCommi_status(0);
											trx.setInst_status(1);
											trx.setUni_status(0);
											trx.setInstitute_id(n.getUser_id());
											trx.setUniversity_id(n.getUniversity_id());
											trx.setMonth_year(n.getCurrent_month_year());
											sessionHQL.save(trx);		
											sessionHQL.flush();
											sessionHQL.clear();
										});
						}
						if(pgpre_hid > 0) 
						{
							String hqlUpdate4 = "update from DG_REC_PRESENTATION_SEMINAR_STUDENT_B set inst_status=:inst_status where user_id=:user_id";
							int app4 = sessionHQL.createQuery(hqlUpdate4).setInteger("inst_status", 1)
									.setInteger("user_id", Integer.parseInt(user_id)).executeUpdate();
							
							Query q = sessionHQL.createQuery("from DG_REC_PRESENTATION_SEMINAR_STUDENT_B where user_id=:user_id and inst_status=:inst_status")
									.setInteger("inst_status", 1)
							        .setInteger("user_id", Integer.parseInt(user_id));
							         @SuppressWarnings("unchecked")
							         
							         List<DG_REC_PRESENTATION_SEMINAR_STUDENT_B> clist = (List<DG_REC_PRESENTATION_SEMINAR_STUDENT_B>) q.list();
							
										clist.forEach((n) -> {
											DG_REC_PG_TRACK_STATUS trx = new DG_REC_PG_TRACK_STATUS();
											trx.setTbl_id(n.getId());
											trx.setCommi_status(0);
											trx.setInst_status(1);
											trx.setUni_status(0);
											trx.setInstitute_id(n.getUser_id());
											trx.setUniversity_id(n.getUniversity_id());
											trx.setMonth_year(n.getCurrent_month_year());
											sessionHQL.save(trx);		
											sessionHQL.flush();
											sessionHQL.clear();
										});
						}
						
						if ((pgad_hid > 0) || (pgex_hid > 0) || (pgdis_hid > 0) || (pglec_hid > 0) || (pgassi_hid > 0) || (pgpre_hid > 0)) {
						msg = "Data Submitted Successfully";

				                //------email----------

//								String subject = "YourRegistrationForAlumniisSuccessfull";
//								String main_txt = " You have received this valid data from the Institute. "
//													 + "This Details has been Successfully Approved by Institute.So ,Check it and give it approval.";
//								String follow_txt = "Now , you can Login with your Email Id and <br> Password is : Bisag@123";
//								e.SendMail(request, subject,main_txt, follow_txt, "", "", "", "");

								// ------end email----------

								tx.commit();
							} else {
								msg = "Something Went Wrong !!!";
							}
							sessionHQL.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
						
//						//----notification---------
//						
//						String uni_id = String.valueOf(common.getAllInfoLogin(sessionFactory,user_id).get(0).getUniversity_id());
//						String userid = SDdao.getUni_user_id(uni_id).get(0).get(0);
//						String notimsg=" You have received this valid data from the Institute. "
//							       + "This Details has been Successfully Approved by Institute.So ,Check it and give the approval.";
//					     common.Notification(notimsg, userid,sessionFactory, session);
//					     
//					   //----end notification---------
//					     
						return msg;
					}
					
					//------------------------------Submit for Re-approval Rejected Data---institute----Admitted--------------------------------

					@RequestMapping(value = "/Reject_Data_institute_student_admitted", method = RequestMethod.POST)
					public @ResponseBody String Reject_Data_institute_student_admitted(ModelMap Mmap, HttpSession session,
							HttpServletRequest request) throws ParseException {

						String msg = "";
						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();

						String userId = session.getAttribute("userId").toString();

						try {

							String hqlUpdate = "update from DG_REC_STUDENTS_ADMITTED_B set inst_status=:inst_status,university_approved_status=:university_approved_status,council_approved_status=:council_approved_status where user_id=:user_id";
							int app = sessionHQL.createQuery(hqlUpdate)
									.setInteger("inst_status", 1)
									.setInteger("university_approved_status", 0)
									.setInteger("council_approved_status", 0)
									.setInteger("user_id", Integer.parseInt(userId))
									.executeUpdate();

							if ((app > 0)) {
								System.err.println("-----in if----" + app);
								msg = "Data Submitted Successfully";
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
					
					//------------------------------Submit for Re-approval Rejected Data---institute----Examiners-MD--------------------------------

					@RequestMapping(value = "/Reject_Data_institute_student_exami_md", method = RequestMethod.POST)
					public @ResponseBody String Reject_Data_institute_student_exami_md(ModelMap Mmap, HttpSession session,
							HttpServletRequest request) throws ParseException {

						String msg = "";
						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();

						String userId = session.getAttribute("userId").toString();

						try {

							String hqlUpdate = "update from DG_REC_EXAMINERS_MD_B set inst_status=:inst_status,university_approved_status=:university_approved_status,council_approved_status=:council_approved_status where user_id=:user_id";
							int app = sessionHQL.createQuery(hqlUpdate)
									.setInteger("inst_status", 1)
									.setInteger("university_approved_status", 0)
									.setInteger("council_approved_status", 0)
									.setInteger("user_id", Integer.parseInt(userId))
									.executeUpdate();

							if ((app > 0)) {
								System.err.println("-----in if----" + app);
								msg = "Data Submitted Successfully";
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
					//------------------------------Submit for Re-approval Rejected Data---institute----Dissertation--------------------------------

					@RequestMapping(value = "/Reject_Data_institute_dissertation", method = RequestMethod.POST)
					public @ResponseBody String Reject_Data_institute_dissertation(ModelMap Mmap, HttpSession session,
							HttpServletRequest request) throws ParseException {

						String msg = "";
						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();

						String userId = session.getAttribute("userId").toString();

						try {

							String hqlUpdate = "update from DG_REC_WORK_DONE_PG_STUDENT_B set inst_status=:inst_status,university_approved_status=:university_approved_status,council_approved_status=:council_approved_status where user_id=:user_id";
							int app = sessionHQL.createQuery(hqlUpdate)
									.setInteger("inst_status", 1)
									.setInteger("university_approved_status", 0)
									.setInteger("council_approved_status", 0)
									.setInteger("user_id", Integer.parseInt(userId))
									.executeUpdate();

							if ((app > 0)) {
								System.err.println("-----in if----" + app);
								msg = "Data Submitted Successfully";
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
					//------------------------------Submit for Re-approval Rejected Data---institute--Lecture------------------------------

					@RequestMapping(value = "/Reject_Data_institute_lecture", method = RequestMethod.POST)
					public @ResponseBody String Reject_Data_institute_lecture(ModelMap Mmap, HttpSession session,
							HttpServletRequest request) throws ParseException {

						String msg = "";
						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();

						String userId = session.getAttribute("userId").toString();

						try {

							String hqlUpdate = "update from DG_REC_LECTURE_PG_STUDENT_B set inst_status=:inst_status,university_approved_status=:university_approved_status,council_approved_status=:council_approved_status where user_id=:user_id";
							int app = sessionHQL.createQuery(hqlUpdate)
									.setInteger("inst_status", 1)
									.setInteger("university_approved_status", 0)
									.setInteger("council_approved_status", 0)
									.setInteger("user_id", Integer.parseInt(userId))
									.executeUpdate();

							if ((app > 0)) {
								System.err.println("-----in if----" + app);
								msg = "Data Submitted Successfully";
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
					//------------------------------Submit for Re-approval Rejected Data---institute--Assignment------------------------------

					@RequestMapping(value = "/Reject_Data_institute_assignment", method = RequestMethod.POST)
					public @ResponseBody String Reject_Data_institute_assignment(ModelMap Mmap, HttpSession session,
							HttpServletRequest request) throws ParseException {

						String msg = "";
						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();

						String userId = session.getAttribute("userId").toString();

						try {

							String hqlUpdate = "update from DG_REC_ASSIGNMENT_PG_STUDENT_B set inst_status=:inst_status,university_approved_status=:university_approved_status,council_approved_status=:council_approved_status where user_id=:user_id";
							int app = sessionHQL.createQuery(hqlUpdate)
									.setInteger("inst_status", 1)
									.setInteger("university_approved_status", 0)
									.setInteger("council_approved_status", 0)
									.setInteger("user_id", Integer.parseInt(userId))
									.executeUpdate();

							if ((app > 0)) {
								System.err.println("-----in if----" + app);
								msg = "Data Submitted Successfully";
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
					//------------------------------Submit for Re-approval Rejected Data---institute--presentation------------------------------

					@RequestMapping(value = "/Reject_Data_institute_presentation", method = RequestMethod.POST)
					public @ResponseBody String Reject_Data_institute_presentation(ModelMap Mmap, HttpSession session,
							HttpServletRequest request) throws ParseException {

						String msg = "";
						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();

						String userId = session.getAttribute("userId").toString();

						try {

							String hqlUpdate = "update from DG_REC_PRESENTATION_SEMINAR_STUDENT_B set inst_status=:inst_status,university_approved_status=:university_approved_status,council_approved_status=:council_approved_status where user_id=:user_id";
							int app = sessionHQL.createQuery(hqlUpdate)
									.setInteger("inst_status", 1)
									.setInteger("university_approved_status", 0)
									.setInteger("council_approved_status", 0)
									.setInteger("user_id", Integer.parseInt(userId))
									.executeUpdate();

							if ((app > 0)) {
								System.err.println("-----in if----" + app);
								msg = "Data Submitted Successfully";
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
}

