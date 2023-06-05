package com.AyushEdu.controller.Part_one;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_COLLEGE_STAFF_LIST_GUEST_TEACHER;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_COLLEGE_STAFF_LIST_NON_TEACHING_FACULTY;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_COLLEGE_STAFF_LIST_TEACHER;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_COLLEGE_STAFF_LIST_TEACHING_FACULTY;
import com.AyushEdu.Models.Clg_Reg_clg_Infra.CLG_REG_COLLEGE_STAFF_LIST_UPLOAD_DOC;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Infrastructure_DAO;
import com.AyushEdu.dao.Part_One.Clg_Reg_College_Staff_List_DAO;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Dao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Clg_Reg_College_Staff_List_Controller {

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
	private Clg_Reg_College_Staff_List_DAO CSLDao;
	
	@Autowired
	Institution_Basic_Details_Dao ibdao;
	
	@RequestMapping(value = "admin/college_staff_list", method = RequestMethod.GET)
	public ModelAndView college_staff_list(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		String role = session.getAttribute("role").toString();
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institude = String.valueOf(ea.getInstitute_id());
		if(role=="Institute_NCH") {
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		int parent_id = (int) sessionHQL
				.createQuery("select id from CLG_REG_COLLEGE_STAFF_LIST_TEACHER where institute_id=:inst_id")
				.setParameter("inst_id", Integer.parseInt(institude))
				.uniqueResult();
		Mmap.put("parent_id", parent_id);
		}
		Mmap.put("institude", institude);
		
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
		
		
		Mmap.put("full_time_teacher_details", CSLDao.getFull_time_teacher_details(institute_id));
		Mmap.put("getAllFull_Time_Teacher_Details", CSLDao.getAllFull_Time_Teacher_Details(institute_id));
		Mmap.put("guest_teacher_details", CSLDao.getGuest_teacher_details(institute_id));
		Mmap.put("getAllGuest_teacher_details", CSLDao.getAllGuest_teacher_details(institute_id));
		Mmap.put("teaching_faculty_details", CSLDao.getTeaching_Faculty_details(institute_id));
		Mmap.put("getNon_Teaching_Faculty_details", CSLDao.getNon_Teaching_Faculty_details(institute_id));
		Mmap.put("Non_Teaching_Faculty_details", CSLDao.getAllNon_Teaching_Faculty_details(institute_id));
		Mmap.put("GetDocument_Details", CSLDao.GetDocument_Details(institute_id));
		Mmap.put("GetPrinacipal_Name", CSLDao.GetPrinacipal_Name(institute_id));
		Mmap.put("login_name", session.getAttribute("roleloginName").toString());
		Mmap.put("dataforinstnc", ibdao.getinstName_Code(Integer.parseInt(institute_id)));
		
		
		return new ModelAndView("college_staff_list");
	}

	
	//SAVE TEACHER DETAILS
	@PostMapping(value = "/Teacher_Details_Save_Draft_Action")
	public @ResponseBody String Teacher_Details_Save_Draft_Action( 
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
		CLG_REG_COLLEGE_STAFF_LIST_TEACHER pers_p =new CLG_REG_COLLEGE_STAFF_LIST_TEACHER();
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String s_id = session.getAttribute("super_id").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		
		ArrayList<ArrayList<String>> techer_detail_list = CSLDao.getAllFull_Time_Teacher_Details(institute_id);
		
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date date = new Date();
		int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
		Session sessionHQL = this.sessionFactory.openSession();
		
		try {
			
			
			Transaction tx = sessionHQL.beginTransaction();
			CLG_REG_COLLEGE_STAFF_LIST_TEACHER teacher_detail =new CLG_REG_COLLEGE_STAFF_LIST_TEACHER();
			
			for (int i = 0; i < techer_detail_list.size(); i++) {
				
				String teacher_id = techer_detail_list.get(i).get(0);
				String ugpgcheck = request.getParameter("ugpgcheck"+techer_detail_list.get(i).get(0));
				String remark = request.getParameter("remark"+techer_detail_list.get(i).get(0));
				String hid_teacher_details = request.getParameter("hid_teacher_details"+techer_detail_list.get(i).get(0));
				
//				if (remark == null || remark.trim().equals("")) {
//					ra.addAttribute("msg", "Please Enter Remark");
//					return "Please Enter Remark";
//				}
				if (validation.isOnlyAlphabet(remark) == false) {
					ra.addAttribute("msg","Remark" +validation.isOnlyAlphabetMSG);
					return "Remark " +validation.isOnlyAlphabetMSG;
				}
				if (validation.maxlengthcheck100(remark) == false) {
					ra.addAttribute("msg", "Remark" + validation.MaxlengthcheckMSG100);
					return "Remark " +validation.MaxlengthcheckMSG100;
				}
				
				teacher_detail.setTeacher_id(Integer.parseInt(teacher_id));
				teacher_detail.setUg_pg_check(ugpgcheck);
				teacher_detail.setRemark(remark);
				teacher_detail.setInstitute_id(Integer.parseInt(institute_id));
				teacher_detail.setS_id(Integer.parseInt(s_id));
				teacher_detail.setCreated_by(Integer.parseInt(userid));
				teacher_detail.setCreated_date(date);
				
				
				if (Integer.parseInt(hid_teacher_details) == 0) {
					int hid_teacher_details1= (Integer) sessionHQL.save(teacher_detail);
					sessionHQL.flush();
					sessionHQL.clear();
				}
				else {
					CLG_REG_COLLEGE_STAFF_LIST_TEACHER teacher_detail_u = (CLG_REG_COLLEGE_STAFF_LIST_TEACHER) sessionHQL
							.get(CLG_REG_COLLEGE_STAFF_LIST_TEACHER.class, Integer.parseInt(hid_teacher_details));
					
					teacher_detail_u.setTeacher_id(Integer.parseInt(teacher_id));
					teacher_detail_u.setUg_pg_check(ugpgcheck);
					teacher_detail_u.setRemark(remark);
					teacher_detail_u.setInstitute_id(Integer.parseInt(institute_id));
					teacher_detail_u.setS_id(Integer.parseInt(s_id));
					teacher_detail_u.setModified_by(Integer.parseInt(userid));
					teacher_detail_u.setModified_date(date);
					sessionHQL.update(teacher_detail_u);
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
	
	
	//FETCH TEACHER DETAILS
	@RequestMapping(value = "admin/getTeacher_Details", method = RequestMethod.POST)
	public @ResponseBody List<CLG_REG_COLLEGE_STAFF_LIST_TEACHER> getTeacher_Details(HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL
				.createQuery("from CLG_REG_COLLEGE_STAFF_LIST_TEACHER where institute_id=:institute_id ");
		
		q.setParameter("institute_id", Integer.parseInt(institute_id));
		@SuppressWarnings("unchecked")
		List<CLG_REG_COLLEGE_STAFF_LIST_TEACHER> clist = (List<CLG_REG_COLLEGE_STAFF_LIST_TEACHER>) q.list();
	
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	
	//SAVE GUEST TEACHER DETAILS
	@PostMapping(value = "/Guest_Teacher_Details_Save_Draft_Action")
	public @ResponseBody String Guest_Teacher_Details_Save_Draft_Action( 
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,MultipartHttpServletRequest mul,
			RedirectAttributes ra) throws IOException, ParseException {

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
		CLG_REG_COLLEGE_STAFF_LIST_GUEST_TEACHER pers_p =new CLG_REG_COLLEGE_STAFF_LIST_GUEST_TEACHER();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String s_id = session.getAttribute("super_id").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		
		ArrayList<ArrayList<String>> guest_techer_detail_list = CSLDao.getAllGuest_teacher_details(institute_id);
		
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date date = new Date();
		int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		
		try {
			
			
			Transaction tx = sessionHQL.beginTransaction();
			CLG_REG_COLLEGE_STAFF_LIST_GUEST_TEACHER guest_teacher_detail =new CLG_REG_COLLEGE_STAFF_LIST_GUEST_TEACHER();
			
			
			for (int i = 0; i < guest_techer_detail_list.size(); i++) {
				MultipartFile file = mul.getFile("guest_teacher_doc"+guest_techer_detail_list.get(i).get(0));
				String guest_teacher_id = guest_techer_detail_list.get(i).get(0);
				String upload_attachment = "guest_teacher_doc"+guest_techer_detail_list.get(i).get(0);
//				String upload_attachment = upload_imagemethod(request,file, session, guest_teacher_doc);
				String guest_teacher_remark = request.getParameter("guest_teacher_remark"+guest_techer_detail_list.get(i).get(0));
				String hid_guest_teacher_details = request.getParameter("hid_guest_teacher_details"+guest_techer_detail_list.get(i).get(0));
				
				if (!file.isEmpty()) {
					upload_attachment = upload_imagemethod(request,file, session, upload_attachment);
				}
				else {
					upload_attachment = request.getParameter("hid_guest_teacher_doc"+guest_techer_detail_list.get(i).get(0));
				}
				
				if (upload_attachment == null || upload_attachment.trim().equals("")) {
					ra.addAttribute("msg", "Please Upload Document");
					return "Please Upload Document";
				}
				if (guest_teacher_remark == null || guest_teacher_remark.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Remark");
					return "Please Enter Remark";
				}
				if (validation.isOnlyAlphabet(guest_teacher_remark) == false) {
					ra.addAttribute("msg","Remark" +validation.isOnlyAlphabetMSG);
					return "Remark " +validation.isOnlyAlphabetMSG;
				}
				if (validation.maxlengthcheck100(guest_teacher_remark) == false) {
					ra.addAttribute("msg", "Remark" + validation.MaxlengthcheckMSG100);
					return "Remark " +validation.MaxlengthcheckMSG100;
				}
				
				guest_teacher_detail.setGuest_teacher_id(Integer.parseInt(guest_teacher_id));
				guest_teacher_detail.setGuest_teacher_doc(upload_attachment);
				guest_teacher_detail.setGuest_teacher_remark(guest_teacher_remark);
				guest_teacher_detail.setInstitute_id(Integer.parseInt(institute_id));
				guest_teacher_detail.setS_id(Integer.parseInt(s_id));
				guest_teacher_detail.setCreated_by(Integer.parseInt(userid));
				guest_teacher_detail.setCreated_date(date);
				
				
				if (Integer.parseInt(hid_guest_teacher_details) == 0) {
					int hid_teacher_details1= (Integer) sessionHQL.save(guest_teacher_detail);
					sessionHQL.flush();
					sessionHQL.clear();
				}
				else {
					CLG_REG_COLLEGE_STAFF_LIST_GUEST_TEACHER guest_teacher_detail_u = (CLG_REG_COLLEGE_STAFF_LIST_GUEST_TEACHER) sessionHQL
							.get(CLG_REG_COLLEGE_STAFF_LIST_GUEST_TEACHER.class, Integer.parseInt(hid_guest_teacher_details));
					
					guest_teacher_detail_u.setGuest_teacher_id(Integer.parseInt(guest_teacher_id));
					guest_teacher_detail_u.setGuest_teacher_doc(upload_attachment);
					guest_teacher_detail_u.setGuest_teacher_remark(guest_teacher_remark);
					guest_teacher_detail_u.setInstitute_id(Integer.parseInt(institute_id));
					guest_teacher_detail_u.setS_id(Integer.parseInt(s_id));
					guest_teacher_detail_u.setModified_by(Integer.parseInt(userid));
					guest_teacher_detail_u.setModified_date(date);
					sessionHQL.update(guest_teacher_detail_u);
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
	
	
	//FETCH GUEST TEACHER DETAILS
	@RequestMapping(value = "admin/getGuest_Teacher_Details", method = RequestMethod.POST)
	public @ResponseBody List<CLG_REG_COLLEGE_STAFF_LIST_GUEST_TEACHER> getGuest_Teacher_Details(HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL
				.createQuery("from CLG_REG_COLLEGE_STAFF_LIST_GUEST_TEACHER where institute_id=:institute_id ");
		
		q.setParameter("institute_id", Integer.parseInt(institute_id));
		@SuppressWarnings("unchecked")
		List<CLG_REG_COLLEGE_STAFF_LIST_GUEST_TEACHER> clist = (List<CLG_REG_COLLEGE_STAFF_LIST_GUEST_TEACHER>) q.list();
	
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	
	//SAVE TEACHING FACULTY DETAILS
	@PostMapping(value = "/Teaching_Staff_Details_Save_Draft_Action")
	public @ResponseBody String Teaching_Staff_Details_Save_Draft_Action( 
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,MultipartHttpServletRequest mul,
			RedirectAttributes ra) throws IOException, ParseException {

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
		CLG_REG_COLLEGE_STAFF_LIST_TEACHING_FACULTY pers_p =new CLG_REG_COLLEGE_STAFF_LIST_TEACHING_FACULTY();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		String s_id = session.getAttribute("super_id").toString();
		
		ArrayList<ArrayList<String>> teaching_faculty_list = CSLDao.getAllFull_Time_Teacher_Details(institute_id);
		
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date date = new Date();
		int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		
		try {
			
			
			Transaction tx = sessionHQL.beginTransaction();
			CLG_REG_COLLEGE_STAFF_LIST_TEACHING_FACULTY teaching_faculty_detail =new CLG_REG_COLLEGE_STAFF_LIST_TEACHING_FACULTY();
			
			
			for (int i = 0; i < teaching_faculty_list.size(); i++) {
				
				MultipartFile file = mul.getFile("teacher_doc"+teaching_faculty_list.get(i).get(0));
				String teacher_id = teaching_faculty_list.get(i).get(0);
				String upload_attachment = "teacher_doc"+teaching_faculty_list.get(i).get(0);
				String teacher_remark = request.getParameter("teacher_remark"+teaching_faculty_list.get(i).get(0));
				String hid_teaching_faculty_details = request.getParameter("hid_teaching_faculty_details"+teaching_faculty_list.get(i).get(0));
				
				if (!file.isEmpty()) {
					upload_attachment = upload_imagemethod(request,file, session, upload_attachment);
				}
				else {
					upload_attachment = request.getParameter("hid_teacher_doc"+teaching_faculty_list.get(i).get(0));
				}
				
				if (upload_attachment == null || upload_attachment.trim().equals("")) {
					ra.addAttribute("msg", "Please Upload Document");
					return "Please Upload Document";
				}
				if (teacher_remark == null || teacher_remark.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Remark");
					return "Please Enter Remark";
				}
				if (validation.isOnlyAlphabet(teacher_remark) == false) {
					ra.addAttribute("msg","Remark" +validation.isOnlyAlphabetMSG);
					return "Remark " +validation.isOnlyAlphabetMSG;
				}
				if (validation.maxlengthcheck100(teacher_remark) == false) {
					ra.addAttribute("msg", "Remark" + validation.MaxlengthcheckMSG100);
					return "Remark " +validation.MaxlengthcheckMSG100;
				}
				
				
				teaching_faculty_detail.setTeacher_id(Integer.parseInt(teacher_id));
				teaching_faculty_detail.setTeacher_doc(upload_attachment);
				teaching_faculty_detail.setTeacher_remark(teacher_remark);
				teaching_faculty_detail.setInstitute_id(Integer.parseInt(institute_id));
				teaching_faculty_detail.setS_id(Integer.parseInt(s_id));
				teaching_faculty_detail.setCreated_by(Integer.parseInt(userid));
				teaching_faculty_detail.setCreated_date(date);
				
				
				if (Integer.parseInt(hid_teaching_faculty_details) == 0) {
					int hid_teaching_faculty_details1= (Integer) sessionHQL.save(teaching_faculty_detail);
					sessionHQL.flush();
					sessionHQL.clear();
				}
				else {
					CLG_REG_COLLEGE_STAFF_LIST_TEACHING_FACULTY teaching_faculty_detail_u = (CLG_REG_COLLEGE_STAFF_LIST_TEACHING_FACULTY) sessionHQL
							.get(CLG_REG_COLLEGE_STAFF_LIST_TEACHING_FACULTY.class, Integer.parseInt(hid_teaching_faculty_details));
					
					teaching_faculty_detail_u.setTeacher_id(Integer.parseInt(teacher_id));
					teaching_faculty_detail_u.setTeacher_doc(upload_attachment);
					teaching_faculty_detail_u.setTeacher_remark(teacher_remark);
					teaching_faculty_detail_u.setInstitute_id(Integer.parseInt(institute_id));
					teaching_faculty_detail_u.setS_id(Integer.parseInt(s_id));
					teaching_faculty_detail_u.setModified_by(Integer.parseInt(userid));
					teaching_faculty_detail_u.setModified_date(date);
					sessionHQL.update(teaching_faculty_detail_u);
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
	
	
	//FETCH TEACHING FACULTY DETAILS
	@RequestMapping(value = "admin/getTeaching_Staff_Details", method = RequestMethod.POST)
	public @ResponseBody List<CLG_REG_COLLEGE_STAFF_LIST_TEACHING_FACULTY> getTeaching_Staff_Details(HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL
				.createQuery("from CLG_REG_COLLEGE_STAFF_LIST_TEACHING_FACULTY where institute_id=:institute_id ");
		
		q.setParameter("institute_id", Integer.parseInt(institute_id));
		@SuppressWarnings("unchecked")
		List<CLG_REG_COLLEGE_STAFF_LIST_TEACHING_FACULTY> clist = (List<CLG_REG_COLLEGE_STAFF_LIST_TEACHING_FACULTY>) q.list();
	
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	
	//SAVE NON TEACHING FACULTY DETAILS
	@PostMapping(value = "/Non_Teaching_Faculty_Details_Save_Draft_Action")
	public @ResponseBody String Non_Teaching_Faculty_Details_Save_Draft_Action( 
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,MultipartHttpServletRequest mul,
			RedirectAttributes ra) throws IOException, ParseException {

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
		CLG_REG_COLLEGE_STAFF_LIST_NON_TEACHING_FACULTY pers_p =new CLG_REG_COLLEGE_STAFF_LIST_NON_TEACHING_FACULTY();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		String s_id = session.getAttribute("super_id").toString();
		
		ArrayList<ArrayList<String>>non_teaching_faculty_list = CSLDao.getAllNon_Teaching_Faculty_details(institute_id);
		
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date date = new Date();
		int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
		String username = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		
		try {
			
			
			Transaction tx = sessionHQL.beginTransaction();
			CLG_REG_COLLEGE_STAFF_LIST_NON_TEACHING_FACULTY non_teaching_faculty_detail =new CLG_REG_COLLEGE_STAFF_LIST_NON_TEACHING_FACULTY();
			
			
			for (int i = 0; i < non_teaching_faculty_list.size(); i++) {
				
				MultipartFile file = mul.getFile("non_teacher_doc"+non_teaching_faculty_list.get(i).get(0));
				String non_teacher_id = non_teaching_faculty_list.get(i).get(0);
				String upload_attachment = "non_teacher_doc"+non_teaching_faculty_list.get(i).get(0);
				String teacher_remark = request.getParameter("non_teacher_remark"+non_teaching_faculty_list.get(i).get(0));
				String hid_non_teaching_faculty_details = request.getParameter("hid_non_teaching_faculty_details"+non_teaching_faculty_list.get(i).get(0));
				
				if (!file.isEmpty()) {
					upload_attachment = upload_imagemethod(request,file, session, upload_attachment);
				}
				else {
					upload_attachment = request.getParameter("hid_non_teacher_doc"+non_teaching_faculty_list.get(i).get(0));
				}
				
				
				if (upload_attachment == null || upload_attachment.trim().equals("")) {
					ra.addAttribute("msg", "Please Upload Document");
					return "Please Upload Document";
				}
				if (teacher_remark == null || teacher_remark.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Remark");
					return "Please Enter Remark";
				}
				if (validation.isOnlyAlphabet(teacher_remark) == false) {
					ra.addAttribute("msg","Remark" +validation.isOnlyAlphabetMSG);
					return "Remark " +validation.isOnlyAlphabetMSG;
				}
				if (validation.maxlengthcheck100(teacher_remark) == false) {
					ra.addAttribute("msg", "Remark" + validation.MaxlengthcheckMSG100);
					return "Remark " +validation.MaxlengthcheckMSG100;
				}
				
				non_teaching_faculty_detail.setNon_teacher_id(Integer.parseInt(non_teacher_id));
				non_teaching_faculty_detail.setNon_teacher_doc(upload_attachment);
				non_teaching_faculty_detail.setNon_teacher_remark(teacher_remark);
				non_teaching_faculty_detail.setInstitute_id(Integer.parseInt(institute_id));
				non_teaching_faculty_detail.setS_id(Integer.parseInt(s_id));
				non_teaching_faculty_detail.setCreated_by(Integer.parseInt(userid));
				non_teaching_faculty_detail.setCreated_date(date);
				
				
				if (Integer.parseInt(hid_non_teaching_faculty_details) == 0) {
					int hid_teaching_faculty_details1= (Integer) sessionHQL.save(non_teaching_faculty_detail);
					sessionHQL.flush();
					sessionHQL.clear();
				}
				else {
					CLG_REG_COLLEGE_STAFF_LIST_NON_TEACHING_FACULTY non_teaching_faculty_detail_u = (CLG_REG_COLLEGE_STAFF_LIST_NON_TEACHING_FACULTY) sessionHQL
							.get(CLG_REG_COLLEGE_STAFF_LIST_NON_TEACHING_FACULTY.class, Integer.parseInt(hid_non_teaching_faculty_details));
					
					non_teaching_faculty_detail_u.setNon_teacher_id(Integer.parseInt(non_teacher_id));
					non_teaching_faculty_detail_u.setNon_teacher_doc(upload_attachment);
					non_teaching_faculty_detail_u.setNon_teacher_remark(teacher_remark);
					non_teaching_faculty_detail_u.setInstitute_id(Integer.parseInt(institute_id));
					non_teaching_faculty_detail_u.setS_id(Integer.parseInt(s_id));
					non_teaching_faculty_detail_u.setModified_by(Integer.parseInt(userid));
					non_teaching_faculty_detail_u.setModified_date(date);
					sessionHQL.update(non_teaching_faculty_detail_u);
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
	
	
	//FETCH NON TEACHING FACULTY DETAILS
	@RequestMapping(value = "admin/getNon_Teaching_Faculty_Details", method = RequestMethod.POST)
	public @ResponseBody List<CLG_REG_COLLEGE_STAFF_LIST_NON_TEACHING_FACULTY> getNon_Teaching_Faculty_Details(HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL
				.createQuery("from CLG_REG_COLLEGE_STAFF_LIST_NON_TEACHING_FACULTY where institute_id=:institute_id ");
		
		q.setParameter("institute_id", Integer.parseInt(institute_id));
		@SuppressWarnings("unchecked")
		List<CLG_REG_COLLEGE_STAFF_LIST_NON_TEACHING_FACULTY> clist = (List<CLG_REG_COLLEGE_STAFF_LIST_NON_TEACHING_FACULTY>) q.list();
	
		tx.commit();
		sessionHQL.close();
		return clist;
	}
	
	

	
	
	//SAVE DECLARATION CHECK
	@PostMapping(value = "/Declaration_check_Save_Draft_Action")
	public @ResponseBody String Declaration_check_Save_Draft_Action( 
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra) throws IOException, ParseException {

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
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		String s_id = session.getAttribute("super_id").toString();
		
		String hid_declaration_detail = request.getParameter("hid_declaration_detail");
		
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date date = new Date();
		Session sessionHQL = this.sessionFactory.openSession();
		
		try {
			
			Transaction tx = sessionHQL.beginTransaction();
			CLG_REG_COLLEGE_STAFF_LIST_UPLOAD_DOC upload_doc_detail =new CLG_REG_COLLEGE_STAFF_LIST_UPLOAD_DOC();
				
				upload_doc_detail.setDeclaration_check(1);
				upload_doc_detail.setInstitute_id(Integer.parseInt(institute_id));
				upload_doc_detail.setS_id(Integer.parseInt(s_id));
				upload_doc_detail.setCreated_by(Integer.parseInt(userid));
				upload_doc_detail.setCreated_date(date);
				
				if (Integer.parseInt(hid_declaration_detail) == 0) {
					int hid_declaration_detail1= (Integer) sessionHQL.save(upload_doc_detail);
					sessionHQL.flush();
					sessionHQL.clear();
					tx.commit();
					return String.valueOf(hid_declaration_detail1) ;
				}
				else {
					CLG_REG_COLLEGE_STAFF_LIST_UPLOAD_DOC upload_doc_detail_u = (CLG_REG_COLLEGE_STAFF_LIST_UPLOAD_DOC) sessionHQL
							.get(CLG_REG_COLLEGE_STAFF_LIST_UPLOAD_DOC.class, Integer.parseInt(hid_declaration_detail));
					
					upload_doc_detail_u.setDeclaration_check(1);
					upload_doc_detail_u.setInstitute_id(Integer.parseInt(institute_id));
					upload_doc_detail_u.setS_id(Integer.parseInt(s_id));
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
	
	
	//FETCH UPLOAD DOCUMENT DETAILS
	@RequestMapping(value = "admin/getDocument_Details", method = RequestMethod.POST)
	public @ResponseBody List<CLG_REG_COLLEGE_STAFF_LIST_UPLOAD_DOC> getDocument_Details(HttpSession session) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = CIDao.getInstitute_id(userid).get(0).get(0);
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		Query q = sessionHQL
				.createQuery("from CLG_REG_COLLEGE_STAFF_LIST_UPLOAD_DOC where institute_id=:institute_id ");
		
		q.setParameter("institute_id", Integer.parseInt(institute_id));
		@SuppressWarnings("unchecked")
		List<CLG_REG_COLLEGE_STAFF_LIST_UPLOAD_DOC> clist = (List<CLG_REG_COLLEGE_STAFF_LIST_UPLOAD_DOC>) q.list();
	
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
	
	
	@RequestMapping(value = "/AttachmentFilePath", method = RequestMethod.GET)
	public void AttachmentFilePath(@ModelAttribute("i_id") String id, @ModelAttribute("doc_id") String doc_id,ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		
//		System.err.println("chgukhdfguhkdfhgkjfffffffffff---" + id);

		final int BUFFER_SIZE = 4096;

		String i_id = id;
		
		String filePath = CSLDao.getAttachmentFilePath(i_id,doc_id);

//		System.out.println("chgukhdfguhkdfhgkj---" + filePath);

		model.put("filePath", filePath);

		ServletContext context = request.getSession().getServletContext();

		try {

			if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

				String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
//				request.getRealPath("/") + "/srv/Document/No_Image.jpg";

				File downloadFile = new File(fullPath);

				FileInputStream inputStream = new FileInputStream(downloadFile);

				String mimeType = context.getMimeType(fullPath);

				if (mimeType == null) {

					mimeType = "application/octet-stream";

				}

				response.setContentType(mimeType);

				response.setContentLength((int) downloadFile.length());

				OutputStream outStream = response.getOutputStream();

				byte[] buffer = new byte[BUFFER_SIZE];

				int bytesRead = -1;

				while ((bytesRead = inputStream.read(buffer)) != -1) {

					outStream.write(buffer, 0, bytesRead);

				}

				inputStream.close();

				outStream.close();

			} else {

				String fullPath = filePath;

				File downloadFile = new File(fullPath);

				FileInputStream inputStream = new FileInputStream(downloadFile);

				String mimeType = context.getMimeType(fullPath);

				if (mimeType == null) {

					mimeType = "application/octet-stream";

				}

				response.setContentType(mimeType);

				response.setContentLength((int) downloadFile.length());

				OutputStream outStream = response.getOutputStream();

				byte[] buffer = new byte[BUFFER_SIZE];

				int bytesRead = -1;

				while ((bytesRead = inputStream.read(buffer)) != -1) {

					outStream.write(buffer, 0, bytesRead);

				}

				inputStream.close();

				outStream.close();

			}

		} catch (Exception ex) {

			System.out.println(ex);
			
		//	admin//js//img//No_Image.jpg
			
			
			
			String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
//					request.getRealPath("/") + "/srv/Document/No_Image.jpg";
			File downloadFile = new File(fullPath);

			FileInputStream inputStream = new FileInputStream(downloadFile);

			String mimeType = context.getMimeType(fullPath);

			if (mimeType == null) {

				mimeType = "application/octet-stream";

			}

			response.setContentType(mimeType);

			response.setContentLength((int) downloadFile.length());

			OutputStream outStream = response.getOutputStream();

			byte[] buffer = new byte[BUFFER_SIZE];

			int bytesRead = -1;

			while ((bytesRead = inputStream.read(buffer)) != -1) {

				outStream.write(buffer, 0, bytesRead);

			}

			inputStream.close();

			outStream.close();
		}
	}

}
