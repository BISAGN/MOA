package com.AyushEdu.controller.TT_Lecture;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.Curriculum.CC_TB_T3_LEARNING_OBJECT_CHILD;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PROFESSIONAL_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_SUB_TOPICS_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_TOPICS_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_COURSE_MASTER;
import com.AyushEdu.Models.TT_Lecture.EDU_LEC_INSTRUCTION_METHOD_MSTR;
import com.AyushEdu.Models.TT_Lecture.EDU_LEC_PLAN_FOR_STUDENTS;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Examination.View_Internal_assessment_MarksDao;
import com.AyushEdu.dao.LMS_Institute.Sys_Deg_Map_Inst_DAO;
import com.AyushEdu.dao.TT_Lecture.Student_Lecture_DAO;
import com.AyushEdu.validation.ValidationController;


@RequestMapping(value = { "admin", "/", "user" })
@Controller
public class Student_Lecture_Controller{
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;

	@Autowired
	private SessionFactory sessionFactory2;
	
	@Autowired
	 ValidationController validation;
	
	@Autowired
	Student_Lecture_DAO Sdao;
	
	@Autowired
	Sys_Deg_Map_Inst_DAO  dmdao;
	
	@Autowired
	View_Internal_assessment_MarksDao IAM;
	
	//==========================================OPEN PAGE STUDENT LECTURE PLAN========================================== 	
	
