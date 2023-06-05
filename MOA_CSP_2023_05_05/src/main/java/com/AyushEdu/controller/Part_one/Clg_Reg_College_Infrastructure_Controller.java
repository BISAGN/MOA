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
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_INFRA_ADDITIONAL_INFORMATION;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_INFRA_CENTRAL_LIBRARY;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_INFRA_CENTRAL_LIBRARY_CHILD;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_INFRA_COLLEGE_COUNCIL;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_INFRA_COLLEGE_COUNCIL_CAMERA;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_INFRA_CONSTRUCTED_AREA;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_INFRA_CONSTRUCTED_AREA_DEPT;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_INFRA_HERBAL_GARDEN;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_INFRA_HOSTEL_DETAILS;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_INFRA_MESS_DETAILS;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_INFRA_PROGRESS_OF_INSTITUTION;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Infrastructure_DAO;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Clg_Reg_College_Infrastructure_Controller {

	@Autowired
	ValidationController validation;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	CommonController common = new CommonController();

	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private Clg_Reg_College_Infrastructure_DAO CIDao;
	
	@Autowired
	Commondao commondao;
	
	@Autowired
	Institution_Basic_Details_Dao ibdao;

	
	@RequestMapping(value = "admin/college_infrastructure", method = RequestMethod.GET)
	public ModelAndView college_infrastructure(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String userid = session.getAttribute("userId_for_jnlp").toString();
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institude = String.valueOf(ea.getInstitute_id());
		String role = session.getAttribute("role").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
//		String s_id = session.getAttribute("super_id").toString();
//		CLG_REG_INST_INFO_INSTITUTION_BASIC_DETAILS pers_p =new CLG_REG_INST_INFO_INSTITUTION_BASIC_DETAILS();
//		int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
//		int p_id=Integer.parseInt(request.getParameter("inst_basic_hidden"));
		
//		System.err.println("iddddddddddddddddddddd"+p_id);
		if(role=="Institute_NCH") {
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int parent_id = (int) sessionHQL
				.createQuery("select id from CLG_REG_INFRA_COLLEGE_COUNCIL where institute_id=:inst_id")
				.setParameter("inst_id", Integer.parseInt(institude))
				.uniqueResult();
		Mmap.put("parent_id", parent_id);
		}
		Mmap.put("institude", institude);
		
		if(ibdao.getpid_from_userid(userid).size()!=0) {
			if(ibdao.getpid_from_userid(userid).get(0).get(0)==null ||ibdao.getpid_from_userid(userid).get(0).get(0).equals("")) {
				Mmap.put("basic_info_id", 0);

			}else {
				
				Mmap.put("basic_info_id", ibdao.getpid_from_userid(userid).get(0).get(0));
				request.getSession().setAttribute("super_id", ibdao.getpid_from_userid(userid).get(0).get(0));
			}
		}else {
			Mmap.put("basic_info_id", 0);
		}
		
//		if(ibdao.getpid_from_userid(userid).size()!=0) {
//			if(ibdao.getpid_from_userid(userid).get(0).get(0)==null ||ibdao.getpid_from_userid(userid).get(0).get(0).equals("")) {
//				Mmap.put("msg", "Please Save Basic details First");
//				return new ModelAndView("redirect:basics_information");
//
//			}else {
//				
//				request.getSession().setAttribute("super_id", ibdao.getpid_from_userid(userid).get(0).get(0));
//			}
//		}else {
//			
//			Mmap.put("msg", "Please Save Basic details First");
//			return new ModelAndView("redirect:basics_information");
//		}
		
		Mmap.put("getCollege_Council_Details", CIDao.getCollege_Council_Details(Integer.parseInt(institute_id)));
		Mmap.put("GetProgress_of_Institution_Details", CIDao.GetProgress_of_Institution_Details(institute_id));
		Mmap.put("getConstructed_Area_Details", CIDao.getConstructed_Area_Details(institute_id));
		Mmap.put("getCentral_Library_Details", CIDao.getCentral_Library_Details(institute_id));
		Mmap.put("getHostelDetails", CIDao.getHostelDetails(institute_id));
		Mmap.put("getMessDetails", CIDao.getMessDetails(institute_id));
		Mmap.put("getHerbal_Garden_Details", CIDao.getHerbal_Garden_Details(institute_id));
		Mmap.put("getAdd_Info_Details", CIDao.getAdd_Info_Details(institute_id));
		Mmap.put("institude", institude);
		Mmap.put("getDepartmentList", CIDao.getAllDepartment_name());
		Mmap.put("getAllDepartment", CIDao.getAllDepartment());
		return new ModelAndView("college_infrastructure");
	}

	//SAVE COLLEGE COUNCIL DETAILS
	@PostMapping(value = "/College_Council_Details_Save_Draft_Action")
	public @ResponseBody String College_Council_Details_Save_Draft_Action( 
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			@RequestParam(value = "council_document", required = false) MultipartFile council_doc,MultipartHttpServletRequest mul,
			RedirectAttributes ra) throws ParseException, IOException {

		if(request.getHeader("Referer") == null ) { 
			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return  "redirect:/login";
		 }
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//			if(val == false) {
//				return  "AccessTiles";
//		}
		
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String council_document = "council_document";
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String s_id = session.getAttribute("super_id").toString();
		
		CLG_REG_INFRA_COLLEGE_COUNCIL pers_p =new CLG_REG_INFRA_COLLEGE_COUNCIL();
		
		String council_check = request.getParameter("council_check");
		String college_website = request.getParameter("college_website");
		String website_update_date = request.getParameter("website_update_date");
		String cctv_status = request.getParameter("cctv_status");
		String login_url = request.getParameter("login_url");
		String username_college = request.getParameter("username_ci");
		String password = request.getParameter("password_ci");
		String biometric_status = request.getParameter("biometric_status");
		String college_working_hours = request.getParameter("college_working_hours");
		String hid_college_council = request.getParameter("hid_college_council");
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		
		if (!council_doc.isEmpty()) {
			council_document = upload_imagemethod(request,council_doc,session, council_document);
		}
		else {
			council_document = request.getParameter("hid_council_document");
		}
		
		if (council_document == null || council_document.trim().equals("")) {
			ra.addAttribute("msg", "Please Upload Document");
			return "Please Upload Document";
		}
		if (website_update_date == null || website_update_date.trim().equals("") || website_update_date.equals("DD/MM/YYYY")) {
			ra.addAttribute("msg", "Please Enter Website Update Date");
			return "Please Enter Website Update Date";
		}
		if (validation.isOnlyDateFormat(website_update_date) == false) {
			ra.addAttribute("msg", "Website Update Date " + validation.isOnlyDateFormatMSG);
			return "" +validation.isOnlyDateFormatMSG;
		}
		if (login_url == null || login_url.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Login Url");
			return "Please Enter Login Url";
		}
		if (validation.maxlengthcheck_50(login_url) == false) {
			ra.addAttribute("msg", "Login Url" + validation.MaxlengthcheckMSG_50);
			return "Login Url " +validation.MaxlengthcheckMSG_50;
		}
		if (username_college == null || username_college.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Username");
			return "Please Enter Username";
		}
		if (validation.maxlengthcheck_50(username_college) == false) {
			ra.addAttribute("msg", "College Username" + validation.MaxlengthcheckMSG_50);
			return "College Username " +validation.MaxlengthcheckMSG_50;
		}
		if (password == null || password.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Password");
			return "Please Enter Password";
		}
		if (validation.maxlengthcheck_50(password) == false) {
			ra.addAttribute("msg", "Password" + validation.MaxlengthcheckMSG_50);
			return "Password " +validation.MaxlengthcheckMSG_50;
		}
		if (college_working_hours == null || college_working_hours.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter College Working Hours");
			return "Please Enter College Working Hours";
		}
		if (validation.maxlength2(college_working_hours) == false) {
			ra.addAttribute("msg", "College Working Hours" + validation.MaxlengthMSG2);
			return "College Working Hours " +validation.MaxlengthMSG2;
		}
		if (validation.isOnlyNumber(college_working_hours) == false) {
			ra.addAttribute("msg","College Working Hours" +validation.isOnlyNumberMSG);
			return "College Working Hours " +validation.isOnlyNumberMSG;
		}
		
		int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			CLG_REG_INFRA_COLLEGE_COUNCIL college_council_detail =new CLG_REG_INFRA_COLLEGE_COUNCIL();
			
			college_council_detail.setCouncil_check(Integer.parseInt(council_check));
			if(!council_document.equals("")) {
			college_council_detail.setCouncil_document(council_document);
			}
			college_council_detail.setCollege_website(Integer.parseInt(college_website));
			college_council_detail.setWebsite_update_date(datePickerFormat.parse(website_update_date));
			college_council_detail.setCctv_status(Integer.parseInt(cctv_status));
			college_council_detail.setLogin_url(login_url);
			college_council_detail.setUsername(username_college);
			college_council_detail.setPassword(password);
			college_council_detail.setBiometric_status(Integer.parseInt(biometric_status));
			college_council_detail.setCollege_working_hours(college_working_hours);
			college_council_detail.setInstitute_id(Integer.parseInt(institute_id));
			college_council_detail.setS_id(Integer.parseInt(s_id));
			college_council_detail.setCreated_by(Integer.parseInt(userid));
			college_council_detail.setCreated_date(date);
			
				if (Integer.parseInt(hid_college_council) == 0) {
					int hid_college_council1= (Integer) sessionHQL.save(college_council_detail);
					System.err.println("hid_college_council1----------------"+hid_college_council1);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					return String.valueOf(hid_college_council1) ;
			}
				else {

					CLG_REG_INFRA_COLLEGE_COUNCIL college_council_detail_u = (CLG_REG_INFRA_COLLEGE_COUNCIL) sessionHQL
							.get(CLG_REG_INFRA_COLLEGE_COUNCIL.class, Integer.parseInt(hid_college_council));
					
					college_council_detail_u.setCouncil_check(Integer.parseInt(council_check));
					if(!council_document.equals("")) {
					college_council_detail_u.setCouncil_document(council_document);
					}
					college_council_detail_u.setCollege_website(Integer.parseInt(college_website));
					college_council_detail_u.setWebsite_update_date(datePickerFormat.parse(website_update_date));
					college_council_detail_u.setCctv_status(Integer.parseInt(cctv_status));
					college_council_detail_u.setLogin_url(login_url);
					college_council_detail_u.setUsername(username_college);
					college_council_detail_u.setPassword(password);
					college_council_detail_u.setBiometric_status(Integer.parseInt(biometric_status));
					college_council_detail_u.setCollege_working_hours(college_working_hours);
					college_council_detail_u.setInstitute_id(Integer.parseInt(institute_id));
					college_council_detail_u.setS_id(Integer.parseInt(s_id));
					college_council_detail_u.setModified_by(Integer.parseInt(userid));
					college_council_detail_u.setModified_date(date);
					college_council_detail_u.setId(Integer.parseInt(hid_college_council));
					sessionHQL.update(college_council_detail_u);
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
	
	//SAVE PROGRESS OF INSTITUTION DETAILS
	@PostMapping(value = "/Progress_Institution_Details_Save_Draft_Action")
	public @ResponseBody String Progress_Institution_Details_Save_Draft_Action( 
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws ParseException {

		if(request.getHeader("Referer") == null ) { 
			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return  "redirect:/login";
		 }
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//			if(val == false) {
//				return  "AccessTiles";
//		}
		
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String s_id = session.getAttribute("super_id").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		
		CLG_REG_INFRA_PROGRESS_OF_INSTITUTION pers_p =new CLG_REG_INFRA_PROGRESS_OF_INSTITUTION();
		
		String cons_clg_hospital = request.getParameter("cons_clg_hospital");
		String app_teaching_staff = request.getParameter("app_teaching_staff");
		String app_non_teaching_staff = request.getParameter("app_non_teaching_staff");
		String app_paramedical = request.getParameter("app_paramedical");
		String expansion_various_dept = request.getParameter("expansion_various_dept");
		String expansion_herbal_ganden = request.getParameter("expansion_herbal_ganden");
		String hospital_opd = request.getParameter("hospital_opd");
		String hospital_ipd = request.getParameter("hospital_ipd");
		String seminars = request.getParameter("seminars");
		String pulication_by_clg = request.getParameter("pulication_by_clg");
		String research_activities = request.getParameter("research_activities");
		String award_details = request.getParameter("award_details");
//		String p_hid_progress_institution = request.getParameter("p_hid_progress_institution");
		String hid_progress_institution = request.getParameter("hid_progress_institution");
		String p_hid_progress_institution = CIDao.getp_idfromuser_id(userid).get(0).get(0);
		
		
//		if (cons_clg_hospital == null || cons_clg_hospital.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter Construction of College");
//			return "Please Enter Construction of College";
//		}
		if (validation.isOnlyAlphabetNumber(cons_clg_hospital) == false) {
			ra.addAttribute("msg","Construction of college" +validation.isOnlyAlphabetNumberMSG);
			return "Construction of college" +validation.isOnlyAlphabetNumberMSG;
		}
		if (validation.maxlengthcheck(cons_clg_hospital) == false) {
			ra.addAttribute("msg", "Construction of college" + validation.MaxlengthcheckMSG);
			return "Construction of college " +validation.MaxlengthcheckMSG;
		}
//		if (app_teaching_staff == null || app_teaching_staff.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter Appointment of Teaching Staff");
//			return "Please Enter Appointment of Teaching Staff";
//		}
		if (validation.isOnlyAlphabetNumber(app_teaching_staff) == false) {
			ra.addAttribute("msg","Appointment of Teaching staff" +validation.isOnlyAlphabetNumberMSG);
			return "Appointment of Teaching staff" +validation.isOnlyAlphabetNumberMSG;
		}
		if (validation.maxlengthcheck(app_teaching_staff) == false) {
			ra.addAttribute("msg", "Appointment of Teaching staff" + validation.MaxlengthcheckMSG);
			return "Appointment of Teaching staff " +validation.MaxlengthcheckMSG;
		}
//		if (app_non_teaching_staff == null || app_non_teaching_staff.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter Appointment of Non-Teaching staff");
//			return "Please Enter Appointment of Non-Teaching staff";
//		}
		if (validation.isOnlyAlphabetNumber(app_non_teaching_staff) == false) {
			ra.addAttribute("msg","Appointment of Non-Teaching staff" +validation.isOnlyAlphabetNumberMSG);
			return "Appointment of Non-Teaching staff" +validation.isOnlyAlphabetNumberMSG;
		}
		if (validation.maxlengthcheck(app_non_teaching_staff) == false) {
			ra.addAttribute("msg", "Appointment of Non-Teaching staff" + validation.MaxlengthcheckMSG);
			return "Appointment of Non-Teaching staff " +validation.MaxlengthcheckMSG;
		}
//		if (app_paramedical == null || app_paramedical.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter Appointment of Paramedical");
//			return "Please Enter Appointment of Paramedical";
//		}
		if (validation.isOnlyAlphabetNumber(app_paramedical) == false) {
			ra.addAttribute("msg","Appointment of Paramedical" +validation.isOnlyAlphabetNumberMSG);
			return "Appointment of Paramedical" +validation.isOnlyAlphabetNumberMSG;
		}
		if (validation.maxlengthcheck(app_paramedical) == false) {
			ra.addAttribute("msg", "Appointment of Paramedical" + validation.MaxlengthcheckMSG);
			return "Appointment of Paramedical " +validation.MaxlengthcheckMSG;
		}
//		if (expansion_various_dept == null || expansion_various_dept.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter Expansion of various departments of college");
//			return "Please Enter Expansion of various departments of college";
//		}
		if (validation.isOnlyAlphabetNumber(expansion_various_dept) == false) {
			ra.addAttribute("msg","Expansion of various departments of college" +validation.isOnlyAlphabetNumberMSG);
			return "Expansion of various departments of college" +validation.isOnlyAlphabetNumberMSG;
		}
		if (validation.maxlengthcheck(expansion_various_dept) == false) {
			ra.addAttribute("msg", "Expansion of various departments of college" + validation.MaxlengthcheckMSG);
			return "Expansion of various departments of college " +validation.MaxlengthcheckMSG;
		}
//		if (expansion_herbal_ganden == null || expansion_herbal_ganden.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter Expansion of Herbal Garden");
//			return "Please Enter Expansion of Herbal Garden";
//		}
		if (validation.isOnlyAlphabetNumber(expansion_herbal_ganden) == false) {
			ra.addAttribute("msg","Expansion of Herbal Garden" +validation.isOnlyAlphabetNumberMSG);
			return "Expansion of Herbal Garden" +validation.isOnlyAlphabetNumberMSG;
		}
		if (validation.maxlengthcheck(expansion_herbal_ganden) == false) {
			ra.addAttribute("msg", "Expansion of Herbal Garden" + validation.MaxlengthcheckMSG);
			return "Expansion of Herbal Garden " +validation.MaxlengthcheckMSG;
		}
//		if (hospital_opd == null || hospital_opd.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter Hospital OPD");
//			return "Please Enter Hospital OPD";
//		}
		if (validation.isOnlyAlphabetNumber(hospital_opd) == false) {
			ra.addAttribute("msg","Hospital OPD" +validation.isOnlyAlphabetNumberMSG);
			return "Hospital OPD" +validation.isOnlyAlphabetNumberMSG;
		}
		if (validation.maxlengthcheck(hospital_opd) == false) {
			ra.addAttribute("msg", "Hospital OPD" + validation.MaxlengthcheckMSG);
			return "Hospital OPD " +validation.MaxlengthcheckMSG;
		}
//		if (hospital_ipd == null || hospital_ipd.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter Hospital IPD");
//			return "Please Enter Hospital IPD";
//		}
		if (validation.isOnlyAlphabetNumber(hospital_ipd) == false) {
			ra.addAttribute("msg","Hospital IPD" +validation.isOnlyAlphabetNumberMSG);
			return "Hospital IPD" +validation.isOnlyAlphabetNumberMSG;
		}
		if (validation.maxlengthcheck(hospital_ipd) == false) {
			ra.addAttribute("msg", "Hospital IPD" + validation.MaxlengthcheckMSG);
			return "Hospital IPD " +validation.MaxlengthcheckMSG;
		}
//		if (seminars == null || seminars.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter Seminars");
//			return "Please Enter Seminars";
//		}
		if (validation.isOnlyAlphabetNumber(seminars) == false) {
			ra.addAttribute("msg","Seminars" +validation.isOnlyAlphabetNumberMSG);
			return "Seminars" +validation.isOnlyAlphabetNumberMSG;
		}
		if (validation.maxlengthcheck(seminars) == false) {
			ra.addAttribute("msg", "Seminars" + validation.MaxlengthcheckMSG);
			return "Seminars " +validation.MaxlengthcheckMSG;
		}
//		if (pulication_by_clg == null || pulication_by_clg.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter Publication");
//			return "Please Enter Publication";
//		}
		if (validation.isOnlyAlphabetNumber(pulication_by_clg) == false) {
			ra.addAttribute("msg","Publication" +validation.isOnlyAlphabetNumberMSG);
			return "Publication" +validation.isOnlyAlphabetNumberMSG;
		}
		if (validation.maxlengthcheck(pulication_by_clg) == false) {
			ra.addAttribute("msg", "Publication" + validation.MaxlengthcheckMSG);
			return "Publication " +validation.MaxlengthcheckMSG;
		}
//		if (research_activities == null || research_activities.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter Research activities");
//			return "Please Enter Research activities";
//		}
		if (validation.isOnlyAlphabetNumber(research_activities) == false) {
			ra.addAttribute("msg","Research activities" +validation.isOnlyAlphabetNumberMSG);
			return "Research activities" +validation.isOnlyAlphabetNumberMSG;
		}
		if (validation.maxlengthcheck(research_activities) == false) {
			ra.addAttribute("msg", "Research activities" + validation.MaxlengthcheckMSG);
			return "Research activities " +validation.MaxlengthcheckMSG;
		}
//		if (award_details == null || award_details.trim().equals("")) {
//			ra.addAttribute("msg", "Please Enter Awards");
//			return "Please Enter Awards";
//		}
		if (validation.isOnlyAlphabetNumber(award_details) == false) {
			ra.addAttribute("msg","Awards" +validation.isOnlyAlphabetNumberMSG);
			return "Awards" +validation.isOnlyAlphabetNumberMSG;
		}
		if (validation.maxlengthcheck(award_details) == false) {
			ra.addAttribute("msg", "Awards" + validation.MaxlengthcheckMSG);
			return "Awards " +validation.MaxlengthcheckMSG;
		}
		
		
		int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		try {
			CLG_REG_INFRA_PROGRESS_OF_INSTITUTION progress_institution_detail =new CLG_REG_INFRA_PROGRESS_OF_INSTITUTION();
			
			progress_institution_detail.setCons_clg_hospital(cons_clg_hospital);
			progress_institution_detail.setApp_teaching_staff(app_teaching_staff);
			progress_institution_detail.setApp_non_teaching_staff(app_non_teaching_staff);
			progress_institution_detail.setApp_paramedical(app_paramedical);
			progress_institution_detail.setExpansion_various_dept(expansion_various_dept);
			progress_institution_detail.setExpansion_herbal_ganden(expansion_herbal_ganden);
			progress_institution_detail.setHospital_opd(hospital_opd);
			progress_institution_detail.setHospital_ipd(hospital_ipd);
			progress_institution_detail.setSeminars(seminars);
			progress_institution_detail.setPulication_by_clg(pulication_by_clg);
			progress_institution_detail.setResearch_activities(research_activities);
			progress_institution_detail.setAward_details(award_details);
			progress_institution_detail.setInstitute_id(Integer.parseInt(institute_id));
			progress_institution_detail.setS_id(Integer.parseInt(s_id));
			progress_institution_detail.setCreated_by(Integer.parseInt(userid));
			progress_institution_detail.setCreated_date(date);
			
				if (Integer.parseInt(hid_progress_institution) == 0) {
					progress_institution_detail.setP_id(Integer.parseInt(p_hid_progress_institution));
					int hid_progress_institution1= (Integer) sessionHQL.save(progress_institution_detail);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					return String.valueOf(hid_progress_institution1) ;
			}
				else {

					CLG_REG_INFRA_PROGRESS_OF_INSTITUTION progress_institution_detail_u = (CLG_REG_INFRA_PROGRESS_OF_INSTITUTION) sessionHQL
							.get(CLG_REG_INFRA_PROGRESS_OF_INSTITUTION.class, Integer.parseInt(hid_progress_institution));
					
					progress_institution_detail_u.setCons_clg_hospital(cons_clg_hospital);
					progress_institution_detail_u.setApp_teaching_staff(app_teaching_staff);
					progress_institution_detail_u.setApp_non_teaching_staff(app_non_teaching_staff);
					progress_institution_detail_u.setApp_paramedical(app_paramedical);
					progress_institution_detail_u.setExpansion_various_dept(expansion_various_dept);
					progress_institution_detail_u.setExpansion_herbal_ganden(expansion_herbal_ganden);
					progress_institution_detail_u.setHospital_opd(hospital_opd);
					progress_institution_detail_u.setHospital_ipd(hospital_ipd);
					progress_institution_detail_u.setSeminars(seminars);
					progress_institution_detail_u.setPulication_by_clg(pulication_by_clg);
					progress_institution_detail_u.setResearch_activities(research_activities);
					progress_institution_detail_u.setAward_details(award_details);
					progress_institution_detail_u.setInstitute_id(Integer.parseInt(institute_id));
					progress_institution_detail_u.setS_id(Integer.parseInt(s_id));
					progress_institution_detail_u.setModified_by(Integer.parseInt(userid));
					progress_institution_detail_u.setModified_date(date);
					sessionHQL.update(progress_institution_detail_u);
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
	
	//SAVE CONSTRUCTED AREA DETAILS
	@PostMapping(value = "/Constructed_Area_Details_Save_Draft_Action")
	public @ResponseBody String Constructed_Area_Details_Save_Draft_Action( 
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws ParseException {

		if(request.getHeader("Referer") == null ) { 
			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return  "redirect:/login";
		 }
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//			if(val == false) {
//				return  "AccessTiles";
//		}
		
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String s_id = session.getAttribute("super_id").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		
		ArrayList<ArrayList<String>> department_list = CIDao.getAllDepartment_name();
		System.err.println("ABC-----------------"+department_list.size());
		
		CLG_REG_INFRA_CONSTRUCTED_AREA pers_p =new CLG_REG_INFRA_CONSTRUCTED_AREA();
		
		
		String college_const = request.getParameter("college_const");
		String lecturer_hall = request.getParameter("lecturer_hall");
		String exam_hall = request.getParameter("exam_hall");
		String central_library = request.getParameter("central_library");
		String boys_common_room = request.getParameter("boys_common_room");
		String girl_common_room = request.getParameter("girl_common_room");
		String canteen = request.getParameter("canteen");
		String administrative_section = request.getParameter("administrative_section");
		String total_area_of_college = request.getParameter("total_area_of_college");
		String total_lecture_hall = request.getParameter("total_lecture_hall");
		String hid_constructed_area = request.getParameter("hid_constructed_area");
		String p_hid_constructed_area = CIDao.getp_idfromuser_id(userid).get(0).get(0);
		
		
		if (college_const == null || college_const.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Construction of college");
			return "Please Enter Construction of college";
		}
		if (validation.isOnlyNumerandDotMSG(college_const) == false) {
			ra.addAttribute("msg","Construction of college" +validation.isOnlyNumerandDotMSG);
			return "Construction of college " +validation.isOnlyNumerandDotMSG;
		}
		if (validation.maxlength10Digit(college_const) == false) {
			ra.addAttribute("msg", "Construction of college" + validation.MaxlengthMSG10Digit);
			return "Construction of college " +validation.MaxlengthMSG10Digit;
		}
		if (lecturer_hall == null || lecturer_hall.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Total Area of Lecturer Halls");
			return "Please Enter Total Area of Lecturer Halls";
		}
		if (validation.isOnlyNumerandDotMSG(lecturer_hall) == false) {
			ra.addAttribute("msg","Total Area of Lecturer Halls" +validation.isOnlyNumerandDotMSG);
			return "Total Area of Lecturer Halls " +validation.isOnlyNumerandDotMSG;
		}
		if (validation.maxlength10Digit(lecturer_hall) == false) {
			ra.addAttribute("msg", "Total Area of Lecturer Halls" + validation.MaxlengthMSG10Digit);
			return "Total Area of Lecturer Halls " +validation.MaxlengthMSG10Digit;
		}
		if (exam_hall == null || exam_hall.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Exam hall Area");
			return "Please Enter Exam hall Area";
		}
		if (validation.isOnlyNumerandDotMSG(exam_hall) == false) {
			ra.addAttribute("msg","Exam hall Area" +validation.isOnlyNumerandDotMSG);
			return "Exam hall Area " +validation.isOnlyNumerandDotMSG;
		}
		if (validation.maxlength10Digit(exam_hall) == false) {
			ra.addAttribute("msg", "Exam hall Area" + validation.MaxlengthMSG10Digit);
			return "Exam hall Area " +validation.MaxlengthMSG10Digit;
		}
		if (central_library == null || central_library.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Central Library Area");
			return "Please Enter Central Library Area";
		}
		if (validation.isOnlyNumerandDotMSG(central_library) == false) {
			ra.addAttribute("msg","Central Library Area" +validation.isOnlyNumerandDotMSG);
			return "Central Library Area " +validation.isOnlyNumerandDotMSG;
		}
		if (validation.maxlength10Digit(central_library) == false) {
			ra.addAttribute("msg", "Central Library Area" + validation.MaxlengthMSG10Digit);
			return "Central Library Area " +validation.MaxlengthMSG10Digit;
		}
		if (boys_common_room == null || boys_common_room.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Area of Boys Common Room");
			return "Please Enter Area of Boys Common Room";
		}
		if (validation.isOnlyNumerandDotMSG(boys_common_room) == false) {
			ra.addAttribute("msg","Area of Boys Common Room" +validation.isOnlyNumerandDotMSG);
			return "Area of Boys Common Room Name " +validation.isOnlyNumerandDotMSG;
		}
		if (validation.maxlength10Digit(boys_common_room) == false) {
			ra.addAttribute("msg", "Area of Boys Common Room" + validation.MaxlengthMSG10Digit);
			return "Area of Boys Common Room " +validation.MaxlengthMSG10Digit;
		}
		if (girl_common_room == null || girl_common_room.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Area of Girls Common Room");
			return "Please Enter Area of Girls Common Room";
		}
		if (validation.isOnlyNumerandDotMSG(girl_common_room) == false) {
			ra.addAttribute("msg","Area of Girls Common Room" +validation.isOnlyNumerandDotMSG);
			return "Area of Girls Common Room " +validation.isOnlyNumerandDotMSG;
		}
		if (validation.maxlength10Digit(girl_common_room) == false) {
			ra.addAttribute("msg", "Area of Girls Common Room" + validation.MaxlengthMSG10Digit);
			return "Area of Girls Common Room " +validation.MaxlengthMSG10Digit;
		}
		if (canteen == null || canteen.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Canteen Area");
			return "Please Enter Canteen Area";
		}
		if (validation.isOnlyNumerandDotMSG(canteen) == false) {
			ra.addAttribute("msg","Canteen Area" +validation.isOnlyNumerandDotMSG);
			return "Canteen Area " +validation.isOnlyNumerandDotMSG;
		}
		if (validation.maxlength10Digit(canteen) == false) {
			ra.addAttribute("msg", "Canteen Area" + validation.MaxlengthMSG10Digit);
			return "Canteen Area " +validation.MaxlengthMSG10Digit;
		}
		if (administrative_section == null || administrative_section.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Administrative section Area");
			return "Please Enter Administrative section Area";
		}
		if (validation.isOnlyNumerandDotMSG(administrative_section) == false) {
			ra.addAttribute("msg","Administrative section Area" +validation.isOnlyNumerandDotMSG);
			return "Administrative section Area " +validation.isOnlyNumerandDotMSG;
		}
		if (validation.maxlength10Digit(administrative_section) == false) {
			ra.addAttribute("msg", "Administrative section Area" + validation.MaxlengthMSG10Digit);
			return "Administrative section Area " +validation.MaxlengthMSG10Digit;
		}
		if (total_area_of_college == null || total_area_of_college.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Total Constructed Area of College");
			return "Please Enter Total Constructed Area of College";
		}
		if (validation.isOnlyNumerandDotMSG(total_area_of_college) == false) {
			ra.addAttribute("msg","Total Constructed Area of College" +validation.isOnlyNumerandDotMSG);
			return "Total Constructed Area of College " +validation.isOnlyNumerandDotMSG;
		}
		if (validation.maxlength10Digit(total_area_of_college) == false) {
			ra.addAttribute("msg", "Total Constructed Area of College" + validation.MaxlengthMSG10Digit);
			return "Total Constructed Area of College " +validation.MaxlengthMSG10Digit;
		}
		if (total_lecture_hall == null || total_lecture_hall.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Total Lecture Halls");
			return "Please Enter Total Lecture Halls";
		}
		if (validation.isOnlyNumber(total_lecture_hall) == false) {
			ra.addAttribute("msg","Total Lecture Halls" +validation.isOnlyNumberMSG);
			return "Total Lecture Halls " +validation.isOnlyNumberMSG;
		}
		if (validation.maxlength10Digit(total_lecture_hall) == false) {
			ra.addAttribute("msg", "Total Lecture Halls" + validation.MaxlengthMSG10Digit);
			return "Total Lecture Halls " +validation.MaxlengthMSG10Digit;
		}
		
		int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		

		try {
			
			
			Transaction tx1 = sessionHQL.beginTransaction();
			CLG_REG_INFRA_CONSTRUCTED_AREA_DEPT constructed_area_dept_detail =new CLG_REG_INFRA_CONSTRUCTED_AREA_DEPT();
			
			for (int i = 0; i < department_list.size(); i++) {
				String department_id = request.getParameter("department_id_"+department_list.get(i).get(0));
				String department_name = request.getParameter("department_name_"+department_list.get(i).get(0));
				String area_of_department = request.getParameter("area_of_department_"+department_list.get(i).get(0));
				String hid_department_area = request.getParameter("hid_department_area_"+department_list.get(i).get(0));
				
				if (area_of_department == null || area_of_department.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Area of Department");
					return "Please Enter Area of Department";
				}
				if (validation.isOnlyNumerandDotMSG(area_of_department) == false) {
					ra.addAttribute("msg","Area of Department" +validation.isOnlyNumerandDotMSG);
					return "Area of "+department_list.get(i).get(1)+" " +validation.isOnlyNumerandDotMSG;
				}
				if (validation.maxlength10Digit(area_of_department) == false) {
					ra.addAttribute("msg", "Area of Department" + validation.MaxlengthMSG10Digit);
					return "Area of "+department_list.get(i).get(1)+" " +validation.MaxlengthMSG10Digit;
				}
				
				constructed_area_dept_detail.setDepartment_id(Integer.parseInt(department_id));
				constructed_area_dept_detail.setDepartment_name(department_name);
				constructed_area_dept_detail.setArea_of_department(area_of_department);
				constructed_area_dept_detail.setInstitute_id(Integer.parseInt(institute_id));
				constructed_area_dept_detail.setS_id(Integer.parseInt(s_id));
				constructed_area_dept_detail.setCreated_by(Integer.parseInt(userid));
				constructed_area_dept_detail.setCreated_date(date);
				
				
				if (Integer.parseInt(hid_department_area) == 0) {
					constructed_area_dept_detail.setP_id(Integer.parseInt(p_hid_constructed_area));
					int hid_department_area1= (Integer) sessionHQL.save(constructed_area_dept_detail);
					sessionHQL.flush();
					sessionHQL.clear();
				}
				else {
					CLG_REG_INFRA_CONSTRUCTED_AREA_DEPT constructed_area_dept_detail_u = (CLG_REG_INFRA_CONSTRUCTED_AREA_DEPT) sessionHQL
							.get(CLG_REG_INFRA_CONSTRUCTED_AREA_DEPT.class, Integer.parseInt(hid_department_area));
					
					constructed_area_dept_detail_u.setDepartment_id(Integer.parseInt(department_id));
					constructed_area_dept_detail_u.setDepartment_name(department_name);
					constructed_area_dept_detail_u.setArea_of_department(area_of_department);
					constructed_area_dept_detail_u.setInstitute_id(Integer.parseInt(institute_id));
					constructed_area_dept_detail_u.setS_id(Integer.parseInt(s_id));
					constructed_area_dept_detail_u.setModified_by(Integer.parseInt(userid));
					constructed_area_dept_detail_u.setModified_date(date);
					sessionHQL.update(constructed_area_dept_detail_u);
					sessionHQL.flush();
					sessionHQL.clear();
					
				}
			
			}
			tx1.commit();
			
			Transaction tx = sessionHQL.beginTransaction();
			CLG_REG_INFRA_CONSTRUCTED_AREA constructed_area_detail =new CLG_REG_INFRA_CONSTRUCTED_AREA();
			
			constructed_area_detail.setCollege_const(college_const);
			constructed_area_detail.setLecturer_hall(lecturer_hall);
			constructed_area_detail.setExam_hall(exam_hall);
			constructed_area_detail.setCentral_library(central_library);
			constructed_area_detail.setBoys_common_room(boys_common_room);
			constructed_area_detail.setGirl_common_room(girl_common_room);
			constructed_area_detail.setCanteen(canteen);
			constructed_area_detail.setAdministrative_section(administrative_section);
			constructed_area_detail.setTotal_area_of_college(total_area_of_college);
			constructed_area_detail.setTotal_lecture_hall(total_lecture_hall);
			constructed_area_detail.setInstitute_id(Integer.parseInt(institute_id));
			constructed_area_detail.setS_id(Integer.parseInt(s_id));
			constructed_area_detail.setCreated_by(Integer.parseInt(userid));
			constructed_area_detail.setCreated_date(date);
			
				if (Integer.parseInt(hid_constructed_area) == 0) {
					constructed_area_detail.setP_id(Integer.parseInt(p_hid_constructed_area));
					int hid_constructed_area1= (Integer) sessionHQL.save(constructed_area_detail);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					return String.valueOf(hid_constructed_area1) ;
			}
				else {

					CLG_REG_INFRA_CONSTRUCTED_AREA constructed_area_detail_u = (CLG_REG_INFRA_CONSTRUCTED_AREA) sessionHQL
							.get(CLG_REG_INFRA_CONSTRUCTED_AREA.class, Integer.parseInt(hid_constructed_area));
					
					constructed_area_detail_u.setCollege_const(college_const);
					constructed_area_detail_u.setLecturer_hall(lecturer_hall);
					constructed_area_detail_u.setExam_hall(exam_hall);
					constructed_area_detail_u.setCentral_library(central_library);
					constructed_area_detail_u.setBoys_common_room(boys_common_room);
					constructed_area_detail_u.setGirl_common_room(girl_common_room);
					constructed_area_detail_u.setCanteen(canteen);
					constructed_area_detail_u.setAdministrative_section(administrative_section);
					constructed_area_detail_u.setTotal_area_of_college(total_area_of_college);
					constructed_area_detail_u.setTotal_lecture_hall(total_lecture_hall);
					constructed_area_detail_u.setInstitute_id(Integer.parseInt(institute_id));
					constructed_area_detail_u.setS_id(Integer.parseInt(s_id));
					constructed_area_detail_u.setModified_by(Integer.parseInt(userid));
					constructed_area_detail_u.setModified_date(date);
					sessionHQL.update(constructed_area_detail_u);
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
	
	//FETCH CONSTRUCTED DEPARTMENT AREA DETAILS
	@RequestMapping(value = "admin/getConstructed_Area_Department_Details", method = RequestMethod.POST)
	public @ResponseBody List<CLG_REG_INFRA_CONSTRUCTED_AREA_DEPT> getConstructed_Area_Department_Details(HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String p_hid_constructed_area = CIDao.getp_idfromuser_id(userid).get(0).get(0);
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL
				.createQuery("from CLG_REG_INFRA_CONSTRUCTED_AREA_DEPT where p_id=:p_id ");
		
		q.setParameter("p_id", Integer.parseInt(p_hid_constructed_area));
		@SuppressWarnings("unchecked")
		List<CLG_REG_INFRA_CONSTRUCTED_AREA_DEPT> clist = (List<CLG_REG_INFRA_CONSTRUCTED_AREA_DEPT>) q.list();
	
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	
	//SAVE CENTRAL LIBRARY DETAILS
	@PostMapping(value = "/Central_Library_Details_Save_Draft_Action")
	public @ResponseBody String Central_Library_Details_Save_Draft_Action( 
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws ParseException {

		if(request.getHeader("Referer") == null ) { 
			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return  "redirect:/login";
		 }
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//			if(val == false) {
//				return  "AccessTiles";
//		}
		
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String s_id = session.getAttribute("super_id").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		
		CLG_REG_INFRA_CENTRAL_LIBRARY pers_p =new CLG_REG_INFRA_CENTRAL_LIBRARY();
		
		String text_book = request.getParameter("text_book");
		String regerence_book = request.getParameter("regerence_book");
		String govn_publication = request.getParameter("govn_publication");
		String new_addition = request.getParameter("new_addition");
		String book_bank = request.getParameter("book_bank");
		String total_book_no = request.getParameter("total_book_no");
		String subscribed_no = request.getParameter("subscribed_no");
		String complementary_no = request.getParameter("complementary_no");
		String news_paper_no = request.getParameter("news_paper_no");
		String back_issue_no = request.getParameter("back_issue_no");
		String library_working_hours = request.getParameter("library_working_hours");
		String student_reading_room_capacity = request.getParameter("student_reading_room_capacity");
		String faculty_reading_room_capacity = request.getParameter("faculty_reading_room_capacity");
		String rading_room_purpose = request.getParameter("rading_room_purpose");
		String home_lending = request.getParameter("home_lending");
		String photocopying_facility = request.getParameter("photocopying_facility");
		String computers_facility = request.getParameter("computers_facility");
		String cataloguing_books = request.getParameter("cataloguing_books");
		String cataloguing_system = request.getParameter("cataloguing_system");
		String librarian_name = request.getParameter("librarian_name");
		String librarian_qualification = request.getParameter("librarian_qualification");
		String other_information = request.getParameter("other_information");
		String elibrary_check = request.getParameter("elibrary_check");
		String total_computers = request.getParameter("total_computers");
		String total_subscription = request.getParameter("total_subscription");
		String total_ebooks = request.getParameter("total_ebooks");
//		String p_hid_central_library = request.getParameter("p_hid_central_library");
		String hid_central_library = request.getParameter("hid_central_library");
		String p_hid_central_library = CIDao.getp_idfromuser_id(userid).get(0).get(0);
		
		
		if (text_book == null || text_book.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Text Book");
			return "Please Enter Text Book";
		}
		if (validation.isOnlyAlphabetNumber(text_book) == false) {
			ra.addAttribute("msg","Text Book " +validation.isOnlyAlphabetNumberMSG);
			return "Text Book" +validation.isOnlyAlphabetNumberMSG;
		}
		if (validation.maxlengthcheck100(text_book) == false) {
			ra.addAttribute("msg", "Text Book" + validation.MaxlengthcheckMSG100);
			return "Text Book " +validation.MaxlengthcheckMSG100;
		}
		if (regerence_book == null || regerence_book.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Reference Book");
			return "Please Enter Reference Book";
		}
		if (validation.isOnlyAlphabetNumber(regerence_book) == false) {
			ra.addAttribute("msg","Reference Book " +validation.isOnlyAlphabetNumberMSG);
			return "Reference Book" +validation.isOnlyAlphabetNumberMSG;
		}
		if (validation.maxlengthcheck100(regerence_book) == false) {
			ra.addAttribute("msg", "Reference Book" + validation.MaxlengthcheckMSG100);
			return "Reference Book " +validation.MaxlengthcheckMSG100;
		}
		if (govn_publication == null || govn_publication.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Government Publication");
			return "Please Enter Government Publication";
		}
		if (validation.isOnlyAlphabetNumber(govn_publication) == false) {
			ra.addAttribute("msg","Government Publication " +validation.isOnlyAlphabetNumberMSG);
			return "Government Publication" +validation.isOnlyAlphabetNumberMSG;
		}
		if (validation.maxlengthcheck100(govn_publication) == false) {
			ra.addAttribute("msg", "Government Publication" + validation.MaxlengthcheckMSG100);
			return "Government Publication " +validation.MaxlengthcheckMSG100;
		}
		if (new_addition == null || new_addition.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter New Addition");
			return "Please Enter New Addition";
		}
		if (validation.isOnlyAlphabetNumber(new_addition) == false) {
			ra.addAttribute("msg","New Addition " +validation.isOnlyAlphabetNumberMSG);
			return "New Addition" +validation.isOnlyAlphabetNumberMSG;
		}
		if (validation.maxlengthcheck100(new_addition) == false) {
			ra.addAttribute("msg", "New Addition" + validation.MaxlengthcheckMSG100);
			return "New Addition " +validation.MaxlengthcheckMSG100;
		}
		if (book_bank == null || book_bank.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Book Bank");
			return "Please Enter Book Bank";
		}
		if (validation.isOnlyAlphabetNumber(book_bank) == false) {
			ra.addAttribute("msg","Book Bank " +validation.isOnlyAlphabetNumberMSG);
			return "Book Bank" +validation.isOnlyAlphabetNumberMSG;
		}
		if (validation.maxlengthcheck100(book_bank) == false) {
			ra.addAttribute("msg", "Book Bank" + validation.MaxlengthcheckMSG100);
			return "Book Bank " +validation.MaxlengthcheckMSG100;
		}
		if (total_book_no == null || total_book_no.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Total Book Number");
			return "Please Enter Total Book Number";
		}
		if (validation.isOnlyNumber(total_book_no) == false) {
			ra.addAttribute("msg","Total Book Number" +validation.isOnlyNumberMSG);
			return "Total Book Number " +validation.isOnlyNumberMSG;
		}
		if (validation.maxlength10Digit(total_book_no) == false) {
			ra.addAttribute("msg", "Total Book Number" + validation.MaxlengthMSG10Digit);
			return "Total Book Number " +validation.MaxlengthMSG10Digit;
		}
		if (subscribed_no == null || subscribed_no.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Total Subscribed Journal");
			return "Please Enter Total Subscribed Journal";
		}
		if (validation.isOnlyNumber(subscribed_no) == false) {
			ra.addAttribute("msg","Total Subscribed Journal" +validation.isOnlyNumberMSG);
			return "Total Subscribed Journal " +validation.isOnlyNumberMSG;
		}
		if (validation.maxlength10Digit(subscribed_no) == false) {
			ra.addAttribute("msg", "Total Subscribed Journal" + validation.MaxlengthMSG10Digit);
			return "Total Subscribed Journal " +validation.MaxlengthMSG10Digit;
		}
		if (complementary_no == null || complementary_no.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Total Complementary Journal");
			return "Please Enter Total Complementary Journal";
		}
		if (validation.isOnlyNumber(complementary_no) == false) {
			ra.addAttribute("msg","Total Complementary Journal" +validation.isOnlyNumberMSG);
			return "Total Complementary Journal " +validation.isOnlyNumberMSG;
		}
		if (validation.maxlength10Digit(complementary_no) == false) {
			ra.addAttribute("msg", "Total Complementary Journal" + validation.MaxlengthMSG10Digit);
			return "Total Complementary Journal " +validation.MaxlengthMSG10Digit;
		}
		if (news_paper_no == null || news_paper_no.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Total News Paper Journal");
			return "Please Enter Total News Paper Journal";
		}
		if (validation.isOnlyNumber(news_paper_no) == false) {
			ra.addAttribute("msg","Total News Paper Journal" +validation.isOnlyNumberMSG);
			return "Total News Paper Journal " +validation.isOnlyNumberMSG;
		}
		if (validation.maxlength10Digit(news_paper_no) == false) {
			ra.addAttribute("msg", "Total News Paper Journal" + validation.MaxlengthMSG10Digit);
			return "Total News Paper Journal " +validation.MaxlengthMSG10Digit;
		}
		if (back_issue_no == null || back_issue_no.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Total Back Issue Journal");
			return "Please Enter Total Back Issue Journal";
		}
		if (validation.isOnlyNumber(back_issue_no) == false) {
			ra.addAttribute("msg","Total Back Issue Journal" +validation.isOnlyNumberMSG);
			return "Total Back Issue Journal " +validation.isOnlyNumberMSG;
		}
		if (validation.maxlength10Digit(back_issue_no) == false) {
			ra.addAttribute("msg", "Total Back Issue Journal" + validation.MaxlengthMSG10Digit);
			return "Total Back Issue Journal " +validation.MaxlengthMSG10Digit;
		}
		if (library_working_hours == null || library_working_hours.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Library Working Hours");
			return "Please Enter Library Working Hours";
		}
		if (validation.isOnlyNumber(library_working_hours) == false) {
			ra.addAttribute("msg","Library Working Hours" +validation.isOnlyNumberMSG);
			return "Library Working Hours " +validation.isOnlyNumberMSG;
		}
		if (validation.maxlength2(library_working_hours) == false) {
			ra.addAttribute("msg", "Library Working Hours" + validation.MaxlengthMSG2);
			return "Library Working Hours " +validation.MaxlengthMSG2;
		}
		if (student_reading_room_capacity == null || student_reading_room_capacity.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Reading Room for Students with Capacity");
			return "Please Enter Reading Room for Students with Capacity";
		}
		if (validation.isOnlyAlphabetNumber(student_reading_room_capacity) == false) {
			ra.addAttribute("msg","Reading Room for Students with Capacity " +validation.isOnlyAlphabetNumberMSG);
			return "Reading Room for Students with Capacity" +validation.isOnlyAlphabetNumberMSG;
		}
		if (validation.maxlengthcheck100(student_reading_room_capacity) == false) {
			ra.addAttribute("msg", "Reading Room for Students with Capacity" + validation.MaxlengthcheckMSG100);
			return "Reading Room for Students with Capacity " +validation.MaxlengthcheckMSG100;
		}
		if (faculty_reading_room_capacity == null || faculty_reading_room_capacity.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Reading Room for Teaching Faculty");
			return "Please Enter Reading Room for Teaching Faculty";
		}
		if (validation.isOnlyAlphabetNumber(faculty_reading_room_capacity) == false) {
			ra.addAttribute("msg","Reading Room for Teaching Faculty " +validation.isOnlyAlphabetNumberMSG);
			return "Reading Room for Teaching Faculty" +validation.isOnlyAlphabetNumberMSG;
		}
		if (validation.maxlengthcheck100(faculty_reading_room_capacity) == false) {
			ra.addAttribute("msg", "Reading Room for Teaching Faculty" + validation.MaxlengthcheckMSG100);
			return "Reading Room for Teaching Faculty " +validation.MaxlengthcheckMSG100;
		}
		if (rading_room_purpose == null || rading_room_purpose.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Reading Room Purpose");
			return "Please Enter Reading Room Purpose";
		}
		if (validation.isOnlyAlphabetNumber(rading_room_purpose) == false) {
			ra.addAttribute("msg","Reading Room Purpose " +validation.isOnlyAlphabetNumberMSG);
			return "Reading Room Purpose" +validation.isOnlyAlphabetNumberMSG;
		}
		if (validation.maxlengthcheck100(rading_room_purpose) == false) {
			ra.addAttribute("msg", "Reading Room Purpose" + validation.MaxlengthcheckMSG100);
			return "Reading Room Purpose " +validation.MaxlengthcheckMSG100;
		}
		if (home_lending == null || home_lending.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Home Lending");
			return "Please Enter Home Lending";
		}
		if (validation.isOnlyAlphabetNumber(home_lending) == false) {
			ra.addAttribute("msg","Home Lending " +validation.isOnlyAlphabetNumberMSG);
			return "Home Lending" +validation.isOnlyAlphabetNumberMSG;
		}
		if (validation.maxlengthcheck100(home_lending) == false) {
			ra.addAttribute("msg", "Home Lending" + validation.MaxlengthcheckMSG100);
			return "Home Lending " +validation.MaxlengthcheckMSG100;
		}
		if (photocopying_facility == null || photocopying_facility.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Photocopying Facility");
			return "Please Enter Photocopying Facility";
		}
		if (validation.isOnlyAlphabetDASH(photocopying_facility) == false) {
			ra.addAttribute("msg","Photocopying Facility " +validation.isOnlyAlphabetMSGDASH);
			return "Photocopying Facility" +validation.isOnlyAlphabetMSGDASH;
		}
		if (validation.maxlengthcheck100(photocopying_facility) == false) {
			ra.addAttribute("msg", "Photocopying Facility" + validation.MaxlengthcheckMSG100);
			return "Photocopying Facility " +validation.MaxlengthcheckMSG100;
		}
			if(cataloguing_books.equals("1")) {
				if (cataloguing_system == null || cataloguing_system.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter System of Cataloguing");
					return "Please Enter System of Cataloguing";
				}
				if (validation.isOnlyAlphabetNumber(cataloguing_system) == false) {
					ra.addAttribute("msg","System of Cataloguing " +validation.isOnlyAlphabetNumberMSG);
					return "System of Cataloguing" +validation.isOnlyAlphabetNumberMSG;
				}
				if (validation.maxlengthcheck100(cataloguing_system) == false) {
					ra.addAttribute("msg", "System of Cataloguing" + validation.MaxlengthcheckMSG100);
					return "System of Cataloguing " +validation.MaxlengthcheckMSG100;
				}
			}
		if (librarian_name == null || librarian_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Librarian Name");
			return "Please Enter Librarian Name";
		}
		if (validation.isOnlyAlphabetDASH(librarian_name) == false) {
			ra.addAttribute("msg","Librarian Name " +validation.isOnlyAlphabetMSGDASH);
			return "Librarian Name" +validation.isOnlyAlphabetMSGDASH;
		}
		if (validation.maxlengthcheck100(librarian_name) == false) {
			ra.addAttribute("msg", "Librarian Name" + validation.MaxlengthcheckMSG100);
			return "Librarian Name " +validation.MaxlengthcheckMSG100;
		}
		if (librarian_qualification == null || librarian_qualification.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Qualification of Librarian");
			return "Please Enter Qualification of Librarian";
		}
		if (validation.isOnlyAlphabetDASH(librarian_qualification) == false) {
			ra.addAttribute("msg","Qualification of Librarian " +validation.isOnlyAlphabetMSGDASH);
			return "Qualification of Librarian" +validation.isOnlyAlphabetMSGDASH;
		}
		if (validation.maxlengthcheck100(librarian_qualification) == false) {
			ra.addAttribute("msg", "Qualification of Librarian" + validation.MaxlengthcheckMSG100);
			return "Qualification of Librarian " +validation.MaxlengthcheckMSG100;
		}
		
			if(elibrary_check.equals("1")) {
				if (total_computers == null || total_computers.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Total Computers");
					return "Please Enter Total Computers";
				}
				if (validation.isOnlyNumber(total_computers) == false) {
					ra.addAttribute("msg","Total Computers" +validation.isOnlyNumberMSG);
					return "Total Computers " +validation.isOnlyNumberMSG;
				}
				if (validation.maxlength10Digit(total_computers) == false) {
					ra.addAttribute("msg", "Total Computers" + validation.MaxlengthMSG10Digit);
					return "Total Computers " +validation.MaxlengthMSG10Digit;
				}
				if (total_subscription == null || total_subscription.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Total Subscription");
					return "Please Enter Total Subscription";
				}
				if (validation.isOnlyNumber(total_subscription) == false) {
					ra.addAttribute("msg","Total Subscription" +validation.isOnlyNumberMSG);
					return "Total Subscription " +validation.isOnlyNumberMSG;
				}
				if (validation.maxlength10Digit(total_subscription) == false) {
					ra.addAttribute("msg", "Total Subscription" + validation.MaxlengthMSG10Digit);
					return "Total Subscription " +validation.MaxlengthMSG10Digit;
				}
				if (total_ebooks == null || total_ebooks.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Total E-Book");
					return "Please Enter Total E-Book";
				}
				if (validation.isOnlyNumber(total_ebooks) == false) {
					ra.addAttribute("msg","Total E-Book" +validation.isOnlyNumberMSG);
					return "Total E-Book " +validation.isOnlyNumberMSG;
				}
				if (validation.maxlength10Digit(total_ebooks) == false) {
					ra.addAttribute("msg", "Total E-Book" + validation.MaxlengthMSG10Digit);
					return "Total E-Book " +validation.MaxlengthMSG10Digit;
				}
			}
		
		if (validation.isOnlyAlphabetNumber(other_information) == false) {
			ra.addAttribute("msg","Other Information " +validation.isOnlyAlphabetNumberMSG);
			return "Other Information" +validation.isOnlyAlphabetNumberMSG;
		}
		if (validation.maxlengthcheck100(other_information) == false) {
			ra.addAttribute("msg", "Other Information" + validation.MaxlengthcheckMSG100);
			return "Other Information " +validation.MaxlengthcheckMSG100;
		}
		
		int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		

		try {
			CLG_REG_INFRA_CENTRAL_LIBRARY central_library_detail =new CLG_REG_INFRA_CENTRAL_LIBRARY();
			
			central_library_detail.setText_book(text_book);
			central_library_detail.setRegerence_book(regerence_book);
			central_library_detail.setGovn_publication(govn_publication);
			central_library_detail.setNew_addition(new_addition);
			central_library_detail.setBook_bank(book_bank);
			central_library_detail.setTotal_book_no((total_book_no.equals("") ? 0 : Integer.parseInt(total_book_no)));
			central_library_detail.setSubscribed_no((subscribed_no.equals("") ? 0 : Integer.parseInt(subscribed_no)));
			central_library_detail.setComplementary_no((complementary_no.equals("") ? 0 : Integer.parseInt(complementary_no)));
			central_library_detail.setNews_paper_no((news_paper_no.equals("") ? 0 : Integer.parseInt(news_paper_no)));
			central_library_detail.setBack_issue_no((back_issue_no.equals("") ? 0 : Integer.parseInt(back_issue_no)));
			central_library_detail.setLibrary_working_hours(library_working_hours);
			central_library_detail.setStudent_reading_room_capacity(student_reading_room_capacity);
			central_library_detail.setFaculty_reading_room_capacity(faculty_reading_room_capacity);
			central_library_detail.setRading_room_purpose(rading_room_purpose);
			central_library_detail.setHome_lending(home_lending);
			central_library_detail.setPhotocopying_facility(photocopying_facility);
			central_library_detail.setComputers_facility(Integer.parseInt(computers_facility));
			central_library_detail.setCataloguing_books(Integer.parseInt(cataloguing_books));
			if(!cataloguing_system.equals("")) {
			central_library_detail.setCataloguing_system(cataloguing_system);
			}
			central_library_detail.setLibrarian_name(librarian_name);
			central_library_detail.setLibrarian_qualification(librarian_qualification);
			central_library_detail.setOther_information(other_information);
			central_library_detail.setElibrary_check(Integer.parseInt(elibrary_check));
			central_library_detail.setTotal_computers((total_computers.equals("") ? 0 : Integer.parseInt(total_computers)));
			central_library_detail.setTotal_subscription((total_subscription.equals("") ? 0 : Integer.parseInt(total_subscription)));
			central_library_detail.setTotal_ebooks((total_ebooks.equals("") ? 0 : Integer.parseInt(total_ebooks)));
			central_library_detail.setInstitute_id(Integer.parseInt(institute_id));
			central_library_detail.setS_id(Integer.parseInt(s_id));
			central_library_detail.setCreated_by(Integer.parseInt(userid));
			central_library_detail.setCreated_date(date);
			
				if (Integer.parseInt(hid_central_library) == 0) {
					central_library_detail.setP_id(Integer.parseInt(p_hid_central_library));
					int hid_central_library1= (Integer) sessionHQL.save(central_library_detail);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					return String.valueOf(hid_central_library1) ;
			}
				else {

					CLG_REG_INFRA_CENTRAL_LIBRARY central_library_detail_u = (CLG_REG_INFRA_CENTRAL_LIBRARY) sessionHQL
							.get(CLG_REG_INFRA_CENTRAL_LIBRARY.class, Integer.parseInt(hid_central_library));
					
					central_library_detail_u.setText_book(text_book);
					central_library_detail_u.setRegerence_book(regerence_book);
					central_library_detail_u.setGovn_publication(govn_publication);
					central_library_detail_u.setNew_addition(new_addition);
					central_library_detail_u.setBook_bank(book_bank);
					central_library_detail_u.setTotal_book_no((total_book_no.equals("") ? 0 : Integer.parseInt(total_book_no)));
					central_library_detail_u.setSubscribed_no((subscribed_no.equals("") ? 0 : Integer.parseInt(subscribed_no)));
					central_library_detail_u.setComplementary_no((complementary_no.equals("") ? 0 : Integer.parseInt(complementary_no)));
					central_library_detail_u.setNews_paper_no((news_paper_no.equals("") ? 0 : Integer.parseInt(news_paper_no)));
					central_library_detail_u.setBack_issue_no((back_issue_no.equals("") ? 0 : Integer.parseInt(back_issue_no)));
					central_library_detail_u.setLibrary_working_hours(library_working_hours);
					central_library_detail_u.setStudent_reading_room_capacity(student_reading_room_capacity);
					central_library_detail_u.setFaculty_reading_room_capacity(faculty_reading_room_capacity);
					central_library_detail_u.setRading_room_purpose(rading_room_purpose);
					central_library_detail_u.setHome_lending(home_lending);
					central_library_detail_u.setPhotocopying_facility(photocopying_facility);
					central_library_detail_u.setComputers_facility(Integer.parseInt(computers_facility));
					central_library_detail_u.setCataloguing_books(Integer.parseInt(cataloguing_books));
					if(!cataloguing_system.equals("")) {
					central_library_detail_u.setCataloguing_system(cataloguing_system);
					}
					central_library_detail_u.setLibrarian_name(librarian_name);
					central_library_detail_u.setLibrarian_qualification(librarian_qualification);
					central_library_detail_u.setOther_information(other_information);
					central_library_detail_u.setElibrary_check(Integer.parseInt(elibrary_check));
					central_library_detail_u.setTotal_computers((total_computers.equals("") ? 0 : Integer.parseInt(total_computers)));
					central_library_detail_u.setTotal_subscription((total_subscription.equals("") ? 0 : Integer.parseInt(total_subscription)));
					central_library_detail_u.setTotal_ebooks((total_ebooks.equals("") ? 0 : Integer.parseInt(total_ebooks)));
					central_library_detail_u.setInstitute_id(Integer.parseInt(institute_id));
					central_library_detail_u.setS_id(Integer.parseInt(s_id));
					central_library_detail_u.setModified_by(Integer.parseInt(userid));
					central_library_detail_u.setModified_date(date);
					sessionHQL.update(central_library_detail_u);
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
	
	//SAVE HOSTEL DETAILS
	@PostMapping(value = "/Hostel_Details_Save_Draft_Action")
	public @ResponseBody String Hostel_Details_Save_Draft_Action( 
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws ParseException {

		if(request.getHeader("Referer") == null ) { 
			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return  "redirect:/login";
		 }
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//			if(val == false) {
//				return  "AccessTiles";
//		}
		
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String s_id = session.getAttribute("super_id").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		
		CLG_REG_INFRA_HOSTEL_DETAILS pers_p =new CLG_REG_INFRA_HOSTEL_DETAILS();
		
		String boys_area = request.getParameter("boys_area");
		String boys_own_rented = request.getParameter("boys_own_rented");
		String boys_room_no = request.getParameter("boys_room_no");
		String boys_capacity = request.getParameter("boys_capacity");
		String boys_mess_facility = request.getParameter("boys_mess_facility");
		String boys_warden_facility = request.getParameter("boys_warden_facility");
		String girls_area = request.getParameter("girls_area");
		String girls_own_rented = request.getParameter("girls_own_rented");
		String girls_room_no = request.getParameter("girls_room_no");
		String girls_capacity = request.getParameter("girls_capacity");
		String girls_mess_facility = request.getParameter("girls_mess_facility");
		String girls_warden_facility = request.getParameter("girls_warden_facility");
		String boys_occupied_room = request.getParameter("boys_occupied_room");
		String girls_occupied_room = request.getParameter("girls_occupied_room");
		
		String guest_area = request.getParameter("guest_area");
		String guest_own_rented = request.getParameter("guest_own_rented");
		String guest_room_no = request.getParameter("guest_room_no");
		String guest_capacity = request.getParameter("guest_capacity");
		String guest_mess_facility = request.getParameter("guest_mess_facility");
		String guest_warden_facility = request.getParameter("guest_warden_facility");
		String guest_occupied_room = request.getParameter("guest_occupied_room");
		
		String hid_hostel_facility = request.getParameter("hid_hostel_facility");
		String p_hid_hostel_facility = CIDao.getp_idfromuser_id(userid).get(0).get(0);
		
		
		if (boys_area == null || boys_area.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Area of Boys Hostel");
			return "Please Enter Area of Boys Hostel";
		}
		if (validation.isOnlyNumerandDotMSG(boys_area) == false) {
			ra.addAttribute("msg","Area of Boys Hostel" +validation.isOnlyNumerandDotMSG);
			return "Area of Boys Hostel " +validation.isOnlyNumerandDotMSG;
		}
		if (validation.maxlength10Digit(boys_area) == false) {
			ra.addAttribute("msg", "Area of Boys Hostel" + validation.MaxlengthMSG10Digit);
			return "Area of Boys Hostel " +validation.MaxlengthMSG10Digit;
		}
		if (boys_own_rented.equals("0")) {
			ra.addAttribute("msg", "Please Select Boys Hostel Own/Rented");
			return "Please Select Boys Hostel Own/Rented";
		}
		if (boys_room_no == null || boys_room_no.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Total Rooms in Boys Hostel");
			return "Please Enter Total Rooms in Boys Hostel";
		}
		if (validation.isOnlyNumber(boys_room_no) == false) {
			ra.addAttribute("msg","Total Rooms in Boys Hostel" +validation.isOnlyNumberMSG);
			return "Total Rooms in Boys Hostel " +validation.isOnlyNumberMSG;
		}
		if (validation.maxlength10Digit(boys_room_no) == false) {
			ra.addAttribute("msg", "Total Rooms in Boys Hostel" + validation.MaxlengthMSG10Digit);
			return "Total Rooms in Boys Hostel " +validation.MaxlengthMSG10Digit;
		}
		if (boys_capacity == null || boys_capacity.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Capacity per Room in Boys Hostel");
			return "Please Enter Capacity per Room in Boys Hostel";
		}
		if (validation.isOnlyNumber(boys_capacity) == false) {
			ra.addAttribute("msg","Capacity per Room in Boys Hostel" +validation.isOnlyNumberMSG);
			return "Capacity per Room in Boys Hostel " +validation.isOnlyNumberMSG;
		}
		if (validation.maxlength10Digit(boys_capacity) == false) {
			ra.addAttribute("msg", "Capacity per Room in Boys Hostel" + validation.MaxlengthMSG10Digit);
			return "Capacity per Room in Boys Hostel " +validation.MaxlengthMSG10Digit;
		}
		if (boys_occupied_room == null || boys_occupied_room.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Total Occupied Rooms in Boys Hostel");
			return "Please Enter Total Occupied Rooms in Boys Hostel";
		}
		if (validation.isOnlyNumber(boys_occupied_room) == false) {
			ra.addAttribute("msg","Total Occupied Rooms in Boys Hostel" +validation.isOnlyNumberMSG);
			return "Total Occupied Rooms in Boys Hostel " +validation.isOnlyNumberMSG;
		}
		if (validation.maxlength10Digit(boys_occupied_room) == false) {
			ra.addAttribute("msg", "Total Occupied Rooms in Boys Hostel" + validation.MaxlengthMSG10Digit);
			return "Total Occupied Rooms in Boys Hostel " +validation.MaxlengthMSG10Digit;
		}
		if (boys_mess_facility.equals("0")) {
			ra.addAttribute("msg", "Please Select Mess Facility in Boys Hostel");
			return "Please Select Mess Facility in Boys Hostel";
		}
		if (boys_warden_facility.equals("0")) {
			ra.addAttribute("msg", "Please Select Warden Facility Boys Hostel");
			return "Please Select Warden Facility Boys Hostel";
		}
		if (girls_area == null || girls_area.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Area of Girls Hostel");
			return "Please Enter Area of Girls Hostel";
		}
		if (validation.isOnlyNumerandDotMSG(girls_area) == false) {
			ra.addAttribute("msg","Area of Girls Hostel" +validation.isOnlyNumerandDotMSG);
			return "Area of Girls Hostel " +validation.isOnlyNumerandDotMSG;
		}
		if (validation.maxlength10Digit(girls_area) == false) {
			ra.addAttribute("msg", "Area of Girls Hostel" + validation.MaxlengthMSG10Digit);
			return "Area of Girls Hostel " +validation.MaxlengthMSG10Digit;
		}
		if (girls_own_rented.equals("0")) {
			ra.addAttribute("msg", "Please Select Girls Hostel Own/Rented");
			return "Please Select Girls Hostel Own/Rented";
		}
		if (girls_room_no == null || girls_room_no.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Total Rooms in Girls Hostel");
			return "Please Enter Total Rooms in Girls Hostel";
		}
		if (validation.isOnlyNumber(girls_room_no) == false) {
			ra.addAttribute("msg","Total Rooms in Girls Hostel" +validation.isOnlyNumberMSG);
			return "Total Rooms in Girls Hostel " +validation.isOnlyNumberMSG;
		}
		if (validation.maxlength10Digit(girls_room_no) == false) {
			ra.addAttribute("msg", "Total Rooms in Girls Hostel" + validation.MaxlengthMSG10Digit);
			return "Total Rooms in Girls Hostel " +validation.MaxlengthMSG10Digit;
		}
		if (girls_capacity == null || girls_capacity.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Capacity per Room in Girls Hostel");
			return "Please Enter Capacity per Room in Girls Hostel";
		}
		if (validation.isOnlyNumber(girls_capacity) == false) {
			ra.addAttribute("msg","Capacity per Room in Girls Hostel" +validation.isOnlyNumberMSG);
			return "Capacity per Room in Girls Hostel " +validation.isOnlyNumberMSG;
		}
		if (validation.maxlength10Digit(girls_capacity) == false) {
			ra.addAttribute("msg", "Capacity per Room in Girls Hostel" + validation.MaxlengthMSG10Digit);
			return "Capacity per Room in Girls Hostel " +validation.MaxlengthMSG10Digit;
		}
		if (girls_occupied_room == null || girls_occupied_room.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Total Occupied Rooms in Girls Hostel");
			return "Please Enter Total Occupied Rooms in Girls Hostel";
		}
		if (validation.isOnlyNumber(girls_occupied_room) == false) {
			ra.addAttribute("msg","Total Occupied Rooms in Girls Hostel" +validation.isOnlyNumberMSG);
			return "Total Occupied Rooms in Girls Hostel " +validation.isOnlyNumberMSG;
		}
		if (validation.maxlength10Digit(girls_occupied_room) == false) {
			ra.addAttribute("msg", "Total Occupied Rooms in Girls Hostel" + validation.MaxlengthMSG10Digit);
			return "Total Occupied Rooms in Girls Hostel " +validation.MaxlengthMSG10Digit;
		}
		if (girls_mess_facility.equals("0")) {
			ra.addAttribute("msg", "Please Select Mess Facility in Girls Hostel");
			return "Please Select Mess Facility in Girls Hostel";
		}
		if (girls_warden_facility.equals("0")) {
			ra.addAttribute("msg", "Please Select Warden Facility Girls Hostel");
			return "Please Select Warden Facility Girls Hostel";
		}
		if (guest_area == null || guest_area.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Area of Guest Hostel");
			return "Please Enter Area of Guest Hostel";
		}
		if (validation.isOnlyNumerandDotMSG(guest_area) == false) {
			ra.addAttribute("msg","Area of Guest Hostel" +validation.isOnlyNumerandDotMSG);
			return "Area of Guest Hostel " +validation.isOnlyNumerandDotMSG;
		}
		if (validation.maxlength10Digit(guest_area) == false) {
			ra.addAttribute("msg", "Area of Guest Hostel" + validation.MaxlengthMSG10Digit);
			return "Area of Guest Hostel " +validation.MaxlengthMSG10Digit;
		}
		if (guest_own_rented.equals("0")) {
			ra.addAttribute("msg", "Please Select Guest Hostel Own/Rented");
			return "Please Select Guest Hostel Own/Rented";
		}
		if (guest_room_no == null || guest_room_no.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Total Rooms in Guest Hostel");
			return "Please Enter Total Rooms in Guest Hostel";
		}
		if (validation.isOnlyNumber(guest_room_no) == false) {
			ra.addAttribute("msg","Total Rooms in Guest Hostel" +validation.isOnlyNumberMSG);
			return "Total Rooms in Guest Hostel " +validation.isOnlyNumberMSG;
		}
		if (validation.maxlength10Digit(guest_room_no) == false) {
			ra.addAttribute("msg", "Total Rooms in Guest Hostel" + validation.MaxlengthMSG10Digit);
			return "Total Rooms in Guest Hostel " +validation.MaxlengthMSG10Digit;
		}
		if (guest_capacity == null || guest_capacity.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Capacity per Room in Guest Hostel");
			return "Please Enter Capacity per Room in Guest Hostel";
		}
		if (validation.isOnlyNumber(guest_capacity) == false) {
			ra.addAttribute("msg","Capacity per Room in Guest Hostel" +validation.isOnlyNumberMSG);
			return "Capacity per Room in Guest Hostel " +validation.isOnlyNumberMSG;
		}
		if (validation.maxlength10Digit(guest_capacity) == false) {
			ra.addAttribute("msg", "Capacity per Room in Guest Hostel" + validation.MaxlengthMSG10Digit);
			return "Capacity per Room in Guest Hostel " +validation.MaxlengthMSG10Digit;
		}
		if (guest_occupied_room == null || guest_occupied_room.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Total Occupied Rooms in Guest Hostel");
			return "Please Enter Total Occupied Rooms in Guest Hostel";
		}
		if (validation.isOnlyNumber(guest_occupied_room) == false) {
			ra.addAttribute("msg","Total Occupied Rooms in Guest Hostel" +validation.isOnlyNumberMSG);
			return "Total Occupied Rooms in Guest Hostel " +validation.isOnlyNumberMSG;
		}
		if (validation.maxlength10Digit(guest_occupied_room) == false) {
			ra.addAttribute("msg", "Total Occupied Rooms in Guest Hostel" + validation.MaxlengthMSG10Digit);
			return "Total Occupied Rooms in Guest Hostel " +validation.MaxlengthMSG10Digit;
		}
		if (guest_mess_facility.equals("0")) {
			ra.addAttribute("msg", "Please Select Mess Facility in Guest Hostel");
			return "Please Select Mess Facility in Guest Hostel";
		}
		if (guest_warden_facility.equals("0")) {
			ra.addAttribute("msg", "Please Select Warden Facility Guest Hostel");
			return "Please Select Warden Facility Guest Hostel";
		}
		
		
		int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		

		try {
			CLG_REG_INFRA_HOSTEL_DETAILS hostel_detail =new CLG_REG_INFRA_HOSTEL_DETAILS();
			
				hostel_detail.setBoys_area(boys_area);
				hostel_detail.setBoys_own_rented(Integer.parseInt(boys_own_rented));
				hostel_detail.setBoys_room_no((boys_room_no.equals("") ? 0 : Integer.parseInt(boys_room_no)));
				hostel_detail.setBoys_capacity((boys_capacity.equals("") ? 0 : Integer.parseInt(boys_capacity)));
				hostel_detail.setBoys_mess_facility(Integer.parseInt(boys_mess_facility));
				hostel_detail.setBoys_warden_facility(Integer.parseInt(boys_warden_facility));
				hostel_detail.setBoys_occupied_room((boys_occupied_room.equals("") ? 0 : Integer.parseInt(boys_occupied_room)));
				
				hostel_detail.setGirls_area(girls_area);
				hostel_detail.setGirls_own_rented(Integer.parseInt(girls_own_rented));
				hostel_detail.setGirls_room_no((girls_room_no.equals("") ? 0 : Integer.parseInt(girls_room_no)));
				hostel_detail.setGirls_capacity((girls_capacity.equals("") ? 0 : Integer.parseInt(girls_capacity)));
				hostel_detail.setGirls_mess_facility(Integer.parseInt(girls_mess_facility));
				hostel_detail.setGirls_warden_facility(Integer.parseInt(girls_warden_facility));
				hostel_detail.setGirls_occupied_room((girls_occupied_room.equals("") ? 0 : Integer.parseInt(girls_occupied_room)));
				
				hostel_detail.setGuest_area(guest_area);
				hostel_detail.setGuest_own_rented((guest_own_rented.equals("") ? 0 : Integer.parseInt(guest_own_rented)));
				hostel_detail.setGuest_room_no((guest_room_no.equals("") ? 0 : Integer.parseInt(guest_room_no)));
				hostel_detail.setGuest_capacity((guest_capacity.equals("") ? 0 : Integer.parseInt(guest_capacity)));
				hostel_detail.setGuest_mess_facility((guest_mess_facility.equals("") ? 0 : Integer.parseInt(guest_mess_facility)));
				hostel_detail.setGuest_warden_facility((guest_warden_facility.equals("") ? 0 : Integer.parseInt(guest_warden_facility)));
				hostel_detail.setGuest_occupied_room((guest_occupied_room.equals("") ? 0 : Integer.parseInt(guest_occupied_room)));
			
			
				hostel_detail.setInstitute_id(Integer.parseInt(institute_id));
				hostel_detail.setS_id(Integer.parseInt(s_id));
				hostel_detail.setCreated_by(Integer.parseInt(userid));
				hostel_detail.setCreated_date(date);
			
				if (Integer.parseInt(hid_hostel_facility) == 0) {
					hostel_detail.setP_id(Integer.parseInt(p_hid_hostel_facility));
					int hid_hostel_facility1= (Integer) sessionHQL.save(hostel_detail);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					return String.valueOf(hid_hostel_facility1) ;
			}
				else {

					CLG_REG_INFRA_HOSTEL_DETAILS hostel_detail_u = (CLG_REG_INFRA_HOSTEL_DETAILS) sessionHQL
							.get(CLG_REG_INFRA_HOSTEL_DETAILS.class, Integer.parseInt(hid_hostel_facility));
					
					hostel_detail_u.setBoys_area(boys_area);
					hostel_detail_u.setBoys_own_rented(Integer.parseInt(boys_own_rented));
					hostel_detail_u.setBoys_room_no((boys_room_no.equals("") ? 0 : Integer.parseInt(boys_room_no)));
					hostel_detail_u.setBoys_capacity((boys_capacity.equals("") ? 0 : Integer.parseInt(boys_capacity)));
					hostel_detail_u.setBoys_mess_facility(Integer.parseInt(boys_mess_facility));
					hostel_detail_u.setBoys_warden_facility(Integer.parseInt(boys_warden_facility));
					hostel_detail_u.setBoys_occupied_room((boys_occupied_room.equals("") ? 0 : Integer.parseInt(boys_occupied_room)));
					
					hostel_detail_u.setGirls_area(girls_area);
					hostel_detail_u.setGirls_own_rented(Integer.parseInt(girls_own_rented));
					hostel_detail_u.setGirls_room_no((girls_room_no.equals("") ? 0 : Integer.parseInt(girls_room_no)));
					hostel_detail_u.setGirls_capacity((girls_capacity.equals("") ? 0 : Integer.parseInt(girls_capacity)));
					hostel_detail_u.setGirls_mess_facility(Integer.parseInt(girls_mess_facility));
					hostel_detail_u.setGirls_warden_facility(Integer.parseInt(girls_warden_facility));
					hostel_detail_u.setGirls_occupied_room((girls_occupied_room.equals("") ? 0 : Integer.parseInt(girls_occupied_room)));
					
					hostel_detail_u.setGuest_area(guest_area);
					hostel_detail_u.setGuest_own_rented((guest_own_rented.equals("") ? 0 : Integer.parseInt(guest_own_rented)));
					hostel_detail_u.setGuest_room_no((guest_room_no.equals("") ? 0 : Integer.parseInt(guest_room_no)));
					hostel_detail_u.setGuest_capacity((guest_capacity.equals("") ? 0 : Integer.parseInt(guest_capacity)));
					hostel_detail_u.setGuest_mess_facility((guest_mess_facility.equals("") ? 0 : Integer.parseInt(guest_mess_facility)));
					hostel_detail_u.setGuest_warden_facility((guest_warden_facility.equals("") ? 0 : Integer.parseInt(guest_warden_facility)));
					hostel_detail_u.setGuest_occupied_room((guest_occupied_room.equals("") ? 0 : Integer.parseInt(guest_occupied_room)));
					
					hostel_detail_u.setInstitute_id(Integer.parseInt(institute_id));
					hostel_detail_u.setS_id(Integer.parseInt(s_id));
					hostel_detail_u.setModified_by(Integer.parseInt(userid));
					hostel_detail_u.setModified_date(date);
					sessionHQL.update(hostel_detail_u);
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
	
	//SAVE MESS DETAILS
	@PostMapping(value = "/Mess_Details_Save_Draft_Action")
	public @ResponseBody String Mess_Details_Save_Draft_Action( 
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws ParseException, IOException {

		if(request.getHeader("Referer") == null ) { 
			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return  "redirect:/login";
		 }
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//			if(val == false) {
//				return  "AccessTiles";
//		}
		
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String s_id = session.getAttribute("super_id").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		
		String type_of_mess = request.getParameter("type_of_mess");
		String mess_total_area = request.getParameter("mess_total_area");
		String mess_total_cooks = request.getParameter("mess_total_cooks");
		String mess_total_capacity = request.getParameter("mess_total_capacity");
		String hid_mess_details = request.getParameter("hid_mess_details");
		String p_hid_mess_details = CIDao.getp_idfromuser_id(userid).get(0).get(0);
		
		
		if (type_of_mess.equals("0")) {
			ra.addAttribute("msg", "Please Select Type of Mess");
			return "Please Select Type of Mess";
		}
		if (mess_total_area == null || mess_total_area.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Total Area of Mess");
			return "Please Enter Total Area of Mess";
		}
		if (validation.isOnlyNumerandDotMSG(mess_total_area) == false) {
			ra.addAttribute("msg","Total Area of Mess" +validation.isOnlyNumerandDotMSG);
			return "Total Area of Mess " +validation.isOnlyNumerandDotMSG;
		}
		if (validation.maxlength10Digit(mess_total_area) == false) {
			ra.addAttribute("msg", "Total Area of Mess" + validation.MaxlengthMSG10Digit);
			return "Total Area of Mess " +validation.MaxlengthMSG10Digit;
		}
		if (mess_total_cooks == null || mess_total_cooks.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Total Cooks in Mess");
			return "Please Enter Total Cooks in Mess";
		}
		if (validation.isOnlyNumber(mess_total_cooks) == false) {
			ra.addAttribute("msg","Total Cooks in Mess" +validation.isOnlyNumberMSG);
			return "Total Cooks in Mess " +validation.isOnlyNumberMSG;
		}
		if (validation.maxlength10Digit(mess_total_cooks) == false) {
			ra.addAttribute("msg", "Total Cooks in Mess" + validation.MaxlengthMSG10Digit);
			return "Total Cooks in Mess " +validation.MaxlengthMSG10Digit;
		}
		if (mess_total_capacity == null || mess_total_capacity.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Total Capacity in Mess");
			return "Please Enter Total Capacity in Mess";
		}
		if (validation.isOnlyNumber(mess_total_capacity) == false) {
			ra.addAttribute("msg","Total Capacity in Mess" +validation.isOnlyNumberMSG);
			return "Total Capacity in Mess " +validation.isOnlyNumberMSG;
		}
		if (validation.maxlength10Digit(mess_total_capacity) == false) {
			ra.addAttribute("msg", "Total Capacity in Mess" + validation.MaxlengthMSG10Digit);
			return "Total Capacity in Mess " +validation.MaxlengthMSG10Digit;
		}
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		

		try {
			CLG_REG_INFRA_MESS_DETAILS mess_detail =new CLG_REG_INFRA_MESS_DETAILS();
			
			mess_detail.setType_of_mess(type_of_mess);
			mess_detail.setMess_total_area(mess_total_area);
			mess_detail.setMess_total_cooks((mess_total_cooks.equals("") ? 0 : Integer.parseInt(mess_total_cooks)));
			mess_detail.setMess_total_capacity((mess_total_capacity.equals("") ? 0 : Integer.parseInt(mess_total_capacity)));
			mess_detail.setInstitute_id(Integer.parseInt(institute_id));
			mess_detail.setS_id(Integer.parseInt(s_id));
			mess_detail.setCreated_by(Integer.parseInt(userid));
			mess_detail.setCreated_date(date);
			
				if (Integer.parseInt(hid_mess_details) == 0) {
					mess_detail.setP_id(Integer.parseInt(p_hid_mess_details));
					int hid_herbal_garden1= (Integer) sessionHQL.save(mess_detail);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					return String.valueOf(hid_herbal_garden1) ;
			}
				else {

					CLG_REG_INFRA_MESS_DETAILS mess_detail_u = (CLG_REG_INFRA_MESS_DETAILS) sessionHQL
							.get(CLG_REG_INFRA_MESS_DETAILS.class, Integer.parseInt(hid_mess_details));
					
					mess_detail_u.setType_of_mess(type_of_mess);
					mess_detail_u.setMess_total_area(mess_total_area);
					mess_detail_u.setMess_total_cooks((mess_total_cooks.equals("") ? 0 : Integer.parseInt(mess_total_cooks)));
					mess_detail_u.setMess_total_capacity((mess_total_capacity.equals("") ? 0 : Integer.parseInt(mess_total_capacity)));
					mess_detail_u.setInstitute_id(Integer.parseInt(institute_id));
					mess_detail_u.setS_id(Integer.parseInt(s_id));
					mess_detail_u.setModified_by(Integer.parseInt(userid));
					mess_detail_u.setModified_date(date);
					sessionHQL.update(mess_detail_u);
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
	
	
	//SAVE HERBAL GARDEN DETAILS
	@PostMapping(value = "/Herbal_Garden_Details_Save_Draft_Action")
	public @ResponseBody String Herbal_Garden_Details_Save_Draft_Action( 
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			@RequestParam(value = "plant_species_list", required = false) MultipartFile plant_species,
			@RequestParam(value = "herbal_garden_list", required = false) MultipartFile herbal_garden,MultipartHttpServletRequest mul,
			RedirectAttributes ra) throws ParseException, IOException {

		if(request.getHeader("Referer") == null ) { 
			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return  "redirect:/login";
		 }
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//			if(val == false) {
//				return  "AccessTiles";
//		}
		
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String plant_species_list = "plant_species_list";
		String herbal_garden_list = "herbal_garden_list";
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String s_id = session.getAttribute("super_id").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		
		String total_area = request.getParameter("total_area");
		String total_cultivated_species = request.getParameter("total_cultivated_species");
		String irrigation_facility = request.getParameter("irrigation_facility");
		String hid_herbal_garden = request.getParameter("hid_herbal_garden");
		String p_hid_herbal_garden = CIDao.getp_idfromuser_id(userid).get(0).get(0);
		
		if (!plant_species.isEmpty()) {
			plant_species_list = upload_imagemethod(request,plant_species,session, plant_species_list);
		}
		else {
			plant_species_list = request.getParameter("hid_plant_species_list");
		}
		if (!herbal_garden.isEmpty()) {
			herbal_garden_list = upload_imagemethod(request,herbal_garden,session, herbal_garden_list);
		}
		else {
			herbal_garden_list = request.getParameter("hid_herbal_garden_list");
		}
		
		
		if (total_area == null || total_area.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Total Area of Herbal Garden");
			return "Please Enter Total Area of Herbal Garden";
		}
		if (validation.isOnlyNumerandDotMSG(total_area) == false) {
			ra.addAttribute("msg","Total Area of Herbal Garden" +validation.isOnlyNumerandDotMSG);
			return "Total Area of Herbal Garden " +validation.isOnlyNumerandDotMSG;
		}
		if (validation.maxlength10Digit(total_area) == false) {
			ra.addAttribute("msg", "Total Area of Herbal Garden" + validation.MaxlengthMSG10Digit);
			return "Total Area of Herbal Garden " +validation.MaxlengthMSG10Digit;
		}
		if (total_cultivated_species == null || total_cultivated_species.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Total Cultivated Speicies");
			return "Please Enter Total Cultivated Speicies";
		}
		if (validation.isOnlyNumber(total_cultivated_species) == false) {
			ra.addAttribute("msg","Total Cultivated Speicies" +validation.isOnlyNumberMSG);
			return "Total Cultivated Speicies " +validation.isOnlyNumberMSG;
		}
		if (validation.maxlength10Digit(total_cultivated_species) == false) {
			ra.addAttribute("msg", "Total Cultivated Speicies" + validation.MaxlengthMSG10Digit);
			return "Total Cultivated Speicies " +validation.MaxlengthMSG10Digit;
		}
		if (irrigation_facility.equals("0")) {
			ra.addAttribute("msg", "Please Select Irrigation Facility");
			return "Please Select Irrigation Facility";
		}
		
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		

		try {
			CLG_REG_INFRA_HERBAL_GARDEN herbal_garden_detail =new CLG_REG_INFRA_HERBAL_GARDEN();
			
			herbal_garden_detail.setTotal_area(total_area);
			herbal_garden_detail.setTotal_cultivated_species((total_cultivated_species.equals("") ? 0 : Integer.parseInt(total_cultivated_species)));
			if(!plant_species_list.equals("")) {
			herbal_garden_detail.setPlant_species_list(plant_species_list);
			}
			if(!herbal_garden_list.equals("")) {
			herbal_garden_detail.setHerbal_garden_list(herbal_garden_list);
			}
			herbal_garden_detail.setIrrigation_facility(Integer.parseInt(irrigation_facility));
			herbal_garden_detail.setInstitute_id(Integer.parseInt(institute_id));
			herbal_garden_detail.setS_id(Integer.parseInt(s_id));
			herbal_garden_detail.setCreated_by(Integer.parseInt(userid));
			herbal_garden_detail.setCreated_date(date);
			
				if (Integer.parseInt(hid_herbal_garden) == 0) {
					herbal_garden_detail.setP_id(Integer.parseInt(p_hid_herbal_garden));
					int hid_herbal_garden1= (Integer) sessionHQL.save(herbal_garden_detail);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					return String.valueOf(hid_herbal_garden1) ;
			}
				else {

					CLG_REG_INFRA_HERBAL_GARDEN herbal_garden_detail_u = (CLG_REG_INFRA_HERBAL_GARDEN) sessionHQL
							.get(CLG_REG_INFRA_HERBAL_GARDEN.class, Integer.parseInt(hid_herbal_garden));
					
					herbal_garden_detail_u.setTotal_area(total_area);
					herbal_garden_detail_u.setTotal_cultivated_species((total_cultivated_species.equals("") ? 0 : Integer.parseInt(total_cultivated_species)));
					if(!plant_species_list.equals("")) {
					herbal_garden_detail_u.setPlant_species_list(plant_species_list);
					}
					if(!herbal_garden_list.equals("")) {
					herbal_garden_detail_u.setHerbal_garden_list(herbal_garden_list);
					}
					herbal_garden_detail_u.setIrrigation_facility(Integer.parseInt(irrigation_facility));
					herbal_garden_detail_u.setInstitute_id(Integer.parseInt(institute_id));
					herbal_garden_detail_u.setS_id(Integer.parseInt(s_id));
					herbal_garden_detail_u.setModified_by(Integer.parseInt(userid));
					herbal_garden_detail_u.setModified_date(date);
					sessionHQL.update(herbal_garden_detail_u);
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
	
	
	//SAVE ADDITIONAL INFORMATION DETAILS
	@PostMapping(value = "/Add_Info_Details_Save_Draft_Action")
	public @ResponseBody String Add_Info_Details_Save_Draft_Action( 
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			@RequestParam(value = "compliance_report_doc", required = false) MultipartFile compliance_report_document,MultipartHttpServletRequest mul,
			RedirectAttributes ra) throws ParseException, IOException {

		if(request.getHeader("Referer") == null ) { 
			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return  "redirect:/login";
		 }
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//			if(val == false) {
//				return  "AccessTiles";
//		}
		
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String s_id = session.getAttribute("super_id").toString();
		
		String compliance_report_doc = "compliance_report_doc";
		String trasport_facility = request.getParameter("trasport_facility");
		String sports_facility = request.getParameter("sports_facility");
		String inspection_pending = request.getParameter("inspection_pending");
		String penalty_amount = request.getParameter("penalty_amount");
		String swasthya_rakshan_programme = request.getParameter("swasthya_rakshan_programme");
		String compliance_report = request.getParameter("compliance_report");
		String ambulance_facility = request.getParameter("ambulance_facility");
		String compliance_report_check = request.getParameter("compliance_report_check");
		String hid_additional_information = request.getParameter("hid_additional_information");
		
		String p_hid_additional_information = CIDao.getp_idfromuser_id(userid).get(0).get(0);
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		
		if (!compliance_report_document.isEmpty()) {
			compliance_report_doc = upload_imagemethod(request,compliance_report_document,session, compliance_report_doc);
		}
		else {
			compliance_report_doc = request.getParameter("hid_compliance_report_doc");
		}
		
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		

		try {
			CLG_REG_INFRA_ADDITIONAL_INFORMATION add_info_detail =new CLG_REG_INFRA_ADDITIONAL_INFORMATION();
			
			add_info_detail.setTrasport_facility(Integer.parseInt(trasport_facility));
			add_info_detail.setSports_facility(Integer.parseInt(sports_facility));
			add_info_detail.setInspection_pending(Integer.parseInt(inspection_pending));
			add_info_detail.setPenalty_amount(Integer.parseInt(penalty_amount));
			add_info_detail.setSwasthya_rakshan_programme(Integer.parseInt(swasthya_rakshan_programme));
			add_info_detail.setCompliance_report(Integer.parseInt(compliance_report));
			add_info_detail.setAmbulance_facility(Integer.parseInt(ambulance_facility));
			add_info_detail.setCompliance_report_check(Integer.parseInt(compliance_report_check));
			add_info_detail.setCompliance_report_doc(compliance_report_doc);
			add_info_detail.setInstitute_id(Integer.parseInt(institute_id));
			add_info_detail.setS_id(Integer.parseInt(s_id));
			add_info_detail.setCreated_by(Integer.parseInt(userid));
			add_info_detail.setCreated_date(date);
			
				if (Integer.parseInt(hid_additional_information) == 0) {
					add_info_detail.setP_id(Integer.parseInt(p_hid_additional_information));
					int hid_additional_information1= (Integer) sessionHQL.save(add_info_detail);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					return String.valueOf(hid_additional_information1) ;
			}
				else {

					CLG_REG_INFRA_ADDITIONAL_INFORMATION add_info_detail_u = (CLG_REG_INFRA_ADDITIONAL_INFORMATION) sessionHQL
							.get(CLG_REG_INFRA_ADDITIONAL_INFORMATION.class, Integer.parseInt(hid_additional_information));
					
					add_info_detail_u.setTrasport_facility(Integer.parseInt(trasport_facility));
					add_info_detail_u.setSports_facility(Integer.parseInt(sports_facility));
					add_info_detail_u.setInspection_pending(Integer.parseInt(inspection_pending));
					add_info_detail_u.setPenalty_amount(Integer.parseInt(penalty_amount));
					add_info_detail_u.setSwasthya_rakshan_programme(Integer.parseInt(swasthya_rakshan_programme));
					add_info_detail_u.setCompliance_report(Integer.parseInt(compliance_report));
					add_info_detail_u.setAmbulance_facility(Integer.parseInt(ambulance_facility));
					add_info_detail_u.setCompliance_report_check(Integer.parseInt(compliance_report_check));
					add_info_detail_u.setCompliance_report_doc(compliance_report_doc);
					add_info_detail_u.setInstitute_id(Integer.parseInt(institute_id));
					add_info_detail_u.setS_id(Integer.parseInt(s_id));
					add_info_detail_u.setModified_by(Integer.parseInt(userid));
					add_info_detail_u.setModified_date(date);
					sessionHQL.update(add_info_detail_u);
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
		return  "Final Submit Sucessfully";
	}
	
	
	//ADD MORE SAVE FOR LIBRARIAN DETAILS
	@PostMapping(value = "/Librarian_Details_Save_Draft_Action")
	public @ResponseBody String Librarian_Details_Save_Draft_Action( 
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws ParseException {

		if(request.getHeader("Referer") == null ) { 
			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return  "redirect:/login";
		 }
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//			if(val == false) {
//				return  "AccessTiles";
//		}
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String s_id = session.getAttribute("super_id").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		
		String indno = request.getParameter("indno_library");
		String library_assistants_name = request.getParameter("library_assistants_name"+indno);
		String assistants_qualification = request.getParameter("assistants_qualification"+indno);
		String hid_library = request.getParameter("hid_library"+indno);
		String p_hid_library = CIDao.getp_idfromuser_id(userid).get(0).get(0);
		
		if (validation.isOnlyAlphabetDASH(library_assistants_name) == false) {
			ra.addAttribute("msg","Library Assistant Name " +validation.isOnlyAlphabetMSGDASH);
			return "Library Assistant Name" +validation.isOnlyAlphabetMSGDASH;
		}
		if (validation.isOnlyAlphabetDASH(assistants_qualification) == false) {
			ra.addAttribute("msg","Assistant Qualification " +validation.isOnlyAlphabetMSGDASH);
			return "Assistant Qualification" +validation.isOnlyAlphabetMSGDASH;
		}
		
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		

		try {
			CLG_REG_INFRA_CENTRAL_LIBRARY_CHILD librarian_detail =new CLG_REG_INFRA_CENTRAL_LIBRARY_CHILD();
			
			librarian_detail.setLibrary_assistants_name(library_assistants_name);
			librarian_detail.setAssistants_qualification(assistants_qualification);
			librarian_detail.setInstitute_id(Integer.parseInt(institute_id));
			librarian_detail.setS_id(Integer.parseInt(s_id));
			librarian_detail.setCreated_by(Integer.parseInt(userid));
			librarian_detail.setCreated_date(date);
				if (Integer.parseInt(hid_library) == 0) {
					librarian_detail.setP_id(Integer.parseInt(p_hid_library));
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
					librarian_detail_u.setInstitute_id(Integer.parseInt(institute_id));
					librarian_detail_u.setS_id(Integer.parseInt(s_id));
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
	
	
	
	//ADD MORE FETCH FOR LIBRARIAN DETAILS
	@RequestMapping(value = "admin/getLibrarian_Details", method = RequestMethod.POST)
	public @ResponseBody List<CLG_REG_INFRA_CENTRAL_LIBRARY_CHILD> getLibrarian_Details(HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL
				.createQuery("from CLG_REG_INFRA_CENTRAL_LIBRARY_CHILD where institute_id=:institute_id_id ");
		
		q.setParameter("institute_id_id", Integer.parseInt(institute_id));
		
		@SuppressWarnings("unchecked")
		List<CLG_REG_INFRA_CENTRAL_LIBRARY_CHILD> clist = (List<CLG_REG_INFRA_CENTRAL_LIBRARY_CHILD>) q.list();
	
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	
	
	//ADD MORE DELETE FOR LIBRARIAN DETAILS
	@PostMapping(value = "/delete_Librarian_Details")
	public @ResponseBody String delete_Librarian_Details(String hid_library,HttpSession session1) {

		String msg="";

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		System.err.println("doc_attach_id===================="+hid_library);
		try {
			String hqlUpdate = "delete from CLG_REG_INFRA_CENTRAL_LIBRARY_CHILD where id=:id";
			int app = session.createQuery(hqlUpdate).setInteger("id",Integer.parseInt(hid_library)).executeUpdate();
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
	
	
	
	
	//ADD MORE SAVE FOR LIBRARIAN DETAILS
	@PostMapping(value = "/Camera_Location_Save_Draft_Action")
	public @ResponseBody String Camera_Location_Save_Draft_Action( 
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,MultipartHttpServletRequest mul,
			RedirectAttributes ra) throws ParseException, IOException {

		if(request.getHeader("Referer") == null ) { 
			 session.invalidate();
			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return  "redirect:/login";
		 }
		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//			if(val == false) {
//				return  "AccessTiles";
//		}
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String s_id = session.getAttribute("super_id").toString();
		String indno = request.getParameter("indno_camera");
		String hid_camera = request.getParameter("hid_camera"+indno);
		MultipartFile file = mul.getFile("camera_location"+indno);
		String upload_attachment = "";
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		
		if (!file.isEmpty()) {
			upload_attachment = upload_imagemethod(request,file, session, hid_camera);
		}
		else {
			upload_attachment = request.getParameter("hid_camera_location"+indno);
		}
		
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		

		try {
			CLG_REG_INFRA_COLLEGE_COUNCIL_CAMERA camera_detail =new CLG_REG_INFRA_COLLEGE_COUNCIL_CAMERA();
			
			camera_detail.setCamera_location(upload_attachment);
			camera_detail.setInstitute_id(Integer.parseInt(institute_id));
			camera_detail.setS_id(Integer.parseInt(s_id));
			camera_detail.setCreated_by(Integer.parseInt(userid));
			camera_detail.setCreated_date(date);
				if (Integer.parseInt(hid_camera) == 0) {
					int hid_camera1= (Integer) sessionHQL.save(camera_detail);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					return String.valueOf(hid_camera1) ;
			}
				else {

					CLG_REG_INFRA_COLLEGE_COUNCIL_CAMERA camera_detail_u = (CLG_REG_INFRA_COLLEGE_COUNCIL_CAMERA) sessionHQL
							.get(CLG_REG_INFRA_COLLEGE_COUNCIL_CAMERA.class, Integer.parseInt(hid_camera));
					
					camera_detail_u.setCamera_location(upload_attachment);
					camera_detail_u.setInstitute_id(Integer.parseInt(institute_id));
					camera_detail_u.setS_id(Integer.parseInt(s_id));
					camera_detail_u.setModified_by(Integer.parseInt(userid));
					camera_detail_u.setModified_date(date);
					sessionHQL.update(camera_detail_u);
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
	
	
	
	//ADD MORE FETCH FOR CAMERA LOCATION DETAILS
	@RequestMapping(value = "admin/getCamera_Location_Details", method = RequestMethod.POST)
	public @ResponseBody List<CLG_REG_INFRA_COLLEGE_COUNCIL_CAMERA> getCamera_Location_Details(HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL
				.createQuery("from CLG_REG_INFRA_COLLEGE_COUNCIL_CAMERA where institute_id=:institute_id ");
		
		q.setParameter("institute_id", Integer.parseInt(institute_id));
		
		@SuppressWarnings("unchecked")
		List<CLG_REG_INFRA_COLLEGE_COUNCIL_CAMERA> clist = (List<CLG_REG_INFRA_COLLEGE_COUNCIL_CAMERA>) q.list();
	
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	
	
	//ADD MORE DELETE FOR CAMERA LOCATION DETAILS
	@PostMapping(value = "/delete_Camera_Location_Details")
	public @ResponseBody String delete_Camera_Location_Details(String hid_camera,HttpSession session1) {

		String msg="";

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		System.err.println("hid_camera===================="+hid_camera);
		try {
			String hqlUpdate = "delete from CLG_REG_INFRA_COLLEGE_COUNCIL_CAMERA where id=:id";
			int app = session.createQuery(hqlUpdate).setInteger("id",Integer.parseInt(hid_camera)).executeUpdate();
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
}
