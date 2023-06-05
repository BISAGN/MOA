package com.AyushEdu.controller.Curriculum;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.AyushEdu.Models.Curriculum.CC_TB_SIXF_DISTRIBUTION_OF_THEORY_EXAM;
import com.AyushEdu.Models.Curriculum.EDU_CC_TB_ADD_NON_LECTURE_ACTIVITIES_CHILD;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum.SixF_Distribution_of_Theory_Exam_Dao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class SixF_Distribution_of_Theory_Exam_Controller {
	
	@Autowired
	CommonController common;
	
	@Autowired
	SixF_Distribution_of_Theory_Exam_Dao dao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@RequestMapping(value = "admin/SixF_Distribution_Theory_Exam_url", method = RequestMethod.GET)
	public ModelAndView SixF_Distribution_Theory_Exam_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("SixF_Distribution_Theory_Exam_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String role = session.getAttribute("role").toString();	
		Mmap.put("msg", msg);
		Mmap.put("getSystemList", common.getSystemList(sessionFactory,role));
		Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
		
	} catch (Exception e) {
		e.printStackTrace();
	}

		return new ModelAndView("SixF_Distribution_Theory_ExamTiles");
	}

	@RequestMapping(value = "/getSixFviewdata", method = RequestMethod.POST)
	public @ResponseBody   ArrayList<ArrayList<String>> getSixFviewdata(String course_id,HttpSession session) {
		 String role = session.getAttribute("role").toString();
//		System.err.println("----course_id-------"+course_id);
	 ArrayList<ArrayList<String>> list = dao.getSixFViewdatabyCourse(course_id, role);
		return list;
	}
	
	@PostMapping(value = "/SixF_Distribution_Theory_Action")
	public ModelAndView SixF_Distribution_Theory_Action(@Validated @ModelAttribute("SixF_Distribution_Theory_CMD") CC_TB_SIXF_DISTRIBUTION_OF_THEORY_EXAM td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("SixF_Distribution_Theory_Exam_url", roleid1);		
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
		
		
		
		try {
			td.setSystem_id(Integer.parseInt(system_id));
			td.setDegree_id(Integer.parseInt(degree_id));
			td.setProfessional_id(Integer.parseInt(professional_id));
			td.setCourse_id(Integer.parseInt(course_id));
			
			int count_hidden_att = Integer.parseInt(request.getParameter("count_hidden_att"));
			
			for(int i=1;i<=count_hidden_att;i++) {
					String topic_id = request.getParameter("topicid"+i);
					String mcq = request.getParameter("mcqcbval"+i);
					String saq = request.getParameter("saqcbval"+i);
					String laq = request.getParameter("laqcbval"+i);
					
							
							td.setCreated_by(username);
							td.setCreated_date(date);
							td.setTopic_id(Integer.parseInt(topic_id));
							td.setMcq(Integer.parseInt(mcq));
							td.setSaq(Integer.parseInt(saq));
							td.setLaq(Integer.parseInt(laq));
							td.setStatus(0);
							
							sessionHQL.save(td);
							sessionHQL.flush();
							sessionHQL.clear();
//						}
//					}else{
//							ra.addAttribute("msg", "Data already Exist.");
//					}
				}
			tx.commit();
			ra.addAttribute("msg", "Data Saved Successfully.");
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
		return new ModelAndView("redirect:SixF_Distribution_Theory_Exam_url");	}
}