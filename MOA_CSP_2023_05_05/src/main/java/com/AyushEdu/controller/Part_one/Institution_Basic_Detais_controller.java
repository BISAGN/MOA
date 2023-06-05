package com.AyushEdu.controller.Part_one;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.Clg_Reg_Inst_Info.CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY;
import com.AyushEdu.Models.Clg_Reg_Inst_Info.CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY_CHILD;
import com.AyushEdu.Models.Clg_Reg_Inst_Info.CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY_PG;
import com.AyushEdu.Models.Clg_Reg_Inst_Info.CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY_UG;
import com.AyushEdu.Models.Clg_Reg_Inst_Info.CLG_REG_INST_INFO_DETAILS_OF_LAND;
import com.AyushEdu.Models.Clg_Reg_Inst_Info.CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS;
import com.AyushEdu.Models.Clg_Reg_Inst_Info.CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS_CHILD;
import com.AyushEdu.Models.Clg_Reg_Inst_Info.CLG_REG_INST_INFO_INFORMATION_OF_CONNECTIVITY;
import com.AyushEdu.Models.Clg_Reg_Inst_Info.CLG_REG_INST_INFO_INSTITUTION_BASIC_DETAILS;
import com.AyushEdu.Models.Clg_Reg_Inst_Info.CLG_REG_INST_INFO_INTAKE_CAPACITY_PG;
import com.AyushEdu.Models.Clg_Reg_Inst_Info.CLG_REG_INST_INFO_POLICE_STATION_DETAILS;
import com.AyushEdu.Models.Clg_Reg_Inst_Info.CLG_REG_INST_INFO_UNDERTAKING_REPORTS;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_COLLEGE_STAFF_LIST_UPLOAD_DOC;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_INFRA_CENTRAL_LIBRARY_CHILD;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_INFRA_CONSTRUCTED_AREA;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_INFRA_CONSTRUCTED_AREA_DEPT;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Staff_List_DAO;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Dao;
import com.AyushEdu.validation.ValidationController;



