package com.AyushEdu.controller.LMS_Course_Publisher;

import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_COURSE_PUBLISHER_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Course_Publisher.Course_Publisher_Act_Inact_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = {"admin", "/", "user"})
public class Course_Publisher_Act_Inact_Controller {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;

	@Autowired
	Course_Publisher_Act_Inact_DAO CP_actdao;
	
	@Autowired
	 ValidationController validation;
	
	//==========================================OPEN PAGE GENDER========================================== 
	
		@RequestMapping(value = "/course_publisher_Act_InactUrl", method = RequestMethod.GET)
		public ModelAndView course_publisher_Act_InactUrl(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		try {	
			
			//SECURITY RAHUL
			
			if(request.getHeader("Referer") == null ) { 
		//		 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("course_publisher_Act_InactUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
		   Mmap.put("msg", msg);

			 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		} catch (Exception e) {
			e.printStackTrace();
		}
			return new ModelAndView("course_publisher_Act_InactTiles");
		}
		
		//==========================================SAVE SYSTEM NAME========================================== 	
		
			@PostMapping(value = "/course_publi_Act_InactAction")
			public ModelAndView course_publi_Act_InactAction(@Validated @ModelAttribute("course_publi_Act_InactCMD") EDU_LMS_COURSE_PUBLISHER_MSTR td, BindingResult result,
					HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
					RedirectAttributes ra) {

				
				if(request.getHeader("Referer") == null ) { 
				//	 session.invalidate();
					 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("course_publisher_Act_InactUrl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
					
				String name = request.getParameter("name");
				String status = request.getParameter("status");

				if (name == null || name.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Publisher Name.");
					return new ModelAndView("redirect:course_publisher_Act_InactUrl");
				}
				
				if (validation.maxlengthcheck50(name) == false) {
					ra.addAttribute("msg","Publisher Name "+ validation.MaxlengthcheckMSG50);
					return new ModelAndView("redirect:course_publisher_Act_InactUrl");
				}
				
				if (validation.isOnlyAlphabetDASH(name) == false) {
					ra.addAttribute("msg","Publisher Name "+ validation.isOnlyAlphabetMSGDASH);
					return new ModelAndView("redirect:course_publisher_Act_InactUrl");
				}
				
				if (status == null || status.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select Status.");
					return new ModelAndView("redirect:course_publisher_Act_InactUrl");
				}
				if (status == "2" || status.trim().equals("2")) {
					ra.addAttribute("msg", "Only Select Active Status.");
					return new ModelAndView("redirect:course_publisher_Act_InactUrl");
				}

				int id = td.getId() > 0 ? td.getId() : 0;
				Date date = new Date();
				String username = principal.getName();
//				String system_name = principal.getName();

				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();

				try {
					Long c = (Long) sessionHQL.createQuery(
							"select count(id) from  EDU_LMS_COURSE_PUBLISHER_MSTR where upper(name)=:name and upper(status)=:status and id !=:id")
							.setParameter("name", td.getName().toUpperCase())
							.setParameter("status", td.getStatus().toUpperCase())
							.setParameter("id", id).uniqueResult();
					
					if (id == 0) {
						td.setName(name);
						td.setCreated_by(username);
						td.setCreated_date(date);
						if (c == 0) {
							sessionHQL.save(td);
							sessionHQL.flush();
							sessionHQL.clear();
							ra.addAttribute("msg", "Data Saved Successfully.");
						} else {
							ra.addAttribute("msg", "Data already Exist.");
						}
					}

					tx.commit();

				} catch (RuntimeException e) {
					try {

						ra.addAttribute("msg", "roll back transaction");
					} catch (RuntimeException rbe) {
						ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe);
					}
					throw e;
				} finally {
					if (sessionHQL != null) {
						sessionHQL.close();
					}
				}

				return new ModelAndView("redirect:course_publisher_Act_InactUrl");
			}
			
			
			@PostMapping("/getFiltercourse_publi_Act_Inact_data")
			public @ResponseBody List<Map<String, Object>> getFiltercourse_publi_Act_Inact_data(int startPage, int pageLength,

					String Search, String orderColunm, String orderType, String name, String status) {

				return CP_actdao.DataTablePubli_Act_InactDataList(startPage, pageLength, Search, orderColunm, orderType,name,status);

			}
			

			@PostMapping("/getTotalcourse_publi_Act_Inact_dataCount")
			public @ResponseBody long getTotalcourse_publi_Act_Inact_dataCount(HttpSession sessionUserId, String Search, String name, String status) {
				return CP_actdao.DataTablePubli_Act_InactDataTotalCount(Search, name,status);
				
			}
			
			@RequestMapping(value = "/ActiveUserData", method = RequestMethod.POST)
			public ModelAndView ActiveUserData(@ModelAttribute("id5") int userid, BindingResult result, RedirectAttributes ra,
					HttpServletRequest request, ModelMap model, HttpSession session1) {
				List<String> liststr = new ArrayList<String>();

				Session session = this.sessionFactory.openSession();
				Transaction tx = session.beginTransaction();

				String username = session1.getAttribute("username").toString();
				try {
		  
					int app = session.createQuery(
							"update UserLogin set enabled = '1' where userId=:userId")
							.setParameter("userId", userid).executeUpdate();

					int app1 = session.createQuery(
							"update EDU_LMS_COURSE_PUBLISHER_MSTR set status ='1' where user_id=:user_id")
							.setParameter("user_id", userid).executeUpdate();
					tx.commit();
					session.close();
					if (app > 0) {
						liststr.add("User Active Successfully.");
					} else {
						liststr.add("User not Activated.");
					}
					ra.addAttribute("msg", liststr.get(0));
				} catch (Exception e) {
					e.printStackTrace();
					//liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
					ra.addAttribute("msg", liststr.get(0));
					
				}
				return new ModelAndView("redirect:course_publisher_Act_InactUrl");
			}
			
			
			@RequestMapping(value = "/InactiveUserData", method = RequestMethod.POST)
			public ModelAndView InactiveUserData(@ModelAttribute("id6") int userid, BindingResult result, RedirectAttributes ra,
					HttpServletRequest request, ModelMap model, HttpSession session1) {
            
				List<String> liststr = new ArrayList<String>();

				Session session = this.sessionFactory.openSession();
				Transaction tx = session.beginTransaction();

				String username = session1.getAttribute("username").toString();
				try {
		  
					int app = session.createQuery(
							"update UserLogin set enabled ='0' where userId=:userId")
							.setParameter("userId", userid).executeUpdate();

					
					tx.commit();
					session.close();
					if (app > 0) {
						liststr.add("User Inactive Successfully.");
					} else {
						liststr.add("User not Inactivated.");
					}
					ra.addAttribute("msg", liststr.get(0));
				} catch (Exception e) {
					e.printStackTrace();
					//liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
					ra.addAttribute("msg", liststr.get(0));
					
				}
				return new ModelAndView("redirect:course_publisher_Act_InactUrl");
			}
			
}
