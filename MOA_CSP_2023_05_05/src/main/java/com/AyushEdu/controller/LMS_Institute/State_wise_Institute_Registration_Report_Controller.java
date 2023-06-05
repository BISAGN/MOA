package com.AyushEdu.controller.LMS_Institute;

import java.util.List;
import java.util.Map;

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
public class State_wise_Institute_Registration_Report_Controller {
	@Autowired
	// @Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Autowired
	CommonController common;
	@Autowired
	State_wise_Institute_Registration_Report_DAO SIRdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	

	@GetMapping(value = "/state_wise_inst_Reg_Report_url")
	public ModelAndView state_wise_inst_Reg_Report_url(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		

		//SECURITY RAHUL
		
		if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/login");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("state_wise_inst_Reg_Report_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			String role = session.getAttribute("role").toString();
		model.addAttribute("msg", msg);
		model.put("getInstituteList", common.getInstituteList(sessionFactory));
		model.put("getSystemList", common.getSystemList(sessionFactory,role));
		model.put("getcoursenameList", common.getcoursenameList(sessionFactory));
//		 model.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

		return new ModelAndView("state_wise_inst_Reg_Report_Tiles");

	}

	@PostMapping("/getFilter_state_wise_institute_reg_data")
	public @ResponseBody List<Map<String, Object>> getFilter_state_wise_institute_reg_data(int startPage,
			int pageLength, String Search, String orderColunm, String orderType, String institute_id, String system_id,
			String course_id) {
		
		return SIRdao.DataTable_state_wise_institute_regDataList(startPage, pageLength, Search, orderColunm, orderType,
				institute_id, system_id, course_id, 5, 19);

	}

	@PostMapping("/getTotal_state_wise_institute_reg_dataCount")
	public @ResponseBody long getTotal_state_wise_institute_reg_dataCount(HttpSession sessionUserId, String Search,
			String institute_id, String system_id, String course_id) {
	
		return SIRdao.DataTable_state_wise_institute_regDataTotalCount(Search, institute_id, system_id, course_id, 5,
				12);
	}
	

}
