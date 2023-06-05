package com.AyushEdu.controller.Regulation;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.Principal;
import java.sql.Timestamp;
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
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DISTRICT_MSTR;
import com.AyushEdu.Models.Regulation.REG_NCH_WORKING_PLACE_DTL_A_CH;
//import com.AyushEdu.Models.Regulation.EDU_REGULATION_HOSPITAL_CHILD_HISTORY;
import com.AyushEdu.Models.Regulation.REG_NCH_MED_DEGREE_DTL_A_CH;
import com.AyushEdu.Models.Regulation.REG_NCH_MED_DEGREE_DTL_A_SUB_CH;
//import com.AyushEdu.Models.Regulation.EDU_REGULATION_MEDICAL_DEGREE_CHILD_ATTACHMENT_SUBCHILD_HISTORY;
//import com.AyushEdu.Models.Regulation.EDU_REGULATION_MEDICAL_DEGREE_CHILD_HISTORY;
import com.AyushEdu.Models.Regulation.REG_NCH_FORM_A_P;
import com.AyushEdu.Models.Regulation.REG_NCH_FORM_A_HISTORY;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.Regulation.EduRegDao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Regulation_Controller {

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

	@RequestMapping(value = "admin/Regulation_Url", method = RequestMethod.GET)
	public ModelAndView Regulation_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		if (request.getHeader("Referer") == null) {
			session.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}

//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Regulation_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		String login_role = session.getAttribute("login_role").toString();
		String role = session.getAttribute("role").toString();
		System.out.println("rolerole "+role);
		Mmap.put("getInstituteList", common.getUniverCityList(sessionFactory));
		Calendar calendar = Calendar.getInstance();
		Mmap.put("msg", msg);
		Mmap.put("login_role", login_role);
		Mmap.put("MedDistrictName", common.getMedDistrictName(sessionFactory));
		Mmap.put("MedStateName", common.getMedStateName(sessionFactory));
		Mmap.put("getMedNationality", common.getMedNationality(sessionFactory));
		Session sessionHQ = this.sessionFactory.openSession();
		String username = session.getAttribute("username").toString();
		Mmap.put("username", username);

		try {
			int data = (int) sessionHQ.createQuery("select userId from UserLogin where upper(userName)=:id")
					.setParameter("id", username.trim().toUpperCase()).setMaxResults(1).uniqueResult();
			Mmap.put("userId", data);
			if (data != 0) {
				Mmap.put("setDataCMD", Rdao.getDataByUserNameForDraft(data));
				Mmap.put("setAddMoreMedicalCMD", Rdao.medicalData(data));
				Mmap.put("setAddMoreHospCMD", Rdao.HospitalData(data));
				Mmap.put("setAddMoreMedicalAttachmentChildCMD", Rdao.medicalDataChildAttachment(data));
				// Mmap.put("setRegAuth", Rdao.RegAuth(data));
				String pid = Rdao.getUserId(data);
				Mmap.put("p_id", pid);

				if (pid != null && !pid.trim().equals("")) {
					int data2 = Integer.parseInt(pid);
					REG_NCH_FORM_A_P INF = (REG_NCH_FORM_A_P) sessionHQ.get(REG_NCH_FORM_A_P.class, data2);
					Mmap.put("parentid", data2);
					Mmap.put("valid_dt", INF.getValid_up_to());
					String hid="0";
					
					if (INF.getStatus() == 0) {
						hid="2";
					} 
//					if (INF.getStatus() == 1 && INF.getNrh_status() != 1) {
//						hid="1";
//					} 
					if (INF.getStatus() == 1   && INF.getInstitute_status() == 1 && INF.getState_status() == 1 && INF.getNrh_status() == 1 ) {
						hid="3";
					} 
					
					if(role.equals("Student_NCH") && INF.getStatus() == 1 && INF.getInstitute_status() == 1 &&  INF.getState_status() != 0){
						hid="4";
					}
					
					if(role.equals("Student_NCH") && INF.getStatus() == 1 && INF.getInstitute_status() == 0 ){
						hid="5";
					}
					if((role.equals("Student_NCH")||role.equals("Intern_NCH") ||role.equals("Practitioner_NCH")) && 
						 INF.getStatus() == 1 && INF.getInstitute_status() == 1 &&  (INF.getState_status() == 0 ||  INF.getNrh_status()==0)){
						hid="6";
					}
					 
					
					if((role.equals("Student_NCH")||role.equals("Intern_NCH") ||role.equals("Practitioner_NCH")) && INF.getStatus() == 1 && 
							INF.getInstitute_status() == 1 &&  INF.getState_status() == 1 &&  INF.getNrh_status()==1){
						hid="7";
					}
					
					//janki
//					if(role.equals("Intern_NCH") &&  INF.getStatus() == 1 && INF.getInstitute_status() == 0 && INF.getState_status()==0 && INF.getNrh_status()==0) {
//						hid="4";
//					}
//					if(role.equals("Intern_NCH") &&  INF.getStatus() == 1 && INF.getInstitute_status() == 0 && INF.getState_status()==-1 && INF.getNrh_status()==-1) {
//						hid="5";
//					}
//					if(role.equals("Intern_NCH") &&  INF.getStatus() == 1 && INF.getInstitute_status() == 1 && INF.getState_status()==0 && INF.getNrh_status()==-1) {
//						hid="8";
//					}
					
					
					//janki clg
					if(role.equals("Intern_NCH") &&  INF.getStatus() == 1 && 
							INF.getClg_status() == 0 &&	 INF.getInstitute_status() == 0 && INF.getState_status()==0 && INF.getNrh_status()==0) {
						hid="4";
					}
					if(role.equals("Intern_NCH") &&  INF.getStatus() == 1 && 
							INF.getClg_status() == 0 &&	INF.getInstitute_status() == -1 && INF.getState_status()==-1 && INF.getNrh_status()==-1) {
						hid="5";
					}
					
					if(role.equals("Intern_NCH") &&  INF.getStatus() == 1 && INF.getInstitute_status() == 1 && INF.getState_status()==0 && INF.getNrh_status()==-1) {
 						hid="8";
 					}
					 
					if(role.equals("Intern_NCH") &&  INF.getStatus() == 1  && INF.getClg_status() == 1 && INF.getInstitute_status() == 0 && INF.getState_status()==-1 && INF.getNrh_status()==-1) {
 						hid="6";
 					}
					 
					System.err.println("hid---      :     "+hid);
					
					Mmap.put("hid", hid);
				} else {
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

		return new ModelAndView("regulation_Tiles");
	}

	public String fileuploadmultipart(long index, String desc, String gs_no, byte[] b, String name) {
		String extension = "";
		String fname = "";

		try {
			byte[] bytes = b; // file.getBytes();
			// Creating the directory to store file
			// String rootPath = System.getProperty("catalina.home");
			String rootPath = "srv/Document";
			File dir = new File(rootPath + File.separator);
			System.err.println("dir---" + dir);
			// File dir = new File("E://mining_documents");
			if (!dir.exists())
				dir.mkdirs();

			String filename = name; // file.getOriginalFilename();

			int i = filename.lastIndexOf('.');
			if (i >= 0) {
				extension = filename.substring(i + 1);
			}

			// Create the file on server
			java.util.Date date = new java.util.Date();
			fname = dir.getAbsolutePath() + File.separator
					+ ("Ayush" + index + "-" + new Timestamp(date.getTime())).toString().replace(":", "").toString()
							.replace(".", ".").toString().replace(" ", "").toString().replace("-", "").toString()
					+ "." + extension;
			File serverFile = new File(fname);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			System.out.println("Error");
		}
		return fname;
	}

	@PostMapping(value = "/Regulation_Action")
	public ModelAndView Regulation_Action(@Validated @ModelAttribute("RegulationCMD") REG_NCH_FORM_A_P td,
			BindingResult result, HttpServletRequest request, MultipartHttpServletRequest mul, ModelMap model,
			HttpSession session, Principal principal, RedirectAttributes ra, MultipartFile files)
			throws IOException, ParseException {

//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Regulation_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");
		String username = session.getAttribute("username").toString();
		String login_role = session.getAttribute("login_role").toString();
		String roleid = session.getAttribute("roleid").toString();
		
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
//		if ((first_name == null || first_name.trim().equals("")) || validation.maxlengthcheck50(first_name) == false || validation.isOnlyAlphabet(first_name) == false) {
//			ra.addAttribute("msg", "Please Enter First Name And Should be Only Alphabate and Should be maxlength 50");
//			System.err.println("428");
//			return new ModelAndView("redirect:Regulation_Url");
//			
//		}
//		
//		if ((father_name == null || father_name.trim().equals("")) || validation.maxlengthcheck50(father_name) == false || validation.isOnlyAlphabet(father_name) == false) {
//			ra.addAttribute("msg", "Please Enter father Name And Should be Only Alphabate and Should be maxlength 50");
//			System.err.println("435");
//			return new ModelAndView("redirect:Regulation_Url");
//		}
//		
//		if ((email_id == null || email_id.trim().equals("")) || validation.maxlengthcheck50(email_id) == false) {
//			ra.addAttribute("msg", "Please Enter Email id and Should be maxlength 50");
//			return new ModelAndView("redirect:Regulation_Url");
//		}
//		
//		if ((dob == null || dob.equals("DD/MM/YYYY"))) {
//			ra.addAttribute("msg", "Please Enter dob");
//			return new ModelAndView("redirect:Regulation_Url");
//		}
//		
//		if ((mo_no == null || mo_no.equals("0") || validation.isOnlyNumer(request.getParameter("mo_no")) == true || validation.maxlengthcheck10(request.getParameter("mo_no")) == false)) {
//			ra.addAttribute("msg", "Please Enter Mobile No should be onlynumber and maxlenght 10");
//			return new ModelAndView("redirect:Regulation_Url");
//		}
//		
//		if ((upload_img_hid == null || upload_img_hid.trim().equals(""))) {
//			ra.addAttribute("msg", "Please Enter upload img hidden");
//			return new ModelAndView("redirect:Regulation_Url");
//		}
//		if ((reg_state == null || reg_state.equals("0"))) {
//			ra.addAttribute("msg", "Please Enter Registration State");
//			return new ModelAndView("redirect:Regulation_Url");
//		}
//		if ((per_address1 == null || per_address1.trim().equals(""))) {
//			ra.addAttribute("msg", "Please Enter Permenent address line1");
//			return new ModelAndView("redirect:Regulation_Url");
//		}
//		
//		if ((per_address2 == null || per_address2.trim().equals(""))) {
//			ra.addAttribute("msg", "Please Enter Permenent address line2");
//			return new ModelAndView("redirect:Regulation_Url");
//		}
//		
//		if ((per_address3 == null || per_address3.trim().equals(""))) {
//			ra.addAttribute("msg", "Please Enter Permenent address line3");
//			return new ModelAndView("redirect:Regulation_Url");
//		}
//		
//		if ((per_state == null || per_state.equals("0"))) {
//			ra.addAttribute("msg", "Please Enter Permenent State");
//			return new ModelAndView("redirect:Regulation_Url");
//		}
//		
//		if ((per_district == null || per_district.equals("0"))) {
//			ra.addAttribute("msg", "Please Enter Per District");
//			return new ModelAndView("redirect:Regulation_Url");
//		}
//		
//		if ((per_pincode == null && per_pincode.trim().equals("")) || validation.maxlengthcheckpincode(per_pincode) == false || validation.minlengthcheckpincode(per_pincode) == false || validation.isOnlyNumer(per_pincode) == true) {
//			ra.addAttribute("msg", "Please Enter perment pincode should be max and min length and only number");
//			return new ModelAndView("redirect:Regulation_Url");
//		}
//		
//		if ((pre_address1 == null || pre_address1.trim().equals(""))) {
//			ra.addAttribute("msg", "Please Enter Present address line1");
//			return new ModelAndView("redirect:Regulation_Url");
//		}
//		
//		if ((pre_address2 == null || pre_address2.trim().equals(""))) {
//			ra.addAttribute("msg", "Please Enter Present address line2");
//			return new ModelAndView("redirect:Regulation_Url");
//		}
//		
//		if ((pre_address3 == null || pre_address3.trim().equals(""))) {
//			ra.addAttribute("msg", "Please Enter Present address line3");
//			return new ModelAndView("redirect:Regulation_Url");
//		}
//		
//		if(pre_state == null || pre_state.equals("0") || pre_state == "0") {
//			ra.addAttribute("msg", "Please Enter Preseent State");
//			return new ModelAndView("redirect:Regulation_Url");
//		}
//		
//		if ((pre_district == null || pre_district.equals("0") || pre_district == "0")) {
//			ra.addAttribute("msg", "Please Enter Present District");
//			return new ModelAndView("redirect:Regulation_Url");
//		}
//		
//		if ((pre_pincode == null || pre_pincode.trim().equals("")) || validation.maxlengthcheckpincode(pre_pincode) == false || validation.minlengthcheckpincode(pre_pincode) == false || validation.isOnlyNumer(pre_pincode) == true) {
//			ra.addAttribute("msg", "Please Enter Present pincode should be max and min length and only number");
//			return new ModelAndView("redirect:Regulation_Url");
//		}
//		
//		if ((curr_address == null || curr_address.trim().equals(""))) {
//			ra.addAttribute("msg", "Please Enter Current address line1");
//			return new ModelAndView("redirect:Regulation_Url");
//		}
//		
//		if ((curr_address2 == null || curr_address2.trim().equals(""))) {
//			ra.addAttribute("msg", "Please Enter Current address line2");
//			return new ModelAndView("redirect:Regulation_Url");
//		}
//		
//		if ((curr_address3 == null || curr_address3.trim().equals(""))) {
//			ra.addAttribute("msg", "Please Enter Current address line3");
//			return new ModelAndView("redirect:Regulation_Url");
//		}
//		
//		if ((curr_state == null || curr_state.equals("0") || curr_state == "0")) {
//			ra.addAttribute("msg", "Please Enter Cuurent State");
//			return new ModelAndView("redirect:Regulation_Url");
//		}
//		
//		if ((curr_district == null || curr_district.equals("0") || curr_district == "0")) {
//			ra.addAttribute("msg", "Please Enter Cuurent District");
//			return new ModelAndView("redirect:Regulation_Url");
//		}
//		
//		if ((curr_pincode == null || curr_pincode.trim().equals("")) || validation.maxlengthcheckpincode(curr_pincode) == false || validation.minlengthcheckpincode(curr_pincode) == false || validation.isOnlyNumer(curr_pincode) == true) {
//			ra.addAttribute("msg", "Please Enter Current pincode should be max and min length and only number");
//			return new ModelAndView("redirect:Regulation_Url");
//		}
//		
//		if ((alt_mo_no1 == null || mo_no.equals("0") || validation.isOnlyNumer(request.getParameter("alt_mo_no1")) == true || validation.maxlengthcheck10(request.getParameter("alt_mo_no1")) == false)) {
//			ra.addAttribute("msg", "Please Enter Alternet Mobile No should be onlynumber and maxlenght 10");
//			return new ModelAndView("redirect:Regulation_Url");
//		}
//		
//		if ((alt_mo_no2 == null || mo_no.equals("0") || validation.isOnlyNumer(request.getParameter("alt_mo_no2")) == true || validation.maxlengthcheck10(request.getParameter("alt_mo_no2")) == false)) {
//			ra.addAttribute("msg", "Please Enter Alternet Mobile No2 should be onlynumber and maxlenght 10");
//			return new ModelAndView("redirect:Regulation_Url");
//		}
//
//		if(typeOfDegree1 == null || typeOfDegree1.equals("0") || typeOfDegree1 == "0") {
//			ra.addAttribute("msg", "Please Enter Type Of Degree");
//			return new ModelAndView("redirect:Regulation_Url");
//		}
//		
//		if(DegreeName1 == null || DegreeName1.equals("0") || DegreeName1 == "0") {
//			ra.addAttribute("msg", "Please Enter Degree Name");
//			return new ModelAndView("redirect:Regulation_Url");
//		}
//		
//		if ((monthYearOfDegree1 == null || monthYearOfDegree1.equals("DD/MM/YYYY"))) {
//			ra.addAttribute("msg", "Please Enter Month Of Degree");
//			return new ModelAndView("redirect:Regulation_Url");
//		}
//		
//		if(NameOfUniversity1 == null || NameOfUniversity1.equals("0") || NameOfUniversity1 == "0") {
//			ra.addAttribute("msg", "Please Enter Name Of University");
//			return new ModelAndView("redirect:Regulation_Url");
//		} 
		
//		//SECURITY
		if (pract_status != null && pract_status.equals("1")) {
//			//SECURITY-----
			if (photo_path.getOriginalFilename().isEmpty()) {
				if(upload_img_hid.isEmpty()) {
				ra.addAttribute("msg", "Please Upload Photo");
				return new ModelAndView("redirect:intern_Regulation_Url");
				}
			}
			if (!photo_path.getOriginalFilename().isEmpty()) {
				if (photo_path.getOriginalFilename().split("[.]").length > 2) {
					ra.addAttribute("msg", "Invalid file extension for Document");
					return new ModelAndView("redirect:Regulation_Url");
				}
				String upload_fileEXt = FilenameUtils.getExtension(photo_path.getOriginalFilename()).toLowerCase();
				if (!upload_fileEXt.equals("jpg") && !upload_fileEXt.equals("jpeg") && !upload_fileEXt.equals("png")) {
					ra.addAttribute("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed");
					return new ModelAndView("redirect:Regulation_Url");
				}
				long filesize = photo_path.getSize() / 1024;
				if (filesize > 50) {
					ra.addAttribute("msg", "File size should be 50 kb or less");
					return new ModelAndView("redirect:Regulation_Url");
				}
			}
//			//SECURITY-----

			if (first_name == null || first_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter First Name");
				return new ModelAndView("redirect:Regulation_Url");
			}

			if (validation.maxlengthcheck50(first_name) == false) {
				ra.addAttribute("msg", "First Name " + validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Regulation_Url");
			}
			if (validation.isOnlyAlphabetDASH(first_name) == false) {
				ra.addAttribute("msg", "First Name " + validation.isOnlyAlphabetMSGDASH);
				return new ModelAndView("redirect:Regulation_Url");
			}

			if (father_name == null || father_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Father Name");
				return new ModelAndView("redirect:Regulation_Url");
			}

			if (validation.maxlengthcheck50(father_name) == false) {
				ra.addAttribute("msg", "Father Name " + validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Regulation_Url");
			}
			if (validation.isOnlyAlphabetDASH(father_name) == false) {
				ra.addAttribute("msg", "Father Name " + validation.isOnlyAlphabetMSGDASH);
				return new ModelAndView("redirect:Regulation_Url");
			}

			if (dob == null || dob.equals("0")) {
				ra.addAttribute("msg", "Please Enter Date Of Birth");
				return new ModelAndView("redirect:Regulation_Url");
			}

			if (dob == null || dob.equals("DD/MM/YYYY")) {
				ra.addAttribute("msg", "Please Enter The Date Of Birth");
				return new ModelAndView("redirect:Regulation_Url");
			}
			if (mo_no.equals("0") || mo_no == null) {
				ra.addAttribute("msg", "Please Enter Mobile Number");
				return new ModelAndView("redirect:Regulation_Url");
			}
			if (upload_img_hid == null || upload_img_hid.trim().equals("")) {
				ra.addAttribute("msg", "Please Select Photo");
				return new ModelAndView("Regulation_Actionredirect:Regulation_Url");
			}
			
						/* address details */

			if (per_address1 == null || per_address1.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Permanent Address Line 1");
				return new ModelAndView("redirect:Regulation_Url");
			}

			if (per_state.equals("0")) {
				ra.addAttribute("msg", "Please Select Permanent State");
				return new ModelAndView("redirect:Regulation_Url");
			}
			if (!login_role.equals("Student")) {
				if (reg_state.equals("0")) {
					ra.addAttribute("msg", "Please Select Registration State");
					return new ModelAndView("redirect:Regulation_Url");
				}
			}
			if (per_district.equals("0")) {
				ra.addAttribute("msg", "Please Select Permanent District");
				return new ModelAndView("redirect:Regulation_Url");
			}
			if (per_pincode == null || per_pincode.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Permanent Pincode");
				return new ModelAndView("redirect:Regulation_Url");
			}

			if (validation.maxlengthcheckpincode(per_pincode) == false) {
				ra.addAttribute("msg", "Permanent Pin Code " + validation.MaxlengthcheckMSGpincode);
				return new ModelAndView("redirect:Regulation_Url");
			}
			if (validation.minlengthcheckpincode(per_pincode) == false) {
				ra.addAttribute("msg", "Permanent Pin Code " + validation.MinlengthcheckMSGpincode);
				return new ModelAndView("redirect:Regulation_Url");
			}
			if (validation.isOnlyNumer(per_pincode) == true) {
				ra.addAttribute("msg", "Permanent Pin Code " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:Regulation_Url");
			}

			if (pre_state != null && pre_state.equals("0") && pre_state != "0") {
				ra.addAttribute("msg", "Please Select Present State");
				return new ModelAndView("redirect:Regulation_Url");
			}
			if (pre_district != null && pre_district.equals("0") && pre_district != "0") {
				ra.addAttribute("msg", "Please Select Present District");
				return new ModelAndView("redirect:Regulation_Url");
			}
			if (pre_pincode == null || pre_pincode.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Present Pincode");
				return new ModelAndView("redirect:Regulation_Url");
			}

			if (validation.isOnlyNumer(pre_pincode) == true) {
				ra.addAttribute("msg", "Corresponding Present PinCode " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:Regulation_Url");
			}
			if (curr_state != null && curr_state.equals("0") && curr_state != "0") {
				ra.addAttribute("msg", "Please Select Corresponding State");
				return new ModelAndView("redirect:Regulation_Url");
			}

			if (curr_district != null && curr_district.equals("0") && curr_district != "0") {
				ra.addAttribute("msg", "Please Select Corresponding District");
				return new ModelAndView("redirect:Regulation_Url");
			}

			if (curr_pincode != null && curr_pincode.equals("0") && curr_pincode != "0") {
				ra.addAttribute("msg", "Please Enter Corresponding Pincode");
				return new ModelAndView("redirect:Regulation_Url");
			}

			if (validation.maxlengthcheckpincode(curr_pincode) == false) {
				ra.addAttribute("msg", "Corresponding Pin Code " + validation.MaxlengthcheckMSGpincode);
				return new ModelAndView("redirect:Regulation_Url");
			}
			if (validation.minlengthcheckpincode(curr_pincode) == false) {
				ra.addAttribute("msg", "Corresponding Pin Code " + validation.MinlengthcheckMSGpincode);
				return new ModelAndView("redirect:Regulation_Url");
			}
			if (validation.isOnlyNumer(curr_pincode) == true) {
				ra.addAttribute("msg", "Corresponding Pin Code " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:Regulation_Url");
			}

			if (validation.isOnlyNumer(request.getParameter("mo_no")) == true) {
				ra.addAttribute("msg", "Mobile Number " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:Regulation_Url");
			}
			if (validation.maxlengthcheck10(request.getParameter("mo_no")) == false) {
				ra.addAttribute("msg", "Mobile Number " + validation.MaxlengthcheckMSG10);
				return new ModelAndView("redirect:Regulation_Url");
			}

			if (validation.isOnlyNumer(request.getParameter("alt_mo_no1")) == true) {
				ra.addAttribute("msg", "Alternet Mobile Number 1" + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:Regulation_Url");
			}
			if (validation.maxlengthcheck10(request.getParameter("alt_mo_no1")) == false) {
				ra.addAttribute("msg", "Alternet Mobile Number 1" + validation.MaxlengthcheckMSG10);
				return new ModelAndView("redirect:Regulation_Url");
			}
			if (validation.isOnlyNumer(request.getParameter("alt_mo_no2")) == true) {
				ra.addAttribute("msg", "Alternet Mobile Number 2" + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:Regulation_Url");
			}
			if (validation.maxlengthcheck10(request.getParameter("alt_mo_no2")) == false) {
				ra.addAttribute("msg", "Alternet Mobile Number 2" + validation.MaxlengthcheckMSG10);
				return new ModelAndView("redirect:Regulation_Url");
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
			int data = (int) sessionHQL.createQuery("select userId from UserLogin where upper(userName)=:id")
					.setParameter("id", username.trim().toUpperCase()).setMaxResults(1).uniqueResult();
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

		if (Integer.parseInt(parent_id) == 0) {
			td.setCreated_by(username);
//			--URMIK
			td.setCreated_date(new Date());
			td.setFirst_name(first_name);
			td.setAyush_id(aayushid);
			td.setAbha_no(abha_no);
			td.setAadhaar_no((adhar));
			td.setMo_no((mo_no));
			td.setPhoto_path(photo_path_att);
			td.setLst_updt_photo_dt(new Date());
			if(roleid!= null && !roleid.equals(""))
			{
				td.setRole(Integer.parseInt(roleid) );
			}
			
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
			if (alt_mo_no1 != null && !alt_mo_no1.equals("")) {
				td.setAlt_mo_no1(alt_mo_no1);
			}
			if (alt_mo_no1 != null && !alt_mo_no1.equals("")) {
				td.setAlt_mo_no2(alt_mo_no2);
			}
			if (alterEmail_id1 != null && !alterEmail_id1.equals("")) {
				td.setAlt_email_id1(alterEmail_id1);
			}
			if (alterEmail_id2 != null && !alterEmail_id2.equals("")) {
				td.setAlt_email_id2(alterEmail_id2);
			}
			if (pract_status != null && !pract_status.equals("") && pract_status.equals("0")) {
				td.setStatus(0);
				td.setClg_status(-1);
				td.setInstitute_status(-1);
				td.setState_status(-1);
				td.setNrh_status(-1);
			} else if (pract_status != null && !pract_status.equals("") && !pract_status.equals("0")) {
				td.setStatus(1);
				td.setClg_status(0);
				td.setInstitute_status(-1);
				td.setState_status(-1);
				td.setNrh_status(-1);
			}
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
			if (nationality != null && nationality != "0") {
				td.setNationality(Integer.parseInt(nationality));
			}
			if (per_district != null && per_district != "0") {
				td.setPer_district(Integer.parseInt(per_district));
			}
			if (pre_district != null && pre_district.equals("0") && pre_district != "0") {
				td.setPre_district(Integer.parseInt(pre_district));
			}

			if (per_state != null && per_state != "0") {
				td.setPer_state(Integer.parseInt(per_state));
			}
			if (!login_role.equals("Student")) {
				if (reg_state != null && reg_state != "0") {
					td.setReg_state(Integer.parseInt(reg_state));
				}
			} else {
				td.setReg_state(0);
			}
			if (pre_state != null && pre_state != "0") {
				td.setPre_state(Integer.parseInt(pre_state));
			}
			if (curr_address != null && !curr_address.equals("")) {
				td.setCurr_address(curr_address);
			}

			if (curr_address2 != null && !curr_address2.equals("")) {
				td.setCurr_address2(curr_address2);
			}
			if (curr_address3 != null && !curr_address3.equals("")) {
				td.setCurr_address3(curr_address3);
			}
			if (curr_state != null && curr_state.equals("0") && curr_state != "0") {
				td.setCurr_state(Integer.parseInt(curr_state));
			}
			if (curr_district != null && curr_district != "0") {
				td.setCurr_district(Integer.parseInt(curr_district));
			}
			if (curr_pincode != null && !curr_pincode.equals("")) {
				td.setCurr_pincode(Integer.parseInt(curr_pincode));
			}
			td.setCreated_date(new Date());
			if (photo_path_att != "") {
				td.setPhoto_path(photo_path_att);
				td.setLst_updt_photo_dt(new Date());
			}
			td.setDel_status(0);
			if (gender != null && !gender.equals("")) {
				td.setGender(Integer.parseInt(gender));
			}
			if (father_name != null && father_name.equals(""))
				td.setFather_name(father_name);
			if (dob != null && !dob.equals(""))
				td.setDob(dob);
			td.setPract_type("NEW");
			int p_id = (int) sessionHQL.save(td);
			sessionHQL.flush();
			sessionHQL.clear();
			if (p_id > 0) {
				int count_hidden_att_name_med = Integer.parseInt(request.getParameter("count_hidden_att_name_med"));
				for (int j = 1; j <= count_hidden_att_name_med; j++) {
					String degree_hid = request.getParameter("degree_hid" + j);
					System.err.println("checkkkkkkkkkkkkkkkkkkkkkkk" + degree_hid);
					Long c = (Long) sessionHQL
							.createQuery("select count(id) from  REG_NCH_MED_DEGREE_DTL_A_CH where id=:degree_hid")
							.setParameter("degree_hid", Integer.parseInt(degree_hid)).uniqueResult();
					if (c != null && c == 0) {
						String typeOfDegree = request.getParameter("typeOfDegree" + j);
						String DegreeName = request.getParameter("DegreeName" + j);
						String monthYearOfDegree = request.getParameter("monthYearOfDegree" + j);
						String NameOfUniversity = request.getParameter("NameOfUniversity" + j);
						REG_NCH_MED_DEGREE_DTL_A_CH ec = new REG_NCH_MED_DEGREE_DTL_A_CH();
						ec.setRegulation_p_id(p_id);
						if (typeOfDegree != null && !typeOfDegree.equals("")) {
							ec.setType_of_degree(Integer.parseInt(typeOfDegree));
						}
						if (DegreeName != null && !DegreeName.equals("")) {
							ec.setDegree_name(Integer.parseInt(DegreeName));
						}
						ec.setName_of_institute(NameOfUniversity);
						ec.setMonth_and_year_of_degree(monthYearOfDegree);
						ec.setCreated_date(new Date());
						ec.setCreated_by(username);
						ec.setClg_status(0);
						ec.setMonth_and_year_of_degree(monthYearOfDegree);
						int at_pid = (int) sessionHQL.save(ec);
						sessionHQL.flush();
						sessionHQL.clear();
						if (at_pid > 0) {
							if (request.getParameter("count_hidden_att_doc" + j) != null
									&& !request.getParameter("count_hidden_att_doc" + j).equals("")) {
								int count_hid_subchild = Integer
										.parseInt(request.getParameter("count_hidden_att_doc" + j));
								for (int k = 1; k <= count_hid_subchild; k++) {
									List<String> lst = sessionHQL.createQuery(
											"select id from REG_NCH_MED_DEGREE_DTL_A_CH where regulation_p_id=:id")
											.setParameter("id", ec.getId()).list();
									if (lst != null && lst.size() > 0) {
										for (int i = 0; i < lst.size(); i++) {
											sessionHQL.createQuery(
													"update  REG_NCH_MED_DEGREE_DTL_A_SUB_CH set modified_by=:modified_by,modified_date=:modified_date where parent_id=:id")
													.setParameter("id", lst.get(i))
													.setParameter("modified_by", username)
													.setParameter("modified_date", new Date()).executeUpdate();
										}
									}
									REG_NCH_MED_DEGREE_DTL_A_SUB_CH subatt = new REG_NCH_MED_DEGREE_DTL_A_SUB_CH();
									String NameOfATT = request.getParameter("NameOfAttachment" + j + "_" + k);
									MultipartFile attachment = mul.getFile("attachmentDocument" + j + "_" + k);
									
									//SECURITY-----
									if (attachment.getOriginalFilename().isEmpty()) {
										ra.addAttribute("msg", "Please Upload Photo");
										return new ModelAndView("redirect:Regulation_Url");
									}
									if (!attachment.getOriginalFilename().isEmpty()) {
										
										
										
										if (attachment.getOriginalFilename().split("[.]").length > 2) {
											ra.addAttribute("msg", "Invalid file extension for Document");
											return new ModelAndView("redirect:Regulation_Url");
										}
										
										
										String upload_fileEXt = FilenameUtils.getExtension(attachment.getOriginalFilename()).toLowerCase();
										if (!upload_fileEXt.equals("pdf")) {
											ra.addAttribute("msg", "Only .pdf file extensions allowed");
											return new ModelAndView("redirect:Regulation_Url");
										}
										long filesize = attachment.getSize() / 1024;
										if (filesize > 200) {
											ra.addAttribute("msg", "File size should be 200 kb or less");
											return new ModelAndView("redirect:Regulation_Url");
										}
									}
									//SECURITY-----
									
									String attachmentDoc_hid = request.getParameter("attachmentDoc_hid" + j + "_" + k);
									String pic = "";
									if (!attachment.isEmpty()) {
										pic = common.fileupload(attachment.getBytes(), attachment.getOriginalFilename(),
												"UploadHardCopy1");
										if (pic != "") {
											subatt.setAttachment(pic);
										} else {
											subatt.setAttachment(attachmentDoc_hid);
										}
									}

									if (NameOfATT != null && !NameOfATT.equals("")) {
										subatt.setName_of_attachment(NameOfATT);
									}
									subatt.setParent_id(at_pid);
									sessionHQL.save(subatt);
									sessionHQL.flush();
									sessionHQL.clear();
								}

							}
						}
					} else {

						String typeOfDegree = request.getParameter("typeOfDegree" + j);
						String DegreeName = request.getParameter("DegreeName" + j);
						String monthYearOfDegree = request.getParameter("monthYearOfDegree" + j);
						String NameOfUniversity = request.getParameter("NameOfUniversity" + j);

						REG_NCH_MED_DEGREE_DTL_A_CH HSCH = (REG_NCH_MED_DEGREE_DTL_A_CH) sessionHQL
								.get(REG_NCH_MED_DEGREE_DTL_A_CH.class, Integer.parseInt(degree_hid));

						HSCH.setRegulation_p_id(p_id);
					
						if (typeOfDegree != null && !typeOfDegree.equals("")) {
							HSCH.setType_of_degree(Integer.parseInt(typeOfDegree));
						}
						if (DegreeName != null && !DegreeName.equals("")) {
							HSCH.setDegree_name(Integer.parseInt(DegreeName));
						}
						HSCH.setName_of_institute(NameOfUniversity);
						HSCH.setMonth_and_year_of_degree(monthYearOfDegree);
						HSCH.setClg_status(0);
						HSCH.setModified_date(new Date());
						HSCH.setModified_by(username);
						HSCH.setMonth_and_year_of_degree(monthYearOfDegree);

						sessionHQL.update(HSCH);
						sessionHQL.flush();
						sessionHQL.clear();

						int at_pid = HSCH.getId();
						if (at_pid > 0) {
							if (request.getParameter("count_hidden_att_doc" + j) != null
									&& !request.getParameter("count_hidden_att_doc" + j).equals("")) {

								int count_hid_subchild = Integer
										.parseInt(request.getParameter("count_hidden_att_doc" + j));

								for (int k = 1; k <= count_hid_subchild; k++) {
									List<String> lst = sessionHQL.createQuery(
											"select id from REG_NCH_MED_DEGREE_DTL_A_CH where regulation_p_id=:id")
											.setParameter("id", HSCH.getId()).list();

									if (lst != null && lst.size() > 0) {

										for (int i = 0; i < lst.size(); i++) {
											sessionHQL.createQuery(
													"update  REG_NCH_MED_DEGREE_DTL_A_SUB_CH set modified_by=:modified_by,modified_date=:modified_date where parent_id=:id")
													.setParameter("id", lst.get(i))
													.setParameter("modified_by", username)
													.setParameter("modified_date", new Date()).executeUpdate();
										}

									}
									REG_NCH_MED_DEGREE_DTL_A_SUB_CH subatt = new REG_NCH_MED_DEGREE_DTL_A_SUB_CH();
									String NameOfATT = request.getParameter("NameOfAttachment" + j + "_" + k);
									MultipartFile attachment = mul.getFile("attachmentDocument" + j + "_" + k);
									String attachmentDoc_hid = request.getParameter("attachmentDoc_hid" + j + "_" + k);

									String pic = "";
									if (!attachment.isEmpty()) {
										pic = common.fileupload(attachment.getBytes(), attachment.getOriginalFilename(),
												"UploadHardCopy1");

										if (pic != "") {
											subatt.setAttachment(pic);
										} else {
											subatt.setAttachment(attachmentDoc_hid);
										}
									}

									if (NameOfATT != null && !NameOfATT.equals("")) {
										subatt.setName_of_attachment(NameOfATT);
									}
									//SECURITY-----
									if (attachment.getOriginalFilename().isEmpty()) {
										if (attachmentDoc_hid.isEmpty()) {
										ra.addAttribute("msg", "Please Upload Photo in row"+j);
										return new ModelAndView("redirect:Regulation_Url");
									}
									}
									if (!attachment.getOriginalFilename().isEmpty()) {
										if (attachment.getOriginalFilename().split("[.]").length > 2) {
											ra.addAttribute("msg", "Invalid file extension for Document in row "+j );
											return new ModelAndView("redirect:Regulation_Url");
										}
										String upload_fileEXt = FilenameUtils.getExtension(attachment.getOriginalFilename()).toLowerCase();
										if (!upload_fileEXt.equals("pdf")) {
											ra.addAttribute("msg", "Only .pdf file extensions allowed in row"+j);
											return new ModelAndView("redirect:Regulation_Url");
										}
										long filesize = attachment.getSize() / 1024;
										if (filesize > 200) {
											ra.addAttribute("msg", "File size should be 200 kb or less");
											return new ModelAndView("redirect:Regulation_Url");
										}
									}
//									//SECURITY-----
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
			if (p_id > 0) {
				if (request.getParameter("count_hidden_att_Hospital") != null
						&& !request.getParameter("count_hidden_att_Hospital").equals("")) {
					int count_hidden_att_Hospital = Integer.parseInt(request.getParameter("count_hidden_att_Hospital"));
					for (int j = 1; j <= count_hidden_att_Hospital; j++) {

						String working_hid = request.getParameter("working_hid" + j);
						Long c = (Long) sessionHQL
								.createQuery(
										"select count(id) from  REG_NCH_WORKING_PLACE_DTL_A_CH where id=:working_hid")
								.setParameter("working_hid", Integer.parseInt(working_hid)).uniqueResult();
						if (c != null && c == 0) {
							String place_of_working = request.getParameter("place_of_working" + j);
							String place_of_working_name = request.getParameter("place_of_working_name" + j);
							String adjunct_place = request.getParameter("adjunct_place" + j);
							String landline = request.getParameter("landline" + j);
							String address1 = request.getParameter("hos_address1_" + j);
							String address2 = request.getParameter("hos_address2_" + j);
							String address3 = request.getParameter("hos_address3_" + j);
							String hos_state = request.getParameter("hos_state" + j);
							String hos_district = request.getParameter("hos_district" + j);
							String email = request.getParameter("email" + j);
							String authority_type = request.getParameter("authority_type" + j);
							String name_of_res_p = request.getParameter("name_of_res_p" + j);
							String mobile_no = request.getParameter("mobileHosp" + j);
							REG_NCH_WORKING_PLACE_DTL_A_CH Hc = new REG_NCH_WORKING_PLACE_DTL_A_CH();
							Hc.setMobile_no(mobile_no);
							Hc.setPlace_of_working_name(place_of_working_name);
							Hc.setHos_address1(address1);
							Hc.setHos_address2(address2);
							Hc.setHos_address3(address3);
							if (hos_state != null && !hos_state.equals("")) {
								Hc.setHos_state(Integer.parseInt(hos_state));
							}
							if (hos_district != null && !hos_district.equals("")) {
								Hc.setHos_district(Integer.parseInt(hos_district));
							}
							Hc.setRegulation_p_id(p_id);
							if (place_of_working != null && !place_of_working.equals("")) {
								Hc.setPlace_of_working(Integer.parseInt(place_of_working));
							}
							Hc.setAdjunct_place(adjunct_place);
							Hc.setLandline(landline);
							Hc.setEmail(email);
							if (authority_type != null && !authority_type.equals("")) {
								Hc.setAuthority_type(Integer.parseInt(authority_type));
							}
							Hc.setName_of_res_p(name_of_res_p);
							Hc.setModified_date(new Date());
							Hc.setModified_by(username);
							sessionHQL.save(Hc);
							sessionHQL.flush();
							sessionHQL.clear();
						}
					}
				}
			}
			tx.commit();
			ra.addAttribute("msg", "Your Details has been Saved as Draft");

		} else {

			String parentid = request.getParameter("parentid");
			REG_NCH_FORM_A_P td2 = (REG_NCH_FORM_A_P) sessionHQL.get(REG_NCH_FORM_A_P.class,
					Integer.parseInt(parent_id));

			if (Integer.parseInt(parent_id) != 0) {
				if (gender != null && !gender.equals("null") && !gender.equals("")) {
					td2.setGender(Integer.parseInt(gender));
				}
				td2.setId(Integer.parseInt(parent_id));
				td2.setModified_by(username);
				td2.setModified_date(new Date());
				td2.setAyush_id(aayushid);
				td2.setAbha_no(abha_no);
				td2.setFirst_name(first_name);
				td2.setAadhaar_no((adhar));
				td2.setMo_no((mo_no));
				if (per_pincode != null && !per_pincode.equals("")) {
					BigInteger perpincode = BigInteger.ZERO;
					perpincode = new BigInteger(per_pincode);
					td2.setPer_pincode(perpincode);
				}
				if (alt_mo_no1 != null && !alt_mo_no1.equals("")) {
					td2.setAlt_mo_no1(alt_mo_no1);
				}
				if (alt_mo_no1 != null && !alt_mo_no1.equals("")) {
					td2.setAlt_mo_no2(alt_mo_no2);
				}
				if (photo_path_att != null && !photo_path_att.trim().equals("")) {
					td2.setPhoto_path(photo_path_att);
					td.setLst_updt_photo_dt(new Date());
				} else {
					td2.setPhoto_path(upload_img_hid);
					td.setLst_updt_photo_dt(new Date());
				}
				td2.setCheck_address(Integer.parseInt(check_address));
				td2.setValid_up_to(td2.getValid_up_to());
				if (pre_pincode != null && !pre_pincode.equals("")) {
					BigInteger prepincode = BigInteger.ZERO;
					prepincode = new BigInteger(pre_pincode);
					td2.setPre_pincode(prepincode);
				}
				if (alterEmail_id1 != null && !alterEmail_id1.equals("")) {
					td2.setAlt_email_id1(alterEmail_id1);
				}
				if (alterEmail_id2 != null && !alterEmail_id2.equals("")) {
					td2.setAlt_email_id2(alterEmail_id2);
				}
				
				//ut
				 
				if(u_t == 0 ) { // when reg state is not union territory
				
					if (pract_status != null && !pract_status.equals("") && pract_status.equals("0")) { // UPDATE BUTTON
						System.err.println("1");
						td2.setStatus(0);
						td2.setClg_status(-1);
						td2.setInstitute_status(-1);
						td2.setState_status(-1);
						td2.setNrh_status(-1);
					} 
					if (pract_status != null && !pract_status.equals("") && !pract_status.equals("0") && (td2.getNrh_en_no()== null 
							|| !String.valueOf(td2.getNrh_en_no()).equals(""))) { // FINAL SUBMIT
						System.err.println("2");
						td2.setStatus(1);
						td2.setClg_status(0);
						td2.setInstitute_status(-1);
						td2.setState_status(-1);
						td2.setNrh_status(-1);
					}
					//KAJAL
					if (pract_status != null && !pract_status.equals("") && !pract_status.equals("0") &&  (td2.getNrh_en_no()!= null && !String.valueOf(td2.getNrh_en_no()).equals(""))) { // FINAL SUBMIT
						
						System.err.println("3");
						td2.setStatus(1);
						td2.setClg_status(1);
						td2.setInstitute_status(1);
	 					td2.setState_status(0);
						td2.setNrh_status(-1);
					}
					
				}
			
				else {// when reg state is   union territory
					
				 
				 if (pract_status != null && !pract_status.equals("") && pract_status.equals("0")) { // UPDATE BUTTON
					 System.err.println("4");	
					 td2.setStatus(0);
						td2.setClg_status(-1);
						td2.setInstitute_status(-1);
						td2.setState_status(-1);
						td2.setNrh_status(-1);
					} 
					if (pract_status != null && !pract_status.equals("") && !pract_status.equals("0") && (td2.getNrh_en_no()== null || !String.valueOf(td2.getNrh_en_no()).equals(""))) { // FINAL SUBMIT
						System.err.println("5");
						td2.setStatus(1);
						td2.setClg_status(1);
						td2.setInstitute_status(1);
						td2.setState_status(1);
						td2.setNrh_status(0);
					}
					//KAJAL
					if (pract_status != null && !pract_status.equals("") 
						&& !pract_status.equals("0") 
						&&  (td2.getNrh_en_no()!= null && !String.valueOf(td2.getNrh_en_no()).equals(""))) { // FINAL SUBMIT
						System.err.println("6");
						td2.setStatus(1);
						td2.setClg_status(1);
						td2.setInstitute_status(1);
	 					td2.setState_status(1);
						td2.setNrh_status(0);
					}
				 
				 
				}
				
			

				td2.setValid_up_to(td2.getValid_up_to());
				td2.setPre_address_details1(pre_address1);
				td2.setPre_address_details2(pre_address2);
				td2.setPre_address_details3(pre_address3);
				td2.setPer_address_details1(per_address1);
				td2.setPer_address_details2(per_address2);
				td2.setPer_address_details3(per_address3);

				if (curr_address != null && !curr_address.equals("")) {
					td2.setCurr_address(curr_address);
				}

				if (curr_address2 != null && !curr_address2.equals("")) {
					td2.setCurr_address2(curr_address2);
				}

				if (curr_address3 != null && !curr_address3.equals("")) {
					td2.setCurr_address3(curr_address3);
				}

				if (per_state != null && per_state != "") {
					td2.setPer_state(Integer.parseInt(per_state));
				}
				if (!login_role.equals("Student")) {
					if (reg_state != null && reg_state != "") {

						td2.setReg_state(Integer.parseInt(reg_state));
					}
				} else {
					td2.setReg_state(0);
				}
				if (per_district != null && per_district != "") {
					td2.setPer_district(Integer.parseInt(per_district));
				}

				if (pre_state != null && pre_state != "") {
					td2.setPre_state(Integer.parseInt(pre_state));
				}

				if (pre_district != null && pre_district != "") {
					td2.setPre_district(Integer.parseInt(pre_district));
				}

				if (curr_state != null && curr_state != "") {
					td2.setCurr_state(Integer.parseInt(curr_state));
				}

				if (curr_district != null && curr_district != "") {
					td2.setCurr_district(Integer.parseInt(curr_district));
				}

				if (curr_pincode != null && !curr_pincode.equals("")) {
					td2.setCurr_pincode(Integer.parseInt(curr_pincode));
				}

				if (father_name != null && father_name.equals("")) {
					td2.setFather_name(father_name);
				}
				if (dob != null && !dob.equals(""))
					td2.setDob(dob);
				td2.setDel_status(0);
				td2.setFather_name(father_name);
				td2.setGender(td.getGender());
				td2.setMo_no(td.getMo_no());
				td2.setPhoto_path(photo_path_att);
				
				int p_id = td2.getId();
				if (p_id > 0) {
					int data = 0;
					int data2 = 0;
					int count_hidden_att_name_med = Integer.parseInt(request.getParameter("count_hidden_att_name_med"));
					for (int j = 1; j <= count_hidden_att_name_med; j++) {
						String degree_hid = request.getParameter("degree_hid" + j);
						Long c = (Long) sessionHQL
								.createQuery("select count(id) from  REG_NCH_MED_DEGREE_DTL_A_CH where id=:degree_hid")
								.setParameter("degree_hid", Integer.parseInt(degree_hid)).uniqueResult();
						if (c != null && c == 0) {
							String typeOfDegree = request.getParameter("typeOfDegree" + j);
							String DegreeName = request.getParameter("DegreeName" + j);
							String monthYearOfDegree = request.getParameter("monthYearOfDegree" + j);
							String NameOfUniversity = request.getParameter("NameOfUniversity" + j);

							REG_NCH_MED_DEGREE_DTL_A_CH ec = new REG_NCH_MED_DEGREE_DTL_A_CH();
							ec.setRegulation_p_id(p_id);

							if (typeOfDegree != null && !typeOfDegree.equals("")) {
								ec.setType_of_degree(Integer.parseInt(typeOfDegree));
							}
							if (DegreeName != null && !DegreeName.equals("")) {
								ec.setDegree_name(Integer.parseInt(DegreeName));
							}
							ec.setName_of_institute(NameOfUniversity);
							ec.setMonth_and_year_of_degree(monthYearOfDegree);
							ec.setCreated_date(new Date());
							ec.setCreated_by(username);
							ec.setMonth_and_year_of_degree(monthYearOfDegree);
							ec.setStatus(0);
							ec.setClg_status(0);
							int at_pid = (int) sessionHQL.save(ec);
							td2.setInstitute_status(0);
							
							td2.setState_status(-1);
							sessionHQL.flush();
							sessionHQL.clear();

							if (at_pid > 0) {
								//DEGREE ADD ON 
								if (request.getParameter("count_hidden_att_doc" + j) != null
										&& !request.getParameter("count_hidden_att_doc" + j).equals("")) {
									int count_hid_subchild = Integer
											.parseInt(request.getParameter("count_hidden_att_doc" + j));
									for (int k = 1; k <= count_hid_subchild; k++) {
										List<String> lst = sessionHQL.createQuery(
												"select id from REG_NCH_MED_DEGREE_DTL_A_CH where regulation_p_id=:id")
												.setParameter("id", ec.getId()).list();
										if (lst != null && lst.size() > 0) {
											for (int i = 0; i < lst.size(); i++) {
												sessionHQL.createQuery(
														"update  REG_NCH_MED_DEGREE_DTL_A_SUB_CH set modified_by=:modified_by,modified_date=:modified_date where parent_id=:id")
														.setParameter("id", lst.get(i))
														.setParameter("modified_by", username)
														.setParameter("modified_date", new Date()).executeUpdate();
											}
										}
										REG_NCH_MED_DEGREE_DTL_A_SUB_CH subatt = new REG_NCH_MED_DEGREE_DTL_A_SUB_CH();
										String NameOfATT = request.getParameter("NameOfAttachment" + j + "_" + k);
										MultipartFile attachment = mul.getFile("attachmentDocument" + j + "_" + k);
										
										String attachmentDoc_hid = request
												.getParameter("attachmentDoc_hid" + j + "_" + k);
										String pic = "";
										if (!attachment.isEmpty()) {
											pic = common.fileupload(attachment.getBytes(),
													attachment.getOriginalFilename(), "UploadHardCopy1");
											if (pic != "") {
												subatt.setAttachment(pic);
											} else {
												subatt.setAttachment(attachmentDoc_hid);
											}
										}

										if (NameOfATT != null && !NameOfATT.equals("")) {
											subatt.setName_of_attachment(NameOfATT);
										}
										
										//SECURITY-----
										if (attachment.getOriginalFilename().isEmpty()) {
											if (attachmentDoc_hid.isEmpty()) {
											ra.addAttribute("msg", "Please Upload Photo in row"+j);
											return new ModelAndView("redirect:Regulation_Url");
										}
										}
										if (!attachment.getOriginalFilename().isEmpty()) {
											if (attachment.getOriginalFilename().split("[.]").length > 2) {
												ra.addAttribute("msg", "Invalid file extension for Document in row "+j);
												return new ModelAndView("redirect:Regulation_Url");
											}
											String upload_fileEXt = FilenameUtils.getExtension(attachment.getOriginalFilename()).toLowerCase();
											if (!upload_fileEXt.equals("pdf")) {
												ra.addAttribute("msg", "Only .pdf file extensions allowed in row"+j);
												return new ModelAndView("redirect:Regulation_Url");
											}
											long filesize = attachment.getSize() / 1024;
											if (filesize > 200) {
												ra.addAttribute("msg", "File size should be 200 kb or less");
												return new ModelAndView("redirect:Regulation_Url");
											}
										}
//										//SECURITY-----
										subatt.setParent_id(at_pid);
										sessionHQL.save(subatt);
										sessionHQL.flush();
										sessionHQL.clear();

									}

								}
							}
						} else {
							//NO DEGREE ADD
							
							String typeOfDegree = request.getParameter("typeOfDegree" + j);
							String DegreeName = request.getParameter("DegreeName" + j);
							String monthYearOfDegree = request.getParameter("monthYearOfDegree" + j);
							String NameOfUniversity = request.getParameter("NameOfUniversity" + j);

							REG_NCH_MED_DEGREE_DTL_A_CH HSCH = (REG_NCH_MED_DEGREE_DTL_A_CH) sessionHQL
									.get(REG_NCH_MED_DEGREE_DTL_A_CH.class, Integer.parseInt(degree_hid));

							HSCH.setRegulation_p_id(p_id);
							if (typeOfDegree != null && !typeOfDegree.equals("")) {
								HSCH.setType_of_degree(Integer.parseInt(typeOfDegree));
							}
							if (DegreeName != null && !DegreeName.equals("")) {
								HSCH.setDegree_name(Integer.parseInt(DegreeName));
							}
							HSCH.setName_of_institute(NameOfUniversity);
							HSCH.setMonth_and_year_of_degree(monthYearOfDegree);
							HSCH.setModified_date(new Date());
							HSCH.setModified_by(username);
							HSCH.setMonth_and_year_of_degree(monthYearOfDegree);
//							---09/12
							HSCH.setClg_status(0);
							int at_pid = HSCH.getId();
							if (at_pid > 0) {
								if (request.getParameter("count_hidden_att_doc" + j) != null
										&& !request.getParameter("count_hidden_att_doc" + j).equals("")) {

									int count_hid_subchild = Integer
											.parseInt(request.getParameter("count_hidden_att_doc" + j));
									for (int m = 1; m <= count_hid_subchild; m++) {
										String f_att = request.getParameter("f_att" + j + "_" + m);
										String name_of_attachment = request
												.getParameter("NameOfAttachment" + j + "_" + m);
										MultipartFile attachment = mul.getFile("attachmentDocument" + j + "_" + m);
										String attachmentDoc_hid = request
												.getParameter("attachmentDoc_hid" + j + "_" + m);
										//SECURITY-----
										if (attachment.getOriginalFilename().isEmpty()) {
											if (attachmentDoc_hid.isEmpty()) {
											ra.addAttribute("msg", "Please Upload Photo in row"+j);
											return new ModelAndView("redirect:Regulation_Url");
										}
										}
										if (!attachment.getOriginalFilename().isEmpty()) {
											if (attachment.getOriginalFilename().split("[.]").length > 2) {
												ra.addAttribute("msg", "Invalid file extension for Document in row "+j);
												return new ModelAndView("redirect:Regulation_Url");
											}
											String upload_fileEXt = FilenameUtils.getExtension(attachment.getOriginalFilename()).toLowerCase();
											if (!upload_fileEXt.equals("pdf")) {
												ra.addAttribute("msg", "Only .pdf file extensions allowed in row"+j);
												return new ModelAndView("redirect:Regulation_Url");
											}
											long filesize = attachment.getSize() / 1024;
											if (filesize > 200) {
												ra.addAttribute("msg", "File size should be 200 kb or less");
												return new ModelAndView("redirect:Regulation_Url");
											}
										}
//										//SECURITY-----
										if (f_att != null && f_att != "" && !f_att.equals("")) {
											// update

											sessionHQL.createQuery(
													"update  REG_NCH_MED_DEGREE_DTL_A_SUB_CH set name_of_attachment=:name_of_attachment, modified_by=:modified_by,modified_date=:modified_date where id=:f_att")
													.setParameter("name_of_attachment", name_of_attachment)
													.setParameter("f_att", Integer.parseInt(f_att))
													.setParameter("modified_by", username)
													.setParameter("modified_date", new Date()).executeUpdate();
											

										} else {
											// save

											REG_NCH_MED_DEGREE_DTL_A_SUB_CH subatt = new REG_NCH_MED_DEGREE_DTL_A_SUB_CH();
											String pic = "";
											if (!attachment.isEmpty()) {
												pic = common.fileupload(attachment.getBytes(),
														attachment.getOriginalFilename(), "UploadHardCopy1");

												if (pic != "") {
													subatt.setAttachment(pic);
												} else {
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
											HSCH.setStatus(0);
											HSCH.setClg_status(0);

										}

									}
								}
								
							}
							//20/12/2022 parth
							String role = session.getAttribute("role").toString();
							if(role.equals("Intern_NCH")) {
								HSCH.setStatus(0);
								System.err.println("1225 for check 0 status");

							}
//							HSCH.setStatus(0);
							sessionHQL.update(HSCH);
							sessionHQL.flush();
							sessionHQL.clear();
						}
					}
//						---------------institute /hosp latest-------------------

					int count_hidden_att_Hospital = Integer.parseInt(request.getParameter("count_hidden_att_Hospital"));
					if (request.getParameter("count_hidden_att_Hospital") != null
							&& !request.getParameter("count_hidden_att_Hospital").equals("")) {

						for (int j = 1; j <= count_hidden_att_Hospital; j++) {

							String working_hid = request.getParameter("working_hid" + j);

							if (working_hid.equals("0")) {

								String place_of_working = request.getParameter("place_of_working" + j);
								String place_of_working_name = request.getParameter("place_of_working_name" + j);
								String adjunct_place = request.getParameter("adjunct_place" + j);
								String address1 = request.getParameter("hos_address1_" + j);
								String address2 = request.getParameter("hos_address2_" + j);
								String address3 = request.getParameter("hos_address3_" + j);
								String hos_state = request.getParameter("hos_state" + j);
								String hos_district = request.getParameter("hos_district" + j);
								String email = request.getParameter("email" + j);
								String landline = request.getParameter("landline" + j);
								String hos_address1 = request.getParameter("hos_address1" + j);
								String hos_address2 = request.getParameter("hos_address2" + j);
								String hos_address3 = request.getParameter("hos_address3" + j);
								String authority_type = request.getParameter("authority_type" + j);
								String name_of_res_p = request.getParameter("name_of_res_p" + j);
								String mobile_no = request.getParameter("mobileHosp" + j);

								/*
								 * if (pract_status != null && pract_status.equals("1")) {
								 * 
								 * if (place_of_working == null || place_of_working.equals("0")) {
								 * ra.addAttribute("msg", "Please Select Place of Working In Row " + j); return
								 * new ModelAndView("redirect:Regulation_Url"); }
								 * 
								 * if (place_of_working_name == null || place_of_working_name.trim().equals(""))
								 * { ra.addAttribute("msg", "Please Select Name Of Place  In Row " + j); return
								 * new ModelAndView("redirect:Regulation_Url"); }
								 * 
								 * if (validation.maxlengthcheck50(place_of_working_name) == false) {
								 * ra.addAttribute("msg", "Please Select Name Of Place " +
								 * validation.MaxlengthcheckMSG50); return new
								 * ModelAndView("redirect:Regulation_Url"); } if
								 * (validation.isOnlyAlphabetDASH(place_of_working_name) == false) {
								 * ra.addAttribute("msg", "Please Select Name Of Place " +
								 * validation.isOnlyAlphabetMSGDASH); return new
								 * ModelAndView("redirect:Regulation_Url"); }
								 * 
								 * if (adjunct_place == null || adjunct_place.equals("")) {
								 * ra.addAttribute("msg", "Please Enter Name Of Adjunct Place In Row " + j);
								 * return new ModelAndView("redirect:Regulation_Url"); }
								 * 
								 * if (landline == null || landline.trim().equals("")) { ra.addAttribute("msg",
								 * "Please Enter Landline No In Row " + j); return new
								 * ModelAndView("redirect:Regulation_Url"); }
								 * 
								 * if (mobile_no == null || mobile_no.trim().equals("")) {
								 * ra.addAttribute("msg", "Please Enter Mobile No In Row " + j); return new
								 * ModelAndView("redirect:Regulation_Url"); }
								 * 
								 * if (email == null || email.equals("")) { ra.addAttribute("msg",
								 * "Please Enter Email In Row" + j); return new
								 * ModelAndView("redirect:Regulation_Url"); }
								 * 
								 * if (hos_state == null || hos_state.equals("0")) { ra.addAttribute("msg",
								 * "Please Select State In Row " + j); return new
								 * ModelAndView("redirect:Regulation_Url"); }
								 * 
								 * if (hos_district == null || hos_district.equals("0")) {
								 * ra.addAttribute("msg", "Please Select District In Row " + j); return new
								 * ModelAndView("redirect:Regulation_Url"); }
								 * 
								 * if (authority_type == null || authority_type.equals("")) {
								 * ra.addAttribute("msg", "Please Enter Authority Type In Row" + j); return new
								 * ModelAndView("redirect:Regulation_Url"); }
								 * 
								 * }
								 */

								REG_NCH_WORKING_PLACE_DTL_A_CH Hc = new REG_NCH_WORKING_PLACE_DTL_A_CH();
								Hc.setRegulation_p_id(p_id);
								Hc.setMobile_no(mobile_no);
								Hc.setPlace_of_working_name(place_of_working_name);
								Hc.setAdjunct_place(adjunct_place);
								Hc.setHos_address1(address1);
								Hc.setHos_address2(address2);
								Hc.setHos_address3(address3);
								if (hos_state != null && !hos_state.equals("")) {
									Hc.setHos_state(Integer.parseInt(hos_state));
								}
								if (hos_district != null && !hos_district.equals("")) {
									Hc.setHos_district(Integer.parseInt(hos_district));
								}
								Hc.setRegulation_p_id(p_id);
								if (place_of_working != null && !place_of_working.equals("")) {
									Hc.setPlace_of_working(Integer.parseInt(place_of_working));
								}
								Hc.setAdjunct_place(adjunct_place);
								Hc.setLandline(landline);
								Hc.setEmail(email);
								if (authority_type != null && !authority_type.equals("")) {
									Hc.setAuthority_type(Integer.parseInt(authority_type));
								}
								Hc.setName_of_res_p(name_of_res_p);
								Hc.setModified_date(new Date());
								Hc.setModified_by(username);

								int att_pid = (int) sessionHQL.save(Hc);
								sessionHQL.flush();
								sessionHQL.clear();
							}

//							if(working_hid != null && working_hid!= "" && !working_hid.equals("")) {
							else {

								String working_hid2 = request.getParameter("working_hid" + j);
								String place_of_working = request.getParameter("place_of_working" + j);
								String place_of_working_name = request.getParameter("place_of_working_name" + j);
								String adjunct_place = request.getParameter("adjunct_place" + j);
								String hos_state = request.getParameter("hos_state" + j);
								String hos_district = request.getParameter("hos_district" + j);
								String email = request.getParameter("email" + j);
								String landline = request.getParameter("landline" + j);
								String hos_address1 = request.getParameter("hos_address1_" + j);
								String hos_address2 = request.getParameter("hos_address2_" + j);
								String hos_address3 = request.getParameter("hos_address3_" + j);
								String authority_type = request.getParameter("authority_type" + j);
								String name_of_res_p = request.getParameter("name_of_res_p" + j);
								String mobile_no = request.getParameter("mobileHosp" + j);
								sessionHQL.createQuery(
										"update  REG_NCH_WORKING_PLACE_DTL_A_CH set place_of_working=:place_of_working,place_of_working_name=:place_of_working_name,adjunct_place=:adjunct_place,hos_address1=:hos_address1,"
												+ "hos_address2=:hos_address2,hos_address3=:hos_address3,mobile_no=:mobile_no,hos_state=:hos_state,hos_district=:hos_district,email=:email,landline=:landline,authority_type=:authority_type,name_of_res_p=:name_of_res_p,modified_by=:modified_by,modified_date=:modified_date where id=:working_hid")
										.setParameter("place_of_working", Integer.parseInt(place_of_working))
										.setParameter("place_of_working_name", place_of_working_name)
										.setParameter("adjunct_place", adjunct_place)
										.setParameter("hos_address1", hos_address1)
										.setParameter("hos_address2", hos_address2)
										.setParameter("hos_address3", hos_address3).setParameter("mobile_no", mobile_no)
										.setParameter("hos_state", Integer.parseInt(hos_state))
										.setParameter("hos_district", Integer.parseInt(hos_district))
										.setParameter("email", email).setParameter("landline", landline)
										.setParameter("authority_type", Integer.parseInt(authority_type))
										.setParameter("name_of_res_p", name_of_res_p)
										.setParameter("working_hid", Integer.parseInt(working_hid))
										.setParameter("modified_by", username).setParameter("modified_date", new Date())
										.executeUpdate();
							}
							if (pract_status != null && pract_status.equals("1")) {
								ra.addAttribute("msg",
										"Your Form has been Successfully Forwarded to University/State/NCH Council for Verification.");
							} else {
								ra.addAttribute("msg", "Your Form has been Updated Successfully.");

							}
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
					}
				}
				
			}
			
			sessionHQL.update(td2);
			sessionHQL.flush();
			sessionHQL.clear();
			tx.commit();
		}

		return new ModelAndView("redirect:Regulation_Url");

	}

//------------------------------------------------Edit--URMIK--Image_View------------------------------------------------------

	@RequestMapping(value = "/MedicalImagePath1", method = RequestMethod.GET)
	public void censusIdentityConvertpath(@ModelAttribute("i_id") String id, @ModelAttribute("id5") String myImg,
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {

		final int BUFFER_SIZE = 4096;

		String i_id = id;

		String filePath = Rdao.getImagePath(i_id);

		model.put("filePath", filePath);

		ServletContext context = request.getSession().getServletContext();

		try {

			if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

				String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
//			request.getRealPath("/") + "/srv/Document/No_Image.jpg";

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

			// System.out.println(ex);

			// admin//js//img//No_Image.jpg

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
		}
	}
//end

//---------------------URMIK--------Excel-----------------------------------

	@RequestMapping(value = "/Excel_Auth_Posted_query", method = RequestMethod.POST)
	public ModelAndView Excel_Auth_Posted_query(HttpServletRequest request, ModelMap model, HttpSession session,
			String typeReport1, @RequestParam(value = "msg", required = false) String msg) {

//  String roleid = session.getAttribute("roleid").toString();
//	Boolean val = dao.ScreenRedirect("Auth_and_Posted_Strength", roleid);
//	if (val == false) {
//		return new ModelAndView("AccessTiles");
//	}
//	if (request.getHeader("Referer") == null) {
//		msg = "";
//		return new ModelAndView("redirect:bodyParameterNotAllow");
//	}
// 
		ArrayList<ArrayList<String>> Excel = Rdao.pdf_getAuth_and_Posted_StrenghReportDataList();

		List<String> TH = new ArrayList<String>();
		TH.add("Ser No");
		TH.add("NRH Enrollment No");
		TH.add("First Name");
		TH.add("Gender");
		TH.add("Photo Path");
		TH.add("Father Name");
		TH.add("Present Address");
		TH.add("Present District");
		TH.add("Present State");
		TH.add("Present Pincode");
		TH.add("Permanent Address");
		TH.add("Permanent District");
		TH.add("Permanent State");
		TH.add("Permanent Pincode");
		TH.add("Aadhaar No");
		TH.add("Fax No");
		TH.add("Mo. No");
		TH.add("Alternate Mo. No");
		TH.add("Email Id");
		TH.add("Date Of Birth");
		TH.add("Nationality");
		TH.add("Name Of Medical Degree");
		TH.add("Name of University");
		TH.add("Month And Year Of Passing");
		TH.add("Registration No.");
		TH.add("Date Of Registration");
		TH.add("Name Of The Register");
		TH.add("Registration Renewable Or Permanent");
		TH.add("Name Of Hospital Or Institute With Address");
		TH.add("Name Of Patient");
		TH.add("CRH No");
		TH.add("CCH No");
		TH.add("NCH No");

		String Heading = "\nAuth and Posted Strength Search Report";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", Excel);
	}

	@PostMapping(value = "/delete_edu_reg_mstr_Url")
	public ModelAndView delete_edu_reg_mstr_Url(@ModelAttribute("id2") int id, BindingResult result,
			RedirectAttributes ra, HttpServletRequest request, ModelMap model, HttpSession session1) {

		List<String> liststr = new ArrayList<String>();

//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session1.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("Regulation_Url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
			
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {

			int app = session.createQuery(
					"update TB_EDU_REGULATION set modified_by=:modified_by,modified_date=:modified_date,del_status=:del_status where id=:id")
					.setParameter("id", id).setParameter("modified_by", username)
					.setParameter("modified_date", new Date()).setParameter("del_status", 2).executeUpdate();

			tx.commit();
			session.close();
			if (app > 0) {
				System.err.println("check " + (app > 0));
				liststr.add("Data Deleted Successfully.");
			} else {
				liststr.add("Data not Deleted.");
			}
			ra.addAttribute("msg", liststr.get(0));
		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			ra.addAttribute("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:edu_search_reg_url");
	}

	@RequestMapping(value = "/getDegreedetails", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getDegreeName(String typeofdegree) {
		List<Map<String, Object>> list = Rdao.getdegreedetailss(typeofdegree);
		return list;
	}

	@RequestMapping(value = "/getDistrictOnstate", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_DISTRICT_MSTR> getDistrictOnstate(String selval) {
		return common.district(sessionFactory, selval);
	}

	@RequestMapping(value = "/getayusAbhaDatalist", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getayusAbhaDatalist(String userId) {
		List<Map<String, Object>> list = Rdao.getayusAbhaDatalist(userId);
		System.err.println("list--" + list);
		return list;
	}

	//// Medical degree attachment delete Subchild

	@PostMapping(value = "/DeleteMedAttachment")
	public ModelAndView DeleteMedAttachment(@ModelAttribute("f_att") int id, BindingResult result,
			RedirectAttributes ra, HttpServletRequest request, ModelMap model, HttpSession session1) {

		List<String> liststr = new ArrayList<String>();
//		SECURITY -- RIDDHI 
//		if(request.getHeader("Referer") == null ) { 
////			session.invalidate();
//			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			return new ModelAndView("redirect:/landingpage");
//		 }
//		String roleid1 = session1.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("Regulation_Url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String username = session1.getAttribute("username").toString();
		try {

			int app = session.createQuery("Delete from REG_NCH_MED_DEGREE_DTL_A_SUB_CH where id=:id")
					.setParameter("id", id).executeUpdate();

			tx.commit();
			session.close();
			if (app > 0) {

				liststr.add("Data Deleted Successfully.");
			} else {
				liststr.add("Data not Deleted.");
			}
			ra.addAttribute("msg", liststr.get(0));
		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			ra.addAttribute("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:Regulation_Url");
	}

	@PostMapping(value = "/DeleteMedicalAddmore")
	public ModelAndView DeleteMedicalAddmore(@ModelAttribute("f_att") int parent_id, BindingResult result,
			RedirectAttributes ra, HttpServletRequest request, ModelMap model, HttpSession session1) {
		List<String> liststr = new ArrayList<String>();
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String username = session1.getAttribute("username").toString();
		try {
//			SECURITY -- RIDDHI 
//			if(request.getHeader("Referer") == null ) { 
////				session.invalidate();
//				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				return new ModelAndView("redirect:/landingpage");
//			 }
//			String roleid1 = session1.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("Regulation_Url", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
			int app = session.createQuery("Delete from REG_NCH_MED_DEGREE_DTL_A_SUB_CH where parent_id=:parent_id")
					.setParameter("parent_id", parent_id).executeUpdate();

			int app2 = session.createQuery("Delete from REG_NCH_MED_DEGREE_DTL_A_CH where id=:parent_id")
					.setParameter("parent_id", parent_id).executeUpdate();

			tx.commit();
			session.close();
			if (app > 0) {

				liststr.add("Data Deleted Successfully.");
			} else {
				liststr.add("Data not Deleted.");
			}
			ra.addAttribute("msg", liststr.get(0));
		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			ra.addAttribute("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:Regulation_Url");
	}

	@PostMapping(value = "/DeleteInstituteAddmore")
	public ModelAndView DeleteInstituteAddmore(@ModelAttribute("f_att") int id, BindingResult result,
			RedirectAttributes ra, HttpServletRequest request, ModelMap model, HttpSession session1) {
		List<String> liststr = new ArrayList<String>();
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String username = session1.getAttribute("username").toString();
		try {
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session1.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("Regulation_Url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			int app = session.createQuery("Delete from REG_NCH_WORKING_PLACE_DTL_A_CH where id=:id")
					.setParameter("id", id).executeUpdate();

			tx.commit();
			session.close();
			if (app > 0) {

				liststr.add("Data Deleted Successfully.");
			} else {
				liststr.add("Data not Deleted.");
			}
			ra.addAttribute("msg", liststr.get(0));
		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			ra.addAttribute("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:Regulation_Url");
	}

	///// Renew
	@RequestMapping(value = "/admin/Renew_Data_Action", method = RequestMethod.POST)
	public @ResponseBody String Renew_Data_Action(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			MultipartHttpServletRequest mul,RedirectAttributes ra) throws ParseException, IOException {
		System.err.println("2121");
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String parent_id = request.getParameter("parentid");
		String username = session.getAttribute("username").toString();
		String upload_img_hid = request.getParameter("upload_img_hid");
		String gender = request.getParameter("gender");
		String aadhaar_no = request.getParameter("aadhaar_no");
		BigInteger mo_no = new BigInteger(request.getParameter("mo_no"));
		String email_id = request.getParameter("email_id");
		String nationality = request.getParameter("nationality");
		BigInteger alt_mo_no1=BigInteger.valueOf(0);
		if(!request.getParameter("alt_mo_no1").equals("")) {
			 alt_mo_no1 = new BigInteger(request.getParameter("alt_mo_no1"));
		}
		BigInteger alt_mo_no2=BigInteger.valueOf(0);
		if(!request.getParameter("alt_mo_no2").equals("")) {
		 alt_mo_no2 = new BigInteger(request.getParameter("alt_mo_no2"));
		}
		String alt_email_id1 = request.getParameter("alt_email_id1");
		String alt_email_id2 = request.getParameter("alt_email_id2");
		String per_address = request.getParameter("per_address");
		String per_address2 = request.getParameter("per_address2");
		String per_address3 = request.getParameter("per_address3");
		String per_state = request.getParameter("per_state");
		String per_district = request.getParameter("per_district");
		BigInteger per_pincode = new BigInteger(request.getParameter("per_pincode"));
		String pre_address = request.getParameter("pre_address");
		String pre_address2 = request.getParameter("pre_address2");
		String pre_address3 = request.getParameter("pre_address3");
		String pre_state = request.getParameter("pre_state");
		String pre_district = request.getParameter("pre_district");
		BigInteger pre_pincode = new BigInteger(request.getParameter("pre_pincode"));
		String first_name = request.getParameter("first_name");
		String father_name = request.getParameter("father_name");
		String dob1 = request.getParameter("dob");
		SimpleDateFormat formate = new SimpleDateFormat("dd/mm/yyyy");
		Date dob = null;
		if (!dob1.equals("DD/MMM/YYYY") && !dob1.equals("DD/MM/YYYY")) {
			dob = formate.parse(dob1);
		}
		MultipartFile photo_path = mul.getFile("photo_path");
		
		//SECURITY-----
		if (photo_path.getOriginalFilename().isEmpty()) {
			if(upload_img_hid.isEmpty()) {
				return "Please Upload Photo";
			 
			}
		}
		if (!photo_path.getOriginalFilename().isEmpty()) {
			if (photo_path.getOriginalFilename().split("[.]").length > 2) {
				
				return "Invalid file extension for Document";
				
			}
			String upload_fileEXt = FilenameUtils.getExtension(photo_path.getOriginalFilename()).toLowerCase();
			if (!upload_fileEXt.equals("pdf")) {
				return "Only .pdf file extensions allowed";
				
			}
			long filesize = photo_path.getSize() / 1024;
			if (filesize > 50) {
				return "File size should be 50 kb or less";
			}
		}
		//SECURITY-----
		
		String photo_path_att = "";
		if (photo_path != null && !photo_path.isEmpty()) {
			photo_path_att = common.fileupload(photo_path.getBytes(), photo_path.getOriginalFilename(),
					"UploadHardCopy1");
		} else {
			photo_path_att = upload_img_hid;
		}
		if (upload_img_hid == null || upload_img_hid.trim().equals("")) {
			Mmap.put("msg", "Please Select Photo");
		}

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String msg = "";
		try {
			REG_NCH_FORM_A_P td2 = (REG_NCH_FORM_A_P) sessionHQL.get(REG_NCH_FORM_A_P.class,
					Integer.parseInt(parent_id));
			// parent_id ==221
			if (Integer.parseInt(parent_id) != 0) {
				// REG_NCH_FORM_A_HISTORY td3 = new REG_NCH_FORM_A_HISTORY();
// 				td3.setNrh_en_no(td2.getNrh_en_no());
//				td3.setFirst_name(td2.getFirst_name());
//				td3.setGender(td2.getGender());
//				td3.setPre_district(td2.getPre_district());
//				td3.setPre_state(td2.getPre_state());
//				td3.setPre_pincode(td2.getPre_pincode());
//				td3.setPer_district(td2.getPer_district());
//				td3.setPer_state(td2.getPer_state());
//				td3.setPer_pincode(td2.getPer_pincode());
//				td3.setAadhaar_no(td2.getAadhaar_no());
//				td3.setStatus(td2.getStatus());
//				td3.setMo_no(td2.getMo_no());
//				td3.setAlt_mo_no1(td2.getAlt_mo_no1());
//				td3.setAlt_mo_no2(td2.getAlt_mo_no2());
//				td3.setEmail_id(td2.getEmail_id());
//				td3.setAlt_email_id1(td2.getAlt_email_id1());
//				td3.setAlt_email_id2(td2.getAlt_email_id2());
//				td3.setAbha_no(td2.getAbha_no());
//				td3.setAyush_id(td2.getAyush_id());
//				td3.setSuspend_up_to(td2.getSuspend_up_to());
//				td3.setReason(td2.getReason());
//				td3.setDob(td2.getDob());
//				td3.setNationality(td2.getNationality());
//				td3.setDegree(td2.getDegree());
//				td3.setDate_of_reg(td2.getDate_of_reg());
//				td3.setPhoto_path(td2.getPhoto_path());
//				td3.setFather_name(td2.getFather_name());
//				td3.setCreated_by(td2.getCreated_by());
//				td3.setCreated_date(td2.getCreated_date());
//				td3.setModified_by(td2.getModified_by());
//				td3.setModified_date(td2.getModified_date());
//				td3.setDel_status(td2.getDel_status());
//				td3.setInstitute_status(td2.getInstitute_status());
//				td3.setState_status(td2.getState_status());
//				td3.setNrh_status(td2.getNrh_status());
//				td3.setUser_id(td2.getUser_id());
// 				td3.setValid_up_to(td2.getValid_up_to());
// 				td3.setPre_address_details1(td2.getPre_address_details1());
//				td3.setPre_address_details2(td2.getPre_address_details2());
//				td3.setPre_address_details3(td2.getPre_address_details3());
//				td3.setPer_address_details1(td2.getPer_address_details1());
// 				td3.setPer_address_details2(td2.getPer_address_details2());
//				td3.setPer_address_details2(td2.getPer_address_details2());
// 				td3.setState_reg_no(td2.getState_reg_no()) ;
//				td3.setPer_address_details3(td2.getPer_address_details3());
//				td3.setDegree(td2.getDegree());
//				td3.setCurr_address(td2.getCurr_address());
//				td3.setCurr_address2(td2.getCurr_address2());
//				td3.setCurr_address3(td2.getCurr_address3());
//				td3.setCurr_district(td2.getCurr_district());
//				td3.setCurr_pincode(td2.getCurr_pincode());
//				td3.setCurr_state(td2.getCurr_state());
//				td3.setPract_type("RENEW");
//				td3.setStatus(6);
//				td3.setP_id(td2.getId());
//				sessionHQL.save(td3);
//				sessionHQL.flush();
//				sessionHQL.clear();

				td2.setFirst_name(first_name);
				td2.setFather_name(father_name);
				td2.setDob(dob);
				td2.setGender(Integer.parseInt(gender));
				td2.setAadhaar_no(aadhaar_no);
				td2.setMo_no(mo_no);
				td2.setEmail_id(email_id);
				td2.setPhoto_path(photo_path_att);
				td2.setLst_updt_photo_dt(new Date());
				td2.setNationality(Integer.parseInt(nationality));
				td2.setAlt_mo_no1(alt_mo_no1);
				td2.setAlt_mo_no2(alt_mo_no2);
				td2.setAlt_email_id1(alt_email_id1);
				td2.setAlt_email_id2(alt_email_id2);
				td2.setPer_address_details1(per_address);
				td2.setPer_address_details2(per_address2);
				td2.setPer_address_details3(per_address3);
				td2.setPer_state(Integer.parseInt(per_state));
				td2.setPer_district(Integer.parseInt(per_district));
				td2.setPer_pincode(per_pincode);
				td2.setPre_address_details1(pre_address);
				td2.setPre_address_details2(pre_address2);
				td2.setPre_address_details3(pre_address3);
				td2.setPre_state(Integer.parseInt(pre_state));
				td2.setPre_district(Integer.parseInt(pre_district));
				td2.setPre_pincode(pre_pincode);
				String pract_status = request.getParameter("Regulationstatus");
//				if (pract_status != null && pract_status.equals("")) {
//					td2.setStatus(0);
//					td2.setInstitute_status(-1);
//					td2.setState_status(-1);
//					td2.setNrh_status(-1);
//					td2.setPract_type("RENEW");
//					//sessionHQL.update(td2);
// 				} else {
				td2.setStatus(1);
				td2.setPract_type("RENEW");
				td2.setInstitute_status(1);
				td2.setState_status(0);
				td2.setNrh_status(-1);
				td2.setPhoto_path(photo_path_att);
				// }
				sessionHQL.update(td2);
				sessionHQL.flush();
				sessionHQL.clear();

				int pid = td2.getId();
				if (pid != 0) {
					List<Integer> list = new ArrayList<Integer>();
					list = (List<Integer>) sessionHQL.createQuery(
							"select id from REG_NCH_WORKING_PLACE_DTL_A_CH where regulation_p_id=:id order by id desc")
							.setParameter("id", pid).list();

					for (int i = 0; i < list.size(); i++) {
						REG_NCH_WORKING_PLACE_DTL_A_CH HS = (REG_NCH_WORKING_PLACE_DTL_A_CH) sessionHQL
								.get(REG_NCH_WORKING_PLACE_DTL_A_CH.class, list.get(i));

						// HS.setStatus(6);
						sessionHQL.update(HS);
						sessionHQL.flush();
						sessionHQL.clear();
						sessionHQL.flush();
						sessionHQL.clear();

// 						List<Integer> list2 = new ArrayList<Integer>();
// 						list2 = (List<Integer>) sessionHQL.createQuery( "select id from REG_NCH_MED_DEGREE_DTL_A_SUB_CH where parent_id=:id order by id desc")
//								.setParameter("id", HS.getId()).list();
//						for (int l = 0; l < list2.size(); l++) {
//							REG_NCH_MED_DEGREE_DTL_A_SUB_CH HSCH = (REG_NCH_MED_DEGREE_DTL_A_SUB_CH) sessionHQL .get(REG_NCH_MED_DEGREE_DTL_A_SUB_CH.class, list2.get(l));
//							//HSCH.setStatus(6);
//							sessionHQL.update(HSCH);
//							sessionHQL.flush();
//							sessionHQL.clear();	
// 						}

					}

					int count_hidden_att_His = Integer.parseInt(request.getParameter("count_hidden_att_His"));
					int count_hidden_att_Hospital = Integer.parseInt(request.getParameter("count_hidden_att_Hospital"));
					for (int j = count_hidden_att_His + 1; j <= count_hidden_att_Hospital; j++) {
						String place_of_working = request.getParameter("place_of_working" + j);
						String adjunct_place = request.getParameter("adjunct_place" + j);

						String place_of_working_name = request.getParameter("place_of_working_name" + j);
						String landline = request.getParameter("landline" + j);
						String address1 = request.getParameter("hos_address1_" + j);
						String address2 = request.getParameter("hos_address2_" + j);
						String address3 = request.getParameter("hos_address3_" + j);
						String hos_state = request.getParameter("hos_state" + j);
						String hos_district = request.getParameter("hos_district" + j);
						String email = request.getParameter("email" + j);
						String authority_type = request.getParameter("authority_type" + j);
						String name_of_res_p = request.getParameter("name_of_res_p" + j);
						String mobile_no = request.getParameter("mobileHosp" + j);

						REG_NCH_WORKING_PLACE_DTL_A_CH Hc = new REG_NCH_WORKING_PLACE_DTL_A_CH();

						Hc.setMobile_no(mobile_no);
						Hc.setPlace_of_working_name(place_of_working_name);
						Hc.setHos_address1(address1);
						Hc.setHos_address2(address2);
						Hc.setHos_address3(address3);
						if (hos_state != null && !hos_state.equals("")) {
							Hc.setHos_state(Integer.parseInt(hos_state));
						}
						if (hos_district != null && !hos_district.equals("")) {
							Hc.setHos_district(Integer.parseInt(hos_district));
						}
						if (place_of_working != null && !place_of_working.equals("")) {
							Hc.setPlace_of_working(Integer.parseInt(place_of_working));
						}
						Hc.setAdjunct_place(adjunct_place);
						Hc.setLandline(landline);
						Hc.setEmail(email);
						if (authority_type != null && !authority_type.equals("")) {
							Hc.setAuthority_type(Integer.parseInt(authority_type));
						}
						Hc.setName_of_res_p(name_of_res_p);
						Hc.setModified_date(new Date());
						Hc.setModified_by(username);
						Hc.setStatus(0);

						Hc.setRegulation_p_id(pid);
						sessionHQL.save(Hc);
						sessionHQL.flush();
						sessionHQL.clear();
					}
				}

				if (pid != 0) {
					List<Integer> list = new ArrayList<Integer>();

					list = (List<Integer>) sessionHQL.createQuery(
							"select id from REG_NCH_MED_DEGREE_DTL_A_CH where regulation_p_id=:id order by id desc")
							.setParameter("id", pid).list();

					for (int i = 0; i < list.size(); i++) {
						REG_NCH_MED_DEGREE_DTL_A_CH RMD = (REG_NCH_MED_DEGREE_DTL_A_CH) sessionHQL
								.get(REG_NCH_MED_DEGREE_DTL_A_CH.class, list.get(i));
						// RMD.setStatus(6);
						sessionHQL.update(RMD);
						sessionHQL.flush();
						sessionHQL.clear();
						sessionHQL.flush();
						sessionHQL.clear();

						List<Integer> list2 = new ArrayList<Integer>();

						list2 = (List<Integer>) sessionHQL.createQuery(
								"select id from REG_NCH_MED_DEGREE_DTL_A_SUB_CH where parent_id=:id order by id desc")
								.setParameter("id", RMD.getId()).list();

						for (int l = 0; l < list2.size(); l++) {
							REG_NCH_MED_DEGREE_DTL_A_SUB_CH HSCH = (REG_NCH_MED_DEGREE_DTL_A_SUB_CH) sessionHQL
									.get(REG_NCH_MED_DEGREE_DTL_A_SUB_CH.class, list2.get(l));
							// HSCH.setStatus(6);
							sessionHQL.update(HSCH);
							sessionHQL.flush();
							sessionHQL.clear();
							sessionHQL.flush();
							sessionHQL.clear();

						}

					} // STATUS MAINTAIN HS.SETSTATUS(0)
					int count_hidden_att_name_med = Integer.parseInt(request.getParameter("count_hidden_att_name_med"));
					int count_hidden_att_name_med1 = Integer
							.parseInt(request.getParameter("count_hidden_att_name_med1"));

					for (int j = count_hidden_att_name_med1 + 1; j <= count_hidden_att_name_med; j++) {

						String typeOfDegree = request.getParameter("typeOfDegree" + j);
						String DegreeName = request.getParameter("DegreeName" + j);
						String monthYearOfDegree = request.getParameter("monthYearOfDegree" + j);
						String NameOfUniversity = request.getParameter("NameOfUniversity" + j);
						MultipartFile attachment = mul.getFile("attachment" + j);
						
						//SECURITY-----
						if (photo_path.getOriginalFilename().isEmpty()) {
							 return "Please Upload Document in row "+j;
						
						}
						if (!photo_path.getOriginalFilename().isEmpty()) {
							
							
							 if (photo_path.getOriginalFilename().split("[.]").length > 2) {
								 return "Invalid file extension for Document";
							}
							
							
							
							String upload_fileEXt = FilenameUtils.getExtension(photo_path.getOriginalFilename()).toLowerCase();
							if (!upload_fileEXt.equals("pdf")) {
								return "Only .pdf file extensions allowed Document in row "+j;
								
							}
							long filesize = photo_path.getSize() / 1024;
							if (filesize > 50) {
								return "File size should be 50 kb or less";
								
							}
						}
						//SECURITY-----

						String attachment_hid = request.getParameter("attachment_hid" + j);

						REG_NCH_MED_DEGREE_DTL_A_CH ec = new REG_NCH_MED_DEGREE_DTL_A_CH();

						if (attachment != null && !attachment.isEmpty()) {
							String pic = common.fileupload(attachment.getBytes(), attachment.getOriginalFilename(),
									"UploadHardCopy1");
						}

						if (typeOfDegree != null && !typeOfDegree.equals("")) {
							ec.setType_of_degree(Integer.parseInt(typeOfDegree));
						}
						if (DegreeName != null && !DegreeName.equals("")) {
							ec.setDegree_name(Integer.parseInt(DegreeName));
						}
						ec.setName_of_institute(NameOfUniversity);
						ec.setMonth_and_year_of_degree(monthYearOfDegree);
						ec.setModified_by(username);
						ec.setMonth_and_year_of_degree(monthYearOfDegree);
						ec.setRegulation_p_id(pid);
						ec.setStatus(0);

						int at_pid = (int) sessionHQL.save(ec);
						sessionHQL.flush();
						sessionHQL.clear();

						if (at_pid > 0) {
							if (request.getParameter("count_hidden_att_doc" + j) != null
									&& !request.getParameter("count_hidden_att_doc" + j).equals("")) {

								int count_hid_subchild = Integer
										.parseInt(request.getParameter("count_hidden_att_doc" + j));

								for (int k = 1; k <= count_hid_subchild; k++) {
									List<String> lst = sessionHQL.createQuery(
											"select id from REG_NCH_MED_DEGREE_DTL_A_CH where regulation_p_id=:id")
											.setParameter("id", ec.getId()).list();

//									if (lst != null && lst.size() > 0) {
//
//										for (int i = 0; i < lst.size(); i++) {
//											sessionHQL.createQuery(
//													"Delete from REG_NCH_MED_DEGREE_DTL_A_SUB_CH where parent_id=:id")
//													.setParameter("id", lst.get(i)).executeUpdate();
//										}
//
//									}

									REG_NCH_MED_DEGREE_DTL_A_SUB_CH subatt = new REG_NCH_MED_DEGREE_DTL_A_SUB_CH();
									String NameOfATT = request.getParameter("NameOfAttachment" + j + "_" + k);

									System.err.println("NameOfATT----" + NameOfATT);

									MultipartFile attachmentchild = mul.getFile("attachmentDocument" + j + "_" + k);
									
									//SECURITY-----
									if (attachmentchild.getOriginalFilename().isEmpty()) {
										 return "Please Upload Document in row "+j;
									
									}
									if (!attachmentchild.getOriginalFilename().isEmpty()) {
										
										
										
										 if (photo_path.getOriginalFilename().split("[.]").length > 2) {
											 return "Invalid file extension for Document in row "+j;
										}
										
										
										
										String upload_fileEXt = FilenameUtils.getExtension(attachmentchild.getOriginalFilename()).toLowerCase();
										if (!upload_fileEXt.equals("pdf")) {
											return "Only .pdf file extensions allowed Document in row "+j;
											
										}
										long filesize = photo_path.getSize() / 1024;
										if (filesize > 50) {
											return "File size should be 50 kb or less";
											
										}
									}
									//SECURITY-----
									
									String attachmentDoc_hid = request.getParameter("attachmentDoc_hid" + j + "_" + k);

									String pic = "";
									if (!attachmentchild.isEmpty()) {
										pic = common.fileupload(attachmentchild.getBytes(),
												attachmentchild.getOriginalFilename(), "UploadHardCopy1");
										if (pic != "") {
											subatt.setAttachment(pic);
										} else {
											subatt.setAttachment(attachmentDoc_hid);
										}
									} else {
										subatt.setAttachment(attachmentDoc_hid);
									}
									if (NameOfATT != null && NameOfATT.equals("")) {
										subatt.setName_of_attachment(NameOfATT);
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
			msg = "0";
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			msg = "1";

		} finally {
			sessionHQL.flush();
			sessionHQL.clear();
//						sessionHQL.close();
			tx.commit();
		}

		return msg;
	}

	//individual attachment
	 
	@RequestMapping(value = "/getIndivisualAttachmentFilePath", method = RequestMethod.POST)
	public @ResponseBody String getIndivisualAttachmentFilePath(HttpSession session,HttpServletRequest request,String id) {
		String filePath ="";
		if(id != null && id!="") {
			  filePath = Rdao.getIndivisualAttachmentPath(Integer.parseInt(id));
		}
		return filePath;
	}
	
	
	
}
