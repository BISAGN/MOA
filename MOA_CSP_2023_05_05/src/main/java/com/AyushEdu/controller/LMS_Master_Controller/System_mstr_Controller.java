package com.AyushEdu.controller.LMS_Master_Controller;

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
import org.springframework.boot.system.JavaVersion;
import org.springframework.core.SpringVersion;
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
import com.AyushEdu.Core_Directory.Degree_Master_CD_DAO;
import com.AyushEdu.Core_Directory.System_Master_CD_DAO;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.SystemDAO;
import com.AyushEdu.validation.ValidationController;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYSTEM_MSTR;
import com.AyushEdu.Models.LMS_Master.TB_COUNTRY;

@Controller
@RequestMapping(value = {"admin", "/", "user"})
public class System_mstr_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;

	@Autowired
	private SessionFactory sessionFactory2;

	@Autowired
	System_Master_CD_DAO  SM_dirdao;
	
	@Autowired
	 ValidationController validation;

	@Autowired
	SystemDAO sdao;
	

	//==========================================OPEN PAGE SYSTEM========================================== 
	
	@RequestMapping(value = "/SystemUrl", method = RequestMethod.GET)
	public ModelAndView SystemUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		try {
			
		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("SystemUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		Mmap.put("msg", msg);
		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

	} catch (Exception e) {
		e.printStackTrace();
	}
		return new ModelAndView("System_Tiles");

	}
	
	
	//==========================================SAVE SYSTEM NAME========================================== 	

	
	@PostMapping(value = "/SystemAction")
	public ModelAndView SystemAction(@Validated @ModelAttribute("SystemCMD") EDU_LMS_SYSTEM_MSTR td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {

		if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/login");
		 }
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("SystemUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		String system_name = request.getParameter("system_name");
		String system_abbr = request.getParameter("system_abbr");
		String status = request.getParameter("status");

		if (system_name == null || system_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter System.");
			return new ModelAndView("redirect:SystemUrl");
		}
		
		if (validation.maxlengthcheck100(system_name) == false) {
			ra.addAttribute("msg","System "+ validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:SystemUrl");
		}
		if (validation.isOnlyAlphabetDASH(system_name) == false) {
			ra.addAttribute("msg","System "+ validation.isOnlyAlphabetMSGDASH);
			return new ModelAndView("redirect:SystemUrl");
		}
		if (system_abbr.equals("") || system_abbr.equals("null") || system_abbr.equals(null)) {
			ra.addAttribute("msg", "Please Enter Abbreviation.");
			return new ModelAndView("redirect:SystemUrl");
		}
		if (validation.maxlengthcheck50(system_abbr) == false) {
			ra.addAttribute("msg", "Abbreviation " + validation.MaxlengthcheckMSG50);
			return new ModelAndView("redirect:SystemUrl");
		}
		if (validation.isOnlyAlphabetDASH(system_abbr) == false) {
			ra.addAttribute("msg", "Abbrivation " + validation.isOnlyAlphabetMSGDASH);
			return new ModelAndView("redirect:SystemUrl");
		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:SystemUrl");
		}
		if (status == "2" || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:SystemUrl");
		}
		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();
//		String system_name = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_LMS_SYSTEM_MSTR where upper(system_name)=:system_name and upper(system_abbr)=:system_abbr and upper(status)=:status and id !=:id")
					.setParameter("system_name", td.getSystem_name().toUpperCase())
					.setParameter("system_abbr", td.getSystem_abbr().toUpperCase())
					.setParameter("status", td.getStatus().toUpperCase())
					.setParameter("id", id).uniqueResult();
			int idd =0;
			if (id == 0) {
				td.setSystem_name(system_name);
				td.setSystem_abbr(system_abbr);
				td.setCreated_by(username);
				td.setCreated_role(role);
				td.setCreated_dt(date);
//				td.setCreated_dt(created_dt);
				if (c == 0) {
					 idd = (int)sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");
				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}
			}

			tx.commit();
			//For Core Directory
			SM_dirdao.Insert_System_Mstr_Data(idd);

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

		return new ModelAndView("redirect:SystemUrl");
	}
	

	@PostMapping("/getFiltersystem_data")

	public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,

			String Search, String orderColunm, String orderType, String system_name,String system_abbr, String status) {

		return sdao.DataTablesystemDataList(startPage, pageLength, Search, orderColunm, orderType, system_name,system_abbr,status);

	}

	@PostMapping("/getTotalsystem_dataCount")
	public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search, String system_name,String system_abbr) {
		return sdao.DataTablesystemDataTotalCount(Search, system_name,system_abbr);
		
	}
	

		@RequestMapping(value = "/Edit_systemUrl", method = RequestMethod.POST)
		public ModelAndView Edit_systemUrl(@ModelAttribute("id2") String updateid, ModelMap Mmap, Principal principal,
				@RequestParam(value = "msg", required = false) String msg,
				HttpServletRequest request, HttpSession sessionEdit) {

			if(request.getHeader("Referer") == null ) { 
			//	sessionEdit.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/login");
			 }
			
			String roleid1 = sessionEdit.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("SystemUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String role = sessionEdit.getAttribute("role").toString();
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			EDU_LMS_SYSTEM_MSTR system_Details = sdao.getsystemByid(Integer.parseInt(updateid));
			Mmap.addAttribute("msg", msg);
			Mmap.put("system_name", common.getSystemList(sessionFactory,role));
			Mmap.put("system_Details", system_Details);
			Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
			Mmap.put("systeminfo", sdao.getsystemByid(Integer.parseInt(updateid)));
			
			tx.commit();
			sessionHQL.close();

			return new ModelAndView("editSystem_Tiles");
		}
		//edit action
		@RequestMapping(value = "/edit_system_Action", method = RequestMethod.POST)
		public ModelAndView edit_system_Action(@ModelAttribute("edit_systemCMD") EDU_LMS_SYSTEM_MSTR rs,
				HttpServletRequest request, ModelMap model, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {

			if(request.getHeader("Referer") == null ) { 
				// session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/login");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("SystemUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
			String username = session.getAttribute("username").toString();

			int id = Integer.parseInt(request.getParameter("id"));
			String system_name = request.getParameter("system_name").trim();
			String system_abbr = request.getParameter("system_abbr").trim();
			String status = request.getParameter("status");
			Session session1 = this.sessionFactory.openSession();
			Transaction tx = session1.beginTransaction();
			
			
			if (system_name == null || system_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter System.");
				return new ModelAndView("redirect:SystemUrl");
			}
			if (validation.maxlengthcheck100(system_name) == false) {
				ra.addAttribute("msg","System "+ validation.MaxlengthcheckMSG100);
				return new ModelAndView("redirect:SystemUrl");
			}
			if (validation.isOnlyAlphabetDASH(system_name) == false) {
				ra.addAttribute("msg","System "+ validation.isOnlyAlphabetMSGDASH);
				return new ModelAndView("redirect:SystemUrl");
			}
			if (system_abbr.equals("") || system_abbr.equals("null") || system_abbr.equals(null)) {
				ra.addAttribute("msg", "Please Enter Abbreviation.");
				return new ModelAndView("redirect:SystemUrl");
			}
			if (validation.maxlengthcheck50(system_abbr) == false) {
				ra.addAttribute("msg", "Abbreviation " + validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:SystemUrl");
			}
			if (validation.isOnlyAlphabetDASH(system_abbr) == false) {
				ra.addAttribute("msg", "Abbrivation " + validation.isOnlyAlphabetMSGDASH);
				return new ModelAndView("redirect:SystemUrl");
			}
			
			if (status == null || status.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Status.");
				return new ModelAndView("redirect:SystemUrl");
			}
			if (status == "2" || status.trim().equals("2")) {
				ra.addAttribute("msg", "Only Select Active Status.");
				return new ModelAndView("redirect:SystemUrl");
			}
			
			try {
				Query q0 = session1.createQuery(
						"select count(id) from EDU_LMS_SYSTEM_MSTR where system_name=:system_name and system_abbr=:system_abbr and status=:status and id !=:id");
				q0.setParameter("system_name", system_name);
				q0.setParameter("system_abbr", system_abbr);
				q0.setParameter("status", status);

				q0.setParameter("id", id);

				Long c = (Long) q0.uniqueResult();

				if (c == 0) {
					String hql = "update EDU_LMS_SYSTEM_MSTR set system_name=:system_name,system_abbr=:system_abbr,status=:status,modified_by=:modified_by,modified_dt=:modified_dt"
							+ " where id=:id";

					Query query = session1.createQuery(hql).setParameter("system_name", system_name).setParameter("system_abbr", system_abbr).setParameter("status", status)
							.setParameter("modified_by", username).setParameter("modified_dt", new Date())
							.setParameter("id", id);
					msg = query.executeUpdate() > 0 ? "1" : "0";
					tx.commit();
					
					SM_dirdao.Update_System_Mstr_Data(id, system_name, system_abbr, status, username, new Date());

					if (msg.equals("1")) {
						ra.addAttribute("msg", "Data Updated Successfully.");
					} else {
						ra.addAttribute("msg", "Data Not Updated.");
					}
				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}

			} catch (RuntimeException e) {
				try {
					tx.rollback();
					ra.addAttribute("msg", "roll back transaction");
				} catch (RuntimeException rbe) {
					ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
				}
				throw e;

			} finally {
				if (session1 != null) {
					session1.close();
				}
			}
			
			return new ModelAndView("redirect:SystemUrl");
		}
		
		// -------------------------SEARCH Battalion  -------------------------------------//
		 
		 @RequestMapping(value = "/admin/getSearch_System_Master", method = RequestMethod.POST)
			public ModelAndView getSearch_System_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
					@RequestParam(value = "msg", required = false) String msg,
					@RequestParam(value = "System_name1", required = false) String System_name1 ,String System_name,@ModelAttribute("status1") String status)  {
			
			 if(request.getHeader("Referer") == null ) { 
			//	 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/login");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("SystemUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}

					Mmap.put("System_name1", System_name1);
					Mmap.put("status1", status);
					Mmap.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
					

				   return new ModelAndView("System_Tiles","SystemCMD",new EDU_LMS_SYSTEM_MSTR());
				   
			}
			
		
		@RequestMapping(value = "/delete_Url2", method = RequestMethod.POST)
		public ModelAndView delete_Url2(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
				HttpServletRequest request, ModelMap model, HttpSession session1) {

			if(request.getHeader("Referer") == null ) { 
			//	session1.invalidate();
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/login");
			 }
			String roleid1 = session1.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("SystemUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
			List<String> liststr = new ArrayList<String>();

			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			String username = session1.getAttribute("username").toString();
			try {
	  
				int app = session.createQuery(
						"update EDU_LMS_SYSTEM_MSTR set modified_by=:modified_by,modified_dt=:modified_dt,status=:status where id=:id")
						.setParameter("id", id).setParameter("modified_by", username)
						.setParameter("modified_dt", new Date()).setParameter("status", "2").executeUpdate();

				
				tx.commit();
				SM_dirdao.Delete_System_Mstr_Data(id);
				System.out.println();
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
			return new ModelAndView("redirect:SystemUrl");
		}


	
}
