package com.AyushEdu.controller.Registration.Postgraduate;

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
import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_REG_PERSONAL_DETAILS;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Registration.Postgraduate.Personaldetails_PG_DAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class PersonalDetailsController_pg {
	
	@Autowired
	private Personaldetails_PG_DAO da;
	
	@Autowired
	ValidationController validation;
	
	@Autowired
	CommonController common;
	
	@Autowired
	Commondao comda;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private SessionFactory sessionFactory;

	@RequestMapping(value = "/Personal_Details_PG_Url",method = RequestMethod.GET)
	public ModelAndView Personal_Details_PG_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,Principal principal)  {
	try {
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Personal_Details_PG_Url", roleid1);		
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
//		 Boolean val = roledao.ScreenRedirect("Personal_Details_PG_Url", roleid1);
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
			Session sessionHQL = this.sessionFactory.openSession();	
		 
			String username = principal.getName();
			String userId = session.getAttribute("userId_for_jnlp").toString();
			
			ArrayList<ArrayList<String>> listp= da.get_p_id_pers_info_data_pg(Integer.parseInt(userId));
			System.err.println(listp);
			if (listp.size()>0) {
				Query qry = sessionHQL.createQuery("select status from EDU_PG_REG_PERSONAL_DETAILS where p_id=:p_id");
				qry.setParameter("p_id", Integer.parseInt(userId));
				check = (Integer) qry.uniqueResult();
				System.err.println("check =     "+check);
			}
			if (check.equals(1)) {
				return new ModelAndView("redirect:PG_Std_details_view_Url");
			}else {
			
//			Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
//			Mmap.put("getAcademicList", common.getAcademicList(sessionFactory));
			Mmap.addAttribute("pddata",da.getAllPersdetails_pg(Integer.parseInt(userId)));
			Mmap.addAttribute("userid",da.getUsername_pg(username));
			Mmap.put("getgenderList", common.getgenderList(sessionFactory));
			Mmap.put("getMedCountryName", common.getMedCountryName(sessionFactory));
			Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
			Mmap.put("getMedDistrictName", common.getMedDistrictName(sessionFactory));
			Mmap.put("getcategorylist", common.getcategoryList(sessionFactory));
			Mmap.put("getreligionListdata", common.getreligionList(sessionFactory));
			Mmap.put("getmsList", common.getmaritalstatusList(sessionFactory));
			Mmap.put("getQuotaList", common.getQuotaList(sessionFactory));
			Mmap.put("getCounselingAuthoList", common.getCounselingAuthoList(sessionFactory));
			//Mmap.addAttribute("basicdata",da.getBesicdetails(Integer.parseInt(userId)));
			Mmap.addAttribute("msg", msg);
			}
			
	} catch (Exception e) {
		e.printStackTrace();
	}
		return new ModelAndView("Personal_Details_PG_Tiles");
//		return new ModelAndView("Personal_Details_newTiles");
	}
	
	@PostMapping(value = "/personal_details_PG_Action")
	public ModelAndView personal_details_PG_Action(
			@Validated @ModelAttribute("personal_details_PG_CMD") EDU_PG_REG_PERSONAL_DETAILS td,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws ParseException {

//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Personal_Details_PG_Url", roleid1);		
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
			String first_name = request.getParameter("first_name");
			String middel_name = request.getParameter("middel_name");
			String surname = request.getParameter("surname");
			String father_name = request.getParameter("father_name");
			String mother_name = request.getParameter("mother_name");
			String gender = request.getParameter("gender");
			String date_of_birth =  request.getParameter("date_of_birth");
			Date dob_i = null;
			if (!request.getParameter("date_of_birth").equals("")) {
				dob_i = formatter1.parse(request.getParameter("date_of_birth"));
			} else {
				dob_i = null;
			}
			 String yrr	= request.getParameter("yrr");
			
			String mob_no = request.getParameter("mob_no");
			String email = request.getParameter("hidden_email");
			String category = request.getParameter("category");
			String religion = request.getParameter("religion");
			String marital_status = request.getParameter("marital_status");
			String nationality = request.getParameter("nationality");
			
//			String state_id = request.getParameter("state_id");
//			String district_id = request.getParameter("district_id");
//			String village = request.getParameter("village");
			
			String aadhar_no1 = request.getParameter("aadhar_no1");
			String aadhar_no2 = request.getParameter("aadhar_no2");
			String aadhar_no3 = request.getParameter("aadhar_no3");
			String aadhar_no = aadhar_no1+aadhar_no2+aadhar_no3;
			
			String permanent_house_no = request.getParameter("permanent_house_no");
			String permanent_add_line1 = request.getParameter("permanent_add_line1");
			String permanent_add_line2 = request.getParameter("permanent_add_line2");
			String permanent_state = request.getParameter("permanent_state");
			String permanent_district = request.getParameter("permanent_district");
			String permanent_village = request.getParameter("permanent_village");
			String permanent_pincode = request.getParameter("permanent_pincode");
			String permanent_lendmark = request.getParameter("permanent_lendmark");
			
			String present_house_no = request.getParameter("present_house_no");
			String present_add_line1 = request.getParameter("present_add_line1");
			String present_add_line2 = request.getParameter("present_add_line2");
			String present_state = request.getParameter("present_state");
			String present_district = request.getParameter("present_district");
			String present_village = request.getParameter("present_village");
			String present_pincode = request.getParameter("present_pincode");
			String present_lendmark = request.getParameter("present_lendmark");
			String p_id = userId;
			
			
			String corre_house_no = request.getParameter("corre_house_no");
			String corre_add_line1 = request.getParameter("corre_add_line1");
			String corre_add_line2 = request.getParameter("corre_add_line2");
			String corre_state = request.getParameter("corre_state");
			String corre_district = request.getParameter("corre_district");
			String corre_village = request.getParameter("corre_village");
			String corre_pincode = request.getParameter("corre_pincode");
			String corre_lendmark = request.getParameter("corre_lendmark");
			
			
//			String cand_prifix = request.getParameter("cand_prifix");
//			String father_title = request.getParameter("father_title");
//			String mother_title = request.getParameter("mother_title");
			
//			String neet_roll_no = request.getParameter("neet_roll_no");
//			String neet_application_no = request.getParameter("neet_application_no");
			
//			String neet_rank = request.getParameter("neet_rank");
//			String neet_marks = request.getParameter("neet_marks");
//			String neet_percentile = request.getParameter("neet_percentile");
		
//			if (cand_prifix == null || cand_prifix.trim().equals("0")) {
//				ra.addAttribute("msg", "Please Select Name Prifix");
//				return new ModelAndView("redirect:Personal_Details_PG_Url");
//			}
			
			if (first_name == null || first_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter First Name.");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.maxlengthcheck50(first_name) == false) {
				ra.addAttribute("msg", "First Name " + validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.isOnlyAlphabet(first_name) == false) {
				ra.addAttribute("msg", "First Name " + validation.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (middel_name == null || middel_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Middle Name.");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.maxlengthcheck50(middel_name) == false) {
				ra.addAttribute("msg", "Middle Name " + validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.isOnlyAlphabetSLASH(middel_name) == false) {
				ra.addAttribute("msg", "Middle Name " + validation.isOnlyAlphabetMSGSLASH);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (surname == null || surname.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Surname.");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.maxlengthcheck50(surname) == false) {
				ra.addAttribute("msg", "Surname" + validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.isOnlyAlphabet(surname) == false) {
				ra.addAttribute("msg", "Surname" + validation.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (father_name == null || father_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Father's Name.");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.maxlengthcheck50(father_name) == false) {
				ra.addAttribute("msg", "Father's Name " + validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.isOnlyAlphabet(father_name) == false) {
				ra.addAttribute("msg", "Father's Name " + validation.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (mother_name == null || mother_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Mother's Name.");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.maxlengthcheck50(mother_name) == false) {
				ra.addAttribute("msg", "Mother's Name " + validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.isOnlyAlphabet(mother_name) == false) {
				ra.addAttribute("msg", "Mother's Name " + validation.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (gender == null || gender.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Gender");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (date_of_birth == null || date_of_birth.trim().equals("") || date_of_birth.equals("DD/MM/YYYY")) {
				ra.addAttribute("msg", "Please Enter The Date Of Birth");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if(Integer.parseInt(yrr) < 17 || yrr == "" || yrr == "0"){
			    	ra.addAttribute("msg", "Age Should Be Greater Than 17 Years,date_of_birth");
					return new ModelAndView("redirect:Personal_Details_PG_Url");
			    }

			if (mob_no == null || mob_no.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Mobile Number");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.isOnlyNumer(mob_no) == true) {
				ra.addAttribute("msg", "Mobile Number " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.maxlengthcheck10(mob_no) == false) {
				ra.addAttribute("msg", "Mobile Number " + validation.MaxlengthcheckMSG10);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.isValidMobileNo(mob_no) == false) {
				ra.addAttribute("msg", "Mobile Number " + validation.isOnlyMobilenumberMSG);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (email == null || email.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Email Address");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.maxlengthcheck70(email) == false) {
				ra.addAttribute("msg", "Email Address " + validation.MaxlengthcheckMSG70);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (category == null || category.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Category");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (religion == null || religion.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Religion");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (marital_status == null || marital_status.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Marital Status");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (nationality == null || nationality.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Nationality");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			
			if (aadhar_no1 == null || aadhar_no1.trim().equals("")) {
				ra.addAttribute("msg", "Enter First Four Digit Of Adharcard No");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.isOnlyNumer(aadhar_no1) == true) {
				ra.addAttribute("msg", "Aadhaar No. " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.AadharNoLength(aadhar_no1) == false) {
				ra.addAttribute("msg", validation.AadharNoMSG11);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.AadharNoMinLength(aadhar_no1) == false) {
				ra.addAttribute("msg", validation.AadharNoMSGMinlen11);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}

			if (aadhar_no2 == null || aadhar_no2.trim().equals("")) {
				ra.addAttribute("msg", "Enter Second Four Digit Of Adharcard No");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.isOnlyNumer(aadhar_no2) == true) {
				ra.addAttribute("msg", "Aadhaar No. " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.AadharNoLength(aadhar_no2) == false) {
				ra.addAttribute("msg", validation.AadharNoMSG11);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.AadharNoMinLength(aadhar_no2) == false) {
				ra.addAttribute("msg", validation.AadharNoMSGMinlen11);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}

			if (aadhar_no3 == null || aadhar_no3.trim().equals("")) {
				ra.addAttribute("msg", "Enter Last Four Digit Of Adharcard No");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.isOnlyNumer(aadhar_no3) == true) {
				ra.addAttribute("msg", "Aadhaar No. " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.AadharNoLength(aadhar_no3) == false) {
				ra.addAttribute("msg", validation.AadharNoMSG11);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.AadharNoMinLength(aadhar_no3) == false) {
				ra.addAttribute("msg", validation.AadharNoMSGMinlen11);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			
//			if (quota_id == null || quota_id.trim().equals("0")) {
//				ra.addAttribute("msg", "Please Select Quota");
//				return new ModelAndView("redirect:Personal_Details_PG_Url");
//			}
//			
//			if (counselling_authority == null || counselling_authority.trim().equals("0")) {
//				ra.addAttribute("msg", "Please Select Counselling Authority");
//				return new ModelAndView("redirect:Personal_Details_PG_Url");
//			}

			/////////// Address
			if (permanent_house_no == null || permanent_house_no.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Permanent House No/Name");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.isOnlyAlphabetNumberSLASH(permanent_house_no) == false) {
				ra.addAttribute("msg", "Present House No. " + validation.isOnlyAlphabetNumberSLASHMSG);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.maxlengthcheck50(permanent_house_no) == false) {
				ra.addAttribute("msg", "Permanent House No/Name " + validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (permanent_add_line1 == null || permanent_add_line1.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Permanent Address Line 1");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			   }
			if (validation.isOnlyAlphabetNumber(permanent_add_line1) == false) {
				ra.addAttribute("msg", "Permanent Address Line 1 " + validation.isOnlyAlphabetNumberMSG);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.maxlengthcheck100(permanent_add_line1) == false) {
				ra.addAttribute("msg", "Permanent Address Line 1 " + validation.MaxlengthcheckMSG100);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (permanent_add_line2 == null || permanent_add_line2.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Permanent Address Line 2");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.maxlengthcheck100(permanent_add_line2) == false) {
				ra.addAttribute("msg", "Permanent Address Line 2 " + validation.MaxlengthcheckMSG100);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.isOnlyAlphabetNumber(permanent_add_line2) == false) {
				ra.addAttribute("msg", "Permanent Address Line 2 " + validation.isOnlyAlphabetNumberMSG);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (permanent_state == null || permanent_state.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Permanent State");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (permanent_district == null || permanent_district.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select District");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (permanent_village == null || permanent_village.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Permanent City/Village");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.maxlengthcheck100(permanent_village) == false) {
				ra.addAttribute("msg", "Permanent City/Village " + validation.MaxlengthcheckMSG100);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.isOnlyAlphabet(permanent_village) == false) {
				ra.addAttribute("msg", "Permanent City/Village " + validation.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (permanent_lendmark == null || permanent_lendmark.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter the Permanent Lendmark");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			   }
			if (validation.maxlengthcheck100(permanent_lendmark) == false) {
				ra.addAttribute("msg", "Permanent Lendmark " + validation.MaxlengthcheckMSG100);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			
			if (validation.isOnlyAlphabet(permanent_lendmark) == false) {
				ra.addAttribute("msg", "Permanent Lendmark " + validation.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			
			if (permanent_pincode == null || permanent_pincode.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Permanent Pin Code");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			
			if (validation.maxlengthcheckpincode(permanent_pincode) == false) {
				ra.addAttribute("msg", "Permanent Pin Code " + validation.MaxlengthcheckMSGpincode);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			
			if (validation.minlengthcheckpincode(permanent_pincode) == false) {
				ra.addAttribute("msg", "Permanent Pin Code " + validation.MinlengthcheckMSGpincode);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			
			if (validation.isOnlyNumer(permanent_pincode) == true) {
				ra.addAttribute("msg", "Permanent Pin Code " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}

			if (request.getParameter("check_address") == null) {
				
				if (present_house_no == null || present_house_no.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Present House No/Name.");
					return new ModelAndView("redirect:Personal_Details_PG_Url");
				}
				if (validation.isOnlyAlphabetNumberSLASH(present_house_no) == false) {
					ra.addAttribute("msg", "Present House No/Name. " + validation.isOnlyAlphabetNumberSLASHMSG);
					return new ModelAndView("redirect:Personal_Details_PG_Url");
				}
				if (validation.maxlengthcheck50(present_house_no) == false) {
					ra.addAttribute("msg", "Present House No/Name " + validation.MaxlengthcheckMSG50);
					return new ModelAndView("redirect:Personal_Details_PG_Url");
				}
				if (present_add_line1 == null || present_add_line1.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Present Address Line 1");
					return new ModelAndView("redirect:Personal_Details_PG_Url");
				}
				if (validation.maxlengthcheck100(present_add_line1) == false) {
					ra.addAttribute("msg", "Present Address Line 1 " + validation.MaxlengthcheckMSG100);
					return new ModelAndView("redirect:Personal_Details_PG_Url");
				}
				if (validation.isOnlyAlphabetNumber(present_add_line1) == false) {
					ra.addAttribute("msg", "Present Address Line 1 " + validation.isOnlyAlphabetNumberMSG);
					return new ModelAndView("redirect:Personal_Details_PG_Url");
				}
				if (present_add_line2 == null || present_add_line2.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Present Address Line 2");
					return new ModelAndView("redirect:Personal_Details_PG_Url");
				}
				if (validation.maxlengthcheck100(present_add_line2) == false) {
					ra.addAttribute("msg", "Present Address Line 2 " + validation.MaxlengthcheckMSG100);
					return new ModelAndView("redirect:Personal_Details_PG_Url");
				}
				if (validation.isOnlyAlphabetNumber(present_add_line2) == false) {
					ra.addAttribute("msg", "Present Address Line 2 " + validation.isOnlyAlphabetNumberMSG);
					return new ModelAndView("redirect:Personal_Details_PG_Url");
				}
				if (present_state == null || present_state.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select Present State");
					return new ModelAndView("redirect:Personal_Details_PG_Url");
					}
				if (present_district == null || present_district.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select District");
					return new ModelAndView("redirect:Personal_Details_PG_Url");
				}
				if (present_village == null || present_village.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Present City/Village");
					return new ModelAndView("redirect:Personal_Details_PG_Url");
				}
				if (validation.maxlengthcheck100(present_village) == false) {
					ra.addAttribute("msg", "Present City/Village " + validation.MaxlengthcheckMSG100);
					return new ModelAndView("redirect:Personal_Details_PG_Url");
				}
				if (validation.isOnlyAlphabet(present_village) == false) {
					ra.addAttribute("msg", "Present City/Village " + validation.isOnlyAlphabetMSG);
					return new ModelAndView("redirect:Personal_Details_PG_Url");
				}
				if (present_lendmark == null || present_lendmark.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter the Present Lendmark");
					return new ModelAndView("redirect:Personal_Details_PG_Url");
				   }
				if (present_pincode == null || present_pincode.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Present Pin Code");
					return new ModelAndView("redirect:Personal_Details_PG_Url");
				}
				if (validation.maxlengthcheckpincode(present_pincode) == false) {
					ra.addAttribute("msg", "Present Pin Code " + validation.MaxlengthcheckMSGpincode);
					return new ModelAndView("redirect:Personal_Details_PG_Url");
				}
				if (validation.minlengthcheckpincode(present_pincode) == false) {
					ra.addAttribute("msg", "Present Pin Code " + validation.MinlengthcheckMSGpincode);
					return new ModelAndView("redirect:Personal_Details_PG_Url");
				}
				if (validation.isOnlyNumer(present_pincode) == true) {
					ra.addAttribute("msg", "Present Pin Code " + validation.isOnlyNumerMSG);
					return new ModelAndView("redirect:Personal_Details_PG_Url");
				}
			}
			
			//corresponding address start
			
			if (corre_house_no == null || corre_house_no.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Corresponding House No/Name");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.maxlengthcheck50(corre_house_no) == false) {
				ra.addAttribute("msg", "Corresponding House No/Name "+ validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.isOnlyAlphabetNumberSLASH(corre_house_no) == false) {
				ra.addAttribute("msg", "Corresponding House No/Name "+ validation.isOnlyAlphabetNumberSLASHMSG);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (corre_add_line1 == null || corre_add_line1.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Corresponding Address Line 1");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.maxlengthcheck100(corre_add_line1) == false) {
				ra.addAttribute("msg", "Corresponding Address Line 1 " + validation.MaxlengthcheckMSG100);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.isOnlyAlphabetNumber(corre_add_line1) == false) {
				ra.addAttribute("msg", "Corresponding Address Line 1 " + validation.isOnlyAlphabetNumberMSG);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (corre_add_line2 == null || corre_add_line2.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Corresponding Address Line 2");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.maxlengthcheck100(corre_add_line2) == false) {
				ra.addAttribute("msg", "Corresponding Address Line 2 " + validation.MaxlengthcheckMSG100);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.isOnlyAlphabetNumber(corre_add_line2) == false) {
				ra.addAttribute("msg", "Corresponding Address Line 2 " + validation.isOnlyAlphabetNumberMSG);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (corre_state == null || corre_state.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Corresponding State");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (corre_district == null || corre_district.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Corresponding District");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (corre_village == null || corre_village.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Corresponding City/Village");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.maxlengthcheck50(corre_village) == false) {
				ra.addAttribute("msg", "Corresponding City/Village "+ validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.isOnlyAlphabet(corre_village) == false) {
				ra.addAttribute("msg", "Corresponding City/Village "+ validation.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (corre_lendmark == null || corre_lendmark.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Corresponding Landmark");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.maxlengthcheck50(corre_lendmark) == false) {
				ra.addAttribute("msg", "Corresponding Landmark "+ validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.isOnlyAlphabet(corre_lendmark) == false) {
				ra.addAttribute("msg", "Corresponding Landmark "+ validation.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (corre_pincode == null || corre_pincode.trim().equals("")) {
				ra.addAttribute("msg", "Please Select Corresponding Pincode");
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.isOnlyNumer(corre_pincode) == true) {
				ra.addAttribute("msg", "Corresponding Pincode "+ validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.maxlengthcheckpincode(corre_pincode) == false) {
				ra.addAttribute("msg", "Corresponding Pincode "+ validation.MaxlengthcheckMSGpincode);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			if (validation.minlengthcheckpincode(corre_pincode) == false) {
				ra.addAttribute("msg", "Corresponding Pincode "+ validation.MinlengthcheckMSGpincode);
				return new ModelAndView("redirect:Personal_Details_PG_Url");
			}
			
			
			
//			if (neet_roll_no == null || neet_roll_no.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter NEET Roll No");
//				return new ModelAndView("redirect:Personal_Details_PG_Url");
//			}
//			if (validation.isOnlyNumer(neet_roll_no) == true) {
//				ra.addAttribute("msg", "NEET Roll No" + validation.isOnlyNumerMSG);
//				return new ModelAndView("redirect:Personal_Details_PG_Url");
//			}
//			if (validation.maxlengthcheckneetrank(neet_roll_no) == false) {
//				ra.addAttribute("msg", " NEET Roll No" + validation.MaxlengthcheckMSGneetrank6);
//				return new ModelAndView("redirect:Personal_Details_PG_Url");
//			}
//			
//			if (neet_application_no == null || neet_application_no.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter NEET Application No");
//				return new ModelAndView("redirect:Personal_Details_PG_Url");
//			}
//			if (validation.isOnlyNumer(neet_application_no) == true) {
//				ra.addAttribute("msg", "NEET Application No" + validation.isOnlyNumerMSG);
//				return new ModelAndView("redirect:Personal_Details_PG_Url");
//			}
//			
//			if (validation.maxlengthcheckneetrank(neet_application_no) == false) {
//				ra.addAttribute("msg", " NEET Application No" + validation.MaxlengthcheckMSGneetrank6);
//				return new ModelAndView("redirect:Personal_Details_PG_Url");
//			}
//			
//			if (neet_rank == null || neet_rank.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter NEET All India Rank");
//				return new ModelAndView("redirect:Personal_Details_PG_Url");
//			}
//			
//			if (validation.isOnlyNumer(neet_rank) == true) {
//				ra.addAttribute("msg", "NEET All India Rank" + validation.isOnlyNumerMSG);
//				return new ModelAndView("redirect:Personal_Details_PG_Url");
//			}
//			
//			if (validation.maxlengthcheckneetrank(neet_rank) == false) {
//				ra.addAttribute("msg", " NEET All India Rank " + validation.MaxlengthcheckMSGneetrank6);
//				return new ModelAndView("redirect:Personal_Details_PG_Url");
//			}
//			
//			if (neet_marks == null || neet_marks.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter NEET Marks");
//				return new ModelAndView("redirect:Personal_Details_PG_Url");
//			}
//			if (validation.isOnlyNumer(neet_marks) == true) {
//				ra.addAttribute("msg", "NEET Marks" + validation.isOnlyNumerMSG);
//				return new ModelAndView("redirect:Personal_Details_PG_Url");
//			}
//			if (validation.maxlengthcheckneetmark(neet_marks) == false) {
//				ra.addAttribute("msg", " NEET Marks " + validation.MaxlengthcheckMSGneetmark3);
//				return new ModelAndView("redirect:Personal_Details_PG_Url");
//			}
//			
//			if (neet_percentile == null || neet_percentile.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter NEET Percentile");
//				return new ModelAndView("redirect:Personal_Details_PG_Url");
//			}
////			if (validation.isOnlyNumer(neet_percentile) == true) {
////				ra.addAttribute("msg", "Neet Percentile" + validation.isOnlyNumerMSG);
////				return new ModelAndView("redirect:Personal_Details_PG_Url");
////			}
//			if (validation.maxlengthcheckneetpercentile(neet_percentile) == false) {
//				ra.addAttribute("msg", " NEET Percentile " + validation.MaxlengthcheckMSGneetpercentile5);
//				return new ModelAndView("redirect:Personal_Details_PG_Url");
//			}

			        td.setCreated_by(username);
					td.setCreated_date(date);
					td.setFirst_name(first_name);
					td.setMiddel_name(middel_name);
					td.setSurname(surname);
					td.setFather_name(father_name);
					td.setMother_name(mother_name);
					td.setDate_of_birth(dob_i);
					td.setGender(Integer.parseInt(gender));
					td.setMob_no(mob_no);
					td.setEmail(email);
					td.setCategory(Integer.parseInt(category));
					td.setReligion(Integer.parseInt(religion));
					td.setMarital_status(Integer.parseInt(marital_status));
					td.setNationality(Integer.parseInt(nationality));
					td.setAadhar_no(aadhar_no);
					td.setPermanent_house_no(permanent_house_no);
					td.setPermanent_add_line1(permanent_add_line1);
					td.setPermanent_add_line2(permanent_add_line2);
					td.setPermanent_state(Integer.parseInt(permanent_state));
					td.setPermanent_district(Integer.parseInt(permanent_district));
					td.setPermanent_village(permanent_village);
					td.setPermanent_pincode(Integer.parseInt(permanent_pincode));
					td.setPermanent_lendmark(permanent_lendmark);
					
					td.setPresent_house_no(present_house_no);
					td.setPresent_add_line1(present_add_line1);
					td.setPresent_add_line2(present_add_line2);
					td.setPresent_state(Integer.parseInt(present_state));
					td.setPresent_district(Integer.parseInt(present_district));
					td.setPresent_village(present_village);
					td.setPresent_pincode(Integer.parseInt(present_pincode));
					td.setPresent_lendmark(present_lendmark);
					td.setP_id(Integer.parseInt(userId));
					td.setCorre_house_no(corre_house_no);
					td.setCorre_add_line1(corre_add_line1);
					td.setCorre_add_line2(corre_add_line2);
					td.setCorre_state(Integer.parseInt(corre_state));
					td.setCorre_district(corre_district);
					td.setCorre_village(corre_village);
					td.setCorre_pincode(Integer.parseInt(corre_pincode));
					td.setCorre_lendmark(corre_lendmark);
					td.setStatus(Integer.parseInt("0"));
					
					int reg_id=(int) sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					
			     	tx.commit();
					
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
				return new ModelAndView("redirect:Personal_Details_PG_Url");
				}
	
	 @RequestMapping(value = "/getPersonaldetails_PG", method = RequestMethod.POST)
	 	public @ResponseBody ArrayList<ArrayList<String>> getPersonaldetails_PG(int userid,HttpSession session) {
	    	ArrayList<ArrayList<String>> data = da.getPersonaldetails_pg(userid, session);
	    	return data;
	 	}
	 
	 @RequestMapping(value = "/getBesicdetails_PG_ctrl", method = RequestMethod.POST)
	 	public @ResponseBody ArrayList<ArrayList<String>> getBesicdetails_PG_ctrl(int userid,HttpSession session) {
		 String roleid = session.getAttribute("roleid").toString();
		 String staff_lvl = comda.getStaffLvlfromRoleid(roleid);
		 ArrayList<ArrayList<String>> data = da.getBesicdetails_pg(userid,staff_lvl);
	    	return data;
	 	}
	 
	 @RequestMapping(value = "/get_p_id_info_PG_ctrl", method = RequestMethod.POST)
	 	public @ResponseBody ArrayList<ArrayList<String>> get_p_id_info_PG_ctrl(int userid) {
	    	ArrayList<ArrayList<String>> data = da.get_p_id_pers_info_data_pg(userid);
	    	return data;
	 	}
	 
	 @RequestMapping(value = "/get_ayush_id_PG_ctrl", method = RequestMethod.POST)
	 	public @ResponseBody ArrayList<ArrayList<String>> get_ayush_id_PG_ctrl(String userid,HttpSession session) {
			String roleid = session.getAttribute("roleid").toString();
			String staff_lvl = comda.getStaffLvlfromRoleid(roleid);
		 
	    	ArrayList<ArrayList<String>> data = da.get_ayush_id_data_pg(userid,staff_lvl);
	    	return data;
	 	}
	     @RequestMapping(value = "/getUpdatePerDetails_PG_ctrl", method = RequestMethod.POST)
		 	public @ResponseBody String getUpdatePerDetails_PG_ctrl(String e_id,HttpServletRequest request, Principal principal) throws ParseException {
	    	 
	    	 String msg ="";
	    	 String username = principal.getName();
	    	 Date date = new Date();
	    	 Session sessionHQL = this.sessionFactory.openSession();
	    	 DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
	    	 String first_name = request.getParameter("first_name");
	    	 String father_name = request.getParameter("father_name");
	    	 String mother_name = request.getParameter("mother_name");
	    	 String date_of_birth	= request.getParameter("date_of_birth");
	    	 
	    	 Date dob_i = null;
				if (!request.getParameter("date_of_birth").equals("")) {
					dob_i = formatter1.parse(request.getParameter("date_of_birth"));
				} else {
					dob_i = null;
				}
				
			 String yrr	= request.getParameter("yrr");
	    	 String gender = request.getParameter("gender");
	    	 String mob_no = request.getParameter("mob_no");
	    	 
	    	 String email = request.getParameter("email");
	    	 String category = request.getParameter("category");
	    	 String religion = request.getParameter("religion");
	    	 String marital_status = request.getParameter("marital_status");
	    	 String nationality = request.getParameter("nationality");
//	    	 String state_id = request.getParameter("state_id");
//	    	 String district_id = request.getParameter("district_id");
//	    	 String village = request.getParameter("village");
	    	 
	    	 	String aadhar_no1 = request.getParameter("aadhar_no1");
				String aadhar_no2 = request.getParameter("aadhar_no2");
				String aadhar_no3 = request.getParameter("aadhar_no3");
					    	 
			// String aadhar_no = request.getParameter("aadhar_no");
	    	 String permanent_house_no = request.getParameter("permanent_house_no");
	    		String permanent_add_line1 = request.getParameter("permanent_add_line1");
				String permanent_add_line2 = request.getParameter("permanent_add_line2");
	    	 
	    	 String permanent_village = request.getParameter("permanent_village");
//	    	 String permanent_postoffice = request.getParameter("permanent_postoffice");
//	    	 String permanent_tehsil = request.getParameter("permanent_tehsil");
//	    	 String permanent_policestation = request.getParameter("permanent_policestation");
	    	 String permanent_district = request.getParameter("permanent_district");
	    	 String permanent_state = request.getParameter("permanent_state");
	    	 String permanent_pincode = request.getParameter("permanent_pincode");
	    	 String permanent_lendmark = request.getParameter("permanent_lendmark");
	    	 
	    	 String present_house_no = request.getParameter("present_house_no");
	    		String present_add_line1 = request.getParameter("present_add_line1");
				String present_add_line2 = request.getParameter("present_add_line2");
	    	 
	    	 String present_village = request.getParameter("present_village");
	    	 String present_district = request.getParameter("present_district");
	    	 String present_state = request.getParameter("present_state");
	    	 String present_pincode = request.getParameter("present_pincode");
	    	 String present_lendmark = request.getParameter("present_lendmark");
	    	 
//	    	    String cand_prifix = request.getParameter("cand_prifix");
//				String father_title = request.getParameter("father_title");
//				String mother_title = request.getParameter("mother_title");
				
				
//				String neet_roll_no = request.getParameter("neet_roll_no");
//				String neet_application_no = request.getParameter("neet_application_no");
//				String neet_rank = request.getParameter("neet_rank");
//				String neet_marks = request.getParameter("neet_marks");
//				String neet_percentile = request.getParameter("neet_percentile");
	    	 
	    	 
	    	 String corre_house_no = request.getParameter("corre_house_no");
	    	 String corre_add_line1 = request.getParameter("corre_add_line1");
	    	 String corre_add_line2 = request.getParameter("corre_add_line2");
	    	 String corre_state = request.getParameter("corre_state");
	    	 String corre_district = request.getParameter("corre_district");
	    	 String corre_village = request.getParameter("corre_village");
	    	 String corre_pincode = request.getParameter("corre_pincode");
	    	 String corre_lendmark = request.getParameter("corre_lendmark");
				
				String middel_name = request.getParameter("middel_name");
				String surname = request.getParameter("surname");
				
//				if (cand_prifix == null || cand_prifix.trim().equals("0")) {
//					msg = "Please Select Name Prifix,cand_prifix";
//					return msg;
//				}

				if (first_name == null || first_name.trim().equals("")) {
					msg = "Please Enter First Name,first_name";
					return msg;
				}
				
				if (validation.maxlengthcheck50(first_name) == false) {
					msg = "First Name " + validation.MaxlengthcheckMSG50 + ",first_name";
					return msg;
				}
				
				if (validation.isOnlyAlphabet(first_name) == false) {
					msg = "First Name " + validation.isOnlyAlphabetMSG + ",first_name";
					return msg;
				}
				if (middel_name == null || middel_name.trim().equals("")) {
					msg = "Please Enter Middel Name,middel_name";
					return msg;
				}
				if (validation.maxlengthcheck50(middel_name) == false) {
					msg = "Middle Name " + validation.MaxlengthcheckMSG50 + ",middel_name";
					return msg;
				}

				if (validation.isOnlyAlphabetSLASH(middel_name) == false) {
					msg = "Middle Name " + validation.isOnlyAlphabetMSGSLASH + ",middel_name";
					return msg;
				}
				
				if (surname == null || surname.trim().equals("")) {
					msg = "Please Enter Surname,surname";
					return msg;
				}

				if (validation.maxlengthcheck50(surname) == false) {
					msg = "Surname " + validation.MaxlengthcheckMSG50 + ",surname";
					return msg;
				}
				
				if (validation.isOnlyAlphabet(surname) == false) {
					msg = "Surname " + validation.isOnlyAlphabetMSG + ",surname";
					return msg;
				}

				if (father_name == null || father_name.trim().equals("")) {
					msg = "Please Enter Father's Name,father_name";
					return msg;
				}
				if (validation.maxlengthcheck50(father_name) == false) {
					msg = "Father's Name " + validation.MaxlengthcheckMSG50 + ",father_name";
					return msg;
				}
				if (validation.isOnlyAlphabet(father_name) == false) {
					msg = "Father's Name " + validation.isOnlyAlphabetMSG + ",father_name";
					return msg;
				}

				if (mother_name == null || mother_name.trim().equals("")) {
					msg = "Please Enter Mother's Name,mother_name";
					return msg;
				}
				
				if (validation.maxlengthcheck50(mother_name) == false) {
					msg = "Mother's Name " + validation.MaxlengthcheckMSG50 + ",mother_name";
					return msg;
				}
				
				if (validation.isOnlyAlphabet(mother_name) == false) {
					msg = "Mother's Name " + validation.isOnlyAlphabetMSG + ",mother_name";
					return msg;
				}

				if (gender == null || gender.trim().equals("0")) {
					msg = "Please Select Gender,gender";
					return msg;
				}

				if (date_of_birth == null || date_of_birth.trim().equals("")
						|| date_of_birth.equals("DD/MM/YYYY")) {
					msg = "Please Enter The Date Of Birth,date_of_birth";
					return msg;
				}
				
				 if(Integer.parseInt(yrr) < 17 || yrr == "" || yrr == "0"){
					 msg="Age Should Be Greater Than 17 Years,date_of_birth";
				    	return msg;
				    }
				

				if (mob_no == null || mob_no.trim().equals("")) {
					msg = "Please Enter Mobile Number,mob_no";
					return msg;
				}
				if (validation.isOnlyNumer(mob_no) == true) {
					msg = "Mobile Number " + validation.isOnlyNumerMSG + ",mob_no";
					return msg;

				}
				if (validation.maxlengthcheck10(mob_no) == false) {
					msg = "Mobile Number " + validation.MaxlengthcheckMSG10 + ",mob_no";
					return msg;
				}
				if (validation.isValidMobileNo(mob_no) == false) {
					msg = "Mobile Number " + validation.isOnlyMobilenumberMSG + ",mob_no";
					return msg;
				}

				if (email == null || email.trim().equals("")) {
					msg = "Please Enter Email Address,email";
					return msg;
				}
				if (validation.maxlengthcheck70(email) == false) {
					msg = "Email Address " + validation.MaxlengthcheckMSG70 + ",email";
					return msg;
				}

				if (category == null || category.trim().equals("0")) {
					msg = "Please Select Category,category";
					return msg;
				}

				if (religion == null || religion.trim().equals("0")) {
					msg = "Please Select Religion,religion";
					return msg;
				}

				if (marital_status == null || marital_status.trim().equals("0")) {
					msg = "Please Select Marital Status,marital_status";
					return msg;
				}

				if (nationality == null || nationality.trim().equals("0")) {
					msg = "Please Select Nationality,nationality";
					return msg;
				}

				if (aadhar_no1 == null || aadhar_no1.trim().equals("")) {
					msg = "Enter First Four Digit Of Adharcard No,aadhar_no1";
					return msg;
				}
				if (validation.isOnlyNumer(aadhar_no1) == true) {
					msg = "Aadhaar No " + validation.isOnlyNumerMSG + ",aadhar_no1";
					return msg;
				}
				if (validation.AadharNoLength(aadhar_no1) == false) {
					msg = "Aadhaar No " + validation.AadharNoMSG11 + ",aadhar_no1";
					return msg;
				}
				if (validation.AadharNoMinLength(aadhar_no1) == false) {
					msg = "Aadhaar No " + validation.AadharNoMSGMinlen11 + ",aadhar_no1";
					return msg;
				}

				if (aadhar_no2 == null || aadhar_no2.trim().equals("")) {
					msg = "Enter Second Four Digit Of Adharcard No,aadhar_no2";
					return msg;
				}
				if (validation.isOnlyNumer(aadhar_no2) == true) {
					msg = "Aadhaar No " + validation.isOnlyNumerMSG + ",aadhar_no2";
					return msg;
				}
				if (validation.AadharNoLength(aadhar_no2) == false) {

					msg = "Aadhaar No " + validation.AadharNoMSG11 + ",aadhar_no2";
					return msg;
				}
				if (validation.AadharNoMinLength(aadhar_no2) == false) {
					msg = "Aadhaar No " + validation.AadharNoMSGMinlen11 + ",aadhar_no2";
					return msg;
				}

				if (aadhar_no3 == null || aadhar_no3.trim().equals("")) {
					msg = "Enter Last Four Digit Of Adharcard No,aadhar_no3";
					return msg;
				}
				if (validation.isOnlyNumer(aadhar_no3) == true) {
					msg = "Aadhaar No " + validation.isOnlyNumerMSG + ",aadhar_no3";
					return msg;
				}
				if (validation.AadharNoLength(aadhar_no3) == false) {
					msg = "Aadhaar No " + validation.AadharNoMSG11 + ",aadhar_no3";
					return msg;
				}
				if (validation.AadharNoMinLength(aadhar_no3) == false) {
					msg = "Aadhaar No " + validation.AadharNoMSGMinlen11 + ",aadhar_no3";
					return msg;
				}


				/////////// Address

				if (permanent_house_no == null || permanent_house_no.trim().equals("")) {
					msg = "Please Enter permanent House No/Name,permanent_house_no";
					return msg;
				}

				if (validation.isOnlyAlphabetNumberSLASH(permanent_house_no) == false) {
					msg= "Permanent House House No/Name " + validation.isOnlyAlphabetNumberSLASHMSG+",permanent_house_no";
					return msg;
				}

				if (validation.maxlengthcheck50(permanent_house_no) == false) {
					msg = "House No/Name " + validation.MaxlengthcheckMSG50 + ",permanent_house_no";
					return msg;
				}

				
				if (permanent_add_line1 == null || permanent_add_line1.trim().equals("")) {
					msg= "Please Enter Permanent Address Line 1,permanent_add_line1";
					return msg;
				}
				
				if (validation.isOnlyAlphabetNumber(permanent_add_line1) == false) {
					msg= "Permanent Address Line 1 " + validation.isOnlyAlphabetNumberMSG+",permanent_add_line1";
					return msg;
				}
				
				if (validation.maxlengthcheck100(permanent_add_line1) == false) {
					msg= "Permanent Address Line 1 " + validation.MaxlengthcheckMSG100+",permanent_add_line1";
					return msg;
				}
				
				if (permanent_add_line2 == null || permanent_add_line2.trim().equals("")) {
					msg="Please Enter Permanent Address Line 2,permanent_add_line2";
					return msg;
				}
				if (validation.isOnlyAlphabetNumber(permanent_add_line2) == false) {
					msg= "Permanent Address Line 2 " + validation.isOnlyAlphabetNumberMSG+",permanent_add_line2";
					return msg;
				}
				
				if (validation.maxlengthcheck100(permanent_add_line2) == false) {
					msg= "Permanent Address Line 2 " + validation.MaxlengthcheckMSG100+",permanent_add_line2";
					return msg;
				}

		

				if (permanent_state == null || permanent_state.trim().equals("0")) {
					msg = "Please Select Permanent State,permanent_state";
					return msg;
				}
				if (permanent_district == null || permanent_district.trim().equals("0")) {
					msg = "Please Select Permanent District,permanent_district";
					return msg;
				}
				
				
				if (permanent_village == null || permanent_village.trim().equals("")) {
					msg = "Please Enter permanent City/Village,permanent_village";
					return msg;
				}
				if (validation.maxlengthcheck50(permanent_village) == false) {
					msg = "Permanent City/Village  " + validation.MaxlengthcheckMSG50 + ",permanent_village";
					return msg;
				}
				if (validation.isOnlyAlphabet(permanent_village) == false) {
					msg = "Permanent City/Village  " + validation.isOnlyAlphabetMSG + ",permanent_village";
					return msg;
				}
				

				if (permanent_lendmark == null || permanent_lendmark.trim().equals("")) {
					msg = "Please Enter Permanent Lendmark,permanent_lendmark";
					return msg;
				}
				if (validation.maxlengthcheck50(permanent_lendmark) == false) {
					msg = "Permanent Lendmark  " + validation.MaxlengthcheckMSG50 + ",permanent_lendmark";
					return msg;
				}
				if (validation.isOnlyAlphabet(permanent_lendmark) == false) {
					msg = "Permanent Lendmark  " + validation.isOnlyAlphabetMSG + ",permanent_lendmark";
					return msg;
				}
				if (permanent_pincode == null || permanent_pincode.trim().equals("")) {
					msg = "Please Enter Permanent Pin Code,permanent_pincode";
					return msg;
				}
				if (validation.maxlengthcheckpincode(permanent_pincode) == false) {
					msg = "Permanent Pin Code " + validation.MaxlengthcheckMSGpincode + ",permanent_pincode";
					return msg;
				}
				if (validation.minlengthcheckpincode(permanent_pincode) == false) {
					msg = "Permanent Pin Code " + validation.MinlengthcheckMSGpincode + ",permanent_pincode";
					return msg;
				}
				if (validation.isOnlyNumer(permanent_pincode) == true) {
					msg = "Permanent Pin Code " + validation.isOnlyNumerMSG + ",permanent_pincode";
					return msg;
				}

				
				// Present Address
				
				if (request.getParameter("check_address") == null) {

					if (present_house_no == null || present_house_no.trim().equals("")) {
						msg = "Please Enter Present House No/Name,present_house_no";
						return msg;
					}
					if (validation.isOnlyAlphabetNumberSLASH(present_house_no) == false) {
						msg = "Present House No/Name " + validation.isOnlyAlphabetNumberSLASHMSG + ",present_house_no";
						return msg;
					}
					if (validation.maxlengthcheck50(present_house_no) == false) {
						msg = "Present House No/Name " + validation.MaxlengthcheckMSG50 + ",present_house_no";
						return msg;

					}
					
					
					if (present_add_line1 == null || present_add_line1.trim().equals("")) {
						msg = "Please Enter Present Address Line 1,present_add_line1";
						return msg;
					}
					
					if (validation.isOnlyAlphabetNumber(present_add_line1) == false) {
						msg= "Present Address Line 1 " + validation.isOnlyAlphabetNumberMSG+",present_add_line1";
						return msg;
					}
					
					if (validation.maxlengthcheck100(present_add_line1) == false) {
						msg= "Present Address Line 1 " + validation.MaxlengthcheckMSG100+",present_add_line1";
						return msg;
					}

					if (present_add_line2 == null || present_add_line2.trim().equals("")) {

						msg = "Please Enter Present Address Line 2,present_add_line2";
						return msg;
					}
					if (validation.isOnlyAlphabetNumber(present_add_line2) == false) {
						msg= "Present Address Line 2 " + validation.isOnlyAlphabetNumberMSG+",present_add_line2";
						return msg;
					}
					
					if (validation.maxlengthcheck100(present_add_line2) == false) {
						msg= "Present Address Line 2 " + validation.MaxlengthcheckMSG100+",present_add_line2";
						return msg;
					}

					if (present_state == null || present_state.trim().equals("0")) {
						msg = "Please Select Present State,present_state";
						return msg;
					}
					if (present_district == null || present_district.trim().equals("0")) {
						msg = "Please Select Present District,present_district";
						return msg;
					}
					
					if (present_village == null || present_village.trim().equals("")) {
						msg = "Please Enter Present City/Village,present_village";
						return msg;
					}
					if (validation.maxlengthcheck50(present_village) == false) {
						msg = "Present City/Village " + validation.MaxlengthcheckMSG50 + ",present_village";
						return msg;
					}
					if (validation.isOnlyAlphabet(present_village) == false) {
						msg = "Present City/Village " + validation.isOnlyAlphabetMSG + ",present_village";
						return msg;
					}
					
					if (present_lendmark == null || present_lendmark.trim().equals("")) {
						msg = "Please Enter Present Lendmark,present_lendmark";
						return msg;
					}
					if (validation.maxlengthcheck50(present_lendmark) == false) {
						msg = "Present Lendmark  " + validation.MaxlengthcheckMSG50 + ",present_lendmark";
						return msg;
					}
					
					if (validation.isOnlyAlphabet(present_lendmark) == false) {
						msg = "Present Lendmark  " + validation.isOnlyAlphabetMSG + ",present_lendmark";
						return msg;
					}
					
					if (present_pincode == null || present_pincode.trim().equals("")) {
						msg = "Please Enter Present Pin Code,present_pincode";
						return msg;
					}

					if (validation.isOnlyNumer(present_pincode) == true) {
						msg = "Present Pin Code " + validation.isOnlyNumerMSG + ",present_pincode";
						return msg;
					}

					if (validation.maxlengthcheckpincode(present_pincode) == false) {
						msg = "Present Pin Code " + validation.MaxlengthcheckMSGpincode + ",present_pincode";
						return msg;
					}
					if (validation.minlengthcheckpincode(present_pincode) == false) {
						msg = "Present Pin Code " + validation.MinlengthcheckMSGpincode + ",present_pincode";
						return msg;
					}
					
				}
					
					
					
//corresponding address
					
					if (corre_house_no == null || corre_house_no.trim().equals("")) {
						msg = "Please Enter Corresponding House No/Name,corre_house_no";
						return msg;
					}
					if (validation.isOnlyAlphabetNumberSLASH(corre_house_no) == false) {
						msg = "Present House No/Name " + validation.isOnlyAlphabetNumberSLASHMSG + ",corre_house_no";
						return msg;
					}
					if (validation.maxlengthcheck50(corre_house_no) == false) {
						msg = "Corresponding House No/Name " + validation.MaxlengthcheckMSG50 + ",corre_house_no";
						return msg;

					}

				

					if (corre_add_line1 == null || corre_add_line1.trim().equals("")) {
						msg = "Please Enter Corresponding Address Line 1,corre_add_line1";
						return msg;
					}
					
					
					if (validation.isOnlyAlphabetNumber(corre_add_line1) == false) {
						msg= "Corresponding Address Line 1 " + validation.isOnlyAlphabetNumberMSG+",corre_add_line1";
						return msg;
					}
					
					if (validation.maxlengthcheck100(corre_add_line1) == false) {
						msg= "Corresponding Address Line 1 " + validation.MaxlengthcheckMSG100+",corre_add_line1";
						return msg;
					}
					
					
					
					
					
					if (corre_add_line2 == null || corre_add_line2.trim().equals("")) {
						msg = "Please Enter Corresponding Address Line 2,corre_add_line2";
						return msg;
					}
					if (validation.isOnlyAlphabetNumber(corre_add_line2) == false) {
						msg= "Corresponding Address Line 2 " + validation.isOnlyAlphabetNumberMSG+",corre_add_line2";
						return msg;
					}
					
					if (validation.maxlengthcheck100(corre_add_line2) == false) {
						msg= "Corresponding Address Line 2 " + validation.MaxlengthcheckMSG100+",corre_add_line2";
						return msg;
					}
					
					
					

					if (corre_state == null || corre_state.trim().equals("0")) {
						msg = "Please Select Corresponding State,corre_state";
						return msg;
					}
					if (corre_district == null || corre_district.trim().equals("0")) {
						msg = "Please Select Corresponding District,corre_district";
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
					if (corre_lendmark == null || corre_lendmark.trim().equals("")) {
						msg = "Please Enter Corresponding Lendmark,corre_lendmark";
						return msg;
					}
					if (validation.maxlengthcheck50(corre_lendmark) == false) {
						msg = "Corresponding Lendmark  " + validation.MaxlengthcheckMSG50 + ",corre_lendmark";
						return msg;
					}
					
					if (validation.isOnlyAlphabet(corre_lendmark) == false) {
						msg = "Corresponding Lendmark  " + validation.isOnlyAlphabetMSG + ",corre_lendmark";
						return msg;
					}
					
					if (corre_pincode == null || corre_pincode.trim().equals("")) {
						msg = "Please Enter Corresponding Pin Code,pers_present_pincode";
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

				
//				if (neet_roll_no == null || neet_roll_no.trim().equals("")) {
//					msg= "Please Enter NEET Roll No,neet_roll_no";
//					return msg;
//				}
//				if (validation.isOnlyNumer(neet_roll_no) == true) {
//					msg= "NEET Roll No" + validation.isOnlyNumerMSG+",neet_roll_no";
//					return msg;
//				}
//				if (validation.maxlengthcheckneetrank(neet_roll_no) == false) {
//					msg= " NEET Roll No" + validation.MaxlengthcheckMSGneetrank6+",neet_roll_no";
//					return msg;
//				}
//				
//				if (neet_application_no == null || neet_application_no.trim().equals("")) {
//					msg= "Please Enter NEET Application No,neet_application_no";
//					return msg;
//				}
//				if (validation.isOnlyNumer(neet_application_no) == true) {
//					msg= "NEET Application No" + validation.isOnlyNumerMSG+",neet_application_no";
//					return msg;
//				}
//				
//				if (validation.maxlengthcheckneetrank(neet_application_no) == false) {
//					msg= " NEET Application No" + validation.MaxlengthcheckMSGneetrank6+",neet_application_no";
//					return msg;
//				}
//				
//				if (neet_rank == null || neet_rank.trim().equals("")) {
//					msg = "Please Enter NEET All India Rank,neet_rank";
//					return msg;
//				}
//
//				if (validation.isOnlyNumer(neet_rank) == true) {
//					msg = "NEET All India Rank " + validation.isOnlyNumerMSG + ",neet_rank";
//					return msg;
//				}
//
//				if (validation.maxlengthcheckneetrank(neet_rank) == false) {
//					msg = "NEET All India Rank " + validation.MaxlengthcheckMSGneetrank6 + ",neet_rank";
//					return msg;
//				}
//
//				if (neet_marks == null || neet_marks.trim().equals("")) {
//					msg = "Please Enter NEET Marks,neet_marks";
//					return msg;
//				}
//
//				if (validation.isOnlyNumer(neet_marks) == true) {
//					msg = "NEET Marks " + validation.isOnlyNumerMSG + ",neet_marks";
//					return msg;
//				}
//
//				if (validation.maxlengthcheckneetmark(neet_marks) == false) {
//
//					msg = "NEET Marks " + validation.MaxlengthcheckMSGneetmark3 + ",neet_marks";
//					return msg;
//
//				}
//
//				if (neet_percentile == null || neet_percentile.trim().equals("")) {
//					msg = "Please Enter NEET Percentile,neet_percentile";
//					return msg;
//				}

				// if (validation.isOnlyNumer(neet_percentile) == true) {
				// ra.addAttribute("msg", "Neet Percentile" + validation.isOnlyNumerMSG);
				// return new ModelAndView("redirect:Personal_Details_PG_Url"); // }

//				if (validation.maxlengthcheckneetpercentile(neet_percentile) == false) {
//					msg = "NEET Percentile " + validation.MaxlengthcheckMSGneetpercentile5 + ",neet_percentile";
//					return msg;
//				}
				
				
				System.err.println("corre_house_no "+corre_house_no+" corre_add_line1 "+corre_add_line1);
				System.err.println("corre_add_line2 "+corre_add_line2+" corre_state "+corre_state);
				System.err.println("corre_state "+corre_state+" corre_district "+corre_district);
				System.err.println("corre_village "+corre_village+" corre_pincode "+corre_pincode);
				System.err.println("corre_lendmark "+corre_lendmark);
				
				String aadhar_no = aadhar_no1+aadhar_no2+aadhar_no3;

				System.err.println("e_id----->    sss  "+e_id);
				
				EDU_PG_REG_PERSONAL_DETAILS pda= (EDU_PG_REG_PERSONAL_DETAILS)sessionHQL.get(EDU_PG_REG_PERSONAL_DETAILS.class, Integer.parseInt(e_id));
	    	 
	    	    pda.setFirst_name(first_name);
	    		pda.setMiddel_name(middel_name);
				pda.setSurname(surname);
				pda.setFather_name(father_name);
				pda.setMother_name(mother_name);
				pda.setDate_of_birth(dob_i);
				pda.setGender(Integer.parseInt(gender));
				pda.setMob_no(mob_no);
//				pda.setEmail(email);
				pda.setCategory(Integer.parseInt(category));
				pda.setReligion(Integer.parseInt(religion));
				pda.setMarital_status(Integer.parseInt(marital_status));
				pda.setNationality(Integer.parseInt(nationality));

				pda.setAadhar_no(aadhar_no);
				pda.setPermanent_house_no(permanent_house_no);
				pda.setPermanent_add_line1(permanent_add_line1);
				pda.setPermanent_add_line2(permanent_add_line2);
				pda.setPermanent_state(Integer.parseInt(permanent_state));
				pda.setPermanent_district(Integer.parseInt(permanent_district));
				pda.setPermanent_village(permanent_village);
				pda.setPermanent_pincode(Integer.parseInt(permanent_pincode));
				pda.setPermanent_lendmark(permanent_lendmark);
				
				pda.setPresent_house_no(present_house_no);
				pda.setPresent_add_line1(present_add_line1);
				pda.setPresent_add_line2(present_add_line2);
				pda.setPresent_state(Integer.parseInt(present_state));
				pda.setPresent_district(Integer.parseInt(present_district));
				pda.setPresent_village(present_village);
				pda.setPresent_pincode(Integer.parseInt(present_pincode));
				pda.setPresent_lendmark(present_lendmark);
				
				pda.setCorre_house_no(corre_house_no);
				pda.setCorre_add_line1(corre_add_line1);
				pda.setCorre_add_line2(corre_add_line2);
				pda.setCorre_state(Integer.parseInt(corre_state));
				pda.setCorre_district(corre_district);
				pda.setCorre_village(corre_village);
				pda.setCorre_pincode(Integer.parseInt(corre_pincode));
				pda.setCorre_lendmark(corre_lendmark);

				pda.setModified_by(username);
				pda.setModified_date(date);
	    	 
		    	msg = da.getUpdatePerDetails_pg(pda);
		    	sessionHQL.close();
		    	return msg;
		    	
		 	}
	     
}