	@RequestMapping(value = "/Student_Lecturer_PlanUrl", method = RequestMethod.GET)
	public ModelAndView Student_Lecturer_PlanUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,HttpSession httpsession) {
		
		try {
			
//			SECURITY -- RIDDHI 
//			if(request.getHeader("Referer") == null ) { 
////				session.invalidate();
//				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				return new ModelAndView("redirect:/landingpage");
//			 }
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("Student_Lecturer_PlanUrl", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
			
			Session sessiont = sessionFactory.openSession();
			String userid = httpsession.getAttribute("userId_for_jnlp").toString();
			Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", (Integer.parseInt(userid)));
			List<UserLogin> institute_idList = q1.list();
			sessiont.close();
			int institute_id = institute_idList.get(0).getInstitute_id();
			
			
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("Student_Lecturer_PlanUrl", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
			
		 Mmap.put("msg", msg);
		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		 Mmap.put("getCourceList", getCourceList());
		 Mmap.put("getLearningList", getLearningList());
		 Mmap.put("getProfessionalList", getProfessionalList());
		 Mmap.put("getInstructionalList", getInstructionalList());
		 Mmap.put("getTopicList", getTopicList());
		 Mmap.put("getSubTopicList",getSubTopicList());
		 String userId = session.getAttribute("userId").toString();
		 Mmap.put("TotalAttendedLecture",Sdao.TotalAttendedLecture(userId));
		 Mmap.put("TotalNotAttendedLecture",Sdao.TotalNotAttendedLecture(userId));
		 
		 
		 
		 String professional ="0";
		 if(IAM.Getdegreeid_fetch(userid).get(0).get(2).equals("1")){
			 professional = "15";
     	}else if(IAM.Getdegreeid_fetch(userid).get(0).get(2).equals("2")){
     		 professional = "16";
     	}else if(IAM.Getdegreeid_fetch(userid).get(0).get(2).equals("3")){
     		 professional = "17";
     	}
     	
		 Mmap.put("TotalLecture",Sdao.TotalLecture(professional,institute_id));
		Mmap.put("getFacultyList",  Sdao.getFacultyData(userId));
		Mmap.put("Current_Prof", IAM.Getdegreeid_fetch(userid));
	} catch (Exception e) {
		e.printStackTrace();
	}
		return new ModelAndView("Student_Lecturer_Plan_Tiles");

}
	
	//==========================================SAVE STUDENT LECTURE PLAN DATA==========================================
	@RequestMapping(value = "/getStudent_Details", method = RequestMethod.POST)
	public @ResponseBody Map<String, String>  getStudent_Details(
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request, 
			RedirectAttributes ra, Principal principal,HttpSession httpsession) throws ParseException {
		
		Map<String, String> data = new HashMap<>();
		

		String role = httpsession.getAttribute("role").toString();
		
		Session sessiont = sessionFactory.openSession();
		String userid = httpsession.getAttribute("userId_for_jnlp").toString();
		Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", (Integer.parseInt(userid)));
		List<UserLogin> institute_idList = q1.list();
		sessiont.close();
		int institute_id = institute_idList.get(0).getInstitute_id();
		
		 Date date = new Date();
		 
		 String username = principal.getName();

		SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");
	     
	
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		EDU_LEC_PLAN_FOR_STUDENTS td =new EDU_LEC_PLAN_FOR_STUDENTS();
		int id = td.getId() > 0 ? td.getId() : 0;
		int datacounthId =Integer.parseInt(request.getParameter("datacounthId"));
//		System.err.println("datdacount hiddent----------->"+datacounthId);
		String professional = request.getParameter("professional");
	    String sdate = request.getParameter("sdate");
		
		try {
			
			for (int i = 1; i <= datacounthId; i++){
				String start_time=request.getParameter("start_time"+i);
				String end_time=request.getParameter("end_time"+i);
			    String faculty = request.getParameter("faculty"+i);
				String course_name = request.getParameter("course_name"+i);
				String topic = request.getParameter("topic"+i);
				String sub_topic = request.getParameter("sub_topic"+i);
				String learning_objective = request.getParameter("learning_objective"+i);
				String instructional_method = request.getParameter("instructional_method"+i);
				String btnradio = request.getParameter("btnradio"+i);
			

				if (topic == null || topic.trim().equals("0")) {
					 data.put( "err" ,  "Please Select Topic of Row "+i);
					  data.put( "field" ,  "topic"+i);
					return data;
				}

				if (sub_topic == null || sub_topic.trim().equals("0")) {
					 data.put( "err" ,  "Please Select Sub-Topic of Row "+i);
					  data.put( "field" ,  "sub_topic"+i);
					return data;
				}
				
				if (learning_objective == null || learning_objective.trim().equals("0")) {
					data.put( "err" ,  "Please Select Learning Objective of Row "+i);
					  data.put( "field" ,  "learning_objective"+i);
					return data;
				}
				
				if (instructional_method == null || instructional_method.trim().equals("0")) {
					 data.put( "err" ,  "Please Select Instructional Method of Row " +i);
					  data.put( "field" ,  "instructional_method"+i);
					return data;
				}
				
				
				
			System.err.println(sdate);
			Long c = (Long) sessionHQL.createQuery(
					"select count(*) from  EDU_LEC_PLAN_FOR_STUDENTS where professional=:professional and sdate=:sdate and "
					+ "start_time=:start_time and end_time=:end_time and faculty=:faculty and "
					+ "course_name=:course_name and student_id=:student_id and institute_id =: institute_id")
					.setParameter("professional", Integer.parseInt(professional))
					.setParameter("sdate", formate.parse(sdate))
					.setParameter("start_time",start_time)
					.setParameter("end_time",end_time)
					.setParameter("faculty", Integer.parseInt(faculty))
					.setParameter("course_name", Integer.parseInt(course_name))
					.setParameter("student_id",Integer.parseInt(userid))
					.setParameter("institute_id", institute_id)
					.uniqueResult();
		
			if (c == 0) {
				
				td.setProfessional(Integer.parseInt(professional));
				td.setSdate(formate.parse(sdate));
				td.setStart_time(start_time);
				td.setEnd_time(end_time);
				td.setFaculty(Integer.parseInt(faculty));
				td.setCourse_name(Integer.parseInt(course_name));
				td.setTopic(Integer.parseInt(topic));
				td.setSub_topic(Integer.parseInt(sub_topic));
				td.setLearning_objective(Integer.parseInt(learning_objective));
				td.setInstructional_method(Integer.parseInt(instructional_method));
				td.setStudent_id(Integer.parseInt(userid));
				td.setAttended(Integer.parseInt(btnradio));
				td.setInstitute_id(institute_id);
				
				td.setCreated_by(username);
				td.setCreated_role(role);
				td.setCreated_dt(date);
				
			
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					
//					msg = "Data Saved Successfully.";
					data.put("msg", "Data Saved Successfully.");
			
				} else {
//					msg =  "Data Already Exist";
					data.put("msg","Data Already Exist");
					
				}
			
		}
			tx.commit();
		}catch (RuntimeException e) {
			e.printStackTrace();
			
			try {
				ra.addAttribute("data", "roll back transaction");
				}
			catch (RuntimeException rbe) {
				ra.addAttribute("data", "Couldnot roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	

		return data;
	}
	
	
	//==========================================SEARCH SCREEN FOR STUDENT LECTURE PLAN========================================== 	
	
	
	@RequestMapping(value = "/Search_Student_Lecturer_PlanUrl", method = RequestMethod.GET)
		public ModelAndView Search_Student_Lecturer_PlanUrl(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
			
			try {
//				SECURITY -- RIDDHI 
				if(request.getHeader("Referer") == null ) { 
//					session.invalidate();
					Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Student_Lecturer_PlanUrl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
				Session sessiont = sessionFactory.openSession();
				String userid = session.getAttribute("userId_for_jnlp").toString();
				Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", (Integer.parseInt(userid)));
				List<UserLogin> institute_idList = q1.list();
				sessiont.close();
				int institute_id = institute_idList.get(0).getInstitute_id();
				
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("Search_Student_Lecturer_PlanUrl", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
//			 String userid = session.getAttribute("userId_for_jnlp").toString();
			Mmap.put("msg", msg);
			 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
			 Mmap.put("getCourceList", getCourceList());
			 
			 Mmap.put("getLearningList", getLearningList());
			 Mmap.put("getProfessionalList", getProfessionalList());
			 Mmap.put("getInstructionalList", getInstructionalList());
			 Mmap.put("getTopicList", getTopicList());
			 Mmap.put("getSubTopicList",getSubTopicList());
			 String userId = session.getAttribute("userId").toString();
			 
			 Mmap.put("system_id", dmdao.Getsytemid_fetch(userid).get(0).get(1));
			 System.err.println("-------- "+dmdao.Getsytemid_fetch(userid).get(0).get(1));
			 Mmap.put("TotalAttendedLecture",Sdao.TotalAttendedLecture(userId));
			 Mmap.put("TotalNotAttendedLecture",Sdao.TotalNotAttendedLecture(userId));
			 
			 
			 
			 String professional ="0";
			 if(IAM.Getdegreeid_fetch(userid).get(0).get(2).equals("1")){
				 professional = "15";
	     	}else if(IAM.Getdegreeid_fetch(userid).get(0).get(2).equals("2")){
	     		 professional = "16";
	     	}else if(IAM.Getdegreeid_fetch(userid).get(0).get(2).equals("3")){
	     		 professional = "17";
	     	}
	     	
			 Mmap.put("TotalLecture",Sdao.TotalLecture(professional,institute_id));
			Mmap.put("getFacultyList",Sdao.getFacultyData(userId));
			
			Mmap.put("Current_Prof", IAM.Getdegreeid_fetch(userId));
				
		} catch (Exception e) {
			e.printStackTrace();
		}
			return new ModelAndView("Search_Student_Lecturer_Plan_Tiles");

	}
		
		//==========================================EDIT SCREEN FOR STUDENT LECTURE PLAN DATA========================================== //	
				@RequestMapping(value = "/edit_student_lecture_planUrl", method = RequestMethod.POST)
				public ModelAndView edit_student_lecture_planUrl(@ModelAttribute("sdate1") String sdate, ModelMap Mmap, Principal principal,
						@RequestParam(value = "msg", required = false) String msg,
						HttpServletRequest request, HttpSession sessionEdit) {
					
					try {
//						SECURITY -- RIDDHI 
						if(request.getHeader("Referer") == null ) { 
//							session.invalidate();
							Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
							return new ModelAndView("redirect:/landingpage");
						 }
						String roleid1 = sessionEdit.getAttribute("roleid").toString();
						 Boolean val = roledao.ScreenRedirect("Student_Lecturer_PlanUrl", roleid1);		
							if(val == false) {
								return new ModelAndView("AccessTiles");
						}
//						String roleid1 = sessionEdit.getAttribute("roleid").toString();
//						 Boolean val = roledao.ScreenRedirect("edit_student_lecture_planUrl", roleid1);		
//							if(val == false) {
//								return new ModelAndView("AccessTiles");
//						}
						
						
						 Mmap.put("sdate", sdate);
						 Mmap.put("msg", msg);
						 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
						 Mmap.put("getCourceList", getCourceList());
						 Mmap.put("getLearningList", getLearningList());
						 Mmap.put("getProfessionalList", getProfessionalList());
						 Mmap.put("getInstructionalList", getInstructionalList());
						 Mmap.put("getTopicList", getTopicList());
						 Mmap.put("getSubTopicList",getSubTopicList());
						 String userId = sessionEdit.getAttribute("userId").toString();
						 Mmap.put("getFacultyList",  Sdao.getFacultyData(userId));
						Mmap.put("Current_Prof", IAM.Getdegreeid_fetch(userId));
						
				} catch (Exception e) {
					e.printStackTrace();
				}
					return new ModelAndView("edit_student_lecture_plan_Tiles");

		}
			///////////////////////////////////////EDIT ACTION FOR STUDENT LECTURE PLAN DATA//////////////////////////////////////////
			
				
				
				@RequestMapping(value = "/getStudent_DetailsforEdit", method = RequestMethod.POST)
				public @ResponseBody  Map<String, String> getStudent_DetailsforEdit(
						@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request, 
						RedirectAttributes ra, Principal principal,HttpSession httpsession) throws ParseException {
					
					Map<String, String> data = new HashMap<>();

					String role = httpsession.getAttribute("role").toString();
					
					Session sessiont = sessionFactory.openSession();
					String userid = httpsession.getAttribute("userId_for_jnlp").toString();
					Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", (Integer.parseInt(userid)));
					List<UserLogin> institute_idList = q1.list();
					sessiont.close();
					int institute_id = institute_idList.get(0).getInstitute_id();
					
					 Date date = new Date();
					 
					 String username = principal.getName();

					SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");
				     
					
					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					int datacounthId =Integer.parseInt(request.getParameter("datacounthId"));
					System.err.println("datdacount hiddent----------->"+datacounthId);
					String professional = request.getParameter("professional");
				    String sdate = request.getParameter("sdate");
					
					
					
					try {
						
						for (int i = 1; i <= datacounthId; i++){
							String start_time = request.getParameter("start_time"+i);
							String end_time = request.getParameter("end_time"+i);
						    String faculty = request.getParameter("faculty"+i);
							String course_name = request.getParameter("course_name"+i);
							String topic = request.getParameter("topic"+i);
							String sub_topic = request.getParameter("sub_topic"+i);
							String learning_objective = request.getParameter("learning_objective"+i);
							String instructional_method = request.getParameter("instructional_method"+i);
							String btnradio = request.getParameter("btnradio"+i);
							String EditHid = request.getParameter("EditHid"+i);
							
							if (topic == null || topic.trim().equals("0")) {
								 data.put( "err" ,  "Please Select Topic of Row "+i);
								  data.put( "field" ,  "topic"+i);
								return data;
							}

							if (sub_topic == null || sub_topic.trim().equals("0")) {
								 data.put( "err" ,  "Please Select Sub-Topic of Row "+i);
								  data.put( "field" ,  "sub_topic"+i);
								return data;
							}
							
							if (learning_objective == null || learning_objective.trim().equals("0")) {
								data.put( "err" ,  "Please Select Learning Objective of Row "+i);
								  data.put( "field" ,  "learning_objective"+i);
								return data;
							}
							
							if (instructional_method == null || instructional_method.trim().equals("0")) {
								 data.put( "err" ,  "Please Select Instructional Method of Row " +i);
								  data.put( "field" ,  "instructional_method"+i);
								return data;
							}
							
						

							EDU_LEC_PLAN_FOR_STUDENTS td =  (EDU_LEC_PLAN_FOR_STUDENTS)sessionHQL.get(EDU_LEC_PLAN_FOR_STUDENTS.class, Integer.parseInt(EditHid));
							
							
							td.setProfessional(Integer.parseInt(professional));
							td.setSdate(formate.parse(sdate));
							td.setStart_time(start_time);
							td.setEnd_time(end_time);
							td.setFaculty(Integer.parseInt(faculty));
							td.setCourse_name(Integer.parseInt(course_name));
							td.setTopic(Integer.parseInt(topic));
							td.setSub_topic(Integer.parseInt(sub_topic));
							td.setLearning_objective(Integer.parseInt(learning_objective));
							td.setInstructional_method(Integer.parseInt(instructional_method));
							td.setStudent_id(Integer.parseInt(userid));
							td.setAttended(Integer.parseInt(btnradio));
							td.setInstitute_id(institute_id);
						
							
							
							td.setModified_by(username);
							td.setCreated_role(role);
							td.setModified_dt(date);
							
						
								sessionHQL.update(td);
								sessionHQL.flush();
								sessionHQL.clear();
								
								
						} 
					
						tx.commit();
					}catch (RuntimeException e) {
						
						try {
							ra.addAttribute("msg", "roll back transaction");
							}
						catch (RuntimeException rbe) {
							ra.addAttribute("msg", "Couldnot roll back transaction " + rbe);
						}
						throw e;
					} finally {
						if (sessionHQL != null) {
							sessionHQL.close();
						}
					}
				

					return data;
				}
				
				
		
				

		
		//==========================================DELETE SCREEN FOR STUDENT LECTURE PLAN========================================== 	
		 
		 
		@RequestMapping(value = "/delete_Student_LectureUrl", method = RequestMethod.POST)
		public ModelAndView delete_Student_LectureUrl(@ModelAttribute("id2") int id, BindingResult result, RedirectAttributes ra,
				HttpServletRequest request, ModelMap model, HttpSession session1) {

//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session1.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Student_Lecturer_PlanUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
//			String roleid1 = session1.getAttribute("roleid").toString();
				
			List<String> liststr = new ArrayList<String>();

			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			String username = session1.getAttribute("username").toString();
			try {
	  
				int app = session.createQuery(
						"delete from EDU_LEC_PLAN_FOR_STUDENTS where id=:id")
						.setParameter("id", id).executeUpdate();

				tx.commit();
				session.close();
				if (app > 0) {
					liststr.add("Data Deleted Successfully.");
				} else {
					liststr.add("Data not Deleted.");
				}
				ra.addAttribute("msg", liststr.get(0));
			} catch (Exception e) {
				e.printStackTrace();
				liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
				ra.addAttribute("msg", liststr.get(0));
				
			}
			return new ModelAndView("redirect:Search_Student_Lecturer_PlanUrl");
		}
////////////////////////////////-------------------------------------------/////////////////////////////////////////////////////////////////////
		
		
	@PostMapping("/getFilter_Student_Lecturer_data")
	public @ResponseBody List<Map<String, Object>> getFilter_Student_Lecturer_data(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String professional, String start_time,String end_time,String course_name,
			String faculty, String topic,String sub_topic,
			String learning_objective, String instructional_method, String sdate, HttpSession session)
	{
		String userId = session.getAttribute("userId").toString();
		return Sdao.DataTableStudentLecturerDataList(startPage, pageLength, Search, orderColunm, orderType,professional,start_time,end_time,course_name, faculty, topic,sub_topic, learning_objective, 
				instructional_method,  sdate, userId);
	}

	@PostMapping("/getTotal_Student_Lecturer_dataCount")
	public @ResponseBody long getTotal_Student_Lecturer_dataCount(HttpSession sessionUserId,String Search, String orderColunm, String orderType, String professional, 
			String start_time,String end_time,String course_name, String faculty, String topic, String sub_topic,String learning_objective,  String instructional_method, String sdate,  HttpSession session) 
    {
		String userId = session.getAttribute("userId").toString();
		return Sdao.DataTableStudentLecturerDataTotalCount(Search, orderColunm , orderType, professional,start_time,end_time,course_name, faculty, topic,sub_topic, learning_objective, 
				instructional_method,  sdate, userId);
	}
	
	public List<EDU_LMS_COURSE_MASTER> getCourceList() {
		 Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 Query q0 = session.createQuery("from EDU_LMS_COURSE_MASTER where type_of_content_id = 5");
		 
		 List<EDU_LMS_COURSE_MASTER> CourceList = (List<EDU_LMS_COURSE_MASTER>) q0.list();
	        session.getTransaction().commit();
	        session.close();                
	       return CourceList;
	 }
   
	 public List<CC_TB_TOPICS_MSTR> getTopicList() {
		 Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 Query q0 = session.createQuery("from CC_TB_TOPICS_MSTR where status='1'");
		 
		 List<CC_TB_TOPICS_MSTR> getTopicList = (List<CC_TB_TOPICS_MSTR>) q0.list();
	        session.getTransaction().commit();
	        session.close();                
	       return getTopicList;
	   }
	 
	 public List<CC_TB_SUB_TOPICS_MSTR> getSubTopicList() {
		 Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 Query q0 = session.createQuery("from CC_TB_SUB_TOPICS_MSTR where status='1'");
		 
		 @SuppressWarnings("unchecked")
		List<CC_TB_SUB_TOPICS_MSTR> getSubTopicList = (List<CC_TB_SUB_TOPICS_MSTR>) q0.list();
	        session.getTransaction().commit();
	        session.close();                
	       return getSubTopicList;
	   }
	 
 	 public List<CC_TB_T3_LEARNING_OBJECT_CHILD> getLearningList() {
			 Session session = sessionFactory.openSession();
			 Transaction tx = session.beginTransaction();
			 Query q0 = session.createQuery("from CC_TB_T3_LEARNING_OBJECT_CHILD where status=0");
			 
			 List<CC_TB_T3_LEARNING_OBJECT_CHILD> LearningList = (List<CC_TB_T3_LEARNING_OBJECT_CHILD>) q0.list();
		        session.getTransaction().commit();
		        session.close();                
		       return LearningList;
		   
	  	 }
 	 
	 public List<CC_TB_PROFESSIONAL_MSTR> getProfessionalList() {
	  		 Session session = sessionFactory.openSession();
	  		 Transaction tx = session.beginTransaction();
	  		 Query q0 = session.createQuery("from CC_TB_PROFESSIONAL_MSTR where status='1' order by id");
	  		 
	  		 List<CC_TB_PROFESSIONAL_MSTR> DegreeList = q0.list();
	  	      session.getTransaction().commit();
	  	      session.close();                
	  	     return DegreeList;
	  	}
	 
	  public List<EDU_LEC_INSTRUCTION_METHOD_MSTR> getInstructionalList() {
	  		 Session session = sessionFactory.openSession();
	  		 Transaction tx = session.beginTransaction();
	  		 Query q0 = session.createQuery("from EDU_LEC_INSTRUCTION_METHOD_MSTR");
	  		 
	  		 List<EDU_LEC_INSTRUCTION_METHOD_MSTR> InstructionalList = q0.list();
	  	      session.getTransaction().commit();
	  	      session.close();                
	  	     return InstructionalList;		 

	  	}
	  
	  @RequestMapping(value = "/getfacutlydetails1", method = RequestMethod.POST)
	  public @ResponseBody ArrayList<ArrayList<String>> getfacutlydetails1(ModelMap Mmap,
	  		@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String course_id) {
	            
	         return Sdao.getfacultydetailsDao1(course_id);

	  }

	  	
	  @RequestMapping(value = "/getCourse1", method = RequestMethod.POST)
	  	public @ResponseBody ArrayList<ArrayList<String>> getCourse1(ModelMap Mmap,
	  			@RequestParam(value = "msg", required = false) String msg, HttpSession session, HttpServletRequest request,String professional) {
//			String userid = session.getAttribute("userId").toString(); 
	  	       return Sdao.getCourse1(professional);
	  	}
	  	
	 @RequestMapping(value = "/getTopic_name1", method = RequestMethod.POST)
	  	public @ResponseBody ArrayList<ArrayList<String>> getTopic_name(ModelMap Mmap,
	  			@RequestParam(value = "msg", required = false) String msg, HttpSession session, HttpServletRequest request,int course_id) {
			String userid = session.getAttribute("userId").toString(); 
	  	       return Sdao.getTopic_name1(course_id,userid);
	  	}
	 
	 @RequestMapping(value = "/getSubTopic_name1", method = RequestMethod.POST)
	  	public @ResponseBody ArrayList<ArrayList<String>> getSubTopic_name(ModelMap Mmap,
	  			@RequestParam(value = "msg", required = false) String msg, HttpSession session, HttpServletRequest request,int topic_id) {
			String userid = session.getAttribute("userId").toString(); 
	  	       return Sdao.getSubTopic_name1(topic_id,userid);
	  	}
	 
	 @RequestMapping(value = "/getLearning_Objective1", method = RequestMethod.POST)
	  	public @ResponseBody ArrayList<ArrayList<String>> getLearning_Objective(ModelMap Mmap,
	  			@RequestParam(value = "msg", required = false) String msg, HttpSession session, HttpServletRequest request,String topic_id) {
			String userid = session.getAttribute("userId").toString(); 
	  	       return Sdao.getLearning_Objective1(topic_id,userid);
	  	}
	 
	 
			////////////////////////////////////////STUDENT LECTURE PLAN DATA FETCH FROM TIME TABLE BY SELECTING DATE////////////////////////////////////////
					
			@PostMapping("/getLecturedetailsbyDate")
			
			//public @ResponseBody List<Map<String, Object>> getLecturedetailsbyDate(String ldate,HttpSession session) {
			public @ResponseBody ArrayList<ArrayList<String>> LectureDataByDate(String ldate,String professional,HttpSession session) {
			
			Session sessiont = sessionFactory.openSession();
			String userid = session.getAttribute("userId_for_jnlp").toString();
			Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", (Integer.parseInt(userid)));
			List<UserLogin> institute_idList = q1.list();
			sessiont.close();
			int institute_id = institute_idList.get(0).getInstitute_id();
			System.err.println("professional---------------->"+professional);
			
			
			return Sdao.LectureDataByDate(institute_id,ldate, Integer.parseInt(professional));
			}
			
			
		////////////////////////////////////////AUTOCOMPLETE LECTURE DETAILS FOR EDIT SCREEN///////////////////////////////////////////////////////////
					
		@PostMapping("/getLecturedetailsbyDateforEdit")
		
		//public @ResponseBody List<Map<String, Object>> getLecturedetailsbyDate(String ldate,HttpSession session) {
		public @ResponseBody ArrayList<ArrayList<String>> LectureDataByDateforEdit(String sdate,String professional,HttpSession session) {
		
		Session sessiont = sessionFactory.openSession();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
		List<UserLogin> institute_idList = q1.list();
		sessiont.close();
		int institute_id = institute_idList.get(0).getInstitute_id();
		int student_id = Integer.parseInt(userid);
		
		return Sdao.LectureDataByDateforEdit(institute_id,student_id,sdate,Integer.parseInt(professional));
		}


}




