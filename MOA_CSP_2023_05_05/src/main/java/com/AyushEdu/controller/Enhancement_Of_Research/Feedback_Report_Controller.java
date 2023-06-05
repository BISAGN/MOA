package com.AyushEdu.controller.Enhancement_Of_Research;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
@Controller
@RequestMapping(value = {"admin", "/", "user"})
public class Feedback_Report_Controller {
	
	@Autowired
	CommonController common;
	
	@Autowired
	Commondao commondao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	
	@RequestMapping(value = "/Feedback_Report_Url", method = RequestMethod.GET)
	public ModelAndView Feedback_Report_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
//		SECURITY -- RIDDHI 
//		if(request.getHeader("Referer") == null ) { 
////			session.invalidate();
//			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("Feedback_Report_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		
//		Mmap.put("msg", msg);
//		Mmap.put("getUniverCityList", commondao.getUniversityNchlist());
//		Mmap.put("getinstitutelist", SSRDao.getinstitutelist(userid));
//		Mmap.put("getgenderList", common.getgenderList(sessionFactory));
		
		
		
		return new ModelAndView("Feedback_Report_Tiles");
	}
	
	
	@PostMapping("/getFilterSearch_Feedback_data")
	public @ResponseBody ArrayList<Map<String, Object>> getFilterSearch_Feedback_data(HttpSession session, int startPage,
			             int pageLength,String Search, String orderColunm, String orderType, String type_of_issue, String first_name,String last_name, String email) {
		
		return (ArrayList<Map<String, Object>>) commondao.Feedback_DataList(startPage, pageLength, Search,
				orderColunm, orderType,  type_of_issue,  first_name, last_name,  email);

	}

	@PostMapping("/getTotalSearch_Feedback_dataCount")
	public @ResponseBody long getTotalSearch_Feedback_dataCount(HttpSession sessionUserId, String Search,
			String type_of_issue, String first_name,String last_name, String email) {

		return commondao.DataTotalFeedbackCount(Search, type_of_issue,  first_name, last_name,  email);

	}
}
