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
import com.AyushEdu.Models.Examination.EXAM_RESULT_STATUS;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Examination.student_result_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class student_result_mstr_controller {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	ValidationController validation;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Autowired
	student_result_Dao Adao;
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;

	@RequestMapping(value = "admin/student_result_Mstr_Url", method = RequestMethod.GET)
	public ModelAndView student_result_Mstr_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {

			//SECURITY ABHISHEK
			if(request.getHeader("Referer") == null ) { 
				
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("student_result_Mstr_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
				
			Mmap.put("msg", msg);
			Mmap.put("getstudentresultstatuslist", common.getstudentresultstatuslist(sessionFactory));
			Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("Student_Rsult_tiles");
	}

	// ==========================================SAVE/view//Edit==========================================

	@PostMapping(value = "/ResultAction")
	public ModelAndView ResultAction(@Validated @ModelAttribute("ResultCMD") EXAM_RESULT_STATUS td,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) {
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("student_result_Mstr_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
	
			
		String result_status = request.getParameter("result_status");
		String status = request.getParameter("status");

		if (result_status == null || result_status.trim().equals("")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:student_result_Mstr_Url");
		}
		if (status == null || status.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Status.");
			return new ModelAndView("redirect:student_result_Mstr_Url");
		}
		if (status == "2" || status.trim().equals("2")) {
			ra.addAttribute("msg", "Only Select Active Status.");
			return new ModelAndView("redirect:student_result_Mstr_Url");
		}

		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EXAM_RESULT_STATUS where upper(result_status)=:result_status and status=:status and id !=:id")
					.setParameter("result_status", td.getResult_status()).setParameter("status", td.getStatus())
					.setParameter("id", id).uniqueResult();

			if (id == 0) {
				td.setResult_status(result_status);
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
			} else {
				td.setResult_status(result_status);
				td.setModified_by(username);
				td.setModified_date(date);
				if (c == 0) {
					td.setId(id);
					String msg = Adao.update_result_status(td);
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

		return new ModelAndView("redirect:student_result_Mstr_Url");
	}

	@PostMapping("/getFilterStudent_result")

	public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,

			String Search, String orderColunm, String orderType, String result_status, String status) {

		return Adao.DataTableStudentresultList(startPage, pageLength, Search, orderColunm, orderType, result_status,
				status);

	}

	@PostMapping("/getTotalStudent_resultCount")
	public @ResponseBody long getTotalStudentresult_dataCount(HttpSession sessionUserId, String Search,
			String result_status, String status) {
		return Adao.DataTableStudentresultTotalCount(Search, result_status,status);

	}

	// -----edit

	@RequestMapping(value = "/Edit_Student_Result_Mstr_Url", method = RequestMethod.POST)
	public ModelAndView Edit_Student_Result_Mstr_Url(@ModelAttribute("id2") String updateid, ModelMap Mmap,
			Principal principal, @RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "result_status", required = false) String result_status,
			@RequestParam(value = "status", required = false) String status, HttpServletRequest request,
			HttpSession sessionEdit) {
		
		if(request.getHeader("Referer") == null ) { 
			sessionEdit.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = sessionEdit.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("student_result_Mstr_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		EXAM_RESULT_STATUS result_status1 = Adao.getResult_statustByid(Integer.parseInt(updateid));
		Mmap.addAttribute("msg", msg);
		Mmap.put("result_status", result_status1);
		Mmap.put("status", status);
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

		tx.commit();
		sessionHQL.close();

		return new ModelAndView("Student_Rsult_tiles");
	}

	@RequestMapping(value = "/Student_Result_Mstr_Delete_Url", method = RequestMethod.POST)
	public ModelAndView Student_Result_Mstr_Delete_Url(@ModelAttribute("id1") int id, BindingResult result,
			RedirectAttributes ra, HttpServletRequest request, ModelMap model, HttpSession session1) {

		if(request.getHeader("Referer") == null ) { 
			session1.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("student_result_Mstr_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {

			int app = session.createQuery(
					"update EXAM_RESULT_STATUS set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
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
		return new ModelAndView("redirect:student_result_Mstr_Url");
	}

}
