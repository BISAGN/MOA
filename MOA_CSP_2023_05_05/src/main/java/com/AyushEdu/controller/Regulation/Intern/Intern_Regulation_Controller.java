package com.AyushEdu.controller.Regulation.Intern;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.TB_LDAP_SCREEN_MASTER;
//import com.AyushEdu.Models.Regulation.EDU_REGULATION_MEDICAL_DEGREE_CHILD_ATTACHMENT_SUBCHILD_HISTORY;
//import com.AyushEdu.Models.Regulation.EDU_REGULATION_MEDICAL_DEGREE_CHILD_HISTORY;
import com.AyushEdu.Models.Regulation.REG_NCH_FORM_A_P;
//import com.AyushEdu.Models.Regulation.EDU_REGULATION_HOSPITAL_CHILD_HISTORY;
import com.AyushEdu.Models.Regulation.REG_NCH_MED_DEGREE_DTL_A_CH;
import com.AyushEdu.Models.Regulation.REG_NCH_MED_DEGREE_DTL_A_SUB_CH;
import com.AyushEdu.Models.Teacher_Master.EDU_DOC_ATTACHMENTS_MSTR;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Regulation.EduRegDao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Intern_Regulation_Controller {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DataSource dataSource;

	@Autowired
	ValidationController validation;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Autowired
	EduRegDao Rdao;
	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;

	@RequestMapping(value = "admin/intern_Regulation_Url", method = RequestMethod.GET)
	public ModelAndView intern_Regulation_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		//SECURITY - POOJA
		if(request.getHeader("Referer") == null ) { 
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			 return new ModelAndView("redirect:/landingpage");
		 }
		
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("intern_Regulation_Url", roleid1);		
			if(val == false && !roleid1.equals("21")) {
				return new ModelAndView("AccessTiles");
		}
			
		Mmap.put("getInstituteList", common.getUniverCityList(sessionFactory));
		Calendar calendar = Calendar.getInstance();
		Mmap.put("msg", msg);
		Mmap.put("MedDistrictName", common.getMedDistrictName(sessionFactory));
		Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
		Mmap.put("getMedNationality", common.getMedNationality(sessionFactory));
		 
		Mmap.put("getDegreeList", common.getDegreeList(sessionFactory));
		
		Session sessionHQ = this.sessionFactory.openSession();
		String username = session.getAttribute("username").toString();
		Mmap.put("username", username);
		
		try {
			int data = (int) sessionHQ.createQuery("select userId from UserLogin where upper(userName)=:id")
					.setParameter("id", username.trim().toUpperCase()).setMaxResults(1).uniqueResult();
			Mmap.put("userId", data);
			System.err.println("userId 117-->"+data);
			if ( request.getParameter("id7") != null && !request.getParameter("id7").equals("0")  && !request.getParameter("id7").equals("")) {
				
				 data = Integer.parseInt(request.getParameter("id7"));
				 username = (String) sessionHQ.createQuery("select userName from UserLogin where userId=:id")
						.setParameter("id", data).setMaxResults(1).uniqueResult();
				Mmap.put("username", username);
				Mmap.put("userId", data);
				System.err.println("check the userrr_id 125--->"+data);
			}
	 
			if (data != 0) {
				Mmap.put("setDataCMD", Rdao.getDataByUserNameForDraft(data));
				Mmap.put("setAddMoreMedicalCMD", Rdao.medicalData(data));
				Mmap.put("setAddMoreHospCMD", Rdao.HospitalData(data));
				Mmap.put("setAddMoreHospCMD", Rdao.HospitalData(data));
				Mmap.put("setAddMoreMedicalAttachmentChildCMD", Rdao.medicalDataChildAttachment(data));
				//Mmap.put("setRegAuth", Rdao.RegAuth(data));
				String pid = Rdao.getUserId(data);
				Mmap.put("p_id", pid);

				if (pid != null && !pid.trim().equals("")) {
					System.err.println("141"+pid);
					int data2 = Integer.parseInt(pid);
					REG_NCH_FORM_A_P INF = (REG_NCH_FORM_A_P) sessionHQ.get(REG_NCH_FORM_A_P.class, data2);
					Mmap.put("parentid", data2);
					Mmap.put("valid_dt", INF.getValid_up_to());
					if (INF.getStatus() == 0) {
						Mmap.put("hid", "2");
					}
					else if (INF.getStatus() == 1 && INF.getNrh_status() != 1) {
						Mmap.put("hid", "1");
					} else if (INF.getStatus() == 1 && INF.getInstitute_status() == 1 && INF.getState_status() == 1
							&& INF.getNrh_status() == 1) {
						Mmap.put("hid", "3");
					} else {
						Mmap.put("hid", "0");
					}
				} else {
					System.err.println("158"+pid);
					Mmap.put("p_id", "0");
				}

			} else {
				Mmap.put("setData", "0");
				Mmap.put("hid", "0");
				Mmap.put("p_id", "0");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		Mmap.put("TypeOfDegree", common.getTypeOfDegree(sessionFactory));
		Mmap.put("PlaceOfWorking", common.getPlaceOfWorking(sessionFactory));
		Mmap.put("NameOfResOwner", common.getNameOfResOwner(sessionFactory));
		Mmap.put("Attachment_data", getAttachmentdata(sessionFactory,"intern_Regulation_Url"));
		Mmap.put("Attachmentdoc_download", getAttachmentdoc_download("parent_id"));
		return new ModelAndView("intern_regulation_Tiles");
	}
	
//	@RequestMapping(value = "admin/intern_Regulation_Urlinst", method = RequestMethod.POST)
//	public ModelAndView intern_Regulation_Urlinst(ModelMap Mmap, HttpSession session,
//			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
//
////		if(request.getHeader("Referer") == null ) { 
////			 session.invalidate();
////			 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
////			 return new ModelAndView("redirect:/landingpage");
////		 }
//		 
////		 String  roleid = session.getAttribute("roleid").toString();
////	      Boolean val = roledao.ScreenRedirect("District", roleid);	
////		 	if(val == false) {
////				return new ModelAndView("AccessTiles");
////			}
////			if(request.getHeader("Referer") == null ) {
////				msg = "";
////				return new ModelAndView("redirect:bodyParameterNotAllow");
////			}
//		Mmap.put("getInstituteList", common.getUniverCityList(sessionFactory));
//		Calendar calendar = Calendar.getInstance();
//		Mmap.put("msg", msg);
//		Mmap.put("MedDistrictName", common.getMedDistrictName(sessionFactory));
//		Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
//		Mmap.put("getMedNationality", common.getMedNationality(sessionFactory));
//		 
//		Mmap.put("getDegreeList", common.getDegreeList(sessionFactory));
//		
//		Session sessionHQ = this.sessionFactory.openSession();
////		String username = session.getAttribute("username").toString();
//		
//		
//		try {System.err.println("id - - "+request.getParameter("id7"));
//			int data = Integer.parseInt(request.getParameter("id7"));
//			String username = (String) sessionHQ.createQuery("select userName from UserLogin where userId=:id")
//					.setParameter("id", data).setMaxResults(1).uniqueResult();
//			Mmap.put("username", username);
//			Mmap.put("userId", data);
//			if (data != 0) {
//				Mmap.put("setDataCMD", Rdao.getDataByUserNameForDraft(data));
//				Mmap.put("setAddMoreMedicalCMD", Rdao.medicalData(data));
//				Mmap.put("setAddMoreHospCMD", Rdao.HospitalData(data));
//				Mmap.put("setAddMoreHospCMD", Rdao.HospitalData(data));
//				Mmap.put("setAddMoreMedicalAttachmentChildCMD", Rdao.medicalDataChildAttachment(data));
//				//Mmap.put("setRegAuth", Rdao.RegAuth(data));
//				String pid = Rdao.getUserId(data);
//				Mmap.put("p_id", pid);
//
//				if (pid != null && !pid.trim().equals("")) {
//					int data2 = Integer.parseInt(pid);
//					REG_NCH_FORM_A_P INF = (REG_NCH_FORM_A_P) sessionHQ.get(REG_NCH_FORM_A_P.class, data2);
//					Mmap.put("parentid", data2);
//					Mmap.put("valid_dt", INF.getValid_up_to());
//					if (INF.getStatus() == 0) {
//						Mmap.put("hid", "2");
//					}
//					else if (INF.getStatus() == 1 && INF.getNrh_status() != 1) {
//						Mmap.put("hid", "1");
//					} else if (INF.getStatus() == 1 && INF.getInstitute_status() == 1 && INF.getState_status() == 1
//							&& INF.getNrh_status() == 1) {
//						Mmap.put("hid", "3");
//					} else {
//						Mmap.put("hid", "0");
//					}
//				} else {
//					Mmap.put("p_id", "0");
//				}
//
//			} else {
//				Mmap.put("setData", "0");
//				Mmap.put("hid", "0");
//				Mmap.put("p_id", "0");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			// TODO: handle exception
//		}
//		Mmap.put("TypeOfDegree", common.getTypeOfDegree(sessionFactory));
//		Mmap.put("PlaceOfWorking", common.getPlaceOfWorking(sessionFactory));
//		Mmap.put("NameOfResOwner", common.getNameOfResOwner(sessionFactory));
//		Mmap.put("Attachment_data", getAttachmentdata(sessionFactory,"intern_Regulation_Url"));
//		Mmap.put("Attachmentdoc_download", getAttachmentdoc_download("parent_id"));
//		return new ModelAndView("intern_regulation_Tiles");
//	}

	public List<EDU_DOC_ATTACHMENTS_MSTR> getAttachmentdata(SessionFactory sessionFactory,String screen_url) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx1 = sessionHQL.beginTransaction();
		try {
			
			String hql = "from TB_LDAP_SCREEN_MASTER sc where sc.screen_url like :screen_url  ";
			List<TB_LDAP_SCREEN_MASTER> users = null;
		
			Query query = sessionHQL.createQuery(hql);
			query.setParameter("screen_url", screen_url);
			users = (List<TB_LDAP_SCREEN_MASTER>) query.list();
				
			Query q1 = sessionHQL.createQuery(
					"from EDU_DOC_ATTACHMENTS_MSTR where status='1' and screen_id=:id order  by id ");
			q1.setParameter("id", users.get(0).getId());
			List<EDU_DOC_ATTACHMENTS_MSTR> list = (List<EDU_DOC_ATTACHMENTS_MSTR>) q1.list();
			System.err.println("check the list"+q1);
			tx1.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}
	 
	public List<String> getAttachmentId(SessionFactory sessionFactory,String doc_name) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx1 = sessionHQL.beginTransaction();
		try {
		
				
			Query q1 = sessionHQL.createQuery(
					"select  cast(id as text) from EDU_DOC_ATTACHMENTS_MSTR where status='1' and doc_name=:doc_name order  by id ");
			q1.setParameter("doc_name", doc_name);
			List<String> list = (List<String>) q1.list();
			System.err.println("check the list aaaaaaaa"+list);
			tx1.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}
	
	public List<String> getAttachmentdoc_download(String parent_id) {
		Session sessionHQL = sessionFactory.openSession();
		Transaction tx1 = sessionHQL.beginTransaction();
		try {
//			String hql = "from TB_LDAP_SCREEN_MASTER sc where sc.screen_url like :screen_url  ";
			String hql="select attachment from reg_nch_med_degree_dtl_a_sub_ch where parent_id=:parent_id and status='0";
			List<REG_NCH_MED_DEGREE_DTL_A_SUB_CH> users = null;
		
			Query query = sessionHQL.createQuery(hql);
			query.setParameter("parent_id", parent_id);
			users = (List<REG_NCH_MED_DEGREE_DTL_A_SUB_CH>) query.list();
				
			Query q1 = sessionHQL.createQuery(
					"select attachment from reg_nch_med_degree_dtl_a_sub_ch where parent_id=:parent_id and status='0 ");
			q1.setParameter("id", users.get(0).getId());
			List<String> list = (List<String>) q1.list();
			System.err.println("check the urmmmmuikk list"+list);
			tx1.commit();
			return list;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	}
	
	@PostMapping(value = "/intern_Regulation_Action")
	public ModelAndView intern_Regulation_Action(@Validated @ModelAttribute("intern_RegulationCMD") REG_NCH_FORM_A_P td,
			BindingResult result, HttpServletRequest request, MultipartHttpServletRequest mul, ModelMap model,
			HttpSession session, Principal principal, RedirectAttributes ra, MultipartFile files)
			throws IOException, ParseException {
		
			//SECURITY - POOJA
				if(request.getHeader("Referer") == null ) { 
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				
					String roleid1 = session.getAttribute("roleid").toString();
					 Boolean val = roledao.ScreenRedirect("intern_Regulation_Url", roleid1);		
						if(val == false && !roleid1.equals("21")) {
							return new ModelAndView("AccessTiles");
					}
		
		ra.addAttribute("id7", request.getParameter("userId"));
		 String  roleid = session.getAttribute("roleid").toString();
 		SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");
		String username = session.getAttribute("username").toString();
		String dob1 = request.getParameter("dob");
		Date dob = null;
		if (!dob1.equals("DD/MM/YYYY") && !dob1.equals("DD/MM/YYYY")) {
			dob = formate.parse(dob1);
		}
		String saveDraft = request.getParameter("SaveDraft");
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
 		String adhar = request.getParameter("aadhaar_no");
		String pract_status = request.getParameter("Regulationstatus");
		String NRHstatus = request.getParameter("NRHstatus");
		String upload_img_hid = request.getParameter("upload_img_hid");
		MultipartFile photo_path = mul.getFile("photo_path");
		
System.err.println("photo path"+photo_path);
		
		String photo_path_att = "";
		if (photo_path != null && !photo_path.isEmpty()) {
			photo_path_att = common.fileupload(photo_path.getBytes(), photo_path.getOriginalFilename(),
					"UploadHardCopy1");
		} else {
			photo_path_att = upload_img_hid;
		}
		String first_name = request.getParameter("first_name");
		String father_name = request.getParameter("father_name");
		String email_id = request.getParameter("email_id").trim();
		String alterEmail_id1 = request.getParameter("alt_email_id1");
		String alterEmail_id2 = request.getParameter("alt_email_id2");
		String nationality = request.getParameter("nationality");
		String gender = request.getParameter("gender");					
		String per_address1 = request.getParameter("per_address");
		String per_address2 = request.getParameter("per_address2");
		String per_address3 = request.getParameter("per_address3");
		String pre_address1 = request.getParameter("pre_address");
		String pre_address2 = request.getParameter("pre_address2");
		String pre_address3 = request.getParameter("pre_address3");
		String per_state = request.getParameter("per_state");
		String reg_state = request.getParameter("reg_state");
		String per_district = request.getParameter("per_district");
		String per_pincode = request.getParameter("per_pincode");
		String pre_state = request.getParameter("pre_state");
		String pre_district = request.getParameter("pre_district");
		String pre_pincode = request.getParameter("pre_pincode");
		String parent_id = request.getParameter("p_id");
		String curr_address = request.getParameter("curr_address");
		String curr_address2 = request.getParameter("curr_address2");
		String curr_address3 = request.getParameter("curr_address3");
		String curr_state = request.getParameter("curr_state");
		String curr_district = request.getParameter("curr_district");
		String curr_pincode = request.getParameter("curr_pincode");
		String check_address = request.getParameter("chk_add_val");
		String aayushid = request.getParameter("aayushid");
		String abha_no = request.getParameter("abha_no");
		String valid_up_to = request.getParameter("valid_up_to");
		String typeOfDegree1 = request.getParameter("typeOfDegree1");
		String DegreeName1 = request.getParameter("DegreeName1");
		String monthYearOfDegree1 = request.getParameter("monthYearOfDegree1");
		String NameOfUniversity1 = request.getParameter("NameOfUniversity1");
		BigInteger mo_no = BigInteger.ZERO;
		BigInteger alt_mo_no1 = BigInteger.ZERO;
		BigInteger alt_mo_no2 = BigInteger.ZERO;
		int id = td.getId() > 0 ? td.getId() : 0;
		if (!request.getParameter("mo_no").equals("")) {
			mo_no = new BigInteger(request.getParameter("mo_no"));
		}
		if (!request.getParameter("alt_mo_no1").equals("")) {
			alt_mo_no1 = new BigInteger(request.getParameter("alt_mo_no1"));
		}
		if (!request.getParameter("alt_mo_no2").equals("")) {
			alt_mo_no2 = new BigInteger(request.getParameter("alt_mo_no2"));
		}
		
		
		
//		// ALL VALIDATION CHECK WITHOUT STATUS --- SECURITY 
//		
//				if ((first_name == null || first_name.trim().equals("")) || validation.maxlengthcheck50(first_name) == false || validation.isOnlyAlphabet(first_name) == false) {
//					ra.addAttribute("msg", "Please Enter First Name And Should be Only Alphabate and Should be maxlength 50");
//					System.err.println("428");
//					return new ModelAndView("redirect:intern_Regulation_Url");
//					
//				}
//				
//				if ((father_name == null || father_name.trim().equals("")) || validation.maxlengthcheck50(father_name) == false || validation.isOnlyAlphabet(father_name) == false) {
//					ra.addAttribute("msg", "Please Enter father Name And Should be Only Alphabate and Should be maxlength 50");
//					System.err.println("435");
//					return new ModelAndView("redirect:intern_Regulation_Url");
//				}
//				
//				if ((email_id == null || email_id.trim().equals("")) || validation.maxlengthcheck50(email_id) == false) {
//					ra.addAttribute("msg", "Please Enter Email id and Should be maxlength 50");
//					return new ModelAndView("redirect:intern_Regulation_Url");
//				}
//				
//				if ((dob == null || dob.equals("DD/MM/YYYY"))) {
//					ra.addAttribute("msg", "Please Enter dob");
//					return new ModelAndView("redirect:intern_Regulation_Url");
//				}
//				
//				if ((mo_no == null || mo_no.equals("0") || validation.isOnlyNumer(request.getParameter("mo_no")) == true || validation.maxlengthcheck10(request.getParameter("mo_no")) == false)) {
//					ra.addAttribute("msg", "Please Enter Mobile No should be onlynumber and maxlenght 10");
//					return new ModelAndView("redirect:intern_Regulation_Url");
//				}
//				
//				if ((upload_img_hid == null || upload_img_hid.trim().equals(""))) {
//					ra.addAttribute("msg", "Please Enter upload img hidden");
//					return new ModelAndView("redirect:intern_Regulation_Url");
//				}
//				if ((reg_state == null || reg_state.equals("0"))) {
//					ra.addAttribute("msg", "Please Enter Registration State");
//					return new ModelAndView("redirect:intern_Regulation_Url");
//				}
//				if ((per_address1 == null || per_address1.trim().equals(""))) {
//					ra.addAttribute("msg", "Please Enter Permenent address line1");
//					return new ModelAndView("redirect:intern_Regulation_Url");
//				}
//				
//				if ((per_address2 == null || per_address2.trim().equals(""))) {
//					ra.addAttribute("msg", "Please Enter Permenent address line2");
//					return new ModelAndView("redirect:intern_Regulation_Url");
//				}
//				
//				if ((per_address3 == null || per_address3.trim().equals(""))) {
//					ra.addAttribute("msg", "Please Enter Permenent address line3");
//					return new ModelAndView("redirect:intern_Regulation_Url");
//				}
//				
//				if ((per_state == null || per_state.equals("0"))) {
//					ra.addAttribute("msg", "Please Enter Permenent State");
//					return new ModelAndView("redirect:intern_Regulation_Url");
//				}
//				
//				if ((per_district == null || per_district.equals("0"))) {
//					ra.addAttribute("msg", "Please Enter Per District");
//					return new ModelAndView("redirect:intern_Regulation_Url");
//				}
//				
//				if ((per_pincode == null && per_pincode.trim().equals("")) || validation.maxlengthcheckpincode(per_pincode) == false || validation.minlengthcheckpincode(per_pincode) == false || validation.isOnlyNumer(per_pincode) == true) {
//					ra.addAttribute("msg", "Please Enter perment pincode should be max and min length and only number");
//					return new ModelAndView("redirect:intern_Regulation_Url");
//				}
//				
//				if ((pre_address1 == null || pre_address1.trim().equals(""))) {
//					ra.addAttribute("msg", "Please Enter Present address line1");
//					return new ModelAndView("redirect:intern_Regulation_Url");
//				}
//				
//				if ((pre_address2 == null || pre_address2.trim().equals(""))) {
//					ra.addAttribute("msg", "Please Enter Present address line2");
//					return new ModelAndView("redirect:intern_Regulation_Url");
//				}
//				
//				if ((pre_address3 == null || pre_address3.trim().equals(""))) {
//					ra.addAttribute("msg", "Please Enter Present address line3");
//					return new ModelAndView("redirect:intern_Regulation_Url");
//				}
//				
//				if(pre_state == null || pre_state.equals("0") || pre_state == "0") {
//					ra.addAttribute("msg", "Please Enter Preseent State");
//					return new ModelAndView("redirect:intern_Regulation_Url");
//				}
//				
//				if ((pre_district == null || pre_district.equals("0") || pre_district == "0")) {
//					ra.addAttribute("msg", "Please Enter Present District");
//					return new ModelAndView("redirect:intern_Regulation_Url");
//				}
//				
//				if ((pre_pincode == null || pre_pincode.trim().equals("")) || validation.maxlengthcheckpincode(pre_pincode) == false || validation.minlengthcheckpincode(pre_pincode) == false || validation.isOnlyNumer(pre_pincode) == true) {
//					ra.addAttribute("msg", "Please Enter Present pincode should be max and min length and only number");
//					return new ModelAndView("redirect:intern_Regulation_Url");
//				}
//				
//				if ((curr_address == null || curr_address.trim().equals(""))) {
//					ra.addAttribute("msg", "Please Enter Current address line1");
//					return new ModelAndView("redirect:intern_Regulation_Url");
//				}
//				
//				if ((curr_address2 == null || curr_address2.trim().equals(""))) {
//					ra.addAttribute("msg", "Please Enter Current address line2");
//					return new ModelAndView("redirect:intern_Regulation_Url");
//				}
//				
//				if ((curr_address3 == null || curr_address3.trim().equals(""))) {
//					ra.addAttribute("msg", "Please Enter Current address line3");
//					return new ModelAndView("redirect:intern_Regulation_Url");
//				}
//				
//				if ((curr_state == null || curr_state.equals("0") || curr_state == "0")) {
//					ra.addAttribute("msg", "Please Enter Cuurent State");
//					return new ModelAndView("redirect:intern_Regulation_Url");
//				}
//				
//				if ((curr_district == null || curr_district.equals("0") || curr_district == "0")) {
//					ra.addAttribute("msg", "Please Enter Cuurent District");
//					return new ModelAndView("redirect:intern_Regulation_Url");
//				}
//				
//				if ((curr_pincode == null || curr_pincode.trim().equals("")) || validation.maxlengthcheckpincode(curr_pincode) == false || validation.minlengthcheckpincode(curr_pincode) == false || validation.isOnlyNumer(curr_pincode) == true) {
//					ra.addAttribute("msg", "Please Enter Current pincode should be max and min length and only number");
//					return new ModelAndView("redirect:intern_Regulation_Url");
//				}
//				
//				if ((alt_mo_no1 == null || mo_no.equals("0") || validation.isOnlyNumer(request.getParameter("alt_mo_no1")) == true || validation.maxlengthcheck10(request.getParameter("alt_mo_no1")) == false)) {
//					ra.addAttribute("msg", "Please Enter Alternet Mobile No should be onlynumber and maxlenght 10");
//					return new ModelAndView("redirect:intern_Regulation_Url");
//				}
//				
//				if ((alt_mo_no2 == null || mo_no.equals("0") || validation.isOnlyNumer(request.getParameter("alt_mo_no2")) == true || validation.maxlengthcheck10(request.getParameter("alt_mo_no2")) == false)) {
//					ra.addAttribute("msg", "Please Enter Alternet Mobile No2 should be onlynumber and maxlenght 10");
//					return new ModelAndView("redirect:intern_Regulation_Url");
//				}
//				
//				if(typeOfDegree1 == null || typeOfDegree1.equals("0") || typeOfDegree1 == "0") {
//					ra.addAttribute("msg", "Please Enter Type Of Degree");
//					return new ModelAndView("redirect:Regulation_Url");
//				}
//				
//				if(DegreeName1 == null || DegreeName1.equals("0") || DegreeName1 == "0") {
//					ra.addAttribute("msg", "Please Enter Degree Name");
//					return new ModelAndView("redirect:Regulation_Url");
//				}
//				
//				if ((monthYearOfDegree1 == null || monthYearOfDegree1.equals("DD/MM/YYYY"))) {
//					ra.addAttribute("msg", "Please Enter Month Of Degree");
//					return new ModelAndView("redirect:Regulation_Url");
//				}
//				
//				if(NameOfUniversity1 == null || NameOfUniversity1.equals("0") || NameOfUniversity1 == "0") {
//					ra.addAttribute("msg", "Please Enter Name Of University");
//					return new ModelAndView("redirect:Regulation_Url");
//				} 
				
		if (pract_status != null && pract_status.equals("1")) { 
			
			//SECURITY-----
			if (photo_path.getOriginalFilename().isEmpty()) {
				if(upload_img_hid.isEmpty()) {
				ra.addAttribute("msg", "Please Upload Photo");
				return new ModelAndView("redirect:intern_Regulation_Url");
				}
			}
			if (!photo_path.getOriginalFilename().isEmpty()) {
				
				 if (photo_path.getOriginalFilename().split("[.]").length > 2) {
					 ra.addAttribute("msg", "Invalid file extension for Document");
						return new ModelAndView("redirect:intern_Regulation_Url");
				}
				String upload_fileEXt = FilenameUtils.getExtension(photo_path.getOriginalFilename()).toLowerCase();
				if (!upload_fileEXt.equals("jpg") && !upload_fileEXt.equals("jpeg") && !upload_fileEXt.equals("png")) {
					ra.addAttribute("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed for Photo");
					return new ModelAndView("redirect:intern_Regulation_Url");
				}
				long filesize = photo_path.getSize() / 1024;
				if (filesize > 50) {
					ra.addAttribute("msg", "File size should be 50 kb or less Document");
					return new ModelAndView("redirect:intern_Regulation_Url");
				}
			}
			//SECURITY-----
			
			if (first_name == null || first_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter First Name");
				return new ModelAndView("redirect:intern_Regulation_Url");
			}
			
			if (validation.maxlengthcheck50(first_name) == false) {
				ra.addAttribute("msg", "First Name " + validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:intern_Regulation_Url");
			}
			
			if (validation.isOnlyAlphabetDASH(first_name) == false) {
				ra.addAttribute("msg", "First Name " + validation.isOnlyAlphabetMSGDASH);
				return new ModelAndView("redirect:intern_Regulation_Url");
			}
			
			if (father_name == null || father_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Father Name");
				return new ModelAndView("redirect:intern_Regulation_Url");
			}
			
			if (validation.maxlengthcheck50(father_name) == false) {
				ra.addAttribute("msg", "Father Name " + validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:intern_Regulation_Url");
			}
			if (validation.isOnlyAlphabetDASH(father_name) == false) {
				ra.addAttribute("msg", "Father Name " + validation.isOnlyAlphabetMSGDASH);
				return new ModelAndView("redirect:intern_Regulation_Url");
			}
			if (email_id == null || email_id.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Email Id");
				return new ModelAndView("redirect:intern_Regulation_Url");
			}
			if (dob == null || dob.equals("0")) {
				ra.addAttribute("msg", "Please Enter Date Of Birth");
				return new ModelAndView("redirect:intern_Regulation_Url");
			}
			
			if (dob == null || dob.equals("DD/MM/YYYY")) {
				ra.addAttribute("msg", "Please Enter The Date Of Birth");
				return new ModelAndView("redirect:intern_Regulation_Url");
			}
			if (mo_no.equals("0") || mo_no == null) {
				ra.addAttribute("msg", "Please Enter Mobile Number");
				return new ModelAndView("redirect:intern_Regulation_Url");
			}
			if (upload_img_hid == null || upload_img_hid.trim().equals("")) {
				ra.addAttribute("msg", "Please Select Photo");
				return new ModelAndView("redirect:intern_Regulation_Url");
			}
			/* address details */

			if (per_address1 == null || per_address1.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Permanent Address Line 1");
				return new ModelAndView("redirect:intern_Regulation_Url");
			}

			if (per_state.equals("0")) {
				ra.addAttribute("msg", "Please Select Permanent State");
				return new ModelAndView("redirect:intern_Regulation_Url");
			}

			if (reg_state.equals("0")) {
				ra.addAttribute("msg", "Please Select registration State");
				return new ModelAndView("redirect:intern_Regulation_Url");
			}
			
			if (per_district.equals("0")) {
				ra.addAttribute("msg", "Please Select Permanent District");
				return new ModelAndView("redirect:intern_Regulation_Url");
			}
			if (per_pincode == null || per_pincode.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Permanent Pincode");
				return new ModelAndView("redirect:intern_Regulation_Url");
			}
			
			if (validation.maxlengthcheckpincode(per_pincode) == false) {
				ra.addAttribute("msg", "Permanent Pin Code " + validation.MaxlengthcheckMSGpincode);
				return new ModelAndView("redirect:intern_Regulation_Url");
			}
			if (validation.minlengthcheckpincode(per_pincode) == false) {
				ra.addAttribute("msg", "Permanent Pin Code " + validation.MinlengthcheckMSGpincode);
				return new ModelAndView("redirect:intern_Regulation_Url");
			}
			if (validation.isOnlyNumer(per_pincode) == true) {
				ra.addAttribute("msg", "Permanent Pin Code " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:intern_Regulation_Url");
			}

			if (pre_state != null && pre_state.equals("0") && pre_state != "0") {
				ra.addAttribute("msg", "Please Select Present State");
				return new ModelAndView("redirect:intern_Regulation_Url");
			}
			if (pre_district != null && pre_district.equals("0") && pre_district != "0") {
				ra.addAttribute("msg", "Please Select Present District");
				return new ModelAndView("redirect:intern_Regulation_Url");
			} 
			if (pre_pincode == null || pre_pincode.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Present Pincode");
				return new ModelAndView("redirect:intern_Regulation_Url");
			}
			
			if (validation.isOnlyNumer(pre_pincode) == true) {
				ra.addAttribute("msg", "Corresponding Present PinCode " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:intern_Regulation_Url");
			}
			if (curr_state != null && curr_state.equals("0")  && curr_state != "0") {
				ra.addAttribute("msg", "Please Select Corresponding State");
				return new ModelAndView("redirect:intern_Regulation_Url");
			}
			 
			if (curr_district != null && curr_district.equals("0")  && curr_district != "0") {
				ra.addAttribute("msg", "Please Select Corresponding District");
				return new ModelAndView("redirect:intern_Regulation_Url");
			}
			
			if (curr_pincode != null && curr_pincode.equals("0")  && curr_pincode != "0") {
				ra.addAttribute("msg", "Please Enter Corresponding Pincode");
				return new ModelAndView("redirect:intern_Regulation_Url");
			}
			
			if (validation.maxlengthcheckpincode(curr_pincode) == false) {
				ra.addAttribute("msg", "Corresponding Pin Code " + validation.MaxlengthcheckMSGpincode);
				return new ModelAndView("redirect:intern_Regulation_Url");
			}
			if (validation.minlengthcheckpincode(curr_pincode) == false) {
				ra.addAttribute("msg", "Corresponding Pin Code " + validation.MinlengthcheckMSGpincode);
				return new ModelAndView("redirect:intern_Regulation_Url");
			}
			if (validation.isOnlyNumer(curr_pincode) == true) {
				ra.addAttribute("msg", "Corresponding Pin Code " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:intern_Regulation_Url");
			}
			
			if (validation.isOnlyNumer(request.getParameter("mo_no")) == true) {
				ra.addAttribute("msg", "Mobile Number " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:intern_Regulation_Url");
			}
			if (validation.maxlengthcheck10(request.getParameter("mo_no")) == false) {
				ra.addAttribute("msg", "Mobile Number " + validation.MaxlengthcheckMSG10);
				return new ModelAndView("redirect:intern_Regulation_Url");
			}
 
			if (validation.isOnlyNumer(request.getParameter("alt_mo_no1")) == true) {
				ra.addAttribute("msg", "Alternet Mobile Number 1" + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:intern_Regulation_Url");
			}
			if (validation.maxlengthcheck10(request.getParameter("alt_mo_no1")) == false) {
				ra.addAttribute("msg", "Alternet Mobile Number 1" + validation.MaxlengthcheckMSG10);
				return new ModelAndView("redirect:intern_Regulation_Url");
			}
 		if (validation.isOnlyNumer(request.getParameter("alt_mo_no2")) == true) {
				ra.addAttribute("msg", "Alternet Mobile Number 2" + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:intern_Regulation_Url");
			}
			if (validation.maxlengthcheck10(request.getParameter("alt_mo_no2")) == false) {
				ra.addAttribute("msg", "Alternet Mobile Number 2" + validation.MaxlengthcheckMSG10);
				return new ModelAndView("redirect:intern_Regulation_Url");
			}

		}
		
		//union terrirtory state
		System.err.println("reg_state=="+reg_state);
		long u_t = (long) sessionHQL.createQuery("select count(id) from TB_UT_MSTR where state_id=:reg_state")
		         .setParameter("reg_state", Integer.parseInt(reg_state)).setMaxResults(1).uniqueResult();
		// String ut_st = String.valueOf(u_t);
	 	System.err.println("ut_st--state==="+u_t);
 		 
	 	// getUSERID
		try {
//			int data = (int) sessionHQL.createQuery("select userId from UserLogin where upper(userName)=:id")
//					 .setParameter("id", username.trim().toUpperCase()).setMaxResults(1).uniqueResult();
			
			int data =  Integer.parseInt(request.getParameter("userId"));
			username = (String) sessionHQL.createQuery("select userName from UserLogin where userId=:id")
					.setParameter("id", data).setMaxResults(1).uniqueResult();
//			ROLE DYNAMIC
			roleid =String.valueOf( sessionHQL.createQuery("select roleId from UserRole where userId=:id")
					.setParameter("id", data).setMaxResults(1).uniqueResult());
			if (data != 0) {
				td.setUser_id(data);
 			}
			 
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			sessionHQL.flush();
			sessionHQL.clear();
 		}
 
		// Save As Draft 
		 System.err.println("check the parent_id--->"+parent_id);
		if (Integer.parseInt(parent_id) == 0) {
			 System.err.println("check the parent_id--->"+parent_id);
//			if (Integer.parseInt(parent_id) == 674457) {
			// 1st time entry in parent table ( reg_nch_form_a_p) 
 			td.setCreated_by(username);
			td.setCreated_date(new Date());
			td.setFirst_name(first_name);
			td.setEmail_id(email_id);
			td.setAyush_id(aayushid);
			td.setAbha_no(abha_no);
			td.setAadhaar_no((adhar));
			td.setMo_no((mo_no));
			td.setRole(Integer.parseInt(roleid));
			td.setPhoto_path(photo_path_att);
			td.setLst_updt_photo_dt(new Date());
			td.setValid_up_to(td.getValid_up_to());
			td.setPre_address_details1(pre_address1);
			td.setPre_address_details2(pre_address2);
			td.setPre_address_details3(pre_address3);
			td.setPer_address_details1(per_address1);
			td.setPer_address_details2(per_address2);
			td.setPer_address_details3(per_address3);
			td.setCurr_address(curr_address);
			td.setCurr_address2(curr_address2);
			td.setCurr_address3(curr_address3);
			td.setCurr_state(Integer.parseInt(curr_state));
			td.setCurr_district(Integer.parseInt(curr_district));
			td.setCreated_date(new Date());
			td.setDel_status(0);
			td.setPract_type("NEW");
			if (nationality != null && nationality != "0") { td.setNationality(Integer.parseInt(nationality)); }
			if (per_district != null && per_district != "0") {td.setPer_district(Integer.parseInt(per_district)); }
			if (pre_district != null && pre_district.equals("0") && pre_district != "0") { td.setPre_district(Integer.parseInt(pre_district)); }
 			if (per_state != null && per_state != "0") { td.setPer_state(Integer.parseInt(per_state)); }
 			if (reg_state != null && reg_state != "0") { td.setReg_state(Integer.parseInt(reg_state));}
  			if (pre_state != null && pre_state != "0") { td.setPre_state(Integer.parseInt(pre_state)); }
			if (curr_address != null && !curr_address.equals("")) {	td.setCurr_address(curr_address);}
 			if (curr_address2 != null && !curr_address2.equals("")) {td.setCurr_address2(curr_address2);	}
			if (curr_address3 != null && !curr_address3.equals("")) {td.setCurr_address3(curr_address3);	}
			if (curr_state != null && curr_state.equals("0") && curr_state != "0") {td.setCurr_state(Integer.parseInt(curr_state));	}
			if (curr_district != null && curr_district != "0") {td.setCurr_district(Integer.parseInt(curr_district));}
			if (curr_pincode != null && !curr_pincode.equals("")) {	td.setCurr_pincode(Integer.parseInt(curr_pincode));	}
 			if (photo_path_att != "") { td.setPhoto_path(photo_path_att); }
 			if(gender != null && !gender.equals("")) { td.setGender(Integer.parseInt(gender)); }
			if(father_name != null && father_name.equals("")) { td.setFather_name(father_name); }
			if(dob != null && !dob.equals("")) { td.setDob(dob); }
			if (alt_mo_no1 != null && !alt_mo_no1.equals("")) { td.setAlt_mo_no1(alt_mo_no1); }
			if (alt_mo_no1 != null && !alt_mo_no1.equals("")) { td.setAlt_mo_no2(alt_mo_no2); }
			if (alterEmail_id1 != null && !alterEmail_id1.equals("")) {	td.setAlt_email_id1(alterEmail_id1);}
			if (alterEmail_id2 != null && !alterEmail_id2.equals("")) {	td.setAlt_email_id2(alterEmail_id2);}
 			if (per_pincode != null && !per_pincode.equals("")) {
				BigInteger perpincode = BigInteger.ZERO;
				perpincode = new BigInteger(per_pincode);
				td.setPer_pincode(perpincode);
			}
			if (pre_pincode != null && !pre_pincode.equals("")) {
				BigInteger prepincode = BigInteger.ZERO;
				prepincode = new BigInteger(pre_pincode);
				td.setPre_pincode(prepincode);
			}
			
		// if(u_t == 0 ) {
				if (pract_status != null && !pract_status.equals("") && pract_status.equals("0")   ) {
					td.setStatus(0);
					td.setClg_status(-1);
					td.setInstitute_status(-1);
					td.setState_status(-1);
					td.setNrh_status(-1);
				} else if (pract_status != null && !pract_status.equals("") && !pract_status.equals("0") ) {
					td.setStatus(1);
					td.setClg_status(-1);
					td.setInstitute_status(0);
					td.setState_status(-1);
					td.setNrh_status(-1);
				}  
		// }
//			else {
//				//ut
// 				if (pract_status != null && !pract_status.equals("") && !pract_status.equals("0") ) {
//					td.setStatus(1);
//					td.setInstitute_status(1);
//					td.setState_status(1);
//					td.setNrh_status(0);
//				}  
//				
//				
//				
//			}
			
			 
 			
 			
 			int p_id = (int) sessionHQL.save(td);
 			System.err.println("696"+p_id);
			sessionHQL.flush();
			sessionHQL.clear();
			// draft  parent table save end 
 			if (p_id > 0) {
 				//Draft SAVE DEGREE child
				int count_hidden_att_name_med = Integer.parseInt(request.getParameter("count_hidden_att_name_med"));
				for (int j = 1; j <= count_hidden_att_name_med; j++) {
					String degree_hid = request.getParameter("degree_hid" + j);
					Long c = (Long) sessionHQL.createQuery( "select count(id) from  REG_NCH_MED_DEGREE_DTL_A_CH where id=:degree_hid") .setParameter("degree_hid",Integer.parseInt(degree_hid)).uniqueResult();
 				
					String typeOfDegree = request.getParameter("typeOfDegree" + j);
					String DegreeName = request.getParameter("DegreeName" + j);
					String monthYearOfDegree = request.getParameter("monthYearOfDegree" + j);
					String NameOfUniversity = request.getParameter("NameOfUniversity" + j); 
					
					REG_NCH_MED_DEGREE_DTL_A_CH ec = new REG_NCH_MED_DEGREE_DTL_A_CH();
					ec.setRegulation_p_id(p_id);
					ec.setName_of_institute(NameOfUniversity);
					ec.setMonth_and_year_of_degree(monthYearOfDegree);
					ec.setCreated_date(new Date());
					ec.setCreated_by(username);
					ec.setMonth_and_year_of_degree(monthYearOfDegree);
					if (typeOfDegree != null && !typeOfDegree.equals("")) {
						ec.setType_of_degree(Integer.parseInt(typeOfDegree));
					}
					if (DegreeName != null && !DegreeName.equals("")) {
						ec.setDegree_name(Integer.parseInt(DegreeName));
					}
 					int at_pid = (int) sessionHQL.save(ec);
					sessionHQL.flush();
					sessionHQL.clear();
					//Draft SAVE DEGREE child end
// 					-----check today
					if (at_pid > 0) {
						//SAVE ATTACHMENT
						if (request.getParameter("count_hidden_att_doc" + j) != null && !request.getParameter("count_hidden_att_doc" + j).equals("")) {
							int count_hid_subchild = Integer.parseInt(request.getParameter("count_hidden_att_doc" + j));
							for (int k = 1; k <= count_hid_subchild; k++) {
 								System.err.println("enteeeeeeerrrrrrrrrrrr");
								REG_NCH_MED_DEGREE_DTL_A_SUB_CH subatt = new REG_NCH_MED_DEGREE_DTL_A_SUB_CH();
								
//								String NameOfATT = request.getParameter("NameOfAttachmenthid" + j + "_" + k);
// 								if(getAttachmentId(sessionFactory,NameOfATT).size()>0)
//								{
//									NameOfATT=getAttachmentId(sessionFactory,NameOfATT).get(0);
//								}
								 String name_of_attachment =request.getParameter("NameOfAttachmenthid" + j + "_" + k);
								MultipartFile attachment = mul.getFile("attachmentDocument" + j + "_" + k);
								
								
								
								String attachmentDoc_hid = request.getParameter("attachmentDoc_hid" + j + "_" + k);
								String pic = "";
								if (!attachment.isEmpty()) {
									pic = common.fileupload(attachment.getBytes(), attachment.getOriginalFilename(), "UploadHardCopy1");
									if (pic != "") {
										subatt.setAttachment(pic);
									}else {
										subatt.setAttachment(attachmentDoc_hid);
									}
								}
								
								//SECURITY-----
								if (attachment.getOriginalFilename().isEmpty()) {
									if(attachmentDoc_hid.isEmpty()) {
									ra.addAttribute("msg", "Please Upload Document in row "+j);
									return new ModelAndView("redirect:intern_Regulation_Url");
								}
								}
								if (!attachment.getOriginalFilename().isEmpty()) {
									
									
									if (attachment.getOriginalFilename().split("[.]").length > 2) {
										 ra.addAttribute("msg", "Invalid file extension for Document");
											return new ModelAndView("redirect:intern_Regulation_Url");
									}
									
									String upload_fileEXt = FilenameUtils.getExtension(attachment.getOriginalFilename()).toLowerCase();
									if (!upload_fileEXt.equals("pdf")) {
										ra.addAttribute("msg", "Only .pdffile extensions allowed for Document in row "+j);
										return new ModelAndView("redirect:intern_Regulation_Url");
									}
									long filesize = attachment.getSize() / 1024;
									if (filesize > 200) {
										ra.addAttribute("msg", "File size should be 200 kb or less Document");
										return new ModelAndView("redirect:intern_Regulation_Url");
									}
								}
								//SECURITY-----
// 								if (NameOfATT != null && !NameOfATT.equals("")) {
//									subatt.setName_of_attachment(NameOfATT);
//								}
								subatt.setParent_id(at_pid);
								subatt.setName_of_attachment(name_of_attachment); 
								td.setStatus_level_1(0);
								td.setStatus_level_2(0);
								sessionHQL.save(subatt);
								sessionHQL.flush();
								sessionHQL.clear();
								//SAVE ATTACHMENT end 
							}

						}
					}

				}
		 }
			 System.err.println("774");
			tx.commit();
			ra.addAttribute("msg", "Your Details has been Saved as Draft");
 		}
		//Draft End
			 
		 else {
 			System.err.println("else check draft statment");
		  
 			REG_NCH_FORM_A_P td2 = (REG_NCH_FORM_A_P) sessionHQL.get(REG_NCH_FORM_A_P.class, Integer.parseInt(parent_id));
 				if (Integer.parseInt(parent_id) != 0) {
 					//UPDATE parent table start
				td2.setRole(Integer.parseInt(roleid));
				td2.setId(Integer.parseInt(parent_id));
				td2.setModified_by(username);
				td2.setModified_date(new Date());
				td2.setAyush_id(aayushid);
				td2.setAbha_no(abha_no);
				td2.setFirst_name(first_name);
				 td2.setEmail_id(email_id);
				td2.setAadhaar_no((adhar));
				td2.setMo_no((mo_no));
				td2.setCheck_address(Integer.parseInt(check_address));
				td2.setValid_up_to(td2.getValid_up_to());
				td2.setValid_up_to(td2.getValid_up_to());
				td2.setPre_address_details1(pre_address1);
				td2.setPre_address_details2(pre_address2);
				td2.setPre_address_details3(pre_address3);
				td2.setPer_address_details1(per_address1);
				td2.setPer_address_details2(per_address2);
				td2.setPer_address_details3(per_address3);
				td2.setDel_status(0);
				td2.setFather_name(father_name);
				td2.setGender(td.getGender());
				td2.setMo_no(td.getMo_no());
				td2.setPhoto_path(photo_path_att);
				td.setLst_updt_photo_dt(new Date());
 				if (gender != null && !gender.equals("null") && !gender.equals("")) { td2.setGender(Integer.parseInt(gender)); }
				if (alterEmail_id1 != null && !alterEmail_id1.equals("")) {	td2.setAlt_email_id1(alterEmail_id1);	}
				if (alterEmail_id2 != null && !alterEmail_id2.equals("")) {	td2.setAlt_email_id2(alterEmail_id2);}
				if (alt_mo_no1 != null && !alt_mo_no1.equals("")) {	td2.setAlt_mo_no1(alt_mo_no1);	}
				if (alt_mo_no1 != null && !alt_mo_no1.equals("")) {	td2.setAlt_mo_no2(alt_mo_no2);	}
				if (curr_address != null && !curr_address.equals("")) {	td2.setCurr_address(curr_address);	}
 				if (curr_address2 != null && !curr_address2.equals("")) { td2.setCurr_address2(curr_address2);}
 				if (curr_address3 != null && !curr_address3.equals("")) {td2.setCurr_address3(curr_address3);}
 				if (per_state != null &&  per_state != "") {td2.setPer_state(Integer.parseInt(per_state));	}
 				if (reg_state != null &&  reg_state != "") {td2.setReg_state(Integer.parseInt(reg_state));	}
 				if (per_district != null &&  per_district != "") {td2.setPer_district(Integer.parseInt(per_district));}
				if (pre_state != null && pre_state != "") {	td2.setPre_state(Integer.parseInt(pre_state));}
				if (pre_district != null && pre_district != "") { td2.setPre_district(Integer.parseInt(pre_district)); }
 				if (curr_state != null && curr_state != "") { td2.setCurr_state(Integer.parseInt(curr_state)); }
 				if (curr_district != null && curr_district != "") { td2.setCurr_district(Integer.parseInt(curr_district)); }
 				if (curr_pincode != null && !curr_pincode.equals("")) {td2.setCurr_pincode(Integer.parseInt(curr_pincode));}
				if(father_name != null && father_name.equals("")) {	td2.setFather_name(father_name);}
				if(dob != null && !dob.equals("")){ td2.setDob(dob); }
				if (per_pincode != null && !per_pincode.equals("")) {
					BigInteger perpincode = BigInteger.ZERO;
					perpincode = new BigInteger(per_pincode);
					td2.setPer_pincode(perpincode);
				}
				if (photo_path_att != null && !photo_path_att.trim().equals("")) {
					td2.setPhoto_path(photo_path_att);
					td.setLst_updt_photo_dt(new Date());
					} 
				else {
					td2.setPhoto_path(upload_img_hid);
					td.setLst_updt_photo_dt(new Date());
				}
 				if (pre_pincode != null && !pre_pincode.equals("")) {
					BigInteger prepincode = BigInteger.ZERO;
					prepincode = new BigInteger(pre_pincode);
					td2.setPre_pincode(prepincode);
				}
 				
 				
 				if(u_t == 0 ) {
 					if (pract_status != null && !pract_status.equals("") && pract_status.equals("0")   ) {
 						td2.setStatus(0);
 						td2.setInstitute_status(-1);
 						td2.setState_status(-1);
 						td2.setNrh_status(-1);
 	 					} 
 	 					if (pract_status != null && !pract_status.equals("") && !pract_status.equals("0") ) {
 	 						td2.setStatus(1);
 	 						td2.setInstitute_status(0);
 	 						td2.setState_status(-1);
 	 						td2.setNrh_status(-1);
 	 					}
 				}
 				else {
 					if (pract_status != null && !pract_status.equals("") && !pract_status.equals("0") ) {
	 						td2.setStatus(1);
	 						td2.setInstitute_status(1);
	 						td2.setState_status(1);
	 						td2.setNrh_status(0);
	 					}
 				}
 				 	
 				 
 			 
 				sessionHQL.update(td2);
				sessionHQL.flush();
				sessionHQL.clear();
 				int p_id = td2.getId();
 				//Update parent table end
 //				if (parentid > 0) {
					int data = 0;
					int data2 = 0;
						int count_hidden_att_name_med = Integer.parseInt(request.getParameter("count_hidden_att_name_med"));
 						for (int j = 1; j <= count_hidden_att_name_med; j++) {
  							String degree_hid = request.getParameter("degree_hid"+j); 
   							String typeOfDegree = request.getParameter("typeOfDegree" + j);
							String DegreeName = request.getParameter("DegreeName" + j);
							String monthYearOfDegree = request.getParameter("monthYearOfDegree" + j);
							String NameOfUniversity = request.getParameter("NameOfUniversity" + j);
							
							Long c = (Long) sessionHQL.createQuery( "select count(id) from  REG_NCH_MED_DEGREE_DTL_A_CH where id=:degree_hid") .setParameter("degree_hid",Integer.parseInt(degree_hid)).uniqueResult();
  							
							//janki
//  							if(c!=null && c==0) { 
//  								// child save start 
//							REG_NCH_MED_DEGREE_DTL_A_CH ec = new REG_NCH_MED_DEGREE_DTL_A_CH();
//							ec.setRegulation_p_id(Integer.parseInt(parent_id));
//							ec.setName_of_institute(NameOfUniversity);
//							ec.setMonth_and_year_of_degree(monthYearOfDegree);
// 							ec.setCreated_date(new Date());
//							ec.setCreated_by(username);
//							ec.setMonth_and_year_of_degree(monthYearOfDegree);
//							if (typeOfDegree != null && !typeOfDegree.equals("")) { ec.setType_of_degree(Integer.parseInt(typeOfDegree)); }
//							if (DegreeName != null && !DegreeName.equals("")) {		ec.setDegree_name(Integer.parseInt(DegreeName));	}
// 							int at_pid = (int) sessionHQL.save(ec);
//							sessionHQL.flush();
//							sessionHQL.clear();
//							// child save  end
//							  
//								if (request.getParameter("count_hidden_att_doc" + j) != null && !request.getParameter("count_hidden_att_doc" + j).equals("")) {
//								int count_hid_subchild = Integer.parseInt(request.getParameter("count_hidden_att_doc" + j));
//								 for(int m = 1 ; m<= count_hid_subchild ; m++) {
// 										 
//										String f_att = request.getParameter("f_att"+j+"_"+m);
//										String name_of_attachment = request.getParameter("NameOfAttachmenthid" + j + "_" + m);
//										MultipartFile attachment = mul.getFile("attachmentDocument" + j + "_" + m);
//  										String attachmentDoc_hid = request.getParameter("attachmentDoc_hid" + j + "_" + m);
//   										if(getAttachmentId(sessionFactory,name_of_attachment).size()>0)
//										{ name_of_attachment=getAttachmentId(sessionFactory,name_of_attachment).get(0); }
//  										
//  										if(f_att != null && f_att!= "" && !f_att.equals("")) {
// 										//update attachment 
//  											sessionHQL.createQuery(
//													"update  REG_NCH_MED_DEGREE_DTL_A_SUB_CH set name_of_attachment=:name_of_attachment, modified_by=:modified_by,modified_date=:modified_date where id=:f_att")
//										 			.setParameter("name_of_attachment", name_of_attachment)
//										 			.setParameter("f_att", Integer.parseInt(f_att))
//													.setParameter("modified_by", username)
//													.setParameter("modified_date", new Date())
//													.executeUpdate();
// 										} else {
// 										//save attachment
// 											REG_NCH_MED_DEGREE_DTL_A_SUB_CH subatt = new REG_NCH_MED_DEGREE_DTL_A_SUB_CH();
//											String pic = "";
//											if (!attachment.isEmpty()) {
//												pic = common.fileupload(attachment.getBytes(), attachment.getOriginalFilename(), "UploadHardCopy1");
//												if (pic != "") { subatt.setAttachment(pic); }
//												else { subatt.setAttachment(attachmentDoc_hid); }
//											}
//											if (name_of_attachment != null && !name_of_attachment.equals("")) { subatt.setName_of_attachment(name_of_attachment); }
//											subatt.setParent_id(at_pid);
//											sessionHQL.save(subatt);
//											sessionHQL.flush();
//											sessionHQL.clear();
//											
//										}   
//										 
//									}   
//								}   
//  							 }   
 							 
  							//c != 0
							// else {
							if(c!=null && c!=0) { 
								 	System.err.println(" cc else");
 									 
									REG_NCH_MED_DEGREE_DTL_A_CH HSCH = (REG_NCH_MED_DEGREE_DTL_A_CH) sessionHQL .get(REG_NCH_MED_DEGREE_DTL_A_CH.class,  Integer.parseInt(degree_hid));
									//  child update 
									HSCH.setName_of_institute(NameOfUniversity);
									HSCH.setMonth_and_year_of_degree(monthYearOfDegree);
		 							HSCH.setModified_date(new Date());
									HSCH.setModified_by(username);
									HSCH.setMonth_and_year_of_degree(monthYearOfDegree);
									HSCH.setRegulation_p_id(p_id);
									HSCH.setStatus(0);
		 							if (typeOfDegree != null && !typeOfDegree.equals("")) { HSCH.setType_of_degree(Integer.parseInt(typeOfDegree)); }
									if (DegreeName != null && !DegreeName.equals("")) { HSCH.setDegree_name(Integer.parseInt(DegreeName)); }
									
		 							sessionHQL.update(HSCH);
									sessionHQL.flush();
									sessionHQL.clear();
								  //	 child update end
 									
		 							int at_pid = HSCH.getId();
									if (at_pid > 0) {
										if (request.getParameter("count_hidden_att_doc" + j) != null && !request.getParameter("count_hidden_att_doc" + j).equals("")) {
 											int count_hid_subchild = Integer.parseInt(request.getParameter("count_hidden_att_doc" + j));
 											REG_NCH_MED_DEGREE_DTL_A_SUB_CH subatt = new REG_NCH_MED_DEGREE_DTL_A_SUB_CH();
 											for(int m = 1 ; m<= count_hid_subchild ; m++) {
											 //	 attachment update	 start
											 String f_att = request.getParameter("f_att"+j+"_"+m); 
											 System.err.println("3866---f_att =="+f_att);
											 String name_of_attachment = request.getParameter("NameOfAttachmenthid" + j + "_" + m);
											 
												MultipartFile attachment = mul.getFile("attachmentDocument" + j + "_" + m);
												
												
												
												String attachmentDoc_hid = request.getParameter("attachmentDoc_hid" + j + "_" + m);
												String pic = "";
												if (!attachment.isEmpty()) {
													pic = common.fileupload(attachment.getBytes(), attachment.getOriginalFilename(), "UploadHardCopy1");
													if (pic != "") {
														subatt.setAttachment(pic);
														attachmentDoc_hid = pic ;
													}else {
														subatt.setAttachment(attachmentDoc_hid);
													}
												}
												
												//SECURITY-----
												if (attachment.getOriginalFilename().isEmpty()) {
													if (attachmentDoc_hid.isEmpty()) {
													ra.addAttribute("msg", "Please Upload Document in row "+j);
													return new ModelAndView("redirect:intern_Regulation_Url");
												}
												}
												if (!attachment.getOriginalFilename().isEmpty()) {
													
													if (attachment.getOriginalFilename().split("[.]").length > 2) {
														 ra.addAttribute("msg", "Invalid file extension for Document");
															return new ModelAndView("redirect:intern_Regulation_Url");
													}
													
													String upload_fileEXt = FilenameUtils.getExtension(attachment.getOriginalFilename()).toLowerCase();
													if (!upload_fileEXt.equals("pdf")) {
														ra.addAttribute("msg", "Only .pdffile extensions allowed for Document in row "+j);
														return new ModelAndView("redirect:intern_Regulation_Url");
													}
													long filesize = attachment.getSize() / 1024;
													if (filesize > 200) {
														ra.addAttribute("msg", "File size should be 200 kb or less Document");
														return new ModelAndView("redirect:intern_Regulation_Url");
													}
												}
												//SECURITY-----
											 
											 System.err.println("3866---name_of_attachment =="+name_of_attachment);
											 System.err.println("3866---attachment =="+attachment);
											 System.err.println("3866---attachmentDoc_hid =="+attachmentDoc_hid);
												 
											 
											 if(f_att != null && f_att!= "" && !f_att.equals("")) {
  												 sessionHQL.createQuery( "update  REG_NCH_MED_DEGREE_DTL_A_SUB_CH set name_of_attachment=:name_of_attachment,"
  												 		+ "  modified_by=:modified_by,modified_date=:modified_date  ,attachment=: attachmentDoc_hid where id=:f_att")
												 			.setParameter("name_of_attachment", name_of_attachment)
												 			.setParameter("f_att", Integer.parseInt(f_att))
															.setParameter("modified_by", username)
															.setParameter("modified_date", new Date())
															.setParameter("attachmentDoc_hid", attachmentDoc_hid)
															.executeUpdate();
 												}
											  else {
		 										//save attachment
  													//REG_NCH_MED_DEGREE_DTL_A_SUB_CH subatt = new REG_NCH_MED_DEGREE_DTL_A_SUB_CH();
													//String pic = "";
													if (!attachment.isEmpty()) {
														pic = common.fileupload(attachment.getBytes(), attachment.getOriginalFilename(),
																"UploadHardCopy1");
													 
														if (pic != "") {
		 													subatt.setAttachment(pic);
														}else {
															subatt.setAttachment(attachmentDoc_hid);
														}
													}
													if (name_of_attachment != null && !name_of_attachment.equals("")) {
														subatt.setName_of_attachment(name_of_attachment);
													}
													subatt.setParent_id(at_pid);
													sessionHQL.save(subatt);
													sessionHQL.flush();
													sessionHQL.clear();
													
												}
												 
											}
									}
		 							}
								 
							 }

							 
						}
							 
			if (pract_status != null && pract_status.equals("1")) {
				ra.addAttribute("msg", "Your Form has been Successfully Forwarded to University .");
				System.err.println("check condition"+pract_status != null && pract_status.equals("1"));
			} else {
				ra.addAttribute("msg", "Your Form has been Updated Successfully.");
				System.err.println("check condition2"+pract_status != null && pract_status.equals("1"));

			}
		 
//		} catch (RuntimeException e) {
//			try {
//				ra.addAttribute("msg", "roll back transaction");
//			} catch (RuntimeException rbe) {
//				ra.addAttribute("msg", "Couldn?t roll back transaction " + rbe);
//			}
//			throw e;
//		} finally {
//			if (sessionHQL != null) {
//				sessionHQL.close();
//			}
//
//		}
		  		
				 
						tx.commit();//4
			} //3
		} //2
		return new ModelAndView("redirect:intern_Regulation_Url");
} //1


 
  
	@RequestMapping(value = "paymentStatusUrl", method = RequestMethod.POST)
	public ModelAndView paymentStatusUrl(@ModelAttribute("a") int id, 
			@ModelAttribute("status") String pay_status, HttpSession session,
			ModelMap model, REG_NCH_FORM_A_P obj, HttpServletRequest request) {
		
			//SECURITY - POOJA
				if(request.getHeader("Referer") == null ) { 
					model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
					 return new ModelAndView("redirect:/landingpage");
				 }
				
				String roleid1 = session.getAttribute("roleid").toString();
				 Boolean val = roledao.ScreenRedirect("intern_Regulation_Url", roleid1);		
					if(val == false && !roleid1.equals("21")) {
						return new ModelAndView("AccessTiles");
				}
	 
		List<String> liststr = new ArrayList<String>();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
 		Date date = new Date();
 		String username = session.getAttribute("username").toString();
		String hqlUpdate2 = "update from REG_NCH_FORM_A_P set pay_status='1',modified_by=:modified_by,modified_date=:modified_date"
				+ " where id=:id  ";
		int app2 = sessionHQL.createQuery(hqlUpdate2)
				.setParameter("id", id)
				.setParameter("modified_by", username)
				.setParameter("modified_date", new Date())
				.executeUpdate();

		 
	 

		tx.commit();
		sessionHQL.close();
		if (app2 > 0) {
			liststr.add("Approved Successfully.");
		} else {
			liststr.add("Not Approved.");
		}
		model.put("msg", liststr.get(0));

		return new ModelAndView("intern_regulation_Tiles");
	}
	@RequestMapping(value = "/getFilePathAttachment", method = RequestMethod.POST)
	public @ResponseBody String getFilePathAttachment(HttpSession session,HttpServletRequest request,String id) {

		System.err.println("file path id --"+id);
		
		String filePath = Rdao.get_AttachmentIndividual(Integer.parseInt(id));

		System.err.println("filePath ==att==="+filePath);

		return filePath;
	}
	 
	@SuppressWarnings("null")
	@RequestMapping(value = "/attachmentfiledownloadIndividual")
	public void attachmentfiledownloadIndividual(@ModelAttribute("path") String filePath, ModelMap model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {

		final int BUFFER_SIZE = 4096;


System.err.println("================="+filePath);
		model.put("filePath", filePath);

		ServletContext context = request.getSession().getServletContext();
		try {
			if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {
				@SuppressWarnings("deprecation")
				String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
				File downloadFile = new File(fullPath);
				FileInputStream inputStream = new FileInputStream(downloadFile);
				String mimeType = context.getMimeType(fullPath);
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
				response.setContentType(mimeType);
				response.setContentLength((int) downloadFile.length());
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
				response.setHeader(headerKey, headerValue);
				ServletOutputStream outStream = response.getOutputStream();
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
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
				response.setHeader(headerKey, headerValue);
				ServletOutputStream outStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outStream.close();
			}
		} catch (Exception ex) {
			@SuppressWarnings("deprecation")
			String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
			File downloadFile = new File(fullPath);
			FileInputStream inputStream = new FileInputStream(downloadFile);
			String mimeType = context.getMimeType(fullPath);
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
			response.setHeader(headerKey, headerValue);
			ServletOutputStream outStream = response.getOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			inputStream.close();
			outStream.close();
		}

	}
	@RequestMapping(value = "/getayusAbhaDatalistStudent", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getayusAbhaDatalistStudent(String userId) {
		List<Map<String, Object>> list = Rdao.getayusAbhaDatalistStudent(userId);
		System.err.println("list--" + list);
		return list;
	}
}
