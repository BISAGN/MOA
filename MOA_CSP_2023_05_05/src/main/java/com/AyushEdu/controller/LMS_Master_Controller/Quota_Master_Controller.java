package com.AyushEdu.controller.LMS_Master_Controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
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
import com.AyushEdu.Models.Masters.EDU_LMS_QUOTA_MSTR;
import com.AyushEdu.Models.Teacher_Master.TB_NCH_DESIGNATION_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.Quota_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Quota_Master_Controller {

	@Autowired
	private SessionFactory sessionFactory;


	@Autowired
	ValidationController validation;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Autowired
	Quota_Dao Qdao;
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
	//---------------------------------Quota Master Page OPEN----------------------------------------------//	
	
	@RequestMapping(value = "admin/Quota_Mstr_Url", method = RequestMethod.GET)
	public ModelAndView Quota_Mstr_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
			if(request.getHeader("Referer") == null ) { 
				// session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Quota_Mstr_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
		Mmap.put("msg", msg);
		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

	} catch (Exception e) {
		e.printStackTrace();
	}

		return new ModelAndView("Quota_Mstr_Tiles");
	}
	
	
	
	
	//---------------------------------Quota Master Page SAVE----------------------------------------------// 	

	
		@PostMapping(value = "/QuotaAction")
		public ModelAndView QuotaAction(@Validated @ModelAttribute("QuotaCMD") EDU_LMS_QUOTA_MSTR td, BindingResult result,
				HttpServletRequest request, ModelMap model, HttpSession session, 
				RedirectAttributes ra) {

			String quota = request.getParameter("quota");
			String status = request.getParameter("status");
			
			if(request.getHeader("Referer") == null ) { 
				// session.invalidate();
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Quota_Mstr_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				

			if (quota == null || quota.equals("")) {
				ra.addAttribute("msg", "Please Enter Quota.");
				return new ModelAndView("redirect:Quota_Mstr_Url");
			}
			
			if (validation.maxlengthcheck100(quota) == false) {
				ra.addAttribute("msg","quota "+ validation.MaxlengthcheckMSG100);
				
				return new ModelAndView("redirect:Quota_Mstr_Url");
				
			}
			
			if (validation.isOnlyAlphabetDASH(quota) == false) {
				ra.addAttribute("msg","quota "+ validation.isOnlyAlphabetMSGDASH);
				
				return new ModelAndView("redirect:Quota_Mstr_Url");
			}
			
			if (status == null || status.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Status.");
				return new ModelAndView("redirect:Quota_Mstr_Url");
			}

			int id =Integer.parseInt(request.getParameter("id"));
			Date date = new Date();
			String userid = session.getAttribute("userId_for_jnlp").toString();
			System.out.println(userid);			
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();


			try {
				Long c = (Long) sessionHQL.createQuery(
						"select count(id) from EDU_LMS_QUOTA_MSTR where quota=:quota and cast(status as integer)=:status and id !=:id")
						.setParameter("quota", td.getQuota())
						.setParameter("status", td.getStatus())
						.setParameter("id", id).uniqueResult();
				int idd =0;
				if (id == 0) {
					td.setQuota(quota);
					td.setCreated_by(userid);
//					td.setCreated_role(role);
					td.setCreated_date(date);
					if (c == 0) {
						idd = (int)sessionHQL.save(td);
						sessionHQL.flush();
						sessionHQL.clear();
						ra.addAttribute("msg", "Data Saved Successfully.");
					} else {
						ra.addAttribute("msg", "Data already Exist.");
					}
				} else {
					td.setQuota(quota);
					td.setModified_by(userid);
					td.setModified_date(date);
					if (c == 0) {
						td.setId(id);
						String msg = Qdao.updateQuota(td);
//						if (msg == "Data Updated Successfully") {
//							model.put("msg", msg);
//							model.put("Nmsg", "0");
//						} else {
//							model.put("msg", msg);
//							model.put("Nmsg", "1");
//						}
						ra.addAttribute("msg", "Data Updated Successfully.");
					} else {
//						model.put("msg", "Data already Exist");
//						model.put("Nmsg", "1");
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
			
			return new ModelAndView("redirect:Quota_Mstr_Url");
		}
		
		
		
		@PostMapping("/getFilterQuota_data")

		public @ResponseBody List<Map<String, Object>> getFilterQuota_data(int startPage, int pageLength,

				String Search, String orderColunm, String orderType, String quota, String status) {

			return Qdao.DataTableQuotaDataList(startPage, pageLength, Search, orderColunm, orderType, quota,status);

		}

		@PostMapping("/getTotalQuota_dataCount")
		public @ResponseBody long getTotalQuota_dataCount(HttpSession sessionUserId, String Search, String quota) {
			return Qdao.DataTableQuotaDataTotalCount(Search, quota);
			
		}
		
		
		//-----edit

		@RequestMapping(value = "/Edit_Quota_Mstr_Url", method = RequestMethod.POST)
		public ModelAndView Edit_Quota_Mstr_Url(@ModelAttribute("id2") String updateid, ModelMap Mmap, Principal principal,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam(value = "quota", required = false) String quota,
				@RequestParam(value = "status", required = false) String status,
				HttpServletRequest request, HttpSession sessionEdit) {
			
			
			
			if(request.getHeader("Referer") == null ) { 
				// session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
			String roleid1 = sessionEdit.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Quota_Mstr_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			EDU_LMS_QUOTA_MSTR Quota_Details = Qdao.getQuotaByid(Integer.parseInt(updateid));
			Mmap.addAttribute("msg", msg);
			Mmap.put("quota", quota);
			Mmap.put("status", status);
			Mmap.put("Quota_Details", Quota_Details);
			Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
			Mmap.put("Designationinfo", Qdao.getQuotaByid(Integer.parseInt(updateid)));
			
			tx.commit();
			sessionHQL.close();

			return new ModelAndView("Quota_Tiles");
		}
		
		
		
		@RequestMapping(value = "/Quota_Mstr_Delete_Url", method = RequestMethod.POST)
		public ModelAndView Quota_Mstr_Delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
				HttpServletRequest request, ModelMap model, HttpSession session1) {

			List<String> liststr = new ArrayList<String>();
			
			if(request.getHeader("Referer") == null ) { 
				// session.invalidate();
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
			String roleid1 = session1.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Quota_Mstr_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}

			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			String username = session1.getAttribute("username").toString();
			try {
	  
				int app = session.createQuery(
						"update EDU_LMS_QUOTA_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
						.setParameter("id", id).setParameter("modified_by", username)
						.setParameter("modified_date", new Date())
						.setParameter("status", 2).executeUpdate();

				
				tx.commit();
				session.close();
				if (app > 0) {
					System.err.println("check delete"+(app > 0));
					liststr.add("Data Deleted Successfully.");
				} else {
					liststr.add("Data not Deleted.");
				}
				ra.addAttribute("msg", liststr.get(0));
			} catch (Exception e) {
				e.printStackTrace();
				liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
				ra.addAttribute("msg", liststr.get(0));
				
			}
			return new ModelAndView("redirect:Quota_Mstr_Url");
		}
}