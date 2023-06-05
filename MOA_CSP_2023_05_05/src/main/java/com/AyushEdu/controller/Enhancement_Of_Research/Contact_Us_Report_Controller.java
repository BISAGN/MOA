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
public class Contact_Us_Report_Controller {
	@Autowired
	CommonController common;
	
	@Autowired
	Commondao commondao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/ContactUs_Report_Url", method = RequestMethod.GET)
	public ModelAndView ContactUs_Report_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
//		SECURITY -- RIDDHI 
//		if(request.getHeader("Referer") == null ) { 
////			session.invalidate();
//			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("ContactUs_Report_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		
//		Mmap.put("msg", msg);
//		Mmap.put("getUniverCityList", commondao.getUniversityNchlist());
//		Mmap.put("getinstitutelist", SSRDao.getinstitutelist(userid));
//		Mmap.put("getgenderList", common.getgenderList(sessionFactory));
		
		
		
		return new ModelAndView("ContactUs_Report_Tiles");
	}
	
	@PostMapping("/getFilterSearch_Contact_Us_data1")
	public @ResponseBody ArrayList<Map<String, Object>> getFilterSearch_Contact_Us_data1(HttpSession session, int startPage,
			             int pageLength,String Search, String orderColunm, String orderType, String name, String email) {

		return (ArrayList<Map<String, Object>>) commondao.Contact_UsDataList(startPage, pageLength, Search,
				orderColunm, orderType, name ,email);

	}

	@PostMapping("/getTotalSearch_Contact_Us_dataCount")
	public @ResponseBody long getTotalSearch_Contact_Us_dataCount(HttpSession sessionUserId, String Search,
			String name, String email) {

		return commondao.DataTotalContact_UsCount(Search,name,email);

	}
}
