package com.AyushEdu.controller.Part_one.Masters;

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
import com.AyushEdu.Models.Part_One.Masters.CLG_REG_CLG_DEPT_AVAILABILITY_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Part_One.Masters.Clg_Reg_Clg_Dept_Availability_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = {"admin", "/", "user"})
public class Clg_Reg_Clg_Dept_Availability_Mstr_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;

	@Autowired
	private SessionFactory sessionFactory2;

	//@Autowired
	//Clg_Reg_Clg_Dept_Availability_CD_DAO SM_dirdao;
//	
	@Autowired
	 ValidationController validation;
//
	@Autowired
	Clg_Reg_Clg_Dept_Availability_DAO sdao;
	

	//==========================================OPEN PAGE SYSTEM========================================== 
	
	@RequestMapping(value = "/get_Reg_Clg_Dept_Availability_Url", method = RequestMethod.GET)
	public ModelAndView get_Reg_Clg_Dept_Availability_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
//		try {
//			
//		if(request.getHeader("Referer") == null ) { 
////			 session.invalidate();
//			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("SystemUrl", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
//			
//		Mmap.put("msg", msg);
//		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
//
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
		Mmap.put("msg", msg);
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		
		
		
		return new ModelAndView("Reg_Clg_Dept_Availability_Tiles");

	}

	//==========================================SAVE/view//Edit Professional========================================== 	

	
		@PostMapping(value = "/ClgDeptAvailabilityAction")
		public ModelAndView ClgDeptAvailabilityAction(@Validated @ModelAttribute("ClgDeptAvailabilityCMD") CLG_REG_CLG_DEPT_AVAILABILITY_MSTR td, BindingResult result,
				HttpServletRequest request, ModelMap model, HttpSession session, 
				RedirectAttributes ra) {
			
			
			if(request.getHeader("Referer") == null ) { 
				// session.invalidate();
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("University_type_Mstr_Url", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}

			String department = request.getParameter("department");
			String status = request.getParameter("status");

			if (department == null || department.equals("")) {
				ra.addAttribute("msg", "Please Enter Department Name.");
				return new ModelAndView("redirect:get_Reg_Clg_Dept_Availability_Url");
			}
			
			if (validation.maxlengthcheck100(department) == false) {
				ra.addAttribute("msg","Department "+ validation.MaxlengthcheckMSG100);
				return new ModelAndView("redirect:get_Reg_Clg_Dept_Availability_Url");
			}
			
			
			  if (validation.isOnlyAlphabetDASH(department) == false) {
			  ra.addAttribute("msg","Department "+ validation.isOnlyAlphabetMSGDASH);
			  
			  return new ModelAndView("redirect:get_Reg_Clg_Dept_Availability_Url"); }
			 
			if (status == null || status.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Status.");
				return new ModelAndView("redirect:get_Reg_Clg_Dept_Availability_Url");
			}

			
			Date date = new Date();
			String userid = session.getAttribute("userId_for_jnlp").toString();
			System.out.println(userid);			
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int id = td.getId() > 0 ? td.getId() : 0;
			try {
				Long c = (Long) sessionHQL.createQuery(
						"select count(id) from  CLG_REG_CLG_DEPT_AVAILABILITY_MSTR where upper(department)=:department and status=:status and id !=:id")
						.setParameter("department", td.getDepartment().toUpperCase())
						.setParameter("status", td.getStatus())
						.setParameter("id", id).uniqueResult();
				int idd =0;
				if (id == 0) {
					td.setDepartment(department);
					td.setCreated_by(userid);
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
					td.setDepartment(department);
					td.setModified_by(userid);
					td.setModified_date(date);
					if (c == 0) {
						td.setId(id);
						String msg = sdao.updateClgDept(td);
						//For Core Directory
						//DM_dirdao.Update_Uni_Typet_Mstr_Data( td.getId(), td.getUniversity_type(), td.getStatus(),  userid,  new Date());
//						if (msg == "Data Updated Successfully") {
//							model.put("msg", msg);
//							model.put("Nmsg", "0");
//						} else {
//							model.put("msg", msg);
//							model.put("Nmsg", "1");
//						}
						ra.addAttribute("msg",msg);
					} else {
//						model.put("msg", "Data already Exist");
//						model.put("Nmsg", "1");
						ra.addAttribute("msg", "Data already Exist.");
					}
				}

				tx.commit();
				//For Core Directory
				//SM_dirdao.Insert_Uni_Type__Mstr_Data(idd);
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
			
			return new ModelAndView("redirect:get_Reg_Clg_Dept_Availability_Url");
		}
	
	
	
	
	
	@PostMapping("/getFilterClgDeptAvailability_data")

	public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,

			String Search, String orderColunm, String orderType, String department, String status) {

		return sdao.DataTableClgDataList(startPage, pageLength, Search, orderColunm, orderType, department,status);

	}

	@PostMapping("/getTotalClgDeptAvailability_dataCount")
	public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search, String department) {
		return sdao.DataTableClgDataTotalCount(Search, department);
		
	}
	
	/*
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
		*/
	
	@RequestMapping(value = "/clg_dept_delete_Url", method = RequestMethod.POST)
	public ModelAndView clg_dept_delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

		if(request.getHeader("Referer") == null ) { 
		//	session1.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/login");
		 }
//		String roleid1 = session1.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("get_Reg_Clg_Dept_Availability_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
			
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
  
			int app = session.createQuery(
					"update CLG_REG_CLG_DEPT_AVAILABILITY_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("status", 2).executeUpdate();

			
			tx.commit();
			//SM_dirdao.Delete_System_Mstr_Data(id);
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
		return new ModelAndView("redirect:get_Reg_Clg_Dept_Availability_Url");
	}




	
}
