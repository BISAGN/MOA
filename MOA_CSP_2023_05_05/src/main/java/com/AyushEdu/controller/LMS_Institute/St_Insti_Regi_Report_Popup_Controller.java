package com.AyushEdu.controller.LMS_Institute;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Institute.State_wise_Institute_Registration_Report_DAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class St_Insti_Regi_Report_Popup_Controller {

	@Autowired
	// @Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Autowired
	CommonController common;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	State_wise_Institute_Registration_Report_DAO SIRdao;
	
	@PostMapping(value = "/st_inst_reg_report_popupurl")
	public ModelAndView st_inst_reg_report_popupurl(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		
		if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("st_inst_reg_report_popupurl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		String institute_id2 = request.getParameter("institute_id2");
		model.addAttribute("msg", msg);
		model.put("list", SIRdao. getSystem_Institute_course_list(0,"",institute_id2,5,12));

		return new ModelAndView("St_Inst_Reg_Report_Popup_Tiles");

	}
	
}
