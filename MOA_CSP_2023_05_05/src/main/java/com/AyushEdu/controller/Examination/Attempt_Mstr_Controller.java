package com.AyushEdu.controller.Examination;
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
import com.AyushEdu.Core_Directory.Attempt_Mstr_CD_DAO;
import com.AyushEdu.Core_Directory.Religion_Master_CD_DAO;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PAPER_MASTER;
import com.AyushEdu.Models.Examination.CC_TB_ATTEMPT_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Examination.Attempt_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Attempt_Mstr_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;


	@Autowired
	ValidationController validation;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Autowired
	Attempt_Dao Adao;
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
	@Autowired
	Attempt_Mstr_CD_DAO  Attmp_dirdao;
	
	@RequestMapping(value = "admin/Attempt_Mstr_Url", method = RequestMethod.GET)
	public ModelAndView Attempt_Mstr_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
			if(request.getHeader("Referer") == null ) { 
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Attempt_Mstr_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
		 Mmap.put("msg", msg);
		 Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

	} catch (Exception e) {
		e.printStackTrace();
	}

		return new ModelAndView("Attempt_Mstr_Tiles");
	}

	//==========================================SAVE/view//Edit========================================== 	


	@PostMapping(value = "/AttemptAction")
	public ModelAndView AttemptAction(@Validated @ModelAttribute("AttemptCMD") CC_TB_ATTEMPT_MSTR td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {

		if(request.getHeader("Referer") == null ) { 
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Attempt_Mstr_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		String attempt = request.getParameter("attempt");
		String status = request.getParameter("status");

		if (attempt == null || attempt.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Attempt.");
			return new ModelAndView("redirect:Attempt_Mstr_Url");
		}
		
		if (validation.maxlengthcheckP(attempt) == false) {
			ra.addAttribute("msg","Professional "+ validation.MaxlengthcheckMSGP);
			return new ModelAndView("redirect:Attempt_Mstr_Url");
		}
		
		if (validation.isOnlyAlphabetDASH(attempt) == false) {
			ra.addAttribute("msg","Professional "+ validation.isOnlyAlphabetMSGDASH);
			return new ModelAndView("redirect:Attempt_Mstr_Url");
		}
		
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:Attempt_Mstr_Url");
		}
		if (status == "2"  || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:Attempt_Mstr_Url");
		}

		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  CC_TB_ATTEMPT_MSTR where upper(attempt)=:attempt and status=:status and id !=:id")
					.setParameter("attempt", td.getAttempt())
					.setParameter("status", td.getStatus())
					.setParameter("id", id).uniqueResult();
			
			int idd =0;
			if (id == 0) {
				td.setAttempt(attempt);
				td.setCreated_by(username);
//				td.setCreated_role(role);
				td.setCreated_date(date);
				if (c == 0) {
					 idd = (int)sessionHQL.save(td);
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");
				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}
			} else {
				td.setAttempt(attempt);
				td.setModified_by(username);
				td.setModified_date(date);
				if (c == 0) {
					td.setId(id);
					String msg = Adao.updateAttempt(td);
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
			
			//For Core Directory
			if (id == 0) {
			Attmp_dirdao.Insert_Attempt_Master_Data(idd);
			}
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

		return new ModelAndView("redirect:Attempt_Mstr_Url");
	}
	
	@PostMapping("/getFilterAttempt_data")
	public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String attempt, String status) {
		return Adao.DataTableAttemptDataList(startPage, pageLength, Search, orderColunm, orderType, attempt,status);

	}

	@PostMapping("/getTotalAttempt_dataCount")
	public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId, String Search, String attempt) {
		return Adao.DataTableAttemptDataTotalCount(Search, attempt);
		
	}
	
	//-----edit

	@RequestMapping(value = "/Edit_Attempt_Mstr_Url", method = RequestMethod.POST)
	public ModelAndView Edit_Attempt_Mstr_Url(@ModelAttribute("id2") String updateid, ModelMap Mmap, Principal principal,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "attempt", required = false) String paper,
			@RequestParam(value = "status", required = false) String status,
			HttpServletRequest request, HttpSession sessionEdit) {
		
		if(request.getHeader("Referer") == null ) { 
			sessionEdit.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = sessionEdit.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Attempt_Mstr_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		CC_TB_ATTEMPT_MSTR Attempt_Details = Adao.getAttemptByid(Integer.parseInt(updateid));
		Mmap.addAttribute("msg", msg);
		Mmap.put("attempt", paper);
		Mmap.put("status", status);
		Mmap.put("Attempt_Details", Attempt_Details);
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
		Mmap.put("Paperinfo", Adao.getAttemptByid(Integer.parseInt(updateid)));
		
		tx.commit();
		
		sessionHQL.close();

		return new ModelAndView("Attempt_Mstr_Tiles");
	}
	
	@RequestMapping(value = "/Attempt_Mstr_Delete_Url", method = RequestMethod.POST)
	public ModelAndView Attempt_Mstr_Delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

		if(request.getHeader("Referer") == null ) { 
			session1.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Attempt_Mstr_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
  
			int app = session.createQuery(
					"update CC_TB_ATTEMPT_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_date", new Date())
					.setParameter("status", 2).executeUpdate();

			
			tx.commit();
			Attmp_dirdao.Delete_Attempt_Master_Data(id); 
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
		return new ModelAndView("redirect:Attempt_Mstr_Url");
	}

}


