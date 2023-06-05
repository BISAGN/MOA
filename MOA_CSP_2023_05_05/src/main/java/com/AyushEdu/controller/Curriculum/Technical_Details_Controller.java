package com.AyushEdu.controller.Curriculum;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Technical_Details_Controller {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	ValidationController validation;
	
	@Autowired
	CommonController common;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "admin/View_Technical_Details_Url", method = RequestMethod.GET)
	public ModelAndView View_Technical_Details_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("View_Technical_Details_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
		Mmap.put("msg", msg);

	} catch (Exception e) {
		e.printStackTrace();
	}

		return new ModelAndView("View_Technical_Details_Tiles");
	}
	
	@RequestMapping(value = "/Edit_Technical_Details_Url", method = RequestMethod.POST)
	public ModelAndView Edit_Technical_Details_Url(@ModelAttribute("id1") int id, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,Authentication authentication,
			HttpSession session,HttpServletRequest request) {

		try {	
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("View_Technical_Details_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
//		ArrayList<ArrayList<String>> Techlist = ANLADAO.GetTechnical_Details_Data(id);
		
//		model.put("edit_Technical_DetailsCMD", ANLADAO.GetTechnical_Details_Data(id));
//		model.put("list", GetTechnical_Details_Data);
		model.put("getDegreeList", common.getDegreeList(sessionFactory));
		model.put("getprofessionalList", common.getprofessionalList(sessionFactory));
		model.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		
		model.put("msg", msg);
	  } catch (Exception e) {
			e.printStackTrace();
	  }
		return new ModelAndView("Edit_Technical_Details_Tiles");
	}
}
