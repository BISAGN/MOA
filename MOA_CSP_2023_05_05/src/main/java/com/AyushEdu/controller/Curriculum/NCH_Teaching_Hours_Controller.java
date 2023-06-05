package com.AyushEdu.controller.Curriculum;
import java.io.IOException;
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
import com.AyushEdu.Models.Curriculum.EDU_CC_TB_NCH_TEACHING_HOURS;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Curriculum.NCH_Teaching_HoursDAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class NCH_Teaching_Hours_Controller {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	ValidationController validation;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Autowired
	CommonController common;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	NCH_Teaching_HoursDAO sdao;
	
	@RequestMapping(value = "admin/NCH_Teaching_Hours_Url", method = RequestMethod.GET)
	public ModelAndView NCH_Teaching_Hours_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		try {
//			SECURITY -- RIDDHI 	
			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("NCH_Teaching_Hours_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String role = session.getAttribute("role").toString();
			Mmap.put("msg", msg);
			Mmap.put("getSystemList", common.getSystemList(sessionFactory, role));
			Mmap.put("getprofessionalList", common.getprofessionalList(sessionFactory));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("NCH_Teaching_Hours_Tiles");
	}
	
	@PostMapping(value = "/nch_teaching_hoursAction")
	public ModelAndView nch_teaching_hoursAction(@Validated @ModelAttribute("nch_teaching_hoursCMD") EDU_CC_TB_NCH_TEACHING_HOURS td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal, RedirectAttributes ra
			)throws IOException, ParseException {

		
		String system_id = request.getParameter("system_id");
		String degree_id = request.getParameter("degree_id");
		String professional_id = request.getParameter("professional_id");
		String course_id = request.getParameter("course_id");
		String theoretical_lecture = request.getParameter("theoretical_lecture");
		String pract_tutor_semi_clinic_post = request.getParameter("pract_tutor_semi_clinic_post");

		if (system_id == null || system_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select System.");
			return new ModelAndView("redirect:NCH_Teaching_Hours_Url");
		}
		if (degree_id == null || degree_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Degree");
			return new ModelAndView("redirect:NCH_Teaching_Hours_Url");
		}
		if (professional_id == null || professional_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Professional");
			return new ModelAndView("redirect:NCH_Teaching_Hours_Url");
		}
		if (course_id == null || course_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Select Subject");
			return new ModelAndView("redirect:NCH_Teaching_Hours_Url");
		}
		if (theoretical_lecture == null || theoretical_lecture.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Theoretical Lecture");
			return new ModelAndView("redirect:NCH_Teaching_Hours_Url");
		}
		if (pract_tutor_semi_clinic_post == null || pract_tutor_semi_clinic_post.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Practical/Tutorial/Seminar/Clinical Posting");
			return new ModelAndView("redirect:NCH_Teaching_Hours_Url");
		}
		int id = td.getId() > 0 ? td.getId() : 0;

		Date date = new Date();
		String username = principal.getName();
		
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		try {
			
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  EDU_CC_TB_NCH_TEACHING_HOURS where system_id=:system_id and degree_id=:degree_id and "
					+ " professional_id=:professional_id and course_id=:course_id and "
					+ "  theoretical_lecture=:theoretical_lecture and pract_tutor_semi_clinic_post=:pract_tutor_semi_clinic_post "
					+ "  and id !=:id ")
					.setParameter("system_id", Integer.parseInt(system_id)).setParameter("degree_id", Integer.parseInt(degree_id))
					.setParameter("professional_id", Integer.parseInt(professional_id))
					.setParameter("course_id", Integer.parseInt(course_id))
					.setParameter("theoretical_lecture",Integer.parseInt(theoretical_lecture) )
					.setParameter("pract_tutor_semi_clinic_post",Integer.parseInt( pract_tutor_semi_clinic_post))
					.setParameter("id", id).uniqueResult();
			
			if (id == 0) {
				td.setSystem_id(Integer.parseInt(system_id));
				td.setDegree_id(Integer.parseInt(degree_id));
				td.setProfessional_id(Integer.parseInt(professional_id));
				td.setCourse_id(Integer.parseInt(course_id));
				td.setTheoretical_lecture(Integer.parseInt(theoretical_lecture));
				td.setPract_tutor_semi_clinic_post(Integer.parseInt(pract_tutor_semi_clinic_post));
				td.setStatus(1);
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
				td.setTheoretical_lecture(Integer.parseInt(theoretical_lecture));
				td.setPract_tutor_semi_clinic_post(Integer.parseInt(pract_tutor_semi_clinic_post));
				td.setModified_by(username);
				td.setModified_date(date);
				if (c == 0) {
					td.setId(id);
					String msg = sdao.updateNCH_Teaching_Hours(td);
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
		
		return new ModelAndView("redirect:NCH_Teaching_Hours_Url");
	}
	
	@PostMapping("/getFilter_NCH_Teaching_Hours_data")
	public @ResponseBody List<Map<String, Object>> getFilter_NCH_Teaching_Hours_data(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String system_id, String degree_id, String professional_id,
			String course_id, String theoretical_lecture, String pract_tutor_semi_clinic_post, HttpSession session) {
		String role = session.getAttribute("role").toString();
		return sdao.NCH_Teaching_HoursDataList(startPage, pageLength, Search, orderColunm, orderType, system_id,
				degree_id, professional_id, course_id, theoretical_lecture,pract_tutor_semi_clinic_post,role );

	}

	@PostMapping("/getFilter_NCH_Teaching_Hours_dataCount")
	public @ResponseBody long getFilter_NCH_Teaching_Hours_dataCount(HttpSession sessionUserId, String Search, String system_id, String degree_id,
			String professional_id, String course_id, String theoretical_lecture, String pract_tutor_semi_clinic_post, HttpSession session) {
		String role = session.getAttribute("role").toString();
		return sdao.DataTableNCH_Teaching_HoursTotalCount(Search, system_id, degree_id, professional_id, course_id,
				 theoretical_lecture,pract_tutor_semi_clinic_post, role);

	}
	
	@RequestMapping(value = "/NCH_Teaching_Hours_Delete_Url", method = RequestMethod.POST)
	public ModelAndView NCH_Teaching_Hours_Delete_Url(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {
//		SECURITY -- RIDDHI 	
		if(request.getHeader("Referer") == null ) { 
//			session1.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("NCH_Teaching_Hours_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
  
			int app = session.createQuery(
					"update EDU_CC_TB_NCH_TEACHING_HOURS set modified_by=:modified_by,modified_date=:modified_date,status=:status where id=:id")
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
		return new ModelAndView("redirect:NCH_Teaching_Hours_Url");
	}
}
