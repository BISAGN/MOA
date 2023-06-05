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
import com.AyushEdu.Models.Part_One.Masters.CLG_REG_HOS_DEPT_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Part_One.Masters.Hospital_Dept_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Hos_Dept_Mstr_Controller {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	CommonController common;


	@Autowired
	ValidationController validation;

	@Autowired
	Hospital_Dept_DAO sdao;

	// ==========================================OPEN PAGE
	// SYSTEM==========================================

	@RequestMapping(value = "/Hospital_Department_Url", method = RequestMethod.GET)
	public ModelAndView Hospital_Department_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Mmap.put("msg", msg);
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		return new ModelAndView("Hospital_Department_Tiles");

	}

	@PostMapping(value = "/Hospital_DepartmentAction")
	public ModelAndView Hospital_DepartmentAction(@Validated @ModelAttribute("Hospital_DepartmentCMD") CLG_REG_HOS_DEPT_MSTR td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, 
			RedirectAttributes ra) {
		
		
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
				
		String roleid1 = session.getAttribute("roleid").toString();
	
		String department_name = request.getParameter("department_name");
		String status = request.getParameter("status");

		if (department_name  == null || department_name.equals("")) {
			ra.addAttribute("msg", "Please Enter Department Name.");
			return new ModelAndView("redirect:Hospital_Department_Url");
		}
		
		if (validation.maxlengthcheck100(department_name) == false) {
			ra.addAttribute("msg","Department name "+ validation.MaxlengthcheckMSG100);
			
			return new ModelAndView("redirect:Hospital_Department_Url");
			
		}
		
		  if (validation.isOnlyAlphabetDASH(department_name ) == false) {
		  ra.addAttribute("msg","Department "+ validation.isOnlyAlphabetMSGDASH);
		  
		  return new ModelAndView("redirect:Hospital_Department_Url"); }
		 
		if (status == null || status.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Department.");
			return new ModelAndView("redirect:Hospital_Department_Url");
		}
		String username = session.getAttribute("username").toString();
		
		Date date = new Date();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		System.out.println(userid);			
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int id = td.getId() > 0 ? td.getId() : 0;
		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  CLG_REG_HOS_DEPT_MSTR where upper(department_name)=:department_name and status=:status and id !=:id")
					.setParameter("department_name", td.getDepartment_name().toUpperCase())
					.setParameter("status", td.getStatus())
					.setParameter("id", id).uniqueResult();
			int idd =0;
			if (id == 0) {
				td.setDepartment_name(department_name);
				td.setCreated_by(username);
				td.setModified_by(username);
//				td.setCreated_role(role);
				td.setCreated_date(date);
				td.setModified_date(date);
				if (c == 0) {
					idd = (int)sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");
				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}
			} else {
				td.setDepartment_name(department_name);
				td.setModified_by(username);
				td.setModified_date(date);
				if (c == 0) {
					td.setId(id);
					String msg = sdao.updateHospitalDepartment(td);
					//For Core Directory
					//DM_dirdao.Update_Uni_Typet_Mstr_Data( td.getId(), td.getUniversity_type(), td.getStatus(),  userid,  new Date());
//					if (msg == "Data Updated Successfully") {
//						model.put("msg", msg);
//						model.put("Nmsg", "0");
//					} else {
//						model.put("msg", msg);
//						model.put("Nmsg", "1");
//					}
					ra.addAttribute("msg", "Data Updated Successfully.");
				} else {
//					model.put("msg", "Data already Exist");
//					model.put("Nmsg", "1");
					ra.addAttribute("msg", "Data already Exist.");
				}
			}

			tx.commit();
			//For Core Directory
			//DM_dirdao.Insert_Uni_Type__Mstr_Data(idd);
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
		
		return new ModelAndView("redirect:Hospital_Department_Url");
	}
	
	
	@RequestMapping(value = "/Hospital_Department_DeleteUrl", method = RequestMethod.POST)
	public ModelAndView Hospital_Department_DeleteUrl(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
  
			int app = session.createQuery(
					"update CLG_REG_HOS_DEPT_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_date", new Date())
					.setParameter("status", 2).executeUpdate();

			
			tx.commit();
			//For Core Directory
			/* DM_dirdao.Delete_Uni_Type_Mstr_Data(id); */
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
		return new ModelAndView("redirect:Hospital_Department_Url");
	}

	@PostMapping("/getDepartmentdata")

	public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String department_name, String status) {
		return sdao.DataTableHospitalDepartmentDataList(startPage, pageLength, Search, orderColunm, orderType, department_name, status);

	}

	@PostMapping("/getTotalDepartment_Name_dataCount")
	public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search,
			String department_name) {
		return sdao.DataTableHospitalDepartmentDataTotalCount(Search, department_name);

	}
}
	  
