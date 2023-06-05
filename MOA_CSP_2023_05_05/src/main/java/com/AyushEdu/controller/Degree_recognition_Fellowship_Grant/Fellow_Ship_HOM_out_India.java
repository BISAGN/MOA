package com.AyushEdu.controller.Degree_recognition_Fellowship_Grant;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import com.AyushEdu.Models.Degree_recognition_Fellowship_Grant.DG_REC_CODING_COURSES_HOMOEOPATHY_MSTR;
import com.AyushEdu.Models.Degree_recognition_Fellowship_Grant.DG_REC_FELLOW_SHIP_HOM_IN_INDIA;
import com.AyushEdu.Models.Degree_recognition_Fellowship_Grant.DG_REC_FELLOW_SHIP_HOM_OUT_INDIA;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_INSTITUTE_REGISTRATION;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_UNIVERSITY_MSTR;
import com.AyushEdu.Models.LMS_Master.TB_COUNTRY;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Degree_recognition_Fellowship_Grant.Fellow_ship_HOM_out_IndiaDAO;
import com.AyushEdu.dao.Degree_recognition_Quali_Grant.University_In_IndiaDAO;
import com.AyushEdu.dao.Degree_recognition_form_A.Form_a_ug_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Fellow_Ship_HOM_out_India {

	@Autowired
	Fellow_ship_HOM_out_IndiaDAO pdao;

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
	Form_a_ug_Dao UDdao;

	@Autowired
	University_In_IndiaDAO Udao;
	// ==========================================OPEN
	// PAGE==========================================

	@RequestMapping(value = "/FellowShipHOMoutIndiaUrl", method = RequestMethod.GET)

	public ModelAndView FellowShipHOMoutIndiaUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//		SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("FellowShipHOMoutIndiaUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		Mmap.put("msg", msg);
		Mmap.put("getCountryName", getCountryName());
		Mmap.put("getUniversityList", getUniversityList());
		Mmap.put("getInstituteListbyUserID", UDdao.getInstituteListbyUserID());
		Mmap.put("getMedUniversityName", common.getMedUniversityName(sessionFactory));
		Mmap.put("getMedicalList", getMedicalList());
			
		return new ModelAndView("Fellow_Ship_HOM_out_India_Tiles" ,"Fellow_Ship_HOM_out_India_CMD", new DG_REC_FELLOW_SHIP_HOM_OUT_INDIA());

	}
	@RequestMapping(value = "/getInstituteUrlfellowOut", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_INSTITUTE_REGISTRATION> getInstituteUrlfellowOut(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "selval", required = false) Integer selval, HttpServletRequest request) {
		System.err.println("selval------>>>"+selval);

		List<EDU_LMS_INSTITUTE_REGISTRATION> list = UDdao.getuniversitylistUrl(selval);

		return list;
	}
	// ==========================================SAVE
	// NAME==========================================

	@PostMapping(value = "/Fellow_Ship_HOM_out_India_Action")
	public ModelAndView Fellow_Ship_HOM_out_India_Action(
			@Validated @ModelAttribute("Fellow_Ship_HOM_out_India_CMD") DG_REC_FELLOW_SHIP_HOM_OUT_INDIA td,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws Exception {
//		SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("FellowShipHOMoutIndiaUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
//		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/login");
//		 }
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
	     
	     String eid = request.getParameter("id");
	     
	    String country_id = request.getParameter("country_id");
	    String university_name = request.getParameter("university_name");
	    String college_id = request.getParameter("college_id");
	    String medical_course_name = request.getParameter("medical_course_name");
	    String abbreviation = request.getParameter("abbreviation");
		String validity_period = request.getParameter("validity_period");
		String digital_code = request.getParameter("digital_code");
		
		if (country_id == null || country_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter Country Name.");
			return new ModelAndView("redirect:FellowShipHOMoutIndiaUrl");
		}
		if (university_name == null || university_name.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter University Name.");
			return new ModelAndView("redirect:FellowShipHOMoutIndiaUrl");
		}
		if (college_id == null || college_id.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter College Name.");
			return new ModelAndView("redirect:FellowShipHOMoutIndiaUrl");
		}
		if (abbreviation == null || abbreviation.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter Abbreviation.");
			return new ModelAndView("redirect:FellowShipHOMoutIndiaUrl");
		}
		if (medical_course_name == null || medical_course_name.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter medical_course_name.");
			return new ModelAndView("redirect:FellowShipHOMoutIndiaUrl");
		}
		if (validity_period == null || validity_period.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter validity_period.");
			return new ModelAndView("redirect:FellowShipHOMoutIndiaUrl");
		}
		if (digital_code == null || digital_code.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter digital_code.");
			return new ModelAndView("redirect:FellowShipHOMoutIndiaUrl");
		}

		int id = td.getId() > 0 ? td.getId() : 0;
		String userid = session.getAttribute("userId_for_jnlp").toString();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();


		try {
			Date s_to = null;
			s_to = format.parse(validity_period);
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  DG_REC_FELLOW_SHIP_HOM_OUT_INDIA where country_id=:country_id and university_name=:university_name and "
					+ "college_id=:college_id and medical_course_name=:medical_course_name  and abbreviation=:abbreviation and validity_period=:validity_period and digital_code=:digital_code")
			
			.setParameter("country_id", country_id)
			.setParameter("university_name", Integer.parseInt(university_name))
			.setParameter("college_id", Integer.parseInt(college_id))
			.setParameter("medical_course_name", Integer.parseInt(medical_course_name))
			.setParameter("abbreviation", abbreviation)
			.setParameter("validity_period", s_to)
			.setParameter("digital_code", Integer.parseInt(digital_code))
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
			else {
				
				td.setCountry_id(country_id);
				td.setUniversity_name(Integer.parseInt(university_name));
				td.setCollege_id(Integer.parseInt(college_id));
				td.setAbbreviation(abbreviation);
				td.setMedical_course_name(Integer.parseInt(medical_course_name));
				td.setValidity_period(s_to);
				td.setDigital_code(Integer.parseInt(digital_code));
				td.setModified_by(username);
				td.setModified_date(date);
				if (c == 0) {
					td.setId(id);
					String msg = pdao.updateFellow_ship_hom_out_india(td);
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
				ra.addAttribute("msg", "Couldnot roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
				
			}
		}

		return new ModelAndView("redirect:FellowShipHOMoutIndiaUrl");
	}

	
	
	
	@PostMapping("/getFilter_fellow_ship_hom_out_india_data")
	public @ResponseBody List<Map<String, Object>> getFilter_fellow_ship_hom_out_india_data(int startPage,
			int pageLength, String Search, String orderColunm, String orderType, String country_id, String university_name,
			String college_id, String abbreviation,String medical_course_name,  String validity_period, String digital_code) {

		return pdao.DataTableFellow_ship_hom_out_india_Details_DataList(startPage, pageLength, Search, orderColunm,
				orderType, country_id, university_name, college_id,  abbreviation, medical_course_name,validity_period, digital_code);
	}
	
	@PostMapping("/getTotal_fellow_ship_hom_out_india_dataCount")
	public @ResponseBody long getTotal_fellow_ship_hom_out_india_dataCount(HttpSession sessionUserId, String Search,
			String country_id, String university_name,
			String college_id,  String abbreviation,String medical_course_name,
			String validity_period, String digital_code) {
		return pdao.DataTableFellow_ship_hom_out_india_Details_DataTotalCount(Search, country_id, university_name, college_id,
				 abbreviation, medical_course_name,validity_period, digital_code);
	}
	
	public List<TB_COUNTRY> getCountryName() {
		 Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 Query q0 = session.createQuery("from TB_COUNTRY  where status='1' ");
		 
		 List<TB_COUNTRY> CountryList = q0.list();
	        session.getTransaction().commit();
	        session.close();                
	       return CountryList;
	 }


	@RequestMapping(value = "/deleteFellow_ship_Hom_out_india_Url", method = RequestMethod.POST)
	public ModelAndView deleteFellow_ship_Hom_out_india_Url(@ModelAttribute("id2") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {

			int app = session.createQuery(
					"delete from DG_REC_FELLOW_SHIP_HOM_OUT_INDIA where id=:id")
					.setParameter("id", id).executeUpdate();
			
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
		return new ModelAndView("redirect:FellowShipHOMoutIndiaUrl");
	}
	
	public List<EDU_LMS_UNIVERSITY_MSTR> getUniversityList() {
		 Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 Query q0 = session.createQuery("from EDU_LMS_UNIVERSITY_MSTR  where status='1' ");
		 
		 List<EDU_LMS_UNIVERSITY_MSTR> CountryList = q0.list();
	        session.getTransaction().commit();
	        session.close();                
	       return CountryList;
	 }
	@RequestMapping(value = "/getAbbreviationListfellow1", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getAbbreviationListfellow1(ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String institute_id) {
	       return Udao.getAbbreviationdao(institute_id);
	       
}
	public List<DG_REC_CODING_COURSES_HOMOEOPATHY_MSTR> getMedicalList() {
		 Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 Query q0 = session.createQuery("select id,concat(code,'-',qualification) as qualification from DG_REC_CODING_COURSES_HOMOEOPATHY_MSTR ");
		 
		 List<DG_REC_CODING_COURSES_HOMOEOPATHY_MSTR> MedicalList = q0.list();
	        session.getTransaction().commit();
	        session.close();                
	       return MedicalList;
	 }
	
}