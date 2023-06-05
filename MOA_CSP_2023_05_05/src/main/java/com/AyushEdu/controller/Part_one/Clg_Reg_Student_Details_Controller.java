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
import java.util.Iterator;
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
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_STUDENT_DTL_ADMITTED_STUDENT;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_STUDENT_DTL_PASS_STUDENT;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_STUDENT_DTL_UPLOAD_DOC;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Infrastructure_DAO;
import com.AyushEdu.dao.Part_One.Clg_Reg_Student_DetailsDao;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Clg_Reg_Student_Details_Controller {

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
	Clg_Reg_Student_DetailsDao sdDao;
	
	@Autowired
	Institution_Basic_Details_Dao ibdao;
	
	
	@RequestMapping(value = "admin/student_details", method = RequestMethod.GET)
	public ModelAndView college_infrastructure(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		String userid = session.getAttribute("userId_for_jnlp").toString();
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institude = String.valueOf(ea.getInstitute_id());
		String role = session.getAttribute("role").toString();
		
		if(role=="Institute_NCH") {
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int parent_id = (int) sessionHQL
				.createQuery("select id from CLG_REG_STUDENT_DTL_ADMITTED_STUDENT where institute_id=:inst_id")
				.setParameter("inst_id", Integer.parseInt(institude))
				.uniqueResult();
		Mmap.put("parent_id", parent_id);
		}
		
		
		if(ibdao.getpid_from_userid(userid).size()!=0) {
			if(ibdao.getpid_from_userid(userid).get(0).get(0)==null ||ibdao.getpid_from_userid(userid).get(0).get(0).equals("")) {
				Mmap.put("msg", "Please Save Basic details First");
				return new ModelAndView("redirect:basics_information");

			}else {
				
				request.getSession().setAttribute("super_id", ibdao.getpid_from_userid(userid).get(0).get(0));
			}
		}else {
			
			Mmap.put("msg", "Please Save Basic details First");
			return new ModelAndView("redirect:basics_information");
		}
		
		Mmap.put("institude", institude);
		
//		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
//		Date date = new Date();
//		String strDate = datePickerFormat.format(date); 
//		String year = strDate.substring(6, 10);
//		String month = strDate.substring(3, 5);
//		System.err.println("YEAR---------------"+year);
//		System.err.println("MONTH---------------"+month);
//		if(Integer.parseInt(month) > 02) {
//			int abc = Integer.sum(Integer.parseInt(year),1);
//			System.err.println("abcd------------"+abc);
//		}
//		for (int i = 0; i < 5; i++) {
//			int admitted_year = Math.subtractExact(Integer.parseInt(year), i);
//		}
		
		return new ModelAndView("student_details");
	}
	
	
	//SAVE ADMITTED STUDENT DETAILS
	@PostMapping(value = "/Admitted_Student_Details_Save_Draft_Action")
	public @ResponseBody String Admitted_Student_Details_Save_Draft_Action( 
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
		CLG_REG_STUDENT_DTL_ADMITTED_STUDENT pers_p =new CLG_REG_STUDENT_DTL_ADMITTED_STUDENT();
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String s_id = session.getAttribute("super_id").toString();
		
		String intimatedcheck = request.getParameter("intimatedcheck");
		String undertakingcheck = request.getParameter("undertakingcheck");
		String hid_admitted_details_check = request.getParameter("hid_admitted_details_check");
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date date = new Date();
		String strDate = datePickerFormat.format(date); 
		String year = strDate.substring(6, 10);
		System.err.println("STR DATE-------------"+strDate);
		System.err.println("YEAR-------------"+year);
		System.err.println("CURRENT DATE----------"+date);
		
		int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		
		try {
			
			
			Transaction tx1 = sessionHQL.beginTransaction();
			CLG_REG_STUDENT_DTL_ADMITTED_STUDENT admitted_student_detail =new CLG_REG_STUDENT_DTL_ADMITTED_STUDENT();
			
			
			for (int i = 0; i < 5; i++) {
				int admitted_year = Math.subtractExact(Integer.parseInt(year), i);
				System.err.println("FOR LOOP YEAR----------------"+admitted_year);
				String govt_quota_ug = request.getParameter("govt_quota_ug_"+admitted_year);
				String mang_quota_ug = request.getParameter("mang_quota_ug_"+admitted_year);
				String govt_quota_pg = request.getParameter("govt_quota_pg_"+admitted_year);
				String mang_quota_pg = request.getParameter("mang_quota_pg_"+admitted_year);
				String court_order = request.getParameter("court_order_"+admitted_year);
				String last_student = request.getParameter("last_student_"+admitted_year);
				String last_stu_add_date = request.getParameter("last_stu_add_date_"+admitted_year);
				String hid_admitted_student = request.getParameter("hid_admitted_student_"+admitted_year);
				
				if (govt_quota_ug == null || govt_quota_ug.trim().equals("")) {
					ra.addAttribute("msg", "Total Students Admitted for UG With Govt Quota");
					return "Please Enter Total Students Admitted for UG With Govt Quota in "+admitted_year;
				}
				if (validation.maxlength6(govt_quota_ug) == false) {
					ra.addAttribute("msg","Total Students "+ validation.MaxlengthMSG6);
					return "Total Students Should Contain Only Six Digits in "+admitted_year;
				}
				if (validation.isOnlyNumber(govt_quota_ug) == false) {
					ra.addAttribute("msg","Total Students "+ validation.isOnlyNumberMSG);
					return "Total Students Must Be Contain Only Digit in "+admitted_year;
				}
				if (mang_quota_ug == null || mang_quota_ug.trim().equals("")) {
					ra.addAttribute("msg", "Total Students Admitted for UG With Management Quota");
					return "Please Enter Total Students Admitted for UG With Management Quota in "+admitted_year;
				}
				if (validation.maxlength6(mang_quota_ug) == false) {
					ra.addAttribute("msg","Total Students "+ validation.MaxlengthMSG6);
					return "Total Students Should Contain Only Six Digits in "+admitted_year;
				}
				if (validation.isOnlyNumber(mang_quota_ug) == false) {
					ra.addAttribute("msg","Total Students "+ validation.isOnlyNumberMSG);
					return "Total Students Must Be Contain Only Digit in "+admitted_year;
				}
				if (govt_quota_pg == null || govt_quota_pg.trim().equals("")) {
					ra.addAttribute("msg", "Total Students Admitted for PG With Govt Quota");
					return "Please Enter Total Students Admitted for PG With Govt Quota in "+admitted_year;
				}
				if (validation.maxlength6(govt_quota_pg) == false) {
					ra.addAttribute("msg","Total Students "+ validation.MaxlengthMSG6);
					return "Total Students Should Contain Only Six Digits in "+admitted_year;
				}
				if (validation.isOnlyNumber(govt_quota_pg) == false) {
					ra.addAttribute("msg","Total Students "+ validation.isOnlyNumberMSG);
					return "Total Students Must Be Contain Only Digit in "+admitted_year;
				}
				if (mang_quota_pg == null || mang_quota_pg.trim().equals("")) {
					ra.addAttribute("msg", "Total Students Admitted for PG With Management Quota");
					return "Please Enter Total Students Admitted for PG With Management Quota in "+admitted_year;
				}
				if (validation.maxlength6(mang_quota_pg) == false) {
					ra.addAttribute("msg","Total Students "+ validation.MaxlengthMSG6);
					return "Total Students Should Contain Only Six Digits in "+admitted_year;
				}
				if (validation.isOnlyNumber(mang_quota_pg) == false) {
					ra.addAttribute("msg","Total Students "+ validation.isOnlyNumberMSG);
					return "Total Students Must Be Contain Only Digit in "+admitted_year;
				}
				if (court_order.equals("0")) {
					ra.addAttribute("msg", "Please Select Court Order");
					return "Please Select Court Order for "+admitted_year;
				}
				if (last_student == null || last_student.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Name of the Last Admitted Student");
					return "Please Enter Name of the Last Admitted Student in "+admitted_year;
				}
				if (validation.maxlengthcheck_50(last_student) == false) {
					ra.addAttribute("msg","Last Student Name "+ validation.MaxlengthcheckMSG_50);
					return "Last Student Name Should be 50 Characters in "+admitted_year;
				}
				if (validation.isOnlyAlphabetDASH(last_student) == false) {
					ra.addAttribute("msg","Last Student Name "+ validation.isOnlyAlphabetMSGDASH);
					return "Last Student Name Must Be Contain Only Alphabet in "+admitted_year;
				}
				if (last_stu_add_date == null || last_stu_add_date.trim().equals("") || last_stu_add_date.equals("DD/MM/YYYY")) {
					ra.addAttribute("msg", "Please Enter Last Student Addmited Date");
					return "Please Enter Last Student Addmited Date in "+admitted_year;
				}
				if (validation.isOnlyDateFormat(last_stu_add_date) == false) {
					ra.addAttribute("msg", "Date of Last Student Addmited " + validation.isOnlyDateFormatMSG);
					return "" +validation.isOnlyDateFormatMSG+" in " +admitted_year;
				}
				
				admitted_student_detail.setYear(admitted_year);
				admitted_student_detail.setCurrent_year(Integer.parseInt(year));
				admitted_student_detail.setGovt_quota_ug(Integer.parseInt(govt_quota_ug));
				admitted_student_detail.setMang_quota_ug(Integer.parseInt(mang_quota_ug));
				admitted_student_detail.setGovt_quota_pg(Integer.parseInt(govt_quota_pg));
				admitted_student_detail.setMang_quota_pg(Integer.parseInt(mang_quota_pg));
				admitted_student_detail.setCourt_order(Integer.parseInt(court_order));
				admitted_student_detail.setLast_student(last_student);
				admitted_student_detail.setLast_stu_add_date(datePickerFormat.parse(last_stu_add_date));
				admitted_student_detail.setInstitute_id(Integer.parseInt(institute_id));
				admitted_student_detail.setS_id(Integer.parseInt(s_id));
				admitted_student_detail.setCreated_by(Integer.parseInt(userid));
				admitted_student_detail.setCreated_date(date);
				
				
				if (Integer.parseInt(hid_admitted_student) == 0) {
					int hid_admitted_student1= (Integer) sessionHQL.save(admitted_student_detail);
					sessionHQL.flush();
					sessionHQL.clear();
				}
				else {
					CLG_REG_STUDENT_DTL_ADMITTED_STUDENT admitted_student_detail_u = (CLG_REG_STUDENT_DTL_ADMITTED_STUDENT) sessionHQL
							.get(CLG_REG_STUDENT_DTL_ADMITTED_STUDENT.class, Integer.parseInt(hid_admitted_student));
					
					admitted_student_detail_u.setYear(admitted_year);
					admitted_student_detail_u.setCurrent_year(Integer.parseInt(year));
					admitted_student_detail_u.setGovt_quota_ug(Integer.parseInt(govt_quota_ug));
					admitted_student_detail_u.setMang_quota_ug(Integer.parseInt(mang_quota_ug));
					admitted_student_detail_u.setGovt_quota_pg(Integer.parseInt(govt_quota_pg));
					admitted_student_detail_u.setMang_quota_pg(Integer.parseInt(mang_quota_pg));
					admitted_student_detail_u.setCourt_order(Integer.parseInt(court_order));
					admitted_student_detail_u.setLast_student(last_student);
					admitted_student_detail_u.setLast_stu_add_date(datePickerFormat.parse(last_stu_add_date));
					admitted_student_detail_u.setInstitute_id(Integer.parseInt(institute_id));
					admitted_student_detail_u.setS_id(Integer.parseInt(s_id));
					admitted_student_detail_u.setModified_by(Integer.parseInt(userid));
					admitted_student_detail_u.setModified_date(date);
					sessionHQL.update(admitted_student_detail_u);
					sessionHQL.flush();
					sessionHQL.clear();
					
				}
			
			}
			tx1.commit();
			
			Transaction tx = sessionHQL.beginTransaction();
			CLG_REG_STUDENT_DTL_UPLOAD_DOC admitted_student_check_detail =new CLG_REG_STUDENT_DTL_UPLOAD_DOC();
			
			admitted_student_check_detail.setIntimatedcheck(Integer.parseInt(intimatedcheck));
			if(intimatedcheck.equals("1")) {
				admitted_student_check_detail.setUndertakingcheck(Integer.parseInt(undertakingcheck));
			}else {
				admitted_student_check_detail.setUndertakingcheck(0);
			}
			admitted_student_check_detail.setInstitute_id(Integer.parseInt(institute_id));
			admitted_student_check_detail.setS_id(Integer.parseInt(s_id));
			admitted_student_check_detail.setCreated_by(Integer.parseInt(userid));
			admitted_student_check_detail.setCreated_date(date);
			
				if (Integer.parseInt(hid_admitted_details_check) == 0) {
					int hid_admitted_details_check1= (Integer) sessionHQL.save(admitted_student_check_detail);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					return String.valueOf(hid_admitted_details_check1) ;
			}
				else {

					CLG_REG_STUDENT_DTL_UPLOAD_DOC admitted_student_check_detail_u = (CLG_REG_STUDENT_DTL_UPLOAD_DOC) sessionHQL
							.get(CLG_REG_STUDENT_DTL_UPLOAD_DOC.class, Integer.parseInt(hid_admitted_details_check));
					
					admitted_student_check_detail_u.setIntimatedcheck(Integer.parseInt(intimatedcheck));
					if(intimatedcheck.equals("1")) {
					admitted_student_check_detail_u.setUndertakingcheck(Integer.parseInt(undertakingcheck));
					}else {
						admitted_student_check_detail_u.setUndertakingcheck(0);
					}
					admitted_student_check_detail_u.setS_id(Integer.parseInt(s_id));
					admitted_student_check_detail_u.setModified_by(Integer.parseInt(userid));
					admitted_student_check_detail_u.setModified_date(date);
					sessionHQL.update(admitted_student_check_detail_u);
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
	
	
	//FETCH ADMITTED STUDENT DETAILS
	@RequestMapping(value = "admin/getAdmitted_Student_Details", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> getAdmitted_Student_Details(HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		return sdDao.getAdmitted_Student_Details(Integer.parseInt(institute_id));
	}
	
	//FETCH ADMITTED STUDENT DETAILS
	@RequestMapping(value = "admin/getAdmitted_Student_Check_Details", method = RequestMethod.POST)
	public @ResponseBody List<CLG_REG_STUDENT_DTL_UPLOAD_DOC> getAdmitted_Student_Check_Details(HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL
				.createQuery("from CLG_REG_STUDENT_DTL_UPLOAD_DOC where institute_id=:institute_id order by id");
		
		q.setParameter("institute_id", Integer.parseInt(institute_id));
		@SuppressWarnings("unchecked")
		List<CLG_REG_STUDENT_DTL_UPLOAD_DOC> clist = (List<CLG_REG_STUDENT_DTL_UPLOAD_DOC>) q.list();
	
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	//SAVE PASSED STUDENT DETAILS
	@PostMapping(value = "/Passed_Student_Details_Save_Draft_Action")
	public @ResponseBody String Passed_Student_Details_Save_Draft_Action( 
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
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String s_id = session.getAttribute("super_id").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date date = new Date();
		String strDate = datePickerFormat.format(date); 
		String year = strDate.substring(6, 10);
		System.err.println("STR DATE-------------"+strDate);
		System.err.println("YEAR-------------"+year);
		System.err.println("CURRENT DATE----------"+date);
		
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		
		try {
			
			
			Transaction tx1 = sessionHQL.beginTransaction();
			CLG_REG_STUDENT_DTL_PASS_STUDENT passed_student_detail =new CLG_REG_STUDENT_DTL_PASS_STUDENT();
			
			
			for (int i = 0; i < 5; i++) {
				int admitted_year = Math.subtractExact(Integer.parseInt(year), i);
				System.err.println("FOR LOOP YEAR----------------"+admitted_year);
				String appeared_stu_ug = request.getParameter("appeared_stu_ug_"+admitted_year);
				String passed_stu_ug = request.getParameter("passed_stu_ug_"+admitted_year);
				String appeared_stu_pg = request.getParameter("appeared_stu_pg_"+admitted_year);
				String passed_stu_pg = request.getParameter("passed_stu_pg_"+admitted_year);
				String hid_passed_student = request.getParameter("hid_passed_student_"+admitted_year);
				
				if (validation.maxlength6(appeared_stu_ug) == false) {
					ra.addAttribute("msg","Total Students "+ validation.MaxlengthMSG6);
					return "Total Students Should Contain Only Six Digits in "+admitted_year;
				}
				if (validation.isOnlyNumber(appeared_stu_ug) == false) {
					ra.addAttribute("msg","Total Students "+ validation.isOnlyNumberMSG);
					return "Total Students Must Be Contain Only Digit in "+admitted_year;
				}
				if (validation.maxlength6(passed_stu_ug) == false) {
					ra.addAttribute("msg","Total Students "+ validation.MaxlengthMSG6);
					return "Total Students Should Contain Only Six Digits in "+admitted_year;
				}
				if (validation.isOnlyNumber(passed_stu_ug) == false) {
					ra.addAttribute("msg","Total Students "+ validation.isOnlyNumberMSG);
					return "Total Students Must Be Contain Only Digit in "+admitted_year;
				}
				if (validation.maxlength6(appeared_stu_pg) == false) {
					ra.addAttribute("msg","Total Students "+ validation.MaxlengthMSG6);
					return "Total Students Should Contain Only Six Digits in "+admitted_year;
				}
				if (validation.isOnlyNumber(appeared_stu_pg) == false) {
					ra.addAttribute("msg","Total Students "+ validation.isOnlyNumberMSG);
					return "Total Students Must Be Contain Only Digit in "+admitted_year;
				}
				if (validation.maxlength6(passed_stu_pg) == false) {
					ra.addAttribute("msg","Total Students "+ validation.MaxlengthMSG6);
					return "Total Students Should Contain Only Six Digits in "+admitted_year;
				}
				if (validation.isOnlyNumber(passed_stu_pg) == false) {
					ra.addAttribute("msg","Total Students "+ validation.isOnlyNumberMSG);
					return "Total Students Must Be Contain Only Digit in "+admitted_year;
				}
				
				passed_student_detail.setYear(admitted_year);
				passed_student_detail.setCurrent_year(Integer.parseInt(year));
				passed_student_detail.setAppeared_stu_ug((appeared_stu_ug.equals("") ? 0 : Integer.parseInt(appeared_stu_ug)));
				passed_student_detail.setPassed_stu_ug((passed_stu_ug.equals("") ? 0 : Integer.parseInt(passed_stu_ug)));
				passed_student_detail.setAppeared_stu_pg((appeared_stu_pg.equals("") ? 0 : Integer.parseInt(appeared_stu_pg)));
				passed_student_detail.setPassed_stu_pg((passed_stu_pg.equals("") ? 0 : Integer.parseInt(passed_stu_pg)));
				passed_student_detail.setInstitute_id(Integer.parseInt(institute_id));
				passed_student_detail.setS_id(Integer.parseInt(s_id));
				passed_student_detail.setCreated_by(Integer.parseInt(userid));
				passed_student_detail.setCreated_date(date);
				
				
				if (Integer.parseInt(hid_passed_student) == 0) {
					int hid_passed_student1= (Integer) sessionHQL.save(passed_student_detail);
					sessionHQL.flush();
					sessionHQL.clear();
				}
				else {
					CLG_REG_STUDENT_DTL_PASS_STUDENT passed_student_detail_u = (CLG_REG_STUDENT_DTL_PASS_STUDENT) sessionHQL
							.get(CLG_REG_STUDENT_DTL_PASS_STUDENT.class, Integer.parseInt(hid_passed_student));
					
					passed_student_detail_u.setYear(admitted_year);
					passed_student_detail_u.setCurrent_year(Integer.parseInt(year));
					passed_student_detail_u.setAppeared_stu_ug((appeared_stu_ug.equals("") ? 0 : Integer.parseInt(appeared_stu_ug)));
					passed_student_detail_u.setPassed_stu_ug((passed_stu_ug.equals("") ? 0 : Integer.parseInt(passed_stu_ug)));
					passed_student_detail_u.setAppeared_stu_pg((appeared_stu_pg.equals("") ? 0 : Integer.parseInt(appeared_stu_pg)));
					passed_student_detail_u.setPassed_stu_pg((passed_stu_pg.equals("") ? 0 : Integer.parseInt(passed_stu_pg)));
					passed_student_detail_u.setS_id(Integer.parseInt(s_id));
					passed_student_detail_u.setModified_by(Integer.parseInt(userid));
					passed_student_detail_u.setModified_date(date);
					sessionHQL.update(passed_student_detail_u);
					sessionHQL.flush();
					sessionHQL.clear();
					
				}
			
			}
			tx1.commit();
			
				
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
	
	
	//FETCH PASSED STUDENT DETAILS
	@RequestMapping(value = "admin/getPassed_Student_Details", method = RequestMethod.POST)
	public @ResponseBody List<CLG_REG_STUDENT_DTL_PASS_STUDENT> getPassed_Student_Details(HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL
				.createQuery("from CLG_REG_STUDENT_DTL_PASS_STUDENT where institute_id=:institute_id order by id");
		
		q.setParameter("institute_id", Integer.parseInt(institute_id));
		@SuppressWarnings("unchecked")
		List<CLG_REG_STUDENT_DTL_PASS_STUDENT> clist = (List<CLG_REG_STUDENT_DTL_PASS_STUDENT>) q.list();
	
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	
	//SAVE UPLOAD DOCUMENT DETAILS
	@PostMapping(value = "/Upload_Documnet_Save_Draft_Action")
	public @ResponseBody String Upload_Documnet_Save_Draft_Action( 
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			@RequestParam(value = "lastfiveguide", required = false) MultipartFile lastfiveguide_doc,
			@RequestParam(value = "neet_score", required = false) MultipartFile neet_score_doc,
			@RequestParam(value = "undertakingofstudent", required = false) MultipartFile undertakingofstudent_doc,
			@RequestParam(value = "biometricattendance", required = false) MultipartFile biometricattendance_doc,
			MultipartHttpServletRequest mul,RedirectAttributes ra) throws ParseException, IOException {

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
		
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String lastfiveguide = "lastfiveguide";
		String neet_score = "neet_score";
		String undertakingofstudent = "undertakingofstudent";
		String biometricattendance = "biometricattendance";
		String hid_upload_document = request.getParameter("hid_upload_document");
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		
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
		
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			CLG_REG_STUDENT_DTL_UPLOAD_DOC upload_document_detail =new CLG_REG_STUDENT_DTL_UPLOAD_DOC();
			
			upload_document_detail.setLastfiveguide(lastfiveguide);
			upload_document_detail.setNeet_score(neet_score);
			upload_document_detail.setUndertakingofstudent(undertakingofstudent);
			upload_document_detail.setBiometricattendance(biometricattendance);
			upload_document_detail.setInstitute_id(Integer.parseInt(institute_id));
			upload_document_detail.setS_id(Integer.parseInt(s_id));
			upload_document_detail.setCreated_by(Integer.parseInt(userid));
			upload_document_detail.setCreated_date(date);
			
				if (Integer.parseInt(hid_upload_document) == 0) {
					int hid_upload_document1= (Integer) sessionHQL.save(upload_document_detail);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					return String.valueOf(hid_upload_document1) ;
			}
				else {

					CLG_REG_STUDENT_DTL_UPLOAD_DOC upload_document_detail_u = (CLG_REG_STUDENT_DTL_UPLOAD_DOC) sessionHQL
							.get(CLG_REG_STUDENT_DTL_UPLOAD_DOC.class, Integer.parseInt(hid_upload_document));
					
					upload_document_detail_u.setLastfiveguide(lastfiveguide);
					upload_document_detail_u.setNeet_score(neet_score);
					upload_document_detail_u.setUndertakingofstudent(undertakingofstudent);
					upload_document_detail_u.setBiometricattendance(biometricattendance);
					upload_document_detail_u.setInstitute_id(Integer.parseInt(institute_id));
					upload_document_detail_u.setS_id(Integer.parseInt(s_id));
					upload_document_detail_u.setModified_by(Integer.parseInt(userid));
					upload_document_detail_u.setModified_date(date);
					sessionHQL.update(upload_document_detail_u);
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
	
	//FETCH UPLOAD DOCUMENT DETAILS
	@RequestMapping(value = "admin/getUpload_Document_Details", method = RequestMethod.POST)
	public @ResponseBody List<CLG_REG_STUDENT_DTL_UPLOAD_DOC> getUpload_Document_Details(HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL
				.createQuery("from CLG_REG_STUDENT_DTL_UPLOAD_DOC where institute_id=:institute_id order by id");
		
		q.setParameter("institute_id", Integer.parseInt(institute_id));
		@SuppressWarnings("unchecked")
		List<CLG_REG_STUDENT_DTL_UPLOAD_DOC> clist = (List<CLG_REG_STUDENT_DTL_UPLOAD_DOC>) q.list();
	
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	
	//SAVE INTERNSHIP DETAILS
	@PostMapping(value = "/Internship_Details_Save_Draft_Action")
	public @ResponseBody String Internship_Details_Save_Draft_Action( 
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			@RequestParam(value = "total_intern", required = false) MultipartFile total_intern_doc,
			@RequestParam(value = "migration_list", required = false) MultipartFile migration_list_doc,
			MultipartHttpServletRequest mul,RedirectAttributes ra) throws ParseException, IOException {

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
		
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String total_intern = "total_intern";
		String migration_list = "migration_list";
		String pro_regcheck = request.getParameter("pro_regcheck");
		String rotationcheck = request.getParameter("rotationcheck");
//		String internsopd = request.getParameter("internsopd");
		String internsdutyopd = request.getParameter("internsdutyopd");
//		String internsipd = request.getParameter("internsipd");
		String internsdutyipd = request.getParameter("internsdutyipd");
		String migrationcheck = request.getParameter("migrationcheck");
		String prescribe = request.getParameter("prescribe");
		String seminar = request.getParameter("seminar");
		String internship_not_completed = request.getParameter("internship_not_completed");
		String house_job = request.getParameter("house_job");
		String no_house_job = request.getParameter("no_house_job");
		String graded_teaching = request.getParameter("graded_teaching");
		String hid_internship_details = request.getParameter("hid_internship_details");
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		
		if (!total_intern_doc.isEmpty()) {
			total_intern = upload_imagemethod(request,total_intern_doc,session, total_intern);
		}
		else {
			total_intern = request.getParameter("hid_total_intern");
		}
		
		if (!migration_list_doc.isEmpty()) {
			migration_list = upload_imagemethod(request,migration_list_doc,session, migration_list);
		}
		else {
			migration_list = request.getParameter("hid_migration_list");
		}
		
		
		if (validation.maxlength2(internsdutyopd) == false) {
			ra.addAttribute("msg","Interns Duty Hours in OPD "+ validation.MaxlengthMSG2);
			return "Interns Duty Hours in OPD"+validation.MaxlengthMSG2;
		}
		if (validation.isOnlyNumber(internsdutyopd) == false) {
			ra.addAttribute("msg","Interns Duty Hours in OPD" +validation.isOnlyNumberMSG);
			return "Interns Duty Hours in OPD " +validation.isOnlyNumberMSG;
		}
		if (validation.maxlength2(internsdutyipd) == false) {
			ra.addAttribute("msg","Interns Duty Hours in IPD "+ validation.MaxlengthMSG2);
			return "Interns Duty Hours in IPD"+validation.MaxlengthMSG2;
		}
		if (validation.isOnlyNumber(internsdutyipd) == false) {
			ra.addAttribute("msg","Interns Duty Hours in IPD" +validation.isOnlyNumberMSG);
			return "Interns Duty Hours in IPD " +validation.isOnlyNumberMSG;
		}
		if (validation.maxlengthcheck100(prescribe) == false) {
			ra.addAttribute("msg","Students Prescribe Medicine "+ validation.MaxlengthcheckMSG100);
			return "Students Prescribe Medicine "+validation.MaxlengthcheckMSG100;
		}
		if (validation.isOnlyAlphabetDASH(prescribe) == false) {
			ra.addAttribute("msg","Students Prescribe Medicine " +validation.isOnlyAlphabetMSGDASH);
			return "Students Prescribe Medicine" +validation.isOnlyAlphabetMSGDASH;
		}
		if (validation.maxlengthcheck100(seminar) == false) {
			ra.addAttribute("msg","Seminar for Internee "+ validation.MaxlengthcheckMSG100);
			return "Seminar for Internee "+validation.MaxlengthcheckMSG100;
		}
		if (validation.isOnlyAlphabetDASH(seminar) == false) {
			ra.addAttribute("msg","Seminar for Internee " +validation.isOnlyAlphabetMSGDASH);
			return "Seminar for Internee" +validation.isOnlyAlphabetMSGDASH;
		}
		if (validation.maxlength10(internship_not_completed) == false) {
			ra.addAttribute("msg","Number of Students who have not Completed Internship "+ validation.MaxlengthMSG10);
			return "Number of Students who have not Completed Internship "+validation.MaxlengthMSG10;
		}
		if (validation.isOnlyNumber(internship_not_completed) == false) {
			ra.addAttribute("msg","Number of Students who have not Completed Internship" +validation.isOnlyNumberMSG);
			return "Number of Students who have not Completed Internship " +validation.isOnlyNumberMSG;
		}
		if (validation.maxlengthcheck100(house_job) == false) {
			ra.addAttribute("msg","House Job "+ validation.MaxlengthcheckMSG100);
			return "House Job "+validation.MaxlengthcheckMSG100;
		}
		if (validation.isOnlyAlphabetDASH(house_job) == false) {
			ra.addAttribute("msg","House Job " +validation.isOnlyAlphabetMSGDASH);
			return "House Job" +validation.isOnlyAlphabetMSGDASH;
		}
		if (validation.maxlength10(no_house_job) == false) {
			ra.addAttribute("msg","Number of Students Undergoing House Job "+ validation.MaxlengthMSG10);
			return "Number of Students Undergoing House Job "+validation.MaxlengthMSG10;
		}
		if (validation.isOnlyNumber(no_house_job) == false) {
			ra.addAttribute("msg","Number of Students Undergoing House Job" +validation.isOnlyNumberMSG);
			return "Number of Students Undergoing House Job " +validation.isOnlyNumberMSG;
		}
		if (validation.maxlengthcheck500(graded_teaching) == false) {
			ra.addAttribute("msg","Graded Teaching Work "+ validation.MaxlengthcheckMSG500);
			return "Graded Teaching Work "+validation.MaxlengthcheckMSG500;
		}
		if (validation.isOnlyAlphabetDASH(graded_teaching) == false) {
			ra.addAttribute("msg","Graded Teaching Work " +validation.isOnlyAlphabetMSGDASH);
			return "Graded Teaching Work" +validation.isOnlyAlphabetMSGDASH;
		}
		
		
		Date date = new Date();
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			CLG_REG_STUDENT_DTL_UPLOAD_DOC internship_detail =new CLG_REG_STUDENT_DTL_UPLOAD_DOC();
			
			internship_detail.setTotal_intern(total_intern);
			internship_detail.setPro_regcheck(Integer.parseInt(pro_regcheck));
			internship_detail.setRotationcheck(Integer.parseInt(rotationcheck));
//			internship_detail.setInternsopd(Integer.parseInt(internsopd));
			internship_detail.setInternsdutyopd(internsdutyopd);
//			internship_detail.setInternsipd(Integer.parseInt(internsipd));
			internship_detail.setInternsdutyipd(internsdutyipd);
			internship_detail.setMigrationcheck(Integer.parseInt(migrationcheck));
			if(migrationcheck.equals("1")) {
			internship_detail.setMigration_list(migration_list);
			}
			internship_detail.setPrescribe(prescribe);
			internship_detail.setSeminar(seminar);
			internship_detail.setInternship_not_completed(Integer.parseInt(internship_not_completed));
			internship_detail.setHouse_job(house_job);
			internship_detail.setNo_house_job(Integer.parseInt(no_house_job));
			internship_detail.setGraded_teaching(graded_teaching);
			internship_detail.setInstitute_id(Integer.parseInt(institute_id));
//			internship_detail.setS_id(Integer.parseInt(s_id));
			internship_detail.setCreated_by(Integer.parseInt(userid));
			internship_detail.setCreated_date(date);
			
				if (Integer.parseInt(hid_internship_details) == 0) {
					int hid_internship_details1= (Integer) sessionHQL.save(internship_detail);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					return String.valueOf(hid_internship_details1) ;
			}
				else {

					CLG_REG_STUDENT_DTL_UPLOAD_DOC internship_detail_u = (CLG_REG_STUDENT_DTL_UPLOAD_DOC) sessionHQL
							.get(CLG_REG_STUDENT_DTL_UPLOAD_DOC.class, Integer.parseInt(hid_internship_details));
					
					internship_detail_u.setTotal_intern(total_intern);
					internship_detail_u.setPro_regcheck(Integer.parseInt(pro_regcheck));
					internship_detail_u.setRotationcheck(Integer.parseInt(rotationcheck));
//					internship_detail_u.setInternsopd(Integer.parseInt(internsopd));
					internship_detail_u.setInternsdutyopd(internsdutyopd);
//					internship_detail_u.setInternsipd(Integer.parseInt(internsipd));
					internship_detail_u.setInternsdutyipd(internsdutyipd);
					internship_detail_u.setMigrationcheck(Integer.parseInt(migrationcheck));
					if(migrationcheck.equals("1")) {
					internship_detail_u.setMigration_list(migration_list);
					}
					internship_detail_u.setPrescribe(prescribe);
					internship_detail_u.setSeminar(seminar);
					internship_detail_u.setInternship_not_completed(Integer.parseInt(internship_not_completed));
					internship_detail_u.setHouse_job(house_job);
					internship_detail_u.setNo_house_job(Integer.parseInt(no_house_job));
					internship_detail_u.setGraded_teaching(graded_teaching);
					internship_detail_u.setInstitute_id(Integer.parseInt(institute_id));
//					internship_detail_u.setS_id(Integer.parseInt(s_id));
					internship_detail_u.setModified_by(Integer.parseInt(userid));
					internship_detail_u.setModified_date(date);
					sessionHQL.update(internship_detail_u);
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
	
	
	//FETCH INTERNSHIP DETAILS
	@RequestMapping(value = "admin/getInternship_Details", method = RequestMethod.POST)
	public @ResponseBody List<CLG_REG_STUDENT_DTL_UPLOAD_DOC> getInternship_Details(HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL
				.createQuery("from CLG_REG_STUDENT_DTL_UPLOAD_DOC where institute_id=:institute_id order by id");
		
		q.setParameter("institute_id", Integer.parseInt(institute_id));
		@SuppressWarnings("unchecked")
		List<CLG_REG_STUDENT_DTL_UPLOAD_DOC> clist = (List<CLG_REG_STUDENT_DTL_UPLOAD_DOC>) q.list();
		
		tx.commit();
		sessionHQL.close();
		return clist;
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
