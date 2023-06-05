package com.AyushEdu.controller.Curriculum;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.Curriculum.CC_TB_NCH_TOPIC_LEARNING_OUTCOME_PARENT;
import com.AyushEdu.Models.Curriculum.EDU_CC_TB_NCH_LIST_OF_TOPICS_PARENT;
import com.AyushEdu.Models.Curriculum.EDU_CC_TB_NCH_THEORY_LEARNING_OBJECTIVES_CHILD;
import com.AyushEdu.Models.Curriculum.EDU_CC_TB_NCH_THEORY_LEARNING_OBJECTIVES_PARENT;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum.Nch_Search_Theory_learning_obj_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class CC_Nch_Theory_Learning_Objectives_Controller {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Autowired
	CommonController common;

	@Autowired
	ValidationController validation;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	Nch_Search_Theory_learning_obj_Dao TLODAO;
	
//	@Autowired
//	Professional_Ayu_Report_Dao PARDAO;
	

	@RequestMapping(value = "admin/Nch_theory_learning_obj_url", method = RequestMethod.GET)
	public ModelAndView Nch_theory_learning_obj_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
//			SECURITY -- RIDDHI 	
			 if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Nch_theory_learning_obj_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
		String role = session.getAttribute("role").toString();
		Mmap.put("msg", msg);
		Mmap.put("getSystemList", common.getSystemList(sessionFactory, role));
		Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
		Mmap.put("getCourse_OutcomeList", common.getCourse_OutcomeList(sessionFactory));
		Mmap.put("NchgetBloom_Domain", common.NchgetBloom_Domain(sessionFactory));
		Mmap.put("Nchget_Mk_Dk_Nk", common.Nchget_Mk_Dk_Nk(sessionFactory));
		Mmap.put("getMillers_level", common.getMillers_level(sessionFactory));
		Mmap.put("Nchgett_l_method", common.Nchgett_l_method(sessionFactory));
		Mmap.put("getg3_assessmentList", common.getg3_assessmentList(sessionFactory));
		Mmap.put("geth3_formative_summativeList", common.geth3_formative_summativeList(sessionFactory));
		//Mmap.put("geti3_termList", common.geti3_termList(sessionFactory));
		Mmap.put("getguilberts_levelList", common.getguilberts_levelList(sessionFactory));
		Mmap.put("getnch_formative_and_summativeList", common.getnch_formative_and_summativeList(sessionFactory));

	} catch (Exception e) {
		e.printStackTrace();
	}
		return new ModelAndView("Nch_theory_learning_obj_Tiles");
	}
