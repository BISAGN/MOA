package com.AyushEdu.controller.Part_one;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Infrastructure_DAO;
import com.AyushEdu.dao.Part_One.Clg_Reg_Dept_Comp_Printer_Avail_Dao;
import com.AyushEdu.dao.Part_One.Clg_Reg_Staff_Info_Dao;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Dao;
import com.AyushEdu.validation.ValidationController;

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
import com.AyushEdu.Models.Clg_Reg_College_Financial.CLG_REG_COLLEGE_BANK_ACCOUNT_DETAILS;
import com.AyushEdu.Models.Clg_Reg_College_Financial.CLG_REG_LAST_FINANCIAL_YEAR_EXPENSES;
import com.AyushEdu.Models.Clg_Reg_College_Financial.CLG_REG_LAST_YEAR_EXPENDITURE_EXPENSES;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_INFRA_ADDITIONAL_INFORMATION;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_INFRA_CENTRAL_LIBRARY;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_INFRA_CENTRAL_LIBRARY_CHILD;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_INFRA_COLLEGE_COUNCIL;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_INFRA_COLLEGE_COUNCIL_CAMERA;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_INFRA_CONSTRUCTED_AREA;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_INFRA_CONSTRUCTED_AREA_DEPT;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_INFRA_HERBAL_GARDEN;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_INFRA_HOSTEL_DETAILS;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_INFRA_PROGRESS_OF_INSTITUTION;
import com.AyushEdu.Models.Clg_Teaching_Staff_Info.CLG_REG_COLLEGE_TEACHER_PROMOTION;
import com.AyushEdu.Models.Clg_Teaching_Staff_Info.CLG_REG_LAST_ACADEMIC_YEAR_STAFF_DETAILS;
import com.AyushEdu.Models.Clg_Teaching_Staff_Info.CLG_REG_NON_TEACHING_STAFF_INFO;
import com.AyushEdu.Models.Clg_Teaching_Staff_Info.CLG_REG_STAFF_SALARY_INFORMATION;
import com.AyushEdu.Models.Clg_Teaching_Staff_Info.CLG_REG_TEACHING_STAFF_PG;
import com.AyushEdu.Models.Clg_Teaching_Staff_Info.CLG_REG_TEACHING_STAFF_UG;
import com.AyushEdu.Models.LMS_NCISM.EDU_LMS_SYSTEM_COURSE_DURATION;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Infrastructure_DAO;
import com.AyushEdu.validation.ValidationController;
@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Clg_Reg_College_Staff_Information_Controller {
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
	Institution_Basic_Details_Dao ibdao;
	
	@Autowired
	private Clg_Reg_Staff_Info_Dao SIDao;
	
	@RequestMapping(value = "admin/college_staff_info", method = RequestMethod.GET)
	public ModelAndView college_staff_info(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
		
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
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institute = String.valueOf(ea.getInstitute_id());
		//String userid = session.getAttribute("userId_for_jnlp").toString();
		String role = session.getAttribute("role").toString();
		
		String username = session.getAttribute("roleloginName").toString();
		if(role=="Institute_NCH") {
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int parent_id = (int) sessionHQL
				.createQuery("select id from CLG_REG_TEACHING_STAFF_UG where institute_id=:inst_id")
				.setParameter("inst_id", Integer.parseInt(institute))
				.uniqueResult();
		Mmap.put("parent_id", parent_id);
		}
		Mmap.put("institude", institute);
		
		Mmap.put("getDepartmentList", SIDao.getAll_ug_Department_name());
//		Mmap.put("getDepartmentListofug", SIDao.getAllDepartment_list_ug_new());
		Mmap.put("listString", SIDao.getAllDepartment_list_new_ug());
		Mmap.put("listStringpg", SIDao.getAllDepartment_list_new_pg());
		Mmap.put("datapgsize", ibdao.getAllCourse_PG());
		Mmap.put("department_list", SIDao.getAll_ug_Department_name());
		Mmap.put("getAllCourse_PG", SIDao.getAllCourse_PG());
		Mmap.put("post_list", SIDao.getAll_post_name());
		Mmap.put("listStringPost", SIDao.getAllPost_list());
		Mmap.put("academic_list", SIDao.getAll_academic_name());
		Mmap.put("listStringAcademicdtls", SIDao.getAllacademic_list());
		Mmap.put("dataparent_document", SIDao.getAlluploaddocumentdetails(Integer.parseInt(institute)));
		Mmap.put("dataparent_promotion", SIDao.getAllteacherpromotiondetails(Integer.parseInt(institute)));
		Mmap.put("getDesignationList", SIDao.getDesignationList());
		
		System.err.println("--institute_id--" + institute);
		return new ModelAndView("college_staff_info");
	}

	
	//SAVE Teaching Staff (UG)--------------------------------------------
	 
	@PostMapping(value = "/College_Staff_UG_Action")
	public @ResponseBody String College_Staff_UG_Action( 
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,MultipartHttpServletRequest mul,
			RedirectAttributes ra) throws ParseException, IOException {

		String role = session.getAttribute("role").toString();
		String roleid1 = session.getAttribute("roleid").toString();
		
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String username = principal.getName();
		String s_id = session.getAttribute("super_id").toString();
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institute_id = String.valueOf(ea.getInstitute_id());
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx1 = sessionHQL.beginTransaction();
		
//		List department_list = new ArrayList();
		ArrayList<ArrayList<String>> department_list = SIDao.getAll_ug_Department_name();
		
		CLG_REG_TEACHING_STAFF_UG pers_p =new CLG_REG_TEACHING_STAFF_UG();
		try {
		for (int i = 0; i < department_list.size(); i++) {
		String hid_staff_info = request.getParameter("hid_staff_info"+department_list.get(i).get(0));
		String department_name = department_list.get(i).get(1);
		String department_id = department_list.get(i).get(0);
//		String department_name = request.getParameter("department_name"+department_list.get(i).get(0));
		String prof_full_time = request.getParameter("anatomy_professor_full_time"+department_list.get(i).get(0));
		String associate_prof_full_time = request.getParameter("anatomy_ass_professor_full_time"+department_list.get(i).get(0));
		String assistant_prof_full_time = request.getParameter("anatomy_lect_professor_full_time"+department_list.get(i).get(0));
		String total = request.getParameter("anatomy_total"+department_list.get(i).get(0));
		String total_professor_full_time = request.getParameter("total_professor_full_time");
		String total_ass_professor_full_time = request.getParameter("total_ass_professor_full_time");
		String total_lect_professor_full_time = request.getParameter("total_lect_professor_full_time");
		String total_total = request.getParameter("total_total");
		String total_full_time = request.getParameter("total_full_time");
		String modern_medicine = request.getParameter("modern_medicine");
		
		MultipartFile file = mul.getFile("teaching_acquittance1");
		System.err.println("---------file"+file);
		String teaching_acquittance = upload_imagemethod(request,file, session,hid_staff_info);
		System.err.println("---------nonteaching_acquittance"+teaching_acquittance);
		
		
		int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
		Date date = new Date();
		if (prof_full_time == null || prof_full_time.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Professor Full Time");
			return "Please Enter Professor Full Time";
		}
		if (validation.isOnlyNumerLib(prof_full_time) == false) {
			ra.addAttribute("msg","Professor Full Time " +validation.isOnlyNumerMSGLib);
			return "Professor Full Time " +validation.isOnlyNumerMSGLib;
		}
		if (validation.maxlengthcheck15(prof_full_time) == false) {
			ra.addAttribute("msg", "Professor Full Time" + validation.MaxlengthcheckMSG15);
			return "Professor Full Time " +validation.MaxlengthcheckMSG15;
		}
		
		if (associate_prof_full_time == null || associate_prof_full_time.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Associate Professors / Readers Full Time  ");
			return "Please Enter Associate Professors / Readers Full Time.";
		}
		if (validation.isOnlyNumerLib(associate_prof_full_time) == false) {
			ra.addAttribute("msg","Associate Professors / Readers Full Time  " +validation.isOnlyNumerMSGLib);
			return "Associate Professors / Readers Full Time  " +validation.isOnlyNumerMSGLib;
		}
		if (validation.maxlengthcheck15(associate_prof_full_time) == false) {
			ra.addAttribute("msg", "Associate Professors / Readers Full Time " + validation.MaxlengthcheckMSG15);
			return "Associate Professors / Readers Full Time  " +validation.MaxlengthcheckMSG15;
		}
		
		if (assistant_prof_full_time == null || assistant_prof_full_time.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Assistant Professors / Lecturers Full Time .");
			return "Please Enter Assistant Professors / Lecturers Full Time .";
		}
		if (validation.isOnlyNumerLib(assistant_prof_full_time) == false) {
			ra.addAttribute("msg","Assistant Professors / Lecturers Full Time " +validation.isOnlyNumerMSGLib);
			return "Assistant Professors / Lecturers Full Time " +validation.isOnlyNumerMSGLib;
		}
		if (validation.maxlengthcheck15(assistant_prof_full_time) == false) {
			ra.addAttribute("msg", "Assistant Professors / Lecturers Full Time " + validation.MaxlengthcheckMSG15);
			return "Assistant Professors / Lecturers Full Time " +validation.MaxlengthcheckMSG15;
		}
		if (validation.isOnlyAlphabetNumber(modern_medicine) == false) {
			ra.addAttribute("msg","Teacher/Consultant of Modern Medicine" +validation.isOnlyAlphabetNumberMSG);
			return "Teacher/Consultant of Modern Medicine" +validation.isOnlyAlphabetNumberMSG;
		}
		if (total == null || total.trim().equals("")) {
			ra.addAttribute("msg", "Total.");
			return "Total";
		}
		if (validation.isOnlyNumerLib(total) == false) {
			ra.addAttribute("msg","Total" +validation.isOnlyNumerMSGLib);
			return "Total " +validation.isOnlyNumerMSGLib;
		}
		if (validation.maxlengthcheck15(total) == false) {
			ra.addAttribute("msg", "Total " + validation.MaxlengthcheckMSG15);
			return "Total " +validation.MaxlengthcheckMSG15;
		}
		if (total_full_time == null || total_full_time.trim().equals("")) {
			ra.addAttribute("msg", "Total Staff for Full Time.");
			return "Total Staff for Full Time";
		}
		if (validation.isOnlyNumerLib(total_full_time) == false) {
			ra.addAttribute("msg","Total Staff for Full Time" +validation.isOnlyNumerMSGLib);
			return "Total Staff for Full Time " +validation.isOnlyNumerMSGLib;
		}
		if (validation.maxlengthcheck15(total_full_time) == false) {
			ra.addAttribute("msg", "Total Staff for Full Time " + validation.MaxlengthcheckMSG15);
			return "Total Staff for Full Time " +validation.MaxlengthcheckMSG15;
		}
		if (teaching_acquittance == null || teaching_acquittance.trim().equals("")) {
			ra.addAttribute("msg", "Please Upload Stamp Acquittance Role of Teaching Staff in Teaching Staff UG");
			return "Please Upload Stamp Acquittance Role of Teaching Staff in Teaching Staff UG.";
		}

		
			CLG_REG_TEACHING_STAFF_UG clg_staff_dtls =new CLG_REG_TEACHING_STAFF_UG();
			
//			for (int i = 0; i < department_list_new.size(); i++) {
				
				clg_staff_dtls.setDepartment_name(department_name);
				clg_staff_dtls.setDepartment_id(Integer.parseInt(department_id));
				clg_staff_dtls.setProf_full_time(prof_full_time);
				clg_staff_dtls.setAssociate_prof_full_time(associate_prof_full_time);
				clg_staff_dtls.setAssistant_prof_full_time(assistant_prof_full_time);
				clg_staff_dtls.setTotal(Integer.parseInt(total));
				clg_staff_dtls.setTotal_professor_full_time(Integer.parseInt(total_professor_full_time));
				clg_staff_dtls.setTotal_ass_professor_full_time(Integer.parseInt(total_ass_professor_full_time));
				clg_staff_dtls.setTotal_lect_professor_full_time(Integer.parseInt(total_lect_professor_full_time));
				clg_staff_dtls.setTotal_total(Integer.parseInt(total_total));
				clg_staff_dtls.setTotal_full_time(Integer.parseInt(total_full_time));
				clg_staff_dtls.setConsultant_modern_medi(modern_medicine);
				clg_staff_dtls.setTeaching_acquittance(teaching_acquittance);
				clg_staff_dtls.setS_id(Integer.parseInt(s_id));
				System.err.println("==============="+ s_id);
				clg_staff_dtls.setInstitute_id(Integer.parseInt(institute_id));
				
//				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
//				String institude = String.valueOf(ea.getInstitute_id());
			
				clg_staff_dtls.setCreated_by(Integer.parseInt(userid));
				clg_staff_dtls.setCreated_date(date);
				
				
				if (Integer.parseInt(hid_staff_info) == 0) {
//					clg_staff_dtls.setP_id(Integer.parseInt(p_hid_staff_info));
					int hid_staff_info1= (Integer) sessionHQL.save(clg_staff_dtls);
					sessionHQL.flush();
					sessionHQL.clear();
					
				}else {
					CLG_REG_TEACHING_STAFF_UG clg_staff_dtls_ug = (CLG_REG_TEACHING_STAFF_UG) sessionHQL
							.get(CLG_REG_TEACHING_STAFF_UG.class, Integer.parseInt(hid_staff_info));
					
					clg_staff_dtls_ug.setDepartment_name(department_name);
					clg_staff_dtls_ug.setDepartment_id(Integer.parseInt(department_id));
					clg_staff_dtls_ug.setProf_full_time(prof_full_time);
					clg_staff_dtls_ug.setAssociate_prof_full_time(associate_prof_full_time);
					clg_staff_dtls_ug.setAssistant_prof_full_time(assistant_prof_full_time);
					clg_staff_dtls_ug.setTotal(Integer.parseInt(total));
					
					clg_staff_dtls_ug.setTotal_ass_professor_full_time(Integer.parseInt(total_ass_professor_full_time));
					clg_staff_dtls_ug.setTotal_lect_professor_full_time(Integer.parseInt(total_lect_professor_full_time));
					clg_staff_dtls_ug.setTotal_total(Integer.parseInt(total_total));
					clg_staff_dtls_ug.setTotal_full_time(Integer.parseInt(total_full_time));
					clg_staff_dtls_ug.setConsultant_modern_medi(modern_medicine);
					clg_staff_dtls_ug.setTeaching_acquittance(teaching_acquittance);
					clg_staff_dtls_ug.setId(Integer.parseInt(hid_staff_info));
					clg_staff_dtls_ug.setS_id(Integer.parseInt(s_id));
					clg_staff_dtls_ug.setModified_by(Integer.parseInt(userid));
					clg_staff_dtls_ug.setModified_date(date);
					sessionHQL.update(clg_staff_dtls_ug);
					sessionHQL.flush();
					sessionHQL.clear();
//				}
			}
		}
		
			tx1.commit();
//			UserLogin ea1 = common.getAllInfoLogin(sessionFactory, userid).get(0);
//			String institute_id1 = String.valueOf(ea.getInstitute_id());
//			ea.setInstitute_id(Integer.parseInt(institute_id));
			Transaction tx = sessionHQL.beginTransaction();
			String s_id2 = session.getAttribute("super_id").toString();
			ArrayList<ArrayList<String>> getAllCourse_PG = SIDao.getAllCourse_PG();
			CLG_REG_TEACHING_STAFF_PG PG_detail =new CLG_REG_TEACHING_STAFF_PG();
		
				for (int i = 0; i < getAllCourse_PG.size(); i++) {
			
					
			String hid_staff_info_pg = request.getParameter("hid_staff_info_pg"+getAllCourse_PG.get(i).get(0));	
			String department = getAllCourse_PG.get(i).get(1);
			String department_id = getAllCourse_PG.get(i).get(0);
			String full_time_prof = request.getParameter("homoeopathicug_professor_full_time"+getAllCourse_PG.get(i).get(0));
			String asso_guest_faculty = request.getParameter("homoeopathicug_professor_guest_faculty"+getAllCourse_PG.get(i).get(0));
			String assis_lect = request.getParameter("ass_professor_lect"+getAllCourse_PG.get(i).get(0));
			String total_teaching_staff = request.getParameter("total_teaching_staff");
			String each_total = request.getParameter("homoeopathicug_total"+getAllCourse_PG.get(i).get(0));
			if (full_time_prof == null || full_time_prof.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Professor Full Time");
				return "Please Enter Professor Full Time";
			}
			if (validation.isOnlyNumerLib(full_time_prof) == false) {
				ra.addAttribute("msg","Professor Full Time " +validation.isOnlyNumerMSGLib);
				return "Professor Full Time " +validation.isOnlyNumerMSGLib;
			}
			if (validation.maxlengthcheck15(full_time_prof) == false) {
				ra.addAttribute("msg", "Professor Full Time " + validation.MaxlengthcheckMSG15);
				return "Professor Full Time " +validation.MaxlengthcheckMSG15;
			}
			if (asso_guest_faculty == null || asso_guest_faculty.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Associate Professors / Readers.");
				return "Please Enter Associate Professors / Readers ";
			}
			if (validation.isOnlyNumerLib(asso_guest_faculty) == false) {
				ra.addAttribute("msg","Associate Professors / Readers" +validation.isOnlyNumerMSGLib);
				return "Associate Professors / Readers " +validation.isOnlyNumerMSGLib;
			}
			if (validation.maxlengthcheck15(asso_guest_faculty) == false) {
				ra.addAttribute("msg", "Associate Professors / Readers " + validation.MaxlengthcheckMSG15);
				return "Associate Professors / Readers " +validation.MaxlengthcheckMSG15;
			}
			if (assis_lect == null || assis_lect.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Assistant Professors / Lecturers");
				return "Please Enter Assistant Professors / Lecturers";
			}
			if (validation.isOnlyNumerLib(assis_lect) == false) {
				ra.addAttribute("msg","Assistant Professors / Lecturers  " +validation.isOnlyNumerMSGLib);
				return "Assistant Professors / Lecturers  " +validation.isOnlyNumerMSGLib;
			}
			if (validation.maxlengthcheck15(assis_lect) == false) {
				ra.addAttribute("msg", "Assistant Professors / Lecturers" + validation.MaxlengthcheckMSG15);
				return "Assistant Professors / Lecturers " +validation.MaxlengthcheckMSG15;
			}
			if (total_teaching_staff == null || total_teaching_staff.trim().equals("")) {
				ra.addAttribute("msg", "Total Teaching Staff ");
				return "Total Teaching Staff ";
			}
			if (validation.isOnlyNumerLib(total_teaching_staff) == false) {
				ra.addAttribute("msg","Total Teaching Staff   " +validation.isOnlyNumerMSGLib);
				return "Total Teaching Staff   " +validation.isOnlyNumerMSGLib;
			}
			if (validation.maxlengthcheck15(total_teaching_staff) == false) {
				ra.addAttribute("msg", "Total Teaching Staff " + validation.MaxlengthcheckMSG15);
				return "Total Teaching Staff " +validation.MaxlengthcheckMSG15;
			}
			if (each_total == null || each_total.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Total");
				return "Please Enter Total";
			}
			if (validation.isOnlyNumerLib(each_total) == false) {
				ra.addAttribute("msg","Total  " +validation.isOnlyNumerMSGLib);
				return "Total  " +validation.isOnlyNumerMSGLib;
			}
			if (validation.maxlengthcheck15(each_total) == false) {
				ra.addAttribute("msg", "Total" + validation.MaxlengthcheckMSG15);
				return "Total " +validation.MaxlengthcheckMSG15;
			}
			
			PG_detail.setDepartment(department);
			PG_detail.setDepartment_id(Integer.parseInt(department_id));
			PG_detail.setFull_time_prof(Integer.parseInt(full_time_prof));
			PG_detail.setAsso_guest_faculty(Integer.parseInt(asso_guest_faculty));
			PG_detail.setAssis_lect(Integer.parseInt(assis_lect));
			PG_detail.setTotal_teaching_staff(Integer.parseInt(total_teaching_staff));
			PG_detail.setEach_total(Integer.parseInt(each_total));
			PG_detail.setCreated_by(Integer.parseInt(userid));
			PG_detail.setS_id(Integer.parseInt(s_id2));
			PG_detail.setInstitute_id(Integer.parseInt(institute_id));
//			PG_detail.setCreated_date(date);
			
				if (Integer.parseInt(hid_staff_info_pg) == 0) {
//					PG_detail.setP_id(Integer.parseInt(p_hid_constructed_area));
					int hid_staff_info_pg1= (Integer) sessionHQL.save(PG_detail);
					sessionHQL.flush();
					sessionHQL.clear();
//					tx.commit();
//					return String.valueOf(hid_staff_info_pg1) ;
			}
				else {

					CLG_REG_TEACHING_STAFF_PG PG_detail_pg = (CLG_REG_TEACHING_STAFF_PG) sessionHQL
							.get(CLG_REG_TEACHING_STAFF_PG.class, Integer.parseInt(hid_staff_info_pg));
					
					PG_detail_pg.setDepartment(department);
					PG_detail_pg.setDepartment_id(Integer.parseInt(department_id));
					PG_detail_pg.setFull_time_prof(Integer.parseInt(full_time_prof));
					PG_detail_pg.setAsso_guest_faculty(Integer.parseInt(asso_guest_faculty));
					PG_detail_pg.setAssis_lect(Integer.parseInt(assis_lect));
					PG_detail_pg.setTotal_teaching_staff(Integer.parseInt(total_teaching_staff));
					PG_detail_pg.setEach_total(Integer.parseInt(each_total));
					PG_detail_pg.setS_id(Integer.parseInt(s_id2));
					PG_detail_pg.setModified_by(Integer.parseInt(userid));
//					PG_detail_pg.setModified_date(date);
					sessionHQL.update(PG_detail_pg);
					sessionHQL.flush();
					sessionHQL.clear();
//					tx.commit();
					
				}
				}
				

				//Transaction tx2 = sessionHQL.beginTransaction();
				ArrayList<ArrayList<String>> post_list= SIDao.getAll_post_name();
				String s_id3 = session.getAttribute("super_id").toString();
				System.out.println("post_list "+post_list);
				CLG_REG_NON_TEACHING_STAFF_INFO post_detail =new CLG_REG_NON_TEACHING_STAFF_INFO();
				
					for (int i = 0; i < post_list.size(); i++) {
						
				String hid_staff_info_nonteaching = request.getParameter("hid_staff_info_nonteaching"+post_list.get(i).get(0));	
				String post_name = post_list.get(i).get(1);
				String post_id = post_list.get(i).get(0);
//				String post = request.getParameter("post"+post_list.get(i).get(0));
				String available_staff = request.getParameter("admin_staff"+post_list.get(i).get(0));
				String total_staff = request.getParameter("total-staff");
				
				MultipartFile file = mul.getFile("nonteaching_acquittance1");
				System.err.println("---------file"+file);
				String nonteaching_acquittance = upload_imagemethod(request,file, session,hid_staff_info_nonteaching);
				System.err.println("---------nonteaching_acquittance"+nonteaching_acquittance);
				if (available_staff == null || available_staff.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Available Staff");
					return "Please Enter Available Staff";
				}
				if (validation.isOnlyNumerLib(available_staff) == false) {
					ra.addAttribute("msg","Available Staff " +validation.isOnlyNumerMSGLib);
					return "Available Staff " +validation.isOnlyNumerMSGLib;
				}
				
				if (validation.maxlengthcheck15(available_staff) == false) {
					ra.addAttribute("msg", "Available Staff " + validation.MaxlengthcheckMSG15);
					return "Available Staff  " +validation.MaxlengthcheckMSG15;
				}		
				if (validation.maxlengthcheck15(total_staff) == false) {
					ra.addAttribute("msg", "Total Staff " + validation.MaxlengthcheckMSG15);
					return "Total Staff  " +validation.MaxlengthcheckMSG15;
				}	
				if (nonteaching_acquittance == null || nonteaching_acquittance.trim().equals("")) {
					ra.addAttribute("msg", "Please Upload Stamp Acquittance Role of Non-teaching Staff");
					return "Please Upload Stamp Acquittance Role of Non-teaching Staff";
				}
			
				post_detail.setPost(post_name);
				post_detail.setAvailable_staff(available_staff);
				post_detail.setTotal_staff(Integer.parseInt(total_staff));
				post_detail.setNonteaching_acquittance(nonteaching_acquittance);
				post_detail.setPost_id(Integer.parseInt(post_id));
				post_detail.setCreated_by(Integer.parseInt(userid));
				post_detail.setS_id(Integer.parseInt(s_id3));
				post_detail.setInstitute_id(Integer.parseInt(institute_id));
				Date date = new Date();
					if (Integer.parseInt(hid_staff_info_nonteaching) == 0) {
//						post_detail.setP_id(Integer.parseInt(p_hid_constructed_area));
						int hid_staff_info_nonteaching1= (Integer) sessionHQL.save(post_detail);
						sessionHQL.flush();
						sessionHQL.clear();
//						tx.commit();
//						return String.valueOf(hid_staff_info_nonteaching1) ;
				}
					else {

						CLG_REG_NON_TEACHING_STAFF_INFO post_detail_pg = (CLG_REG_NON_TEACHING_STAFF_INFO) sessionHQL
								.get(CLG_REG_NON_TEACHING_STAFF_INFO.class, Integer.parseInt(hid_staff_info_nonteaching));
						
						post_detail_pg.setPost(post_name);
						post_detail_pg.setPost_id(Integer.parseInt(post_id));
						post_detail_pg.setAvailable_staff(available_staff);
						post_detail_pg.setTotal_staff(Integer.parseInt(total_staff));
						post_detail_pg.setNonteaching_acquittance(nonteaching_acquittance);
						post_detail_pg.setModified_by(Integer.parseInt(userid));
						post_detail_pg.setS_id(Integer.parseInt(s_id3));
						post_detail_pg.setModified_date(date);
						sessionHQL.update(post_detail_pg);
						sessionHQL.flush();
						sessionHQL.clear();
						
						
					}
					}
					CLG_REG_NON_TEACHING_STAFF_INFO post_detail_add =new CLG_REG_NON_TEACHING_STAFF_INFO();
					String total_extra = request.getParameter("total_extra");
					for (int i = 1; i <= Integer.parseInt(total_extra); i++) {
						
						String other_post = request.getParameter("other_post"+i);
						String other_admin_staff = request.getParameter("other_admin_staff"+i);
						String hid_other_post = request.getParameter("hid_other_post"+i);
						String total_staff = request.getParameter("total-staff");
						
						post_detail_add.setPost(other_post);
						post_detail_add.setAvailable_staff(other_admin_staff);
						post_detail_add.setPost_id(0);
						post_detail_add.setTotal_staff(Integer.parseInt(total_staff));
						post_detail_add.setCreated_by(Integer.parseInt(userid));
						post_detail_add.setS_id(Integer.parseInt(s_id3));
						post_detail_add.setInstitute_id(Integer.parseInt(institute_id));
						if (Integer.parseInt(hid_other_post) == 0) {
							if(!other_post.equals("") || !other_admin_staff.equals("")) {
							sessionHQL.save(post_detail_add);
							sessionHQL.flush();
							sessionHQL.clear();
							}
						}
						else {

							CLG_REG_NON_TEACHING_STAFF_INFO post_detail_add_u = (CLG_REG_NON_TEACHING_STAFF_INFO) sessionHQL
									.get(CLG_REG_NON_TEACHING_STAFF_INFO.class, Integer.parseInt(hid_other_post));
							
							post_detail_add_u.setPost(other_post);
							post_detail_add_u.setAvailable_staff(other_admin_staff);
							post_detail_add_u.setPost_id(0);
							post_detail_add_u.setTotal_staff(Integer.parseInt(total_staff));
							post_detail_add_u.setModified_by(Integer.parseInt(userid));
							post_detail_add_u.setS_id(Integer.parseInt(s_id3));
							post_detail_add_u.setInstitute_id(Integer.parseInt(institute_id));
							sessionHQL.update(post_detail_add_u);
							sessionHQL.flush();
							sessionHQL.clear();
							
							
						}
						
					}
					
					
//					tx.commit();
					ArrayList<ArrayList<String>> academic_list= SIDao.getAll_academic_name();
					String s_id4 = session.getAttribute("super_id").toString();
					CLG_REG_LAST_ACADEMIC_YEAR_STAFF_DETAILS academic_detail =new CLG_REG_LAST_ACADEMIC_YEAR_STAFF_DETAILS();
					
						for (int i = 0; i < academic_list.size(); i++) {
							
					String hid_staff_info_academic = request.getParameter("hid_staff_info_academic"+academic_list.get(i).get(0));	
					String teacher_info = academic_list.get(i).get(1);
					String teacher_info_id = academic_list.get(i).get(0);
					String professor = request.getParameter("ret-professer"+academic_list.get(i).get(0));
					String associate_prof = request.getParameter("ret-reader"+academic_list.get(i).get(0));
					String assistant_prof = request.getParameter("ret-lecturers"+academic_list.get(i).get(0));
					String total = request.getParameter("retired_total"+academic_list.get(i).get(0));
					
					
//					String hid_guide_list = request.getParameter("hid_guide_list");
					
					MultipartFile file = mul.getFile("guide_list1");
					String guide_list = upload_imagemethod(request,file, session,hid_staff_info_academic);
					
					
//					String hid_teaching_attendance1 = request.getParameter("hid_teaching_attendance");
					MultipartFile file1 = mul.getFile("teaching_attendance1");
					String teaching_attendance = upload_imagemethod(request,file, session,hid_staff_info_academic);
					
//					String hid_non_teaching_attendance1 = request.getParameter("hid_non_teaching_attendance");
					MultipartFile file2 = mul.getFile("non_teaching_attendance1");
					String non_teaching_attendance = upload_imagemethod(request,file, session,hid_staff_info_academic);
					
					
					if (professor == null || professor.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Professor");
						return "Please Enter Professor";
					}
					if (validation.isOnlyNumerLib(professor) == false) {
						ra.addAttribute("msg","Professor " +validation.isOnlyNumerMSGLib);
						return "Professor " +validation.isOnlyNumerMSGLib;
					}
					if (validation.maxlengthcheck15(professor) == false) {
						ra.addAttribute("msg", "Professor " + validation.MaxlengthcheckMSG15);
						return "Professor  " +validation.MaxlengthcheckMSG15;
					}	
					if (associate_prof == null || associate_prof.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Associate Professors / Readers.");
						return "Please Enter Associate Professors / Readers ";
					}
					if (validation.isOnlyNumerLib(associate_prof) == false) {
						ra.addAttribute("msg","Associate Professors / Readers" +validation.isOnlyNumerMSGLib);
						return "Associate Professors / Readers " +validation.isOnlyNumerMSGLib;
					}
					if (validation.maxlengthcheck15(associate_prof) == false) {
						ra.addAttribute("msg", "Associate Professors / Readers " + validation.MaxlengthcheckMSG15);
						return "Associate Professors / Readers  " +validation.MaxlengthcheckMSG15;
					}	
					if (assistant_prof == null || assistant_prof.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Assistant Professors / Lecturers");
						return "Please Enter Assistant Professors / Lecturers";
					}
					if (validation.isOnlyNumerLib(assistant_prof) == false) {
						ra.addAttribute("msg","Assistant Professors / Lecturers  " +validation.isOnlyNumerMSGLib);
						return "Assistant Professors / Lecturers  " +validation.isOnlyNumerMSGLib;
					}	
					if (validation.maxlengthcheck15(assistant_prof) == false) {
						ra.addAttribute("msg", "Total Staff " + validation.MaxlengthcheckMSG15);
						return "Assistant Professors / Lecturers  " +validation.MaxlengthcheckMSG15;
					}
					if (total == null || total.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Total");
						return "Please Enter Total";
					}
					if (validation.maxlengthcheck15(total) == false) {
						ra.addAttribute("msg", "Total Staff Information" + validation.MaxlengthcheckMSG15);
						return "Total Staff Information  " +validation.MaxlengthcheckMSG15;
					}
					if (guide_list == null || guide_list.trim().equals("")) {
						ra.addAttribute("msg", "Please Upload Eligible Guide list as approved by the University in Last Academic Year Tab");
						return "Please Upload Eligible Guide list as approved by the University in Last Academic Year Tab ";
					}
					if (teaching_attendance == null || teaching_attendance.trim().equals("")) {
						ra.addAttribute("msg", "Upload Attendance Register & biometric attendance of last academic session of Teaching Staff in Last Academic Year Tab.");
						return "Please Upload Attendance Register & biometric attendance of last academic session of Teaching Staff in Last Academic Year Tab";
					}
					if (non_teaching_attendance == null || non_teaching_attendance.trim().equals("")) {
						ra.addAttribute("msg", "Please Upload Attendance Register & biometric attendance of last academic session of Non-teaching Staff in Last Academic Year Tab.");
						return "Please Upload Attendance Register & biometric attendance of last academic session of Non-teaching Staff in Last Academic Year Tab";
					}
					academic_detail.setTeacher_info(teacher_info);
					academic_detail.setProfessor(Integer.parseInt(professor));
					academic_detail.setAssociate_prof(Integer.parseInt(associate_prof));
					academic_detail.setAssistant_prof(Integer.parseInt(assistant_prof));
					academic_detail.setTeacher_info_id(Integer.parseInt(teacher_info_id));
					academic_detail.setTotal(Integer.parseInt(total));
					academic_detail.setGuide_list(guide_list);
					academic_detail.setTeaching_attendance(teaching_attendance);
					academic_detail.setNon_teaching_attendance(non_teaching_attendance);
					academic_detail.setCreated_by(Integer.parseInt(userid));
					academic_detail.setS_id(Integer.parseInt(s_id4));
					academic_detail.setInstitute_id(Integer.parseInt(institute_id));
					Date date = new Date();
						if (Integer.parseInt(hid_staff_info_academic) == 0) {
//							academic_detail.setP_id(Integer.parseInt(p_hid_constructed_area));
							int hid_staff_info_academic1= (Integer) sessionHQL.save(academic_detail);
							sessionHQL.flush();
							sessionHQL.clear();
					}
						else {

							CLG_REG_LAST_ACADEMIC_YEAR_STAFF_DETAILS academic_detail_ad  = (CLG_REG_LAST_ACADEMIC_YEAR_STAFF_DETAILS) sessionHQL
									.get(CLG_REG_LAST_ACADEMIC_YEAR_STAFF_DETAILS.class, Integer.parseInt(hid_staff_info_academic));
							
							academic_detail_ad.setTeacher_info(teacher_info);
							academic_detail_ad.setProfessor(Integer.parseInt(professor));
							academic_detail_ad.setAssociate_prof(Integer.parseInt(associate_prof));
							academic_detail_ad.setAssistant_prof(Integer.parseInt(assistant_prof));
							academic_detail_ad.setTeacher_info_id(Integer.parseInt(teacher_info_id));
							academic_detail_ad.setTotal(Integer.parseInt(total));
							academic_detail_ad.setGuide_list(guide_list);
							academic_detail_ad.setTeaching_attendance(teaching_attendance);
							academic_detail_ad.setNon_teaching_attendance(non_teaching_attendance);
							academic_detail_ad .setModified_by(Integer.parseInt(userid));
							academic_detail_ad.setS_id(Integer.parseInt(s_id4));
							academic_detail_ad .setModified_date(date);
							sessionHQL.update(academic_detail_ad );
							sessionHQL.flush();
							sessionHQL.clear();
							
							
						}
						}
						tx.commit();
					
					
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
	
	
	//FETCH PG DETAILS
	@RequestMapping(value = "admin/getAllPg_Details", method = RequestMethod.POST)
	public @ResponseBody List<CLG_REG_TEACHING_STAFF_PG> getAllPg_Details(HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institute_id = String.valueOf(ea.getInstitute_id());
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL
				.createQuery("from CLG_REG_TEACHING_STAFF_PG where institute_id=:institute_id ");
		
		q.setParameter("institute_id", Integer.parseInt(institute_id));
		@SuppressWarnings("unchecked")
		List<CLG_REG_TEACHING_STAFF_PG> clist = (List<CLG_REG_TEACHING_STAFF_PG>) q.list();
	
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	//FETCH UG DETAILS
		@RequestMapping(value = "admin/getAllUg_Details", method = RequestMethod.POST)
		public @ResponseBody List<CLG_REG_TEACHING_STAFF_UG> getAllUg_Details(HttpSession session) {
			String userid = session.getAttribute("userId_for_jnlp").toString();
			UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
			String institute_id = String.valueOf(ea.getInstitute_id());
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL
					.createQuery("from CLG_REG_TEACHING_STAFF_UG where institute_id=:institute_id ");
			
			q.setParameter("institute_id", Integer.parseInt(institute_id));
			@SuppressWarnings("unchecked")
			List<CLG_REG_TEACHING_STAFF_UG> clist = (List<CLG_REG_TEACHING_STAFF_UG>) q.list();
		
			tx.commit();
			sessionHQL.close();
			return clist;
		}
//		//FETCH UG DETAILS
		@RequestMapping(value = "admin/getAllNonteaching_Details", method = RequestMethod.POST)
		public @ResponseBody List<CLG_REG_NON_TEACHING_STAFF_INFO> getAllNonteaching_Details(HttpSession session) {
			String userid = session.getAttribute("userId_for_jnlp").toString();
			UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
			String institute_id = String.valueOf(ea.getInstitute_id());
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL
					.createQuery("from CLG_REG_NON_TEACHING_STAFF_INFO where institute_id=:institute_id ");
			
			q.setParameter("institute_id", Integer.parseInt(institute_id));
			@SuppressWarnings("unchecked")
			List<CLG_REG_NON_TEACHING_STAFF_INFO> clist = (List<CLG_REG_NON_TEACHING_STAFF_INFO>) q.list();
		
			tx.commit();
			sessionHQL.close();
			return clist;
		}
		
		
//		//FETCH UG DETAILS
		@RequestMapping(value = "admin/getAllAcademic_Details", method = RequestMethod.POST)
		public @ResponseBody List<CLG_REG_LAST_ACADEMIC_YEAR_STAFF_DETAILS> getAllAcademic_Details(HttpSession session) {
			String userid = session.getAttribute("userId_for_jnlp").toString();
			UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
			String institute_id = String.valueOf(ea.getInstitute_id());
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL
					.createQuery("from CLG_REG_LAST_ACADEMIC_YEAR_STAFF_DETAILS where institute_id=:institute_id ");
			
			q.setParameter("institute_id", Integer.parseInt(institute_id));
			@SuppressWarnings("unchecked")
			List<CLG_REG_LAST_ACADEMIC_YEAR_STAFF_DETAILS> clist = (List<CLG_REG_LAST_ACADEMIC_YEAR_STAFF_DETAILS>) q.list();
		
			tx.commit();
			sessionHQL.close();
			return clist;
		}
		
		// SAVE College_Last Financial Year Expenses DETAILS
		@PostMapping(value = "/Staff_Salary_Infor_Action")
		public @ResponseBody String Staff_Salary_Infor_Action(HttpServletRequest request, ModelMap model,
				HttpSession session, Principal principal,
				@RequestParam(value = "bankpay_attachment", required = false) MultipartFile bankpay_attach,
				@RequestParam(value = "gpfdeduct_attachment", required = false) MultipartFile gpfdeduct_attach,
				@RequestParam(value = "cchnorms_attachment", required = false) MultipartFile cchnorms_attach,
				@RequestParam(value = "staff_payscale_attachment", required = false) MultipartFile staff_payscale_attach,
				@RequestParam(value = "payscalegradepay_attachment", required = false) MultipartFile payscalegradepay_attach,
				@RequestParam(value = "ass_pro_payattachment", required = false) MultipartFile ass_pro_payattach,
				@RequestParam(value = "lectass_pro_payattachment", required = false) MultipartFile lectass_pro_payattach,MultipartHttpServletRequest mul,
				RedirectAttributes ra)throws ParseException, IOException {
//			String hid_expenses_dtls = request.getParameter("hid_expenses_dtls");
			if (request.getHeader("Referer") == null) {
				session.invalidate();
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return "redirect:/login";
			}
			String role = session.getAttribute("role").toString();
			String roleid1 = session.getAttribute("roleid").toString();
			DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			String userid = session.getAttribute("userId_for_jnlp").toString();
			String s_id5 = session.getAttribute("super_id").toString();
			UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
			String institute = String.valueOf(ea.getInstitute_id());
			System.err.println("--institute_id--" + institute);
			String msg="";
			
			CLG_REG_STAFF_SALARY_INFORMATION pers_p =new CLG_REG_STAFF_SALARY_INFORMATION();
			
//			String salary_dtls = request.getParameter("salary_dtls");
			String bankpay_attachment1 = "bankpay_attachment";
			String gpfdeduct_attachment1 = "gpfdeduct_attachment";
			String cchnorms_attachment1 = "cchnorms_attachment";
			String staff_payscale_attachment1 = "staff_payscale_attachment";
			String payscalegradepay_attachment1 = "payscalegradepay_attachment";
			String ass_pro_payattachment1 = "ass_pro_payattachment";
			String lectass_pro_payattachment1 = "lectass_pro_payattachment";
			String bankpaycheck = request.getParameter("bankpaycheck");
			String bankpayremarks = request.getParameter("bankpayremarks");
			
			String gpfdeductcheck = request.getParameter("gpfdeductcheck");
			String gpfdeductremarks = request.getParameter("gpfdeductremarks");
			
			String cchnormscheck = request.getParameter("cchnormscheck");
			String cchnormsremarks = request.getParameter("cchnormsremarks");
			
			String staff_payscale = request.getParameter("staff-payscale");
			String staff_payscale_remarks = request.getParameter("staff_payscale_remarks");
			
			String payscalegradepay = request.getParameter("payscalegradepay");
			String payscalegradepay_remarks = request.getParameter("payscalegradepay_remarks");
			
			String ass_pro_pay = request.getParameter("ass-pro-pay");
			String ass_pro_pay_remarks = request.getParameter("ass-pro-pay-remarks");
			
			String lectass_pro_pay = request.getParameter("lectass-pro-pay");
			String lectass_pro_pay_remarks = request.getParameter("lectass-pro-pay-remarks");
			
			String hid_salary_dtls = request.getParameter("hid_salary_dtls");
			String institute_id = SIDao.getInstitute_id(userid).get(0).get(0);
//			String p_hid_salary_dtls = SIDao.getp_idfromuser_id(userid).get(0).get(0);
			
			if (!bankpay_attach.isEmpty()) {
				bankpay_attachment1 = upload_imagemethod(request,bankpay_attach,session, bankpay_attachment1);
			}
			else {
				bankpay_attachment1 = request.getParameter("hid_bankpay_attachment1");
			
			}
			
			if (!gpfdeduct_attach.isEmpty()) {
				gpfdeduct_attachment1 = upload_imagemethod(request,gpfdeduct_attach,session, gpfdeduct_attachment1);
			}
			else {
				gpfdeduct_attachment1 = request.getParameter("hid_gpfdeduct_attachment1");
			
			}
			
			if (!cchnorms_attach.isEmpty()) {
				cchnorms_attachment1 = upload_imagemethod(request,cchnorms_attach,session, cchnorms_attachment1);
			}
			else {
				cchnorms_attachment1 = request.getParameter("hid_cchnorms_attachment1");
			
			}
			
			if (!staff_payscale_attach.isEmpty()) {
				staff_payscale_attachment1 = upload_imagemethod(request,staff_payscale_attach,session,staff_payscale_attachment1);
			}
			else {
				staff_payscale_attachment1 = request.getParameter("hid_staff_payscale_attachment1");
			
			}
			
			if (!payscalegradepay_attach.isEmpty()) {
				payscalegradepay_attachment1 = upload_imagemethod(request,payscalegradepay_attach,session, payscalegradepay_attachment1);
			}
			else {
				payscalegradepay_attachment1 = request.getParameter("hid_payscalegradepay_attachment1");
			
			}
			
			
			if (!ass_pro_payattach.isEmpty()) {
				ass_pro_payattachment1 = upload_imagemethod(request,ass_pro_payattach,session, ass_pro_payattachment1);
			}
			else {
				ass_pro_payattachment1 = request.getParameter("hid_ass_pro_payattachment1");
			
			}
			
			if (!lectass_pro_payattach.isEmpty()) {
				lectass_pro_payattachment1 = upload_imagemethod(request,lectass_pro_payattach,session, lectass_pro_payattachment1);
			}
			else {
				lectass_pro_payattachment1 = request.getParameter("hid_lectass_pro_payattachment1");
			
			}
			if (bankpay_attachment1 == null || bankpay_attachment1.trim().equals("")) {
				ra.addAttribute("msg", "Please Upload Mode of Payment Document");
				return "Please Upload Mode of Payment Document";
			}
			if (gpfdeduct_attachment1 == null || gpfdeduct_attachment1.trim().equals("")) {
				ra.addAttribute("msg", "Please Upload GPF & EPF deducted Document");
				return "Please Upload GPF & EPF deducted Document";
			}
			if (cchnorms_attachment1 == null || cchnorms_attachment1.trim().equals("")) {
				ra.addAttribute("msg", "Please Upload Teachers Promotion Document");
				return "Please Upload of Teachers Promotion Document";
			}
			
			if (payscalegradepay == null || payscalegradepay.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Pay Scale and Grade pay of Professor");
				return "Please Enter of Pay Scale and Grade pay of Professor";
			}
			if (validation.maxlengthcheck100(payscalegradepay) == false) {
				ra.addAttribute("msg", "Pay Scale and Grade pay of Professor" + validation.MaxlengthcheckMSG100);
				return "Pay Scale and Grade pay of Professor  " +validation.MaxlengthcheckMSG100;
			}
			if (payscalegradepay_attachment1 == null || payscalegradepay_attachment1.trim().equals("")) {
				ra.addAttribute("msg", "Please Upload Document of Pay Scale and Grade pay of Professor");
				return "Please Upload Document of Pay Scale and Grade pay of Professor";
			}
			if (staff_payscale == null || staff_payscale.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Pay Scale of teaching staff");
				return "Please Select Pay Scale of teaching staff.";
			}
			if (staff_payscale_attachment1 == null || staff_payscale_attachment1.trim().equals("")) {
				ra.addAttribute("msg", "Please Upload Document of Pay Scale of Teaching Staff");
				return "Please Upload Document of Pay Scale of Teaching Staff";
			}
			
			if (ass_pro_pay == null || ass_pro_pay.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Pay Scale and Grade pay of Reader/Associate Professor");
				return "Please Enter Pay Scale and Grade pay of Reader/Associate Professor";
			}
			if (validation.maxlengthcheck100(ass_pro_pay) == false) {
				ra.addAttribute("msg", "Pay Scale and Grade pay of Reader/Associate Professor" + validation.MaxlengthcheckMSG100);
				return "Pay Scale and Grade pay of Reader/Associate Professor  " +validation.MaxlengthcheckMSG100;
			}
			if (ass_pro_payattachment1 == null || ass_pro_payattachment1.trim().equals("")) {
				ra.addAttribute("msg", "Please Upload Document of Pay Scale and Grade pay of Reader/Associate Professor");
				return "Please Upload Document of Pay Scale and Grade pay of Reader/Associate Professor";
			}
			if (lectass_pro_pay == null || lectass_pro_pay.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Pay Scale and Grade pay of Lecturer/Assistant Professor.");
				return "Please Enter Pay Scale and Grade pay of Lecturer/Assistant Professor";
			}
			if (validation.maxlengthcheck100(lectass_pro_pay) == false) {
				ra.addAttribute("msg", "Pay Scale and Grade pay of Lecturer/Assistant Professor." + validation.MaxlengthcheckMSG100);
				return "Pay Scale and Grade pay of Lecturer/Assistant Professor.  " +validation.MaxlengthcheckMSG100;
			}
			if (lectass_pro_payattachment1 == null || lectass_pro_payattachment1.trim().equals("")) {
				ra.addAttribute("msg", "Please Upload Document of Pay Scale and Grade pay of Lecturer/Assistant Professor");
				return "Please Upload Document of Pay Scale and Grade pay of Lecturer/Assistant Professor";
			}
			
			
			int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
			Date date = new Date();
			String username = principal.getName();
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			

			try {
				CLG_REG_STAFF_SALARY_INFORMATION staff_salary_dtls =new CLG_REG_STAFF_SALARY_INFORMATION();
				
//				staff_salary_dtls.setSalary_dtls(salary_dtls);
				staff_salary_dtls.setBankpaycheck(bankpaycheck);
				staff_salary_dtls.setBankpayremarks(bankpayremarks);
				
				staff_salary_dtls.setGpfdeductcheck(gpfdeductcheck);
				staff_salary_dtls.setGpfdeductremarks(gpfdeductremarks);
				
				staff_salary_dtls.setCchnormscheck(cchnormscheck);
				staff_salary_dtls.setCchnormsremarks(cchnormsremarks);
				
				staff_salary_dtls.setStaff_payscale(staff_payscale);
				staff_salary_dtls.setStaff_payscale_remarks(staff_payscale_remarks);
				
				staff_salary_dtls.setPayscalegradepay(payscalegradepay);
				staff_salary_dtls.setPayscalegradepay_remarks(payscalegradepay_remarks);
				
				staff_salary_dtls.setAss_pro_pay(ass_pro_pay);
				staff_salary_dtls.setAss_pro_pay_remarks(ass_pro_pay_remarks);
				
				staff_salary_dtls.setLectass_pro_pay(lectass_pro_pay);
				staff_salary_dtls.setLectass_pro_pay_remarks(lectass_pro_pay_remarks);
				
				staff_salary_dtls.setBankpay_attachment(bankpay_attachment1);
				staff_salary_dtls.setGpfdeduct_attachment(gpfdeduct_attachment1);
				staff_salary_dtls.setCchnorms_attachment(cchnorms_attachment1);
				staff_salary_dtls.setStaff_payscale_attachment(staff_payscale_attachment1);
				staff_salary_dtls.setPayscalegradepay_attachment(payscalegradepay_attachment1);
				staff_salary_dtls.setAss_pro_payattachment(ass_pro_payattachment1);
				staff_salary_dtls.setLectass_pro_payattachment(lectass_pro_payattachment1);
				
				staff_salary_dtls.setInstitute_id(Integer.parseInt(institute_id));
				staff_salary_dtls.setS_id(Integer.parseInt(s_id5));
				staff_salary_dtls.setCreated_by(Integer.parseInt(userid));
				staff_salary_dtls.setCreated_date(date);
				
					if (Integer.parseInt(hid_salary_dtls) == 0) {
//						staff_salary_dtls.setP_id(Integer.parseInt(p_hid_salary_dtls));
						int hid_salary_dtls1= (Integer) sessionHQL.save(staff_salary_dtls);
						System.err.println("hid_salary_dtls1----------------"+hid_salary_dtls1);
						sessionHQL.flush();
						sessionHQL.clear();
						msg = "Data Saved Successfully";
						tx.commit();
						return String.valueOf(hid_salary_dtls1) ;
				}
					else {

						CLG_REG_STAFF_SALARY_INFORMATION staff_salary_dtls_sd = (CLG_REG_STAFF_SALARY_INFORMATION) sessionHQL
								.get(CLG_REG_STAFF_SALARY_INFORMATION.class, Integer.parseInt(hid_salary_dtls));
						
//						staff_salary_dtls_sd.setSalary_dtls(salary_dtls);
						
						staff_salary_dtls_sd.setBankpaycheck(bankpaycheck);
						staff_salary_dtls_sd.setBankpayremarks(bankpayremarks);
						
						staff_salary_dtls_sd.setGpfdeductcheck(gpfdeductcheck);
						staff_salary_dtls_sd.setGpfdeductremarks(gpfdeductremarks);
						
						staff_salary_dtls_sd.setCchnormscheck(cchnormscheck);
						staff_salary_dtls_sd.setCchnormsremarks(cchnormsremarks);
						
						staff_salary_dtls_sd.setStaff_payscale(staff_payscale);
						staff_salary_dtls_sd.setStaff_payscale_remarks(staff_payscale_remarks);
						
						staff_salary_dtls_sd.setPayscalegradepay(payscalegradepay);
						staff_salary_dtls_sd.setPayscalegradepay_remarks(payscalegradepay_remarks);
						
						staff_salary_dtls_sd.setAss_pro_pay(ass_pro_pay);
						staff_salary_dtls_sd.setAss_pro_pay_remarks(ass_pro_pay_remarks);
						
						staff_salary_dtls_sd.setLectass_pro_pay(lectass_pro_pay);
						staff_salary_dtls_sd.setLectass_pro_pay_remarks(lectass_pro_pay_remarks);
						
						staff_salary_dtls_sd.setBankpay_attachment(bankpay_attachment1);
						staff_salary_dtls_sd.setGpfdeduct_attachment(gpfdeduct_attachment1);
						staff_salary_dtls_sd.setCchnorms_attachment(cchnorms_attachment1);
						staff_salary_dtls_sd.setStaff_payscale_attachment(staff_payscale_attachment1);
						staff_salary_dtls_sd.setPayscalegradepay_attachment(payscalegradepay_attachment1);
						staff_salary_dtls_sd.setAss_pro_payattachment(ass_pro_payattachment1);
						staff_salary_dtls_sd.setLectass_pro_payattachment(lectass_pro_payattachment1);
						
						
						staff_salary_dtls_sd.setInstitute_id(Integer.parseInt(institute_id));
						staff_salary_dtls_sd.setModified_by(Integer.parseInt(userid));
						staff_salary_dtls_sd.setModified_date(date);
						staff_salary_dtls_sd.setS_id(Integer.parseInt(s_id5));
//						staff_salary_dtls.setP_id(Integer.parseInt(p_hid_salary_dtls));
						staff_salary_dtls_sd.setId(Integer.parseInt(hid_salary_dtls));
						sessionHQL.update(staff_salary_dtls_sd);
						sessionHQL.flush();
						sessionHQL.clear();
						msg = "Data Updated Successfully";
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
			return  msg;
		}

			//FETCH COLLEGE COUNCIL DETAILS
				@RequestMapping(value = "admin/getAllSalary_Details", method = RequestMethod.POST)
				public @ResponseBody ArrayList<ArrayList<String>> getAllSalary_Details(HttpSession session) {
					String userid = session.getAttribute("userId_for_jnlp").toString();
					String institute_id = SIDao.getInstitute_id(userid).get(0).get(0);
					return SIDao.getAllSalary_Details(Integer.parseInt(institute_id));
				}
//				@RequestMapping(value = "admin/getAllSalary_Details", method = RequestMethod.POST)
//				public @ResponseBody List<CLG_REG_STAFF_SALARY_INFORMATION> getAllSalary_Details(HttpSession session) {
//					String userid = session.getAttribute("userId_for_jnlp").toString();
//					String p_hid_salary_dtls = SIDao.getp_idfromuser_id(userid).get(0).get(0);
//					Session sessionHQL = sessionFactory.openSession();
//					Transaction tx = sessionHQL.beginTransaction();
//					Query q = sessionHQL.createQuery("from CLG_REG_STAFF_SALARY_INFORMATION where p_id=:p_id ");
//
//					q.setParameter("p_id", Integer.parseInt(p_hid_salary_dtls));
//
//					@SuppressWarnings("unchecked")
//					List<CLG_REG_STAFF_SALARY_INFORMATION> clist = (List<CLG_REG_STAFF_SALARY_INFORMATION>) q.list();
//
//					tx.commit();
//					sessionHQL.close();
//					return clist;
//				}
				
				// SAVE College_Last Financial Year Expenses DETAILS
				@PostMapping(value = "/College_Teacher_Promotion_Action")
				public @ResponseBody String College_Teacher_Promotion_Action(HttpServletRequest request, ModelMap model,
						HttpSession session,Principal principal,MultipartHttpServletRequest mul, RedirectAttributes ra)
						throws ParseException, IOException {
					if (request.getHeader("Referer") == null) {
						session.invalidate();
						model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						return "redirect:/login";
					}
					String role = session.getAttribute("role").toString();
					String roleid1 = session.getAttribute("roleid").toString();
					DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
					String userid = session.getAttribute("userId_for_jnlp").toString();
					UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
					String institute = String.valueOf(ea.getInstitute_id());
					System.err.println("--institute_id--" + institute);
					String s_id = session.getAttribute("super_id").toString();
					String msg="";
					CLG_REG_COLLEGE_TEACHER_PROMOTION pers_p = new CLG_REG_COLLEGE_TEACHER_PROMOTION();
					
					String indno = request.getParameter("indno_library");
					String faculty1 = request.getParameter("faculty"+indno);
					String designation1 = request.getParameter("designation"+indno);
					
					String institute_id = SIDao.getInstitute_id(userid).get(0).get(0);
					String hid_teacherpromo = request.getParameter("hid_teacherpromo"+indno);
					String hid_document1 = request.getParameter("hid_document"+indno);
					System.err.println("HID BANK AUDIT------------"+hid_teacherpromo);
					
					MultipartFile file = mul.getFile("document"+indno);
					
					System.err.println("FILE--------========="+file);
					
					String document = upload_imagemethod(request,file, session, hid_teacherpromo);
					
					System.err.println("UPLOAD ATTACH-------------"+document);
					
//						String institute_id = FDdao.getInstitute_id(Integer.parseInt(userid)).get(0).get(0);
					
					if (faculty1 == null || faculty1.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Name of Faculty");
						return "Please Enter Name of Faculty";
					}
					if (validation.isOnlyAlphabet(faculty1) == false) {
						ra.addAttribute("msg","Name Of Faculty" +validation.isOnlyAlphabetMSG);
						return "Name Of Faculty" +validation.isOnlyAlphabetMSG;
					}
					if (validation.maxlengthcheck100(faculty1) == false) {
						ra.addAttribute("msg", "Name Of Faculty" + validation.MaxlengthcheckMSG100);
						return "Name Of Faculty " +validation.MaxlengthcheckMSG100;
					}
					
					if (document == null || document.trim().equals("")) {
						ra.addAttribute("msg", "Please Upload Document.");
						return "Please Upload Document.";
					}
					int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
					Date date = new Date();
					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();

					
					try {
						CLG_REG_COLLEGE_TEACHER_PROMOTION teacher_dtls = new CLG_REG_COLLEGE_TEACHER_PROMOTION();
				
						teacher_dtls.setDocument(document);
						teacher_dtls.setFaculty(faculty1);
						teacher_dtls.setDesignation(designation1);
						teacher_dtls.setInstitute_id(Integer.parseInt(institute_id));
						teacher_dtls.setS_id(Integer.parseInt(s_id));
						teacher_dtls.setCreated_by(Integer.parseInt(userid));
						teacher_dtls.setCreated_date(date);
						
						if (Integer.parseInt(hid_teacherpromo) == 0) {
							int hid_teacherpromo1 = (Integer) sessionHQL.save(teacher_dtls);
							sessionHQL.flush();
							sessionHQL.clear();
							msg = "Data Saved Successfully";
							tx.commit();
							return String.valueOf(hid_teacherpromo1);


							
						} else {
							CLG_REG_COLLEGE_TEACHER_PROMOTION teacher_dtls_f = (CLG_REG_COLLEGE_TEACHER_PROMOTION) sessionHQL
									.get(CLG_REG_COLLEGE_TEACHER_PROMOTION.class, Integer.parseInt(hid_teacherpromo));
							
							teacher_dtls_f.setFaculty(faculty1);
							teacher_dtls_f.setDesignation(designation1);
							teacher_dtls_f.setDocument(document);
							teacher_dtls_f.setModified_by(Integer.parseInt(userid));
							teacher_dtls_f.setS_id(Integer.parseInt(s_id));
							teacher_dtls_f.setModified_date(date);
							sessionHQL.update(teacher_dtls_f);
							sessionHQL.flush();
							sessionHQL.clear();
							msg = "Data Updated Successfully";
							tx.commit();
						}
						
					} catch (RuntimeException e) {
						e.printStackTrace();
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
					return msg;
				}
				// ADD MORE FETCH FOR Medical Details
				@RequestMapping(value = "admin/getTeacher_Promotion", method = RequestMethod.POST)
				public @ResponseBody List<CLG_REG_COLLEGE_TEACHER_PROMOTION>getTeacher_Promotion(HttpSession session) {
					String userid = session.getAttribute("userId_for_jnlp").toString();
					String institute_id = SIDao.getInstitute_id(userid).get(0).get(0);
					Session sessionHQL = sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					Query q = sessionHQL.createQuery("from CLG_REG_COLLEGE_TEACHER_PROMOTION where institute_id=:institute_id ");

					q.setParameter("institute_id", Integer.parseInt(institute_id));

					@SuppressWarnings("unchecked")
					List<CLG_REG_COLLEGE_TEACHER_PROMOTION> clist = (List<CLG_REG_COLLEGE_TEACHER_PROMOTION>) q.list();

					tx.commit();
					sessionHQL.close();
					return clist;
				}
				

				// ADD MORE DELETE FOR LIBRARIAN DETAILS
				@PostMapping(value = "/delete_teacher_promo")
				public @ResponseBody String delete_teacher_promo(String hid_teacherpromo, HttpSession session1) {

					String msg = "";

					Session session = this.sessionFactory.openSession();
					Transaction tx = session.beginTransaction();
					try {
						String hqlUpdate = "delete from CLG_REG_COLLEGE_TEACHER_PROMOTION where id=:id";
						int app = session.createQuery(hqlUpdate).setInteger("id", Integer.parseInt(hid_teacherpromo)).executeUpdate();
						tx.commit();
						session.close();
						if (app > 0) {
							msg = "Data Deleted Successfully.";
						} else {
							msg = "Data not Deleted.";
						}
					} catch (Exception e) {
						msg = "CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.";
					} finally {

					}
					return msg;
				}
			
				
				public String upload_imagemethod(HttpServletRequest request, MultipartFile mul, HttpSession session, String id)
						throws IOException {

					String extension = ""; // add line
					String fname = ""; // add line

					request.getSession().setAttribute(id, "/srv" + File.separator + "Document");

					MultipartFile file = mul;

					if (!file.getOriginalFilename().isEmpty()) {

						byte[] bytes = file.getBytes();
						String mnhFilePath = session.getAttribute(id).toString();

						File dir = new File(mnhFilePath);
						if (!dir.exists())
							dir.mkdirs();
						String filename = file.getOriginalFilename();

						int j = filename.lastIndexOf('.');
						if (j >= 0) {
							extension = filename.substring(j + 1);
						}
						java.util.Date date1 = new java.util.Date();
						fname = dir.getAbsolutePath()
								+ File.separator + (new Timestamp(date1.getTime())).toString().replace(":", "").toString()
										.replace(".", ".").toString().replace(" ", "").toString().replace("-", "").toString()
								+ id + "." + extension;

						File serverFile = new File(fname);
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
						stream.write(bytes);
						stream.close();

					} else {

					}
					return fname;

				}

			//ADD MORE FETCH FOR CAMERA LOCATION DETAILS
			@RequestMapping(value = "admin/getNonTeaching_Staff_Details", method = RequestMethod.POST)
			public @ResponseBody List<CLG_REG_NON_TEACHING_STAFF_INFO> getNonTeaching_Staff_Details(HttpSession session) {
				String userid = session.getAttribute("userId_for_jnlp").toString();
				UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
				String institute_id = String.valueOf(ea.getInstitute_id());
				ea.setInstitute_id(Integer.parseInt(institute_id));
				Session sessionHQL = sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				Query q = sessionHQL
						.createQuery("from CLG_REG_NON_TEACHING_STAFF_INFO where institute_id=:institute_id and post_id=0");
				
				q.setParameter("institute_id", Integer.parseInt(institute_id));
				
				@SuppressWarnings("unchecked")
				List<CLG_REG_NON_TEACHING_STAFF_INFO> clist = (List<CLG_REG_NON_TEACHING_STAFF_INFO>) q.list();
			
				tx.commit();
				sessionHQL.close();
				return clist;
			}
				
				
	}
