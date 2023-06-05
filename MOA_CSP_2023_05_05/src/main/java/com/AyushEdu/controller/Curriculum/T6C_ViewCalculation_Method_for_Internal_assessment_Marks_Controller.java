package com.AyushEdu.controller.Curriculum;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.AyushEdu.dao.Curriculum.T6C_View_Int_Marks_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class T6C_ViewCalculation_Method_for_Internal_assessment_Marks_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	ValidationController validation;
	
	@Autowired
	CommonController common;
	
	@Autowired
	private T6C_View_Int_Marks_Dao tcDAO;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/View_t6c_intern_assem_marks_Url", method = RequestMethod.GET)
	public ModelAndView View_t6c_intern_assem_marks_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("View_t6c_intern_assem_marks_Url", roleid1);		
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
		return new ModelAndView("View_t6c_intern_assem_marks_Tiles");
	}
	
	@RequestMapping(value = "/getMarksbyCourse", method = RequestMethod.POST)
	public @ResponseBody   ArrayList<ArrayList<String>> getMarksbyCourse(String course_id) {
	 ArrayList<ArrayList<String>> list = tcDAO.getMarksbyCoursedata(course_id );
		return list;
	}
	
}
