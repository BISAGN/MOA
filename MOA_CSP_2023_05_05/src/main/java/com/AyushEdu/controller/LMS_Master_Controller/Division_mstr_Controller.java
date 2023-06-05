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
import com.AyushEdu.Models.LMS_Master.EDU_DIVISION_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.Division_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = {"admin", "/", "user"})
public class Division_mstr_Controller {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;

	@Autowired
	private SessionFactory sessionFactory2;
	
	@Autowired
	 ValidationController validation;

	@Autowired
	Division_DAO ddao;
	
	

	//==========================================OPEN PAGE DIVISION========================================== 
	
	@RequestMapping(value = "/DivisionUrl", method = RequestMethod.GET)
	public ModelAndView DivisionUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		try {
			
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("DivisionUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		Mmap.put("msg", msg);
		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

	} catch (Exception e) {
		e.printStackTrace();
	}
		return new ModelAndView("Division_Tiles");

	}
	
	
	//==========================================SAVE DIVISION NAME========================================== 	

	
	@PostMapping(value = "/DivisionAction")
	public ModelAndView DivisionAction(@Validated @ModelAttribute("DivisionCMD") EDU_DIVISION_MSTR td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {

		if(request.getHeader("Referer") == null ) { 
			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/login");
		 }
		String role = session.getAttribute("role").toString();
//		System.out.println("Dr=======================>>>"+role);
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("DivisionUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		String division_name = request.getParameter("division_name");
		String status = request.getParameter("status");

		if (division_name == null || division_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Division Name.");
			return new ModelAndView("redirect:DivisionUrl");
		}
		
		if (validation.maxlengthcheck100(division_name) == false) {
			ra.addAttribute("msg","Division Name "+ validation.MaxlengthcheckMSG100);
			return new ModelAndView("redirect:DivisionUrl");
		}
		if (validation.isOnlyAlphabetDASH(division_name) == false) {
			ra.addAttribute("msg","Division Name "+ validation.isOnlyAlphabetMSGDASH);
			return new ModelAndView("redirect:DivisionUrl");
		}
		if (status == null || status.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Status.");
			return new ModelAndView("redirect:DivisionUrl");
		}

		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();
//		String system_name = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_DIVISION_MSTR where upper(division_name)=:division_name and upper(status)=:status and id !=:id")
					.setParameter("division_name", td.getDivision_name().toUpperCase())
					.setParameter("status", td.getStatus().toUpperCase())
					.setParameter("id", id).uniqueResult();
			
			if (id == 0) {
				td.setDivision_name(division_name);
				td.setCreated_by(username);
				td.setCreated_role(role);
				td.setCreated_dt(date);
//				td.setCreated_dt(created_dt);
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

		return new ModelAndView("redirect:DivisionUrl");
	}
	

	@PostMapping("/getFilter_Division_data")

	public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,

			String Search, String orderColunm, String orderType, String division_name, String status) {

		return ddao.DataTableDivisionDataList(startPage, pageLength, Search, orderColunm, orderType, division_name,status);

	}

	@PostMapping("/getTotal_Division_dataCount")
	public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search, String division_name) {
		return ddao.DataTableDivisionDataTotalCount(Search, division_name);
		
	}
	
	//-----edit

		@RequestMapping(value = "/Edit_divisionUrl", method = RequestMethod.POST)
		public ModelAndView Edit_divisionUrl(@ModelAttribute("id25") String updateid, ModelMap Mmap, Principal principal,
				@RequestParam(value = "msg", required = false) String msg,
				HttpServletRequest request, HttpSession sessionEdit) {

			if(request.getHeader("Referer") == null ) { 
		//		sessionEdit.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/login");
			 }
			
			String roleid1 = sessionEdit.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("DivisionUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			EDU_DIVISION_MSTR division_Details = ddao.getdivisionByid(Integer.parseInt(updateid));
			Mmap.addAttribute("msg", msg);
			Mmap.put("division_name", common.getDivisionList(sessionFactory, roleid1));
			Mmap.put("division_Details", division_Details);
			Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
			Mmap.put("divisioninfo", ddao.getdivisionByid(Integer.parseInt(updateid)));
			
			tx.commit();
			sessionHQL.close();

			return new ModelAndView("edit_Division_Tiles");
		}
		//edit action
		@RequestMapping(value = "/edit_division_Action", method = RequestMethod.POST)
		public ModelAndView edit_division_Action(@ModelAttribute("edit_divisionCMD") EDU_DIVISION_MSTR rs,
				HttpServletRequest request, ModelMap model, HttpSession session,
				@RequestParam(value = "msg", required = false) String msg, RedirectAttributes ra) throws ParseException {

			if(request.getHeader("Referer") == null ) { 
			//	 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/login");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("DivisionUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
			String username = session.getAttribute("username").toString();

			int id = Integer.parseInt(request.getParameter("id"));
			String division_name = request.getParameter("division_name").trim();
			String status = request.getParameter("status");
			Session session1 = this.sessionFactory.openSession();
			Transaction tx = session1.beginTransaction();
			
			if (division_name == null || division_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Division Name.");
				return new ModelAndView("redirect:DivisionUrl");
			}
			
			if (validation.maxlengthcheck100(division_name) == false) {
				ra.addAttribute("msg","Division Name "+ validation.MaxlengthcheckMSG100);
				return new ModelAndView("redirect:DivisionUrl");
			}
			
			if (validation.isOnlyAlphabetDASH(division_name) == false) {
				ra.addAttribute("msg","Division Name "+ validation.isOnlyAlphabetMSGDASH);
				return new ModelAndView("redirect:DivisionUrl");
			}
			
			
			if (status == null || status.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Status.");
				return new ModelAndView("redirect:DivisionUrl");
			}
			
			try {
				Query q0 = session1.createQuery(
						"select count(id) from EDU_DIVISION_MSTR where division_name=:division_name and status=:status and id !=:id");
				q0.setParameter("division_name", division_name);

				q0.setParameter("status", status);

				q0.setParameter("id", id);

				Long c = (Long) q0.uniqueResult();

				if (c == 0) {
					String hql = "update EDU_DIVISION_MSTR set division_name=:division_name,status=:status,modified_by=:modified_by,modified_dt=:modified_dt"
							+ " where id=:id";

					Query query = session1.createQuery(hql).setParameter("division_name", division_name).setParameter("status", status)
							.setParameter("modified_by", username).setParameter("modified_dt", new Date())
							.setParameter("id", id);
					msg = query.executeUpdate() > 0 ? "1" : "0";
					tx.commit();

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
			
			return new ModelAndView("redirect:DivisionUrl");
		}
		
		// -------------------------SEARCH Battalion  -------------------------------------//
		 
		 @RequestMapping(value = "/admin/getSearch_Division_Master", method = RequestMethod.POST)
			public ModelAndView getSearch_System_Master(ModelMap Mmap,HttpSession session,HttpServletRequest request,
					@RequestParam(value = "msg", required = false) String msg,
					@RequestParam(value = "Division_name1", required = false) String Division_name1 ,String Division_name,@ModelAttribute("status1") String status)  {
			
			 if(request.getHeader("Referer") == null ) { 
				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/login");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("DivisionUrl", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
//			 String  roleid = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("SystemUrl", roleid);	

					Mmap.put("Division_name1", Division_name1);
					Mmap.put("status1", status);
					Mmap.put("getStatusMasterList", common.getActiveInActiveList(sessionFactory));
					

				   return new ModelAndView("Division_Tiles","DivisionCMD",new EDU_DIVISION_MSTR());
				   
			}
			
		
		@RequestMapping(value = "/delete_Url13", method = RequestMethod.POST)
		public ModelAndView delete_Url13(@ModelAttribute("id13") int id, BindingResult result, RedirectAttributes ra,
				HttpServletRequest request, ModelMap model, HttpSession session1) {

//			if(request.getHeader("Referer") == null ) { 
//				session1.invalidate();
//				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				 return new ModelAndView("redirect:/login");
//			 }
			String roleid1 = session1.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("SystemUrl", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
				
			List<String> liststr = new ArrayList<String>();

			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			String username = session1.getAttribute("username").toString();
			try {
	  
				int app = session.createQuery(
						"update EDU_DIVISION_MSTR set modified_by=:modified_by,modified_dt=:modified_dt,status=:status where id=:id")
						.setParameter("id", id).setParameter("modified_by", username)
						.setParameter("modified_dt", new Date()).setParameter("status", "2").executeUpdate();

				
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
			return new ModelAndView("redirect:DivisionUrl");
		}


	
}
