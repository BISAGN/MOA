package com.AyushEdu.controller.TT_Lecture;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_H3_ASSESSMENT_TYPE_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PROFESSIONAL_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_TOPICS_MSTR;
import com.AyushEdu.Models.Exp_Excel.EDU_LMS_FACULTY_NCH;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_COURSE_MASTER;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DEGREE_MASTER;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYSTEM_MSTR;
import com.AyushEdu.Models.TT_Lecture.EDU_LEC_INSTRUCTION_METHOD_MSTR;
import com.AyushEdu.Models.TT_Lecture.EDU_LEC_PLAN_TEMP;
import com.AyushEdu.Models.Time_Table.EDU_TT_ACADEMIC_DETAILS;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Institute.Sys_Deg_Map_Inst_DAO;
import com.AyushEdu.dao.TT_Lecture.Institute_Lecture_DAO;
import com.AyushEdu.dao.TT_Lecture.LecturerDAO;
import com.AyushEdu.dao.TT_Lecture.Student_Lecture_DAO;
import com.AyushEdu.validation.ValidationController;

@RequestMapping(value = { "admin", "/", "user" })

@Controller
public class Lecture_Controller {

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
	Sys_Deg_Map_Inst_DAO  dmdao;
	
	@Autowired
	Student_Lecture_DAO Sdao;


	@Autowired
	LecturerDAO ldao;
	
	@Autowired
	Institute_Lecture_DAO ILdao;
	
	//==========================================OPEN PAGE========================================== 	
	
	@RequestMapping(value = "/LecturerUrl", method = RequestMethod.GET)
	public ModelAndView LecturerUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		try {
			
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("LecturerUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
		 String userid = session.getAttribute("userId_for_jnlp").toString();
		 Mmap.put("system", dmdao.Getsytemid_fetch(userid));
		Mmap.put("msg", msg);
		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		 Mmap.put("getCourceList", getCourceList());
		 Mmap.put("getLearningList", getLearningList());
		 Mmap.put("getProfessionalList", getProfessionalList());
		 Mmap.put("getInstructionalList", getInstructionalList());
		 Mmap.put("getAssessmentList", getAssessmentList());
		 Mmap.put("getTopicList", getTopicList());
		 
	} catch (Exception e) {
		e.printStackTrace();
	}
		return new ModelAndView("Lecturer_Tiles");

	}
	
