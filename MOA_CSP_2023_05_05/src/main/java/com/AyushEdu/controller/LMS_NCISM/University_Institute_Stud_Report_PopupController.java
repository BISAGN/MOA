package com.AyushEdu.controller.LMS_NCISM;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.dao.LMS_NCISM.University_Institute_Stud_ReportDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class University_Institute_Stud_Report_PopupController {
	@Autowired
	private SessionFactory sessionFactory;
	

	@Autowired
	CommonController common;
	@Autowired
	University_Institute_Stud_ReportDAO UISRD;
	
//	@PostMapping(value = "/University_Institute_Stud_Result_View_Url")
//	public ModelAndView University_Institute_Stud_Result_View_Url(ModelMap model, HttpSession session,
//			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//		
//		String uid = request.getParameter("userid2");
////		String userId = session.getAttribute("userId_for_jnlp").toString();
////		String institute_id2 = request.getParameter("institute_id2");
////		System.err.println("institute_id2================="+institute_id2);
//		model.addAttribute("msg", msg);
//		model.put("list", UISRD. getPopup_Data(uid));
//		
//
//		return new ModelAndView("University_Institute_Stud_Result_View_Tiles");
//
//	}

}
