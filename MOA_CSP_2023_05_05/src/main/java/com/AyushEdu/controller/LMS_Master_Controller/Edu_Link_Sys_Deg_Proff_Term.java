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
import com.AyushEdu.Models.LMS_Master.EDU_LINK_SYS_DEG_PROF_TERM;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.Link_Sys_Deg_Prof_Term_DAO;
@Controller
@RequestMapping(value = {"admin", "/", "user"})
public class Edu_Link_Sys_Deg_Proff_Term {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;
	@Autowired
	Link_Sys_Deg_Prof_Term_DAO sdao;
	//==========================================OPEN PAGE SYSTEM========================================== 
	
		@RequestMapping(value = "/link_sys_deg_prof_term_Url", method = RequestMethod.GET)
		public ModelAndView link_sys_deg_prof_term_Url(ModelMap Mmap, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
			
			try {
				
//			if(request.getHeader("Referer") == null ) { 
////				 session.invalidate();
//				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				 return new ModelAndView("redirect:/landingpage");
//			 }
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("link_sys_deg_prof_term_Url", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
			String role = session.getAttribute("role").toString();	
			Mmap.put("msg", msg);
			Mmap.put("getSystemList", common.getSystemList(sessionFactory,role));
			Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
			Mmap.put("geti3_termList", common.geti3_termList(sessionFactory));
			Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			return new ModelAndView("link_sys_deg_prof_term_mstr_Tiles");

		}
		
		@PostMapping(value = "/Link_Sys_Deg_prod_termAction")
		public ModelAndView Link_Sys_Deg_prod_termAction(@Validated @ModelAttribute("Link_Sys_Deg_prod_termCMD") EDU_LINK_SYS_DEG_PROF_TERM td,
				BindingResult result,HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
				RedirectAttributes ra) {

//			if(request.getHeader("Referer") == null ) { 
//			//	 session.invalidate();
//				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				 return new ModelAndView("redirect:/login");
//			 }
			String role = session.getAttribute("role").toString();
			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("link_sys_deg_prof_term_Url", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
			String system = request.getParameter("system_id");
			String degree = request.getParameter("degree_id");
			String prof = request.getParameter("prof");
			String term = request.getParameter("term");
			String status = request.getParameter("status");

			if (system == null || system.equals("0")) {
				ra.addAttribute("msg", "Please Select System.");
				return new ModelAndView("redirect:link_sys_deg_prof_term_Url");
			}
			if (degree == null || degree.equals("0")) {
				ra.addAttribute("msg", "Please Select Degree.");
				return new ModelAndView("redirect:link_sys_deg_prof_term_Url");
			}
			if (prof == null || prof.equals("0")) {
				ra.addAttribute("msg", "Please Select Profession.");
				return new ModelAndView("redirect:link_sys_deg_prof_term_Url");
			}
			if (term == null || term.equals("0")) {
				ra.addAttribute("msg", "Please Select Term.");
				return new ModelAndView("redirect:link_sys_deg_prof_term_Url");
			}
			if (status == null || status.equals("0")) {
				ra.addAttribute("msg", "Please Select Status.");
				return new ModelAndView("redirect:link_sys_deg_prof_term_Url");
			}
			if (status == "2" || status.equals("2")) {
				ra.addAttribute("msg", "Only Select Active Status.");
				return new ModelAndView("redirect:link_sys_deg_prof_term_Url");
			}
			int id = td.getId() > 0 ? td.getId() : 0;
			Date date = new Date();
			String username = principal.getName();

			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();

			try {
				Long c = (Long) sessionHQL.createQuery(
						"select count(id) from  EDU_LINK_SYS_DEG_PROF_TERM where system=:system and degree=:degree and prof=:prof and term=:term and status=:status and id !=:id")
						.setParameter("system", Integer.parseInt(system))
						.setParameter("degree", Integer.parseInt(degree))
						.setParameter("prof", Integer.parseInt(prof))
						.setParameter("term", Integer.parseInt(term))
						.setParameter("status", Integer.parseInt(status))
						.setParameter("id", id).uniqueResult();
				
				if (id == 0) {
					td.setSystem(Integer.parseInt(system));
					td.setDegree(Integer.parseInt(degree));
					td.setProf(Integer.parseInt(prof));
					td.setTerm(Integer.parseInt(term));
					td.setStatus(Integer.parseInt(status));
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
				}else {
					if (c == 0) {
						
						td.setSystem(Integer.parseInt(system));
						td.setDegree(Integer.parseInt(degree));
						td.setProf(Integer.parseInt(prof));
						td.setTerm(Integer.parseInt(term));
						td.setStatus(Integer.parseInt(status));
						td.setModified_by(username);
						td.setModified_date(date);
						
						String msg = sdao.updateSysDegProfTerm(td);
						
						ra.addAttribute("msg", msg);
					}else {
						ra.addAttribute("msg", "Data already Exist.");
					}
				}

				tx.commit();

			} catch (RuntimeException e) {
				try {

					ra.addAttribute("msg", "roll back transaction");
				} catch (RuntimeException rbe) {
					ra.addAttribute("msg", "Could not roll back transaction " + rbe);
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}

			return new ModelAndView("redirect:link_sys_deg_prof_term_Url");
		}
		
		@PostMapping("/getFilterlink_sys_deg_prof_termdata")

		public @ResponseBody List<Map<String, Object>> getFilterlink_sys_deg_prof_termdata(int startPage, int pageLength,
				String Search, String orderColunm, String orderType, String system_id,String degree_id,String prof,String term, String status) {
			return sdao.DataTablelink_sys_deg_prof_termDataList(startPage, pageLength, Search, orderColunm, orderType, system_id,degree_id,prof,term,status);
		}

		@PostMapping("/getTotallink_sys_deg_prof_termdataCount")
		public @ResponseBody long getTotallink_sys_deg_prof_termdataCount(HttpSession sessionUserId, String Search, String system_id,String degree_id,String prof,String term,String status) {
			return sdao.DataTablelink_sys_deg_prof_termDataTotalCount(Search, system_id,degree_id,prof,term,status);
		}
		
		
		@RequestMapping(value = "/delete_link_sys_deg_prof_term_Url", method = RequestMethod.POST)
		public ModelAndView delete_link_sys_deg_prof_term_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
				HttpServletRequest request, ModelMap model, HttpSession session1) {

//			if(request.getHeader("Referer") == null ) { 
//			//	session1.invalidate();
//				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				 return new ModelAndView("redirect:/login");
//			 }
//			String roleid1 = session1.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("link_sys_deg_prof_term_Url", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
				
			List<String> liststr = new ArrayList<String>();

			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			String username = session1.getAttribute("username").toString();
			try {
	  
				int app = session.createQuery(
						"update EDU_LINK_SYS_DEG_PROF_TERM set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
						.setParameter("id", id).setParameter("modified_by", username)
						.setParameter("modified_date", new Date()).setParameter("status", 2).executeUpdate();
				
				tx.commit();
				session.close();
				if (app > 0) {
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
			return new ModelAndView("redirect:link_sys_deg_prof_term_Url");
		}
		
}
