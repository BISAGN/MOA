package com.AyushEdu.controller.Registration.Graduation_NCISM;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
import com.AyushEdu.Models.Registration.Graduation_NCISM.EDU_NCISM_REG_GRADU_PERSONAL_DTLS;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Registration.Graduation_NCISM.PersonaldetailsNCISMDAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class PersonalDetailsNCISM_Controller {
	
	@Autowired
	private PersonaldetailsNCISMDAO da;
	
	@Autowired
	ValidationController validation;
	
	@Autowired
	CommonController common;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private SessionFactory sessionFactory;
	

	@RequestMapping(value = "/Personal_Details_Ncism_Url",method = RequestMethod.GET)
	public ModelAndView Personal_Details_Ncism_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,Principal principal,RedirectAttributes ra)  {
		try {
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Personal_Details_Ncism_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			Long v = 0L;
			Integer check=0;
			
//			if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
//			 Mmap.addAttribute("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("Personal_Details_Ncism_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
			Session sessionHQL = this.sessionFactory.openSession();
			
			String username = principal.getName();
			String userId = session.getAttribute("userId_for_jnlp").toString();
			
			ArrayList<ArrayList<String>> listp= da.get_p_id_Ncism_pers_info_data(Integer.parseInt(userId));
			System.err.println(listp);
			if (listp.size()>0) {
				Query qry = sessionHQL.createQuery("select status from EDU_NCISM_REG_GRADU_PERSONAL_DTLS where p_id=:p_id");
				qry.setParameter("p_id", Integer.parseInt(userId));
				check = (Integer) qry.uniqueResult();
				System.err.println("check =     "+check);
			}
			if (check.equals(1)) {
				ra.addAttribute("ch_eid", listp.get(0).get(0));
				return new ModelAndView("redirect:NCISM_Std_details_view_Url");
			}else {
			
			//Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
//			Mmap.put("getAcademicList", common.getAcademicList(sessionFactory));
			Mmap.addAttribute("pddata",da.getAllNcism_Persdetails(Integer.parseInt(userId)));
			Mmap.addAttribute("userid",da.getNcism_Username(username));
			Mmap.put("getgenderList", common.getgenderList(sessionFactory));
			Mmap.put("getMedCountryName", common.getMedCountryName(sessionFactory));
			Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
//			Mmap.put("getMedDistrictName", common.getMedDistrictName(sessionFactory));
			Mmap.put("getcategorylist", common.getcategoryList(sessionFactory));
			Mmap.put("getreligionListdata", common.getreligionList(sessionFactory));
			Mmap.put("getmsList", common.getmaritalstatusList(sessionFactory));
			//Mmap.addAttribute("basicdata",da.getBesicdetails(Integer.parseInt(userId)));
			Mmap.put("getPrefixList", common.getPrefixList(sessionFactory));
			
			Mmap.put("getQuotaList", common.getQuotaList(sessionFactory));
			Mmap.put("getCounselingAuthoList", common.getCounselingAuthoList(sessionFactory));
			
			Mmap.addAttribute("msg", msg);
			} 
		}catch (Exception e) {
				e.printStackTrace();
			}
		
		return new ModelAndView("Personal_Details_Ncism_Tiles");
	}
	
	@PostMapping(value = "/personal_details_Ncism_Action")
	public ModelAndView personal_details_Ncism_Action(
			@Validated @ModelAttribute("personal_details_Ncism_CMD") EDU_NCISM_REG_GRADU_PERSONAL_DTLS td,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws ParseException {
		
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Personal_Details_Ncism_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		String username = principal.getName();
		String userId = session.getAttribute("userId_for_jnlp").toString();
	
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
		try {
			String pers_name = request.getParameter("pers_name");
			String pers_father_name = request.getParameter("pers_father_name");
			String pers_mother_name = request.getParameter("pers_mother_name");
			String pers_date_of_birth =  request.getParameter("pers_date_of_birth");
			Date dob_i = null;
			if (!request.getParameter("pers_date_of_birth").equals("")) {
				dob_i = formatter1.parse(request.getParameter("pers_date_of_birth"));
			} else {
				dob_i = null;
			}
			 String yrr	= request.getParameter("yrr");
			String pers_gender = request.getParameter("pers_gender");
			String pers_mob_no = request.getParameter("pers_mob_no");
			String pers_email = request.getParameter("pers_email");
			String hemail = request.getParameter("hemail");
			
			String pers_category = request.getParameter("pers_category");
			String pers_religion = request.getParameter("pers_religion");
			String pers_marital_status = request.getParameter("pers_marital_status");
			String pers_nationality = request.getParameter("pers_nationality");
			String state_id = request.getParameter("state_id");
			String district_id = request.getParameter("district_id");
			String village = request.getParameter("village");
			String pers_aadhar_no1 = request.getParameter("pers_aadhar_no1");
			String pers_aadhar_no2 = request.getParameter("pers_aadhar_no2");
			String pers_aadhar_no3 = request.getParameter("pers_aadhar_no3");
			
			
			String quota_id = request.getParameter("quota_id");
			String counselling_authority = request.getParameter("counselling_authority");
			
			String pers_aadhar_no = pers_aadhar_no1+pers_aadhar_no2+pers_aadhar_no3;
			String pers_permanent_house_no = request.getParameter("pers_permanent_house_no");
			String pers_permanent_village = request.getParameter("pers_permanent_village");
			String pers_permanent_postoffice = request.getParameter("pers_permanent_postoffice");
			String pers_permanent_tehsil = request.getParameter("pers_permanent_tehsil");
			String pers_permanent_policestation = request.getParameter("pers_permanent_policestation");
			String pers_permanent_district = request.getParameter("pers_permanent_district");
			String pers_permanent_state = request.getParameter("pers_permanent_state");
			String pers_permanent_pincode = request.getParameter("pers_permanent_pincode");
			String pers_permanent_lendmark = request.getParameter("pers_permanent_lendmark");
			String pers_present_house_no = request.getParameter("pers_present_house_no");
			String pers_present_village = request.getParameter("pers_present_village");
			String pers_present_postoffice = request.getParameter("pers_present_postoffice");
			String pers_present_tehsil = request.getParameter("pers_present_tehsil");
			String pers_present_policestation = request.getParameter("pers_present_policestation");
			String pers_present_district = request.getParameter("pers_present_district");
			String pers_present_state = request.getParameter("pers_present_state");
			String pers_present_pincode = request.getParameter("pers_present_pincode");
			String pers_present_lendmark = request.getParameter("pers_present_lendmark");
			String p_id = userId;
			
			String cand_prifix = request.getParameter("cand_prifix");
			String pers_father_title = request.getParameter("pers_father_title");
			String pers_mother_title = request.getParameter("pers_mother_title");
			String neet_roll_no = request.getParameter("neet_roll_no");
			String neet_application_no = request.getParameter("neet_application_no");
			
			String neet_rank = request.getParameter("neet_rank");
			String neet_marks = request.getParameter("neet_marks");
			String neet_percentile = request.getParameter("neet_percentile");
			
			String corre_house_no = request.getParameter("corre_house_no");
			String corre_village = request.getParameter("corre_village");
			String corre_postoffice = request.getParameter("corre_postoffice");
			String corre_tehsil = request.getParameter("corre_tehsil");
			String corre_policestation = request.getParameter("corre_policestation");
			String corre_district = request.getParameter("corre_district");
			String corre_state = request.getParameter("corre_state");
			String corre_pincode = request.getParameter("corre_pincode");
			String corre_lendmark = request.getParameter("corre_lendmark");
			
			
			String pers_middel_name = request.getParameter("pers_middel_name");
			
			String pers_surname = request.getParameter("pers_surname");
			String hiddenUpdate = request.getParameter("hiddenUpdate");
		
		
			if (cand_prifix == null || cand_prifix.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Name Prifix");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			
			if (pers_name == null || pers_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter First Name.");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			
			if (validation.maxlengthcheck50(pers_name) == false) {
				ra.addAttribute("msg", "First Name " + validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.isOnlyAlphabet(pers_name) == false) {
				ra.addAttribute("msg", "First Name " + validation.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			
			if (pers_middel_name == null || pers_middel_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Middle Name.");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}

			if (validation.maxlengthcheck50(pers_middel_name) == false) {
				ra.addAttribute("msg", "Middle Name " + validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			
			if (validation.isOnlyAlphabetSLASH(pers_middel_name) == false) {
				ra.addAttribute("msg", "Middle Name " + validation.isOnlyAlphabetMSGSLASH);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}

			if (pers_surname == null || pers_surname.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Surname.");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
		
			if (validation.maxlengthcheck50(pers_surname) == false) {
				ra.addAttribute("msg", "Surname" + validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.isOnlyAlphabet(pers_surname) == false) {
				ra.addAttribute("msg", "Surname" + validation.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			
			if (pers_father_name == null || pers_father_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Father's Name.");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.maxlengthcheck50(pers_father_name) == false) {
				ra.addAttribute("msg", "Father's Name " + validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.isOnlyAlphabet(pers_father_name) == false) {
				ra.addAttribute("msg", "Father's Name " + validation.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}

			if (pers_mother_name == null || pers_mother_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Mother's Name.");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.maxlengthcheck50(pers_mother_name) == false) {
				ra.addAttribute("msg", "Mother's Name " + validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.isOnlyAlphabet(pers_mother_name) == false) {
				ra.addAttribute("msg", "Mother's Name " + validation.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}

			if (pers_gender == null || pers_gender.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Gender");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}

			if (pers_date_of_birth == null || pers_date_of_birth.trim().equals("") || pers_date_of_birth.equals("DD/MM/YYYY")) {
				ra.addAttribute("msg", "Please Enter The Date Of Birth");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if(Integer.parseInt(yrr) < 17 || yrr == "" || yrr == "0"){
			    	ra.addAttribute("msg", "Age Should Be Greater Than 17 Years,pers_date_of_birth");
					return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			    }
			
		

//			if (spouse_name == null || spouse_name.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter Spouse's Name.");
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.maxlengthcheck50(spouse_name) == false) {
//				ra.addAttribute("msg", "Spouse's Name " + validation.MaxlengthcheckMSG50);
//				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
//			}
//			if (validation.isOnlyAlphabet(spouse_name) == false) {
//				ra.addAttribute("msg", "Spouse's Name " + validation.isOnlyAlphabetMSG);
//				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
//			}

			if (pers_mob_no == null || pers_mob_no.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Mobile Number");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.isOnlyNumer(pers_mob_no) == true) {
				ra.addAttribute("msg", "Mobile Number " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.maxlengthcheck10(pers_mob_no) == false) {
				ra.addAttribute("msg", "Mobile Number " + validation.MaxlengthcheckMSG10);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.isValidMobileNo(pers_mob_no) == false) {
				ra.addAttribute("msg", "Mobile Number " + validation.isOnlyMobilenumberMSG);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}

			if (pers_email == null || pers_email.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Email Address");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.maxlengthcheck70(pers_email) == false) {
				ra.addAttribute("msg", "Email Address " + validation.MaxlengthcheckMSG70);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			
			if (pers_category == null || pers_category.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Category");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			
			if (pers_religion == null || pers_religion.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Religion");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			
			if (pers_marital_status == null || pers_marital_status.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Marital Status");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			
			if (pers_nationality == null || pers_nationality.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Nationality");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			
			if (state_id == null || state_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select State");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			
			if (district_id == null || district_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select District");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			
			if (village == null || village.trim().equals("")) {
				ra.addAttribute("msg", "Please Select Village");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			
			if (pers_aadhar_no1 == null || pers_aadhar_no1.trim().equals("")) {
				ra.addAttribute("msg", "Enter First Four Digit Of Adharcard No");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.isOnlyNumer(pers_aadhar_no1) == true) {
				ra.addAttribute("msg", "Aadhaar No. " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.AadharNoLength(pers_aadhar_no1) == false) {
				ra.addAttribute("msg", validation.AadharNoMSG11);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.AadharNoMinLength(pers_aadhar_no1) == false) {
				ra.addAttribute("msg", validation.AadharNoMSGMinlen11);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}

			if (pers_aadhar_no2 == null || pers_aadhar_no2.trim().equals("")) {
				ra.addAttribute("msg", "Enter Second Four Digit Of Adharcard No");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.isOnlyNumer(pers_aadhar_no2) == true) {
				ra.addAttribute("msg", "Aadhaar No. " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.AadharNoLength(pers_aadhar_no2) == false) {
				ra.addAttribute("msg", validation.AadharNoMSG11);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.AadharNoMinLength(pers_aadhar_no2) == false) {
				ra.addAttribute("msg", validation.AadharNoMSGMinlen11);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}

			if (pers_aadhar_no3 == null || pers_aadhar_no3.trim().equals("")) {
				ra.addAttribute("msg", "Enter Last Four Digit Of Adharcard No");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.isOnlyNumer(pers_aadhar_no3) == true) {
				ra.addAttribute("msg", "Aadhaar No. " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.AadharNoLength(pers_aadhar_no3) == false) {
				ra.addAttribute("msg", validation.AadharNoMSG11);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.AadharNoMinLength(pers_aadhar_no3) == false) {
				ra.addAttribute("msg", validation.AadharNoMSGMinlen11);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			
//			ADD quota and councelling 
			
			if (quota_id == null || quota_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Quota");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			
			if (counselling_authority == null || counselling_authority.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Counselling Authority");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			

//			if (pan_no == null || pan_no.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter PAN No.");
//				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
//			}
//			if (validation.maxlength10(pan_no) == false) {
//				ra.addAttribute("msg", "PAN No. " + validation.MaxlengthMSG10);
//				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
//			}
//			if (validation.minlength10(pan_no) == false) {
//				ra.addAttribute("msg", "PAN No. " + validation.MinlengthMSG10);
//				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
//			}
//			if (validation.isOnlyAlphabetNumeric(pan_no) == false) {
//				ra.addAttribute("msg", "PAN No. " + validation.isOnlyAlphabetNumericMSG);
//				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
//			}

//			if (academic_quali == null || academic_quali.trim().equals("0")) {
//				ra.addAttribute("msg", "Please Select Academic Qualification");
//				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
//			}
//			// if(request.getParameter("sex") == null ||
//			// request.getParameter("sex").trim().equals("2")){
//			if (academic_quali == null || academic_quali.trim().equals("2")) {
//				if (subject == null || subject.trim().equals("")) {
//					ra.addAttribute("msg", "Please Enter Subject");
//					return new ModelAndView("redirect:Personal_Details_Ncism_Url");
//				}
//				if (validation.maxlengthcheck50(subject) == false) {
//					ra.addAttribute("msg", "Subject " + validation.MaxlengthcheckMSG50);
//					return new ModelAndView("redirect:Personal_Details_Ncism_Url");
//				}
//				if (validation.isOnlyAlphabetNumeric(subject) == false) {
//					ra.addAttribute("msg", "Subject " + validation.isOnlyAlphabetNumericMSG);
//					return new ModelAndView("redirect:Personal_Details_Ncism_Url");
//				}
//			}
			/////////// Address

			if (pers_permanent_house_no == null || pers_permanent_house_no.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Permanent House No/Name");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.isOnlyAlphabetNumberSLASH(pers_permanent_house_no) == false) {
				ra.addAttribute("msg", "Permanent House No/Name " + validation.isOnlyAlphabetNumberSLASHMSG);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.maxlengthcheck50(pers_permanent_house_no) == false) {
				ra.addAttribute("msg", "Permanent House No/Name " + validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			
//			if (per_add_line1 == null || per_add_line1.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter Permanent Address-Line-1");
//				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
//			}
//			if (validation.maxlengthcheck100(per_add_line1) == false) {
//				ra.addAttribute("msg", "Permanent Address-Line-1 " + validation.MaxlengthcheckMSG100);
//				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
//			}
//
//			if (per_add_line2 == null || per_add_line2.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter Permanent Address-Line-2");
//				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
//			}
//			if (validation.maxlengthcheck100(per_add_line2) == false) {
//				ra.addAttribute("msg", "Permanent Address-Line-2 " + validation.MaxlengthcheckMSG100);
//				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
//			}
			
			if (pers_permanent_village == null || pers_permanent_village.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Permanent City/Village");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.maxlengthcheck100(pers_permanent_village) == false) {
				ra.addAttribute("msg", "Permanent City/Village " + validation.MaxlengthcheckMSG100);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.isOnlyAlphabet(pers_permanent_village) == false) {
				ra.addAttribute("msg", "Permanent City/Village " + validation.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}

			if (pers_permanent_postoffice == null || pers_permanent_postoffice.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter the Permanent Post Office");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			   }
			if (pers_permanent_tehsil == null || pers_permanent_tehsil.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter the Permanent Tehsil");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			   }
			if (pers_permanent_policestation == null || pers_permanent_policestation.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter the Permanent Police Station");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			   }
			
			if (pers_permanent_state == null || pers_permanent_state.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select State");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (pers_permanent_district == null || pers_permanent_district.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select District");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}

			if (pers_permanent_pincode == null || pers_permanent_pincode.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Permanent Pin Code");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.maxlengthcheckpincode(pers_permanent_pincode) == false) {
				ra.addAttribute("msg", "Permanent Pin Code " + validation.MaxlengthcheckMSGpincode);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.minlengthcheckpincode(pers_permanent_pincode) == false) {
				ra.addAttribute("msg", "Permanent Pin Code " + validation.MinlengthcheckMSGpincode);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.isOnlyNumer(pers_permanent_pincode) == true) {
				ra.addAttribute("msg", "Permanent Pin Code " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (pers_permanent_lendmark == null || pers_permanent_lendmark.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter the Permanent Lendmark");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			   }

//			if (per_phn_no == null || per_phn_no.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter Permanent Phone No.");
//				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
//			}
//			if (validation.isOnlyNumer(per_phn_no) == true) {
//				ra.addAttribute("msg", "Permanent Phone No. " + validation.isOnlyNumerMSG);
//				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
//			}
//			if (validation.maxlengthcheck10(per_phn_no) == false) {
//				ra.addAttribute("msg", "Permanent Phone No. " + validation.MaxlengthcheckMSG10);
//				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
//			}
//			if (validation.isValidMobileNo(per_phn_no) == false) {
//				ra.addAttribute("msg", "Permanent Phone No. " + validation.isOnlyMobilenumberMSG);
//				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
//			}

			if (request.getParameter("check_address") == null) {
				if (pers_present_house_no == null || pers_present_house_no.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Present House No/Name.");
					return new ModelAndView("redirect:Personal_Details_Ncism_Url");
				}
//				if (validation.isOnlyNumer(pers_present_house_no) == true) {
//					ra.addAttribute("msg", "Present House No/Name. " + validation.isOnlyNumerMSG);
//					return new ModelAndView("redirect:Personal_Details_Ncism_Url");
//				}
				if (validation.isOnlyAlphabetNumberSLASH(pers_present_house_no) == false) {
					ra.addAttribute("msg", "Present House No/Name " + validation.isOnlyAlphabetNumberSLASHMSG);
					return new ModelAndView("redirect:Personal_Details_Ncism_Url");
				}
				if (validation.maxlengthcheck50(pers_present_house_no) == false) {
					ra.addAttribute("msg", "Present House No/Name " + validation.MaxlengthcheckMSG50);
					return new ModelAndView("redirect:Personal_Details_Ncism_Url");
				}
				if (pers_present_village == null || pers_present_village.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Present City/Village");
					return new ModelAndView("redirect:Personal_Details_Ncism_Url");
				}
				if (validation.maxlengthcheck100(pers_present_village) == false) {
					ra.addAttribute("msg", "Present City/Village " + validation.MaxlengthcheckMSG100);
					return new ModelAndView("redirect:Personal_Details_Ncism_Url");
				}
				if (validation.isOnlyAlphabet(pers_present_village) == false) {
					ra.addAttribute("msg", "Present City/Village " + validation.isOnlyAlphabetMSG);
					return new ModelAndView("redirect:Personal_Details_Ncism_Url");
				}
				if (pers_present_postoffice == null || pers_present_postoffice.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter the Present PostOffice");
					return new ModelAndView("redirect:Personal_Details_Ncism_Url");
				   }
				if (pers_permanent_tehsil == null || pers_permanent_tehsil.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter the Present Tehsil");
					return new ModelAndView("redirect:Personal_Details_Ncism_Url");
				   }
				if (pers_present_postoffice == null || pers_present_postoffice.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter the Present Police Station");
					return new ModelAndView("redirect:Personal_Details_Ncism_Url");
				   }
				if (pers_present_state == null || pers_present_state.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select State");
					return new ModelAndView("redirect:Personal_Details_Ncism_Url");
					}
				if (pers_present_district == null || pers_present_district.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select District");
					return new ModelAndView("redirect:Personal_Details_Ncism_Url");
				}
				if (pers_present_pincode == null || pers_present_pincode.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Present Pin Code");
					return new ModelAndView("redirect:Personal_Details_Ncism_Url");
				}
				if (validation.maxlengthcheckpincode(pers_present_pincode) == false) {
					ra.addAttribute("msg", "Present Pin Code " + validation.MaxlengthcheckMSGpincode);
					return new ModelAndView("redirect:Personal_Details_Ncism_Url");
				}
				if (validation.minlengthcheckpincode(pers_present_pincode) == false) {
					ra.addAttribute("msg", "Present Pin Code " + validation.MinlengthcheckMSGpincode);
					return new ModelAndView("redirect:Personal_Details_Ncism_Url");
				}
				if (validation.isOnlyNumer(pers_present_pincode) == true) {
					ra.addAttribute("msg", "Present Pin Code " + validation.isOnlyNumerMSG);
					return new ModelAndView("redirect:Personal_Details_Ncism_Url");
				}
			}
			//corresponding address start
			if (corre_house_no == null || corre_house_no.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Corresponding House No/Name");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.maxlengthcheck50(corre_house_no) == false) {
				ra.addAttribute("msg", "Corresponding House No/Name "+ validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.isOnlyAlphabetNumberSLASH(corre_house_no) == false) {
				ra.addAttribute("msg", "Corresponding House No/Name "+ validation.isOnlyAlphabetNumberSLASHMSG);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (corre_village == null || corre_village.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Corresponding City/Village");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.maxlengthcheck50(corre_village) == false) {
				ra.addAttribute("msg", "Corresponding City/Village "+ validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.isOnlyAlphabet(corre_village) == false) {
				ra.addAttribute("msg", "Corresponding City/Village "+ validation.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (corre_postoffice == null || corre_postoffice.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Corresponding PostOffice");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (corre_tehsil == null || corre_tehsil.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Corresponding Tehsil");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (corre_policestation == null || corre_policestation.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Corresponding Police Station");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (corre_state == null || corre_state.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Corresponding State");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (corre_district == null || corre_district.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Corresponding District");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (corre_pincode == null || corre_pincode.trim().equals("")) {
				ra.addAttribute("msg", "Please Select Corresponding Pincode");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.isOnlyNumer(corre_pincode) == true) {
				ra.addAttribute("msg", "Corresponding Pincode "+ validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.maxlengthcheckpincode(corre_pincode) == false) {
				ra.addAttribute("msg", "Corresponding Pincode "+ validation.MaxlengthcheckMSGpincode);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.minlengthcheckpincode(corre_pincode) == false) {
				ra.addAttribute("msg", "Corresponding Pincode "+ validation.MinlengthcheckMSGpincode);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			//corresponding address End
			if (neet_roll_no == null || neet_roll_no.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter NEET Roll No");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.isOnlyNumer(neet_roll_no) == true) {
				ra.addAttribute("msg", "NEET Roll No" + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.maxlengthcheckneetrollno(neet_roll_no) == false) {
				ra.addAttribute("msg", " NEET Roll No" + validation.MaxlengthcheckMSGneetrollno10);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (neet_application_no == null || neet_application_no.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter NEET Application No");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.isOnlyNumer(neet_application_no) == true) {
				ra.addAttribute("msg", "NEET Application No" + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.maxlengthcheckneetapplicationno(neet_application_no) == false) {
				ra.addAttribute("msg", " NEET Application No" + validation.MaxlengthcheckMSGneetapplicationno12);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (neet_rank == null || neet_rank.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter NEET All India Rank");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.isOnlyNumer(neet_rank) == true) {
				ra.addAttribute("msg", "NEET All India Rank" + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.maxlengthcheckneetindiarank(neet_rank) == false) {
				ra.addAttribute("msg", " NEET All India Rank " + validation.MaxlengthcheckMSGneetallindiarank7);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (neet_marks == null || neet_marks.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter NEET Marks");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.isOnlyNumer(neet_marks) == true) {
				ra.addAttribute("msg", "NEET Marks" + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (validation.maxlengthcheckneetmark(neet_marks) == false) {
				ra.addAttribute("msg", " NEET Marks " + validation.MaxlengthcheckMSGneetmark3);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
			if (neet_percentile == null || neet_percentile.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter NEET Percentile");
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}
//			if (validation.isOnlyNumer(neet_percentile) == true) {
//				ra.addAttribute("msg", "Neet Percentile" + validation.isOnlyNumerMSG);
//				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
//			}
			if (validation.maxlengthcheckneetpercentile(neet_percentile) == false) {
				ra.addAttribute("msg", " NEET Percentile " + validation.MaxlengthcheckMSGneetpercentile5);
				return new ModelAndView("redirect:Personal_Details_Ncism_Url");
			}

			        td.setCreated_by(username);
					td.setCreated_date(date);
					td.setPers_name(pers_name);
					td.setPers_father_name(pers_father_name);
					td.setPers_mother_name(pers_mother_name);
					td.setPers_date_of_birth(dob_i);
					td.setPers_gender(pers_gender);
					td.setPers_mob_no(pers_mob_no);
					td.setPers_email(hemail);
					td.setPers_category(pers_category);
					td.setPers_religion(pers_religion);
					td.setPers_marital_status(Integer.parseInt(pers_marital_status));
					td.setPers_nationality(Integer.parseInt(pers_nationality));
					td.setState_id(Integer.parseInt(state_id));
					td.setDistrict_id(Integer.parseInt(district_id));
					td.setVillage(village);
					td.setPers_aadhar_no(pers_aadhar_no);
					td.setQuota_id(Integer.parseInt(quota_id));
					td.setCounselling_authority(Integer.parseInt(counselling_authority));
					td.setPers_permanent_house_no(pers_permanent_house_no);
					td.setPers_permanent_village(pers_permanent_village);
					td.setPers_permanent_postoffice(pers_permanent_postoffice);
					td.setPers_permanent_tehsil(pers_permanent_tehsil);
					td.setPers_permanent_policestation(pers_permanent_policestation);
					td.setPers_permanent_district(pers_permanent_district);
					td.setPers_permanent_state(Integer.parseInt(pers_permanent_state));
					td.setPers_permanent_pincode(Integer.parseInt(pers_permanent_pincode));
					td.setPers_permanent_lendmark(pers_permanent_lendmark);
					td.setPers_present_house_no(pers_present_house_no);
					td.setPers_present_village(pers_present_village);
					td.setPers_present_postoffice(pers_present_postoffice);
					td.setPers_present_tehsil(pers_present_tehsil);
					td.setPers_present_policestation(pers_present_policestation);
					td.setPers_present_district(pers_present_district);
					td.setPers_present_state(Integer.parseInt(pers_present_state));
					td.setPers_present_pincode(Integer.parseInt(pers_present_pincode));
					td.setPers_present_lendmark(pers_present_lendmark);
					td.setP_id(Integer.parseInt(userId));
					td.setNeet_roll_no(neet_roll_no);
					td.setNeet_application_no(neet_application_no);
					td.setNeet_marks(Integer.parseInt(neet_marks));
					td.setNeet_rank(Integer.parseInt(neet_rank));
					td.setNeet_percentile(neet_percentile);
					td.setPers_middel_name(pers_middel_name);
					td.setPers_surname(pers_surname);
				    td.setCand_prifix(Integer.parseInt(cand_prifix));	
				    
					td.setCorre_house_no(corre_house_no);
					td.setCorre_village(corre_village);
					td.setCorre_postoffice(corre_postoffice);
					td.setCorre_tehsil(corre_tehsil);
					td.setCorre_policestation(corre_policestation);
					td.setCorre_district(corre_district);
					td.setCorre_state(Integer.parseInt(corre_state));
					td.setCorre_pincode(Integer.parseInt(corre_pincode));
					td.setCorre_lendmark(corre_lendmark);
					td.setStatus(Integer.parseInt("0"));
				    
//				    td.setPers_father_title(Integer.parseInt(pers_father_title));
//				    td.setPers_mother_title(Integer.parseInt(pers_mother_title));
					//int reg_id = (int)
					int reg_id=(int) sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					
					//Query q2 = sessionHQL.createQuery("select userId from UserLogin Where userName=:userName").setParameter("userName", username);
					//@SuppressWarnings("unchecked")
					//List<Integer> list2 = (List<Integer>) q2.list();				
				//	int lid = list2.get(0);
					//String reg_id_u_msg = da.UpdateRegid(reg_id,lid);
				 
			     	tx.commit();
					
					//if(reg_id_u_msg.equals("Data Updated Successfully.")) {
					
						//ra.addAttribute("msg", "Data Saved Successfully.");
					//}
						
						if (reg_id > 0) {
							ra.addAttribute("msg", "Data Saved Successfully.");
							ra.addAttribute("eid", reg_id);
						}
						
					
					} catch (RuntimeException e) {
						try {
							ra.addAttribute("msg", "roll back transaction");
						} catch (RuntimeException rbe) {
							ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
						}
						throw e;
					} finally {
						if (sessionHQL != null) {
							sessionHQL.close();
						}
					}
				return new ModelAndView("redirect:Edu_Det_Ncism_Url");
				}
	
//	 @RequestMapping(value = "/getNcism_Personaldetails", method = RequestMethod.POST)
//	 	public @ResponseBody ArrayList<ArrayList<String>> getNcism_Personaldetails(int userid,HttpSession session) {
//	    	ArrayList<ArrayList<String>> data = da.getNcism_Personaldetails(userid, session);
//	    	return data;
//	 	}
	 
	 @RequestMapping(value = "/getNcism_Besicdetails_ctrl", method = RequestMethod.POST)
	 	public @ResponseBody ArrayList<ArrayList<String>> getNcism_Besicdetails_ctrl(int userid,HttpSession session) {
	    	ArrayList<ArrayList<String>> data = da.getBesicNcism_details(userid);
	    	return data;
	 	}
	 
	 @RequestMapping(value = "/get_p_id_Ncism_pers_info_ctrl", method = RequestMethod.POST)
	 	public @ResponseBody ArrayList<ArrayList<String>> get_p_id_Ncism_pers_info_ctrl(int userid) {
	    	ArrayList<ArrayList<String>> data = da.get_p_id_Ncism_pers_info_data(userid);
	    	return data;
	 	}
	 
	 @RequestMapping(value = "/get_ayush_id_Ncism_ctrl", method = RequestMethod.POST)
	 	public @ResponseBody ArrayList<ArrayList<String>> get_ayush_id_Ncism_ctrl(String userid) {
	    	ArrayList<ArrayList<String>> data = da.get_ayush_id_Ncism_data(userid);
	    	return data;
	 	}
	     
	     @RequestMapping(value = "/getUpdateNcism_PerDetails", method = RequestMethod.POST)
		 	public @ResponseBody String getUpdateNcism_PerDetails(String e_id,HttpServletRequest request, Principal principal) throws ParseException {
	    	 
	    	 
	    	 String msg ="";
	    	 String username = principal.getName();
	    	 Date date = new Date();
	    	 Session sessionHQL = this.sessionFactory.openSession();
	    	 DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
	    	 String pers_name = request.getParameter("pers_name");
	    	 String pers_father_name = request.getParameter("pers_father_name");
	    	 String pers_mother_name = request.getParameter("pers_mother_name");
	    	 String pers_date_of_birth	= request.getParameter("pers_date_of_birth");
	    	 
	    	 Date dob_i = null;
				if (!request.getParameter("pers_date_of_birth").equals("")) {
					dob_i = formatter1.parse(request.getParameter("pers_date_of_birth"));
				} else {
					dob_i = null;
				}
			 String yrr	= request.getParameter("yrr");
				
	    	 String pers_gender = request.getParameter("pers_gender");
	    	 String pers_mob_no = request.getParameter("pers_mob_no");
	    	 String pers_email = request.getParameter("pers_email");
	    	 String pers_category = request.getParameter("pers_category");
	    	 String pers_religion = request.getParameter("pers_religion");
	    	 String pers_marital_status = request.getParameter("pers_marital_status");
	    	 String pers_nationality = request.getParameter("pers_nationality");
	    	 String state_id = request.getParameter("state_id");
	    	 String district_id = request.getParameter("district_id");
	    	 String village = request.getParameter("village");
	    	 
	    	 
	    	 	String pers_aadhar_no1 = request.getParameter("pers_aadhar_no1");
				String pers_aadhar_no2 = request.getParameter("pers_aadhar_no2");
				String pers_aadhar_no3 = request.getParameter("pers_aadhar_no3");
				
				
//				add quota and councelling
				
				 String quota_id = request.getParameter("quota_id");
		    	 String counselling_authority = request.getParameter("counselling_authority");
				
					    	 
			// String pers_aadhar_no = request.getParameter("pers_aadhar_no");
	    	 String pers_permanent_house_no = request.getParameter("pers_permanent_house_no");
	    	 String pers_permanent_village = request.getParameter("pers_permanent_village");
	    	 String pers_permanent_postoffice = request.getParameter("pers_permanent_postoffice");
	    	 String pers_permanent_tehsil = request.getParameter("pers_permanent_tehsil");
	    	 String pers_permanent_policestation = request.getParameter("pers_permanent_policestation");
	    	 String pers_permanent_district = request.getParameter("pers_permanent_district");
	    	 String pers_permanent_state = request.getParameter("pers_permanent_state");
	    	 String pers_permanent_pincode = request.getParameter("pers_permanent_pincode");
	    	 String pers_permanent_lendmark = request.getParameter("pers_permanent_lendmark");
	    	 String pers_present_house_no = request.getParameter("pers_present_house_no");
	    	 String pers_present_village = request.getParameter("pers_present_village");
	    	 String pers_present_postoffice = request.getParameter("pers_present_postoffice");
	    	 String pers_present_tehsil = request.getParameter("pers_present_tehsil");
	    	 String pers_present_policestation = request.getParameter("pers_present_policestation");
	    	 String pers_present_district = request.getParameter("pers_present_district");
	    	 String pers_present_state = request.getParameter("pers_present_state");
	    	 String pers_present_pincode = request.getParameter("pers_present_pincode");
	    	 String pers_present_lendmark = request.getParameter("pers_present_lendmark");
	    	 
	    	    String cand_prifix = request.getParameter("cand_prifix");
				String pers_father_title = request.getParameter("pers_father_title");
				String pers_mother_title = request.getParameter("pers_mother_title");
				
				
				String neet_roll_no = request.getParameter("neet_roll_no");
				String neet_application_no = request.getParameter("neet_application_no");
				String neet_rank = request.getParameter("neet_rank");
				String neet_marks = request.getParameter("neet_marks");
				String neet_percentile = request.getParameter("neet_percentile");
				
				 String corre_house_no = request.getParameter("corre_house_no");
		    	 String corre_village = request.getParameter("corre_village");
		    	 String corre_postoffice = request.getParameter("corre_postoffice");
		    	 String corre_tehsil = request.getParameter("corre_tehsil");
		    	 String corre_policestation = request.getParameter("corre_policestation");
		    	 String corre_district = request.getParameter("corre_district");
		    	 String corre_state = request.getParameter("corre_state");
		    	 String corre_pincode = request.getParameter("corre_pincode");
		    	 String corre_lendmark = request.getParameter("corre_lendmark");
				
				String pers_middel_name = request.getParameter("pers_middel_name");
				String pers_surname = request.getParameter("pers_surname");
				
				if (cand_prifix == null || cand_prifix.trim().equals("0")) {
					msg = "Please Select Name Prifix,cand_prifix";
					return msg;
				}

				if (pers_name == null || pers_name.trim().equals("")) {
					msg = "Please Enter First Name,pers_name";
					return msg;
				}
				if (validation.maxlengthcheck50(pers_name) == false) {
					msg = "First Name " + validation.MaxlengthcheckMSG50 + ",pers_name";
					return msg;
				}
				if (validation.isOnlyAlphabet(pers_name) == false) {
					msg = "First Name " + validation.isOnlyAlphabetMSG + ",pers_name";
					return msg;
				}

				if (pers_middel_name == null || pers_middel_name.trim().equals("")) {
					msg = "Please Enter Middel Name,pers_middel_name";
					return msg;
				}

				if (validation.maxlengthcheck50(pers_middel_name) == false) {
					msg = "Middle Name " + validation.MaxlengthcheckMSG50 + ",pers_middel_name";
					return msg;
				}
				
				if (validation.isOnlyAlphabetSLASH(pers_middel_name) == false) {
					msg = "Middle Name " + validation.isOnlyAlphabetMSGSLASH + ",pers_middel_name";
					return msg;
				}

				if (pers_surname == null || pers_surname.trim().equals("")) {
					msg = "Please Enter Surname,pers_surname";
					return msg;
				}

				if (validation.maxlengthcheck50(pers_surname) == false) {
					msg = "Surname " + validation.MaxlengthcheckMSG50 + ",pers_surname";
					return msg;
				}
				
				if (validation.isOnlyAlphabet(pers_surname) == false) {
					msg = "Surname " + validation.isOnlyAlphabetMSG + ",pers_surname";
					return msg;
				}

				if (pers_father_name == null || pers_father_name.trim().equals("")) {
					msg = "Please Enter Father's Name,pers_father_name";
					return msg;
				}
				if (validation.maxlengthcheck50(pers_father_name) == false) {
					msg = "Father's Name " + validation.MaxlengthcheckMSG50 + ",pers_father_name";
					return msg;
				}
				if (validation.isOnlyAlphabet(pers_father_name) == false) {
					msg = "Father's Name " + validation.isOnlyAlphabetMSG + ",pers_father_name";
					return msg;
				}

				if (pers_mother_name == null || pers_mother_name.trim().equals("")) {
					msg = "Please Enter Mother's Name,pers_mother_name";
					return msg;
				}
				if (validation.maxlengthcheck50(pers_mother_name) == false) {
					msg = "Mother's Name " + validation.MaxlengthcheckMSG50 + ",pers_mother_name";
					return msg;
				}
				if (validation.isOnlyAlphabet(pers_mother_name) == false) {
					msg = "Mother's Name " + validation.isOnlyAlphabetMSG + ",pers_mother_name";
					return msg;
				}

				if (pers_gender == null || pers_gender.trim().equals("0")) {
					msg = "Please Select Gender,pers_gender";
					return msg;
				}

				if (pers_date_of_birth == null || pers_date_of_birth.trim().equals("")
						|| pers_date_of_birth.equals("DD/MM/YYYY")) {
					msg = "Please Enter The Date Of Birth,pers_date_of_birth";
					return msg;
				}
				
				 if(Integer.parseInt(yrr) < 17 || yrr == "" || yrr == "0"){
					 msg="Age Should Be Greater Than 17 Years,pers_date_of_birth";
				    	return msg;
				    }
				
				

//				if (spouse_name == null || spouse_name.trim().equals("")) {
//					ra.addAttribute("msg", "Please Enter Spouse's Name.");
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//				if (validation.maxlengthcheck50(spouse_name) == false) {
//					ra.addAttribute("msg", "Spouse's Name " + validation.MaxlengthcheckMSG50);
//					return new ModelAndView("redirect:Personal_Details_Ncism_Url");
//				}
//				if (validation.isOnlyAlphabet(spouse_name) == false) {
//					ra.addAttribute("msg", "Spouse's Name " + validation.isOnlyAlphabetMSG);
//					return new ModelAndView("redirect:Personal_Details_Ncism_Url");
//				}

				if (pers_mob_no == null || pers_mob_no.trim().equals("")) {
					msg = "Please Enter Mobile Number,pers_mob_no";
					return msg;
				}
				if (validation.isOnlyNumer(pers_mob_no) == true) {
					msg = "Mobile Number " + validation.isOnlyNumerMSG + ",pers_mob_no";
					return msg;

				}
				if (validation.maxlengthcheck10(pers_mob_no) == false) {
					msg = "Mobile Number " + validation.MaxlengthcheckMSG10 + ",pers_mob_no";
					return msg;
				}
				if (validation.isValidMobileNo(pers_mob_no) == false) {
					msg = "Mobile Number " + validation.isOnlyMobilenumberMSG + ",pers_mob_no";
					return msg;
				}

				if (pers_email == null || pers_email.trim().equals("")) {
					msg = "Please Enter Email Address,pers_email";
					return msg;
				}
				if (validation.maxlengthcheck70(pers_email) == false) {
					msg = "Email Address " + validation.MaxlengthcheckMSG70 + ",pers_email";
					return msg;
				}

				if (pers_category == null || pers_category.trim().equals("0")) {
					msg = "Please Select Category,pers_category";
					return msg;
				}

				if (pers_religion == null || pers_religion.trim().equals("0")) {
					msg = "Please Select Religion,pers_religion";
					return msg;
				}

				if (pers_marital_status == null || pers_marital_status.trim().equals("0")) {
					msg = "Please Select Marital Status,pers_marital_status";
					return msg;
				}

				if (pers_nationality == null || pers_nationality.trim().equals("0")) {
					msg = "Please Select Nationality,pers_nationality";
					return msg;
				}

				if (state_id == null || state_id.trim().equals("0")) {
					msg = "Please Select State,state_id";
					return msg;
				}

				if (district_id == null || district_id.trim().equals("0")) {
					msg = "Please Select District,district_id";
					return msg;
				}

				if (village == null || village.trim().equals("")) {
					msg = "Please Enter Village,village";
					return msg;
				}

				if (pers_aadhar_no1 == null || pers_aadhar_no1.trim().equals("")) {
					msg = "Enter First Four Digit Of Adharcard No,pers_aadhar_no1";
					return msg;
				}
				if (validation.isOnlyNumer(pers_aadhar_no1) == true) {
					msg = "Aadhaar No " + validation.isOnlyNumerMSG + ",pers_aadhar_no1";
					return msg;
				}
				if (validation.AadharNoLength(pers_aadhar_no1) == false) {
					msg = "Aadhaar No " + validation.AadharNoMSG11 + ",pers_aadhar_no1";
					return msg;
				}
				if (validation.AadharNoMinLength(pers_aadhar_no1) == false) {
					msg = "Aadhaar No " + validation.AadharNoMSGMinlen11 + ",pers_aadhar_no1";
					return msg;
				}

				if (pers_aadhar_no2 == null || pers_aadhar_no2.trim().equals("")) {
					msg = "Enter Second Four Digit Of Adharcard No,pers_aadhar_no2";
					return msg;
				}
				if (validation.isOnlyNumer(pers_aadhar_no2) == true) {
					msg = "Aadhaar No " + validation.isOnlyNumerMSG + ",pers_aadhar_no2";
					return msg;
				}
				if (validation.AadharNoLength(pers_aadhar_no2) == false) {

					msg = "Aadhaar No " + validation.AadharNoMSG11 + ",pers_aadhar_no2";
					return msg;
				}
				if (validation.AadharNoMinLength(pers_aadhar_no2) == false) {
					msg = "Aadhaar No " + validation.AadharNoMSGMinlen11 + ",pers_aadhar_no2";
					return msg;
				}

				if (pers_aadhar_no3 == null || pers_aadhar_no3.trim().equals("")) {
					msg = "Enter Last Four Digit Of Adharcard No,pers_aadhar_no3";
					return msg;
				}
				if (validation.isOnlyNumer(pers_aadhar_no3) == true) {
					msg = "Aadhaar No " + validation.isOnlyNumerMSG + ",pers_aadhar_no3";
					return msg;
				}
				if (validation.AadharNoLength(pers_aadhar_no3) == false) {
					msg = "Aadhaar No " + validation.AadharNoMSG11 + ",pers_aadhar_no3";
					return msg;
				}
				if (validation.AadharNoMinLength(pers_aadhar_no3) == false) {
					msg = "Aadhaar No " + validation.AadharNoMSGMinlen11 + ",pers_aadhar_no3";
					return msg;
				}
				if (quota_id == null || quota_id.trim().equals("0")) {
					msg = "Please Select Quota,quota_id";
					return msg;
				}

				if (counselling_authority == null || counselling_authority.trim().equals("0")) {
					msg = "Please Select Counselling Authority,counselling_authority";
					return msg;
				}
				
				
//				if (pan_no == null || pan_no.trim().equals("")) {
//					ra.addAttribute("msg", "Please Enter PAN No.");
//					return new ModelAndView("redirect:Personal_Details_Ncism_Url");
//				}
//				if (validation.maxlength10(pan_no) == false) {
//					ra.addAttribute("msg", "PAN No. " + validation.MaxlengthMSG10);
//					return new ModelAndView("redirect:Personal_Details_Ncism_Url");
//				}
//				if (validation.minlength10(pan_no) == false) {
//					ra.addAttribute("msg", "PAN No. " + validation.MinlengthMSG10);
//					return new ModelAndView("redirect:Personal_Details_Ncism_Url");
//				}
//				if (validation.isOnlyAlphabetNumeric(pan_no) == false) {
//					ra.addAttribute("msg", "PAN No. " + validation.isOnlyAlphabetNumericMSG);
//					return new ModelAndView("redirect:Personal_Details_Ncism_Url");
//				}

				////// Address

				if (pers_permanent_house_no == null || pers_permanent_house_no.trim().equals("")) {
					msg = "Please Enter permanent House No/Name,pers_permanent_house_no";
					return msg;
				}

				if (validation.maxlengthcheck50(pers_permanent_house_no) == false) {
					msg = "Permanent House No/Name " + validation.MaxlengthcheckMSG50 + ",pers_permanent_house_no";
					return msg;
				}
				
				if (validation.isOnlyAlphabetNumberSLASH(pers_permanent_house_no) == false) {
					msg = "Permanent House No/Name  " + validation.isOnlyAlphabetNumberSLASHMSG + ",pers_permanent_house_no";
					return msg;
				}

//				if (per_add_line1 == null || per_add_line1.trim().equals("")) {
//					ra.addAttribute("msg", "Please Enter Permanent Address-Line-1");
//					return new ModelAndView("redirect:Personal_Details_Ncism_Url");
//				}
//				if (validation.maxlengthcheck100(per_add_line1) == false) {
//					ra.addAttribute("msg", "Permanent Address-Line-1 " + validation.MaxlengthcheckMSG100);
//					return new ModelAndView("redirect:Personal_Details_Ncism_Url");
//				}
				//
//				if (per_add_line2 == null || per_add_line2.trim().equals("")) {
//					ra.addAttribute("msg", "Please Enter Permanent Address-Line-2");
//					return new ModelAndView("redirect:Personal_Details_Ncism_Url");
//				}
//				if (validation.maxlengthcheck100(per_add_line2) == false) {
//					ra.addAttribute("msg", "Permanent Address-Line-2 " + validation.MaxlengthcheckMSG100);
//					return new ModelAndView("redirect:Personal_Details_Ncism_Url");
//				}

				if (pers_permanent_village == null || pers_permanent_village.trim().equals("")) {
					msg = "Please Enter permanent House No/Name,pers_permanent_village";
					return msg;
				}
				if (validation.maxlengthcheck50(pers_permanent_village) == false) {
					msg = "Permanent City/Village  " + validation.MaxlengthcheckMSG50 + ",pers_permanent_village";
					return msg;
				}
				if (validation.isOnlyAlphabet(pers_permanent_village) == false) {
					msg = "Permanent City/Village  " + validation.isOnlyAlphabetMSG + ",pers_permanent_village";
					return msg;
				}

				if (pers_permanent_postoffice == null || pers_permanent_postoffice.trim().equals("")) {
					msg = "Please Enter Permanent PostOffice,pers_permanent_postoffice";
					return msg;
				}

				if (pers_permanent_tehsil == null || pers_permanent_tehsil.trim().equals("")) {

					msg = "Please Enter Permanent Tehsil,pers_permanent_tehsil";
					return msg;
				}

				if (pers_permanent_policestation == null || pers_permanent_policestation.trim().equals("")) {
					msg = "Please Enter Permanent Police Station,pers_permanent_policestation";
					return msg;
				}

				if (pers_permanent_state == null || pers_permanent_state.trim().equals("0")) {
					msg = "Please Select State,pers_permanent_state";
					return msg;
				}
				if (pers_permanent_district == null || pers_permanent_district.trim().equals("0")) {
					msg = "Please Select District,pers_permanent_district";
					return msg;
				}

				if (pers_permanent_pincode == null || pers_permanent_pincode.trim().equals("")) {
					msg = "Please Enter Permanent Pin Code,pers_permanent_pincode";
					return msg;
				}
				if (validation.maxlengthcheckpincode(pers_permanent_pincode) == false) {
					msg = "Permanent Pin Code " + validation.MaxlengthcheckMSGpincode + ",pers_permanent_pincode";
					return msg;
				}
				if (validation.minlengthcheckpincode(pers_permanent_pincode) == false) {
					msg = "Permanent Pin Code " + validation.MinlengthcheckMSGpincode + ",pers_permanent_pincode";
					return msg;
				}
				if (validation.isOnlyNumer(pers_permanent_pincode) == true) {
					msg = "Permanent Pin Code " + validation.isOnlyNumerMSG + ",pers_permanent_pincode";
					return msg;
				}

				if (pers_permanent_lendmark == null || pers_permanent_lendmark.trim().equals("")) {
					msg = "Please Enter the Permanent Lendmark,pers_permanent_lendmark";
					return msg;
				}

				
				if (request.getParameter("check_address") == null) {

					if (pers_present_house_no == null || pers_present_house_no.trim().equals("")) {
						msg = "Please Enter Present House No/Name,pers_present_house_no";
						return msg;
					}
					if (validation.maxlengthcheck50(pers_present_house_no) == false) {
						msg = "Present House No/Name " + validation.MaxlengthcheckMSG50 + ",pers_present_house_no";
						return msg;

					}
					if (validation.isOnlyAlphabetNumberSLASH(pers_present_house_no) == false) {
						msg = "Present House No/Name " + validation.isOnlyAlphabetNumberSLASHMSG + ",pers_present_house_no";
						return msg;
					}

					if (pers_present_village == null || pers_present_village.trim().equals("")) {
						msg = "Please Enter Present City/Village,pers_present_village";
						return msg;
					}
					if (validation.maxlengthcheck50(pers_present_village) == false) {
						msg = "Present City/Village " + validation.MaxlengthcheckMSG50 + ",pers_present_village";
						return msg;
					}
					if (validation.isOnlyAlphabet(pers_present_village) == false) {
						msg = "Present City/Village " + validation.isOnlyAlphabetMSG + ",pers_present_village";
						return msg;
					}

					if (pers_present_postoffice == null || pers_present_postoffice.trim().equals("")) {
						msg = "Please Enter Present PostOffice,pers_present_postoffice";
						return msg;
					}
					if (pers_permanent_tehsil == null || pers_permanent_tehsil.trim().equals("")) {
						msg = "Please Enter Present Tehsil,pers_permanent_tehsil";
						return msg;
					}

					if (pers_present_policestation == null || pers_present_policestation.trim().equals("")) {
						msg = "Please Enter Present Police Station,pers_present_policestation";
						return msg;
					}

					if (pers_present_state == null || pers_present_state.trim().equals("0")) {
						msg = "Please Select State,pers_present_state";
						return msg;
					}
					if (pers_present_district == null || pers_present_district.trim().equals("0")) {
						msg = "Please Select District,pers_present_district";
						return msg;
					}

					if (pers_present_pincode == null || pers_present_pincode.trim().equals("")) {
						msg = "Please Enter Present Pin Code,pers_present_pincode";
						return msg;
					}

					if (validation.isOnlyNumer(pers_present_pincode) == true) {
						msg = "Present Pin Code " + validation.isOnlyNumerMSG + ",pers_present_pincode";
						return msg;
					}

					if (validation.maxlengthcheckpincode(pers_present_pincode) == false) {
						msg = "Present Pin Code " + validation.MaxlengthcheckMSGpincode + ",pers_present_pincode";
						return msg;
					}
					if (validation.minlengthcheckpincode(pers_present_pincode) == false) {
						msg = "Present Pin Code " + validation.MinlengthcheckMSGpincode + ",pers_present_pincode";
						return msg;
					}
					
						//corresponding address
					
					if (corre_house_no == null || corre_house_no.trim().equals("")) {
						msg = "Please Enter Corresponding House No/Name,corre_house_no";
						return msg;
					}
					if (validation.maxlengthcheck50(corre_house_no) == false) {
						msg = "Corresponding House No/Name " + validation.MaxlengthcheckMSG50 + ",corre_house_no";
						return msg;
					}
					if (validation.isOnlyAlphabetNumberSLASH(corre_house_no) == false) {
						msg = "Corresponding House No/Name " + validation.isOnlyAlphabetNumberSLASHMSG + ",corre_house_no";
						return msg;
					}
					if (corre_village == null || corre_village.trim().equals("")) {
						msg = "Please Enter Corresponding City/Village,corre_village";
						return msg;
					}
					if (validation.maxlengthcheck50(corre_village) == false) {
						msg = "Corresponding City/Village " + validation.MaxlengthcheckMSG50 + ",corre_village";
						return msg;
					}
					if (validation.isOnlyAlphabet(corre_village) == false) {
						msg = "Corresponding City/Village " + validation.isOnlyAlphabetMSG + ",corre_village";
						return msg;
					}

					if (corre_postoffice == null || corre_postoffice.trim().equals("")) {
						msg = "Please Enter Corresponding PostOffice,corre_postoffice";
						return msg;
					}
					if (corre_tehsil == null || corre_tehsil.trim().equals("")) {
						msg = "Please Enter Corresponding Tehsil,corre_tehsil";
						return msg;
					}

					if (corre_policestation == null || corre_policestation.trim().equals("")) {
						msg = "Please Enter Corresponding Police Station,corre_policestation";
						return msg;
					}

					if (corre_state == null || corre_state.trim().equals("0")) {
						msg = "Please Select State,corre_state";
						return msg;
					}
					if (corre_district == null || corre_district.trim().equals("0")) {
						msg = "Please Select District,corre_district";
						return msg;
					}

					if (corre_pincode == null || corre_pincode.trim().equals("")) {
						msg = "Please Enter Corresponding Pin Code,corre_pincode";
						return msg;
					}

					if (validation.isOnlyNumer(corre_pincode) == true) {
						msg = "Corresponding Pin Code " + validation.isOnlyNumerMSG + ",corre_pincode";
						return msg;
					}

					if (validation.maxlengthcheckpincode(corre_pincode) == false) {
						msg = "Corresponding Pin Code " + validation.MaxlengthcheckMSGpincode + ",corre_pincode";
						return msg;
					}
					if (validation.minlengthcheckpincode(corre_pincode) == false) {
						msg = "Corresponding Pin Code " + validation.MinlengthcheckMSGpincode + ",corre_pincode";
						return msg;
					}


				}
				
				if (neet_roll_no == null || neet_roll_no.trim().equals("")) {
					msg= "Please Enter NEET Roll No,neet_roll_no";
					return msg;
				}
				if (validation.isOnlyNumer(neet_roll_no) == true) {
					msg= "NEET Roll No" + validation.isOnlyNumerMSG+",neet_roll_no";
					return msg;
				}
				if (validation.maxlengthcheckneetrollno(neet_roll_no) == false) {
					msg = " NEET Roll No" + validation.MaxlengthcheckMSGneetrollno10 + ",neet_roll_no";
					return msg;
				}
				
				if (neet_application_no == null || neet_application_no.trim().equals("")) {
					msg= "Please Enter NEET Application No,neet_application_no";
					return msg;
				}
				if (validation.isOnlyNumer(neet_application_no) == true) {
					msg= "NEET Application No" + validation.isOnlyNumerMSG+",neet_application_no";
					return msg;
				}
				
				
				if (validation.maxlengthcheckneetapplicationno(neet_application_no) == false) {
					msg = " NEET Application No" + validation.MaxlengthcheckMSGneetapplicationno12 + ",neet_application_no";
					return msg;
				}
				
				if (neet_rank == null || neet_rank.trim().equals("")) {
					msg = "Please Enter NEET All India Rank,neet_rank";
					return msg;
				}

				if (validation.isOnlyNumer(neet_rank) == true) {
					msg = "NEET All India Rank " + validation.isOnlyNumerMSG + ",neet_rank";
					return msg;
				}

				
				if (validation.maxlengthcheckneetindiarank(neet_rank) == false) {
					msg = "NEET All India Rank " + validation.MaxlengthcheckMSGneetallindiarank7 + ",neet_rank";
					return msg;
				}

				if (neet_marks == null || neet_marks.trim().equals("")) {
					msg = "Please Enter NEET Marks,neet_marks";
					return msg;
				}

				if (validation.isOnlyNumer(neet_marks) == true) {
					msg = "NEET Marks " + validation.isOnlyNumerMSG + ",neet_marks";
					return msg;
				}

				if (validation.maxlengthcheckneetmark(neet_marks) == false) {

					msg = "NEET Marks " + validation.MaxlengthcheckMSGneetmark3 + ",neet_marks";
					return msg;

				}

				if (neet_percentile == null || neet_percentile.trim().equals("")) {
					msg = "Please Enter NEET Percentile,neet_percentile";
					return msg;
				}


				if (validation.maxlengthcheckneetpercentile(neet_percentile) == false) {
					msg = "NEET Percentile " + validation.MaxlengthcheckMSGneetpercentile5 + ",neet_percentile";
					return msg;
				}
				
				String pers_aadhar_no = pers_aadhar_no1+pers_aadhar_no2+pers_aadhar_no3;

	    	 EDU_NCISM_REG_GRADU_PERSONAL_DTLS pda= (EDU_NCISM_REG_GRADU_PERSONAL_DTLS)sessionHQL.get(EDU_NCISM_REG_GRADU_PERSONAL_DTLS.class, Integer.parseInt(e_id));
	    	 
	    	    pda.setPers_name(pers_name);
				pda.setPers_father_name(pers_father_name);
				pda.setPers_mother_name(pers_mother_name);
				pda.setPers_date_of_birth(dob_i);
				pda.setPers_gender(pers_gender);
				pda.setPers_mob_no(pers_mob_no);
				pda.setPers_email(pers_email);
				pda.setPers_category(pers_category);
				pda.setPers_religion(pers_religion);
				pda.setPers_marital_status(Integer.parseInt(pers_marital_status));
				pda.setPers_nationality(Integer.parseInt(pers_nationality));
				pda.setState_id(Integer.parseInt(state_id));
				pda.setDistrict_id(Integer.parseInt(district_id));
				pda.setVillage(village);
				pda.setPers_aadhar_no(pers_aadhar_no);
				
				
//				add quota and councelling
				
				pda.setQuota_id(Integer.parseInt(quota_id));
				pda.setCounselling_authority(Integer.parseInt(counselling_authority));
				
				
				pda.setPers_permanent_house_no(pers_permanent_house_no);
				pda.setPers_permanent_village(pers_permanent_village);
				pda.setPers_permanent_postoffice(pers_permanent_postoffice);
				pda.setPers_permanent_tehsil(pers_permanent_tehsil);
				pda.setPers_permanent_policestation(pers_permanent_policestation);
				pda.setPers_permanent_district(pers_permanent_district);
				pda.setPers_permanent_state(Integer.parseInt(pers_permanent_state));
				pda.setPers_permanent_pincode(Integer.parseInt(pers_permanent_pincode));
				pda.setPers_permanent_lendmark(pers_permanent_lendmark);
				pda.setPers_present_house_no(pers_present_house_no);
				pda.setPers_present_village(pers_present_village);
				pda.setPers_present_postoffice(pers_present_postoffice);
				pda.setPers_present_tehsil(pers_present_tehsil);
				pda.setPers_present_policestation(pers_present_policestation);
				pda.setPers_present_district(pers_present_district);
				pda.setPers_present_state(Integer.parseInt(pers_present_state));
				pda.setPers_present_pincode(Integer.parseInt(pers_present_pincode));
				pda.setPers_present_lendmark(pers_present_lendmark);
				
				
				pda.setCorre_house_no(corre_house_no);
				pda.setCorre_village(corre_village);
				pda.setCorre_postoffice(corre_postoffice);
				pda.setCorre_tehsil(corre_tehsil);
				pda.setCorre_policestation(corre_policestation);
				pda.setCorre_district(corre_district);
				pda.setCorre_state(Integer.parseInt(corre_state));
				pda.setCorre_pincode(Integer.parseInt(corre_pincode));
				pda.setCorre_lendmark(corre_lendmark);
				
				pda.setNeet_roll_no(neet_roll_no);
				pda.setNeet_application_no(neet_application_no);
				
				pda.setNeet_marks(Integer.parseInt(neet_marks));
				pda.setNeet_rank(Integer.parseInt(neet_rank));
				pda.setNeet_percentile(neet_percentile);
				pda.setPers_middel_name(pers_middel_name);
				pda.setPers_surname(pers_surname);
				
				pda.setCand_prifix(Integer.parseInt(cand_prifix));	
				
				pda.setModified_by(username);
				pda.setModified_date(date);
	    	 
		    	msg = da.getUpdateNcism_PerDetails(pda);
		    	sessionHQL.close();
		    	return msg;
		    	
		 	}
	     
}
