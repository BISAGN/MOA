package com.AyushEdu.controller.Curriculum;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum.View_Scheme_of_AssessmentDao;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class View_Scheme_of_Assessment_Controller {
	
	@Autowired
	CommonController common;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	View_Scheme_of_AssessmentDao SOADao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "admin/View_Scheme_of_Assessment_url", method = RequestMethod.GET)
	public ModelAndView View_Scheme_of_Assessment_url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("View_Scheme_of_Assessment_url", roleid1);		
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
		return new ModelAndView("View_Scheme_of_AssessmentTiles");
	}

	@RequestMapping(value = "/get6BSchemeviewdata", method = RequestMethod.POST)
	public @ResponseBody   ArrayList<ArrayList<String>> get6BSchemeviewdata(String course_id,String term,HttpSession session) {
//		String role = session.getAttribute("role").toString();	
	 ArrayList<ArrayList<String>> list = SOADao.get6BSchemeViewdatabyCourse(course_id,term);
		return list;
	}
}
