package com.AyushEdu.controller.Degree_recognition_Fellowship_Grant;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import com.AyushEdu.Models.Degree_recognition_Fellowship_Grant.DG_REC_CODING_COURSES_HOMOEOPATHY_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Degree_recognition_Fellowship_Grant.Coding_Courses_HomoeopathyDAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Coding_Courses_Homoeopathy_Mstr {
	

	@Autowired
	Coding_Courses_HomoeopathyDAO pdao;

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

	
	// ==========================================OPEN PAGE==========================================

	@RequestMapping(value = "/Code_Course_HomUrl", method = RequestMethod.GET)
	public ModelAndView Code_Course_HomUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//		SECURITY ABHISHEK
//		if(request.getHeader("Referer") == null ) { 
//			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("Code_Course_HomUrl", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
//		Mmap.put("msg", msg);
		
		
		
		return new ModelAndView("Codeing_Courses_Hom_Tiles", "Code_Courses_HomCMD", new DG_REC_CODING_COURSES_HOMOEOPATHY_MSTR());

	}
	
	
	////////////////////SAVE////////////////////////
	
	
	
	@PostMapping(value = "/Code_Courses_Hom_Action")
	public ModelAndView Code_Courses_Hom_Action(@Validated @ModelAttribute("Code_Courses_HomCMD") DG_REC_CODING_COURSES_HOMOEOPATHY_MSTR td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws Exception {

//		SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Code_Course_HomUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		String role = session.getAttribute("role").toString();
//		System.out.println("Dr=======================>>>"+role);
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("academic_detailsUrl", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		
		 Date date = new Date();
		 
		 String username = principal.getName();
		 
	     DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
	     
	    String sr_no = request.getParameter("sr_no");
	    String qualification = request.getParameter("qualification");
	    String code = request.getParameter("code");
	   
		
		
		
//		if (country_name == null || country_name.trim().equals("0")) {
//			ra.addAttribute("msg", "Please Enter Country Name.");
//			return new ModelAndView("redirect:FellowShipHOMInIndiaUrl");
//		}
//		if (university_name == null || university_name.trim().equals("0")) {
//			ra.addAttribute("msg", "Please Enter University Name.");
//			return new ModelAndView("redirect:FellowShipHOMInIndiaUrl");
//		}
//		if (college_name == null || college_name.trim().equals("0")) {
//			ra.addAttribute("msg", "Please Enter College Name.");
//			return new ModelAndView("redirect:FellowShipHOMInIndiaUrl");
//		}
//		
		
		
		int id = td.getId() > 0 ? td.getId() : 0;
		
		
//		String system_name = principal.getName();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  DG_REC_CODING_COURSES_HOMOEOPATHY_MSTR where sr_no=:sr_no and qualification=:qualification and code=:code")
					.setParameter("sr_no",Double.parseDouble(sr_no))
					.setParameter("qualification", qualification)
					.setParameter("code", code)
					.uniqueResult();
			
			System.err.println("ccccccccccccccccccccccccc"+c);
			if (id == 0) {
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
			}
			tx.commit();
		} catch (RuntimeException e) {
			try {

				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				ra.addAttribute("msg", "Couldnot roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
				
			}
		}

		return new ModelAndView("redirect:Code_Course_HomUrl");
	}
	
	
	@PostMapping("/getFilter_coding_courses_hom_data")
	public @ResponseBody List<Map<String, Object>> DataTableCoding_Courses_Hom_Details_DataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String sr_no, String qualification, String code) {

		return pdao.DataTableCoding_Courses_Hom_Details_DataList(startPage, pageLength,Search , orderColunm, orderType, qualification,code, code);
	}

	@PostMapping("/getTotal_coding_courses_hom_dataCount")
	public @ResponseBody long getTotal_coding_courses_hom_dataCount(HttpSession sessionUserId, String Search, String sr_no, String qualification, String code) {
		return pdao.DataTableCoding_Courses_Hom_Details_DataTotalCount(Search, qualification,code, code);
	}
	

}
