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
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
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
import com.AyushEdu.Models.Clg_Reg_Hospital_Details.CLG_REG_HOSPITAL_AUXILLARY_MEDICAL_STAFF_DETAILS;
import com.AyushEdu.Models.Clg_Reg_Hospital_Details.CLG_REG_HOSPITAL_MEDICAL_STAFF_DETAILS;
import com.AyushEdu.Models.Clg_Reg_Hospital_Details.CLG_REG_HOSPITAL_PARAMEDICAL_STAFF_DETAILS;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Part_One.Clg_Reg_Hospital_Staff_Dtls_Dao;
import com.AyushEdu.dao.Part_One.Institution_Basic_Details_Dao;
import com.AyushEdu.validation.ValidationController;
@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Clg_Reg_Hospital_Staff_Details_Controller {
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
	private Clg_Reg_Hospital_Staff_Dtls_Dao HSDao;
	
	@Autowired
	Institution_Basic_Details_Dao ibdao;
	
	@RequestMapping(value = "admin/hospital_staff_list", method = RequestMethod.GET)
	public ModelAndView hospital_staff_list(ModelMap Mmap, HttpSession session,
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

		String role = session.getAttribute("role").toString();
		String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		System.err.println("--institute_id--" + institute_id);
		
		UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
		String institude = String.valueOf(ea.getInstitute_id());
		
		if(role=="Institute_NCH") {
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			int parent_id = (int) sessionHQL
					.createQuery("select id from CLG_REG_HOSPITAL_MEDICAL_STAFF_DETAILS where institute_id=:inst_id")
					.setParameter("inst_id", Integer.parseInt(institude))
					.uniqueResult();
			Mmap.put("parent_id", parent_id);
			}
		Mmap.put("institude", institude);
		
		Mmap.put("msg", msg);
		Mmap.put("dataparent_medical", HSDao.getAllhospitaldetails(Integer.parseInt(institute_id)));
		Mmap.put("dataparent_paramedical", HSDao.getAllhospital_Paramedical_details(Integer.parseInt(institute_id)));
		Mmap.put("dataparent_auxillarystaff", HSDao.getAllhospital_auxillarystaff_details(Integer.parseInt(institute_id)));
		Mmap.put("getMedicalPostList", HSDao.getMedicalPostList());
		Mmap.put("getParaMedicalPostList", HSDao.getParaMedicalPostList());
		Mmap.put("getAuxillaryPostList", HSDao.getAuxillaryPostList());
		return new ModelAndView("hospital_staff_list");
	}
	
	
	
	//SAVE Medical DETAILS
		@PostMapping(value = "/hospital_medical_staff_Action")
		public @ResponseBody String hospital_medical_staff_Action( 
				HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,MultipartHttpServletRequest mul,
				RedirectAttributes ra) throws ParseException, IOException {

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
			UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
			String institute = String.valueOf(ea.getInstitute_id());
			System.err.println("--institute_id--" + institute);
	
			String s_id = session.getAttribute("super_id").toString();
			
			CLG_REG_HOSPITAL_MEDICAL_STAFF_DETAILS pers_p =new CLG_REG_HOSPITAL_MEDICAL_STAFF_DETAILS();
			String indno = request.getParameter("indno_library");
			String name_dtls = request.getParameter("name_dtls"+indno);
			String post_dtls = request.getParameter("post_dtls"+indno);
			String department_dtls = request.getParameter("department_dtls"+indno);
			String remarks = request.getParameter("remarks"+indno);
			String hid_medical_staff = request.getParameter("hid_medical_staff"+indno);
			String hid_medicalstaff_doc = request.getParameter("hid_medicalstaff_doc"+indno);
			String msg="";
			MultipartFile file = mul.getFile("medicalstaff_doc"+indno);
			String upload_attachment = upload_imagemethod(request,file, session, hid_medical_staff);
			String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
			
			int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
			Date date = new Date();
			
			String username = principal.getName();
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			if (name_dtls == null || name_dtls.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Medical Staff Name in Medical Staff Tab");
				return "Please Enter Medical Staff Name in Medical Staff Tab ";
			}
			if (validation.isOnlyAlphabet(name_dtls) == false) {
				ra.addAttribute("msg","Medical Staff Name in Medical Staff Tab " +validation.isOnlyAlphabetMSG);
				return "Medical Staff Name in Medical Staff Tab " +validation.isOnlyAlphabetMSG;
			}
			if (validation.maxlengthcheck100(name_dtls) == false) {
				ra.addAttribute("msg", "Medical Staff Name  in Medical Staff Tab " + validation.MaxlengthcheckMSG100);
				return "Medical Staff Name in Medical Staff Tab " +validation.MaxlengthcheckMSG100;
			}
			if (post_dtls == null || post_dtls.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Medical Staff Post in Medical Staff Tab ");
				return "Please Select Medical Staff Post in Medical Staff Tab ";
			}
			
			if (department_dtls == null || department_dtls.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Medical Staff Department in Medical Staff Tab ");
				return "Please Enter Medical Staff Department in Medical Staff Tab ";
			}
			if (validation.maxlengthcheck100(department_dtls) == false) {
				ra.addAttribute("msg", "Medical Staff Department in Medical Staff Tab " + validation.MaxlengthcheckMSG100);
				return "Medical Staff Department in Medical Staff Tab " +validation.MaxlengthcheckMSG100;
			} 
			if (validation.isOnlyAlphabetNumber(department_dtls) == false) {
				ra.addAttribute("msg","Medical Staff Department in Medical Staff Tab" +validation.isOnlyAlphabetNumberMSG);
				return " Medical Staff Department in Medical Staff Tab " +validation.isOnlyAlphabetNumberMSG;
			}
//			if (upload_attachment.isEmpty()) {
//				ra.addAttribute("msg", "Please Upload  Medical Staff Document.");
//				return "Please Upload Medical Staff Document";
//			}
			if (remarks == null || remarks.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Medical Staff Remarks in Medical Staff Tab");
				return "Please Enter Medical Staff Remarks in Medical Staff Tab";
			}
			if (validation.isOnlyAlphabetNumber(remarks) == false) {
				ra.addAttribute("msg"," Medical Staff Remarks in Medical Staff Tab " +validation.isOnlyAlphabetNumberMSG);
				return " Medical Staff Remarks in Medical Staff Tab " +validation.isOnlyAlphabetNumberMSG;
			}
			if (validation.maxlengthcheck(department_dtls) == false) {
				ra.addAttribute("msg", "Medical Staff Remarks in Medical Staff Tab " + validation.MaxlengthcheckMSG);
				return "Medical Staff Remarks in Medical Staff Tab " +validation.MaxlengthcheckMSG;
			} 
//			if (upload_attachment.equals("0")) {
//				ra.addAttribute("msg", "Please Select Degree");
//				return "Please Enter Medical Staff Remarks.............d";
//			}
			try {
				CLG_REG_HOSPITAL_MEDICAL_STAFF_DETAILS medical_detail =new CLG_REG_HOSPITAL_MEDICAL_STAFF_DETAILS();
			
				medical_detail.setName(name_dtls);
				medical_detail.setDocument(upload_attachment);
				medical_detail.setPost(post_dtls);
				medical_detail.setDepartment(department_dtls);
				medical_detail.setRemarks(remarks);
				medical_detail.setS_id(Integer.parseInt(s_id));
				medical_detail.setInstitute_id(Integer.parseInt(institute_id));
				medical_detail.setCreated_by(Integer.parseInt(userid));
				medical_detail.setCreated_date(date);
				
					if (Integer.parseInt(hid_medical_staff) == 0) {
						int hid_medical_staff1= (Integer) sessionHQL.save(medical_detail);
						sessionHQL.flush();
						sessionHQL.clear();
						msg = "Data Saved Successfully";
						tx.commit();
						return String.valueOf(hid_medical_staff1) ;
				}
					else {

						CLG_REG_HOSPITAL_MEDICAL_STAFF_DETAILS medical_detail_u = (CLG_REG_HOSPITAL_MEDICAL_STAFF_DETAILS) sessionHQL
								.get(CLG_REG_HOSPITAL_MEDICAL_STAFF_DETAILS.class, Integer.parseInt(hid_medical_staff));
						
						medical_detail_u.setName(name_dtls);
						medical_detail_u.setDocument(upload_attachment);
						medical_detail_u.setPost(post_dtls);
						medical_detail_u.setDepartment(department_dtls);
						medical_detail_u.setRemarks(remarks);
						medical_detail_u.setInstitute_id(Integer.parseInt(institute_id));
						medical_detail_u.setS_id(Integer.parseInt(s_id));
						medical_detail_u.setModified_by(Integer.parseInt(userid));
						medical_detail_u.setModified_date(date);
						medical_detail_u.setId(Integer.parseInt(hid_medical_staff));
						sessionHQL.update(medical_detail_u);
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
		
		
		// ADD MORE FETCH FOR Medical Details
		@RequestMapping(value = "admin/getMedical_Details", method = RequestMethod.POST)
		public @ResponseBody List<CLG_REG_HOSPITAL_MEDICAL_STAFF_DETAILS> getMedical_Details(HttpSession session) {
			String userid = session.getAttribute("userId_for_jnlp").toString();
			String institute_id = HSDao.getInstitute_id(userid).get(0).get(0);
			System.err.println("--------institute_id----------" + institute_id);
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL.createQuery("from CLG_REG_HOSPITAL_MEDICAL_STAFF_DETAILS where institute_id=:institute_id ");

			q.setParameter("institute_id", Integer.parseInt(institute_id));

			@SuppressWarnings("unchecked")
			List<CLG_REG_HOSPITAL_MEDICAL_STAFF_DETAILS> clist = (List<CLG_REG_HOSPITAL_MEDICAL_STAFF_DETAILS>) q.list();

			tx.commit();
			sessionHQL.close();
			return clist;
		}

		// ADD MORE DELETE FOR Medical Details
		@PostMapping(value = "/delete_Medical_Details")
		public @ResponseBody String delete_Medical_Details(String hid_medical_staff, HttpSession session1) {

			String msg = "";

			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			try {
				String hqlUpdate = "delete from CLG_REG_HOSPITAL_MEDICAL_STAFF_DETAILS where id=:id";
				int app = session.createQuery(hqlUpdate).setInteger("id", Integer.parseInt(hid_medical_staff)).executeUpdate();
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

		//SAVE Paramedical Staff
		@PostMapping(value = "/hospital_Paramedical_Staff_Action")
		public @ResponseBody String hospital_Paramedical_Staff_Action( 
				HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,MultipartHttpServletRequest mul,
				RedirectAttributes ra) throws ParseException, IOException {

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
			UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
			String institute = String.valueOf(ea.getInstitute_id());
			System.err.println("--institute_id--" + institute);
			String s_id = session.getAttribute("super_id").toString();
			CLG_REG_HOSPITAL_PARAMEDICAL_STAFF_DETAILS pers_p =new CLG_REG_HOSPITAL_PARAMEDICAL_STAFF_DETAILS();
			
			String indno = request.getParameter("indno_para");
			String p_name_dtls = request.getParameter("p_name_dtls"+indno);
			String p_post_dtls = request.getParameter("p_post_dtls"+indno);
			String p_department_dtls = request.getParameter("p_department_dtls"+indno);
			String p_remarks = request.getParameter("p_remarks"+indno);
			String hid_paramedicalstaff_doc = request.getParameter("hid_paramedicalstaff_doc"+indno);
			String hid_paramedical_staff = request.getParameter("hid_paramedical_staff"+indno);
			String msg="";
			MultipartFile file = mul.getFile("paramedical_doc"+indno);
			System.err.println("FILE-----------------------"+file);
			String p_upload_attachment = upload_imagemethod(request,file, session, hid_paramedical_staff);
			System.err.println("p_upload_attachment-----------------------"+p_upload_attachment);
			String institute_id = commondao.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);		
			
			int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
			Date date = new Date();
			String username = principal.getName();
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			
			if (p_name_dtls == null || p_name_dtls.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter ParaMedical Staff Name in ParaMedical Staff Tab");
				return "Please Enter ParaMedical Staff Name in ParaMedical Staff Tab ";
			}
			if (validation.isOnlyAlphabet(p_name_dtls) == false) {
				ra.addAttribute("msg","ParaMedical Staff Name in ParaMedical Staff Tab" +validation.isOnlyAlphabetMSG);
				return "ParaMedical Staff Name in ParaMedical Staff Tab " +validation.isOnlyAlphabetMSG;
			}
			if (validation.maxlengthcheck100(p_name_dtls) == false) {
				ra.addAttribute("msg", "ParaMedical Staff Name in ParaMedical Staff Tab" + validation.MaxlengthcheckMSG100);
				return "ParaMedical Staff Name in ParaMedical Staff Tab " +validation.MaxlengthcheckMSG100;
			}
			if (p_post_dtls == null || p_post_dtls.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select ParaMedical Staff Post in ParaMedical Staff Tab ");
				return "Please Select ParaMedical Staff Post in ParaMedical Staff Tab ";
			}
			if (p_department_dtls == null || p_department_dtls.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter ParaMedical Staff Department in ParaMedical Staff Tab ");
				return "Please Enter ParaMedical Staff Department in ParaMedical Staff Tab ";
			}
			if (validation.maxlengthcheck100(p_department_dtls) == false) {
				ra.addAttribute("msg", "ParaMedical Staff Department in ParaMedical Staff Tab " + validation.MaxlengthcheckMSG100);
				return "ParaMedical Staff Department in ParaMedical Staff Tab " +validation.MaxlengthcheckMSG100;
			} 
			if (validation.isOnlyAlphabetNumber(p_department_dtls) == false) {
				ra.addAttribute("msg","ParaMedical Staff Department in ParaMedical Staff Tab" +validation.isOnlyAlphabetNumberMSG);
				return " ParaMedical Staff Department in ParaMedical Staff Tab " +validation.isOnlyAlphabetNumberMSG;
			}
//			if (p_upload_attachment.isEmpty()) {
//				ra.addAttribute("msg", "Please Upload  ParaMedical Staff Document.");
//				return "Please Upload ParaMedical Staff Document";
//			}
			if (p_remarks == null || p_remarks.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter ParaMedical Staff Remarks in ParaMedical Staff Tab");
				return "Please Enter ParaMedical Staff Remarks in ParaMedical Staff Tab";
			}
			if (validation.isOnlyAlphabetNumber(p_remarks) == false) {
				ra.addAttribute("msg"," ParaMedical Staff Remarks in ParaMedical Staff Tab " +validation.isOnlyAlphabetNumberMSG);
				return " ParaMedical Staff Remarks in ParaMedical Staff Tab " +validation.isOnlyAlphabetNumberMSG;
			}
			if (validation.maxlengthcheck(p_remarks) == false) {
				ra.addAttribute("msg", "ParaMedical Staff Remarks in ParaMedical Staff Tab " + validation.MaxlengthcheckMSG);
				return "ParaMedical Staff Remarks in ParaMedical Staff Tab " +validation.MaxlengthcheckMSG;
			} 
			try {
				CLG_REG_HOSPITAL_PARAMEDICAL_STAFF_DETAILS paramedical_detail =new CLG_REG_HOSPITAL_PARAMEDICAL_STAFF_DETAILS();
			
				paramedical_detail.setName(p_name_dtls);
				paramedical_detail.setDocument(p_upload_attachment);
				paramedical_detail.setPost(p_post_dtls);
				paramedical_detail.setDepartment(p_department_dtls);
				paramedical_detail.setRemarks(p_remarks);
				paramedical_detail.setInstitute_id(Integer.parseInt(institute_id));
				paramedical_detail.setS_id(Integer.parseInt(s_id));
				paramedical_detail.setCreated_by(Integer.parseInt(userid));
				paramedical_detail.setCreated_date(date);
				
					if (Integer.parseInt(hid_paramedical_staff) == 0) {
						int hid_paramedical_staff1= (Integer) sessionHQL.save(paramedical_detail);
						sessionHQL.flush();
						sessionHQL.clear();
						msg = "Data Saved Successfully";
						tx.commit();
						return String.valueOf(hid_paramedical_staff1) ;
				}
					else {

						CLG_REG_HOSPITAL_PARAMEDICAL_STAFF_DETAILS paramedical_detail_u = (CLG_REG_HOSPITAL_PARAMEDICAL_STAFF_DETAILS) sessionHQL
								.get(CLG_REG_HOSPITAL_PARAMEDICAL_STAFF_DETAILS.class, Integer.parseInt(hid_paramedical_staff));
						
						paramedical_detail_u.setName(p_name_dtls);
						paramedical_detail_u.setDocument(p_upload_attachment);
						paramedical_detail_u.setPost(p_post_dtls);
						paramedical_detail_u.setDepartment(p_department_dtls);
						paramedical_detail_u.setRemarks(p_remarks);
						paramedical_detail_u.setInstitute_id(Integer.parseInt(institute_id));
						paramedical_detail_u.setModified_by(Integer.parseInt(userid));
						paramedical_detail_u.setModified_date(date);
						paramedical_detail_u.setId(Integer.parseInt(hid_paramedical_staff));
						paramedical_detail_u.setS_id(Integer.parseInt(s_id));
						sessionHQL.update(paramedical_detail_u);
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
		
		// ADD MORE FETCH FOR Medical Details
		@RequestMapping(value = "admin/getParaMedical_Details", method = RequestMethod.POST)
		public @ResponseBody List<CLG_REG_HOSPITAL_PARAMEDICAL_STAFF_DETAILS> getParaMedical_Details(HttpSession session) {
			String userid = session.getAttribute("userId_for_jnlp").toString();
			String institute_id = HSDao.getInstitute_id(userid).get(0).get(0);
			System.err.println("--------institute_id----------" + institute_id);
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL.createQuery("from CLG_REG_HOSPITAL_PARAMEDICAL_STAFF_DETAILS where institute_id=:institute_id ");

			q.setParameter("institute_id", Integer.parseInt(institute_id));

			@SuppressWarnings("unchecked")
			List<CLG_REG_HOSPITAL_PARAMEDICAL_STAFF_DETAILS> clist = (List<CLG_REG_HOSPITAL_PARAMEDICAL_STAFF_DETAILS>) q.list();

			tx.commit();
			sessionHQL.close();
			return clist;
		}

		// ADD MORE DELETE FOR Medical Details
		@PostMapping(value = "/delete_ParaMedical_Details")
		public @ResponseBody String delete_ParaMedical_Details(String hid_paramedical_staff, HttpSession session1) {

			String msg = "";

			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			try {
				String hqlUpdate = "delete from CLG_REG_HOSPITAL_PARAMEDICAL_STAFF_DETAILS where id=:id";
				int app = session.createQuery(hqlUpdate).setInteger("id", Integer.parseInt(hid_paramedical_staff)).executeUpdate();
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
		//SAVE Auxillary_Staff_
				@PostMapping(value = "/hospital_Auxillary_Staff_Action")
				public @ResponseBody String hospital_Auxillary_Staff_Action( 
						HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,MultipartHttpServletRequest mul,
						RedirectAttributes ra) throws ParseException, IOException {

					if(request.getHeader("Referer") == null ) { 
						 session.invalidate();
						 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						 return  "redirect:/login";
					 }
					String role = session.getAttribute("role").toString();
					String roleid1 = session.getAttribute("roleid").toString();
//					 Boolean val = roledao.ScreenRedirect("provisional_reg", roleid1);		
//						if(val == false) {
//							return  "AccessTiles";
//					}
					
					DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
					String userid = session.getAttribute("userId_for_jnlp").toString();
					String s_id = session.getAttribute("super_id").toString();
					UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
					String institute = String.valueOf(ea.getInstitute_id());
					System.err.println("--institute_id--" + institute);
					CLG_REG_HOSPITAL_AUXILLARY_MEDICAL_STAFF_DETAILS pers_p =new CLG_REG_HOSPITAL_AUXILLARY_MEDICAL_STAFF_DETAILS();
					
					String indno = request.getParameter("indno_auxi");
					String a_name_dtls = request.getParameter("a_name_dtls"+indno);
					String a_post_dtls = request.getParameter("a_post_dtls"+indno);
					String a_remarks = request.getParameter("a_remarks"+indno);
					String hid_auxillarystaff = request.getParameter("hid_auxillarystaff"+indno);
					String institute_id = HSDao.getInstitute_id(userid).get(0).get(0);
					String hid_auxillarystaff_doc = request.getParameter("hid_auxillarystaff_doc"+indno);
					String msg="";
					MultipartFile file = mul.getFile("auxillarystaff_doc"+indno);
					System.err.println("FILE-----------------------"+file);
					String a_upload_attachment = upload_imagemethod(request,file, session, hid_auxillarystaff);
					System.err.println("a_upload_attachment-----------------------"+a_upload_attachment);
					
					int id = pers_p.getId() > 0 ? pers_p.getId() : 0;
					Date date = new Date();
					String username = principal.getName();
					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					
					if (a_name_dtls == null || a_name_dtls.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Auxillary  Staff Name in Auxillary Staff Tab");
						return "Please Enter Auxillary  Staff Name in Auxillary Staff Tab ";
					}
					if (validation.maxlengthcheck100(a_name_dtls) == false) {
						ra.addAttribute("msg", "Auxillary Staff Name in Auxillary Staff Tab " + validation.MaxlengthcheckMSG100);
						return "Auxillary Staff Name in Auxillary Staff Tab " +validation.MaxlengthcheckMSG100;
					}
					if (validation.isOnlyAlphabet(a_name_dtls) == false) {
						ra.addAttribute("msg","Auxillary Staff Name in Auxillary Staff Tab" +validation.isOnlyAlphabetMSG);
						return "Auxillary Staff Name in Auxillary Staff Tab " +validation.isOnlyAlphabetMSG;
					}
					if (a_post_dtls == null || a_post_dtls.trim().equals("0")) {
						ra.addAttribute("msg", "Please Select Auxillary Staff Post in Auxillary Staff Tab ");
						return "Please Select Auxillary Staff Post in Auxillary Staff Tab ";
					}
//					if (a_upload_attachment.isEmpty()) {
//						ra.addAttribute("msg", "Please Upload Auxillary Staff Document.");
//						return "Please Upload Auxillary Staff Document";
//					}
					if (a_remarks == null || a_remarks.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Auxillary  Staff Remarks in Auxillary Staff Tab");
						return "Please Enter Auxillary Staff Remarks in Auxillary Staff Tab ";
					}
					if (validation.isOnlyAlphabetNumber(a_remarks) == false) {
						ra.addAttribute("msg","Auxillary Staff Remarks in Auxillary Staff Tab " +validation.isOnlyAlphabetNumberMSG);
						return "Auxillary  Staff Remarks in Auxillary Staff Tab " +validation.isOnlyAlphabetNumberMSG;
					}
					if (validation.maxlengthcheck(a_remarks) == false) {
						ra.addAttribute("msg", "Auxillary Staff Remarks in Auxillary Staff Tab " + validation.MaxlengthcheckMSG);
						return "Auxillary Staff Remarks in Auxillary Staff Tab " +validation.MaxlengthcheckMSG;
					}
					try {
						CLG_REG_HOSPITAL_AUXILLARY_MEDICAL_STAFF_DETAILS auxi_details =new CLG_REG_HOSPITAL_AUXILLARY_MEDICAL_STAFF_DETAILS();
					
						auxi_details.setName(a_name_dtls);
						auxi_details.setDocument(a_upload_attachment);
						auxi_details.setPost(a_post_dtls);
						auxi_details.setRemarks(a_remarks);
						auxi_details.setInstitute_id(Integer.parseInt(institute_id));
						auxi_details.setS_id(Integer.parseInt(s_id));
						auxi_details.setCreated_by(Integer.parseInt(userid));
						auxi_details.setCreated_date(date);
						
							if (Integer.parseInt(hid_auxillarystaff) == 0) {
								int hid_auxillarystaff1= (Integer) sessionHQL.save(auxi_details);
								sessionHQL.flush();
								sessionHQL.clear();
								msg = "Data Saved Successfully";
								tx.commit();
								return String.valueOf(hid_auxillarystaff1) ;
						}
							else {

								CLG_REG_HOSPITAL_AUXILLARY_MEDICAL_STAFF_DETAILS auxi_details_u = (CLG_REG_HOSPITAL_AUXILLARY_MEDICAL_STAFF_DETAILS) sessionHQL
										.get(CLG_REG_HOSPITAL_AUXILLARY_MEDICAL_STAFF_DETAILS.class, Integer.parseInt(hid_auxillarystaff));
								
								auxi_details_u.setName(a_name_dtls);
								auxi_details_u.setDocument(a_upload_attachment);
								auxi_details_u.setPost(a_post_dtls);
								auxi_details_u.setRemarks(a_remarks);
								auxi_details_u.setInstitute_id(Integer.parseInt(institute_id));
								auxi_details_u.setS_id(Integer.parseInt(s_id));
								auxi_details_u.setModified_by(Integer.parseInt(userid));
								auxi_details_u.setModified_date(date);
								auxi_details_u.setId(Integer.parseInt(hid_auxillarystaff));
								sessionHQL.update(auxi_details_u);
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
		
		// ADD MORE FETCH FOR Medical Details
		@RequestMapping(value = "admin/getAuxiMedical_Details", method = RequestMethod.POST)
		public @ResponseBody List<CLG_REG_HOSPITAL_AUXILLARY_MEDICAL_STAFF_DETAILS> getAuxiMedical_Details(HttpSession session) {
			String userid = session.getAttribute("userId_for_jnlp").toString();
			String institute_id = HSDao.getInstitute_id(userid).get(0).get(0);
			System.err.println("--------institute_id----------" + institute_id);
			Session sessionHQL = sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			Query q = sessionHQL.createQuery("from CLG_REG_HOSPITAL_AUXILLARY_MEDICAL_STAFF_DETAILS where institute_id=:institute_id ");

			q.setParameter("institute_id", Integer.parseInt(institute_id));

			@SuppressWarnings("unchecked")
			List<CLG_REG_HOSPITAL_AUXILLARY_MEDICAL_STAFF_DETAILS> clist = (List<CLG_REG_HOSPITAL_AUXILLARY_MEDICAL_STAFF_DETAILS>) q.list();

			tx.commit();
			sessionHQL.close();
			return clist;
		}

		// ADD MORE DELETE FOR Medical Details
		@PostMapping(value = "/delete_auxiMedical_Details")
		public @ResponseBody String delete_auxiMedical_Details(String hid_auxillarystaff, HttpSession session1) {

			String msg = "";

			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			try {
				String hqlUpdate = "delete from CLG_REG_HOSPITAL_AUXILLARY_MEDICAL_STAFF_DETAILS where id=:id";
				int app = session.createQuery(hqlUpdate).setInteger("id", Integer.parseInt(hid_auxillarystaff)).executeUpdate();
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
	}