	//==========================================Save NAME========================================== 	
	@PostMapping(value = "/Lecturer_Action")
	public ModelAndView Lecturer_Action(@Validated @ModelAttribute("LecturerCMD") EDU_LEC_PLAN_TEMP td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws Exception {

//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("LecturerUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String role = session.getAttribute("role").toString();
		String userid = session.getAttribute("userId").toString();
		
		Date date = new Date();
		 
		SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formate1 = new SimpleDateFormat("yyyy");
		
		 String username = principal.getName();
		
	    
		String course_name = request.getParameter("course_name");
		String topic = request.getParameter("topic");
		String academic_year = request.getParameter("academic_year");
		String professional = request.getParameter("professional");
		String learning_objective = request.getParameter("learning_objective");
		String instructional_method = request.getParameter("instructional_method");
		String lecture_hours = request.getParameter("lecture_hours");
		String non_lecture_hours = request.getParameter("non_lecture_hours");
		String time = request.getParameter("time");
		String time_rem = request.getParameter("time_rem");
		String fdate = request.getParameter("fdate");
		String activity_description = request.getParameter("activity_description");
		String resources = request.getParameter("resources");
		String assessment_method = request.getParameter("assessment_method");
		
		
		if (professional == null || professional.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter Professional.");
			return new ModelAndView("redirect:LecturerUrl");
		}
		
		if (course_name == null || course_name.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter Course Name.");
			return new ModelAndView("redirect:LecturerUrl");
		}
		
		if (topic == null || topic.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter Topic.");
			return new ModelAndView("redirect:LecturerUrl");
		}
		
		if (learning_objective == null || learning_objective.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter Learning Objective.");
			return new ModelAndView("redirect:LecturerUrl");
		}
		
		if (instructional_method == null || instructional_method.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter Instructional Method.");
			return new ModelAndView("redirect:LecturerUrl");
		}
		
		if (academic_year.trim().equals("") || academic_year.trim().equals("YYYY")) {
			ra.addAttribute("msg", "Please Enter Academic Year.");
			return new ModelAndView("redirect:LecturerUrl");
		}		
		
		if (fdate == null || fdate.trim().equals("") || fdate.equals("DD/MM/YYYY")) {
			ra.addAttribute("msg", "Please Enter Date");
			return new ModelAndView("redirect:LecturerUrl");
		}
		
		if (validation.isOnlyDateFormat(fdate) == false) {
			ra.addAttribute("msg", "Date " + validation.isOnlyDateFormatMSG);
			return new ModelAndView("redirect:LecturerUrl");
		}
		
		if (activity_description == null || activity_description.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Activity Description.");
			return new ModelAndView("redirect:LecturerUrl");
		}
		
		if (validation.isOnlyAlphabet(activity_description) == false) {
			ra.addAttribute("msg","Activity Description "+ validation.isOnlyAlphabetMSG);
			return new ModelAndView("redirect:LecturerUrl");
		}
		
		if (validation.maxlengthcheck100(activity_description) == false) {
			ra.addAttribute("msg","Activity Description "+ validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:LecturerUrl");
		}
		
		if (resources == null || resources.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Resources/ A-V Aids.");
			return new ModelAndView("redirect:LecturerUrl");
		}
		
		if (validation.isOnlyAlphabet(resources) == false) {
			ra.addAttribute("msg","Resources/ A-V Aids "+ validation.isOnlyAlphabetMSG);
			return new ModelAndView("redirect:LecturerUrl");
		}
		
		if (validation.maxlengthcheck100(resources) == false) {
			ra.addAttribute("msg","Resources/ A-V Aids "+ validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:LecturerUrl");
		}
		if (assessment_method == null || assessment_method.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter Assessment Method.");
			return new ModelAndView("redirect:LecturerUrl");
		}
		
		int id = td.getId() > 0 ? td.getId() : 0;
		
		
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_LEC_PLAN_TEMP where fdate=:fdate")
					.setParameter("fdate", formate.parse(fdate))
					.uniqueResult();
			System.err.println(c);
			if (c == 0) {
				td.setActivity_description(activity_description);
				td.setAssessment_method(Integer.parseInt(assessment_method));
				td.setCourse_name(Integer.parseInt(course_name));
				td.setInstructional_method(Integer.parseInt(instructional_method));
				td.setLearning_objective(Integer.parseInt(learning_objective));
				td.setProfessional(Integer.parseInt(professional));
				td.setTopic(Integer.parseInt(topic));
				td.setResources(resources);
				td.setAcademic_year(formate1.parse(academic_year));
				td.setFdate(formate.parse(fdate));
				System.err.println("f_date+++++"+fdate);
				td.setCreated_by(username);
				td.setCreated_role(role);
				td.setCreated_dt(date);
				td.setFaculty(Integer.parseInt(userid));
				
				 
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");
				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}
			
			tx.commit();
		} catch (RuntimeException e) {
//			System.err.println("err--"+e);
			try {

				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				ra.addAttribute("msg", "Couldnot roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}

		return new ModelAndView("redirect:LecturerUrl");
	}
	
	@PostMapping("/getFilter_Lecturer_data")

	public @ResponseBody List<Map<String, Object>> getFilter_Lecturer_data(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String professional, String course_name,String topic,
			String learning_objective, String lecture_hours, String non_lecture_hours, String instructional_method, String academic_year, String fdate,
			 String time, String time_rem, String activity_description, String resources, String assessment_method,HttpSession session)
	{
		
		String userId = session.getAttribute("userId").toString();
		return ldao.DataTableLecturerDataList(startPage, pageLength, Search, orderColunm, orderType, professional,course_name, topic, learning_objective,  
				lecture_hours, non_lecture_hours, instructional_method, academic_year, fdate, time, time_rem, activity_description, resources, assessment_method, userId);

	}

	@PostMapping("/getTotal_Lecturer_dataCount")
	public @ResponseBody long getTotal_Lecturer_dataCount(HttpSession sessionUserId,String Search, String orderColunm, String orderType, String professional, 
			String course_name,String topic, String learning_objective, String lecture_hours, String non_lecture_hours, String instructional_method, 
			String academic_year, String fdate, String time, String time_rem, String activity_description, String resources, String assessment_method, HttpSession session) 
    {
		
		String userId = session.getAttribute("userId").toString();
		return ldao.DataTableLecturerDataTotalCount(Search, orderColunm , orderType, professional,course_name, topic, learning_objective, lecture_hours, 
				non_lecture_hours, instructional_method, academic_year, fdate, time, time_rem, activity_description, resources, assessment_method, userId);
		
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
	  	
	  public List<CC_TB_H3_ASSESSMENT_TYPE_MSTR> getAssessmentList() {
	  		 Session session = sessionFactory.openSession();
	  		 Transaction tx = session.beginTransaction();
	  		 Query q0 = session.createQuery("from CC_TB_H3_ASSESSMENT_TYPE_MSTR where status = 1");
	  		 
	  		 List<CC_TB_H3_ASSESSMENT_TYPE_MSTR> getAssessmentList = q0.list();
	  	      session.getTransaction().commit();
	  	      session.close();                
	  	     return getAssessmentList;
	  	}
	  
	 @RequestMapping(value = "/getL_N_Hours", method = RequestMethod.POST)
		   	public @ResponseBody ArrayList<ArrayList<String>> getL_N_Hours(ModelMap Mmap, 
		   			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String learning_objective) {
		   	       return ldao.getLecturerDao(learning_objective);
		   	
		   }
     @RequestMapping(value = "/getPrev_Lec_HR", method = RequestMethod.POST)
	   	  public @ResponseBody ArrayList<ArrayList<String>> getPrev_Lec_HR(ModelMap Mmap, 
	   			@RequestParam(value = "msg", required = false) String msg, HttpSession session, HttpServletRequest request,String professional,
	   			String course_name ,String topic,String learning_objective) {
	   	       return ldao.getPrev_Lec_HRDao(session,professional,course_name,topic,learning_objective);
	   	
	     }
	  @RequestMapping(value = "/gettime_duration", method = RequestMethod.POST)
		   	public @ResponseBody ArrayList<ArrayList<String>> gettime_duration(ModelMap Mmap, 
		   			@RequestParam(value = "msg", required = false) String msg,HttpSession session, HttpServletRequest request,String fdate) {
	  		String userid = session.getAttribute("userId").toString();  
	  		System.err.println(userid);
	  		 return ldao.gettime_duration(fdate,userid);
		   	
	    }
	  	 
	  @RequestMapping(value = "/getlearningobj", method = RequestMethod.POST)
	  	public @ResponseBody ArrayList<ArrayList<String>> getlearningobj(ModelMap Mmap,
	  			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String course) {
	  	       return ldao.getlearningobj(course);
	    }
	  	
	  @RequestMapping(value = "/getCourse", method = RequestMethod.POST)
	  	public @ResponseBody ArrayList<ArrayList<String>> getCourse(ModelMap Mmap,
	  			@RequestParam(value = "msg", required = false) String msg, HttpSession session, HttpServletRequest request,String professional_id, String system_id) {
			String userid = session.getAttribute("userId").toString(); 
	  	       return ldao.getCourse(professional_id,system_id,userid);
	  	}
	  	
	 @RequestMapping(value = "/getTopic_name", method = RequestMethod.POST)
	  	public @ResponseBody ArrayList<ArrayList<String>> getTopic_name(ModelMap Mmap,
	  			@RequestParam(value = "msg", required = false) String msg, HttpSession session, HttpServletRequest request,String course_id) {
			String userid = session.getAttribute("userId").toString(); 
	  	       return ldao.getTopic_name(course_id,userid);
	  	}
	 
	 @RequestMapping(value = "/getLearning_Objective", method = RequestMethod.POST)
	  	public @ResponseBody ArrayList<ArrayList<String>> getLearning_Objective(ModelMap Mmap,
	  			@RequestParam(value = "msg", required = false) String msg, HttpSession session, HttpServletRequest request,String topic_id) {
			String userid = session.getAttribute("userId").toString(); 
	  	       return ldao.getLearning_Objective(topic_id,userid);
	  	}
		
		//==========================================DELETE NAME========================================== 	
		 
		 
		@RequestMapping(value = "/delete_LecUrl", method = RequestMethod.POST)
		public ModelAndView delete_LecUrl(@ModelAttribute("id2") int id, BindingResult result, RedirectAttributes ra,
				HttpServletRequest request, ModelMap model, HttpSession session1) {

//			String roleid1 = session1.getAttribute("roleid").toString();
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session1.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("LecturerUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			List<String> liststr = new ArrayList<String>();

			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			String username = session1.getAttribute("username").toString();
			try {
	  
				int app = session.createQuery(
						"delete from EDU_LEC_PLAN_TEMP where id=:id")
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
			return new ModelAndView("redirect:Lecturer_ViewUrl");
		}

		

		//==========================================OPEN VIEW PAGE========================================== 	
			
			@RequestMapping(value = "/Lecturer_ViewUrl", method = RequestMethod.GET)
			public ModelAndView Lecturer_ViewUrl(ModelMap Mmap, HttpSession session,
					@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
				
				try {
//					SECURITY -- RIDDHI 
					if(request.getHeader("Referer") == null ) { 
//						session.invalidate();
						Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						return new ModelAndView("redirect:/landingpage");
					 }
					String roleid1 = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("LecturerUrl", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
//					String roleid1 = session.getAttribute("roleid").toString();
//					 Boolean val = roledao.ScreenRedirect("Lecturer_ViewUrl", roleid1);		
//						if(val == false) {
//							return new ModelAndView("AccessTiles");
//					}
					
				String userid = session.getAttribute("userId_for_jnlp").toString();
				 Mmap.put("system", dmdao.Getsytemid_fetch(userid));
				 Mmap.put("msg", msg);
				 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
				 Mmap.put("getCourceList", getCourceList());
				 Mmap.put("getLearningList", getLearningList());
				 Mmap.put("getProfessionalList", getProfessionalList());
				 Mmap.put("getInstructionalList", getInstructionalList());
				 Mmap.put("getAssessmentList", getAssessmentList());
				 Mmap.put("getTopicList", getTopicList());
				 
			} catch (Exception e) {
				e.printStackTrace();
			}
				return new ModelAndView("Lecturer_View_Tiles");

			}
			
//==========================================OPEN EDIT PAGE========================================== 	
			@RequestMapping(value = "/Lecturer_EditUrl", method = RequestMethod.GET)
			public ModelAndView Lecturer_EditUrl(@ModelAttribute("id3") String updateid, ModelMap Mmap, Principal principal,
					@RequestParam(value = "msg", required = false) String msg,
					HttpServletRequest request, HttpSession sessionEdit) {
				
				try {
//					SECURITY -- RIDDHI 
					if(request.getHeader("Referer") == null ) { 
//						session.invalidate();
						Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						return new ModelAndView("redirect:/landingpage");
					 }
					String roleid1 = sessionEdit.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("LecturerUrl", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
//					String roleid1 = sessionEdit.getAttribute("roleid").toString();
//					 Boolean val = roledao.ScreenRedirect("Lecturer_EditUrl", roleid1);		
//						if(val == false) {
//							return new ModelAndView("AccessTiles");
//					}
//				
				String userid = sessionEdit.getAttribute("userId").toString();
				Mmap.put("system", dmdao.Getsytemid_fetch(userid));
				String role = sessionEdit.getAttribute("role").toString();
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				EDU_LEC_PLAN_TEMP lecture_Details = ldao.getlectureByid(Integer.parseInt(updateid));
				Mmap.put("course_name", getlectureList());
				Mmap.put("lecture_Details", lecture_Details);
				Mmap.put("lectureinfo", ldao.getlectureByid(Integer.parseInt(updateid)));
				
				Mmap.put("msg", msg);
				Mmap.put("getCourceList", getCourceList());
			    Mmap.put("getLearningList", getLearningList());
				Mmap.put("getProfessionalList", getProfessionalList());
				Mmap.put("getInstructionalList", getInstructionalList());
				Mmap.put("getAssessmentList", getAssessmentList());
				Mmap.put("getTopicList", getTopicList());
				Mmap.put("updateid", updateid);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
				return new ModelAndView("Lecturer_Edit_Tiles");

			}
			//edit action
			@SuppressWarnings("deprecation")
			@RequestMapping(value = "/Lecturer_Edit_Action", method = RequestMethod.POST)
			public ModelAndView Lecturer_Edit_Action(@ModelAttribute("Lecturer_Edit_CMD") EDU_LEC_PLAN_TEMP td,BindingResult bs,
					HttpServletRequest request, ModelMap model, HttpSession session,Principal principal,
					@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {

//				SECURITY -- RIDDHI 
				if(request.getHeader("Referer") == null ) { 
//					session.invalidate();
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("LecturerUrl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
//				String roleid1 = session.getAttribute("roleid").toString();
//				 Boolean val = roledao.ScreenRedirect("Lecturer_Edit_Action", roleid1);		
//					if(val == false) {
//						return new ModelAndView("AccessTiles");
//				}
				Date date = new Date();
				 
				SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat formate1 = new SimpleDateFormat("yyyy");
				
				 
				 String username = principal.getName();
				 String userid = session.getAttribute("userId").toString();
				 
			     String id3 = String.valueOf(td.getId());
				String course_name = request.getParameter("course_name").trim();
				String topic = request.getParameter("topic").trim();
				String academic_year = request.getParameter("academic_year").trim();
				String professional = request.getParameter("professional").trim();
				String learning_objective = request.getParameter("learning_objective").trim();
				String instructional_method = request.getParameter("instructional_method").trim();
				String lecture_hours = request.getParameter("lecture_hours").trim();
				String non_lecture_hours = request.getParameter("non_lecture_hours").trim();
				String time = request.getParameter("time").trim();
				String time_rem = request.getParameter("time_rem").trim();
				String fdate = request.getParameter("fdate").trim();
				String activity_description = request.getParameter("activity_description").trim();
				String resources = request.getParameter("resources").trim();
				String assessment_method = request.getParameter("assessment_method").trim();
				
				if (professional == null || professional.trim().equals("0")) {
					ra.addAttribute("msg", "Please Enter Professional.");
					return new ModelAndView("redirect:LecturerUrl");
				}
				
				if (course_name == null || course_name.trim().equals("0")) {
					ra.addAttribute("msg", "Please Enter Course Name.");
					return new ModelAndView("redirect:LecturerUrl");
				}
				
				if (topic == null || topic.trim().equals("0")) {
					ra.addAttribute("msg", "Please Enter Topic.");
					return new ModelAndView("redirect:LecturerUrl");
				}
				
				if (learning_objective == null || learning_objective.trim().equals("0")) {
					ra.addAttribute("msg", "Please Enter Learning Objective.");
					return new ModelAndView("redirect:LecturerUrl");
				}
				
				if (instructional_method == null || instructional_method.trim().equals("0")) {
					ra.addAttribute("msg", "Please Enter Instructional Method.");
					return new ModelAndView("redirect:LecturerUrl");
				}
				
				if (academic_year.trim().equals("") || academic_year.trim().equals("YYYY")) {
					ra.addAttribute("msg", "Please Enter Academic Year.");
					return new ModelAndView("redirect:LecturerUrl");
				}
				
				if (fdate == null || fdate.trim().equals("") || fdate.equals("DD/MM/YYYY")) {
					ra.addAttribute("msg", "Please Enter Date");
					return new ModelAndView("redirect:LecturerUrl");
				}
				
				if (validation.isOnlyDateFormat(fdate) == false) {
					ra.addAttribute("msg", "Date " + validation.isOnlyDateFormatMSG);
					return new ModelAndView("redirect:LecturerUrl");
				}
				
				if (activity_description == null || activity_description.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Activity Description.");
					return new ModelAndView("redirect:LecturerUrl");
				}
				
				if (validation.isOnlyAlphabet(activity_description) == false) {
					ra.addAttribute("msg","Activity Description "+ validation.isOnlyAlphabetMSG);
					return new ModelAndView("redirect:LecturerUrl");
				}
				
				if (validation.maxlengthcheck100(activity_description) == false) {
					ra.addAttribute("msg","Activity Description "+ validation.MaxlengthcheckMSG100);
					return new ModelAndView("redirect:LecturerUrl");
				}
				
				if (resources == null || resources.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Resources/ A-V Aids.");
					return new ModelAndView("redirect:LecturerUrl");
				}
				
				if (validation.isOnlyAlphabet(resources) == false) {
					ra.addAttribute("msg","Resources/ A-V Aids "+ validation.isOnlyAlphabetMSG);
					return new ModelAndView("redirect:LecturerUrl");
				}
				
				if (validation.maxlengthcheck100(resources) == false) {
					ra.addAttribute("msg","Resources/ A-V Aids "+ validation.MaxlengthcheckMSG100);
					return new ModelAndView("redirect:LecturerUrl");
				}
				if (assessment_method == null || assessment_method.trim().equals("0")) {
					ra.addAttribute("msg", "Please Enter Assessment Method.");
					return new ModelAndView("redirect:LecturerUrl");
				}
				
				System.err.println("DATE====........."+fdate);
				int id = td.getId() > 0 ? td.getId() : 0;
				
				Session session1 = this.sessionFactory.openSession();
				Transaction tx = session1.beginTransaction();
				
				
				try {
					
					Query q0 = session1.createQuery(
							"select count(id) from EDU_LEC_PLAN_TEMP where  professional=:professional and "
							+ "course_name=:course_name and topic=:topic and learning_objective=:learning_objective and lecture_hours=:lecture_hours and "
							+ "non_lecture_hours=:non_lecture_hours and instructional_method=:instructional_method and academic_year=:academic_year and "
							+ "fdate=:fdate and time=:time and time_rem=:time_rem and activity_description=:activity_description and "
							+ "resources=:resources and assessment_method=:assessment_method and faculty=:faculty and id !=:id");
					q0.setParameter("professional",Integer.parseInt(professional));
					q0.setParameter("course_name", Integer.parseInt(course_name));
					q0.setParameter("topic", Integer.parseInt(topic));
					q0.setParameter("learning_objective", Integer.parseInt(learning_objective));
					q0.setParameter("lecture_hours", lecture_hours);
					q0.setParameter("non_lecture_hours", non_lecture_hours);
					q0.setParameter("instructional_method", Integer.parseInt(instructional_method));	
					q0.setParameter("academic_year", formate1.parse(academic_year));
					q0.setParameter("fdate", formate.parse(fdate));
					q0.setParameter("time", time);
					q0.setParameter("time_rem", time_rem);
					q0.setParameter("activity_description", activity_description);
					q0.setParameter("resources", resources);
					q0.setParameter("assessment_method", Integer.parseInt(assessment_method));
					q0.setParameter("faculty", Integer.parseInt(userid));
					q0.setParameter("id", id);

					Long c = (Long) q0.uniqueResult();
					 System.err.println("update====........."+c);
					if (c == 0) {
						String hql = "update EDU_LEC_PLAN_TEMP set professional=:professional,course_name=:course_name,"
								+ "topic=:topic,learning_objective=:learning_objective,lecture_hours=:lecture_hours,non_lecture_hours=:non_lecture_hours,"
								+ "instructional_method=:instructional_method,academic_year=:academic_year,fdate=:fdate,time=:time,"
								+ "time_rem=:time_rem,activity_description=:activity_description,resources=:resources,assessment_method=:assessment_method,modified_by=:modified_by,modified_dt=:modified_dt"
								+ " where faculty=:faculty and id=:id ";
						Query query = session1.createQuery(hql)
								.setParameter("professional", Integer.parseInt(professional))
								.setParameter("course_name",Integer.parseInt(course_name))
								.setParameter("topic", Integer.parseInt(topic))
								.setParameter("learning_objective", Integer.parseInt(learning_objective))
								.setParameter("lecture_hours", lecture_hours)
								.setParameter("non_lecture_hours", non_lecture_hours)
								.setParameter("instructional_method", Integer.parseInt(instructional_method))
								.setParameter("academic_year", formate1.parse(academic_year))
								.setParameter("fdate", formate.parse(fdate))
								.setParameter("time", time)
								.setParameter("time_rem", time_rem)
								.setParameter("activity_description", activity_description)
								.setParameter("resources", resources)
								.setParameter("assessment_method", Integer.parseInt(assessment_method))
								.setParameter("faculty", Integer.parseInt(userid))
								.setParameter("modified_by", username)
								.setParameter("modified_dt",date)
								.setParameter("id", id);
						msg = query.executeUpdate() > 0 ? "1" : "0";
						tx.commit();
						System.err.println("c====........."+hql);
						
						
						if (msg.equals("1")) {
							ra.addAttribute("msg", "Data Updated Successfully.");
						} else {
							ra.addAttribute("msg", "Data Not Updated.");
						}
					} else {
						ra.addAttribute("msg", "Data already Exist.");
					}

				} catch (RuntimeException e) {
					try {
						tx.rollback();
						ra.addAttribute("msg", "roll back transaction");
					} catch (RuntimeException rbe) {
						ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
					}
					throw e;

				} finally {
					if (session1 != null) {
						session1.close();
					}
				}
				
				return new ModelAndView("redirect:Lecturer_ViewUrl");
			}
			
			public List<EDU_LEC_PLAN_TEMP> getlectureList() {
				 Session sessionHQL = sessionFactory.openSession();
				 Transaction tx = sessionHQL.beginTransaction();
				 Query q = sessionHQL.createQuery("from EDU_LEC_PLAN_TEMP where id != 0");
				 List<EDU_LEC_PLAN_TEMP> lectureList = q.list();
				 sessionHQL.getTransaction().commit();
				 sessionHQL.close();                
			       return lectureList;
			}
			
//==========================================OPEN VIEW FOR INSTITUTE========================================== 	
			
			@RequestMapping(value = "/View_Institute_Lecture_PlanUrl", method = RequestMethod.GET)
			public ModelAndView View_Institute_Lecture_PlanUrl(ModelMap Mmap, HttpSession session,
					@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
				
				try {
					
//					SECURITY -- RIDDHI 
//					if(request.getHeader("Referer") == null ) { 
////						session.invalidate();
//						Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//						return new ModelAndView("redirect:/landingpage");
//					 }
//					String roleid1 = session.getAttribute("roleid").toString();
//					 Boolean val = roledao.ScreenRedirect("View_Institute_Lecture_PlanUrl", roleid1);		
//						if(val == false) {
//							return new ModelAndView("AccessTiles");
//					}
					
//					String roleid1 = session.getAttribute("roleid").toString();
//					 Boolean val = roledao.ScreenRedirect("View_Institute_Lecture_PlanUrl", roleid1);		
//						if(val == false) {
//							return new ModelAndView("AccessTiles");
//					}
					
				String userid = session.getAttribute("userId_for_jnlp").toString();
				 Mmap.put("system", dmdao.Getsytemid_fetch(userid));
				 Mmap.put("msg", msg);
				 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
				 Mmap.put("getCourceList", getCourceList());
				 Mmap.put("getLearningList", getLearningList());
				 Mmap.put("getProfessionalList", getProfessionalList());
				 Mmap.put("getInstructionalList", getInstructionalList());
				 Mmap.put("getAssessmentList", getAssessmentList());
				 Mmap.put("getTopicList", getTopicList());
				 
				 Session sessiont = sessionFactory.openSession();
					Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", (Integer.parseInt(userid)));
					List<UserLogin> institute_idList = q1.list();
					sessiont.close();
					int institute_id = institute_idList.get(0).getInstitute_id();
					
				 Mmap.put("getFaculty", Sdao.getFacultyData(userid));
				 
			} catch (Exception e) {
				e.printStackTrace();
			}
				return new ModelAndView("View_Institute_Lecture_Plan_Tiles");

			}
			
			
//			 @RequestMapping(value = "/getFaculty", method = RequestMethod.POST)
//			  	public @ResponseBody ArrayList<ArrayList<String>> getFaculty(ModelMap Mmap,
//			  			@RequestParam(value = "msg", required = false) String msg, HttpSession session, HttpServletRequest request) {
//					String userid = session.getAttribute("userId").toString(); 
//			  	       return ldao.getFaculty(userid);
//			  	}
			 @RequestMapping(value = "/getFaculty", method = RequestMethod.POST)
			  	public @ResponseBody ArrayList<ArrayList<String>> getFaculty(ModelMap Mmap,
			  			@RequestParam(value = "msg", required = false) String msg, HttpSession session, HttpServletRequest request) {
			  		String institute_id = session.getAttribute("userId").toString();  
			  	       return ldao.getFaculty(institute_id);
			  	}
			 
	//======================================For Institute Faculty Report==========================================			 
			 @PostMapping("/getFilter_LecturerIns_data")
				public @ResponseBody List<Map<String, Object>> getFilter_LecturerIns_data(int startPage, int pageLength, String Search,
						String orderColunm, String orderType,String faculty, String professional, String course_name,String topic,
						String learning_objective, String lecture_hours, String non_lecture_hours, String instructional_method, String academic_year, String fdate,
						 String time, String time_rem, String activity_description, String resources, String assessment_method,HttpSession session)
				{
				 Session sessiont = sessionFactory.openSession();
					String userid = session.getAttribute("userId_for_jnlp").toString();
					Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
					 List<UserLogin> institute_idList = q1.list();
					 sessiont.close();
					int institute_id = institute_idList.get(0).getInstitute_id();				 
					
					String userId = session.getAttribute("userId").toString();
					return ILdao.DataTableLecturerInsDataList(startPage, pageLength, Search, orderColunm, orderType,faculty, professional,course_name, topic, learning_objective,  
							lecture_hours, non_lecture_hours, instructional_method, academic_year, fdate, time, time_rem, activity_description, resources, assessment_method, userId, institute_id);

				}

				@PostMapping("/getTotal_LecturerIns_dataCount")
				public @ResponseBody long getTotal_LecturerIns_dataCount(HttpSession sessionUserId,String Search, String orderColunm, String orderType,String faculty, String professional, 
						String course_name,String topic, String learning_objective, String lecture_hours, String non_lecture_hours, String instructional_method, 
						String academic_year, String fdate, String time, String time_rem, String activity_description, String resources, String assessment_method, HttpSession session) 
			    {
					
					Session sessiont = sessionFactory.openSession();
					String userid = session.getAttribute("userId_for_jnlp").toString();
					Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
					 List<UserLogin> institute_idList = q1.list();
					 sessiont.close();
					int institute_id = institute_idList.get(0).getInstitute_id();
					
					String userId = session.getAttribute("userId").toString();
					return ILdao.DataTableLecturerInsDataTotalCount(Search, orderColunm , orderType,faculty, professional,course_name, topic, learning_objective, lecture_hours, 
							non_lecture_hours, instructional_method, academic_year, fdate, time, time_rem, activity_description, resources, assessment_method, userId, institute_id);
					
				}			 
			 
	 }

