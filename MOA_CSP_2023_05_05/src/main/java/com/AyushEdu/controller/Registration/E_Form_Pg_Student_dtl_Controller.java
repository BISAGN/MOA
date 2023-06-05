package com.AyushEdu.controller.Registration;

import java.io.IOException;
import java.math.BigInteger;
import java.security.Principal;
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

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.UserRole;
import com.AyushEdu.Models.Exp_Excel.EDU_LMS_NCH_STUDENT_DETAILS;
import com.AyushEdu.Models.Exp_Excel.EDU_LMS_STUDENT_DETAILS;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_INSTITUTE_REGISTRATION;
import com.AyushEdu.config.EmailConfig;
import com.AyushEdu.controller.AyushId_Directory.AyushId_Directory_Controller;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.LMS_Master.Edu_Lms_Subject_Wise_Pg_Seats_Dao;
import com.AyushEdu.dao.Registration.E_Form_Student_Dtl_DAO;
import com.AyushEdu.dao.Registration.Search_Student_RegistrationDao;
import com.AyushEdu.dao.Registration.Graduation_NCISM.Search_Ncism_Student_RegistrationDao;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class E_Form_Pg_Student_dtl_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private Commondao cd;
	@Autowired
	CommonController common;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	AyushId_Directory_Controller dir;
	@Autowired
	EmailConfig emailsend;
	@Autowired
	E_Form_Student_Dtl_DAO edao;
	@Autowired
	Search_Student_RegistrationDao SSRnchDao;
	@Autowired
	Search_Ncism_Student_RegistrationDao SSRncismDao;
	@Autowired
	ValidationController validation;
	@Autowired
	Edu_Lms_Subject_Wise_Pg_Seats_Dao sdao;
	
	@GetMapping(value = "/eform_pg_url")
	public ModelAndView eform_pg_url(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,RedirectAttributes ra) {
		
		try {
			//SECURITY ABHISHEK
//			if (request.getHeader("Referer") == null) {
//				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				return new ModelAndView("redirect:/landingpage");
//			}
//			String roleid1 = session.getAttribute("roleid").toString();
//			Boolean val = roledao.ScreenRedirect("eform_pg_url", roleid1);
//			if (val == false) {
//				return new ModelAndView("AccessTiles");
//			}
			
//			Excel---------------------------------------------------------------------------------------------
			
			String userid = session.getAttribute("userId_for_jnlp").toString();
			String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
			model.put("msg", msg);
			Date date = new Date();
			model.put("getinstitute_system", cd.getinstitute_system(Integer.parseInt(institute_id)));
			model.put("getstu_DegreeList", cd.getstu_DegreeList(Integer.parseInt(institute_id),"16"));
			String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
			model.put("date", date1);

//			E-Form---------------------------------------------------------------------------------------------
			
			String role = session.getAttribute("rolename").toString();
			model.put("msg", msg);
			model.put("getinstitute_system", cd.getinstitute_system(Integer.parseInt(institute_id)));
			model.put("getDegreePG", cd.getDegreeListPG(Integer.parseInt(institute_id)));
			model.put("list", edao.E_Form_Student_DtlDataPG(role,institute_id));
			model.put("date", date1);
			model.put("id", institute_id);
			model.put("filledData", edao.FilledDataofStudents(institute_id,role));
			model.put("getMedStateName", common.getMedStateName(sessionFactory));
			model.put("getcategorylist", common.getcategoryList(sessionFactory));
			model.put("getCounselingAuthoList", common.getCounselingAuthoList(sessionFactory));
			model.put("getQuotaList", common.getQuotaList(sessionFactory));
			model.put("getInsCodeListbyInsName", common.getInsCodeListbyInsName(sessionFactory,institute_id));
			model.put("getintake_typelist", common.getintake_typelist());
			
			
			
			
//			System.err.println("================="+SSRnchDao.getinstitutelist(userid));
			 String Staff_lvl = session.getAttribute("roleStaff_lvl").toString();
				if (Staff_lvl.toUpperCase().equals("NCH")) {
					model.put("getapp_instituteNameList", SSRnchDao.getinstitutelist(userid));
				}else if (Staff_lvl.toUpperCase().equals("NCISM")) {
					model.put("getapp_instituteNameList", SSRncismDao.getinstitute_Ncismlist(userid));
				}

		} catch (RuntimeException e) {
			try {

				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				ra.addAttribute("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;
		} 
		return new ModelAndView("Eform_Pg_Tiles");
	}
	
	@RequestMapping(value = "/fetchFilledDataPG", method = RequestMethod.POST)
	public @ResponseBody ArrayList<ArrayList<String>> fetchFilledDataPG(HttpSession session,String subject,String intake_id) {
		String role = session.getAttribute("rolename").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		return edao.FilledDataofStudentsPG(institute_id,role,subject,intake_id);
	}
	
	@RequestMapping(value = "/getPGdashStatusBySubject", method = RequestMethod.POST)
	public @ResponseBody String getPGdashStatusBySubject(HttpSession session,String subject) {
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		String pds = edao.getPGDashboardstatusbySubject(institute_id,subject).get(0).get(0);
		return pds;
	}
	
	@PostMapping("/getcountsbyPGSubject")
	public @ResponseBody ArrayList<ArrayList<String>> getcountsbyPGSubject(HttpSession session,String degree,String subject) {
//		String role = session.getAttribute("rolename").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		return edao.getSeatcountsbyPGSubject(degree,subject,institute_id);
	}
	
	@PostMapping("/getPGSubjectsofInstitute")
	public @ResponseBody List<Map<String, Object>> getPGSubjectsofInstitute(HttpSession session,String degree) {
 		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		return sdao.PGSubjectsofInst(degree,institute_id);

	}
	
	@PostMapping(value = "/e_form_pg_student_Action")
	public @ResponseBody Map<String, String> e_form_pg_student_Action( 
			HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication, String id,
			RedirectAttributes ra)throws IOException, ParseException {
		
		Map<String, String> data = new HashMap<>();
		
		String indno = request.getParameter("indno");
		
		String role = session.getAttribute("role").toString();

//		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return  "redirect:/login";
//		 }
		
		String hid_quali = request.getParameter("hid_quali"+indno);
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = request.getParameter("institute_id"+indno);
		String name_of_institute = request.getParameter("name_of_institute"+indno);
		String state_id = request.getParameter("state_id"+indno);
		String cand_name = request.getParameter("cand_name"+indno);
		String last_name = request.getParameter("last_name"+indno);
		String mother_name = request.getParameter("mother_name"+indno);
		String father_name = request.getParameter("father_name"+indno);
		String dob = request.getParameter("dob"+indno);
		String neet_application_no = request.getParameter("neet_application_no"+indno);
		String neet_roll_no = request.getParameter("neet_roll_no"+indno);
		String neet_rank = request.getParameter("neet_rank"+indno);
		String neet_percentile = request.getParameter("neet_percentile"+indno);
		String neet_marks = request.getParameter("neet_marks"+indno);
		String quota_id = request.getParameter("quota_id"+indno);
		String counselling_authority = request.getParameter("counselling_authority"+indno);
		String category_id = request.getParameter("category_id"+indno);
		String email = request.getParameter("email"+indno);
		String degree = request.getParameter("degree_hid");
		String udid = request.getParameter("savedid"+indno);
		String useridofstudent = request.getParameter("userid"+indno);
		String subject = request.getParameter("subject_hid");
		String intake_id = request.getParameter("intake_hid");
//		System.err.println("\n\n intake_id------------>>>>>>   "+intake_id+"\n\n");
		Date date = new Date();
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
//		String userid = session.getAttribute("userId_for_jnlp").toString();
		
		if (degree.trim().equals("0") || degree.trim().equals("")) {
			  data.put( "err" ,  "Please Select Degree");
			  data.put( "field" ,  "degree");
			  return data;
		}
		
		if (subject.trim().equals("0") || subject.trim().equals("")) {
			  data.put( "err" ,  "Please Select Subject");
			  data.put( "field" ,  "subject");
			  return data;
		}
		
		if (intake_id.trim().equals("0") || intake_id.trim().equals("")) {
			  data.put( "err" ,  "Please Select Intek Type");
			  data.put( "field" ,  "intake_id");
			  return data;
		}
		
		if (name_of_institute.trim().equals("0")) {
			  data.put( "err" ,  "Please Select Name of the Institute");
			  data.put( "field" ,  "name_of_institute");
			  return data;
		}
		
//		if (institute_id == null || institute_id.trim().equals("")) {
//			  data.put( "err" ,  "Please Enter Institute ID");
//			  data.put( "field" ,  "institute_id");
//			  return data;
//		}
		if (state_id.trim().equals("0")) {
			  data.put( "err" ,  "Please Select State");
			  data.put( "field" ,  "state_id");
			  return data;
		}
		if (cand_name == null || cand_name.trim().equals("")) {
			  data.put( "err" ,  "Please Enter First Name");
			  data.put( "field" ,  "cand_name");
			  return data;
		}
		if (validation.maxlengthcheck30(cand_name) == false) {
			data.put("err", "First Name " + validation.MaxlengthcheckMSG30);
			data.put( "field" ,  "cand_name");
			return data;
		}
		if (validation.isOnlyAlphabet(cand_name) == false) {
			data.put("err", "First Name " + validation.isOnlyAlphabetMSG);
			data.put( "field" ,  "cand_name");
			return data;
		}
		if (last_name == null || last_name.trim().equals("")) {
			  data.put( "err" ,  "Please Enter Middle Name & Last Name");
			  data.put( "field" ,  "last_name");
			  return data;
		}
		if (validation.maxlengthcheck50(last_name) == false) {
			data.put("err", "Middle Name & Last Name " + validation.MaxlengthcheckMSG50);
			data.put( "field" ,  "last_name");
			return data;
		}
		if (validation.isOnlyAlphabet(last_name) == false) {
			data.put("err", "Middle Name & Last Name " + validation.isOnlyAlphabetMSG);
			data.put( "field" ,  "last_name");
			return data;
		}
		if (mother_name == null || mother_name.trim().equals("")) {
			  data.put( "err" ,  "Please Enter Mother's Name");
			  data.put( "field" ,  "mother_name");
			  return data;
		}
		if (validation.maxlengthcheck30(mother_name) == false) {
			data.put("err", "Mother Name " + validation.MaxlengthcheckMSG30);
			data.put( "field" ,  "mother_name");
			return data;
		}
		if (validation.isOnlyAlphabet(mother_name) == false) {
			data.put("err", "Mother Name " + validation.isOnlyAlphabetMSG);
			data.put( "field" ,  "mother_name");
			return data;
		}
		if (father_name == null || father_name.trim().equals("")) {
			  data.put( "err" ,  "Please Enter Father's Name");
			  data.put( "field" ,  "father_name");
			  return data;
		}
		if (validation.maxlengthcheck30(father_name) == false) {
			data.put("err", "Father Name " + validation.MaxlengthcheckMSG30);
			data.put( "field" ,  "father_name");
			return data;
		}
		if (validation.isOnlyAlphabet(father_name) == false) {
			data.put("err", "Father Name " + validation.isOnlyAlphabetMSG);
			data.put( "field" ,  "father_name");
			return data;
		}
		if (dob == null || dob.trim().equals("") || dob.equals("DD/MM/YYYY")) {
			data.put("err", "Please Enter The Date Of Birth");
			data.put( "field" ,  "dob");
			return data;
		}
		
		if (email == null || email.trim().equals("")) {
			  data.put( "err" ,  "Please Enter Email ID");
			  data.put( "field" ,  "email");
			  return data;
		}
		if (validation.maxlengthcheck70(email) == false) {
			data.put("err", "Email Address " + validation.MaxlengthcheckMSG70);
			data.put( "field" ,  "email");
			return data;
		}
	
		if(!intake_id.equals("2")) {
			if (neet_application_no == null || neet_application_no.trim().equals("")) {
				  data.put( "err" ,  "Please Enter AIAPGET Application No");
				  data.put( "field" ,  "neet_application_no");
				  return data;
			}
			if (neet_application_no.length() != 12) {
				  data.put( "err" ,  "Please Enter 12 digit AIAPGET Application No");
				  data.put( "field" ,  "neet_application_no");
				  return data;
			}
			
			if (validation.isOnlyNumer(neet_application_no) == true) {
				data.put("msg", "AIAPGET Application No " + validation.isOnlyAlphabetNumberMSG);
				return data;
			}
			
			if (neet_roll_no == null || neet_roll_no.trim().equals("")) {
				  data.put( "err" ,  "Please Enter AIAPGET Roll No");
				  data.put( "field" ,  "neet_roll_no");
				  return data;
			}
			
			if (neet_roll_no.length() != 10) {
				  data.put( "err" ,  "Please Enter 10 digit AIAPGET Roll No");
				  data.put( "field" ,  "neet_roll_no");
				  return data;
			}
			
			if (validation.isOnlyAlphabetNumber(neet_roll_no) == false) {
				data.put("err", "AIAPGET Roll No. " + validation.isOnlyAlphabetNumberMSG);
				data.put( "field" ,  "neet_roll_no");
				return data;
			}
			
			if (neet_rank == null || neet_rank.trim().equals("")) {
				  data.put( "err" ,  "Please Enter ALL INDIA AIAPGET Rank");
				  data.put( "field" ,  "neet_rank");
				  return data;
			}
			
			if (neet_rank.length() > 7) {
				  data.put( "err" ,  "Please Enter Maximum 7 digit ALL INDIA AIAPGET Rank");
				  data.put( "field" ,  "neet_rank");
				  return data;
			}
			if (validation.isOnlyNumer(neet_rank) == true) {
				data.put("err", "ALL INDIA AIAPGET Rank " + validation.isOnlyNumerMSG);
				data.put( "field" ,  "neet_rank");
				return data;
			}
			
			if (neet_percentile == null || neet_percentile.trim().equals("")) {
				  data.put( "err" ,  "Please Enter AIAPGET Percentile");
				  data.put( "field" ,  "neet_percentile");
				  return data;
			}
			
//			System.err.println("neet percent------------"+neet_percentile.matches("[0-9.]*"));
			
//			if(neet_percentile.contains(".")) {
//				if (Integer.parseInt(neet_percentile) > 5) {
//					  data.put( "err" ,  "Please Enter 2 digit and 2 decimal value for NEET Percentile");
//					  data.put( "field" ,  "neet_percentile");
//					  return data;
//				}
//			}else {
//				if(Integer.parseInt(neet_percentile) > 100) {
//					data.put( "err" ,  "Please Enter Less than or Equal 100 in Neet Percentile");
//					data.put( "field" ,  "neet_percentile");
//					return data;
//				}
//			}
			
			if (validation.isOnlyNumerandDotMSG(neet_percentile) == false) {
				data.put("err", "AIAPGET Percentile " + validation.isOnlyNumerandDotMSG);
				data.put( "field" ,  "neet_percentile");
				return data;
			}
			
			if (neet_marks == null || neet_marks.trim().equals("")) {
				  data.put( "err" ,  "Please Enter Marks Obtained");
				  data.put( "field" ,  "neet_marks");
				  return data;
			}
			if (Integer.parseInt(neet_marks) > 480) {
				  data.put( "err" ,  "Please Enter Maximum 480 Marks Obtained");
				  data.put( "field" ,  "neet_marks");
				  return data;
			}		
			if (validation.isOnlyNumer(neet_marks) == true) {
				data.put("err", "Marks Obtained " + validation.isOnlyNumerMSG);
				data.put( "field" ,  "neet_marks");
				return data;
			}
		}
		
		
		
		if (quota_id.trim().equals("0")) {
			  data.put( "err" ,  "Please Select Quota");
			  data.put( "field" ,  "quota_id");
			  return data;
		}
		if (counselling_authority.trim().equals("0")) {
			  data.put( "err" ,  "Please Select Counselling Authority");
			  data.put( "field" ,  "counselling_authority");
			  return data;
		}
		if (category_id.trim().equals("0")) {
			  data.put( "err" ,  "Please Select Category");
			  data.put( "field" ,  "category_id");
			  return data;
		}
		
		try {
			
			int roleId=0;
			
			Date date_of_birth = formatter.parse(dob);
			
			System.err.println("\n\n DATE OF BIRTH--------------"+date_of_birth+"\n\n");
			
			String university_id = common.getUniIdfromInsid(name_of_institute).get(0);
			
//			System.err.println("university_id------------>   "+university_id);
			
			EDU_LMS_INSTITUTE_REGISTRATION ir = common.getInsCodeListbyInsName(sessionFactory,name_of_institute).get(0);
			int sanctionedseats = Integer.parseInt(edao.getSeatcountsbyPGSubject(degree,subject,name_of_institute).get(0).get(0));
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode("Bisag@123");
			
			int savedid = 0;
			
			
			if(hid_quali.equals("0")) {
				
				if(role.toUpperCase().contains("NCISM")) {
					
					if(!intake_id.equals("2")) {
						Query cneetdtlq = sessionHQL.createSQLQuery("select count(*) from edu_lms_student_details a inner join edu_lms_degree_mstr b on a.degree=b.id\n"
										+" where  b.type_of_degree = 16 and \n"
										+" (neet_application_no=:neet_application_no or neet_roll_no=:neet_roll_no or neet_rank=:neet_rank) ");
						cneetdtlq.setParameter("neet_application_no",neet_application_no);
						cneetdtlq.setParameter("neet_roll_no",neet_roll_no);
						cneetdtlq.setParameter("neet_rank",Integer.parseInt(neet_rank));
						BigInteger cneetdtlcnt = (BigInteger) cneetdtlq.uniqueResult();
						
						Query cneetdtlq2 = sessionHQL.createSQLQuery("select count(*) from edu_lms_nch_student_details a inner join edu_lms_degree_mstr b on a.degree=b.id\n"
										+" where  b.type_of_degree = 16 and \n "
										+" (neet_application_no=:neet_application_no or neet_roll_no=:neet_roll_no or neet_rank=:neet_rank) ");
						cneetdtlq2.setParameter("neet_application_no",neet_application_no);
						cneetdtlq2.setParameter("neet_roll_no",neet_roll_no);
						cneetdtlq2.setParameter("neet_rank",Integer.parseInt(neet_rank));
						BigInteger cneetdtlcnt2 = (BigInteger) cneetdtlq2.uniqueResult();
						
						System.err.println("\n\n NCISM COUNT--------------------"+cneetdtlcnt+"---NCH COUNT---"+cneetdtlcnt2+"\n\n"+"check condition---"+BigInteger.valueOf(0).compareTo(cneetdtlcnt));
						
						if(BigInteger.valueOf(0).compareTo(cneetdtlcnt) < 0 || BigInteger.valueOf(0).compareTo(cneetdtlcnt2) < 0) {
								  data.put( "err" ,  "AIAPGET App. No. or AIAPGET Roll No. or AIAPGET Rank is already exist");
								  data.put( "field" ,  "neet_application_no");
								  return data;
						}
					}
					
					
				}
				if(role.toUpperCase().contains("NCH")) {
					
					if(!intake_id.equals("2")) {
						Query cneetdtlq = sessionHQL.createSQLQuery("select count(*) from edu_lms_student_details a inner join edu_lms_degree_mstr b on a.degree=b.id \n"
								+" where  b.type_of_degree = 16 and \n"
								+" (neet_application_no=:neet_application_no or neet_roll_no=:neet_roll_no or neet_rank=:neet_rank) ");
						cneetdtlq.setParameter("neet_application_no",neet_application_no);
						cneetdtlq.setParameter("neet_roll_no",neet_roll_no);
						cneetdtlq.setParameter("neet_rank",Integer.parseInt(neet_rank));
						BigInteger cneetdtlcnt = (BigInteger) cneetdtlq.uniqueResult();
						
						Query cneetdtlq2 = sessionHQL.createSQLQuery("select count(*) from edu_lms_nch_student_details a inner join edu_lms_degree_mstr b on a.degree=b.id\n"
								+" where b.type_of_degree = 16 and \n "
								+" (neet_application_no=:neet_application_no or neet_roll_no=:neet_roll_no or neet_rank=:neet_rank) ");
						cneetdtlq2.setParameter("neet_application_no",neet_application_no);
						cneetdtlq2.setParameter("neet_application_no",neet_application_no);
						cneetdtlq2.setParameter("neet_roll_no",neet_roll_no);
						cneetdtlq2.setParameter("neet_rank",Integer.parseInt(neet_rank));
						BigInteger cneetdtlcnt2 = (BigInteger) cneetdtlq2.uniqueResult();
						
						System.err.println("\n\n NCISM COUNT--------------------"+cneetdtlcnt+"---NCH COUNT---"+cneetdtlcnt2+"\n\n"+"check condition---"+BigInteger.valueOf(0).compareTo(cneetdtlcnt));
						
						if(BigInteger.valueOf(0).compareTo(cneetdtlcnt) < 0 || BigInteger.valueOf(0).compareTo(cneetdtlcnt2) < 0) {
						  data.put( "err" ,  "AIAPGET App. No. or AIAPGET Roll No. or AIAPGET Rank is already exist");
						  data.put( "field" ,  "neet_application_no");
						  return data;
						}
					}
					
					
				}
				
				Query lecq = sessionHQL.createQuery("select count(*) from UserLogin where upper(userName)=:email ");
				lecq.setParameter("email", email.toUpperCase());
				Long lecqcount = (Long) lecq.uniqueResult();
				
				if(lecqcount == 0) {
				
				if(role.toUpperCase().contains("NCISM")) {
					
					Query cscq = sessionHQL.createQuery("select count(*) from EDU_LMS_STUDENT_DETAILS where institude_userid=:inst_id and degree=:degree and pg_subject=:pg_subject");
					cscq.setParameter("inst_id",Integer.parseInt(name_of_institute));
					cscq.setParameter("degree",Integer.parseInt(degree));
					cscq.setParameter("pg_subject",Integer.parseInt(subject));
					Long checkseatcount = (Long) cscq.uniqueResult();
					
//					System.err.println("\n\n*************"+checkseatcount+"***"+ir.getTotal_sanctioned_seat()+"***"+name_of_institute+"\n\n");
					
					if(checkseatcount < sanctionedseats) {
					
						roleId = 44;
						Query q0 = sessionHQL.createQuery("select count(*) from EDU_LMS_STUDENT_DETAILS where upper(email)=:email ");
						q0.setParameter("email", email.toUpperCase());
						Long c = (Long) q0.uniqueResult();
						
						Query qle = sessionHQL.createQuery("select count(*) from UserLogin where upper(email_id)=:email ");
						qle.setParameter("email", email.toUpperCase());
						Long cle = (Long) qle.uniqueResult();
						
						
						if(c == 0 && cle == 0){
						
						EDU_LMS_STUDENT_DETAILS add_detail =new EDU_LMS_STUDENT_DETAILS();
						
//							String maxAID="";
//							maxAID = exp.getMaxAID(userid);
//							add_detail.setAyush_id(maxAID);
						
					    	add_detail.setInstitude_userid(Integer.parseInt(name_of_institute));
					    	add_detail.setState(Integer.parseInt(state_id));
					    	add_detail.setName(cand_name);
					    	add_detail.setLast_name(last_name);
					    	add_detail.setMother_name(mother_name);
					    	add_detail.setFather_name(father_name);
					    	add_detail.setDob(date_of_birth);
					    	
					    	if(!intake_id.equals("2")) {
					    		add_detail.setNeet_application_no(neet_application_no);
						    	add_detail.setNeet_roll_no(neet_roll_no);
						    	add_detail.setNeet_rank(Integer.parseInt(neet_rank));
						    	add_detail.setNeet_percentile(neet_percentile);
						    	add_detail.setNeet_marks(Integer.parseInt(neet_marks));
					    	}
					    	
					    	add_detail.setQuota(Integer.parseInt(quota_id));
					    	add_detail.setCounc_auth(Integer.parseInt(counselling_authority));
					    	add_detail.setCategory(Integer.parseInt(category_id));
					    	add_detail.setUniversity_userid(Integer.parseInt(university_id));
					    	add_detail.setEmail(email);
					    	add_detail.setCreated_by(userid);
					    	add_detail.setCreated_date(date);
					    	add_detail.setVerified_status(-1);
					    	add_detail.setDegree(Integer.parseInt(degree));
					    	add_detail.setSystem(ir.getSystem_id());
					    	add_detail.setSemester("1");
					    	add_detail.setInst_code(institute_id);
					    	add_detail.setPg_subject(Integer.parseInt(subject));
					    	add_detail.setIntake_id(Integer.parseInt(intake_id)); 
					    	
					    	
					    	savedid = (int) sessionHQL.save(add_detail);
							sessionHQL.flush();
							sessionHQL.clear();
						}
						if(c > 0 || cle > 0) {
							data.put("err", "This "+ email +" Email is Already Registered.");
							data.put( "field" ,  "email");
							return data;
						}
					
					}
					if(checkseatcount >= sanctionedseats) {
						data.put("err", "No Vacant Seats are available as per Total Number of Sanctioned Seat");
						return data;
					}
				}
				
				if(role.toUpperCase().contains("NCH")) {
					
					Query cscq = sessionHQL.createQuery("select count(*) from EDU_LMS_NCH_STUDENT_DETAILS where institude_userid=:inst_id and degree=:degree and pg_subject=:pg_subject");
					cscq.setParameter("inst_id",Integer.parseInt(name_of_institute));
					cscq.setParameter("degree",Integer.parseInt(degree));
					cscq.setParameter("pg_subject",Integer.parseInt(subject));
					Long checkseatcount = (Long) cscq.uniqueResult();
					
					if(checkseatcount < sanctionedseats) {
					
						roleId = 42;
						Query q0 = sessionHQL.createQuery("select count(*) from EDU_LMS_NCH_STUDENT_DETAILS where upper(email)=:email ");
						q0.setParameter("email", email.toUpperCase());
						Long c = (Long) q0.uniqueResult();
						
						Query qle2 = sessionHQL.createQuery("select count(*) from UserLogin where upper(email_id)=:email ");
						qle2.setParameter("email", email.toUpperCase());
						Long cle2 = (Long) qle2.uniqueResult();
						
						if(c == 0 && cle2 == 0){
						EDU_LMS_NCH_STUDENT_DETAILS add_detailNch =new EDU_LMS_NCH_STUDENT_DETAILS();
						
//							String maxAID="";
//							maxAID = eenchc.getMaxAID(userid);
//							add_detailNch.setAyush_id(maxAID);
						
							add_detailNch.setInstitude_userid(Integer.parseInt(name_of_institute));
							add_detailNch.setState(Integer.parseInt(state_id));
							add_detailNch.setName(cand_name);
							add_detailNch.setLast_name(last_name);
							add_detailNch.setMother_name(mother_name);
					    	add_detailNch.setFather_name(father_name);
					    	add_detailNch.setDob(date_of_birth);
					    	
					    	if(!intake_id.equals("2")) {
					    		add_detailNch.setNeet_application_no(neet_application_no);
						    	add_detailNch.setNeet_roll_no(neet_roll_no);
						    	add_detailNch.setNeet_rank(Integer.parseInt(neet_rank));
						    	add_detailNch.setNeet_percentile(neet_percentile);
						    	add_detailNch.setNeet_marks(Integer.parseInt(neet_marks));
					    	}
					    	
					    	add_detailNch.setQuota(Integer.parseInt(quota_id));
					    	add_detailNch.setCounc_auth(Integer.parseInt(counselling_authority));
					    	add_detailNch.setCategory(Integer.parseInt(category_id));
					    	add_detailNch.setUniversity_userid(Integer.parseInt(university_id));
					    	add_detailNch.setEmail(email);
					    	add_detailNch.setCreated_by(userid);
					    	add_detailNch.setCreated_date(date);
					    	add_detailNch.setVerified_status(-1);
					    	add_detailNch.setDegree(Integer.parseInt(degree));
					    	add_detailNch.setSystem(ir.getSystem_id());
					    	add_detailNch.setSemester("1");
					    	add_detailNch.setInst_code(institute_id);
					    	add_detailNch.setPg_subject(Integer.parseInt(subject));
//					    	add_detailNch.setIntake_id(Integer.parseInt(intake_id)); 
					    	
//					    	System.err.println("\n\n"+add_detailNch.getInst_code()+"**********"+add_detailNch.getDegree()+"\n\n");
					    	
					    	savedid = (int) sessionHQL.save(add_detailNch);
							sessionHQL.flush();
							sessionHQL.clear();
						}
						if(c > 0 || cle2 > 0) {
							data.put("err", "This "+ email +" Email is Already Registered.");
							data.put( "field" , "email");
							return data;
						}
					
					}
					
					if(checkseatcount >= sanctionedseats) {
						data.put("err", "No Vacant Seats are available as per Total Number of Sanctioned Seat");
						return data;
					}
				}
			    	
//			    UserLogin p = new UserLogin();
//			    	
//			    	p.setUserName(email);
//			    	p.setPassword(hashedPassword);
//			    	p.setLogin_name(cand_name);
//			    	p.setEmail_id(email);
//			    	p.setInstitute_id(Integer.parseInt(name_of_institute));
//					p.setUniversity_id(Integer.parseInt(university_id));
//			    	p.setState_id(Integer.parseInt(state_id));
//					p.setEnabled(1);
//					p.setAccountNonExpired(1);
//					p.setAccountNonLocked(1);
//					p.setCredentialsNonExpired(1);
//					
//					p.setCreated_on(new Date());
//					p.setCreated_by(userid);
//			    	
//					int uid = (int) sessionHQL.save(p);
//					sessionHQL.flush();
//					sessionHQL.clear();
//					
//					UserRole role_tbl = new UserRole();
//					
//					role_tbl.setRoleId(roleId);
//					role_tbl.setUserId(uid);
//					
//					int rid = (int) sessionHQL.save(role_tbl);
//					sessionHQL.flush();
//					sessionHQL.clear();
					
					tx.commit();
					
					data.put( "msg" ,  "Data Saved Successfully.");
					data.put("savedid",String.valueOf(savedid));
					data.put("userid",String.valueOf(0));
					String sanc_seat = edao.getSeatcountsbyPGSubject(degree,subject,name_of_institute).get(0).get(0);
					String admt_seat = edao.getSeatcountsbyPGSubject(degree,subject,name_of_institute).get(0).get(1);
					String vacn_seat = String.valueOf(Integer.parseInt(sanc_seat)-Integer.parseInt(admt_seat));
					data.put("sanc_seat",sanc_seat);
					data.put("admt_seat",admt_seat);
					data.put("vacn_seat",vacn_seat);
					
					
					
					
//					String maintext = "Your Registration is successfull with Institute : "+ir.getInstitute_name()+" now you can login with your registered Email ID as a username and  OTP that is sent to the registered Email ID (username).";
//					String followuptext = "";
////					FOR MAIL SEND
//					if(savedid > 0 && uid > 0 && rid > 0 ) {
//						if(role.contains("NCISM")){
//							emailsend.SendMail(request,p.getEmail_id(),cand_name,"NCISM,MOA AYUSH EDUCATION REGISTRATION",maintext,followuptext,"","");
//						}
//						if(role.contains("NCIH")){
//							emailsend.SendMail(request,p.getEmail_id(),cand_name,"NCH,MOA AYUSH EDUCATION REGISTRATION",maintext,followuptext,"","");
//						}
//					}
					
				}else {
					data.put("err", "This "+ email +" Email is Already Registered.");
					data.put( "field" ,  "email");
					return data;
				}
				
			}else {
				
				String hql = "";
				
				if(role.toUpperCase().contains("NCISM")) {
					
					if(!intake_id.equals("2")) {
						
						Query cneetdtlq = sessionHQL.createSQLQuery("select count(*) from edu_lms_student_details a inner join edu_lms_degree_mstr b on a.degree=b.id \n"
								+" where  b.type_of_degree = 16 and \n"
								+" (neet_application_no=:neet_application_no or neet_roll_no=:neet_roll_no or neet_rank=:neet_rank) and a.id!=:id");
						cneetdtlq.setParameter("neet_application_no",neet_application_no);
						cneetdtlq.setParameter("neet_roll_no",neet_roll_no);
						cneetdtlq.setParameter("neet_rank",Integer.parseInt(neet_rank));
						cneetdtlq.setParameter("id",Integer.parseInt(udid));
						BigInteger cneetdtlcnt = (BigInteger) cneetdtlq.uniqueResult();
						
						Query cneetdtlq2 = sessionHQL.createSQLQuery("select count(*) from edu_lms_nch_student_details a inner join edu_lms_degree_mstr b on a.degree=b.id\n"
								+" where b.type_of_degree = 16 and \n "
								+" (neet_application_no=:neet_application_no or neet_roll_no=:neet_roll_no or neet_rank=:neet_rank) and a.id!=:id");
						cneetdtlq2.setParameter("neet_application_no",neet_application_no);
						cneetdtlq2.setParameter("neet_application_no",neet_application_no);
						cneetdtlq2.setParameter("neet_roll_no",neet_roll_no);
						cneetdtlq2.setParameter("neet_rank",Integer.parseInt(neet_rank));
						cneetdtlq2.setParameter("id",Integer.parseInt(udid));
						BigInteger cneetdtlcnt2 = (BigInteger) cneetdtlq2.uniqueResult();
						
						
//						Query cneetdtlq = sessionHQL.createQuery("select count(*) from EDU_LMS_STUDENT_DETAILS where (neet_application_no=:neet_application_no or neet_roll_no=:neet_roll_no or neet_rank=:neet_rank) and id!=:id");
//						cneetdtlq.setParameter("neet_application_no",neet_application_no);
//						cneetdtlq.setParameter("neet_roll_no",neet_roll_no);
//						cneetdtlq.setParameter("neet_rank",Integer.parseInt(neet_rank));
//						cneetdtlq.setParameter("id",Integer.parseInt(udid));
//						Long cneetdtlcnt = (Long) cneetdtlq.uniqueResult();
						
						System.err.println("\n\n NCISM COUNT--------------------"+cneetdtlcnt+"***"+udid+"\n\n"+"1---"+BigInteger.valueOf(0).compareTo(cneetdtlcnt)+"-2---"+BigInteger.valueOf(0).compareTo(cneetdtlcnt2));
						
//						if(cneetdtlcnt > 0) {
//								  data.put( "err" ,  "NEET App. No. or NEET Roll No. or NEET Rank is already exist");
//								  data.put( "field" ,  "neet_application_no");
//								  return data;
//						}
						
						
						if(BigInteger.valueOf(0).compareTo(cneetdtlcnt) < 0 || BigInteger.valueOf(0).compareTo(cneetdtlcnt2) < 0) {
							  data.put( "err" ,  "AIAPGET App. No. or AIAPGET Roll No. or AIAPGET Rank is already exist");
							  data.put( "field" ,  "neet_application_no");
							  return data;
						}
						
					}
					
					
					
					Query qceu = sessionHQL.createQuery("select count(*) from EDU_LMS_STUDENT_DETAILS where upper(email)=:email and id!=:id");
					qceu.setParameter("email", email.toUpperCase());
					qceu.setParameter("id",Integer.parseInt(udid));
					Long ceu = (Long) qceu.uniqueResult();
					
					Query qle3 = sessionHQL.createQuery("select count(*) from UserLogin where upper(email_id)=:email and userId!=:userId");
					qle3.setParameter("email", email.toUpperCase())
						.setParameter("userId",Integer.parseInt(useridofstudent));
					Long cle3 = (Long) qle3.uniqueResult();
					
					if(ceu > 0 || cle3 > 0) {
						data.put("err", "This "+ email +" Email is Already Registered.");
						data.put( "field" ,  "email");
						return data;
					}
					
				}
				if(role.toUpperCase().contains("NCH")) {
					
					if(!intake_id.equals("2")) {
						Query cneetdtlq = sessionHQL.createQuery("select count(*) from EDU_LMS_NCH_STUDENT_DETAILS where (neet_application_no=:neet_application_no or neet_roll_no=:neet_roll_no or neet_rank=:neet_rank) and id!=:id");
						cneetdtlq.setParameter("neet_application_no",neet_application_no);
						cneetdtlq.setParameter("neet_roll_no",neet_roll_no);
						cneetdtlq.setParameter("neet_rank",Integer.parseInt(neet_rank));
						cneetdtlq.setParameter("id",Integer.parseInt(udid));
						Long cneetdtlcnt = (Long) cneetdtlq.uniqueResult();
						
						System.err.println("\n\n NCH COUNT--------------------"+cneetdtlcnt+"\n\n");
						
						if(cneetdtlcnt > 0) {
						  data.put( "err" ,  "NEET App. No. or NEET Roll No. or NEET Rank is already exist");
						  data.put( "field" ,  "neet_application_no");
						  return data;
						}
					}
					
					
					
					Query qceu = sessionHQL.createQuery("select count(*) from EDU_LMS_NCH_STUDENT_DETAILS where upper(email)=:email and id!=:id");
					qceu.setParameter("email", email.toUpperCase());
					qceu.setParameter("id",Integer.parseInt(udid));
					Long ceu = (Long) qceu.uniqueResult();
					
					Query qle4 = sessionHQL.createQuery("select count(*) from UserLogin where upper(email_id)=:email and userId!=:userId");
					qle4.setParameter("email", email.toUpperCase())
						.setParameter("userId",Integer.parseInt(useridofstudent));
					Long cle4 = (Long) qle4.uniqueResult();
					
					if(ceu > 0 || cle4 > 0) {
						data.put("err", "This "+ email +" Email is Already Registered.");
						data.put( "field" ,  "email");
						return data;
					}
					
				}
				
				if(role.toUpperCase().contains("NCISM")) {
					
					Query q1 = sessionHQL.createQuery("select count(*) from EDU_LMS_STUDENT_DETAILS where upper(email)=:email and id !=: id ");
					q1.setParameter("email", email.toUpperCase());
					q1.setParameter("id", id);
					Long c1 = (Long) q1.uniqueResult();
					if(c1 == 0) {
					hql = "update EDU_LMS_STUDENT_DETAILS set modified_by=:modified_by ,modified_date=:modified_date ,institude_userid=:institude_userid ,state=:state ,name=:name ,last_name=:last_name ,"
							+ "mother_name=:mother_name ,father_name=:father_name ,dob=:dob ,neet_application_no=:neet_application_no ,neet_roll_no=:neet_roll_no ,"
							+ "neet_rank=:neet_rank ,neet_percentile=:neet_percentile ,neet_marks=:neet_marks ,quota=:quota ,counc_auth=:counc_auth ,category=:category ,"
							+ "university_userid=:university_userid ,email=:email,degree=:degree,pg_subject=:pg_subject,intake_id=:intake_id where id=:id";
					}
					
					
					
					if(c1 > 0 ) {
						data.put("msg", "This "+ email +" Email is Already Registered." );
						return data;
					}
				}
				
				if(role.toUpperCase().contains("NCH")) {
					Query q2 = sessionHQL.createQuery("select count(*) from EDU_LMS_NCH_STUDENT_DETAILS where upper(email)=:email and id !=: id ");
					q2.setParameter("email", email.toUpperCase());
					q2.setParameter("id", id);
					Long c2 = (Long) q2.uniqueResult();
					if(c2 == 0) {
					hql = "update EDU_LMS_NCH_STUDENT_DETAILS set modified_by=:modified_by ,modified_date=:modified_date ,institude_userid=:institude_userid ,state=:state ,name=:name ,last_name=:last_name ,"
							+ "mother_name=:mother_name ,father_name=:father_name ,dob=:dob ,neet_application_no=:neet_application_no ,neet_roll_no=:neet_roll_no ,"
							+ "neet_rank=:neet_rank ,neet_percentile=:neet_percentile ,neet_marks=:neet_marks ,quota=:quota ,counc_auth=:counc_auth ,category=:category ,"
							+ "university_userid=:university_userid ,email=:email,degree=:degree,pg_subject=:pg_subject where id=:id";
					
//					hql = "update EDU_LMS_NCH_STUDENT_DETAILS set modified_by=:modified_by ,modified_date=:modified_date ,institude_userid=:institude_userid ,state=:state ,name=:name ,last_name=:last_name ,"
//							+ "mother_name=:mother_name ,father_name=:father_name ,dob=:dob ,neet_application_no=:neet_application_no ,neet_roll_no=:neet_roll_no ,"
//							+ "neet_rank=:neet_rank ,neet_percentile=:neet_percentile ,neet_marks=:neet_marks ,quota=:quota ,counc_auth=:counc_auth ,category=:category ,"
//							+ "university_userid=:university_userid ,email=:email,degree=:degree,pg_subject=:pg_subject,intake_id=:intake_id where id=:id";
					}
					if(c2 > 0 ) {
						data.put("msg", "This "+ email +" Email is Already Registered.");
						return data;
					}
				}
				
				if(!intake_id.equals("2")) {
					Query query = sessionHQL.createQuery(hql)
							.setParameter("state",Integer.parseInt(state_id))
					    	.setParameter("name",cand_name)
					    	.setParameter("last_name",last_name)
					    	.setParameter("mother_name",mother_name)
					    	.setParameter("father_name",father_name)
					    	.setParameter("dob",date_of_birth)
					    	.setParameter("neet_application_no",neet_application_no)
					    	.setParameter("neet_roll_no",neet_roll_no)
					    	.setParameter("neet_rank",Integer.parseInt(neet_rank))
					    	.setParameter("neet_percentile",neet_percentile)
					    	.setParameter("neet_marks",Integer.parseInt(neet_marks))
					    	.setParameter("quota",Integer.parseInt(quota_id))
					    	.setParameter("counc_auth",Integer.parseInt(counselling_authority))
					    	.setParameter("category",Integer.parseInt(category_id))
					    	.setParameter("institude_userid",Integer.parseInt(name_of_institute))
					    	.setParameter("university_userid", Integer.parseInt(university_id))
					    	.setParameter("email",email)
					    	.setParameter("modified_date",new Date())
					    	.setParameter("modified_by", email)
					    	.setParameter("degree", Integer.parseInt(degree))
					    	.setParameter("pg_subject", Integer.parseInt(subject))
					    	.setParameter("intake_id", Integer.parseInt(intake_id))
					    	.setParameter("id",Integer.parseInt(udid));	
					
					msg = query.executeUpdate() > 0 ? "1" : "0";
					
				}else {
					Query query = sessionHQL.createQuery(hql)
							.setParameter("state",Integer.parseInt(state_id))
					    	.setParameter("name",cand_name)
					    	.setParameter("last_name",last_name)
					    	.setParameter("mother_name",mother_name)
					    	.setParameter("father_name",father_name)
					    	.setParameter("dob",date_of_birth)
					    	.setParameter("neet_application_no","")
					    	.setParameter("neet_roll_no","")
					    	.setParameter("neet_rank",0)
					    	.setParameter("neet_percentile","")
					    	.setParameter("neet_marks",0)
					    	.setParameter("quota",Integer.parseInt(quota_id))
					    	.setParameter("counc_auth",Integer.parseInt(counselling_authority))
					    	.setParameter("category",Integer.parseInt(category_id))
					    	.setParameter("institude_userid",Integer.parseInt(name_of_institute))
					    	.setParameter("university_userid", Integer.parseInt(university_id))
					    	.setParameter("email",email)
					    	.setParameter("modified_date",new Date())
					    	.setParameter("modified_by", email)
					    	.setParameter("degree", Integer.parseInt(degree))
					    	.setParameter("pg_subject", Integer.parseInt(subject))
					    	.setParameter("intake_id", Integer.parseInt(intake_id))
					    	.setParameter("id",Integer.parseInt(udid));	
					
					msg = query.executeUpdate() > 0 ? "1" : "0";
				}
				   
//				   String ueilqs = "update UserLogin set email_id=:email_id,userName=:userName where userId=:userId";
//				   
//				   Query ueilq = sessionHQL.createQuery(ueilqs)
//						    .setParameter("email_id",email)
//						    .setParameter("userName",email)
//					    	.setParameter("userId",Integer.parseInt(useridofstudent));
//				   
//				   String leumsg = ueilq.executeUpdate() > 0 ? "1" : "0";
				   
				   sessionHQL.flush();
				   sessionHQL.clear();
					tx.commit();
					
					data.put("savedid",String.valueOf(udid));
					data.put("userid",useridofstudent);
					
					
					//&& leumsg.equals("1")
					if (msg.equals("1")) {
						data.put( "msg" ,  "Data Updated Sucessfully");
					} else {
						data.put( "msg" ,  "Data Not Updated");
					}
			}
			    	
		} catch (RuntimeException e) {
			e.printStackTrace();
			try {
				data.put( "err" ,  "roll back transaction");
			} catch (RuntimeException rbe) {
				data.put( "err" ,  "Couldn't roll back transaction"+ rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}

		return data;
	}
	
	@RequestMapping(value = "/getInsCodeListbyInsName_ctrlPG", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_INSTITUTE_REGISTRATION> getInsCodeListbyInsName_ctrlPG(String name_of_institute) {
		List<EDU_LMS_INSTITUTE_REGISTRATION> list = common.getInsCodeListbyInsName(sessionFactory, name_of_institute);
		return list;
	}
	
	@RequestMapping(value = "/eformFinalsubmitPG", method = RequestMethod.POST)
	public @ResponseBody String eformFinalsubmitPG(HttpSession session,String entrycount,String subject) {
		
		String msg= "";
		
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		try {
		
		String role = session.getAttribute("rolename").toString();
		String username = session.getAttribute("username").toString();
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		
		
		String stu_role = "";
		if(role.contains("NCISM")) {
			stu_role = "44";
		}
		if(role.contains("NCH")) {
			stu_role = "42";
		}
		
		String hql = "update EDU_LMS_SUBJECT_WISE_PG_SEATS set modify_by=:modified_by,modify_date=:modified_date,pg_dashboard_status=:pg_dashboard_status where institute_id=:institute_id and pg_subject=:pg_subject";
		
		Query query = sessionHQL.createQuery(hql)
		    	.setParameter("modified_date",new Date())
		    	.setParameter("modified_by", username)
		    	.setParameter("pg_dashboard_status",1)
		    	.setParameter("institute_id",Integer.parseInt(institute_id))
		    	.setParameter("pg_subject",Integer.parseInt(subject));				   
		   
		   msg = query.executeUpdate() > 0 ? "Data Submitted Successfully" : "Data Not Submitted";
		   
		   sessionHQL.flush();
		   sessionHQL.clear();
		   
		   ArrayList<ArrayList<String>> studentlist = edao.FilledDataofStudentsPG(institute_id,role,subject,"FS");
		   
		   BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		   String hashedPassword = passwordEncoder.encode("Bisag@123");
		   
		   if(msg.equals("Data Submitted Successfully")) {
			   for(int i=0;i<studentlist.size();i++) {
				   
				   String stu_email = studentlist.get(i).get(8);
				   String university_id = common.getUniIdfromInsid(studentlist.get(i).get(0)).get(0);
				   
				   System.err.println("\n"+i+"Student Email ---"+stu_email+"\n");
				   
				   UserLogin p = new UserLogin();
			    	
			    	p.setUserName(stu_email.toLowerCase().trim());
			    	p.setPassword(hashedPassword);
			    	p.setLogin_name(studentlist.get(i).get(3));
			    	p.setEmail_id(stu_email.toLowerCase().trim());
			    	p.setInstitute_id(Integer.parseInt(studentlist.get(i).get(0)));
					p.setUniversity_id(Integer.parseInt(university_id));
			    	p.setState_id(Integer.parseInt(studentlist.get(i).get(2)));
					p.setEnabled(1);
					p.setAccountNonExpired(1);
					p.setAccountNonLocked(1);
					p.setCredentialsNonExpired(1);
					
					p.setCreated_on(new Date());
					p.setCreated_by(userid);
			    	
					int uid = (int) sessionHQL.save(p);
					sessionHQL.flush();
					sessionHQL.clear();
					
					UserRole role_tbl = new UserRole();
					
					role_tbl.setRoleId(Integer.parseInt(stu_role));
					role_tbl.setUserId(uid);
					
					sessionHQL.save(role_tbl);
					sessionHQL.flush();
					sessionHQL.clear();
				   
			   }
		   }
		   
//		   for send EMAIL to student dynamically on final submit
		   
//		   ArrayList<ArrayList<String>> studentlist = edao.FilledDataofStudents(institute_id,role);
//		   
//		   if(msg.equals("Data Submitted Successfully")) {
//			   for(int i=0;i<Integer.parseInt(entrycount);i++) {
//				   String stu_email = studentlist.get(i).get(8);
//				   System.err.println("\n"+i+"Student Email ---"+stu_email+"\n");
//			   }
//		   }
		   
		   sessionHQL.flush();
		   sessionHQL.clear();
		   tx.commit();
		
		} catch (RuntimeException e) {
			e.printStackTrace();
			try {
				msg = "roll back transaction";
			} catch (RuntimeException rbe) {
				msg = "Couldn't roll back transaction"+ rbe;
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		
		return msg;
	}
	
	@RequestMapping(value = "/eformDeleteDataPG", method = RequestMethod.POST)
	public @ResponseBody String eformDeleteDataPG(HttpSession session,String stu_id,String email_id) {
		
		String msg= "";
		String msg1= "";
		String msg2= "";
		String msg3= "";
		
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		try {
		
		String role = session.getAttribute("role").toString();
		
//		Query q1 = sessionHQL.createQuery("select cast(userId as text) from UserLogin where email_id=:email_id ");
//		q1.setParameter("email_id",email_id);
//		List<String> list = (List<String>) q1.list();
//		
//		String userid = list.get(0);
//		
//		Query q2 = sessionHQL.createQuery("select cast(roleId as text) from UserRole where userId=:userId");
//		q2.setParameter("userId",Integer.parseInt(userid));
//		List<String> list2 = (List<String>) q2.list();
		
		
//		String roleid = list2.get(0);
//		
//		System.err.println("\n\n******USERID*****"+userid+"***roleid***"+roleid+"---\n\n");
		
//		if(roleid.equals("42") || roleid.equals("44")){
		
			String hqlST ="";
			if(role.toUpperCase().contains("NCISM")) {
				hqlST = "delete from EDU_LMS_STUDENT_DETAILS where id=:id";
			}
			if(role.toUpperCase().contains("NCH")) {
				hqlST = "delete from EDU_LMS_NCH_STUDENT_DETAILS where id=:id";
			}
			
//			String hqlLT = "delete from UserLogin where userId=:userId ";
//			String hqlURT = "delete from UserRole where userId=:userId and roleId=:roleId";
			
			Query query = sessionHQL.createQuery(hqlST)
			    	.setParameter("id",Integer.parseInt(stu_id));				   
			msg1 = query.executeUpdate() > 0 ? "1" : "0";
			
//			Query query3 = sessionHQL.createQuery(hqlURT)
//			    	.setParameter("userId",Integer.parseInt(userid))
//			    	.setParameter("roleId",Integer.parseInt(roleid));				   
//			msg3 = query3.executeUpdate() > 0 ? "1" : "0";
//			   
//			Query query2 = sessionHQL.createQuery(hqlLT)
//				    	.setParameter("userId",Integer.parseInt(userid));				   
//			msg2 = query2.executeUpdate() > 0 ? "1" : "0";
			
			//&& msg2.equals("1") && msg3.equals("1")
			
			if(msg1.equals("1")) {
				msg = "Data Deleted Successfully";
			}
//		}else {
//			msg = "Email Id belongs to other than student data";
//		}
		
		sessionHQL.flush();
		sessionHQL.clear();
		tx.commit();
		
		} catch (RuntimeException e) {
			e.printStackTrace();
			try {
				msg = "roll back transaction";
			} catch (RuntimeException rbe) {
				msg = "Couldn't roll back transaction"+ rbe;
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		
		return msg;
	}

}