@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Institution_Basic_Detais_controller {
	
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Autowired
	Institution_Basic_Details_Dao ibdao;
	
	@Autowired
	private Clg_Reg_College_Staff_List_DAO CSLDao;
	
	@Autowired
	CommonController common;
	
	@Autowired
	ValidationController validation;
	
	@Autowired
	Commondao commondao;
	
	@RequestMapping(value = "admin/basics_information", method = RequestMethod.GET)
	public ModelAndView basics_information(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
//		if(!ibdao.getpid_from_userid(userid).get(0).get(0).equals("")) {
//			request.getSession().setAttribute("super_id", ibdao.getpid_from_userid(userid).get(0).get(0));
//
//		}else {
//			Mmap.put("msg", "Please Save Basic details First");
//			return new ModelAndView("redirect:basics_information");
//		}
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institude = String.valueOf(ea.getInstitute_id());
		String role = session.getAttribute("role").toString();
		
		String username = session.getAttribute("roleloginName").toString();
		System.err.println("-----1 APR-------"+username);
		
		

//String superid = session.getAttribute("super_id").toString();
//System.err.println("=============SID=============="+superid);
		
//		CLG_REG_INST_INFO_INSTITUTION_BASIC_DETAILS pers_p =new CLG_REG_INST_INFO_INSTITUTION_BASIC_DETAILS();
//		int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
//		int p_id=Integer.parseInt(request.getParameter("inst_basic_hidden"));
		
//		System.err.println("iddddddddddddddddddddd"+p_id);
		if(role=="Institute_NCH") {
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int parent_id = (int) sessionHQL
				.createQuery("select id from CLG_REG_INST_INFO_INSTITUTION_BASIC_DETAILS where inst_id=:inst_id")
				.setParameter("inst_id", Integer.parseInt(institude))
				.uniqueResult();
		Mmap.put("parent_id", parent_id);
		}
		
		Mmap.put("msg", msg);
		Mmap.put("institude", institude);
		
		Mmap.put("dataparent", ibdao.getAllPersdetails(Integer.parseInt(institude)));
		Mmap.put("datainfoconnect", ibdao.getAllinfo_connectivity(Integer.parseInt(institude)));
		Mmap.put("datapolice", ibdao.getAllinfo_police_st(Integer.parseInt(institude)));
		Mmap.put("datainstdtl", ibdao.getAllinfo_inst_dtl(Integer.parseInt(institude)));
		Mmap.put("datadtlland", ibdao.getAllinfo_dtl_land(Integer.parseInt(institude)));
		Mmap.put("dataundertake", ibdao.getAllinfo_undertaling_repo(Integer.parseInt(institude)));
		Mmap.put("dataintak_child", ibdao.getAllinfo_intake_cap_child(Integer.parseInt(institude)));
		
	//	Mmap.put("dataquali", ibdao.getAllinfo_quali_inst(Integer.parseInt(institude)));
		Mmap.put("dataforPG", ibdao.getAllSubjectforPG());
		Mmap.put("dataforUG", ibdao.getAllSubjectforUG(Integer.parseInt(institude)));
		Mmap.put("login_name", session.getAttribute("roleloginName").toString());
		Mmap.put("dataforugsize", ibdao.getAllCourse_UG(Integer.parseInt(institude)));
		Mmap.put("dataforpgsize", ibdao.getAllCourse_PG());
		Mmap.put("dataforinstnc", ibdao.getinstName_Code(Integer.parseInt(institude)));
		Mmap.put("GetDocument_Details", CSLDao.GetDocument_Details(institude));
		
		
		
		
		ArrayList<ArrayList<String>> alist2 = new ArrayList<ArrayList<String>>();

		
		
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date date = new Date();
		String strDate = datePickerFormat.format(date); 
		String year = strDate.substring(6, 10);
		List a = new ArrayList();
		
			if(ibdao.getdate(Integer.parseInt(institude)).size()!=0) {
			
			Mmap.put("getdate", ibdao.getdate(Integer.parseInt(institude)));
			
		
		for (int i = 0; i < 10; i++) {
			ArrayList<String> list = new ArrayList<String>();

			System.out.println("year "+Math.subtractExact(Integer.parseInt(year), i));
			System.out.println("affilating "+Integer.parseInt(ibdao.getdate(Integer.parseInt(institude)).get(0).get(0)));
			if(Math.subtractExact(Integer.parseInt(year), i) >= Integer.parseInt(ibdao.getdate(Integer.parseInt(institude)).get(0).get(0)) ) {
			list.add(String.valueOf( Math.subtractExact(Integer.parseInt(year), i)));
			alist2.add(list);
			}
			

		}
		Mmap.put("year", alist2);

		System.err.println("admitted_year=============="+alist2);

		
		
		}
		
		
		
//		for (int i = 0; i < 10; i++) {
//			a.add(Math.subtractExact(Integer.parseInt(year), i));
//		}
		
		System.err.println("admitted_year=============="+a);

		//Mmap.put("year", a);
		
		
		
		Mmap.put("TypeOfDegree", common.gettype_of_degree(sessionFactory));
		
			

		return new ModelAndView("basics_information");
	}
	
	
	
	 @PostMapping(value = "/basics_information_Action")
	 public @ResponseBody Map<String, String> basics_information_Action(ModelMap Mmap, HttpSession session,
			 HttpServletRequest request,RedirectAttributes ra,@RequestParam(value = "trustreg_doc", required = false) MultipartFile trusted_dc,
			 @RequestParam(value = "trustdeed_doc", required = false) MultipartFile trusteed_dc,
			 @RequestParam(value = "furnish_bank_doc", required = false) MultipartFile furnish_bank_dc,
			 @RequestParam(value = "noc_state_doc", required = false) MultipartFile noc_state_dc,
			 @RequestParam(value = "affilat_doc", required = false) MultipartFile affilat_dc,
			 @RequestParam(value = "uni_app_doc", required = false) MultipartFile uni_app_dc,
			 @RequestParam(value = "inspection_acade_yr_doc", required = false) MultipartFile inspection_acade_yr_dc) throws IOException, ParseException {
	 
	
	 
		Date date = new Date();
		String username = session.getAttribute("username").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String role = session.getAttribute("roleStaff_lvl").toString();
		System.err.println("role for regi-----------" + role + "---");

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		
		Map<String, String> data = new HashMap<>();

		
		try {
			
			int p_id=Integer.parseInt(request.getParameter("inst_basic_hidden"));
			
			
				String per_add_line1 = request.getParameter("per_add_line1");
				
				String per_add_line2 = request.getParameter("per_add_line2");
				String per_state = request.getParameter("per_state");
				String per_district = request.getParameter("per_district");
				String city = request.getParameter("city");
				String per_pincode = request.getParameter("per_pincode");
				String cand_prefix_clg = request.getParameter("cand_prefix_clg");
				
				
			//	System.err.println("----------"+dob);
				String alt_mo_no_clg = request.getParameter("alt_mo_no_clg");
				String cand_prefix_hospi = request.getParameter("cand_prefix_hospi");
				String alt_mo_no_hosp = request.getParameter("alt_mo_no_hosp");
				String mobile_no_addr = request.getParameter("mobile_no_addr");
				String fax_code = request.getParameter("fax_code");
				String fax_no = request.getParameter("fax_no");
				String email = request.getParameter("email");
				String website = request.getParameter("website");
				
				String institution_type = request.getParameter("institution_type");
				String managing_body = request.getParameter("managing_body");
				String management_contact = request.getParameter("management_contact");
				String mang_per_add_line1 = request.getParameter("mang_per_add_line1");
				String mang_per_add_line2 = request.getParameter("mang_per_add_line2");
				String mang_per_state = request.getParameter("mang_per_state");
				String mang_per_district = request.getParameter("mang_per_district");
				String mang_city = request.getParameter("mang_city");
				String mang_office_code = request.getParameter("mang_office_code");
				String mang_office_phone = request.getParameter("mang_office_phone");
				String mang_residence_code = request.getParameter("mang_residence_code");
				String mang_residence_phone = request.getParameter("mang_residence_phone");
				String mobile_no_mngmt_cont_dt = request.getParameter("mobile_no_mngmt_cont_dt");
				
				String email_mgmt_contact_dt = request.getParameter("email_mgmt_contact_dt");
				String name_of_society = request.getParameter("name_of_society");
				String s_registration_no = request.getParameter("s_registration_no");
				String s_establishment = request.getParameter("s_establishment");
				String date_permission_s_govt = request.getParameter("date_permission_s_govt");
				String date_permission_c_govt = request.getParameter("date_permission_c_govt");
				String university_affiliated = request.getParameter("university_affiliated");
				String date_affili_uni = request.getParameter("date_affili_uni");
				String establishment_college = request.getParameter("establishment_college");
				String date_consent_affili_uni = request.getParameter("date_consent_affili_uni");
				String date_last_affili_uni = request.getParameter("date_last_affili_uni");
				
				String trustreg_doc  = "trustreg_doc";
				String trustdeed_doc  = "trustdeed_doc";
				String furnish_bank_doc  = "furnish_bank_doc";
				String noc_state_doc  = "noc_state_doc";
				String affilat_doc  = "affilat_doc";
				String uni_app_doc  = "uni_app_doc";
				String inspection_acade_yr_doc  = "inspection_acade_yr_doc";
				
				
				
				
				if (per_add_line1 == null || per_add_line1.trim().equals("")) {
					data.put("msg", "Please Enter  Address-Line-1 In INstitution Address Detail");
					return data;
				}
				if (validation.maxlengthcheck100(per_add_line1) == false) {
					data.put("msg", "Correspondence Address-Line-1 In INstitution Address Detail " + validation.MaxlengthcheckMSG100);
					return data;
				}
				if (per_add_line2 == null || per_add_line2.trim().equals("")) {
					data.put("msg", "Please Enter Address-Line-2 In INstitution Address Detail");
					return data;
				}
				if (validation.maxlengthcheck100(per_add_line2) == false) {
					data.put("msg", " Please Enter Address-Line-2 In INstitution Address Detail" + validation.MaxlengthcheckMSG100);
					return data;
				}
				if (per_state == null || per_state.trim().equals("0")) {
					data.put("msg", "Please Select State  In INstitution Address Detail");
					return data;
				}
				if (city == null || city.trim().equals("")) {
					data.put("msg", "Please Enter  City In INstitution Address Detail");
					return data;
				}
				if (validation.maxlengthcheck100(city) == false) {
					data.put("msg", "City In INstitution Address Detail " + validation.MaxlengthcheckMSG100);
					return data;
				}
				if (validation.isOnlyAlphabet(city) == false) {
					data.put("msg", "City In INstitution Address Detail " + validation.isOnlyAlphabetMSG);
					return data;
				}
				if (per_pincode == null || per_pincode.trim().equals("")) {
					data.put("msg", "Please Enter  Pin Code In INstitution Address Detail");
					return data;
				}
				if (validation.maxlengthcheckpincode(per_pincode) == false) {
					data.put("msg", " Address Pin Code In INstitution Address Detail" + validation.MaxlengthcheckMSGpincode);
					return data;
				}
				if (validation.minlengthcheckpincode(per_pincode) == false) {
					data.put("msg", " Address Pin Code In INstitution Address Detail" + validation.MinlengthcheckMSGpincode);
					return data;
				}
				if (validation.isOnlyNumer(per_pincode) == true) {
					data.put("msg", " Address Pin Code In INstitution Address Detail" + validation.isOnlyNumerMSG);
					return data;
				}
				if (alt_mo_no_clg == null || alt_mo_no_clg.trim().equals("")) {
					data.put("msg", "Please Enter College Phone Number In INstitution Address Detail");
					return data;
				}
				if (validation.isOnlyNumer(alt_mo_no_clg) == true) {
					data.put("msg", "College Phone Number In INstitution Address Detail" + validation.isOnlyNumerMSG);
					return data;
				}
				if (validation.maxlengthcheck10(alt_mo_no_clg) == false) {
					data.put("msg", "College Phone Number In INstitution Address Detail" + validation.MaxlengthcheckMSG10);
					return data;
				}
//				if (validation.isValidMobileNo(alt_mo_no_clg) == false) {
//					data.put("msg", "College Phone Number In INstitution Address Detail " + validation.isOnlyMobilenumberMSG);
//					return data;
//				}
				if (alt_mo_no_hosp == null || alt_mo_no_hosp.trim().equals("")) {
					data.put("msg", "Please Enter Hospital Phone Number In INstitution Address Detail");
					return data;
				}
				if (validation.isOnlyNumer(alt_mo_no_hosp) == true) {
					data.put("msg", "Hospital Phone Number In INstitution Address Detail" + validation.isOnlyNumerMSG);
					return data;
				}
				if (validation.maxlengthcheck10(alt_mo_no_hosp) == false) {
					data.put("msg", "Hospital Phone Number In INstitution Address Detail" + validation.MaxlengthcheckMSG10);
					return data;
				}
//				if (validation.isValidMobileNo(alt_mo_no_hosp) == false) {
//					data.put("msg", "Hospital Phone Number In INstitution Address Detail " + validation.isOnlyMobilenumberMSG);
//					return data;
//				}
				if (mobile_no_addr == null || mobile_no_addr.trim().equals("")) {
					data.put("msg", "Please Enter Mobile Number In INstitution Address Detail");
					return data;
				}
				if (validation.isOnlyNumer(mobile_no_addr) == true) {
					data.put("msg", "Please Enter Mobile Number In INstitution Address Detail" + validation.isOnlyNumerMSG);
					return data;
				}
				if (validation.maxlengthcheck10(mobile_no_addr) == false) {
					data.put("msg", "Mobile Number In INstitution Address Detail" + validation.MaxlengthcheckMSG10);
					return data;
				}
				if (validation.isValidMobileNo(mobile_no_addr) == false) {
					data.put("msg", "Mobile Number In INstitution Address Detail " + validation.isOnlyMobilenumberMSG);
					return data;
				}
				if (email == null || email.trim().equals("")) {
					data.put("msg", "Please Enter Email Address In INstitution Address Detail");
					return data;
				}
				if (validation.maxlengthcheck70(email) == false) {
					data.put("msg", "Email Address In INstitution Address Detail " + validation.MaxlengthcheckMSG70);
					return data;
				}
				
				if (managing_body == null || managing_body.trim().equals("")) {
					data.put("msg", "Please Enter Name of the Managing Body In Management contact Detail.");
					return data;
				}
//				if (validation.maxlengthcheck50(managing_body) == false) {
//					data.put("msg", "Name of the Managing Body" + validation.MaxlengthcheckMSG50);
//					return data;
//				}
				if (validation.isOnlyAlphabet(managing_body) == false) {
					data.put("msg", "Name of the Managing Body " + validation.isOnlyAlphabetMSG);
					return data;
				}
				if (management_contact == null || management_contact.trim().equals("")) {
					data.put("msg", "Please EnterName of the Management Contact In Management contact Detail.");
					return data;
				}
				if (validation.maxlengthcheck100(management_contact) == false) {
					data.put("msg", "Name of the Management Contact" + validation.MaxlengthcheckMSG100);
					return data;
				}
//				if (validation.isOnlyAlphabet(management_contact) == false) {
//					data.put("msg", "Name of the Management Contact " + validation.isOnlyAlphabetMSG);
//					return data;
//				}
				if (mang_per_add_line1 == null || mang_per_add_line1.trim().equals("")) {
					data.put("msg", "Please Enter Address Line 1 In Management contact Detail");
					return data;
				}
				if (validation.maxlengthcheck100(mang_per_add_line1) == false) {
					data.put("msg", " Please Enter Address-Line-1 In Management contact Detail" + validation.MaxlengthcheckMSG100);
					return data;
				}
				if (mang_per_add_line2 == null || mang_per_add_line2.trim().equals("")) {
					data.put("msg", "Please Enter Address Line 2 In Management contact Detail");
					return data;
				}
				if (validation.maxlengthcheck100(mang_per_add_line2) == false) {
					data.put("msg", " Please Enter Address-Line-2 In Management contact Detail" + validation.MaxlengthcheckMSG100);
					return data;
				}
				if (mang_city == null || mang_city.trim().equals("")) {
					data.put("msg", "Please Enter City In Management contact Detail");
					return data;
				}
				if (validation.maxlengthcheck100(mang_city) == false) {
					data.put("msg", " City In Management contact Detail " + validation.MaxlengthcheckMSG100);
					return data;
				}
				if (validation.isOnlyAlphabet(mang_city) == false) {
					data.put("msg", " City In Management contact Detail " + validation.isOnlyAlphabetMSG);
					return data;
				}
				if (email_mgmt_contact_dt == null || email_mgmt_contact_dt.trim().equals("")) {
					data.put("msg", "Please Enter Email Address In Management contact Detail");
					return data;
				}
				if (validation.maxlengthcheck70(email_mgmt_contact_dt) == false) {
					data.put("msg", "Email Address In Management contact Detail " + validation.MaxlengthcheckMSG70);
					return data;
				}
				if (name_of_society == null || name_of_society.trim().equals("")) {
					data.put("msg", "Please Enter Name of Society/Trust/Government In Basic Detail.");
					return data;
				}
				if (validation.maxlengthcheck50(name_of_society) == false) {
					data.put("msg", "Name of the Managing Body" + validation.MaxlengthcheckMSG50);
					return data;
				}
				if (validation.isOnlyAlphabet(name_of_society) == false) {
					data.put("msg", "Name of the Managing Body " + validation.isOnlyAlphabetMSG);
					return data;
				}
				if (s_registration_no == null || s_registration_no.trim().equals("")) {
					data.put("msg", "Please Enter Society/Trust Registration Number In Basic Detail.");
					return data;
				}
				if (validation.maxlengthcheck50(s_registration_no) == false) {
					data.put("msg", "Society/Trust Registration Number  In Basic Detail" + validation.MaxlengthcheckMSG50);
					return data;
				}
				if (s_establishment == null || s_establishment.trim().equals("")) {
					data.put("msg", "Please Enter Year of Establishment of the Society/Trust In Basic Detail.");
					return data;
				}
				
				if (date_permission_s_govt == null || date_permission_s_govt.trim().equals("") || date_permission_s_govt.equals("DD/MM/YYYY")) {
					data.put("msg", "Please Enter Date of affiliation of University");
					return data;
				}
				
				if (date_permission_c_govt == null || date_permission_c_govt.trim().equals("") || date_permission_c_govt.equals("DD/MM/YYYY")) {
					data.put("msg", "Please Enter Date of First Permission of CCH/NCH Central Govt.");
					return data;
				}
				
				
				if (university_affiliated == null || university_affiliated.trim().equals("")) {
					data.put("msg", "Please Enter Year of Establishment of the Society/Trust In Basic Detail.");
					return data;
				}
				
//				if (validation.maxlengthcheck50(university_affiliated) == false) {
//					data.put("msg", "Name of the University affiliated" + validation.MaxlengthcheckMSG50);
//					return data;
//				}
				
				if (date_affili_uni == null || date_affili_uni.trim().equals("") || date_affili_uni.equals("DD/MM/YYYY")) {
					data.put("msg", "Please Enter Date of affiliation of University");
					return data;
				}
				
				if (establishment_college == null || establishment_college.trim().equals("")) {
					data.put("msg", "Please Enter Year of Establishment of the College In Basic Detail.");
					return data;
				}
				
				if (date_consent_affili_uni == null || date_consent_affili_uni.trim().equals("") || date_consent_affili_uni.equals("DD/MM/YYYY")) {
					data.put("msg", "Please Enter Date of affiliation of University");
					return data;
				}
				
				if (date_last_affili_uni == null || date_last_affili_uni.trim().equals("") || date_last_affili_uni.equals("DD/MM/YYYY")) {
					data.put("msg", "Please Enter Date of last continuous affiliation University");
					return data;
				}
				
				if (trustreg_doc == null || trustreg_doc.trim().equals("")) {
					data.put("msg", "Please Upload Registration certificate of society/trust In Basic Detail.");
					return data;
				}
				
				if (trustdeed_doc == null || trustdeed_doc.trim().equals("")) {
					data.put("msg", "Please Upload A copy of the society/trust deed In Basic Detail.");
					return data;
				}
				
//				if (id == 0) {
//					
//					if (file.getOriginalFilename().isEmpty()) {
//						ra.addAttribute("msg", "Please Upload Signature");
//						return new ModelAndView("redirect:doc_uploadNcism_Url");
//					}
//					if (file3.getOriginalFilename().isEmpty()) {
//						ra.addAttribute("msg", "Please Upload Photo");
//						return new ModelAndView("redirect:doc_uploadNcism_Url");
//					}
//					
//					if (file4.getOriginalFilename().isEmpty() && late_admission_status.equals("1")) {
//						ra.addAttribute("msg", "Please Upload Court Order");
//						return new ModelAndView("redirect:doc_uploadNcism_Url");
//					}
//				
//				}
				
				
				if (!trusted_dc.isEmpty()) {
					trustreg_doc = upload_imagemethod(request,trusted_dc,session, trustreg_doc);
				}
				else {
					trustreg_doc = request.getParameter("hid_trustreg_doc");
				}
				
				if (!trusteed_dc.isEmpty()) {
					trustdeed_doc = upload_imagemethod(request,trusteed_dc,session, trustdeed_doc);
				}
				else {
					trustdeed_doc = request.getParameter("hid_trustdeed_doc");
				}
				
				if (!furnish_bank_dc.isEmpty()) {
					furnish_bank_doc = upload_imagemethod(request,furnish_bank_dc,session, furnish_bank_doc);
				}
				else {
					furnish_bank_doc = request.getParameter("hid_furnish_bank_doc");
				}
				
				if (!noc_state_dc.isEmpty()) {
					noc_state_doc = upload_imagemethod(request,noc_state_dc,session, noc_state_doc);
				}
				else {
					noc_state_doc = request.getParameter("hid_noc_state_doc");
				}
				
				if (!affilat_dc.isEmpty()) {
					affilat_doc = upload_imagemethod(request,affilat_dc,session, affilat_doc);
				}
				else {
					affilat_doc = request.getParameter("hid_affilat_doc");
				}
				if (!uni_app_dc.isEmpty()) {
					uni_app_doc = upload_imagemethod(request,uni_app_dc,session, uni_app_doc);
				}
				else {
					uni_app_doc = request.getParameter("hid_uni_app_doc");
				}
				if (!inspection_acade_yr_dc.isEmpty()) {
					inspection_acade_yr_doc = upload_imagemethod(request,inspection_acade_yr_dc,session, inspection_acade_yr_doc);
				}
				else {
					inspection_acade_yr_doc = request.getParameter("hid_inspection_acade_yr_doc");
				}
				

				
				if(p_id==0) {
					CLG_REG_INST_INFO_INSTITUTION_BASIC_DETAILS rd =new CLG_REG_INST_INFO_INSTITUTION_BASIC_DETAILS();
//					

				rd.setAddress_line1(per_add_line1);
				rd.setAddress_line2(per_add_line2);
				rd.setState_id(Integer.parseInt(per_state));
				rd.setDistrict_inst_add(Integer.parseInt(per_district));
				rd.setCity(city);
				rd.setPincode(Integer.parseInt(per_pincode));
				rd.setCollege_phone_code(Integer.parseInt(cand_prefix_clg));
				rd.setCollege_phone_no(BigInteger.valueOf(Long.parseLong(alt_mo_no_clg)));
				rd.setHospital_phone_code(Integer.parseInt(cand_prefix_hospi));
				rd.setHospital_phone_no(BigInteger.valueOf(Long.parseLong(alt_mo_no_hosp)));
				rd.setMobile_no(BigInteger.valueOf(Long.parseLong(mobile_no_addr)));
				if(fax_no != null && !fax_no.equals("")) {
				rd.setFax_no(fax_no);
				}
				rd.setEmail_id(email);
				rd.setWebsite(website);
				rd.setInstitute_type(Integer.parseInt(institution_type));
				rd.setName_of_managing_body(managing_body);
				rd.setName_of_management_contact(management_contact);
				rd.setMngt_address_line1(mang_per_add_line1);
				rd.setMngt_address_line2(mang_per_add_line2);
				rd.setMngt_city(mang_city);
				rd.setMngt_state(Integer.parseInt(mang_per_state));
				rd.setMngt_district(Integer.parseInt(mang_per_district));
				rd.setMngt_office_phone_no(BigInteger.valueOf(Long.parseLong(mang_office_phone)));
				if(mang_residence_phone != null && !mang_residence_phone.equals("")) {
				rd.setMngt_residence_phone_no(BigInteger.valueOf(Long.parseLong(mang_residence_phone)));
				}
				rd.setMngt_mobile_no(BigInteger.valueOf(Long.parseLong(mobile_no_mngmt_cont_dt)));
				rd.setMngt_email_id(email_mgmt_contact_dt);
				rd.setName_of_society(name_of_society);
				rd.setSociety_reg_no(s_registration_no);
				rd.setYear_of_establish_society(s_establishment);
				if(fax_code != null && !fax_code.equals("")) {
				rd.setFax_code(Integer.parseInt(fax_code));
				}
				rd.setMang_office_code(Integer.parseInt(mang_office_code));
				if(mang_residence_code != null && !mang_residence_code.equals("")) {
				rd.setMang_residence_code(Integer.parseInt(mang_residence_code));
				}
				Date permission_of_state=null;
				if(!date_permission_s_govt.equals("DD/MM/YYYY") && !date_permission_s_govt.equals("")) {
					permission_of_state=datePickerFormat.parse(date_permission_s_govt);
				}
				rd.setDop_state_govn(permission_of_state);
				
				Date permission_of_central=null;
				if(!date_permission_c_govt.equals("DD/MM/YYYY") && !date_permission_c_govt.equals("")) {
					permission_of_central=datePickerFormat.parse(date_permission_c_govt);
				}
				rd.setDop_central_govn(permission_of_central);
				rd.setName_of_uni_affilate(university_affiliated);
				
				
				Date date_of_first_affiliation_of_university=null;
				if(!date_affili_uni.equals("DD/MM/YYYY") && !date_affili_uni.equals("")) {
					date_of_first_affiliation_of_university=datePickerFormat.parse(date_affili_uni);
				}
				rd.setDoa_university(date_of_first_affiliation_of_university);
				rd.setYear_of_establish_college(establishment_college);
				
				Date date_of_concent_affiliation_of_university=null;
				if(!date_consent_affili_uni.equals("DD/MM/YYYY") && !date_consent_affili_uni.equals("")) {
					date_of_concent_affiliation_of_university=datePickerFormat.parse(date_consent_affili_uni);
				}
				rd.setDoc_affilation_university(date_of_concent_affiliation_of_university);
				
				Date date_of_last_affilia_of_university=null;
				if(!date_last_affili_uni.equals("DD/MM/YYYY") && !date_last_affili_uni.equals("")) {
					date_of_last_affilia_of_university=datePickerFormat.parse(date_last_affili_uni);
				}
				rd.setDoc_last_aff_university(date_of_last_affilia_of_university);
				
				
				if(!trustreg_doc.equals("")) {
					rd.setReg_certi_of_society(trustreg_doc);
					}
					if(!trustdeed_doc.equals("")) {
					rd.setCopy_of_society(trustdeed_doc);
					}
					if(!furnish_bank_doc.equals("")) {
					rd.setUndertaking_letter_furnish_bank_guarantee(furnish_bank_doc);
					}
					if(!noc_state_doc.equals("")) {
						rd.setNoc_doc_state_gov(noc_state_doc);
					}
					if(!affilat_doc.equals("")) {
						rd.setAffiliaion_doc_concern_uni(affilat_doc);
					}
					if(!uni_app_doc.equals("")) {
						rd.setUni_app_letter(uni_app_doc);
					}
					if(!inspection_acade_yr_doc.equals("")) {
						rd.setInspection_last_academic_yr(inspection_acade_yr_doc);
					}
				
				

				rd.setUserid(Integer.parseInt(userid));
				
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());
				rd.setInst_id(Integer.parseInt(institude));
				
				rd.setCreated_by(Integer.parseInt(userid));
				rd.setCreated_date(date);
				rd.setStatus(0);
				
				p_id = (int) sessionHQL.save(rd);
				
			//	session.setAttribute("super_id", p_id);
				request.getSession().setAttribute("super_id", p_id);
				
				 data.put("msg", "Save as Draft Successfully");
				sessionHQL.flush();
				sessionHQL.clear();
			}
				
				else {
//				
//				
//				
//				System.err.println("id for update-----else------" + id_for_update + "---");
				CLG_REG_INST_INFO_INSTITUTION_BASIC_DETAILS urd = (CLG_REG_INST_INFO_INSTITUTION_BASIC_DETAILS) sessionHQL.get(CLG_REG_INST_INFO_INSTITUTION_BASIC_DETAILS.class,
						(p_id));
//				
				urd.setAddress_line1(per_add_line1);
				urd.setAddress_line2(per_add_line2);
				urd.setState_id(Integer.parseInt(per_state));
				urd.setDistrict_inst_add(Integer.parseInt(per_district));
				urd.setCity(city);
				urd.setPincode(Integer.parseInt(per_pincode));	
				urd.setCollege_phone_code(Integer.parseInt(cand_prefix_clg));
				urd.setCollege_phone_no(BigInteger.valueOf(Long.parseLong(alt_mo_no_clg)));
				urd.setHospital_phone_code(Integer.parseInt(cand_prefix_hospi));
				urd.setHospital_phone_no(BigInteger.valueOf(Long.parseLong(alt_mo_no_hosp)));
				urd.setMobile_no(BigInteger.valueOf(Long.parseLong(mobile_no_addr)));
				if(fax_no != null && !fax_no.equals("")) {
				urd.setFax_no(fax_no);
				}
				urd.setEmail_id(email);
				urd.setWebsite(website);
				urd.setInstitute_type(Integer.parseInt(institution_type));
				urd.setName_of_managing_body(managing_body);
				urd.setName_of_management_contact(management_contact);
				urd.setMngt_address_line1(mang_per_add_line1);
				urd.setMngt_address_line2(mang_per_add_line2);
				urd.setMngt_city(mang_city);
				urd.setMngt_state(Integer.parseInt(mang_per_state));
				urd.setMngt_district(Integer.parseInt(mang_per_district));
				urd.setMngt_office_phone_no(BigInteger.valueOf(Long.parseLong(mang_office_phone)));
				if(mang_residence_phone != null && !mang_residence_phone.equals("")) {
				urd.setMngt_residence_phone_no(BigInteger.valueOf(Long.parseLong(mang_residence_phone)));
				}
				urd.setMngt_mobile_no(BigInteger.valueOf(Long.parseLong(mobile_no_mngmt_cont_dt)));
				urd.setMngt_email_id(email_mgmt_contact_dt);
				urd.setName_of_society(name_of_society);
				urd.setSociety_reg_no(s_registration_no);
				urd.setYear_of_establish_society(s_establishment);
				if(fax_code != null && !fax_code.equals("")) {
				urd.setFax_code(Integer.parseInt(fax_code));
				}
				urd.setMang_office_code(Integer.parseInt(mang_office_code));
				if(mang_residence_code != null && !mang_residence_code.equals("")) {
				urd.setMang_residence_code(Integer.parseInt(mang_residence_code));
				}
				
				Date permission_of_state=null;
				if(!date_permission_s_govt.equals("DD/MM/YYYY") && !date_permission_s_govt.equals("")) {
					permission_of_state=datePickerFormat.parse(date_permission_s_govt);
				}
				urd.setDop_state_govn(permission_of_state);
				
				Date permission_of_central=null;
				if(!date_permission_c_govt.equals("DD/MM/YYYY") && !date_permission_c_govt.equals("")) {
					permission_of_central=datePickerFormat.parse(date_permission_c_govt);
				}
				urd.setDop_central_govn(permission_of_central);
				urd.setName_of_uni_affilate(university_affiliated);
				
				
				Date date_of_first_affiliation_of_university=null;
				if(!date_affili_uni.equals("DD/MM/YYYY") && !date_affili_uni.equals("")) {
					date_of_first_affiliation_of_university=datePickerFormat.parse(date_affili_uni);
				}
				urd.setDoa_university(date_of_first_affiliation_of_university);
				urd.setYear_of_establish_college(establishment_college);
				
				Date date_of_concent_affiliation_of_university=null;
				if(!date_consent_affili_uni.equals("DD/MM/YYYY") && !date_consent_affili_uni.equals("")) {
					date_of_concent_affiliation_of_university=datePickerFormat.parse(date_consent_affili_uni);
				}
				urd.setDoc_affilation_university(date_of_concent_affiliation_of_university);
				
				Date date_of_last_affilia_of_university=null;
				if(!date_last_affili_uni.equals("DD/MM/YYYY") && !date_last_affili_uni.equals("")) {
					date_of_last_affilia_of_university=datePickerFormat.parse(date_last_affili_uni);
				}
				urd.setDoc_last_aff_university(date_of_last_affilia_of_university);
				
				if(!trustreg_doc.equals("")) {
					urd.setReg_certi_of_society(trustreg_doc);
					}
					if(!trustdeed_doc.equals("")) {
					urd.setCopy_of_society(trustdeed_doc);
					}
					if(!furnish_bank_doc.equals("")) {
					urd.setUndertaking_letter_furnish_bank_guarantee(furnish_bank_doc);
					}
					if(!noc_state_doc.equals("")) {
						urd.setNoc_doc_state_gov(noc_state_doc);
					}
					if(!affilat_doc.equals("")) {
						urd.setAffiliaion_doc_concern_uni(affilat_doc);
					}
					if(!uni_app_doc.equals("")) {
						urd.setUni_app_letter(uni_app_doc);
					}
					if(!inspection_acade_yr_doc.equals("")) {
						urd.setInspection_last_academic_yr(inspection_acade_yr_doc);
					}
				

				urd.setUserid(Integer.parseInt(userid));
				
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());
				urd.setInst_id(Integer.parseInt(institude));
				
				urd.setModified_by(Integer.parseInt(userid));
				urd.setModified_date(date);
				urd.setStatus(0);


//

//
				sessionHQL.update(urd);
				 data.put("msg", "Draft Update Successfully");
				sessionHQL.flush();
				sessionHQL.clear();
			}

				tx.commit();
				data.put("msg", "Data Save As Draft Successfully.");
				//}

			} catch (RuntimeException e) {
				e.printStackTrace();
				try {
				} catch (RuntimeException rbe) {
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
				return data; 
	}
	 
	 
	//-------------------------Head Of Institution----------------------------------------------------------------
	 
	 
	 
	
	 @PostMapping(value = "/head_of_Institution_Action")
	 public @ResponseBody Map<String, String> head_of_Institution_Action(ModelMap Mmap, HttpSession session,
			 HttpServletRequest request,RedirectAttributes ra,String pernt_id) throws IOException, ParseException {
	 
	 
		Date date = new Date();
		String username = session.getAttribute("username").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		
//		String s_id = session.getAttribute("super_id").toString();
//		System.err.println("========s_id for super"+s_id);
		
		String role = session.getAttribute("roleStaff_lvl").toString();
		System.err.println("role for regi-----------" + role + "---");

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		
		Map<String, String> data = new HashMap<>();

		
		try {
			int p_id=Integer.parseInt(request.getParameter("pernt_id"));
			int h_id=Integer.parseInt(request.getParameter("head_of_inst_hidden"));
			
			
			
		//	String id_for_update = request.getParameter("id_hidden");
		//	System.err.println("id for update-----------" + id_for_update + "---");
				String name_of_principal = request.getParameter("name_pdd");
				System.err.println("name_of_principal-----------21 mar------"+name_of_principal);
				String cch_registration_no = request.getParameter("cch_registration");
				
				String state_rn_head = request.getParameter("state_rn_head");
				String state_registration_no = request.getParameter("state_registration_no");
				
				String dob_id = request.getParameter("dob_id");
				String doj_id = request.getParameter("doj_id");
				System.err.println("doj_id-------21 mar------"+doj_id);
				String professor = request.getParameter("professor");
						System.err.println("professor-------21 mar------"+professor);
				String reader = request.getParameter("reader");
				String lecturer = request.getParameter("lecturer");
				String demonstrator = request.getParameter("demonstrator");
				String head_per_add_line1 = request.getParameter("head_per_add_line1");
				System.err.println("value-----------21 mar------"+head_per_add_line1);
				String head_per_add_line2 = request.getParameter("head_per_add_line2");
				String per_state_head_inst = request.getParameter("per_state_head_inst");
				String per_district_head_inst = request.getParameter("per_district_head_inst");
				String head_city = request.getParameter("head_city");
				String head_pincode = request.getParameter("head_pincode");
				String head_alt_mo_no1 = request.getParameter("head_alt_mo_no1");
				String headre_alt_mo_no1 = request.getParameter("headre_alt_mo_no1");
				String head_alt_mo_no2 = request.getParameter("head_alt_mo_no2");
				String head_email = request.getParameter("head_email");
				String head_std = request.getParameter("head_std");
				
				String headre_std = request.getParameter("headre_std");
				String teacher_code = request.getParameter("teacher_code");
				
				

//				if (teacher_code == null || teacher_code.trim().equals("")) {
//					data.put("msg", "Please Enter Teacher Code In Head of Institution Details.");
//					return data;
//				}
				if (name_of_principal == null || name_of_principal.trim().equals("")) {
					data.put("msg", "Please Enter Teacher Code In Basic Detail.");
					return data;
				}
				if (cch_registration_no == null || cch_registration_no.trim().equals("")) {
					data.put("msg", "Please Enter CCH/NCH Registration Number In Head of Institution Details.");
					return data;
				}
				if (state_rn_head == null || state_rn_head.trim().equals("")) {
					data.put("msg", "Please Enter State Registration In Head of Institution Details.");
					return data;
				}
				if (state_registration_no == null || state_registration_no.trim().equals("")) {
					data.put("msg", "Please Enter Name of The State Registration Number In Head of Institution Details.");
					return data;
				}
				
				if (dob_id == null || dob_id.trim().equals("") || dob_id.equals("DD/MM/YYYY")) {
					data.put("msg", "Please Enter Date of Birth In Head of Institution Details.");
					return data;
				}
				if (doj_id == null || doj_id.trim().equals("") || doj_id.equals("DD/MM/YYYY")) {
					data.put("msg", "Please Enter Date of Birth In Head of Institution Details.");
					return data;
				}
				
				
				if (professor == null || professor.trim().equals("") ) {
					data.put("msg", "Please Enter Professor In Head of Institution Details.");
					return data;
				}
				
				if (reader == null || reader.trim().equals("") ) {
					data.put("msg", "Please Enter Reader / Associate Professor In Head of Institution Details.");
					return data;
				}
				if (lecturer == null || lecturer.trim().equals("") ) {
					data.put("msg", "Please Enter Lecturer / Assistant professor  In Head of Institution Details.");
					return data;
				}
				
				if (demonstrator == null || demonstrator.trim().equals("") ) {
					data.put("msg", "Please Enter Demonstrator/ Tutor  In Head of Institution Details.");
					return data;
				}
				if (head_per_add_line1 == null || head_per_add_line1.trim().equals("") ) {
					data.put("msg", "Please Enter Address Line 1  In Head of Institution Details.");
					return data;
				}
				if (head_per_add_line2 == null || head_per_add_line2.trim().equals("") ) {
					data.put("msg", "Please Enter Address Line 2  In Head of Institution Details.");
					return data;
				}
				if (per_state_head_inst == null || per_state_head_inst.trim().equals("") ) {
					data.put("msg", "Please Enter State In Head of Institution Details.");
					return data;
				}
				
				if (head_city == null || head_city.trim().equals("") ) {
					data.put("msg", "Please Enter City In Head of Institution Details.");
					return data;
				}
				if (head_pincode == null || head_pincode.trim().equals("") ) {
					data.put("msg", "Please Enter Pincode In Head of Institution Details.");
					return data;
				}
				
				if (validation.maxlengthcheckpincode(head_pincode) == false) {
					ra.addAttribute("msg", " Address Pin Code " + validation.MaxlengthcheckMSGpincode);
					return data;
				}
				if (validation.minlengthcheckpincode(head_pincode) == false) {
					ra.addAttribute("msg", "Address Pin Code " + validation.MinlengthcheckMSGpincode);
					return data;
				}
				if (validation.isOnlyNumer(head_pincode) == true) {
					ra.addAttribute("msg", "Address Pin Code " + validation.isOnlyNumerMSG);
					return data;
				}
				if (head_std == null || head_std.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter STD Office Phone Number");
					return data;
				}
				
				if (head_alt_mo_no1 == null || head_alt_mo_no1.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Office Phone Number");
					return data;
				}
				if (validation.isOnlyNumer(head_alt_mo_no1) == true) {
					ra.addAttribute("msg", "Office Phone Number " + validation.isOnlyNumerMSG);
					return data;
				}
				if (validation.maxlengthcheck10(head_alt_mo_no1) == false) {
					ra.addAttribute("msg", "Office Phone Number " + validation.MaxlengthcheckMSG10);
					return data;
				}
//				if (validation.isValidMobileNo(head_alt_mo_no1) == false) {
//					ra.addAttribute("msg", "Office Phone Number " + validation.isOnlyMobilenumberMSG);
//					return data;
//				}
//				if (headre_std == null || headre_std.trim().equals("")) {
//					ra.addAttribute("msg", "Please Enter STD Residence Phone Number");
//					return data;
//				}
//				if (headre_alt_mo_no1 == null || headre_alt_mo_no1.trim().equals("")) {
//					ra.addAttribute("msg", "Please Enter Residence Phone Number");
//					return data;
//				}
//				if (validation.isOnlyNumer(headre_alt_mo_no1) == true) {
//					ra.addAttribute("msg", "Residence Phone Number " + validation.isOnlyNumerMSG);
//					return data;
//				}
//				if (validation.maxlengthcheck10(headre_alt_mo_no1) == false) {
//					ra.addAttribute("msg", "Residence Phone Number " + validation.MaxlengthcheckMSG10);
//					return data;
//				}
//				if (validation.isValidMobileNo(headre_alt_mo_no1) == false) {
//					ra.addAttribute("msg", "Residence Phone Number " + validation.isOnlyMobilenumberMSG);
//					return data;
//				}
				
				if (head_alt_mo_no2 == null || head_alt_mo_no2.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Mobile Number");
					return data;
				}
				if (validation.isOnlyNumer(head_alt_mo_no2) == true) {
					ra.addAttribute("msg", "Mobile Number " + validation.isOnlyNumerMSG);
					return data;
				}
				if (validation.maxlengthcheck10(head_alt_mo_no2) == false) {
					ra.addAttribute("msg", "Mobile Number " + validation.MaxlengthcheckMSG10);
					return data;
				}
				if (validation.isValidMobileNo(head_alt_mo_no2) == false) {
					ra.addAttribute("msg", "Mobile Number " + validation.isOnlyMobilenumberMSG);
					return data;
				}
				
				if (head_email == null || head_email.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter E-mail");
					return data;
				}
				
				if (validation.maxlengthcheck70(head_email) == false) {
					ra.addAttribute("msg", "E-mail Address " + validation.MaxlengthcheckMSG70);
					return data;
				}
				
				if(h_id==0) {
					CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS id =new CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS();
					
					id.setName_of_principal(name_of_principal);
					id.setCch_reg_no(cch_registration_no);
					id.setState_reg(Integer.parseInt(state_rn_head));
					id.setState_reg_no(state_registration_no);
					
					Date date_of_birth=null;
					if(!dob_id.equals("DD/MM/YYYY") && !dob_id.equals("")) {
						date_of_birth=datePickerFormat.parse(dob_id);
					}
					id.setDob(date_of_birth);
					
					Date date_of_joining=null;
					if(!doj_id.equals("DD/MM/YYYY") && !doj_id.equals("")) {
						date_of_joining=datePickerFormat.parse(doj_id);
					}
					id.setDate_of_join_princi(date_of_joining);
					
					id.setProfessor_exp(professor);
					id.setReader_exp(reader);
					id.setLecturer_exp(lecturer);
					id.setDemonstraror_exp(demonstrator);
					id.setAddress_line1(head_per_add_line1);
					id.setAddress_line2(head_per_add_line2);
					id.setState_add_detai(Integer.parseInt(per_state_head_inst));
					id.setDistrict_add_detai(Integer.parseInt(per_district_head_inst));
					id.setCity(head_city);
					id.setPincode(Integer.parseInt(head_pincode));
					id.setOffice_phone_no(BigInteger.valueOf(Long.parseLong(head_alt_mo_no1)));
					if(headre_alt_mo_no1 != null && !headre_alt_mo_no1.equals("")) {
					id.setResidence_phone_no(BigInteger.valueOf(Long.parseLong(headre_alt_mo_no1)));
					}
					id.setMobile_no(BigInteger.valueOf(Long.parseLong(head_alt_mo_no2)));
					id.setEmail_id(head_email);
					id.setP_id(p_id);
					id.setHead_std(Integer.parseInt(head_std));
					if(headre_std != null && !headre_std.equals("")) {
					id.setHeadre_std(Integer.parseInt(headre_std));
					}
					id.setTeacher_code(teacher_code);
					
				
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());
				id.setInst_id(Integer.parseInt(institude));
				
		//		rd.setCreated_by(Integer.parseInt(userid));
				id.setCreated_date(date);
	//			rd.setStatus(0);
				
				h_id = (int) sessionHQL.save(id);
				 data.put("msg", "Save as Draft Successfully");
				sessionHQL.flush();
				sessionHQL.clear();
			}
				else {
//				
//				
//				
//				System.err.println("id for update-----else------" + id_for_update + "---");
					CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS uid = (CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS) sessionHQL.get(CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS.class,
						(h_id));
//				
					uid.setName_of_principal(name_of_principal);
					uid.setCch_reg_no(cch_registration_no);
					uid.setState_reg(Integer.parseInt(state_rn_head));
					uid.setState_reg_no(state_registration_no);
					
					Date date_of_birth=null;
					if(!dob_id.equals("DD/MM/YYYY") && !dob_id.equals("")) {
						date_of_birth=datePickerFormat.parse(dob_id);
					}
					uid.setDob(date_of_birth);
					
					Date date_of_joining=null;
					if(!doj_id.equals("DD/MM/YYYY") && !doj_id.equals("")) {
						date_of_joining=datePickerFormat.parse(doj_id);
					}
					uid.setDate_of_join_princi(date_of_joining);
					
					uid.setProfessor_exp(professor);
					uid.setReader_exp(reader);
					uid.setLecturer_exp(lecturer);
					uid.setDemonstraror_exp(demonstrator);
					uid.setAddress_line1(head_per_add_line1);
					uid.setAddress_line2(head_per_add_line2);
					uid.setState_add_detai(Integer.parseInt(per_state_head_inst));
					uid.setDistrict_add_detai(Integer.parseInt(per_district_head_inst));
					uid.setCity(head_city);
					uid.setPincode(Integer.parseInt(head_pincode));
					uid.setOffice_phone_no(BigInteger.valueOf(Long.parseLong(head_alt_mo_no1)));
					if(headre_alt_mo_no1 != null && !headre_alt_mo_no1.equals("")) {
					uid.setResidence_phone_no(BigInteger.valueOf(Long.parseLong(headre_alt_mo_no1)));
					}
					uid.setMobile_no(BigInteger.valueOf(Long.parseLong(head_alt_mo_no2)));
					uid.setEmail_id(head_email);		
					uid.setHead_std(Integer.parseInt(head_std));
					if(headre_std != null && !headre_std.equals("")) {
					uid.setHeadre_std(Integer.parseInt(headre_std));
					}
					uid.setTeacher_code(teacher_code);



			//	urd.setUserid(Integer.parseInt(userid));
				
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());
				uid.setInst_id(Integer.parseInt(institude));
				
		//		urd.setModified_by(Integer.parseInt(userid));
				uid.setModified_date(date);
		//		urd.setStatus(0);


//

//
				sessionHQL.update(uid);
				 data.put("msg", "Draft Update Successfully");
				sessionHQL.flush();
				sessionHQL.clear();
			}

				tx.commit();
				data.put("msg", "Data Save As Draft Successfully.");
				//}

			} catch (RuntimeException e) {
				e.printStackTrace();
				try {
				} catch (RuntimeException rbe) {
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
				return data; 
	}
	
	 
	 
	 
//-------------------------------------------------------Important Information Of Connectivity-----------------------------------
	 
	 
	// CLG_REG_INST_INFO_INFORMATION_OF_CONNECTIVITY
	 
	 
	 @PostMapping(value = "/important_information_connectivity_Action")
	 public @ResponseBody Map<String, String> important_information_connectivity_Action(ModelMap Mmap, HttpSession session,
			 HttpServletRequest request,RedirectAttributes ra,String pernt_id) throws IOException, ParseException {
	 
	System.err.println("------parent------"+pernt_id);
	 
		Date date = new Date();
		String username = session.getAttribute("username").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String role = session.getAttribute("roleStaff_lvl").toString();
		System.err.println("role for regi-----------" + role + "---");

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		
		Map<String, String> data = new HashMap<>();

		
		try {
			int p_id=Integer.parseInt(request.getParameter("pernt_id"));
			int h_id=Integer.parseInt(request.getParameter("info_connecti_hidden"));
				
				String nearest_airport = request.getParameter("nearest_airport");
				String airport_distance = request.getParameter("airport_distance");
				String nearest_railway = request.getParameter("nearest_railway");
				String railway_distance = request.getParameter("railway_distance");
				String nearest_institutions = request.getParameter("nearest_institutions");
				String nr_inst_state_ic = request.getParameter("nr_inst_state_ic");
				String nr_inst_district_ic = request.getParameter("nr_inst_district_ic");
				
				String inst_city = request.getParameter("inst_city");
				

				
				if(h_id==0) {
					
					CLG_REG_INST_INFO_INFORMATION_OF_CONNECTIVITY rd =new CLG_REG_INST_INFO_INFORMATION_OF_CONNECTIVITY();
					
	

				rd.setNearest_airport_name(nearest_airport);
				rd.setApprox_distance(airport_distance);
				rd.setNearest_railway_station(nearest_railway);
				rd.setApprox_dist_railway(railway_distance);
				rd.setNearest_inst_name(nearest_institutions);
				rd.setCity(inst_city);
				rd.setOther_state(Integer.parseInt(nr_inst_state_ic));
				rd.setOther_district(Integer.parseInt(nr_inst_district_ic));
				
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());
				rd.setInst_id(Integer.parseInt(institude));
				rd.setP_id(p_id);
				
				rd.setCreated_by((userid));
				rd.setCreated_date(date);
				
				h_id = (int) sessionHQL.save(rd);
				 data.put("msg", "Save as Draft Successfully");
				sessionHQL.flush();
				sessionHQL.clear();
			}
				else {
				
					CLG_REG_INST_INFO_INFORMATION_OF_CONNECTIVITY urd = (CLG_REG_INST_INFO_INFORMATION_OF_CONNECTIVITY) sessionHQL.get(CLG_REG_INST_INFO_INFORMATION_OF_CONNECTIVITY.class,
						(h_id));
					
					
					urd.setNearest_airport_name(nearest_airport);
					urd.setApprox_distance(airport_distance);
					urd.setNearest_railway_station(nearest_railway);
					urd.setApprox_dist_railway(railway_distance);
					urd.setNearest_inst_name(nearest_institutions);
					urd.setCity(inst_city);
					urd.setOther_state(Integer.parseInt(nr_inst_state_ic));
					urd.setOther_district(Integer.parseInt(nr_inst_district_ic));
				
				
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());
				urd.setInst_id(Integer.parseInt(institude));
				
				urd.setModified_by((userid));
				urd.setModified_date(date);

				sessionHQL.update(urd);
				 data.put("msg", "Draft Update Successfully");
				sessionHQL.flush();
				sessionHQL.clear();
			}

				tx.commit();
				data.put("msg", "Data Save As Draft Successfully.");
				//}

			} catch (RuntimeException e) {
				e.printStackTrace();
				try {
				} catch (RuntimeException rbe) {
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
				return data; 
	}
	 
	 
	 
	 
	 
	 
	 
	//-------------------------------------------------------Police Station Details-----------------------------------	 
	 
	 
	 
	 
	 @PostMapping(value = "/police_station_dtl_Action")
	 public @ResponseBody Map<String, String> police_station_dtl_Action(ModelMap Mmap, HttpSession session,
			 HttpServletRequest request,RedirectAttributes ra,String pernt_id) throws IOException, ParseException {
	 
	//System.err.println("------parent------"+pernt_id);
	 
		Date date = new Date();
		String username = session.getAttribute("username").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String role = session.getAttribute("roleStaff_lvl").toString();
		System.err.println("role for regi-----------" + role + "---");

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		
		Map<String, String> data = new HashMap<>();

		
		try {
			int p_id=Integer.parseInt(request.getParameter("pernt_id"));
			int h_id=Integer.parseInt(request.getParameter("police_station_hidden"));
				
	
				
				String nearest_police = request.getParameter("nearest_police");
				String police_per_add_line1 = request.getParameter("police_per_add_line1");
				String police_per_add_line2 = request.getParameter("police_per_add_line2");
				String police_per_state = request.getParameter("police_per_state");
				String police_per_district = request.getParameter("police_per_district");
				
				String police_city = request.getParameter("police_city");
				String police_per_pincode = request.getParameter("police_per_pincode");
				String police_std = request.getParameter("police_std");
				String police_phone_no = request.getParameter("police_phone_no");
				String sp_office_std = request.getParameter("sp_office_std");
				String sp_office_phone_no = request.getParameter("sp_office_phone_no");
				
				
				
				
				if (nearest_police == null || nearest_police.trim().equals("")) {
					data.put("msg", "Please Enter Name of Nearest Police Station   In Police Station Details");
					return data;
				}
				if (validation.maxlengthcheck100(nearest_police) == false) {
					data.put("msg", "Name of Nearest Police Station   In Police Station Details " + validation.MaxlengthcheckMSG100);
					return data;
				}
				if (validation.isOnlyAlphabet(nearest_police) == false) {
					data.put("msg", "Name of Nearest Police Station   In Police Station Details " + validation.isOnlyAlphabetMSG);
					return data;
				}
				if (police_per_add_line1 == null || police_per_add_line1.trim().equals("")) {
					data.put("msg", "Please Enter  Address-Line-1 In Police Station Details");
					return data;
				}
				if (validation.maxlengthcheck100(police_per_add_line1) == false) {
					data.put("msg", "Correspondence Address-Line-1 In Police Station Details " + validation.MaxlengthcheckMSG100);
					return data;
				}
				
				if (police_per_add_line2 == null || police_per_add_line2.trim().equals("")) {
					data.put("msg", "Please Enter  Address-Line-2 In Police Station Details");
					return data;
				}
				if (validation.maxlengthcheck100(police_per_add_line2) == false) {
					data.put("msg", "Correspondence Address-Line-2 In Police Station Details " + validation.MaxlengthcheckMSG100);
					return data;
				}
				if (police_per_state == null || police_per_state.trim().equals("0")) {
					data.put("msg", "Please Select State  In Police Station Details");
					return data;
				}
				if (police_per_district == null || police_per_district.trim().equals("0")) {
					data.put("msg", "Please Select District  In Police Station Details");
					return data;
				}
				if (police_city == null || police_city.trim().equals("")) {
					data.put("msg", "Please Enter  City In Police Station Details");
					return data;
				}
				if (validation.maxlengthcheck100(police_city) == false) {
					data.put("msg", "City In Police Station Details " + validation.MaxlengthcheckMSG100);
					return data;
				}
				if (validation.isOnlyAlphabet(police_city) == false) {
					data.put("msg", "City In Police Station Details " + validation.isOnlyAlphabetMSG);
					return data;
				}
				if (police_per_pincode == null || police_per_pincode.trim().equals("")) {
					data.put("msg", "Please Enter  Pincode  In Police Station Details");
					return data;
				}
				if (validation.isOnlyNumer(police_per_pincode) == true) {
					data.put("msg", " Pin Code In Police Station Details" + validation.isOnlyNumerMSG);
					return data;
				}
				
				
				
				
				if (police_std == null || police_std.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Landline Number Code In Police Station Details");
					return data;
				}
				if (police_phone_no == null || police_phone_no.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Landline Number In Police Station Details");
					return data;
				}
				if (validation.isOnlyNumer(police_phone_no) == true) {
					ra.addAttribute("msg", "S.P. Office Number/Landline Number In Police Station Details " + validation.isOnlyNumerMSG);
					return data;
				}
				if (validation.maxlengthcheck10(police_phone_no) == false) {
					ra.addAttribute("msg", "S.P. Office Number/Landline Number In Police Station Details " + validation.MaxlengthcheckMSG10);
					return data;
				}
				if (sp_office_std == null || sp_office_std.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter S.P. Office Number/Landline Number Code In Police Station Details");
					return data;
				}
				if (sp_office_phone_no == null || sp_office_phone_no.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter S.P. Office Number/Landline Number In Police Station Details");
					return data;
				}
				if (validation.isOnlyNumer(sp_office_phone_no) == true) {
					ra.addAttribute("msg", "S.P. Office Number/Landline Number In Police Station Details" + validation.isOnlyNumerMSG);
					return data;
				}
				if (validation.maxlengthcheck10(sp_office_phone_no) == false) {
					ra.addAttribute("msg", "S.P. Office Number/Landline Number In Police Station Details" + validation.MaxlengthcheckMSG10);
					return data;
				}
				
				
				
				
				
							

				
				if(h_id==0) {
					
					CLG_REG_INST_INFO_POLICE_STATION_DETAILS rd =new CLG_REG_INST_INFO_POLICE_STATION_DETAILS();
					
	

					rd.setName_of_nearest_police_station(nearest_police);
					rd.setAddr_line1(police_per_add_line1);
					rd.setAddr_line2(police_per_add_line2);
					rd.setState(Integer.parseInt(police_per_state));
					rd.setDistrict_id(Integer.parseInt(police_per_district));
					rd.setCity(police_city);
					rd.setPincode(Integer.parseInt(police_per_pincode));
					rd.setPhone_number(BigInteger.valueOf(Long.parseLong(police_phone_no)));
					rd.setPolice_std_code(Integer.parseInt(police_std));
					rd.setSp_std_code(Integer.parseInt(sp_office_std));
					rd.setSp_phone_number(BigInteger.valueOf(Long.parseLong(sp_office_phone_no)));
					
					
		
				
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());
				rd.setInst_id(Integer.parseInt(institude));
				rd.setP_id(p_id);
				
				rd.setCreated_by((userid));
				rd.setCreated_date(date);
				
				h_id = (int) sessionHQL.save(rd);
				 data.put("msg", "Save as Draft Successfully");
				sessionHQL.flush();
				sessionHQL.clear();
			}
				else {
				
					CLG_REG_INST_INFO_POLICE_STATION_DETAILS urd = (CLG_REG_INST_INFO_POLICE_STATION_DETAILS) sessionHQL.get(CLG_REG_INST_INFO_POLICE_STATION_DETAILS.class,
						(h_id));
					
					
					urd.setName_of_nearest_police_station(nearest_police);
					urd.setAddr_line1(police_per_add_line1);
					urd.setAddr_line2(police_per_add_line2);
					urd.setState(Integer.parseInt(police_per_state));
					urd.setDistrict_id(Integer.parseInt(police_per_district));
					urd.setCity(police_city);
					urd.setPincode(Integer.parseInt(police_per_pincode));
					urd.setPhone_number(BigInteger.valueOf(Long.parseLong(police_phone_no)));
					urd.setPolice_std_code(Integer.parseInt(police_std));
					urd.setSp_std_code(Integer.parseInt(sp_office_std));
					urd.setSp_phone_number(BigInteger.valueOf(Long.parseLong(sp_office_phone_no)));
				
				
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());
				urd.setInst_id(Integer.parseInt(institude));
				
				urd.setModified_by((userid));
				urd.setModified_date(date);

				sessionHQL.update(urd);
				 data.put("msg", "Draft Update Successfully");
				sessionHQL.flush();
				sessionHQL.clear();
			}

				tx.commit();
				data.put("msg", "Data Save As Draft Successfully.");
				//}

			} catch (RuntimeException e) {
				e.printStackTrace();
				try {
				} catch (RuntimeException rbe) {
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
				return data; 
	}
	 
	 
		//------------------------------------------------------- Details of Land-----------------------------------
	 
	 
	 @PostMapping(value = "/details_land_Action")
	 public @ResponseBody Map<String, String> details_land_Action(ModelMap Mmap, HttpSession session,
			 HttpServletRequest request,RedirectAttributes ra,String pernt_id, @RequestParam(value = "land_doc_clg", required = false) MultipartFile land_dc_clg,
			 @RequestParam(value = "building_plan_doc", required = false) MultipartFile building_plan_dc,
			 @RequestParam(value = "architect_doc", required = false) MultipartFile architect_dc,@RequestParam(value = "balancesheet_doc", required = false) MultipartFile balancesheet_dc,
			 @RequestParam(value = "annual_doc", required = false) MultipartFile annual_dc, @RequestParam(value = "add_doc", required = false) MultipartFile add_dc) throws IOException, ParseException {
	 
	//System.err.println("------parent------"+pernt_id);
	 
		Date date = new Date();
		String username = session.getAttribute("username").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String role = session.getAttribute("roleStaff_lvl").toString();
		System.err.println("role for regi-----------" + role + "---");

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		
		Map<String, String> data = new HashMap<>();

		
		try {
			int p_id=Integer.parseInt(request.getParameter("pernt_id"));
			int h_id=Integer.parseInt(request.getParameter("detail_land_hidden"));
				
	
				
				
				
				String area_of_land  = request.getParameter("area_of_land");
				String own_land  = request.getParameter("own_land");
				String land_name  = request.getParameter("land_name");
				String entire_name  = request.getParameter("entire_name");
				String land_availability  = request.getParameter("land_availability");
				String stic  = request.getParameter("stic");
				String constructedcheck  = request.getParameter("constructedcheck");
				String buildingcheck  = request.getParameter("buildingcheck");
				String clg_land  = request.getParameter("clg_land");
				String hospital_land  = request.getParameter("hospital_land");
				String hostels_land  = request.getParameter("hostels_land");
				String shiftedcheck  = request.getParameter("shiftedcheck");
				String changedcheck  = request.getParameter("changedcheck");
				
				String land_doc_clg  ="land_doc_clg";
				String building_plan_doc  = "building_plan_doc";
				String architect_doc  = "architect_doc";
				String balancesheet_doc  = "balancesheet_doc";
				String annual_doc  = "annual_doc";
				String add_doc  = "add_doc";
				
				
				if (!land_dc_clg.isEmpty()) {
					land_doc_clg = upload_imagemethod(request,land_dc_clg,session, land_doc_clg);
				}
				else {
					land_doc_clg = request.getParameter("hid_land_doc_clg");
				}
				
				if (!building_plan_dc.isEmpty()) {
					building_plan_doc = upload_imagemethod(request,building_plan_dc,session, building_plan_doc);
				}
				else {
					building_plan_doc = request.getParameter("hid_building_plan_doc");
				}
				
				if (!architect_dc.isEmpty()) {
					architect_doc = upload_imagemethod(request,architect_dc,session, architect_doc);
				}
				else {
					architect_doc = request.getParameter("hid_architect_doc");
				}
				if (!balancesheet_dc.isEmpty()) {
					balancesheet_doc = upload_imagemethod(request,balancesheet_dc,session, balancesheet_doc);
				}
				else {
					balancesheet_doc = request.getParameter("hid_balancesheet_doc");
				}
				if (!annual_dc.isEmpty()) {
					annual_doc = upload_imagemethod(request,annual_dc,session, annual_doc);
				}
				else {
					annual_doc = request.getParameter("hid_annual_doc");
				}
				if (!add_dc.isEmpty()) {
					add_doc = upload_imagemethod(request,add_dc,session, add_doc);
				}
				else {
					add_doc = request.getParameter("hid_add_doc");
				}
				
				
				
				
				if (area_of_land == null || area_of_land.trim().equals("")) {
					data.put("msg", "Please Enter Area of Land");
					return data;
				}
				if (validation.isOnlyNumber(area_of_land) == false) {
					data.put("msg","Total Area of Land " +validation.isOnlyNumberMSG);
					return data;
				}
				if (own_land.equals("0")) {
					data.put("msg", "Please Select Ownership of Land");
					return data;
				}
				if (land_name.equals("0")) {
					data.put("msg", "Please Select Land in the Name Of");
					return data;
				}
				if (entire_name.equals("0")) {
					data.put("msg", "Please Select Distribution of Entire Land");
					return data;
				}
				if (land_availability.equals("0")) {
					data.put("msg", "Please Select Land Availability");
					return data;
				}
				if (stic.equals("0")) {
					data.put("msg", "Please Select Does the same society/trust run any other institutions/colleges");
					return data;
				}
				if (clg_land == null || clg_land.trim().equals("")) {
					data.put("msg", "Please Enter Total Area of Land Allotted to the College");
					return data;
				}
				if (validation.isOnlyNumber(clg_land) == false) {
					data.put("msg","Total Area of Land Allotted to the College " +validation.isOnlyNumberMSG);
					return data;
				}
				if (hospital_land == null || hospital_land.trim().equals("")) {
					data.put("msg", "Please Enter Total Area of Land Allotted to the Hospital");
					return data;
				}
				if (validation.isOnlyNumber(hospital_land) == false) {
					data.put("msg","Total Area of Land Allotted to the Hospital " +validation.isOnlyNumberMSG);
					return data;
				}
				if (hostels_land == null || hostels_land.trim().equals("")) {
					data.put("msg", "Please Enter Total Area of Land Allotted to the Hostels");
					return data;
				}
				if (validation.isOnlyNumber(hostels_land) == false) {
					data.put("msg","Total Area of Land Allotted to the Hostels " +validation.isOnlyNumberMSG);
					return data;
				}

				


				
				if(h_id==0) {
					
					CLG_REG_INST_INFO_DETAILS_OF_LAND rd =new CLG_REG_INST_INFO_DETAILS_OF_LAND();
					
					rd.setTotal_area_of_land_society(area_of_land);
					rd.setOwnership_of_land(Integer.parseInt(own_land));
					rd.setLand_in_the_name(Integer.parseInt(entire_name));
					rd.setDistribution_of_entire_land(Integer.parseInt(entire_name));
					rd.setLand_availability_with_society(Integer.parseInt(land_availability));
					rd.setSame_society_trust(Integer.parseInt(stic));
					rd.setCollege_and_hospital_constructed(constructedcheck);
					rd.setCollege_and_hospital_building(buildingcheck);
					rd.setTotal_area_of_land_alloted_clg(clg_land);
					rd.setTotal_area_of_land_alloted_clg_hospital(hospital_land);
					rd.setTotal_area_of_land_alloted_clg_hostels(hostels_land);
					rd.setCollege_and_hospital_building_shifted(shiftedcheck);
					rd.setManagement_of_college_changed(changedcheck);
					if(!land_doc_clg.equals("")) {
						rd.setLand_document_clg(land_doc_clg);
					}
					if(!building_plan_doc.equals("")) {
						rd.setBuilding_plan(building_plan_doc);
					}
					if(!architect_doc.equals("")) {
						rd.setStatement_doc_architect(architect_doc);
					}
					if(!balancesheet_doc.equals("")) {
						rd.setAudited_balance_sheet(balancesheet_doc);
					}
					if(!annual_doc.equals("")) {
						rd.setAnnual_report(annual_doc);
					}
					if(!add_doc.equals("")) {
						rd.setAdditional_document(add_doc);
					}
					
					
				
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());
				rd.setInst_id(Integer.parseInt(institude));
				rd.setP_id(p_id);
				
				rd.setCreated_by((userid));
				rd.setCreated_date(date);
			
				
				h_id = (int) sessionHQL.save(rd);
				 data.put("msg", "Save as Draft Successfully");
				sessionHQL.flush();
				sessionHQL.clear();
			}
				else {
				
					CLG_REG_INST_INFO_DETAILS_OF_LAND urd = (CLG_REG_INST_INFO_DETAILS_OF_LAND) sessionHQL.get(CLG_REG_INST_INFO_DETAILS_OF_LAND.class,
						(h_id));
					
					
					urd.setTotal_area_of_land_society(area_of_land);
					urd.setOwnership_of_land(Integer.parseInt(own_land));
					urd.setLand_in_the_name(Integer.parseInt(entire_name));
					urd.setDistribution_of_entire_land(Integer.parseInt(entire_name));
					urd.setLand_availability_with_society(Integer.parseInt(land_availability));
					urd.setSame_society_trust(Integer.parseInt(stic));
					urd.setCollege_and_hospital_constructed(constructedcheck);
					urd.setCollege_and_hospital_building(buildingcheck);
					urd.setTotal_area_of_land_alloted_clg(clg_land);
					urd.setTotal_area_of_land_alloted_clg_hospital(hospital_land);
					urd.setTotal_area_of_land_alloted_clg_hostels(hostels_land);
					urd.setCollege_and_hospital_building_shifted(shiftedcheck);
					urd.setManagement_of_college_changed(changedcheck);
					if(!land_doc_clg.equals("")) {
						urd.setLand_document_clg(land_doc_clg);
					}
					if(!building_plan_doc.equals("")) {
						urd.setBuilding_plan(building_plan_doc);
					}
					if(!architect_doc.equals("")) {
						urd.setStatement_doc_architect(architect_doc);
					}
					if(!balancesheet_doc.equals("")) {
						urd.setAudited_balance_sheet(balancesheet_doc);
					}
					if(!annual_doc.equals("")) {
						urd.setAnnual_report(annual_doc);
					}
					if(!add_doc.equals("")) {
						urd.setAdditional_document(add_doc);
					}
				
				
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());
				urd.setInst_id(Integer.parseInt(institude));
				
				urd.setModified_by((userid));
				urd.setModified_date(date);

				sessionHQL.update(urd);
				 data.put("msg", "Draft Update Successfully");
				sessionHQL.flush();
				sessionHQL.clear();
			}

				tx.commit();
				data.put("msg", "Data Save As Draft Successfully.");
				//}

			} catch (RuntimeException e) {
				e.printStackTrace();
				try {
				} catch (RuntimeException rbe) {
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
				return data; 
	}
	 
	 
	 
		//ADD MORE SAVE FOR INTAKE CAPACITY===============================
	 
	 
		@PostMapping(value = "/Intake_Capacity_Save_Draft_Action")
		public @ResponseBody String Intake_Capacity_Save_Draft_Action( 
				HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
				RedirectAttributes ra) throws ParseException {

			if(request.getHeader("Referer") == null ) { 
				 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return  "redirect:/login";
			 }
			String role = session.getAttribute("role").toString();
			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//				if(val == false) {
//					return  "AccessTiles";
//			}
			DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			String userid = session.getAttribute("userId_for_jnlp").toString();
			
			String indno = request.getParameter("indno_library");
			String library_assistants_name = request.getParameter("library_assistants_name"+indno);
			String assistants_qualification = request.getParameter("assistants_qualification"+indno);
			String hid_library = request.getParameter("hid_library"+indno);
		//	String p_hid_library = CIDao.getp_idfromuser_id(userid).get(0).get(0);
			Date date = new Date();
			String username = principal.getName();
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			
			

			try {
				CLG_REG_INFRA_CENTRAL_LIBRARY_CHILD librarian_detail =new CLG_REG_INFRA_CENTRAL_LIBRARY_CHILD();
				
				librarian_detail.setLibrary_assistants_name(library_assistants_name);
				librarian_detail.setAssistants_qualification(assistants_qualification);
				librarian_detail.setCreated_by(Integer.parseInt(userid));
				librarian_detail.setCreated_date(date);
					if (Integer.parseInt(hid_library) == 0) {
		//				librarian_detail.setP_id(Integer.parseInt(p_hid_library));
						int hid_library1= (Integer) sessionHQL.save(librarian_detail);
						sessionHQL.flush();
						sessionHQL.clear();
						tx.commit();
						return String.valueOf(hid_library1) ;
				}
					else {

						CLG_REG_INFRA_CENTRAL_LIBRARY_CHILD librarian_detail_u = (CLG_REG_INFRA_CENTRAL_LIBRARY_CHILD) sessionHQL
								.get(CLG_REG_INFRA_CENTRAL_LIBRARY_CHILD.class, Integer.parseInt(hid_library));
						
						librarian_detail_u.setLibrary_assistants_name(library_assistants_name);
						librarian_detail_u.setAssistants_qualification(assistants_qualification);
						librarian_detail_u.setModified_by(Integer.parseInt(userid));
						librarian_detail_u.setModified_date(date);
						sessionHQL.update(librarian_detail_u);
						sessionHQL.flush();
						sessionHQL.clear();
						tx.commit();
						
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
			return  "Data Saved Successfully";
		}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
		//-----------------------------------------Undertaking and reports-------------------------------------------------
	 
	 
	 @PostMapping(value = "/undertaking_report_Action")
	 public @ResponseBody Map<String, String> undertaking_report_Action(ModelMap Mmap, HttpSession session,
			 HttpServletRequest request,RedirectAttributes ra,@RequestParam(value = "trustreg_doc", required = false) MultipartFile trusted_dc,
			 @RequestParam(value = "trustdeed_doc", required = false) MultipartFile trusteed_dc,
			 @RequestParam(value = "furnish_bank_doc", required = false) MultipartFile furnish_bank_dc,
			 @RequestParam(value = "noc_state_doc", required = false) MultipartFile noc_state_dc,
			 @RequestParam(value = "affilat_doc", required = false) MultipartFile affilat_dc,
			 @RequestParam(value = "denial_doc", required = false) MultipartFile denial_dc,
			 @RequestParam(value = "courtorder_doc", required = false) MultipartFile courtorder_dc,
			 @RequestParam(value = "land_doc_clg", required = false) MultipartFile land_dc_clg,
			 @RequestParam(value = "building_plan_doc", required = false) MultipartFile building_plan_dc,
			 @RequestParam(value = "architect_doc", required = false) MultipartFile architect_dc,
			 @RequestParam(value = "per_gov_doc", required = false) MultipartFile per_gov_dc,
			 @RequestParam(value = "balancesheet_doc", required = false) MultipartFile balancesheet_dc,
			 @RequestParam(value = "annual_doc", required = false) MultipartFile annual_dc,
			 @RequestParam(value = "cchreg_doc", required = false) MultipartFile cchreg_dc,
			 @RequestParam(value = "add_doc", required = false) MultipartFile add_dc,String pernt_id) throws IOException, ParseException {
	 
	//System.err.println("------parent------"+pernt_id);
	 
		Date date = new Date();
		String username = session.getAttribute("username").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String role = session.getAttribute("roleStaff_lvl").toString();
		System.err.println("role for regi-----------" + role + "---");

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		
		Map<String, String> data = new HashMap<>();

		
		try {
			int p_id=Integer.parseInt(request.getParameter("pernt_id"));
			int h_id=Integer.parseInt(request.getParameter("undertaking_report_hidden"));
				

				
				
				
				String trustreg_doc  = "trustreg_doc";
				String trustdeed_doc  = "trustdeed_doc";
				String furnish_bank_doc  = "furnish_bank_doc";
				String noc_state_doc  = "noc_state_doc";
				String affilat_doc  = "affilat_doc";
				String denial_doc  = "denial_doc";
				String courtorder_doc  = "courtorder_doc";
				String land_doc_clg  ="land_doc_clg";
				String building_plan_doc  = "building_plan_doc";
				String architect_doc  = "architect_doc";
				String per_gov_doc  = "per_gov_doc";
				String balancesheet_doc  = "balancesheet_doc";
				String annual_doc  = "annual_doc";
				String cchreg_doc  ="cchreg_doc";
				String add_doc  = "add_doc";
				
				
				if (!trusted_dc.isEmpty()) {
					trustreg_doc = upload_imagemethod(request,trusted_dc,session, trustreg_doc);
				}
				else {
					trustreg_doc = request.getParameter("hid_trustreg_doc");
				}
				
				if (!trusteed_dc.isEmpty()) {
					trustdeed_doc = upload_imagemethod(request,trusteed_dc,session, trustdeed_doc);
				}
				else {
					trustdeed_doc = request.getParameter("hid_trustdeed_doc");
				}
				
				if (!furnish_bank_dc.isEmpty()) {
					furnish_bank_doc = upload_imagemethod(request,furnish_bank_dc,session, furnish_bank_doc);
				}
				else {
					furnish_bank_doc = request.getParameter("hid_furnish_bank_doc");
				}
				
				if (!noc_state_dc.isEmpty()) {
					noc_state_doc = upload_imagemethod(request,noc_state_dc,session, noc_state_doc);
				}
				else {
					noc_state_doc = request.getParameter("hid_noc_state_doc");
				}
				
				if (!affilat_dc.isEmpty()) {
					affilat_doc = upload_imagemethod(request,affilat_dc,session, affilat_doc);
				}
				else {
					affilat_doc = request.getParameter("hid_affilat_doc");
				}
				
				if (!denial_dc.isEmpty()) {
					denial_doc = upload_imagemethod(request,denial_dc,session, denial_doc);
				}
				else {
					denial_doc = request.getParameter("hid_denial_doc");
				}
				
				if (!courtorder_dc.isEmpty()) {
					courtorder_doc = upload_imagemethod(request,courtorder_dc,session, courtorder_doc);
				}
				else {
					courtorder_doc = request.getParameter("hid_courtorder_doc");
				}
				
				if (!land_dc_clg.isEmpty()) {
					land_doc_clg = upload_imagemethod(request,land_dc_clg,session, land_doc_clg);
				}
				else {
					land_doc_clg = request.getParameter("hid_land_doc_clg");
				}
				
				if (!building_plan_dc.isEmpty()) {
					building_plan_doc = upload_imagemethod(request,building_plan_dc,session, building_plan_doc);
				}
				else {
					building_plan_doc = request.getParameter("hid_building_plan_doc");
				}
				
				if (!architect_dc.isEmpty()) {
					architect_doc = upload_imagemethod(request,architect_dc,session, architect_doc);
				}
				else {
					architect_doc = request.getParameter("hid_architect_doc");
				}
				
				if (!per_gov_dc.isEmpty()) {
					per_gov_doc = upload_imagemethod(request,per_gov_dc,session, per_gov_doc);
				}
				else {
					per_gov_doc = request.getParameter("hid_per_gov_doc");
				}
				
				if (!balancesheet_dc.isEmpty()) {
					balancesheet_doc = upload_imagemethod(request,balancesheet_dc,session, balancesheet_doc);
				}
				else {
					balancesheet_doc = request.getParameter("hid_balancesheet_doc");
				}
				
				if (!annual_dc.isEmpty()) {
					annual_doc = upload_imagemethod(request,annual_dc,session, annual_doc);
				}
				else {
					annual_doc = request.getParameter("hid_annual_doc");
				}
				
				if (!cchreg_dc.isEmpty()) {
					cchreg_doc = upload_imagemethod(request,cchreg_dc,session, cchreg_doc);
				}
				else {
					cchreg_doc = request.getParameter("hid_cchreg_doc");
				}
				
				if (!add_dc.isEmpty()) {
					add_doc = upload_imagemethod(request,add_dc,session, add_doc);
				}
				else {
					add_doc = request.getParameter("hid_add_doc");
				}
				
				
				
				
				
				
				if(h_id==0) {
					
					
					CLG_REG_INST_INFO_UNDERTAKING_REPORTS rd =new CLG_REG_INST_INFO_UNDERTAKING_REPORTS();
					
					
					if(!trustreg_doc.equals("")) {
					rd.setReg_certi_of_society(trustreg_doc);
					}
					if(!trustdeed_doc.equals("")) {
					rd.setCopy_of_society(trustdeed_doc);
					}
					if(!furnish_bank_doc.equals("")) {
					rd.setUndertaking_letter_furnish_bank_guarantee(furnish_bank_doc);
					}
					if(!noc_state_doc.equals("")) {
						rd.setNoc_doc_state_gov(noc_state_doc);
					}
					if(!affilat_doc.equals("")) {
						rd.setAffiliaion_doc_concern_uni(affilat_doc);
					}
					if(!denial_doc.equals("")) {
						rd.setAyush_permission_letter(denial_doc);
					}
					if(!courtorder_doc.equals("")) {
						rd.setUpload_court_order(courtorder_doc);
					}
					if(!land_doc_clg.equals("")) {
						rd.setLand_document_clg(land_doc_clg);
					}
					if(!building_plan_doc.equals("")) {
						rd.setBuilding_plan(building_plan_doc);
					}
					if(!architect_doc.equals("")) {
						rd.setStatement_doc_architect(architect_doc);
					}
					if(!per_gov_doc.equals("")) {
						rd.setInst_not_admit_stu_without_permission(per_gov_doc);
					}
					if(!balancesheet_doc.equals("")) {
						rd.setAudited_balance_sheet(balancesheet_doc);
					}
					if(!annual_doc.equals("")) {
						rd.setAnnual_report(annual_doc);
					}
					if(!cchreg_doc.equals("")) {
						rd.setSelection_of_students(cchreg_doc);
					}
					if(!add_doc.equals("")) {
						rd.setAdditional_document(add_doc);
					}
					
				
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());
				rd.setInst_id(Integer.parseInt(institude));
				rd.setP_id(p_id);
				
				rd.setCreated_by((userid));
				rd.setCreated_date(date);
			
				
				h_id = (int) sessionHQL.save(rd);
				 data.put("msg", "Save as Draft Successfully");
				sessionHQL.flush();
				sessionHQL.clear();
			}
				else {
				
					CLG_REG_INST_INFO_UNDERTAKING_REPORTS urd = (CLG_REG_INST_INFO_UNDERTAKING_REPORTS) sessionHQL.get(CLG_REG_INST_INFO_UNDERTAKING_REPORTS.class,
						(h_id));
					
					if(!trustreg_doc.equals("")) {
						urd.setReg_certi_of_society(trustreg_doc);
						}
						if(!trustdeed_doc.equals("")) {
						urd.setCopy_of_society(trustdeed_doc);
						}
						if(!furnish_bank_doc.equals("")) {
						urd.setUndertaking_letter_furnish_bank_guarantee(furnish_bank_doc);
						}
						if(!noc_state_doc.equals("")) {
							urd.setNoc_doc_state_gov(noc_state_doc);
						}
						if(!affilat_doc.equals("")) {
							urd.setAffiliaion_doc_concern_uni(affilat_doc);
						}
						if(!denial_doc.equals("")) {
							urd.setAyush_permission_letter(denial_doc);
						}
						if(!courtorder_doc.equals("")) {
							urd.setUpload_court_order(courtorder_doc);
						}
						if(!land_doc_clg.equals("")) {
							urd.setLand_document_clg(land_doc_clg);
						}
						if(!building_plan_doc.equals("")) {
							urd.setBuilding_plan(building_plan_doc);
						}
						if(!architect_doc.equals("")) {
							urd.setStatement_doc_architect(architect_doc);
						}
						if(!per_gov_doc.equals("")) {
							urd.setInst_not_admit_stu_without_permission(per_gov_doc);
						}
						if(!balancesheet_doc.equals("")) {
							urd.setAudited_balance_sheet(balancesheet_doc);
						}
						if(!annual_doc.equals("")) {
							urd.setAnnual_report(annual_doc);
						}
						if(!cchreg_doc.equals("")) {
							urd.setSelection_of_students(cchreg_doc);
						}
						if(!add_doc.equals("")) {
							urd.setAdditional_document(add_doc);
						}
					
				
				
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());
				urd.setInst_id(Integer.parseInt(institude));
				
				urd.setModified_by((userid));
				urd.setModified_date(date);

				sessionHQL.update(urd);
				 data.put("msg", "Draft Update Successfully");
				sessionHQL.flush();
				sessionHQL.clear();
			}

				tx.commit();
				data.put("msg", "Data Save As Draft Successfully.");
				//}

			} catch (RuntimeException e) {
				e.printStackTrace();
				try {
				} catch (RuntimeException rbe) {
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
				return data; 
	}
	 
	//--------------------------------Qualification Add More-------------------------------------------------
	 
	 
	 
//	 
//	 @PostMapping(value = "/quali_inst_dtl_Action")
//	 public @ResponseBody Map<String, String> quali_inst_dtl_Action(ModelMap Mmap, HttpSession session,
//			 HttpServletRequest request,RedirectAttributes ra,String pernt_id) throws IOException, ParseException {
//	 
//	//System.err.println("------parent------"+pernt_id);
//	 
//		Date date = new Date();
//		String username = session.getAttribute("username").toString();
//		String userid = session.getAttribute("userId_for_jnlp").toString();
//		String role = session.getAttribute("roleStaff_lvl").toString();
//		System.err.println("role for regi-----------" + role + "---");
//
//		Session sessionHQL = this.sessionFactory.openSession();
//		Transaction tx = sessionHQL.beginTransaction();
//		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
//		
//		Map<String, String> data = new HashMap<>();
//
//		
//		try {
//			int p_id=Integer.parseInt(request.getParameter("pernt_id"));
//			int h_id=Integer.parseInt(request.getParameter("hid_quali"));
//				
//			
//				String hidden_main = request.getParameter("hidden_main");
//				String hid_quali_inst = request.getParameter("hid_quali_inst"+hidden_main);
//				
//				String typeOfDegree1  = request.getParameter("typeOfDegree"+hidden_main);
//				String awarding_authority  = request.getParameter("awarding_authority"+hidden_main);
//				String passsing_yr  = request.getParameter("passsing_yr"+hidden_main);
//				
//
//				if((h_id) == 0) {
//					
//					CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS_CHILD rd =new CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS_CHILD();
//					
//				rd.setQuali_type(Integer.parseInt(typeOfDegree1));
//				rd.setAwarding_authority(awarding_authority);
//				rd.setPassing_year(passsing_yr);
//
//				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
//				String institude = String.valueOf(ea.getInstitute_id());
//				rd.setInst_id(Integer.parseInt(institude));
//				rd.setP_id(p_id);
//				
//				rd.setCreated_by((userid));
//				rd.setCreated_date(date);
//			
//				
//				int hid_quali_inst1 = (int) sessionHQL.save(rd);
//				 data.put("msg", "Save as Draft Successfully");
//				sessionHQL.flush();
//				sessionHQL.clear();
//				
//			}
//				else {
//				
//					CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS_CHILD urd = (CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS_CHILD) sessionHQL.get(CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS_CHILD.class,
//						(h_id));
//					
//			
//					urd.setQuali_type(Integer.parseInt(typeOfDegree1));
//					urd.setAwarding_authority(awarding_authority);
//					urd.setPassing_year(passsing_yr);
//				
//				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
//				String institude = String.valueOf(ea.getInstitute_id());
//				urd.setInst_id(Integer.parseInt(institude));
//				
//				urd.setModified_by((userid));
//				urd.setModified_date(date);
//
//				sessionHQL.update(urd);
//				 data.put("msg", "Draft Update Successfully");
//				sessionHQL.flush();
//				sessionHQL.clear();
//			}
//
//				tx.commit();
//				data.put("msg", "Data Save As Draft Successfully.");
//				//}
//
//			} catch (RuntimeException e) {
//				e.printStackTrace();
//				try {
//				} catch (RuntimeException rbe) {
//				}
//				throw e;
//			} finally {
//				if (sessionHQL != null) {
//					sessionHQL.close();
//				}
//			}
//				return data; 
//	}
	 
	 
	 
	 
/////////////////////////////////Add More Save for Qualification Institute////////////////////////////////////////
		
		
		
						//ADD MORE SAVE FOR Intake Capacity
						@PostMapping(value = "/quali_inst_dtl_Action")
						public @ResponseBody String quali_inst_dtl_Action( 
						HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
						RedirectAttributes ra,String pernt_id) throws ParseException {
						
							if(request.getHeader("Referer") == null ) { 
								session.invalidate();
								model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
								return  "redirect:/login";
							}
						String role = session.getAttribute("role").toString();
						String roleid1 = session.getAttribute("roleid").toString();
						
						DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
						String userid = session.getAttribute("userId_for_jnlp").toString();
						
						String typeOfDegree1  = request.getParameter("typeOfDegree1");
						System.err.println("=======typeOfDegree1=========="+typeOfDegree1);
						String indno = request.getParameter("indno_quali");
						System.err.println("=======indno=========="+indno);
						String hid_quali_inst = request.getParameter("hid_quali_inst"+indno);
						String typeOfDegree  = request.getParameter("typeOfDegree"+indno);
						System.err.println("-----31 MAR-----"+typeOfDegree);
						String awarding_authority  = request.getParameter("awarding_authority"+indno);
						String passsing_yr  = request.getParameter("passsing_yr"+indno);
						
						String p_id = ibdao.getpid_from_userid(userid).get(0).get(0);
						
					
						
						String hid_quali = request.getParameter("hid_quali"+indno);
						
						
						if (typeOfDegree == "" || typeOfDegree.trim().equals("0") ) {
							ra.addAttribute("msg", "Please Enter Type Of Degree In Head Of Institution Details.");
							return ("Please Enter Type Of Degree In Head of Institution Details.");
						}
						
						if (awarding_authority == null || awarding_authority.trim().equals("")) {
							ra.addAttribute("msg", "Please Enter Awarding Authority In Head of Institution Details.");
							return ("Please Enter Type Of Degree In Head of Institution Details.");
						}
						
						if (passsing_yr == null || passsing_yr.trim().equals("") || passsing_yr.equals("DD/MM/YYYY")) {
							ra.addAttribute("msg", "Please Enter Passing Year In Head of Institution Details.");
							return ("Please Enter Type Of Degree In Head of Institution Details.");
						}
						
						System.err.println("--hidden------"+hid_quali_inst);
					
						Date date = new Date();
						String username = principal.getName();
						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
						
						
						
						try {
							CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS_CHILD quali_inst =new CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS_CHILD();
						
						
							quali_inst.setQuali_type(Integer.parseInt(typeOfDegree));
							quali_inst.setAwarding_authority(awarding_authority);
							quali_inst.setPassing_year(passsing_yr);
							
						UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
						String institude = String.valueOf(ea.getInstitute_id());
						quali_inst.setInst_id(Integer.parseInt(institude));
						quali_inst.setP_id(Integer.parseInt(p_id));
						
						quali_inst.setCreated_by((userid));
						quali_inst.setCreated_date(date);
						if (Integer.parseInt(hid_quali) == 0) {
						//				librarian_detail.setP_id(Integer.parseInt(p_hid_library));
						int hid_library1= (Integer) sessionHQL.save(quali_inst);
						
						sessionHQL.flush();
						sessionHQL.clear();
						tx.commit();
						return String.valueOf(hid_library1) ;
						}
						else {
						
						CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS_CHILD quali_u = (CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS_CHILD) sessionHQL
								.get(CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS_CHILD.class, Integer.parseInt(hid_quali));
						
						quali_u.setQuali_type(Integer.parseInt(typeOfDegree));
						quali_u.setAwarding_authority(awarding_authority);
						quali_u.setPassing_year(passsing_yr);
						
						quali_u.setInst_id(Integer.parseInt(institude));
						
						//			librarian_detail_u.setLibrary_assistants_name(library_assistants_name);
						//			librarian_detail_u.setAssistants_qualification(assistants_qualification);
						quali_u.setModified_by((userid));
						quali_u.setModified_date(date);
						sessionHQL.update(quali_u);
						sessionHQL.flush();
						sessionHQL.clear();
						tx.commit();
						
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
						return  "Data Saved Successfully";
						}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	// ----------------search-------------------------
	 
	 
	
		
		//----------------------Upload pdf method-------------------------------------------
		
		public String upload_imagemethod(HttpServletRequest request,MultipartFile mul,HttpSession session,String id) throws IOException {
			
			String extension=""; //add line
			String fname = ""; //add line
			
			request.getSession().setAttribute(id, "/srv"+ File.separator + "Document");
			
			MultipartFile file = mul;
			
			if (!file.getOriginalFilename().isEmpty()) {
				
				byte[] bytes = file.getBytes();
				String  mnhFilePath = session.getAttribute(id).toString();
				
		        File dir = new File(mnhFilePath);
				if (!dir.exists())
					dir.mkdirs();
				String filename = file.getOriginalFilename();
						
				int j = filename.lastIndexOf('.');
				if (j >= 0) {
					extension = filename.substring(j+1);
				}
				java.util.Date date1= new java.util.Date();
				fname = dir.getAbsolutePath() + File.separator + (new Timestamp(date1.getTime())).toString().replace(":", "").toString().replace(".", ".").toString().replace(" ","").toString().replace("-","").toString()+id+"."+extension;
				
				File serverFile = new File(fname);	               
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);	                
				stream.close();

			}else {

				
			}
			return fname;
			
			}
		
		
		
/////////////////////////////////Add More Save for Intake Capacity////////////////////////////////////////
		
		
		
		//ADD MORE SAVE FOR Intake Capacity
		@PostMapping(value = "/intake_capacity_Details_Save_Draft_Action")
		public @ResponseBody String intake_capacity_Details_Save_Draft_Action( 
				HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
				RedirectAttributes ra,String pernt_id) throws ParseException {

			if(request.getHeader("Referer") == null ) { 
				 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return  "redirect:/login";
			 }
			String role = session.getAttribute("role").toString();
			String roleid1 = session.getAttribute("roleid").toString();

			DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			String userid = session.getAttribute("userId_for_jnlp").toString();
			
		//	int p_id=Integer.parseInt(request.getParameter("pernt_id"));
		//	System.err.println("-------p_id-------"+p_id);
			
			String indno = request.getParameter("indno_intake");
			
			String year = request.getParameter("year"+indno);
			String per_capacity = request.getParameter("per_capacity"+indno);
		//	System.err.println("-----31 mar-----"+per_capacity);
			String intake_capacity = request.getParameter("intake_capacity"+indno);
			
			String hid_intake = request.getParameter("hid_intake"+indno);
			

			String amitted_seat = request.getParameter("amitted_seat"+indno);
			String permitted_seat = request.getParameter("permitted_seat"+indno);
			String sanctioned_seat = request.getParameter("sanctioned_seat"+indno);
			String govt_quota_ug = request.getParameter("govt_quota_ug"+indno);
			String mang_quota_ug = request.getParameter("mang_quota_ug"+indno);
			String court_order = request.getParameter("court_order"+indno);
			String last_student = request.getParameter("last_student"+indno);
			String last_stu_add_date = request.getParameter("last_stu_add_date"+indno);
			String appeared_stu_ug = request.getParameter("appeared_stu_ug"+indno);
			String passed_stu_ug = request.getParameter("passed_stu_ug"+indno);
			
			
			
			
			String p_id = ibdao.getpid_from_userid(userid).get(0).get(0);
		//	String p_hid_library = CIDao.getp_idfromuser_id(userid).get(0).get(0);
			Date date = new Date();
			String username = principal.getName();
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			
			
			if (year.equals("0")) {
				ra.addAttribute("msg", "Please Select Year");
				return "Please Select Year";
			}
			if (per_capacity.equals("0")) {
				ra.addAttribute("msg", "Please Select Permission");
				return "Please Select Permission";
			}
			if (intake_capacity == null || intake_capacity.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Intake Capacity");
				return "Please Enter Intake Capacity";
			}
			if (validation.isOnlyNumber(intake_capacity) == false) {
				ra.addAttribute("msg","Intake Capacity" +validation.isOnlyNumberMSG);
				return "Intake Capacity " +validation.isOnlyNumberMSG;
			}
			if (amitted_seat == null || amitted_seat.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Admitted Seat");
				return "Please Enter Admitted Seat";
			}
			if (validation.isOnlyNumber(amitted_seat) == false) {
				ra.addAttribute("msg","Admitted Seat" +validation.isOnlyNumberMSG);
				return "Admitted Seat " +validation.isOnlyNumberMSG;
			}
			if (permitted_seat == null || permitted_seat.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Permitted Seat");
				return "Please Enter Permitted Seat";
			}
			if (validation.isOnlyNumber(permitted_seat) == false) {
				ra.addAttribute("msg","Permitted Seat" +validation.isOnlyNumberMSG);
				return "Permitted Seat " +validation.isOnlyNumberMSG;
			}
			if (sanctioned_seat == null || sanctioned_seat.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Sanctioned Seat");
				return "Please Enter Sanctioned Seat";
			}
			if (validation.isOnlyNumber(sanctioned_seat) == false) {
				ra.addAttribute("msg","Sanctioned Seat" +validation.isOnlyNumberMSG);
				return "Sanctioned Seat " +validation.isOnlyNumberMSG;
			}
			if (govt_quota_ug == null || govt_quota_ug.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Total Students admitted with Govt Quota");
				return "Please Enter Total Students admitted with Govt Quota";
			}
			if (validation.isOnlyNumber(govt_quota_ug) == false) {
				ra.addAttribute("msg","Total Students admitted with Govt Quota" +validation.isOnlyNumberMSG);
				return "Total Students admitted with Govt Quota " +validation.isOnlyNumberMSG;
			}
			if (mang_quota_ug == null || mang_quota_ug.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Total Students admitted with Management Quota ");
				return "Please Enter Total Students admitted with Management Quota ";
			}
			if (validation.isOnlyNumber(mang_quota_ug) == false) {
				ra.addAttribute("msg","Total Students admitted with Management Quota" +validation.isOnlyNumberMSG);
				return "Total Students admitted with Management Quota " +validation.isOnlyNumberMSG;
			}
			if (court_order.equals("0")) {
				ra.addAttribute("msg", "Please Select Students admitted by Court order");
				return "Please Select Students admitted by Court order";
			}
			if (last_student == null || last_student.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Name of the Last Student Admitted ");
				return "Please Enter Name of the Last Student Admitted";
			}
			if (validation.maxlengthcheck100(last_student) == false) {
				ra.addAttribute("msg", "Name of the Last Student Admitted" + validation.MaxlengthcheckMSG100);
				return "Name of the Last Student Admitted " +validation.MaxlengthcheckMSG100;
			}
			if (last_stu_add_date == null || last_stu_add_date.trim().equals("") || last_stu_add_date.equals("DD/MM/YYYY")) {
				ra.addAttribute("msg", "Please Enter Date of Admission of the last Student");
				return "Please Enter Date of Admission of the last Student";
			}
			if (validation.isOnlyDateFormat(last_stu_add_date) == false) {
				ra.addAttribute("msg", "Date of Admission of the last Student " + validation.isOnlyDateFormatMSG);
				return "" +validation.isOnlyDateFormatMSG;
			}
			if (appeared_stu_ug == null || appeared_stu_ug.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Total Final Year students appeared for exams");
				return "Please Enter Total Final Year students appeared for exams";
			}
			if (validation.isOnlyNumber(appeared_stu_ug) == false) {
				ra.addAttribute("msg","Total Final Year students appeared for exams" +validation.isOnlyNumberMSG);
				return "Total Final Year students appeared for exams " +validation.isOnlyNumberMSG;
			}
			if (passed_stu_ug == null || passed_stu_ug.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Total Final Year students passed out in exams ");
				return "Total Final Year students passed out in exams  ";
			}
			if (validation.isOnlyNumber(passed_stu_ug) == false) {
				ra.addAttribute("msg","Total Final Year students passed out in exams " +validation.isOnlyNumberMSG);
				return "Total Final Year students passed out in exams  " +validation.isOnlyNumberMSG;
			}
			
			try {
				CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY intake_cap =new CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY();
				
				
				intake_cap.setYear(year);
				intake_cap.setPermission(Integer.parseInt(per_capacity));
				intake_cap.setIntake_capacity(intake_capacity);
				
				intake_cap.setAmitted_seat(Integer.parseInt(amitted_seat));
				intake_cap.setPermitted_seat(Integer.parseInt(permitted_seat));
				intake_cap.setSanctioned_seat(Integer.parseInt(sanctioned_seat));
				intake_cap.setGovt_quota_ug(Integer.parseInt(govt_quota_ug));
				intake_cap.setMang_quota_ug(Integer.parseInt(mang_quota_ug));
				intake_cap.setCourt_order(court_order);
				intake_cap.setLast_student(last_student);
				intake_cap.setLast_stu_add_date(datePickerFormat.parse(last_stu_add_date));
				intake_cap.setAppeared_stu_ug(Integer.parseInt(appeared_stu_ug));
				intake_cap.setPassed_stu_ug(Integer.parseInt(passed_stu_ug));
				
				
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institude = String.valueOf(ea.getInstitute_id());
				intake_cap.setInst_id(Integer.parseInt(institude));
				intake_cap.setP_id(Integer.parseInt(p_id));
				
				intake_cap.setCreated_by((userid));
				intake_cap.setCreated_date(date);
					if (Integer.parseInt(hid_intake) == 0) {
		//				librarian_detail.setP_id(Integer.parseInt(p_hid_library));
						int hid_library1= (Integer) sessionHQL.save(intake_cap);
						
						sessionHQL.flush();
						sessionHQL.clear();
						tx.commit();
						return String.valueOf(hid_library1) ;
				}
					else {

						CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY intake_detail_u = (CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY) sessionHQL
								.get(CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY.class, Integer.parseInt(hid_intake));
						
						intake_detail_u.setYear(year);
						intake_detail_u.setPermission(Integer.parseInt(per_capacity));
						intake_detail_u.setIntake_capacity(intake_capacity);
						
						intake_detail_u.setAmitted_seat(Integer.parseInt(amitted_seat));
						intake_detail_u.setPermitted_seat(Integer.parseInt(permitted_seat));
						intake_detail_u.setSanctioned_seat(Integer.parseInt(sanctioned_seat));
						intake_detail_u.setGovt_quota_ug(Integer.parseInt(govt_quota_ug));
						intake_detail_u.setMang_quota_ug(Integer.parseInt(mang_quota_ug));
						intake_detail_u.setCourt_order(court_order);
						intake_detail_u.setLast_student(last_student);
						intake_detail_u.setLast_stu_add_date(datePickerFormat.parse(last_stu_add_date));
						intake_detail_u.setAppeared_stu_ug(Integer.parseInt(appeared_stu_ug));
						intake_detail_u.setPassed_stu_ug(Integer.parseInt(passed_stu_ug));
						
						intake_detail_u.setInst_id(Integer.parseInt(institude));
						
			//			librarian_detail_u.setLibrary_assistants_name(library_assistants_name);
			//			librarian_detail_u.setAssistants_qualification(assistants_qualification);
						intake_detail_u.setModified_by((userid));
						intake_detail_u.setModified_date(date);
						sessionHQL.update(intake_detail_u);
						sessionHQL.flush();
						sessionHQL.clear();
						tx.commit();
						
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
			return  "Data Saved Successfully";
		}
		
		
		
		
//------------------------------------------------------------------------------------------------
		
		
		
		
		
/////////////////////////////////Add More Save for Intake Capacity_PG////////////////////////////////////////


					
		@PostMapping(value = "/intake_capacity_pg_Details_Save_Draft_Action")
		public @ResponseBody String intake_capacity_pg_Details_Save_Draft_Action( 
		HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
		RedirectAttributes ra,String pernt_id) throws ParseException {
		
						if(request.getHeader("Referer") == null ) { 
						session.invalidate();
						model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						return  "redirect:/login";
						}
						String role = session.getAttribute("role").toString();
						String roleid1 = session.getAttribute("roleid").toString();
						
						DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
						String userid = session.getAttribute("userId_for_jnlp").toString();
						
						//	int p_id=Integer.parseInt(request.getParameter("pernt_id"));
						//	System.err.println("-------p_id-------"+p_id);
						
						String indno = request.getParameter("indno_intake_pg");
						
						String year = request.getParameter("year_pg"+indno);
						String per_capacity = request.getParameter("per_capacity_pg"+indno);
						//	System.err.println("-----31 mar-----"+per_capacity);
						String intake_capacity = request.getParameter("intake_capacity_pg"+indno);
						
						String admited_seat_pg = request.getParameter("admited_seat_pg"+indno);
						String permitted_seat_pg = request.getParameter("permitted_seat_pg"+indno);
						String sanction_seat_pg = request.getParameter("sanction_seat_pg"+indno);
						String stu_govt_quota = request.getParameter("stu_govt_quota"+indno);
						String stu_mgmt_quota = request.getParameter("stu_mgmt_quota"+indno);
						String stu_court_order = request.getParameter("stu_court_order"+indno);
						String last_stu_admitted = request.getParameter("last_stu_admitted"+indno);
						String last_stu_date_admitted = request.getParameter("last_stu_date_admitted"+indno);
						String final_stu_app_exam = request.getParameter("final_stu_app_exam"+indno);
						String final_stu_pass_exam = request.getParameter("final_stu_pass_exam"+indno);
						
						

						
						String hid_intake = request.getParameter("hid_intake_pg"+indno);
						System.err.println("--hidden------"+hid_intake);
						
						String p_id = ibdao.getpid_from_userid(userid).get(0).get(0);
						//	String p_hid_library = CIDao.getp_idfromuser_id(userid).get(0).get(0);
						Date date = new Date();
						String username = principal.getName();
						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
						
						if (year.equals("0")) {
							ra.addAttribute("msg", "Please Select Year");
							return "Please Select Year";
						}
						if (per_capacity.equals("0")) {
							ra.addAttribute("msg", "Please Select Permission");
							return "Please Select Permission";
						}
						if (intake_capacity == null || intake_capacity.trim().equals("")) {
							ra.addAttribute("msg", "Please Enter Intake Capacity");
							return "Please Enter Intake Capacity";
						}
						if (validation.isOnlyNumber(intake_capacity) == false) {
							ra.addAttribute("msg","Intake Capacity" +validation.isOnlyNumberMSG);
							return "Intake Capacity " +validation.isOnlyNumberMSG;
						}
						
						try {
							CLG_REG_INST_INFO_INTAKE_CAPACITY_PG intake_cap =new CLG_REG_INST_INFO_INTAKE_CAPACITY_PG();
						
						
						intake_cap.setYear(year);
						intake_cap.setPermission(Integer.parseInt(per_capacity));
						intake_cap.setIntake_capacity_pg(intake_capacity);
						
						intake_cap.setAdmitted_seat(admited_seat_pg);
						intake_cap.setPermitted_seat(permitted_seat_pg);
						intake_cap.setSanction_seat(sanction_seat_pg);
						intake_cap.setStudent_govt_quota(stu_govt_quota);
						intake_cap.setStudent_mgmt_quota(stu_mgmt_quota);
						intake_cap.setAdmitted_court_order(Integer.parseInt(stu_court_order));
						intake_cap.setLast_stu_admitted(last_stu_admitted);
						intake_cap.setDate_stu_admitted(datePickerFormat.parse(last_stu_date_admitted));
						intake_cap.setTotal_stu_app_exam(final_stu_app_exam);
						intake_cap.setTotal_stu_pass_exam(final_stu_pass_exam);
						
						
						
						
						
						
						UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
						String institude = String.valueOf(ea.getInstitute_id());
						intake_cap.setInst_id(Integer.parseInt(institude));
						intake_cap.setP_id(Integer.parseInt(p_id));
						
						intake_cap.setCreated_by((userid));
						intake_cap.setCreated_date(date);
						if (Integer.parseInt(hid_intake) == 0) {
						//				librarian_detail.setP_id(Integer.parseInt(p_hid_library));
						int hid_library1= (Integer) sessionHQL.save(intake_cap);
						
						sessionHQL.flush();
						sessionHQL.clear();
						tx.commit();
						return String.valueOf(hid_library1) ;
						}
						else {
						
							CLG_REG_INST_INFO_INTAKE_CAPACITY_PG intake_detail_u = (CLG_REG_INST_INFO_INTAKE_CAPACITY_PG) sessionHQL
								.get(CLG_REG_INST_INFO_INTAKE_CAPACITY_PG.class, Integer.parseInt(hid_intake));
						
						intake_detail_u.setYear(year);
						intake_detail_u.setPermission(Integer.parseInt(per_capacity));
						intake_detail_u.setIntake_capacity_pg(intake_capacity);
						intake_detail_u.setAdmitted_seat(admited_seat_pg);
						intake_detail_u.setPermitted_seat(permitted_seat_pg);
						intake_detail_u.setSanction_seat(sanction_seat_pg);
						intake_detail_u.setStudent_govt_quota(stu_govt_quota);
						intake_detail_u.setStudent_mgmt_quota(stu_mgmt_quota);
						intake_detail_u.setAdmitted_court_order(Integer.parseInt(stu_court_order));
						intake_detail_u.setLast_stu_admitted(last_stu_admitted);
						intake_detail_u.setDate_stu_admitted(datePickerFormat.parse(last_stu_date_admitted));
						intake_detail_u.setTotal_stu_app_exam(final_stu_app_exam);
						intake_detail_u.setTotal_stu_pass_exam(final_stu_pass_exam);
						
						intake_detail_u.setInst_id(Integer.parseInt(institude));
						
						//			librarian_detail_u.setLibrary_assistants_name(library_assistants_name);
						//			librarian_detail_u.setAssistants_qualification(assistants_qualification);
						intake_detail_u.setModified_by((userid));
						intake_detail_u.setModified_date(date);
						sessionHQL.update(intake_detail_u);
						sessionHQL.flush();
						sessionHQL.clear();
						tx.commit();
						
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
						return  "Data Saved Successfully";
						}




//------------------------ADD MORE DELETE FOR INTAKE CAPACITY
	@PostMapping(value = "/delete_intake_Capacity")
	public @ResponseBody String delete_intake_Capacity(String hid_intake,HttpSession session1) {

		String msg="";

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		System.err.println("doc_attach_id===================="+hid_intake);
		try {
			String hqlUpdate = "delete from CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY where id=:id";
			int app = session.createQuery(hqlUpdate).setInteger("id",Integer.parseInt(hid_intake)).executeUpdate();
			tx.commit();
			session.close();
			if (app > 0) {
				msg = "Data Deleted Successfully.";
			} else {
				msg = "Data not Deleted.";
			}
		} catch (Exception e) {
			msg = "CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.";
		}finally {
			
		}
		return msg;
	}
	
	
	//------------------------ADD MORE DELETE FOR INTAKE CAPACITY PG
		@PostMapping(value = "/delete_intake_Capacity_pg")
		public @ResponseBody String delete_intake_Capacity_pg(String hid_intake,HttpSession session1) {

			String msg="";

			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			System.err.println("doc_attach_id===================="+hid_intake);
			try {
				String hqlUpdate = "delete from CLG_REG_INST_INFO_INTAKE_CAPACITY_PG where id=:id";
				int app = session.createQuery(hqlUpdate).setInteger("id",Integer.parseInt(hid_intake)).executeUpdate();
				tx.commit();
				session.close();
				if (app > 0) {
					msg = "Data Deleted Successfully.";
				} else {
					msg = "Data not Deleted.";
				}
			} catch (Exception e) {
				msg = "CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.";
			}finally {
				
			}
			return msg;
		}
	
	
	
	//------------------------ADD MORE DELETE FOR Quali 
		@PostMapping(value = "/delete_quali")
		public @ResponseBody String delete_quali(String hid_quali,HttpSession session1) {

			String msg="";

			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			System.err.println("doc_attach_id===================="+hid_quali);
			try {
				String hqlUpdate = "delete from CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS_CHILD where id=:id";
				int app = session.createQuery(hqlUpdate).setInteger("id",Integer.parseInt(hid_quali)).executeUpdate();
				tx.commit();
				session.close();
				if (app > 0) {
					msg = "Data Deleted Successfully.";
				} else {
					msg = "Data not Deleted.";
				}
			} catch (Exception e) {
				msg = "CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.";
			}finally {
				
			}
			return msg;
		}
	
	
	
	

	
	
//	//ADD MORE FETCH FOR INTAKE CAPACITY
	@PostMapping("admin/getIntake_Cap")
	public @ResponseBody List<Map<String, Object>> getIntake_Cap(HttpSession session,String pernt_id) {
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institude = String.valueOf(ea.getInstitute_id());
		
		return ibdao.getIntake_Cap(institude);

	}
	
	@PostMapping("admin/getIntake_Cap_pg")
	public @ResponseBody List<Map<String, Object>> getIntake_Cap_pg(HttpSession session,String pernt_id) {
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institude = String.valueOf(ea.getInstitute_id());
		
		return ibdao.getIntake_Cap_pg(institude);

	}
	//ADD MORE FETCH FOR INTAKE CAPACITY PG
//	@RequestMapping(value = "admin/getIntake_Cap_pg", method = RequestMethod.POST)
//	public @ResponseBody List<CLG_REG_INST_INFO_INTAKE_CAPACITY_PG> getIntake_Cap_pg(HttpSession session,String pernt_id) {
//		String userid = session.getAttribute("userId_for_jnlp").toString();
//		//String p_hid_library = CIDao.getp_idfromuser_id(userid).get(0).get(0);
//		
//		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
//		String institude = String.valueOf(ea.getInstitute_id());
//		Session sessionHQL = sessionFactory.openSession();
//		Transaction tx = sessionHQL.beginTransaction();
//		Query q = sessionHQL
//				.createQuery("from CLG_REG_INST_INFO_INTAKE_CAPACITY_PG where inst_id=:inst_id ");
//		
//		q.setParameter("inst_id", Integer.parseInt(institude));
//		
//		@SuppressWarnings("unchecked")
//		List<CLG_REG_INST_INFO_INTAKE_CAPACITY_PG> clist = (List<CLG_REG_INST_INFO_INTAKE_CAPACITY_PG>) q.list();
//	
//		tx.commit();
//		sessionHQL.close();
//		return clist;
//	}

	
	
	
	
	
	//ADD MORE FETCH FOR QUALIFICATION DETAILS
	@RequestMapping(value = "admin/getquali", method = RequestMethod.POST)
	public @ResponseBody List<CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS_CHILD> getquali(HttpSession session,String pernt_id) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		//String p_hid_library = CIDao.getp_idfromuser_id(userid).get(0).get(0);
		
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institude = String.valueOf(ea.getInstitute_id());
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL
				.createQuery("from CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS_CHILD where inst_id=:inst_id ");
		
		q.setParameter("inst_id", Integer.parseInt(institude));
		
		@SuppressWarnings("unchecked")
		List<CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS_CHILD> clist = (List<CLG_REG_INST_INFO_HEAD_OF_INSTITUTION_DETAILS_CHILD>) q.list();
	
		tx.commit();
		sessionHQL.close();
		return clist;
	}
		
	
	
	
	
	//SAVE COURSE INTAKE CAPACITY DETAILS
		@PostMapping(value = "/Couse_intake_cap_Save_Draft_Action")
		public @ResponseBody String Couse_intake_cap_Save_Draft_Action( 
				HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
				RedirectAttributes ra, @RequestParam(value = "denial_doc", required = false) MultipartFile denial_dc,
				 @RequestParam(value = "courtorder_doc", required = false) MultipartFile courtorder_dc, 
				 @RequestParam(value = "per_gov_doc", required = false) MultipartFile per_gov_dc,
				 @RequestParam(value = "cchreg_doc", required = false) MultipartFile cchreg_dc,
				 @RequestParam(value = "lastfiveguide", required = false) MultipartFile lastfiveguide_doc,
				@RequestParam(value = "neet_score", required = false) MultipartFile neet_score_doc,
				@RequestParam(value = "undertakingofstudent", required = false) MultipartFile undertakingofstudent_doc,
				@RequestParam(value = "biometricattendance", required = false) MultipartFile biometricattendance_doc,MultipartHttpServletRequest mul) throws ParseException, IOException {

			if(request.getHeader("Referer") == null ) { 
				 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return  "redirect:/login";
			 }
			String role = session.getAttribute("role").toString();
			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//				if(val == false) {
//					return  "AccessTiles";
//			}
			String userid = session.getAttribute("userId_for_jnlp").toString();
			UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
			String institude = String.valueOf(ea.getInstitute_id());
			
			DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			
			
			ArrayList<ArrayList<String>> course_list_UG = ibdao.getAllCourse_UG(Integer.parseInt(institude));
		//	System.err.println("ABC-UG----------------"+ibdao.getAllCourse_UG(nteger.parseInt(institude)).size());
			ArrayList<ArrayList<String>> course_list_PG = ibdao.getAllCourse_PG();
			System.err.println("ABC---PG--------------"+ibdao.getAllCourse_PG().size());
		
			
			String p_id = ibdao.getpid_from_userid(userid).get(0).get(0);
			
			
			
		//	int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
			Date date = new Date();
			String username = principal.getName();
			Session sessionHQL = this.sessionFactory.openSession();
			
			
			

			try {
				
				
				Transaction tx1 = sessionHQL.beginTransaction();
				CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY_UG intake_cap_ug =new CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY_UG();
				
				for (int i = 0; i < course_list_UG.size(); i++) {
					String course_id_ug = request.getParameter("course_id_ug"+course_list_UG.get(i).get(0));
					String course_name_ug = request.getParameter("course_name_ug_"+course_list_UG.get(i).get(0));
					String intake_cap_course_ug = request.getParameter("intake_cap_course_ug_"+course_list_UG.get(i).get(0));
					String hid_course_ug = request.getParameter("hid_course_ug_"+course_list_UG.get(i).get(0));
					
					
					if (intake_cap_course_ug == null || intake_cap_course_ug.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Intake Capacity in UG Courses");
						return "Please Enter Intake Capacity";
					}
					if (validation.isOnlyNumber(intake_cap_course_ug) == false) {
						ra.addAttribute("msg","Intake Capacity" +validation.isOnlyNumberMSG);
						return "Intake Capacity in UG Courses " +validation.isOnlyNumberMSG;
					}

					

					
					intake_cap_ug.setCourse_id(Integer.parseInt(course_id_ug));
					intake_cap_ug.setCourse_name(course_name_ug);
					intake_cap_ug.setIntake_capacity(intake_cap_course_ug);
					intake_cap_ug.setCreated_by((userid));
					intake_cap_ug.setCreated_date(date);
					intake_cap_ug.setInst_id(Integer.parseInt(institude));
					intake_cap_ug.setP_id(Integer.parseInt(p_id));
					
					
					if (Integer.parseInt(hid_course_ug) == 0) {
					//	constructed_area_dept_detail.setP_id(Integer.parseInt(p_hid_constructed_area));
						int hid_department_area1= (Integer) sessionHQL.save(intake_cap_ug);
						sessionHQL.flush();
						sessionHQL.clear();
					}
					else {
						CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY_UG intake_cap_ug_u = (CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY_UG) sessionHQL
								.get(CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY_UG.class, Integer.parseInt(hid_course_ug));
						
						intake_cap_ug_u.setCourse_id(Integer.parseInt(course_id_ug));
						intake_cap_ug_u.setCourse_name(course_name_ug);
						intake_cap_ug_u.setIntake_capacity(intake_cap_course_ug);
						intake_cap_ug_u.setModified_by((userid));
						intake_cap_ug_u.setModified_date(date);
						sessionHQL.update(intake_cap_ug_u);
						sessionHQL.flush();
						sessionHQL.clear();
						
					}
				
				}
				tx1.commit();
				
				Transaction tx = sessionHQL.beginTransaction();
				CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY_PG intake_cap_pg =new CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY_PG();
				
				for (int i = 0; i < course_list_PG.size(); i++) {
				String course_id_pg = request.getParameter("course_id_pg"+course_list_PG.get(i).get(0));
				String course_name_pg = request.getParameter("course_name_pg_"+course_list_PG.get(i).get(0));
				String intake_cap_course_pg = request.getParameter("intake_cap_course_pg_"+course_list_PG.get(i).get(0));
				String hid_course_pg = request.getParameter("hid_course_pg_"+course_list_PG.get(i).get(0));
				

				if (intake_cap_course_pg == null || intake_cap_course_pg.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Intake Capacity in PG Courses");
					return "Please Enter Intake Capacity";
				}
				if (validation.isOnlyNumber(intake_cap_course_pg) == false) {
					ra.addAttribute("msg","Intake Capacity" +validation.isOnlyNumberMSG);
					return "Intake Capacity in PG Courses " +validation.isOnlyNumberMSG;
				}
				

				
				
				intake_cap_pg.setCourse_id(Integer.parseInt(course_id_pg));
				intake_cap_pg.setCourse_name(course_name_pg);
				intake_cap_pg.setIntake_capacity(intake_cap_course_pg);
				intake_cap_pg.setCreated_by((userid));
				intake_cap_pg.setCreated_date(date);
				intake_cap_pg.setInst_id(Integer.parseInt(institude));
				intake_cap_pg.setP_id(Integer.parseInt(p_id));
				
					if (Integer.parseInt(hid_course_pg) == 0) {
				//		constructed_area_detail.setP_id(Integer.parseInt(p_hid_constructed_area));
						int hid_course_pg1= (Integer) sessionHQL.save(intake_cap_pg);
						sessionHQL.flush();
						sessionHQL.clear();
						//tx.commit();
						//return String.valueOf(hid_course_pg1) ;
				}
					else {

						CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY_PG intake_cap_pg_u = (CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY_PG) sessionHQL
								.get(CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY_PG.class, Integer.parseInt(hid_course_pg));
						
						intake_cap_pg_u.setCourse_id(Integer.parseInt(course_id_pg));
						intake_cap_pg_u.setCourse_name(course_name_pg);
						intake_cap_pg_u.setIntake_capacity(intake_cap_course_pg);
						intake_cap_pg_u.setModified_by((userid));
						intake_cap_pg_u.setModified_date(date);
						sessionHQL.update(intake_cap_pg_u);
						sessionHQL.flush();
						sessionHQL.clear();
					
						
						
					}
				}
				tx.commit();
				
				Transaction tx2 = sessionHQL.beginTransaction();
				
				CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY_CHILD intake_cap_child =new CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY_CHILD();
				
				
				
				
				String denial_doc  = "denial_doc";
				String courtorder_doc  = "courtorder_doc";
				String per_gov_doc  = "per_gov_doc";
				String cchreg_doc  ="cchreg_doc";
				String lastfiveguide = "lastfiveguide";
				String neet_score = "neet_score";
				String undertakingofstudent = "undertakingofstudent";
				String biometricattendance = "biometricattendance";
				String hid_for_doc = request.getParameter("hid_for_doc");
				String intimatedcheck = request.getParameter("intimatedcheck");
				String undertakingcheck = request.getParameter("undertakingcheck");
				
				
				
				if (!denial_dc.isEmpty()) {
					denial_doc = upload_imagemethod(request,denial_dc,session, denial_doc);
				}
				else {
					denial_doc = request.getParameter("hid_denial_doc");
				}
				
				if (!courtorder_dc.isEmpty()) {
					courtorder_doc = upload_imagemethod(request,courtorder_dc,session, courtorder_doc);
				}
				else {
					courtorder_doc = request.getParameter("hid_courtorder_doc");
				}
				if (!per_gov_dc.isEmpty()) {
					per_gov_doc = upload_imagemethod(request,per_gov_dc,session, per_gov_doc);
				}
				else {
					per_gov_doc = request.getParameter("hid_per_gov_doc");
				}
				if (!cchreg_dc.isEmpty()) {
					cchreg_doc = upload_imagemethod(request,cchreg_dc,session, cchreg_doc);
				}
				else {
					cchreg_doc = request.getParameter("hid_cchreg_doc");
				}
				if (!lastfiveguide_doc.isEmpty()) {
					lastfiveguide = upload_imagemethod(request,lastfiveguide_doc,session, lastfiveguide);
				}
				else {
					lastfiveguide = request.getParameter("hid_lastfiveguide");
				}
				
				if (!neet_score_doc.isEmpty()) {
					neet_score = upload_imagemethod(request,neet_score_doc,session, neet_score);
				}
				else {
					neet_score = request.getParameter("hid_neet_score");
				}
				
				if (!undertakingofstudent_doc.isEmpty()) {
					undertakingofstudent = upload_imagemethod(request,undertakingofstudent_doc,session, undertakingofstudent);
				}
				else {
					undertakingofstudent = request.getParameter("hid_undertakingofstudent");
				}
				
				if (!biometricattendance_doc.isEmpty()) {
					biometricattendance = upload_imagemethod(request,biometricattendance_doc,session, biometricattendance);
				}
				else {
					biometricattendance = request.getParameter("hid_biometricattendance");
				}
				
				
				
				if(!denial_doc.equals("")) {
					intake_cap_child.setAyush_permission_letter(denial_doc);
				}
				if(!courtorder_doc.equals("")) {
					intake_cap_child.setUpload_court_order(courtorder_doc);
				}
				if(!per_gov_doc.equals("")) {
					intake_cap_child.setInst_not_admit_stu_without_permission(per_gov_doc);
				}
				if(!cchreg_doc.equals("")) {
					intake_cap_child.setSelection_of_students(cchreg_doc);
				}
				intake_cap_child.setLastfiveguide(lastfiveguide);
				intake_cap_child.setNeet_score(neet_score);
				intake_cap_child.setUndertakingofstudent(undertakingofstudent);
				intake_cap_child.setBiometricattendance(biometricattendance);
				intake_cap_child.setIntimatedcheck(intimatedcheck);
				if(intimatedcheck.equals("2")) {
					intake_cap_child.setUndertakingcheck("0");
				}else {
					intake_cap_child.setUndertakingcheck(undertakingcheck);
				}
				intake_cap_child.setCreated_by((userid));
				intake_cap_child.setCreated_date(date);
				intake_cap_child.setInst_id(Integer.parseInt(institude));
				intake_cap_child.setP_id(Integer.parseInt(p_id));
				
				
				if (Integer.parseInt(hid_for_doc) == 0) {
					
							int hid_course_pg2= (Integer) sessionHQL.save(intake_cap_child);
							sessionHQL.flush();
							sessionHQL.clear();
							//tx.commit();
							//return String.valueOf(hid_course_pg1) ;
					}
				
				else {

					CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY_CHILD intake_cap_child_u = (CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY_CHILD) sessionHQL
							.get(CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY_CHILD.class, Integer.parseInt(hid_for_doc));
					
					if(!denial_doc.equals("")) {
						intake_cap_child_u.setAyush_permission_letter(denial_doc);
					}
					if(!courtorder_doc.equals("")) {
						intake_cap_child_u.setUpload_court_order(courtorder_doc);
					}
					if(!per_gov_doc.equals("")) {
						intake_cap_child_u.setInst_not_admit_stu_without_permission(per_gov_doc);
					}
					if(!cchreg_doc.equals("")) {
						intake_cap_child_u.setSelection_of_students(cchreg_doc);
					}
					intake_cap_child_u.setLastfiveguide(lastfiveguide);
					intake_cap_child_u.setNeet_score(neet_score);
					intake_cap_child_u.setUndertakingofstudent(undertakingofstudent);
					intake_cap_child_u.setBiometricattendance(biometricattendance);
					intake_cap_child_u.setIntimatedcheck(intimatedcheck);
					if(intimatedcheck.equals("2")) {
						intake_cap_child_u.setUndertakingcheck("0");
					}else {
						intake_cap_child_u.setUndertakingcheck(undertakingcheck);
					}
					intake_cap_child_u.setModified_by((userid));
					intake_cap_child_u.setModified_date(date);
					sessionHQL.update(intake_cap_child_u);
					sessionHQL.flush();
					sessionHQL.clear();
				
					
					
				}
				tx2.commit();
				
				
					
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
			return  "Data Saved Successfully";
		}
	
	
	
	
	
		//FETCH COURSE INTAKE CAPACITY FOR PG
		@RequestMapping(value = "admin/getCourse_cap_intake_pg", method = RequestMethod.POST)
		public @ResponseBody List<CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY_PG> getCourse_cap_intake_pg(HttpSession session) {
			String userid = session.getAttribute("userId_for_jnlp").toString();
	//		String p_hid_constructed_area = CIDao.getp_idfromuser_id(userid).get(0).get(0);
	
			UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
			String institude = String.valueOf(ea.getInstitute_id());
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL
					.createQuery("from CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY_PG where inst_id=:inst_id");
			
			//System.err.println("q============1 APR=="+q);
			
			q.setParameter("inst_id", Integer.parseInt(institude));
			
			System.err.println("q============1 APR=="+q);
			@SuppressWarnings("unchecked")
			List<CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY_PG> clist = (List<CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY_PG>) q.list();
			System.err.println("q============1 APR=="+clist);
			tx.commit();
			sessionHQL.close();
			return clist;
		}
	
		
		
		//FETCH COURSE INTAKE CAPACITY FOR UG
		@RequestMapping(value = "admin/getCourse_cap_intake_ug", method = RequestMethod.POST)
		public @ResponseBody List<CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY_UG> getCourse_cap_intake_ug(HttpSession session) {
			String userid = session.getAttribute("userId_for_jnlp").toString();
	//		String p_hid_constructed_area = CIDao.getp_idfromuser_id(userid).get(0).get(0);
			
			
			
			UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
			String institude = String.valueOf(ea.getInstitute_id());
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL
					.createQuery("from CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY_UG where inst_id=:inst_id");
			
			//System.err.println("q============1 APR=="+q);
			
			q.setParameter("inst_id", Integer.parseInt(institude));
			
			System.err.println("q============1 APR=="+q);
			@SuppressWarnings("unchecked")
			List<CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY_UG> clist = (List<CLG_REG_INST_INFO_COURSE_INTAKE_CAPACITY_UG>) q.list();
			System.err.println("q============1 APR=="+clist);
			tx.commit();
			sessionHQL.close();
			return clist;
		}
	
		
		


	
		//FINAL SUBMIT=====================================================
	
	@PostMapping(value = "/final_submit_action")
	public @ResponseBody String final_submit_action(String hid_intake,HttpSession session1) {

		String msg="";

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String userid = session1.getAttribute("userId_for_jnlp").toString();
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institude = String.valueOf(ea.getInstitute_id());
		System.err.println("doc_attach_id===================="+hid_intake);
		try {
			String hqlUpdate = "update from CLG_REG_INST_INFO_INSTITUTION_BASIC_DETAILS set status='1' where inst_id=:inst_id";
			int app = session.createQuery(hqlUpdate).setInteger("inst_id",Integer.parseInt(institude)).executeUpdate();
			tx.commit();
			session.close();
			if (app > 0) {
				msg = "Form Submit Successfully.";
			} else {
				msg = "Data not Successfully.";
			}
		} catch (Exception e) {
			msg = "CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.";
		}finally {
			
		}
		return msg;
	}
	
	
	
	
	
	
	 @RequestMapping(value = "/getinfofromteacher_ctrl", method = RequestMethod.POST)
		public @ResponseBody   ArrayList<ArrayList<String>> getinfofromteacher_ctrl(String teachercode) {
		 
		 ArrayList<ArrayList<String>> list = ibdao.getinfofromteacher_code(teachercode);
		 
		 
		
			return list;
		}
		
	 
	 
	 

		
	 @PostMapping(value = "/approve_Teaching_Faculty_Details")
		public @ResponseBody String approve_Teaching_Faculty_Details(String institute_hid,HttpSession session1) {

			String msg="";

			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			System.err.println("hid_teaching_faculty===================="+institute_hid);
			try {
				String hqlUpdate = "UPDATE CLG_REG_INST_INFO_INSTITUTION_BASIC_DETAILS\n"
						+ "	SET  status=3	WHERE inst_id=:institute_id ";
				int app = session.createQuery(hqlUpdate).setInteger("institute_id",Integer.parseInt(institute_hid)).executeUpdate();
				tx.commit();
				if (app > 0) {
					msg = "Data Approve Successfully.";
				} else {
					msg = "Data not Approve.";
				}
			} catch (Exception e) {
				e.printStackTrace();
				//msg = "CAN NOT APPROVE THIS DATA BECAUSE IT IS ALREADY IN USED.";
			}finally {
				session.close();

			}
			return msg;
		}
	 
	 @PostMapping(value = "/Enable_Teaching_Faculty_Details")
		public @ResponseBody String Enable_Teaching_Faculty_Details(String institute_hid,HttpSession session1) {

			String msg="";

			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			System.err.println("hid_teaching_faculty===================="+institute_hid);
			try {
				String hqlUpdate = "UPDATE CLG_REG_INST_INFO_INSTITUTION_BASIC_DETAILS\n"
						+ "	SET  status=0	WHERE inst_id=:institute_id ";
				int app = session.createQuery(hqlUpdate).setInteger("institute_id",Integer.parseInt(institute_hid)).executeUpdate();
				tx.commit();
				if (app > 0) {
					msg = "Data Enable To Edit Successfully.";
				} else {
					msg = "Data not Enable To Edit.";
				}
			} catch (Exception e) {
				e.printStackTrace();
				//msg = "CAN NOT APPROVE THIS DATA BECAUSE IT IS ALREADY IN USED.";
			}finally {
				session.close();

			}
			return msg;
		}
	 @PostMapping(value = "/Reject_Teaching_Faculty_Details")
		public @ResponseBody String Reject_Teaching_Faculty_Details(String institute_hid,HttpSession session1) {

			String msg="";

			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			System.err.println("hid_teaching_faculty===================="+institute_hid);
			try {
				String hqlUpdate = "UPDATE CLG_REG_INST_INFO_INSTITUTION_BASIC_DETAILS\n"
						+ "	SET  status=2	WHERE inst_id=:institute_id ";
				int app = session.createQuery(hqlUpdate).setInteger("institute_id",Integer.parseInt(institute_hid)).executeUpdate();
				tx.commit();
				if (app > 0) {
					msg = "Data Reject Successfully.";
				} else {
					msg = "Data not Reject.";
				}
			} catch (Exception e) {
				e.printStackTrace();
				//msg = "CAN NOT APPROVE THIS DATA BECAUSE IT IS ALREADY IN USED.";
			}finally {
				session.close();

			}
			return msg;
		}
	
	
		
		//SAVE UPLOAD DOCUMENT DETAILS
		@PostMapping(value = "/Document_Details_Save_Draft_Action")
		public @ResponseBody String Document_Details_Save_Draft_Action( 
				HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
				@RequestParam(value = "full_time_teacher_affidavit", required = false) MultipartFile full_time_teacher_affidavit_doc,
				@RequestParam(value = "guest_teacher_affidavit", required = false) MultipartFile guest_teacher_affidavit_doc,
				@RequestParam(value = "miscellaneous_doc", required = false) MultipartFile miscellaneous_doc_doc,MultipartHttpServletRequest mul,
				RedirectAttributes ra) throws IOException, ParseException {

			if(request.getHeader("Referer") == null ) { 
				 session.invalidate();
				 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				 return  "redirect:/login";
			 }
			String role = session.getAttribute("role").toString();
			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//				if(val == false) {
//					return  "AccessTiles";
//			}
			CLG_REG_COLLEGE_STAFF_LIST_UPLOAD_DOC pers_p =new CLG_REG_COLLEGE_STAFF_LIST_UPLOAD_DOC();
			String userid = session.getAttribute("userId_for_jnlp").toString();
			UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
			String institute_id = String.valueOf(ea.getInstitute_id());
			
	//		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		//	String s_id = session.getAttribute("super_id").toString();
			
			String hid_document_detail = request.getParameter("hid_document_detail");
			String full_time_teacher_affidavit = "full_time_teacher_affidavit";
			String guest_teacher_affidavit = "guest_teacher_affidavit";
			String miscellaneous_doc = "miscellaneous_doc";
			
			
			if (!full_time_teacher_affidavit_doc.isEmpty()) {
				full_time_teacher_affidavit = upload_imagemethod(request,full_time_teacher_affidavit_doc,session, full_time_teacher_affidavit);
			}
			else {
				full_time_teacher_affidavit = request.getParameter("hid_full_time_teacher_affidavit");
			}
			
			if (!guest_teacher_affidavit_doc.isEmpty()) {
				guest_teacher_affidavit = upload_imagemethod(request,guest_teacher_affidavit_doc,session, guest_teacher_affidavit);
			}
			else {
				guest_teacher_affidavit = request.getParameter("hid_guest_teacher_affidavit");
			}
			
			if (!miscellaneous_doc_doc.isEmpty()) {
				miscellaneous_doc = upload_imagemethod(request,miscellaneous_doc_doc,session, miscellaneous_doc);
			}
			else {
				miscellaneous_doc = request.getParameter("hid_miscellaneous_doc");
			}
			
			
			DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			Date date = new Date();
			int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
			String username = principal.getName();
			Session sessionHQL = this.sessionFactory.openSession();
			
			try {
				
				Transaction tx = sessionHQL.beginTransaction();
				CLG_REG_COLLEGE_STAFF_LIST_UPLOAD_DOC upload_doc_detail =new CLG_REG_COLLEGE_STAFF_LIST_UPLOAD_DOC();
					
					upload_doc_detail.setFull_time_teacher_affidavit(full_time_teacher_affidavit);
					upload_doc_detail.setGuest_teacher_affidavit(guest_teacher_affidavit);
					upload_doc_detail.setMiscellaneous_doc(miscellaneous_doc);
					upload_doc_detail.setInstitute_id(Integer.parseInt(institute_id));
				//	upload_doc_detail.setS_id(Integer.parseInt(s_id));
					upload_doc_detail.setCreated_by(Integer.parseInt(userid));
					upload_doc_detail.setCreated_date(date);
					
					if (Integer.parseInt(hid_document_detail) == 0) {
						int hid_document_detail1= (Integer) sessionHQL.save(upload_doc_detail);
						sessionHQL.flush();
						sessionHQL.clear();
						tx.commit();
						return String.valueOf(hid_document_detail1) ;
					}
					else {
						CLG_REG_COLLEGE_STAFF_LIST_UPLOAD_DOC upload_doc_detail_u = (CLG_REG_COLLEGE_STAFF_LIST_UPLOAD_DOC) sessionHQL
								.get(CLG_REG_COLLEGE_STAFF_LIST_UPLOAD_DOC.class, Integer.parseInt(hid_document_detail));
						
						upload_doc_detail_u.setFull_time_teacher_affidavit(full_time_teacher_affidavit);
						upload_doc_detail_u.setGuest_teacher_affidavit(guest_teacher_affidavit);
						upload_doc_detail_u.setMiscellaneous_doc(miscellaneous_doc);
						upload_doc_detail_u.setInstitute_id(Integer.parseInt(institute_id));
					//	upload_doc_detail_u.setS_id(Integer.parseInt(s_id));
						upload_doc_detail_u.setModified_by(Integer.parseInt(userid));
						upload_doc_detail_u.setModified_date(date);
						sessionHQL.update(upload_doc_detail_u);
						sessionHQL.flush();
						sessionHQL.clear();
						tx.commit();
						
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
			return  "Data Saved Successfully";
		}
		
		
		

}
