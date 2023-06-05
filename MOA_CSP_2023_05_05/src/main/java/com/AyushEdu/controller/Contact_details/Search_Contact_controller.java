package com.AyushEdu.controller.Contact_details;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Contact_details.Contact_Dao;

@Controller
public class Search_Contact_controller {
	@Autowired
	CommonController common;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	Contact_Dao  CDdao;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/Search_Contact_Us_Url", method = RequestMethod.GET)
	public ModelAndView Search_Contact_Us_Url(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
//		SECURITY -- RIDDHI 
		 if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Search_Contact_Us_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		Mmap.put("msg", msg);
		
		return new ModelAndView("Search_Contact_Us_Tiles");

	}
	 
	 @PostMapping("admin/getSearch_contactus_dataList")
		public @ResponseBody List<Map<String, Object>> getSearch_contactus_dataList(HttpSession sessionUserId, int startPage,
				int pageLength, String Search, String orderColunm, String orderType, String name, String email, String subject,
				String message, String date) {
		 System.out.println("hi  =========================");
			return CDdao.DataTableSearch_contactusList(startPage, pageLength, Search, orderColunm, orderType,  name, email,
					subject, message, date);

		}

		@PostMapping("admin/getTotalSearch_contactus_dataCount")
		public @ResponseBody long getTotalSearch_contactus_dataCount(HttpSession sessionUserId, String Search, String name, String email, String subject,
				String message, String date) {
			return CDdao.DataTableSearch_contactus_count(Search, name, email,subject, message,date);
		}

}
