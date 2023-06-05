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
import com.AyushEdu.Models.Curriculum.CC_TB_LIST_OF_TOPICS_PARENT;
import com.AyushEdu.Models.Curriculum.CC_TB_T3_LEARNING_OBJECT_CHILD;
import com.AyushEdu.Models.Curriculum.CC_TB_T3_LEARNING_OBJECT_PARENT;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum.Professional_Ayu_Report_Dao;
import com.AyushEdu.dao.Curriculum.T3_Search_Learning_Object_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class T3_Learning_Object_Controller {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	@Autowired
	CommonController common;
	
	@Autowired
	T3_Search_Learning_Object_Dao SLODAO;
	
	@Autowired
	Professional_Ayu_Report_Dao PARDAO;

	@Autowired
	ValidationController validation;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	
	
	@RequestMapping(value = "admin/T3_Learning_Object_Url", method = RequestMethod.GET)
	public ModelAndView T3_Learning_Object_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
//			SECURITY -- RIDDHI 	
			 if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("T3_Learning_Object_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String role = session.getAttribute("role").toString();	
		Mmap.put("msg", msg);
		Mmap.put("getSystemList", common.getSystemList(sessionFactory,role));
		Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
		Mmap.put("getCourse_OutcomeList", common.getCourse_OutcomeList(sessionFactory));
		Mmap.put("getc3_domain_subList", common.getc3_domain_subList(sessionFactory));
		Mmap.put("getd3_desirable_knowList", common.getd3_desirable_knowList(sessionFactory));
		Mmap.put("gete3_level_show_knowList", common.gete3_level_show_knowList(sessionFactory));
		Mmap.put("getf3_t_l_methodList", common.getf3_t_l_methodList(sessionFactory));
		Mmap.put("getg3_assessmentList", common.getg3_assessmentList(sessionFactory));
		Mmap.put("geth3_formative_summativeList", common.geth3_formative_summativeList(sessionFactory));
		Mmap.put("geti3_termList", common.geti3_termList(sessionFactory));
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		return new ModelAndView("T3_Learning_Object_Tiles");
	}
	@RequestMapping(value = "/getTopicListby_Course", method = RequestMethod.POST)
	public @ResponseBody List<CC_TB_LIST_OF_TOPICS_PARENT> getTopicListby_Course(String course_id)  {
	
		List<CC_TB_LIST_OF_TOPICS_PARENT> list =  common.getTopicListbyCourse(course_id);
		System.err.println("course_id"+list);
		return list;
	}
	
	@PostMapping(value = "/t3_learning_objectAction")
	public ModelAndView t3_learning_objectAction(@Validated @ModelAttribute("t3_learning_objectCMD") CC_TB_T3_LEARNING_OBJECT_PARENT td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal, RedirectAttributes ra,
			MultipartHttpServletRequest mul)throws IOException, ParseException {
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("T3_Learning_Object_Url", roleid1);		
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

		if (system_id.equals("0")) {
			ra.addAttribute("msg", "Please Select System");
			return new ModelAndView("redirect:T3_Learning_Object_Url");
		}
		if (degree_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:T3_Learning_Object_Url");
		}
		if (professional_id.equals("0")) {
			ra.addAttribute("msg", "Please Select professional");
			return new ModelAndView("redirect:T3_Learning_Object_Url");
		}
		if (course_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Subject");
			return new ModelAndView("redirect:T3_Learning_Object_Url");
		}
		if (topic_id.equals("0")) {
			ra.addAttribute("msg", "Please Select Topic");
			return new ModelAndView("redirect:T3_Learning_Object_Url");
		}
		try {

//			Long c = (Long) sessionHQL.createQuery(
//						"select count(id) from  CC_TB_T3_LEARNING_OBJECT_PARENT where system_id=:system_id and degree_id=:degree_id \n"
//						+ " and professional_id=:professional_id and course_id=:course_id and  topic_id=:topic_id and status=0\n"
//						+ " and id!=:id")
//					.setParameter("system_id", Integer.parseInt(system_id))
//					.setParameter("degree_id", Integer.parseInt(degree_id))
//					.setParameter("professional_id", Integer.parseInt(professional_id))
//					.setParameter("course_id", Integer.parseInt(course_id))
//					.setParameter("topic_id", Integer.parseInt(topic_id))
//					.setParameter("id", id).uniqueResult();
			
//			if (id == 0) {
//				if (c == 0) {
					td.setCreated_by(username);
					td.setCreated_date(date);
					
					int parent_id = (int) sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					
					CC_TB_T3_LEARNING_OBJECT_CHILD od = new CC_TB_T3_LEARNING_OBJECT_CHILD();
					
					int count_hidden_att = Integer.parseInt(request.getParameter("count_hidden_att"));
					for(int i=1; i <= count_hidden_att; i++) {
						
						String a3_couse_outcome = request.getParameter("a3_couse_outcome"+i);
						String b3_learning_obj = request.getParameter("b3_learning_obj"+i);
						String c3_domain_sub = request.getParameter("c3_domain_sub"+i);
						String d3_desirable_know = request.getParameter("d3_desirable_know"+i);
						String e3_level_show_know = request.getParameter("e3_level_show_know"+i);
						String f3_t_l_method = request.getParameter("f3_t_l_method"+i);
						String g3_assessment = request.getParameter("g3_assessment"+i);
						String h3_formative_summative = request.getParameter("h3_formative_summative"+i);
						String i3_term = request.getParameter("i3_term"+i);
						String j3_integration = request.getParameter("j3_integration"+i);
						
						
						if (a3_couse_outcome.equals("0")) {
							ra.addAttribute("msg", "Please Select A3 Course Outcome.");
							return new ModelAndView("redirect:T3_Learning_Object_Url");
						}
						if (b3_learning_obj.equals("0")) {
							ra.addAttribute("msg", "Please Select B3 Learning Objective.");
							return new ModelAndView("redirect:T3_Learning_Object_Url");
						}
						if (validation.checkDescriptionLengthHelpdeskLength(b3_learning_obj) == false) {
							ra.addAttribute("msg", "Lecture Hours " + validation.DescriptionLengthHelpdeskMSG);
							return new ModelAndView("redirect:T3_Learning_Object_Url");
						}
						if (c3_domain_sub.equals("0")) {
							ra.addAttribute("msg", "Please Select C3 Domain/sub.");
							return new ModelAndView("redirect:T3_Learning_Object_Url");
						}
						if (d3_desirable_know.equals("0")) {
							ra.addAttribute("msg", "Please Select D3 Must to know/desirable to know/Nice to know.");
							return new ModelAndView("redirect:T3_Learning_Object_Url");
						}
						if (e3_level_show_know.equals("0")) {
							ra.addAttribute("msg", "Please Select E3 Level Does/Shows how/Knows how/Know.");
							return new ModelAndView("redirect:T3_Learning_Object_Url");
						}
						if (f3_t_l_method.equals("0")) {
							ra.addAttribute("msg", "Please Select F3 T-L method.");
							return new ModelAndView("redirect:T3_Learning_Object_Url");
						}
						if (g3_assessment.equals("0")) {
							ra.addAttribute("msg", "Please Select G3 Assessment.");
							return new ModelAndView("redirect:T3_Learning_Object_Url");
						}
						if (h3_formative_summative.equals("0")) {
							ra.addAttribute("msg", "Please Select H3 Formative/Summative.");
							return new ModelAndView("redirect:T3_Learning_Object_Url");
						}
						if (i3_term.equals("0")) {
							ra.addAttribute("msg", "Please Select I3 Term.");
							return new ModelAndView("redirect:T3_Learning_Object_Url");
						}
						if (j3_integration.equals("0")) {
							ra.addAttribute("msg", "Please Select J3 Integration.");
							return new ModelAndView("redirect:T3_Learning_Object_Url");
						}
					 	od.setA3_couse_outcome(Integer.parseInt(a3_couse_outcome));
					 	od.setB3_learning_obj(b3_learning_obj);
					 	od.setC3_domain_sub(Integer.parseInt(c3_domain_sub));
					 	od.setD3_desirable_know(Integer.parseInt(d3_desirable_know));
					 	od.setE3_level_show_know(Integer.parseInt(e3_level_show_know));
					 	od.setF3_t_l_method(Integer.parseInt(f3_t_l_method));
					 	od.setG3_assessment(Integer.parseInt(g3_assessment));
					 	od.setH3_formative_summative(Integer.parseInt(h3_formative_summative));
					 	od.setI3_term(Integer.parseInt(i3_term));
					 	od.setJ3_integration(j3_integration);
					 	
					 	od.setP_id(parent_id);
					 	od.setCreated_by(username);
					 	od.setCreated_date(date);
						sessionHQL.save(od);
						sessionHQL.flush();
						sessionHQL.clear();
					}
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
		return new ModelAndView("redirect:T3_Learning_Object_Url");
	}
	
	@RequestMapping(value = "admin/T3_Search_Learning_Object_Url", method = RequestMethod.GET)
	public ModelAndView T3_Search_Learning_Object_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("T3_Learning_Object_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
		String role = session.getAttribute("role").toString();	
		Mmap.put("msg", msg);
		Mmap.put("getSystemList", common.getSystemList(sessionFactory,role));	
		Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
		Mmap.put("getCourse_OutcomeList", common.getCourse_OutcomeList(sessionFactory));
		Mmap.put("getc3_domain_subList", common.getc3_domain_subList(sessionFactory));
		Mmap.put("getd3_desirable_knowList", common.getd3_desirable_knowList(sessionFactory));
		Mmap.put("gete3_level_show_knowList", common.gete3_level_show_knowList(sessionFactory));
		Mmap.put("getf3_t_l_methodList", common.getf3_t_l_methodList(sessionFactory));
		Mmap.put("getg3_assessmentList", common.getg3_assessmentList(sessionFactory));
		Mmap.put("geth3_formative_summativeList", common.geth3_formative_summativeList(sessionFactory));
		Mmap.put("geti3_termList", common.geti3_termList(sessionFactory));
		Mmap.put("getDegreeList", common.getDegreeList(sessionFactory));
		Mmap.put("getCourseList", common.getCourseList(sessionFactory));
		Mmap.put("getTopicList", common.getTopicList(sessionFactory));
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		return new ModelAndView("T3_Search_Learning_Object_Tiles");
	}
	
	 @PostMapping("/getFilterT3SearchLearningObject_data")
		public @ResponseBody List<Map<String, Object>> getFilterT3SearchLearningObject_data(int startPage, int pageLength,
				String Search, String orderColunm, String orderType, String system_id, String degree_id,String professional_id,String course_id,String topic_id,HttpSession session) {
		 String role = session.getAttribute("role").toString();	
		 return SLODAO.DataTableT3SearchLearningObjectDataList(startPage, pageLength, Search, orderColunm, orderType, system_id, degree_id,professional_id,course_id,topic_id,role);

		}
	 
	 @PostMapping("/getTotalT3SearchLearningObject_dataCount")
		public @ResponseBody long getTotalT3SearchLearningObject_dataCount(HttpSession sessionUserId, String Search, String system_id, String degree_id,String professional_id,String course_id,String topic_id,HttpSession session) {
		 String role = session.getAttribute("role").toString();
			return SLODAO.DataTableT3SearchLearningObjectDataTotalCount(Search, system_id, degree_id,professional_id,course_id,topic_id,role);
		}
		@RequestMapping(value = "/T3SearchLearningObject_ChildUrl", method = RequestMethod.POST)
		public @ResponseBody   ArrayList<ArrayList<String>> T3SearchLearningObject_ChildUrl(String hid) {
		 ArrayList<ArrayList<String>> list = SLODAO.getPopup_Child_LearningDatalist(hid);
			return list;
		}
		
		@RequestMapping(value = "/Edit_T3_Search_Learning_Object_Url", method = RequestMethod.POST)
		public ModelAndView Edit_T3_Search_Learning_Object_Url(@ModelAttribute("id1") int id, ModelMap model,
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
				 Boolean val = roledao.ScreenRedirect("T3_Learning_Object_Url", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
			ArrayList<ArrayList<String>> GetLearning_Parent_Data = SLODAO.GetLearning_Parent_Data(id);
			List<ArrayList<String>> liteLearningchildlist = SLODAO.getLearning_Child_By_id(id);
			
			String role = session.getAttribute("role").toString();	
			model.put("edit_t3_learning_objectCMD", SLODAO.GetLearning_Parent_Data(id));
			model.put("list", GetLearning_Parent_Data);
			model.put("liteLearningchildlist", liteLearningchildlist);
			model.put("getSystemList", common.getSystemList(sessionFactory,role));	
			model.put("getprofessionalList", common.getprofessionalList(sessionFactory));
			//model.put("getTopicList", common.getTopicList(sessionFactory));
			
			model.put("getCourse_OutcomeList", common.getCourse_OutcomeList(sessionFactory));
			model.put("getc3_domain_subList", common.getc3_domain_subList(sessionFactory));
			model.put("getd3_desirable_knowList", common.getd3_desirable_knowList(sessionFactory));
			model.put("gete3_level_show_knowList", common.gete3_level_show_knowList(sessionFactory));
			model.put("getf3_t_l_methodList", common.getf3_t_l_methodList(sessionFactory));
			model.put("getg3_assessmentList", common.getg3_assessmentList(sessionFactory));
			model.put("geth3_formative_summativeList", common.geth3_formative_summativeList(sessionFactory));
			model.put("geti3_termList", common.geti3_termList(sessionFactory));
			
			model.put("msg", msg);
		  } catch (Exception e) {
				e.printStackTrace();
		  }
			return new ModelAndView("Edit_T3_Search_Learning_Object_Tiles");
		}
		
		@RequestMapping(value = "/edit_t3_learning_objectAction", method = RequestMethod.POST)
		public ModelAndView edit_t3_learning_objectAction(@ModelAttribute("edit_t3_learning_objectCMD") CC_TB_T3_LEARNING_OBJECT_PARENT rs,
				HttpServletRequest request, ModelMap model, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("T3_Learning_Object_Url", roleid1);		
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
			
			if (system_id.equals("0")) {
				ra.addAttribute("msg", "Please Select System");
				return new ModelAndView("redirect:T3_Learning_Object_Url");
			}
			if (degree_id.equals("0")) {
				ra.addAttribute("msg", "Please Select Degree");
				return new ModelAndView("redirect:T3_Learning_Object_Url");
			}
			if (professional_id.equals("0")) {
				ra.addAttribute("msg", "Please Select professional");
				return new ModelAndView("redirect:T3_Learning_Object_Url");
			}
			if (course_id.equals("0")) {
				ra.addAttribute("msg", "Please Select Subject");
				return new ModelAndView("redirect:T3_Learning_Object_Url");
			}
			if (topic_id.equals("0")) {
				ra.addAttribute("msg", "Please Select Topic");
				return new ModelAndView("redirect:T3_Learning_Object_Url");
			}
			
			int new_count_hidden = Integer.parseInt(request.getParameter("new_count_hidden"));
			int old_hidden_att = Integer.parseInt(request.getParameter("old_count"));
			
			for(int i=1; i <= new_count_hidden; i++) {

				String a3_couse_outcome = request.getParameter("a3_couse_outcome"+i);
				String b3_learning_obj = request.getParameter("b3_learning_obj"+i);
				String c3_domain_sub = request.getParameter("c3_domain_sub"+i);
				String d3_desirable_know = request.getParameter("d3_desirable_know"+i);
				String e3_level_show_know = request.getParameter("e3_level_show_know"+i);
				String f3_t_l_method = request.getParameter("f3_t_l_method"+i);
				String g3_assessment = request.getParameter("g3_assessment"+i);
				String h3_formative_summative = request.getParameter("h3_formative_summative"+i);
				String i3_term = request.getParameter("i3_term"+i);
				String j3_integration = request.getParameter("j3_integration"+i);
				
				if (a3_couse_outcome.equals("0")) {
					ra.addAttribute("msg", "Please Select A3 Course Outcome.");
					return new ModelAndView("redirect:T3_Learning_Object_Url");
				}
				if (b3_learning_obj.equals("0")) {
					ra.addAttribute("msg", "Please Select B3 Learning Objective.");
					return new ModelAndView("redirect:T3_Learning_Object_Url");
				}
				if (c3_domain_sub.equals("0")) {
					ra.addAttribute("msg", "Please Select C3 Domain/sub.");
					return new ModelAndView("redirect:T3_Learning_Object_Url");
				}
				if (d3_desirable_know.equals("0")) {
					ra.addAttribute("msg", "Please Select D3 Must to know/desirable to know/Nice to know.");
					return new ModelAndView("redirect:T3_Learning_Object_Url");
				}
				if (e3_level_show_know.equals("0")) {
					ra.addAttribute("msg", "Please Select E3 Level Does/Shows how/Knows how/Know.");
					return new ModelAndView("redirect:T3_Learning_Object_Url");
				}
				if (f3_t_l_method.equals("0")) {
					ra.addAttribute("msg", "Please Select F3 T-L method.");
					return new ModelAndView("redirect:T3_Learning_Object_Url");
				}
				if (g3_assessment.equals("0")) {
					ra.addAttribute("msg", "Please Select G3 Assessment.");
					return new ModelAndView("redirect:T3_Learning_Object_Url");
				}
				if (h3_formative_summative.equals("0")) {
					ra.addAttribute("msg", "Please Select H3 Formative/Summative.");
					return new ModelAndView("redirect:T3_Learning_Object_Url");
				}
				if (i3_term.equals("0")) {
					ra.addAttribute("msg", "Please Select I3 Term.");
					return new ModelAndView("redirect:T3_Learning_Object_Url");
				}
				if (j3_integration.equals("0")) {
					ra.addAttribute("msg", "Please Select J3 Integration.");
					return new ModelAndView("redirect:T3_Learning_Object_Url");
				}
			}
			try {
					String hql = "update CC_TB_T3_LEARNING_OBJECT_PARENT set system_id=:system_id,degree_id=:degree_id,professional_id=:professional_id,course_id=:course_id,topic_id=:topic_id,modified_by=:modified_by,modified_date=:modified_date"
							+ " where id=:id";

					Query query = session1.createQuery(hql).setParameter("system_id", Integer.parseInt(system_id))
							.setParameter("degree_id", Integer.parseInt(degree_id))
							.setParameter("professional_id", Integer.parseInt(professional_id))
							.setParameter("course_id", Integer.parseInt(course_id))
							.setParameter("topic_id", Integer.parseInt(topic_id))
							.setParameter("modified_by", username).setParameter("modified_date", new Date())
							.setParameter("id", Integer.parseInt(id));
							msg = query.executeUpdate() > 0 ? "1" : "0";
					
					if ( old_hidden_att <= new_count_hidden) {
						for (int j = 1; j <= old_hidden_att; j++) {
							
							String editid = request.getParameter("eid"+j);
							
							CC_TB_T3_LEARNING_OBJECT_CHILD add = (CC_TB_T3_LEARNING_OBJECT_CHILD) session1
									.get(CC_TB_T3_LEARNING_OBJECT_CHILD.class, Integer.parseInt(editid));
							
							String a3_couse_outcome = request.getParameter("a3_couse_outcome"+j);
							String b3_learning_obj = request.getParameter("b3_learning_obj"+j);
							String c3_domain_sub = request.getParameter("c3_domain_sub"+j);
							String d3_desirable_know = request.getParameter("d3_desirable_know"+j);
							String e3_level_show_know = request.getParameter("e3_level_show_know"+j);
							String f3_t_l_method = request.getParameter("f3_t_l_method"+j);
							String g3_assessment = request.getParameter("g3_assessment"+j);
							String h3_formative_summative = request.getParameter("h3_formative_summative"+j);
							String i3_term = request.getParameter("i3_term"+j);
							String j3_integration = request.getParameter("j3_integration"+j);
							add.setA3_couse_outcome(Integer.parseInt(a3_couse_outcome));
						 	add.setB3_learning_obj(b3_learning_obj);
						 	add.setC3_domain_sub(Integer.parseInt(c3_domain_sub));
						 	add.setD3_desirable_know(Integer.parseInt(d3_desirable_know));
						 	add.setE3_level_show_know(Integer.parseInt(e3_level_show_know));
						 	add.setF3_t_l_method(Integer.parseInt(f3_t_l_method));
						 	add.setG3_assessment(Integer.parseInt(g3_assessment));
						 	add.setH3_formative_summative(Integer.parseInt(h3_formative_summative));
						 	add.setI3_term(Integer.parseInt(i3_term));
						 	add.setJ3_integration(j3_integration);
							add.setCreated_by(username);
							add.setCreated_date(date);
							add.setId(Integer.parseInt(editid));
							session1.update(add);
							session1.flush();
							session1.clear();
						}
					}
					
					if ( old_hidden_att < new_count_hidden) {
						CC_TB_T3_LEARNING_OBJECT_CHILD xray = new CC_TB_T3_LEARNING_OBJECT_CHILD();
						
							for (int j = old_hidden_att  + 1; j <= new_count_hidden; j++) {
								
								String a3_couse_outcome = request.getParameter("a3_couse_outcome"+j);
								String b3_learning_obj = request.getParameter("b3_learning_obj"+j);
								String c3_domain_sub = request.getParameter("c3_domain_sub"+j);
								String d3_desirable_know = request.getParameter("d3_desirable_know"+j);
								String e3_level_show_know = request.getParameter("e3_level_show_know"+j);
								String f3_t_l_method = request.getParameter("f3_t_l_method"+j);
								String g3_assessment = request.getParameter("g3_assessment"+j);
								String h3_formative_summative = request.getParameter("h3_formative_summative"+j);
								String i3_term = request.getParameter("i3_term"+j);
								String j3_integration = request.getParameter("j3_integration"+j);
								xray.setA3_couse_outcome(Integer.parseInt(a3_couse_outcome));
							 	xray.setB3_learning_obj(b3_learning_obj);
							 	xray.setC3_domain_sub(Integer.parseInt(c3_domain_sub));
							 	xray.setD3_desirable_know(Integer.parseInt(d3_desirable_know));
							 	xray.setE3_level_show_know(Integer.parseInt(e3_level_show_know));
							 	xray.setF3_t_l_method(Integer.parseInt(f3_t_l_method));
							 	xray.setG3_assessment(Integer.parseInt(g3_assessment));
							 	xray.setH3_formative_summative(Integer.parseInt(h3_formative_summative));
							 	xray.setI3_term(Integer.parseInt(i3_term));
							 	xray.setJ3_integration(j3_integration);
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
							
							CC_TB_T3_LEARNING_OBJECT_CHILD del = (CC_TB_T3_LEARNING_OBJECT_CHILD) session1
									.get(CC_TB_T3_LEARNING_OBJECT_CHILD.class, Integer.parseInt(editid));
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
			return new ModelAndView("redirect:T3_Search_Learning_Object_Url");
		}
		
		@PostMapping(value = "/delete_T3_Search_Learning_Object_Url")
		public ModelAndView delete_T3_Search_Learning_Object_Url(@ModelAttribute("id2") String id, BindingResult result, RedirectAttributes ra,
				HttpServletRequest request, ModelMap model, HttpSession session1) {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				 session1.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session1.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("T3_Learning_Object_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
			List<String> liststr = new ArrayList<String>();

			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			String username = session1.getAttribute("username").toString();
			
			try {
				int app = session.createQuery(
						"update CC_TB_T3_LEARNING_OBJECT_PARENT set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
						.setParameter("id", Integer.parseInt(id)).setParameter("modified_by", username)
						.setParameter("modified_date", new Date()).setParameter("status", 2).executeUpdate();
				
				int app1 = session.createQuery(
						"update CC_TB_T3_LEARNING_OBJECT_CHILD set modified_by=:modified_by,modified_date=:modified_date,status=:status where p_id=:p_id")
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
			return new ModelAndView("redirect:T3_Search_Learning_Object_Url");
		}
		
		@RequestMapping(value = "/getView_All_Screenviewdata", method = RequestMethod.POST)
		public @ResponseBody   List<ArrayList<String>> getView_All_Screenviewdata(String course_id) {
			List<ArrayList<String>> t3LearningObject_Course_AyUGRS_list = PARDAO.table3_Learning_Objectives_Course_AyUGRS(course_id);
			return t3LearningObject_Course_AyUGRS_list;
		}
}
