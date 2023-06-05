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
import com.AyushEdu.Core_Directory.Department_Mstr_CD__DAO;
import com.AyushEdu.Core_Directory.Type_of_Degree_Mstr_CD_Dao;
import com.AyushEdu.Models.Teacher_Master.TB_NCH_DEPARTMENT_MSTR;
//import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PROFESSIONAL_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.Department_Mstr_DAO;
//import com.AyushEdu.dao.Practice.Department_Mstr_DAO;
//import com.AyushEdu.dao.Curriculum_Mstr.CC_Professional_Mstr_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Department_Mstr_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	
	@Autowired
	Department_Mstr_DAO Ddao;

	@Autowired
	ValidationController validation;
	
	@Autowired
	Department_Mstr_CD__DAO  DM_dirdao;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	 
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
	@RequestMapping(value = "admin/Department_Url", method = RequestMethod.GET)
	public ModelAndView Department_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
			
			
			//SECURITY RAHUL
			
			if(request.getHeader("Referer") == null ) { 
				// session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Department_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
		Mmap.put("msg", msg);
		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

	} catch (Exception e) {
		e.printStackTrace();
	}

		return new ModelAndView("Department_Tiles");
	}
	
	//==========================================SAVE/view//Edit Professional========================================== 	


	@PostMapping(value = "/DepartmentAction")
	public ModelAndView DepartmentAction(@Validated @ModelAttribute("DepartmentCMD") TB_NCH_DEPARTMENT_MSTR td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {

		String department = request.getParameter("department");
		String status = request.getParameter("status");
		
		
		if(request.getHeader("Referer") == null ) { 
		//	 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
				
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Department_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		
		

		if (department == null || department.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter department");
			return new ModelAndView("redirect:Department_Url");
		}
		
		if (validation.maxlengthcheck100(department) == false) {
			ra.addAttribute("msg","Department "+ validation.MaxlengthcheckMSG100);
			
			return new ModelAndView("redirect:Department_Url");
			
		}
		
		if (validation.isOnlyAlphabetDASH(department) == false) {
			ra.addAttribute("msg","Department "+ validation.isOnlyAlphabetMSGDASH);
			
			return new ModelAndView("redirect:Department_Url");
		}
		
		if (status == null || status.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Status.");
			return new ModelAndView("redirect:Department_Url");
		}

		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		//String username = principal.getName();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  TB_NCH_DEPARTMENT_MSTR where upper(department)=:department and status=:status and id !=:id")
					.setParameter("department", td.getDepartment().toUpperCase())
					.setParameter("status", td.getStatus())
					.setParameter("id", id).uniqueResult();
			int idd =0;
			if (id == 0) {
				td.setDepartment(department);
				td.setCreated_by(userid);
//				td.setCreated_role(role);
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
					String msg = Ddao.updateDepartment(td);
					
					DM_dirdao.Update_Department_Mstr_Data( td.getId(),td.getDepartment(),td.getStatus(),userid,new Date());
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
			DM_dirdao.Insert_Department_Mstr_Data(idd);
			
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
		
		return new ModelAndView("redirect:Department_Url");
	}
	
	
	@PostMapping("/getFilterDepartment_data")

	public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,

			String Search, String orderColunm, String orderType, String department, String status) {

		return Ddao.DataTableDepartmentDataList(startPage, pageLength, Search, orderColunm, orderType, department,status);

	}

	@PostMapping("/getTotalDepartment_dataCount")
	public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search, String department) {
		return Ddao.DataTableDepartmentDataTotalCount(Search, department);
		
	}
	
	

	@RequestMapping(value = "/Edit_Department_Mstr_Url", method = RequestMethod.POST)
	public ModelAndView Edit_Department_Mstr_Url(@ModelAttribute("id2") String updateid, HttpSession session, ModelMap Mmap, Principal principal,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "department", required = false) String department,
			@RequestParam(value = "status", required = false) String status,
			HttpServletRequest request, HttpSession sessionEdit) {
		String id = request.getParameter("id");
		String userid = session.getAttribute("userId_for_jnlp").toString();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		TB_NCH_DEPARTMENT_MSTR Department_Details = Ddao.getDepartmentByid(Integer.parseInt(updateid));
		Mmap.addAttribute("msg", msg);
		Mmap.put("department", department);
		Mmap.put("status", status);
		Mmap.put("Department_Details", Department_Details);
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
		Mmap.put("Departmentinfo", Ddao.getDepartmentByid(Integer.parseInt(updateid)));
		
		tx.commit();
		//For Core Directory
		DM_dirdao.Update_Department_Mstr_Data( Integer.parseInt(id),  department,  Integer.parseInt(status),  userid,  new Date());
		sessionHQL.close();

		return new ModelAndView("Department_Tiles");
	}
	
	@RequestMapping(value = "/Department_Mstr_Delete_Url", method = RequestMethod.POST)
	public ModelAndView Department_Mstr_Delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
  
			int app = session.createQuery(
					"update TB_NCH_DEPARTMENT_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_date", new Date())
					.setParameter("status", 2).executeUpdate();

			
			tx.commit();
			//For Core Directory
			DM_dirdao.Delete_Department_Mstr_Data(id); 
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
		return new ModelAndView("redirect:Department_Url");
	}
	
}