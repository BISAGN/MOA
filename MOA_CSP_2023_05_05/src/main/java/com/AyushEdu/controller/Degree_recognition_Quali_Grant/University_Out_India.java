package com.AyushEdu.controller.Degree_recognition_Quali_Grant;

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
import com.AyushEdu.Models.Degree_recognition_Quali_Grant.DG_REC_UNIVERSITY_OUT_INDIA;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_INSTITUTE_REGISTRATION;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_UNIVERSITY_MSTR;
import com.AyushEdu.Models.LMS_Master.TB_COUNTRY;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Degree_recognition_Quali_Grant.University_Out_IndiaDAO;
import com.AyushEdu.dao.Degree_recognition_form_A.Form_a_ug_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class University_Out_India {

	@Autowired
	University_Out_IndiaDAO Udao;

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
	
	// ==========================================OPEN PAGE==========================================

	@RequestMapping(value = "/UniversityOutIndiaUrl", method = RequestMethod.GET)

	public ModelAndView UniversityOutIndiaUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("UniversityOutIndiaUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		Mmap.put("msg", msg);
		Mmap.put("getMedUniversityName", common.getMedUniversityName(sessionFactory));
		Mmap.put("getInstituteListbyUserID", UDdao.getInstituteListbyUserID());
		Mmap.put("getMedicalList", getMedicalList());

		
		
		return new ModelAndView("University_Out_India_Tiles", "University_Out_CMD", new DG_REC_UNIVERSITY_OUT_INDIA());

	}
	@RequestMapping(value = "/getInstituteUrlindiaout", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_INSTITUTE_REGISTRATION> getInstituteUrlindiaout(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "selval", required = false) Integer selval, HttpServletRequest request) {

		List<EDU_LMS_INSTITUTE_REGISTRATION> list = UDdao.getuniversitylistUrl(selval);

		return list;
	}
	// ==========================================SAVE NAME==========================================

	

	@PostMapping(value = "/University_Out_Action")
	public ModelAndView University_Out_Action(@Validated @ModelAttribute("University_Out_CMD") DG_REC_UNIVERSITY_OUT_INDIA td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra,ModelMap Mmap) throws Exception {
if(request.getHeader("Referer") == null ) { 
			
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("UniversityOutIndiaUrl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			Mmap.put("getMedUniversityName", common.getMedUniversityName(sessionFactory));

		
		 Date date = new Date();
		 
		 String username = principal.getName();
		 
	     DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
	     
		    String country_name = request.getParameter("country_name");
		    
		    String university_name = request.getParameter("university_name");
		    String college_name = request.getParameter("college_name");
		    String abbreviation = request.getParameter("abbreviation");
		    String medical_course_name = request.getParameter("medical_course_name");
			String validity_period = request.getParameter("validity_period");
			String digital_code = request.getParameter("digital_code");
		
		
		
		if (country_name == null || country_name.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter Country Name.");
			return new ModelAndView("redirect:UniversityOutIndiaUrl");
		}
		if (university_name == null || university_name.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter University Name.");
			return new ModelAndView("redirect:UniversityOutIndiaUrl");
		}
		if (college_name == null || college_name.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter College Name.");
			return new ModelAndView("redirect:UniversityOutIndiaUrl");
		}
		if (abbreviation == null || abbreviation.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter Abbreviation.");
			return new ModelAndView("redirect:UniversityOutIndiaUrl");
		}
		if (medical_course_name == null || medical_course_name.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter medical_course_name.");
			return new ModelAndView("redirect:UniversityOutIndiaUrl");
		}
		if (validity_period == null || validity_period.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter validity_period.");
			return new ModelAndView("redirect:UniversityOutIndiaUrl");
		}
		if (digital_code == null || digital_code.trim().equals("0")) {
			ra.addAttribute("msg", "Please Enter digital_code.");
			return new ModelAndView("redirect:UniversityOutIndiaUrl");
		}
		
		
		int id = td.getId() > 0 ? td.getId() : 0;

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Date s_to = null;
			
			s_to = format.parse(validity_period);
			
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  DG_REC_UNIVERSITY_OUT_INDIA where country_name=:country_name and university_name=:university_name and "
					+ "college_name=:college_name  and Upper(abbreviation)=:abbreviation and medical_course_name=:medical_course_name and validity_period=:validity_period and digital_code=:digital_code")
					.setParameter("country_name", country_name)
					.setParameter("university_name", Integer.parseInt(university_name))
					.setParameter("college_name", Integer.parseInt(college_name))
					.setParameter("abbreviation", abbreviation)
					.setParameter("medical_course_name", Integer.parseInt(medical_course_name))
					.setParameter("validity_period", s_to)
					.setParameter("digital_code", Integer.parseInt(digital_code))
					.uniqueResult();
			
			if (id == 0) {
				td.setCreated_by(username);
				td.setCreated_date(date);
				if (c == 0) {
					td.setValidity_period(s_to);
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Saved Successfully.");
				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}
			}else {
				td.setCountry_name(country_name);
				td.setUniversity_name(Integer.parseInt(university_name));
				td.setCollege_name(Integer.parseInt(college_name));
				td.setAbbreviation(abbreviation);
				td.setMedical_course_name(Integer.parseInt(medical_course_name));
				td.setValidity_period(s_to);
				td.setDigital_code(Integer.parseInt(digital_code));
				td.setModified_by(username);
				td.setModified_date(date);
				if (c == 0) {
					td.setId(id);
					String msg = Udao.updateoutuniversity(td);
					
					ra.addAttribute("msg", "Data Updated Successfully.");
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

		return new ModelAndView("redirect:UniversityOutIndiaUrl");
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

	@PostMapping("/getFilter_Out_University")

	public @ResponseBody List<Map<String, Object>> getFilterRegistration_data(int startPage, int pageLength,

			String Search, String orderColunm, String orderType, String country_name, String university_name, String college_name, String abbreviation, String medical_course_name , String validity_period, String digital_code) {

		return Udao.DataTable_Out_UniversityDataList(startPage, pageLength, Search, orderColunm, orderType, country_name, university_name, college_name, abbreviation, medical_course_name, validity_period, digital_code);

	}

	@PostMapping("/getTotal_Out_University")
	public @ResponseBody long getTotalRegistration_dataCount(HttpSession sessionUserId,String Search, String country_name, String university_name, String college_name, String abbreviation, String medical_course_name, String validity_period, String digital_code) 
    {
		return Udao.DataTable_Out_UniversityDataTotalCount(Search, country_name, university_name, college_name, abbreviation, medical_course_name, validity_period, digital_code);
		
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
	

	@RequestMapping(value = "/getAbbreviationListoutindia", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getAbbreviationListoutindia(ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String institute_id) {
	       return Udao.getAbbreviationoutdao(institute_id);
	       
}
	

	// -------------------------SEARCH Battalion  -------------------------------------//
	 
			 @RequestMapping(value = "/admin/getSearch_Out_University", method = RequestMethod.POST)
				public ModelAndView getSearch_Out_University(ModelMap Mmap,HttpSession session,HttpServletRequest request,
						@RequestParam(value = "msg", required = false) String msg,
						@RequestParam(value = "country_name1", required = false) String country_name1 ,String country_name,
						@ModelAttribute("university_name1") String university_name,@ModelAttribute("college_name1") String college_name,
						@ModelAttribute("abbreviation1") String abbreviation, 
						@ModelAttribute("medical_course_name1") String medical_course_name,@ModelAttribute("validity_period1") String validity_period,
						@ModelAttribute("adigital_code1") String adigital_code){
				
				 if(request.getHeader("Referer") == null ) { 
						
						Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return new ModelAndView("redirect:/landingpage");
					 }
					String roleid1 = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("UniversityOutIndiaUrl", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}


				        Mmap.put("country_name1", country_name);
				        Mmap.put("university_name1", university_name);
						Mmap.put("college_name1", college_name);
						Mmap.put("abbreviation1", abbreviation);
						Mmap.put("medical_course_name1", medical_course_name);
						Mmap.put("validity_period1", validity_period);
						Mmap.put("adigital_code1", adigital_code);
					

					   return new ModelAndView("University_Out_India_Tiles","University_Out_CMD", new DG_REC_UNIVERSITY_OUT_INDIA());
					   
				}
			 
			//==========================================DELETE NAME========================================== 	
			 
			 
				@RequestMapping(value = "/delete_Url34", method = RequestMethod.POST)
				public ModelAndView delete_Url34(@ModelAttribute("id34") int id, BindingResult result, RedirectAttributes ra,
						HttpServletRequest request, ModelMap model, HttpSession session1) {
	
//					if(request.getHeader("Referer") == null ) { 
//						session1.invalidate();
//						model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//						 return new ModelAndView("redirect:/login");
//					 }
					String roleid1 = session1.getAttribute("roleid").toString();
//					 Boolean val = roledao.ScreenRedirect("academic_detailsUrl", roleid1);		
//						if(val == false) {
//							return new ModelAndView("AccessTiles");
//					}
						
					List<String> liststr = new ArrayList<String>();

					Session session = this.sessionFactory.openSession();
					Transaction tx = session.beginTransaction();

					String username = session1.getAttribute("username").toString();
					try {
			  
						int app = session.createQuery(
								"delete from DG_REC_UNIVERSITY_OUT_INDIA where id=:id")
								.setParameter("id", id).executeUpdate();

						tx.commit();
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
					return new ModelAndView("redirect:UniversityOutIndiaUrl");
				}
				
				
				public int changeMonthStringToInt(String month) {
					switch(month.toUpperCase()) {
		            case "JAN":
		                return 1;
		            case "FEB":
		                return 2;
		            case "MAR":
		                return 3;
		            case "APR":
		                return 4;
		            case "MAY":
		                return 5;
		            case "JUN":
		                return 6;
		            case "JUL":
		                return 7;
		            case "AUG":
		                return 8;
		            case "SEP":
		                return 9;
		            case "OCT":
		                return 10;
		            case "NOV":
		                return 11;
		            case "DEC":
		                return 12;
		            }
					return 0;
				}
				

}
