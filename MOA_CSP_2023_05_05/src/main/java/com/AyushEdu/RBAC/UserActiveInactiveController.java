package com.AyushEdu.RBAC;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.RBAC.UserActiveInactiveDAO;
@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class UserActiveInactiveController {

	
	@Autowired
	UserActiveInactiveDAO UADAO;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	RoleMstrController roleMstrController;
	
	// open Search User page
	@RequestMapping(value = "/search_user_active_Url", method = RequestMethod.GET)
	public ModelAndView search_user_active_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("search_user_active_Url", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		} else {
//			if (request.getHeader("Referer") == null) {
//				msg = "";
//			}
			Mmap.put("msg", msg);
			Mmap.put("getRoleNameList", roleMstrController.getRoleNameList());
//			Mmap.put("access_lvl1", access_lvl1);
//			Mmap.put("user_name", user_name1);
			return new ModelAndView("search_user_active_Tiles");
//		}
	}
	
	
	@PostMapping("/getFilterSearch_User_Active_data")
	public @ResponseBody List<Map<String, Object>> getFilterSearch_User_Active_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String role_id,String username,String loginname,String email_id,String enable_id) {
		return UADAO.DataTableSearch_User_ActiveDataList(startPage, pageLength, Search, orderColunm, orderType, role_id, username, loginname, email_id,enable_id);
	}
	
	@PostMapping("/getTotalSearch_User_Active_dataCount")
	public @ResponseBody long getTotalSearch_User_Active_dataCount(HttpSession session, String Search, String role_id,String username,String loginname,String email_id,String enable_id) {
		return UADAO.DataTableSearch_User_ActiveDataTotalCount(Search, role_id, username, loginname, email_id,enable_id);
	}
	
	
	@RequestMapping(value = "/USER_Login_Active_Url", method = RequestMethod.POST)
	public ModelAndView USER_Login_Active_Url(@ModelAttribute("user_id1") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {
		
//		if(request.getHeader("Referer") == null ) { 
//			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/login");
//		 }
//		String roleid1 = session1.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("SystemUrl", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		
		System.err.println("userId--active---->  "+id);
		
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
  
			int app = session.createQuery(
					"update UserLogin set enabled=:enabled where userId=:userId")
					.setParameter("enabled", 1).setParameter("userId", id).executeUpdate();
			
			tx.commit();
			session.close();
			if (app > 0) {
				liststr.add("User Activated Successfully.");
			} else {
				liststr.add("User Not Activated.");
			}
			ra.addAttribute("msg", liststr.get(0));
		} catch (Exception e) {
			e.printStackTrace();
			liststr.add("CAN NOT ACTIVATED THIS DATA BECAUSE IT IS ALREADY IN USED.");
			ra.addAttribute("msg", liststr.get(0));
			
		}
		return new ModelAndView("redirect:search_user_active_Url");
	}
	
	
	@RequestMapping(value = "/USER_Login_InActive_Url", method = RequestMethod.POST)
	public ModelAndView USER_Login_InActive_Url(@ModelAttribute("user_id2") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {
		
//		if(request.getHeader("Referer") == null ) { 
//		//	session1.invalidate();
//			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/login");
//		 }
//		String roleid1 = session1.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("SystemUrl", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
  
			int app = session.createQuery(
					"update UserLogin set enabled=:enabled where userId=:userId")
					.setParameter("enabled",0).setParameter("userId", id).executeUpdate();
			
			tx.commit();
			System.out.println();
			session.close();
			if (app > 0) {
				liststr.add("User Inactivated Successfully.");
			} else {
				liststr.add("User Not Inactivated.");
			}
			ra.addAttribute("msg", liststr.get(0));
		} catch (Exception e) {
			e.printStackTrace();
			liststr.add("CAN NOT INACTIVATED THIS DATA BECAUSE IT IS ALREADY IN USED.");
			ra.addAttribute("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:search_user_active_Url");
	}
	
	
	
}
