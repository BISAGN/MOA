package com.AyushEdu.controller.Part_one;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_ADD_STAFF_GUEST_FACULTY;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_ADD_STAFF_NON_TEACHING_STAFF;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_ADD_STAFF_TEACHING_FACULTY;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Part_One.Clg_Reg_Add_Staff_DAO;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Infrastructure_DAO;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Clg_Reg_Add_Staff_Controller {

	@Autowired
	ValidationController validation;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	CommonController common = new CommonController();

	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	Commondao commondao;
	
	@Autowired
	private Clg_Reg_College_Infrastructure_DAO CIDao;
	
	@Autowired
	private Clg_Reg_Add_Staff_DAO ASDao;
	
	@Autowired
	Institution_Basic_Details_Dao ibdao;
	
	
	@RequestMapping(value = "admin/add_staff", method = RequestMethod.GET)
	public ModelAndView add_staff(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String userid = session.getAttribute("userId_for_jnlp").toString();
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institute = String.valueOf(ea.getInstitute_id());
		String role = session.getAttribute("role").toString();
		String username = session.getAttribute("roleloginName").toString();
		System.err.println("-----1 APR-------"+username);
		if(role=="Institute_NCH") {
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int parent_id = (int) sessionHQL
				.createQuery("select id from CLG_REG_ADD_STAFF_TEACHING_FACULTY where institute_id=:inst_id")
				.setParameter("inst_id", Integer.parseInt(institute))
				.uniqueResult();
		Mmap.put("parent_id", parent_id);
		}
		
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
		
		Mmap.put("institude", institute);
		Mmap.put("getPrefixList", common.getPrefixList(sessionFactory));
		Mmap.put("getNature_of_Appoinment", common.getNature_of_Appoinment(sessionFactory));
		Mmap.put("login_name", session.getAttribute("roleloginName").toString());
		Mmap.put("dataforinstnc", ibdao.getinstName_Code(Integer.parseInt(institute)));
		return new ModelAndView("add_staff");
	}

	
	//ADD MORE SAVE FOR TEACHING FACULTY DETAILS
	@PostMapping(value = "/Teaching_Faculty_Details_Save_Draft_Action")
	public @ResponseBody String Teaching_Faculty_Details_Save_Draft_Action( 
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
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String s_id = session.getAttribute("super_id").toString();
		
		String indno = request.getParameter("indno_teaching_faculty");
		String prefix_id = request.getParameter("prefix_id"+indno);
		String first_name = request.getParameter("first_name"+indno);
		String middle_name = request.getParameter("middle_name"+indno);
		String last_name = request.getParameter("last_name"+indno);
		String date_of_appoinment = request.getParameter("date_of_appoinment"+indno);
		String nature_of_appoinment = request.getParameter("nature_of_appoinment"+indno);
		String emp_id = request.getParameter("emp_id"+indno);
		String emp_department = request.getParameter("emp_department"+indno);
		String emp_qualification = request.getParameter("emp_qualification"+indno);
		String emp_pay_scale = request.getParameter("emp_pay_scale"+indno);
		String emp_designation = request.getParameter("emp_designation"+indno);
		String pan_no = request.getParameter("pan_no"+indno);
		String aadhar_no = request.getParameter("aadhar_no"+indno);
		String reg_authority = request.getParameter("reg_authority"+indno);
		String reg_type = request.getParameter("reg_type"+indno);
		String reg_no = request.getParameter("reg_no"+indno);
		String ug_check_name = request.getParameter("ug_check_name"+indno);
		
		String hid_teaching_attachment = request.getParameter("hid_teaching_attachment"+indno);
		MultipartFile teaching_attachment = mul.getFile("teaching_attachment"+indno);
		String teaching_faculty_attachment = "";
		if (!teaching_attachment.isEmpty()) {
			teaching_faculty_attachment = upload_imagemethod(request,teaching_attachment, session, hid_teaching_attachment);
		}else {
			teaching_faculty_attachment = request.getParameter("hid_teaching_attachment"+indno);
		}
		
		
		String hid_teaching_faculty = request.getParameter("hid_teaching_faculty"+indno);
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		if (prefix_id.equals("0")) {
			ra.addAttribute("msg", "Please Enter Your Prefix");
			return "Please Enter Your Prefix";
		}
		if (first_name == null || first_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter First Name");
			return "Please Enter First Name";
		}
		if (validation.isOnlyAlphabet(first_name) == false) {
			ra.addAttribute("msg","First Name" +validation.isOnlyAlphabetMSG);
			return "First Name " +validation.isOnlyAlphabetMSG;
		}
		if (validation.maxlengthcheck100(first_name) == false) {
			ra.addAttribute("msg", "First Name" + validation.MaxlengthcheckMSG100);
			return "First Name " +validation.MaxlengthcheckMSG100;
		}
		if (middle_name == null || middle_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Middle Name");
			return "Please Enter Middle Name";
		}
		if (validation.isOnlyAlphabet(middle_name) == false) {
			ra.addAttribute("msg","Middle Name" +validation.isOnlyAlphabetMSG);
			return "Middle Name " +validation.isOnlyAlphabetMSG;
		}
		if (validation.maxlengthcheck100(middle_name) == false) {
			ra.addAttribute("msg", "Middle Name" + validation.MaxlengthcheckMSG100);
			return "Middle Name " +validation.MaxlengthcheckMSG100;
		}
		if (last_name == null || last_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Last Name");
			return "Please Enter Last Name";
		}
		if (validation.isOnlyAlphabet(last_name) == false) {
			ra.addAttribute("msg","Last Name" +validation.isOnlyAlphabetMSG);
			return "Last Name " +validation.isOnlyAlphabetMSG;
		}
		if (validation.maxlengthcheck100(last_name) == false) {
			ra.addAttribute("msg", "Last Name" + validation.MaxlengthcheckMSG100);
			return "Last Name " +validation.MaxlengthcheckMSG100;
		}
		if (date_of_appoinment == null || date_of_appoinment.trim().equals("") || date_of_appoinment.equals("DD/MM/YYYY")) {
			ra.addAttribute("msg", "Please Enter Date of Appoinment");
			return "Please Enter Date of Appoinment";
		}
		if (validation.isOnlyDateFormat(date_of_appoinment) == false) {
			ra.addAttribute("msg", "Date of Appoinment " + validation.isOnlyDateFormatMSG);
			return "" +validation.isOnlyDateFormatMSG;
		}
		if (nature_of_appoinment.equals("0")) {
			ra.addAttribute("msg", "Please Enter Natute of Appoinment");
			return "Please Enter Natute of Appoinment";
		}
		if (emp_id == null || emp_id.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Employee ID");
			return "Please Enter Employee ID";
		}
		if (validation.isOnlyAlphabetNumber(emp_id) == false) {
			ra.addAttribute("msg","Employee ID" +validation.isOnlyAlphabetNumberMSG);
			return "Employee ID " +validation.isOnlyAlphabetNumberMSG;
		}
		if (emp_department == null || emp_department.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Employee Department");
			return "Please Enter Employee Department";
		}
		if (validation.isOnlyAlphabet(emp_department) == false) {
			ra.addAttribute("msg","Employee Department" +validation.isOnlyAlphabetMSG);
			return "Employee Department " +validation.isOnlyAlphabetMSG;
		}
		if (validation.maxlengthcheck100(emp_department) == false) {
			ra.addAttribute("msg", "Employee Department" + validation.MaxlengthcheckMSG100);
			return "Employee Department " +validation.MaxlengthcheckMSG100;
		}
		if (emp_qualification == null || emp_qualification.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Employee Qualification");
			return "Please Enter Employee Qualification";
		}
		if (validation.isOnlyAlphabet(emp_qualification) == false) {
			ra.addAttribute("msg","Employee Qualification" +validation.isOnlyAlphabetMSG);
			return "Employee Qualification " +validation.isOnlyAlphabetMSG;
		}
		if (validation.maxlengthcheck100(emp_qualification) == false) {
			ra.addAttribute("msg", "Employee Qualification" + validation.MaxlengthcheckMSG100);
			return "Employee Qualification " +validation.MaxlengthcheckMSG100;
		}
		if (emp_pay_scale == null || emp_pay_scale.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Employee Pay Scale");
			return "Please Enter Employee Pay Scale";
		}
		if (validation.isOnlyNumber(emp_pay_scale) == false) {
			ra.addAttribute("msg","Employee Pay Scale" +validation.isOnlyNumberMSG);
			return "Employee Pay Scale " +validation.isOnlyNumberMSG;
		}
		if (validation.maxlength10Digit(emp_pay_scale) == false) {
			ra.addAttribute("msg", "Employee Pay Scale" + validation.MaxlengthMSG10Digit);
			return "Employee Pay Scale " +validation.MaxlengthMSG10Digit;
		}
		if (emp_designation == null || emp_designation.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Employee Designation");
			return "Please Enter Employee Designation";
		}
		if (validation.isOnlyAlphabet(emp_designation) == false) {
			ra.addAttribute("msg","Employee Designation" +validation.isOnlyAlphabetMSG);
			return "Employee Designation " +validation.isOnlyAlphabetMSG;
		}
		if (validation.maxlengthcheck100(emp_designation) == false) {
			ra.addAttribute("msg", "Employee Designation" + validation.MaxlengthcheckMSG100);
			return "Employee Designation " +validation.MaxlengthcheckMSG100;
		}
		if (pan_no == null || pan_no.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter PAN Number");
			return "Please Enter PAN Number";
		}
		if (validation.isOnlyAlphabetNumber(pan_no) == false) {
			ra.addAttribute("msg","PAN Number" +validation.isOnlyAlphabetNumberMSG);
			return "PAN Number " +validation.isOnlyAlphabetNumberMSG;
		}
		if (aadhar_no == null || aadhar_no.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Aadhar Number");
			return "Please Enter Aadhar Number";
		}
		if (validation.isOnlyNumber(aadhar_no) == false) {
			ra.addAttribute("msg","Aadhar Number" +validation.isOnlyNumberMSG);
			return "Aadhar Number " +validation.isOnlyNumberMSG;
		}
//		if (validation.AadhaarNoLength_12(aadhar_no) == false) {
//			ra.addAttribute("msg","Aadhar Number" +validation.AadhaarNoMSG);
//			return "Aadhar Number " +validation.AadhaarNoMSG;
//		}
		if (reg_authority.equals("0")) {
			ra.addAttribute("msg", "Please Select Registration Authority");
			return "Please Select Registration Authority";
		}
		if (reg_type.equals("0")) {
			ra.addAttribute("msg", "Please Select Registration Type");
			return "Please Select Registration Type";
		}
		if (reg_no == null || reg_no.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Registration Number");
			return "Please Enter Registration Number";
		}
		if (validation.isOnlyAlphabetNumber(reg_no) == false) {
			ra.addAttribute("msg","Registration Number" +validation.isOnlyAlphabetNumberMSG);
			return "Registration Number " +validation.isOnlyAlphabetNumberMSG;
		}
		

		try {
			CLG_REG_ADD_STAFF_TEACHING_FACULTY teaching_faculty_detail =new CLG_REG_ADD_STAFF_TEACHING_FACULTY();
			
			teaching_faculty_detail.setPrefix_id(Integer.parseInt(prefix_id));
			teaching_faculty_detail.setFirst_name(first_name);
			teaching_faculty_detail.setMiddle_name(middle_name);
			teaching_faculty_detail.setLast_name(last_name);
			teaching_faculty_detail.setDate_of_appoinment(datePickerFormat.parse(date_of_appoinment));
			teaching_faculty_detail.setNature_of_appoinment(nature_of_appoinment);
			teaching_faculty_detail.setEmp_id(emp_id);
			teaching_faculty_detail.setEmp_department(emp_department);
			teaching_faculty_detail.setEmp_qualification(emp_qualification);
			teaching_faculty_detail.setEmp_pay_scale(emp_pay_scale);
			teaching_faculty_detail.setEmp_designation(emp_designation);
			teaching_faculty_detail.setPan_no(pan_no);
			teaching_faculty_detail.setAadhar_no(aadhar_no);
			teaching_faculty_detail.setUgpgcheck(ug_check_name);
			teaching_faculty_detail.setAttachment(teaching_faculty_attachment);
			teaching_faculty_detail.setReg_authority(reg_authority);
			teaching_faculty_detail.setReg_type(reg_type);
			teaching_faculty_detail.setReg_no(reg_no);
			teaching_faculty_detail.setInstitute_id(Integer.parseInt(institute_id));
			teaching_faculty_detail.setS_id(Integer.parseInt(s_id));
			teaching_faculty_detail.setCreated_by(Integer.parseInt(userid));
			teaching_faculty_detail.setCreated_date(date);
				if (Integer.parseInt(hid_teaching_faculty) == 0) {
					int hid_teaching_faculty1= (Integer) sessionHQL.save(teaching_faculty_detail);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					return String.valueOf(hid_teaching_faculty1) ;
			}
				else {

					CLG_REG_ADD_STAFF_TEACHING_FACULTY teaching_faculty_detail_u = (CLG_REG_ADD_STAFF_TEACHING_FACULTY) sessionHQL
							.get(CLG_REG_ADD_STAFF_TEACHING_FACULTY.class, Integer.parseInt(hid_teaching_faculty));
					
					teaching_faculty_detail_u.setPrefix_id(Integer.parseInt(prefix_id));
					teaching_faculty_detail_u.setFirst_name(first_name);
					teaching_faculty_detail_u.setMiddle_name(middle_name);
					teaching_faculty_detail_u.setLast_name(last_name);
					teaching_faculty_detail_u.setDate_of_appoinment(datePickerFormat.parse(date_of_appoinment));
					teaching_faculty_detail_u.setNature_of_appoinment(nature_of_appoinment);
					teaching_faculty_detail_u.setEmp_id(emp_id);
					teaching_faculty_detail_u.setEmp_department(emp_department);
					teaching_faculty_detail_u.setEmp_qualification(emp_qualification);
					teaching_faculty_detail_u.setEmp_pay_scale(emp_pay_scale);
					teaching_faculty_detail_u.setEmp_designation(emp_designation);
					teaching_faculty_detail_u.setPan_no(pan_no);
					teaching_faculty_detail_u.setAadhar_no(aadhar_no);
					teaching_faculty_detail_u.setUgpgcheck(ug_check_name);
					teaching_faculty_detail_u.setAttachment(teaching_faculty_attachment);
					teaching_faculty_detail_u.setReg_authority(reg_authority);
					teaching_faculty_detail_u.setReg_type(reg_type);
					teaching_faculty_detail_u.setReg_no(reg_no);
					teaching_faculty_detail_u.setInstitute_id(Integer.parseInt(institute_id));
					teaching_faculty_detail_u.setS_id(Integer.parseInt(s_id));
					teaching_faculty_detail_u.setModified_by(Integer.parseInt(userid));
					teaching_faculty_detail_u.setModified_date(date);
					sessionHQL.update(teaching_faculty_detail_u);
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
	
	
	//FETCH TEACHING FACULTY DETAILS
	@RequestMapping(value = "admin/getTeaching_Faculty_Details", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getTeaching_Faculty_Details(HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		return ASDao.getTeaching_Faculty_Details(Integer.parseInt(institute_id));
	}
	
	//ADD MORE DELETE FOR TEACHING FACULTY  DETAILS
	@PostMapping(value = "/delete_Teaching_Faculty_Details")
	public @ResponseBody String delete_Teaching_Faculty_Details(String hid_teaching_faculty,HttpSession session1) {

		String msg="";

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		System.err.println("hid_teaching_faculty===================="+hid_teaching_faculty);
		try {
			String hqlUpdate = "delete from CLG_REG_ADD_STAFF_TEACHING_FACULTY where id=:id";
			int app = session.createQuery(hqlUpdate).setInteger("id",Integer.parseInt(hid_teaching_faculty)).executeUpdate();
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
	
	
	//ADD MORE SAVE FOR GUEST FACULTY DETAILS
	@PostMapping(value = "/Guest_Faculty_Details_Save_Draft_Action")
	public @ResponseBody String Guest_Faculty_Details_Save_Draft_Action( 
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
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String s_id = session.getAttribute("super_id").toString();
		
		String indno = request.getParameter("indno_guest_faculty");
		String prefix_id = request.getParameter("guest_prefix_id"+indno);
		String first_name = request.getParameter("guest_first_name"+indno);
		String middle_name = request.getParameter("guest_middle_name"+indno);
		String last_name = request.getParameter("guest_last_name"+indno);
		String date_of_appoinment = request.getParameter("guest_date_of_appoinment"+indno);
		String nature_of_appoinment = request.getParameter("guest_nature_of_appoinment"+indno);
		String emp_id = request.getParameter("guest_emp_id"+indno);
		String emp_department = request.getParameter("guest_emp_department"+indno);
		String emp_qualification = request.getParameter("guest_emp_qualification"+indno);
		String emp_pay_scale = request.getParameter("guest_emp_pay_scale"+indno);
		String emp_designation = request.getParameter("guest_emp_designation"+indno);
		String pan_no = request.getParameter("guest_pan_no"+indno);
		String aadhar_no = request.getParameter("guest_aadhar_no"+indno);
		
		String hid_appoinment_letter = request.getParameter("hid_appoinment_letter"+indno);
		MultipartFile appoinment_letter = mul.getFile("appoinment_letter"+indno);
		String appoinment_attachment = "";
		if (!appoinment_letter.isEmpty()) {
			appoinment_attachment = upload_imagemethod(request,appoinment_letter, session, hid_appoinment_letter);
		}else {
			appoinment_attachment = request.getParameter("hid_appoinment_letter"+indno);
		}
		
		String hid_exe_certi = request.getParameter("hid_exe_certi"+indno);
		MultipartFile exe_certi = mul.getFile("exe_certi"+indno);
		String exe_attachment = "";
		if (!exe_certi.isEmpty()) {
			exe_attachment = upload_imagemethod(request,exe_certi, session, hid_exe_certi);
		}else {
			exe_attachment = request.getParameter("hid_exe_certi"+indno);
		}
		
		String hid_guest_teaching_attachment = request.getParameter("hid_guest_teaching_attachment"+indno);
		MultipartFile guest_teaching_attachment = mul.getFile("guest_teaching_attachment"+indno);
		String guest_teaching_faculty_attachment = "";
		if (!guest_teaching_attachment.isEmpty()) {
			guest_teaching_faculty_attachment = upload_imagemethod(request,guest_teaching_attachment, session, hid_guest_teaching_attachment);
		}else {
			guest_teaching_faculty_attachment = request.getParameter("hid_guest_teaching_attachment"+indno);
		}
		
		String hid_guest_faculty = request.getParameter("hid_guest_faculty"+indno);
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		
		if (prefix_id.equals("0")) {
			ra.addAttribute("msg", "Please Enter Your Prefix");
			return "Please Enter Your Prefix";
		}
		if (first_name == null || first_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter First Name");
			return "Please Enter First Name";
		}
		if (validation.isOnlyAlphabet(first_name) == false) {
			ra.addAttribute("msg","First Name" +validation.isOnlyAlphabetMSG);
			return "First Name " +validation.isOnlyAlphabetMSG;
		}
		if (validation.maxlengthcheck100(first_name) == false) {
			ra.addAttribute("msg", "First Name" + validation.MaxlengthcheckMSG100);
			return "First Name " +validation.MaxlengthcheckMSG100;
		}
		if (middle_name == null || middle_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Middle Name");
			return "Please Enter Middle Name";
		}
		if (validation.isOnlyAlphabet(middle_name) == false) {
			ra.addAttribute("msg","Middle Name" +validation.isOnlyAlphabetMSG);
			return "Middle Name " +validation.isOnlyAlphabetMSG;
		}
		if (validation.maxlengthcheck100(middle_name) == false) {
			ra.addAttribute("msg", "Middle Name" + validation.MaxlengthcheckMSG100);
			return "Middle Name " +validation.MaxlengthcheckMSG100;
		}
		if (last_name == null || last_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Last Name");
			return "Please Enter Last Name";
		}
		if (validation.isOnlyAlphabet(last_name) == false) {
			ra.addAttribute("msg","Last Name" +validation.isOnlyAlphabetMSG);
			return "Last Name " +validation.isOnlyAlphabetMSG;
		}
		if (validation.maxlengthcheck100(last_name) == false) {
			ra.addAttribute("msg", "Last Name" + validation.MaxlengthcheckMSG100);
			return "Last Name " +validation.MaxlengthcheckMSG100;
		}
		if (date_of_appoinment == null || date_of_appoinment.trim().equals("") || date_of_appoinment.equals("DD/MM/YYYY")) {
			ra.addAttribute("msg", "Please Enter Date of Appoinment");
			return "Please Enter Date of Appoinment";
		}
		if (validation.isOnlyDateFormat(date_of_appoinment) == false) {
			ra.addAttribute("msg", "Date of Appoinment " + validation.isOnlyDateFormatMSG);
			return "" +validation.isOnlyDateFormatMSG;
		}
		if (nature_of_appoinment.equals("0")) {
			ra.addAttribute("msg", "Please Enter Natute of Appoinment");
			return "Please Enter Natute of Appoinment";
		}
		if (emp_id == null || emp_id.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Employee ID");
			return "Please Enter Employee ID";
		}
		if (validation.isOnlyAlphabetNumber(emp_id) == false) {
			ra.addAttribute("msg","Employee ID" +validation.isOnlyAlphabetNumberMSG);
			return "Employee ID " +validation.isOnlyAlphabetNumberMSG;
		}
		if (emp_department == null || emp_department.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Employee Department");
			return "Please Enter Employee Department";
		}
		if (validation.isOnlyAlphabet(emp_department) == false) {
			ra.addAttribute("msg","Employee Department" +validation.isOnlyAlphabetMSG);
			return "Employee Department " +validation.isOnlyAlphabetMSG;
		}
		if (validation.maxlengthcheck100(emp_department) == false) {
			ra.addAttribute("msg", "Employee Department" + validation.MaxlengthcheckMSG100);
			return "Employee Department " +validation.MaxlengthcheckMSG100;
		}
		if (emp_qualification == null || emp_qualification.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Employee Qualification");
			return "Please Enter Employee Qualification";
		}
		if (validation.isOnlyAlphabet(emp_qualification) == false) {
			ra.addAttribute("msg","Employee Qualification" +validation.isOnlyAlphabetMSG);
			return "Employee Qualification " +validation.isOnlyAlphabetMSG;
		}
		if (validation.maxlengthcheck100(emp_qualification) == false) {
			ra.addAttribute("msg", "Employee Qualification" + validation.MaxlengthcheckMSG100);
			return "Employee Qualification " +validation.MaxlengthcheckMSG100;
		}
		if (emp_pay_scale == null || emp_pay_scale.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Employee Pay Scale");
			return "Please Enter Employee Pay Scale";
		}
		if (validation.isOnlyNumber(emp_pay_scale) == false) {
			ra.addAttribute("msg","Employee Pay Scale" +validation.isOnlyNumberMSG);
			return "Employee Pay Scale " +validation.isOnlyNumberMSG;
		}
		if (validation.maxlength10Digit(emp_pay_scale) == false) {
			ra.addAttribute("msg", "Employee Pay Scale" + validation.MaxlengthMSG10Digit);
			return "Employee Pay Scale " +validation.MaxlengthMSG10Digit;
		}
		if (emp_designation == null || emp_designation.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Employee Designation");
			return "Please Enter Employee Designation";
		}
		if (validation.isOnlyAlphabet(emp_designation) == false) {
			ra.addAttribute("msg","Employee Designation" +validation.isOnlyAlphabetMSG);
			return "Employee Designation " +validation.isOnlyAlphabetMSG;
		}
		if (validation.maxlengthcheck100(emp_designation) == false) {
			ra.addAttribute("msg", "Employee Designation" + validation.MaxlengthcheckMSG100);
			return "Employee Designation " +validation.MaxlengthcheckMSG100;
		}
		if (pan_no == null || pan_no.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter PAN Number");
			return "Please Enter PAN Number";
		}
		if (validation.isOnlyAlphabetNumber(pan_no) == false) {
			ra.addAttribute("msg","PAN Number" +validation.isOnlyAlphabetNumberMSG);
			return "PAN Number " +validation.isOnlyAlphabetNumberMSG;
		}
		if (aadhar_no == null || aadhar_no.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Aadhar Number");
			return "Please Enter Aadhar Number";
		}
		if (validation.isOnlyNumber(aadhar_no) == false) {
			ra.addAttribute("msg","Aadhar Number" +validation.isOnlyNumberMSG);
			return "Aadhar Number " +validation.isOnlyNumberMSG;
		}
//		if (validation.AadhaarNoLength_12(aadhar_no) == false) {
//			ra.addAttribute("msg","Aadhar Number" +validation.AadhaarNoMSG);
//			return "Aadhar Number " +validation.AadhaarNoMSG;
//		}
		

		try {
			CLG_REG_ADD_STAFF_GUEST_FACULTY guest_faculty_detail =new CLG_REG_ADD_STAFF_GUEST_FACULTY();
			
			guest_faculty_detail.setGuest_prefix_id(Integer.parseInt(prefix_id));
			guest_faculty_detail.setGuest_first_name(first_name);
			guest_faculty_detail.setGuest_middle_name(middle_name);
			guest_faculty_detail.setGuest_last_name(last_name);
			guest_faculty_detail.setGuest_date_of_appoinment(datePickerFormat.parse(date_of_appoinment));
			guest_faculty_detail.setGuest_nature_of_appoinment(nature_of_appoinment);
			guest_faculty_detail.setGuest_emp_id(emp_id);
			guest_faculty_detail.setGuest_emp_department(emp_department);
			guest_faculty_detail.setGuest_emp_qualification(emp_qualification);
			guest_faculty_detail.setGuest_emp_pay_scale(emp_pay_scale);
			guest_faculty_detail.setGuest_emp_designation(emp_designation);
			guest_faculty_detail.setGuest_pan_no(pan_no);
			guest_faculty_detail.setGuest_aadhar_no(aadhar_no);
			guest_faculty_detail.setAppoinment_letter(appoinment_attachment);
			guest_faculty_detail.setExe_certi(exe_attachment);
			guest_faculty_detail.setGuest_teaching_attachment(guest_teaching_faculty_attachment);
			guest_faculty_detail.setInstitute_id(Integer.parseInt(institute_id));
			guest_faculty_detail.setS_id(Integer.parseInt(s_id));
			guest_faculty_detail.setCreated_by(Integer.parseInt(userid));
			guest_faculty_detail.setCreated_date(date);
				if (Integer.parseInt(hid_guest_faculty) == 0) {
					int hid_guest_faculty1= (Integer) sessionHQL.save(guest_faculty_detail);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					return String.valueOf(hid_guest_faculty1) ;
			}
				else {

					CLG_REG_ADD_STAFF_GUEST_FACULTY guest_faculty_detail_u = (CLG_REG_ADD_STAFF_GUEST_FACULTY) sessionHQL
							.get(CLG_REG_ADD_STAFF_GUEST_FACULTY.class, Integer.parseInt(hid_guest_faculty));
					
					guest_faculty_detail_u.setGuest_prefix_id(Integer.parseInt(prefix_id));
					guest_faculty_detail_u.setGuest_first_name(first_name);
					guest_faculty_detail_u.setGuest_middle_name(middle_name);
					guest_faculty_detail_u.setGuest_last_name(last_name);
					guest_faculty_detail_u.setGuest_date_of_appoinment(datePickerFormat.parse(date_of_appoinment));
					guest_faculty_detail_u.setGuest_nature_of_appoinment(nature_of_appoinment);
					guest_faculty_detail_u.setGuest_emp_id(emp_id);
					guest_faculty_detail_u.setGuest_emp_department(emp_department);
					guest_faculty_detail_u.setGuest_emp_qualification(emp_qualification);
					guest_faculty_detail_u.setGuest_emp_pay_scale(emp_pay_scale);
					guest_faculty_detail_u.setGuest_emp_designation(emp_designation);
					guest_faculty_detail_u.setGuest_pan_no(pan_no);
					guest_faculty_detail_u.setGuest_aadhar_no(aadhar_no);
					guest_faculty_detail_u.setAppoinment_letter(appoinment_attachment);
					guest_faculty_detail_u.setExe_certi(exe_attachment);
					guest_faculty_detail_u.setGuest_teaching_attachment(guest_teaching_faculty_attachment);
					guest_faculty_detail_u.setInstitute_id(Integer.parseInt(institute_id));
					guest_faculty_detail_u.setS_id(Integer.parseInt(s_id));
					guest_faculty_detail_u.setModified_by(Integer.parseInt(userid));
					guest_faculty_detail_u.setModified_date(date);
					sessionHQL.update(guest_faculty_detail_u);
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
	
	
	//FETCH GUEST FACULTY DETAILS
	@RequestMapping(value = "admin/getGuest_Faculty_Details", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getGuest_Faculty_Details(HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		return ASDao.getGuest_Faculty_Details(Integer.parseInt(institute_id));
	}
	
	//ADD MORE DELETE FOR GUEST FACULTY  DETAILS
	@PostMapping(value = "/delete_Guest_Faculty_Details")
	public @ResponseBody String delete_Guest_Faculty_Details(String hid_guest_faculty,HttpSession session1) {

		String msg="";

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		System.err.println("hid_guest_faculty===================="+hid_guest_faculty);
		try {
			String hqlUpdate = "delete from CLG_REG_ADD_STAFF_GUEST_FACULTY where id=:id";
			int app = session.createQuery(hqlUpdate).setInteger("id",Integer.parseInt(hid_guest_faculty)).executeUpdate();
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
	
	
	
	//ADD MORE SAVE FOR NON TEACHING STAFF DETAILS
	@PostMapping(value = "/Non_Teaching_Staff_Details_Save_Draft_Action")
	public @ResponseBody String Non_Teaching_Staff_Details_Save_Draft_Action( 
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
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String s_id = session.getAttribute("super_id").toString();
		
		String indno = request.getParameter("indno_non_teaching_staff");
		String prefix_id = request.getParameter("non_prefix_id"+indno);
		String first_name = request.getParameter("non_first_name"+indno);
		String middle_name = request.getParameter("non_middle_name"+indno);
		String last_name = request.getParameter("non_last_name"+indno);
		String date_of_appoinment = request.getParameter("non_date_of_appoinment"+indno);
		String nature_of_appoinment = request.getParameter("non_nature_of_appoinment"+indno);
		String emp_id = request.getParameter("non_emp_id"+indno);
		String emp_department = request.getParameter("non_emp_department"+indno);
		String emp_qualification = request.getParameter("non_emp_qualification"+indno);
		String emp_pay_scale = request.getParameter("non_emp_pay_scale"+indno);
		String emp_designation = request.getParameter("non_emp_designation"+indno);
		String pan_no = request.getParameter("non_pan_no"+indno);
		String aadhar_no = request.getParameter("non_aadhar_no"+indno);
		
		String hid_non_appoinment_letter = request.getParameter("hid_non_appoinment_letter"+indno);
		MultipartFile non_appoinment_letter = mul.getFile("non_appoinment_letter"+indno);
		String non_appoinment_attachment = "";
		if (!non_appoinment_letter.isEmpty()) {
			non_appoinment_attachment = upload_imagemethod(request,non_appoinment_letter, session, hid_non_appoinment_letter);
		}else {
			non_appoinment_attachment = request.getParameter("hid_non_appoinment_letter"+indno);
		}
		
		String hid_non_exe_certi = request.getParameter("hid_non_exe_certi"+indno);
		MultipartFile non_exe_certi = mul.getFile("non_exe_certi"+indno);
		String non_exe_attachment = "";
		if (!non_exe_certi.isEmpty()) {
			non_exe_attachment = upload_imagemethod(request,non_exe_certi, session, hid_non_exe_certi);
		}else {
			non_exe_attachment = request.getParameter("hid_non_exe_certi"+indno);
		}
		
		String hid_non_teaching_attachment = request.getParameter("hid_non_teaching_attachment"+indno);
		MultipartFile non_teaching_attachment = mul.getFile("non_teaching_attachment"+indno);
		String non_teaching_faculty_attachment = "";
		if (!non_teaching_attachment.isEmpty()) {
			non_teaching_faculty_attachment = upload_imagemethod(request,non_teaching_attachment, session, hid_non_teaching_attachment);
		}else {
			non_teaching_faculty_attachment = request.getParameter("hid_non_teaching_attachment"+indno);
		}
		
		String hid_non_teaching_staff = request.getParameter("hid_non_teaching_staff"+indno);
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		if (prefix_id.equals("0")) {
			ra.addAttribute("msg", "Please Enter Your Prefix");
			return "Please Enter Your Prefix";
		}
		if (first_name == null || first_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter First Name");
			return "Please Enter First Name";
		}
		if (validation.isOnlyAlphabet(first_name) == false) {
			ra.addAttribute("msg","First Name" +validation.isOnlyAlphabetMSG);
			return "First Name " +validation.isOnlyAlphabetMSG;
		}
		if (validation.maxlengthcheck100(first_name) == false) {
			ra.addAttribute("msg", "First Name" + validation.MaxlengthcheckMSG100);
			return "First Name " +validation.MaxlengthcheckMSG100;
		}
		if (middle_name == null || middle_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Middle Name");
			return "Please Enter Middle Name";
		}
		if (validation.isOnlyAlphabet(middle_name) == false) {
			ra.addAttribute("msg","Middle Name" +validation.isOnlyAlphabetMSG);
			return "Middle Name " +validation.isOnlyAlphabetMSG;
		}
		if (validation.maxlengthcheck100(middle_name) == false) {
			ra.addAttribute("msg", "Middle Name" + validation.MaxlengthcheckMSG100);
			return "Middle Name " +validation.MaxlengthcheckMSG100;
		}
		if (last_name == null || last_name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Last Name");
			return "Please Enter Last Name";
		}
		if (validation.isOnlyAlphabet(last_name) == false) {
			ra.addAttribute("msg","Last Name" +validation.isOnlyAlphabetMSG);
			return "Last Name " +validation.isOnlyAlphabetMSG;
		}
		if (validation.maxlengthcheck100(last_name) == false) {
			ra.addAttribute("msg", "Last Name" + validation.MaxlengthcheckMSG100);
			return "Last Name " +validation.MaxlengthcheckMSG100;
		}
		if (date_of_appoinment == null || date_of_appoinment.trim().equals("") || date_of_appoinment.equals("DD/MM/YYYY")) {
			ra.addAttribute("msg", "Please Enter Date of Appoinment");
			return "Please Enter Date of Appoinment";
		}
		if (validation.isOnlyDateFormat(date_of_appoinment) == false) {
			ra.addAttribute("msg", "Date of Appoinment " + validation.isOnlyDateFormatMSG);
			return "" +validation.isOnlyDateFormatMSG;
		}
		if (nature_of_appoinment.equals("0")) {
			ra.addAttribute("msg", "Please Enter Natute of Appoinment");
			return "Please Enter Natute of Appoinment";
		}
		if (emp_id == null || emp_id.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Employee ID");
			return "Please Enter Employee ID";
		}
		if (validation.isOnlyAlphabetNumber(emp_id) == false) {
			ra.addAttribute("msg","Employee ID" +validation.isOnlyAlphabetNumberMSG);
			return "Employee ID " +validation.isOnlyAlphabetNumberMSG;
		}
		if (emp_department == null || emp_department.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Employee Department");
			return "Please Enter Employee Department";
		}
		if (validation.isOnlyAlphabet(emp_department) == false) {
			ra.addAttribute("msg","Employee Department" +validation.isOnlyAlphabetMSG);
			return "Employee Department " +validation.isOnlyAlphabetMSG;
		}
		if (validation.maxlengthcheck100(emp_department) == false) {
			ra.addAttribute("msg", "Employee Department" + validation.MaxlengthcheckMSG100);
			return "Employee Department " +validation.MaxlengthcheckMSG100;
		}
		if (emp_qualification == null || emp_qualification.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Employee Qualification");
			return "Please Enter Employee Qualification";
		}
		if (validation.isOnlyAlphabet(emp_qualification) == false) {
			ra.addAttribute("msg","Employee Qualification" +validation.isOnlyAlphabetMSG);
			return "Employee Qualification " +validation.isOnlyAlphabetMSG;
		}
		if (validation.maxlengthcheck100(emp_qualification) == false) {
			ra.addAttribute("msg", "Employee Qualification" + validation.MaxlengthcheckMSG100);
			return "Employee Qualification " +validation.MaxlengthcheckMSG100;
		}
		if (emp_pay_scale == null || emp_pay_scale.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Employee Pay Scale");
			return "Please Enter Employee Pay Scale";
		}
		if (validation.isOnlyNumber(emp_pay_scale) == false) {
			ra.addAttribute("msg","Employee Pay Scale" +validation.isOnlyNumberMSG);
			return "Employee Pay Scale " +validation.isOnlyNumberMSG;
		}
		if (validation.maxlength10Digit(emp_pay_scale) == false) {
			ra.addAttribute("msg", "Employee Pay Scale" + validation.MaxlengthMSG10Digit);
			return "Employee Pay Scale " +validation.MaxlengthMSG10Digit;
		}
		if (emp_designation == null || emp_designation.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Employee Designation");
			return "Please Enter Employee Designation";
		}
		if (validation.isOnlyAlphabet(emp_designation) == false) {
			ra.addAttribute("msg","Employee Designation" +validation.isOnlyAlphabetMSG);
			return "Employee Designation " +validation.isOnlyAlphabetMSG;
		}
		if (validation.maxlengthcheck100(emp_designation) == false) {
			ra.addAttribute("msg", "Employee Designation" + validation.MaxlengthcheckMSG100);
			return "Employee Designation " +validation.MaxlengthcheckMSG100;
		}
		if (pan_no == null || pan_no.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter PAN Number");
			return "Please Enter PAN Number";
		}
		if (validation.isOnlyAlphabetNumber(pan_no) == false) {
			ra.addAttribute("msg","PAN Number" +validation.isOnlyAlphabetNumberMSG);
			return "PAN Number " +validation.isOnlyAlphabetNumberMSG;
		}
		if (aadhar_no == null || aadhar_no.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Aadhar Number");
			return "Please Enter Aadhar Number";
		}
		if (validation.isOnlyNumber(aadhar_no) == false) {
			ra.addAttribute("msg","Aadhar Number" +validation.isOnlyNumberMSG);
			return "Aadhar Number " +validation.isOnlyNumberMSG;
		}
//		if (validation.AadhaarNoLength_12(aadhar_no) == false) {
//			ra.addAttribute("msg","Aadhar Number" +validation.AadhaarNoMSG);
//			return "Aadhar Number " +validation.AadhaarNoMSG;
//		}
		

		try {
			CLG_REG_ADD_STAFF_NON_TEACHING_STAFF non_teaching_staff_detail =new CLG_REG_ADD_STAFF_NON_TEACHING_STAFF();
			
			non_teaching_staff_detail.setNon_prefix_id(Integer.parseInt(prefix_id));
			non_teaching_staff_detail.setNon_first_name(first_name);
			non_teaching_staff_detail.setNon_middle_name(middle_name);
			non_teaching_staff_detail.setNon_last_name(last_name);
			non_teaching_staff_detail.setNon_date_of_appoinment(datePickerFormat.parse(date_of_appoinment));
			non_teaching_staff_detail.setNon_nature_of_appoinment(nature_of_appoinment);
			non_teaching_staff_detail.setNon_emp_id(emp_id);
			non_teaching_staff_detail.setNon_emp_department(emp_department);
			non_teaching_staff_detail.setNon_emp_qualification(emp_qualification);
			non_teaching_staff_detail.setNon_emp_pay_scale(emp_pay_scale);
			non_teaching_staff_detail.setNon_emp_designation(emp_designation);
			non_teaching_staff_detail.setNon_pan_no(pan_no);
			non_teaching_staff_detail.setNon_aadhar_no(aadhar_no);
			non_teaching_staff_detail.setNon_appoinment_letter(non_appoinment_attachment);
			non_teaching_staff_detail.setNon_exe_certi(non_exe_attachment);
			non_teaching_staff_detail.setNon_teaching_attachment(non_teaching_faculty_attachment);
			non_teaching_staff_detail.setInstitute_id(Integer.parseInt(institute_id));
			non_teaching_staff_detail.setS_id(Integer.parseInt(s_id));
			non_teaching_staff_detail.setCreated_by(Integer.parseInt(userid));
			non_teaching_staff_detail.setCreated_date(date);
				if (Integer.parseInt(hid_non_teaching_staff) == 0) {
					int hid_non_teaching_staff1= (Integer) sessionHQL.save(non_teaching_staff_detail);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					return String.valueOf(hid_non_teaching_staff1) ;
			}
				else {

					CLG_REG_ADD_STAFF_NON_TEACHING_STAFF non_teaching_staff_detail_u = (CLG_REG_ADD_STAFF_NON_TEACHING_STAFF) sessionHQL
							.get(CLG_REG_ADD_STAFF_NON_TEACHING_STAFF.class, Integer.parseInt(hid_non_teaching_staff));
					
					non_teaching_staff_detail_u.setNon_prefix_id(Integer.parseInt(prefix_id));
					non_teaching_staff_detail_u.setNon_first_name(first_name);
					non_teaching_staff_detail_u.setNon_middle_name(middle_name);
					non_teaching_staff_detail_u.setNon_last_name(last_name);
					non_teaching_staff_detail_u.setNon_date_of_appoinment(datePickerFormat.parse(date_of_appoinment));
					non_teaching_staff_detail_u.setNon_nature_of_appoinment(nature_of_appoinment);
					non_teaching_staff_detail_u.setNon_emp_id(emp_id);
					non_teaching_staff_detail_u.setNon_emp_department(emp_department);
					non_teaching_staff_detail_u.setNon_emp_qualification(emp_qualification);
					non_teaching_staff_detail_u.setNon_emp_pay_scale(emp_pay_scale);
					non_teaching_staff_detail_u.setNon_emp_designation(emp_designation);
					non_teaching_staff_detail_u.setNon_pan_no(pan_no);
					non_teaching_staff_detail_u.setNon_aadhar_no(aadhar_no);
					non_teaching_staff_detail_u.setNon_appoinment_letter(non_appoinment_attachment);
					non_teaching_staff_detail_u.setNon_exe_certi(non_exe_attachment);
					non_teaching_staff_detail_u.setNon_teaching_attachment(non_teaching_faculty_attachment);
					non_teaching_staff_detail_u.setInstitute_id(Integer.parseInt(institute_id));
					non_teaching_staff_detail_u.setS_id(Integer.parseInt(s_id));
					non_teaching_staff_detail_u.setModified_by(Integer.parseInt(userid));
					non_teaching_staff_detail_u.setModified_date(date);
					sessionHQL.update(non_teaching_staff_detail_u);
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
	
	
	//FETCH NON TEACHING STAFF DETAILS
	@RequestMapping(value = "admin/getNon_Teaching_Staff_Details", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getNon_Teaching_Staff_Details(HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		return ASDao.getNon_Teaching_Staff_Details(Integer.parseInt(institute_id));
	}
	
	//ADD MORE DELETE FOR NON TEACHING STAFF DETAILS
	@PostMapping(value = "/delete_Non_Teaching_Staff_Details")
	public @ResponseBody String delete_Non_Teaching_Staff_Details(String hid_non_teaching_staff,HttpSession session1) {

		String msg="";

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		System.err.println("hid_guest_faculty===================="+hid_non_teaching_staff);
		try {
			String hqlUpdate = "delete from CLG_REG_ADD_STAFF_NON_TEACHING_STAFF where id=:id";
			int app = session.createQuery(hqlUpdate).setInteger("id",Integer.parseInt(hid_non_teaching_staff)).executeUpdate();
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
