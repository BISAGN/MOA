package com.AyushEdu.controller.Degree_recognition_form_B;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.AyushEdu.Models.Degree_recognition_form_A.DG_REC_CLARIFICATION_DATA_HISTORY;
import com.AyushEdu.Models.Degree_recognition_form_B.DG_REC_CLARIFICATION_DATA_HISTORY_PG;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Degree_recognition_form_B.Form_b_pg_Dao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Degree_recognition_contoller_student_app_rej_university {

	private static final Session HibernateUtil = null;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	Form_b_pg_Dao PDdao;
	
	//==========================================OPEN PAGE INSTITUTE DETAILS UNIVERSITY APPROVED REJECT========================================== //
	
	 @RequestMapping(value = "/Institute_app_rej_PG_Url", method = RequestMethod.GET)
		public ModelAndView Institute_app_rej_PG_Url(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
			//SECURITY ABHISHEK
			if(request.getHeader("Referer") == null ) { 
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Institute_app_rej_PG_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String roleid = session.getAttribute("roleid").toString();
			Mmap.put("msg", msg);
			LocalDate date_without_time = LocalDate.now();
			Mmap.put("date_without_time", date_without_time);
			Mmap.put("getInstituteListbyUserID", PDdao.getInstituteListbyUserID());
			
			return new ModelAndView("Ins_app_rej_PG_Tiles");
		}
	 
	//-------------------------------------------View table Admitted Student-------------------------------------------

		 @PostMapping("/getFilter_studentAdmitted_list")	
		public @ResponseBody List<Map<String, Object>> getFilter_studentAdmitted_list(HttpSession session,int startPage, int pageLength,
				String Search, String orderColunm, String orderType,String id,String institute_id,String status,String student_name,String date_of_admission,
				String date_of_registration,String course_name,String rank,String marks,String all_india,String state,String admission_authority,String court_order,
				String qualification_name,String year_of_award_admission,String date_of_registration_state,String date_of_completion_md_part1,
				String date_of_completion_md_part2,String date_of_declaration_of_md,String date_of_completion_internship,String remarks,
				String reject_remarks) throws ParseException {
			 
			 String university_id = session.getAttribute("university_id").toString();
		     return PDdao.getFilter_studentAdmitted_list(startPage, pageLength, Search, orderColunm, orderType,university_id,institute_id,status,student_name, date_of_admission,
					 date_of_registration, course_name, rank, marks, all_india, state, admission_authority, court_order,
					 qualification_name, year_of_award_admission, date_of_registration_state, date_of_completion_md_part1,
					 date_of_completion_md_part2, date_of_declaration_of_md, date_of_completion_internship, remarks,
					 reject_remarks);
		     
	    }
		 @PostMapping("/getFilter_Admitted_pg_ListCount")	
		 public @ResponseBody long getFilter_Admitted_pg_ListCount(HttpSession session ,String Search,String id ,String institute_id,String status,String student_name,String date_of_admission,
					String date_of_registration,String course_name,String rank,String marks,String all_india,String state,String admission_authority,String court_order,
					String qualification_name,String year_of_award_admission,String date_of_registration_state,String date_of_completion_md_part1,
					String date_of_completion_md_part2,String date_of_declaration_of_md,String date_of_completion_internship,String remarks,
					String reject_remarks) throws ParseException {
			 
				 String university_id = session.getAttribute("university_id").toString();
			return PDdao.getFilter_Admitted_pg_ListCount(Search,university_id,institute_id,status,student_name, date_of_admission,
					 date_of_registration, course_name, rank, marks, all_india, state, admission_authority, court_order,
					 qualification_name, year_of_award_admission, date_of_registration_state, date_of_completion_md_part1,
					 date_of_completion_md_part2, date_of_declaration_of_md, date_of_completion_internship, remarks,
					 reject_remarks);
			
	    }  
		 
		//-------------------------------------------View table Examiners Student-------------------------------------------

			 @PostMapping("/getFilter_examiners_md_list")	
			public @ResponseBody List<Map<String, Object>> getFilter_examiners_md_list(HttpSession session,int startPage, int pageLength,
					String Search, String orderColunm, String orderType,String id,String institute_id,String status,String name_of_homoeopathic_medical_college,
					String name_of_guide,String name_of_student_for_guide,String topic_of_dissertation,String whether_guide_participated_in_examination,
					String date_of_submission,String date_of_acceptance,String whether_student_published_article,String mention_details,
					String article_title,String month_year_exam,String reject_remarks) throws ParseException {
				 
				 String university_id = session.getAttribute("university_id").toString();
			     return PDdao.getFilter_examiners_md_list(startPage, pageLength, Search, orderColunm, orderType,university_id,institute_id,status,name_of_homoeopathic_medical_college,
						 name_of_guide, name_of_student_for_guide, topic_of_dissertation, whether_guide_participated_in_examination,
						 date_of_submission, date_of_acceptance, whether_student_published_article, mention_details,
						 article_title, month_year_exam, reject_remarks);
			     
		    }
			 @PostMapping("/getFilter_examiners_md_ListCount")	
			 public @ResponseBody long getFilter_examiners_md_ListCount(HttpSession session ,String Search,String id ,String institute_id,String status,String name_of_homoeopathic_medical_college,
						String name_of_guide,String name_of_student_for_guide,String topic_of_dissertation,String whether_guide_participated_in_examination,
						String date_of_submission,String date_of_acceptance,String whether_student_published_article,String mention_details,
						String article_title,String month_year_exam,String reject_remarks) throws ParseException {
				 
					 String university_id = session.getAttribute("university_id").toString();
				return PDdao.getFilter_examiners_md_ListCount(Search,university_id,institute_id,status,name_of_homoeopathic_medical_college,
						 name_of_guide, name_of_student_for_guide, topic_of_dissertation, whether_guide_participated_in_examination,
						 date_of_submission, date_of_acceptance, whether_student_published_article, mention_details,
						 article_title, month_year_exam, reject_remarks);
				
		    }  
			 
			//-------------------------------------------View table Disseration Student-------------------------------------------

			 @PostMapping("/getFilter_disseration_list")	
			public @ResponseBody List<Map<String, Object>> getFilter_disseration_list(HttpSession session,int startPage, int pageLength,
					String Search, String orderColunm, String orderType,String id,String institute_id,String status,String student_name_pg,String from_date,
					String to_date,String name_of_topic,String conclusion_obtain,String date_of_submission_pg,String reject_remarks,
					String publication,String mention_article) throws ParseException {
				 
				 String university_id = session.getAttribute("university_id").toString();
			     return PDdao.getFilter_disseration_list(startPage, pageLength, Search, orderColunm, orderType,university_id,institute_id,status,student_name_pg, from_date,
						 to_date, name_of_topic, conclusion_obtain, date_of_submission_pg, reject_remarks,
						 publication, mention_article);
			     
		    }
			 @PostMapping("/getFilter_disseration_ListCount")	
			 public @ResponseBody long getFilter_disseration_ListCount(HttpSession session ,String Search,String id ,String institute_id,String status,String student_name_pg,String from_date,
						String to_date,String name_of_topic,String conclusion_obtain,String date_of_submission_pg,String reject_remarks,
						String publication,String mention_article) throws ParseException {
				 
					 String university_id = session.getAttribute("university_id").toString();
				return PDdao.getFilter_disseration_ListCount(Search,university_id,institute_id,status,student_name_pg, from_date,
						 to_date, name_of_topic, conclusion_obtain, date_of_submission_pg, reject_remarks,
						 publication, mention_article);
				
		    }
			//-------------------------------------------View table Lecture Taken Student-------------------------------------------

			 @PostMapping("/getFilter_lecture_list")	
			public @ResponseBody List<Map<String, Object>> getFilter_lecture_list(HttpSession session,int startPage, int pageLength,
					String Search, String orderColunm, String orderType,String id,String institute_id,String status,String student_name,String student_class,
					String lecture_date,String lecture_day,String lecture_time,String topic,String reject_remarks) throws ParseException {
				 
				 String university_id = session.getAttribute("university_id").toString();
			     return PDdao.getFilter_lecture_list(startPage, pageLength, Search, orderColunm, orderType,university_id,institute_id,status,student_name, student_class,
						 lecture_date, lecture_day, lecture_time, topic, reject_remarks);
			     
		    }
			 @PostMapping("/getFilter_lectuture_ListCount")	
			 public @ResponseBody long getFilter_lectuture_ListCount(HttpSession session ,String Search,String id ,String institute_id,String status,String student_name,String student_class,
						String lecture_date,String lecture_day,String lecture_time,String topic,String reject_remarks) throws ParseException {
				 
					 String university_id = session.getAttribute("university_id").toString();
				return PDdao.getFilter_lectuture_ListCount(Search,university_id,institute_id,status,student_name, student_class,
						 lecture_date, lecture_day, lecture_time, topic, reject_remarks);
				
		    }
			//-------------------------------------------View table Assignment Student-------------------------------------------

			 @PostMapping("/getFilter_assignment_list")	
			public @ResponseBody List<Map<String, Object>> getFilter_assignment_list(HttpSession session,int startPage, int pageLength,
					String Search, String orderColunm, String orderType,String id,String institute_id,String status,String student_name_formc,
					String list_of_assignment_formc, String reject_remarks) throws ParseException {
				 
				 String university_id = session.getAttribute("university_id").toString();
			     return PDdao.getFilter_assignment_list(startPage, pageLength, Search, orderColunm, orderType,university_id,institute_id,status,
			    		 student_name_formc, list_of_assignment_formc,  reject_remarks);
			     
		    }
			 @PostMapping("/getFilter_assignment_ListCount")	
			 public @ResponseBody long getFilter_assignment_ListCount(HttpSession session ,String Search,String id ,String institute_id,String status,String student_name_formc,
						String list_of_assignment_formc, String reject_remarks) throws ParseException {
				 
					 String university_id = session.getAttribute("university_id").toString();
				return PDdao.getFilter_assignment_ListCount(Search,university_id,institute_id,status, student_name_formc, list_of_assignment_formc,  reject_remarks);
				
		    }
			//-------------------------------------------View table Presentation Student-------------------------------------------

			 @PostMapping("/getFilter_presentation_list")	
			public @ResponseBody List<Map<String, Object>> getFilter_presentation_list(HttpSession session,int startPage, int pageLength,
					String Search, String orderColunm, String orderType,String id,String institute_id,String status,String student_name_presen,
					String list_of_presentations,String title_of_seminar_attended, String reject_remarks) throws ParseException {
				 
				 String university_id = session.getAttribute("university_id").toString();
			     return PDdao.getFilter_presentation_list(startPage, pageLength, Search, orderColunm, orderType,university_id,institute_id,status,
			    		 student_name_presen, list_of_presentations,title_of_seminar_attended , reject_remarks);
			     
		    }
			 @PostMapping("/getFilter_presentation_ListCount")	
			 public @ResponseBody long getFilter_presentation_ListCount(HttpSession session ,String Search,String id ,String institute_id,String status,String student_name_presen,
						String list_of_presentations,String title_of_seminar_attended, String reject_remarks) throws ParseException {
				 
					 String university_id = session.getAttribute("university_id").toString();
				return PDdao.getFilter_presentation_ListCount(Search,university_id,institute_id,status,student_name_presen, list_of_presentations,title_of_seminar_attended , reject_remarks);
				
		    }

/////////////////// Reject ///////////////
		
@RequestMapping(value = "reject_action_pg_ins", method = RequestMethod.POST)
public @ResponseBody String reject_action_pg_ins(ModelMap Mmap, String id,String reject_remarks,HttpSession session, HttpServletRequest request)
		throws ParseException {
				
	String msg = "";
	Session sessionHQL = this.sessionFactory.openSession();
	Transaction tx = sessionHQL.beginTransaction();
	DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
	String username = session.getAttribute("username").toString();
	Date date = new Date();
	String userId = request.getParameter("user_id");
	System.out.println("---user_id------"+userId);
	String university_id = session.getAttribute("university_id").toString();
		        
				try {
					
					String hqlUpdate = "update DG_REC_STUDENTS_ADMITTED_B set inst_status=:inst_status,university_approved_status=:university_approved_status,reject_remarks=:reject_remarks where id=:id";
					int app = sessionHQL.createQuery(hqlUpdate)
							.setInteger("university_approved_status",-1)
							.setInteger("inst_status",-1)
							.setString("reject_remarks",reject_remarks)
							.setInteger("id", Integer.parseInt(id))
						    .executeUpdate();
					
					Long c6 = (Long) sessionHQL.createQuery("select count(id) from  DG_REC_CLARIFICATION_DATA_HISTORY_PG where tbl_id=:id")
                            .setParameter("id",Integer.parseInt(id)).uniqueResult();
                     
                     if(c6 == 0) {
                    	 DG_REC_CLARIFICATION_DATA_HISTORY_PG trx = new DG_REC_CLARIFICATION_DATA_HISTORY_PG();					
								trx.setTbl_id(Integer.parseInt(id));
								trx.setClarification_remarks(reject_remarks);
								trx.setUniversity_id(Integer.parseInt(university_id));
								trx.setDate_of_current(date);
								trx.setCreated_date(date);
								trx.setCreated_by(username);
								sessionHQL.save(trx);		
								sessionHQL.flush();
								sessionHQL.clear();
                     }
                     
						// Examiners //	
                    String hqlUpdate1 = "update DG_REC_EXAMINERS_MD_B set inst_status=:inst_status,university_approved_status=:university_approved_status,reject_remarks=:reject_remarks where id=:id";
 					int app1 = sessionHQL.createQuery(hqlUpdate1)
 							.setInteger("university_approved_status",-1)
 							.setInteger("inst_status",-1)
 							.setString("reject_remarks",reject_remarks)
 							.setInteger("id", Integer.parseInt(id))
 						    .executeUpdate();
 					
 					Long c61 = (Long) sessionHQL.createQuery("select count(id) from  DG_REC_CLARIFICATION_DATA_HISTORY_PG where tbl_id=:id")
                             .setParameter("id",Integer.parseInt(id)).uniqueResult();
                      
                      if(c61 == 0) {
                     	 DG_REC_CLARIFICATION_DATA_HISTORY_PG trx = new DG_REC_CLARIFICATION_DATA_HISTORY_PG();					
 								trx.setTbl_id(Integer.parseInt(id));
 								trx.setClarification_remarks(reject_remarks);
 								trx.setUniversity_id(Integer.parseInt(university_id));
 								trx.setDate_of_current(date);
 								trx.setCreated_date(date);
 								trx.setCreated_by(username);
 								sessionHQL.save(trx);		
 								sessionHQL.flush();
 								sessionHQL.clear();
                      }
                   
                   // Dissertation //	
                    String hqlUpdate2 = "update DG_REC_WORK_DONE_PG_STUDENT_B set inst_status=:inst_status,university_approved_status=:university_approved_status,reject_remarks=:reject_remarks where id=:id";
   					int app2 = sessionHQL.createQuery(hqlUpdate2)
   							.setInteger("university_approved_status",-1)
   							.setInteger("inst_status",-1)
   							.setString("reject_remarks",reject_remarks)
   							.setInteger("id", Integer.parseInt(id))
   						    .executeUpdate();
   					
   					Long c62 = (Long) sessionHQL.createQuery("select count(id) from  DG_REC_CLARIFICATION_DATA_HISTORY_PG where tbl_id=:id")
                               .setParameter("id",Integer.parseInt(id)).uniqueResult();
                        
                        if(c62 == 0) {
                       	 DG_REC_CLARIFICATION_DATA_HISTORY_PG trx = new DG_REC_CLARIFICATION_DATA_HISTORY_PG();					
   								trx.setTbl_id(Integer.parseInt(id));
   								trx.setClarification_remarks(reject_remarks);
   								trx.setUniversity_id(Integer.parseInt(university_id));
   								trx.setDate_of_current(date);
   								trx.setCreated_date(date);
   								trx.setCreated_by(username);
   								sessionHQL.save(trx);		
   								sessionHQL.flush();
   								sessionHQL.clear();
                        }
                     
                     // Lecture //	
                        String hqlUpdate3 = "update DG_REC_LECTURE_PG_STUDENT_B set inst_status=:inst_status,university_approved_status=:university_approved_status,reject_remarks=:reject_remarks where id=:id";
       					int app3 = sessionHQL.createQuery(hqlUpdate3)
       							.setInteger("university_approved_status",-1)
       							.setInteger("inst_status",-1)
       							.setString("reject_remarks",reject_remarks)
       							.setInteger("id", Integer.parseInt(id))
       						    .executeUpdate();
       					
       					Long c63 = (Long) sessionHQL.createQuery("select count(id) from  DG_REC_CLARIFICATION_DATA_HISTORY_PG where tbl_id=:id")
                                   .setParameter("id",Integer.parseInt(id)).uniqueResult();
                            
                            if(c63 == 0) {
                           	 DG_REC_CLARIFICATION_DATA_HISTORY_PG trx = new DG_REC_CLARIFICATION_DATA_HISTORY_PG();					
       								trx.setTbl_id(Integer.parseInt(id));
       								trx.setClarification_remarks(reject_remarks);
       								trx.setUniversity_id(Integer.parseInt(university_id));
       								trx.setDate_of_current(date);
       								trx.setCreated_date(date);
       								trx.setCreated_by(username);
       								sessionHQL.save(trx);		
       								sessionHQL.flush();
       								sessionHQL.clear();
                            }
                            
                            // Assignment //
                            String hqlUpdate4 = "update DG_REC_ASSIGNMENT_PG_STUDENT_B set inst_status=:inst_status,university_approved_status=:university_approved_status,reject_remarks=:reject_remarks where id=:id";
           					int app4 = sessionHQL.createQuery(hqlUpdate4)
           							.setInteger("university_approved_status",-1)
           							.setInteger("inst_status",-1)
           							.setString("reject_remarks",reject_remarks)
           							.setInteger("id", Integer.parseInt(id))
           						    .executeUpdate();
           					
           					Long c64 = (Long) sessionHQL.createQuery("select count(id) from  DG_REC_CLARIFICATION_DATA_HISTORY_PG where tbl_id=:id")
                                       .setParameter("id",Integer.parseInt(id)).uniqueResult();
                                
                                if(c64 == 0) {
                               	 DG_REC_CLARIFICATION_DATA_HISTORY_PG trx = new DG_REC_CLARIFICATION_DATA_HISTORY_PG();					
           								trx.setTbl_id(Integer.parseInt(id));
           								trx.setClarification_remarks(reject_remarks);
           								trx.setUniversity_id(Integer.parseInt(university_id));
           								trx.setDate_of_current(date);
           								trx.setCreated_date(date);
           								trx.setCreated_by(username);
           								sessionHQL.save(trx);		
           								sessionHQL.flush();
           								sessionHQL.clear();
                                }
                             // Presentation //
                                String hqlUpdate5 = "update DG_REC_PRESENTATION_SEMINAR_STUDENT_B set inst_status=:inst_status,university_approved_status=:university_approved_status,reject_remarks=:reject_remarks where id=:id";
               					int app5 = sessionHQL.createQuery(hqlUpdate5)
               							.setInteger("university_approved_status",-1)
               							.setInteger("inst_status",-1)
               							.setString("reject_remarks",reject_remarks)
               							.setInteger("id", Integer.parseInt(id))
               						    .executeUpdate();
               					
               					Long c65 = (Long) sessionHQL.createQuery("select count(id) from  DG_REC_CLARIFICATION_DATA_HISTORY_PG where tbl_id=:id")
                                           .setParameter("id",Integer.parseInt(id)).uniqueResult();
                                    
                                    if(c65 == 0) {
                                   	 DG_REC_CLARIFICATION_DATA_HISTORY_PG trx = new DG_REC_CLARIFICATION_DATA_HISTORY_PG();					
               								trx.setTbl_id(Integer.parseInt(id));
               								trx.setClarification_remarks(reject_remarks);
               								trx.setUniversity_id(Integer.parseInt(university_id));
               								trx.setDate_of_current(date);
               								trx.setCreated_date(date);
               								trx.setCreated_by(username);
               								sessionHQL.save(trx);		
               								sessionHQL.flush();
               								sessionHQL.clear();
                                    }
					tx.commit();
					sessionHQL.close();
					

					if ((app > 0) || (app1 > 0) || (app2 > 0) || (app3 > 0) || (app4 > 0) || (app5 > 0)) {
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
//--------------------------------------------Submit for approval-----institute data university side------------------------------------

		@RequestMapping(value = "/Submit_Approval_Data__pg", method = RequestMethod.POST)
		public @ResponseBody String Submit_Approval_Data__pg(ModelMap Mmap, HttpSession session,
				HttpServletRequest request,
				@RequestParam(value = "a_hid", required = false) int a_hid,
				@RequestParam(value = "b_hid", required = false) int b_hid,
				@RequestParam(value = "c_hid", required = false) int c_hid,
				@RequestParam(value = "d_hid", required = false) int d_hid,
				@RequestParam(value = "e_hid", required = false) int e_hid,
				@RequestParam(value = "f_hid", required = false) int f_hid)throws ParseException {

			String msg = "";

			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();

			String university_id = session.getAttribute("university_id").toString();
			String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		    String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
		    String current_mm_yy = month+"-"+year;
		    String user_id = session.getAttribute("userId").toString();

			String cmoyr = (month + 1) + "-" + year;

			System.err.println("university_id---" + university_id + "---co mo yr---" + cmoyr);

			try {
					if(a_hid > 0) 
					{
						String hqlUpdate = "update DG_REC_STUDENTS_ADMITTED_B set \n"
											+ "university_approved_status=:university_approved_status \n"
											+ "where university_id=:university_id";
					
						int app = sessionHQL.createQuery(hqlUpdate)
								.setInteger("university_approved_status", 1)
								.setInteger("university_id", Integer.parseInt(university_id))
								.executeUpdate();
				
						String hqlUpdate1 = "update DG_REC_PG_TRACK_STATUS set inst_status=:inst_status,uni_status=:uni_status,"
											+ "commi_status=:commi_status where university_id=:university_id";
				
						int app1 = sessionHQL.createQuery(hqlUpdate1)
								.setInteger("inst_status", 1)
								.setInteger("uni_status", 1)
								.setInteger("commi_status", 0)
								.setInteger("university_id", Integer.parseInt(university_id))
								.executeUpdate();
					}
					if(b_hid > 0) 
					{
						String hqlUpdate = "update DG_REC_EXAMINERS_MD_B set \n"
											+ "university_approved_status=:university_approved_status \n"
											+ "where university_id=:university_id";
					
						int app = sessionHQL.createQuery(hqlUpdate)
								.setInteger("university_approved_status", 1)
								.setInteger("university_id", Integer.parseInt(university_id))
								.executeUpdate();
				
						String hqlUpdate1 = "update DG_REC_PG_TRACK_STATUS set inst_status=:inst_status,uni_status=:uni_status,"
											+ "commi_status=:commi_status where university_id=:university_id";
				
						int app1 = sessionHQL.createQuery(hqlUpdate1)
								.setInteger("inst_status", 1)
								.setInteger("uni_status", 1)
								.setInteger("commi_status", 0)
								.setInteger("university_id", Integer.parseInt(university_id))
								.executeUpdate();
					}
					if(c_hid > 0) 
					{
						String hqlUpdate = "update DG_REC_WORK_DONE_PG_STUDENT_B set \n"
											+ "university_approved_status=:university_approved_status \n"
											+ "where university_id=:university_id";
					
						int app = sessionHQL.createQuery(hqlUpdate)
								.setInteger("university_approved_status", 1)
								.setInteger("university_id", Integer.parseInt(university_id))
								.executeUpdate();
				
						String hqlUpdate1 = "update DG_REC_PG_TRACK_STATUS set inst_status=:inst_status,uni_status=:uni_status,"
											+ "commi_status=:commi_status where university_id=:university_id";
				
						int app1 = sessionHQL.createQuery(hqlUpdate1)
								.setInteger("inst_status", 1)
								.setInteger("uni_status", 1)
								.setInteger("commi_status", 0)
								.setInteger("university_id", Integer.parseInt(university_id))
								.executeUpdate();
					}
					if(d_hid > 0) 
					{
						String hqlUpdate = "update DG_REC_LECTURE_PG_STUDENT_B set \n"
											+ "university_approved_status=:university_approved_status \n"
											+ "where university_id=:university_id";
					
						int app = sessionHQL.createQuery(hqlUpdate)
								.setInteger("university_approved_status", 1)
								.setInteger("university_id", Integer.parseInt(university_id))
								.executeUpdate();
				
						String hqlUpdate1 = "update DG_REC_PG_TRACK_STATUS set inst_status=:inst_status,uni_status=:uni_status,"
											+ "commi_status=:commi_status where university_id=:university_id";
				
						int app1 = sessionHQL.createQuery(hqlUpdate1)
								.setInteger("inst_status", 1)
								.setInteger("uni_status", 1)
								.setInteger("commi_status", 0)
								.setInteger("university_id", Integer.parseInt(university_id))
								.executeUpdate();
					}
					if(e_hid > 0) 
					{
						String hqlUpdate = "update DG_REC_ASSIGNMENT_PG_STUDENT_B set \n"
											+ "university_approved_status=:university_approved_status \n"
											+ "where university_id=:university_id";
					
						int app = sessionHQL.createQuery(hqlUpdate)
								.setInteger("university_approved_status", 1)
								.setInteger("university_id", Integer.parseInt(university_id))
								.executeUpdate();
				
						String hqlUpdate1 = "update DG_REC_PG_TRACK_STATUS set inst_status=:inst_status,uni_status=:uni_status,"
											+ "commi_status=:commi_status where university_id=:university_id";
				
						int app1 = sessionHQL.createQuery(hqlUpdate1)
								.setInteger("inst_status", 1)
								.setInteger("uni_status", 1)
								.setInteger("commi_status", 0)
								.setInteger("university_id", Integer.parseInt(university_id))
								.executeUpdate();
					}
					if(f_hid > 0) 
					{
						String hqlUpdate = "update DG_REC_PRESENTATION_SEMINAR_STUDENT_B set \n"
											+ "university_approved_status=:university_approved_status \n"
											+ "where university_id=:university_id";
					
						int app = sessionHQL.createQuery(hqlUpdate)
								.setInteger("university_approved_status", 1)
								.setInteger("university_id", Integer.parseInt(university_id))
								.executeUpdate();
				
						String hqlUpdate1 = "update DG_REC_PG_TRACK_STATUS set inst_status=:inst_status,uni_status=:uni_status,"
											+ "commi_status=:commi_status where university_id=:university_id";
				
						int app1 = sessionHQL.createQuery(hqlUpdate1)
								.setInteger("inst_status", 1)
								.setInteger("uni_status", 1)
								.setInteger("commi_status", 0)
								.setInteger("university_id", Integer.parseInt(university_id))
								.executeUpdate();
					}
				if ((a_hid > 0) || (b_hid > 0) || (c_hid > 0) || (d_hid > 0) || (e_hid > 0) || (f_hid > 0)) {

					msg = "Data Approved Successfully";
					
					//------email----------
					
//					String subject = "YourRegistrationForAlumniisSuccessfull";
//					String main_txt = " You have received this valid data from the Institute. "
//							         + "This Details has been Successfully Approved by Institute.So ,Check it and give it approval.";
//					String follow_txt = "Now , you can Login with your Email Id and <br> Password is : Bisag@123";
//					e.SendMail(request, subject,main_txt, follow_txt, "", "", "", "");
					
					//------end email----------
					
					tx.commit();
				} else {
					msg = "Something Went Wrong !!!";
				}
				sessionHQL.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
//			//----notification---------
//			

//			 String notimsg=" You have received this valid data from the University. "
//				       + "This Details has been Successfully Approved by University.So ,Check it and give the approval.";
//		     common.Notification(notimsg, "419",sessionFactory, session);
//		     System.err.println(notimsg+"-------notimsg");
		     
		   //----end notification---------
		     
			return msg;
		}
	 
}
