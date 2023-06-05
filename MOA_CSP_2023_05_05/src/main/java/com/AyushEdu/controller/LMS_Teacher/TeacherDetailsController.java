package com.AyushEdu.controller.LMS_Teacher;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.security.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.UserLogin;

import com.AyushEdu.Models.LMS_NCISM.TB_COURSE_CONTENT_MSTR;
import com.AyushEdu.Models.LMS_Teacher.EDU_LMS_DEGREE_AND_SUPPORT_DOC_CHILD;
import com.AyushEdu.Models.LMS_Teacher.EDU_LMS_FACULTY_TRANSFER;
import com.AyushEdu.Models.LMS_Teacher.EDU_LMS_TEACHER_ACADEMIC_EXPERIENCE_CHILD;
import com.AyushEdu.Models.LMS_Teacher.EDU_LMS_TEACHER_DTL;
import com.AyushEdu.Models.LMS_Teacher.EDU_LMS_TEACHER_EXPERIENCE_CHILD;
import com.AyushEdu.Models.LMS_Teacher.EDU_LMS_TEACHER_OTHER_QUALIFICATION_CHILD;
import com.AyushEdu.Models.LMS_Teacher.EDU_LMS_TEACHER_OTHER_QUALIFICATION_SUB_CHILD;
import com.AyushEdu.Models.LMS_Teacher.EDU_LMS_TEACHER_QUALIFICATION_ATTACHMENT_SUBCHILD;
import com.AyushEdu.Models.LMS_Teacher.EDU_LMS_TEACHER_QUALIFICATION_CHILD;
import com.AyushEdu.controller.AyushId_Directory.AyushId_Directory_Controller;
import com.AyushEdu.controller.Registration.Student_Registration_Excel;
import com.AyushEdu.controller.Registration.Student_Registration_PDF;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.AyushId_Directory.AyushId_DirectoryDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.LMS_Teacher.TeacherDetailsDao;
import com.AyushEdu.dao.LMS_Teacher.TeacherReportDao;
import com.AyushEdu.dao.Registration.Search_Student_RegistrationDao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class TeacherDetailsController {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	TeacherDetailsDao tdao;
	
	@Autowired
	TeacherReportDao trdao;

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	CommonController common;
	
	@Autowired
	Commondao cdao;
	
	@Autowired
	Search_Student_RegistrationDao SSRDao;

	@Autowired
	ValidationController validation;

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	AyushId_Directory_Controller directory;
	
	@Autowired
	AyushId_DirectoryDAO addao;

	@RequestMapping(value = "/Teacher_dtl_Url", method = RequestMethod.GET)
	public ModelAndView Teacher_dtl_Url(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		try {
			
			if (request.getHeader("Referer") == null) {
			//	session.invalidate();
				Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			}
			String roleid1 = session.getAttribute("roleid").toString();
			System.err.println("roleid1----------"+roleid1);
			String username = session.getAttribute("username").toString();
			System.err.println("username---------"+username);
			Boolean val = roledao.ScreenRedirect("Teacher_dtl_Url", roleid1);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String role = session.getAttribute("rolename").toString();
		
		
		Mmap.put("datafetchlog", tdao.getlogininformation(role,Integer.parseInt(userid)));
		Mmap.put("getgenderList", common.getgenderList(sessionFactory));
		Mmap.put("getDocList", common.getDocList(sessionFactory));
		Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
		Mmap.put("TypeOfDegree", common.gettype_of_degree(sessionFactory));
//		System.err.println("common.gettype_of_degree(sessionFactory)---"+common.gettype_of_degree(sessionFactory));
		Mmap.put("getDegreeList", common.getFMcourseList(sessionFactory));
		Mmap.put("getnameofDoc", common.getnameofDoc(sessionFactory));
//		Mmap.put("getcourseList", common.getFMcourseList(sessionFactory));
		Mmap.put("getSubjectList", common.getSubjectList(sessionFactory));
//		Mmap.put("getSystemList", common.getSystemList(sessionFactory));
		Mmap.put("getDesignationList", common.getDesignationList(sessionFactory));
		Mmap.put("getsalary_statusList", common.getsalary_statusList(sessionFactory));
		Mmap.put("getemploymnt_typeList", common.getemploymnt_typeList(sessionFactory));
		Mmap.put("getPrefixList", common.getPrefixList(sessionFactory));
		Mmap.put("getRegTypeList", common.getregistrationtypeList(sessionFactory));
		Mmap.put("Type_of_exp", common.getType_of_exp(sessionFactory));
		
		Mmap.put("getSystemList", cdao.getFacultyNchSystemlist());

		Mmap.put("getUniList", common.getUniverCityList(sessionFactory));
		Mmap.put("getInsttituteList", cdao.getinstituteNchlist());

		
//		Mmap.put("getqualificationchildAttchment", tdao.getqualificationchildAttchment(userid)); /////////Rahul_19_7_22
		List<Map<String, Object>> data = tdao.getAllPersdetails(Integer.parseInt(userid));
		Mmap.put("data", tdao.getAllPersdetails(Integer.parseInt(userid)));
		if (data.size() != 0) {
			int main_id = Integer
					.parseInt(tdao.getAllPersdetails(Integer.parseInt(userid)).get(0).get("mainid").toString());
			
			System.err.println("MAIN-=-=-=-=-=-=ID-=-=-=" + main_id);
			ArrayList<ArrayList<String>> list1 = tdao.getaddmoredata1(main_id);
			ArrayList<ArrayList<String>> list2 = tdao.getaddmoredata2(main_id);
			ArrayList<ArrayList<String>> list3 = tdao.getaddmoredata3(main_id);
			ArrayList<ArrayList<String>> list4 = tdao.getaddmoredata4(main_id);
			
			Mmap.put("Type_of_exp_acad", tdao.getacademicexp(main_id));

			Mmap.put("edit_expchild", list1);
			Mmap.put("edit_expchild_2", list2);
			Mmap.put("edit_expchild_3", list3);
			Mmap.put("edit_expchild_4", list4);
			Mmap.put("getqualificationchildAttchment", tdao.getqualificationchildAttchment(userid));
			
			Mmap.put("getotherQualiAttSubchild", tdao.getotherqualisubchild(main_id));

		}
		Mmap.addAttribute("msg", msg);
		return new ModelAndView("Teacher_dtlTiles");
	}
	

	
	@PostMapping(value = "/tea_dtlAction")
	public ModelAndView tea_dtlAction(@Validated @ModelAttribute("tea_dtl_CMD") EDU_LMS_TEACHER_DTL rd,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra, MultipartHttpServletRequest mul) throws IOException, ParseException {
		Date date = new Date();
		Session sessionHQL = this.sessionFactory.openSession();
		
		
		
		if (request.getHeader("Referer") == null) {
		//	session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		}
		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Teacher_dtl_Url", roleid1);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String username = session.getAttribute("username").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();

		
		Transaction tx = sessionHQL.beginTransaction();
		// DateFormat formatter1 = new SimpleDateFormat("DD/MM/YYYY");
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		
		try {
				String first_name = request.getParameter("first_name");
				String cand_prefix = request.getParameter("cand_prefix");
				String middle_name = request.getParameter("middle_name");
				String last_name = request.getParameter("last_name");
				String gender = request.getParameter("gender");

				String date_of_birth = request.getParameter("date_of_birth");

				String father_name = request.getParameter("father_name");
				String mother_name = request.getParameter("mother_name");
				String spouse_name = request.getParameter("spouse_name");
				String mobile_no = request.getParameter("mobile_no");
				String email = request.getParameter("email");

				String aadhar_no1 = request.getParameter("aadhar_no1");
				String aadhar_no2 = request.getParameter("aadhar_no2");
				String aadhar_no3 = request.getParameter("aadhar_no3");
				String aadhar_no = aadhar_no1 + aadhar_no2 + aadhar_no3;

				String pan_no = request.getParameter("pan_no");
				String present_add_line1 = request.getParameter("present_add_line1");
				String present_add_line2 = request.getParameter("present_add_line2");
				String present_village = request.getParameter("present_village");
				String present_state = request.getParameter("present_state");
				String present_district = request.getParameter("present_district");
				String present_pincode = request.getParameter("present_pincode");
				String present_phn_no = request.getParameter("present_phn_no");

				String per_add_line1 = request.getParameter("per_add_line1");
				String per_add_line2 = request.getParameter("per_add_line2");
				String per_village = request.getParameter("per_village");
				String per_state = request.getParameter("per_state");
				String per_district = request.getParameter("per_district");
				String per_pincode = request.getParameter("per_pincode");
				String per_phn_no = request.getParameter("per_phn_no");

				String state_reg_no = request.getParameter("state_reg_no");
				String state_board_name = request.getParameter("state_board_name");
				String date_of_reg = request.getParameter("date_of_reg");
				String central_reg_no = request.getParameter("central_reg_no");

				// Integer direct_reg_no =
				// Integer.parseInt(request.getParameter("direct_reg_no"));
				String registration_type = request.getParameter("registration_type");
				String direct_reg_no = request.getParameter("direct_reg_no");
				String direct_date_of_reg = request.getParameter("direct_date_of_reg");
				String name_of_department = request.getParameter("name_of_department");

				String academic_quali = request.getParameter("academic_quali");
				String subject = request.getParameter("subject");

				String sub_quali_degree = request.getParameter("sub_quali_degree");
				String sub_deg_course = request.getParameter("sub_deg_course");
				String adjunct_registration_no = request.getParameter("adjunct_registration_no");
				String adjunct_state_no = request.getParameter("adjunct_state_no");
				String state_validity_upto = request.getParameter("state_validity_upto");
				String direct_validity_upto = request.getParameter("direct_validity_upto");
				String cs_date_of_reg = request.getParameter("cs_date_of_reg");
				String other_date_of_reg = request.getParameter("other_date_of_reg");
				String other_validity_upto = request.getParameter("other_validity_upto");
				String data_fetch = request.getParameter("data_fetch");
				String teachercode = request.getParameter("teachercode_hidden");
				String ayush_id = request.getParameter("ayushid_hidden");
				String id_for_update = request.getParameter("id_hidden");
				String hiddenUpdate = request.getParameter("hiddenUpdate");
				String current_designation = request.getParameter("current_designation");
				
				
			
				if (cand_prefix == null || cand_prefix.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select Candidate Prifix");
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (first_name == null || first_name.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter First Name.");
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (validation.maxlengthcheck50(first_name) == false) {
					ra.addAttribute("msg", "First Name " + validation.MaxlengthcheckMSG50);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (validation.isOnlyAlphabet(first_name) == false) {
					ra.addAttribute("msg", "First Name " + validation.isOnlyAlphabetMSG);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}

				if (validation.maxlengthcheck50(middle_name) == false) {
					ra.addAttribute("msg", "Middle Name " + validation.MaxlengthcheckMSG50);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
//				if (validation.isOnlyAlphabetDASH(middle_name) == false) {
//					ra.addAttribute("msg", "Middle Name " + validation.isOnlyAlphabetMSGDASH);
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}

				if (last_name == null || last_name.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Last Name.");
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (validation.maxlengthcheck50(last_name) == false) {
					ra.addAttribute("msg", "Last Name " + validation.MaxlengthcheckMSG50);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (validation.isOnlyAlphabet(last_name) == false) {
					ra.addAttribute("msg", "Last Name " + validation.isOnlyAlphabetMSG);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}

				if (gender == null || gender.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select Gender");
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}

				if (date_of_birth == null || date_of_birth.trim().equals("") || date_of_birth.equals("DD/MM/YYYY")) {
					ra.addAttribute("msg", "Please Enter The Date Of Birth");
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}

				if (father_name == null || father_name.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Father's Name.");
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (validation.maxlengthcheck50(father_name) == false) {
					ra.addAttribute("msg", "Father's Name " + validation.MaxlengthcheckMSG50);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (validation.isOnlyAlphabet(father_name) == false) {
					ra.addAttribute("msg", "Father's Name " + validation.isOnlyAlphabetMSG);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}

				if (mother_name == null || mother_name.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Mother's Name.");
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (validation.maxlengthcheck50(mother_name) == false) {
					ra.addAttribute("msg", "Mother's Name " + validation.MaxlengthcheckMSG50);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (validation.isOnlyAlphabet(mother_name) == false) {
					ra.addAttribute("msg", "Mother's Name " + validation.isOnlyAlphabetMSG);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}

				if (validation.maxlengthcheck50(spouse_name) == false) {
					ra.addAttribute("msg", "Spouse's Name " + validation.MaxlengthcheckMSG50);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (validation.isOnlyAlphabet(spouse_name) == false) {
					ra.addAttribute("msg", "Spouse's Name " + validation.isOnlyAlphabetMSG);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}

				if (mobile_no == null || mobile_no.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Mobile Number");
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (validation.isOnlyNumer(mobile_no) == true) {
					ra.addAttribute("msg", "Mobile Number " + validation.isOnlyNumerMSG);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (validation.maxlengthcheck10(mobile_no) == false) {
					ra.addAttribute("msg", "Mobile Number " + validation.MaxlengthcheckMSG10);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (validation.isValidMobileNo(mobile_no) == false) {
					ra.addAttribute("msg", "Mobile Number " + validation.isOnlyMobilenumberMSG);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}

				if (email == null || email.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Email Address");
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (validation.maxlengthcheck70(email) == false) {
					ra.addAttribute("msg", "Email Address " + validation.MaxlengthcheckMSG70);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}

				if (aadhar_no1 == null || aadhar_no1.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Aadhaar No.");
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (validation.isOnlyNumer(aadhar_no1) == true) {
					ra.addAttribute("msg", "Aadhaar No. " + validation.isOnlyNumerMSG);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (validation.AadharNoLength(aadhar_no1) == false) {
					ra.addAttribute("msg", validation.AadharNoMSG11);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (validation.AadharNoMinLength(aadhar_no1) == false) {
					ra.addAttribute("msg", validation.AadharNoMSGMinlen11);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}

				if (aadhar_no2 == null || aadhar_no2.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Aadhaar No.");
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (validation.isOnlyNumer(aadhar_no2) == true) {
					ra.addAttribute("msg", "Aadhaar No. " + validation.isOnlyNumerMSG);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (validation.AadharNoLength(aadhar_no2) == false) {
					ra.addAttribute("msg", validation.AadharNoMSG11);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (validation.AadharNoMinLength(aadhar_no2) == false) {
					ra.addAttribute("msg", validation.AadharNoMSGMinlen11);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}

				if (aadhar_no3 == null || aadhar_no3.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Aadhaar No.");
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (validation.isOnlyNumer(aadhar_no3) == true) {
					ra.addAttribute("msg", "Aadhaar No. " + validation.isOnlyNumerMSG);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (validation.AadharNoLength(aadhar_no3) == false) {
					ra.addAttribute("msg", validation.AadharNoMSG11);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (validation.AadharNoMinLength(aadhar_no3) == false) {
					ra.addAttribute("msg", validation.AadharNoMSGMinlen11);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}

				if (pan_no == null || pan_no.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter PAN No.");
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (validation.maxlength10(pan_no) == false) {
					ra.addAttribute("msg", "PAN No. " + validation.MaxlengthMSG10);
					return new ModelAndView("redirect:Search_teacher_detail_url");
				}
				if (validation.minlength10(pan_no) == false) {
					ra.addAttribute("msg", "PAN No. " + validation.MinlengthMSG10);
					return new ModelAndView("redirect:Search_teacher_detail_url");
				}
				if (validation.isOnlyAlphabetNumeric(pan_no) == false) {
					ra.addAttribute("msg", "PAN No. " + validation.isOnlyAlphabetNumericMSG);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}

				/////////// Address
				if (per_add_line1 == null || per_add_line1.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Present Address Address-Line-1");
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (validation.maxlengthcheck100(per_add_line1) == false) {
					ra.addAttribute("msg", "Present Address Address-Line-1 " + validation.MaxlengthcheckMSG100);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}

				if (per_add_line2 == null || per_add_line2.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Present Address  Address-Line-2");
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (validation.maxlengthcheck100(per_add_line2) == false) {
					ra.addAttribute("msg", "Present Address Address-Line-2 " + validation.MaxlengthcheckMSG100);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}

				if (per_state == null || per_state.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select State");
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (per_district == null || per_district.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select District");
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}

				if (per_village == null || per_village.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Present Address City/Village");
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (validation.maxlengthcheck100(per_village) == false) {
					ra.addAttribute("msg", "Present Address City/Village " + validation.MaxlengthcheckMSG100);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (validation.isOnlyAlphabet(per_village) == false) {
					ra.addAttribute("msg", "Present Address City/Village " + validation.isOnlyAlphabetMSG);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}

				if (per_pincode == null || per_pincode.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Present Address Pin Code");
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (validation.maxlengthcheckpincode(per_pincode) == false) {
					ra.addAttribute("msg", "Present Address Pin Code " + validation.MaxlengthcheckMSGpincode);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (validation.minlengthcheckpincode(per_pincode) == false) {
					ra.addAttribute("msg", "Present Address Pin Code " + validation.MinlengthcheckMSGpincode);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				if (validation.isOnlyNumer(per_pincode) == true) {
					ra.addAttribute("msg", "Present Address Pin Code " + validation.isOnlyNumerMSG);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}

//				if (per_phn_no == null || per_phn_no.trim().equals("")) {
//					ra.addAttribute("msg", "Please Enter Present Address Phone No.");
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//				if (validation.isOnlyNumer(per_phn_no) == true) {
//					ra.addAttribute("msg", "Present Address Phone No. " + validation.isOnlyNumerMSG);
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//				if (validation.maxlengthcheck10(per_phn_no) == false) {
//					ra.addAttribute("msg", "Present Address Phone No. " + validation.MaxlengthcheckMSG10);
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//				if (validation.isValidMobileNo(per_phn_no) == false) {
//					ra.addAttribute("msg", "Present Address Phone No. " + validation.isOnlyMobilenumberMSG);
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}

				if (request.getParameter("check_address") == null) {

					if (present_add_line1 == null || present_add_line1.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Correspondence Address-Line-1");
						return new ModelAndView("redirect:Teacher_dtl_Url");
					}
					if (validation.maxlengthcheck100(present_add_line1) == false) {
						ra.addAttribute("msg", "Correspondence Address-Line-1 " + validation.MaxlengthcheckMSG100);
						return new ModelAndView("redirect:Teacher_dtl_Url");
					}

					if (present_add_line2 == null || present_add_line2.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Correspondence Address-Line-2");
						return new ModelAndView("redirect:Teacher_dtl_Url");
					}
					if (validation.maxlengthcheck100(present_add_line2) == false) {
						ra.addAttribute("msg", "Correspondence Address-Line-2 " + validation.MaxlengthcheckMSG100);
						return new ModelAndView("redirect:Teacher_dtl_Url");
					}

					if (present_state == null || present_state.trim().equals("0")) {
						ra.addAttribute("msg", "Please Select State");
						return new ModelAndView("redirect:Teacher_dtl_Url");
					}
					if (present_district == null || present_district.trim().equals("0")) {
						ra.addAttribute("msg", "Please Select District");
						return new ModelAndView("redirect:Teacher_dtl_Url");
					}

					if (present_village == null || present_village.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Present City/Village");
						return new ModelAndView("redirect:Teacher_dtl_Url");
					}
					if (validation.maxlengthcheck100(present_village) == false) {
						ra.addAttribute("msg", "Correspondence City/Village " + validation.MaxlengthcheckMSG100);
						return new ModelAndView("redirect:Teacher_dtl_Url");
					}
					if (validation.isOnlyAlphabet(present_village) == false) {
						ra.addAttribute("msg", "Correspondence City/Village " + validation.isOnlyAlphabetMSG);
						return new ModelAndView("redirect:Teacher_dtl_Url");
					}

					if (present_pincode == null || present_pincode.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Correspondence Pin Code");
						return new ModelAndView("redirect:Teacher_dtl_Url");
					}
					if (validation.maxlengthcheckpincode(present_pincode) == false) {
						ra.addAttribute("msg", "Correspondence Pin Code " + validation.MaxlengthcheckMSGpincode);
						return new ModelAndView("redirect:Teacher_dtl_Url");
					}
					if (validation.minlengthcheckpincode(present_pincode) == false) {
						ra.addAttribute("msg", "Correspondence Pin Code " + validation.MinlengthcheckMSGpincode);
						return new ModelAndView("redirect:Teacher_dtl_Url");
					}
					if (validation.isOnlyNumer(present_pincode) == true) {
						ra.addAttribute("msg", "Present Enter Pin Code " + validation.isOnlyNumerMSG);
						return new ModelAndView("redirect:Teacher_dtl_Url");
					}

//					if (present_phn_no == null || present_phn_no.trim().equals("")) {
//						ra.addAttribute("msg", "Please Enter Correspondence Phone No.");
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
//					if (validation.isOnlyNumer(present_phn_no) == true) {
//						ra.addAttribute("msg", "Correspondence Phone No. " + validation.isOnlyNumerMSG);
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
//					if (validation.maxlengthcheck10(present_phn_no) == false) {
//						ra.addAttribute("msg", "Correspondence Phone No. " + validation.MaxlengthcheckMSG10);
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
//					if (validation.isValidMobileNo(present_phn_no) == false) {
//						ra.addAttribute("msg", "Correspondence Phone No. " + validation.isOnlyMobilenumberMSG);
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
					
				}

				//////// Registration Details

				if (registration_type == null || registration_type.trim().equals("1")) {

					if (state_reg_no == null || state_reg_no.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter State Registration No.");
						return new ModelAndView("redirect:Teacher_dtl_Url");
					}
					if (validation.maxlengthcheck50(state_reg_no) == false) {
						ra.addAttribute("msg", "State Registration No. " + validation.MaxlengthcheckMSG50);
						return new ModelAndView("redirect:Teacher_dtl_Url");
					}
//					if (validation.isOnlyAlphabetNumeric(state_reg_no) == false) {
//						ra.addAttribute("msg", "State Registration No. " + validation.isOnlyAlphabetNumericMSG);
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}

//					if (state_board_name == null || state_board_name.trim().equals("")) {
//						ra.addAttribute("msg", "Please Enter Name of the State Board");
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
//					if (validation.maxlengthcheck100(state_board_name) == false) {
//						ra.addAttribute("msg", "Name of the State Board " + validation.MaxlengthcheckMSG100);
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
//					if (validation.isOnlyAlphabetDASH(state_board_name) == false) {
//						ra.addAttribute("msg", "Name of the State Board " + validation.isOnlyAlphabetMSGDASH);
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}

					if (date_of_reg == null || date_of_reg.trim().equals("") || date_of_reg.equals("DD/MM/YYYY")) {
						ra.addAttribute("msg", "Please Enter The Date Of Registration");
						return new ModelAndView("redirect:Teacher_dtl_Url");
					}

//					if (central_reg_no == null || central_reg_no.trim().equals("")) {
//						ra.addAttribute("msg", "Please Enter Central Registration No.");
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
//					if (validation.maxlengthcheck50(central_reg_no) == false) {
//						ra.addAttribute("msg", "Central Registration No. " + validation.MaxlengthcheckMSG50);
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
//					if (validation.isOnlyAlphabetNumeric(central_reg_no) == false) {
//						ra.addAttribute("msg", "Central Registration No. " + validation.isOnlyAlphabetNumericMSG);
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
					
					if (state_validity_upto == null || state_validity_upto.trim().equals("")
							|| state_validity_upto.equals("DD/MM/YYYY")) {
						ra.addAttribute("msg", "Please Enter The Validity Upto");
						return new ModelAndView("redirect:Teacher_dtl_Url");
					}
				}
				if (registration_type == null || registration_type.trim().equals("2")) {

					if (direct_reg_no == null || direct_reg_no.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Direct Registration No.");
						return new ModelAndView("redirect:Teacher_dtl_Url");
					}
					if (validation.maxlengthcheck50(direct_reg_no) == false) {
						ra.addAttribute("msg", "Direct Registration No. " + validation.MaxlengthcheckMSG50);
						return new ModelAndView("redirect:Teacher_dtl_Url");
					}
//					if (validation.isOnlyAlphabetNumeric(direct_reg_no) == false) {
//						ra.addAttribute("msg", "Direct Registration No. " + validation.isOnlyAlphabetNumericMSG);
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}

					if (direct_date_of_reg == null || direct_date_of_reg.trim().equals("")
							|| direct_date_of_reg.equals("DD/MM/YYYY")) {
						ra.addAttribute("msg", "Please Enter The Date Of Registration");
						return new ModelAndView("redirect:Teacher_dtl_Url");
					}

					if (name_of_department == null || name_of_department.trim().equals("")) {
						ra.addAttribute("msg", "Please Enter Name of Department/Organization");
						return new ModelAndView("redirect:Teacher_dtl_Url");
					}
					if (validation.maxlengthcheck100(name_of_department) == false) {
						ra.addAttribute("msg", "Name of Department/Organization " + validation.MaxlengthcheckMSG100);
						return new ModelAndView("redirect:Teacher_dtl_Url");
					}
					if (validation.isOnlyAlphabet(name_of_department) == false) {
						ra.addAttribute("msg", "Name of Department/Organization " + validation.isOnlyAlphabetMSG);
						return new ModelAndView("redirect:Teacher_dtl_Url");
					}

					if (direct_validity_upto == null || direct_validity_upto.trim().equals("")
							|| direct_validity_upto.equals("DD/MM/YYYY")) {
						ra.addAttribute("msg", "Please Enter The Date Of Registration");
						return new ModelAndView("redirect:Teacher_dtl_Url");
					}

				}
				
				int p_id=Integer.parseInt(request.getParameter("perentId"));
				if(p_id==0) {
					String depart = "HM-";
					String reg_no = geteacher_code();
					String testold = "";
					if (reg_no != null) {
						int newn = Integer.parseInt(reg_no);
						newn++;
						String abc = String.format("%8s", newn).replace(' ', '0');
						testold = depart + abc;
						// System.err.println("testold------->>>>" + testold);
						rd.setTeacher_code(testold);

					} else {
						testold = "HM-00000001";
						rd.setTeacher_code(testold);
					}

	/////////////////////////ayush_id generate		
					int increemt = 0;
					String abr = "";
//					String maxAID = "";

//					maxAID = getAyushID(userid);
//					if (maxAID == null) {
//
//					} else {
//						increemt = Integer.parseInt(maxAID.substring(11));
//						abr = maxAID.substring(0, 11);
//						// increemt += 1;
//						maxAID = abr + String.format("%4s", increemt).replace(' ', '0');
//					}

					
					
					String maxAID="";
//					if(i==1) {
						maxAID = getAyushID(userid);
						increemt=Integer.parseInt(maxAID.substring(11));
						abr=maxAID.substring(0,11);
						
//					}else {
//						increemt+=1;
//						maxAID=abr+String.format("%5s", increemt).replace(' ', '0');
//					}
//						skl.setAyush_id(maxAID);
						
					rd.setAyush_id(maxAID);
					rd.setUser_id(Integer.parseInt(userid));
					rd.setFirst_name(first_name);
					rd.setCand_prefix(cand_prefix);
					rd.setMiddle_name(middle_name);
					rd.setLast_name(last_name);
					rd.setGender(gender);
					rd.setDate_of_birth(datePickerFormat.parse(date_of_birth));
					// rd.setDate_of_birth(formatter1.parse(dob1));
					rd.setFather_name(father_name);
					rd.setMother_name(mother_name);
					rd.setSpouse_name(spouse_name);
					rd.setMobile_no(mobile_no);
					rd.setEmail(email);
					rd.setAadhar_no(aadhar_no);
					rd.setPan_no(pan_no);
					rd.setStatus(Integer.parseInt(hiddenUpdate));
					rd.setPresent_add_line1(present_add_line1);
					rd.setPresent_add_line2(present_add_line2);
					rd.setPresent_village(present_village);
					rd.setPresent_state(Integer.parseInt(present_state));
					rd.setPresent_district(Integer.parseInt(present_district));
					rd.setPresent_pincode(Integer.parseInt(present_pincode));
					rd.setPresent_phn_no(present_phn_no);

					rd.setPer_add_line1(per_add_line1);
					rd.setPer_add_line2(per_add_line2);
					rd.setPer_village(per_village);
					rd.setPer_state(Integer.parseInt(per_state));
					rd.setPer_district(Integer.parseInt(per_district));
					rd.setPer_pincode(Integer.parseInt(per_pincode));
					rd.setPer_phn_no(per_phn_no);
					rd.setPrincipal_status(0);
					rd.setCurrent_designation(current_designation);
					
					
					
					
					
					
					

	////////////////////mirangi_21_07_22
					Date other_date_of_reg_date=null;
					Date other_validity_upto_date=null;
					Date date_of_reg_date=null;
					Date cs_date_of_reg_date=null;
					Date state_validity_upto_date=null;
					Date direct_date_of_reg_date=null;
					Date direct_validity_upto_date=null;
					if(!other_date_of_reg.equals("DD/MM/YYYY") && !other_date_of_reg.equals("")) {
						other_date_of_reg_date=datePickerFormat.parse(other_date_of_reg);
					}
					if(!other_validity_upto.equals("DD/MM/YYYY") && !other_validity_upto.equals(""))  {
						other_validity_upto_date=datePickerFormat.parse(other_validity_upto);
					}
					if(!date_of_reg.equals("DD/MM/YYYY") && !date_of_reg.equals("")) {
						date_of_reg_date=datePickerFormat.parse(date_of_reg);
					}
					if(!cs_date_of_reg.equals("DD/MM/YYYY") && !cs_date_of_reg.equals("")) {
						cs_date_of_reg_date=datePickerFormat.parse(cs_date_of_reg);
					}
					if(!state_validity_upto.equals("DD/MM/YYYY") && !state_validity_upto.equals("")) {
						state_validity_upto_date=datePickerFormat.parse(state_validity_upto);
					}
					if (registration_type == null || registration_type.trim().equals("1")) {
						rd.setState_reg_no(state_reg_no);
						rd.setState_board_name(state_board_name);
						rd.setDate_of_reg(date_of_reg_date);
	// rd.setDate_of_reg(formatter1.parse(dt_reg));
						rd.setCentral_reg_no(central_reg_no);
						rd.setAdjunct_registration_no(adjunct_registration_no);
						rd.setAdjunct_state_no(adjunct_state_no);
						rd.setState_validity_upto(state_validity_upto_date);
						rd.setCs_date_of_reg(cs_date_of_reg_date);
						rd.setOther_date_of_reg(other_date_of_reg_date);
						rd.setOther_validity_upto(other_validity_upto_date);

					}

					if (registration_type == null || registration_type.trim().equals("2")) {
						if(!direct_date_of_reg.equals("DD/MM/YYYY") && !direct_date_of_reg.equals("")) {
							direct_date_of_reg_date=datePickerFormat.parse(direct_date_of_reg);
						}
						if(!direct_validity_upto.equals("DD/MM/YYYY") && !direct_validity_upto.equals("")) {
							direct_validity_upto_date=datePickerFormat.parse(direct_validity_upto);
						}
						rd.setDirect_reg_no((direct_reg_no));
						rd.setDirect_date_of_reg(direct_date_of_reg_date);
						rd.setName_of_department(name_of_department);
						rd.setDirect_validity_upto(direct_validity_upto_date);
					}
					rd.setRegistration_type(registration_type);
					rd.setAcademic_quali(academic_quali);
					rd.setSubject(subject);

	///////////////	//----rahul_11_07_22
//					List<ArrayList<String>> list2 = tdao.getUniversity_id(userid, "University_NCH");
//					List<ArrayList<String>> list = tdao.getPrincipal_id(userid, "Principal_NCH");

					//rd.setUniversity_userid(Integer.parseInt(list2.get(0).get(0)));
					//rd.setPrincipal_userid(Integer.parseInt(list.get(0).get(0)));

//		rd.setSub_quali_degree(Integer.parseInt(sub_quali_degree));
//		rd.setSub_deg_course(Integer.parseInt(sub_deg_course));

					rd.setCreated_by(username);
					rd.setCreated_date(date);

					p_id = (int) sessionHQL.save(rd);
					sessionHQL.flush();
					sessionHQL.clear();
					
					
					
				}else {
					EDU_LMS_TEACHER_DTL urd = (EDU_LMS_TEACHER_DTL) sessionHQL.get(EDU_LMS_TEACHER_DTL.class,
							Integer.parseInt(id_for_update));
//					if(urd.getAyush_id().equals("")) {
//					String depart = "HM-";
//					String reg_no = geteacher_code();
//					String testold = "";
//					if (reg_no != null) {
//						int newn = Integer.parseInt(reg_no);
//						newn++;
//						String abc = String.format("%8s", newn).replace(' ', '0');
//						testold = depart + abc;
//						// System.err.println("testold------->>>>" + testold);
//						rd.setTeacher_code(testold);
//
//					} else {
//						testold = "HM-00000001";
//						rd.setTeacher_code(testold);
//					}
//					}
					System.err.println("id for update-----------" + id_for_update + "---");

					urd.setTeacher_code(teachercode);
					urd.setAyush_id(ayush_id);
					urd.setUser_id(Integer.parseInt(userid));
					urd.setFirst_name(first_name);
					urd.setCand_prefix(cand_prefix);
					urd.setMiddle_name(middle_name);
					urd.setLast_name(last_name);
					urd.setGender(gender);
					urd.setDate_of_birth(datePickerFormat.parse(date_of_birth));
					// rd.setDate_of_birth(formatter1.parse(dob1));
					urd.setFather_name(father_name);
					urd.setMother_name(mother_name);
					urd.setSpouse_name(spouse_name);
					urd.setMobile_no(mobile_no);
					urd.setEmail(email);
					urd.setAadhar_no(aadhar_no);
					urd.setPan_no(pan_no);
					urd.setStatus(Integer.parseInt(hiddenUpdate));

					urd.setPresent_add_line1(present_add_line1);
					urd.setPresent_add_line2(present_add_line2);
					urd.setPresent_village(present_village);
					urd.setPresent_state(Integer.parseInt(present_state));
					urd.setPresent_district(Integer.parseInt(present_district));
					urd.setPresent_pincode(Integer.parseInt(present_pincode));
					urd.setPresent_phn_no(present_phn_no);

					urd.setPer_add_line1(per_add_line1);
					urd.setPer_add_line2(per_add_line2);
					urd.setPer_village(per_village);
					urd.setPer_state(Integer.parseInt(per_state));
					urd.setPer_district(Integer.parseInt(per_district));
					urd.setPer_pincode(Integer.parseInt(per_pincode));
					urd.setPer_phn_no(per_phn_no);
					urd.setPrincipal_status(0);
					urd.setCurrent_designation(current_designation);

	////////////////////mirangi_21_07_22
					Date other_date_of_reg_date=null;
					Date other_validity_upto_date=null;
					Date date_of_reg_date=null;
					Date cs_date_of_reg_date=null;
					Date state_validity_upto_date=null;
					Date direct_date_of_reg_date=null;
					Date direct_validity_upto_date=null;
					
					
				if(!other_date_of_reg.equals("DD/MM/YYYY") && !other_date_of_reg.equals("")) {
					other_date_of_reg_date=datePickerFormat.parse(other_date_of_reg);
				}
				if(!other_validity_upto.equals("DD/MM/YYYY") && !other_validity_upto.equals(""))  {
					other_validity_upto_date=datePickerFormat.parse(other_validity_upto);
				}
				if(!date_of_reg.equals("DD/MM/YYYY") && !date_of_reg.equals("")) {
					date_of_reg_date=datePickerFormat.parse(date_of_reg);
				}
				if(!cs_date_of_reg.equals("DD/MM/YYYY") && !cs_date_of_reg.equals("")) {
					cs_date_of_reg_date=datePickerFormat.parse(cs_date_of_reg);
				}
				if(!state_validity_upto.equals("DD/MM/YYYY") && !state_validity_upto.equals("")) {
					state_validity_upto_date=datePickerFormat.parse(state_validity_upto);
				}
//					
					
					
					
					
					if (registration_type == null || registration_type.trim().equals("1")) {
						
						urd.setState_reg_no(state_reg_no);
						urd.setState_board_name(state_board_name);
						urd.setDate_of_reg(date_of_reg_date);
	//rd.setDate_of_reg(formatter1.parse(dt_reg));
						urd.setCentral_reg_no(central_reg_no);
						urd.setAdjunct_registration_no(adjunct_registration_no);
						urd.setAdjunct_state_no(adjunct_state_no);
						urd.setState_validity_upto(state_validity_upto_date);
						urd.setCs_date_of_reg(cs_date_of_reg_date);
						urd.setOther_date_of_reg(other_date_of_reg_date);
						urd.setOther_validity_upto(other_validity_upto_date);
					}

					if (registration_type == null || registration_type.trim().equals("2")) {
						if(!direct_date_of_reg.equals("DD/MM/YYYY") && !direct_date_of_reg.equals("")) {
							direct_date_of_reg_date=datePickerFormat.parse(direct_date_of_reg);
						}
						if(!direct_validity_upto.equals("DD/MM/YYYY") && !direct_validity_upto.equals("")) {
							direct_validity_upto_date=datePickerFormat.parse(direct_validity_upto);
						}
						urd.setDirect_reg_no((direct_reg_no));
						urd.setDirect_date_of_reg(direct_date_of_reg_date);
						urd.setName_of_department(name_of_department);
						urd.setDirect_validity_upto(direct_validity_upto_date);
					}
					urd.setRegistration_type(registration_type);
					urd.setAcademic_quali(academic_quali);
					urd.setSubject(subject);

//					urd.setSub_quali_degree(Integer.parseInt(sub_quali_degree));
//					urd.setSub_deg_course(Integer.parseInt(sub_deg_course));

					urd.setModified_by(username);
					urd.setModified_date(date);

					sessionHQL.update(urd);
					sessionHQL.flush();
					sessionHQL.clear();
					
					
				}
				
			//parent save and update done
				EDU_LMS_TEACHER_QUALIFICATION_CHILD qc = new EDU_LMS_TEACHER_QUALIFICATION_CHILD();
				int count_hidden_att_name_med = Integer.parseInt(request.getParameter("count_hidden_att_name_med"));
				for (int j = 1; j <= count_hidden_att_name_med; j++) {
					
					int qulification_id=Integer.parseInt(request.getParameter("qualtification_id"+j));
					int at_pid=qulification_id;
					if(qulification_id==0) {
						
						String typeOfDegree = request.getParameter("typeOfDegree" + j);
						String course = request.getParameter("course" + j);
						String subjectQ = request.getParameter("subject" + j);
						String monthYearOfDegree = request.getParameter("monthYearOfDegree" + j);
//					String rollno = request.getParameter("rollno" + j);
						String NameOfUniversity = request.getParameter("NameOfUniversity" + j);
						String NameOfAffUni = request.getParameter("NameOfAffUni" + j);
						String universityother = request.getParameter("universityother" + j);
//					String percentage = request.getParameter("percentage" + j); 

						if (typeOfDegree == null || typeOfDegree.equals("0")) {
							ra.addAttribute("msg", "Please Select Name of Exam/Degree In Row " + j);
							return new ModelAndView("redirect:Teacher_dtl_Url");
						}
						if (course == null || course.equals("0")) {
							ra.addAttribute("msg", "Please Select Course In Row " + j);
							return new ModelAndView("redirect:Teacher_dtl_Url");
						}
//						if (subjectQ == null || subjectQ.equals("0")) {
//							ra.addAttribute("msg", "Please Select Subject In Row " + j);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
						if (NameOfUniversity == null || NameOfUniversity.trim().equals("")) {
							ra.addAttribute("msg", "Please Enter Institute Name In Row " + j);
							return new ModelAndView("redirect:Teacher_dtl_Url");
						}
						if (validation.maxlengthcheck100(NameOfUniversity) == false) {
							ra.addAttribute("msg", "Institute Name " + validation.MaxlengthcheckMSG100);
							return new ModelAndView("redirect:Teacher_dtl_Url");
						}
//						if (validation.isOnlyAlphabetDASH(NameOfUniversity) == false) {
//							ra.addAttribute("msg", "Institute Name " + validation.isOnlyAlphabetMSGDASH);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
						if (NameOfAffUni == null || NameOfAffUni.trim().equals("")) {
							ra.addAttribute("msg", "Please Enter Name of Affiliated University In Row " + j);
							return new ModelAndView("redirect:Teacher_dtl_Url");
						}
						if (validation.maxlengthcheck100(NameOfAffUni) == false) {
							ra.addAttribute("msg", "Name of Affiliated University " + validation.MaxlengthcheckMSG100);
							return new ModelAndView("redirect:Teacher_dtl_Url");
						}
						if (NameOfAffUni.equals("OTHER")) {
							if(universityother.equals("")) {
								ra.addAttribute("msg", "Please Enter Other University Name In Row " + j);
								return new ModelAndView("redirect:Teacher_dtl_Url");
							}
							
						}
//						if (validation.isOnlyAlphabetDASH(NameOfAffUni) == false) {
//							ra.addAttribute("msg", "Name of Affiliated University" + validation.isOnlyAlphabetMSGDASH);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
						if (monthYearOfDegree == null || monthYearOfDegree.equals("")
								|| monthYearOfDegree.equals("dd/mm/yyyy")) {
							ra.addAttribute("msg", "Please Enter Month Year Of Degree" + j);
							return new ModelAndView("redirect:Teacher_dtl_Url");
						}
						
						Query q0 = sessionHQL.createQuery("select count(id) from EDU_LMS_TEACHER_QUALIFICATION_CHILD where upper(type_of_degree)=:type_of_degree and p_id=:p_id");
						q0.setParameter("type_of_degree", typeOfDegree.toUpperCase());
						q0.setParameter("p_id", p_id);
						Long c = (Long) q0.uniqueResult();
						
						if (c>0) {
							ra.addAttribute("msg", "Qualification Data already Exist.");
							return new ModelAndView("redirect:Teacher_dtl_Url");
						}
						
						
						

						qc.setP_id(p_id);
						qc.setType_of_degree(typeOfDegree);
						qc.setCourse(course);
						qc.setSubject(Integer.parseInt(subjectQ));
						qc.setMonth_and_year(monthYearOfDegree);
//					qc.setRoll_no(Integer.parseInt(rollno));
						qc.setName_of_institute(NameOfUniversity);
						qc.setAffiliated_university(NameOfAffUni);
						if(NameOfAffUni.equals("OTHER")) {
							qc.setOtheruniversity(universityother);
						}else {
							qc.setOtheruniversity("");
						}
//					qc.setPercentage(Integer.parseInt(percentage));
						qc.setCreated_by(username);
						qc.setCreated_date(date);

						 at_pid = (int) sessionHQL.save(qc);
						sessionHQL.flush();
						sessionHQL.clear();
					}else {
						String typeOfDegree = request.getParameter("typeOfDegree" + j);
						String course = request.getParameter("course" + j);
						String subjectQ = request.getParameter("subject" + j);
						String monthYearOfDegree = request.getParameter("monthYearOfDegree" + j);
//						String rollno = request.getParameter("rollno" + j);
						String NameOfUniversity = request.getParameter("NameOfUniversity" + j);
						String NameOfAffUni = request.getParameter("NameOfAffUni" + j);
//						String percentage = request.getParameter("percentage" + j); 
						String universityother = request.getParameter("universityother" + j);
						EDU_LMS_TEACHER_QUALIFICATION_CHILD HSCH = (EDU_LMS_TEACHER_QUALIFICATION_CHILD) sessionHQL
								.get(EDU_LMS_TEACHER_QUALIFICATION_CHILD.class, qulification_id);

						if (typeOfDegree == null || typeOfDegree.equals("0")) {
							ra.addAttribute("msg", "Please Select Name of Exam/Degree In Row " + j);
							return new ModelAndView("redirect:Teacher_dtl_Url");
						}
						if (course == null || course.equals("0")) {
							ra.addAttribute("msg", "Please Select Course In Row " + j);
							return new ModelAndView("redirect:Teacher_dtl_Url");
						}
//						if (subjectQ == null || subjectQ.equals("0")) {
//							ra.addAttribute("msg", "Please Select Subject In Row " + j);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
						if ( NameOfUniversity.trim().equals("0")) {
							ra.addAttribute("msg", "Please Enter Institute Name In Row " + j);
							return new ModelAndView("redirect:Teacher_dtl_Url");
						}
//						if (validation.maxlengthcheck50(NameOfUniversity) == false) {
//							ra.addAttribute("msg", "Institute Name " + validation.MaxlengthcheckMSG50);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (validation.isOnlyAlphabetDASH(NameOfUniversity) == false) {
//							ra.addAttribute("msg", "Institute Name " + validation.isOnlyAlphabetMSGDASH);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
						if ( NameOfAffUni.trim().equals("0")) {
							ra.addAttribute("msg", "Please Enter Name of Affiliated University In Row " + j);
							return new ModelAndView("redirect:Teacher_dtl_Url");
						}
//						if (validation.maxlengthcheck50(NameOfAffUni) == false) {
//							ra.addAttribute("msg", "Name of Affiliated University " + validation.MaxlengthcheckMSG50);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (validation.isOnlyAlphabetDASH(NameOfAffUni) == false) {
//							ra.addAttribute("msg", "Name of Affiliated University" + validation.isOnlyAlphabetMSGDASH);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
						if (monthYearOfDegree == null || monthYearOfDegree.equals("")
								|| monthYearOfDegree.equals("dd/mm/yyyy")) {
							ra.addAttribute("msg", "Please Enter Month Year Of Degree" + j);
							return new ModelAndView("redirect:Teacher_dtl_Url");
						}
						if (NameOfAffUni.equals("other")) {
							if(universityother.equals("")) {
								ra.addAttribute("msg", "Please Enter Other University Name In Row " + j);
								return new ModelAndView("redirect:Teacher_dtl_Url");
							}
							
						}

						HSCH.setP_id(p_id);

						HSCH.setType_of_degree(typeOfDegree);
						HSCH.setCourse(course);
						HSCH.setSubject(Integer.parseInt(subjectQ));
						HSCH.setMonth_and_year(monthYearOfDegree);

						HSCH.setName_of_institute(NameOfUniversity);
						HSCH.setAffiliated_university(NameOfAffUni);
						System.out.println("NameOfAffUni=========  "+NameOfAffUni);
						if(NameOfAffUni.equals("OTHER")) {
							HSCH.setOtheruniversity(universityother);
						}else {
							HSCH.setOtheruniversity("");
						}
						HSCH.setCreated_by(username);
						HSCH.setCreated_date(date);

						sessionHQL.update(HSCH);
						sessionHQL.flush();
						sessionHQL.clear();
						
					}
					
					int count_hid_subchild = Integer.parseInt(request.getParameter("count_hidden_att_doc"+j));
					
					for (int k = 1; k <= count_hid_subchild; k++) {
						String name_of_attachment = request
								.getParameter("name_of_attachment" + j + "_" + k);
						String certificate_type = request.getParameter("name_of_degree" + j + "_" + k);
						MultipartFile attachmentchild = mul.getFile("attachmentDocument" + j + "_" + k);
						String attachmentDoc_hid = request.getParameter("attachmentDoc_hid" + j + "_" + k);
						
						if (name_of_attachment == null || name_of_attachment.equals("0")) {
							ra.addAttribute("msg",
									"Please Select Name of Attachment In Row " + j + " " + k);
							break;
							//return new ModelAndView("redirect:Teacher_dtl_Url");
						}
						if (name_of_attachment == null || name_of_attachment.equals("3")) {

							if (certificate_type == null || certificate_type.equals("0")) {

								ra.addAttribute("msg",
										"Please Select Certificate Type In Row " + j + " " + k);
								break;
								//return new ModelAndView("redirect:Teacher_dtl_Url");
							}
						}
//																								medicalqualificationAtt_id2_1
						int medicalqualificationAtt_id = Integer.parseInt(request.getParameter("medicalqualificationAtt_id" + j + "_" + k));

						if(medicalqualificationAtt_id==0) {
							
							EDU_LMS_TEACHER_QUALIFICATION_ATTACHMENT_SUBCHILD qas = new EDU_LMS_TEACHER_QUALIFICATION_ATTACHMENT_SUBCHILD();
							
							String pic = "";
							if (!attachmentchild.isEmpty()) {
								
								
								 if (attachmentchild.getOriginalFilename().split("[.]").length > 2) {
									 ra.addAttribute("msg", "Invalid file extension for Document");
										return new ModelAndView("redirect:Teacher_dtl_Url");
								}
								
								
								pic = common.fileupload(attachmentchild.getBytes(),
										attachmentchild.getOriginalFilename(), "UploadHardCopy1");
								System.err.println("");
								if (pic != "") {

									qas.setAttachment(pic);
//							ec.setAttachment_path(namep);
								} else {
									qas.setAttachment(attachmentDoc_hid);

								}
							}

							if (name_of_attachment != null && !name_of_attachment.equals("")) {
								qas.setName_of_attachment(name_of_attachment);
							}

							qas.setParent_id(at_pid);

							qas.setCertificate_type(Integer.parseInt(certificate_type));

							sessionHQL.save(qas);
							sessionHQL.flush();
							sessionHQL.clear();

							
							
						}else {
							
							EDU_LMS_TEACHER_QUALIFICATION_ATTACHMENT_SUBCHILD qas1 = (EDU_LMS_TEACHER_QUALIFICATION_ATTACHMENT_SUBCHILD) sessionHQL
									.get(EDU_LMS_TEACHER_QUALIFICATION_ATTACHMENT_SUBCHILD.class, medicalqualificationAtt_id);
							
							
//							String name_of_attachment = request
//									.getParameter("name_of_attachment" + j + "_" + k);
//							String certificate_type = request.getParameter("name_of_degree" + j + "_" + k);
//							MultipartFile attachmentchild = mul.getFile("attachmentDocument" + j + "_" + k);
//							String attachmentDoc_hid = request.getParameter("attachmentDoc_hid" + j + "_" + k);
							
							if (name_of_attachment == null || name_of_attachment.equals("0")) {
								ra.addAttribute("msg",
										"Please Select Name of Attachment In Row " + j + " " + k);
								return new ModelAndView("redirect:Teacher_dtl_Url");
							}
							if (name_of_attachment == null || name_of_attachment.equals("3")) {

								if (certificate_type == null || certificate_type.equals("0")) {

									ra.addAttribute("msg",
											"Please Select Certificate Type In Row " + j + " " + k);
									return new ModelAndView("redirect:Teacher_dtl_Url");
								}
							}

							String pic = "";
							if (!attachmentchild.isEmpty()) {
								pic = common.fileupload(attachmentchild.getBytes(),
										attachmentchild.getOriginalFilename(), "UploadHardCopy1");
								System.err.println("");
								if (pic != "") {

									qas1.setAttachment(pic);
//							ec.setAttachment_path(namep);
								} else {
									qas1.setAttachment(attachmentDoc_hid);

								}
							}

							if (name_of_attachment != null && !name_of_attachment.equals("")) {
								qas1.setName_of_attachment(name_of_attachment);
							}

							qas1.setParent_id(at_pid);

							qas1.setCertificate_type(Integer.parseInt(certificate_type));

							sessionHQL.update(qas1);
							sessionHQL.flush();
							sessionHQL.clear();
							
						}
					}
					
					
				}
				
				//other qualification
				EDU_LMS_TEACHER_OTHER_QUALIFICATION_CHILD oqc = new EDU_LMS_TEACHER_OTHER_QUALIFICATION_CHILD();
				
				int count_hidden_att_oth_quali = Integer.parseInt(request.getParameter("count_hidden_att_oth_quali"));
				System.out.println("count_hidden_att_oth_quali "+count_hidden_att_oth_quali);
				for (int j = 1; j <= count_hidden_att_oth_quali; j++) {
					
				int othquali_id=Integer.parseInt(request.getParameter("othquali_id"+j));
				
				int at_pid=othquali_id;
				
				String OthnameofExDeg = request.getParameter("OthnameofExDeg" + j);
				String othSubject = request.getParameter("othSubject" + j);
				String othUniInst = request.getParameter("othUniInst" + j);
				String othAffuni = request.getParameter("othAffuni" + j);
				String othYrofpass = request.getParameter("othYrofpass" + j);

				
				
				if(!OthnameofExDeg.equals("") && !othSubject.equals("") && !othUniInst.equals("") && !othAffuni.equals("") && !othYrofpass.equals("")) {
				if(othquali_id==0) {
					
					
					
					
					oqc.setP_id(p_id);
					oqc.setName_of_exam_degree(OthnameofExDeg);
					oqc.setSubject(othSubject);
					oqc.setName_of_uni_inst(othUniInst);
					oqc.setName_of_aff_uni(othAffuni);
					oqc.setMonth_and_year(othYrofpass);
					oqc.setCreated_by(username);
					oqc.setCreated_date(date);

					 at_pid = (int) sessionHQL.save(oqc);
					sessionHQL.flush();
					sessionHQL.clear();
					
					
				}else {
			
//					String OthnameofExDeg = request.getParameter("OthnameofExDeg" + j);
//					String othSubject = request.getParameter("othSubject" + j);
//					String othUniInst = request.getParameter("othUniInst" + j);
//					String othAffuni = request.getParameter("othAffuni" + j);
//					String othYrofpass = request.getParameter("othYrofpass" + j);
					
					EDU_LMS_TEACHER_OTHER_QUALIFICATION_CHILD quaother = (EDU_LMS_TEACHER_OTHER_QUALIFICATION_CHILD) sessionHQL
							.get(EDU_LMS_TEACHER_OTHER_QUALIFICATION_CHILD.class, othquali_id);
					
					
					quaother.setP_id(p_id);
					quaother.setName_of_exam_degree(OthnameofExDeg);
					quaother.setSubject(othSubject);
					quaother.setName_of_uni_inst(othUniInst);
					quaother.setName_of_aff_uni(othAffuni);
					quaother.setMonth_and_year(othYrofpass);
					quaother.setCreated_by(username);
					quaother.setCreated_date(date);

					
					sessionHQL.update(quaother);
					sessionHQL.flush();
					sessionHQL.clear();
					
				
				}
				}
				
			if(request.getParameter("count_hidden_att_OQ"+j)!=null) {
				
			
				int count_hid_subchild = Integer.parseInt(request.getParameter("count_hidden_att_OQ"+j));
				
				for (int k = 1; k <= count_hid_subchild; k++) {
					String name_of_attachment = request.getParameter("name_of_attachmentOQ" + j + "_" + k);

					MultipartFile attachmentchild = mul.getFile("attachmentDocumentOQ" + j + "_" + k);
					String attachmentDoc_hid = request.getParameter("attachmentDoc_hidOQ" + j + "_" + k);
					
				
					int otherqualificationAtt_id = Integer.parseInt(request.getParameter("otherqualificationAtt_id" + j + "_" + k));

					if(otherqualificationAtt_id==0) {
						
						EDU_LMS_TEACHER_OTHER_QUALIFICATION_SUB_CHILD oqas = new EDU_LMS_TEACHER_OTHER_QUALIFICATION_SUB_CHILD();
						
						String pic = "";
						if (!attachmentchild.isEmpty()) {
							
							
							pic = common.fileupload(attachmentchild.getBytes(),
									attachmentchild.getOriginalFilename(), "UploadHardCopy1");
							System.err.println("");
							if (pic != "") {

								oqas.setAttachment(pic);
//						ec.setAttachment_path(namep);
							} else {
								oqas.setAttachment(attachmentDoc_hid);

							}
						}
						if (name_of_attachment != null && !name_of_attachment.equals("")) {
							oqas.setName_of_attachment(name_of_attachment);
						}
						
						oqas.setParent_id(at_pid);

						sessionHQL.save(oqas);
						sessionHQL.flush();
						sessionHQL.clear();

					
					}else {
						
						EDU_LMS_TEACHER_OTHER_QUALIFICATION_SUB_CHILD qas1 = (EDU_LMS_TEACHER_OTHER_QUALIFICATION_SUB_CHILD) sessionHQL
								.get(EDU_LMS_TEACHER_OTHER_QUALIFICATION_SUB_CHILD.class, otherqualificationAtt_id);
						
						


						String pic = "";
						if (!attachmentchild.isEmpty()) {
							pic = common.fileupload(attachmentchild.getBytes(),
									attachmentchild.getOriginalFilename(), "UploadHardCopy1");
							System.err.println("");
							if (pic != "") {

								qas1.setAttachment(pic);
//						ec.setAttachment_path(namep);
							} else {
								qas1.setAttachment(attachmentDoc_hid);

							}
						}
						if (name_of_attachment != null && !name_of_attachment.equals("")) {
							qas1.setName_of_attachment(name_of_attachment);
						}

						qas1.setParent_id(at_pid);

						sessionHQL.update(qas1);
						sessionHQL.flush();
						sessionHQL.clear();
						
					}
				}
				}
	
			}
	
				//profession/academic experience
				
				EDU_LMS_TEACHER_EXPERIENCE_CHILD od = new EDU_LMS_TEACHER_EXPERIENCE_CHILD();
				
				int count_hidden_att = Integer.parseInt(request.getParameter("count_hidden_att"));
				
				for (int i = 1; i <= count_hidden_att; i++) {
					
					int profession_id=Integer.parseInt(request.getParameter("profession_id"+i));
					
					
					if(profession_id==0) {
						
						String institute_name = request.getParameter("institute_name" + i);
						String depart_name = request.getParameter("depart_name" + i);
						String desig = request.getParameter("desig" + i);
						String employment_type = request.getParameter("employment_type" + i);
						String from_date = request.getParameter("from_date" + i);
						String to_date = request.getParameter("to_date" + i);
						String honorarium = request.getParameter("honorarium" + i);

						String du = "upload_file" + i;
						String doc = gd(request, mul, session, du);
						
						
						if (institute_name == null || institute_name.trim().equals("")) {
							ra.addAttribute("msg", "Please Enter Institute Name In Row " + i);
							return new ModelAndView("redirect:Teacher_dtl_Url");
						}
						if (validation.maxlengthcheck50(institute_name) == false) {
							ra.addAttribute("msg", "Institute Name " + validation.MaxlengthcheckMSG50);
							return new ModelAndView("redirect:Teacher_dtl_Url");
						}
//						if (validation.isOnlyAlphabetDASH(institute_name) == false) {
//							ra.addAttribute("msg", "Institute Name " + validation.isOnlyAlphabetMSGDASH);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}

						if (depart_name == null || depart_name.equals("0")) {
							ra.addAttribute("msg", "Please Select Department Name In Row " + i);
							return new ModelAndView("redirect:Teacher_dtl_Url");
						}
						if (desig == null || desig.equals("0")) {
							ra.addAttribute("msg", "Please Select Designation In Row " + i);
							return new ModelAndView("redirect:Teacher_dtl_Url");
						}
						if (employment_type == null || employment_type.equals("0")) {
							ra.addAttribute("msg", "Please Select Employment Type In Row " + i);
							return new ModelAndView("redirect:Teacher_dtl_Url");
						}

						if (from_date == null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
							ra.addAttribute("msg", "Please Enter The From Date In Row " + i);
							return new ModelAndView("redirect:Teacher_dtl_Url");
						}
						if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
							ra.addAttribute("msg", "Please Enter The To Date In Row " + i);
							return new ModelAndView("redirect:Teacher_dtl_Url");
						}
						if (datePickerFormat.parse(to_date).compareTo(datePickerFormat.parse(from_date)) < 0) {
							ra.addAttribute("msg", "To Date Should Be Greater Than Or Equal To From Date In Row " + i);
							return new ModelAndView("redirect:Teacher_dtl_Url");
						}
//						if (doc.isEmpty()) {
//							ra.addAttribute("msg", "Please Select File In Row " + i);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
	//
//						}
						
						od.setInstitute_name(institute_name);
						od.setDepart_name(depart_name);
						od.setDesig(desig);
						od.setEmployment_type(employment_type);
						od.setFrom_date(datePickerFormat.parse(from_date));
						od.setTo_date(datePickerFormat.parse(to_date));
						od.setUpload_file(doc);
						od.setCreated_by(username);
						od.setCreated_date(date);
						od.setHonorarium(honorarium);
						od.setP_id(p_id);

						sessionHQL.save(od);// 1
						sessionHQL.flush();
						sessionHQL.clear();
						
						
					}else {
						
						EDU_LMS_TEACHER_EXPERIENCE_CHILD od2 = (EDU_LMS_TEACHER_EXPERIENCE_CHILD) sessionHQL
								.get(EDU_LMS_TEACHER_EXPERIENCE_CHILD.class, profession_id);
						
						//EDU_LMS_TEACHER_EXPERIENCE_CHILD od2 = new EDU_LMS_TEACHER_EXPERIENCE_CHILD();
						
						String institute_name = request.getParameter("institute_name" + i);
						String depart_name = request.getParameter("depart_name" + i);
						String desig = request.getParameter("desig" + i);
						String employment_type = request.getParameter("employment_type" + i);
						String from_date = request.getParameter("from_date" + i);
						String to_date = request.getParameter("to_date" + i);
						String honorarium = request.getParameter("honorarium" + i);

						String upload_file_hidT = request.getParameter("upload_file_hid" + i);

						String du = "upload_file" + i;
						String doc = gd(request, mul, session, du);
						
						
						if (institute_name == null || institute_name.trim().equals("")) {
							ra.addAttribute("msg", "Please Enter Institute Name In Row " + i);
							return new ModelAndView("redirect:Teacher_dtl_Url");
						}
						if (validation.maxlengthcheck50(institute_name) == false) {
							ra.addAttribute("msg", "Institute Name " + validation.MaxlengthcheckMSG50);
							return new ModelAndView("redirect:Teacher_dtl_Url");
						}
//							if (validation.isOnlyAlphabetDASH(institute_name) == false) {
//								ra.addAttribute("msg", "Institute Name " + validation.isOnlyAlphabetMSGDASH);
//								return new ModelAndView("redirect:Teacher_dtl_Url");
//							}

						if (depart_name == null || depart_name.equals("0")) {
							ra.addAttribute("msg", "Please Select Department Name In Row " + i);
							return new ModelAndView("redirect:Teacher_dtl_Url");
						}
						if (desig == null || desig.equals("0")) {
							ra.addAttribute("msg", "Please Select Designation In Row " + i);
							return new ModelAndView("redirect:Teacher_dtl_Url");
						}

						if (employment_type == null || employment_type.equals("0")) {
							ra.addAttribute("msg", "Please Select Employment Type In Row " + i);
							return new ModelAndView("redirect:Teacher_dtl_Url");
						}

						if (from_date == null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
							ra.addAttribute("msg", "Please Enter The From Date In Row " + i);
							return new ModelAndView("redirect:Teacher_dtl_Url");
						}
						if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
							ra.addAttribute("msg", "Please Enter The To Date In Row " + i);
							return new ModelAndView("redirect:Teacher_dtl_Url");
						}
						if (datePickerFormat.parse(to_date).compareTo(datePickerFormat.parse(from_date)) < 0) {
							ra.addAttribute("msg", "To Date Should Be Greater Than Or Equal To From Date In Row " + i);
							return new ModelAndView("redirect:Teacher_dtl_Url");
						}
						if (honorarium == null || honorarium.equals("0")) {
							ra.addAttribute("msg", "Please Select Honorarium In Row " + i);
							return new ModelAndView("redirect:Teacher_dtl_Url");
						}
						
						od2.setInstitute_name(institute_name);
						od2.setDepart_name(depart_name);
						od2.setDesig(desig);
						od2.setEmployment_type(employment_type);
						od2.setFrom_date(datePickerFormat.parse(from_date));
						od2.setTo_date(datePickerFormat.parse(to_date));
						od2.setHonorarium(honorarium);
						// od2.setUpload_file(doc);

						String pic = "";
						if (doc != "") {

							od2.setUpload_file(doc);
						} else {
							od2.setUpload_file(upload_file_hidT);

						}

						od2.setCreated_by(username);
						od2.setCreated_date(date);
						od2.setP_id(Integer.parseInt(id_for_update));

						sessionHQL.update(od2);// 1
						sessionHQL.flush();
						sessionHQL.clear();
	
					}
					
				}
				
				
				//-------------------------------------------Start---------------------------------------------------------
				
				//academic experience
				
				
//				
//				EDU_LMS_TEACHER_ACADEMIC_EXPERIENCE_CHILD aec = new EDU_LMS_TEACHER_ACADEMIC_EXPERIENCE_CHILD();
//				
//				int count_hidden_att_pro = Integer.parseInt(request.getParameter("count_hidden_att_pro"));
//				
//				for (int i = 1; i <= count_hidden_att_pro; i++) {
//				
//				
//				
//					int academic_exp=Integer.parseInt(request.getParameter("academic_exp"+i));
//					
//					
//					
//					if(academic_exp==0) {
//						
//						
//						String organization_name = request.getParameter("organization_name" + i);
//						String from_date_pro = request.getParameter("from_date_pro" + i);
//						String to_date_pro = request.getParameter("to_date_pro" + i);
//						String designation_name = request.getParameter("designation_name" + i);
//						
//						
//						int type_of_exp = Integer.parseInt(request.getParameter("type_of_exp"+i));
//						String experienceother = request.getParameter("experienceother"+i);
//						
//						
//						if (organization_name == null || organization_name.trim().equals("")) {
//							ra.addAttribute("msg", "Please Enter Institute Name In Row " + i);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (validation.maxlengthcheck50(organization_name) == false) {
//							ra.addAttribute("msg", "Institute Name " + validation.MaxlengthcheckMSG50);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (from_date_pro == null || from_date_pro.trim().equals("") || from_date_pro.equals("DD/MM/YYYY")) {
//							ra.addAttribute("msg", "Please Enter The From Date In Row " + i);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (to_date_pro == null || to_date_pro.trim().equals("") || to_date_pro.equals("DD/MM/YYYY")) {
//							ra.addAttribute("msg", "Please Enter The To Date In Row " + i);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (datePickerFormat.parse(to_date_pro).compareTo(datePickerFormat.parse(from_date_pro)) < 0) {
//							ra.addAttribute("msg", "To Date Should Be Greater Than Or Equal To From Date In Row " + i);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (designation_name == null || designation_name.trim().equals("")) {
//							ra.addAttribute("msg", "Please Enter Designation Name In Row " + i);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (validation.maxlengthcheck50(designation_name) == false) {
//							ra.addAttribute("msg", "Designation Name " + validation.MaxlengthcheckMSG50);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						
//						if ( type_of_exp == 0) {
//							ra.addAttribute("msg", "Please Select Type Of Experience");
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						
//	
//						aec.setOrganization_name(organization_name);
//						aec.setFrom_date_pro(datePickerFormat.parse(from_date_pro));
//						aec.setTo_date_pro(datePickerFormat.parse(to_date_pro));
//						aec.setDesignation_name(designation_name);
//						aec.setType_of_experience(type_of_exp);
//						aec.setP_id(p_id);
//						aec.setCreated_by(username);
//						aec.setCreated_date(date);
//							
//							if(type_of_exp== -1) {
//								aec.setOthertype_of_exp(experienceother);
//							}else {
//								aec.setOthertype_of_exp("");
//							}
//
//							sessionHQL.save(aec);// 1
//							
//							sessionHQL.flush();
//							sessionHQL.clear();
//	
//										
//					}else {
//						
//						EDU_LMS_TEACHER_ACADEMIC_EXPERIENCE_CHILD odc = (EDU_LMS_TEACHER_ACADEMIC_EXPERIENCE_CHILD) sessionHQL
//								.get(EDU_LMS_TEACHER_ACADEMIC_EXPERIENCE_CHILD.class, academic_exp);
//						
//						//EDU_LMS_TEACHER_EXPERIENCE_CHILD od2 = new EDU_LMS_TEACHER_EXPERIENCE_CHILD();
//						String organization_name = request.getParameter("organization_name" + i);
//						String from_date_pro = request.getParameter("from_date_pro" + i);
//						String to_date_pro = request.getParameter("to_date_pro" + i);
//						String designation_name = request.getParameter("designation_name" + i);
//						int type_of_exp = Integer.parseInt(request.getParameter("type_of_exp"+i));
//						String experienceother = request.getParameter("experienceother"+i);
//						
//						if (organization_name == null || organization_name.trim().equals("")) {
//							ra.addAttribute("msg", "Please Enter Institute Name In Row " + i);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (validation.maxlengthcheck50(organization_name) == false) {
//							ra.addAttribute("msg", "Institute Name " + validation.MaxlengthcheckMSG50);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (from_date_pro == null || from_date_pro.trim().equals("") || from_date_pro.equals("DD/MM/YYYY")) {
//							ra.addAttribute("msg", "Please Enter The From Date In Row " + i);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (to_date_pro == null || to_date_pro.trim().equals("") || to_date_pro.equals("DD/MM/YYYY")) {
//							ra.addAttribute("msg", "Please Enter The To Date In Row " + i);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (datePickerFormat.parse(to_date_pro).compareTo(datePickerFormat.parse(from_date_pro)) < 0) {
//							ra.addAttribute("msg", "To Date Should Be Greater Than Or Equal To From Date In Row " + i);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (designation_name == null || designation_name.trim().equals("")) {
//							ra.addAttribute("msg", "Please Enter Designation Name In Row " + i);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (validation.maxlengthcheck50(designation_name) == false) {
//							ra.addAttribute("msg", "Designation Name " + validation.MaxlengthcheckMSG50);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						
//						if ( type_of_exp == 0) {
//							ra.addAttribute("msg", "Please Select Type Of Experience");
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						
//						
//						
//						odc.setOrganization_name(organization_name);
//						odc.setFrom_date_pro(datePickerFormat.parse(from_date_pro));
//						odc.setTo_date_pro(datePickerFormat.parse(to_date_pro));
//						odc.setDesignation_name(designation_name);
//						odc.setType_of_experience(type_of_exp);
//						odc.setP_id(Integer.parseInt(id_for_update));
//						odc.setModified_by(username);
//						odc.setModified_date(date);
//						if(type_of_exp== -1) {
//							odc.setOthertype_of_exp(experienceother);
//						}else {
//							odc.setOthertype_of_exp("");
//						}
//
//						sessionHQL.update(odc);// 1
//						
//						sessionHQL.flush();
//						sessionHQL.clear();
//					}
//				}	
//				

					
				
			//-----------------------------------------------end--------------------------------------------------------------------------------------	
				
				
				
				
			
		
				//Additional Qualification
		
				EDU_LMS_DEGREE_AND_SUPPORT_DOC_CHILD ds = new EDU_LMS_DEGREE_AND_SUPPORT_DOC_CHILD();
				
				int count_hidden_doc = Integer.parseInt(request.getParameter("count_hidden_doc"));
				
				for (int i = 1; i <= count_hidden_doc; i++) {
					String doc_name = request.getParameter("doc_name" + i);
					String doc_id = request.getParameter("doc_id" + i);
					String du = "upload_doc" + i;
					String doc = gd(request, mul, session, du);

					if (doc_name == null || doc_name.equals("0")) {
						ra.addAttribute("msg", "Please Select Document Name In Row " + i);
						break;
						//return new ModelAndView("redirect:Teacher_dtl_Url");
					}
					if (doc_id == null || doc_id.equals("0")) {
						ra.addAttribute("msg", "Please Select Document Type In Row " + i);
						break;
						//return new ModelAndView("redirect:Teacher_dtl_Url");
					}
					int add_quali=Integer.parseInt(request.getParameter("add_quali"+i));
					
					if(add_quali==0) {
				
//						if (doc.isEmpty()) {
//							ra.addAttribute("msg", "Please Select Document In Row " + i);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
	//
//						}

						ds.setDoc_name(doc_name);
						ds.setDoc_id(Integer.parseInt(doc_id));
						ds.setUpload_doc(doc);
						ds.setCreated_by(username);
						ds.setCreated_date(date);
						ds.setP_id(p_id);

						sessionHQL.save(ds);// 2
						sessionHQL.flush();
						sessionHQL.clear();
					
					}else {
						
						EDU_LMS_DEGREE_AND_SUPPORT_DOC_CHILD ds2 = (EDU_LMS_DEGREE_AND_SUPPORT_DOC_CHILD) sessionHQL
								.get(EDU_LMS_DEGREE_AND_SUPPORT_DOC_CHILD.class, add_quali);
						
						
						String upload_docT = request.getParameter("doc_upload_hid" + i);
		
//						if (doc.isEmpty()) {
//							ra.addAttribute("msg", "Please Select Document In Row " + i);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
	//
//						}

						ds2.setDoc_name(doc_name);
						ds2.setDoc_id(Integer.parseInt(doc_id));
						// ds2.setUpload_doc(doc);

						String pic = "";

						if (doc != "") {

							ds2.setUpload_doc(doc);
						} else {
							ds2.setUpload_doc(upload_docT);

						}

						ds2.setCreated_by(username);
						ds2.setCreated_date(date);
						ds2.setP_id(Integer.parseInt(id_for_update));

						sessionHQL.update(ds2);// 2
						sessionHQL.flush();
						sessionHQL.clear();
				
					}
		
				}
	
				tx.commit();
				ra.addAttribute("msg", "Data Submited Successfully.");
			//	}
//				else {
//					ra.addAttribute("msg", "Pan number is already exist");
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
			} catch (RuntimeException e) {
				e.printStackTrace();
				
				try {
					ra.addAttribute("msg", "roll back transaction");
					//return new ModelAndView("redirect:Teacher_dtl_Url");
					
				} catch (RuntimeException rbe) {
					ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
					return new ModelAndView("redirect:Teacher_dtl_Url");
				}
				throw e;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}
		
		return new ModelAndView("redirect:Teacher_dtl_Url");
		
		
	}
	
	
	
//	--------------------------------------------------------------------------------------------------------------------
	
	
	

///////////////////////save////old
//
//	@PostMapping(value = "/tea_dtlAction1")
//	public ModelAndView tea_dtlAction1(@Validated @ModelAttribute("tea_dtl_CMD") EDU_LMS_TEACHER_DTL rd,
//			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
//			RedirectAttributes ra, MultipartHttpServletRequest mul) throws IOException, ParseException {
//
//		int id = rd.getId() > 0 ? rd.getId() : 0;
//		Date date = new Date();
//		String username = principal.getName();
//		String userid = session.getAttribute("userId_for_jnlp").toString();
//
//		Session sessionHQL = this.sessionFactory.openSession();
//		Transaction tx = sessionHQL.beginTransaction();
//		// DateFormat formatter1 = new SimpleDateFormat("DD/MM/YYYY");
//		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
//		try {
//
//			String first_name = request.getParameter("first_name");
//			String cand_prefix = request.getParameter("cand_prefix");
//			String middle_name = request.getParameter("middle_name");
//			String last_name = request.getParameter("last_name");
//			String gender = request.getParameter("gender");
//
//			String date_of_birth = request.getParameter("date_of_birth");
//
//			String father_name = request.getParameter("father_name");
//			String mother_name = request.getParameter("mother_name");
//			String spouse_name = request.getParameter("spouse_name");
//			String mobile_no = request.getParameter("mobile_no");
//			String email = request.getParameter("email");
//
//			String aadhar_no1 = request.getParameter("aadhar_no1");
//			String aadhar_no2 = request.getParameter("aadhar_no2");
//			String aadhar_no3 = request.getParameter("aadhar_no3");
//			String aadhar_no = aadhar_no1 + aadhar_no2 + aadhar_no3;
//
//			String pan_no = request.getParameter("pan_no");
//			String present_add_line1 = request.getParameter("present_add_line1");
//			String present_add_line2 = request.getParameter("present_add_line2");
//			String present_village = request.getParameter("present_village");
//			String present_state = request.getParameter("present_state");
//			String present_district = request.getParameter("present_district");
//			String present_pincode = request.getParameter("present_pincode");
//			String present_phn_no = request.getParameter("present_phn_no");
//
//			String per_add_line1 = request.getParameter("per_add_line1");
//			String per_add_line2 = request.getParameter("per_add_line2");
//			String per_village = request.getParameter("per_village");
//			String per_state = request.getParameter("per_state");
//			String per_district = request.getParameter("per_district");
//			String per_pincode = request.getParameter("per_pincode");
//			String per_phn_no = request.getParameter("per_phn_no");
//
//			String state_reg_no = request.getParameter("state_reg_no");
//			String state_board_name = request.getParameter("state_board_name");
//			String date_of_reg = request.getParameter("date_of_reg");
//			String central_reg_no = request.getParameter("central_reg_no");
//
//			// Integer direct_reg_no =
//			// Integer.parseInt(request.getParameter("direct_reg_no"));
//			String registration_type = request.getParameter("registration_type");
//			String direct_reg_no = request.getParameter("direct_reg_no");
//			String direct_date_of_reg = request.getParameter("direct_date_of_reg");
//			String name_of_department = request.getParameter("name_of_department");
//
//			String academic_quali = request.getParameter("academic_quali");
//			String subject = request.getParameter("subject");
//
//			String sub_quali_degree = request.getParameter("sub_quali_degree");
//			String sub_deg_course = request.getParameter("sub_deg_course");
//			String adjunct_registration_no = request.getParameter("adjunct_registration_no");
//			String adjunct_state_no = request.getParameter("adjunct_state_no");
//			String state_validity_upto = request.getParameter("state_validity_upto");
//			String direct_validity_upto = request.getParameter("direct_validity_upto");
//
//			String data_fetch = request.getParameter("data_fetch");
//			String teachercode = request.getParameter("teachercode_hidden");
//			String ayush_id = request.getParameter("ayushid_hidden");
//			String id_for_update = request.getParameter("id_hidden");
//
//			if (cand_prefix == null || cand_prefix.trim().equals("0")) {
//				ra.addAttribute("msg", "Please Select Candidate Prifix");
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (first_name == null || first_name.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter First Name.");
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.maxlengthcheck50(first_name) == false) {
//				ra.addAttribute("msg", "First Name " + validation.MaxlengthcheckMSG50);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.isOnlyAlphabetDASH(first_name) == false) {
//				ra.addAttribute("msg", "First Name " + validation.isOnlyAlphabetMSGDASH);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//
//			if (validation.maxlengthcheck50(middle_name) == false) {
//				ra.addAttribute("msg", "Middle Name " + validation.MaxlengthcheckMSG50);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.isOnlyAlphabetDASH(middle_name) == false) {
//				ra.addAttribute("msg", "Middle Name " + validation.isOnlyAlphabetMSGDASH);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//
//			if (last_name == null || last_name.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter Last Name.");
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.maxlengthcheck50(last_name) == false) {
//				ra.addAttribute("msg", "Last Name " + validation.MaxlengthcheckMSG50);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.isOnlyAlphabetDASH(last_name) == false) {
//				ra.addAttribute("msg", "Last Name " + validation.isOnlyAlphabetMSGDASH);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//
//			if (gender == null || gender.trim().equals("0")) {
//				ra.addAttribute("msg", "Please Select Gender");
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//
//			if (date_of_birth == null || date_of_birth.trim().equals("") || date_of_birth.equals("DD/MM/YYYY")) {
//				ra.addAttribute("msg", "Please Enter The Date Of Birth");
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//
//			if (father_name == null || father_name.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter Father's Name.");
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.maxlengthcheck50(father_name) == false) {
//				ra.addAttribute("msg", "Father's Name " + validation.MaxlengthcheckMSG50);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.isOnlyAlphabetDASH(father_name) == false) {
//				ra.addAttribute("msg", "Father's Name " + validation.isOnlyAlphabetMSGDASH);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//
//			if (mother_name == null || mother_name.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter Mother's Name.");
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.maxlengthcheck50(mother_name) == false) {
//				ra.addAttribute("msg", "Mother's Name " + validation.MaxlengthcheckMSG50);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.isOnlyAlphabetDASH(mother_name) == false) {
//				ra.addAttribute("msg", "Mother's Name " + validation.isOnlyAlphabetMSGDASH);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//
//			if (validation.maxlengthcheck50(spouse_name) == false) {
//				ra.addAttribute("msg", "Spouse's Name " + validation.MaxlengthcheckMSG50);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.isOnlyAlphabetDASH(spouse_name) == false) {
//				ra.addAttribute("msg", "Spouse's Name " + validation.isOnlyAlphabetMSGDASH);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//
//			if (mobile_no == null || mobile_no.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter Mobile Number");
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.isOnlyNumer(mobile_no) == true) {
//				ra.addAttribute("msg", "Mobile Number " + validation.isOnlyNumerMSG);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.maxlengthcheck10(mobile_no) == false) {
//				ra.addAttribute("msg", "Mobile Number " + validation.MaxlengthcheckMSG10);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.isValidMobileNo(mobile_no) == false) {
//				ra.addAttribute("msg", "Mobile Number " + validation.isOnlyMobilenumberMSG);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//
//			if (email == null || email.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter Email Address");
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.maxlengthcheck70(email) == false) {
//				ra.addAttribute("msg", "Email Address " + validation.MaxlengthcheckMSG70);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//
//			if (aadhar_no1 == null || aadhar_no1.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter Aadhaar No.");
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.isOnlyNumer(aadhar_no1) == true) {
//				ra.addAttribute("msg", "Aadhaar No. " + validation.isOnlyNumerMSG);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.AadharNoLength(aadhar_no1) == false) {
//				ra.addAttribute("msg", validation.AadharNoMSG11);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.AadharNoMinLength(aadhar_no1) == false) {
//				ra.addAttribute("msg", validation.AadharNoMSGMinlen11);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//
//			if (aadhar_no2 == null || aadhar_no2.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter Aadhaar No.");
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.isOnlyNumer(aadhar_no2) == true) {
//				ra.addAttribute("msg", "Aadhaar No. " + validation.isOnlyNumerMSG);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.AadharNoLength(aadhar_no2) == false) {
//				ra.addAttribute("msg", validation.AadharNoMSG11);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.AadharNoMinLength(aadhar_no2) == false) {
//				ra.addAttribute("msg", validation.AadharNoMSGMinlen11);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//
//			if (aadhar_no3 == null || aadhar_no3.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter Aadhaar No.");
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.isOnlyNumer(aadhar_no3) == true) {
//				ra.addAttribute("msg", "Aadhaar No. " + validation.isOnlyNumerMSG);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.AadharNoLength(aadhar_no3) == false) {
//				ra.addAttribute("msg", validation.AadharNoMSG11);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.AadharNoMinLength(aadhar_no3) == false) {
//				ra.addAttribute("msg", validation.AadharNoMSGMinlen11);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//
//			if (pan_no == null || pan_no.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter PAN No.");
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.maxlength10(pan_no) == false) {
//				ra.addAttribute("msg", "PAN No. " + validation.MaxlengthMSG10);
//				return new ModelAndView("redirect:Search_teacher_detail_url");
//			}
//			if (validation.minlength10(pan_no) == false) {
//				ra.addAttribute("msg", "PAN No. " + validation.MinlengthMSG10);
//				return new ModelAndView("redirect:Search_teacher_detail_url");
//			}
//			if (validation.isOnlyAlphabetNumeric(pan_no) == false) {
//				ra.addAttribute("msg", "PAN No. " + validation.isOnlyAlphabetNumericMSG);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//
//			/////////// Address
//			if (per_add_line1 == null || per_add_line1.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter Present Address Address-Line-1");
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.maxlengthcheck100(per_add_line1) == false) {
//				ra.addAttribute("msg", "Present Address Address-Line-1 " + validation.MaxlengthcheckMSG100);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//
//			if (per_add_line2 == null || per_add_line2.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter Present Address  Address-Line-2");
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.maxlengthcheck100(per_add_line2) == false) {
//				ra.addAttribute("msg", "Present Address Address-Line-2 " + validation.MaxlengthcheckMSG100);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//
//			if (per_state == null || per_state.trim().equals("0")) {
//				ra.addAttribute("msg", "Please Select State");
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (per_district == null || per_district.trim().equals("0")) {
//				ra.addAttribute("msg", "Please Select District");
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//
//			if (per_village == null || per_village.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter Present Address City/Village");
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.maxlengthcheck100(per_village) == false) {
//				ra.addAttribute("msg", "Present Address City/Village " + validation.MaxlengthcheckMSG100);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.isOnlyAlphabetDASH(per_village) == false) {
//				ra.addAttribute("msg", "Present Address City/Village " + validation.isOnlyAlphabetMSGDASH);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//
//			if (per_pincode == null || per_pincode.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter Present Address Pin Code");
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.maxlengthcheckpincode(per_pincode) == false) {
//				ra.addAttribute("msg", "Present Address Pin Code " + validation.MaxlengthcheckMSGpincode);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.minlengthcheckpincode(per_pincode) == false) {
//				ra.addAttribute("msg", "Present Address Pin Code " + validation.MinlengthcheckMSGpincode);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.isOnlyNumer(per_pincode) == true) {
//				ra.addAttribute("msg", "Present Address Pin Code " + validation.isOnlyNumerMSG);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//
//			if (per_phn_no == null || per_phn_no.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter Present Address Phone No.");
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.isOnlyNumer(per_phn_no) == true) {
//				ra.addAttribute("msg", "Present Address Phone No. " + validation.isOnlyNumerMSG);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.maxlengthcheck10(per_phn_no) == false) {
//				ra.addAttribute("msg", "Present Address Phone No. " + validation.MaxlengthcheckMSG10);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//			if (validation.isValidMobileNo(per_phn_no) == false) {
//				ra.addAttribute("msg", "Present Address Phone No. " + validation.isOnlyMobilenumberMSG);
//				return new ModelAndView("redirect:Teacher_dtl_Url");
//			}
//
//			if (request.getParameter("check_address") == null) {
//
//				if (present_add_line1 == null || present_add_line1.trim().equals("")) {
//					ra.addAttribute("msg", "Please Enter Correspondence Address-Line-1");
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//				if (validation.maxlengthcheck100(present_add_line1) == false) {
//					ra.addAttribute("msg", "Correspondence Address-Line-1 " + validation.MaxlengthcheckMSG100);
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//
//				if (present_add_line2 == null || present_add_line2.trim().equals("")) {
//					ra.addAttribute("msg", "Please Enter Correspondence Address-Line-2");
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//				if (validation.maxlengthcheck100(present_add_line2) == false) {
//					ra.addAttribute("msg", "Correspondence Address-Line-2 " + validation.MaxlengthcheckMSG100);
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//
//				if (present_state == null || present_state.trim().equals("0")) {
//					ra.addAttribute("msg", "Please Select State");
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//				if (present_district == null || present_district.trim().equals("0")) {
//					ra.addAttribute("msg", "Please Select District");
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//
//				if (present_village == null || present_village.trim().equals("")) {
//					ra.addAttribute("msg", "Please Enter Present City/Village");
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//				if (validation.maxlengthcheck100(present_village) == false) {
//					ra.addAttribute("msg", "Correspondence City/Village " + validation.MaxlengthcheckMSG100);
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//				if (validation.isOnlyAlphabetDASH(present_village) == false) {
//					ra.addAttribute("msg", "Correspondence City/Village " + validation.isOnlyAlphabetMSGDASH);
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//
//				if (present_pincode == null || present_pincode.trim().equals("")) {
//					ra.addAttribute("msg", "Please Enter Correspondence Pin Code");
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//				if (validation.maxlengthcheckpincode(present_pincode) == false) {
//					ra.addAttribute("msg", "Correspondence Pin Code " + validation.MaxlengthcheckMSGpincode);
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//				if (validation.minlengthcheckpincode(present_pincode) == false) {
//					ra.addAttribute("msg", "Correspondence Pin Code " + validation.MinlengthcheckMSGpincode);
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//				if (validation.isOnlyNumer(present_pincode) == true) {
//					ra.addAttribute("msg", "Present Enter Pin Code " + validation.isOnlyNumerMSG);
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//
//				if (present_phn_no == null || present_phn_no.trim().equals("")) {
//					ra.addAttribute("msg", "Please Enter Correspondence Phone No.");
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//				if (validation.isOnlyNumer(present_phn_no) == true) {
//					ra.addAttribute("msg", "Correspondence Phone No. " + validation.isOnlyNumerMSG);
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//				if (validation.maxlengthcheck10(present_phn_no) == false) {
//					ra.addAttribute("msg", "Correspondence Phone No. " + validation.MaxlengthcheckMSG10);
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//				if (validation.isValidMobileNo(present_phn_no) == false) {
//					ra.addAttribute("msg", "Correspondence Phone No. " + validation.isOnlyMobilenumberMSG);
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//			}
//
//			//////// Registration Details
//
//			if (registration_type == null || registration_type.trim().equals("1")) {
//
//				if (state_reg_no == null || state_reg_no.trim().equals("")) {
//					ra.addAttribute("msg", "Please Enter State Registration No.");
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//				if (validation.maxlengthcheck50(state_reg_no) == false) {
//					ra.addAttribute("msg", "State Registration No. " + validation.MaxlengthcheckMSG50);
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//				if (validation.isOnlyAlphabetNumeric(state_reg_no) == false) {
//					ra.addAttribute("msg", "State Registration No. " + validation.isOnlyAlphabetNumericMSG);
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//
//				if (state_board_name == null || state_board_name.trim().equals("")) {
//					ra.addAttribute("msg", "Please Enter Name of the State Board");
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//				if (validation.maxlengthcheck100(state_board_name) == false) {
//					ra.addAttribute("msg", "Name of the State Board " + validation.MaxlengthcheckMSG100);
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//				if (validation.isOnlyAlphabetDASH(state_board_name) == false) {
//					ra.addAttribute("msg", "Name of the State Board " + validation.isOnlyAlphabetMSGDASH);
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//
//				if (date_of_reg == null || date_of_reg.trim().equals("") || date_of_reg.equals("DD/MM/YYYY")) {
//					ra.addAttribute("msg", "Please Enter The Date Of Registration");
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//
//				if (central_reg_no == null || central_reg_no.trim().equals("")) {
//					ra.addAttribute("msg", "Please Enter Central Registration No.");
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//				if (validation.maxlengthcheck50(central_reg_no) == false) {
//					ra.addAttribute("msg", "Central Registration No. " + validation.MaxlengthcheckMSG50);
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//				if (validation.isOnlyAlphabetNumeric(central_reg_no) == false) {
//					ra.addAttribute("msg", "Central Registration No. " + validation.isOnlyAlphabetNumericMSG);
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//				if (state_validity_upto == null || state_validity_upto.trim().equals("")
//						|| state_validity_upto.equals("DD/MM/YYYY")) {
//					ra.addAttribute("msg", "Please Enter The Validity Upto");
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//			}
//			if (registration_type == null || registration_type.trim().equals("2")) {
//
//				if (direct_reg_no == null || direct_reg_no.trim().equals("")) {
//					ra.addAttribute("msg", "Please Enter Direct Registration No.");
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//				if (validation.maxlengthcheck50(direct_reg_no) == false) {
//					ra.addAttribute("msg", "Direct Registration No. " + validation.MaxlengthcheckMSG50);
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//				if (validation.isOnlyAlphabetNumeric(direct_reg_no) == false) {
//					ra.addAttribute("msg", "Direct Registration No. " + validation.isOnlyAlphabetNumericMSG);
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//
//				if (direct_date_of_reg == null || direct_date_of_reg.trim().equals("")
//						|| direct_date_of_reg.equals("DD/MM/YYYY")) {
//					ra.addAttribute("msg", "Please Enter The Date Of Registration");
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//
//				if (name_of_department == null || name_of_department.trim().equals("")) {
//					ra.addAttribute("msg", "Please Enter Name of Department/Organization");
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//				if (validation.maxlengthcheck100(name_of_department) == false) {
//					ra.addAttribute("msg", "Name of Department/Organization " + validation.MaxlengthcheckMSG100);
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//				if (validation.isOnlyAlphabetDASH(name_of_department) == false) {
//					ra.addAttribute("msg", "Name of Department/Organization " + validation.isOnlyAlphabetMSGDASH);
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//
//				if (direct_validity_upto == null || direct_validity_upto.trim().equals("")
//						|| direct_validity_upto.equals("DD/MM/YYYY")) {
//					ra.addAttribute("msg", "Please Enter The Date Of Registration");
//					return new ModelAndView("redirect:Teacher_dtl_Url");
//				}
//
//			}
//
//			if (data_fetch.equals("0")) {
//
///////////////////////////teacher_code generate			
//				String depart = "H-";
//				String reg_no = geteacher_code();
//				String testold = "";
//				if (reg_no != null) {
//					int newn = Integer.parseInt(reg_no);
//					newn++;
//					String abc = String.format("%8s", newn).replace(' ', '0');
//					testold = depart + abc;
//					// System.err.println("testold------->>>>" + testold);
//					rd.setTeacher_code(testold);
//
//				} else {
//					testold = "H-00000001";
//					rd.setTeacher_code(testold);
//				}
//
///////////////////////////ayush_id generate		
//				int increemt = 0;
//				String abr = "";
//				String maxAID = "";
//
//				maxAID = getAyushID(userid);
//				if (maxAID == null) {
//
//				} else {
//					increemt = Integer.parseInt(maxAID.substring(10));
//					abr = maxAID.substring(0, 10);
//					// increemt += 1;
//					maxAID = abr + String.format("%4s", increemt).replace(' ', '0');
//				}
//
//				rd.setAyush_id(maxAID);
//				rd.setUser_id(Integer.parseInt(userid));
//				rd.setFirst_name(first_name);
//				rd.setCand_prefix(cand_prefix);
//				rd.setMiddle_name(middle_name);
//				rd.setLast_name(last_name);
//				rd.setGender(gender);
//				rd.setDate_of_birth(datePickerFormat.parse(date_of_birth));
//				// rd.setDate_of_birth(formatter1.parse(dob1));
//				rd.setFather_name(father_name);
//				rd.setMother_name(mother_name);
//				rd.setSpouse_name(spouse_name);
//				rd.setMobile_no(mobile_no);
//				rd.setEmail(email);
//				rd.setAadhar_no(aadhar_no);
//				rd.setPan_no(pan_no);
//
//				rd.setPresent_add_line1(present_add_line1);
//				rd.setPresent_add_line2(present_add_line2);
//				rd.setPresent_village(present_village);
//				rd.setPresent_state(Integer.parseInt(present_state));
//				rd.setPresent_district(Integer.parseInt(present_district));
//				rd.setPresent_pincode(Integer.parseInt(present_pincode));
//				rd.setPresent_phn_no(present_phn_no);
//
//				rd.setPer_add_line1(per_add_line1);
//				rd.setPer_add_line2(per_add_line2);
//				rd.setPer_village(per_village);
//				rd.setPer_state(Integer.parseInt(per_state));
//				rd.setPer_district(Integer.parseInt(per_district));
//				rd.setPer_pincode(Integer.parseInt(per_pincode));
//				rd.setPer_phn_no(per_phn_no);
//
//////////////////////mirangi_21_07_22	
//				if (registration_type == null || registration_type.trim().equals("1")) {
//					rd.setState_reg_no(state_reg_no);
//					rd.setState_board_name(state_board_name);
//					rd.setDate_of_reg(datePickerFormat.parse(date_of_reg));
//// rd.setDate_of_reg(formatter1.parse(dt_reg));
//					rd.setCentral_reg_no(central_reg_no);
//					rd.setAdjunct_registration_no(adjunct_registration_no);
//					rd.setAdjunct_state_no(adjunct_state_no);
//					rd.setState_validity_upto(datePickerFormat.parse(state_validity_upto));
//				}
//
//				if (registration_type == null || registration_type.trim().equals("2")) {
//					rd.setRegistration_type(registration_type);
//					rd.setDirect_reg_no(Integer.parseInt(direct_reg_no));
//					rd.setDirect_date_of_reg(datePickerFormat.parse(direct_date_of_reg));
//					rd.setName_of_department(name_of_department);
//					rd.setDirect_validity_upto(datePickerFormat.parse(direct_validity_upto));
//				}
//
//				rd.setAcademic_quali(academic_quali);
//				rd.setSubject(subject);
//
/////////////////	//----rahul_11_07_22
//				List<ArrayList<String>> list2 = tdao.getUniversity_id(userid, "University_NCH");
//				List<ArrayList<String>> list = tdao.getPrincipal_id(userid, "Principal_NCH");
//
//				rd.setUniversity_userid(Integer.parseInt(list2.get(0).get(0)));
//				rd.setPrincipal_userid(Integer.parseInt(list.get(0).get(0)));
//
////	rd.setSub_quali_degree(Integer.parseInt(sub_quali_degree));
////	rd.setSub_deg_course(Integer.parseInt(sub_deg_course));
//
//				rd.setCreated_by(username);
//				rd.setCreated_date(date);
//
//				int pid = 0;
//
//				pid = (int) sessionHQL.save(rd);
//				sessionHQL.flush();
//				sessionHQL.clear();
//
//				// qualification
//
//				EDU_LMS_TEACHER_QUALIFICATION_CHILD qc = new EDU_LMS_TEACHER_QUALIFICATION_CHILD();
//
//				int count_hidden_att_name_med = Integer.parseInt(request.getParameter("count_hidden_att_name_med"));
//
//				// 23/08--edit
//				for (int j = 1; j <= count_hidden_att_name_med; j++) {
//
//					String quali_hid = request.getParameter("quali_hid" + j);
//
//					Long c = (Long) sessionHQL
//							.createQuery(
//									"select count(id) from  EDU_LMS_TEACHER_QUALIFICATION_CHILD where id=:quali_hid")
//							.setParameter("quali_hid", Integer.parseInt(quali_hid)).uniqueResult();
//
//					if (c != null && c == 0) {
//
//						String typeOfDegree = request.getParameter("typeOfDegree" + j);
//						String course = request.getParameter("course" + j);
//						String subjectQ = request.getParameter("subject" + j);
//						String monthYearOfDegree = request.getParameter("monthYearOfDegree" + j);
////					String rollno = request.getParameter("rollno" + j);
//						String NameOfUniversity = request.getParameter("NameOfUniversity" + j);
//						String NameOfAffUni = request.getParameter("NameOfAffUni" + j);
////					String percentage = request.getParameter("percentage" + j); 
//
//						if (typeOfDegree == null || typeOfDegree.equals("0")) {
//							ra.addAttribute("msg", "Please Select Name of Exam/Degree In Row " + j);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (course == null || course.equals("0")) {
//							ra.addAttribute("msg", "Please Select Course In Row " + j);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (subjectQ == null || subjectQ.equals("0")) {
//							ra.addAttribute("msg", "Please Select Subject In Row " + j);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (NameOfUniversity == null || NameOfUniversity.trim().equals("")) {
//							ra.addAttribute("msg", "Please Enter Institute Name In Row " + j);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (validation.maxlengthcheck50(NameOfUniversity) == false) {
//							ra.addAttribute("msg", "Institute Name " + validation.MaxlengthcheckMSG50);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (validation.isOnlyAlphabetDASH(NameOfUniversity) == false) {
//							ra.addAttribute("msg", "Institute Name " + validation.isOnlyAlphabetMSGDASH);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (NameOfAffUni == null || NameOfAffUni.trim().equals("")) {
//							ra.addAttribute("msg", "Please Enter Name of Affiliated University In Row " + j);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (validation.maxlengthcheck50(NameOfAffUni) == false) {
//							ra.addAttribute("msg", "Name of Affiliated University " + validation.MaxlengthcheckMSG50);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (validation.isOnlyAlphabetDASH(NameOfAffUni) == false) {
//							ra.addAttribute("msg", "Name of Affiliated University" + validation.isOnlyAlphabetMSGDASH);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (monthYearOfDegree == null || monthYearOfDegree.equals("")
//								|| monthYearOfDegree.equals("dd/mm/yyyy")) {
//							ra.addAttribute("msg", "Please Enter Month Year Of Degree" + j);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//
//						qc.setP_id(pid);
//						qc.setType_of_degree(typeOfDegree);
//						qc.setCourse(course);
//						qc.setSubject(Integer.parseInt(subjectQ));
//						qc.setMonth_and_year(monthYearOfDegree);
////					qc.setRoll_no(Integer.parseInt(rollno));
//						qc.setName_of_institute(NameOfUniversity);
//						qc.setAffiliated_university(NameOfAffUni);
////					qc.setPercentage(Integer.parseInt(percentage));
//						qc.setCreated_by(username);
//						qc.setCreated_date(date);
//
//						int at_pid = (int) sessionHQL.save(qc);
//						sessionHQL.flush();
//						sessionHQL.clear();
//
//						if (at_pid > 0) {
//
//							if (request.getParameter("count_hidden_att_doc" + j) != null
//									&& !request.getParameter("count_hidden_att_doc" + j).equals("")) {
//
//								int count_hid_subchild = Integer
//										.parseInt(request.getParameter("count_hidden_att_doc" + j));
//
//								for (int k = 1; k <= count_hid_subchild; k++) {
//									List<String> lst = sessionHQL
//											.createQuery(
//													"select id from EDU_LMS_TEACHER_QUALIFICATION_CHILD where p_id=:id")
//											.setParameter("id", qc.getId()).list();
//
//									if (lst != null && lst.size() > 0) {
//
//										for (int i = 0; i < lst.size(); i++) {
//											sessionHQL.createQuery(
//													"update  EDU_LMS_TEACHER_QUALIFICATION_ATTACHMENT_SUBCHILD set modified_by=:modified_by,modified_date=:modified_date where parent_id=:id")
//													.setParameter("id", lst.get(i))
//													.setParameter("modified_by", username)
//													.setParameter("modified_date", new Date()).executeUpdate();
//										}
//
//									}
//
//									EDU_LMS_TEACHER_QUALIFICATION_ATTACHMENT_SUBCHILD qas = new EDU_LMS_TEACHER_QUALIFICATION_ATTACHMENT_SUBCHILD();
//
//									String name_of_attachment = request
//											.getParameter("name_of_attachment" + j + "_" + k);
//									String certificate_type = request.getParameter("name_of_degree" + j + "_" + k);
//									MultipartFile attachmentchild = mul.getFile("attachmentDocument" + j + "_" + k);
//									String attachmentDoc_hid = request.getParameter("attachmentDoc_hid" + j + "_" + k);
//
//									if (name_of_attachment == null || name_of_attachment.equals("0")) {
//										ra.addAttribute("msg",
//												"Please Select Name of Attachment In Row " + j + " " + k);
//										return new ModelAndView("redirect:Teacher_dtl_Url");
//									}
//									if (name_of_attachment == null || name_of_attachment.equals("3")) {
//
//										if (certificate_type == null || certificate_type.equals("0")) {
//
//											ra.addAttribute("msg",
//													"Please Select Certificate Type In Row " + j + " " + k);
//											return new ModelAndView("redirect:Teacher_dtl_Url");
//										}
//									}
//
//									String pic = "";
//									if (!attachmentchild.isEmpty()) {
//										pic = common.fileupload(attachmentchild.getBytes(),
//												attachmentchild.getOriginalFilename(), "UploadHardCopy1");
//										System.err.println("");
//										if (pic != "") {
//
//											qas.setAttachment(pic);
////								ec.setAttachment_path(namep);
//										} else {
//											qas.setAttachment(attachmentDoc_hid);
//
//										}
//									}
//
//									if (name_of_attachment != null && !name_of_attachment.equals("")) {
//										qas.setName_of_attachment(name_of_attachment);
//									}
//
//									qas.setParent_id(at_pid);
//
//									qas.setCertificate_type(Integer.parseInt(certificate_type));
//
//									sessionHQL.save(qas);
//									sessionHQL.flush();
//									sessionHQL.clear();
//
//								}
//							}
//						}
//					}
//
//					else {
//
//						String typeOfDegree = request.getParameter("typeOfDegree" + j);
//						String course = request.getParameter("course" + j);
//						String subjectQ = request.getParameter("subject" + j);
//						String monthYearOfDegree = request.getParameter("monthYearOfDegree" + j);
////						String rollno = request.getParameter("rollno" + j);
//						String NameOfUniversity = request.getParameter("NameOfUniversity" + j);
//						String NameOfAffUni = request.getParameter("NameOfAffUni" + j);
////						String percentage = request.getParameter("percentage" + j); 
//
//						EDU_LMS_TEACHER_QUALIFICATION_CHILD HSCH = (EDU_LMS_TEACHER_QUALIFICATION_CHILD) sessionHQL
//								.get(EDU_LMS_TEACHER_QUALIFICATION_CHILD.class, Integer.parseInt(quali_hid));
//
//						if (typeOfDegree == null || typeOfDegree.equals("0")) {
//							ra.addAttribute("msg", "Please Select Name of Exam/Degree In Row " + j);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (course == null || course.equals("0")) {
//							ra.addAttribute("msg", "Please Select Course In Row " + j);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (subjectQ == null || subjectQ.equals("0")) {
//							ra.addAttribute("msg", "Please Select Subject In Row " + j);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (NameOfUniversity == null || NameOfUniversity.trim().equals("")) {
//							ra.addAttribute("msg", "Please Enter Institute Name In Row " + j);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (validation.maxlengthcheck50(NameOfUniversity) == false) {
//							ra.addAttribute("msg", "Institute Name " + validation.MaxlengthcheckMSG50);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (validation.isOnlyAlphabetDASH(NameOfUniversity) == false) {
//							ra.addAttribute("msg", "Institute Name " + validation.isOnlyAlphabetMSGDASH);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (NameOfAffUni == null || NameOfAffUni.trim().equals("")) {
//							ra.addAttribute("msg", "Please Enter Name of Affiliated University In Row " + j);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (validation.maxlengthcheck50(NameOfAffUni) == false) {
//							ra.addAttribute("msg", "Name of Affiliated University " + validation.MaxlengthcheckMSG50);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (validation.isOnlyAlphabetDASH(NameOfAffUni) == false) {
//							ra.addAttribute("msg", "Name of Affiliated University" + validation.isOnlyAlphabetMSGDASH);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (monthYearOfDegree == null || monthYearOfDegree.equals("")
//								|| monthYearOfDegree.equals("dd/mm/yyyy")) {
//							ra.addAttribute("msg", "Please Enter Month Year Of Degree" + j);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//
//						HSCH.setP_id(pid);
//
//						HSCH.setType_of_degree(typeOfDegree);
//						HSCH.setCourse(course);
//						HSCH.setSubject(Integer.parseInt(subjectQ));
//						HSCH.setMonth_and_year(monthYearOfDegree);
//
//						HSCH.setName_of_institute(NameOfUniversity);
//						qc.setAffiliated_university(NameOfAffUni);
//
//						HSCH.setCreated_by(username);
//						HSCH.setCreated_date(date);
//
//						sessionHQL.update(HSCH);
//						sessionHQL.flush();
//						sessionHQL.clear();
//
//						int at_pid = HSCH.getId();
//
//						if (at_pid > 0) {
//
//							if (request.getParameter("count_hidden_att_doc" + j) != null
//									&& !request.getParameter("count_hidden_att_doc" + j).equals("")) {
//
//								int count_hid_subchild = Integer
//										.parseInt(request.getParameter("count_hidden_att_doc" + j));
//
//								for (int k = 1; k <= count_hid_subchild; k++) {
//									List<String> lst = sessionHQL
//											.createQuery(
//													"select id from EDU_LMS_TEACHER_QUALIFICATION_CHILD where p_id=:id")
//											.setParameter("id", qc.getId()).list();
//
//									if (lst != null && lst.size() > 0) {
//
//										for (int i = 0; i < lst.size(); i++) {
//											sessionHQL.createQuery(
//													"update  EDU_LMS_TEACHER_QUALIFICATION_ATTACHMENT_SUBCHILD set modified_by=:modified_by,modified_date=:modified_date where parent_id=:id")
//													.setParameter("id", lst.get(i))
//													.setParameter("modified_by", username)
//													.setParameter("modified_date", new Date()).executeUpdate();
//										}
//
//									}
//
//									EDU_LMS_TEACHER_QUALIFICATION_ATTACHMENT_SUBCHILD qas = new EDU_LMS_TEACHER_QUALIFICATION_ATTACHMENT_SUBCHILD();
//
//									String name_of_attachment = request
//											.getParameter("name_of_attachment" + j + "_" + k);
//									String certificate_type = request.getParameter("name_of_degree" + j + "_" + k);
//									MultipartFile attachmentchild = mul.getFile("attachmentDocument" + j + "_" + k);
//									String attachmentDoc_hid = request.getParameter("attachmentDoc_hid" + j + "_" + k);
//
//									if (name_of_attachment == null || name_of_attachment.equals("0")) {
//										ra.addAttribute("msg",
//												"Please Select Name of Attachment In Row " + j + " " + k);
//										return new ModelAndView("redirect:Teacher_dtl_Url");
//									}
//									if (name_of_attachment == null || name_of_attachment.equals("3")) {
//
//										if (certificate_type == null || certificate_type.equals("0")) {
//
//											ra.addAttribute("msg",
//													"Please Select Certificate Type In Row " + j + " " + k);
//											return new ModelAndView("redirect:Teacher_dtl_Url");
//										}
//									}
//
//									String pic = "";
//									if (!attachmentchild.isEmpty()) {
//										pic = common.fileupload(attachmentchild.getBytes(),
//												attachmentchild.getOriginalFilename(), "UploadHardCopy1");
//										System.err.println("");
//										if (pic != "") {
//
//											qas.setAttachment(pic);
////									ec.setAttachment_path(namep);
//										} else {
//											qas.setAttachment(attachmentDoc_hid);
//
//										}
//									}
//
//									if (name_of_attachment != null && !name_of_attachment.equals("")) {
//										qas.setName_of_attachment(name_of_attachment);
//									}
//
//									qas.setParent_id(at_pid);
//
//									qas.setCertificate_type(Integer.parseInt(certificate_type));
//
//									sessionHQL.save(qas);
//									sessionHQL.flush();
//									sessionHQL.clear();
//
//								}
//							}
//						}
//
//					}
//				}
//
//				// other qualification
//
//				EDU_LMS_TEACHER_OTHER_QUALIFICATION_CHILD oqc = new EDU_LMS_TEACHER_OTHER_QUALIFICATION_CHILD();
//
//				int count_hidden_att_oth_quali = Integer.parseInt(request.getParameter("count_hidden_att_oth_quali"));
//
//				for (int j = 1; j <= count_hidden_att_oth_quali; j++) {
//
//					String OthnameofExDeg = request.getParameter("OthnameofExDeg" + j);
//					String othSubject = request.getParameter("othSubject" + j);
//					String othUniInst = request.getParameter("othUniInst" + j);
//					String othAffuni = request.getParameter("othAffuni" + j);
//					String othYrofpass = request.getParameter("othYrofpass" + j);
//
//					oqc.setP_id(pid);
//					oqc.setName_of_exam_degree(OthnameofExDeg);
//					oqc.setSubject(othSubject);
//					oqc.setName_of_uni_inst(othUniInst);
//					oqc.setName_of_aff_uni(othAffuni);
//					oqc.setMonth_and_year(othYrofpass);
//					oqc.setCreated_by(username);
//					oqc.setCreated_date(date);
//
//					int at_pid = (int) sessionHQL.save(oqc);
//					sessionHQL.flush();
//					sessionHQL.clear();
//
//					if (at_pid > 0) {
//						if (request.getParameter("count_hidden_att_OQ" + j) != null
//								&& !request.getParameter("count_hidden_att_OQ" + j).equals("")) {
//
//							int count_hid_subchild = Integer.parseInt(request.getParameter("count_hidden_att_OQ" + j));
//
//							for (int k = 1; k <= count_hid_subchild; k++) {
//								List<String> lst = sessionHQL.createQuery(
//										"select id from EDU_LMS_TEACHER_OTHER_QUALIFICATION_CHILD where p_id=:id")
//										.setParameter("id", qc.getId()).list();
//
//								if (lst != null && lst.size() > 0) {
//
//									for (int i = 0; i < lst.size(); i++) {
//										sessionHQL.createQuery(
//												"Delete from EDU_LMS_TEACHER_OTHER_QUALIFICATION_SUB_CHILD where parent_id=:id")
//												.setParameter("id", lst.get(i)).executeUpdate();
//									}
//
//								}
//
//								EDU_LMS_TEACHER_OTHER_QUALIFICATION_SUB_CHILD oqas = new EDU_LMS_TEACHER_OTHER_QUALIFICATION_SUB_CHILD();
//
//								String name_of_attachment = request.getParameter("name_of_attachmentOQ" + j + "_" + k);
//
//								MultipartFile attachmentchild = mul.getFile("attachmentDocumentOQ" + j + "_" + k);
//								String attachmentDoc_hid = request.getParameter("attachmentDoc_hidOQ" + j + "_" + k);
//
//								String pic = "";
//								if (!attachmentchild.isEmpty()) {
//									pic = common.fileupload(attachmentchild.getBytes(),
//											attachmentchild.getOriginalFilename(), "UploadHardCopy1");
//									System.err.println("");
//									if (pic != "") {
//
//										oqas.setAttachment(pic);
////								ec.setAttachment_path(namep);
//									} else {
//										oqas.setAttachment(attachmentDoc_hid);
//
//									}
//								}
//								if (name_of_attachment != null && !name_of_attachment.equals("")) {
//									oqas.setName_of_attachment(name_of_attachment);
//								}
//
//								oqas.setParent_id(at_pid);
//
//								sessionHQL.save(oqas);
//								sessionHQL.flush();
//								sessionHQL.clear();
//
//							}
//						}
//					}
//				}
//
//				EDU_LMS_TEACHER_EXPERIENCE_CHILD od = new EDU_LMS_TEACHER_EXPERIENCE_CHILD();
//
//				// DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy",
//				// Locale.ENGLISH);
//				int count_hidden_att = Integer.parseInt(request.getParameter("count_hidden_att"));
//
//				for (int i = 1; i <= count_hidden_att; i++) {
//					String institute_name = request.getParameter("institute_name" + i);
//					String depart_name = request.getParameter("depart_name" + i);
//					String desig = request.getParameter("desig" + i);
//					String employment_type = request.getParameter("employment_type" + i);
//					String from_date = request.getParameter("from_date" + i);
//					String to_date = request.getParameter("to_date" + i);
//					String honorarium = request.getParameter("honorarium" + i);
//
//					String du = "upload_file" + i;
//					String doc = gd(request, mul, session, du);
//
//					if (institute_name == null || institute_name.trim().equals("")) {
//						ra.addAttribute("msg", "Please Enter Institute Name In Row " + i);
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
//					if (validation.maxlengthcheck50(institute_name) == false) {
//						ra.addAttribute("msg", "Institute Name " + validation.MaxlengthcheckMSG50);
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
////					if (validation.isOnlyAlphabetDASH(institute_name) == false) {
////						ra.addAttribute("msg", "Institute Name " + validation.isOnlyAlphabetMSGDASH);
////						return new ModelAndView("redirect:Teacher_dtl_Url");
////					}
//
//					if (depart_name == null || depart_name.equals("0")) {
//						ra.addAttribute("msg", "Please Select Department Name In Row " + i);
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
//					if (desig == null || desig.equals("0")) {
//						ra.addAttribute("msg", "Please Select Designation In Row " + i);
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
//					if (employment_type == null || employment_type.equals("0")) {
//						ra.addAttribute("msg", "Please Select Employment Type In Row " + i);
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
//
//					if (from_date == null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
//						ra.addAttribute("msg", "Please Enter The From Date In Row " + i);
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
//					if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
//						ra.addAttribute("msg", "Please Enter The To Date In Row " + i);
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
//					if (datePickerFormat.parse(to_date).compareTo(datePickerFormat.parse(from_date)) < 0) {
//						ra.addAttribute("msg", "To Date Should Be Greater Than Or Equal To From Date In Row " + i);
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
////					if (doc.isEmpty()) {
////						ra.addAttribute("msg", "Please Select File In Row " + i);
////						return new ModelAndView("redirect:Teacher_dtl_Url");
////
////					}
//
//					od.setInstitute_name(institute_name);
//					od.setDepart_name(depart_name);
//					od.setDesig(desig);
//					od.setEmployment_type(employment_type);
//					od.setFrom_date(datePickerFormat.parse(from_date));
//					od.setTo_date(datePickerFormat.parse(to_date));
//					od.setUpload_file(doc);
//					od.setCreated_by(username);
//					od.setCreated_date(date);
//					od.setHonorarium(honorarium);
//					od.setP_id(pid);
//
//					sessionHQL.save(od);// 1
//					sessionHQL.flush();
//					sessionHQL.clear();
//				}
//
//				EDU_LMS_DEGREE_AND_SUPPORT_DOC_CHILD ds = new EDU_LMS_DEGREE_AND_SUPPORT_DOC_CHILD();
//
//				int count_hidden_doc = Integer.parseInt(request.getParameter("count_hidden_doc"));
//
//				for (int i = 1; i <= count_hidden_doc; i++) {
//					String doc_name = request.getParameter("doc_name" + i);
//					String doc_id = request.getParameter("doc_id" + i);
//					String du = "upload_doc" + i;
//					String doc = gd(request, mul, session, du);
//
//					if (doc_name == null || doc_name.equals("0")) {
//						ra.addAttribute("msg", "Please Select Document Name In Row " + i);
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
//					if (doc_id == null || doc_id.equals("0")) {
//						ra.addAttribute("msg", "Please Select Document Type In Row " + i);
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
//
////					if (doc.isEmpty()) {
////						ra.addAttribute("msg", "Please Select Document In Row " + i);
////						return new ModelAndView("redirect:Teacher_dtl_Url");
////
////					}
//
//					ds.setDoc_name(doc_name);
//					ds.setDoc_id(Integer.parseInt(doc_id));
//					ds.setUpload_doc(doc);
//					ds.setCreated_by(username);
//					ds.setCreated_date(date);
//					ds.setP_id(pid);
//
//					sessionHQL.save(ds);// 2
//					sessionHQL.flush();
//					sessionHQL.clear();
//				}
//
//				ra.addAttribute("msg", "Data Saved Successfully.");
//
//			} else {
//				System.err.println("id for update-----------" + id_for_update + "---");
//				EDU_LMS_TEACHER_DTL urd = (EDU_LMS_TEACHER_DTL) sessionHQL.get(EDU_LMS_TEACHER_DTL.class,
//						Integer.parseInt(id_for_update));
//
//				urd.setTeacher_code(teachercode);
//				urd.setAyush_id(ayush_id);
//				urd.setUser_id(Integer.parseInt(userid));
//				urd.setFirst_name(first_name);
//				urd.setCand_prefix(cand_prefix);
//				urd.setMiddle_name(middle_name);
//				urd.setLast_name(last_name);
//				urd.setGender(gender);
//				urd.setDate_of_birth(datePickerFormat.parse(date_of_birth));
//				// rd.setDate_of_birth(formatter1.parse(dob1));
//				urd.setFather_name(father_name);
//				urd.setMother_name(mother_name);
//				urd.setSpouse_name(spouse_name);
//				urd.setMobile_no(mobile_no);
//				urd.setEmail(email);
//				urd.setAadhar_no(aadhar_no);
//				urd.setPan_no(pan_no);
//
//				urd.setPresent_add_line1(present_add_line1);
//				urd.setPresent_add_line2(present_add_line2);
//				urd.setPresent_village(present_village);
//				urd.setPresent_state(Integer.parseInt(present_state));
//				urd.setPresent_district(Integer.parseInt(present_district));
//				urd.setPresent_pincode(Integer.parseInt(present_pincode));
//				urd.setPresent_phn_no(present_phn_no);
//
//				urd.setPer_add_line1(per_add_line1);
//				urd.setPer_add_line2(per_add_line2);
//				urd.setPer_village(per_village);
//				urd.setPer_state(Integer.parseInt(per_state));
//				urd.setPer_district(Integer.parseInt(per_district));
//				urd.setPer_pincode(Integer.parseInt(per_pincode));
//				urd.setPer_phn_no(per_phn_no);
//
//////////////////////mirangi_21_07_22	
//				if (registration_type == null || registration_type.trim().equals("1")) {
//					urd.setState_reg_no(state_reg_no);
//					urd.setState_board_name(state_board_name);
//					urd.setDate_of_reg(datePickerFormat.parse(date_of_reg));
////rd.setDate_of_reg(formatter1.parse(dt_reg));
//					urd.setCentral_reg_no(central_reg_no);
//					urd.setAdjunct_registration_no(adjunct_registration_no);
//					urd.setAdjunct_state_no(adjunct_state_no);
//					urd.setState_validity_upto(datePickerFormat.parse(state_validity_upto));
//				}
//
//				if (registration_type == null || registration_type.trim().equals("2")) {
//					urd.setRegistration_type(registration_type);
//					urd.setDirect_reg_no(Integer.parseInt(direct_reg_no));
//					urd.setDirect_date_of_reg(datePickerFormat.parse(direct_date_of_reg));
//					urd.setName_of_department(name_of_department);
//					urd.setDirect_validity_upto(datePickerFormat.parse(direct_validity_upto));
//				}
//
//				urd.setAcademic_quali(academic_quali);
//				urd.setSubject(subject);
//
////				urd.setSub_quali_degree(Integer.parseInt(sub_quali_degree));
////				urd.setSub_deg_course(Integer.parseInt(sub_deg_course));
//
//				urd.setModified_by(username);
//				urd.setModified_date(date);
//
//				sessionHQL.update(urd);
//				sessionHQL.flush();
//				sessionHQL.clear();
//
//				List<Map<String, Object>> list = tdao.getTeacherchild(Integer.parseInt(id_for_update));
//				List<Map<String, Object>> list2 = tdao.getdegreeandsupportchild(Integer.parseInt(id_for_update));
//
//				int count_hidden_att = Integer.parseInt(request.getParameter("count_hidden_att"));
//
//				for (int i = 1; i <= count_hidden_att; i++) {
//
//					for (int z = 0; z < list.size(); z++) {
//						String child_id = list.get(z).get("id").toString();
//						System.err.println("child id ---" + child_id);
//
//						Session sessionHQLD = this.sessionFactory.openSession();
//						Transaction txD = sessionHQLD.beginTransaction();
//
//						String hqlUpdate = "delete from EDU_LMS_TEACHER_EXPERIENCE_CHILD where id=:id";
//
//						int app = sessionHQL.createQuery(hqlUpdate).setParameter("id", Integer.parseInt(child_id))
//								.executeUpdate();
//
//						System.err.println("");
//						txD.commit();
//						sessionHQLD.close();
//
//						if (app > 0) {
//							System.err.println("DELETED");
//						} else {
//							System.err.println("NOT DELETED");
//						}
//					}
//					EDU_LMS_TEACHER_EXPERIENCE_CHILD od2 = new EDU_LMS_TEACHER_EXPERIENCE_CHILD();
//
////						EDU_LMS_TEACHER_EXPERIENCE_CHILD od2 = (EDU_LMS_TEACHER_EXPERIENCE_CHILD) sessionHQL.get(EDU_LMS_TEACHER_EXPERIENCE_CHILD.class,Integer.parseInt(child_id));
//
//					String institute_name = request.getParameter("institute_name" + i);
//					String depart_name = request.getParameter("depart_name" + i);
//					String desig = request.getParameter("desig" + i);
//					String employment_type = request.getParameter("employment_type" + i);
//					String from_date = request.getParameter("from_date" + i);
//					String to_date = request.getParameter("to_date" + i);
//					String honorarium = request.getParameter("honorarium" + i);
//
//					String upload_file_hidT = request.getParameter("upload_file_hid" + i);
//
//					String du = "upload_file" + i;
//					String doc = gd(request, mul, session, du);
//
//					if (institute_name == null || institute_name.trim().equals("")) {
//						ra.addAttribute("msg", "Please Enter Institute Name In Row " + i);
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
//					if (validation.maxlengthcheck50(institute_name) == false) {
//						ra.addAttribute("msg", "Institute Name " + validation.MaxlengthcheckMSG50);
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
////						if (validation.isOnlyAlphabetDASH(institute_name) == false) {
////							ra.addAttribute("msg", "Institute Name " + validation.isOnlyAlphabetMSGDASH);
////							return new ModelAndView("redirect:Teacher_dtl_Url");
////						}
//
//					if (depart_name == null || depart_name.equals("0")) {
//						ra.addAttribute("msg", "Please Select Department Name In Row " + i);
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
//					if (desig == null || desig.equals("0")) {
//						ra.addAttribute("msg", "Please Select Designation In Row " + i);
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
//
//					if (employment_type == null || employment_type.equals("0")) {
//						ra.addAttribute("msg", "Please Select Employment Type In Row " + i);
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
//
//					if (from_date == null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
//						ra.addAttribute("msg", "Please Enter The From Date In Row " + i);
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
//					if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
//						ra.addAttribute("msg", "Please Enter The To Date In Row " + i);
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
//					if (datePickerFormat.parse(to_date).compareTo(datePickerFormat.parse(from_date)) < 0) {
//						ra.addAttribute("msg", "To Date Should Be Greater Than Or Equal To From Date In Row " + i);
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
//					if (honorarium == null || honorarium.equals("0")) {
//						ra.addAttribute("msg", "Please Select Honorarium In Row " + i);
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
////						if (doc.isEmpty()) {
////							ra.addAttribute("msg", "Please Select File In Row " + i);
////							return new ModelAndView("redirect:Teacher_dtl_Url");
////
////						}
//
//					od2.setInstitute_name(institute_name);
//					od2.setDepart_name(depart_name);
//					od2.setDesig(desig);
//					od2.setEmployment_type(employment_type);
//					od2.setFrom_date(datePickerFormat.parse(from_date));
//					od2.setTo_date(datePickerFormat.parse(to_date));
//					od2.setHonorarium(honorarium);
//					// od2.setUpload_file(doc);
//
//					String pic = "";
//					if (doc != "") {
//
//						od2.setUpload_file(doc);
//					} else {
//						od2.setUpload_file(upload_file_hidT);
//
//					}
//
//					od2.setCreated_by(username);
//					od2.setCreated_date(date);
//					od2.setP_id(Integer.parseInt(id_for_update));
//
//					sessionHQL.save(od2);// 1
//					sessionHQL.flush();
//					sessionHQL.clear();
//
//				}
//
//				int count_hidden_doc = Integer.parseInt(request.getParameter("count_hidden_doc"));
//
//				for (int i = 1; i <= count_hidden_doc; i++) {
//
//					for (int w = 0; w < list2.size(); w++) {
//
//						String child_id2 = list2.get(w).get("id").toString();
//						System.err.println("child id ---" + child_id2);
//
//						Session sessionHQLD1 = this.sessionFactory.openSession();
//						Transaction txD1 = sessionHQLD1.beginTransaction();
//
//						String hqlUpdate = "delete from EDU_LMS_DEGREE_AND_SUPPORT_DOC_CHILD where id=:id";
//
//						int app = sessionHQL.createQuery(hqlUpdate).setParameter("id", Integer.parseInt(child_id2))
//								.executeUpdate();
//						txD1.commit();
//						sessionHQLD1.close();
//
//						if (app > 0) {
//							System.err.println("DELETED 2");
//						} else {
//							System.err.println("NOT DELETED 2");
//						}
//					}
//					EDU_LMS_DEGREE_AND_SUPPORT_DOC_CHILD ds2 = new EDU_LMS_DEGREE_AND_SUPPORT_DOC_CHILD();
//
////						EDU_LMS_DEGREE_AND_SUPPORT_DOC_CHILD ds2 = (EDU_LMS_DEGREE_AND_SUPPORT_DOC_CHILD) sessionHQL.get(EDU_LMS_DEGREE_AND_SUPPORT_DOC_CHILD.class,Integer.parseInt(child_id2));
//
//					String doc_name = request.getParameter("doc_name" + i);
//					String doc_id = request.getParameter("doc_id" + i);
//
//					String upload_docT = request.getParameter("doc_upload_hid" + i);
//					String du = "upload_doc" + i;
//					String doc = gd(request, mul, session, du);
//
//					if (doc_name == null || doc_name.equals("0")) {
//						ra.addAttribute("msg", "Please Select Document Name In Row " + i);
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
//					if (doc_id == null || doc_id.equals("0")) {
//						ra.addAttribute("msg", "Please Select Document Type In Row " + i);
//						return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
//
////					if (doc.isEmpty()) {
////						ra.addAttribute("msg", "Please Select Document In Row " + i);
////						return new ModelAndView("redirect:Teacher_dtl_Url");
////
////					}
//
//					ds2.setDoc_name(doc_name);
//					ds2.setDoc_id(Integer.parseInt(doc_id));
//					// ds2.setUpload_doc(doc);
//
//					String pic = "";
//
//					if (doc != "") {
//
//						ds2.setUpload_doc(doc);
//					} else {
//						ds2.setUpload_doc(upload_docT);
//
//					}
//
//					ds2.setCreated_by(username);
//					ds2.setCreated_date(date);
//					ds2.setP_id(Integer.parseInt(id_for_update));
//
//					sessionHQL.save(ds2);// 2
//					sessionHQL.flush();
//					sessionHQL.clear();
//
//				}
//
/////////////////Rahul-20/07/2022
//
//				int p_id = urd.getId();
//				if (p_id > 0) {
//
//					int data = 0;
//					int data2 = 0;
//					try {
//
//						List<String> lst = sessionHQL
//								.createQuery("select id from EDU_LMS_TEACHER_QUALIFICATION_CHILD where p_id=:id")
//								.setParameter("id", urd.getId()).list();
//
//						if (lst != null && lst.size() > 0) {
//
//							for (int i = 0; i < lst.size(); i++) {
//								sessionHQL.createQuery(
//										"Delete from EDU_LMS_TEACHER_QUALIFICATION_ATTACHMENT_SUBCHILD where parent_id=:id")
//										.setParameter("id", lst.get(i)).executeUpdate();
//							}
//
//						}
//
//						data = (int) sessionHQL
//								.createQuery("Delete from EDU_LMS_TEACHER_QUALIFICATION_CHILD where p_id=:id")
//								.setParameter("id", urd.getId()).executeUpdate();
//						System.err.println("========" + data);
//
//						System.err.println("========" + data2);
//
//					} catch (Exception e) {
//						e.printStackTrace();
//						// TODO: handle exception
//					} finally {
//						sessionHQL.flush();
//						sessionHQL.clear();
////					sessionHQL.close();
////					tx.commit();
//					}
//
//					if (data != 0) {
//						int count_hidden_att_name_med = Integer
//								.parseInt(request.getParameter("count_hidden_att_name_med"));
//
//						for (int j = 1; j <= count_hidden_att_name_med; j++) {
//
//							EDU_LMS_TEACHER_QUALIFICATION_CHILD ec = new EDU_LMS_TEACHER_QUALIFICATION_CHILD();
//
//							String typeOfDegree = request.getParameter("typeOfDegree" + j);
//							String course = request.getParameter("course" + j);
//							String subjectQ = request.getParameter("subject" + j);
//							String monthYearOfDegree = request.getParameter("monthYearOfDegree" + j);
//							String NameOfUniversity = request.getParameter("NameOfUniversity" + j);
//							String NameOfAffUni = request.getParameter("NameOfAffUni" + j);
//
//							ec.setP_id(p_id);
//							ec.setType_of_degree(typeOfDegree);
//							ec.setCourse(course);
//							ec.setSubject(Integer.parseInt(subjectQ));
//							ec.setMonth_and_year(monthYearOfDegree);
//							ec.setName_of_institute(NameOfUniversity);
//							ec.setAffiliated_university(NameOfAffUni);
//							ec.setCreated_by(username);
//							ec.setCreated_date(date);
//
//							int at_pidu = (int) sessionHQL.save(ec);
//							sessionHQL.flush();
//							sessionHQL.clear();
//
//							if (at_pidu > 0) {
//								if (request.getParameter("count_hidden_att_doc" + j) != null
//										&& !request.getParameter("count_hidden_att_doc" + j).equals("")) {
//
//									int count_hid_subchild = Integer
//											.parseInt(request.getParameter("count_hidden_att_doc" + j));
//
//									for (int k = 1; k <= count_hid_subchild; k++) {
//
//										EDU_LMS_TEACHER_QUALIFICATION_ATTACHMENT_SUBCHILD subatt = new EDU_LMS_TEACHER_QUALIFICATION_ATTACHMENT_SUBCHILD();
//
//										String NameOfATT = request.getParameter("name_of_attachment" + j + "_" + k);
//										String certificate_type = request.getParameter("name_of_degree" + j + "_" + k);
//										MultipartFile attachment = mul.getFile("attachmentDocument" + j + "_" + k);
//
//										String attachmentDoc_hid = request
//												.getParameter("attachmentDoc_hid" + j + "_" + k);
//
//										String pic = "";
//										if (attachment != null && !attachment.isEmpty()) {
//											pic = common.fileupload(attachment.getBytes(),
//													attachment.getOriginalFilename(), "UploadHardCopy1");
//											System.err.println("");
//											if (pic != "") {
//												subatt.setAttachment(pic);
//											} else {
//												subatt.setAttachment(attachmentDoc_hid);
//											}
//										} else {
//											subatt.setAttachment(attachmentDoc_hid);
//										}
//										if (NameOfATT != null && !NameOfATT.equals("")) {
//											subatt.setName_of_attachment(NameOfATT);
//										}
//										if (NameOfATT != null && !NameOfATT.equals("")) {
//
//											subatt.setCertificate_type(Integer.parseInt(certificate_type));
//											subatt.setParent_id(at_pidu);
//
//											sessionHQL.save(subatt);
//											sessionHQL.flush();
//											sessionHQL.clear();
//										}
//
//									}
//
//								}
//
//							}
//						}
//					}
//
//					// ----------------------------HARSH--------------------HARSH------------------------HARSH
//
//					int data3 = 0;
//
//					try {
//
//						List<String> lst = sessionHQL
//								.createQuery("select id from EDU_LMS_TEACHER_OTHER_QUALIFICATION_CHILD where p_id=:id")
//								.setParameter("id", urd.getId()).list();
//
//						if (lst != null && lst.size() > 0) {
//
//							for (int i = 0; i < lst.size(); i++) {
//								sessionHQL.createQuery(
//										"Delete from EDU_LMS_TEACHER_OTHER_QUALIFICATION_SUB_CHILD where parent_id=:id")
//										.setParameter("id", lst.get(i)).executeUpdate();
//							}
//
//						}
//
//						data3 = (int) sessionHQL
//								.createQuery("Delete from EDU_LMS_TEACHER_OTHER_QUALIFICATION_CHILD where p_id=:id")
//								.setParameter("id", urd.getId()).executeUpdate();
//						System.err.println("========" + data3);
//
//					} catch (Exception e) {
//						e.printStackTrace();
//					} finally {
//						sessionHQL.flush();
//						sessionHQL.clear();
//					}
//
//					if (data3 != 0) {
//						int count_hidden_att_oth_quali = Integer
//								.parseInt(request.getParameter("count_hidden_att_oth_quali"));
//
//						for (int j = 1; j <= count_hidden_att_oth_quali; j++) {
//
//							EDU_LMS_TEACHER_OTHER_QUALIFICATION_CHILD ec = new EDU_LMS_TEACHER_OTHER_QUALIFICATION_CHILD();
//
////					String typeOfDegree = request.getParameter("typeOfDegree" + j);
////					String course = request.getParameter("course" + j);
////					String subjectQ = request.getParameter("subject" + j);
////					String monthYearOfDegree = request.getParameter("monthYearOfDegree" + j);
////					String NameOfUniversity = request.getParameter("NameOfUniversity" + j);
////					String NameOfAffUni = request.getParameter("NameOfAffUni" + j);
////					
////					
////					ec.setP_id(p_id);
////					ec.setType_of_degree(typeOfDegree);
////					ec.setCourse(course);
////					ec.setSubject(Integer.parseInt(subjectQ));
////					ec.setMonth_and_year(monthYearOfDegree);
////					ec.setName_of_institute(NameOfUniversity);
////					ec.setAffiliated_university(NameOfAffUni);
//
//							String OthnameofExDeg = request.getParameter("OthnameofExDeg" + j);
//							String othSubject = request.getParameter("othSubject" + j);
//							String othUniInst = request.getParameter("othUniInst" + j);
//							String othAffuni = request.getParameter("othAffuni" + j);
//							String othYrofpass = request.getParameter("othYrofpass" + j);
//
//							ec.setP_id(p_id);
//							ec.setName_of_exam_degree(OthnameofExDeg);
//							ec.setSubject(othSubject);
//							ec.setName_of_uni_inst(othUniInst);
//							ec.setName_of_aff_uni(othAffuni);
//							ec.setMonth_and_year(othYrofpass);
//
//							int at_pidu = (int) sessionHQL.save(ec);
//							sessionHQL.flush();
//							sessionHQL.clear();
//
//							if (at_pidu > 0) {
//								if (request.getParameter("count_hidden_att_OQ" + j) != null
//										&& !request.getParameter("count_hidden_att_OQ" + j).equals("")) {
//
//									int count_hid_subchild = Integer
//											.parseInt(request.getParameter("count_hidden_att_OQ" + j));
//
//									for (int k = 1; k <= count_hid_subchild; k++) {
//
//										EDU_LMS_TEACHER_OTHER_QUALIFICATION_SUB_CHILD subatt = new EDU_LMS_TEACHER_OTHER_QUALIFICATION_SUB_CHILD();
//
//										String NameOfATT = request.getParameter("name_of_attachmentOQ" + j + "_" + k);
//										MultipartFile attachment = mul.getFile("attachmentDocumentOQ" + j + "_" + k);
//										String attachmentDoc_hid = request
//												.getParameter("attachmentDoc_hidOQ" + j + "_" + k);
//
//										String pic = "";
//										if (attachment != null && !attachment.isEmpty()) {
//											pic = common.fileupload(attachment.getBytes(),
//													attachment.getOriginalFilename(), "UploadHardCopy1");
//											System.err.println("");
//											if (pic != "") {
//												subatt.setAttachment(pic);
//											} else {
//												subatt.setAttachment(attachmentDoc_hid);
//											}
//										} else {
//											subatt.setAttachment(attachmentDoc_hid);
//										}
//										if (NameOfATT != null && !NameOfATT.equals("")) {
//											subatt.setName_of_attachment(NameOfATT);
//										}
//										if (NameOfATT != null && !NameOfATT.equals("")) {
//
//											subatt.setParent_id(at_pidu);
//
//											sessionHQL.save(subatt);
//											sessionHQL.flush();
//											sessionHQL.clear();
//										}
//
//									}
//
//								}
//
//							}
//						}
//					}
//
//				}
//				ra.addAttribute("msg", "Data Updated Successfully.");
//			}
//
////			UserLogin p = new UserLogin();
////			String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
////			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
////			String hashedPassword = passwordEncoder.encode("Bisag@123");
////			p.setPassword(hashedPassword);
////			p.setUserName(email);
////			p.setEnabled(1);
////			p.setAccountNonExpired(1);
////			p.setAccountNonLocked(1);
////			p.setCredentialsNonExpired(1);
////			p.setAc_dc_date(modifydate);
////			p.setMobile_no(mobile_no);
////			p.setEmail_id(email);
////			p.setInstitute_id(Integer.parseInt(common.getInstIdfromUserid(sessionFactory, userid).get(0)));
//////			p.setState_id(Integer.parseInt(
//////					common.getInstitudeState(sessionFactory, common.getInstIdfromUserid(sessionFactory, userid).get(0)).get(0)));
////
////			// p.setArmy_no(army_no);
////			p.setCreated_on(new Date());
////			p.setCreated_by(username);
////			UserRole role_tbl = new UserRole();
////			// sessionHQL.beginTransaction();
////
////			int did = (Integer) sessionHQL.save(p);// 3
////			role_tbl.setRoleId();
////			role_tbl.setUserId(did);
////			sessionHQL.save(role_tbl);
////			sessionHQL.flush();
////			sessionHQL.clear();
//
//			tx.commit();
//
//		} catch (RuntimeException e) {
//			try {
//				ra.addAttribute("msg", "roll back transaction");
//			} catch (RuntimeException rbe) {
//				ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
//			}
//			throw e;
//		} finally {
//			if (sessionHQL != null) {
//				sessionHQL.close();
//			}
//		}
//		return new ModelAndView("redirect:Teacher_dtl_Url");
//	}
//	
//
	public String gd(HttpServletRequest request, MultipartHttpServletRequest mul, HttpSession session, String id)
			throws IOException {

		String extension = ""; // add line
		String fname = ""; // add line

		request.getSession().setAttribute(id, "/srv" + File.separator + "Document");

		MultipartFile file = mul.getFile(id);
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
					+ "." + extension;
			File serverFile = new File(fname);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();

		} else {
		}
		return fname;

	}
///////////For Doc list

	@PostMapping("/getTypelistFromDoc")

	public @ResponseBody ArrayList<ArrayList<String>> getTypelistFromDoc(String doc_name, HttpSession sessionUserId) {
		
		System.err.println("doc_name------------"+doc_name);

		return tdao.getTypelistFromDocDataList(doc_name);

	}

	@PostMapping("/getSubjectList")

	public @ResponseBody ArrayList<ArrayList<String>> getSubjectList(String course) {

		return tdao.getSubFromCourse(course);

	}

	
	
	@PostMapping("/getcoursebytypeOfDegreeList_ctrl")
	public @ResponseBody ArrayList<ArrayList<String>> getcoursebytypeOfDegreeList_ctrl(String typeofdegree) {

		return tdao.getcoursebytypeOfDegreeList(typeofdegree);

	}
	
	
///////////////Search


	@GetMapping(value = "/Search_teacher_detail_url")
	public ModelAndView Search_teacher_detail_url(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

				if(request.getHeader("Referer") == null ) { 
				//session.invalidate();
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/login");
				}
				String roleid1 = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("Search_teacher_detail_url", roleid1);		
				if(val == false) {
				return new ModelAndView("AccessTiles");
				}
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String role = session.getAttribute("role").toString();
		System.out.println("role "+role);
		if(role.equals("University_NCH")) {
			model.put("logininfo", common.getAllInfoLogin(sessionFactory, userid).get(0).getUniversity_id());
		}else if(role.equals("Institute_NCH")) {
			model.put("logininfo", common.getAllInfoLogin(sessionFactory, userid).get(0).getInstitute_id());
			}
		model.put("msg", msg);
		model.put("getgenderList", common.getgenderList(sessionFactory));
		model.put("getUniverCityList", tdao.getUniversitylist());
		model.put("getinstitutelist", tdao.getinstitutelist(userid));
		model.put("getMedStateName", common.getMedStateName(sessionFactory));
		model.put("getMedDistrictName", common.getMedDistrictName(sessionFactory));
		model.put("getInsttituteList", cdao.getinstituteNchlist());
		model.put("getUniList", common.getUniverCityList(sessionFactory));
		return new ModelAndView("Search_Teacher_dtlTiles");

	}
	@PostMapping("/getFilterTeacher_dtl_data")
	public @ResponseBody List<Map<String, Object>> getFilterTeacher_dtl_data(HttpSession sessionUserId, int startPage,
			int pageLength, String Search, String orderColunm, String orderType, String ayush_id, String teacher_code,
			String first_name, String gender, String date_of_birth, String per_state, String per_district,
			String per_village, String state_reg_no, String state_board_name, String date_of_reg, String central_reg_no,
			String yr_of_exp ,String institute,String university,String status) {
		
		String role = sessionUserId.getAttribute("role").toString();
		String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
		return tdao.DataTableTeacher_dtlDataList(startPage, pageLength, Search, orderColunm, orderType, ayush_id,
				teacher_code, first_name, gender, date_of_birth, per_state, per_district, per_village, state_reg_no,
				state_board_name, date_of_reg, central_reg_no, role, userid, yr_of_exp,institute,university,status);

	}

	@PostMapping("/getTotalTeacher_dtl_dataCount")
	public @ResponseBody long getTotalTeacher_dtl_dataCount(HttpSession sessionUserId, String Search, String ayush_id,
			String teacher_code, String first_name, String gender, String date_of_birth, String per_state,
			String per_district, String per_village, String state_reg_no, String state_board_name, String date_of_reg,
			String central_reg_no, String yr_of_exp,String institute,String university,String status) {
		String role = sessionUserId.getAttribute("role").toString();
		String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
		return tdao.DataTableTeacher_dtlDataTotalCount(Search, ayush_id, teacher_code, first_name, gender,
				date_of_birth, per_state, per_district, per_village, state_reg_no, state_board_name, date_of_reg,
				central_reg_no, role, userid, yr_of_exp,institute,university,status);
	}

//////////////////edit_url

	@RequestMapping(value = "/Edit_Teacher_dtlUrl", method = RequestMethod.POST)
	public ModelAndView Edit_Teacher_dtlUrl(@ModelAttribute("id2") String id2, ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Session sessionHQL = this.sessionFactory.openSession();
		EDU_LMS_TEACHER_DTL updateid = (EDU_LMS_TEACHER_DTL) sessionHQL.get(EDU_LMS_TEACHER_DTL.class,
				Integer.parseInt(id2));

		ArrayList<ArrayList<String>> list1 = tdao.getaddmoredata1(Integer.parseInt(id2));
		ArrayList<ArrayList<String>> list2 = tdao.getaddmoredata2(Integer.parseInt(id2));
		ArrayList<ArrayList<String>> list3 = tdao.getaddmoredata3(Integer.parseInt(id2));

		Mmap.put("edit_tea_dtl_CMD", updateid);
		Mmap.put("edit_expchild", list1);
		Mmap.put("edit_expchild_2", list2);
		Mmap.put("edit_expchild_3", list3);
//	Mmap.put("edit_degree_child", dsd);

		Mmap.put("getgenderList", common.getgenderList(sessionFactory));
		Mmap.put("getDocList", common.getDocList(sessionFactory));
		Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
		Mmap.put("getMedDistrictName", common.getMedDistrictName(sessionFactory));
		Mmap.put("TypeOfDegree", common.gettype_of_degree(sessionFactory));
		Mmap.put("getDegreeList", common.getDegreeList(sessionFactory));
		Mmap.addAttribute("msg", msg);
		return new ModelAndView("Edit_Teacher_dtlTiles");
	}

//////////////////editAction

	@PostMapping(value = "/edit_tea_dtlAction")
	public ModelAndView edit_tea_dtlAction(@Validated @ModelAttribute("edit_tea_dtl_CMD") EDU_LMS_TEACHER_DTL x,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra, MultipartHttpServletRequest mul) throws IOException, ParseException {
		
		
		if(request.getHeader("Referer") == null ) { 
			//session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/login");
			}
			String roleid1 = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Teacher_dtl_Url", roleid1);		
			if(val == false) {
			return new ModelAndView("AccessTiles");
			}

		int id = x.getId() > 0 ? x.getId() : 0;
		Date date = new Date();
		String username = principal.getName();
		String userid = session.getAttribute("userId_for_jnlp").toString();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
//	DateFormat formatter1 = new SimpleDateFormat("DD/MM/YYYY");
		DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		try {

			String first_name = request.getParameter("first_name");
			String cand_prefix = request.getParameter("cand_prefix");
			String middle_name = request.getParameter("middle_name");
			String last_name = request.getParameter("last_name");
			String gender = request.getParameter("gender");

			String date_of_birth = request.getParameter("date_of_birth");

			String father_name = request.getParameter("father_name");
			String mother_name = request.getParameter("mother_name");
			String spouse_name = request.getParameter("spouse_name");
			String mobile_no = request.getParameter("mobile_no");
			String email = request.getParameter("email");

			String aadhar_no1 = request.getParameter("aadhar_no1");
			String aadhar_no2 = request.getParameter("aadhar_no2");
			String aadhar_no3 = request.getParameter("aadhar_no3");
			String aadhar_no = aadhar_no1 + aadhar_no2 + aadhar_no3;

			String pan_no = request.getParameter("pan_no");
			String present_add_line1 = request.getParameter("present_add_line1");
			String present_add_line2 = request.getParameter("present_add_line2");
			String present_village = request.getParameter("present_village");
			String present_state = request.getParameter("present_state");
			String present_district = request.getParameter("present_district");
			String present_pincode = request.getParameter("present_pincode");
			String present_phn_no = request.getParameter("present_phn_no");

			String per_add_line1 = request.getParameter("per_add_line1");
			String per_add_line2 = request.getParameter("per_add_line2");
			String per_village = request.getParameter("per_village");
			String per_state = request.getParameter("per_state");
			String per_district = request.getParameter("per_district");
			String per_pincode = request.getParameter("per_pincode");
			String per_phn_no = request.getParameter("per_phn_no");

			String state_reg_no = request.getParameter("state_reg_no");
			String state_board_name = request.getParameter("state_board_name");

			String date_of_reg = request.getParameter("date_of_reg");
			String p_id = request.getParameter("id");
			String central_reg_no = request.getParameter("central_reg_no");
			String academic_quali = request.getParameter("academic_quali");
			String subject = request.getParameter("subject");

			String sub_quali_degree = request.getParameter("sub_quali_degree");
			String sub_deg_course = request.getParameter("sub_deg_course");

			if (cand_prefix == null || cand_prefix.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Candidate Prifix");
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (first_name == null || first_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter First Name.");
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.maxlengthcheck50(first_name) == false) {
				ra.addAttribute("msg", "First Name " + validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.isOnlyAlphabet(first_name) == false) {
				ra.addAttribute("msg", "First Name " + validation.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}

//			if (middle_name == null || middle_name.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter Middle Name.");
//				return new ModelAndView("redirect:Search_teacher_detail_url");
//			}
			if (validation.maxlengthcheck50(middle_name) == false) {
				ra.addAttribute("msg", "Middle Name " + validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.isOnlyAlphabet(middle_name) == false) {
				ra.addAttribute("msg", "Middle Name " + validation.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}

			if (last_name == null || last_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Last Name.");
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.maxlengthcheck50(last_name) == false) {
				ra.addAttribute("msg", "Last Name " + validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.isOnlyAlphabet(last_name) == false) {
				ra.addAttribute("msg", "Last Name " + validation.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}

			if (gender == null || gender.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Gender");
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}

			if (date_of_birth == null || date_of_birth.trim().equals("") || date_of_birth.equals("DD/MM/YYYY")) {
				ra.addAttribute("msg", "Please Enter The Date Of Birth");
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}

			if (father_name == null || father_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Father's Name.");
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.maxlengthcheck50(father_name) == false) {
				ra.addAttribute("msg", "Father's Name " + validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.isOnlyAlphabet(father_name) == false) {
				ra.addAttribute("msg", "Father's Name " + validation.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}

			if (mother_name == null || mother_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Mother's Name.");
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.maxlengthcheck50(mother_name) == false) {
				ra.addAttribute("msg", "Mother's Name " + validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.isOnlyAlphabet(mother_name) == false) {
				ra.addAttribute("msg", "Mother's Name " + validation.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}

			if (spouse_name == null || spouse_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Spouse's Name.");
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.maxlengthcheck50(spouse_name) == false) {
				ra.addAttribute("msg", "Spouse's Name " + validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.isOnlyAlphabet(spouse_name) == false) {
				ra.addAttribute("msg", "Spouse's Name " + validation.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}

			if (mobile_no == null || mobile_no.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Mobile Number");
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.isOnlyNumer(mobile_no) == true) {
				ra.addAttribute("msg", "Mobile Number " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.maxlengthcheck10(mobile_no) == false) {
				ra.addAttribute("msg", "Mobile Number " + validation.MaxlengthcheckMSG10);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.isValidMobileNo(mobile_no) == false) {
				ra.addAttribute("msg", "Mobile Number " + validation.isOnlyMobilenumberMSG);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}

			if (email == null || email.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Email Address");
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.maxlengthcheck70(email) == false) {
				ra.addAttribute("msg", "Email Address " + validation.MaxlengthcheckMSG70);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}

			if (aadhar_no1 == null || aadhar_no1.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Aadhaar No.");
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.isOnlyNumer(aadhar_no1) == true) {
				ra.addAttribute("msg", "Aadhaar No. " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.AadharNoLength(aadhar_no1) == false) {
				ra.addAttribute("msg", validation.AadharNoMSG11);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.AadharNoMinLength(aadhar_no1) == false) {
				ra.addAttribute("msg", validation.AadharNoMSGMinlen11);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}

			if (aadhar_no2 == null || aadhar_no2.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Aadhaar No.");
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.isOnlyNumer(aadhar_no2) == true) {
				ra.addAttribute("msg", "Aadhaar No. " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.AadharNoLength(aadhar_no2) == false) {
				ra.addAttribute("msg", validation.AadharNoMSG11);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.AadharNoMinLength(aadhar_no2) == false) {
				ra.addAttribute("msg", validation.AadharNoMSGMinlen11);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}

			if (aadhar_no3 == null || aadhar_no3.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Aadhaar No.");
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.isOnlyNumer(aadhar_no3) == true) {
				ra.addAttribute("msg", "Aadhaar No. " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.AadharNoLength(aadhar_no3) == false) {
				ra.addAttribute("msg", validation.AadharNoMSG11);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.AadharNoMinLength(aadhar_no3) == false) {
				ra.addAttribute("msg", validation.AadharNoMSGMinlen11);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}

			if (pan_no == null || pan_no.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter PAN No.");
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.maxlength10(pan_no) == false) {
				ra.addAttribute("msg", "PAN No. " + validation.MaxlengthMSG10);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.minlength10(pan_no) == false) {
				ra.addAttribute("msg", "PAN No. " + validation.MinlengthMSG10);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.isOnlyAlphabetNumeric(pan_no) == false) {
				ra.addAttribute("msg", "PAN No. " + validation.isOnlyAlphabetNumericMSG);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}

//			if (academic_quali == null || academic_quali.trim().equals("0")) {
//				ra.addAttribute("msg", "Please Select Academic Qualification");
//				return new ModelAndView("redirect:Search_teacher_detail_url");
//			}
			// if(request.getParameter("sex") == null ||
			// request.getParameter("sex").trim().equals("2")){
//			if (academic_quali == null || academic_quali.trim().equals("2")) {
//				if (subject == null || subject.trim().equals("")) {
//					ra.addAttribute("msg", "Please Enter Subject");
//					return new ModelAndView("redirect:Search_teacher_detail_url");
//				}
//				if (validation.maxlengthcheck50(subject) == false) {
//					ra.addAttribute("msg", "Subject " + validation.MaxlengthcheckMSG50);
//					return new ModelAndView("redirect:Search_teacher_detail_url");
//				}
//				if (validation.isOnlyAlphabetNumeric(subject) == false) {
//					ra.addAttribute("msg", "Subject " + validation.isOnlyAlphabetNumericMSG);
//					return new ModelAndView("redirect:Search_teacher_detail_url");
//				}
//			}
///////////Address
			if (per_add_line1 == null || per_add_line1.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Permanent Address-Line-1");
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.maxlengthcheck100(per_add_line1) == false) {
				ra.addAttribute("msg", "Permanent Address-Line-1 " + validation.MaxlengthcheckMSG100);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}

			if (per_add_line2 == null || per_add_line2.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Permanent Address-Line-2");
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.maxlengthcheck100(per_add_line2) == false) {
				ra.addAttribute("msg", "Permanent Address-Line-2 " + validation.MaxlengthcheckMSG100);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}

			if (per_state == null || per_state.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select State");
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (per_district == null || per_district.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select District");
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}

			if (per_village == null || per_village.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Permanent City/Village");
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.maxlengthcheck100(per_village) == false) {
				ra.addAttribute("msg", "Permanent City/Village " + validation.MaxlengthcheckMSG100);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.isOnlyAlphabet(per_village) == false) {
				ra.addAttribute("msg", "Permanent City/Village " + validation.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}

			if (per_pincode == null || per_pincode.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Permanent Pin Code");
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.maxlengthcheckpincode(per_pincode) == false) {
				ra.addAttribute("msg", "Permanent Pin Code " + validation.MaxlengthcheckMSGpincode);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.minlengthcheckpincode(per_pincode) == false) {
				ra.addAttribute("msg", "Permanent Pin Code " + validation.MinlengthcheckMSGpincode);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.isOnlyNumer(per_pincode) == true) {
				ra.addAttribute("msg", "Permanent Pin Code " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}

			if (per_phn_no == null || per_phn_no.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Permanent Phone No.");
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.isOnlyNumer(per_phn_no) == true) {
				ra.addAttribute("msg", "Permanent Phone No. " + validation.isOnlyNumerMSG);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.maxlengthcheck10(per_phn_no) == false) {
				ra.addAttribute("msg", "Permanent Phone No. " + validation.MaxlengthcheckMSG10);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.isValidMobileNo(per_phn_no) == false) {
				ra.addAttribute("msg", "Permanent Phone No. " + validation.isOnlyMobilenumberMSG);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}

			if (request.getParameter("check_address") == null) {

				if (present_add_line1 == null || present_add_line1.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Present Address-Line-1");
					return new ModelAndView("redirect:Search_teacher_detail_url");
				}
				if (validation.maxlengthcheck100(present_add_line1) == false) {
					ra.addAttribute("msg", "Present Address-Line-1 " + validation.MaxlengthcheckMSG100);
					return new ModelAndView("redirect:Search_teacher_detail_url");
				}

				if (present_add_line2 == null || present_add_line2.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Present Address-Line-2");
					return new ModelAndView("redirect:Search_teacher_detail_url");
				}
				if (validation.maxlengthcheck100(present_add_line2) == false) {
					ra.addAttribute("msg", "Present Address-Line-2 " + validation.MaxlengthcheckMSG100);
					return new ModelAndView("redirect:Search_teacher_detail_url");
				}

				if (present_state == null || present_state.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select State");
					return new ModelAndView("redirect:Search_teacher_detail_url");
				}
				if (present_district == null || present_district.trim().equals("0")) {
					ra.addAttribute("msg", "Please Select District");
					return new ModelAndView("redirect:Search_teacher_detail_url");
				}

				if (present_village == null || present_village.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Present City/Village");
					return new ModelAndView("redirect:Search_teacher_detail_url");
				}
				if (validation.maxlengthcheck100(present_village) == false) {
					ra.addAttribute("msg", "Present City/Village " + validation.MaxlengthcheckMSG100);
					return new ModelAndView("redirect:Search_teacher_detail_url");
				}
				if (validation.isOnlyAlphabet(present_village) == false) {
					ra.addAttribute("msg", "Present City/Village " + validation.isOnlyAlphabetMSG);
					return new ModelAndView("redirect:Search_teacher_detail_url");
				}

				if (present_pincode == null || present_pincode.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Present Pin Code");
					return new ModelAndView("redirect:Search_teacher_detail_url");
				}
				if (validation.maxlengthcheckpincode(present_pincode) == false) {
					ra.addAttribute("msg", "Present Pin Code " + validation.MaxlengthcheckMSGpincode);
					return new ModelAndView("redirect:Search_teacher_detail_url");
				}
				if (validation.minlengthcheckpincode(present_pincode) == false) {
					ra.addAttribute("msg", "Present Pin Code " + validation.MinlengthcheckMSGpincode);
					return new ModelAndView("redirect:Search_teacher_detail_url");
				}
				if (validation.isOnlyNumer(present_pincode) == true) {
					ra.addAttribute("msg", "Present Pin Code " + validation.isOnlyNumerMSG);
					return new ModelAndView("redirect:Search_teacher_detail_url");
				}

				if (present_phn_no == null || present_phn_no.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Present Phone No.");
					return new ModelAndView("redirect:Search_teacher_detail_url");
				}
				if (validation.isOnlyNumer(present_phn_no) == true) {
					ra.addAttribute("msg", "Present Phone No. " + validation.isOnlyNumerMSG);
					return new ModelAndView("redirect:Search_teacher_detail_url");
				}
				if (validation.maxlengthcheck10(present_phn_no) == false) {
					ra.addAttribute("msg", "Present Phone No. " + validation.MaxlengthcheckMSG10);
					return new ModelAndView("redirect:Search_teacher_detail_url");
				}
				if (validation.isValidMobileNo(present_phn_no) == false) {
					ra.addAttribute("msg", "Present Phone No. " + validation.isOnlyMobilenumberMSG);
					return new ModelAndView("redirect:Search_teacher_detail_url");
				}
			}

////////Registration Details	

			if (state_reg_no == null || state_reg_no.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter State Registration No.");
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.maxlengthcheck50(state_reg_no) == false) {
				ra.addAttribute("msg", "State Registration No. " + validation.MaxlengthcheckMSG50);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
//			if (validation.isOnlyAlphabetNumeric(state_reg_no) == false) {
//				ra.addAttribute("msg", "State Registration No. " + validation.isOnlyAlphabetNumericMSG);
//				return new ModelAndView("redirect:Search_teacher_detail_url");
//			}

			if (state_board_name == null || state_board_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Name of the State Board");
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.maxlengthcheck100(state_board_name) == false) {
				ra.addAttribute("msg", "Name of the State Board " + validation.MaxlengthcheckMSG100);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}
			if (validation.isOnlyAlphabet(state_board_name) == false) {
				ra.addAttribute("msg", "Name of the State Board " + validation.isOnlyAlphabetMSG);
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}

			if (date_of_reg == null || date_of_reg.trim().equals("") || date_of_reg.equals("DD/MM/YYYY")) {
				ra.addAttribute("msg", "Please Enter The Date Of Registration");
				return new ModelAndView("redirect:Search_teacher_detail_url");
			}

//			if (central_reg_no == null || central_reg_no.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter Central Registration No.");
//				return new ModelAndView("redirect:Search_teacher_detail_url");
//			}
//			if (validation.maxlengthcheck50(central_reg_no) == false) {
//				ra.addAttribute("msg", "Central Registration No. " + validation.MaxlengthcheckMSG50);
//				return new ModelAndView("redirect:Search_teacher_detail_url");
//			}
//			if (validation.isOnlyAlphabetNumeric(central_reg_no) == false) {
//				ra.addAttribute("msg", "Central Registration No. " + validation.isOnlyAlphabetNumericMSG);
//				return new ModelAndView("redirect:Search_teacher_detail_url");
//			}

			ArrayList<ArrayList<String>> child_id_tech = tdao.getchild_idByp_id(Integer.parseInt(p_id));
			ArrayList<ArrayList<String>> child_id_deg = tdao.getchild_idByp_idDegree(Integer.parseInt(p_id));

			EDU_LMS_TEACHER_DTL rd = (EDU_LMS_TEACHER_DTL) sessionHQL.get(EDU_LMS_TEACHER_DTL.class, id);

			rd.setFirst_name(first_name);
			rd.setCand_prefix(cand_prefix);
			rd.setMiddle_name(middle_name);
			rd.setLast_name(last_name);
			rd.setGender(gender);
			rd.setDate_of_birth(datePickerFormat.parse(date_of_birth));
			// rd.setDate_of_birth(formatter1.parse(dob1));
			rd.setFather_name(father_name);
			rd.setMother_name(mother_name);
			rd.setSpouse_name(spouse_name);
			rd.setMobile_no(mobile_no);
			rd.setEmail(email);
			rd.setAadhar_no(aadhar_no);
			rd.setPan_no(pan_no);

			rd.setPresent_add_line1(present_add_line1);
			rd.setPresent_add_line2(present_add_line2);
			rd.setPresent_village(present_village);
			rd.setPresent_state(Integer.parseInt(present_state));
			rd.setPresent_district(Integer.parseInt(present_district));
			rd.setPresent_pincode(Integer.parseInt(present_pincode));
			rd.setPresent_phn_no(present_phn_no);

			rd.setPer_add_line1(per_add_line1);
			rd.setPer_add_line2(per_add_line2);
			rd.setPer_village(per_village);
			rd.setPer_state(Integer.parseInt(per_state));
			rd.setPer_district(Integer.parseInt(per_district));
			rd.setPer_pincode(Integer.parseInt(per_pincode));
			rd.setPer_phn_no(per_phn_no);

			rd.setState_reg_no(state_reg_no);
			rd.setState_board_name(state_board_name);
			rd.setDate_of_reg(datePickerFormat.parse(date_of_reg));
			// rd.setDate_of_reg(formatter1.parse(dt_reg));
			rd.setCentral_reg_no(central_reg_no);
			rd.setAcademic_quali(academic_quali);
			rd.setSubject(subject);

			rd.setSub_quali_degree(Integer.parseInt(sub_quali_degree));
			rd.setSub_deg_course(Integer.parseInt(sub_deg_course));

			rd.setModified_by(username);
			rd.setModified_date(date);

			sessionHQL.update(rd);
			sessionHQL.flush();
			sessionHQL.clear();

			int count_hidden_att = Integer.parseInt(request.getParameter("count_hidden_att"));

			for (int v = 0; v < child_id_tech.size(); v++) {
				int tech_id = Integer.parseInt(child_id_tech.get(0).get(v));
				EDU_LMS_TEACHER_EXPERIENCE_CHILD od1 = (EDU_LMS_TEACHER_EXPERIENCE_CHILD) sessionHQL
						.get(EDU_LMS_TEACHER_EXPERIENCE_CHILD.class, tech_id);
				sessionHQL.delete(od1);
				sessionHQL.flush();
				sessionHQL.clear();
			}

			for (int i = 1; i <= count_hidden_att; i++) {

				EDU_LMS_TEACHER_EXPERIENCE_CHILD od = new EDU_LMS_TEACHER_EXPERIENCE_CHILD();

				String institute_name = request.getParameter("institute_name" + i);
				String depart_name = request.getParameter("depart_name" + i);
				String desig = request.getParameter("desig" + i);
				String from_date = request.getParameter("from_date" + i);
				String to_date = request.getParameter("to_date" + i);
				String upload_file_hid = request.getParameter("upload_file_hid" + i);

				String extension = ""; // add line
				String fname = ""; // add line

				request.getSession().setAttribute("upload_file" + i, "/srv" + File.separator + "Document");

				MultipartFile file = mul.getFile("upload_file" + i);
				if (!file.getOriginalFilename().isEmpty()) {

					byte[] bytes = file.getBytes();
					String mnhFilePath = session.getAttribute("upload_file" + i).toString();

					File dir = new File(mnhFilePath);
					if (!dir.exists())
						dir.mkdirs();
					String filename = file.getOriginalFilename();
					int j = filename.lastIndexOf('.');
					if (j >= 0) {
						extension = filename.substring(j + 1);
					}
					java.util.Date date1 = new java.util.Date();
					fname = dir.getAbsolutePath() + File.separator
							+ (new Timestamp(date1.getTime())).toString().replace(":", "").toString().replace(".", ".")
									.toString().replace(" ", "").toString().replace("-", "").toString()
							+ "." + extension;

					File serverFile = new File(fname);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

					od.setUpload_file(fname);
				} else {
					od.setUpload_file(request.getParameter("upload_file_hid" + i));
				}

				if (institute_name == null || institute_name.trim().equals("")) {
					ra.addAttribute("msg", "Please Enter Institute Name In Row " + i);
					return new ModelAndView("redirect:Search_teacher_detail_url");
				}
				if (validation.maxlengthcheck50(institute_name) == false) {
					ra.addAttribute("msg", "Institute Name " + validation.MaxlengthcheckMSG50);
					return new ModelAndView("redirect:Search_teacher_detail_url");
				}
				if (validation.isOnlyAlphabet(institute_name) == false) {
					ra.addAttribute("msg", "Institute Name " + validation.isOnlyAlphabetMSG);
					return new ModelAndView("redirect:Search_teacher_detail_url");
				}

				if (depart_name == null || depart_name.equals("0")) {
					ra.addAttribute("msg", "Please Select Department Name In Row " + i);
					return new ModelAndView("redirect:Search_teacher_detail_url");
				}
				if (desig == null || desig.equals("0")) {
					ra.addAttribute("msg", "Please Select Designation In Row " + i);
					return new ModelAndView("redirect:Search_teacher_detail_url");
				}

				if (from_date == null || from_date.trim().equals("") || from_date.equals("DD/MM/YYYY")) {
					ra.addAttribute("msg", "Please Enter The From Date In Row " + i);
					return new ModelAndView("redirect:Search_teacher_detail_url");
				}
				if (to_date == null || to_date.trim().equals("") || to_date.equals("DD/MM/YYYY")) {
					ra.addAttribute("msg", "Please Enter The To Date In Row " + i);
					return new ModelAndView("redirect:Search_teacher_detail_url");
				}
				if (datePickerFormat.parse(to_date).compareTo(datePickerFormat.parse(from_date)) < 0) {
					ra.addAttribute("msg", "To Date Should Be Greater Than Or Equal To From Date In Row " + i);
					return new ModelAndView("redirect:Search_teacher_detail_url");
				}
//				if (upload_file_hid.isEmpty()) {
//					ra.addAttribute("msg", "Please Select File In Row " + i);
//					return new ModelAndView("redirect:Search_teacher_detail_url");
//
//				}

				od.setInstitute_name(institute_name);
				od.setDepart_name(depart_name);
				od.setDesig(desig);
				od.setFrom_date(datePickerFormat.parse(from_date));
				od.setTo_date(datePickerFormat.parse(to_date));
				od.setModified_by(username);
				od.setModified_date(date);
				od.setP_id(Integer.parseInt(p_id));

				sessionHQL.save(od);// 1
				sessionHQL.flush();
				sessionHQL.clear();

			}

			int count_hidden_doc = Integer.parseInt(request.getParameter("count_hidden_doc"));

			for (int m = 0; m < child_id_deg.size(); m++) {
				int deg_id = Integer.parseInt(child_id_deg.get(0).get(m));
				EDU_LMS_DEGREE_AND_SUPPORT_DOC_CHILD od2 = (EDU_LMS_DEGREE_AND_SUPPORT_DOC_CHILD) sessionHQL
						.get(EDU_LMS_DEGREE_AND_SUPPORT_DOC_CHILD.class, deg_id);
				sessionHQL.delete(od2);
				sessionHQL.flush();
				sessionHQL.clear();
			}

			for (int i = 1; i <= count_hidden_doc; i++) {

				EDU_LMS_DEGREE_AND_SUPPORT_DOC_CHILD ds = new EDU_LMS_DEGREE_AND_SUPPORT_DOC_CHILD();

				String doc_name = request.getParameter("doc_name" + i);
				String doc_id = request.getParameter("doc_id" + i);
				String doc_upload_hid = request.getParameter("doc_upload_hid" + i);
//			String du = "upload_doc"+i;
//			String doc1 = gd(request, mul, session,du);

				String extension = ""; // add line
				String fname = ""; // add line

				request.getSession().setAttribute("upload_doc" + i, "/srv" + File.separator + "Document");

				MultipartFile file = mul.getFile("upload_doc" + i);
				if (!file.getOriginalFilename().isEmpty()) {

					byte[] bytes = file.getBytes();
					String mnhFilePath = session.getAttribute("upload_doc" + i).toString();

					File dir = new File(mnhFilePath);
					if (!dir.exists())
						dir.mkdirs();
					String filename = file.getOriginalFilename();
					int j = filename.lastIndexOf('.');
					if (j >= 0) {
						extension = filename.substring(j + 1);
					}
					java.util.Date date1 = new java.util.Date();
					fname = dir.getAbsolutePath() + File.separator
							+ (new Timestamp(date1.getTime())).toString().replace(":", "").toString().replace(".", ".")
									.toString().replace(" ", "").toString().replace("-", "").toString()
							+ "." + extension;

					File serverFile = new File(fname);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

					ds.setUpload_doc(fname);
				} else {
					ds.setUpload_doc(request.getParameter("doc_upload_hid" + i));
				}

				if (doc_name == null || doc_name.equals("0")) {
					ra.addAttribute("msg", "Please Select Document Name In Row " + i);
					return new ModelAndView("redirect:Search_teacher_detail_url");
				}
				if (doc_id == null || doc_id.equals("0")) {
					ra.addAttribute("msg", "Please Select Document Type In Row " + i);
					return new ModelAndView("redirect:Search_teacher_detail_url");
				}


				ds.setDoc_name(doc_name);
				ds.setDoc_id(Integer.parseInt(doc_id));
				ds.setModified_by(username);
				ds.setModified_date(date);
				ds.setP_id(Integer.parseInt(p_id));

				sessionHQL.save(ds);// 2
				sessionHQL.flush();
				sessionHQL.clear();
			}

			tx.commit();
			ra.addAttribute("msg", "Data Updated Successfully.");

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
		return new ModelAndView("redirect:Search_teacher_detail_url");
	}

//////////////////View_url

	@RequestMapping(value = "/View_Teacher_dtlUrl", method = RequestMethod.POST)
	public ModelAndView View_Teacher_dtlUrl(@ModelAttribute("id6") String id, ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

		Session sessionHQL = this.sessionFactory.openSession();
		Mmap.put("id6", id);
		
		System.err.println("22/2/23----------------id------>"+id);

		EDU_LMS_TEACHER_DTL viewid = (EDU_LMS_TEACHER_DTL) sessionHQL.get(EDU_LMS_TEACHER_DTL.class,
				Integer.parseInt(id));
		
		String userid = session.getAttribute("userId_for_jnlp").toString();

		ArrayList<ArrayList<String>> tea_exp_chlist1 = tdao.getaddmoredata1(Integer.parseInt(id));
		ArrayList<ArrayList<String>> deg_doc_chlist2 = tdao.View_getaddmoredata2(Integer.parseInt(id));
		ArrayList<ArrayList<String>> quali_chlist2 = tdao.getaddmoredata3(Integer.parseInt(id));
		ArrayList<ArrayList<String>> quali_chlist3 = tdao.getaddmoredata5(Integer.parseInt(id));
		ArrayList<ArrayList<String>> otherquali_chlist2 = tdao.getotherquali_chlist(Integer.parseInt(id));/// Riddhi_22_7_22
		ArrayList<ArrayList<String>> otherquali_chlist3 = tdao.getaddmoredataforview1(Integer.parseInt(id));

		Mmap.put("View_tea_dtlCMD", viewid);
		Mmap.put("View_expchild", tea_exp_chlist1);
		Mmap.put("View_expchild_2", deg_doc_chlist2);
		Mmap.put("View_expchild_3", quali_chlist2);
		Mmap.put("View_expchild_4", quali_chlist3);
		Mmap.put("View_otherexpchild_3", otherquali_chlist2);/// Riddhi_22_7_22
		Mmap.put("View_otherexpchild_4", otherquali_chlist3);/// Riddhi_22_7_22

		Mmap.put("getgenderList", common.getgenderList(sessionFactory));
		Mmap.put("getDocList", common.getDocList(sessionFactory));
		Mmap.put("getMedStateName", common.getMedStateName(sessionFactory));
		Mmap.put("getMedDistrictName", common.getMedDistrictName(sessionFactory));
		Mmap.put("TypeOfDegree", common.gettype_of_degree(sessionFactory));
		Mmap.put("getDegreeList", common.getDegreeList(sessionFactory));
		//Mmap.put("getregView", tdao.getregistrationViewdata());
		
		Mmap.put("data", tdao.getAllPersdetails(Integer.parseInt(userid)));
		
		
//		int main_id = Integer
//				.parseInt(tdao.getAllPersdetails(Integer.parseInt(userid)).get(0).get("mainid").toString());
//		
//		System.err.println("22/2/23--------------main--id------>"+main_id);
		Mmap.put("Type_of_exp_acadView", tdao.getacademicexpView(Integer.parseInt(id)));
		Mmap.put("getregView", tdao.getregistrationViewdata(Integer.parseInt(id)));
		
		
		Mmap.addAttribute("msg", msg);
		return new ModelAndView("View_Teacher_dtlTiles");
	}

//////////////////view_pdf

	@SuppressWarnings("null")
	@RequestMapping(value = "/getDownloadUrlforexp_child")
	public ModelAndView getDownloadUrlforexp_child(@RequestParam(value = "msg", required = false) String msg,
			String pageUrl, String upload_file2, ModelMap model, HttpServletRequest request, HttpSession session,
			HttpServletResponse response) throws IOException {

		String url = pageUrl;
		String EXTERNAL_FILE_PATH = "";
		String enckey = "commonPwdEncKeys";
		EXTERNAL_FILE_PATH = tdao.getFilePathQueryForUpload_file(upload_file2);
//	model.put("downloadid", downloadid);
		model.put("msg", "Sorry ! The file you are looking for does not exist.");

		if (EXTERNAL_FILE_PATH != "") {
			File file = null;
			file = new File(EXTERNAL_FILE_PATH);
			try {
				if (!file.exists()) {

					return new ModelAndView(url);
				}
			} catch (Exception exception) {
			}

			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
			response.setContentLength((int) file.length());
			InputStream inputStream = null;
			try {
				inputStream = new BufferedInputStream(new FileInputStream(file));
				FileCopyUtils.copy(inputStream, response.getOutputStream());
			} catch (FileNotFoundException e) {
				// e.printStackTrace();
			}
		}
		return new ModelAndView(url);
	}

	@SuppressWarnings("null")
	@RequestMapping(value = "/getDownloadUrlforDoc")
	public ModelAndView getDownloadUrlforDoc(@RequestParam(value = "msg", required = false) String msg, String pageUrl,
			String upload_doc2, ModelMap model, HttpServletRequest request, HttpSession session,
			HttpServletResponse response) throws IOException {

		String url = pageUrl;
		String EXTERNAL_FILE_PATH = "";
		String enckey = "commonPwdEncKeys";
		EXTERNAL_FILE_PATH = tdao.getFilePathQueryForDoc(upload_doc2);
//	model.put("downloadid", downloadid);
		model.put("msg", "Sorry ! The file you are looking for does not exist.");

		if (EXTERNAL_FILE_PATH != "") {
			File file = null;
			file = new File(EXTERNAL_FILE_PATH);
			try {
				if (!file.exists()) {

					return new ModelAndView(url);
				}
			} catch (Exception exception) {
			}

			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
			response.setContentLength((int) file.length());
			InputStream inputStream = null;
			try {
				inputStream = new BufferedInputStream(new FileInputStream(file));
				FileCopyUtils.copy(inputStream, response.getOutputStream());
			} catch (FileNotFoundException e) {
				// e.printStackTrace();
			}
		}
		return new ModelAndView(url);
	}

////////////////delete

	@RequestMapping(value = "/delete_Teacher_dtl_Url", method = RequestMethod.POST)
	public @ResponseBody ModelAndView delete_Teacher_dtl_Url(@ModelAttribute("id1") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg) {
		List<String> liststr = new ArrayList<String>();

		try {
			Session sessionHQL = sessionFactory.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();

			String hqlUpdate1 = "delete from EDU_LMS_TEACHER_EXPERIENCE_CHILD where p_id=:id";
			int app1 = sessionHQL.createQuery(hqlUpdate1).setParameter("id", id).executeUpdate();

			String hqlUpdate2 = "delete from EDU_LMS_DEGREE_AND_SUPPORT_DOC_CHILD where p_id=:id";
			int app2 = sessionHQL.createQuery(hqlUpdate2).setParameter("id", id).executeUpdate();

			String hqlUpdate = "delete from EDU_LMS_TEACHER_DTL where id=:id";
			int app = sessionHQL.createQuery(hqlUpdate).setParameter("id", id).executeUpdate();
			tx.commit();
			sessionHQL.close();

			if (app > 0) {
				liststr.add("Delete Successfully.");
			} else {
				liststr.add("Delete UNSuccessfully.");
			}
			model.put("msg", liststr.get(0));

		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			model.put("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:Search_teacher_detail_url");
	}
	
	
	
	@PostMapping(value = "/delete_quali_addmore")
	public ModelAndView delete_quali_addmore(@ModelAttribute("qualtification_idhid2") int parent_id, BindingResult result,
			RedirectAttributes ra, HttpServletRequest request, ModelMap model, HttpSession session1) {
 		List<String> liststr = new ArrayList<String>();
 		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
 		String username = session1.getAttribute("username").toString();
		try {

			int app = session.createQuery("Delete from EDU_LMS_TEACHER_QUALIFICATION_ATTACHMENT_SUBCHILD where parent_id=:parent_id")
					.setParameter("parent_id", parent_id).executeUpdate();
			
			System.err.println("delete add more child of child"+app);
			 
			int app2 = session.createQuery("Delete from EDU_LMS_TEACHER_QUALIFICATION_CHILD where id=:parent_id")
					.setParameter("parent_id", parent_id).executeUpdate();
			
			System.err.println("delete add more child "+app2);
			 
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
		return new ModelAndView("redirect:Teacher_dtl_Url");
	}
	
	
	@PostMapping(value = "/delete_profession_addmore")
	public ModelAndView delete_profession_addmore(@ModelAttribute("profession_id") int parent_id, BindingResult result,
			RedirectAttributes ra, HttpServletRequest request, ModelMap model, HttpSession session1) {
 		List<String> liststr = new ArrayList<String>();
 		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
 		String username = session1.getAttribute("username").toString();
 		System.out.println("parent_id "+parent_id);
		try {
 
			int app = session.createQuery("Delete from EDU_LMS_TEACHER_EXPERIENCE_CHILD where id=:id")
					.setParameter("id", parent_id).executeUpdate();
			 
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
		return new ModelAndView("redirect:Teacher_dtl_Url");
	}
	
	
	
	@PostMapping(value = "/delete_academic_addmore")
	public ModelAndView delete_academic_addmore(@ModelAttribute("academic_id") int parent_id, BindingResult result,
			RedirectAttributes ra, HttpServletRequest request, ModelMap model, HttpSession session1) {
 		List<String> liststr = new ArrayList<String>();
 		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
 		String username = session1.getAttribute("username").toString();
 		System.out.println("parent_id-----28/02/23-- "+parent_id);
		try {
 
			int app = session.createQuery("Delete from EDU_LMS_TEACHER_ACADEMIC_EXPERIENCE_CHILD where id=:id")
					.setParameter("id", parent_id).executeUpdate();
			 
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
		return new ModelAndView("redirect:Teacher_dtl_Url");
	}
	
	
	
	
	@PostMapping(value = "/delete_adddoc_addmore")
	public ModelAndView delete_adddoc_addmore(@ModelAttribute("add_quali") int parent_id, BindingResult result,
			RedirectAttributes ra, HttpServletRequest request, ModelMap model, HttpSession session1) {
 		List<String> liststr = new ArrayList<String>();
 		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
 		String username = session1.getAttribute("username").toString();
 		System.out.println("parent_id "+parent_id);
 		System.out.println("parent_id-----28/02/23- add doc- "+parent_id);
		try {
 
			int app = session.createQuery("Delete from EDU_LMS_DEGREE_AND_SUPPORT_DOC_CHILD where id=:id")
					.setParameter("id", parent_id).executeUpdate();
			 
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
		return new ModelAndView("redirect:Teacher_dtl_Url");
	}
	
	@PostMapping(value = "/delete_otherquali_addmore")
	public ModelAndView delete_otherquali_addmore(@ModelAttribute("othquali_id") int parent_id, BindingResult result,
			RedirectAttributes ra, HttpServletRequest request, ModelMap model, HttpSession session1) {
 		List<String> liststr = new ArrayList<String>();
 		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
 		String username = session1.getAttribute("username").toString();
 		System.out.println("parent_id "+parent_id);
		try {
 
			int app = session.createQuery("Delete from EDU_LMS_TEACHER_OTHER_QUALIFICATION_CHILD where p_id=:p_id")
					.setParameter("p_id", parent_id).executeUpdate();
			 
			int app2 = session.createQuery("Delete from EDU_LMS_TEACHER_OTHER_QUALIFICATION_CHILD where id=:p_id")
					.setParameter("p_id", parent_id).executeUpdate();
			 
			 
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
		return new ModelAndView("redirect:Teacher_dtl_Url");
	}
	
	
	@PostMapping(value = "/delete_qualiattch_addmore")
	public ModelAndView delete_qualiattch_addmore(@ModelAttribute("qua_att") int parent_id, BindingResult result,
			RedirectAttributes ra, HttpServletRequest request, ModelMap model, HttpSession session1) {
 		List<String> liststr = new ArrayList<String>();
 		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
 		String username = session1.getAttribute("username").toString();
 		System.out.println("parent_id "+parent_id);
		try {
 
			int app = session.createQuery("Delete from EDU_LMS_TEACHER_QUALIFICATION_ATTACHMENT_SUBCHILD where id=:id")
					.setParameter("id", parent_id).executeUpdate();
			 
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
		return new ModelAndView("redirect:Teacher_dtl_Url");
	}
	
	
	@PostMapping(value = "/delete_otherqualiatt_addmore")
	public ModelAndView delete_otherqualiatt_addmore(@ModelAttribute("othqualiatt_id") int parent_id, BindingResult result,
			RedirectAttributes ra, HttpServletRequest request, ModelMap model, HttpSession session1) {
 		List<String> liststr = new ArrayList<String>();
 		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
 		String username = session1.getAttribute("username").toString();
 		System.out.println("parent_id "+parent_id);
		try {
 
			int app = session.createQuery("Delete from EDU_LMS_TEACHER_OTHER_QUALIFICATION_SUB_CHILD where id=:id")
					.setParameter("id", parent_id).executeUpdate();
			 
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
		return new ModelAndView("redirect:Teacher_dtl_Url");
	}
	
	
//	@RequestMapping(value = "/delete_quali_addmore", method = RequestMethod.POST)
//	public @ResponseBody   ArrayList<String> delete_quali_addmore(String qualtification_idhid2){
//		
//		System.err.println("21/09"+qualtification_idhid2);
//
//		//ArrayList<ArrayList<String>> list = cdao.getUniversitybyinstitutelist(institute_id);
//		ArrayList<String> liststr = new ArrayList<String>();
//		try {
//			Session session = this.sessionFactory.openSession();
//			Transaction tx = session.beginTransaction();
//			
//
//			int app = session.createQuery("Delete from EDU_LMS_TEACHER_QUALIFICATION_ATTACHMENT_SUBCHILD where parent_id=:parent_id")
//					.setParameter("parent_id", qualtification_idhid2).executeUpdate();
//			
//			System.err.println("hiiiiiiiiiiiiiiiiiii"+app);
//			
//
//			int app2 = session.createQuery("Delete from EDU_LMS_TEACHER_QUALIFICATION_CHILD where id=:parent_id")
//					.setParameter("parent_id", qualtification_idhid2).executeUpdate();
//
//			tx.commit();
//			session.close();
//
//			if (app > 0) {
//				liststr.add("Delete Successfully.");
//			} else {
//				liststr.add("Delete UNSuccessfully.");
//			}
//			//model.put("msg", liststr.get(0));
//
//		} catch (Exception e) {
//			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
//			//model.put("msg", liststr.get(0));
//		}
//		return  liststr;
//	}
	
	

////////////////////////////////Teacher code Generate

	public String geteacher_code() {

		Connection conn = null;
		String reg_no = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = "";
			query = "select max(substring(teacher_code,4,12))::int from tb_nch_add_teacher_details where substring(teacher_code,1,3)='HM-'\n"
					+ "";
			stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				reg_no = rs.getString("max");

			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return reg_no;
	}

////////////////////////////////Ayush Id Generate
	public String getAyushID(String userid) {

		Connection conn = null;
		String reg_no = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
//			String query = "";
//			query = "  select Substring(st.state_name,1,2)||Substring(code,1,4)||EXTRACT(YEAR FROM now())||lpad((select case when (select max(Substring(ayush_id,11,14))\n"
//					+ "from tb_nch_add_teacher_details) = '' or (select max(Substring(ayush_id,11,14))\n"
//					+ "from tb_nch_add_teacher_details) is null then '0' else (select max(Substring(ayush_id,11,14))\n"
//					+ "from tb_nch_add_teacher_details) end::int+1)::text, 4, '0')as max from logininformation l\n"
//					+ "inner join edu_lms_institute_reg ir on ir.id=l.institute_id and userid = ?   \n"
//					+ "inner join edu_lms_state_mstr st on st.state_id=l.state_id";
			
			String query = "select to_char(CURRENT_TIMESTAMP,'yy')||'HOM'||ir.college_unique_id||sm.state_abbr||lpad((select case when (select max(Substring(ayush_id,12,5))\n"
					+ "from ayush_id_directory_parent )='' or (select max(Substring(ayush_id,12,5))\n"
					+ "from ayush_id_directory_parent ) is null  then '0' else (select max(Substring(ayush_id,12,5))\n"
					+ "from ayush_id_directory_parent ) end::int+1)::text, 5, '0')as ayushid from logininformation l\n"
					+ "inner join edu_lms_institute_reg ir on ir.id=l.institute_id and userid = ?\n"
					+ "inner join edu_lms_state_mstr sm on sm.state_id=l.state_id";

			stmt = conn.prepareStatement(query);

			stmt.setInt(1, Integer.parseInt(userid));
			System.out.println("stmt  " + stmt);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				reg_no = rs.getString("ayushid");

			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return reg_no;
	}
	
	
	
	
	
				
				////////////////////////////////Ayush Id Generate for NCISM
				public String getAyushIDNCISM(String userid) {
				
				Connection conn = null;
				String reg_no = "";
				try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				//String query = "";
				//query = "  select Substring(st.state_name,1,2)||Substring(code,1,4)||EXTRACT(YEAR FROM now())||lpad((select case when (select max(Substring(ayush_id,11,14))\n"
				//+ "from tb_nch_add_teacher_details) = '' or (select max(Substring(ayush_id,11,14))\n"
				//+ "from tb_nch_add_teacher_details) is null then '0' else (select max(Substring(ayush_id,11,14))\n"
				//+ "from tb_nch_add_teacher_details) end::int+1)::text, 4, '0')as max from logininformation l\n"
				//+ "inner join edu_lms_institute_reg ir on ir.id=l.institute_id and userid = ?   \n"
				//+ "inner join edu_lms_state_mstr st on st.state_id=l.state_id";
				
			
				String query="select to_char(CURRENT_TIMESTAMP,'yy')||'AYU'||'0001'||st.state_abbr||lpad((select case when (select max(Substring(ayush_id,12,5))\n"
						+ "				from tb_nch_add_teacher_details)='' or (select max(Substring(ayush_id,12,5))\n"
						+ "				from tb_nch_add_teacher_details) is null  then '0' else (select max(Substring(ayush_id,12,5))\n"
						+ "				from tb_nch_add_teacher_details) end::int+1)::text, 5, '0')as max \n"
						+ "				from logininformation l\n"
						+ "				inner join edu_lms_institute_reg ir on ir.id=l.institute_id and userid = ?\n"
						+ "				inner join edu_lms_state_mstr st on st.state_id=l.state_id";
				
				stmt = conn.prepareStatement(query);
				
				stmt.setInt(1, Integer.parseInt(userid));
				System.out.println("stmt  " + stmt);
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
				reg_no = rs.getString("max");
				
				}
				rs.close();
				stmt.close();
				conn.close();
				} catch (SQLException e) {
				
				e.printStackTrace();
				}
				return reg_no;
				}


	
	
	

////////////////////////////Download Print  form///////////////////Tushar_11_07_22

	
@RequestMapping(value = "/getfacultyList", method = RequestMethod.POST)
	public ModelAndView getfacultyList(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "emp_id3", required = false) int fID, String typeReport, HttpServletRequest request) {

		try {

			String userid = session.getAttribute("userId_for_jnlp").toString();
			ArrayList<ArrayList<String>> list = tdao.getfacultyList(Integer.parseInt(userid));
			ArrayList<ArrayList<String>> list1 = tdao.getpreviousexperiancelist(Integer.parseInt(userid));
			ArrayList<ArrayList<String>> list3 = tdao.getdatalist(Integer.parseInt(userid));
			ArrayList<ArrayList<String>> list4 = tdao.getmedicalpdf(Integer.parseInt(userid));
			ArrayList<ArrayList<String>> list5 = tdao.getothermedicalpdf(Integer.parseInt(userid));
			ArrayList<ArrayList<String>> list6 = tdao.getuniversityname(Integer.parseInt(userid));
			ArrayList<ArrayList<String>> list7 = tdao.getacademicexpViewforPDF((fID));
			
			System.err.println("------list7--------"+list7);
			
			
			System.out.println("list  " + list);
		//	System.out.println("list----3/3/23-  " + fID);
//			System.out.println("list  " + list1);
//			System.out.println("list  " + list3);

			if (list.size() == 0) {
				Mmap.put("msg", "Data Not Available");

			} else {
				Mmap.put("list", list);
				Mmap.put("list.size()", list.size());

				if (typeReport != null && typeReport.equals("pdfL")) {
					if (list.size() > 0) {

						List<String> TH = new ArrayList<String>();

						TH.add("First Name");// 0
						TH.add("Middle Name");// 1
						TH.add("Last Name");// 2
						TH.add("Gender");// 3
						TH.add("Date of Birth");// 4
						TH.add("Father name");// 5
						TH.add("Mother name");// 6
						TH.add("Spouse name");// 7
						TH.add("Mobile no");// 8
						TH.add("Emailid");// 9
						TH.add("Aadhar Card");// 10
						TH.add("Pan Card");// 11
						// TH.add("Academic Qualification");// 12

						TH.add("Address Line1");// 13
						TH.add("Address Line2");// 14
						TH.add("State");// 15
						TH.add("District");// 16
						TH.add("City/Village");// 17
						TH.add("Pin Code");// 18
						TH.add("Phone No");// 19

						TH.add("Permanent Address Line1");// 20
						TH.add("Permanent Address Line2");// 21
						TH.add("State");// 22
						TH.add("District");// 23
						TH.add("City/Village");// 24
						TH.add("Pin Code");// 25
						TH.add("Phone No");// 26
						

						TH.add("State Registration No.");// 27
						TH.add("Name Of The State Board");// 28
						TH.add("Date Of The Register");// 29
						TH.add("Central Registeration No.");// 30
						TH.add("Adjunct Registration No.");// 31
						TH.add("Adjunct State Name.");// 32
						TH.add("Validity Upto");// 33
						
						TH.add("Direct Registration No ");// 33
						TH.add("Date of Registration ");// 34
						TH.add("Name of Department/Organization" );//35
						TH.add("Validity Upto");// 36

						List<String> TH4 = new ArrayList<String>();

						TH4.add("Name of Exam/Degree");// 0
						TH4.add("Course");// 1
						TH4.add("Subject");// 2
						TH4.add("Name of Institute/College");// 3
						TH4.add("Name of Affiliated Uniersity");// 4
						TH4.add("Month & Year of Passing");// 5

						List<String> TH5 = new ArrayList<String>();

						TH5.add("Name of Exam/Degree");// 0
						TH5.add("Subject");// 1
						TH5.add("Name of University/Institute");// 2
						TH5.add("Name of Affiliated Uniersity");// 3
						TH5.add(" Year of Passing");// 4

						List<String> TH2 = new ArrayList<String>();

						TH2.add("Organization Name");// 0
						TH2.add("Department Name");// 1
						TH2.add("Designation");// 2
						TH2.add("From Date");// 3
						TH2.add("To Date");// 4
						TH2.add("Honorarium");// 5
						TH2.add("Employment Type");// 6
//TH2.add("Upload File");// 5
						
						List<String> TH6 = new ArrayList<String>();
						TH6.add("Organization Name");// 0
						TH6.add("From Date");// 1
						TH6.add("To Date");// 2
						TH6.add("Year Of Experience");// 3
						TH6.add("Designation");// 4
						TH6.add("Type Of Experience");// 5

						List<String> TH3 = new ArrayList<String>();
						String foot = " Report Generated On " + new SimpleDateFormat("dd-MM-YYYY").format(new Date());
						TH3.add("Document Name");// 0
						TH3.add("Document Type");// 1
//TH3.add("Upload");// 2
//TH3.add("From Date");// 3

						String Heading = "";
						String username = session.getAttribute("username").toString();

						return new ModelAndView(new DownloadPdfprintForm("P", TH2, Heading, username,foot, list1, TH3, TH4,
								TH5,TH6,list3, list4, list5,list6,list7), "userList", list);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("View_Teacher_dtlTiles");
	}
	

//	 @RequestMapping(value = "/getfacultyListstatus", method = RequestMethod.POST)
//		public @ResponseBody   ArrayList<ArrayList<String>> getfacultyListstatus(HttpSession session) {
//		 
//		 String userid = session.getAttribute("userId_for_jnlp").toString();
//		 ArrayList<ArrayList<String>> list = tdao.getfacultyList(Integer.parseInt(userid));
//		
//			return list;
//		}
	
	 @RequestMapping(value = "/getuniversitybyinstlist_ctrl", method = RequestMethod.POST)
		public @ResponseBody   ArrayList<ArrayList<String>> getuniversitybyinstlist_ctrl(String institute_id) {
		 
		 ArrayList<ArrayList<String>> list = cdao.getUniversitybyinstitutelist(institute_id);
		
			return list;
		}

	 @RequestMapping(value = "/quali_attachment", method = RequestMethod.POST)
		public @ResponseBody   ArrayList<ArrayList<String>> quali_attachment( HttpSession session,String parent_id) {
		 String userid = session.getAttribute("userId_for_jnlp").toString();
		
		 ArrayList<ArrayList<String>> list =tdao.getPopup_Datalistquali (userid,parent_id);
		 System.err.println("-----2408-------list"+list);
			return list;
		}
		
		@SuppressWarnings("null")
		@RequestMapping(value = "/getDownloadUrlforDocAttsub")
		public ModelAndView getDownloadUrlforDocAttsub(@RequestParam(value = "msg", required = false) String msg, String pageUrl,
				String upload_docattsub, ModelMap model, HttpServletRequest request, HttpSession session,
				HttpServletResponse response) throws IOException {
			System.out.println("upload_doc2 "+upload_docattsub);
			String url = pageUrl;
			String EXTERNAL_FILE_PATH = "";
			String enckey = "commonPwdEncKeys";
			EXTERNAL_FILE_PATH = tdao.getFilePathQueryForDocAttSub(upload_docattsub);
//		model.put("downloadid", downloadid);
			model.put("msg", "Sorry ! The file you are looking for does not exist.");

			if (EXTERNAL_FILE_PATH != "") {
				File file = null;
				file = new File(EXTERNAL_FILE_PATH);
				try {
					if (!file.exists()) {

						return new ModelAndView(url);
					}
				} catch (Exception exception) {
				}

				String mimeType = URLConnection.guessContentTypeFromName(file.getName());
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
				response.setContentType(mimeType);
				response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
				response.setContentLength((int) file.length());
				InputStream inputStream = null;
				try {
					inputStream = new BufferedInputStream(new FileInputStream(file));
					FileCopyUtils.copy(inputStream, response.getOutputStream());
				} catch (FileNotFoundException e) {
					// e.printStackTrace();
				}
			}
			return new ModelAndView(url);
		}
		
		
		 @PostMapping(value = "/teacher_saveDraft_Action")
		 public @ResponseBody Map<String, String> teacher_saveDraft_Action(ModelMap Mmap, HttpSession session,
				 HttpServletRequest request,RedirectAttributes ra) throws IOException, ParseException {
		 
		
		 
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
				
				int p_id=Integer.parseInt(request.getParameter("perentId"));
				
				System.err.println("p_id for update-----------" + p_id + "---");
				
				String id_for_update = request.getParameter("id_hidden");
				System.err.println("id for update-----------" + id_for_update + "---");
					String first_name = request.getParameter("first_name");
					System.out.println("first  "+first_name);
					String cand_prefix = request.getParameter("cand_prefix");
					String middle_name = request.getParameter("middle_name");
					String last_name = request.getParameter("last_name");
					String gender = request.getParameter("gender");
					String date_of_birth = request.getParameter("date_of_birth");
					String dob = request.getParameter("dob");
				//	System.err.println("----------"+dob);
					String father_name = request.getParameter("father_name");
					String mother_name = request.getParameter("mother_name");
					String spouse_name = request.getParameter("spouse_name");
					String mobile_no = request.getParameter("mobile_no");
					String email = request.getParameter("email");
					String aadhar_no1 = request.getParameter("aadhar_no1");
					String aadhar_no2 = request.getParameter("aadhar_no2");
					String aadhar_no3 = request.getParameter("aadhar_no3");
					String aadhar_no = aadhar_no1 + aadhar_no2 + aadhar_no3;
					String pan_no = request.getParameter("pan_no");
					String present_add_line1 = request.getParameter("present_add_line1");
					String present_add_line2 = request.getParameter("present_add_line2");
					String present_village = request.getParameter("present_village");
					String present_state = request.getParameter("per_state");
					
					
					
					String present_district = request.getParameter("per_district");
					String present_pincode = request.getParameter("per_pincode");
					
					System.err.println("--------19---"+present_pincode);
					String present_phn_no = request.getParameter("present_phn_no");
					String per_add_line1 = request.getParameter("per_add_line1");
					String per_add_line2 = request.getParameter("per_add_line2");
					String per_village = request.getParameter("per_village");
					String per_state = request.getParameter("present_state");
					String per_district = request.getParameter("present_district");
					String per_pincode = request.getParameter("present_pincode");
					String per_phn_no = request.getParameter("per_phn_no");
					String ayush_id = request.getParameter("ayushid_hidden");
					String current_designation = request.getParameter("current_designation");
					String date_of_joining = request.getParameter("date_of_joining");
					String date_of_appoint = request.getParameter("date_of_appoint");
					
					
					if (cand_prefix == null || cand_prefix.trim().equals("0")) {
						data.put("msg", "Please Select Candidate Prifix");
						return  data;
					}
					if (first_name == null || first_name.trim().equals("")) {
						data.put("msg", "Please Enter First Name.");
						return data;
					}
					if (validation.maxlengthcheck50(first_name) == false) {
						data.put("msg", "First Name " + validation.MaxlengthcheckMSG50);
						return data;
					}
					if (validation.isOnlyAlphabet(first_name) == false) {
						data.put("msg", "First Name " + validation.isOnlyAlphabetMSG);
						return data;
					}

					if (validation.maxlengthcheck50(middle_name) == false) {
						data.put("msg", "Middle Name " + validation.MaxlengthcheckMSG50);
						return data;
					}
//					if (validation.isOnlyAlphabetDASH(middle_name) == false) {
//						data.put("msg", "Middle Name " + validation.isOnlyAlphabetMSGDASH);
//						return data;
//					}

					if (last_name == null || last_name.trim().equals("")) {
						data.put("msg", "Please Enter Last Name.");
						return data;
					}
					if (validation.maxlengthcheck50(last_name) == false) {
						data.put("msg", "Last Name " + validation.MaxlengthcheckMSG50);
						return data;
					}
					if (validation.isOnlyAlphabet(last_name) == false) {
						data.put("msg", "Last Name " + validation.isOnlyAlphabetMSG);
						return data;
					}

					if (gender == null || gender.trim().equals("0")) {
						data.put("msg", "Please Select Gender");
						return data;
					}

//					if (date_of_birth == null || date_of_birth.trim().equals("") || date_of_birth.equals("DD/MM/YYYY")) {
//						data.put("msg", "Please Enter The Date Of Birth");
//						return data;
//					}
					
					if (dob == null || dob.trim().equals("") || dob.equals("DD/MM/YYYY")) {
						data.put("msg", "Please Enter The Date Of Birth");
						return data;
					}

					if (father_name == null || father_name.trim().equals("")) {
						data.put("msg", "Please Enter Father's Name.");
						return data;
					}
					if (validation.maxlengthcheck50(father_name) == false) {
						data.put("msg", "Father's Name " + validation.MaxlengthcheckMSG50);
						return data;
					}
					if (validation.isOnlyAlphabet(father_name) == false) {
						data.put("msg", "Father's Name " + validation.isOnlyAlphabetMSG);
						return data;
					}

					if (mother_name == null || mother_name.trim().equals("")) {
						data.put("msg", "Please Enter Mother's Name.");
						return data;
					}
					if (validation.maxlengthcheck50(mother_name) == false) {
						data.put("msg", "Mother's Name " + validation.MaxlengthcheckMSG50);
						return data;
					}
					if (validation.isOnlyAlphabet(mother_name) == false) {
						data.put("msg", "Mother's Name " + validation.isOnlyAlphabetMSG);
						return data;
					}

					if (validation.maxlengthcheck50(spouse_name) == false) {
						data.put("msg", "Spouse's Name " + validation.MaxlengthcheckMSG50);
						return data;
					}
					if (validation.isOnlyAlphabet(spouse_name) == false) {
						data.put("msg", "Spouse's Name " + validation.isOnlyAlphabetMSG);
						return data;
					}

					if (mobile_no == null || mobile_no.trim().equals("")) {
						data.put("msg", "Please Enter Mobile Number");
						return data;
					}
					if (validation.isOnlyNumer(mobile_no) == true) {
						data.put("msg", "Mobile Number " + validation.isOnlyNumerMSG);
						return data;
					}
					if (validation.maxlengthcheck10(mobile_no) == false) {
						data.put("msg", "Mobile Number " + validation.MaxlengthcheckMSG10);
						return data;
					}
					if (validation.isValidMobileNo(mobile_no) == false) {
						data.put("msg", "Mobile Number " + validation.isOnlyMobilenumberMSG);
						return data;
					}

					if (email == null || email.trim().equals("")) {
						data.put("msg", "Please Enter Email Address");
						return data;
					}
					if (validation.maxlengthcheck70(email) == false) {
						data.put("msg", "Email Address " + validation.MaxlengthcheckMSG70);
						return data;
					}

					if (aadhar_no1 == null || aadhar_no1.trim().equals("")) {
						data.put("msg", "Please Enter Aadhaar No.");
						return data;
					}
					if (validation.isOnlyNumer(aadhar_no1) == true) {
						data.put("msg", "Aadhaar No. " + validation.isOnlyNumerMSG);
						return data;
					}
					if (validation.AadharNoLength(aadhar_no1) == false) {
						data.put("msg", validation.AadharNoMSG11);
						return data;
					}
					if (validation.AadharNoMinLength(aadhar_no1) == false) {
						data.put("msg", validation.AadharNoMSGMinlen11);
						return data;
					}

					if (aadhar_no2 == null || aadhar_no2.trim().equals("")) {
						data.put("msg", "Please Enter Aadhaar No.");
						return data;
					}
					if (validation.isOnlyNumer(aadhar_no2) == true) {
						data.put("msg", "Aadhaar No. " + validation.isOnlyNumerMSG);
						return data;
					}
					if (validation.AadharNoLength(aadhar_no2) == false) {
						data.put("msg", validation.AadharNoMSG11);
						return data;
					}
					if (validation.AadharNoMinLength(aadhar_no2) == false) {
						data.put("msg", validation.AadharNoMSGMinlen11);
						return data;
					}

					if (aadhar_no3 == null || aadhar_no3.trim().equals("")) {
						data.put("msg", "Please Enter Aadhaar No.");
						return data;
					}
					if (validation.isOnlyNumer(aadhar_no3) == true) {
						data.put("msg", "Aadhaar No. " + validation.isOnlyNumerMSG);
						return data;
					}
					if (validation.AadharNoLength(aadhar_no3) == false) {
						data.put("msg", validation.AadharNoMSG11);
						return data;
					}
					if (validation.AadharNoMinLength(aadhar_no3) == false) {
						data.put("msg", validation.AadharNoMSGMinlen11);
						return data;
					}

					if (pan_no == null || pan_no.trim().equals("")) {
						data.put("msg", "Please Enter PAN No.");
						return data;
					}
					if (validation.maxlength10(pan_no) == false) {
						data.put("msg", "PAN No. " + validation.MaxlengthMSG10);
						return data;
					}
					if (validation.minlength10(pan_no) == false) {
						data.put("msg", "PAN No. " + validation.MinlengthMSG10);
						return data;
					}
					if (validation.isOnlyAlphabetNumeric(pan_no) == false) {
						data.put("msg", "PAN No. " + validation.isOnlyAlphabetNumericMSG);
						return data;
					}

					/////////// Address
					if (per_add_line1 == null || per_add_line1.trim().equals("")) {
						data.put("msg", "Please Enter Present Address Address-Line-1");
						return data;
					}
					if (validation.maxlengthcheck100(per_add_line1) == false) {
						data.put("msg", "Present Address Address-Line-1 " + validation.MaxlengthcheckMSG100);
						return data;
					}

					if (per_add_line2 == null || per_add_line2.trim().equals("")) {
						data.put("msg", "Please Enter Present Address  Address-Line-2");
						return data;
					}
					if (validation.maxlengthcheck100(per_add_line2) == false) {
						data.put("msg", "Present Address Address-Line-2 " + validation.MaxlengthcheckMSG100);
						return data;
					}

					if (per_state == null || per_state.trim().equals("0")) {
						data.put("msg", "Please Select State");
						return data;
					}
					if (per_district == null || per_district.trim().equals("0")) {
						data.put("msg", "Please Select District");
						return data;
					}

					if (per_village == null || per_village.trim().equals("")) {
						data.put("msg", "Please Enter Present Address City/Village");
						return data;
					}
					if (validation.maxlengthcheck100(per_village) == false) {
						data.put("msg", "Present Address City/Village " + validation.MaxlengthcheckMSG100);
						return data;
					}
					if (validation.isOnlyAlphabet(per_village) == false) {
						data.put("msg", "Present Address City/Village " + validation.isOnlyAlphabetMSG);
						return data;
					}

					if (per_pincode == null || per_pincode.trim().equals("")) {
						data.put("msg", "Please Enter Present Address Pin Code");
						return data;
					}
					if (validation.maxlengthcheckpincode(per_pincode) == false) {
						data.put("msg", "Present Address Pin Code " + validation.MaxlengthcheckMSGpincode);
						return data;
					}
					if (validation.minlengthcheckpincode(per_pincode) == false) {
						data.put("msg", "Present Address Pin Code " + validation.MinlengthcheckMSGpincode);
						return data;
					}
					if (validation.isOnlyNumer(per_pincode) == true) {
						data.put("msg", "Present Address Pin Code " + validation.isOnlyNumerMSG);
						return data;
					}



					if (request.getParameter("check_address") == null) {

						if (present_add_line1 == null || present_add_line1.trim().equals("")) {
							data.put("msg", "Please Enter Correspondence Address-Line-1");
							return data;
						}
						if (validation.maxlengthcheck100(present_add_line1) == false) {
							data.put("msg", "Correspondence Address-Line-1 " + validation.MaxlengthcheckMSG100);
							return data;
						}

						if (present_add_line2 == null || present_add_line2.trim().equals("")) {
							data.put("msg", "Please Enter Correspondence Address-Line-2");
							return data;
						}
						if (validation.maxlengthcheck100(present_add_line2) == false) {
							data.put("msg", "Correspondence Address-Line-2 " + validation.MaxlengthcheckMSG100);
							return data;
						}

						if (present_state == null || present_state.trim().equals("0")) {
							data.put("msg", "Please Select State");
							return data;
						}
						if (present_district == null || present_district.trim().equals("0")) {
							data.put("msg", "Please Select District");
							return data;
						}

						if (present_village == null || present_village.trim().equals("")) {
							data.put("msg", "Please Enter Present City/Village");
							return data;
						}
						if (validation.maxlengthcheck100(present_village) == false) {
							data.put("msg", "Correspondence City/Village " + validation.MaxlengthcheckMSG100);
							return data;
						}
						if (validation.isOnlyAlphabet(present_village) == false) {
							data.put("msg", "Correspondence City/Village " + validation.isOnlyAlphabetMSG);
							return data;
						}

						if (present_pincode == null || present_pincode.trim().equals("")) {
							data.put("msg", "Please Enter Correspondence Pin Code");
							return data;
						}
						if (validation.maxlengthcheckpincode(present_pincode) == false) {
							data.put("msg", "Correspondence Pin Code " + validation.MaxlengthcheckMSGpincode);
							return data;
						}
						if (validation.minlengthcheckpincode(present_pincode) == false) {
							data.put("msg", "Correspondence Pin Code " + validation.MinlengthcheckMSGpincode);
							return data;
						}
						if (validation.isOnlyNumer(present_pincode) == true) {
							data.put("msg", "Present Enter Pin Code " + validation.isOnlyNumerMSG);
							return data;
						}
//						if (current_designation == null || current_designation.trim().equals("")) {
//							data.put("msg", "Please Enter Current Designation.");
//							return data;
//						}
//						if (validation.maxlengthcheck50(current_designation) == false) {
//							data.put("msg", "Current Designation " + validation.MaxlengthcheckMSG50);
//							return data;
//						}
//						if (validation.isOnlyAlphabet(current_designation) == false) {
//							data.put("msg", "Current Designation " + validation.isOnlyAlphabetMSG);
//							return data;
//						}
					
					
					}
					System.out.println("id_for_update "+id_for_update);
					Long c1 = (Long) sessionHQL.createQuery("select count(*) from  EDU_LMS_TEACHER_DTL pr where pan_no=:pan_no and cast(id as text)!=:id")
							.setParameter("pan_no", request.getParameter("pan_no"))
							.setParameter("id", id_for_update) 
							.uniqueResult();
					System.out.println("c1========================  "+c1);
					if(c1==0) { 
					
					if(p_id==0) {
						EDU_LMS_TEACHER_DTL rd =new EDU_LMS_TEACHER_DTL();
						
						String depart = "HM-";
						String reg_no = geteacher_code();
						String testold = "";
						if (reg_no != null) {
							int newn = Integer.parseInt(reg_no);
							newn++;
							String abc = String.format("%8s", newn).replace(' ', '0');
							testold = depart + abc;
							// System.err.println("testold------->>>>" + testold);
							rd.setTeacher_code(testold);

						} else {
							testold = "HM-00000001";
							rd.setTeacher_code(testold);
						}

						/////////////////////////ayush_id generate		
						int increemt = 0;
						String abr = "";

						
//					if(role.toUpperCase().equals("NCH")) {	
						String maxAID="";
//						if(i==1) {
							maxAID = addao.getAyushID(userid,session);
							increemt=Integer.parseInt(maxAID.substring(11));
							abr=maxAID.substring(0,11);
							rd.setAyush_id(maxAID);		
//					} 
//					if(role.toUpperCase().equals("NCISM")) {	
//						String maxAID="";
////						if(i==1) {
//							maxAID = getAyushIDNCISM(userid);
//							increemt=Integer.parseInt(maxAID.substring(11));
//							abr=maxAID.substring(0,11);
//						
//							rd.setAyush_id(maxAID);		
//					}
					
					Boolean Directory = directory.SaveAyushId_Directory(maxAID, String.valueOf(userid), aadhar_no, "24", session);
					
					System.err.println("Directory---------->     "+Directory);
					
					rd.setUser_id(Integer.parseInt(userid));
					rd.setFirst_name(first_name);
					rd.setCand_prefix(cand_prefix);
					rd.setMiddle_name(middle_name);
					rd.setLast_name(last_name);
					rd.setGender(gender);
					rd.setDate_of_birth(datePickerFormat.parse(dob));
					rd.setFather_name(father_name);
					rd.setMother_name(mother_name);
					rd.setSpouse_name(spouse_name);
					rd.setMobile_no(mobile_no);
					rd.setEmail(email);
					rd.setAadhar_no(aadhar_no);
					rd.setPan_no(pan_no);
					rd.setPresent_add_line1(present_add_line1);
					rd.setPresent_add_line2(present_add_line2);
					rd.setPresent_village(present_village);
					rd.setPresent_state(Integer.parseInt(per_state));
					rd.setPresent_district(Integer.parseInt(per_district));
					rd.setPresent_pincode(Integer.parseInt(per_pincode));
					rd.setPresent_phn_no(present_phn_no);
					rd.setPer_add_line1(per_add_line1);
					rd.setPer_add_line2(per_add_line2);
					rd.setPer_village(per_village);
					rd.setPer_state(Integer.parseInt(present_state));
					rd.setPer_district(Integer.parseInt(present_district));
					rd.setPer_pincode(Integer.parseInt(present_pincode));
					rd.setPer_phn_no(per_phn_no);
					rd.setPrincipal_status(0);
					rd.setCurrent_designation(current_designation);
					rd.setDate_of_joining(datePickerFormat.parse(date_of_joining));
					rd.setNature_of_appointment(datePickerFormat.parse(date_of_appoint));
					
					
					
					
					UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
                	String university = String.valueOf(ea.getUniversity_id());
					rd.setUniversity_id(Integer.parseInt(university));
					//p.setUniversity_id(Integer.parseInt(university));
					
					String institude = String.valueOf(ea.getInstitute_id());
					rd.setInstitute_id(Integer.parseInt(institude));
				//	p.setInstitute_id(Integer.parseInt(institude));
					
					

					
					rd.setCreated_by(username);
					rd.setCreated_date(date);
					rd.setStatus(0);
					p_id = (int) sessionHQL.save(rd);
					 data.put("msg", "Save as Draft Successfylly");
					sessionHQL.flush();
					sessionHQL.clear();
				}
					else {
					
					
					
					System.err.println("id for update-----else------" + id_for_update + "---");
					EDU_LMS_TEACHER_DTL urd = (EDU_LMS_TEACHER_DTL) sessionHQL.get(EDU_LMS_TEACHER_DTL.class,
							Integer.parseInt(id_for_update));
					
					//urd.setTeacher_code(teachercode);
					urd.setAyush_id(ayush_id);
					urd.setUser_id(Integer.parseInt(userid));
					urd.setFirst_name(first_name);
					urd.setCand_prefix(cand_prefix);
					urd.setMiddle_name(middle_name);
					urd.setLast_name(last_name);
					urd.setGender(gender);
					urd.setDate_of_birth(datePickerFormat.parse(dob));
					// rd.setDate_of_birth(formatter1.parse(dob1));
					urd.setFather_name(father_name);
					urd.setMother_name(mother_name);
					urd.setSpouse_name(spouse_name);
					urd.setMobile_no(mobile_no);
					urd.setEmail(email);
					urd.setAadhar_no(aadhar_no);
					urd.setPan_no(pan_no);
					//urd.setStatus(Integer.parseInt(hiddenUpdate));

					urd.setPresent_add_line1(present_add_line1);
					urd.setPresent_add_line2(present_add_line2);
					urd.setPresent_village(present_village);
					urd.setPresent_state(Integer.parseInt(per_state));
					urd.setPresent_district(Integer.parseInt(per_district));
					urd.setPresent_pincode(Integer.parseInt(per_pincode));
					urd.setPresent_phn_no(present_phn_no);

					urd.setPer_add_line1(per_add_line1);
					urd.setPer_add_line2(per_add_line2);
					urd.setPer_village(per_village);
					urd.setPer_state(Integer.parseInt(present_state));
					urd.setPer_district(Integer.parseInt(present_district));
					urd.setPer_pincode(Integer.parseInt(present_pincode));
					urd.setPer_phn_no(per_phn_no);
					urd.setPrincipal_status(0);
					urd.setCurrent_designation(current_designation);
					urd.setDate_of_joining(datePickerFormat.parse(date_of_joining));
					urd.setNature_of_appointment(datePickerFormat.parse(date_of_appoint));



					urd.setModified_by(username);
					urd.setModified_date(date);

					sessionHQL.update(urd);
					 data.put("msg", "Draft Update Successfully");
					sessionHQL.flush();
					sessionHQL.clear();
				}
					}else {
						data.put("msg", "Pan No Already Exits");
						return data;
					}
//					}	
//					}
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

	 
		 @PostMapping(value = "/teacher_saveDraftforquali_Action")
		 public @ResponseBody Map<String, String> teacher_saveDraftforquali_Action(ModelMap Mmap, HttpSession session,EDU_LMS_TEACHER_QUALIFICATION_CHILD qc,
				 HttpServletRequest request,MultipartHttpServletRequest mul) throws IOException, ParseException {
		 
		// System.err.println("IN ACTION RAHUL");
		 
			Date date = new Date();
			String username = session.getAttribute("username").toString();
			String userid = session.getAttribute("userId_for_jnlp").toString();

			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx1 = sessionHQL.beginTransaction();
			DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		//	MultipartFile mul = request.getFile(paramName);
			Map<String, String> data = new HashMap<>();
			
			try {
				int p_id=Integer.parseInt(request.getParameter("perentId"));
				
				System.err.println("20sep--------"+p_id);
				
				int count_hidden_att_name_med = Integer.parseInt(request.getParameter("count_hidden_att_name_med"));
				System.out.println("count_hidden_att_name_med "+count_hidden_att_name_med);
				for (int j = 1; j <= count_hidden_att_name_med; j++) {
					
					int qulification_id=Integer.parseInt(request.getParameter("qualtification_id"+j));
					int at_pid=qulification_id;
					if(qulification_id==0) {
						
						String typeOfDegree = request.getParameter("typeOfDegree" + j);
						String course = request.getParameter("course" + j);
						String subjectQ = request.getParameter("subject" + j);
						String monthYearOfDegree = request.getParameter("monthYearOfDegree" + j);
//					String rollno = request.getParameter("rollno" + j);
						String NameOfUniversity = request.getParameter("NameOfUniversity" + j);
						String NameOfAffUni = request.getParameter("NameOfAffUni" + j);
						String universityother = request.getParameter("universityother" + j);
//					String percentage = request.getParameter("percentage" + j); 
						if (typeOfDegree == null || typeOfDegree.equals("0")) {
							
							data.put("msg", "Please Select Name of Exam/Degree In Row " + j);
							
						}
						if (course == null || course.equals("0")) {
							data.put("msg", "Please Select Course In Row " + j);
							
						}
//						if (subjectQ == null || subjectQ.equals("0")) {
//							ra.addAttribute("msg", "Please Select Subject In Row " + j);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
						if (NameOfUniversity == null || NameOfUniversity.trim().equals("")) {
							data.put("msg", "Please Enter Institute Name In Row " + j);
							
						}
						if (validation.maxlengthcheck100(NameOfUniversity) == false) {
							data.put("msg", "Institute Name " + validation.MaxlengthcheckMSG100);
							
						}
//						if (validation.isOnlyAlphabetDASH(NameOfUniversity) == false) {
//							ra.addAttribute("msg", "Institute Name " + validation.isOnlyAlphabetMSGDASH);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
						if (NameOfAffUni == null || NameOfAffUni.trim().equals("")) {
							data.put("msg", "Please Enter Name of Affiliated University In Row " + j);
							
						}
						if (validation.maxlengthcheck100(NameOfAffUni) == false) {
							data.put("msg", "Name of Affiliated University " + validation.MaxlengthcheckMSG100);
							
						}
						if (NameOfAffUni.equals("OTHER")) {
							if(universityother.equals("")) {
								data.put("msg", "Please Enter Other University Name In Row " + j);
								
							}
							
						}
//						if (validation.isOnlyAlphabetDASH(NameOfAffUni) == false) {
//							ra.addAttribute("msg", "Name of Affiliated University" + validation.isOnlyAlphabetMSGDASH);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
						if (monthYearOfDegree == null || monthYearOfDegree.equals("")
								|| monthYearOfDegree.equals("dd/mm/yyyy")) {
							data.put("msg", "Please Enter Month Year Of Degree" + j);
							
						}

						
						Query q0 = sessionHQL.createQuery("select count(id) from EDU_LMS_TEACHER_QUALIFICATION_CHILD where upper(type_of_degree)=:type_of_degree and p_id=:p_id");
						q0.setParameter("type_of_degree", typeOfDegree.toUpperCase());
						q0.setParameter("p_id", p_id);
						Long c = (Long) q0.uniqueResult();
						
						if(!typeOfDegree.equals("0") && !course.equals("0") && !monthYearOfDegree.equals("") && !NameOfUniversity.equals("0") && !NameOfAffUni.equals("0") ) {
	
						qc.setP_id(p_id);
						qc.setType_of_degree(typeOfDegree);
						qc.setCourse(course);
						qc.setSubject(Integer.parseInt(subjectQ));
						qc.setMonth_and_year(monthYearOfDegree);
//					qc.setRoll_no(Integer.parseInt(rollno));
						qc.setName_of_institute(NameOfUniversity);
						qc.setAffiliated_university(NameOfAffUni);
						if(NameOfAffUni.equals("OTHER")) {
							qc.setOtheruniversity(universityother);
						}else {
							qc.setOtheruniversity("");
						}
//					qc.setPercentage(Integer.parseInt(percentage));
						qc.setCreated_by(username);
						qc.setCreated_date(date);

						 at_pid = (int) sessionHQL.save(qc);
						 System.out.println("at_pid "+at_pid);
						 data.put("msg", "Data Save As Draft Successfully");
						sessionHQL.flush();
						sessionHQL.clear();
						
						}else {
							
							data.put("msg", "Please select all the Mandatory Field");
						}
					//}
					}else {
						String typeOfDegree = request.getParameter("typeOfDegree" + j);
						String course = request.getParameter("course" + j);
						String subjectQ = request.getParameter("subject" + j);
						String monthYearOfDegree = request.getParameter("monthYearOfDegree" + j);
//						String rollno = request.getParameter("rollno" + j);
						String NameOfUniversity = request.getParameter("NameOfUniversity" + j);
						String NameOfAffUni = request.getParameter("NameOfAffUni" + j);
//						String percentage = request.getParameter("percentage" + j); 
						String universityother = request.getParameter("universityother" + j);
if (typeOfDegree == null || typeOfDegree.equals("0")) {
							
							data.put("msg", "Please Select Name of Exam/Degree In Row " + j);
							
						}
						if (course == null || course.equals("0")) {
							data.put("msg", "Please Select Course In Row " + j);
							
						}
//						if (subjectQ == null || subjectQ.equals("0")) {
//							ra.addAttribute("msg", "Please Select Subject In Row " + j);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
						if (NameOfUniversity == null || NameOfUniversity.trim().equals("")) {
							data.put("msg", "Please Enter Institute Name In Row " + j);
							
						}
						if (validation.maxlengthcheck100(NameOfUniversity) == false) {
							data.put("msg", "Institute Name " + validation.MaxlengthcheckMSG100);
							
						}
//						if (validation.isOnlyAlphabetDASH(NameOfUniversity) == false) {
//							ra.addAttribute("msg", "Institute Name " + validation.isOnlyAlphabetMSGDASH);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
						if (NameOfAffUni == null || NameOfAffUni.trim().equals("")) {
							data.put("msg", "Please Enter Name of Affiliated University In Row " + j);
							
						}
						if (validation.maxlengthcheck100(NameOfAffUni) == false) {
							data.put("msg", "Name of Affiliated University " + validation.MaxlengthcheckMSG100);
							
						}
						if (NameOfAffUni.equals("OTHER")) {
							if(universityother.equals("")) {
								data.put("msg", "Please Enter Other University Name In Row " + j);
								
							}
							
						}
						EDU_LMS_TEACHER_QUALIFICATION_CHILD HSCH = (EDU_LMS_TEACHER_QUALIFICATION_CHILD) sessionHQL
								.get(EDU_LMS_TEACHER_QUALIFICATION_CHILD.class, qulification_id);



						HSCH.setP_id(p_id);

						HSCH.setType_of_degree(typeOfDegree);
						HSCH.setCourse(course);
						HSCH.setSubject(Integer.parseInt(subjectQ));
						HSCH.setMonth_and_year(monthYearOfDegree);

						HSCH.setName_of_institute(NameOfUniversity);
						HSCH.setAffiliated_university(NameOfAffUni);
						System.out.println("NameOfAffUni=========  "+NameOfAffUni);
						if(NameOfAffUni.equals("OTHER")) {
							HSCH.setOtheruniversity(universityother);
						}else {
							HSCH.setOtheruniversity("");
						}
						HSCH.setCreated_by(username);
						HSCH.setCreated_date(date);

						sessionHQL.update(HSCH);
						data.put("msg", "Data Save As Draft Successfully");
						sessionHQL.flush();
						sessionHQL.clear();
//						
					}
//					
					System.out.println("count_hidden_att_doc=======  "+request.getParameter("count_hidden_att_doc"+j));
				if(request.getParameter("count_hidden_att_doc"+j)!=null) {
					
				
					int count_hid_subchild = Integer.parseInt(request.getParameter("count_hidden_att_doc"+j));
					System.out.println("count_hid_subchild "+count_hid_subchild);
					for (int k = 1; k <= count_hid_subchild; k++) {
						String name_of_attachment = request
								.getParameter("name_of_attachment" + j + "_" + k);
						String certificate_type = request.getParameter("name_of_degree" + j + "_" + k);
						System.out.println("certificate_type "+certificate_type);
					//	MultipartFile attachmentchild = ((MultipartRequest) mul).getFile("attachmentDocument" + j + "_" + k);
						MultipartFile attachmentchild = mul.getFile("attachmentDocument" + j + "_" + k);
						String attachmentDoc_hid = request.getParameter("attachmentDoc_hid" + j + "_" + k);
						
//						if (name_of_attachment == null || name_of_attachment.equals("0")) {
//						//	ra.addAttribute("msg",
//									"Please Select Name of Attachment In Row " + j + " " + k);
//							break;
//							//return new ModelAndView("redirect:Teacher_dtl_Url");
//						}
//						if (name_of_attachment == null || name_of_attachment.equals("3")) {
//
//							if (certificate_type == null || certificate_type.equals("0")) {
//
//								ra.addAttribute("msg",
//										"Please Select Certificate Type In Row " + j + " " + k);
//								break;
//								//return new ModelAndView("redirect:Teacher_dtl_Url");
//							}
//						}
//						
//																								medicalqualificationAtt_id2_1
						System.out.println("j "+j+" k "+k);
						int medicalqualificationAtt_id = Integer.parseInt(request.getParameter("medicalqualificationAtt_id" + j + "_" + k));

						if(medicalqualificationAtt_id==0) {
							if (name_of_attachment == null || name_of_attachment.equals("0")) {
								data.put("msg",
										"Please Select Name of Attachment In Row " + j + " " + k);
								break;
								//return new ModelAndView("redirect:Teacher_dtl_Url");
							}
							if (name_of_attachment == null || name_of_attachment.equals("3")) {

								if (certificate_type == null || certificate_type.equals("0")) {

									data.put("msg",
											"Please Select Certificate Type In Row " + j + " " + k);
									break;
									//return new ModelAndView("redirect:Teacher_dtl_Url");
								}
							}
							EDU_LMS_TEACHER_QUALIFICATION_ATTACHMENT_SUBCHILD qas = new EDU_LMS_TEACHER_QUALIFICATION_ATTACHMENT_SUBCHILD();
							String pic="";
							if (!attachmentchild.isEmpty()) {
								pic = common.fileupload(attachmentchild.getBytes(),
										attachmentchild.getOriginalFilename(), "UploadHardCopy1");
								System.err.println("");
								if (pic != "") {

									qas.setAttachment(pic);
//							ec.setAttachment_path(namep);
								} else {
									qas.setAttachment(attachmentDoc_hid);

								}
							}
							qas.setParent_id(at_pid);
							qas.setName_of_attachment(name_of_attachment);
							qas.setCertificate_type(Integer.parseInt(certificate_type));
							//qas.setAttachment(attachmentchild);
							sessionHQL.save(qas);
							sessionHQL.flush();
							sessionHQL.clear();

				//		}	
//							
						}else {
							if (name_of_attachment == null || name_of_attachment.equals("0")) {
								data.put("msg",
										"Please Select Name of Attachment In Row " + j + " " + k);
								break;
								//return new ModelAndView("redirect:Teacher_dtl_Url");
							}
							if (name_of_attachment == null || name_of_attachment.equals("3")) {

								if (certificate_type == null || certificate_type.equals("0")) {

									data.put("msg",
											"Please Select Certificate Type In Row " + j + " " + k);
									break;
									//return new ModelAndView("redirect:Teacher_dtl_Url");
								}
							}
							EDU_LMS_TEACHER_QUALIFICATION_ATTACHMENT_SUBCHILD qas1 = (EDU_LMS_TEACHER_QUALIFICATION_ATTACHMENT_SUBCHILD) sessionHQL
									.get(EDU_LMS_TEACHER_QUALIFICATION_ATTACHMENT_SUBCHILD.class, medicalqualificationAtt_id);
							
							
//							String name_of_attachment = request
//									.getParameter("name_of_attachment" + j + "_" + k);
//							String certificate_type = request.getParameter("name_of_degree" + j + "_" + k);
//							MultipartFile attachmentchild = mul.getFile("attachmentDocument" + j + "_" + k);
//							String attachmentDoc_hid = request.getParameter("attachmentDoc_hid" + j + "_" + k);
							

							qas1.setParent_id(at_pid);
							qas1.setAttachment(attachmentDoc_hid);
							qas1.setCertificate_type(Integer.parseInt(certificate_type));
							qas1.setName_of_attachment(name_of_attachment);
							sessionHQL.update(qas1);
							sessionHQL.flush();
							sessionHQL.clear();	
						}
					}
				}else {
					//data.put("msg", "Please Choose File " + j);
				}
					
				}
				
				//other qualification
				EDU_LMS_TEACHER_OTHER_QUALIFICATION_CHILD oqc = new EDU_LMS_TEACHER_OTHER_QUALIFICATION_CHILD();
				
				int count_hidden_att_oth_quali = Integer.parseInt(request.getParameter("count_hidden_att_oth_quali"));
				System.out.println("count_hidden_att_oth_quali "+count_hidden_att_oth_quali);
				for (int j = 1; j <= count_hidden_att_oth_quali; j++) {
					
				int othquali_id=Integer.parseInt(request.getParameter("othquali_id"+j));
				
				int at_pid=othquali_id;
				
				String OthnameofExDeg = request.getParameter("OthnameofExDeg" + j);
				String othSubject = request.getParameter("othSubject" + j);
				String othUniInst = request.getParameter("othUniInst" + j);
				String othAffuni = request.getParameter("othAffuni" + j);
				String othYrofpass = request.getParameter("othYrofpass" + j);

				
				
				if(!OthnameofExDeg.equals("") && !othSubject.equals("") && !othUniInst.equals("") && !othAffuni.equals("") && !othYrofpass.equals("")) {
				if(othquali_id==0) {
					
					
					
					
					oqc.setP_id(p_id);
					oqc.setName_of_exam_degree(OthnameofExDeg);
					oqc.setSubject(othSubject);
					oqc.setName_of_uni_inst(othUniInst);
					oqc.setName_of_aff_uni(othAffuni);
					oqc.setMonth_and_year(othYrofpass);
					oqc.setCreated_by(username);
					oqc.setCreated_date(date);

					 at_pid = (int) sessionHQL.save(oqc);
					sessionHQL.flush();
					sessionHQL.clear();
					
					
				}else {
			

					
					EDU_LMS_TEACHER_OTHER_QUALIFICATION_CHILD quaother = (EDU_LMS_TEACHER_OTHER_QUALIFICATION_CHILD) sessionHQL
							.get(EDU_LMS_TEACHER_OTHER_QUALIFICATION_CHILD.class, othquali_id);
					
					
					quaother.setP_id(p_id);
					quaother.setName_of_exam_degree(OthnameofExDeg);
					quaother.setSubject(othSubject);
					quaother.setName_of_uni_inst(othUniInst);
					quaother.setName_of_aff_uni(othAffuni);
					quaother.setMonth_and_year(othYrofpass);
					quaother.setCreated_by(username);
					quaother.setCreated_date(date);

					
					sessionHQL.update(quaother);
					sessionHQL.flush();
					sessionHQL.clear();
					
				
				}
				}
				
			if(request.getParameter("count_hidden_att_OQ"+j)!=null) {
				
			
				int count_hid_subchild = Integer.parseInt(request.getParameter("count_hidden_att_OQ"+j));
				
				for (int k = 1; k <= count_hid_subchild; k++) {
					String name_of_attachment = request.getParameter("name_of_attachmentOQ" + j + "_" + k);

					MultipartFile attachmentchild = mul.getFile("attachmentDocumentOQ" + j + "_" + k);
					String attachmentDoc_hid = request.getParameter("attachmentDoc_hidOQ" + j + "_" + k);
					
				
					int otherqualificationAtt_id = Integer.parseInt(request.getParameter("otherqualificationAtt_id" + j + "_" + k));

					if(otherqualificationAtt_id==0) {
						
						EDU_LMS_TEACHER_OTHER_QUALIFICATION_SUB_CHILD oqas = new EDU_LMS_TEACHER_OTHER_QUALIFICATION_SUB_CHILD();
						
						String pic = "";
						if (!attachmentchild.isEmpty()) {
							pic = common.fileupload(attachmentchild.getBytes(),
									attachmentchild.getOriginalFilename(), "UploadHardCopy1");
							System.err.println("");
							if (pic != "") {

								oqas.setAttachment(pic);
//						ec.setAttachment_path(namep);
							} else {
								oqas.setAttachment(attachmentDoc_hid);

							}
						}
						if (name_of_attachment != null && !name_of_attachment.equals("")) {
							oqas.setName_of_attachment(name_of_attachment);
						}
						
						oqas.setParent_id(at_pid);

						sessionHQL.save(oqas);
						sessionHQL.flush();
						sessionHQL.clear();

					
					}else {
						
						EDU_LMS_TEACHER_OTHER_QUALIFICATION_SUB_CHILD qas1 = (EDU_LMS_TEACHER_OTHER_QUALIFICATION_SUB_CHILD) sessionHQL
								.get(EDU_LMS_TEACHER_OTHER_QUALIFICATION_SUB_CHILD.class, otherqualificationAtt_id);
						
						


						String pic = "";
						if (!attachmentchild.isEmpty()) {
							pic = common.fileupload(attachmentchild.getBytes(),
									attachmentchild.getOriginalFilename(), "UploadHardCopy1");
							System.err.println("");
							if (pic != "") {

								qas1.setAttachment(pic);
//						ec.setAttachment_path(namep);
							} else {
								qas1.setAttachment(attachmentDoc_hid);

							}
						}
						if (name_of_attachment != null && !name_of_attachment.equals("")) {
							qas1.setName_of_attachment(name_of_attachment);
						}

						qas1.setParent_id(at_pid);

						sessionHQL.update(qas1);
						sessionHQL.flush();
						sessionHQL.clear();
						
					}
				}
				}
	
			}
				

					qc.setStatus(0);
					//sessionHQL.save(qc);// 1
					sessionHQL.flush();
					sessionHQL.clear();
//						
//					}
					tx1.commit();
					//ra.addAttribute("msg", "Data Saved Successfully.");


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
		 
		 
	 
		 @PostMapping(value = "/teacher_saveDraftforregi_Action")
		 public @ResponseBody Map<String, String> teacher_saveDraftforregi_Action(ModelMap Mmap, HttpSession session,
				 HttpServletRequest request) throws IOException, ParseException {
		 
		// System.err.println("IN ACTION RAHUL");
		 
			Date date = new Date();
			String username = session.getAttribute("username").toString();
			String userid = session.getAttribute("userId_for_jnlp").toString();

			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			
			Map<String, String> data = new HashMap<>();
			
			try {
				int p_id=Integer.parseInt(request.getParameter("perentId"));
				
				System.err.println("---------"+p_id);
				String id_for_update = request.getParameter("id_hidden");
				
				String state_reg_no = request.getParameter("state_reg_no");
				String state_board_name = request.getParameter("state_board_name");
				String date_of_reg = request.getParameter("date_of_reg");
				String central_reg_no = request.getParameter("central_reg_no");

				// Integer direct_reg_no =
				// Integer.parseInt(request.getParameter("direct_reg_no"));
				String registration_type = request.getParameter("registration_type");
				String direct_reg_no = request.getParameter("direct_reg_no");
				String direct_date_of_reg = request.getParameter("direct_date_of_reg");
				String name_of_department = request.getParameter("name_of_department");
				
				String adjunct_registration_no = request.getParameter("adjunct_registration_no");
				String adjunct_state_no = request.getParameter("adjunct_state_no");
				String state_validity_upto = request.getParameter("state_validity_upto");
				String direct_validity_upto = request.getParameter("direct_validity_upto");
				String cs_date_of_reg = request.getParameter("cs_date_of_reg");
				String other_date_of_reg = request.getParameter("other_date_of_reg");
				String other_validity_upto = request.getParameter("other_validity_upto");
				
				//EDU_LMS_TEACHER_DTL rd =new EDU_LMS_TEACHER_DTL();
				EDU_LMS_TEACHER_DTL rd = (EDU_LMS_TEACHER_DTL) sessionHQL.get(EDU_LMS_TEACHER_DTL.class,
						Integer.parseInt(id_for_update));
				if(p_id>0) {
				
				Date other_date_of_reg_date=null;
				Date other_validity_upto_date=null;
				Date date_of_reg_date=null;
				Date cs_date_of_reg_date=null;
				Date state_validity_upto_date=null;
				Date direct_date_of_reg_date=null;
				Date direct_validity_upto_date=null;
				
				
			if(!other_date_of_reg.equals("DD/MM/YYYY") && !other_date_of_reg.equals("")) {
				other_date_of_reg_date=datePickerFormat.parse(other_date_of_reg);
			}
			if(!other_validity_upto.equals("DD/MM/YYYY") && !other_validity_upto.equals(""))  {
				other_validity_upto_date=datePickerFormat.parse(other_validity_upto);
			}
			if(!date_of_reg.equals("DD/MM/YYYY") && !date_of_reg.equals("")) {
				date_of_reg_date=datePickerFormat.parse(date_of_reg);
			}
			if(!cs_date_of_reg.equals("DD/MM/YYYY") && !cs_date_of_reg.equals("")) {
				cs_date_of_reg_date=datePickerFormat.parse(cs_date_of_reg);
			}
			if(!state_validity_upto.equals("DD/MM/YYYY") && !state_validity_upto.equals("")) {
				state_validity_upto_date=datePickerFormat.parse(state_validity_upto);
			}
			
			
				//	if(!registration_type.equals("0")) {
			
			
				if (registration_type == null || registration_type.trim().equals("1")) {
					
				//	if(state_reg_no.equals("") && state_reg_no.equals("null") && state_board_name.equals("0") && !date_of_reg_date.equals("DD/MM/YYYY") && !date_of_reg_date.equals("") && state_validity_upto_date.equals("DD/MM/YYYY") && state_validity_upto_date.equals("")) {
					
					rd.setState_reg_no(state_reg_no);
					rd.setState_board_name(state_board_name);
					rd.setDate_of_reg(date_of_reg_date);
// rd.setDate_of_reg(formatter1.parse(dt_reg));
					rd.setCentral_reg_no(central_reg_no);
					rd.setAdjunct_registration_no(adjunct_registration_no);
					rd.setAdjunct_state_no(adjunct_state_no);
					rd.setState_validity_upto(state_validity_upto_date);
					rd.setCs_date_of_reg(cs_date_of_reg_date);
					rd.setOther_date_of_reg(other_date_of_reg_date);
					rd.setOther_validity_upto(other_validity_upto_date);

//					}else {
//						
//						 data.put("msg", "Please Select All The Mandatory Field");
//					}
					
				}

					if (registration_type == null || registration_type.trim().equals("2")) {
						
						
						//if(direct_reg_no.equals("") && direct_reg_no.equals("null") && name_of_department.equals("") && !name_of_department.equals("null") && !direct_date_of_reg_date.equals("DD/MM/YYYY") && !direct_date_of_reg_date.equals("") && direct_validity_upto_date.equals("DD/MM/YYYY") && direct_validity_upto_date.equals("")) {
					
						
						
					if(!direct_date_of_reg.equals("DD/MM/YYYY") && !direct_date_of_reg.equals("")) {
						direct_date_of_reg_date=datePickerFormat.parse(direct_date_of_reg);
					}
					if(!direct_validity_upto.equals("DD/MM/YYYY") && !direct_validity_upto.equals("")) {
					direct_validity_upto_date=datePickerFormat.parse(direct_validity_upto);
					}
					
//					if(!direct_reg_no.equals("") && !direct_reg_no.equals("null") && !direct_date_of_reg_date.equals("DD/MM/YYYY") && !direct_date_of_reg_date.equals("") && !direct_validity_upto_date.equals("DD/MM/YYYY") && !direct_validity_upto_date.equals("") && !name_of_department.equals("") && !name_of_department.equals("null")){		
					
					
					rd.setDirect_reg_no((direct_reg_no));
					rd.setDirect_date_of_reg(direct_date_of_reg_date);
					rd.setName_of_department(name_of_department);
					rd.setDirect_validity_upto(direct_validity_upto_date);
					
					
//						data.put("msg", "Please Select All The Mandatory Field");
//
//				return data;
//			}
					}			
				rd.setRegistration_type(registration_type);
				
				rd.setStatus(0);
				sessionHQL.update(rd);// 1
				 data.put("msg", "Data Save As Draft Successfully");
				sessionHQL.flush();
				sessionHQL.clear();
				
//					}else {
//						
//						data.put("msg", "Please Select All The Mandatory Field");
//						
//					}
				
				}
//						
//					}
					tx.commit();
					//ra.addAttribute("msg", "Data Saved Successfully.");


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
		 
		 
		 @PostMapping(value = "/teacher_saveDraftforacademic_Action")
		 public @ResponseBody Map<String, String> teacher_saveDraftforacademic_Action(ModelMap Mmap, HttpSession session,
				 HttpServletRequest request,MultipartHttpServletRequest mul) throws IOException, ParseException {
		 
	
		 
			Date date = new Date();
			String username = session.getAttribute("username").toString();
			String userid = session.getAttribute("userId_for_jnlp").toString();

			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			
			Map<String, String> data = new HashMap<>();
			
			System.err.println("data ------  "+data);
			
			try {
				
//profession/academic experience
				
				EDU_LMS_TEACHER_EXPERIENCE_CHILD od = new EDU_LMS_TEACHER_EXPERIENCE_CHILD();
				
				int count_hidden_att = Integer.parseInt(request.getParameter("count_hidden_att"));
				
				for (int i = 1; i <= count_hidden_att; i++) {
					
					int profession_id=Integer.parseInt(request.getParameter("profession_id"+i));
					
					int p_id=Integer.parseInt(request.getParameter("perentId"));
					String id_for_update = request.getParameter("id_hidden");
					if(profession_id==0) {
						
						String institute_name = request.getParameter("institute_name" + i);
						String depart_name = request.getParameter("depart_name" + i);
						String desig = request.getParameter("desig" + i);
						String employment_type = request.getParameter("employment_type" + i);
						String from_date = request.getParameter("from_date" + i);
						String to_date = request.getParameter("to_date" + i);
						String honorarium = request.getParameter("honorarium" + i);

						String du = "upload_file" + i;
						String doc = gd(request, mul, session, du);
						
						
						Date from_date_dt=null;
						Date to_date_dt=null;
						
						
						if(!from_date.equals("DD/MM/YYYY") && !from_date.equals("")) {
							from_date_dt=datePickerFormat.parse(from_date);
						}
						if(!to_date.equals("DD/MM/YYYY") && !to_date.equals("")) {
							to_date_dt=datePickerFormat.parse(to_date);
						}

						if((!institute_name.equals("")) && (!institute_name.equals("null")) && (!depart_name.equals("0"))  && (!desig.equals("0")) && (!employment_type.equals("0")) && (!from_date.equals("DD/MM/YYYY")) && (!from_date.equals("")) && (!to_date.equals("DD/MM/YYYY")) && (!to_date.equals("")) && (!honorarium.equals("0"))) {
		
							
							
							od.setInstitute_name(institute_name);
							od.setDepart_name(depart_name);
							od.setDesig(desig);
							od.setEmployment_type(employment_type);
							od.setFrom_date(from_date_dt);
							od.setTo_date(to_date_dt);
							od.setUpload_file(doc);
							od.setCreated_by(username);
							od.setCreated_date(date);
							od.setHonorarium(honorarium);
							od.setP_id(p_id);

							sessionHQL.save(od);// 1
							data.put("msg", "Data Save As Draft Successfully");
							sessionHQL.flush();
							sessionHQL.clear();
	
					}else {
						
						data.put("msg", "Please Select All The Mandatory Field");
						
					}
						
					}else {
						
						EDU_LMS_TEACHER_EXPERIENCE_CHILD od2 = (EDU_LMS_TEACHER_EXPERIENCE_CHILD) sessionHQL
								.get(EDU_LMS_TEACHER_EXPERIENCE_CHILD.class, profession_id);
						
						//EDU_LMS_TEACHER_EXPERIENCE_CHILD od2 = new EDU_LMS_TEACHER_EXPERIENCE_CHILD();
						
						String institute_name = request.getParameter("institute_name" + i);
						String depart_name = request.getParameter("depart_name" + i);
						String desig = request.getParameter("desig" + i);
						String employment_type = request.getParameter("employment_type" + i);
						String from_date = request.getParameter("from_date" + i);
						String to_date = request.getParameter("to_date" + i);
						String honorarium = request.getParameter("honorarium" + i);

						String upload_file_hidT = request.getParameter("upload_file_hid" + i);

						String du = "upload_file" + i;
						String doc = gd(request, mul, session, du);
						
						

						
						od2.setInstitute_name(institute_name);
						od2.setDepart_name(depart_name);
						od2.setDesig(desig);
						od2.setEmployment_type(employment_type);
						od2.setFrom_date(datePickerFormat.parse(from_date));
						od2.setTo_date(datePickerFormat.parse(to_date));
						od2.setHonorarium(honorarium);
						// od2.setUpload_file(doc);

						String pic = "";
						if (doc != "") {

							od2.setUpload_file(doc);
						} else {
							od2.setUpload_file(upload_file_hidT);

						}

						od2.setCreated_by(username);
						od2.setCreated_date(date);
						od2.setP_id(Integer.parseInt(id_for_update));

						sessionHQL.update(od2);// 1
						data.put("msg", "Data Save As Draft Successfully");
						sessionHQL.flush();
						sessionHQL.clear();
					}
				}

					tx.commit();
					//ra.addAttribute("msg", "Data Saved Successfully.");


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
		 
		 
		 
		 @PostMapping(value = "/teacher_saveDraftforexperience_Action")
		 public @ResponseBody Map<String, String> teacher_saveDraftforexperience_Action(ModelMap Mmap, HttpSession session,
				 HttpServletRequest request,MultipartHttpServletRequest mul) throws IOException, ParseException {
		 
		// System.err.println("IN ACTION RAHUL");
		 
			Date date = new Date();
			String username = session.getAttribute("username").toString();
			String userid = session.getAttribute("userId_for_jnlp").toString();

			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			
			Map<String, String> data = new HashMap<>();
			
			try {
				
				EDU_LMS_DEGREE_AND_SUPPORT_DOC_CHILD ds = new EDU_LMS_DEGREE_AND_SUPPORT_DOC_CHILD();
				
				int count_hidden_doc = Integer.parseInt(request.getParameter("count_hidden_doc"));
				int p_id=Integer.parseInt(request.getParameter("perentId"));
				String id_for_update = request.getParameter("id_hidden");
				
				for (int i = 1; i <= count_hidden_doc; i++) {
					String doc_name = request.getParameter("doc_name" + i);
					String doc_id = request.getParameter("doc_id" + i);
					String du = "upload_doc" + i;
					String doc = gd(request, mul, session, du);

//					if (doc_name == null || doc_name.equals("0")) {
//						ra.addAttribute("msg", "Please Select Document Name In Row " + i);
//						break;
//						//return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
//					if (doc_id == null || doc_id.equals("0")) {
//						ra.addAttribute("msg", "Please Select Document Type In Row " + i);
//						break;
//						//return new ModelAndView("redirect:Teacher_dtl_Url");
//					}
					int add_quali=Integer.parseInt(request.getParameter("add_quali"+i));
					
					if(add_quali==0) {
				
//						if (doc.isEmpty()) {
//							ra.addAttribute("msg", "Please Select Document In Row " + i);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
	//
//						}
						
						
						if(!doc_name.equals("0") && !doc_id.equals("0") && !doc.equals("")) {
						

						ds.setDoc_name(doc_name);
						ds.setDoc_id(Integer.parseInt(doc_id));
						ds.setUpload_doc(doc);
						ds.setCreated_by(username);
						ds.setCreated_date(date);
						ds.setP_id(p_id);

						sessionHQL.save(ds);// 2
						data.put("msg", "Data Save As Draft Successfully");
						sessionHQL.flush();
						sessionHQL.clear();
						
						}else {
							
							data.put("msg", "Please Select All The Mandatory Field");
						}
					
					}else {
						
						EDU_LMS_DEGREE_AND_SUPPORT_DOC_CHILD ds2 = (EDU_LMS_DEGREE_AND_SUPPORT_DOC_CHILD) sessionHQL
								.get(EDU_LMS_DEGREE_AND_SUPPORT_DOC_CHILD.class, add_quali);
						
						
						String upload_docT = request.getParameter("doc_upload_hid" + i);
		
//						if (doc.isEmpty()) {
//							ra.addAttribute("msg", "Please Select Document In Row " + i);
//							return new ModelAndView("redirect:Teacher_dtl_Url");
	//
//						}

						ds2.setDoc_name(doc_name);
						ds2.setDoc_id(Integer.parseInt(doc_id));
						// ds2.setUpload_doc(doc);

						String pic = "";

						if (doc != "") {

							ds2.setUpload_doc(doc);
						} else {
							ds2.setUpload_doc(upload_docT);

						}

						ds2.setCreated_by(username);
						ds2.setCreated_date(date);
						ds2.setP_id(Integer.parseInt(id_for_update));

						sessionHQL.update(ds2);// 2
						data.put("msg", "Data Save As Draft Successfully");
						sessionHQL.flush();
						sessionHQL.clear();
				
					}
		
				}

					
//						
//					}
					tx.commit();
					//ra.addAttribute("msg", "Data Saved Successfully.");


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
		 	 
		 
		 @PostMapping(value = "/teacher_saveDraftforacademicexperience_Action")
		 public @ResponseBody Map<String, String> teacher_saveDraftforacademicexperience_Action(ModelMap Mmap, HttpSession session,
				 HttpServletRequest request,MultipartHttpServletRequest mul) throws IOException, ParseException {
		 
	
		 
			Date date = new Date();
			String username = session.getAttribute("username").toString();
			String userid = session.getAttribute("userId_for_jnlp").toString();

			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			
			Map<String, String> data = new HashMap<>();
					
			
			try {
				
//Type Of experience
				
				EDU_LMS_TEACHER_ACADEMIC_EXPERIENCE_CHILD od = new EDU_LMS_TEACHER_ACADEMIC_EXPERIENCE_CHILD();
				
				int count_hidden_att_pro = Integer.parseInt(request.getParameter("count_hidden_att_pro"));
				
				for (int i = 1; i <= count_hidden_att_pro; i++) {
				
				
				
					int academic_exp=Integer.parseInt(request.getParameter("academic_exp"+i));
					
					int p_id=Integer.parseInt(request.getParameter("perentId"));
					
					String id_for_update = request.getParameter("id_hidden");
					
					
					
					
					if(academic_exp==0) {
						
						
						String organization_name = request.getParameter("organization_name" + i);
						String from_date_pro = request.getParameter("from_date_pro" + i);
						String to_date_pro = request.getParameter("to_date_pro" + i);
						String designation_name = request.getParameter("designation_name" + i);
						
						
						int type_of_exp = Integer.parseInt(request.getParameter("type_of_exp"+i));
						String experienceother = request.getParameter("experienceother"+i);
						
						
						
						if (organization_name == null || organization_name.trim().equals("")) {
							data.put("msg", "Please Enter Institute Name In Row " + i);
							return data;
						}
						if (validation.maxlengthcheck50(organization_name) == false) {
							data.put("msg", "Institute Name " + validation.MaxlengthcheckMSG50);
							return data;
						}
						if (from_date_pro == null || from_date_pro.trim().equals("") || from_date_pro.equals("DD/MM/YYYY")) {
							data.put("msg", "Please Enter The From Date In Row " + i);
							return data;
						}
						if (to_date_pro == null || to_date_pro.trim().equals("") || to_date_pro.equals("DD/MM/YYYY")) {
							data.put("msg", "Please Enter The To Date In Row " + i);
							return data;
						}
						if (datePickerFormat.parse(to_date_pro).compareTo(datePickerFormat.parse(from_date_pro)) < 0) {
							data.put("msg", "To Date Should Be Greater Than Or Equal To From Date In Row " + i);
							return data;
						}
						if (designation_name == null || designation_name.trim().equals("")) {
							data.put("msg", "Please Enter Designation Name In Row " + i);
							return data;
						}
						if (validation.maxlengthcheck50(designation_name) == false) {
							data.put("msg", "Designation Name " + validation.MaxlengthcheckMSG50);
							return data;
						}
						
						
						if ( type_of_exp == 0) {
							data.put("msg", "Please Select Type Of Experience");
							return data;
						}
						


							
							od.setOrganization_name(organization_name);
							od.setFrom_date_pro(datePickerFormat.parse(from_date_pro));
							od.setTo_date_pro(datePickerFormat.parse(to_date_pro));
							od.setDesignation_name(designation_name);
							od.setType_of_experience(type_of_exp);
							od.setP_id(p_id);
							od.setCreated_by(username);
							od.setCreated_date(date);
							
							if(type_of_exp== -1) {
								od.setOthertype_of_exp(experienceother);
							}else {
								od.setOthertype_of_exp("");
							}

							sessionHQL.save(od);// 1
							data.put("msg", "Data Save As Draft Successfully");
							sessionHQL.flush();
							sessionHQL.clear();
	
										
					}else {
						
						EDU_LMS_TEACHER_ACADEMIC_EXPERIENCE_CHILD od2 = (EDU_LMS_TEACHER_ACADEMIC_EXPERIENCE_CHILD) sessionHQL
								.get(EDU_LMS_TEACHER_ACADEMIC_EXPERIENCE_CHILD.class, academic_exp);
						
						//EDU_LMS_TEACHER_EXPERIENCE_CHILD od2 = new EDU_LMS_TEACHER_EXPERIENCE_CHILD();
						String organization_name = request.getParameter("organization_name" + i);
						String from_date_pro = request.getParameter("from_date_pro" + i);
						String to_date_pro = request.getParameter("to_date_pro" + i);
						String designation_name = request.getParameter("designation_name" + i);
						int type_of_exp = Integer.parseInt(request.getParameter("type_of_exp"+i));
						String experienceother = request.getParameter("experienceother"+i);
						
						
						
						od2.setOrganization_name(organization_name);
						od2.setFrom_date_pro(datePickerFormat.parse(from_date_pro));
						od2.setTo_date_pro(datePickerFormat.parse(to_date_pro));
						od2.setDesignation_name(designation_name);
						od2.setType_of_experience(type_of_exp);
						od2.setP_id(Integer.parseInt(id_for_update));
						od2.setModified_by(username);
						od2.setModified_date(date);
						if(type_of_exp== -1) {
							od2.setOthertype_of_exp(experienceother);
						}else {
							od2.setOthertype_of_exp("");
						}

						sessionHQL.update(od2);// 1
						data.put("msg", "Data Save As Draft Successfully");
						sessionHQL.flush();
						sessionHQL.clear();
					}
				}	
				

					tx.commit();
					//ra.addAttribute("msg", "Data Saved Successfully.");


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
		 
	
		 
		 @RequestMapping(value = "/quali_attachmentother", method = RequestMethod.POST)
			public @ResponseBody   ArrayList<ArrayList<String>> quali_attachmentother( HttpSession session,String parent_id) {
			 String userid = session.getAttribute("userId_for_jnlp").toString();
			 ArrayList<ArrayList<String>> list =tdao.getPopup_Datalistother (userid,parent_id);
			 System.err.println("-----2209-------list"+list);
				return list;
			}
			
		 
			
			@SuppressWarnings("null")
	@RequestMapping(value = "/getDownloadUrlforDocAttsubother")
	public ModelAndView getDownloadUrlforDocAttsubother(@RequestParam(value = "msg", required = false) String msg, String pageUrl,
			String upload_docattsubother, ModelMap model, HttpServletRequest request, HttpSession session,
			HttpServletResponse response) throws IOException {
		System.out.println("upload_doc2 "+upload_docattsubother);
		String url = pageUrl;
		String EXTERNAL_FILE_PATH = "";
		String enckey = "commonPwdEncKeys";
		EXTERNAL_FILE_PATH = tdao.getFilePathQueryForDocAttSubother(upload_docattsubother);
//	model.put("downloadid", downloadid);
		model.put("msg", "Sorry ! The file you are looking for does not exist.");

		if (EXTERNAL_FILE_PATH != "") {
			File file = null;
			file = new File(EXTERNAL_FILE_PATH);
			try {
				if (!file.exists()) {

					return new ModelAndView(url);
				}
			} catch (Exception exception) {
			}

			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
			response.setContentLength((int) file.length());
			InputStream inputStream = null;
			try {
				inputStream = new BufferedInputStream(new FileInputStream(file));
				FileCopyUtils.copy(inputStream, response.getOutputStream());
			} catch (FileNotFoundException e) {
				// e.printStackTrace();
			}
		}
		return new ModelAndView(url);
	}
			
			@GetMapping(value = "/Search_teacher_detail_princi_url")
			public ModelAndView Search_teacher_detail_princi_url(ModelMap model, HttpSession session,
					@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

					if(request.getHeader("Referer") == null ) { 
					//	session.invalidate();
						model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						return new ModelAndView("redirect:/login");
						}
						String roleid1 = session.getAttribute("roleid").toString();
						Boolean val = roledao.ScreenRedirect("Search_teacher_detail_princi_url", roleid1);		
						if(val == false) {
						return new ModelAndView("AccessTiles");
					}
				String userid = session.getAttribute("userId_for_jnlp").toString();
				String role = session.getAttribute("role").toString();
				System.out.println("role "+role);
				if(role.equals("University_NCH")) {
					model.put("logininfo", common.getAllInfoLogin(sessionFactory, userid).get(0).getUniversity_id());
				}else if(role.equals("Institute_NCH")) {
					model.put("logininfo", common.getAllInfoLogin(sessionFactory, userid).get(0).getInstitute_id());
					}
				
				
				
				
				model.put("msg", msg);
				model.put("getgenderList", common.getgenderList(sessionFactory));
				model.put("getUniverCityList", tdao.getUniversitylist());
				model.put("getinstitutelist", tdao.getinstitutelist(userid));
				model.put("getMedStateName", common.getMedStateName(sessionFactory));
				model.put("getMedDistrictName", common.getMedDistrictName(sessionFactory));
				model.put("getInsttituteList", common.getapp_instituteNameList(sessionFactory));
				model.put("getUniList", common.getUniverCityList(sessionFactory));
				return new ModelAndView("Search_Teacher_dtl_princi_Tiles");

			}
			
			
			@PostMapping("/getFilterTeacher_dtl_princi_data")
			public @ResponseBody List<Map<String, Object>> getFilterTeacher_dtl_princi_data(HttpSession sessionUserId, int startPage,
					int pageLength, String Search, String orderColunm, String orderType, String ayush_id, String teacher_code,
					String first_name, String gender, String date_of_birth, String per_state, String per_district,
					String per_village, String state_reg_no, String state_board_name, String date_of_reg, String central_reg_no,
					String yr_of_exp ,String institute,String university,String status) {
				String role = sessionUserId.getAttribute("role").toString();
				String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
				String inst_id =   common.getInstIdfromUserid(sessionFactory,userid).get(0);
				System.err.println("inst_id19 jan------>   "+inst_id);
				return tdao.DataTableTeacher_dtl_princi_DataList(startPage, pageLength, Search, orderColunm, orderType, ayush_id,
						teacher_code, first_name, gender, date_of_birth, per_state, per_district, per_village, state_reg_no,
						state_board_name, date_of_reg, central_reg_no, role, userid, yr_of_exp,institute,university,status,inst_id);

			}

			@PostMapping("/DataTableTeacher_dtl_principal_DataTotalCount")
			public @ResponseBody long DataTableTeacher_dtl_principal_DataTotalCount(HttpSession sessionUserId, String Search, String ayush_id,
					String teacher_code, String first_name, String gender, String date_of_birth, String per_state,
					String per_district, String per_village, String state_reg_no, String state_board_name, String date_of_reg,
					String central_reg_no, String yr_of_exp,String institute,String university,String status) {
				String role = sessionUserId.getAttribute("role").toString();
				String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
				String inst_id =   common.getInstIdfromUserid(sessionFactory,userid).get(0);
				System.err.println("inst_id19 jan------>   "+inst_id);
				return tdao.DataTableTeacher_dtl_princi_DataTotalCount(Search, ayush_id, teacher_code, first_name, gender,
						date_of_birth, per_state, per_district, per_village, state_reg_no, state_board_name, date_of_reg,
						central_reg_no, role, userid, yr_of_exp,institute,university,status,inst_id);
			}
						
		
			@RequestMapping(value = "faculty_principal_Approve_url", method = RequestMethod.POST)
			public ModelAndView faculty_principal_Approve_url(@ModelAttribute("Acceptid") int id,HttpSession session,ModelMap model
					) {


			List<String> liststr = new ArrayList<String>();

			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();

			 Date date = new Date();

				String username = session.getAttribute("username").toString();
				String hqlUpdate2 = "update from EDU_LMS_TEACHER_DTL set principal_status='1',modified_by=:modified_by,modified_date=:modified_date"
						+ " where id=:id  ";
				int app2 = sessionHQL.createQuery(hqlUpdate2).setInteger("id", id).setString("modified_by", username)
						.setDate("modified_date", new Date()).executeUpdate();
			 
				tx.commit();
				sessionHQL.close();
				if (app2 > 0 ) {
					liststr.add("Approved Successfully.");
				} else {
					liststr.add("Not Approved.");
				}
				model.put("msg", liststr.get(0));

			 return new ModelAndView("Search_Teacher_dtl_princi_Tiles");
			}

			@RequestMapping(value = "faculty_principal_Reject_url", method = RequestMethod.POST)
			public ModelAndView faculty_principal_Reject_url(@ModelAttribute("Rejectid") int id,HttpSession session,ModelMap model
					) {

			List<String> liststr = new ArrayList<String>();
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();


			 Date date = new Date();

			 	String username = session.getAttribute("username").toString();
				String hqlUpdate2 = "update from EDU_LMS_TEACHER_DTL set principal_status='3',status='3',modified_by=:modified_by,modified_date=:modified_date"
						+ " where id=:id  ";
				int app2 = sessionHQL.createQuery(hqlUpdate2).setInteger("id", id).setString("modified_by", username)
						.setDate("modified_date", new Date()).executeUpdate();
			 
				tx.commit();
				sessionHQL.close();
				if (app2 > 0 ) {
					liststr.add("Enable To Edit Successfully.");
				} else {
					liststr.add("Not Enable To Edit.");
				}
				model.put("msg", liststr.get(0));

			 
				 return new ModelAndView("Search_Teacher_dtl_princi_Tiles");

			}
			
			@RequestMapping(value = "faculty_principal_approveedit_url", method = RequestMethod.POST)
			public ModelAndView faculty_principal_approveedit_url(@ModelAttribute("appeditid") int id,HttpSession session,ModelMap model
					) {

			List<String> liststr = new ArrayList<String>();
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();


			 Date date = new Date();

			 	String username = session.getAttribute("username").toString();
				String hqlUpdate2 = "update from EDU_LMS_TEACHER_DTL set principal_status='4',status='4',modified_by=:modified_by,modified_date=:modified_date"
						+ " where id=:id  ";
				int app2 = sessionHQL.createQuery(hqlUpdate2).setInteger("id", id).setString("modified_by", username)
						.setDate("modified_date", new Date()).executeUpdate();
			 
				tx.commit();
				sessionHQL.close();
				if (app2 > 0 ) {
					liststr.add(" Edit Successfully.");
				} else {
					liststr.add("Not Edited.");
				}
				model.put("msg", liststr.get(0));

			 
				 return new ModelAndView("Search_Teacher_dtl_princi_Tiles");

			}


			 @RequestMapping(value = "/getprofessiondata", method = RequestMethod.POST)
				public @ResponseBody   ArrayList<ArrayList<String>> getprofessiondata(String main_id,String val,HttpSession session) {
				 
				 ArrayList<ArrayList<String>> list=new ArrayList<>();
				
				 String userid = session.getAttribute("userId_for_jnlp").toString();

				 System.out.println("val "+val);
						 if(val.equals("1")) {
								
							 list = tdao.getaddmoredata3(Integer.parseInt(main_id));
					
						}
						 if(val.equals("2")) {
								
							 list = tdao.getaddmoredata4(Integer.parseInt(main_id));
					
						}
						 
					
						if(val.equals("3")) {
							
							 list = tdao.getaddmoredata1(Integer.parseInt(main_id));
						}
						if(val.equals("4")) {
							
							 list = tdao.getaddmoredata2(Integer.parseInt(main_id));
						}
						
			
					return list;
				}
		 
		 
	 
			 @RequestMapping(value = "/getaddmoreattchfetch", method = RequestMethod.POST)
				public @ResponseBody   ArrayList<ArrayList<String>> getaddmoreattchfetch(String parent_id,String val,HttpSession session) {
				 
				 ArrayList<ArrayList<String>> list=new ArrayList<>();
				
				 String userid = session.getAttribute("userId_for_jnlp").toString();

				 
						 if(val.trim().equals("1")) {
							
							list= tdao.getqualificationchildAttchment(userid);
					
						}
			
					return list;
				}
			 
			 
			 @RequestMapping(value = "/getaddmoreattchfetchother", method = RequestMethod.POST)
				public @ResponseBody   ArrayList<ArrayList<String>> getaddmoreattchfetchother(String main_id,String val,HttpSession session) {
				 
				 ArrayList<ArrayList<String>> list=new ArrayList<>();
				
				 String userid = session.getAttribute("userId_for_jnlp").toString();

				 
						 if(val.trim().equals("2")) {
							
							list= tdao.getotherqualisubchild(Integer.parseInt(main_id));
							
			
						}
					
	
					return list;
				}
			 
			 
			 
			 
				@GetMapping(value = "/Search_teacher_report")
				public ModelAndView Search_teacher_url(ModelMap model, HttpSession session,
						@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {

					if(request.getHeader("Referer") == null ) { 
						session.invalidate();
						model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
						return new ModelAndView("redirect:/login");
						}
						String roleid1 = session.getAttribute("roleid").toString();
						Boolean val = roledao.ScreenRedirect("Search_teacher_report", roleid1);		
						if(val == false) {
							return new ModelAndView("AccessTiles");
					}
					String userid = session.getAttribute("userId_for_jnlp").toString();
//					String role = session.getAttribute("role").toString();
//					System.out.println("role "+role);
//					if(role.equals("University_NCH")) {
//						model.put("logininfo", common.getAllInfoLogin(sessionFactory, userid).get(0).getUniversity_id());
//					}else if(role.equals("Institute_NCH")) {
//						model.put("logininfo", common.getAllInfoLogin(sessionFactory, userid).get(0).getInstitute_id());
//						}
					model.put("msg", msg);
					model.put("getUniverCityList", cdao.getUniversityNchlist());
					model.put("getinstitutelist", SSRDao.getinstitutelist(userid));
					model.put("getgenderList", common.getgenderList(sessionFactory));
					model.put("getSubjectList", common.getSubjectList(sessionFactory));
					model.put("TypeOfDegree", common.gettype_of_degree(sessionFactory));
					model.put("getMedStateName", common.getMedStateName(sessionFactory));
					
					String role = session.getAttribute("roleid").toString();
					System.err.println("--------role19jan"+role);
					if (role.equals("19")) {//uni nch
						String uni_id =   common.getUniIdfromUserid(sessionFactory,userid).get(0);
						System.err.println("uni_id------>   "+uni_id);
						model.put("uni_id",uni_id);
						}
						
						if (role.equals("21")) {//inst nch
							String inst_id =   common.getInstIdfromUserid(sessionFactory,userid).get(0);
							System.err.println("inst_id------>   "+inst_id);
							model.put("inst_id",inst_id);
						}
						if (role.equals("30")) {//inst nch
							String state_id =   common.getStateIdfromUserid(sessionFactory,userid).get(0);
							System.err.println("state_id------>   "+state_id);
							model.put("state_id",state_id);
						}
					
					
					
					
				//	model.put("getstatelist", trdao.getStatelistlogin(userid));
					
//					model.put("getgenderList", common.getgenderList(sessionFactory));
//					model.put("getUniverCityList", tdao.getUniversitylist());
//					model.put("getinstitutelist", tdao.getinstitutelist(userid));
//					model.put("getMedStateName", common.getMedStateName(sessionFactory));
//					model.put("getMedDistrictName", common.getMedDistrictName(sessionFactory));
//					model.put("getInsttituteList", common.getapp_instituteNameList(sessionFactory));
//					model.put("getUniList", common.getUniverCityList(sessionFactory));
					return new ModelAndView("Search_Teacher_Tiles");

				}
				
				
				
				@PostMapping("/getFilterTeacher_data")
				public @ResponseBody List<Map<String, Object>> getFilterTeacher_data(HttpSession sessionUserId, int startPage,
						int pageLength, String Search, String orderColunm, String orderType, String ayush_id, String teacher_code,
						String name, String university_id,String institute_id,String ug_pg,String subject,String gender, String date_of_birth, String experience,String state, String district,String village,String othquali)
						 {
					String role = sessionUserId.getAttribute("role").toString();
					String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
					return trdao.DataTableTeacher_DataList(startPage, pageLength, Search, orderColunm, orderType, ayush_id,
							teacher_code, name, university_id,institute_id,ug_pg,subject,gender, date_of_birth, experience,state, district, village,othquali, role, userid);

				}

				@PostMapping("/DataTableTeacher__DataTotalCount")
				public @ResponseBody long DataTableTeacher__DataTotalCount(HttpSession sessionUserId, String Search, String ayush_id, String teacher_code,
						String name, String university_id,String institute_id,String ug_pg,String subject,String gender, String date_of_birth, String experience,String state, String district,String village,String othquali) {
					String role = sessionUserId.getAttribute("role").toString();
					String userid = sessionUserId.getAttribute("userId_for_jnlp").toString();
					return trdao.DataTableTeacher_DataTotalCount(Search, ayush_id, teacher_code, name,university_id,institute_id,ug_pg,subject,gender, date_of_birth, experience,state, district, village,othquali,role, userid);
				}
				
				
				
				
				
				
				@RequestMapping(value = "/Teacher_Report_PDF", method = RequestMethod.POST)
				public ModelAndView Teacher_Report_PDF(HttpSession session, HttpServletRequest request,RedirectAttributes ra)
						throws ParseException {

					String username = session.getAttribute("username").toString();
					String role = session.getAttribute("role").toString();
					String userid = session.getAttribute("userId_for_jnlp").toString();

					String ayush_id = request.getParameter("ayush_id1");
					String teacher_code = request.getParameter("teacher_code1");
					String faculty_name = request.getParameter("faculty_name1");
					
					String university_id = request.getParameter("university_id1");
					
					String institute_id = request.getParameter("institute_id1");
					String ug_pg = request.getParameter("ug_pg1");
					String subject = request.getParameter("subject1");
					
					String gender = request.getParameter("gender1");
					String date_of_birth = request.getParameter("dob1");
					String experience = request.getParameter("exp1");
					
					String state_name = request.getParameter("state1");
					String district = request.getParameter("district1");
					String village = request.getParameter("village1");
					
					String othquali = request.getParameter("othquali1");
					
					
					
					
					String stafflevel1 = session.getAttribute("roleStaff_lvl").toString();
//					String userId = session.getAttribute("userId_for_jnlp").toString();
//					String institute_uid = cd.getinstIDfromuserID(Integer.parseInt(userId)).get(0).get(0);
					List<ArrayList<String>> list = new ArrayList<ArrayList<String>>(); 
					
					
					list=trdao.getTeacher_Report_Excel(ayush_id,teacher_code,faculty_name,university_id, institute_id, ug_pg,subject,gender,date_of_birth,experience,state_name,district,village,othquali,role,userid);
					
					
//		            if(stafflevel1.toUpperCase().equals("NCH")) {
//					 nonljhjh = SSRDao.getStudent_Registration_Report_Excel(university_id, institute_id,
//							name, ayush_id, gender, date_of_birth);}
		            
//		            if(stafflevel1.toUpperCase().equals("NCISM")) {
//		    			 nonljhjh = SSNCISM.getStudent_Registration_Report_Excel_NCISM(university_id, institute_id,
//		    					name, ayush_id, gender, date_of_birth);}
		                
		            

					int total = list.size();
					List<String> TH = new ArrayList<String>();

					TH.add("Ser No."); //0
					TH.add("Ayush Id");//1
					TH.add("Teacher Code");//2
					TH.add("Faculty Name");//3
					TH.add("University");//4
					TH.add("Institute");//5
					TH.add("UG/PG");//6
					TH.add("Subject");//7
					TH.add("Gender");//8
					TH.add("Date Of Birth");//9
					TH.add("Experience");//10
					TH.add("State");//11
					TH.add("District");//12
					TH.add("City");//13
					TH.add("Other Qualification");//14
					
					String Heading = "\nIn Inspection";
					
						  return new ModelAndView(new Teacher_Report_PDF("L", TH, Heading, username, total ), "userList", list);
							
					
			}
				
				
				 @RequestMapping(value = "/Teacher_Report_Excel", method = RequestMethod.POST)
					public ModelAndView Teacher_Report_Excel(HttpSession session, HttpServletRequest request)
							throws ParseException {

//						String username = session.getAttribute("username").toString();
						String role = session.getAttribute("role").toString();
						String userid = session.getAttribute("userId_for_jnlp").toString();

						String ayush_id = request.getParameter("ayush_id2");
						String teacher_code = request.getParameter("teacher_code2");
						String faculty_name = request.getParameter("faculty_name2");
						
						String university_id = request.getParameter("university_id2");
						
						String institute_id = request.getParameter("institute_id2");
						String ug_pg = request.getParameter("ug_pg2");
						String subject = request.getParameter("subject2");
						
						String gender = request.getParameter("gender2");
						String date_of_birth = request.getParameter("dob2");
						String experience = request.getParameter("exp2");
						
						String state_name = request.getParameter("state2");
						String district = request.getParameter("district2");
						String village = request.getParameter("village2");
						
						String othquali = request.getParameter("othquali2");
						
						String stafflevel1 = session.getAttribute("roleStaff_lvl").toString();
//						String userId = session.getAttribute("userId_for_jnlp").toString();
//						String institute_uid = cd.getinstIDfromuserID(Integer.parseInt(userId)).get(0).get(0);
						List<ArrayList<String>> list = new ArrayList<ArrayList<String>>(); 
						
						
						list=trdao.getTeacher_Report_Excel(ayush_id,teacher_code,faculty_name,university_id, institute_id, ug_pg,subject,gender,date_of_birth,experience,state_name,district,village,othquali,role,userid);
						

//						ArrayList<ArrayList<String>> non = new ArrayList<ArrayList<String>>();

						int total = list.size();
						List<String> TH = new ArrayList<String>();

						TH.add("Ser No."); //0
						TH.add("Ayush Id");//1
						TH.add("Teacher Code");//2
						TH.add("Faculty Name");//3
						TH.add("University");//4
						TH.add("Institute");//5
						TH.add("UG/PG");//6
						TH.add("Subject");//7
						TH.add("Gender");//8
						TH.add("Date Of Birth");//9
						TH.add("Experience");//10
						TH.add("State");//11
						TH.add("District");//12
						TH.add("City");//13
						TH.add("Other Qualification");//14
						
						String Heading = "\nIn Inspection";
						
						String username = session.getAttribute("username").toString();
						return new ModelAndView(new Student_Registration_Excel("L", TH, list, Heading, username), "userList",
								list);
					}
				 
				 
				 
//					FACULTY TRANSFER FLOW
					@RequestMapping(value = "/faculty_transfer_action", method = RequestMethod.POST)
					public ModelAndView faculty_transfer_action(RedirectAttributes ra,EDU_LMS_FACULTY_TRANSFER ft,
							BindingResult result,HttpServletRequest request, ModelMap model, HttpSession Session, Principal principal,
							
							@RequestParam(value = "remark", required = false) String remark,RedirectAttributes ra1) throws ParseException {

						
						List<String> liststr = new ArrayList<String>();
						
						DateFormat datePickerFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
						String username = Session.getAttribute("username").toString();
						Date date = new Date();
						
						
						try {

						String userid = Session.getAttribute("userId_for_jnlp").toString();
						
//						
						ArrayList<String> list = trdao.getfromdatelogoninfo(Integer.parseInt(userid)).get(0);
						System.err.println("list 20 jan---"+list);
						
						//ArrayList<String> list1 = trdao.getfromdatelogoninfo(Integer.parseInt(userid)).get(1);
						
						
//						

						Date other_date_of_reg_date=null;
					
						//if(!other_date_of_reg.equals("DD/MM/YYYY") && !other_date_of_reg.equals("")) {
							other_date_of_reg_date=datePickerFormat.parse(list.get(0));
							
							Integer p_id=null;
							
							p_id=Integer.parseInt(list.get(1));
							System.err.println("p_id---30/01---"+p_id);
					//	}
						
						UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
	                	String university = String.valueOf(ea.getUniversity_id());
						ft.setUniversity_id(Integer.parseInt(university));
						//p.setUniversity_id(Integer.parseInt(university));
						
						String institude = String.valueOf(ea.getInstitute_id());
						ft.setInstitute_id(Integer.parseInt(institude));
						
						String state = String.valueOf(ea.getState_id());
						ft.setState_id(Integer.parseInt(state));
						
						ft.setUser_id(Integer.parseInt(userid));
						ft.setStatus(1);
						ft.setFrom_date(other_date_of_reg_date);
						ft.setP_id((p_id));
						ft.setCreated_date(new Date());
						ft.setCreated_by(username);
						
						 sessionHQL.save(ft);
						// data.put("msg", "Save as Draft Successfylly");
						sessionHQL.flush();
						sessionHQL.clear();

			
						tx.commit();
						
					}catch (RuntimeException e) {
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
		
						return new ModelAndView("redirect:Teacher_dtl_Url");
					}
				 
				 
				 

			 
 
			 
		 


}
