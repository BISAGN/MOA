package com.AyushEdu.controller.LMS_Master_Controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.AyushEdu.Models.Teacher_Master.TB_NCH_EMPLOYEMENT_TYPE_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Master.Employment_type_Mstr_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Employment_type_Mstr_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	ValidationController validation;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Autowired
	Employment_type_Mstr_DAO Edao;
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;

	@RequestMapping(value = "admin/EmploymentType_Url", method = RequestMethod.GET)
	public ModelAndView Employment_Mstr_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
			
		//SECURITY RAHUL
			
			if(request.getHeader("Referer") == null ) { 
				// session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("EmploymentType_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}

			Mmap.put("msg", msg);
			Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("Employment_type_Tiles");
	}

	// ==========================================SAVE/view//Edit
	// Professional==========================================

	@PostMapping(value = "/EmploymentAction")
	public ModelAndView EmploymentAction(@Validated @ModelAttribute("EmploymentCMD") TB_NCH_EMPLOYEMENT_TYPE_MSTR td,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session,
			RedirectAttributes ra) {
		
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
				
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("EmploymentType_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		String userId = session.getAttribute("userId").toString();
		System.err.println("UID--------------" + userId);
		String employment = request.getParameter("empl_type");
		String status = request.getParameter("status");

		if (employment == null || employment.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Employment Tpye.");
			return new ModelAndView("redirect:EmploymentType_Url");
		}

		if (validation.maxlengthcheck100(employment) == false) {
			ra.addAttribute("msg", "Employment " + validation.MaxlengthcheckMSG100);

			return new ModelAndView("redirect:EmploymentType_Url");

		}

		if (validation.isOnlyAlphabetDASH(employment) == false) {
			ra.addAttribute("msg", "Employment " + validation.isOnlyAlphabetMSGDASH);

			return new ModelAndView("redirect:EmploymentType_Url");
		}

		if (status == null || status.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Status.");
			return new ModelAndView("redirect:EmploymentType_Url");
		}

		int id = Integer.parseInt(request.getParameter("id"));
		Date date = new Date();
//			String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  TB_NCH_EMPLOYEMENT_TYPE_MSTR where upper(empl_type)=:empl_type and status=:status and id !=:id")
					.setParameter("empl_type", td.getEmpl_type().toUpperCase()).setParameter("status", td.getStatus())
					.setParameter("id", id).uniqueResult();

			if (id == 0) {

				td.setEmpl_type(employment);
				td.setCreated_by(userId);
//					td.setCreated_role(role);
				td.setCreated_date(date);
				if (c == 0) {
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");
				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}
			} else {
				td.setEmpl_type(employment);
				td.setModified_by(userId);
				td.setModified_date(date);
				if (c == 0) {
					td.setId(id);
					String msg = Edao.updateEmployment(td);
					if (msg == "Data Updated Successfully") {
						model.put("msg", msg);
						model.put("Nmsg", "0");
					} else {
						model.put("msg", msg);
						model.put("Nmsg", "1");
					}
					ra.addAttribute("msg", "Data Updated Successfully.");
				} else {
					model.put("msg", "Data already Exist");
					model.put("Nmsg", "1");
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

		return new ModelAndView("redirect:EmploymentType_Url");
	}

	@PostMapping("/getFilterEmployment_data")
	public @ResponseBody List getFilterRegistration_data(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String empl_type, String status) {
		System.err.println("IN ");
		return Edao.DataTableEmploymentDataList(startPage, pageLength, Search, orderColunm, orderType, empl_type,
				status);
	}

	@PostMapping("/getTotalEmployment_dataCount")
	public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search,
			String empl_type) {
		return Edao.DataTableEmploymentDataTotalCount(Search, empl_type);

	}

	

	@RequestMapping(value = "/Edit_EmploymentType_Url", method = RequestMethod.POST)
	public ModelAndView Edit_Employment_Mstr_Url(@ModelAttribute("id2") String updateid, ModelMap Mmap,
			Principal principal, @RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "empl_type", required = false) String employment,
			@RequestParam(value = "status", required = false) String status, HttpServletRequest request,
			HttpSession sessionEdit) {
		
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
				
		String roleid1 = sessionEdit.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("EmploymentType_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		TB_NCH_EMPLOYEMENT_TYPE_MSTR Employment_type_Details = Edao.getEmploymentByid(Integer.parseInt(updateid));
		Mmap.addAttribute("msg", msg);
//					Mmap.put("empl_type", empl_type);
		Mmap.put("status", status);
		Mmap.put("Employment_type_Details", Employment_type_Details);
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		Mmap.put("Employmentinfo", Edao.getEmploymentByid(Integer.parseInt(updateid)));

		tx.commit();
		sessionHQL.close();

		return new ModelAndView("Employment_type_Tiles");
	}

	@RequestMapping(value = "/Employment_Mstr_Delete_Url", method = RequestMethod.POST)
	public ModelAndView Employment_Mstr_Delete_Url(@ModelAttribute("id1") int id, BindingResult result,
			RedirectAttributes ra, HttpServletRequest request, ModelMap model, HttpSession session1) {

		List<String> liststr = new ArrayList<String>();
		
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
				
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("EmploymentType_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String userid = session1.getAttribute("userId").toString();
		String username = session1.getAttribute("username").toString();
		try {

			int app = session.createQuery(
					"update TB_NCH_EMPLOYEMENT_TYPE_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", userid)
					.setParameter("modified_date", new Date()).setParameter("status", 2).executeUpdate();

			tx.commit();
			session.close();
			if (app > 0) {
				System.err.println("check delete" + (app > 0));
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
		return new ModelAndView("redirect:EmploymentType_Url");
	}

}