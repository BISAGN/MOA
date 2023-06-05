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
import com.AyushEdu.Models.Teacher_Master.EDU_DOC_ATTACHMENTS_MSTR;
import com.AyushEdu.Models.Teacher_Master.EDU_FACULTY_SUBJECT_MSTR;
import com.AyushEdu.Models.Teacher_Master.TB_NCH_DEPARTMENT_MSTR;
//import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PROFESSIONAL_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
//import com.AyushEdu.dao.LMS_Master.DocumentName_Mstr_DAO;
import com.AyushEdu.dao.LMS_Master.Document_Attachments_Mstr_DAO;
import com.AyushEdu.dao.LMS_Master.Faculty_Subject_Mstr_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Faculty_Subject_Mstr_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	
	
	  @Autowired
	  Faculty_Subject_Mstr_DAO Ddao;
	 

	@Autowired
	ValidationController validation;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	 
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
	@RequestMapping(value = "admin/Faculty_Subject_Url", method = RequestMethod.GET)
	public ModelAndView Faculty_Subject_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
			
//SECURITY RAHUL
			
			if(request.getHeader("Referer") == null ) { 
				// session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
					
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Faculty_Subject_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			
		Mmap.put("msg", msg);
		Mmap.put("getFacultyCourseList", common.getFacultyCourseList(sessionFactory));
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));

	} catch (Exception e) {
		e.printStackTrace();
	}

		return new ModelAndView("faculty_subject_Tiles");
	}
	
	//==========================================SAVE/view//Edit Professional========================================== 	


	@PostMapping(value = "/FacultySubjectAction")
	public ModelAndView FacultySubjectAction(@Validated @ModelAttribute("FacultySubjectCMD") EDU_FACULTY_SUBJECT_MSTR td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session,
			RedirectAttributes ra) {
		
		
		if(request.getHeader("Referer") == null ) { 
			// session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
				
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Faculty_Subject_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}

		String subject_name = request.getParameter("subject_name");
		String fac_course_id = request.getParameter("fac_course_id");
		String status = request.getParameter("status");

		if (subject_name == null || subject_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Document Name");
			return new ModelAndView("redirect:Faculty_Subject_Url");
		}
		
		if (validation.maxlengthcheck100(subject_name) == false) {
			ra.addAttribute("msg","Subject Name "+ validation.MaxlengthcheckMSG100);
			
			return new ModelAndView("redirect:Faculty_Subject_Url");
			
		}
		if (validation.isOnlyAlphabetDASH(subject_name) == false) {
			ra.addAttribute("msg","Subject "+ validation.isOnlyAlphabetMSGDASH);
			return new ModelAndView("redirect:Faculty_Subject_Url");
		}
		/*
		 * if (validation.isOnlyAlphabetDASH(subject_name) == false) {
		 * ra.addAttribute("msg","Subject Name"+ validation.isOnlyAlphabetMSGDASH);
		 * 
		 * return new ModelAndView("redirect:Faculty_Subject_Url"); }
		 */
		
		if (fac_course_id == null || fac_course_id.trim().equals("")) {
			ra.addAttribute("msg", "Please Select Course.");
			return new ModelAndView("redirect:Faculty_Subject_Url");
		}
		
		if (status == null || status.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Status.");
			return new ModelAndView("redirect:Faculty_Subject_Url");
		}

		int id =Integer.parseInt(request.getParameter("id"));
		Date date = new Date();
		//String username = principal.getName();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_FACULTY_SUBJECT_MSTR where upper(subject_name)=:subject_name and fac_course_id=:fac_course_id and status=:status and id !=:id")
					.setParameter("subject_name", td.getSubject_name().toUpperCase())
					.setParameter("fac_course_id", td.getFac_course_id())
					.setParameter("status", td.getStatus())
					.setParameter("id", id).uniqueResult();
			
			if (id == 0) {
				td.setSubject_name(subject_name);
				//td.setFac_course_id(fac_course_id);
				td.setCreated_by(userid);
//				td.setCreated_role(role);
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
				td.setSubject_name(subject_name);
//				td.setFac_course_id(Integer.parseInt(fac_course_id));
				td.setModified_by(userid);
				td.setModified_date(date);
				if (c == 0) {
					td.setId(id);
				
					String msg = Ddao.updateFacultySubject(td);
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
		
		return new ModelAndView("redirect:Faculty_Subject_Url");
	}
	
	
	@PostMapping("/getFilterSubject_data")

	public @ResponseBody List<Map<String, Object>> getFilterSubject_data(int startPage, int pageLength,

			String Search, String orderColunm, String orderType, String fac_course_id, String subject_name,  String status) {

		return Ddao.DataTableSubjectDataList(startPage, pageLength, Search, orderColunm, orderType, fac_course_id, subject_name,status);

	}

	@PostMapping("/getTotalSubject_dataCount")
	public @ResponseBody long getTotalSubject_dataCount(HttpSession sessionUserId, String Search, String fac_course_id, String subject_name) {
		return Ddao.DataTableSubjectDataTotalCount(Search, fac_course_id, subject_name);
		
	}


	@RequestMapping(value = "/Edit_Faculty_Subject_Mstr_Url", method = RequestMethod.POST)
	public ModelAndView Edit_Faculty_Subject_Mstr_Url(@ModelAttribute("id2") String updateid, ModelMap Mmap, Principal principal,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "fac_course_id", required = false) String fac_course_id,
			@RequestParam(value = "subject_name", required = false) String subject_name,
			@RequestParam(value = "status", required = false) String status,
			HttpServletRequest request, HttpSession sessionEdit) {
		
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		EDU_FACULTY_SUBJECT_MSTR Faculty_Subject_Details = Ddao.getSubjectByid(Integer.parseInt(updateid));
		Mmap.addAttribute("msg", msg);
		Mmap.put("fac_course_id", fac_course_id);
		Mmap.put("subject_name", subject_name);
		Mmap.put("status", status);
		Mmap.put("Faculty_Subject_Details", Faculty_Subject_Details);
		Mmap.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));		
		Mmap.put("FacultySubjectinfo", Ddao.getSubjectByid(Integer.parseInt(updateid)));
		
		tx.commit();
		sessionHQL.close();

		return new ModelAndView("faculty_subject_Tiles");
	}
	
	@RequestMapping(value = "/Faculty_Subject_Mstr_Delete_Url", method = RequestMethod.POST)
	public ModelAndView Faculty_Subject_Mstr_Delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
  
			int app = session.createQuery(
					"update EDU_FACULTY_SUBJECT_MSTR set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
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
		return new ModelAndView("redirect:Faculty_Subject_Url");
	}
	
}