package com.AyushEdu.controller.Degree_recognition_List_DRL5;

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
import com.AyushEdu.Models.Degree_recognition_List_DRL5.DG_REC_DEGREE_RECOGNITION_LIST_DRL;
import com.AyushEdu.Models.Degree_recognition_List_DRL5.DG_REC_RECOGNITION_MEDICAL_QUALI_IN_INDIA;
import com.AyushEdu.Models.Degree_recognition_List_DRL5.DG_REC_RECOGNITION_MEDICAL_QUALI_OUT_INDIA;
import com.AyushEdu.Models.Degree_recognition_Quali_Grant.DG_REC_UNIVERSITY_IN_INDIA;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_INSTITUTE_REGISTRATION;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_UNIVERSITY_MSTR;
import com.AyushEdu.Models.LMS_Master.TB_COUNTRY;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Degree_Recognition_List_DRL5.Degree_recognition_List_DRLDao;
import com.AyushEdu.dao.Degree_recognition_Quali_Grant.University_In_IndiaDAO;
import com.AyushEdu.dao.Degree_recognition_form_A.Form_a_ug_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Degree_Recognition_List_DRL5 {
	
	@Autowired
	Degree_recognition_List_DRLDao Udao;
	
	
	
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
	
				/////////////////////////////////// DLR FORM A Open Page URL  /////////////////////////////////

	@RequestMapping(value = "/DegreeRecognitionListurl", method = RequestMethod.GET)

	public ModelAndView DegreeRecognitionListurl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("DegreeRecognitionListurl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			Mmap.put("msg", msg);
			Mmap.put("getUniversityList", getUniversityList());
			Mmap.put("getInstituteListbyUserID", UDdao.getInstituteListbyUserID());
			Mmap.put("getMedUniversityName", common.getMedUniversityName(sessionFactory));
			Mmap.put("getMedicalList", getMedicalList());
		
		return new ModelAndView("Degree_Recognition_List_Tiles", "Degree_Recognition_List_DRL_CMD", new DG_REC_UNIVERSITY_IN_INDIA());

	}
	
	@RequestMapping(value = "/getInstituteUrldrl", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_INSTITUTE_REGISTRATION> getInstituteUrldrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "selval", required = false) Integer selval, HttpServletRequest request) {
		System.err.println("selval------>>>"+selval);

		List<EDU_LMS_INSTITUTE_REGISTRATION> list = UDdao.getuniversitylistUrl(selval);

		return list;
	}
	
	@RequestMapping(value = "/getAbbreviationListDRL", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getAbbreviationListDRL(ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String institute_id) {
	       return Udao.getAbbreviationdaome(institute_id);
	       
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
	
	public List<DG_REC_CODING_COURSES_HOMOEOPATHY_MSTR> getMedicalList() {
		 Session session = sessionFactory.openSession();
		 Transaction tx = session.beginTransaction();
		 Query q0 = session.createQuery("select id,concat(code,'-',qualification) as qualification from DG_REC_CODING_COURSES_HOMOEOPATHY_MSTR ");
		 
		 List<DG_REC_CODING_COURSES_HOMOEOPATHY_MSTR> MedicalList = q0.list();
	        session.getTransaction().commit();
	        session.close();                
	       return MedicalList;
	 }
	
	
	// ==========================================SAVE FORM A==========================================

	@PostMapping(value = "/Degree_Recognition_List_DRL_Action")
	public ModelAndView Degree_Recognition_List_DRL_Action(@Validated @ModelAttribute("Degree_Recognition_List_DRL_CMD") DG_REC_DEGREE_RECOGNITION_LIST_DRL td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws Exception {
		//SECURITY ABHISHEK
				if(request.getHeader("Referer") == null ) { 
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				 String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("DegreeRecognitionListurl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}

		String role = session.getAttribute("role").toString();
		Date date = new Date();
		String username = principal.getName();
	    DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
	    String eid = request.getParameter("id");
	    String applicant_name = request.getParameter("applicant_name");
	    String country_name = request.getParameter("country_name");
	    String university_name = request.getParameter("university_name");
	    String college_name = request.getParameter("college_name");
	    String abbreviation = request.getParameter("abbreviation");
	    String medical_course_name = request.getParameter("medical_course_name");
		String validity_period = request.getParameter("validity_period");
		String digital_code = request.getParameter("digital_code");
	
//		if (country_name == null || country_name.trim().equals("")) {
//			System.err.println("----in country_id");
//			ra.addAttribute("msg","Please Enter Country Name.");
//			return new ModelAndView("redirect:DegreeRecognitionListurl");
//			
//		}
//		if (university_name == null || university_name.trim().equals("")) {
//			ra.addAttribute("msg", "Please Select University Name.");
//			return new ModelAndView("redirect:DegreeRecognitionListurl");
//		}
//		if (college_name == null || college_name.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter College Name.");
//			return new ModelAndView("redirect:UniversityIndiaUrl");
//		}
//		if (abbreviation == null || abbreviation.trim().equals("0")) {
//			ra.addAttribute("msg", "Please Enter Abbreviation.");
//			return new ModelAndView("redirect:UniversityIndiaUrl");
//		}
//		if (medical_course_name == null || medical_course_name.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter medical_course_name.");
//			return new ModelAndView("redirect:UniversityIndiaUrl");
//		}
//		if (validity_period == null || validity_period.trim().equals("0")) {
//			ra.addAttribute("msg", "Please Enter validity_period.");
//			return new ModelAndView("redirect:UniversityIndiaUrl");
//		}
//		if (digital_code == null || digital_code.trim().equals("0")) {
//			ra.addAttribute("msg", "Please Enter digital_code.");
//			return new ModelAndView("redirect:UniversityIndiaUrl");
//		}
		
		
		int id = td.getId() > 0 ? td.getId() : 0;

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Date s_to = null;

			s_to = format.parse(validity_period);
			
			Long c = (Long) sessionHQL.createQuery(
					"select count(id) from  DG_REC_DEGREE_RECOGNITION_LIST_DRL where Upper(applicant_name)=:applicant_name and country_name=:country_name and university_name=:university_name and "
					+ "college_name=:college_name  and Upper(abbreviation)=:abbreviation and medical_course_name=:medical_course_name and validity_period=:validity_period and digital_code=:digital_code")
					.setParameter("applicant_name", applicant_name)
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
					String msg = Udao.updatedegreerecognitionlistA(td);
					
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
		return new ModelAndView("redirect:DegreeRecognitionListurl");
	}
	

	
	@PostMapping("/getFilter_degree_recognition_list_data")

	public @ResponseBody List<Map<String, Object>> getFilterRegistration_dataa(int startPage, int pageLength,

			String Search, String orderColunm, String orderType, String applicant_name,String country_name, String university_name, String college_name, String abbreviation, String medical_course_name , String validity_period, String digital_code) {

		return Udao.DataTableDegreeRecognitionListDataLista(startPage, pageLength, Search, orderColunm, orderType, applicant_name, country_name, university_name, college_name, abbreviation, medical_course_name, validity_period, digital_code);

	}
	
	@PostMapping("/getTotal_degree_recognition_list_dataCount")
	public @ResponseBody long getTotalRegistration_dataCounta(HttpSession sessionUserId,String Search, String applicant_name,String country_name, String university_name, String college_name, String abbreviation, String medical_course_name, String validity_period, String digital_code) 
    {
		return Udao.DataTableDegreeRecognitionListDataTotalCounta(Search, applicant_name,country_name, university_name, college_name, abbreviation, medical_course_name, validity_period, digital_code);
		
	}
	@RequestMapping(value = "/degree_recognition_list_deletea", method = RequestMethod.POST)
	public ModelAndView degree_recognition_list_deletea(@ModelAttribute("id33") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {
			
		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {
			
			int app = session.createQuery(
					"delete from DG_REC_DEGREE_RECOGNITION_LIST_DRL where id=:id")
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
		return new ModelAndView("redirect:DegreeRecognitionListurl");
	}


	
	/////////////////////////////////// DLR FORM B(Medical In India) Open Page URL  /////////////////////////////////
	
	@RequestMapping(value = "/recognitionmedicalqualificationinindiaurl", method = RequestMethod.GET)

	public ModelAndView recognitionmedicalqualificationinindiaurl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			
			Mmap.addAttribute("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("recognitionmedicalqualificationinindiaurl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		Mmap.put("msg", msg);
		Mmap.put("getUniversityList", getUniversityList());
		Mmap.put("getInstituteListbyUserID", UDdao.getInstituteListbyUserID());
		Mmap.put("getMedUniversityName", common.getMedUniversityName(sessionFactory));
		Mmap.put("getMedicalList", getMedicalList());
		return new ModelAndView("Recognition_Medical_Quali_In_India_Tiles", "Degree_Recognition_List_DRL_CMD", new DG_REC_RECOGNITION_MEDICAL_QUALI_IN_INDIA());

	}
	
	@RequestMapping(value = "/getInstituteUrlmedi", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_INSTITUTE_REGISTRATION> getInstituteUrlmedi(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "selval", required = false) Integer selval, HttpServletRequest request) {
		System.err.println("selval------>>>"+selval);

		List<EDU_LMS_INSTITUTE_REGISTRATION> list = UDdao.getuniversitylistUrl(selval);

		return list;
	}
	
	@RequestMapping(value = "/getAbbreviationListme", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getAbbreviationListme(ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String institute_id) {
	       return Udao.getAbbreviationdaome(institute_id);
	       
     }
	
							////////////////////////// In India save /////////////////
	
	@PostMapping(value = "/Reco_Med_Qua_In_India_Action")
	public ModelAndView Reco_Med_Qua_In_India_Action(@Validated @ModelAttribute("Reco_Med_Qua_In_India_CMD") DG_REC_RECOGNITION_MEDICAL_QUALI_IN_INDIA td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws Exception {
		//SECURITY ABHISHEK
				if(request.getHeader("Referer") == null ) { 
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("recognitionmedicalqualificationinindiaurl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
		 
		Date date = new Date();
     	String username = principal.getName();
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
	    String eid = request.getParameter("id");
	     
	    String university_name = request.getParameter("university_name");
	    System.out.println("----university_name-----"+university_name);
	    String college_id = request.getParameter("college_id");
	    System.out.println("----college_id-----"+college_id);
	    String medical_course_name = request.getParameter("medical_course_name");
	    System.out.println("----medical_course_name-----"+medical_course_name);
	    String abbreviation = request.getParameter("abbreviation");
	    System.out.println("----abbreviation-----"+abbreviation);
		String remarks = request.getParameter("remarks");

//		if (university_name == null || university_name.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter University Name.");
//			return new ModelAndView("redirect:recognitionmedicalqualificationinindiaurl");
//		}
//		if (college_id == null || college_id.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter College Name.");
//			return new ModelAndView("redirect:recognitionmedicalqualificationinindiaurl");
//		}
//		if (abbreviation == null || abbreviation.trim().equals("0")) {
//			ra.addAttribute("msg", "Please Enter Abbreviation.");
//			return new ModelAndView("redirect:recognitionmedicalqualificationinindiaurl");
//		}
//		if (medical_course_name == null || medical_course_name.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter medical_course_name.");
//			return new ModelAndView("redirect:recognitionmedicalqualificationinindiaurl");
//		}
//		if (remarks == null || remarks.trim().equals("0")) {
//			ra.addAttribute("msg", "Please Enter Remarks.");
//			return new ModelAndView("redirect:recognitionmedicalqualificationinindiaurl");
//		}
		
		
		int id = td.getId() > 0 ? td.getId() : 0;
		String userid = session.getAttribute("userId_for_jnlp").toString();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Date s_to = null;
			Long c = (Long) sessionHQL.createQuery(
					
					"select count(id) from  DG_REC_RECOGNITION_MEDICAL_QUALI_IN_INDIA where university_name=:university_name and "
					+ "college_id=:college_id and medical_course_name=:medical_course_name  and abbreviation=:abbreviation and  remarks=:remarks")

					.setParameter("university_name", Integer.parseInt(university_name))
					.setParameter("college_id", Integer.parseInt(college_id))
					.setParameter("medical_course_name", Integer.parseInt(medical_course_name))
					.setParameter("abbreviation", abbreviation)
					.setParameter("remarks", remarks)
					.uniqueResult();
			
			System.err.println("c---"+c+"---s_to---"+s_to);
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
				td.setUniversity_name(Integer.parseInt(university_name));
				td.setCollege_id(Integer.parseInt(college_id));
				td.setAbbreviation(abbreviation);
				td.setMedical_course_name(Integer.parseInt(medical_course_name));
				td.setRemarks(remarks);
				td.setModified_by(username);
				td.setModified_date(date);
				if (c == 0) {
					td.setId(id);
				String msg = Udao.updaterecmedquainindia(td);
					
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

		return new ModelAndView("redirect:recognitionmedicalqualificationinindiaurl");
	}
	
	@PostMapping("/getFilter_rec_med_qua_in_ind_data")
	public @ResponseBody List<Map<String, Object>> getFilter_rec_med_qua_in_ind_data(int startPage,
			int pageLength, String Search, String orderColunm, String orderType, String university_name,
			String college_id, String abbreviation,String medical_course_name,  String remarks) {

		return Udao.DataTableRecMedQuaInIndiaDataList(startPage, pageLength, Search, orderColunm,
				orderType,university_name, college_id,  abbreviation, medical_course_name,remarks);
	}
	@PostMapping("/getTotal_rec_med_qua_in_ind_dataCount")
	public @ResponseBody long getTotal_rec_med_qua_in_ind_dataCount(HttpSession sessionUserId, String Search,
			String university_name, String college_id,  String abbreviation,String medical_course_name, String remarks) {
		
		return Udao.DataTableRecMedQuaInIndiaDataTotalCount(Search, university_name, college_id,
				 abbreviation, medical_course_name,remarks);
	}
	
	
	@RequestMapping(value = "/deleterec_medical_quali_in_india", method = RequestMethod.POST)
	public ModelAndView deleterec_medical_quali_in_india(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {

			int app = session.createQuery(
					"delete from DG_REC_RECOGNITION_MEDICAL_QUALI_IN_INDIA where id=:id")
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
		return new ModelAndView("redirect:recognitionmedicalqualificationinindiaurl");
	}
	
/////////////////////////////////// DLR FORM C Open Page URL  /////////////////////////////////
	
	@RequestMapping(value = "/recognitionmedicalqualificationoutindiaurl", method = RequestMethod.GET)

	public ModelAndView recognitionmedicalqualificationoutindiaurl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		//SECURITY ABHISHEK
		if(request.getHeader("Referer") == null ) { 
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("recognitionmedicalqualificationoutindiaurl", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			Mmap.put("msg", msg);
			Mmap.put("getUniversityList", getUniversityList());
			Mmap.put("getInstituteListbyUserID", UDdao.getInstituteListbyUserID());
			Mmap.put("getMedUniversityName", common.getMedUniversityName(sessionFactory));
			Mmap.put("getMedicalList", getMedicalList());
			
		return new ModelAndView("Recognition_Medical_Quali_Out_India_Tiles", "Reco_Med_Qua_Out_India_CMD", new DG_REC_RECOGNITION_MEDICAL_QUALI_OUT_INDIA());

	}
	@RequestMapping(value = "/getInstituteUrlmedic", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_INSTITUTE_REGISTRATION> getInstituteUrlmedic(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "selval", required = false) Integer selval, HttpServletRequest request) {
		System.err.println("selval------>>>"+selval);

		List<EDU_LMS_INSTITUTE_REGISTRATION> list = UDdao.getuniversitylistUrl(selval);

		return list;
	}
	@RequestMapping(value = "/getAbbreviationListmeC", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getAbbreviationListmeC(ModelMap Mmap,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,String institute_id) {
	       return Udao.getAbbreviationdaome(institute_id);
	       
     }
	
	
	@PostMapping(value = "/Reco_Med_Qua_Out_India_Action")
	public ModelAndView Reco_Med_Qua_Out_India_Action(@Validated @ModelAttribute("Reco_Med_Qua_Out_India_CMD") DG_REC_RECOGNITION_MEDICAL_QUALI_OUT_INDIA td, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws Exception {
		//SECURITY ABHISHEK
				if(request.getHeader("Referer") == null ) { 
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("recognitionmedicalqualificationinindiaurl", roleid1);		
					if(val == false) {
						return new ModelAndView("AccessTiles");
				}
		 
		Date date = new Date();
     	String username = principal.getName();
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
	    String eid = request.getParameter("id");
	     
	    String university_name = request.getParameter("university_name");
	    System.out.println("----university_name-----"+university_name);
	    String college_id = request.getParameter("college_id");
	    System.out.println("----college_id-----"+college_id);
	    String medical_course_name = request.getParameter("medical_course_name");
	    System.out.println("----medical_course_name-----"+medical_course_name);
	    String abbreviation = request.getParameter("abbreviation");
	    System.out.println("----abbreviation-----"+abbreviation);
		String remarks = request.getParameter("remarks");

//		if (university_name == null || university_name.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter University Name.");
//			return new ModelAndView("redirect:recognitionmedicalqualificationinindiaurl");
//		}
//		if (college_id == null || college_id.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter College Name.");
//			return new ModelAndView("redirect:recognitionmedicalqualificationinindiaurl");
//		}
//		if (abbreviation == null || abbreviation.trim().equals("0")) {
//			ra.addAttribute("msg", "Please Enter Abbreviation.");
//			return new ModelAndView("redirect:recognitionmedicalqualificationinindiaurl");
//		}
//		if (medical_course_name == null || medical_course_name.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter medical_course_name.");
//			return new ModelAndView("redirect:recognitionmedicalqualificationinindiaurl");
//		}
//		if (remarks == null || remarks.trim().equals("0")) {
//			ra.addAttribute("msg", "Please Enter Remarks.");
//			return new ModelAndView("redirect:recognitionmedicalqualificationinindiaurl");
//		}
		
		
		int id = td.getId() > 0 ? td.getId() : 0;
		String userid = session.getAttribute("userId_for_jnlp").toString();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Date s_to = null;
			Long c = (Long) sessionHQL.createQuery(
					
					"select count(id) from  DG_REC_RECOGNITION_MEDICAL_QUALI_OUT_INDIA where university_name=:university_name and "
					+ "college_id=:college_id and medical_course_name=:medical_course_name  and abbreviation=:abbreviation and  remarks=:remarks")

					.setParameter("university_name", Integer.parseInt(university_name))
					.setParameter("college_id", Integer.parseInt(college_id))
					.setParameter("medical_course_name", Integer.parseInt(medical_course_name))
					.setParameter("abbreviation", abbreviation)
					.setParameter("remarks", remarks)
					.uniqueResult();
			
			System.err.println("c---"+c+"---s_to---"+s_to);
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
				td.setUniversity_name(Integer.parseInt(university_name));
				td.setCollege_id(Integer.parseInt(college_id));
				td.setAbbreviation(abbreviation);
				td.setMedical_course_name(Integer.parseInt(medical_course_name));
				td.setRemarks(remarks);
				td.setModified_by(username);
				td.setModified_date(date);
				if (c == 0) {
					td.setId(id);
				String msg = Udao.updaterecmedquaoutindia(td);
					
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

		return new ModelAndView("redirect:recognitionmedicalqualificationoutindiaurl");
	}

	
	@PostMapping("/getFilter_rec_med_qua_out_ind_data")
	public @ResponseBody List<Map<String, Object>> getFilter_rec_med_qua_out_ind_data(int startPage,
			int pageLength, String Search, String orderColunm, String orderType, String university_name,
			String college_id, String abbreviation,String medical_course_name,  String remarks) {

		return Udao.DataTableRecMedQuaOutIndiaDataList(startPage, pageLength, Search, orderColunm,
				orderType,university_name, college_id,  abbreviation, medical_course_name,remarks);
	}
	@PostMapping("/getTotal_rec_med_qua_out_ind_dataCount")
	public @ResponseBody long getTotal_rec_med_qua_out_ind_dataCount(HttpSession sessionUserId, String Search,
			String university_name, String college_id,  String abbreviation,String medical_course_name, String remarks) {
		
		return Udao.DataTableRecMedQuaOutIndiaDataTotalCount(Search, university_name, college_id,
				 abbreviation, medical_course_name,remarks);
	}
	
	
	@RequestMapping(value = "/deleterec_medical_quali_out_india", method = RequestMethod.POST)
	public ModelAndView deleterec_medical_quali_out_india(@ModelAttribute("id1") int id, BindingResult result, RedirectAttributes ra,
			HttpServletRequest request, ModelMap model, HttpSession session1) {

		List<String> liststr = new ArrayList<String>();

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {

			int app = session.createQuery(
					"delete from DG_REC_RECOGNITION_MEDICAL_QUALI_OUT_INDIA where id=:id")
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
		return new ModelAndView("redirect:recognitionmedicalqualificationinindiaurl");
	}
}