//new
	@RequestMapping(value = "/NchgetTopicListby_Course", method = RequestMethod.POST)
	public @ResponseBody List<EDU_CC_TB_NCH_LIST_OF_TOPICS_PARENT> NchgetTopicListby_Course(String course_id) {

		List<EDU_CC_TB_NCH_LIST_OF_TOPICS_PARENT> list = common.getNCHTopicListbyCourse(course_id);
		return list;
	}

	@RequestMapping(value = "/Nchgetlearn_outmeListby_Topic", method = RequestMethod.POST)
	public @ResponseBody List<CC_TB_NCH_TOPIC_LEARNING_OUTCOME_PARENT> Nchgetlearn_outmeListby_Topic(String topic_id) {

		List<CC_TB_NCH_TOPIC_LEARNING_OUTCOME_PARENT> list = common.getlearning_outcome_listbytopic(topic_id);
		return list;
	}
	
	
	
	
	@PostMapping(value = "/theory_learning_objectAction")
	public ModelAndView theory_learning_objectAction(
			@Validated @ModelAttribute("theory_learning_objectCMD") EDU_CC_TB_NCH_THEORY_LEARNING_OBJECTIVES_PARENT td,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra, MultipartHttpServletRequest mul) throws IOException, ParseException {
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Nch_theory_learning_obj_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		int id = td.getId() > 0 ? td.getId() : 0;

		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String system_id = request.getParameter("system_id");
		String degree_id = request.getParameter("degree_id");
		String professional_id = request.getParameter("professional_id");
		String course_id = request.getParameter("course_id");
		String topic_id = request.getParameter("topic_id");
//		String learning_outcome_hid[] = request.getParameter("learning_outcome_hid").split(",");
		String learning_outcome_hid = request.getParameter("learning_outcome_hid");
		if (system_id.equals("0")) {
			ra.addAttribute("msg", "Please Select System");
			return new ModelAndView("redirect:Nch_theory_learning_obj_url");
		}
		if (degree_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:Nch_theory_learning_obj_url");
		}
		if (professional_id.equals("0")) {
			ra.addAttribute("msg", "Please Select professional");
			return new ModelAndView("redirect:Nch_theory_learning_obj_url");
		}
		if (course_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Subject");
			return new ModelAndView("redirect:Nch_theory_learning_obj_url");
		}
		if (topic_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Topic");
			return new ModelAndView("redirect:Nch_theory_learning_obj_url");
		}
//		if (request.getParameter("learning_outcome_hid") == null || request.getParameter("learning_outcome_hid").trim().equals("")) {
//			ra.addAttribute("msg", "Please Select Learning Outcomes .");
//			return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//		}
		try {
//			for (int k = 0; k < learning_outcome_hid.length; k++) {
//			Long c = (Long) sessionHQL.createQuery(
//						"select count(id) from  EDU_CC_TB_NCH_THEORY_LEARNING_OBJECTIVES_PARENT where system_id=:system_id and degree_id=:degree_id \n"
//						+ " and professional_id=:professional_id and course_id=:course_id and  topic_id=:topic_id and learn_outme_id=:learn_outme_id and status=1\n"
//						+ " and id!=:id")
//					.setParameter("system_id", Integer.parseInt(system_id))
//					.setParameter("degree_id", Integer.parseInt(degree_id))
//					.setParameter("professional_id", Integer.parseInt(professional_id))
//					.setParameter("course_id", Integer.parseInt(course_id))
//					.setParameter("topic_id", Integer.parseInt(topic_id))
//					.setParameter("learn_outme_id", Integer.parseInt(learning_outcome_hid[k]))
//					.setParameter("id", id).uniqueResult();

//			if (id == 0) {
//			  if (c == 0) {
				td.setCreated_by(username);
				td.setCreated_date(date);
				td.setLearn_outme_id(learning_outcome_hid);
				int parent_id = (int) sessionHQL.save(td);
				sessionHQL.flush();
				sessionHQL.clear();

			   EDU_CC_TB_NCH_THEORY_LEARNING_OBJECTIVES_CHILD od = new EDU_CC_TB_NCH_THEORY_LEARNING_OBJECTIVES_CHILD();

					int count_hidden_att = Integer.parseInt(request.getParameter("count_hidden_att"));
					for (int i = 1; i <= count_hidden_att; i++) {

					String generic_competency = request.getParameter("generic_competency" + i);
					String subject_area = request.getParameter("subject_area" + i);
					String millers_level = request.getParameter("millers_level" + i);
					String specific_competency = request.getParameter("specific_competency" + i);
					String slo_outcome = request.getParameter("slo_outcome" + i);
					String blooms_domain = request.getParameter("blooms_domain" + i);
					String guilberts_level = request.getParameter("guilberts_level" + i);
					String mk_dk = request.getParameter("mk_dk" + i);
					String tl_methods = request.getParameter("tl_methods" + i);
					String form_assessment = request.getParameter("form_assessment" + i);
					String summ_assessment = request.getParameter("summ_assessment" + i);
					String int_departments = request.getParameter("int_departments" + i);
	
//					if (generic_competency == null || generic_competency.trim().equals("")) {
//						ra.addAttribute("msg", "Please Enter Generic Competency.");
//						return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//					}
//	
//					if (validation.maxlengthcheckP(generic_competency) == false) {
//						ra.addAttribute("msg", "Generic Competency " + validation.MaxlengthcheckMSGP);
//						return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//					}
//	
//					if (validation.isOnlyAlphabetDASH(generic_competency) == false) {
//						ra.addAttribute("msg", "Generic Competency " + validation.isOnlyAlphabetMSGDASH);
//						return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//					}
//					if (subject_area == null || subject_area.trim().equals("")) {
//						ra.addAttribute("msg", "Please Enter Subject Area.");
//						return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//					}
//	
//					if (validation.maxlengthcheckP(subject_area) == false) {
//						ra.addAttribute("msg", "Subject Area " + validation.MaxlengthcheckMSGP);
//						return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//					}
//	
//					if (validation.isOnlyAlphabetDASH(subject_area) == false) {
//						ra.addAttribute("msg", "Subject Area " + validation.isOnlyAlphabetMSGDASH);
//						return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//					}
//					if (millers_level.equals("0")) {
//						ra.addAttribute("msg", "Please Select Millers Level.");
//						return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//					}
//					if (specific_competency == null || specific_competency.trim().equals("")) {
//						ra.addAttribute("msg", "Please Enter Specific Competency.");
//						return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//					}
//	
//					if (validation.maxlengthcheckP(specific_competency) == false) {
//						ra.addAttribute("msg", "Specific Competency " + validation.MaxlengthcheckMSGP);
//						return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//					}
//	
//					if (validation.isOnlyAlphabetDASH(specific_competency) == false) {
//						ra.addAttribute("msg", "Specific Competency " + validation.isOnlyAlphabetMSGDASH);
//						return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//					}
//					if (slo_outcome == null || slo_outcome.trim().equals("")) {
//						ra.addAttribute("msg", "Please Enter SLO/ Outcome.");
//						return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//					}
//	
//					if (validation.maxlengthcheckP(slo_outcome) == false) {
//						ra.addAttribute("msg", "SLO/ Outcome " + validation.MaxlengthcheckMSGP);
//						return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//					}
//	
//					if (validation.isOnlyAlphabetDASH(slo_outcome) == false) {
//						ra.addAttribute("msg", "SLO/ Outcome " + validation.isOnlyAlphabetMSGDASH);
//						return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//					}
//					if (blooms_domain.equals("0")) {
//						ra.addAttribute("msg", "Please Select Blooms Domain.");
//						return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//					}
//					if (guilberts_level.equals("0")) {
//						ra.addAttribute("msg", "Please Select Guilber's Level.");
//						return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//					}
//					if (mk_dk.equals("0")) {
//						ra.addAttribute("msg", "Please Select Must Know/ Desirable to know/ nice to know.");
//						return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//					}
//					if (tl_methods.equals("0")) {
//						ra.addAttribute("msg", "Please Select T-L Methods.");
//						return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//					}
//					if (form_assessment.equals("0")) {
//						ra.addAttribute("msg", "Please Select Formative Assessment .");
//						return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//					}
//					if (summ_assessment.equals("0")) {
//						ra.addAttribute("msg", "Please Select Summative Assessment.");
//						return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//					}
//					if (int_departments == null || int_departments.trim().equals("")) {
//						ra.addAttribute("msg", "Please Enter Integration Departments.");
//						return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//					}
//	
//					if (validation.maxlengthcheckP(int_departments) == false) {
//						ra.addAttribute("msg", "Integration Departments " + validation.MaxlengthcheckMSGP);
//						return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//					}
//	
//					if (validation.isOnlyAlphabetDASH(int_departments) == false) {
//						ra.addAttribute("msg", "Integration Departments " + validation.isOnlyAlphabetMSGDASH);
//						return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//					}
					od.setGeneric_competency(generic_competency);
					od.setSubject_area(subject_area);
					od.setMillers_level(Integer.parseInt(millers_level));
					od.setSpecific_competency(specific_competency);
					od.setSlo_outcome(slo_outcome);
					od.setBlooms_domain(Integer.parseInt(blooms_domain));
					od.setGuilberts_level(Integer.parseInt(guilberts_level));
					od.setMk_dk(Integer.parseInt(mk_dk));
					od.setTl_methods(Integer.parseInt(tl_methods));
					od.setForm_assessment(Integer.parseInt(form_assessment));
					od.setSumm_assessment(Integer.parseInt(summ_assessment));
					od.setInt_departments(int_departments);
	
					od.setP_id(parent_id);
					od.setCreated_by(username);
					od.setCreated_date(date);
					sessionHQL.save(od);
					sessionHQL.flush();
					sessionHQL.clear();
			}
//			  }
			tx.commit();
			ra.addAttribute("msg", "Data Saved Successfully.");
//				} else {
//					ra.addAttribute("msg", "Data already Exist.");
//				}
//			}
		} catch (RuntimeException e) {
			try {
				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:Nch_theory_learning_obj_url");
	}
	@RequestMapping(value = "admin/Nch_Search_Theory_Learning_Object_Url", method = RequestMethod.GET)
	public ModelAndView Nch_Search_Theory_Learning_Object_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Nch_theory_learning_obj_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
		String role = session.getAttribute("role").toString();	
		Mmap.put("msg", msg);
		Mmap.put("getSystemList", common.getSystemList(sessionFactory,role));	
		Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
		Mmap.put("getCourse_OutcomeList", common.getCourse_OutcomeList(sessionFactory));
		Mmap.put("NchgetBloom_Domain", common.NchgetBloom_Domain(sessionFactory));
		Mmap.put("Nchget_Mk_Dk_Nk", common.Nchget_Mk_Dk_Nk(sessionFactory));
		Mmap.put("getMillers_level", common.getMillers_level(sessionFactory));
		Mmap.put("Nchgett_l_method", common.Nchgett_l_method(sessionFactory));
		Mmap.put("getg3_assessmentList", common.getg3_assessmentList(sessionFactory));
		Mmap.put("geth3_formative_summativeList", common.geth3_formative_summativeList(sessionFactory));
		Mmap.put("geti3_termList", common.geti3_termList(sessionFactory));
		Mmap.put("getDegreeList", common.getDegreeList(sessionFactory));
		Mmap.put("getCourseList", common.getCourseList(sessionFactory));
		Mmap.put("getTopicList", common.getTopicList(sessionFactory));
		Mmap.put("getguilberts_levelList", common.getguilberts_levelList(sessionFactory));
		Mmap.put("getnch_formative_and_summativeList", common.getnch_formative_and_summativeList(sessionFactory));
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		return new ModelAndView("NCH_Search_Theory_Learning_Object_Tiles");
	}
	
	 @PostMapping("/getFilterNCHSearchLearningObject_data")
		public @ResponseBody List<Map<String, Object>> getFilterNCHSearchLearningObject_data(int startPage, int pageLength,
				String Search, String orderColunm, String orderType, String system_id, String degree_id,String professional_id,String course_id,String topic_id,String learn_outme_id,HttpSession session) {
		 String role = session.getAttribute("role").toString();	
		 return TLODAO.DataTableNCHSearchLearningObjectDataList(startPage, pageLength, Search, orderColunm, orderType, system_id, degree_id,professional_id,course_id,topic_id,learn_outme_id,role);

		}
	 
	 @PostMapping("/getTotalNCHSearchLearningObject_dataCount")
		public @ResponseBody long getTotalNCHSearchLearningObject_dataCount(HttpSession sessionUserId, String Search, String system_id, String degree_id,String professional_id,String course_id,String topic_id, String learn_outme_id,HttpSession session) {
		 String role = session.getAttribute("role").toString();
			return TLODAO.DataTableNCHSearchLearningObjectDataTotalCount(Search, system_id, degree_id,professional_id,course_id,topic_id,learn_outme_id,role);
		}
		@RequestMapping(value = "/NCHSearchLearningObject_ChildUrl", method = RequestMethod.POST)
		public @ResponseBody   ArrayList<ArrayList<String>> NCHSearchLearningObject_ChildUrl(String hid) {
		 ArrayList<ArrayList<String>> list = TLODAO.getPopup_LearningDatalist(hid);
			return list;
		}
		
	
		@RequestMapping(value = "/Edit_NCH_Search_Learning_Object_Url", method = RequestMethod.POST)
		public ModelAndView Edit_NCH_Search_Learning_Object_Url(@ModelAttribute("id1") int id, ModelMap model,
				@RequestParam(value = "msg", required = false) String msg,Authentication authentication,
				HttpSession session,HttpServletRequest request) {

			try {	
//				SECURITY -- RIDDHI 	
				if(request.getHeader("Referer") == null ) { 
//					 session.invalidate();
					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("Nch_theory_learning_obj_url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
			ArrayList<ArrayList<String>> GetLearning_Parent_Data = TLODAO.GetLearning_Parent_Data(id);
			List<ArrayList<String>> liteLearningchildlist = TLODAO.getLearning_Child_By_id(id);
			
			String role = session.getAttribute("role").toString();	
			model.put("edit_NCH_Theory_learning_objCMD", TLODAO.GetLearning_Parent_Data(id));
			model.put("list", GetLearning_Parent_Data);
			model.put("liteLearningchildlist", liteLearningchildlist);
			model.put("getSystemList", common.getSystemList(sessionFactory,role));	
			model.put("getprofessionalList", common.getprofessionalList(sessionFactory));
			//model.put("getTopicList", common.getTopicList(sessionFactory));
			
			model.put("msg", msg);
			model.put("getSystemList", common.getSystemList(sessionFactory, role));
			model.put("getprofessionalList", common.getprofessionalList(sessionFactory));
			model.put("getCourse_OutcomeList", common.getCourse_OutcomeList(sessionFactory));
			model.put("NchgetBloom_Domain", common.NchgetBloom_Domain(sessionFactory));
			model.put("Nchget_Mk_Dk_Nk", common.Nchget_Mk_Dk_Nk(sessionFactory));
			model.put("getMillers_level", common.getMillers_level(sessionFactory));
			model.put("Nchgett_l_method", common.Nchgett_l_method(sessionFactory));
			model.put("getg3_assessmentList", common.getg3_assessmentList(sessionFactory));
			model.put("geth3_formative_summativeList", common.geth3_formative_summativeList(sessionFactory));
			//Mmap.put("geti3_termList", common.geti3_termList(sessionFactory));
			model.put("getguilberts_levelList", common.getguilberts_levelList(sessionFactory));
			model.put("getnch_formative_and_summativeList", common.getnch_formative_and_summativeList(sessionFactory));
			
			model.put("msg", msg);
		  } catch (Exception e) {
				e.printStackTrace();
		  }
			return new ModelAndView("Edit_NCH_Search_Learning_Object_Tiles");
		}
		
		@RequestMapping(value = "/edit_NCH_Theory_learning_objAction", method = RequestMethod.POST)
		public ModelAndView edit_NCH_Theory_learning_objAction(@ModelAttribute("edit_NCH_Theory_learning_objCMD") EDU_CC_TB_NCH_THEORY_LEARNING_OBJECTIVES_PARENT rs,
				HttpServletRequest request, ModelMap model, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Nch_theory_learning_obj_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
			String username = session.getAttribute("username").toString();
			Session session1 = this.sessionFactory.openSession();
			Transaction tx = session1.beginTransaction();
			Date date = new Date();

			String id = request.getParameter("pmid");
			String system_id = request.getParameter("system_id").trim();
			String degree_id = request.getParameter("degree_id").trim();
			String professional_id = request.getParameter("professional_id").trim();
			String course_id = request.getParameter("course_id");
			String topic_id = request.getParameter("topic_id").trim();
			String learning_outcome_hid = request.getParameter("learning_outcome_hid");
			System.out.println("learning_outcome_hid===="+learning_outcome_hid);
			if (system_id.equals("0")) {
				ra.addAttribute("msg", "Please Select System");
				return new ModelAndView("redirect:Nch_theory_learning_obj_url");
			}
			if (degree_id.equals("0")) {
				ra.addAttribute("msg", "Please Select Degree");
				return new ModelAndView("redirect:Nch_theory_learning_obj_url");
			}
			if (professional_id.equals("0")) {
				ra.addAttribute("msg", "Please Select professional");
				return new ModelAndView("redirect:Nch_theory_learning_obj_url");
			}
			if (course_id.equals("0")) {
				ra.addAttribute("msg", "Please Select Subject");
				return new ModelAndView("redirect:Nch_theory_learning_obj_url");
			}
			if (topic_id.equals("0")) {
				ra.addAttribute("msg", "Please Select Topic");
				return new ModelAndView("redirect:Nch_theory_learning_obj_url");
			}
//			if (learning_outcome_hid.equals("0")) {
//				ra.addAttribute("msg", "Please Select Learning Outcome");
//				return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//			}
//			
			
			
			int new_count_hidden = Integer.parseInt(request.getParameter("new_count_hidden"));
			int old_hidden_att = Integer.parseInt(request.getParameter("old_count"));
			
			for(int i=1; i <= new_count_hidden; i++) {

				String generic_competency = request.getParameter("generic_competency" + i);
				String subject_area = request.getParameter("subject_area" + i);
				String millers_level = request.getParameter("millers_level" + i);
				String specific_competency = request.getParameter("specific_competency" + i);
				String slo_outcome = request.getParameter("slo_outcome" + i);
				String blooms_domain = request.getParameter("blooms_domain" + i);
				String guilberts_level = request.getParameter("guilberts_level" + i);
				String mk_dk = request.getParameter("mk_dk" + i);
				String tl_methods = request.getParameter("tl_methods" + i);
				String form_assessment = request.getParameter("form_assessment" + i);
				String summ_assessment = request.getParameter("summ_assessment" + i);
				String int_departments = request.getParameter("int_departments" + i);
				
//				if (generic_competency == null || generic_competency.trim().equals("")) {
//					ra.addAttribute("msg", "Please Enter Generic Competency.");
//					return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//				}
//
//				if (validation.maxlengthcheckP(generic_competency) == false) {
//					ra.addAttribute("msg", "Generic Competency " + validation.MaxlengthcheckMSGP);
//					return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//				}
//
//				if (validation.isOnlyAlphabetDASH(generic_competency) == false) {
//					ra.addAttribute("msg", "Generic Competency " + validation.isOnlyAlphabetMSGDASH);
//					return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//				}
//				if (subject_area == null || subject_area.trim().equals("")) {
//					ra.addAttribute("msg", "Please Enter Subject Area.");
//					return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//				}
//
//				if (validation.maxlengthcheckP(subject_area) == false) {
//					ra.addAttribute("msg", "Subject Area " + validation.MaxlengthcheckMSGP);
//					return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//				}
//
//				if (validation.isOnlyAlphabetDASH(subject_area) == false) {
//					ra.addAttribute("msg", "Subject Area " + validation.isOnlyAlphabetMSGDASH);
//					return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//				}
//				if (millers_level.equals("0")) {
//					ra.addAttribute("msg", "Please Select Millers Level.");
//					return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//				}
//				if (specific_competency == null || specific_competency.trim().equals("")) {
//					ra.addAttribute("msg", "Please Enter Specific Competency.");
//					return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//				}
//
//				if (validation.maxlengthcheckP(specific_competency) == false) {
//					ra.addAttribute("msg", "Specific Competency " + validation.MaxlengthcheckMSGP);
//					return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//				}
//
//				if (validation.isOnlyAlphabetDASH(specific_competency) == false) {
//					ra.addAttribute("msg", "Specific Competency " + validation.isOnlyAlphabetMSGDASH);
//					return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//				}
//				if (slo_outcome == null || slo_outcome.trim().equals("")) {
//					ra.addAttribute("msg", "Please Enter SLO/ Outcome.");
//					return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//				}
//
//				if (validation.maxlengthcheckP(slo_outcome) == false) {
//					ra.addAttribute("msg", "SLO/ Outcome " + validation.MaxlengthcheckMSGP);
//					return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//				}
//
//				if (validation.isOnlyAlphabetDASH(slo_outcome) == false) {
//					ra.addAttribute("msg", "SLO/ Outcome " + validation.isOnlyAlphabetMSGDASH);
//					return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//				}
//				if (blooms_domain.equals("0")) {
//					ra.addAttribute("msg", "Please Select Blooms Domain.");
//					return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//				}
//				if (guilberts_level.equals("0")) {
//					ra.addAttribute("msg", "Please Select Guilber's Level.");
//					return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//				}
//				if (mk_dk.equals("0")) {
//					ra.addAttribute("msg", "Please Select Must Know/ Desirable to know/ nice to know.");
//					return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//				}
//				if (tl_methods.equals("0")) {
//					ra.addAttribute("msg", "Please Select T-L Methods.");
//					return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//				}
//				if (form_assessment.equals("0")) {
//					ra.addAttribute("msg", "Please Select Formative Assessment .");
//					return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//				}
//				if (summ_assessment.equals("0")) {
//					ra.addAttribute("msg", "Please Select Summative Assessment.");
//					return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//				}
//				if (int_departments == null || int_departments.trim().equals("")) {
//					ra.addAttribute("msg", "Please Enter Integration Departments.");
//					ra.addAttribute("msg", "Integration Departments " + validation.MaxlengthcheckMSGP);
//					return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//				}
//
//				if (validation.isOnlyAlphabetDASH(int_departments) == false) {
//					ra.addAttribute("msg", "Integration Departments " + validation.isOnlyAlphabetMSGDASH);
//					return new ModelAndView("redirect:Nch_theory_learning_obj_url");
//				}
			}
			try {
					String hql = "update EDU_CC_TB_NCH_THEORY_LEARNING_OBJECTIVES_PARENT set system_id=:system_id,degree_id=:degree_id,professional_id=:professional_id,course_id=:course_id,topic_id=:topic_id,learn_outme_id=:learn_outme_id,modified_by=:modified_by,modified_date=:modified_date"
							+ " where id=:id";

					Query query = session1.createQuery(hql).setParameter("system_id", Integer.parseInt(system_id))
							.setParameter("degree_id", Integer.parseInt(degree_id))
							.setParameter("professional_id", Integer.parseInt(professional_id))
							.setParameter("course_id", Integer.parseInt(course_id))
							.setParameter("topic_id", Integer.parseInt(topic_id))
							.setParameter("topic_id", Integer.parseInt(topic_id))
							.setParameter("learn_outme_id", learning_outcome_hid)
							.setParameter("modified_by", username).setParameter("modified_date", new Date())
							.setParameter("id", Integer.parseInt(id));
							msg = query.executeUpdate() > 0 ? "1" : "0";
					
					if ( old_hidden_att <= new_count_hidden) {
						for (int j = 1; j <= old_hidden_att; j++) {
							
							String editid = request.getParameter("eid"+j);
							
							EDU_CC_TB_NCH_THEORY_LEARNING_OBJECTIVES_CHILD add = (EDU_CC_TB_NCH_THEORY_LEARNING_OBJECTIVES_CHILD) session1
									.get(EDU_CC_TB_NCH_THEORY_LEARNING_OBJECTIVES_CHILD.class, Integer.parseInt(editid));
							
							String generic_competency = request.getParameter("generic_competency"+j);
							String subject_area = request.getParameter("subject_area"+j);
							String millers_level = request.getParameter("millers_level"+j);
							String specific_competency = request.getParameter("specific_competency"+j);
							String slo_outcome = request.getParameter("slo_outcome"+j);
							String blooms_domain = request.getParameter("blooms_domain"+j);
							String guilberts_level = request.getParameter("guilberts_level"+j);
							String mk_dk = request.getParameter("mk_dk"+j);
							String tl_methods = request.getParameter("tl_methods"+j);
							String form_assessment = request.getParameter("form_assessment"+j);
							String summ_assessment = request.getParameter("summ_assessment"+j);
							String int_departments = request.getParameter("int_departments"+j);
							add.setGeneric_competency(generic_competency);
						 	add.setSubject_area(subject_area);
						 	add.setMillers_level(Integer.parseInt(millers_level));
						 	add.setSpecific_competency(specific_competency);
						 	add.setSlo_outcome(slo_outcome);
						 	add.setBlooms_domain(Integer.parseInt(blooms_domain));
						 	add.setGuilberts_level(Integer.parseInt(guilberts_level));
						 	add.setMk_dk(Integer.parseInt(mk_dk));
						 	add.setTl_methods(Integer.parseInt(tl_methods));
							add.setForm_assessment(Integer.parseInt(form_assessment));
							add.setSumm_assessment(Integer.parseInt(summ_assessment));
						 	add.setInt_departments(int_departments);
							add.setCreated_by(username);
							add.setCreated_date(date);
							add.setId(Integer.parseInt(editid));
							session1.update(add);
							session1.flush();
							session1.clear();
						}
					}
					
					if ( old_hidden_att < new_count_hidden) {
						EDU_CC_TB_NCH_THEORY_LEARNING_OBJECTIVES_CHILD xray = new EDU_CC_TB_NCH_THEORY_LEARNING_OBJECTIVES_CHILD();
						
							for (int j = old_hidden_att  + 1; j <= new_count_hidden; j++) {
								
								String generic_competency = request.getParameter("generic_competency"+j);
								String subject_area = request.getParameter("subject_area"+j);
								String millers_level = request.getParameter("millers_level"+j);
								String specific_competency = request.getParameter("specific_competency"+j);
								String slo_outcome = request.getParameter("slo_outcome"+j);
								String blooms_domain = request.getParameter("blooms_domain"+j);
								String guilberts_level = request.getParameter("guilberts_level"+j);
								String mk_dk = request.getParameter("mk_dk"+j);
								String tl_methods = request.getParameter("tl_methods"+j);
								String form_assessment = request.getParameter("form_assessment"+j);
								String summ_assessment = request.getParameter("summ_assessment"+j);
								String int_departments = request.getParameter("int_departments"+j);
								xray.setGeneric_competency(generic_competency);
							 	xray.setSubject_area(subject_area);
							 	xray.setMillers_level(Integer.parseInt(millers_level));
							 	xray.setSpecific_competency(specific_competency);
							 	xray.setSlo_outcome(slo_outcome);
							 	xray.setBlooms_domain(Integer.parseInt(blooms_domain));
							 	xray.setGuilberts_level(Integer.parseInt(guilberts_level));
							 	xray.setMk_dk(Integer.parseInt(mk_dk));
							 	xray.setTl_methods(Integer.parseInt(tl_methods));
								xray.setForm_assessment(Integer.parseInt(form_assessment));
								xray.setSumm_assessment(Integer.parseInt(summ_assessment));
							 	xray.setInt_departments(int_departments);
								xray.setCreated_by(username);
								xray.setCreated_date(date);
								xray.setP_id(Integer.parseInt(id));
								session1.save(xray);
								session1.flush();
								session1.clear();
							}
					}
					
					if ( old_hidden_att > new_count_hidden) {
						
						for (int j = new_count_hidden + 1; j <= old_hidden_att; j++) {
							
							String editid = request.getParameter("eid"+j);
							
							EDU_CC_TB_NCH_THEORY_LEARNING_OBJECTIVES_CHILD del = (EDU_CC_TB_NCH_THEORY_LEARNING_OBJECTIVES_CHILD) session1
									.get(EDU_CC_TB_NCH_THEORY_LEARNING_OBJECTIVES_CHILD.class, Integer.parseInt(editid));
							session1.delete(del);
							session1.flush();
							session1.clear();
						}
					}
					tx.commit();
					ra.addAttribute("msg", "Data Updated Successfully.");
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
			return new ModelAndView("redirect:Nch_theory_learning_obj_url");
		}
		@PostMapping(value = "/delete_NCH_Search_Learning_Object_Url")
		public ModelAndView delete_NCH_Search_Learning_Object_Url(@ModelAttribute("id2") String id, BindingResult result, RedirectAttributes ra,
				HttpServletRequest request, ModelMap model, HttpSession session1) {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				 session1.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session1.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Nch_theory_learning_obj_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
			List<String> liststr = new ArrayList<String>();

			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			String username = session1.getAttribute("username").toString();
			
			try {
				int app = session.createQuery(
						"update EDU_CC_TB_NCH_THEORY_LEARNING_OBJECTIVES_PARENT set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
						.setParameter("id", Integer.parseInt(id)).setParameter("modified_by", username)
						.setParameter("modified_date", new Date()).setParameter("status", 2).executeUpdate();
				
				int app1 = session.createQuery(
						"update EDU_CC_TB_NCH_THEORY_LEARNING_OBJECTIVES_CHILD set modified_by=:modified_by,modified_date=:modified_date,status=:status where p_id=:p_id")
						.setParameter("p_id", Integer.parseInt(id)).setParameter("modified_by", username)
						.setParameter("modified_date", new Date()).setParameter("status", 2).executeUpdate();

				tx.commit();
				session.close();
				if (app > 0 && app1 > 0) {
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
			return new ModelAndView("redirect:Nch_theory_learning_obj_url");
		}
		
		@RequestMapping(value = "/Nch_getView_All_Screenviewdata", method = RequestMethod.POST)
		public @ResponseBody   List<ArrayList<String>> Nch_getView_All_Screenviewdata(String course_id) {
			List<ArrayList<String>> NCH_theoryLearning_Objectives_list = TLODAO.NCH_theoryLearning_Objectives(course_id);
			return NCH_theoryLearning_Objectives_list;
		}
}

