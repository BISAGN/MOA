package com.AyushEdu.controller.LMS_Mobile_API;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.TB_NOTIFICATION;
import com.AyushEdu.Models.TB_STATE;
import com.AyushEdu.Models.T_Domain_Value;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.Alumni.EDU_ALUM_ALUMNI_EVENT;
import com.AyushEdu.Models.Alumni.EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR;
import com.AyushEdu.Models.Alumni.EDU_ALUM_ALUMNI_PARTICIPATE;
import com.AyushEdu.Models.Alumni.EDU_ALUM_ALUMNI_POST;
import com.AyushEdu.Models.Alumni.EDU_ALUM_REG_ALUMNI_CLG;
import com.AyushEdu.Models.Alumni.TB_KNOWLEDGE_REPO;
import com.AyushEdu.Models.Alumni.TB_KNOWLEDGE_REPO_CATEGORY_MSTR;
import com.AyushEdu.Models.Curriculum.CC_TB_LIST_OF_TOPICS_PARENT;
import com.AyushEdu.Models.Curriculum.CC_TB_T3_LEARNING_OBJECT_CHILD;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_PROFESSIONAL_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_SUB_TOPICS_MSTR;
import com.AyushEdu.Models.Curriculum_Mstr.CC_TB_TOPICS_MSTR;
import com.AyushEdu.Models.Feedback.TB_FEEDBACK_DETAILS;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_INSTITUTE_REGISTRATION;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_CHILD;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_PARENT;
import com.AyushEdu.Models.LMS_Master.EDU_ACADEMIC_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_CATEGORY_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_COUNSELING_AUTHORITY_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_COURSE_MASTER;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DEGREE_MASTER;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DISTRICT_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_ELECTIVE_COURSE_MASTER;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_GENDER_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_LEVEL_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_MODULE_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYSTEM_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYS_DEG_MAP_MASTER;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_TERM_MASTER;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_TYPE_OF_DEGREE_MASTER;
import com.AyushEdu.Models.LMS_Master.TB_COUNTRY;
import com.AyushEdu.Models.LMS_Master.TB_FEEDBACK_CATEGORY_MSTR;
import com.AyushEdu.Models.LMS_Master.TB_FEEDBACK_SUBCATEGORY_MSTR;
import com.AyushEdu.Models.LMS_Master.TB_LMS_MARITAL_STATUS_MSTR;
import com.AyushEdu.Models.LMS_Master.TB_LMS_RELIGION_MSTR;
import com.AyushEdu.Models.LMS_Master.TB_SET_MASTER;
import com.AyushEdu.Models.LMS_NCISM.EDU_LMS_FREE_COURSE;
import com.AyushEdu.Models.LMS_NCISM.EDU_LMS_UPLODE_CERTIFICATE;
import com.AyushEdu.Models.LMS_Student.EDU_LMS_STUDENT_COURSE_ENROLL_SEQUENCE;
import com.AyushEdu.Models.Library_mgmt.TB_BOOK_DTL;
import com.AyushEdu.Models.Library_mgmt.TB_MEMBER_BOOK_REQ;
import com.AyushEdu.Models.Library_mgmt.TB_MEMBER_DTL;
import com.AyushEdu.Models.Masters.EDU_LMS_QUOTA_MSTR;
import com.AyushEdu.Models.Mentor_Mentee.EDU_MEN_MENTOR_MENTEE_REQUEST;
import com.AyushEdu.Models.Mentor_Mentee.EDU_Mentor_Mentee_communication;
import com.AyushEdu.Models.Placement_Mgmt.EDU_PLACEMENT_REG;
import com.AyushEdu.Models.QuizBank.EDU_LMS_EXAM_PAPER;
import com.AyushEdu.Models.QuizBank.EDU_LMS_PAPER_GENERATION;
import com.AyushEdu.Models.Registration.TB_DOCUMENT_UPLOAD;
import com.AyushEdu.Models.Registration.TB_OTHER_DOCCUMENT_UPLOAD;
import com.AyushEdu.Models.Registration.TB_PERSONAL_DETAILS;
import com.AyushEdu.Models.Registration.TB_PRE_EDUCATION_DETAILS;
import com.AyushEdu.Models.Registration.Graduation_NCISM.EDU_NCISM_REG_GRADU_DOCUMENT_UPLOAD;
import com.AyushEdu.Models.Registration.Graduation_NCISM.EDU_NCISM_REG_GRADU_OTHER_DOC_UPLOAD;
import com.AyushEdu.Models.Registration.Graduation_NCISM.EDU_NCISM_REG_GRADU_PERSONAL_DTLS;
import com.AyushEdu.Models.Registration.Graduation_NCISM.EDU_NCISM_REG_GRADU_PRE_EDU_DTLS_TBL;
import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_GRADU_EXAMNAME;
import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_PRE_EDUCATION_DETAILS;
import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_REG_DOCUMENT_UPLOAD;
import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_REG_GRADU_DTLS;
import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_REG_GRADU_SEMWISE_DTLS;
import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_REG_OTHER_DOC_UPLOAD;
import com.AyushEdu.Models.Registration.Postgraduate.EDU_PG_REG_PERSONAL_DETAILS;
import com.AyushEdu.Models.TT_Lecture.EDU_LEC_INSTRUCTION_METHOD_MSTR;
import com.AyushEdu.Models.TT_Lecture.EDU_LEC_PLAN_FOR_STUDENTS;
import com.AyushEdu.Models.Teacher_Master.TB_NCH_PREFIX_MSTR;
import com.AyushEdu.Models.Ug_Pg_Fee_Collection.EDU_LMS_DEGREE_CATE_MSTR;
import com.AyushEdu.Models.Ug_Pg_Fee_Collection.EDU_LMS_FEES_COLLECT;
import com.AyushEdu.PDF.AdmitCardDownloadPdf_old;
import com.AyushEdu.config.EmailConfig;
import com.AyushEdu.config.VideoService;
import com.AyushEdu.controller.Curriculum.DownloadCurriculumPdf;
import com.AyushEdu.dao.NotificationDAO;
import com.AyushEdu.dao.Alumni.Alumni_CREATE_EVENT_DAO;
import com.AyushEdu.dao.Alumni.Alumni_Ventures_DAO;
import com.AyushEdu.dao.Alumni.Edit_Profile_Dao;
import com.AyushEdu.dao.Alumni.Knowledge_Repo_DAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.Curriculum.Professional_Ayu_Report_Dao;
import com.AyushEdu.dao.Curriculum.T6C_View_Int_Marks_Dao;
import com.AyushEdu.dao.Curriculum.View_Que_Paper_Blue_PrintDao;
import com.AyushEdu.dao.Curriculum.View_Scheme_of_AssessmentDao;
import com.AyushEdu.dao.Examination.View_Internal_assessment_MarksDao;
import com.AyushEdu.dao.LMS_Attendance.Faculty_Attendance_ReportDao;
import com.AyushEdu.dao.LMS_Attendance.Student_Attendance_ReportDao;
import com.AyushEdu.dao.LMS_Institute.Course_EnrollDao;
import com.AyushEdu.dao.LMS_Institute.Institute_Registration_Dao;
import com.AyushEdu.dao.LMS_Institute.Stud_Elect_Courses_Dao;
import com.AyushEdu.dao.LMS_Institute.Sys_Deg_Map_Inst_DAO;
import com.AyushEdu.dao.LMS_NCISM.CourseDuration_Dao;
import com.AyushEdu.dao.LMS_NCISM.Course_Content_Dao;
import com.AyushEdu.dao.LMS_NCISM.Course_Duration_EnrollDao;
import com.AyushEdu.dao.LMS_NCISM.Free_CourseDao;
import com.AyushEdu.dao.LMS_Student.CertificateDao;
import com.AyushEdu.dao.LMS_Student.PaymentDao;
import com.AyushEdu.dao.LMS_Student.Students_Marks_ReportDao;
import com.AyushEdu.dao.LMS_Student.View_Course_Content_Dao;
import com.AyushEdu.dao.Mentor_Mentee.Mentor_Mentee_DAO;
import com.AyushEdu.dao.Placement_Mgmt.Placement_mang_Ent_Signup_DAO;
import com.AyushEdu.dao.Question_Bank.Exam_Paper_DAO;
import com.AyushEdu.dao.Registration.DocumentUploadDao;
import com.AyushEdu.dao.Registration.EducationDetailsDao;
import com.AyushEdu.dao.Registration.Std_Pers_Details_Dao;
import com.AyushEdu.dao.Registration.personaldetailsDAO;
import com.AyushEdu.dao.Registration.Graduation_NCISM.DocumentUploadNCISMDao;
import com.AyushEdu.dao.Registration.Graduation_NCISM.EducationDetailsNCISMDao;
import com.AyushEdu.dao.Registration.Graduation_NCISM.PersonaldetailsNCISMDAO;
import com.AyushEdu.dao.Registration.Graduation_NCISM.Std_Pers_Details_NCISMDao;
import com.AyushEdu.dao.Registration.Postgraduate.Declaration_PG_Dao;
import com.AyushEdu.dao.Registration.Postgraduate.DocumentUpload_PGDao;
import com.AyushEdu.dao.Registration.Postgraduate.EducationDetails_PG_Dao;
import com.AyushEdu.dao.Registration.Postgraduate.GraduationDetails_PG_Dao;
import com.AyushEdu.dao.Registration.Postgraduate.Personaldetails_PG_DAO;
import com.AyushEdu.dao.Regulation.Practitioner_RegistrationDAO;
import com.AyushEdu.dao.TT_Lecture.Student_Lecture_DAO;
import com.AyushEdu.dao.Time_Table.Academic_Schedule_DAO;
import com.AyushEdu.dao.Time_Table.TIME_TABLE_DAO;
import com.AyushEdu.dao.Ug_Pg_Fee_Collection.Ug_Pg_Fee_Collection_Dao;
import com.vader.sentiment.analyzer.SentimentAnalyzer;
import com.vader.sentiment.analyzer.SentimentPolarities;

import reactor.core.publisher.Mono;


@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class LMS_Student_Controller {

	
	 	@Autowired
		private SessionFactory sessionFactory;
	    
	    @Autowired
		CommonController common ;
	    
	    @Autowired(required=true)
		private AuthenticationManager authmanager;
	    
	    @Autowired
	    private AppUserDAO appUserDAO;
	    
		@Autowired
		Stud_Elect_Courses_Dao sdc;
		
		@Autowired
		View_Course_Content_Dao vcdao;
		
		@Autowired
		Course_Content_Dao ccd;
		
		@Autowired
		private Course_EnrollDao CDDAO;
		@Autowired
		Commondao cmdao;
		
		@Autowired
		private VideoService service;
		
		@Autowired
		Free_CourseDao free_CourseDao;
		
		@Autowired
		private Course_Duration_EnrollDao CDEDAO;
		
		@Autowired
		CourseDuration_Dao CD;
		
		@Autowired
		Exam_Paper_DAO edao;
		
		@Autowired
		EducationDetailsDao edao1NCH;
		
		@Autowired
		EducationDetailsNCISMDao edao1;
		

		@Autowired
		EducationDetails_PG_Dao edao1PG;
		
		@Autowired
		PaymentDao pdao;
		
		@Autowired
		Course_Content_Dao Cdao;
		

		@Autowired
		private CertificateDao sdao;
		
		@Autowired
		Commondao cdao;
		
		@Autowired
		TIME_TABLE_DAO ttdao;
		
		@Autowired
		Professional_Ayu_Report_Dao PARDAO;		
		 
	    @Autowired
		View_Scheme_of_AssessmentDao vsaDAO;	
	    
	    @Autowired
	    View_Que_Paper_Blue_PrintDao qpbao;
	
	    @Autowired
		Placement_mang_Ent_Signup_DAO  ARdao;
	    
		@Autowired 
		Alumni_CREATE_EVENT_DAO aedao;

		@Autowired
		Alumni_Ventures_DAO AlumVenDao;

		@Autowired
		private JavaMailSender mailSender;
		

		@Autowired
		private Knowledge_Repo_DAO knowdao;
		
		@Autowired
		Edit_Profile_Dao TDDAO;
		
		
		@Autowired
		private Practitioner_RegistrationDAO  prdao;

		@Autowired
		View_Internal_assessment_MarksDao IAM;
		
		@Autowired
		private T6C_View_Int_Marks_Dao tcDAO;

		@Autowired
		Student_Attendance_ReportDao  SARdao;
		
		@Autowired
		Academic_Schedule_DAO asdao;
		
		@Autowired
		 PersonaldetailsNCISMDAO da;
		
		@Autowired
		 Personaldetails_PG_DAO daPG;
		
		@Autowired
		 personaldetailsDAO daNCH;
		

		 
		@Autowired
		 Ug_Pg_Fee_Collection_Dao upd;
		
		@Autowired
		Institute_Registration_Dao IRdao;
		
		@Autowired
		Student_Lecture_DAO Sdao;
		
		@Autowired
		Faculty_Attendance_ReportDao FARdao;
		
		@Autowired
		private DocumentUploadNCISMDao docdao;
		
		@Autowired
		private DocumentUploadDao docdaoNCH;
		
		@Autowired
		private DocumentUpload_PGDao docdaoPG;
		
		@Autowired
		Std_Pers_Details_NCISMDao pdd;
		
		@Autowired
		Std_Pers_Details_Dao pddNCH;
		
		@Autowired
		Declaration_PG_Dao pddPG;
		
		@Autowired
		private GraduationDetails_PG_Dao gddao;
		
		@Autowired
		Sys_Deg_Map_Inst_DAO  dmdao;
		
		@Autowired
		Students_Marks_ReportDao SMRdao;
		
		@Autowired
		EmailConfig emailsend;
		
		@Autowired
		LMS_Student_Dao lmsDao;
		
		@Autowired
		NotificationDAO notiDao;
		
		
		@Autowired
		Mentor_Mentee_DAO mdao;
		
	  @CrossOrigin( origins = "*" )
	  @RequestMapping(value = "/grievance_status1", method = RequestMethod.POST)
		public @ResponseBody JSONObject grievance_status(@RequestBody Map<String, String> data) throws Exception{
			  Collection<JSONObject> items = new ArrayList<JSONObject>();
			  JSONObject JSON =  new JSONObject();
			  System.out.println("grievance_status---");
		      String userid = data.get("user_id");
		      System.out.println("PD2--"+userid);
		     return JSON;
	   }
	  
	  
	  @CrossOrigin( origins = "*" )
		@RequestMapping(value="/login",method = RequestMethod.POST)
		public @ResponseBody JSONObject loginHet(@RequestBody Map<String, String> data) throws Exception{
		  JSONObject ob = new JSONObject();
		  System.err.println("object------");
			try {
			this.authmanager.authenticate(new UsernamePasswordAuthenticationToken(data.get("username"), data.get("password")));
			}catch (BadCredentialsException e) {
				System.err.println("err---");
				ob.put("token", "USER NOT FOUND");
	    		throw new ResponseStatusException(HttpStatus.FORBIDDEN, "BAD CREDENTIALS");
			}
			System.out.println("data---"+data);
			Authentication auth = 		this.authmanager.authenticate(new UsernamePasswordAuthenticationToken(data.get("username"), data.get("password")));
			System.out.println("data2---");
			UserLogin appUser = this.appUserDAO.findUserAccount(data.get("username"));
			String roleid = auth.getAuthorities().iterator().next().toString();
			//int roleid = 2;
			System.out.println("appUser---"+appUser+"roleid---"+roleid);
//			Role roleList = appRoleDAO.findRole_url(roleid);
			

	        if (appUser != null) {

			ob.put("name", appUser.getLogin_name());
			ob.put("token", "SUCCESSFUL");
			ob.put("role", roleid);
			//ob.put("role", appUser.get);

	        }else {
	    		ob.put("token", "USER NOT FOUND");

	        }
			return ob;
		}
	  
	  @CrossOrigin( origins = "*" )
		 @RequestMapping(value = "/getUserDetails_API", method = RequestMethod.POST)
			public @ResponseBody List<Map> getUserDetails_API(@RequestBody Map<String, String> data,HttpSession session) throws ParseException {
			 String username = data.get("username");//377
			
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				String hqlUpdate = "from UserLogin where username=:username";
				Query query = sessionHQL.createQuery(hqlUpdate).setParameter("username", username);
				List<Map> list = (List<Map>) query.list();
				tx.commit();
				sessionHQL.close();
				return list;
			}

//---get user role----	  
	  @CrossOrigin( origins = "*" )
		 @RequestMapping(value = "/getRole_API", method = RequestMethod.POST)
			public @ResponseBody ArrayList<ArrayList<String>> getRole_API(@RequestBody Map<String, String> data,HttpSession session) throws ParseException {
			 String user_id = data.get("user_id");//377
			
				
				return lmsDao.getRole(user_id);
			}

	  
	  	//-------------ELECTIVE COURSE API START------------//
	  
	  //-------------courses new-----------
	  @RequestMapping(value = "/getCourses_Description_fetch_new_api", method = RequestMethod.POST)
		public @ResponseBody ArrayList<ArrayList<String>> getCourses_Description_fetch_new_api(@RequestBody Map<String, String> data) {
				
			String userId = data.get("userId").toString();
			System.err.println("userId shivali-------------------"+userId);
			String system_id1 = sdc.getsystem_list(userId).get(0).get(0);
		System.err.println("system_id2 shivali-------------------"+system_id1);
			String degree_id1 = sdc.getdegree_list(userId).get(0).get(0);
			//System.err.println("degreeid shivali-------------------"+degree_id1);
			String term_id1 = sdc.getterm_list(userId).get(0).get(0);
			System.err.println("getterm_list shivali-------------------"+term_id1);
			
			//no filter then all 3 are 0
			String c_id = data.get("c_id");
			String course_duration = data.get("course_duration");
			String course_start_date = data.get("course_start_date");
			//72 52 weeks28-JUN-2022
			System.err.println("c_id shivali-------------------"+c_id+" "+course_duration+""+course_start_date);

			return sdc.getCourse_Description_fetch_new(userId,c_id,course_duration,course_start_date,system_id1,degree_id1,term_id1);
		}	 
	  
	  //-----------my courses new-------
	  @RequestMapping(value = "/getDescriptionfetchAlreadyAppliedMyCoursesAPI", method = RequestMethod.POST)
		public @ResponseBody ArrayList<ArrayList<String>> getDescriptionfetchAlreadyAppliedMyCoursesAPI(@RequestBody Map<String, String> data) {
		  
		  String userId = data.get("userId").toString();
			System.err.println("userId shivali-------------------"+userId);
			String system_id2 = sdc.getsystem_list(userId).get(0).get(0);
			System.err.println("system_id2 shivali-------------------"+system_id2);
			String degree_id2 = sdc.getdegree_list(userId).get(0).get(0);
			//System.err.println("degreeid shivali-------------------"+degree_id1);
			String term_id2 = sdc.getterm_list(userId).get(0).get(0);
			System.err.println("getterm_list shivali-------------------"+term_id2);
			
			//no filter then all 3 are 0
			String c_id2 = data.get("c_id");
			String course_duration2 = data.get("course_duration");
			String course_start_date2 = data.get("course_start_date");
			System.err.println("c_id shivali-------------------"+c_id2+" "+course_duration2+""+course_start_date2);

			return sdc.getDescriptionfetchAlreadyAppliedMyCourses_List(userId,c_id2,course_duration2,course_start_date2,system_id2,degree_id2,term_id2);
		}
		//----------Upcoming_Enroll_Open-------------------------
		@RequestMapping(value = "/getCourse_Description_fetch_API", method = RequestMethod.POST)
		public @ResponseBody ArrayList<ArrayList<String>> getCourse_Description_fetch_API(@RequestBody Map<String, String> data) {
			
			String c_id = data.get("c_id");
			String course_duration = data.get("course_duration");
			String course_start_date = data.get("course_start_date");
			String userId = data.get("userId");			
			String system_id = sdc.getsystem_list(userId).get(0).get(0);
			String institute_id = common.getInstIdfromUserid(sessionFactory,userId).get(0);	

			System.err.println("MY COURSE API"+c_id);
			System.err.println("userid============"+userId+"system_id--"+system_id+"institute_id---"+institute_id);
			
			return sdc.getCourse_Description_fetch_list(userId,c_id,course_duration,course_start_date,system_id,institute_id);
		}
		
	  	//------OnGoing_Enroll_Closed_Path-------------------------
		/*
		 * @RequestMapping(value =
		 * "/getCourse_Description_fetch_OnGoing_Enroll_Closed_API", method =
		 * RequestMethod.POST) public @ResponseBody ArrayList<ArrayList<String>>
		 * getCourse_Description_fetch_OnGoing_Enroll_Closed_API(@RequestBody
		 * Map<String, String> data) {
		 * 
		 * String course_category1 = data.get("course_category1"); String
		 * course_duration1 = data.get("course_duration1"); String course_start_date1 =
		 * data.get("course_start_date1"); // String system_id1 =
		 * data.get("system_id1"); String userId = data.get("userId"); String system_id1
		 * = sdc.getsystem_list(userId).get(0).get(0);
		 * 
		 * System.err.println("course_category1============"+course_category1+
		 * "course_duration1---"+
		 * course_duration1+"course_start_date1---"+course_start_date1+"system_id1---"+
		 * system_id1+"userId---"+userId);
		 * 
		 * return
		 * CD.getCourse_Description_fetch_OnGoing_Enroll_Closed_list(course_category1,
		 * course_duration1,course_start_date1,userId);
		 * 
		 * }
		 */
			 
	   //---------------------Already Applied(My Courses)----------------------------
//		@RequestMapping(value = "/getCourse_Description_fetch_Already_Applied_API", method = RequestMethod.POST)
//		public @ResponseBody ArrayList<ArrayList<String>> getCourse_Description_fetch_Already_Applied_API(@RequestBody Map<String, String> data) {
//	
//			  String userId = data.get("userId");
//			 // String system_id2 = data.get("system_id");
//			  String c_id2 = data.get("c_id2");
//			  String course_duration2 = data.get("course_duration2");
//			  String course_start_date2 = data.get("course_start_date2");			  
//			  String system_id2 = sdc.getsystem_list(userId).get(0).get(0);
//			  
//			  System.err.println("c_id2============"+c_id2+"course_duration2---"+  course_duration2+"course_start_date2---"+course_start_date2+"system_id2---"+ system_id2+"userId---"+userId); 
//			  			
//			  return sdc.getCourse_Description_fetch_Already_Applied(userId,c_id2,course_duration2,course_start_date2,system_id2);
//		}
	   
	   
		
		//-----Course Duration List----
		@CrossOrigin( origins = "*" )
		@PostMapping(value = "/getCourse_Duration_API", produces = "application/json")
		public @ResponseBody ArrayList<ArrayList<String>> getCourse_Duration_API(@RequestBody Map<String, String> data) throws Exception{
		    //Collection<JSONObject> items = new ArrayList<JSONObject>();
			//JSONObject JSON =  new JSONObject();

		      String userId = data.get("userId");
			  String system_id = sdc.getsystem_list(userId).get(0).get(0);
			  System.err.println("userid============"+userId+"system_id---"+system_id);
				
		    //List<Map<String, Object>> list1 = help.getIntStringByResult("getlistPto_details",0,army_no);	 
			// System.out.println("PD--"+list1);
			//JSON.put("status", list1);
			
		     return sdc.getCourse_Duration(sessionFactory,userId,system_id);

		}
		
		//-----Course Start Date List----
		@CrossOrigin( origins = "*" )
		@PostMapping(value = "/getCourse_Start_Date_API", produces = "application/json")
		public @ResponseBody ArrayList<ArrayList<String>> getCourse_Start_Date_API(@RequestBody Map<String, String> data) throws Exception{
		   
		      String userId = data.get("userId");
			  String system_id = sdc.getsystem_list(userId).get(0).get(0);
			  System.err.println("userid============"+userId+"system_id---"+system_id);				
			
		     return sdc.getCourse_Start_Date(sessionFactory,system_id);

		}
		
		//-----Course Category List----
		@CrossOrigin( origins = "*" )
		@PostMapping(value = "/getcoursenameList_API", produces = "application/json")
		public @ResponseBody List<EDU_LMS_ELECTIVE_COURSE_MASTER> getcoursenameList_API(@RequestBody Map<String, String> data) throws Exception{
		   
		      String userId = data.get("userId");
			 // String system_id = sdc.getsystem_list(userId).get(0).get(0);
			 // System.err.println("userid============"+userId+"system_id---"+system_id);				
			return common.getcoursebystudentEnroll(sessionFactory,userId);
		    // return sdc.getcoursenameList_new(sessionFactory,system_id);

		}
		

		//-----Course Category List New----
		@CrossOrigin( origins = "*" )
		@PostMapping(value = "/getcoursenameListNew_API", produces = "application/json")
		public @ResponseBody List<EDU_LMS_ELECTIVE_COURSE_MASTER> getcoursenameListNew_API(@RequestBody Map<String, String> data) throws Exception{
		   
		      String userId = data.get("userId");
			  String system_id = sdc.getsystem_list(userId).get(0).get(0);
			  System.err.println("userid============"+userId+"system_id---"+system_id);				
			
		     return common.getcoursenameList(sessionFactory);

		}
		
		
		//---video elective course----
		@CrossOrigin( origins = "*" )
		 @RequestMapping(value = "/videoChoose_Ele_Course_Stu_API")
			public void videoChoose_Ele_Course_Stu_API(@ModelAttribute("i_id") String id,HttpServletRequest request, HttpServletResponse response) throws IOException {
				final int BUFFER_SIZE = 4096;
				String filePath = sdc.getTopicChoose_Ele_Course_Stu(id);
				//String filePath = "/srv/VideoPath/20181014.mp4";
				System.out.println("filePath----------------"+filePath);
				//model.put("filePath", filePath);
				ServletContext context = request.getSession().getServletContext();
				
				try {
					if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

						String fullPath = request.getRealPath("/") + "admin\\js\\video\\05_Slope.mp4";
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
						String headerKey = "Content-Disposition";
						String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
						response.setHeader(headerKey, headerValue);
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

					String fullPath = request.getRealPath("/") + "admin\\js\\video\\05_Slope.mp4";
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
		
		//-----Image my course-----
		@RequestMapping(value = "/Image_Fetch_Path_Already_Applied_My_CoursesAPI", method = RequestMethod.GET)
		public void Image_Fetch_Path_Already_Applied_My_CoursesAPI(@ModelAttribute("i_id") String id, @ModelAttribute("userId") String userId, 
				HttpServletRequest request, HttpServletResponse response) throws IOException {
			
		//	String userId = session.getAttribute("userId_for_jnlp").toString();
			String system_id = sdc.getsystem_list(userId).get(0).get(0);
//			System.err.println("system_id============"+system_id);

			final int BUFFER_SIZE = 4096;

			String i_id = id;

			
			String filePath = sdc.Already_Applied_Path_fetch_list_My_Courses(i_id,userId,system_id);
			
			//String filePath = "/srv/samadhan/1/__2022_05_05_03_05_00_753.png";
			
//			System.out.println("shivali---------Already_Applied_list_Path_fetch_list--------------" + filePath);

			//model.put("filePath", filePath);

			ServletContext context = request.getSession().getServletContext();

			try {

				if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

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
//						request.getRealPath("/") + "/srv/Document/No_Image.jpg";
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
		
		//----course enroll set video -----
		@RequestMapping(value = "/ElectCoursevideoplay_API",method = RequestMethod.GET)
		public void ElectDemovideoplay_API(String id,
			 HttpServletRequest request, HttpServletResponse response) throws IOException {
			final int BUFFER_SIZE = 4096;
			String filePath = "";

			filePath = CDDAO.getCoursedemoVideoPath(Integer.parseInt(id));
			//model.put("filePath", filePath);
			ServletContext context = request.getSession().getServletContext();

			try {
				if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

					String fullPath = request.getRealPath("/") + "admin\\js\\video\\05_Slope.mp4";
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
					String headerKey = "Content-Disposition";
					String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
					response.setHeader(headerKey, headerValue);
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

				String fullPath = request.getRealPath("/") + "admin\\js\\video\\05_Slope.mp4";
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
		
		//-------------ELECTIVE COURSE API END------------//
		
		
		 
		//-------------COURSE ENROLLMENT API START----------//
		 
		
		//------get system id----
		 @CrossOrigin( origins = "*" )
		 @RequestMapping(value = "/getSystem_id_API", method = RequestMethod.POST)
			public @ResponseBody String getSystem_id_API(@RequestBody Map<String, String> data) {
		    	//33
			// String course_id = data.get("course_id");
			 String userId = data.get("userId");
			 
			 String list = sdc.getsystem_list(userId).get(0).get(0); 
				return list;
			}
		 
		//------get degree id----
		 @CrossOrigin( origins = "*" )
		 @RequestMapping(value = "/getDegree_id_API", method = RequestMethod.POST)
			public @ResponseBody String getDegree_id_API(@RequestBody Map<String, String> data) {
		    	//23
			// String course_id = data.get("course_id");
			 String userId = data.get("userId");
			 
			 String list = sdc.getdegree_list(userId).get(0).get(0);
				return list;
			}
		 
		//------get term id----
		 @CrossOrigin( origins = "*" )
		 @RequestMapping(value = "/getTerm_id_API", method = RequestMethod.POST)
			public @ResponseBody String getTerm_id_API(@RequestBody Map<String, String> data) {
		    	//1
			// String course_id = data.get("course_id");244
			 String userId = data.get("userId");
			 
			 String list = sdc.getterm_list(userId).get(0).get(0);
				return list;
			}
		
		//---Need to check---
		 @CrossOrigin( origins = "*" )
		 @RequestMapping(value = "/getCourse_Set_API", method = RequestMethod.POST)
			public @ResponseBody List<ArrayList<String>> getCourse_Set_API(@RequestBody Map<String, String> data) {
		    	
			// String course_id = data.get("course_id");
			 String system_id = data.get("system_id");
			 String term_id = data.get("term_id");
			 String degree_id = data.get("degree_id");
			 String userid = data.get("userid");
			 List<ArrayList<String>> list = CDDAO.GetCourse_Set(system_id,term_id,degree_id,userid);	 
				return list;
			}
		 
		//----module fetch usable----
		 @CrossOrigin( origins = "*" )
		 @RequestMapping(value = "/getModule_fetch_API", method = RequestMethod.POST)
			public @ResponseBody List<ArrayList<String>> getModule_fetch_API(@RequestBody Map<String, String> data) {
		    	
//			 String set = data.get("set");
			 String course_id = data.get("course_id");
			 List<ArrayList<String>> list = CDDAO.GetModule_fetch(course_id);	 
				return list;
			}
		 
//		 //----Summary-----
		 @CrossOrigin( origins = "*" )
		 @RequestMapping(value = "/getSummary_API", method = RequestMethod.POST)
			public @ResponseBody List<ArrayList<String>> getSummary_API(@RequestBody Map<String, String> data) {
		    	
			 String course_id = data.get("course_id");
			 String system_id = data.get("system_id");
			 String term_id = data.get("term_id");
			 String degree_id = data.get("degree_id");
			 System.err.println("course_id2222111--"+course_id);
			 List<ArrayList<String>> list = CDDAO.GetSummary(course_id);	
			 //List<ArrayList<String>> list = CDDAO.getSummarydata(system_id,term_id,degree_id);	
				return list;
			}
		 
		 //----Course Description(About the Course)-----
		 @CrossOrigin( origins = "*" )
		 @RequestMapping(value = "/getCourse_Description_API", method = RequestMethod.POST)
			public @ResponseBody List<ArrayList<String>> getCourse_Description_API(@RequestBody Map<String, String> data) {
		    	System.out.println("getCourse----");
			 //String course_id = data.get("course_id");
			 String user_id = data.get("userid");
			 String system_id = data.get("system_id");
			 String degree_id = data.get("degree_id");
			 String p_id = data.get("p_id");
			 System.err.println("user_id---"+user_id+"system_id--"+system_id+"degree_id---"+degree_id+"p_id---"+p_id);
			 List<ArrayList<String>> list = CDDAO.GetCourse_Description(user_id,system_id,degree_id,p_id);	 
				return list;
			}
		 
		 //----Course Title-----
//		 @CrossOrigin( origins = "*" )
//		 @RequestMapping(value = "/getCourse_Title_API", method = RequestMethod.POST)
//			public @ResponseBody List<ArrayList<String>> getCourse_Title_API(@RequestBody Map<String, String> data) {
//		    	
//			 String course_id = data.get("course_id");
//			 List<ArrayList<String>> list = CDDAO.GetCourse_Title(course_id);	 
//				return list;
//			}
		 
		 //----Learner Enrolled Counts----
		 @CrossOrigin( origins = "*" )
		 @RequestMapping(value = "/getLearn_Count_API", method = RequestMethod.POST)
			public @ResponseBody List<ArrayList<String>> getLearn_Count_API(@RequestBody Map<String, String> data) {
		    	
			 String course_id = data.get("course_id");
			 List<ArrayList<String>> list = CDDAO.GetLearn_Count(course_id);	 
				return list;
			}
		 
		 //---course exit----
		 @CrossOrigin( origins = "*" )
		 @RequestMapping(value = "/Course_Exit_API", method = RequestMethod.POST)
			public @ResponseBody List<ArrayList<String>> Course_Exit_API(@RequestBody Map<String, String> data) {
			// String course_id = data.get("course_id");
			 String userid = data.get("userid");
			 String system_id = data.get("system_id");
			 String degree_id = data.get("degree_id");
			 List<ArrayList<String>> list = CDDAO.GetCourse_Exit(system_id,degree_id,userid);	 
				return list;
			}
		 
		
		 //----join date fetch----
//		 @CrossOrigin( origins = "*" )
//		 @RequestMapping(value = "/getjoindate_fetch_API", method = RequestMethod.POST)
//			public @ResponseBody List<ArrayList<String>> getjoindate_fetch_API(@RequestBody Map<String, String> data) {
//			 
//			 String course_id = data.get("course_id");
//			 String userid = data.get("userid");
//			 
//			 List<ArrayList<String>> list = CDDAO.Getjoindate_fetch(course_id,userid);	 
//				return list;
//			}
		 
		 //---credit point---
//		 @CrossOrigin( origins = "*" )
//		 @RequestMapping(value = "/getCredit_Point_API", method = RequestMethod.POST)
//			public @ResponseBody List<ArrayList<String>> getCredit_Point_API(@RequestBody Map<String, String> data) {
//		    	
//			 String course_id = data.get("course_id");
//			 
//			 List<ArrayList<String>> list = CDDAO.GetCredit_Point(course_id);	 
//				return list;
//			}
		 
		 //----module fetch not usable----
		 @CrossOrigin( origins = "*" )
		 @RequestMapping(value = "/getSetModule_Fetch_API", method = RequestMethod.POST)
			public @ResponseBody List<ArrayList<String>> getSetModule_Fetch_API(@RequestBody Map<String, String> data) {
			 
			 String course_id = data.get("course_id");
			 String userid = data.get("userid");
			 
			 List<ArrayList<String>> list = CDEDAO.GetSetModule_Fetch(course_id,userid);	 
				return list;
			}
		 
		 
		//----video for couser enrollment----
		 @CrossOrigin( origins = "*" )
		 @RequestMapping(value = "/videocourseenroll_API", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
			public void videocourseenroll_API(
					@ModelAttribute("kmlFileId65") String id, /* @ModelAttribute("fildname") String fildname, */HttpServletRequest request, HttpServletResponse response) throws IOException {
				final int BUFFER_SIZE = 4096;
				String filePath = CDDAO.getTopicVideoPath(id);
				System.out.println("filePath1----------------"+filePath);
				//model.put("filePath", filePath);
				ServletContext context = request.getSession().getServletContext();
				
				try {
					System.out.println("filePath2----------------"+filePath);
					
					if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {
						System.out.println("filePath-------if---------"+filePath);
						String fullPath = request.getRealPath("/") + "admin\\js\\video\\05_Slope.mp4";
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
						OutputStream outStream = response.getOutputStream();
						byte[] buffer = new byte[BUFFER_SIZE];
						int bytesRead = -1;
						while ((bytesRead = inputStream.read(buffer)) != -1) {
							outStream.write(buffer, 0, bytesRead);
						}
						inputStream.close();
						outStream.close();
					} else {
						System.out.println("filePath3-------else---------"+filePath);
						String fullPath = filePath;

						System.out.println("fullPath1-------else---------"+fullPath);
						File downloadFile = new File(fullPath);

						System.out.println("downloadFile-------else---------"+downloadFile);
						FileInputStream inputStream = new FileInputStream(downloadFile);

						System.out.println("fullPath2-------else---------"+fullPath);
						String mimeType = context.getMimeType(fullPath);
						if (mimeType == null) {
							mimeType = "application/octet-stream";
						}
						response.setContentType(mimeType);
						response.setContentLength((int) downloadFile.length());
						String headerKey = "Content-Disposition";
						String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
						System.out.println("headerValue-------else---------"+headerValue);
						response.setHeader(headerKey, headerValue);
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

					String fullPath = request.getRealPath("/") + "admin\\js\\video\\05_Slope.mp4";
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
		 
		 //----course enroll action------
		 @CrossOrigin( origins = "*" )
		 @PostMapping(value = "/course_enrollaction_API")
			public @ResponseBody JSONObject course_enrollaction_API(@RequestBody Map<String, String> data)throws IOException, ParseException {

			 JSONObject ob = new JSONObject();
			 EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_PARENT td = new EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_PARENT();
			 EDU_LMS_STUDENT_COURSE_ENROLL_SEQUENCE ts = new EDU_LMS_STUDENT_COURSE_ENROLL_SEQUENCE();
				int id = td.getId() > 0 ? td.getId() : 0;
			
				Date date = new Date();
				
				String course_fee = data.get("course_fee");
				String username = data.get("username").toString();
				String userid = data.get("userId").toString();
				
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				
				String system_hid = data.get("system_hid");
				String degree_hid = data.get("degree_hid");
				String course_hid = "";
				System.err.println("helloo---");
				//System.err.println("data.get(\"set_id\")---"+data.get("set_id"));
				List<String> set_id = new ArrayList<String>();
				List<String> course_id1 = new ArrayList<String>();
				List<String> course_id2 = new ArrayList<String>();
				List<String> course_id3 = new ArrayList<String>();
				set_id = Arrays.asList(data.get("set_id").split(","));
				course_id1 = Arrays.asList(data.get("course_id1").split(","));
				course_id2 = Arrays.asList(data.get("course_id2").split(","));
				course_id3 = Arrays.asList(data.get("course_id3").split(","));
					System.err.println("set---"+set_id);
					System.err.println("course_id1---"+course_id1);
					System.err.println("course_id2---"+course_id2);
					System.err.println("course_id3---"+course_id3);
				System.err.println("system_hid--------------->    "+system_hid);
				System.err.println("degree_hid--------------->    "+degree_hid);
				System.err.println("userid--------------->    "+userid);
				System.err.println("system_hid--------------->    "+system_hid);
				
				int length_set = set_id.size();
				try {
					Long c = null;
					for (int k = 0; k < length_set; k++) {
						
						//String new_elective_name = data.get("new_elective_name"+k);
						//String set_hid = data.get("setid"+k);
						String set_hid = "0";
						List<String> newList = new ArrayList<String>();
						if(k==0) {
							 set_hid = set_id.get(0);
							 newList = course_id1;
						}else if(k==1) {
							 set_hid = set_id.get(1);
							 newList = course_id2;
						}else if(k==2) {
							 set_hid = set_id.get(2);
							 newList = course_id3;
						}
						System.err.println("set_hid---"+set_hid);
						System.err.println("newList---"+newList);
						
//						if (new_elective_name != null && !new_elective_name.equals("")) {
//							newList = Arrays.asList(new_elective_name.split(","));
//						}
					
						if (newList.size() > 0) {
							
							for (int i = 0; i < newList.size(); i++) {
							
//								 c = (Long) sessionHQL.createQuery(
//										"select count(id) from  EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_CHILD where   user_id=:userid  and set_id=:set_hid "
//										+ "and course_id=:course_id and id !=:id") //and status=:status
//								.setParameter("userid", Integer.parseInt(userid))
//								.setParameter("set_hid", Integer.parseInt(set_hid))
//								.setParameter("course_id" , Integer.parseInt(newList.get(i)))
////								.setParameter("status", "1")
//								.setParameter("id", id).uniqueResult();
							}
						}
					}
					
					

					
	  			if (id == 0) {
//						if (c == 0) {
							
							td.setSystem_id(Integer.parseInt(system_hid));
							td.setDegree_id(Integer.parseInt(degree_hid));
							td.setStatus("1");
							td.setEle_course_id(0);
							td.setUser_id(Integer.parseInt(userid));
							td.setSet_id(0);
							td.setPayment_status(0);
							td.setCreated_by(username);
							td.setCreated_date(date);
							
							int parent_id = (int) sessionHQL.save(td);
							sessionHQL.flush();
							sessionHQL.clear();
							
							EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_CHILD od = new EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_CHILD();
//							Session sessionHQL2 = this.sessionFactory.openSession();
//							Transaction tx2 = sessionHQL2.beginTransaction();
//							String system_hid = data.get("system_hid");
							
							for (int k = 0; k < length_set; k++) {
								
//								String new_elective_name = data.get("new_elective_name"+k);
								String set_hid = "0";
								List<String> newList = new ArrayList<String>();
								if(k==0) {
									 set_hid = set_id.get(0);
									 newList = course_id1;
								}else if(k==1) {
									 set_hid = set_id.get(1);
									 newList = course_id2;
								}else if(k==2) {
									 set_hid = set_id.get(2);
									 newList = course_id3;
								}
								System.err.println("set_hid---"+set_hid);
								System.err.println("newList---"+newList);
								//String set_hid = data.get("setid"+k);
								
								
//								if (new_elective_name != null && !new_elective_name.equals("")) {
//									newList = Arrays.asList(new_elective_name.split(","));
//								}
							if(newList.toString() != "[]") {
								if (newList.size() > 0) {
									
									for (int i = 0; i < newList.size(); i++) {
									
										od.setCourse_id(Integer.parseInt(newList.get(i)));
									 	od.setSet_id(Integer.parseInt(set_hid));
									 	od.setSystem_id(Integer.parseInt(system_hid));
									 	od.setP_id(parent_id);
									 	od.setCreated_by(username);
									 	od.setUser_id(Integer.parseInt(userid));
									 	od.setCreated_date(date);
										od.setStatus("0");
										
										course_hid+=newList.get(i)+",";
										
										sessionHQL.save(od);
										sessionHQL.flush();
										sessionHQL.clear();
									}
								}}
							}
							System.err.println("CID-=-=-=-=-=-=-=-=-=-="+course_hid);
							String cids[] = course_hid.split(",");
							System.err.println("cids[]-=-=-=-=-=-=-=-=-=-="+cids);
							for(int x=0;x < cids.length;x++) {
								
								List<ArrayList<String>> list = CDDAO.GetlevelofCoursese(cids[x]);
								System.err.println(x+"---"+list);
								
								for(int j=0;j<list.size();j++) {
									
									String lvl_module = list.get(j).get(1);
									System.err.println("---"+lvl_module);
									String module_id = list.get(j).get(2);
									int seq =Integer.parseInt(common.getsequenceList(sessionFactory,lvl_module).get(0).getSequence_name());
									String cc_id = list.get(j).get(3);
									
										ts.setCreated_by(username);
										ts.setCreated_date(date);
										ts.setCourse_id(Integer.parseInt(cids[x]));
										ts.setUser_id(Integer.parseInt(userid));
										ts.setLevel_of_module(lvl_module);
										ts.setModule_id(Integer.parseInt(module_id));
										ts.setSequence(seq);
										ts.setStatus(0);
										ts.setCc_id(Integer.parseInt(cc_id));
										
										sessionHQL.save(ts);
										sessionHQL.flush();
										sessionHQL.clear();
								
								}
								
							}
						
							
							tx.commit();
//							tx2.commit();
							ob.put("msg", "YOU HAVE ENROLL SUCCESSFULLY.");
							ob.put("id", parent_id);
							ob.put("course_fee", course_fee);
							
//						} else {
//							ob.put("msg", "YOU HAVE ENROLL ALREADY.");
//							//return new ModelAndView("redirect:Stud_Elect_Courses_Url");
//						}
					}

				} catch (RuntimeException e) {
					try {
						ob.put("msg", "roll back transaction");
					} catch (RuntimeException rbe) {
						ob.put("msg", "Couldn't roll back transaction " + rbe);
					}
					throw e;
				} finally {
					if (sessionHQL != null) {
						sessionHQL.close();
					}
				}
				return ob;
			}
		 
		 
		 //----exit from course----
		 @CrossOrigin( origins = "*" )
		 @RequestMapping(value = "/ExitFromCourseActionAPI", method = RequestMethod.POST)
		 public @ResponseBody JSONObject ExitFromCourseActionAPI(@RequestBody Map<String, String> data) {
			 
			 System.err.println("helloooo----");

			 JSONObject ob = new JSONObject();
			 String userid = data.get("userId").toString();
			 String id = data.get("course_id");
			 List<String> liststr = new ArrayList<String>();
			 Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				
			 try {
				 
					String hqlUpdatechild ="update from EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_CHILD set status='2' where user_id="+Integer.parseInt(userid)+" and id="+Integer.parseInt(id)+" ";
					int appchild = sessionHQL.createQuery(hqlUpdatechild)
							.executeUpdate();
					
					tx.commit();
					if ( appchild > 0) {
						liststr.add("Exit from Course Successfully");
					} else {
						liststr.add("Exit from Course UnSuccessfully");
					}
					ob.put("msg", liststr.get(0));
				} catch (RuntimeException e) {
					try {
						tx.rollback();
						ob.put("msg", "Roll back transaction");
					} catch (RuntimeException rbe) {
						ob.put("msg", "Couldn't roll back transaction " + rbe);
					}
					throw e;
				} 
					finally {
					if (sessionHQL != null) {
						sessionHQL.close();
					}
				}
			 return ob;
		}

			//-------------COURSE ENROLLMENT API END----------//
		 
		 //-------------PAYMENT API START-------------------//
		 @CrossOrigin( origins = "*" )
		 @RequestMapping(value = "/getCourse_Set_paymentAPI", method = RequestMethod.POST)
			public @ResponseBody List<ArrayList<String>> getCourse_Set_paymentAPI(@RequestBody Map<String, String> data) {
			 
			 String p_id = data.get("p_id");
			 System.err.println("p_id------------------------------"+p_id);
			 String userid = data.get("userid");
			 List<ArrayList<String>> list = pdao.GetCourse_Set_payment(p_id,userid);	 
				return list;
			}
		 
		 
		 @CrossOrigin( origins = "*" )
		 @RequestMapping(value = "/PaymentActionAPI", method = RequestMethod.POST)
			public @ResponseBody JSONObject PaymentActionAPI( @RequestBody Map<String, String> data,BindingResult result,
					HttpServletRequest request, ModelMap model, HttpSession session, Principal principal) {
			 
			 JSONObject ob = new JSONObject();
			 EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_PARENT td = new EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_PARENT();
			 //String userid = data.get("userId").toString();
			 List<String> liststr = new ArrayList<String>();
			 Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				
				String id= data.get("enroll_pid").toString();
				
			 try {
				 
					String hqlUpdatechild ="update from EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_PARENT set payment_status='1' where id=:id";
					int appchild = sessionHQL.createQuery(hqlUpdatechild)
//							.setInteger("userid",Integer.parseInt(userid))
							.setInteger("id",Integer.parseInt(id))
							.executeUpdate();
					
					tx.commit();
					if ( appchild > 0) {
						liststr.add("Payment  Successfully.");
//						ra.addAttribute("msg", " Payment  Successfully.");
					}
//					else {
//						liststr.add("Exit from Course UnSuccessfully");
//					}
					ob.put("msg", liststr.get(0));
				} catch (RuntimeException e) {
					try {
						tx.rollback();
						ob.put("msg", "Roll back transaction");
					} catch (RuntimeException rbe) {
						ob.put("msg", "Couldn't roll back transaction " + rbe);
					}
					throw e;
				} finally {
					if (sessionHQL != null) {
						sessionHQL.close();
					}
				}
			 return ob;
		}
		//-------------PAYMENT API END-------------------//
		 
		 //-----------skill enhancement api start-----------------//
		 
		 //---skill enhancement main page all data----
		 @CrossOrigin( origins = "*" )
		 @RequestMapping(value = "/getFilterFc_url_data2_API", method = RequestMethod.POST)
			public @ResponseBody ArrayList<ArrayList<String>> getFilterAlu_url_data2_API(@RequestBody Map<String, String> data) {
			System.err.println("kkk");
			 String url = data.get("url");
			 String coursename = data.get("coursename");
			 String description = data.get("description");
			 String role = data.get("role");	
			 
				return free_CourseDao.DataTablefc_urlDataList2(coursename,url,description,role);

			}
		 
		//----SKILL ENHANCEMENT DOWNLOAD BUTTON ONCLICK EVENT----
		 	@CrossOrigin( origins = "*" )
			@RequestMapping(value = "/getDownloadPdfonlineCourse_API", method = RequestMethod.GET)
			public @ResponseBody String getDownloadPdfonlineCourse_API(String doc_id1, 
					HttpServletRequest request, HttpSession session, HttpServletResponse response)
					throws IOException {

		 		String msg1 = "";
				String EXTERNAL_FILE_PATH = "";
				System.err.println("doc_id1232423---"+doc_id1);

				EXTERNAL_FILE_PATH = free_CourseDao.getFilePathQueryForDocFile(Integer.parseInt(doc_id1));
				//EXTERNAL_FILE_PATH = "/srv/samadhan/163/__2022_05_19_23_56_14_053.wav";
				System.err.println("EXTERNAL_FILE_PATH---"+EXTERNAL_FILE_PATH);
				if (EXTERNAL_FILE_PATH != "") {
					File file = null;
					file = new File(EXTERNAL_FILE_PATH);
					try {
						if (!file.exists()) {
							System.err.println("IF---");
							msg1 = "Sorry.The file you are looking for does not exist!";
							//model.put("msg", "Sorry.The file you are looking for does not exist!");
							//return new ModelAndView(url);
						}else {
							System.err.println("else---");
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
								msg1 = "Downloaded Successfully";
								//model.put("msg", "Downloaded Successfully");
								//return new ModelAndView(url);
							} catch (FileNotFoundException e) {
								//e.printStackTrace();
							}
						}
					} catch (Exception exception) {
						System.out.println("exception---"+exception);
					}

					
				} 
				System.err.println("msg1---"+msg1);
				return msg1;
			}
		 	
		 	//-----upload certificate couser name----
		 	@CrossOrigin( origins = "*" )
		 	@RequestMapping(value = "/uplode_certificate_course_name_API", method = RequestMethod.POST)
			public @ResponseBody EDU_LMS_FREE_COURSE uplode_certificate_course_name_API(@RequestBody Map<String, String> data) {
			  
		 		String updateid = data.get("updateid");
			    EDU_LMS_FREE_COURSE Onlinecourese = free_CourseDao.getolinecourseByid(Integer.parseInt(updateid));
			    
				return Onlinecourese;
			}
		 	
		 	//---upload certificate submit----
		 	@CrossOrigin( origins = "*" )
		 	@RequestMapping(value = "/uplode_certificate_submit_API", method = RequestMethod.POST)
			public @ResponseBody String uplode_certificate_submit_API(MultipartFile mul,String start_date, String end_date,String coursename,String username,HttpServletRequest request,HttpSession session)throws IOException, ParseException {

		 		EDU_LMS_UPLODE_CERTIFICATE td = new EDU_LMS_UPLODE_CERTIFICATE();
			System.err.println("mul---"+mul);
				String uplode_certificate = gd(request, mul, session, "uplode_certificate");
			
				String msg = "";

				int id = td.getId() > 0 ? td.getId() : 0;
				Date date = new Date();
				//String username = principal.getName();

				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();

				try {
					
					
					if (id == 0) {
						td.setUsername(username);
						td.setCreated_date(date);
						td.setUplode_certificate(uplode_certificate);
						td.setStart_date(start_date);
						td.setEnd_date(end_date);
						td.setCoursename(coursename);
					
							sessionHQL.save(td);
							sessionHQL.flush();
							sessionHQL.clear();
							msg =  "Data Saved Successfully.";
						} else {
							msg = "Data already Exist.";
						}
					

					tx.commit();

				} catch (RuntimeException e) {
					try {

						msg = "roll back transaction";
					} catch (RuntimeException rbe) {
						msg = "Couldn�t roll back transaction " + rbe;
					}
					throw e;
				} finally {
					if (sessionHQL != null) {
						sessionHQL.close();
					}
				}

				return msg;
			}
		 	
		 	//==========file upload method===============
			   
			   public String gd(HttpServletRequest request, MultipartFile mul, HttpSession session, String id)
						throws IOException {

					String extension = ""; // add line
					String fname = ""; // add line

					request.getSession().setAttribute(id, "/srv" + File.separator + "Document");
						System.err.println("id13123==="+id);
					//MultipartFile file = mul.getFile(id);
					//String doc_path1Ext = FilenameUtils.getExtension(mul.getOriginalFilename()).toUpperCase();
					if (!mul.getOriginalFilename().isEmpty()) {

						byte[] bytes = mul.getBytes();
						String mnhFilePath = session.getAttribute(id).toString();

						File dir = new File(mnhFilePath);
						if (!dir.exists())
							dir.mkdirs();
						String filename = mul.getOriginalFilename();

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
			   
			 //---------------skill enhancement api end-----------------//
			   
			   //---------Exam Paper api start--------------//
				
				//-----Course Name List----
//				@CrossOrigin( origins = "*" )
//				@RequestMapping(value = "/getcourse_name_API", method = RequestMethod.GET)
//				public @ResponseBody List<EDU_LMS_ELECTIVE_COURSE_MASTER> getcourse_name_API() throws Exception{
//				
//					//System.out.println("hello---"+common.getCourseNamelist(sessionFactory));
//				     return common.getcoursenameListALL(sessionFactory);
//
//				}
//				
//				//-----Module Name List----
//				@CrossOrigin( origins = "*" )
//				@GetMapping(value = "/getmodule_name_API")
//				public @ResponseBody List<EDU_LMS_MODULE_MSTR> getmodule_name_API() throws Exception{
//				
//				     return common.getModulnameList(sessionFactory);
//				}
				
			   //---set list---
			   @CrossOrigin( origins = "*" )
				@GetMapping(value = "/getSetList_API", produces = "application/json")
				public @ResponseBody List<TB_SET_MASTER> getSetList_API() throws Exception{
				
				     return common.getSetList(sessionFactory);

				}
				
				//----exam list----
				 @CrossOrigin( origins = "*" )
					@GetMapping(value = "/getExamList_API", produces = "application/json")
					public @ResponseBody List<EDU_LMS_PAPER_GENERATION> getExamList_API() throws Exception{
					
					     return common.getExamList(sessionFactory);

				}
					
					//----correct ans list----
					 @CrossOrigin( origins = "*" )
						@PostMapping(value = "/getPaperSolution_API", produces = "application/json")
						public @ResponseBody ArrayList<ArrayList<String>> getPaperSolution_API(@RequestBody Map<String, String> data) throws Exception{
						
							String course_id = data.get("course_id");
							String module_id = data.get("module_id");
							String userid = data.get("userid");
						     return edao.papersolution(course_id,module_id,userid);

					}
			   
				//-----------------Exam Paper api end------------//
					
					//-------------VIEW COURSE CONTENTS API START----------//

					
					//-----Type of Content List----
					@CrossOrigin( origins = "*" )
					@GetMapping(value = "/getTypeOfContent_API", produces = "application/json")
					public @ResponseBody List<Map> getTypeOfContent_API() throws Exception{
					
						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
						String hqlUpdate = "from EDU_LMS_TYPE_OF_CONTENT_MSTR where status='1' order by id "; 
						Query query = sessionHQL.createQuery(hqlUpdate);
						List<Map> list = (List<Map>) query.list();
						tx.commit();
						sessionHQL.close();
						return list;
						
					    // return common.getTypeOfcontent(sessionFactory);

					}
					
					//-----Level of level List----
					@CrossOrigin( origins = "*" )
					@GetMapping(value = "/getlevel_list_API", produces = "application/json")
					public @ResponseBody List<EDU_LMS_LEVEL_MSTR> getlevel_list_API() throws Exception{
					
					     return common.getlevel_Ofcontentlist(sessionFactory);

					}
					
					//-----module List----
					@CrossOrigin( origins = "*" )
					@GetMapping(value = "/getmodule_list_API", produces = "application/json")
					public @ResponseBody List<EDU_LMS_MODULE_MSTR> getmodule_list_API() throws Exception{
					
					     return common.getModulnameList(sessionFactory);

					}
					
//					//-----course List----
//					@CrossOrigin( origins = "*" )
//					@GetMapping(value = "/getcourse_list_API", produces = "application/json")
//					public @ResponseBody List<Map> getCourseNamelist() throws Exception{
//						Session sessionHQL = sessionFactory.openSession();
//						Transaction tx = sessionHQL.beginTransaction();
//						Query q = sessionHQL.createQuery("select cm.course_name, lcm.course_name from EDU_LMS_ELECTIVE_COURSE_MASTER cm ,EDU_LMS_COURSE_MASTER lcm  where cast(cm.course_name as integer)=lcm.id and cm.status='1' order  by lcm.course_name");
//
//						List<Map> list = (List<Map>) q.list();
//						tx.commit();
//						sessionHQL.close();
//						return list;
//					
//					
//					}
					
//					//-----course List for view course content----
//					@CrossOrigin( origins = "*" )
//					@GetMapping(value = "/getcourse_list_API", produces = "application/json")
//					public @ResponseBody List<Map> getCourseNamelist() throws Exception{
//						Session sessionHQL = sessionFactory.openSession();
//						Transaction tx = sessionHQL.beginTransaction();
//						Query q = sessionHQL.createQuery("select  ecm.course_name, cm.course_name from EDU_LMS_SYSTEM_ELE_COURSE_SETS_LINK_CHILD evcc\n"
//								+ "Inner join EDU_LMS_ELECTIVE_COURSE_MASTER ecm ON ecm.id = evcc.course_id\n"
//								+ "Inner join EDU_LMS_COURSE_MASTER cm ON ecm.course_name = cm.id::varchar\n"
//								+ "where user_id='374'");
//
//						List<Map> list = (List<Map>) q.list();
//						tx.commit();
//						sessionHQL.close();
//						return list;
//					
//					
//					}
//					
					//-----course List for view course content----
					@CrossOrigin( origins = "*" )
					 @RequestMapping(value = "/getcourse_list_API", method = RequestMethod.POST)
						public @ResponseBody   List<ArrayList<String>> getcourse_list_API(@RequestBody Map<String, String> data) {

						 String userid = data.get("userId");
						//String userid = "374";
						 
						 List<ArrayList<String>> list = vcdao.getcourselistForViewCourse(userid);
							
							return list;
					 }
				 
					
					//-----Get Course Data By Type of Content----
					@CrossOrigin( origins = "*" )
					 @RequestMapping(value = "/getcourseDatabytypeofcontent_API", method = RequestMethod.POST)
						public @ResponseBody   List<ArrayList<String>> getcourseDatabytypeofcontent_API(@RequestBody Map<String, String> data) {

						 String userid = data.get("userId");
						 String type_content = data.get("type_content");
						 
						 List<ArrayList<String>> list = vcdao.getcourselistFromtypeofcontent(type_content,userid);
							
							return list;
					 }
					 
					
					//-----Get Course Data Curriculum----
//					@CrossOrigin( origins = "*" )
//					 @RequestMapping(value = "/getcourseData_API", method = RequestMethod.POST)
//						public @ResponseBody   List<ArrayList<String>> getcourseData_API(@RequestBody Map<String, String> data) {
//
//						 String course_id = data.get("course_id");
//						 
//						 List<ArrayList<String>> list = PARDAO.getCoursedata(course_id);
//							
//							return list;
//					 }
					 
					//-----Get Module Data By Course----
					@CrossOrigin( origins = "*" )
					 @RequestMapping(value = "/getmoduleDatabycourse_API", method = RequestMethod.POST)
						public @ResponseBody   ArrayList<ArrayList<String>> getmoduleDatabycourse_API(@RequestBody Map<String, String> data) {
						 
						 String userid = data.get("userId");
						 String course_name = data.get("course_name");
						 
						 ArrayList<ArrayList<String>> list = vcdao.getmodulelistFromtcourse(course_name,userid);
							
							return list;
					 }
					
					///-------------list of data-------------
				@CrossOrigin( origins = "*" )
				@PostMapping("/getFilterviewcourse__dataAPI")
				public @ResponseBody List<Map<String, Object>> getFilterviewcourse__dataAPI(@RequestBody Map<String, String> data,HttpSession session) {
					
					String userid = data.get("userid"); 
					String startPage = data.get("startPage"); 
					String pageLength = data.get("pageLength");					
					String Search = data.get("Search"); 
					String orderColunm = data.get("orderColunm"); 
					String orderType = data.get("orderType"); 
					String course_name = data.get("course_name");
					String module_name = data.get("module_name");
					String type_content = data.get("type_content");
					String level_of_module = data.get("level_of_module");
					
					System.out.println("username-363--------------------------->"+orderColunm+module_name+orderType+level_of_module);
					
					
					return vcdao.DataTableviewcourse_DataList(Integer.parseInt(startPage), Integer.parseInt(pageLength), Search, orderColunm, orderType,course_name,module_name,type_content, level_of_module,userid, session);

				}
				
				
				//---- api for video start----
				@CrossOrigin( origins = "*" )
				@RequestMapping(value = "/getdataForSeqValAPI", method = RequestMethod.POST)
				public @ResponseBody List<String> getdataForSeqValAPI(@RequestBody Map<String, String> data,HttpSession session) {
					String userid = data.get("userid"); 
					String courseid = data.get("courseid"); 
					String lm = data.get("lm"); 
					String module_id = data.get("module_id"); 
					
					System.out.println("courseid "+courseid);
//					System.out.println("lc "+lc);
					System.out.println("lm "+lm);
					List<String> ls = new ArrayList<String>();

					ls = vcdao.getdataForSeqVal2(courseid,lm,userid,module_id).get(0);	 
					return ls;
					
				
				}
				
				@CrossOrigin( origins = "*" )
				@RequestMapping(value = "getVideoviewstatusAPI", method = RequestMethod.POST)
				public @ResponseBody String getVideoviewstatusAPI(@RequestBody Map<String, String> data,HttpSession session) {
					//courseid--100lm--1ms---1module--465userid--1505username--939393939393
					
					String userid = data.get("userid"); 
					String username = data.get("username"); 
					String courseid = data.get("courseid"); 
					String lm = data.get("lm"); 
					String ms = data.get("ms"); 
					String module = data.get("module"); 
					System.err.println("c_id"+courseid+"-lm-"+lm);
				
					String msg = "";
					
					EDU_LMS_STUDENT_COURSE_ENROLL_SEQUENCE obj = new EDU_LMS_STUDENT_COURSE_ENROLL_SEQUENCE();
					
				List<String> liststr = new ArrayList<String>();
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				 		
				
				String hqlUpdate2 = "update from EDU_LMS_STUDENT_COURSE_ENROLL_SEQUENCE set status=1,modified_by=:modified_by,modified_date=:modified_date "
						+ " where course_id=:course_id   and user_id=:user_id  and sequence=:ms and module_id=:module";
	
	 		int app2 = sessionHQL.createQuery(hqlUpdate2).setInteger("course_id", Integer.parseInt(courseid))
	 				.setInteger("user_id",Integer.parseInt(userid)).setInteger("ms",Integer.parseInt(ms))
	 				.setInteger("module",Integer.parseInt(module))
	 				.setString("modified_by", username)
				.setDate("modified_date", new Date()).executeUpdate();
	 		
//				String hqlUpdate2 = "update from EDU_LMS_STUDENT_COURSE_ENROLL_SEQUENCE set status=1,modified_by=:modified_by,modified_date=:modified_date "
//									+ " where course_id=:course_id  and level_of_module=:lm and user_id=:user_id  ";
//				
//				 		int app2 = sessionHQL.createQuery(hqlUpdate2).setInteger("course_id", Integer.parseInt(courseid))
//				 				.setString("lm", lm).setInteger("user_id",Integer.parseInt(userid))
//				 				.setString("modified_by", username)
//							.setDate("modified_date", new Date()).executeUpdate();
				 
				 System.err.println("app2--"+app2);
				 	tx.commit();
					sessionHQL.close();
					if (app2 > 0 ) {
						liststr.add("Video Complete Successfully.");
					} else {
						liststr.add("Video Not Complete");
					}
					msg = liststr.get(0);
					
				 
					 return msg;


				}
				
				
				@CrossOrigin( origins = "*" )
				@RequestMapping(value = "getTimeviewstatusAPI", method = RequestMethod.POST)
				public @ResponseBody void  getTimeviewstatusAPI(@RequestBody Map<String, String> data,HttpSession session) {
					
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();
				
				String userid = data.get("userid"); 
				String username = data.get("username"); 
				String courseid = data.get("courseid"); 
				String v1 = data.get("v1"); 
				String module = data.get("module"); 
				

				System.err.println("c_id"+courseid+"-lm-"+v1);
			

				 		
				String hqlUpdate2 = "update from EDU_LMS_STUDENT_COURSE_ENROLL_SEQUENCE set time=:time,modified_by=:modified_by,modified_date=:modified_date "
									+ " where course_id=:course_id  and user_id=:user_id and module_id=:module_id ";
				
				 		int app2 = sessionHQL.createQuery(hqlUpdate2).setString("time", v1).setInteger("course_id", Integer.parseInt(courseid))
				 				.setInteger("user_id",Integer.parseInt(userid))
				 				.setInteger("module_id",Integer.parseInt(module))
				 				.setString("modified_by", username)
							.setDate("modified_date", new Date()).executeUpdate();
				 		
				 		
				 
				 
				 	tx.commit();
					sessionHQL.close();
				
				}
				//---- api for video end----

				//--- query submit ----
				@CrossOrigin( origins = "*" )
				@RequestMapping(value = "getAskQueryMethodAPI", method = RequestMethod.POST)
				public @ResponseBody String getAskQueryMethodAPI(@RequestBody Map<String, String> data,HttpSession session) {

					 	Date date = new Date();
					 	
					 	String userId = data.get("userid"); 
						String username = data.get("username"); 
						//String courseid = data.get("courseid"); 
						//String moduleid = data.get("moduleid"); 
						String message = data.get("message"); 
						
						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
						String msg = "";

						try {
							
//							System.err.println("courseid-"+courseid+"-moduleid-"+moduleid+"-message-"+message);
							
							TB_NOTIFICATION nt = new TB_NOTIFICATION();

							nt.setCreated_by(username);
							nt.setCreated_date(date);
							nt.setMessage(message);
							nt.setFrom_name_send(userId);
							nt.setTo_name_sent("1");
							nt.setStatus("1");
							
							sessionHQL.save(nt);
							sessionHQL.flush();
							sessionHQL.clear();
							tx.commit();
							
							msg="Query Submmitted Successfully";

						} catch (Exception e) {
							e.printStackTrace();
							msg="Something went wrong !!!";
							tx.rollback();
						}
						return msg;
				}
				
				//------------pdf download----------------
				@CrossOrigin( origins = "*" )
				@SuppressWarnings("null")
				@RequestMapping(value = "/kmlFileDownload444_API", method = RequestMethod.GET)
				public @ResponseBody void kmlFileDownload444_API(String id, 
						String fildname, HttpServletRequest request,
						HttpServletResponse response, HttpSession session) throws IOException {
			System.out.println("fildname---"+fildname+"id---"+id);
					final int BUFFER_SIZE = 4096;
			System.err.println("INSIDE");
			
					String filePath = Cdao.getFilePathQueryForDocFile(id,fildname);
					//String filePath = "/srv/sample.pdf";
					//model.put("filePath", filePath);
			
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
				
				@CrossOrigin( origins = "*" )
				@GetMapping(value = "/video_API")
				public @ResponseBody void getVideo_API(String p_id1,
						String p_id2,
						HttpServletRequest request,HttpServletResponse response) throws IOException {

					String filePath = cmdao.getTopicVideoPath2(Integer.parseInt(p_id1),
							Integer.parseInt(p_id2));
					final int BUFFER_SIZE = 4096;
					//String filePath = sdc.getTopicChoose_Ele_Course_Stu(id);
					//String filePath = "/srv/VideoPath/20181014.mp4";
					System.out.println("filePath----------------"+filePath);
					//model.put("filePath", filePath);
					ServletContext context = request.getSession().getServletContext();
					
					try {
						if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {
System.err.println("if--");
							String fullPath = request.getRealPath("/") + "admin\\js\\video\\05_Slope.mp4";
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
							OutputStream outStream = response.getOutputStream();
							byte[] buffer = new byte[BUFFER_SIZE];
							int bytesRead = -1;
							while ((bytesRead = inputStream.read(buffer)) != -1) {
								outStream.write(buffer, 0, bytesRead);
							}
							inputStream.close();
							outStream.close();
						} else {
							System.err.println("else--");
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

						String fullPath = filePath;
								//request.getRealPath("/") + filePath;
								//"admin\\js\\video\\05_Slope.mp4";
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
						OutputStream outStream = response.getOutputStream();
						byte[] buffer = new byte[BUFFER_SIZE];
						int bytesRead = -1;
						while ((bytesRead = inputStream.read(buffer)) != -1) {
							outStream.write(buffer, 0, bytesRead);
						}
						inputStream.close();
						outStream.close();
					}
				//	return service.getVideo(filePath);
				}
				 
				//-------------VIEW COURSE CONTENTS API END----------//
				
				//--------------RESULT API START---------------------//
				
				//----course api new-----------
//			
				@CrossOrigin( origins = "*" )
				 @RequestMapping(value = "/getCourse_dataResultAPI", method = RequestMethod.POST)
					public @ResponseBody   ArrayList<ArrayList<String>> getCourse_dataResultAPI(@RequestBody Map<String, String> data) {
						
					String userid = data.get("userid"); 
					String module_id = data.get("module_id"); 

					ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
					if (module_id==null || module_id.equals("")) {
						list = edao.coursenamelistofResult(userid);
					}
					else {
						list = edao.coursenamelist(userid,module_id);
					}
					
					ArrayList<ArrayList<String>> list2 = new ArrayList<ArrayList<String>>();
					System.err.println("list------"+list);
					for(int i=0;i<list.size();i++) {
						 list2.add(edao.method2(userid,list.get(i).get(0),list.get(i).get(1)));
//						System.err.println("list-2-"+list2);
					}
						
						return list2;
					}
				
				@CrossOrigin( origins = "*" )
				 @RequestMapping(value = "/getCourse_dataExamAPI", method = RequestMethod.POST)
					public @ResponseBody   ArrayList<ArrayList<String>> getCourse_dataExamAPI(@RequestBody Map<String, String> data) {
						
					String userid = data.get("userid"); 
					String module_id = data.get("module_id"); 

					ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
					if (module_id==null || module_id.equals("")) {
						list = edao.coursenamelistofResult(userid);
					}
					else {
						list = edao.coursenamelist(userid,module_id);
					}
					
					
					ArrayList<ArrayList<String>> list2 = new ArrayList<ArrayList<String>>();
					ArrayList<String> list3 = new ArrayList<String>();
					
					for(int i=0;i<list.size();i++) {
						list3 = edao.method2(userid,list.get(i).get(0),list.get(i).get(1));
						if(!list3.isEmpty()) {
							list2.add(list3);
						}
					}
						
						return list2;
					}
				
				
				
				//---get marks api start---
				@CrossOrigin( origins = "*" )
				 @RequestMapping(value = "/getModuleListByCourse_dataAPI", method = RequestMethod.POST)
					public @ResponseBody   ArrayList<ArrayList<String>> getModuleListByCourse_dataAPI(@RequestBody Map<String, String> data,HttpSession session) {
						
					String userid = data.get("userid"); 
					String course_id = data.get("courseid"); 
						
					   ArrayList<ArrayList<String>> list = edao.getModulelistFromcourse(course_id,userid);
						
						return list;
					}
				
				//-----------marks---------------
				@CrossOrigin( origins = "*" )
				@RequestMapping(value = "Exam_result_actionAPI", method = RequestMethod.POST)
				public @ResponseBody int Exam_result_actionAPI(@RequestBody Map<String, String> data,HttpSession session, HttpServletRequest request) {
					String userid = data.get("userid"); 
					String course_id = data.get("course_id"); 
					int marks = 0;
					ArrayList<ArrayList<String>> list = edao.getattemptedQuizdata(Integer.parseInt(userid),course_id);
//					System.err.println("list.size()---------->  "+list.size());
										System.err.println("list22------------>   "+list);
					
					for(int i=0;i<list.size();i++) {
						System.err.println("for--");
						System.err.println("list.get(i).get(0))--------------->   "+list.get(i).get(0));
						System.err.println("list.get(i).get(1))--------------->   "+list.get(i).get(1));
						
						ArrayList<ArrayList<String>> list2 = edao.getcorrectanscheck(Integer.parseInt(list.get(i).get(0)));
						System.err.println("list2.size()---------->  "+list2.size()  +"   "+ i);
						
						System.err.println("list2---------->  "+list2);
						//System.err.println("list2.get(i).get(1)---------->  "+list2.get(0).get(1));
						
						for(int j=0;j<list2.size();j++) {
							
							System.err.println("list2.get(j).get(0)---------->  "+list2.get(j).get(0));
							System.err.println("list2.get(j).get(1)---------->  "+list2.get(j).get(1));
							
							if (list.get(i).get(1) != "" && list.get(i).get(1) != null){
							
							if(list.get(i).get(1).equals(list2.get(j).get(0))) {
								marks +=  Integer.parseInt(list2.get(j).get(1));
								System.err.println("mark------>   "+marks);
							}
							}
							
						}
						
					}
					
					System.err.println("mark------>   "+marks);
					
					return marks;
				}
				
				//---get marks api end----
				
				//--------------download pdf-----------------
				@CrossOrigin( origins = "*" )
				@RequestMapping(value = "/getExamBoardAdmitCardPDF_API", method = RequestMethod.GET)
				public @ResponseBody ModelAndView getExamBoardAdmitCardPDF_API(String userId,String ayush_id, String course_name,String  set_name ,
						String  logo_img_path,String  exam_id,String  course_id,String  module_id, String typeReport, String begin_date1, String reportname1,String username, ModelMap Mmap, HttpSession session,HttpServletRequest request) {
						try {
							
//						System.err.println("course_name----------------"+course_name);
//						System.err.println("set_name----------------"+set_name);
//						System.err.println("course_id----------------"+course_id);
//						System.err.println("exam_id----------------"+exam_id);
//						System.err.println("ayush_id----------------"+ayush_id);
					
//							System.err.println("report_name1----------------"+begin_date1);
						//String userId = data.get("userId").toString();
						ArrayList<ArrayList<String>> list1 = new ArrayList<ArrayList<String>>();
						ArrayList<String> list2 = new ArrayList<String>();
						
						String count =sdao.getpercentage(Integer.parseInt(userId)).get(0).get(0);
						String credit =sdao.getcredit(Integer.parseInt(userId), Integer.parseInt(course_id)).get(0).get(0);
						
//						 ArrayList<ArrayList<String>> list = sdao.getuserList(Integer.parseInt(userId),Integer.parseInt(module_id), exam_id);
						
						
							System.out.println("logo  ========= "+logo_img_path);
							if(reportname1.equals("1")) {
								
								if ( typeReport.equals("pdfL")) {
									//if (list.size() > 0) {

//												list2.add("1");
												list2.add("basic of microbiolgy");
//												list2.add("7.2");
												list2.add("15");
												list2.add("20");
//												list2.add("75");
												list1.add(list2);
										String Heading = "";
										//String username = session.getAttribute("username").toString();
										//String ayush_id1 =  session.getAttribute("ayush_id").toString();
										
										//String course_name1 = session.getAttribute("course_name").toString();
								//		String course_name1 = request.getAttribute("course_name").toString();
									//	System.err.println("course_name1--->  "+course_name);
										//String course_name = request.getAttribute("course_name").toString();
									//	System.err.println("course_name--->  "+course_name);
									
										

											List<String> TH = new ArrayList<String>();
//											TH.add("Sr.No.");
											TH.add("Course");// 0
//											TH.add("Video Time");// 1
											TH.add("Exam Marks");// 2
											TH.add("Total Marks");// 3
//											TH.add("Percentage");// 4
											

					return new ModelAndView(new AdmitCardDownloadPdf_old("L", TH, Heading,ayush_id, username,course_name, logo_img_path,count,credit));

									//}
								}
							}
							
							
							
							} catch (Exception e) {
							e.printStackTrace();
						}

						return new ModelAndView("Exam_result_Tiles");
					}
				
				@CrossOrigin( origins = "*" )
				@RequestMapping(value = "getInstLogoPath_API", method = RequestMethod.POST)
				public @ResponseBody String getInstLogoPath_API(@RequestBody Map<String, String> data,ModelMap model, HttpSession session, HttpServletRequest request) {
					String userid = data.get("userId").toString();
					String imgPath  = edao.getInstLogo(Integer.parseInt(userid)).get(0).get(0);
					return imgPath;
				}

				
				@CrossOrigin( origins = "*" )
				 @RequestMapping(value = "/getaayushidfromuserid_API", method = RequestMethod.POST)
					public @ResponseBody String getaayushidfromuserid_API(@RequestBody Map<String, String> data,HttpSession session) throws ParseException {
					 String userid = data.get("userid").toString();
					
					  String list = edao.getaayush_idby_uid(Integer.parseInt(userid)).get(0);
					  
//					  System.err.println("list------------ ctrl------->    "+list);
					
						return list;
					}
				
				//---get course----
				@CrossOrigin( origins = "*" )
				 @RequestMapping(value = "/getcourseResult_API", method = RequestMethod.POST)
					public @ResponseBody ArrayList<ArrayList<String>> getcourseResult_API(@RequestBody Map<String, String> data,HttpSession session) throws ParseException {
					 String userid = data.get("userId").toString();
					
					  ArrayList<ArrayList<String>> list = SMRdao.getStu_Marks_Replist(userid);
					
						return list;
					}
				
				//---get set name from course---
				@CrossOrigin( origins = "*" )
				 @RequestMapping(value = "/getsetResult_API", method = RequestMethod.POST)
					public @ResponseBody ArrayList<ArrayList<String>> getsetResult_API(@RequestBody Map<String, String> data,HttpSession session) throws ParseException {
					 String userid = data.get("userId").toString();
					 String course_name = data.get("course_name").toString();
					 
						 ArrayList<ArrayList<String>> list = SMRdao.getStu_Marks_Reportlist(userid,course_name);
							return list;
						}
					
				//-----View_Marks-----
				@CrossOrigin( origins = "*" )
				 @RequestMapping(value = "/Students_MarksheetUrl_API", method = RequestMethod.POST)
				public @ResponseBody ArrayList<ArrayList<String>> Students_MarksheetUrl_API(@RequestBody Map<String, String> data,HttpSession session) throws ParseException {
 
					 String userid = data.get("userId").toString();
						String degree_id = sdc.getdegree_list(userid).get(0).get(0);
						 String course_name = data.get("course_name").toString();
						 String setname = data.get("setname").toString();

					 ArrayList<ArrayList<String>> list = SMRdao.getPopup_Datalist(userid,course_name,setname,degree_id);
						return list;
					}
				
				//-----View Exam Result---
				@CrossOrigin( origins = "*" )
				 @RequestMapping(value = "/getFilterSearch_Stu_Marks_data_API", method = RequestMethod.POST)
				public @ResponseBody List<Map<String, Object>> getFilterSearch_Stu_Marks_data_API(@RequestBody Map<String, String> data,HttpSession session) throws ParseException {

					 String userid = data.get("userId").toString();
					  String courses = data.get("courses").toString(); 
					  String set = data.get("set").toString(); 
					  String Search = data.get("Search").toString();
					  String startPage = data.get("startPage").toString();
					  String pageLength = data.get("pageLength").toString();  
					  String orderColunm = data.get("orderColunm").toString(); 
					  String orderType = data.get("orderType").toString();
					
					List<Map<String, Object>>  list = SMRdao.DataTableSearch_Stu_MarksDataList(Integer.parseInt(startPage),Integer.parseInt(pageLength) , Search, orderColunm, orderType, courses, set,userid);
						
						return list;

				}
				
				//------------RESULT API END-----------------------//
				
				//----------EXAM API START--------------
				@CrossOrigin( origins = "*" )
				 @RequestMapping(value = "/course_exam_API", method = RequestMethod.POST)
					public @ResponseBody List<Map> course_exam_API(@RequestBody Map<String, String> data,HttpSession session) throws ParseException {
					 String userid = data.get("userid").toString();//377
					 String course_id = data.get("course_id").toString();//97
					 String module_id = data.get("module_id").toString();//187
						 
						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
						String hqlUpdate = "select count(*) from EDU_LMS_EXAM_PAPER p where user_id=:userid and course_id=:course_id and module_id=:module_id";
						Query query = sessionHQL.createQuery(hqlUpdate).setParameter("userid", Integer.parseInt(userid))
								.setParameter("course_id", Integer.parseInt(course_id))
								.setParameter("module_id", Integer.parseInt(module_id));
						List<Map> list = (List<Map>) query.list();
						tx.commit();
						sessionHQL.close();
						return list;
					}
				
				//--------question list-----------
				
				/*
				 * @CrossOrigin( origins = "*" )
				 * 
				 * @RequestMapping(value = "/getquestion_API", method = RequestMethod.GET)
				 * public @ResponseBody ArrayList<ArrayList<String>> getquestion_API() throws
				 * Exception{
				 * 
				 * //System.out.println("hello---"+common.getCourseNamelist(sessionFactory));
				 * return edao.getquestion();
				 * 
				 * }
				 */
				@CrossOrigin( origins = "*" )
				@RequestMapping(value = "/getquestion_API", method = RequestMethod.POST)
				public @ResponseBody ArrayList<ArrayList<String>> getquestion_API(@RequestBody Map<String, String> data) throws Exception{
			
					String course_id = data.get("course_id");
					String module_id = data.get("module_id");
					System.out.println("hello---");
					 ArrayList<ArrayList<String>> list = edao.getquestion(course_id,module_id);
					 
						return list;
				     //return edao.getquestion();

				}
				//-------exam paper submit------------
				@PostMapping(value = "/Exam_Paper_action_API")
				public@ResponseBody JSONObject Exam_Paper_action_API(@RequestBody Map<String, String> data,
						HttpServletRequest request,HttpSession session, Principal principal, RedirectAttributes ra
						) throws IOException, ParseException {

					 JSONObject ob = new JSONObject();
					 EDU_LMS_EXAM_PAPER elmp = new EDU_LMS_EXAM_PAPER();
					System.err.println("IN ACTION EXAM");
					
						DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
						//String role = session.getAttribute("role").toString();
						ArrayList<ArrayList<String>> list = edao.getquestion();
//						System.err.println("---------list "+list.size());

					try {

						List<String> ans = new ArrayList<String>();
						ans = Arrays.asList(data.get("check_ans").split(","));
						System.err.println("ans---"+ans.get(1).substring(1));
						for(int i =0;i< list.size(); i++) {
							
							String quiz_id=list.get(i).get(3);
							//6
							String course_id = data.get("course_id");
							//7
						//	String set_id = data.get("set_id");

							//---- pass id in position 3 in question api----
							String check_ans = "";
							if(i==0) {
								check_ans = ans.get(i);
							}else {
								check_ans = ans.get(i).substring(1);
							}
						
							String userid = data.get("userId").toString();
							
							Session sessionHQL = this.sessionFactory.openSession();
							Transaction tx = sessionHQL.beginTransaction();
							Date date = new Date();
							String username = data.get("username");
							elmp.setExam_name(data.get("exam_name"));
							elmp.setQuiz_id(Integer.parseInt(quiz_id));
							elmp.setAnswer_id(check_ans);
							elmp.setCourse_id(Integer.parseInt(course_id));
//							elmp.setSet_id(Integer.parseInt(set_id));
							elmp.setModule_id(Integer.parseInt(data.get("module_id")));
							elmp.setUser_id(Integer.parseInt(userid));
							elmp.setCreated_by(username);
							elmp.setCreated_date(date);
							
							    sessionHQL.save(elmp);
								
								sessionHQL.flush();
								sessionHQL.clear();
								ob.put("msg", "Data Saved Successfully.");
								tx.commit();
								sessionHQL.close();
						}
				
					} catch (RuntimeException e) {
						e.printStackTrace();
						try {
							ob.put("msg", "roll back transaction");
						} catch (RuntimeException rbe) {
							ob.put("msg", "Couldn't roll back transaction " + rbe);
						}
						throw e;
					} finally {
						
					}
					return ob;
				}
				
				  @CrossOrigin( origins = "*" )
				  @RequestMapping(value = "/Exam_Paper_action_APInew", method = RequestMethod.POST)
				  public@ResponseBody JSONObject Exam_Paper_action_APInew(@RequestBody Map<String, String> data,
							HttpServletRequest request,HttpSession session, Principal principal, RedirectAttributes ra
							) throws IOException, ParseException {
					  
						 JSONObject ob = new JSONObject();
						 EDU_LMS_EXAM_PAPER elmp = new EDU_LMS_EXAM_PAPER();
 
						DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
						String role = data.get("role");//session.getAttribute("role").toString();
						String course_id=data.get("course_id");//request.getParameter("course_id");
						String module_id=data.get("module_id");//request.getParameter("module_id");
						
						ArrayList<ArrayList<String>> list = edao.getquestion(course_id,module_id);
									
					try {
						List<String> ans = new ArrayList<String>();
						ans = Arrays.asList(data.get("check_ans").split(","));
						System.err.println("check_ans---"+ans.get(1).substring(1));
						
						for(int i =0;i< list.size(); i++) {

							//---- pass id in position 3 in question api----
							String check_ans = "";
							if(i==0) {
								check_ans = ans.get(i);
							}else {
								check_ans = ans.get(i).substring(1);
							}
							String quiz_id=list.get(i).get(3);				
							String userid = data.get("userId");
							Session sessionHQL = this.sessionFactory.openSession();
							Transaction tx = sessionHQL.beginTransaction();
							Date date = new Date();
							//String username = principal.getName();
							String username = data.get("username");
							elmp.setExam_name(data.get("exam_name") );
							elmp.setQuiz_id(Integer.parseInt(quiz_id));
							elmp.setAnswer_id(check_ans);
							elmp.setCourse_id(Integer.parseInt(course_id));
							elmp.setModule_id(Integer.parseInt(data.get("module_id") ));
							elmp.setUser_id(Integer.parseInt(userid));
							elmp.setCreated_by(username);
							elmp.setCreated_date(date);
							  sessionHQL.save(elmp);
							sessionHQL.flush();
								sessionHQL.clear();
								ob.put("msg", "Data Saved Successfully.");
								tx.commit();
								sessionHQL.close();
					  
						}
					}catch (RuntimeException e) {
						e.printStackTrace();
						try {
							ob.put("msg", "roll back transaction");
						} catch (RuntimeException rbe) {
							ob.put("msg", "Couldn't roll back transaction " + rbe);
						}
						throw e;
					} finally {
						
					}
					return ob;
				}
				
				//--------EXAM API END--------------
				
				//-------ASK QUERY API START--------
				
				
				@CrossOrigin( origins = "*" )
				 @RequestMapping(value = "/getgetteacher_list_fromuserid_API", method = RequestMethod.POST)
					public @ResponseBody ArrayList<ArrayList<String>> getgetteacher_list_fromuserid_API(@RequestBody Map<String, String> data,HttpSession session) throws ParseException {
					 String userid = data.get("userId").toString();
					
					 ArrayList<ArrayList<String>> list = cdao.getteacher_list(userid);
					
						return list;
					}
				

				@PostMapping(value = "/StudentQuery_action_API")
				public@ResponseBody JSONObject StudentQuery_action_API(@RequestBody Map<String, String> data,
						HttpServletRequest request, ModelMap model, HttpSession session) {
					
					TB_NOTIFICATION td = new TB_NOTIFICATION();
					 JSONObject ob = new JSONObject();
					
					String query = data.get("query");
				
					int id = td.getId() > 0 ? td.getId() : 0;
					Date date = new Date();
					String username = data.get("username");
					String userId = data.get("userId");
					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					
					
					try {
						
						/*
						 * List<String> course_id3 = new ArrayList<String>(); set_id =
						 * Arrays.asList(data.get("set_id").split(","));
						 */
			
						String in_teacher_id_hid_ch[] = data.get("in_teacher_id_hid_ch").split(",");
						
						for (int k = 0; k < in_teacher_id_hid_ch.length; k++) {
							
							TB_NOTIFICATION add = new TB_NOTIFICATION();
							add.setMessage(query);
							add.setCreated_by(username);
							add.setCreated_date(date);
							add.setFrom_name_send(userId);
							add.setTo_name_sent(in_teacher_id_hid_ch[k]);
							add.setStatus("1");
						
							sessionHQL.save(add);
							sessionHQL.flush();
							sessionHQL.clear();
							ob.put("msg", "Data Saved Successfully.");
						}
						tx.commit();
					} catch (RuntimeException e) {
						try {
							ob.put("msg", "roll back transaction");
						} catch (RuntimeException rbe) {
							ob.put("msg", "Couldn�t roll back transaction " + rbe);
						}
						throw e;
					} finally {
						if (sessionHQL != null) {
							sessionHQL.close();
						}
					}
					return ob;
				}
				
				//-------ASK QUERY API END--------------
				
				//----------------TIME TABLE API START-------------
				
				//-----Professional List-------
				@CrossOrigin( origins = "*" )
				@RequestMapping(value = "/getProfessional_API", method = RequestMethod.GET)
				public @ResponseBody List<CC_TB_PROFESSIONAL_MSTR> getProfessional_API() throws Exception{
				    
					Session session = sessionFactory.openSession();
					 Transaction tx = session.beginTransaction();
					 Query q0 = session.createQuery("from CC_TB_PROFESSIONAL_MSTR where status='1' order by id");
					 
					 List<CC_TB_PROFESSIONAL_MSTR> DegreeList = q0.list();
				      session.getTransaction().commit();
				      session.close();                
				     return DegreeList;

				}
				
				//--------Weekly Time Table--------
				@CrossOrigin( origins = "*" )
				@RequestMapping(value = "/getWeeklyTimetable_API", method = RequestMethod.POST)
				public @ResponseBody ArrayList<ArrayList<String>> getWeeklyTimetable_API(@RequestBody Map<String, String> data) {
					
					String sdate = data.get("sdate");
					String professional = data.get("professional");
					String userid = data.get("userid");
					
					Session sessiont = sessionFactory.openSession();
					Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
					 List<UserLogin> institute_idList = q1.list();
					 sessiont.close();
					 
					 int institute_id = institute_idList.get(0).getInstitute_id();

					System.err.println("sdate----"+sdate);
				       return ttdao.getWeeklyTimetable(sdate, professional, institute_id);
				}
				
				//--------Weekly Exam Table--------
				@CrossOrigin( origins = "*" )
				@RequestMapping(value = "/getweeklyExamList_API", method = RequestMethod.POST)
				public @ResponseBody ArrayList<ArrayList<String>> getweeklyExamList_API(@RequestBody Map<String, String> data) {
					
					String sdate = data.get("sdate");
					String professional = data.get("professional");
					String userid = data.get("userid");
					
					Session sessiont = sessionFactory.openSession();
					Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
					 List<UserLogin> institute_idList = q1.list();
					 sessiont.close();
					 
					 int institute_id = institute_idList.get(0).getInstitute_id();

					System.err.println("sdate----"+sdate);
				       return ttdao.getweeklyExamList(sdate,  professional, institute_id);
				}
				
				//--------Weekly Event Table--------
				@CrossOrigin( origins = "*" )
				@RequestMapping(value = "/getweeklyEventList_API", method = RequestMethod.POST)
				public @ResponseBody ArrayList<ArrayList<String>> getweeklyEventList_API(@RequestBody Map<String, String> data) {
					
					String sdate = data.get("sdate");
					String userid = data.get("userid");
					
					Session sessiont = sessionFactory.openSession();
					Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
					 List<UserLogin> institute_idList = q1.list();
					 sessiont.close();
					 
					 int institute_id = institute_idList.get(0).getInstitute_id();

					System.err.println("sdate----"+sdate);
				       return ttdao.getweeklyEventList(sdate, institute_id);
				}
				//----------------TIME TABLE API END----------------
				
				//-----------CURRICULAM API START---------------
				
				//-------System List-------
				@CrossOrigin( origins = "*" )
				@RequestMapping(value = "/GetSystemdegreeid_fetch_API", method = RequestMethod.POST)
				public @ResponseBody ArrayList<ArrayList<String>> GetSystemdegreeid_fetch_API(@RequestBody Map<String, String> data) throws Exception{
				    
					String userid = data.get("userid");
					//role = 'NCISM';
					          
				     return PARDAO.GetSystemdegreeid_fetch(userid);

				}
				
				//-------System List-------
//				@CrossOrigin( origins = "*" )
//				@RequestMapping(value = "/getSystemList_API", method = RequestMethod.POST)
//				public @ResponseBody List<EDU_LMS_SYSTEM_MSTR> getSystemList_API(@RequestBody Map<String, String> data) throws Exception{
//				    
//					String role = data.get("role");
//					//role = 'NCISM';
//					          System.err.println("role222---"+role);
//					          System.err.println("role222333---"+common.getSystemList(sessionFactory,role));
//				     return common.getSystemList(sessionFactory,role);
//
//				}
				
				//---------degree by system--------
				@CrossOrigin( origins = "*" )
				@RequestMapping(value = "/getDegreeListbysystem1_API", method = RequestMethod.POST)
				public @ResponseBody List<EDU_LMS_SYS_DEG_MAP_MASTER> getDegreeListbysystem1_API(@RequestBody Map<String, String> data)  {
					
					String system_name = data.get("system_name");
					//system_name = 44;
					List<EDU_LMS_SYS_DEG_MAP_MASTER> list =  common.getDegreeListbysystem(sessionFactory,system_name);
					return list;
					
				}
				
				//---------get course by degree and system--------
				@CrossOrigin( origins = "*" )
				@RequestMapping(value = "admin/getCourseList_API", method = RequestMethod.POST)
				public @ResponseBody List<EDU_LMS_COURSE_MASTER> getMainCourseList_API(@RequestBody Map<String, String> data
						) {
					
					String system_id  = data.get("system_id");
					String degree_id = data.get("degree_id");
					String professional_id = data.get("professional_id");
					System.err.println("getCourseList353245"+system_id+" "+degree_id+" " +professional_id);
					//system_id+" "+degree_id+" " +professional_id = 44 59 null
					Session sessionHQL = sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					
					String qry = " select distinct cm.id,cm.course_name \n"
							+ " from EDU_CC_LINK_SYSTEM_DEGREE_PROFESSIONAL_COURSE m , EDU_LMS_COURSE_MASTER cm \n"
							+ " where cm.id=m.course_id and m.status=1 ";
					
					String system_qry = " and m.system_id=:system_id ";
					String degree_qry = " and m.degree_id=:degree_id ";
					String prof_qry = " and m.professional_id=:professional_id ";
					
					if(system_id != null) {
						qry += system_qry;
					}
					if(degree_id != null) {
						qry += degree_qry;
					}
					if(professional_id != null) {
						qry += prof_qry;
					}
					
					Query q = sessionHQL.createQuery(qry);
					
					if(system_id != null) {
						q.setParameter("system_id", Integer.parseInt(system_id));
					}
					if(degree_id != null) {
						q.setParameter("degree_id", Integer.parseInt(degree_id));
					}
					if(professional_id != null) {
						q.setParameter("professional_id", Integer.parseInt(professional_id));
					}
					
					@SuppressWarnings("unchecked")
					List<EDU_LMS_COURSE_MASTER> clist = (List<EDU_LMS_COURSE_MASTER>) q.list();
					
					tx.commit();
					sessionHQL.close();
					return clist;
				}
				

				// download pdf
				@CrossOrigin( origins = "*" )
				@RequestMapping(value = "admin/Professional_Ayu_Report_Url_pdf_API", method = RequestMethod.GET)
				public @ResponseBody ModelAndView Professional_Ayu_Report_Url_pdf_API(String msg,String typeReport,String course_id,String username,ModelMap Mmap, HttpSession session,HttpServletRequest request) {
				 //String msg = data.get("msg");
					//String typeReport = data.get("typeReport");
					 //String course_id = data.get("course_id");
					System.err.println("Professional_Ayu_Report_Url_pdf222"+msg+"--- "+typeReport+"----"+course_id);
					
					//null--- pdfL----50
					if (typeReport != null && !typeReport.equals("")) {

						List<Map<String, Object>> list = PARDAO.DataTableEdu_Reg_Report_masterDataList_pdf(course_id);

						List<Map<String, Object>> examination_list = PARDAO.examination_list(course_id);

						// end of summary and examination============================

						// random table=======================================

						List<ArrayList<String>> randomlist = PARDAO.non_lec_activities(course_id);

						int total = randomlist.size();
						List<String> THr = new ArrayList<String>();
						THr.add("SN");
						THr.add("Name of Practical");
						THr.add("Term");

						// end of random table=========================================

						// Table 1- Course learning outcomes and matched
						// PO========================================

						ArrayList<ArrayList<String>> t1copolink_list = PARDAO.table1_co_po_link(course_id);
						int total1 = t1copolink_list.size();
						List<String> TH1 = new ArrayList<String>();
						TH1.add("SR1\n" + "CO No");
						TH1.add("A1\n" + "Course learning Outcome (CO) AyUG RS\n"
								+ "At the end of the course AyUG RS, the student should be able\n" + "to");
						TH1.add("B1\n" + "Course learning\n" + "Outcome matched with\n" + "program learning\n" + "outcomes..");

						// end of Table 1- Course learning outcomes and matched
						// PO==========================================================

						// Table 2: Contents of Course AyUG-RS=======================================

						List<ArrayList<String>> t2Content_Course_AyUGRS_list = PARDAO.t2Content_Course_AyUGRS_list(course_id);

						int total2 = t2Content_Course_AyUGRS_list.size();
						List<String> TH2 = new ArrayList<String>();
						TH2.add("SN");
						TH2.add("A2\n" + "List of Topics AyUG-RS");
						TH2.add("Sub Topics");
						TH2.add("B2\n" + "Term");
						TH2.add("C2\n" + "Marks");
						TH2.add("D2\n" + "Lecture\n" + "hours");
						TH2.add("E2\n" + "NonLecture\n" + "hours");

						// end of Table 2: Contents of Course
						// AyUG-RS===========================================

						// Table 3: Learning objectives (Theory) of Course
						// AyUG-RS============================

						List<ArrayList<String>> t3LearningObject_Course_AyUGRS_list = PARDAO
								.table3_Learning_Objectives_Course_AyUGRS(course_id);
						int total3 = t3LearningObject_Course_AyUGRS_list.size();
						List<String> TH3 = new ArrayList<String>();

						TH3.add("A3\n" + "Course\n" + "outcom\n" + "e\n");

						TH3.add("B3\n" + "Learning Objective\n" + "(At the end of the\n" + "session, the students\n"
								+ "should be able to)\n");

						TH3.add("C3\n" + "Domain/\n" + "sub\n");

						TH3.add("D3\n" + "Must to\n" + "know \n" + "desirable\n" + "to\n" + "know/Ni\n" + "ce to\n" + "know\n");

						TH3.add("E3\n" + "Level\n" + "Does/\n" + "Shows\n" + "how/\n" + "Knows\n" + "how/\n" + "Know\n");
						TH3.add("F3\n" + "T-L method\n");
						TH3.add("G3\n" + "Assessment\n");
						TH3.add("H3\n" + "Formati\n" + "ve\n" + "/summa\n" + "tive\n");
						TH3.add("I3\n" + "Te\n" + "rm\n");
						TH3.add("J3\n" + "Integrat\n" + "ion\n");

						// end of Table 3: Learning objectives (Theory) of Course
						// AyUG-RS===============================

			//======================start Practical- list===========================

						List<ArrayList<String>> tableList_of_practical_list = PARDAO
								.TableList_of_practicalDataTotalCount(course_id);

						int totalLPA = tableList_of_practical_list.size();
						List<String> THLPA = new ArrayList<String>();

						THLPA.add("SN"); // 0
						THLPA.add("Name of Practical");// 1
						THLPA.add("Term"); // 2
						THLPA.add("Activity / Practical Description/ Hours ");// 3

						// ==================== end Practical- list===========================

						// Table 4: Learning objectives (Practical) of AyUG-
						// RS===========================

						List<ArrayList<String>> t4Learning_Objectiveslist = PARDAO
								.table4_Learning_Objectives_Practical_of_AyUGRS(course_id);

						int total4 = t4Learning_Objectiveslist.size();
						List<String> TH4 = new ArrayList<String>();

						TH4.add("A4\n" + "Course\n" + "outcome");

						TH4.add("B4\n" + "Learning\n" + "Objectiv\n" + "e\n" + "(At the\n" + "end of\n" + "the\n" + "session,\n"
								+ "the\n" + "students\n" + "should\n" + "be able\n" + "to)");

						TH4.add("C4\n" + "Domain/\n" + "s\n" + "ub");

						TH4.add("D4\n" + "Must to\n" + "know/\n" + "desirabl\n" + "e to\n" + "know/N\n" + "ice to\n" + "know");

						TH4.add("E4\n" + "Level\n" + "Does/\n" + "Shows\n" + "how/\n" + "Knows\n" + "how/\n" + "Know");
						TH4.add("F4\n" + "T-L method");
						TH4.add("G4\n" + "Assessment");
						TH4.add("H4\n" + "Form\n" + "ative\n" + "/sum\n" + "mativ\n" + "e");
						TH4.add("I4\n" + "Te\n" + "rm");
						TH4.add("J4\n" + "Integration");

						// end of Table 4: Learning objectives (Practical) of AyUG-
						// RS===========================

						// Table 5: Non-Lecture Activities Course AyUG-RS===============================

						List<ArrayList<String>> table5nlac_list = PARDAO.table5_Non_Lecture_Activities_Course_AyUGRS(course_id);

//						System.err.println("table5nlac_list"+table5nlac_list);

						List<ArrayList<String>> practhours = PARDAO.practhours(course_id);

						List<String> TH5 = new ArrayList<String>();

						TH5.add("Ser No.\n");
						TH5.add(" List non lecture Teaching-Learning methods");
						TH5.add("No of Activities\n" + "(Values in hours)");

						// end of Table 5: Non-Lecture Activities Course
						// AyUG-RS===============================

						// 6 A - Number of Papers and Marks Distribution============================

						List<ArrayList<String>> table6A_number_of_papers_list = PARDAO
								.Table6A_NumberofPapersDataTotalCount(course_id);

						int total6 = table6A_number_of_papers_list.size();
						List<String> TH6A = new ArrayList<String>();

						TH6A.add("S.No.");// 0
						TH6A.add("Subject Code");// 1
						TH6A.add("Papers");// 2
						TH6A.add("Theory");// 3
						TH6A.add("Practical/ \n" + "Clinical");// 4
						TH6A.add("Viva");// 5
						TH6A.add("Electives");// 6
						TH6A.add("IA");// 7
						TH6A.add("Sub Total");// 8
						TH6A.add("Grand Total");// 9
						TH6A.add("Practical/Clinical Assessment");// 10

						// end of 6 A - Number of Papers and Marks
						// Distribution============================

						// 6-B=====================

						ArrayList<ArrayList<String>> table6b_term1list = vsaDAO.get6BSchemeViewdatabyCourse(course_id, "I");
						ArrayList<ArrayList<String>> table6b_term2list = vsaDAO.get6BSchemeViewdatabyCourse(course_id, "II");
						ArrayList<ArrayList<String>> table6b_term3list = vsaDAO.get6BSchemeViewdatabyCourse(course_id, "III");

						// 6-B======================

						// 6 D - Evaluation Methods for Periodical
						// Assessment============================

						List<ArrayList<String>> Table6Dnlac_list = PARDAO
								.Table6D_Evaluation_Methods_For_Periodical_Assessment(course_id);

						List<String> TH6D = new ArrayList<String>();

						TH6D.add("Ser No.\n");
						TH6D.add(" Evaluation Methods for Periodical Assessment");

						// end 6 D - Evaluation Methods for Periodical
						// Assessment===============================

						// Table 6E: Paper Layout===============================

						ArrayList<ArrayList<String>> table_6E1 = PARDAO.table_6E_paper_layout(course_id);
						ArrayList<ArrayList<String>> table_6E2 = PARDAO.table_6E_paper_layout2(course_id);

						List<String> TH6E = new ArrayList<String>();
						List<String> TH6E2 = new ArrayList<String>();

						// end of Table 6E: Paper
						// Layout==========================================================

						// 6FI-PaperI=========================================

						ArrayList<ArrayList<String>> Table6F_IDistribution_of_Theory_Exam_List = (ArrayList<ArrayList<String>>) PARDAO
								.Table6F_IDistribution_of_Theory_Exam_List_DAO(course_id);

						// end 6FI-PaperI===========================

						// 6FI- PaperII=========================================

						ArrayList<ArrayList<String>> Table6F_IIDistribution_of_Theory_Exam_List = (ArrayList<ArrayList<String>>) PARDAO
								.Table6F_II_Distribution_of_Theory_Exam_List_DAO(course_id);

						// end 6FI- PaperII===============================

						// 6 G Question paper Blue print for AyU-RS====================================

						List<ArrayList<String>> table6g1_list = qpbao.getpaperformatdata(course_id, "5,6,7", "mcq", "PAPER I");
						List<ArrayList<String>> table6g2_list = qpbao.getpaperformatdata(course_id, "5,6", "saq", "PAPER I");
						List<ArrayList<String>> table6g3_list = qpbao.getpaperformatdata(course_id, "5", "laq", "PAPER I");
						List<ArrayList<String>> table6g4_list = qpbao.getpaperformatdata(course_id, "5,6,7", "mcq", "PAPER II");
						List<ArrayList<String>> table6g5_list = qpbao.getpaperformatdata(course_id, "5,6", "saq", "PAPER II");
						List<ArrayList<String>> table6g6_list = qpbao.getpaperformatdata(course_id, "5", "laq", "PAPER II");

						List<String> TH6G = new ArrayList<String>();

						TH6G.add("A\n" // 0
								+ "Question\n" + "Sr. No \n");
						TH6G.add("B\n" // 1
								+ "Type of Question \n");
						TH6G.add("C\n" // 2
								+ "Question Paper Format\n" + "(Refer table 6 F II Theme table for themes) \n");
						TH6G.add("Q1 \n"); // 3
						TH6G.add("Multiple choice Questions\n" // 4
								+ "(MCQ)\n\n" + "20 Questions\n\n" + "1 mark each\n\n" + "All compulsory\n\n"
								+ "Must know part: 15 MCQ\n" + "Desirable to know: 3 MCQ\n" + "Nice to know: 2 MCQ \n");
						TH6G.add("Q2 \n"); // 5
						TH6G.add(
								"Short answer Questions\n" + "(SAQ)\n" + "Eight Questions\n" + "5 Marks Each\n" + "All compulsory\n"
										+ "Must know part: 7 SAQ\n" + "Desirable to know: 1 SAQ\n" + "Nice to know: Nil");
						TH6G.add("Q3 \n"); // 6
						TH6G.add("Long answer Questions\n" // 7
								+ "(LAQ)\n" + "Four Questions\n" + "10 marks each\n" + "All compulsory\n"
								+ "All questions on must know\n" + "No Questions on Nice to know and Desirable to\n" + "know");
						TH6G.add("Q1 \n"); // 8
						TH6G.add("Multiple choice Questions\n" // 9
								+ "(MCQ)\n\n" + "20 Questions\n\n" + "1 mark each\n\n" + "All compulsory\n\n"
								+ "Must know part: 15 MCQ\n" + "Desirable to know: 3 MCQ.\n" + "Nice to know: 2 MCQ");
						TH6G.add("Q2 \n"); // 10
						TH6G.add("Short answer Questions\n" // 11
								+ "(SAQ)\n" + "Eight Questions\n" + "5 Marks Each\n" + "All compulsory\n"
								+ "Must know part: 7 SAQ\n" + "Desirable to know: 1 SAQ\n" + "Nice to know: Nil");
						TH6G.add("Q3 \n"); // 12
						TH6G.add("Long answer Questions\n" // 13
								+ "(LAQ)\n" + "Four Questions\n" + "10 marks each\n" + "All compulsory\n"
								+ "All questions on must know\n" + "No Questions on Nice to know and Desirable to\n" + "know");

						TH6G.add("Question Sr. No  \n"); // 14

						TH6G.add("SET \n"); // 15

						// end of 6 G Question paper Blue print for
						// AyU-RS================================

						// Table 6H - I - Distribution of Practical Exam ===============================

						List<ArrayList<String>> table_6H1 = PARDAO.table_6H_I_Distribution_of_Practical_Exam(course_id);

						List<String> TH6H1 = new ArrayList<String>();

						TH6H1.add("SN");
						TH6H1.add("Heads ");
						TH6H1.add("Marks");

						// end of Table 6H - I - Distribution of Practical
						// Exam==========================================================

						// 7. Reference and Resourses========================================

						ArrayList<ArrayList<String>> reference_resourses_list = PARDAO.tablereference_resourses(course_id);
						int totalrrlist = reference_resourses_list.size();

						// End 7. Reference and Resourses======================================

						if (list.size() == 0) {
							Mmap.put("msg", "Data Not Available.");
						} else {
							Mmap.put("list", list);
							if (typeReport != null && typeReport.equals("pdfL")) {
								if (list.size() > 0) {
									List<String> TH = new ArrayList<String>();
									TH.add("sr. no."); // 0
									TH.add("Ayush Id/ABHA No.");// 1
									TH.add("Name Of The Professional With Recent Photograph"); // 2
									TH.add("NRH Enrollment No."); // 3
									TH.add("Father's Name"); // 4
									TH.add("Present Correspondence Address"); // 5
									TH.add("Permanent Address"); // 6
									TH.add("Aadhaar Number"); // 7
									TH.add("Email Address With Mobile No."); // 8
									TH.add("Email Address With Mobile No."); // 9
									TH.add("Date of Birth And Nationality"); // 10
									TH.add("Name Of Medical Degree or Diploma Obtained And Uni"); // 11

									String Heading = "\nSCHEDULE OF CREDIT";
									return new ModelAndView(new DownloadCurriculumPdf("L", TH, TH1, TH2, TH3, THLPA, TH4, TH5, TH6A,
											TH6D, TH6E, TH6E2, TH6G, TH6H1, username, list, total, examination_list,
											t1copolink_list, t2Content_Course_AyUGRS_list, t3LearningObject_Course_AyUGRS_list,
											tableList_of_practical_list, t4Learning_Objectiveslist, table5nlac_list, practhours,
											table6A_number_of_papers_list, table6b_term1list, table6b_term2list, table6b_term3list,
											Table6Dnlac_list, table_6E1, table_6E2, Table6F_IDistribution_of_Theory_Exam_List,
											Table6F_IIDistribution_of_Theory_Exam_List, table6g1_list, table6g2_list, table6g3_list,
											table6g4_list, table6g5_list, table6g6_list, table_6H1, reference_resourses_list,course_id),
											"userList", list);
								}
							}
						}
						// end
					}
					return new ModelAndView("CC_Professional_Ayu_Report_Tiles");
				}
				//-----------CURIICULAM API END-----------------
				
			//------------PLACEMENT MANAGMENT API START---------
				
				 //-----get district list-------
				 @CrossOrigin( origins = "*" )
				 @RequestMapping(value = "/getMedDistrictName_API", method = RequestMethod.GET)
					public @ResponseBody List<EDU_LMS_DISTRICT_MSTR> getMedDistrictName_API() throws ParseException {
					
					 
					 Session sessionHQL = sessionFactory.openSession();
						Transaction tx1 = sessionHQL.beginTransaction();
						try {
							Query q1 = sessionHQL.createQuery(
									"select distinct district_id,district_name from EDU_LMS_DISTRICT_MSTR where status='1' order  by district_name");
							List<EDU_LMS_DISTRICT_MSTR> list = (List<EDU_LMS_DISTRICT_MSTR>) q1.list();
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
				 
				 //-----get state list-------
				 @CrossOrigin( origins = "*" )
				 @RequestMapping(value = "/getMedStateName_API", method = RequestMethod.GET)
					public @ResponseBody List<TB_STATE>  getMedStateName_API() throws ParseException {
					
					 Session sessionHQL = sessionFactory.openSession();
						Transaction tx1 = sessionHQL.beginTransaction();
						try {
							Query q1 = sessionHQL.createQuery(
									"select distinct state_id,state_name from TB_STATE where status='1' order  by state_name");
							List<TB_STATE> list = (List<TB_STATE>) q1.list();
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
				
				//------Registartion Save----------
				@CrossOrigin( origins = "*" )
				@RequestMapping(value = "/placement_reg_Action_API", method = RequestMethod.POST)
				public @ResponseBody String placement_reg_Action_API(MultipartFile mulPhoto,MultipartFile mulFile,
						String first_name, String father_name, String dob,String gender ,String mo_no,
						String alt_no,String email_id,String add_line1,String curr_add,String state,String per_district,
						String pincode,String gp_title,String fm_name,String fm_email,String im_name,String fi_email,
						String im_designation,String typeOfDegree,String job_seekers_hid,String upload_cv_hid,
						String intership_dur, String intern_hours_from,String intern_hours_to,String role, String username,
						 HttpServletRequest request, HttpSession session) throws IOException, ParseException {

				//String role = session.getAttribute("role").toString();

				String msg = "";
				EDU_PLACEMENT_REG td = new EDU_PLACEMENT_REG();
				 Date date = new Date();
				 
				 
			     DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			      
					
//					MultipartFile file2 = mul.getFile("photo_path");
					String photo_path2="";
					if (!mulPhoto.getOriginalFilename().isEmpty()) {
						photo_path2 = common.fileupload1(mulPhoto.getBytes(), mulPhoto.getOriginalFilename(),
								1, "signature" + "1");
						
						System.err.println("signature--------------"+photo_path2);
					}
//					if (upload_img_hid.trim().equals("")) {
//						
//						upload_img_hid=photo_path2;
//						
//						System.err.println("signature--------------"+photo_path2);
//						
//					}
					
//					===============FOR FILE
	//				MultipartFile file28 = mul.getFile("photo_path");
					String photo_file420="";
					if (!mulFile.getOriginalFilename().isEmpty()) {
						photo_file420 = common.fileupload1(mulFile.getBytes(),mulFile.getOriginalFilename(),
								1, "signature" + "1");
						
						System.err.println("upload_cv--------------"+photo_file420);
					}
					if (upload_cv_hid.trim().equals("")) {
						
						upload_cv_hid=photo_file420;
						
						System.err.println("upload_cv--------------"+photo_file420);
						
					}
					
					int id = td.getId() > 0 ? td.getId() : 0;
				
				
//				String system_name = principal.getName();

				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();

				try {
					Date dob3 = null;
					dob3 = format.parse(dob);
					Long c = (Long) sessionHQL.createQuery(
							"select count(id) from  EDU_PLACEMENT_REG where upper(first_name)=:first_name and father_name=:father_name and photo_path=:photo_path and upload_cv=:upload_cv and id !=:id")
							.setParameter("first_name", td.getFirst_name())
							.setParameter("father_name", father_name)
							.setParameter("photo_path", photo_path2)
							.setParameter("upload_cv", photo_file420)
							.setParameter("id", id).uniqueResult();
					if (id == 0) {
						if (state != null && !state.equals("")) {
							td.setState(Integer.parseInt(state));
						}
						
					if (id == 0) {
							if (per_district != null && !per_district.equals("")) {
								td.setPer_district(Integer.parseInt(per_district));
							}
							
							if (photo_path2 != null && !photo_path2.equals("")) {
								td.setPhoto_path((photo_path2));
							}
							
							if (intership_dur != null && !intership_dur.equals("")) {
								td.setIntership_dur((Integer.parseInt(intership_dur)));
							}
			 
							if (photo_file420 != null && !photo_file420.equals("")) {
								td.setUpload_cv((photo_file420));
							}
							
						td.setCreated_by(username);
//						td.setCreated_role(role);
						td.setCreated_date(date);
						if (c == 0) {
							sessionHQL.save(td);
//							td.setUpload_cv(upload_cv_hid);
							td.setIntern_hours_to(intern_hours_to);
							td.setJob_seekers(Integer.parseInt(job_seekers_hid));
							td.setFather_name(father_name);
							td.setFirst_name(first_name);
							td.setDob(dob3);
							td.setGender(gender);
							td.setMo_no(mo_no);
							td.setAlt_no(alt_no);
							td.setEmail_id(email_id);
							td.setAdd_line1(add_line1);
							td.setPincode(pincode);
							td.setGp_title(gp_title);
							td.setFm_name(fm_name);
							td.setFm_email(fm_email);
							td.setIm_name(im_name);
							td.setFi_email(fi_email);
							td.setIm_designation(im_designation);
						td.setIntern_hours_from(intern_hours_from);
						
							
							sessionHQL.flush();
							sessionHQL.clear();
							msg = "Data Saved Successfully.";
						} else {
							msg = "Data already Exist.";
						}
					}
					}
					tx.commit();
					}
				 catch (RuntimeException e) {
					try {

						msg = "roll back transaction";
					} catch (RuntimeException rbe) {
						msg = "Couldnot roll back transaction " + rbe;
					}
					throw e;
				} finally {
					if (sessionHQL != null) {
						sessionHQL.close();
					}
				}

				return msg;
			}	
			
				//----------apply search--------
				 @CrossOrigin( origins = "*" )
				 @RequestMapping(value = "/getFilter_Enterprise_signup_data_API", method = RequestMethod.POST)
					public @ResponseBody List<Map<String, Object>>  getFilter_Enterprise_signup_data_API(@RequestBody Map<String, String> data) throws ParseException {
					
					 int startPage = 0;
					 int pageLength = 100;

					 String Search = "";
					 String orderColunm = "";	
					 String orderType = "";
					 String company_name = data.get("company_name");
					 String name = data.get("name");
					 String email_id = data.get("email_id");
					 String mobile_no = data.get("mobile_no");
					 String ph_no = data.get("ph_no");
					 String address = data.get("address");
					 String state = data.get("state");
					 String per_district = data.get("per_district");
					 String pincode = data.get("pincode");
					 String hours_from = data.get("hours_from");
					 String hours_to = data.get("hours_to");
					 String web_url = data.get("web_url");
//					 String photo_path = data.get("photo_path");
//					 String photo_path_pic = data.get("photo_path_pic");
					 String photo_path = "undefined";
					 String photo_path_pic = "undefined";
					 String userid = data.get("user_id");
					 
					 return ARdao.DataTableEdu_Reg_Report_placement_mang_enterprise(startPage, pageLength, Search, orderColunm,
							 orderType,company_name,name,email_id,mobile_no,ph_no,address ,
								state,per_district,pincode,hours_from,hours_to,web_url,photo_path,photo_path_pic,userid);


					}
				 
				// comment from 2_11_2022 war
				 //-----get apply data from id-------
				 @CrossOrigin( origins = "*" )
				 @RequestMapping(value = "/getFilter_Enterprise_signup_dataFromId_API", method = RequestMethod.POST)
					public @ResponseBody List<Map<String, Object>>  getFilter_Enterprise_signup_dataFromId_API(@RequestBody Map<String, String> data) throws ParseException {
					
			
				 String id = data.get("id");
					 
			 return ARdao.DataTableEdu_Reg_Report_placement_mang_enterprise_fromId(id);


				}
				 
				//----------interested click event--------
				 @CrossOrigin( origins = "*" )
				 @RequestMapping(value = "/Approve_FromStudent_to_enterprise_Data_API" , method = RequestMethod.POST)
					public @ResponseBody List<String> Approve_FromStudent_to_enterprise_Data_API(@RequestBody Map<String, String> data,HttpSession session) throws ParseException {	
						SimpleDateFormat formate = new SimpleDateFormat("dd/mm/yyyy");
						String username = data.get("username").toString();
						String userId = data.get("userId").toString();
						String a = data.get("a"); 
						String email_id  = data.get("email_id");
						String notified = data.get("notified");
						
						String[] id_list = a.split(":");
						String[] email_list = email_id.split(":");
						String[] notif_list = notified.split(":");

						List<String> list2 = new ArrayList<String>();
						int id = 0;
						String email = "" ;
						String notif = "" ;
						String date;
						for (int i = 0; i < id_list.length; i++) {
							id = Integer.parseInt(id_list[i]);
							email  =  (email_list[i]);
							notif  =  (notif_list[i]);
							 
							list2.add(ARdao.approve_StudentPracData(Integer.toString(id),username,userId));
							
							if(notified.equals("1")) {
//								SendRegMail(email);
							}
							
							//end
						}
						
						
						return list2;
					}
				//----------not interested click event--------
				 @CrossOrigin( origins = "*" )
					@RequestMapping(value = "/Reject_FromStudent_to_enterprise_Data_API" , method = RequestMethod.POST)
					public @ResponseBody List<String> Reject_FromStudent_to_enterprise_Data_API(@RequestBody Map<String, String> data,HttpSession session) throws ParseException {	
						String username = data.get("username").toString();
						String userId_reject = data.get("userId").toString();
						String a = data.get("a");
						String[] id_list = a.split(":");
//						String[] id_list2 = upto2.split(":");
//						String[] tempSt2 = tempSt.split(",");
						List<String> list2 = new ArrayList<String>();
						int id = 0;
						String date;
						
						for (int i = 0; i < id_list.length; i++) {
							id = Integer.parseInt(id_list[i]);
//							date = id_list2[i];
//							System.out.println("remarks======================== "+tempSt2[i]);
							list2.add(ARdao.reject_StudentPracData(Integer.toString(id),username,userId_reject));
						}
						return list2;
					}
					
					// FOR EMAIL
//					public void SendRegMail(String email) throws ParseException {
//						
//						System.err.println("mail senttttttttttttttttt");
////						String code = randomString(5);
//						MailHTML html = new MailHTML();
//						try {
//							MimeMessage mimeMessage = mailSender.createMimeMessage();
//							MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
//							try {
//								String text = "Your Registration is Successfull ... Thank you for Registrating with us! Now , you can Login with your credentials.";
//								String note = "";
//								html.setHTML(text, note);
//								String htmlMsg = html.getHTML();
//								helper.setText(htmlMsg, true);
//								helper.setTo(email);
//								helper.setSubject("MOA Registration is Successfull");
//								/* helper.setFrom("abc@gmail.com"); */
//								mailSender.send(mimeMessage);
//							} catch (MessagingException e) {
//								e.printStackTrace();
//							}
//						} catch (Exception e) {
//						}
//					}
					
					//--------------------------------------------------Compny Logo Image_View------------------------------------------------------

					@RequestMapping(value = "/MedicalImagePathplace1_API", method = RequestMethod.GET)
					public void MedicalImagePathplace1_API(@ModelAttribute("i_id") String id,
							ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {

						final int BUFFER_SIZE = 4096;
						String i_id = id;
						String filePath = ARdao.getImagePath(i_id);
						model.put("filePath", filePath);
						ServletContext context = request.getSession().getServletContext();
						try {

							if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

								String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
//							request.getRealPath("/") + "/srv/Document/No_Image.jpg";

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



							String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
//								request.getRealPath("/") + "/srv/Document/No_Image.jpg";
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
					
					
//					-----------------company picture--------------
					@RequestMapping(value = "/MedicalImagePathplace3_API", method = RequestMethod.GET)
					public void MedicalImagePathplace3_API(@ModelAttribute("i_id6") String id,
							ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {

						final int BUFFER_SIZE = 4096;
						String i_id6 = id;
						String filePath1 = ARdao.getImagePath3(i_id6);
						model.put("filePath1", filePath1);
						ServletContext context = request.getSession().getServletContext();
						try {

							if (filePath1 == null && filePath1.isEmpty() && filePath1 == "" && filePath1 == "null") {

								String fullPath1 = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
//							request.getRealPath("/") + "/srv/Document/No_Image.jpg";

								File downloadFile1 = new File(fullPath1);

								FileInputStream inputStream1 = new FileInputStream(downloadFile1);

								String mimeType1 = context.getMimeType(fullPath1);

								if (mimeType1 == null) {

									mimeType1 = "application/octet-stream";

								}

								response.setContentType(mimeType1);

								response.setContentLength((int) downloadFile1.length());

								OutputStream outStream1 = response.getOutputStream();

								byte[] buffer = new byte[BUFFER_SIZE];

								int bytesRead = -1;

								while ((bytesRead = inputStream1.read(buffer)) != -1) {

									outStream1.write(buffer, 0, bytesRead);

								}

								inputStream1.close();

								outStream1.close();

							} else {

								String fullPath1 = filePath1;

								File downloadFile1 = new File(fullPath1);

								FileInputStream inputStream1 = new FileInputStream(downloadFile1);

								String mimeType1 = context.getMimeType(fullPath1);

								if (mimeType1 == null) {

									mimeType1 = "application/octet-stream";

								}

								response.setContentType(mimeType1);

								response.setContentLength((int) downloadFile1.length());

								OutputStream outStream1 = response.getOutputStream();

								byte[] buffer = new byte[BUFFER_SIZE];

								int bytesRead = -1;

								while ((bytesRead = inputStream1.read(buffer)) != -1) {
									outStream1.write(buffer, 0, bytesRead);
								}
								inputStream1.close();

								outStream1.close();

							}

						} catch (Exception ex) {

							// System.out.println(ex);

							// admin//js//img//No_Image.jpg

							String fullPath1 = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
//								request.getRealPath("/") + "/srv/Document/No_Image.jpg";
							File downloadFile1 = new File(fullPath1);

							FileInputStream inputStream1 = new FileInputStream(downloadFile1);

							String mimeType1 = context.getMimeType(fullPath1);

							if (mimeType1 == null) {

								mimeType1 = "application/octet-stream";

							}

							response.setContentType(mimeType1);

							response.setContentLength((int) downloadFile1.length());

							OutputStream outStream1 = response.getOutputStream();

							byte[] buffer = new byte[BUFFER_SIZE];

							int bytesRead = -1;

							while ((bytesRead = inputStream1.read(buffer)) != -1) {

								outStream1.write(buffer, 0, bytesRead);

							}

							inputStream1.close();

							outStream1.close();
						}
					}
				//end
			//------------PLACEMENT MANAGMENT API END---------
					
					
		//------------ALUMINI API START---------------
					
			//-----upcoming events data----
			@CrossOrigin( origins = "*" )
			@RequestMapping(value = "/Getupcomingevents_API", method = RequestMethod.GET)
			public @ResponseBody List<Map<String, Object>>  Getupcomingevents_API() throws Exception{
		
				List<Map<String, Object>>  list = aedao.Getupcomingeventsdata();
				 
					return list;
			     //return edao.getquestion();

			}
			
			
			  //----get up coming event details from event id-----
			  @CrossOrigin( origins = "*" )
				@RequestMapping(value = "/alumni_events_deatils_API", method = RequestMethod.POST)
				public @ResponseBody List<Map<String, Object>>  alumni_events_deatils_API(@RequestBody Map<String, String> data) throws Exception{
			
					String event_id = data.get("event_id");
					List<Map<String, Object>>  list = aedao.Getevent_divdata(event_id);
					 
						return list;
				     //return edao.getquestion();

				}
			
			  @CrossOrigin( origins = "*" )
			  @RequestMapping(value = "/getTotaleventinterestedCount_API", method = RequestMethod.POST)
				public @ResponseBody List<ArrayList<String>> getTotaleventinterestedCount_API(@RequestBody Map<String, String> data) {
				  String id = data.get("id");
					List<ArrayList<String>> list = aedao.getTotaleventinterestedCount(id);
					return list;
				}
				
			  @CrossOrigin( origins = "*" )
				@RequestMapping(value = "/getTotaleventparticipateCount_API", method = RequestMethod.POST)
				public @ResponseBody List<ArrayList<String>> getTotaleventparticipateCount_API(@RequestBody Map<String, String> data) {
				  String id = data.get("id");
					List<ArrayList<String>> list = aedao.getTotaleventparticipateCount(id);
					return list;
				}
				
			  //-----interested and particvipate click event-----
			  @RequestMapping(value = "/getevent_name_API", method = RequestMethod.POST)
				public @ResponseBody String getevent_name_API(@RequestBody Map<String, String> data,
					HttpServletRequest request, HttpSession Session) {

				  EDU_ALUM_ALUMNI_PARTICIPATE td =  new EDU_ALUM_ALUMNI_PARTICIPATE();
					String msg="";
					String event_id = data.get("event_id");
					String cat = data.get("cat");
					String userid = data.get("userid");
					
					int id = td.getId() > 0 ? td.getId() : 0;
					Date date = new Date();
					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx = sessionHQL.beginTransaction();
					String event = "";
					if (cat.equals("interested")) {
						event = " and interested=:interested ";
					}
					else if (cat.equals("participate")){
						event = " and participate=:participate ";
					}
					
					Query q = sessionHQL.createQuery(
							"select count(id) from EDU_ALUM_ALUMNI_PARTICIPATE where event_id=:event_id and user_id=:user_id" + event);
								q.setParameter("event_id", td.getEvent_id());
								q.setParameter("user_id", Integer.parseInt(userid));
								if (cat.equals("interested")) {
									q.setParameter("interested", 1);
								}
								else if (cat.equals("participate")){ 
									q.setParameter("participate", 1);
								}
								
								Long c = (Long) q.uniqueResult();
					
						
						if (id == 0) {
							td.setEvent_id(Integer.parseInt(event_id));
							if (cat.equals("interested")) {
								td.setInterested(1);
							}
							else if (cat.equals("participate")){
								td.setParticipate(1);
							}
							td.setUser_id(Integer.parseInt(userid));
							td.setCreated_by(userid);
//							td.setCreated_role(role);
							td.setCreated_date(date);
							if (c == 0) {
								sessionHQL.save(td);
								sessionHQL.flush();
								sessionHQL.clear();
								msg="Choice Captured.";
								
							} else {
								msg="Choice had been Captured.";
							}
						} 

					tx.commit();
					sessionHQL.close();
					return msg;
				}
			  
			//---- alumini batch data----
			@CrossOrigin( origins = "*" )
			@RequestMapping(value = "/GetAlumnibatchdata_API", method = RequestMethod.POST)
			public @ResponseBody List<Map<String, Object>>  GetAlumnibatchdata_API(@RequestBody Map<String, String> data) throws Exception{
					System.err.println("data---"+data);
				String userid = data.get("userid");
				//userid = 1415
				List<Map<String, Object>>  list = aedao.GetAlumnibatchdata(userid);
				 
					return list;
			     //return edao.getquestion();

			}
			
			
			//-----feeds data----
			@CrossOrigin( origins = "*" )
			@RequestMapping(value = "/getFeedsData_API", method = RequestMethod.GET)
			public @ResponseBody List<ArrayList<String>>  getFeedsData_API() throws Exception{
		
				List<ArrayList<String>>  list = aedao.getFeedsData();
				 
					return list;
			     //return edao.getquestion();

			}
			
			@CrossOrigin( origins = "*" )
			@RequestMapping(value = "/getEventImg_API", method = RequestMethod.GET)
			public void getEventImg_API(String id, ModelMap model,
					HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {

				final int BUFFER_SIZE = 4096;
				
				String filePath = aedao.getEventImg(id);
				

				model.put("filePath", filePath);

				ServletContext context = request.getSession().getServletContext();

				try {

					if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

						String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";

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
					
					
					String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";

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
			
			//----feed alumini profile photo-----
			@RequestMapping(value = "/getAlumniProfilephoto_API", method = RequestMethod.GET)
			public void getAlumniProfilephoto_API(@ModelAttribute("id") String id, ModelMap model,
					HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {

				final int BUFFER_SIZE = 4096;
				
				String filePath = aedao.getAlumniPP(id);
				

				model.put("filePath", filePath);

				ServletContext context = request.getSession().getServletContext();

				try {

					if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

						String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";

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
					
					
					String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";

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
			
			//---------feed image -----
			@RequestMapping(value = "/getFeedImg_API", method = RequestMethod.GET)
			public void getFeedImg_API(@ModelAttribute("id") String id, ModelMap model,
					HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {

				final int BUFFER_SIZE = 4096;
				
				String filePath = aedao.getFeedsImg(id);
				

				model.put("filePath", filePath);

				ServletContext context = request.getSession().getServletContext();

				try {

					if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

						String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";

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
					
					
					String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";

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
				
			
			//----create event api start----
			
			//---create event save-----
			@CrossOrigin( origins = "*" )
			  @PostMapping(value = "/Create_Event_Action_API") 
			  public @ResponseBody String Create_Event_Action_API( MultipartFile file, String title,String description ,String venue,String date_time,String batch,String eid,String userid,
					  HttpServletRequest request, HttpSession session) throws IOException {

				  String msg1 = "";
				  EDU_ALUM_ALUMNI_EVENT td = new EDU_ALUM_ALUMNI_EVENT();
			  	
			  	


			  	int id = td.getId() > 0 ? td.getId() : 0;
			  	if(!eid.equals("0")) {
			  		id = Integer.parseInt(eid);
			  	}
			  	Date date = new Date();

			  	//String userid = session.getAttribute("userId_for_jnlp").toString();

			  	//MultipartFile file = mul.getFile("upload_img");
			  	
			  	String eventimg = common.fileupload(file.getBytes(), file.getOriginalFilename(),
			  			1, "eventimg");

			  	Session sessionHQL = this.sessionFactory.openSession();
			  	Transaction tx = sessionHQL.beginTransaction();

			  	try {
			  		Long c = (Long) sessionHQL.createQuery(
			  				"select count(id) from  EDU_ALUM_ALUMNI_EVENT where upper(title)=:title and description=:description and venue=:venue and date_time=:date_time and batch=:batch and id !=:id")
			  				.setParameter("title", title.toUpperCase())
			  				.setParameter("description", description.toUpperCase())
			  				.setParameter("venue", venue.toUpperCase())
			  				.setParameter("date_time", date_time.toUpperCase())
			  				.setParameter("batch", batch.toUpperCase())
			  				.setParameter("id", id).uniqueResult();
			  		
			  		if (id == 0) {
			  			td.setTitle(title);
			  			td.setDescription(description);
			  			td.setDescription(description);
			  			td.setUpload_img(eventimg);
			  			td.setVenue(venue);
			  			td.setDate_time(date_time);
			  			td.setBatch(batch);
			  			td.setCreated_by(userid);
			  			td.setCreated_date(date);
			  			if (c == 0) {
			  				sessionHQL.save(td);
			  				sessionHQL.flush();
			  				sessionHQL.clear();
			  				msg1 = "Data Saved Successfully.";
			  			} else {
			  				msg1 = "Data already Exist.";
			  			}
			  		}
			  		
			  		else {
			  			td.setTitle(title);
			  			td.setDescription(description);
			  			td.setDescription(description);
			  			if(file.getOriginalFilename().equals("")) {
			  				td.setUpload_img(request.getParameter("upload_img_hid"));
			  			}else {
			  				td.setUpload_img(eventimg);
			  			}
			  			
			  			td.setVenue(venue);
			  			td.setDate_time(date_time);
			  			td.setBatch(batch);
			  			td.setModified_by(userid);
			  			td.setModified_date(date);
			  			
			  			if (c == 0) {
			  				td.setId(id);
			  				String msg = aedao.updateEvent(td);
			  				msg1 = msg;
			  			} else {
			  				msg1= "Data already Exist.";
			  			}
			  		}
			  		tx.commit();

			  	} catch (RuntimeException e) {
			  		try {

			  			msg1 = "roll back transaction";
			  		} catch (RuntimeException rbe) {
			  			msg1 = "Couldn't roll back transaction " + rbe;
			  		}
			  		throw e;
			  	} finally {
			  		if (sessionHQL != null) {
			  			sessionHQL.close();
			  		}
			  	}
			  	
			  	return msg1;
			  }

		
			  //------- event search-----
				  @CrossOrigin( origins = "*" )
				  @RequestMapping(value = "/getFiltereventdata_API", method = RequestMethod.POST)
				public @ResponseBody List<Map<String, Object>> getFiltereventdata_API(@RequestBody Map<String, String> data,HttpSession session) {
					  
					  	 int startPage = 0;
						 int pageLength = 100;
						 String Search = "";
						 String orderColunm = "";	
						 String orderType = "";
						 String title=data.get("title");
						 String description=data.get("description"); 
						 String upload_img=data.get("upload_img");
						 String venue=data.get("venue");
						 String date_time=data.get("date_time");
						 String batch=data.get("batch");
						 String userid=data.get("userid");
						 
					return aedao.DataTableEventDataList(startPage, pageLength, Search, orderColunm, orderType, title, description, upload_img, venue, date_time ,batch,userid);

				}

				  //-----event delete-----
				  @CrossOrigin( origins = "*" )
				  @RequestMapping(value = "/DeleteAlumVenturesData_API", method = RequestMethod.POST)
					public @ResponseBody String DeleteAlumVenturesData_API(@RequestBody JSONObject data,
							HttpServletRequest request,HttpSession session1) {


						//JSONObject jsonObject = new JSONObject();
						JSONArray jsonArray1 = new JSONArray();
						JSONParser jsonp = new JSONParser();
						JSONObject jsonobjectout = new JSONObject();
						String returnstring = "";
						try {
							// Add Server Side Validation TODO
							//jsonObject = (JSONObject) jsonp.parse(data);
							if (data.get("ventid") != null) {
								int userid = Integer.parseInt(data.get("userId").toString());
								int ventid = Integer.parseInt(data.get("ventid").toString());
								EDU_ALUM_ALUMNI_POST alum_ventures = AlumVenDao.GetAlumVenturesDataByID(ventid);
								if (alum_ventures != null) {

//									String sessionuserid = request.getSession().getAttribute("userId_for_jnlp").toString();
									alum_ventures.setModifiedBy(userid);
									alum_ventures.setModifiedDate(new Date());
									alum_ventures.setStatus(2);
									AlumVenDao.SaveAlumVenturesData(alum_ventures,"Edit");
									jsonobjectout.put("status", "1");
									jsonobjectout.put("message", "Data Deleted Successfully");
									returnstring = jsonobjectout.toJSONString();

								} else {
									jsonobjectout.put("status", "0");
									jsonobjectout.put("message", "No Data Found");
									returnstring = jsonobjectout.toJSONString();
								}

							} else {
								jsonobjectout.put("status", "0");
								jsonobjectout.put("message", "No Data Found");
								returnstring = jsonobjectout.toJSONString();
							}

						} catch (Exception e) {
							e.printStackTrace();
							jsonobjectout.put("status", "0");
							jsonobjectout.put("message", "Failure");
							returnstring = jsonobjectout.toJSONString();
						}

						return returnstring;
					}
				  
				
					
				  //------create event api end------
				  
				  //-----create post api start-----
					//-----categories list----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getFeedCategoryList_API", method = RequestMethod.GET)
					public @ResponseBody List<EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR>  getFeedCategoryList_API() throws Exception{
				
						List<EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR>  list = common.getFeedCategoryList(sessionFactory);
						 
							return list;
					     //return edao.getquestion();

					}
					
					//---- create post save----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/admin/SaveAlumVenturesData_API", method = RequestMethod.POST)
					@ResponseBody public String SaveAlumVenturesData(MultipartFile image,String feed_cat,String title,String description,String batch,
							String actiontype,String id1,String userId,HttpSession session,HttpServletRequest request) {
						String upload_img = "";
						JSONObject jsonobjectout = new JSONObject();
						 try {
							 System.out.println("image----"+image);
							upload_img = gd(request, image, session,upload_img);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						try {
							
							
							DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
						
							
							EDU_ALUM_ALUMNI_POST alum_ven = null;
							boolean checkTitlename = false;
							
							int userid = Integer.parseInt(userId);
						
							if (actiontype.equalsIgnoreCase("add")) {
								alum_ven = new EDU_ALUM_ALUMNI_POST();
							}else {
								System.out.println(id1);
								int id = Integer.parseInt(id1);
								alum_ven  = AlumVenDao.GetAlumVenturesDataByID(id);
							}
							alum_ven.setTitle(title);
							alum_ven.setDescription(description);
							alum_ven.setUploadImg(upload_img);
							alum_ven.setPostDate(new Date());
							alum_ven.setBatch(batch);
							alum_ven.setStatus(1);
							
							EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR Feed_cat_mst = new EDU_ALUM_ALUMNI_FEED_CATEGORY_MSTR();
							Feed_cat_mst.setId(Integer.parseInt(feed_cat));
							alum_ven.setFeedCatId(Feed_cat_mst);

							if (actiontype.equalsIgnoreCase("add")) {
								alum_ven.setCreatedBy(userid);
								alum_ven.setCreatedDate(new Date());
							}
							else {
								alum_ven.setModifiedBy(userid);
								alum_ven.setModifiedDate(new Date());
							}

							AlumVenDao.SaveAlumVenturesData(alum_ven, actiontype);

							jsonobjectout = new JSONObject();
							jsonobjectout.put("status", "1");
							if (actiontype.equalsIgnoreCase("add")) {
								jsonobjectout.put("message", "Data Saved Successfully");
							}

							else {
								jsonobjectout.put("message", "Data Updated Successfully");
							}

						} catch (Exception e) {
							e.printStackTrace();
							jsonobjectout = new JSONObject();
							jsonobjectout.put("status", "0");
							jsonobjectout.put("message", "Something went wrong");
							//res = jsonobjectout.toJSONString();
						}
						return jsonobjectout.toJSONString();
					}
					
					//-----search post-----

					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/admin/LoadAlumVenturesData_API", method = RequestMethod.POST)
					@ResponseBody public String LoadAlumVenturesData_API(HttpServletRequest request, @RequestBody JSONObject data) {

						JSONArray jSONArray = new JSONArray();
						JSONObject object = new JSONObject();

						JSONObject object1 = new JSONObject();
						JSONParser jsonParser = new JSONParser();
						List<EDU_ALUM_ALUMNI_POST> alum_ventures = null;
						List<EDU_ALUM_ALUMNI_POST> Totalalum_ventures = null;
						/*
						 * "startPage" : startPage, "pageLength" : pageLength, "Search" : Search,
						 * "orderColunm" : orderColunm, "orderType" : orderType, "feed_cat":feed_cat,
						 * "title":title, "post_date":post_date, "batch":batch
						 */
						try {

							

							object = (JSONObject) data;

							//String username = request.getSession().getAttribute("username").toString();
							//int userid = Integer.parseInt(request.getSession().getAttribute("userId").toString());
							int userid = Integer.parseInt(object.get("userid").toString());
							alum_ventures = AlumVenDao.Loadalum_ventureData(userid, data.toString());
							int counter = Integer.parseInt(object.get("startPage").toString()) + 1;
							int hidval = 1;
							


							if (!alum_ventures.isEmpty()) {
								for (EDU_ALUM_ALUMNI_POST alumventures : alum_ventures) {
									DateFormat formater = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
									object = new JSONObject();
									object.put("id", alumventures.getId().toString());
									object.put("srno", "<span class='avtar avatar-blue'>" + counter + "</span>");
									object.put("feed_cat", alumventures.getFeedCatId().getFeed_category());
									object.put("title", alumventures.getTitle());
									object.put("description", alumventures.getDescription());
//									String  dt =format.alumventures.getPostDate().toString();
//									object.put("postdate",dt.substring(0,10));
									object.put("postdate",formater.format(alumventures.getPostDate()));
									object.put("batch", alumventures.getBatch());
									
									String imagestr = imageEncoderDecoder(request, alumventures.getUploadImg());
									object.put("Ventuimage", "<img src='data:image/png;base64," + imagestr
											+ "' style='height:100px;width:100px;' onclick=\"ViewImage('" + imagestr + "')\" />");
									
									String f = "";

									String hidden = "<input type='hidden' name='hid" + hidval + "' id='hid" + hidval + "' value='"
											+encrypt(alumventures.getId() + "") + "' /> ";
									f = "<i class='action_icons action_update edtcls' title='Edit Data'></i>";
									f += "<i class='action_icons action_delete delcls' title='Delete Data'></i>";

									object.put("action",
											"<ul class=\"buttons-group\"><li><a href=\"#\" class=\"main-btn active-btn btn-hover btn-sm edtcls\"><i class=\"lni lni-pencil-alt\"></i></a></li><li><a href=\"#\" class=\"main-btn danger-btn btn-hover btn-sm delcls\"><i class=\"lni lni-trash-can\"></i></a></li></ul>"
													+ hidden);

									jSONArray.add(object);
									counter++;
									hidval++;

								}

								Totalalum_ventures = AlumVenDao.LoadalumVenturesDataForCount(userid);

								object1.put("alumVentureslist", jSONArray);
							} else {
								jSONArray = new JSONArray();
								object1.put("alumVentureslist", jSONArray);
							}

							object1.put("status", "1");
							object1.put("message", "Success");
							if (Totalalum_ventures != null) {
								object1.put("TotalCount", Totalalum_ventures.size());
							} else {
								object1.put("TotalCount", 0);
							}

						} catch (Exception e) {
							e.printStackTrace();
							object1 = new JSONObject();
							object1.put("status", "0");
							object1.put("message", "Something went wrong");
						}

						return object1.toJSONString();
					}
						
					
					 //-----get post data from id-------
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/admin/GetAlumVenturesDataForUpdate_API", method = RequestMethod.POST)
					@ResponseBody public String GetAlumVenturesDataForUpdate_API(HttpServletRequest request, @RequestBody JSONObject data) {
						//JSONObject jsonObject = new JSONObject();
						JSONArray jsonArray1 = new JSONArray();
						JSONParser jsonp = new JSONParser();
						JSONObject jsonobjectout = new JSONObject();
						String returnstring = "";
						try {

							// Add Server Side Validation TODO
							//jsonObject = (JSONObject) jsonp.parse(data);
							if (data.get("ventid") != null) {
								DateFormat formater = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

								int ventid = Integer.parseInt(data.get("ventid").toString());
								EDU_ALUM_ALUMNI_POST alum_ventures = AlumVenDao.GetAlumVenturesDataByID(ventid);
								if (alum_ventures != null) {
									jsonobjectout.put("feed_cat", alum_ventures.getFeedCatId().getId());
									jsonobjectout.put("title", alum_ventures.getTitle());
									jsonobjectout.put("description", alum_ventures.getDescription());
									jsonobjectout.put("post_date",formater.format(alum_ventures.getPostDate()));
									//jsonobjectout.put("post_date", alum_ventures.getPostDate());
									jsonobjectout.put("batch", alum_ventures.getBatch());
									jsonobjectout.put("imagedata", imageEncoderDecoder(request,alum_ventures.getUploadImg()));
									jsonobjectout.put("status", "1");
									jsonobjectout.put("message", "Success");
									jsonobjectout.put("vent_id",alum_ventures.getId());
									returnstring = jsonobjectout.toJSONString();
								} else {
									jsonobjectout.put("status", "0");
									jsonobjectout.put("message", "No Data Found");
									returnstring = jsonobjectout.toJSONString();
								}

							} else {
								jsonobjectout.put("status", "0");
								jsonobjectout.put("message", "No Data Found");
								returnstring = jsonobjectout.toJSONString();
							}

						} catch (Exception e) {
							e.printStackTrace();
							jsonobjectout.put("status", "0");
							jsonobjectout.put("message", "Failure");
							returnstring = jsonobjectout.toJSONString();
						}

						return returnstring;
					}
					
					//---post delete-------
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/Create_event_Url_Delete_Url_API", method = RequestMethod.POST)
					public @ResponseBody String Create_event_Url_Delete_Url_API(@RequestBody Map<String, String> data,
							HttpServletRequest request, HttpSession session1) {
							String msg = "";
						List<String> liststr = new ArrayList<String>();

						Session session = this.sessionFactory.openSession();
						Transaction tx = session.beginTransaction();

						String username = data.get("username").toString();
						String id = data.get("id");
						try {

							int app = session.createQuery(
									"delete from EDU_ALUM_ALUMNI_EVENT where id=:id")
									.setParameter("id", Integer.parseInt(id)).executeUpdate();

							
							tx.commit();
							session.close();
							if (app > 0) {
								System.err.println("check delete"+(app > 0));
								liststr.add("Data Deleted Successfully.");
							} else {
								liststr.add("Data not Deleted.");
							}
							msg = liststr.get(0);
						} catch (Exception e) {
							e.printStackTrace();
							liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
							msg = liststr.get(0);
							
						}
						return msg;
					}
					

					//-----create post Api End----
					
					//-----Repository Api Start----
					
					
					//-----categories list----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getCategoryList_API", method = RequestMethod.GET)
					public @ResponseBody List<TB_KNOWLEDGE_REPO_CATEGORY_MSTR>  getCategoryList_API() throws Exception{
				
						List<TB_KNOWLEDGE_REPO_CATEGORY_MSTR>  list = common.getCategoryList(sessionFactory);
						 
							return list;
					     //return edao.getquestion();

					}
					
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/KnowledgeRepoAction_API", method = RequestMethod.POST)
					public @ResponseBody String KnowledgeRepoAction_API(MultipartFile upload_doc,	String category_id ,String title,String description,String userid,
							HttpServletRequest request,HttpSession session
						) throws IOException {

						TB_KNOWLEDGE_REPO td = new TB_KNOWLEDGE_REPO();
					
						String msg = "";
						
						//MultipartFile upload_doc = mul.getFile("upload_doc");
						String photo_path_att = "";
						if (upload_doc != null && !upload_doc.isEmpty()) {
							photo_path_att = common.fileupload(upload_doc.getBytes(), upload_doc.getOriginalFilename(),
									"UploadHardCopy");
						}

						int id = td.getId() > 0 ? td.getId() : 0;
						Date date = new Date();
						//String username = principal.getName();
						//String userid = session.getAttribute("userId_for_jnlp").toString();
						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();

						try {
							Long c = (Long) sessionHQL.createQuery(
									"select count(id) from  TB_KNOWLEDGE_REPO where category_id=:category_id and title=:title and description=:description and id !=:id")
									.setParameter("category_id", td.getCategory_id())
									.setParameter("title", td.getTitle())
									.setParameter("description", td.getDescription())
									.setParameter("id", id).uniqueResult();
							
							if (id == 0) {
								td.setUpload_doc(photo_path_att);
								td.setUserid(Integer.parseInt(userid));
								td.setCreated_by(userid);
//								td.setCreated_role(role);
								td.setCreated_date(date);
								if (c == 0) {
									sessionHQL.save(td);
									sessionHQL.flush();
									sessionHQL.clear();
									msg = "Data Saved Successfully.";
								} else {
									msg = "Data already Exist.";
								}
							} 
							tx.commit();

						} catch (RuntimeException e) {
							try {

								msg = "roll back transaction";
							} catch (RuntimeException rbe) {
								msg = "Couldn�t roll back transaction " + rbe;
							}
							throw e;
						} finally {
							if (sessionHQL != null) {
								sessionHQL.close();
							}
						}
						
						return msg;
					}
					
					//-----Search Repository-----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getSearchRepo_API", method = RequestMethod.POST) 
					public @ResponseBody List<Map<String, Object>> getSearchRepo_API(@RequestBody Map<String, String> data) {
						
					        Session session = this.sessionFactory.openSession();
					        
							Transaction tx = session.beginTransaction();
							String a = data.get("search_value");
						       String qry ="";

						       System.err.println("============="+a);

					       if (a != null && !a.trim().equals("")) {
					    	   return knowdao.getSearchDetails(a);
					       }else {
					    	   return null;
					       }
					}


					private static SecretKeySpec secretKey;
					private static byte[] key;
					private static String passphrase = "Vision4@Bisag";
					public static void setKey(String myKey) {
						MessageDigest sha = null;
						try {
							key = myKey.getBytes("UTF-8");
							sha = MessageDigest.getInstance("SHA-256");
							key = sha.digest(key);
							key = Arrays.copyOf(key, 16);
							secretKey = new SecretKeySpec(key, "AES");
						} catch (NoSuchAlgorithmException e) {
							e.printStackTrace();
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}
					
					public static String encrypt(String strToEncrypt) {
						try {
							setKey(passphrase);
							Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
							cipher.init(Cipher.ENCRYPT_MODE, secretKey);
							return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
						} catch (Exception e) {
							e.printStackTrace();
						}
						return null;
					}
					
					public static String decrypt(String strToDecrypt) {
						try {
							setKey(passphrase);
							Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
							cipher.init(Cipher.DECRYPT_MODE, secretKey);
							return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
						} catch (Exception e) {
							e.printStackTrace();
						}
						return null;
					}
					
					public String imageEncoderDecoder(HttpServletRequest request,String imagepath) throws IOException {
					
						// image path declaration
						// String imgPath = "src/main/resources/images/bean.png";
					
						// read image from file
						FileInputStream stream = null;
						try {
						 stream = new FileInputStream(imagepath);
						}catch(IOException e){
							String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
							stream = new FileInputStream(fullPath);
						}
					
						// get byte array from image stream
						int bufLength = 2048;
						byte[] buffer = new byte[2048];
						byte[] data;
					
						ByteArrayOutputStream out = new ByteArrayOutputStream();
						int readLength;
						while ((readLength = stream.read(buffer, 0, bufLength)) != -1) {
							out.write(buffer, 0, readLength);
						}
					
						data = out.toByteArray();
						String imageString = Base64.getEncoder().withoutPadding().encodeToString(data);
					
						out.close();
						stream.close();
						return imageString;
					}
					
					//-----Repository Api End----
					
					//----edit profile start api-----------
					
					//----get profile data by id----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/GetProfileDataById_API", method = RequestMethod.POST)
					public @ResponseBody List<Map<String, Object>>  GetProfileDataById_API(@RequestBody Map<String, String> data) throws Exception{
							System.err.println("data---"+data);
						String userid = data.get("userid");
						//userid = 1415
						List<Map<String, Object>>  list = TDDAO.GetTechnical_Details_Data(Integer.parseInt(userid));
						 
							return list;
					     //return edao.getquestion();

					}
					
					//----get degree list----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getDegreeList_API", method = RequestMethod.GET)
					public @ResponseBody List<EDU_LMS_DEGREE_MASTER>  getDegreeList_API() throws Exception{
	
						List<EDU_LMS_DEGREE_MASTER>  list = common.getDegreeList(sessionFactory);
						 
							return list;

					}
					
					
					//-----get technical image----
					
					@RequestMapping(value = "/MedicalImagePathC_API", method = RequestMethod.GET)
					public void MedicalImagePathC_API(@ModelAttribute("i_id") String id, ModelMap model,
							HttpServletRequest request, HttpServletResponse response) throws IOException {
						
//						System.err.println("chgukhdfguhkdfhgkjfffffffffff---" + id);

						final int BUFFER_SIZE = 4096;

						String i_id = id;

						
						String filePath = TDDAO.getImagePathC(i_id);

//						System.out.println("chgukhdfguhkdfhgkj---" + filePath);

						model.put("filePath", filePath);
						System.err.println("filePath"+filePath);

						ServletContext context = request.getSession().getServletContext();

						try {

							if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {

								String fullPath = request.getRealPath("/") + "admin/js/images/No_Image.jpg";
//								request.getRealPath("/") + "/srv/Document/No_Image.jpg";

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
//									request.getRealPath("/") + "/srv/Document/No_Image.jpg";
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
					
					@PostMapping(value = "/Edit_ProfileAction_API")
					public @ResponseBody String Edit_ProfileAction_API( HttpServletRequest request, HttpSession session, Principal principal,
							MultipartFile file2,String id,String degree_id,String specialization_id ,String others ,String alum_name,String alum_address,
					String alum_email,String alum_mo_no,String alum_passing_year,String alum_batch,String alum_insta_id,String alum_fb_id,
					String alum_linkdin_id,String alum_curr_occu,String userid,String upload_img_hid) throws IOException {

						EDU_ALUM_REG_ALUMNI_CLG rs = new EDU_ALUM_REG_ALUMNI_CLG();
						String msg1 = "";
						System.err.println("-------userid------------"+userid);
						Session session1 = this.sessionFactory.openSession();
						Transaction tx = session1.beginTransaction();
						Date date = new Date();

						String prof_photo="";
						if (!file2.getOriginalFilename().isEmpty()) {
							prof_photo = common.fileupload(file2.getBytes(), file2.getOriginalFilename(),
									1, "signature" + "1");
							
							System.err.println("signature--------------"+prof_photo);
						}
						if (upload_img_hid.trim().equals("")) {
							
							upload_img_hid=prof_photo;
							
							System.err.println("signature--------------"+prof_photo);
							
						}
//						
						try {
							
								String hql = "update EDU_ALUM_REG_ALUMNI_CLG set alum_photo=:alum_photo,degree_id=:degree_id,specialization_id=:specialization_id,others=:others,"
										+ "alum_name=:alum_name,alum_address=:alum_address,alum_email=:alum_email,alum_mo_no=:alum_mo_no,alum_passing_year=:alum_passing_year,"
										+ "alum_batch=:alum_batch,alum_insta_id=:alum_insta_id,alum_fb_id=:alum_fb_id,alum_linkdin_id=:alum_linkdin_id,"
										+ "alum_curr_occu=:alum_curr_occu,modified_by=:modified_by,modified_date=:modified_date"
										+ " where id=:id";

								Query query = session1.createQuery(hql)
										.setParameter("alum_photo", prof_photo)
										.setParameter("degree_id", Integer.parseInt(degree_id))
										.setParameter("specialization_id", Integer.parseInt(specialization_id))
										.setParameter("others", others)
										.setParameter("alum_name", alum_name)
										.setParameter("alum_address", alum_address)
										.setParameter("alum_email", alum_email)
										.setParameter("alum_mo_no", alum_mo_no)
										.setParameter("alum_passing_year", alum_passing_year)
										.setParameter("alum_batch", alum_batch)
										.setParameter("alum_insta_id", alum_insta_id)
										.setParameter("alum_fb_id", alum_fb_id)
										.setParameter("alum_linkdin_id", alum_linkdin_id)
										.setParameter("alum_curr_occu", alum_curr_occu)
										.setParameter("modified_by", Integer.parseInt(userid))
										.setParameter("modified_date", new Date())
										.setParameter("id", Integer.parseInt(id));
								
										String msg = query.executeUpdate() > 0 ? "Data Updated Succefully" : "Data Not Updated";
										
										msg1 = msg;
								
								tx.commit();
								msg1 = "Data Updated Successfully.";
						} catch (RuntimeException e) {
							e.printStackTrace();
							try {
								tx.rollback();
								msg1 = "roll back transaction";
							} catch (RuntimeException rbe) {
								rbe.printStackTrace();
								msg1 = "Couldn't roll back transaction " + rbe;
							}
							throw e;

						} finally {
							if (session1 != null) {
								session1.close();
							}
						}
						return msg1;
					}
					
				public String gdC(HttpServletRequest request,MultipartHttpServletRequest mul,HttpSession session,String id) throws IOException {
						
						System.err.println("id----"+id);
					
					String extension=""; //add line
					String fname = ""; //add line
					
					request.getSession().setAttribute(id, "/srv"+ File.separator + "Document");
					
					MultipartFile file = mul.getFile(id);
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
						fname = dir.getAbsolutePath() + File.separator + (new Timestamp(date1.getTime())).toString().replace(":", "").toString().replace(".", ".").toString().replace(" ","").toString().replace("-","").toString()+ "."+extension;
						
						File serverFile = new File(fname);	               
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
						stream.write(bytes);	                
						stream.close();

					}else {

						
					}
					return fname;
					
					}
					//------edit profile end api-----------
				
			
					
					//------------ALUMINI API END---------------
				
				
				

				//----------LIBRARY API START------------
				
				
				//-----Book List API-------
				@CrossOrigin( origins = "*" )
				 @RequestMapping(value = "/getBookList_API", method = RequestMethod.GET)
					public @ResponseBody List<TB_BOOK_DTL> getBookList_API() throws ParseException {
					
					Session sessionHQL = this.sessionFactory.openSession();
					Transaction tx1 = sessionHQL.beginTransaction();
					try {
						Query q1 = sessionHQL.createQuery("from TB_BOOK_DTL where total_book_qty > 0");
						@SuppressWarnings("unchecked")
						List<TB_BOOK_DTL> list = (List<TB_BOOK_DTL>) q1.list();
						tx1.commit();
						return list;
					} catch (RuntimeException e) {
						e.printStackTrace();
						return null;
					} finally {
						if (sessionHQL != null) {
							sessionHQL.close();
						}
					}
					}
				
					//-------get member details----------
					@CrossOrigin( origins = "*" )
					 @RequestMapping(value = "/getMemberDetails_API", method = RequestMethod.POST)
					public @ResponseBody List<TB_MEMBER_DTL> getMemberDetails_API(@RequestBody Map<String, String> data) throws ParseException{

						String member_id = data.get("member_id");
						Session session1 = this.sessionFactory.openSession();

						Transaction tx1 = session1.beginTransaction();

						Query q1 = session1.createQuery("from TB_MEMBER_DTL where upper(member_id)=:member_id order by id")
								.setParameter("member_id", member_id.toUpperCase());

						@SuppressWarnings("unchecked")

						List<TB_MEMBER_DTL> list = (List<TB_MEMBER_DTL>) q1.list();

						return list;
					}
					
					//-------get book price api---------
					@CrossOrigin( origins = "*" )
					 @RequestMapping(value = "/getBookPrice_API", method = RequestMethod.POST)
					public @ResponseBody List<TB_BOOK_DTL> getBookPrice_API(@RequestBody Map<String, String> data) throws ParseException{

						String book_id = data.get("book_id");
						Session session1 = this.sessionFactory.openSession();

						Transaction tx1 = session1.beginTransaction();

						Query q1 = session1.createQuery("from TB_BOOK_DTL where id=:id order by id").setParameter("id",
								Integer.parseInt(book_id));

						@SuppressWarnings("unchecked")

						List<TB_BOOK_DTL> list = (List<TB_BOOK_DTL>) q1.list();

						return list;
					}
					
					//--------member course (system list) api----------------
					@CrossOrigin( origins = "*" )
					 @RequestMapping(value = "/getSystemList_API", method = RequestMethod.GET)
					public @ResponseBody List<EDU_LMS_SYSTEM_MSTR> getSystemList_API() throws ParseException{

						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx1 = sessionHQL.beginTransaction();
						try {
							Query q1 = sessionHQL.createQuery("from EDU_LMS_SYSTEM_MSTR where status='1' ");
							List<EDU_LMS_SYSTEM_MSTR> list = (List<EDU_LMS_SYSTEM_MSTR>) q1.list();
							tx1.commit();
							return list;
						} catch (RuntimeException e) {
							e.printStackTrace();
							return null;
						} finally {
							if (sessionHQL != null) {
								sessionHQL.close();
							}
						}
					}
					
					//------------book payment save api-----------
					
					@PostMapping(value = "/Member_Book_Req_Action_Save_API")
					public @ResponseBody String Member_Book_Req_Action_Save_API(@RequestBody Map<String, String> data,HttpServletRequest request,  
							HttpSession session) throws ParseException {

						String msg = "";
						String username = data.get("username");
						String member_id = data.get("member_id");
						String multiSelect_Book = data.get("multiSelect_Book");
						String book_charges = data.get("book_charges");
						

						SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");


						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();

						Long c = (Long) sessionHQL
								.createQuery(
										"select count(id) from TB_MEMBER_BOOK_REQ where member_id=:member_id and return_status = 1")
								.setParameter("member_id", member_id).uniqueResult();

						System.err.println("=============" + c);
						if (c == 0) {

							TB_MEMBER_BOOK_REQ TB = new TB_MEMBER_BOOK_REQ();
							TB.setMember_id(member_id);
							TB.setBook_select(multiSelect_Book);
							TB.setBook_charges(book_charges);
							TB.setBook_req_date(new Date());
							TB.setCreated_by(username);
							TB.setCreated_date(new Date());
							TB.setReturn_status(1);
							sessionHQL.save(TB);
							sessionHQL.flush();
							sessionHQL.clear();
							msg = "Paid Successfully.";
						} else {
							msg = "Book Already Requested/Not Returned.";
						}

						tx.commit();

						sessionHQL.close();
						return msg;
					}
				//-----------LIBRARY API END-------------
					
//					//---------HOSTAL MANAGMENT API START-----------------
//					
//					//------Hostel Name List-------
//					@CrossOrigin( origins = "*" )
//					@RequestMapping(value = "/gethostel_name_API", method = RequestMethod.GET)
//					public @ResponseBody List<Hostel_Room_Master> gethostel_name_API() throws Exception{
//					                
//					     return common.gethostel_name(sessionFactory);
//
//					}
//					
//					//-----Amenities List--------
//					@CrossOrigin( origins = "*" )
//					@RequestMapping(value = "/getAmenities_API", method = RequestMethod.GET)
//					public @ResponseBody List<Amenities_Master> getAmenities_API() throws Exception{
//					                
//					     return common.getAmenities(sessionFactory);
//
//					}
//					
//					  @PostMapping(value = "/Student_Room_RequestSave_API") 
//					  public @ResponseBody String Student_Room_RequestSave_API(@RequestBody Map<String, String> data,HttpServletRequest request, HttpSession session) {
//						  
//						String msg = "";
//						Date date = new Date(); 
//						TB_STUDENT_ROOM_REQUEST td = new TB_STUDENT_ROOM_REQUEST();
//						int hostel_id = Integer.parseInt(data.get("hostel_id"));
//						int Amenities =Integer.parseInt( data.get("Amenities"));
//						String registration_no = data.get("registration_no");
//						String username = data.get("username");
//						String user_id = data.get("userId").toString();
//
//						int id = td.getId() > 0 ? td.getId() : 0;
//
//						
//
//						Session sessionHQL = this.sessionFactory.openSession();
//						Transaction tx = sessionHQL.beginTransaction();
//
//						//try {
//							Long c = (Long) sessionHQL.createQuery(
//									"select count(id) from  TB_STUDENT_ROOM_REQUEST where hostel_id=:hostel_id  and registration_no=:registration_no and Amenities=:Amenities  and id !=:id")
//									.setParameter("id", td.getId())
//									.setParameter("hostel_id", td.getHostel_id())
//												 .setParameter("registration_no", td.getRegistration_no())
//														 .setParameter("Amenities", td.getAmenities())
//									.uniqueResult();
//
//									
//							if (id == 0) {
//								td.setHostel_id(hostel_id);
//								td.setRoom_no(0);
//								td.setRegistration_no(registration_no);
//								td.setUser_id(Integer.parseInt(user_id));
//								td.setAmenities(Amenities);
//								td.setRenew_date(new Date());
//
//								td.setStatus(0);
//								td.getBlock_id(0);
//								td.getFloor_no(0);
//								
//								td.setCreated_by(username);
//								td.setCreated_date(date);
//								td.setModified_by(username);
//								td.setModified_date(date);
//								if (c == 0) {
//									sessionHQL.save(td);
//									sessionHQL.flush();
//									sessionHQL.clear();
//									
//									msg = "Data Saved Successfully.";
//								} else {
//									msg = "Data already Exist.";
//								}
//							}
//
//							tx.commit();
//
//
//							sessionHQL.close();
//						return msg;
//					}
//					
//					  
//						
//					  @PostMapping(value = "/Student_Room_Transfer_RequestSave_API")
//					  public @ResponseBody String Student_Room_Transfer_RequestSave_API(@RequestBody Map<String, String> data, HttpServletRequest request,
//					  ModelMap model, HttpSession session) {
//
//						  TB_STUDENT_ROOM_TANSFER_REQUEST td = new TB_STUDENT_ROOM_TANSFER_REQUEST();
//						  
//						  Date date =  new Date();
//						  String msg = "";
//						  
//						  int hostel_id = Integer.parseInt(data.get("hostel_id"));
//						 String Amenities = data.get("Amenities");
//						 String registration_no = data.get("registration_no");
//						 String specification = data.get("specification");
//						 String username = data.get("username");
//						 String user_id = data.get("userId").toString();
//
//						int id = td.getId() > 0 ? td.getId() : 0;
//
//						
//
//						Session sessionHQL = this.sessionFactory.openSession();
//						Transaction tx = sessionHQL.beginTransaction();
//
//						//try {
//							Long c = (Long) sessionHQL.createQuery(
//									"select count(id) from  TB_STUDENT_ROOM_TANSFER_REQUEST where hostel_id=:hostel_id and registration_no=:registration_no and amenities=:amenities and specification=:specification and id !=:id")
//									.setParameter("id", td.getId())
//									.setParameter("hostel_id", td.getHostel_id())
//									.setParameter("registration_no", td.getRegistration_no())
//									.setParameter("specification", td.getSpecification())
//									.setParameter("amenities", td.getAmenities()).uniqueResult();
//
//									
//							if (id == 0) {
//								td.setHostel_id(hostel_id);
//								td.setRoom_no(0);										
//								td.setRent(0);
//								td.setRegistration_no(registration_no);
//								td.setUser_id(Integer.parseInt(user_id));
//								td.setAmenities(Integer.parseInt(Amenities));
//								td.setSpecification(specification);
//								td.setStatus(0);
//								td.setCreated_by(username);
//								td.setCreated_date(date);
//								td.setModified_by(username);
//								td.setModified_date(date);
//								if (c == 0) {
//									sessionHQL.save(td);
//									sessionHQL.flush();
//									sessionHQL.clear();
//									
//									
//									
//									
//									sessionHQL.flush();
//									sessionHQL.clear();
//									
//									msg= "Data Saved Successfully.";
//								} else {
//									msg="Data already Exist.";
//								}
//							}
//
//							tx.commit();
//
//						
//
//							sessionHQL.close();
//						return msg;
//					}
//
//						
//					//----------HOSTAL MANAGMENT API END-----------------
					
					//--------------FEEDBACK API START-----------------------
					
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getFeedBackCat_API", method = RequestMethod.POST)
					public @ResponseBody List<TB_FEEDBACK_CATEGORY_MSTR> getFeedBackCat_API(HttpServletRequest request) {
						Session sessionHQL = sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
						Query q = sessionHQL.createQuery("from TB_FEEDBACK_CATEGORY_MSTR where status = 1 order by id ");
						@SuppressWarnings("unchecked")
						List<TB_FEEDBACK_CATEGORY_MSTR> clist = (List<TB_FEEDBACK_CATEGORY_MSTR>) q.list();
						tx.commit();
						sessionHQL.close();

						return clist;
					}
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getFeedBackSubCat_API", method = RequestMethod.POST)
					public @ResponseBody List<TB_FEEDBACK_SUBCATEGORY_MSTR> getFeedBackSubCat_API(HttpServletRequest request) {
						Session sessionHQL = sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
						Query q = sessionHQL.createQuery("from TB_FEEDBACK_SUBCATEGORY_MSTR where status = 1 order by id ");
						@SuppressWarnings("unchecked")
						List<TB_FEEDBACK_SUBCATEGORY_MSTR> clist = (List<TB_FEEDBACK_SUBCATEGORY_MSTR>) q.list();
						tx.commit();
						sessionHQL.close();

						return clist;
					}
					
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/Feedback_details_save_ctrl_API", method = RequestMethod.POST)
					
					public @ResponseBody String Feedback_details_save_ctrl_API(@RequestBody Map<String, String> data)
					{
						
						String msg =""; //
						String user_id = data.get("user_id");
						String feedback_for= data.get("feedback_for");
						String feedback_rating= data.get("feedback_rating");
						String feedback_details= data.get("feedback_details");
						String feedback_for_sub = data.get("feedback_for_sub");
						
						
						System.err.println("user_id    >   "+user_id);
						System.err.println("feedback_for-------->   "+feedback_for);
						System.err.println("feedback_rating----->  "+feedback_rating);
						System.err.println("feedback_details----->  "+feedback_details);
						
						
						
						TB_FEEDBACK_DETAILS fd = new TB_FEEDBACK_DETAILS();
						
						Date date = new Date();
						
						Session sessionHQL = this.sessionFactory.openSession();
						 Transaction tx = sessionHQL.beginTransaction();
							
							
						 final SentimentPolarities sentimentPolarities =
						            SentimentAnalyzer.getScoresFor(feedback_details);
						        System.out.println(sentimentPolarities);
							// SentimentPolarities{positivePolarity=0.437, negativePolarity=0.0, neutralPolarity=0.563, compoundPolarity=0.4767}
						        System.out.println(" ====");
							
								
						 
						if(sentimentPolarities.getCompoundPolarity() > 0) {
							fd.setSentiment(1);

						}else {
							fd.setSentiment(0);

						}
						fd.setUser_id(Integer.parseInt(user_id));
						fd.setFeedback_for(Integer.parseInt(feedback_for));
						fd.setFeedback_rating(Integer.parseInt(feedback_rating));
						fd.setFeedback_details(feedback_details);
						fd.setFeedback_subcat(Integer.parseInt(feedback_for_sub));
						fd.setCreated_by(user_id);
						fd.setCreated_date(date);

						sessionHQL.save(fd);
						tx.commit();
						sessionHQL.close();
						msg = "Feedback Succesfully Saved";
						
						return msg;
					}

				
					//--------------FEEDBACK API END-----------------------
	
					//---------EXAMINATION API START--------
					
					//---------get degree---------
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getDegree_API", method = RequestMethod.POST)
					public @ResponseBody ArrayList<ArrayList<String>>  getDegree_API(@RequestBody Map<String, String> data) throws Exception{
						
						String userid = data.get("userid");
						ArrayList<ArrayList<String>>  list =  IAM.Getdegreeid_fetch(userid);
						 
							return list;

					}
					//-----------get course list by professional----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getcourselistby_professional_API", method = RequestMethod.POST)
					public @ResponseBody List<CC_TB_LIST_OF_TOPICS_PARENT> getcourselistby_professional_API(@RequestBody Map<String, String> data) {
						
						String professional_id = data.get("professional_id");
						
						Session sessionHQL = sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
						
						Query q = sessionHQL
								.createQuery("select cm.id,cm.course_name from CC_TB_LIST_OF_TOPICS_PARENT tp,"
										+ "EDU_LMS_ELECTIVE_COURSE_MASTER ec,EDU_LMS_COURSE_MASTER cm,CC_TB_PROFESSIONAL_MSTR pm\n"
										+ "where ec.id=tp.course_id and cm.id=cast(ec.course_name as integer)  and pm.id=tp.professional_id and tp.professional_id=:professional_id");
						
						q.setParameter("professional_id", Integer.parseInt(professional_id));
						@SuppressWarnings("unchecked")
						List<CC_TB_LIST_OF_TOPICS_PARENT> clist = (List<CC_TB_LIST_OF_TOPICS_PARENT>) q.list();
						tx.commit();
						sessionHQL.close();
						return clist;
						
					}
					
					//-----------get course list----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "admin/getCourseListByterm_API", method = RequestMethod.POST)
					public @ResponseBody List<EDU_LMS_COURSE_MASTER> getCourseListByterm_API(@RequestBody Map<String, String> data) {
						String system_id = data.get("system_id");//--null
						String degree_id = data.get("degree_id");
						String professional_id = data.get("professional_id");
						System.out.println("system_id1---"+system_id);
						System.out.println("degree_id1---"+degree_id);
						System.out.println("professional_id1---"+professional_id);
						

						Session sessionHQL = sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
						
						String qry = " select distinct cm.id,cm.course_name \n"
								+ " from EDU_CC_LINK_SYSTEM_DEGREE_PROFESSIONAL_COURSE m , EDU_LMS_COURSE_MASTER cm \n"
								+ " where cm.id=m.course_id and m.status=1 ";
						
						String system_qry = " and m.system_id=:system_id ";
						String degree_qry = " and m.degree_id=:degree_id ";
						String prof_qry = " and m.professional_id=:professional_id ";
						
						if(system_id != null) {
							qry += system_qry;
						}
						if(degree_id != null) {
							qry += degree_qry;
						}
						if(professional_id != null) {
							qry += prof_qry;
						}
						
						Query q = sessionHQL.createQuery(qry);
						
						if(system_id != null) {
							q.setParameter("system_id", Integer.parseInt(system_id));
						}
						if(degree_id != null) {
							q.setParameter("degree_id", Integer.parseInt(degree_id));
						}
						if(professional_id != null) {
							q.setParameter("professional_id", Integer.parseInt(professional_id));
						}
						
						@SuppressWarnings("unchecked")
						List<EDU_LMS_COURSE_MASTER> clist = (List<EDU_LMS_COURSE_MASTER>) q.list();
						
						tx.commit();
						sessionHQL.close();
						return clist;
					}
					
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getMarksbyCourse_API", method = RequestMethod.POST)
					public @ResponseBody   ArrayList<ArrayList<String>> getMarksbyCourse_API(@RequestBody Map<String, String> data) {
						
						String course_id = data.get("course_id");
						
					 ArrayList<ArrayList<String>> list = tcDAO.getMarksbyCoursedata(course_id);
						return list;
					}
					
					//------view onclick event------
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getviewInternal_ass_marks_data_API", method = RequestMethod.POST)
					public @ResponseBody List<ArrayList<String>> getviewInternal_ass_marks_data_API(@RequestBody Map<String, String> data,HttpSession session) {
						
						String course_id = data.get("course_id");
						String professional_id = data.get("professional_id");
						String userid = data.get("userid");
						String exam_type = data.get("exam_type");
						String exam_seral = data.get("exam_seral");
						String role = data.get("role");
						
						
						System.err.println("stmt====course_id=111===="+course_id);
						
						
						List<ArrayList<String>> list = IAM.GetviewInternal_ass_marks(course_id,professional_id,userid,exam_type,exam_seral,role);
						return list;
					}
					
					//---------EXAMINATION API END--------
					
					//-----STUDENTS ATTENDANCE REPORT-------START---
					
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getCourseListofStudent_API", method = RequestMethod.POST)
					public @ResponseBody List<Map<String, Object>>  getCourseListofStudent_API(@RequestBody Map<String, String> data) throws Exception{
						
						//String userid = data.get("userid");
						String system = data.get("system_id");
						String degree = data.get("degree_id");
						String professional = data.get("professional_id");
						List<Map<String, Object>>  list = SARdao.getCourselistofStudent(system,degree,professional); //IAM.Getdegreeid_fetch(userid);
						 
							return list;

					}
					
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getStu_ChildUrl_API", method = RequestMethod.POST)
					public @ResponseBody List<ArrayList<String>>  getStu_ChildUrl_API(@RequestBody Map<String, String> data) throws Exception{
						
						String userid1 = data.get("userId");
						String crsid = data.get("course_name");
						String role = data.get("role");
						String role_id = data.get("role_id");
						String searchmonth = data.get("searchmonth");
						String instid = common.getInstIdfromUserid( sessionFactory,  userid1).get(0);
						//String crsid = data.get("crsid");
						
						String month="";
						int year=0;
						if(searchmonth.equals("")) {
							Calendar calendar = Calendar.getInstance();
							month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
							year = calendar.get(Calendar.YEAR);
						}else {
							month = searchmonth.split("-")[1];
							year = Integer.parseInt(searchmonth.split("-")[0]);
						}
						if(String.valueOf(month).length()==1) {
							month="0"+String.valueOf(month);
						}
//						System.err.println("\n\n month---"+month);
					 ArrayList<ArrayList<String>> list = SARdao.getPopup_ChildDatalist(userid1,crsid,role,role_id,month, String.valueOf(year),instid);
						return list;
						

					}
					
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getCountofPA_API", method = RequestMethod.POST)
					public @ResponseBody  List<Map<String, Object>> getCountofPA_API(@RequestBody Map<String, String> data) throws Exception{
						
						String userid1 = data.get("userId");
						String course_id = data.get("course_id");
						String role = data.get("role");
						String attendance = data.get("attendance");
						
						List<Map<String, Object>>  list = SARdao.getStudcountPA(course_id,userid1,role,attendance); 
						 System.out.println("getStudcountPA---"+list);
							return list;

					}
					
					
					
					
		//-----------STUDENTS ATTENDANCE REPORT-------END---
					
					
		//-------------ACADEMIC SCHEDUAL-----(STD_MNGT_TIME_TABLE)--API---START-------
					
					//getSystemList_API   ----Post-----  "role":"NCISM"						
					//getDegreeList_API		----Get
					//getProfessional_API    --Get
					//getDegreeListbysystem1_API-----system_name = 44;
					
					//get system id---Academic Schedual---
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/Getsytemid_fetch_API", method = RequestMethod.POST)
					public @ResponseBody List<ArrayList<String>>  Getsytemid_fetch_API(@RequestBody Map<String, String> data) throws Exception{
						
						String userid = data.get("userId").toString();
						
						List<ArrayList<String>>  list = dmdao.Getsytemid_fetch(userid);
						 System.out.println("GetviewDate_Academic_Proflist---"+list);
							return list;

					}
					
					//onView Btn Click---Academic Schedual---
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/GetviewDate_Academic_Prof_API", method = RequestMethod.POST)
					public @ResponseBody List<ArrayList<String>>  GetviewDate_Academic_Prof_API(@RequestBody Map<String, String> data) throws Exception{
						
						String professional_id = data.get("professional_id");
								String userid = data.get("userId").toString();
						
						Session sessiont = sessionFactory.openSession();
						Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
						 List<UserLogin> institute_idList = q1.list();
						 sessiont.close();
						int institute_id = institute_idList.get(0).getInstitute_id();
						
						List<ArrayList<String>>  list = asdao.GetviewDate_Academic_Prof(professional_id,institute_id);
						 System.out.println("GetviewDate_Academic_Proflist---"+list);
							return list;

					}
					
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/GetviewDate_Examination_Prof_API", method = RequestMethod.POST)
					public @ResponseBody List<ArrayList<String>>  GetviewDate_Examination_Prof_API(@RequestBody Map<String, String> data) throws Exception{
						
						String professional_id = data.get("professional_id");
String userid = data.get("userId").toString();
						
						Session sessiont = sessionFactory.openSession();
						Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
						 List<UserLogin> institute_idList = q1.list();
						 sessiont.close();
						int institute_id = institute_idList.get(0).getInstitute_id();
						
						List<ArrayList<String>>  list = asdao.GetviewDate_Examination_Prof(professional_id,institute_id);
						 System.out.println("GetviewDate_Examination_Proflist---"+list);
							return list;

					}
					
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/GetviewDate_Fee_Prof_API", method = RequestMethod.POST)
					public @ResponseBody List<ArrayList<String>>  GetviewDate_Fee_Prof_API(@RequestBody Map<String, String> data) throws Exception{
						
						String professional_id = data.get("professional_id");
						String userid = data.get("userId").toString();
						
						Session sessiont = sessionFactory.openSession();
						Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
						 List<UserLogin> institute_idList = q1.list();
						 sessiont.close();
						int institute_id = institute_idList.get(0).getInstitute_id();
						
						List<ArrayList<String>>  list = asdao.GetviewDate_Fee_Prof(professional_id,institute_id);
						 System.out.println("GetviewDate_Fee_Proflist---"+list);
							return list;

					}
				
					
		//-------------ACADEMIC SCHEDUAL----API---END------	
					
	//-----------------FEE--------- API---START------
					
				
					//----type of degree List----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/gettype_of_degree_API", method = RequestMethod.GET)
					public @ResponseBody List<EDU_LMS_TYPE_OF_DEGREE_MASTER>  gettype_of_degree_API() throws Exception{
						
						
						List<EDU_LMS_TYPE_OF_DEGREE_MASTER>  list =  common.gettype_of_degree(sessionFactory);
						 System.out.println("getBesicNcism_detailslist---"+list);
							return list;

					}
					
					//----degree list by type of degree-----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/degreefrom_fromybyinstlist_ctrl_API", method = RequestMethod.POST)
					public @ResponseBody List<ArrayList<String>>  degreefrom_fromybyinstlist_ctrl_API(@RequestBody Map<String, String> data) throws Exception{
						
						String role = data.get("role").toString();
						String userid = data.get("userId").toString();
						String type_of_degree = data.get("type_of_degree").toString();
						
						String inst_id = common.getInstIdfromUserid( sessionFactory,  userid).get(0);
						
						
						List<ArrayList<String>>  list = upd.degreefrom_fromybyinstlist_ctrl(type_of_degree,inst_id,role);
							return list;

					}
					
					//---degree list-----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getDegreeCateList_API", method = RequestMethod.GET)
					public @ResponseBody List<EDU_LMS_DEGREE_CATE_MSTR> getDegreeCateList_API(HttpServletRequest request) {
						Session sessionHQL = sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
						Query q = sessionHQL.createQuery("from EDU_LMS_DEGREE_CATE_MSTR where status = 1 order by id ");
						@SuppressWarnings("unchecked")
						List<EDU_LMS_DEGREE_CATE_MSTR> clist = (List<EDU_LMS_DEGREE_CATE_MSTR>) q.list();
						tx.commit();
						sessionHQL.close();

						return clist;
					}
				
					//----(profession ) term List----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/gettermList_API", method = RequestMethod.GET)
					public @ResponseBody List<EDU_LMS_TERM_MASTER>  gettermList_API() throws Exception{
						
						
						List<EDU_LMS_TERM_MASTER>  list =  common.gettermList(sessionFactory);
						 System.out.println("getBesicNcism_detailslist---"+list);
							return list;

					}
					
					//---get fetch data for student -----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/sturoledata_API", method = RequestMethod.POST)
					public @ResponseBody List<Map<String, Object>>  sturoledata_API(@RequestBody Map<String, String> data) throws Exception{
						
					
						String role =data.get("role");
						String userid = data.get("userid");
						//String inst_id = common.getInstIdfromUserid( sessionFactory,  userid).get(0);
						
							List<Map<String, Object>>  list = upd.getStuTypeofDegProf(role,userid);
						
						
							return list;

					}
					
					//---get fetch data for institute -----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/instituteroledata_API", method = RequestMethod.POST)
					public @ResponseBody String  instituteroledata_API(@RequestBody Map<String, String> data) throws Exception{
						
					
						String role =data.get("role");
						String userid = data.get("userid");
						String inst_id = common.getInstIdfromUserid( sessionFactory,  userid).get(0);
					
							String  list = upd.InstNoOfPart(inst_id);
						
						
							return list;

					}
					
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getStudentNameAuto_API", method = RequestMethod.POST)
					public @ResponseBody  List<Map<String, Object>> getStudentNameAuto_API(@RequestBody Map<String, String> data) throws Exception{
						
						String autoString = data.get("userid");
						String degree_cat = data.get("degree_cat");
						String role = data.get("role");
						String instid = data.get("instid");
						//String userid = data.get("userid");					
						List<Map<String, Object>>  list = upd.StudentDataAuto(autoString,role,degree_cat,instid); 						 
							return list;

					}
				
					//-----fee details radio btn onclick event-----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getFeesDetails_API", method = RequestMethod.POST)
					public @ResponseBody  List<Map<String, Object>> getFeesDetails_API(@RequestBody Map<String, String> data) throws Exception{
						
						String role = data.get("role");
						String userid = data.get("userid");
						String studentid  = data.get("studentid");
						
						System.err.println("studentid---"+studentid+" role"+role+" userid"+userid);
						
						String instid = common.getInstIdfromUserid( sessionFactory,  userid).get(0);
						List<Map<String, Object>>  list =  upd.Getfeesdetails(studentid,role,instid);					 
							return list;

					}
					
					
						
						@CrossOrigin( origins = "*" )
						@RequestMapping(value = "/getSupplydata_API", method = RequestMethod.POST)
						public @ResponseBody String getSupplydata_API(@RequestBody Map<String, String> data) {
							String stu_id = data.get("stu_id");
						 String count = upd.getSupplyData(stu_id);
							return count;
						}
						
						@CrossOrigin( origins = "*" )
						@RequestMapping(value = "/isfeespaid_API", method = RequestMethod.POST)
						public @ResponseBody String isfeespaid_API(@RequestBody Map<String, String> data) {
							 String stu_id = data.get("stu_id");
							 String prof  = data.get("prof");
						 String count = upd.isFessPaid(stu_id,prof);
						 System.err.println("count---"+count);
							return count;
						}
						
						@CrossOrigin( origins = "*" )
						@RequestMapping(value = "/isfeesDataAvailable_API", method = RequestMethod.POST)
						public @ResponseBody String isfeesDataAvailable_API(@RequestBody Map<String, String> data) {
							 String stu_id = data.get("stu_id");
							 String prof  = data.get("prof");
							 String degree  = data.get("degree");
							 String count = upd.CheckFessdataAvlbl(stu_id,prof,degree);
								return count;
						}
						
						@RequestMapping(value = "/getVerifiedStatus_API", method = RequestMethod.POST)
						public @ResponseBody ArrayList<ArrayList<String>> getVerifiedStatus_API(@RequestBody Map<String, String> data,HttpSession Session) {
							 String stu_id = data.get("stu_id");
							 String role  = data.get("stu_id,role");
						 ArrayList<ArrayList<String>> list = upd.getVerifyStatus(stu_id,role);
						 System.err.println("getVerifiedStatus Dao"+list);
							return list;
						}
//						//------pay fee onclick event------
//						@CrossOrigin( origins = "*" )
//						@RequestMapping(value = "/ug_pg_coll_fees_API", method = RequestMethod.POST)
//						public @ResponseBody String ug_pg_coll_fees_API(@RequestBody Map<String, String> data,HttpSession Session,HttpServletRequest request) {
//
//							Session sessionHQL = this.sessionFactory.openSession();
//							Transaction tx = sessionHQL.beginTransaction();
//							EDU_LMS_FEES_COLLECT td = new EDU_LMS_FEES_COLLECT();
//							String msg="";
//							String msg2="";
//							String msg3="";
//							
//							int sid = 0;
//							int ru = 0;
//							String studentId_hid = data.get("studentId");
//							String type_of_degree = data.get("type_of_degree");
//							String degree_name = data.get("degree_name");
//							String term_id = data.get("term_id");
//							String fullorpart = data.get("fullorpart");
//							String no_of_part = data.get("no_of_part");
//							//---get fees detail id
//							String ch_tb_ids = data.get("ch_tb_ids");
//							String userid = data.get("userid");
//							String role = data.get("role");
//							
//							if(fullorpart.equals("2")) {
//								td.setDegree_name(Integer.parseInt(degree_name));
//								td.setType_of_degree(Integer.parseInt(type_of_degree));
//								td.setTerm_id(Integer.parseInt(term_id));
//								td.setStudent_name(Integer.parseInt(studentId_hid));
//								td.setCreated_by(userid);
//								td.setCreated_date(new Date());
//								sid = (int) sessionHQL.save(td);
//								sessionHQL.flush();
//								sessionHQL.clear();}
//							
//							/*
//							 * td.setDegree_name(Integer.parseInt(degree_name));
//							 * td.setType_of_degree(Integer.parseInt(type_of_degree));
//							 * td.setTerm_id(Integer.parseInt(term_id));
//							 * td.setStudent_name(Integer.parseInt(studentId_hid));
//							 * td.setCreated_by(userid); td.setCreated_date(new Date()); sid = (int)
//							 * sessionHQL.save(td); sessionHQL.flush(); sessionHQL.clear();
//							 */
//							
//							String hql="";
//							
//							String[] chidArr = ch_tb_ids.split(",");
//							System.err.println("fullorpart---"+fullorpart);
//							if(fullorpart.equals("1")) {
//								
//								for(int p=0;p<chidArr.length;p++) {
//									
//									td.setDegree_name(Integer.parseInt(degree_name));
//									td.setType_of_degree(Integer.parseInt(type_of_degree));
//									td.setTerm_id(Integer.parseInt(term_id));
//									td.setStudent_name(Integer.parseInt(studentId_hid));
//									td.setCreated_by(userid);
//									td.setCreated_date(new Date());
//									sid = (int) sessionHQL.save(td);
//									sessionHQL.flush();
//									sessionHQL.clear();
//									
//									 hql = "update EDU_LMS_SET_STU_FEES_CHILD set status=:status,modified_by=:modified_by,modified_date=:modified_date\n"
//												+ "	 where id=:id ";
//										 Query query = sessionHQL.createQuery(hql).setParameter("status", 1).setParameter("modified_by", userid).setParameter("modified_date", new Date())
//													.setParameter("id",Integer.parseInt(chidArr[p]));
//										 msg3 = query.executeUpdate() > 0 ? "1" : "0";
//								}
//								
//							}
//							if(fullorpart.equals("2")) {
//								
//								
//								 hql = "update EDU_LMS_SET_STU_FEES_CHILD set status=:status,modified_by=:modified_by,modified_date=:modified_date\n"
//											+ "	 where id=:id ";
//									 Query query = sessionHQL.createQuery(hql).setParameter("status", 1).setParameter("modified_by", userid).setParameter("modified_date", new Date())
//												.setParameter("id",Integer.parseInt(chidArr[0]));
//									 msg3 = query.executeUpdate() > 0 ? "1" : "0";
//								
//							}
//							
//							if(role.contains("NCISM")) {
//								 hql = "update EDU_LMS_STUDENT_DETAILS set verified_status=:verified_status,fee_paid_status=1,modified_by=:modified_by,modified_date=:modified_date\n"
//										+ "	 where id=:id ";
//								 Query query = sessionHQL.createQuery(hql).setParameter("verified_status", 1).setParameter("modified_by", userid).setParameter("modified_date", new Date())
//											.setParameter("id", Integer.parseInt(studentId_hid));
//									msg = query.executeUpdate() > 0 ? "1" : "0";
//								
//							}
//							if(role.contains("NCH")) {
//								 hql = "update EDU_LMS_NCH_STUDENT_DETAILS set verified_status=:verified_status,fee_paid_status=1,modified_by=:modified_by,modified_date=:modified_date\n"
//										+ "	 where id=:id ";
//								 Query query = sessionHQL.createQuery(hql).setParameter("verified_status", 1).setParameter("modified_by", userid).setParameter("modified_date", new Date())
//											.setParameter("id", Integer.parseInt(studentId_hid));
//									msg = query.executeUpdate() > 0 ? "1" : "0";
//							}
//							
//							if(role.contains("ADM")) {
//								List<Map<String, Object>> stu_cur_dtl = upd.GetRoleInfoFromStudentId(role,studentId_hid);
//								
//								String stu_curroled = stu_cur_dtl.get(0).get("role_id").toString();
//								String stu_userid = stu_cur_dtl.get(0).get("userid").toString();
//								String updroleid = "";
//								System.err.println("stu_curroled---"+stu_curroled);
//								if(stu_curroled.equals("45")) {
//									updroleid = "25";
//								}
//								if(stu_curroled.equals("46")) {
//									updroleid = "26";
//								} if(stu_curroled.equals("25")||stu_curroled.equals("26")){
//									
//									updroleid=stu_curroled;
//								}
//								System.err.println("updroleid---"+updroleid);
//								String hql2 = "update UserRole set role_id=:role_id "
//										//+ ",modified_on=:modified_on\n"
//										+ "	 where user_id=:user_id ";
//								
//								Query query2 = sessionHQL.createQuery(hql2).setParameter("role_id", Integer.parseInt(updroleid))
//										//.setParameter("modified_on", new Date())
//										.setParameter("user_id", Integer.parseInt(stu_userid));
//								msg2 = query2.executeUpdate() > 0 ? "1" : "0";
//								
//								if(msg2.equals("1")) {
//									ru = 1;
//								}
//							}
//							
//							if(ru == 0) {
//								System.err.println("msg---"+msg+"sid---"+sid+"msg3---"+msg3);
//								if (msg.equals("1") && sid > 0 && msg3.equals("1")) {
//									msg	= "Payment Done Successfully";
//									tx.commit();
//								} else {
//									msg = "Something Went Wrong !!!";
//								}
//							}
//							
//							if(ru == 1) {
//								System.err.println("msg---"+msg+"msg2---"+msg2+"sid---"+sid+"msg3---"+msg3);
//								if (msg.equals("1") && msg2.equals("1") && sid > 0 && msg3.equals("1")) {
//									msg	= "Payment Done Successfully";
//									tx.commit();
//								} else {
//									msg = "Something Went Wrong !!!";
//								}
//							}
//
//							return msg;
//						}
						
						//------pay fee onclick event------
						@CrossOrigin( origins = "*" )
						@RequestMapping(value = "/ug_pg_coll_fees_API", method = RequestMethod.POST)
						public @ResponseBody String ug_pg_coll_fees_API(@RequestBody Map<String, String> data,HttpSession Session,HttpServletRequest request) {

							Session sessionHQL = this.sessionFactory.openSession();
							Transaction tx = sessionHQL.beginTransaction();
							EDU_LMS_FEES_COLLECT td = new EDU_LMS_FEES_COLLECT();
							String msg="";
							String msg2="";
							String msg3="";
							
							int sid = 0;
							int ru = 0;
							String studentId_hid = data.get("studentId");
							String type_of_degree = data.get("type_of_degree");
							String degree_name = data.get("degree_name");
							String term_id = data.get("term_id");
							String fullorpart = data.get("fullorpart");
							String no_of_part = data.get("no_of_part");
							//---get fees detail id
							String ch_tb_ids = data.get("ch_tb_ids");
							String userid = data.get("userid");
							String role = data.get("role");
							ArrayList<ArrayList<String>> list = upd.Getlastnoofpart(studentId_hid,role);
							
							System.err.println("ug_pg_coll_fees--------------list"+list.get(0).get(1));
							String part_ser = list.get(0).get(1);
							System.err.println("part_ser==============="+part_ser);
							String nop = list.get(0).get(2);
							System.err.println("nop==============="+nop);
							
							System.err.println("fullorpart"+fullorpart);
							
							if(fullorpart.equals("2")) {
								
								td.setDegree_name(Integer.parseInt(degree_name));
								td.setType_of_degree(Integer.parseInt(type_of_degree));
								td.setTerm_id(Integer.parseInt(term_id));
								td.setStudent_name(Integer.parseInt(studentId_hid));
								td.setCreated_by(userid);
								td.setCreated_date(new Date());
								sid = (int) sessionHQL.save(td);
								sessionHQL.flush();
								sessionHQL.clear();
								}
							
							
							
							String hql="";
							
							String[] chidArr = ch_tb_ids.split(",");
							System.err.println("fullorpart---"+fullorpart);
							if(fullorpart.equals("1")) {
								
								for(int p=0;p<chidArr.length;p++) {
									td.setDegree_name(Integer.parseInt(degree_name));
									td.setType_of_degree(Integer.parseInt(type_of_degree));
									td.setTerm_id(Integer.parseInt(term_id));
									td.setStudent_name(Integer.parseInt(studentId_hid));
									td.setCreated_by(userid);
									td.setCreated_date(new Date());
									sid = (int) sessionHQL.save(td);
									sessionHQL.flush();
									sessionHQL.clear();
									
									 hql = "update EDU_LMS_SET_STU_FEES_CHILD set status=:status,modified_by=:modified_by,modified_date=:modified_date\n"
												+ "	 where id=:id ";
										 Query query = sessionHQL.createQuery(hql).setParameter("status", 1).setParameter("modified_by", userid).setParameter("modified_date", new Date())
													.setParameter("id",Integer.parseInt(chidArr[p]));
										 msg3 = query.executeUpdate() > 0 ? "1" : "0";
								}
								
							}
							if(fullorpart.equals("2")) {
								
								 hql = "update EDU_LMS_SET_STU_FEES_CHILD set status=:status,modified_by=:modified_by,modified_date=:modified_date\n"
											+ "	 where id=:id ";
									 Query query = sessionHQL.createQuery(hql).setParameter("status", 1).setParameter("modified_by", userid).setParameter("modified_date", new Date())
												.setParameter("id",Integer.parseInt(chidArr[0]));
									 msg3 = query.executeUpdate() > 0 ? "1" : "0";
								
							}
							
							if(fullorpart.equals("1")) {
							
								if(role.contains("NCISM")) {
									 hql = "update EDU_LMS_STUDENT_DETAILS set verified_status=:verified_status,fee_paid_status=1,modified_by=:modified_by,modified_date=:modified_date\n"
											+ "	 where id=:id ";
									 Query query = sessionHQL.createQuery(hql).setParameter("verified_status", 1).setParameter("modified_by", userid).setParameter("modified_date", new Date())
												.setParameter("id", Integer.parseInt(studentId_hid));
										msg = query.executeUpdate() > 0 ? "1" : "0";
									
								}
								if(role.contains("NCH")) {
									 hql = "update EDU_LMS_NCH_STUDENT_DETAILS set verified_status=:verified_status,fee_paid_status=1,modified_by=:modified_by,modified_date=:modified_date\n"
											+ "	 where id=:id ";
									 Query query = sessionHQL.createQuery(hql).setParameter("verified_status", 1).setParameter("modified_by", userid).setParameter("modified_date", new Date())
												.setParameter("id", Integer.parseInt(studentId_hid));
										msg = query.executeUpdate() > 0 ? "1" : "0";
								}
							} else if(fullorpart.equals("2")) {
//								if(part_ser.equals(nop)) {
									if(role.contains("NCISM")) {
										 hql = "update EDU_LMS_STUDENT_DETAILS set modified_by=:modified_by,modified_date=:modified_date\n"
												+ "	 where id=:id ";
										 Query query = sessionHQL.createQuery(hql).setParameter("modified_by", userid).setParameter("modified_date", new Date())
													.setParameter("id", Integer.parseInt(studentId_hid));
											msg = query.executeUpdate() > 0 ? "1" : "0";
										
									}
									if(role.contains("NCH")) {
										 hql = "update EDU_LMS_NCH_STUDENT_DETAILS set modified_by=:modified_by,modified_date=:modified_date\n"
												+ "	 where id=:id ";
										 Query query = sessionHQL.createQuery(hql).setParameter("modified_by", userid).setParameter("modified_date", new Date())
													.setParameter("id", Integer.parseInt(studentId_hid));
											msg = query.executeUpdate() > 0 ? "1" : "0";
									}
//								}
									if(part_ser.equals(nop)) {
										if(role.contains("NCISM")) {
											 hql = "update EDU_LMS_STUDENT_DETAILS set verified_status=:verified_status,fee_paid_status=1,modified_by=:modified_by,modified_date=:modified_date\n"
													+ "	 where id=:id ";
											 Query query = sessionHQL.createQuery(hql).setParameter("verified_status", 1).setParameter("modified_by", userid).setParameter("modified_date", new Date())
														.setParameter("id", Integer.parseInt(studentId_hid));
												msg = query.executeUpdate() > 0 ? "1" : "0";
											
										}
										if(role.contains("NCH")) {
											 hql = "update EDU_LMS_NCH_STUDENT_DETAILS set verified_status=:verified_status,fee_paid_status=1,modified_by=:modified_by,modified_date=:modified_date\n"
													+ "	 where id=:id ";
											 Query query = sessionHQL.createQuery(hql).setParameter("verified_status", 1).setParameter("modified_by", userid).setParameter("modified_date", new Date())
														.setParameter("id", Integer.parseInt(studentId_hid));
												msg = query.executeUpdate() > 0 ? "1" : "0";
										}
									}
							}
							
							if(ru == 0) {
								System.err.println("msg---"+msg+"sid---"+sid+"msg3---"+msg3);
								if (msg.equals("1") && sid > 0 && msg3.equals("1")) {
									msg	= "Payment Done Successfully";
									tx.commit();
								} else {
									msg = "Something Went Wrong !!!";
								}
							}
							
							if(ru == 1) {
								System.err.println("msg---"+msg+"msg2---"+msg2+"sid---"+sid+"msg3---"+msg3);
								if (msg.equals("1") && msg2.equals("1") && sid > 0 && msg3.equals("1")) {
									msg	= "Payment Done Successfully";
									tx.commit();
								} else {
									msg = "Something Went Wrong !!!";
								}
							}

							return msg;
						}
					
	//-----------------FEE--------- API---END------
					
					
					
					
					//-----------------ADMISSION MNGT--------- API---START------(REGISTRATION)--------	
						
						//-----Gender List----
						@CrossOrigin( origins = "*" )
						 @RequestMapping(value = "/getgenderList_API", method = RequestMethod.POST)
							public @ResponseBody List<EDU_LMS_GENDER_MSTR> getgenderList_API(HttpServletRequest request) throws ParseException {
							
							 
							 Session sessionHQL = sessionFactory.openSession();
								Transaction tx1 = sessionHQL.beginTransaction();
								try {
									Query q1 = sessionHQL.createQuery(
											"select id, gender_name from EDU_LMS_GENDER_MSTR where status='1' order by id");
									List<EDU_LMS_GENDER_MSTR> list = (List<EDU_LMS_GENDER_MSTR>) q1.list();
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
							
						//----Category List----
						@CrossOrigin( origins = "*" )
						@RequestMapping(value = "/getcategorylist_API", method = RequestMethod.GET)
						public @ResponseBody List<EDU_LMS_CATEGORY_MSTR>  getcategorylist_API() throws Exception{
							
							
							List<EDU_LMS_CATEGORY_MSTR>  list =  common.getcategoryList(sessionFactory);
							 System.out.println("getBesicNcism_detailslist---"+list);
								return list;

						}
						
						//----Religion List----
						@CrossOrigin( origins = "*" )
						@RequestMapping(value = "/getreligionListdata_API", method = RequestMethod.GET)
						public @ResponseBody List<TB_LMS_RELIGION_MSTR>  getreligionListdata_API() throws Exception{
							
							
							List<TB_LMS_RELIGION_MSTR>  list =  common.getreligionList(sessionFactory);
							 System.out.println("getBesicNcism_detailslist---"+list);
								return list;

						}
						
						//----Merital Status List----
						@CrossOrigin( origins = "*" )
						@RequestMapping(value = "/getmsList_API", method = RequestMethod.GET)
						public @ResponseBody List<TB_LMS_MARITAL_STATUS_MSTR>  getmsList_API() throws Exception{							
							
							List<TB_LMS_MARITAL_STATUS_MSTR>  list =  common.getmaritalstatusList(sessionFactory);
							 System.out.println("getBesicNcism_detailslist---"+list);
								return list;

						}
						
						
						//----Prefix List------
						@CrossOrigin( origins = "*" )
						@RequestMapping(value = "/getPrefixList_API", method = RequestMethod.GET)
						public @ResponseBody List<TB_NCH_PREFIX_MSTR> getPrefixList_API() {
//							Session sessionHQL = sessionFactory.openSession();
//							Transaction tx = sessionHQL.beginTransaction();
//							Query q = sessionHQL.createQuery("from TB_NCH_PREFIX_MSTR where status = 1 order by id ");
//							@SuppressWarnings("unchecked")
//							List<TB_NCH_PREFIX_MSTR> clist = (List<TB_NCH_PREFIX_MSTR>) q.list();
//							tx.commit();
//							sessionHQL.close();
//
//							return clist;
//							
							return common.getPrefixList(sessionFactory);
						}
						
						
						//----getQuotaList ------
						/*@CrossOrigin( origins = "*" )
						@RequestMapping(value = "/getQuotaList_API", method = RequestMethod.GET)
						public @ResponseBody List<EDU_LMS_QUOTA_MSTR> getQuotaList_API(SessionFactory sessionFactory) {
							Session sessionHQL = sessionFactory.openSession();
							Transaction tx = sessionHQL.beginTransaction();
							Query q = sessionHQL.createQuery("from EDU_LMS_QUOTA_MSTR where status=1 order by id ");

							@SuppressWarnings("unchecked")
							List<EDU_LMS_QUOTA_MSTR> list = (List<EDU_LMS_QUOTA_MSTR>) q.list();
							System.err.println("common==============="+list);
							tx.commit();
							sessionHQL.close();
							return list;
						}*/
						//----getCounselingAuthoList ------
						/*@CrossOrigin( origins = "*" )
						@RequestMapping(value = "/getCounselingAuthoList_API", method = RequestMethod.GET)
						public @ResponseBody List<EDU_LMS_COUNSELING_AUTHORITY_MSTR> getCounselingAuthoList_API(HttpServletRequest request) {
							Session sessionHQL = sessionFactory.openSession();
							Transaction tx = sessionHQL.beginTransaction();
							Query q = sessionHQL.createQuery("from EDU_LMS_COUNSELING_AUTHORITY_MSTR where status=1 order by id ");

							@SuppressWarnings("unchecked")
							List<EDU_LMS_COUNSELING_AUTHORITY_MSTR> list = (List<EDU_LMS_COUNSELING_AUTHORITY_MSTR>) q.list();
							System.err.println("common==============="+list);
							tx.commit();
							sessionHQL.close();
							return list;
						}*/
					
						//----- country list----
						 @CrossOrigin( origins = "*" )
						 @RequestMapping(value = "/getMedCountryName_API", method = RequestMethod.POST)
							public @ResponseBody List<TB_COUNTRY> getMedCountryName_API(HttpServletRequest request) throws ParseException {
							
							 
							 Session sessionHQL = sessionFactory.openSession();
								Transaction tx1 = sessionHQL.beginTransaction();
								try {
									Query q1 = sessionHQL.createQuery(
											"select distinct id,name from TB_COUNTRY where status='1' order  by name");
									List<TB_COUNTRY> list = (List<TB_COUNTRY>) q1.list();
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
					
							//-----state list----
							//getMedStateName_API() this method is already in this page 
							
							//----district List----
							//getMedDistrictName_API() this method is already in this page 
							
							// ---state list from country-----
							 @CrossOrigin( origins = "*" )
							@RequestMapping(value = "/getStateUrl_API", method = RequestMethod.POST)
							public @ResponseBody List<TB_STATE> getStateUrl_API(@RequestBody Map<String, String> data) {

								String country_id = data.get("country_id");
								
								List<TB_STATE> list = IRdao.getcountrylistUrl(Integer.parseInt(country_id));

								return list;
							}

							//----district list from state----
							 @CrossOrigin( origins = "*" )
							@RequestMapping(value = "/getDistrictUrl_API", method = RequestMethod.POST)
							public @ResponseBody List<EDU_LMS_DISTRICT_MSTR> getDistrictUrl_API(@RequestBody Map<String, String> data) {

								String state_id = data.get("state_id");
								
								List<EDU_LMS_DISTRICT_MSTR> list = IRdao.getStatelistUrl(Integer.parseInt(state_id));

								return list;
							}
							
							
							//----Name of Exam List----
							@CrossOrigin( origins = "*" )
							@RequestMapping(value = "/getAcademicList_API", method = RequestMethod.GET)
							public @ResponseBody List<EDU_ACADEMIC_MSTR>  getAcademicList_API() throws Exception{
								
								
								List<EDU_ACADEMIC_MSTR>  list =  common.getEducationAcademicList(sessionFactory);
								 System.out.println("getBesicNcism_detailslist---"+list);
									return list;

							}
							
							//----Document name List----
							@CrossOrigin( origins = "*" )
							@RequestMapping(value = "/getDoc_nameList_API", method = RequestMethod.GET)
							public @ResponseBody List<T_Domain_Value>  getDoc_nameList_API() throws Exception{
								
								Session sessionHQL = sessionFactory.openSession();
								Transaction tx = sessionHQL.beginTransaction();
								Query q = sessionHQL.createQuery("from T_Domain_Value  where domainid='DOC_NAME' order by label ");
								@SuppressWarnings("unchecked")
								List<T_Domain_Value> clist = (List<T_Domain_Value>) q.list();
								tx.commit();
								System.err.println("clist----"+clist);
								sessionHQL.close();
								return clist;

							}
							
							//----NCH Document name List----
							@CrossOrigin( origins = "*" )
							@RequestMapping(value = "/getDoc_nameListNCH_API", method = RequestMethod.GET)
							public @ResponseBody List<Map<String, Object>>  getDoc_nameListNCH_API() throws Exception{
								
								String screenurl = "Personal_Details_Url";
								List<Map<String, Object>>  list = cdao.getDocumentAtchmantlistbyscreen_url(screenurl);
								 System.out.println("getBesicNcism_detailslist---"+list);
									return list;

							}
							
							
							//----PG Document name List----
							@CrossOrigin( origins = "*" )
							@RequestMapping(value = "/getDoc_nameListPG_API", method = RequestMethod.GET)
							public @ResponseBody List<String>  getDoc_nameListPG_API() throws Exception{

								
								List<String>  list = common.getDoc_name_pgList(sessionFactory);
								 System.out.println("getBesicNcism_detailslist---"+list);
									return list;

							}
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getNcism_Besicdetails_ctrl_API", method = RequestMethod.POST)
					public @ResponseBody List<ArrayList<String>>  getNcism_Besicdetails_ctrl_API(@RequestBody Map<String, String> data) throws Exception{
						
						String userid = data.get("userid");
						
						List<ArrayList<String>>  list = da.getBesicNcism_details(Integer.parseInt(userid));
						 System.out.println("getBesicNcism_detailslist---"+list);
							return list;

					}
					
					//---NCH basic Details---
					@RequestMapping(value = "/getBesicdetails_ctrl_API", method = RequestMethod.POST)
					public @ResponseBody ArrayList<ArrayList<String>> getBesicdetails_ctrl_API(@RequestBody Map<String, String> data, HttpSession session) {
						
						String userid = data.get("userid");
						ArrayList<ArrayList<String>> list = daNCH.getBesicdetails(Integer.parseInt(userid));
						return list;
					}

					
					//---PG basic Details---
					@RequestMapping(value = "/getBesicdetailsPG_ctrl_API", method = RequestMethod.POST)
					public @ResponseBody ArrayList<ArrayList<String>> getBesicdetailsPG_ctrl_API(@RequestBody Map<String, String> data, HttpSession session) {
						
						String userid = data.get("userid");
						String roleid = data.get("roleid");
						
						 String staff_lvl = cdao.getStaffLvlfromRoleid(roleid);
						ArrayList<ArrayList<String>> list = daPG.getBesicdetails_pg(Integer.parseInt(userid),staff_lvl);
						return list;
					}

				
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getAllNcism_Persdetails_API", method = RequestMethod.POST)
					public @ResponseBody List<ArrayList<String>>  getAllNcism_Persdetails_API(@RequestBody Map<String, String> data) throws Exception{
						
						String userid = data.get("userid");
						
						List<ArrayList<String>>  list =da.getAllNcism_Persdetails(Integer.parseInt(userid));// da.getBesicNcism_details(Integer.parseInt(userid));
						 System.out.println("getAllNcism_PersdetailsList---"+list);
							return list;

					}
					
					//---NCH Personal all Details---
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getAllNch_Persdetails_API", method = RequestMethod.POST)
					public @ResponseBody List<ArrayList<String>>  getAllNch_Persdetails_API(@RequestBody Map<String, String> data) throws Exception{
						
						String userid = data.get("userid");
						
						List<ArrayList<String>>  list =daNCH.getAllPersdetails(Integer.parseInt(userid));// da.getBesicNcism_details(Integer.parseInt(userid));
						 System.out.println("getAllNcism_PersdetailsList---"+list);
							return list;

					}
					
					//---PG Personal all Details---
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getAllPG_Persdetails_API", method = RequestMethod.POST)
					public @ResponseBody ArrayList<ArrayList<String>>  getAllPG_Persdetails_API(@RequestBody Map<String, String> data,HttpSession session) throws Exception{
						
						String userid = data.get("userid");
						
						ArrayList<ArrayList<String>>  list =daPG.getPersonaldetails_pg(Integer.parseInt(userid), session);// da.getBesicNcism_details(Integer.parseInt(userid));
						 System.out.println("getAllNcism_PersdetailsList---"+list);
							return list;

					}
					



					//-------personal details save------
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/personal_details_Ncism_Action_API", method = RequestMethod.POST)
					public @ResponseBody JSONObject personal_details_Ncism_Action_API(@RequestBody Map<String, String> data,
							 HttpServletRequest request, HttpSession session) throws ParseException {
						JSONObject JSON =  new JSONObject();
						EDU_NCISM_REG_GRADU_PERSONAL_DTLS td = new EDU_NCISM_REG_GRADU_PERSONAL_DTLS();
						int id = td.getId() > 0 ? td.getId() : 0;
						Date date = new Date();
						System.err.println("NCISMhh---");
						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
						
						DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
						try {
							String userId = data.get("userId");
							String username = data.get("username");
							String pers_name = data.get("pers_name");
							String pers_father_name = data.get("pers_father_name");
							String pers_mother_name = data.get("pers_mother_name");
							String pers_date_of_birth =  data.get("pers_date_of_birth");
							String cand_prifix = data.get("cand_prifix");
							String pers_middel_name = data.get("pers_middel_name");							
							String pers_surname = data.get("pers_surname");
							Date dob_i = null;
							System.err.println("data.get(\"pers_date_of_birth\")---1"+data.get("pers_date_of_birth"));
							if (!data.get("pers_date_of_birth").equals("")) {
								
								dob_i = formatter1.parse(data.get("pers_date_of_birth"));
								System.err.println("dob_i---2"+dob_i);
							} else {
								
								dob_i = null;
								System.err.println("dob_i---3"+dob_i);
							}
							System.err.println("dob_i---4"+dob_i);
							// String yrr	= data.get("yrr");
							String pers_gender = data.get("pers_gender");
							String pers_mob_no = data.get("pers_mob_no");
							String pers_email = data.get("pers_email");
							
							String pers_category = data.get("pers_category");
							String pers_religion = data.get("pers_religion");
							String pers_marital_status = data.get("pers_marital_status");
							String pers_nationality = data.get("pers_nationality");
							String state_id = data.get("state_id");
							String district_id = data.get("district_id");
							String village = data.get("village");
							//String pers_aadhar_no1 = data.get("pers_aadhar_no1");
							//String pers_aadhar_no2 = data.get("pers_aadhar_no2");
							//String pers_aadhar_no3 = data.get("pers_aadhar_no3");
							String pers_aadhar_no = data.get("pers_aadhar_no");//pers_aadhar_no1+pers_aadhar_no2+pers_aadhar_no3;
							
							String quota_id =  data.get("quota_id");
							String counselling_authority =  data.get("counselling_authority");
							
							String pers_permanent_house_no = data.get("pers_permanent_house_no");
							String pers_permanent_village = data.get("pers_permanent_village");
							String pers_permanent_postoffice = data.get("pers_permanent_postoffice");
							String pers_permanent_tehsil = data.get("pers_permanent_tehsil");
							String pers_permanent_policestation = data.get("pers_permanent_policestation");
							String pers_permanent_district = data.get("pers_permanent_district");
							String pers_permanent_state = data.get("pers_permanent_state");
							String pers_permanent_pincode = data.get("pers_permanent_pincode");
							String pers_permanent_lendmark = data.get("pers_permanent_lendmark");
							
							String pers_present_house_no = data.get("pers_present_house_no");
							String pers_present_village = data.get("pers_present_village");
							String pers_present_postoffice = data.get("pers_present_postoffice");
							String pers_present_tehsil = data.get("pers_present_tehsil");
							String pers_present_policestation = data.get("pers_present_policestation");
							String pers_present_district = data.get("pers_present_district");
							String pers_present_state = data.get("pers_present_state");
							String pers_present_pincode = data.get("pers_present_pincode");
							String pers_present_lendmark = data.get("pers_present_lendmark");
							String p_id = userId;
							
							
							
							String neet_roll_no = data.get("neet_roll_no");
							String neet_application_no = data.get("neet_application_no");
							
							String neet_rank = data.get("neet_rank");
							String neet_marks = data.get("neet_marks");
							String neet_percentile = data.get("neet_percentile");
							
							
							String corre_house_no = data.get("corre_house_no");
							String corre_village = data.get("corre_village");
							String corre_postoffice = data.get("corre_postoffice");
							String corre_tehsil = data.get("corre_tehsil");
							String corre_policestation = data.get("corre_policestation");
							String corre_district = data.get("corre_district");
							String corre_state = data.get("corre_state");
							String corre_pincode = data.get("corre_pincode");
							String corre_lendmark = data.get("corre_lendmark");
							
							
							
							//String hiddenUpdate = data.get("hiddenUpdate");

							//String pers_father_title = data.get("pers_father_title");
							//String pers_mother_title = data.get("pers_mother_title");

							        td.setCreated_by(username);
									td.setCreated_date(date);
									td.setPers_name(pers_name);
									td.setPers_father_name(pers_father_name);
									td.setPers_mother_name(pers_mother_name);
									td.setPers_date_of_birth(dob_i);
									td.setPers_gender(pers_gender);
									td.setPers_mob_no(pers_mob_no);
									td.setPers_email(pers_email);
									td.setPers_category(pers_category);
									td.setPers_religion(pers_religion);
									td.setPers_marital_status(Integer.parseInt(pers_marital_status));
									td.setPers_nationality(Integer.parseInt(pers_nationality));
									td.setState_id(Integer.parseInt(state_id));
									td.setDistrict_id(Integer.parseInt(district_id));
									td.setVillage(village);
									td.setPers_aadhar_no(pers_aadhar_no);
									td.setQuota_id(Integer.parseInt(quota_id));
									td.setCounselling_authority(Integer.parseInt(counselling_authority));
									
									td.setPers_permanent_house_no(pers_permanent_house_no);
									td.setPers_permanent_village(pers_permanent_village);
									td.setPers_permanent_postoffice(pers_permanent_postoffice);
									td.setPers_permanent_tehsil(pers_permanent_tehsil);
									td.setPers_permanent_policestation(pers_permanent_policestation);
									td.setPers_permanent_district(pers_permanent_district);
									td.setPers_permanent_state(Integer.parseInt(pers_permanent_state));
									td.setPers_permanent_pincode(Integer.parseInt(pers_permanent_pincode));
									td.setPers_permanent_lendmark(pers_permanent_lendmark);
									td.setPers_present_house_no(pers_present_house_no);
									td.setPers_present_village(pers_present_village);
									td.setPers_present_postoffice(pers_present_postoffice);
									td.setPers_present_tehsil(pers_present_tehsil);
									td.setPers_present_policestation(pers_present_policestation);
									td.setPers_present_district(pers_present_district);
									td.setPers_present_state(Integer.parseInt(pers_present_state));
									td.setPers_present_pincode(Integer.parseInt(pers_present_pincode));
									td.setPers_present_lendmark(pers_present_lendmark);
									td.setP_id(Integer.parseInt(userId));
									td.setNeet_roll_no(neet_roll_no);
									td.setNeet_application_no(neet_application_no);
									td.setNeet_marks(Integer.parseInt(neet_marks));
									td.setNeet_rank(Integer.parseInt(neet_rank));
									td.setNeet_percentile(neet_percentile);
									td.setPers_middel_name(pers_middel_name);
									td.setPers_surname(pers_surname);
								    td.setCand_prifix(Integer.parseInt(cand_prifix));	
								    
									td.setCorre_house_no(corre_house_no);
									td.setCorre_village(corre_village);
									td.setCorre_postoffice(corre_postoffice);
									td.setCorre_tehsil(corre_tehsil);
									td.setCorre_policestation(corre_policestation);
									td.setCorre_district(corre_district);
									td.setCorre_state(Integer.parseInt(corre_state));
									td.setCorre_pincode(Integer.parseInt(corre_pincode));
									td.setCorre_lendmark(corre_lendmark);
									td.setStatus(Integer.parseInt("0"));
								    
//								    td.setPers_father_title(Integer.parseInt(pers_father_title));
//								    td.setPers_mother_title(Integer.parseInt(pers_mother_title));
									//int reg_id = (int)
									int reg_id=(int) sessionHQL.save(td);
									sessionHQL.flush();
									sessionHQL.clear();
									
									//Query q2 = sessionHQL.createQuery("select userId from UserLogin Where userName=:userName").setParameter("userName", username);
									//@SuppressWarnings("unchecked")
									//List<Integer> list2 = (List<Integer>) q2.list();				
								//	int lid = list2.get(0);
									//String reg_id_u_msg = da.UpdateRegid(reg_id,lid);
								 
							     	tx.commit();
									
									//if(reg_id_u_msg.equals("Data Updated Successfully.")) {
									
										//ra.addAttribute("msg", "Data Saved Successfully.");
									//}
										
										if (reg_id > 0) {
											JSON.put("msg", "Data Saved Successfully.");
											JSON.put("eid", reg_id);
										}
										
									
									} catch (RuntimeException e) {
										try {
											JSON.put("msg", "roll back transaction");
										} catch (RuntimeException rbe) {
											JSON.put("msg", "Couldn't roll back transaction " + rbe);
										}
										throw e;
									} finally {
										if (sessionHQL != null) {
											sessionHQL.close();
										}
									}
								return JSON;
								}
					
					
					//---- NCH personal Details save----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/personal_details_NCH_Action_API", method = RequestMethod.POST)
					public @ResponseBody JSONObject personal_details_NCH_Action_API(@RequestBody Map<String, String> data,
							 HttpServletRequest request, HttpSession session) throws ParseException {
						JSONObject JSON =  new JSONObject();
						TB_PERSONAL_DETAILS td = new TB_PERSONAL_DETAILS();
						int id = td.getId() > 0 ? td.getId() : 0;
						Date date = new Date();
							System.err.println("NCHhh---");
						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
						
						DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
						try {
							String userId = data.get("userId");
							String username = data.get("username");
							String pers_name = data.get("pers_name");
							String pers_father_name = data.get("pers_father_name");
							String pers_mother_name = data.get("pers_mother_name");
							String pers_date_of_birth =  data.get("pers_date_of_birth");
							String cand_prifix = data.get("cand_prifix");
							String pers_middel_name = data.get("pers_middel_name");							
							String pers_surname = data.get("pers_surname");
							Date dob_i = null;
							System.err.println("data.get(\"pers_date_of_birth\")---1"+data.get("pers_date_of_birth"));
							if (!data.get("pers_date_of_birth").equals("")) {
								
								dob_i = formatter1.parse(data.get("pers_date_of_birth"));
								System.err.println("dob_i---2"+dob_i);
							} else {
								
								dob_i = null;
								System.err.println("dob_i---3"+dob_i);
							}
							System.err.println("dob_i---4"+dob_i);
							// String yrr	= data.get("yrr");
							String pers_gender = data.get("pers_gender");
							String pers_mob_no = data.get("pers_mob_no");
							String pers_email = data.get("pers_email");
							
							String pers_category = data.get("pers_category");
							String pers_religion = data.get("pers_religion");
							String pers_marital_status = data.get("pers_marital_status");
							String pers_nationality = data.get("pers_nationality");
							String state_id = data.get("state_id");
							String district_id = data.get("district_id");
							String village = data.get("village");
							//String pers_aadhar_no1 = data.get("pers_aadhar_no1");
							//String pers_aadhar_no2 = data.get("pers_aadhar_no2");
							//String pers_aadhar_no3 = data.get("pers_aadhar_no3");
							String pers_aadhar_no = data.get("pers_aadhar_no");//pers_aadhar_no1+pers_aadhar_no2+pers_aadhar_no3;
							
							String quota_id =  data.get("quota_id");
							String counselling_authority =  data.get("counselling_authority");
							
							String pers_permanent_house_no = data.get("pers_permanent_house_no");
							String pers_permanent_village = data.get("pers_permanent_village");
							String pers_permanent_postoffice = data.get("pers_permanent_postoffice");
							String pers_permanent_tehsil = data.get("pers_permanent_tehsil");
							String pers_permanent_policestation = data.get("pers_permanent_policestation");
							String pers_permanent_district = data.get("pers_permanent_district");
							String pers_permanent_state = data.get("pers_permanent_state");
							String pers_permanent_pincode = data.get("pers_permanent_pincode");
							String pers_permanent_lendmark = data.get("pers_permanent_lendmark");
							
							String pers_present_house_no = data.get("pers_present_house_no");
							String pers_present_village = data.get("pers_present_village");
							String pers_present_postoffice = data.get("pers_present_postoffice");
							String pers_present_tehsil = data.get("pers_present_tehsil");
							String pers_present_policestation = data.get("pers_present_policestation");
							String pers_present_district = data.get("pers_present_district");
							String pers_present_state = data.get("pers_present_state");
							String pers_present_pincode = data.get("pers_present_pincode");
							String pers_present_lendmark = data.get("pers_present_lendmark");
							String p_id = userId;
							
							
							
							String neet_roll_no = data.get("neet_roll_no");
							String neet_application_no = data.get("neet_application_no");
							
							String neet_rank = data.get("neet_rank");
							String neet_marks = data.get("neet_marks");
							String neet_percentile = data.get("neet_percentile");
							
							
							String corre_house_no = data.get("corre_house_no");
							String corre_village = data.get("corre_village");
							String corre_postoffice = data.get("corre_postoffice");
							String corre_tehsil = data.get("corre_tehsil");
							String corre_policestation = data.get("corre_policestation");
							String corre_district = data.get("corre_district");
							String corre_state = data.get("corre_state");
							String corre_pincode = data.get("corre_pincode");
							String corre_lendmark = data.get("corre_lendmark");
							
							
							
							//String hiddenUpdate = data.get("hiddenUpdate");

							//String pers_father_title = data.get("pers_father_title");
							//String pers_mother_title = data.get("pers_mother_title");

							        td.setCreated_by(username);
									td.setCreated_date(date);
									td.setPers_name(pers_name);
									td.setPers_father_name(pers_father_name);
									td.setPers_mother_name(pers_mother_name);
									td.setPers_date_of_birth(dob_i);
									td.setPers_gender(pers_gender);
									td.setPers_mob_no(pers_mob_no);
									td.setPers_email(pers_email);
									td.setPers_category(pers_category);
									td.setPers_religion(pers_religion);
									td.setPers_marital_status(Integer.parseInt(pers_marital_status));
									td.setPers_nationality(Integer.parseInt(pers_nationality));
									td.setState_id(Integer.parseInt(state_id));
									td.setDistrict_id(Integer.parseInt(district_id));
									td.setVillage(village);
									td.setPers_aadhar_no(pers_aadhar_no);
									
									td.setQuota_id(Integer.parseInt(quota_id));
									td.setCounselling_authority(Integer.parseInt(counselling_authority));
									
									td.setPers_permanent_house_no(pers_permanent_house_no);
									td.setPers_permanent_village(pers_permanent_village);
									td.setPers_permanent_postoffice(pers_permanent_postoffice);
									td.setPers_permanent_tehsil(pers_permanent_tehsil);
									td.setPers_permanent_policestation(pers_permanent_policestation);
									td.setPers_permanent_district(pers_permanent_district);
									td.setPers_permanent_state(Integer.parseInt(pers_permanent_state));
									td.setPers_permanent_pincode(Integer.parseInt(pers_permanent_pincode));
									td.setPers_permanent_lendmark(pers_permanent_lendmark);
									td.setPers_present_house_no(pers_present_house_no);
									td.setPers_present_village(pers_present_village);
									td.setPers_present_postoffice(pers_present_postoffice);
									td.setPers_present_tehsil(pers_present_tehsil);
									td.setPers_present_policestation(pers_present_policestation);
									td.setPers_present_district(pers_present_district);
									td.setPers_present_state(Integer.parseInt(pers_present_state));
									td.setPers_present_pincode(Integer.parseInt(pers_present_pincode));
									td.setPers_present_lendmark(pers_present_lendmark);
									td.setP_id(Integer.parseInt(userId));
									td.setNeet_roll_no(neet_roll_no);
									td.setNeet_application_no(neet_application_no);
									td.setNeet_marks(Integer.parseInt(neet_marks));
									td.setNeet_rank(Integer.parseInt(neet_rank));
									td.setNeet_percentile(neet_percentile);
									td.setPers_middel_name(pers_middel_name);
									td.setPers_surname(pers_surname);
								    td.setCand_prifix(Integer.parseInt(cand_prifix));	
								    
									td.setCorre_house_no(corre_house_no);
									td.setCorre_village(corre_village);
									td.setCorre_postoffice(corre_postoffice);
									td.setCorre_tehsil(corre_tehsil);
									td.setCorre_policestation(corre_policestation);
									td.setCorre_district(corre_district);
									td.setCorre_state(Integer.parseInt(corre_state));
									td.setCorre_pincode(Integer.parseInt(corre_pincode));
									td.setCorre_lendmark(corre_lendmark);
									td.setStatus(Integer.parseInt("0"));
								    
//								    td.setPers_father_title(Integer.parseInt(pers_father_title));
//								    td.setPers_mother_title(Integer.parseInt(pers_mother_title));
									//int reg_id = (int)
									int reg_id=(int) sessionHQL.save(td);
									sessionHQL.flush();
									sessionHQL.clear();
									
									//Query q2 = sessionHQL.createQuery("select userId from UserLogin Where userName=:userName").setParameter("userName", username);
									//@SuppressWarnings("unchecked")
									//List<Integer> list2 = (List<Integer>) q2.list();				
								//	int lid = list2.get(0);
									//String reg_id_u_msg = da.UpdateRegid(reg_id,lid);
								 
							     	tx.commit();
									
									//if(reg_id_u_msg.equals("Data Updated Successfully.")) {
									
										//ra.addAttribute("msg", "Data Saved Successfully.");
									//}
										
										if (reg_id > 0) {
											JSON.put("msg", "Data Saved Successfully.");
											JSON.put("eid", reg_id);
										}
										
									
									} catch (RuntimeException e) {
										try {
											JSON.put("msg", "roll back transaction");
										} catch (RuntimeException rbe) {
											JSON.put("msg", "Couldn't roll back transaction " + rbe);
										}
										throw e;
									} finally {
										if (sessionHQL != null) {
											sessionHQL.close();
										}
									}
								return JSON;
								}
					
					//---- PG personal Details save----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/personal_details_PG_Action_API", method = RequestMethod.POST)
					public @ResponseBody JSONObject personal_details_PG_Action_API(@RequestBody Map<String, String> data,
							 HttpServletRequest request, HttpSession session) throws ParseException {
						JSONObject JSON =  new JSONObject();
						EDU_PG_REG_PERSONAL_DETAILS td = new EDU_PG_REG_PERSONAL_DETAILS();
						int id = td.getId() > 0 ? td.getId() : 0;
						Date date = new Date();
							System.err.println("NCHhh---");
						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
						
						DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
						try {

							String userId = data.get("userId");
							String username = data.get("username");
							String first_name = data.get("first_name");
							String middel_name = data.get("middel_name");
							String surname = data.get("surname");
							String father_name = data.get("father_name");
							String mother_name = data.get("mother_name");
							String gender = data.get("gender");
							String date_of_birth =  data.get("date_of_birth");
							Date dob_i = null;
							if (!data.get("date_of_birth").equals("")) {
								dob_i = formatter1.parse(data.get("date_of_birth"));
							} else {
								dob_i = null;
							}
							 String yrr	= data.get("yrr");
							
							String mob_no = data.get("mob_no");
							String email = data.get("email");
							
							String category = data.get("category");
							String religion = data.get("religion");
							String marital_status = data.get("marital_status");
							String nationality = data.get("nationality");
							
//							String state_id = data.get("state_id");
//							String district_id = data.get("district_id");
//							String village = data.get("village");
							
							//String aadhar_no1 = data.get("aadhar_no1");
							//String aadhar_no2 = data.get("aadhar_no2");
							//String aadhar_no3 = data.get("aadhar_no3");
							//String aadhar_no = aadhar_no1+aadhar_no2+aadhar_no3;
							
							String aadhar_no = data.get("aadhar_no");
							
							String permanent_house_no = data.get("permanent_house_no");
							String permanent_add_line1 = data.get("permanent_add_line1");
							String permanent_add_line2 = data.get("permanent_add_line2");
							String permanent_state = data.get("permanent_state");
							String permanent_district = data.get("permanent_district");
							String permanent_village = data.get("permanent_village");
							String permanent_pincode = data.get("permanent_pincode");
							String permanent_lendmark = data.get("permanent_lendmark");
							
							String present_house_no = data.get("present_house_no");
							String present_add_line1 = data.get("present_add_line1");
							String present_add_line2 = data.get("present_add_line2");
							String present_state = data.get("present_state");
							String present_district = data.get("present_district");
							String present_village = data.get("present_village");
							String present_pincode = data.get("present_pincode");
							String present_lendmark = data.get("present_lendmark");
							//String p_id = userId;
							
							
							String corre_house_no = data.get("corre_house_no");
							String corre_add_line1 = data.get("corre_add_line1");
							String corre_add_line2 = data.get("corre_add_line2");
							String corre_state = data.get("corre_state");
							String corre_district = data.get("corre_district");
							String corre_village = data.get("corre_village");
							String corre_pincode = data.get("corre_pincode");
							String corre_lendmark = data.get("corre_lendmark");

							td.setCreated_by(username);
							td.setCreated_date(date);
							td.setFirst_name(first_name);
							td.setMiddel_name(middel_name);
							td.setSurname(surname);
							td.setFather_name(father_name);
							td.setMother_name(mother_name);
							td.setDate_of_birth(dob_i);
							td.setGender(Integer.parseInt(gender));
							td.setMob_no(mob_no);
							td.setEmail(email);
							td.setCategory(Integer.parseInt(category));
							td.setReligion(Integer.parseInt(religion));
							td.setMarital_status(Integer.parseInt(marital_status));
							td.setNationality(Integer.parseInt(nationality));
							td.setAadhar_no(aadhar_no);
							
							td.setPermanent_house_no(permanent_house_no);
							td.setPermanent_add_line1(permanent_add_line1);
							td.setPermanent_add_line2(permanent_add_line2);
							td.setPermanent_state(Integer.parseInt(permanent_state));
							td.setPermanent_district(Integer.parseInt(permanent_district));
							td.setPermanent_village(permanent_village);
							td.setPermanent_pincode(Integer.parseInt(permanent_pincode));
							td.setPermanent_lendmark(permanent_lendmark);
							
							td.setPresent_house_no(present_house_no);
							td.setPresent_add_line1(present_add_line1);
							td.setPresent_add_line2(present_add_line2);
							td.setPresent_state(Integer.parseInt(present_state));
							td.setPresent_district(Integer.parseInt(present_district));
							td.setPresent_village(present_village);
							td.setPresent_pincode(Integer.parseInt(present_pincode));
							td.setPresent_lendmark(present_lendmark);
							
							td.setP_id(Integer.parseInt(userId));
							
							td.setCorre_house_no(corre_house_no);
							td.setCorre_add_line1(corre_add_line1);
							td.setCorre_add_line2(corre_add_line2);
							td.setCorre_state(Integer.parseInt(corre_state));
							td.setCorre_district(corre_district);
							td.setCorre_village(corre_village);
							td.setCorre_pincode(Integer.parseInt(corre_pincode));
							td.setCorre_lendmark(corre_lendmark);
							td.setStatus(Integer.parseInt("0"));
								    
//								    td.setPers_father_title(Integer.parseInt(pers_father_title));
//								    td.setPers_mother_title(Integer.parseInt(pers_mother_title));
									//int reg_id = (int)
									int reg_id=(int) sessionHQL.save(td);
									sessionHQL.flush();
									sessionHQL.clear();
									
									//Query q2 = sessionHQL.createQuery("select userId from UserLogin Where userName=:userName").setParameter("userName", username);
									//@SuppressWarnings("unchecked")
									//List<Integer> list2 = (List<Integer>) q2.list();				
								//	int lid = list2.get(0);
									//String reg_id_u_msg = da.UpdateRegid(reg_id,lid);
								 
							     	tx.commit();
									
									//if(reg_id_u_msg.equals("Data Updated Successfully.")) {
									
										//ra.addAttribute("msg", "Data Saved Successfully.");
									//}
										
										if (reg_id > 0) {
											JSON.put("msg", "Data Saved Successfully.");
											JSON.put("eid", reg_id);
										}
										
									
									} catch (RuntimeException e) {
										try {
											JSON.put("msg", "roll back transaction");
										} catch (RuntimeException rbe) {
											JSON.put("msg", "Couldn't roll back transaction " + rbe);
										}
										throw e;
									} finally {
										if (sessionHQL != null) {
											sessionHQL.close();
										}
									}
								return JSON;
								}
					
					//----getQuotaList ------
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getQuotaList_API", method = RequestMethod.GET)
					public @ResponseBody List<EDU_LMS_QUOTA_MSTR> getQuotaList_API(HttpServletRequest request) {
						Session sessionHQL = sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
						Query q = sessionHQL.createQuery("from EDU_LMS_QUOTA_MSTR where status=1 order by id ");

						@SuppressWarnings("unchecked")
						List<EDU_LMS_QUOTA_MSTR> list = (List<EDU_LMS_QUOTA_MSTR>) q.list();
						System.err.println("common==============="+list);
						tx.commit();
						sessionHQL.close();
						return list;
					}
					//----getCounselingAuthoList ------
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getCounselingAuthoList_API", method = RequestMethod.GET)
					public @ResponseBody List<EDU_LMS_COUNSELING_AUTHORITY_MSTR> getCounselingAuthoList_API(HttpServletRequest request) {
						Session sessionHQL = sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
						Query q = sessionHQL.createQuery("from EDU_LMS_COUNSELING_AUTHORITY_MSTR where status=1 order by id ");

						@SuppressWarnings("unchecked")
						List<EDU_LMS_COUNSELING_AUTHORITY_MSTR> list = (List<EDU_LMS_COUNSELING_AUTHORITY_MSTR>) q.list();
						System.err.println("common==============="+list);
						tx.commit();
						sessionHQL.close();
						return list;
					}
					//-----Update Personal Details------
					@CrossOrigin( origins = "*" )
				     @RequestMapping(value = "/getUpdateNcism_PerDetails_API", method = RequestMethod.POST)
					 	public @ResponseBody String getUpdateNcism_PerDetails_API(@RequestBody Map<String, String> data,HttpServletRequest request, Principal principal) throws ParseException {
				    	
						String msg ="";
				    	 String username = data.get("username");
				    	 Date date = new Date();
				    	 Session sessionHQL = this.sessionFactory.openSession();
				    	 DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
				    	 
				    	 String e_id = data.get("e_id");
				    	 String pers_name = data.get("pers_name");
				    	 String pers_father_name = data.get("pers_father_name");
				    	 String pers_mother_name = data.get("pers_mother_name");
				    	 String pers_date_of_birth	= data.get("pers_date_of_birth");
				    	 
				    	 Date dob_i = null;
							if (!data.get("pers_date_of_birth").equals("")) {
								dob_i = formatter1.parse(data.get("pers_date_of_birth"));
							} else {
								dob_i = null;
							}
						// String yrr	= data.get("yrr");
							
				    	 String pers_gender = data.get("pers_gender");
				    	 String pers_mob_no = data.get("pers_mob_no");
				    	 String pers_email = data.get("pers_email");
				    	 String pers_category = data.get("pers_category");
				    	 String pers_religion = data.get("pers_religion");
				    	 String pers_marital_status = data.get("pers_marital_status");
				    	 String pers_nationality = data.get("pers_nationality");
				    	 String state_id = data.get("state_id");
				    	 String district_id = data.get("district_id");
				    	 String village = data.get("village");
				    	 
				    	 
				    	 //	String pers_aadhar_no1 = data.get("pers_aadhar_no1");
							//String pers_aadhar_no2 = data.get("pers_aadhar_no2");
						//	String pers_aadhar_no3 = data.get("pers_aadhar_no3");
								    	 
				    	 String quota_id =  data.get("quota_id");
				    	 String counselling_authority =  data.get("counselling_authority");
						 String pers_aadhar_no = data.get("pers_aadhar_no");
				    	 String pers_permanent_house_no = data.get("pers_permanent_house_no");
				    	 String pers_permanent_village = data.get("pers_permanent_village");
				    	 String pers_permanent_postoffice = data.get("pers_permanent_postoffice");
				    	 String pers_permanent_tehsil = data.get("pers_permanent_tehsil");
				    	 String pers_permanent_policestation = data.get("pers_permanent_policestation");
				    	 String pers_permanent_district = data.get("pers_permanent_district");
				    	 String pers_permanent_state = data.get("pers_permanent_state");
				    	 String pers_permanent_pincode = data.get("pers_permanent_pincode");
				    	 String pers_permanent_lendmark = data.get("pers_permanent_lendmark");
				    	 String pers_present_house_no = data.get("pers_present_house_no");
				    	 String pers_present_village = data.get("pers_present_village");
				    	 String pers_present_postoffice = data.get("pers_present_postoffice");
				    	 String pers_present_tehsil = data.get("pers_present_tehsil");
				    	 String pers_present_policestation = data.get("pers_present_policestation");
				    	 String pers_present_district = data.get("pers_present_district");
				    	 String pers_present_state = data.get("pers_present_state");
				    	 String pers_present_pincode = data.get("pers_present_pincode");
				    	 String pers_present_lendmark = data.get("pers_present_lendmark");
				    	 
				    	    String cand_prifix = data.get("cand_prifix");
							String pers_father_title = data.get("pers_father_title");
							String pers_mother_title = data.get("pers_mother_title");
							
							
							String neet_roll_no = data.get("neet_roll_no");
							String neet_application_no = data.get("neet_application_no");
							String neet_rank = data.get("neet_rank");
							String neet_marks = data.get("neet_marks");
							String neet_percentile = data.get("neet_percentile");
							
							 String corre_house_no = data.get("corre_house_no");
					    	 String corre_village = data.get("corre_village");
					    	 String corre_postoffice = data.get("corre_postoffice");
					    	 String corre_tehsil = data.get("corre_tehsil");
					    	 String corre_policestation = data.get("corre_policestation");
					    	 String corre_district = data.get("corre_district");
					    	 String corre_state = data.get("corre_state");
					    	 String corre_pincode = data.get("corre_pincode");
					    	 String corre_lendmark = data.get("corre_lendmark");
							
							String pers_middel_name = data.get("pers_middel_name");
							String pers_surname = data.get("pers_surname");
						
							
							//String pers_aadhar_no = pers_aadhar_no1+pers_aadhar_no2+pers_aadhar_no3;

				    	 EDU_NCISM_REG_GRADU_PERSONAL_DTLS pda= (EDU_NCISM_REG_GRADU_PERSONAL_DTLS)sessionHQL.get(EDU_NCISM_REG_GRADU_PERSONAL_DTLS.class, Integer.parseInt(e_id));
				    	 
				    	    pda.setPers_name(pers_name);
							pda.setPers_father_name(pers_father_name);
							pda.setPers_mother_name(pers_mother_name);
							pda.setPers_date_of_birth(dob_i);
							pda.setPers_gender(pers_gender);
							pda.setPers_mob_no(pers_mob_no);
							pda.setPers_email(pers_email);
							pda.setPers_category(pers_category);
							pda.setPers_religion(pers_religion);
							pda.setPers_marital_status(Integer.parseInt(pers_marital_status));
							pda.setPers_nationality(Integer.parseInt(pers_nationality));
							pda.setState_id(Integer.parseInt(state_id));
							pda.setDistrict_id(Integer.parseInt(district_id));
							pda.setVillage(village);
							pda.setPers_aadhar_no(pers_aadhar_no);
							pda.setQuota_id(Integer.parseInt(quota_id));
							pda.setCounselling_authority(Integer.parseInt(counselling_authority));
							
							pda.setPers_permanent_house_no(pers_permanent_house_no);
							pda.setPers_permanent_village(pers_permanent_village);
							pda.setPers_permanent_postoffice(pers_permanent_postoffice);
							pda.setPers_permanent_tehsil(pers_permanent_tehsil);
							pda.setPers_permanent_policestation(pers_permanent_policestation);
							pda.setPers_permanent_district(pers_permanent_district);
							pda.setPers_permanent_state(Integer.parseInt(pers_permanent_state));
							pda.setPers_permanent_pincode(Integer.parseInt(pers_permanent_pincode));
							pda.setPers_permanent_lendmark(pers_permanent_lendmark);
							pda.setPers_present_house_no(pers_present_house_no);
							pda.setPers_present_village(pers_present_village);
							pda.setPers_present_postoffice(pers_present_postoffice);
							pda.setPers_present_tehsil(pers_present_tehsil);
							pda.setPers_present_policestation(pers_present_policestation);
							pda.setPers_present_district(pers_present_district);
							pda.setPers_present_state(Integer.parseInt(pers_present_state));
							pda.setPers_present_pincode(Integer.parseInt(pers_present_pincode));
							pda.setPers_present_lendmark(pers_present_lendmark);
							
							
							pda.setCorre_house_no(corre_house_no);
							pda.setCorre_village(corre_village);
							pda.setCorre_postoffice(corre_postoffice);
							pda.setCorre_tehsil(corre_tehsil);
							pda.setCorre_policestation(corre_policestation);
							pda.setCorre_district(corre_district);
							pda.setCorre_state(Integer.parseInt(corre_state));
							pda.setCorre_pincode(Integer.parseInt(corre_pincode));
							pda.setCorre_lendmark(corre_lendmark);
							
							pda.setNeet_roll_no(neet_roll_no);
							pda.setNeet_application_no(neet_application_no);
							
							pda.setNeet_marks(Integer.parseInt(neet_marks));
							pda.setNeet_rank(Integer.parseInt(neet_rank));
							pda.setNeet_percentile(neet_percentile);
							pda.setPers_middel_name(pers_middel_name);
							pda.setPers_surname(pers_surname);
							
							pda.setCand_prifix(Integer.parseInt(cand_prifix));	
//							pda.setPers_father_title(Integer.parseInt(pers_father_title));
//							pda.setPers_mother_title(Integer.parseInt(pers_mother_title));
							
							pda.setModified_by(username);
							pda.setModified_date(date);
				    	 
					    	msg = da.getUpdateNcism_PerDetails(pda);
					    	sessionHQL.close();
					    	return msg;
					    	
					 	}
					
					//-----NCH Update Personal Details------
					@CrossOrigin( origins = "*" )
				     @RequestMapping(value = "/getUpdateNch_PerDetails_API", method = RequestMethod.POST)
					 	public @ResponseBody String getUpdateNch_PerDetails_API(@RequestBody Map<String, String> data,HttpServletRequest request, Principal principal) throws ParseException {
				    	
						String msg ="";
				    	 String username = data.get("username");
				    	 Date date = new Date();
				    	 Session sessionHQL = this.sessionFactory.openSession();
				    	 DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
				    	 
				    	 String e_id = data.get("e_id");
				    	 String pers_name = data.get("pers_name");
				    	 String pers_father_name = data.get("pers_father_name");
				    	 String pers_mother_name = data.get("pers_mother_name");
				    	 String pers_date_of_birth	= data.get("pers_date_of_birth");
				    	 
				    	 Date dob_i = null;
							if (!data.get("pers_date_of_birth").equals("")) {
								dob_i = formatter1.parse(data.get("pers_date_of_birth"));
							} else {
								dob_i = null;
							}
						// String yrr	= data.get("yrr");
							
				    	 String pers_gender = data.get("pers_gender");
				    	 String pers_mob_no = data.get("pers_mob_no");
				    	 String pers_email = data.get("pers_email");
				    	 String pers_category = data.get("pers_category");
				    	 String pers_religion = data.get("pers_religion");
				    	 String pers_marital_status = data.get("pers_marital_status");
				    	 String pers_nationality = data.get("pers_nationality");
				    	 String state_id = data.get("state_id");
				    	 String district_id = data.get("district_id");
				    	 String village = data.get("village");
				    	 
				    	 
				    	 //	String pers_aadhar_no1 = data.get("pers_aadhar_no1");
							//String pers_aadhar_no2 = data.get("pers_aadhar_no2");
						//	String pers_aadhar_no3 = data.get("pers_aadhar_no3");
								    	 
						 String pers_aadhar_no = data.get("pers_aadhar_no");
						 String quota_id = data.get("quota_id");
				    	 String counselling_authority =  data.get("counselling_authority");
				    	 System.out.println("quotaid----"+quota_id);
				    	 System.out.println("counselling_authority----"+counselling_authority);
				    	 String pers_permanent_house_no = data.get("pers_permanent_house_no");
				    	 String pers_permanent_village = data.get("pers_permanent_village");
				    	 String pers_permanent_postoffice = data.get("pers_permanent_postoffice");
				    	 String pers_permanent_tehsil = data.get("pers_permanent_tehsil");
				    	 String pers_permanent_policestation = data.get("pers_permanent_policestation");
				    	 String pers_permanent_district = data.get("pers_permanent_district");
				    	 String pers_permanent_state = data.get("pers_permanent_state");
				    	 String pers_permanent_pincode = data.get("pers_permanent_pincode");
				    	 String pers_permanent_lendmark = data.get("pers_permanent_lendmark");
				    	 String pers_present_house_no = data.get("pers_present_house_no");
				    	 String pers_present_village = data.get("pers_present_village");
				    	 String pers_present_postoffice = data.get("pers_present_postoffice");
				    	 String pers_present_tehsil = data.get("pers_present_tehsil");
				    	 String pers_present_policestation = data.get("pers_present_policestation");
				    	 String pers_present_district = data.get("pers_present_district");
				    	 String pers_present_state = data.get("pers_present_state");
				    	 String pers_present_pincode = data.get("pers_present_pincode");
				    	 String pers_present_lendmark = data.get("pers_present_lendmark");
				    	 
				    	    String cand_prifix = data.get("cand_prifix");
							String pers_father_title = data.get("pers_father_title");
							String pers_mother_title = data.get("pers_mother_title");
							
							
							String neet_roll_no = data.get("neet_roll_no");
							String neet_application_no = data.get("neet_application_no");
							String neet_rank = data.get("neet_rank");
							String neet_marks = data.get("neet_marks");
							String neet_percentile = data.get("neet_percentile");
							
							 String corre_house_no = data.get("corre_house_no");
					    	 String corre_village = data.get("corre_village");
					    	 String corre_postoffice = data.get("corre_postoffice");
					    	 String corre_tehsil = data.get("corre_tehsil");
					    	 String corre_policestation = data.get("corre_policestation");
					    	 String corre_district = data.get("corre_district");
					    	 String corre_state = data.get("corre_state");
					    	 String corre_pincode = data.get("corre_pincode");
					    	 String corre_lendmark = data.get("corre_lendmark");
							
							String pers_middel_name = data.get("pers_middel_name");
							String pers_surname = data.get("pers_surname");
						
							
							//String pers_aadhar_no = pers_aadhar_no1+pers_aadhar_no2+pers_aadhar_no3;

							TB_PERSONAL_DETAILS pda= (TB_PERSONAL_DETAILS)sessionHQL.get(TB_PERSONAL_DETAILS.class, Integer.parseInt(e_id));
				    	 
				    	    pda.setPers_name(pers_name);
							pda.setPers_father_name(pers_father_name);
							pda.setPers_mother_name(pers_mother_name);
							pda.setPers_date_of_birth(dob_i);
							pda.setPers_gender(pers_gender);
							pda.setPers_mob_no(pers_mob_no);
							pda.setPers_email(pers_email);
							pda.setPers_category(pers_category);
							pda.setPers_religion(pers_religion);
							pda.setPers_marital_status(Integer.parseInt(pers_marital_status));
							pda.setPers_nationality(Integer.parseInt(pers_nationality));
							pda.setState_id(Integer.parseInt(state_id));
							pda.setDistrict_id(Integer.parseInt(district_id));
							pda.setVillage(village);
							pda.setPers_aadhar_no(pers_aadhar_no);
							pda.setQuota_id(Integer.parseInt(quota_id));
							pda.setCounselling_authority(Integer.parseInt(counselling_authority));
							
							pda.setPers_permanent_house_no(pers_permanent_house_no);
							pda.setPers_permanent_village(pers_permanent_village);
							pda.setPers_permanent_postoffice(pers_permanent_postoffice);
							pda.setPers_permanent_tehsil(pers_permanent_tehsil);
							pda.setPers_permanent_policestation(pers_permanent_policestation);
							pda.setPers_permanent_district(pers_permanent_district);
							pda.setPers_permanent_state(Integer.parseInt(pers_permanent_state));
							pda.setPers_permanent_pincode(Integer.parseInt(pers_permanent_pincode));
							pda.setPers_permanent_lendmark(pers_permanent_lendmark);
							pda.setPers_present_house_no(pers_present_house_no);
							pda.setPers_present_village(pers_present_village);
							pda.setPers_present_postoffice(pers_present_postoffice);
							pda.setPers_present_tehsil(pers_present_tehsil);
							pda.setPers_present_policestation(pers_present_policestation);
							pda.setPers_present_district(pers_present_district);
							pda.setPers_present_state(Integer.parseInt(pers_present_state));
							pda.setPers_present_pincode(Integer.parseInt(pers_present_pincode));
							pda.setPers_present_lendmark(pers_present_lendmark);
							
							
							pda.setCorre_house_no(corre_house_no);
							pda.setCorre_village(corre_village);
							pda.setCorre_postoffice(corre_postoffice);
							pda.setCorre_tehsil(corre_tehsil);
							pda.setCorre_policestation(corre_policestation);
							pda.setCorre_district(corre_district);
							pda.setCorre_state(Integer.parseInt(corre_state));
							pda.setCorre_pincode(Integer.parseInt(corre_pincode));
							pda.setCorre_lendmark(corre_lendmark);
							
							pda.setNeet_roll_no(neet_roll_no);
							pda.setNeet_application_no(neet_application_no);
							
							pda.setNeet_marks(Integer.parseInt(neet_marks));
							pda.setNeet_rank(Integer.parseInt(neet_rank));
							pda.setNeet_percentile(neet_percentile);
							pda.setPers_middel_name(pers_middel_name);
							pda.setPers_surname(pers_surname);
							
							pda.setCand_prifix(Integer.parseInt(cand_prifix));	
//							pda.setPers_father_title(Integer.parseInt(pers_father_title));
//							pda.setPers_mother_title(Integer.parseInt(pers_mother_title));
							
							pda.setModified_by(username);
							pda.setModified_date(date);
				    	 
					    	msg = daNCH.getUpdatePerDetails(pda);
					    	sessionHQL.close();
					    	return msg;
					    	
					 	}
					
					//-----PG Update Personal Details------
					@CrossOrigin( origins = "*" )
				     @RequestMapping(value = "/getUpdatePG_PerDetails_API", method = RequestMethod.POST)
					 	public @ResponseBody String getUpdatePG_PerDetails_API(@RequestBody Map<String, String> data) throws ParseException {
				    	
						String msg ="";
				    	 String username = data.get("username");
				    	 Date date = new Date();
				    	 Session sessionHQL = this.sessionFactory.openSession();
				    	 DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
				    	 String first_name = data.get("first_name");
				    	 String father_name = data.get("father_name");
				    	 String mother_name = data.get("mother_name");
				    	 String date_of_birth	= data.get("date_of_birth");
				    	 
				    	 Date dob_i = null;
							if (!data.get("date_of_birth").equals("")) {
								dob_i = formatter1.parse(data.get("date_of_birth"));
							} else {
								dob_i = null;
							}
							
						 String yrr	= data.get("yrr");
				    	 String gender = data.get("gender");
				    	 String mob_no = data.get("mob_no");
				    	 
				    	 String email = data.get("email");
				    	 String category = data.get("category");
				    	 String religion = data.get("religion");
				    	 String marital_status = data.get("marital_status");
				    	 String nationality = data.get("nationality");
//				    	 String state_id = data.get("state_id");
//				    	 String district_id = data.get("district_id");
//				    	 String village = data.get("village");
				    	 
				    	 	//String aadhar_no1 = data.get("aadhar_no1");
							//String aadhar_no2 = data.get("aadhar_no2");
							//String aadhar_no3 = data.get("aadhar_no3");
								    	 
						 String aadhar_no = data.get("aadhar_no");
				    	 String permanent_house_no = data.get("permanent_house_no");
				    		String permanent_add_line1 = data.get("permanent_add_line1");
							String permanent_add_line2 = data.get("permanent_add_line2");
				    	 
				    	 String permanent_village = data.get("permanent_village");
//				    	 String permanent_postoffice = data.get("permanent_postoffice");
//				    	 String permanent_tehsil = data.get("permanent_tehsil");
//				    	 String permanent_policestation = data.get("permanent_policestation");
				    	 String permanent_district = data.get("permanent_district");
				    	 String permanent_state = data.get("permanent_state");
				    	 String permanent_pincode = data.get("permanent_pincode");
				    	 String permanent_lendmark = data.get("permanent_lendmark");
				    	 
				    	 String present_house_no = data.get("present_house_no");
				    		String present_add_line1 = data.get("present_add_line1");
							String present_add_line2 = data.get("present_add_line2");
				    	 
				    	 String present_village = data.get("present_village");
				    	 String present_district = data.get("present_district");
				    	 String present_state = data.get("present_state");
				    	 String present_pincode = data.get("present_pincode");
				    	 String present_lendmark = data.get("present_lendmark");
				    	 
//				    	    String cand_prifix = data.get("cand_prifix");
//							String father_title = data.get("father_title");
//							String mother_title = data.get("mother_title");
							
							
//							String neet_roll_no = data.get("neet_roll_no");
//							String neet_application_no = data.get("neet_application_no");
//							String neet_rank = data.get("neet_rank");
//							String neet_marks = data.get("neet_marks");
//							String neet_percentile = data.get("neet_percentile");
				    	 
				    	 
				    	 String corre_house_no = data.get("corre_house_no");
				    	 String corre_add_line1 = data.get("corre_add_line1");
				    	 String corre_add_line2 = data.get("corre_add_line2");
				    	 String corre_state = data.get("corre_state");
				    	 String corre_district = data.get("corre_district");
				    	 String corre_village = data.get("corre_village");
				    	 String corre_pincode = data.get("corre_pincode");
				    	 String corre_lendmark = data.get("corre_lendmark");
							
							String middel_name = data.get("middel_name");
							String surname = data.get("surname");
							String e_id = data.get("e_id");
							
							//String pers_aadhar_no = pers_aadhar_no1+pers_aadhar_no2+pers_aadhar_no3;

							EDU_PG_REG_PERSONAL_DETAILS pda= (EDU_PG_REG_PERSONAL_DETAILS)sessionHQL.get(EDU_PG_REG_PERSONAL_DETAILS.class, Integer.parseInt(e_id));
					    	 
				    	    pda.setFirst_name(first_name);
				    		pda.setMiddel_name(middel_name);
							pda.setSurname(surname);
							pda.setFather_name(father_name);
							pda.setMother_name(mother_name);
							pda.setDate_of_birth(dob_i);
							pda.setGender(Integer.parseInt(gender));
							pda.setMob_no(mob_no);
							pda.setEmail(email);
							pda.setCategory(Integer.parseInt(category));
							pda.setReligion(Integer.parseInt(religion));
							pda.setMarital_status(Integer.parseInt(marital_status));
							pda.setNationality(Integer.parseInt(nationality));

							pda.setAadhar_no(aadhar_no);
							pda.setPermanent_house_no(permanent_house_no);
							pda.setPermanent_add_line1(permanent_add_line1);
							pda.setPermanent_add_line2(permanent_add_line2);
							pda.setPermanent_state(Integer.parseInt(permanent_state));
							pda.setPermanent_district(Integer.parseInt(permanent_district));
							pda.setPermanent_village(permanent_village);
							pda.setPermanent_pincode(Integer.parseInt(permanent_pincode));
							pda.setPermanent_lendmark(permanent_lendmark);
							
							pda.setPresent_house_no(present_house_no);
							pda.setPresent_add_line1(present_add_line1);
							pda.setPresent_add_line2(present_add_line2);
							pda.setPresent_state(Integer.parseInt(present_state));
							pda.setPresent_district(Integer.parseInt(present_district));
							pda.setPresent_village(present_village);
							pda.setPresent_pincode(Integer.parseInt(present_pincode));
							pda.setPresent_lendmark(present_lendmark);
							
							pda.setCorre_house_no(corre_house_no);
							pda.setCorre_add_line1(corre_add_line1);
							pda.setCorre_add_line2(corre_add_line2);
							pda.setCorre_state(Integer.parseInt(corre_state));
							pda.setCorre_district(corre_district);
							pda.setCorre_village(corre_village);
							pda.setCorre_pincode(Integer.parseInt(corre_pincode));
							pda.setCorre_lendmark(corre_lendmark);

							pda.setModified_by(username);
							pda.setModified_date(date);
				    	 
					    	msg = daPG.getUpdatePerDetails_pg(pda);
					    	sessionHQL.close();
					    	return msg;
					    	
					 	}
					
					//------Educational Details Save------
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/Edu_Det_Ncism_Action_API", method = RequestMethod.POST)
					public @ResponseBody JSONObject Edu_Det_Ncism_Action_API(MultipartFile mul,String academic,String passing_year,
							String obtain_marks,String grade,String board_or_university,String school_or_collage,String subject,
							String doc_path_hid,
							String p_id,String id_org,String username,HttpServletRequest request, HttpSession session) throws IOException {

						JSONObject JSON =  new JSONObject();
						EDU_NCISM_REG_GRADU_PRE_EDU_DTLS_TBL ed = new EDU_NCISM_REG_GRADU_PRE_EDU_DTLS_TBL();
						int id = ed.getId() > 0 ? ed.getId() : 0;
						System.err.println("school_or_collage----"+school_or_collage);
						Date date = new Date();
						int id1 = Integer.parseInt(id_org);
						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
						DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
						
						
							String extension=""; //add line
							String fname = ""; //add line
							
							//request.getSession().setAttribute("doc_path", "/srv"+ File.separator + "Document");
							
							//MultipartFile file = mul.getFile("doc_path");
							
//							if (file.getOriginalFilename().isEmpty()) {
//								ra.addAttribute("msg", "Please Upload File");
//								return new ModelAndView("redirect:Edu_Det_Ncism_Url");
//							}
							if(mul != null) {
							if (!mul.getOriginalFilename().isEmpty()) {
								
								byte[] bytes = mul.getBytes();
								String  mnhFilePath = "/srv"+ File.separator + "Document";
								
					            File dir = new File(mnhFilePath);
								if (!dir.exists())
									dir.mkdirs();
								String filename = mul.getOriginalFilename();
								System.out.println("dir "+!dir.exists());		
								int j = filename.lastIndexOf('.');
								if (j >= 0) {
									extension = filename.substring(j+1);
								}
								java.util.Date date1= new java.util.Date();
								fname = dir.getAbsolutePath() + File.separator + (new Timestamp(date1.getTime())).toString().replace(":", "").toString().replace(".", ".").toString().replace(" ","").toString().replace("-","").toString()+ "."+extension;
								
								File serverFile = new File(fname);	               
								BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
								stream.write(bytes);	                
								stream.close();
								ed.setDoc_path(fname);
							}}else {
								ed.setDoc_path(doc_path_hid);
							}
							
							try {
								
								if (Integer.parseInt(academic) > 12) {
									Query qry = sessionHQL.createQuery(
											"select count(id) from EDU_NCISM_REG_GRADU_PRE_EDU_DTLS_TBL where academic=:academic and p_id=:p_id");
									qry.setParameter("academic", "12");
									qry.setParameter("p_id", Integer.parseInt(p_id));
									Long v = (Long) qry.uniqueResult();

									if (v > 0) {
										Query qrypy = sessionHQL.createQuery(
												"select passing_year from EDU_NCISM_REG_GRADU_PRE_EDU_DTLS_TBL where academic=:academic and p_id=:p_id");
										qrypy.setParameter("academic", "12");
										qrypy.setParameter("p_id", Integer.parseInt(p_id));
										List<EDU_NCISM_REG_GRADU_PRE_EDU_DTLS_TBL> x = (List<EDU_NCISM_REG_GRADU_PRE_EDU_DTLS_TBL>) qrypy.list();
										String py = String.valueOf(x.get(0));

										if ((Integer.parseInt(passing_year) - Integer.parseInt(py)) < 2) {
											JSON.put("msg", "Please Enter the Passing Year graterthen 2 year");
											//return new ModelAndView("redirect:Edu_Det_Ncism_Url");
										}
									}
								}
								
							if (id1 == 0) {
							

								Query q0 = sessionHQL.createQuery(
										"select count(id) from EDU_NCISM_REG_GRADU_PRE_EDU_DTLS_TBL where upper(academic)=:academic and p_id=:p_id"
//										+ "and passing_year=:passing_year \n"
//										+ "and upper(institute_name)=:institute_name \n"
//												+ "and obtain_marks=:obtain_marks "
//												+ "and total_marks=:total_marks "
//												+ "and upper(grade)=:grade "
										);
								
								q0.setParameter("academic", academic.toUpperCase());
								q0.setParameter("p_id", Integer.parseInt(p_id));
								//q0.setParameter("passing_year", (Integer.parseInt(passing_year)));
								//q0.setParameter("institute_name", institute_name.toUpperCase());
							//	q0.setParameter("obtain_marks", (Integer.parseInt(obtain_marks)));
//								q0.setParameter("total_marks", (Integer.parseInt(total_marks)));
								//q0.setParameter("grade", grade.toUpperCase());
								
								Long c = (Long) q0.uniqueResult();
								
								ed.setCreated_by(username);
								ed.setCreated_date(date);
									if (c == 0) {
										
										ed.setAcademic(academic.toUpperCase());
										ed.setPassing_year(Integer.parseInt(passing_year));
										ed.setBoard_or_university(board_or_university);
										ed.setSchool_or_collage(school_or_collage);
										ed.setSubject(subject);
										
										//ed.setInstitute_name(institute_name);
										//ed.setTotal_marks(Integer.parseInt(data.get("total_marks")));
										ed.setObtain_marks(obtain_marks);
										ed.setGrade(grade);
										ed.setP_id(Integer.parseInt(p_id));
										sessionHQL.save(ed);
										sessionHQL.flush();
										sessionHQL.clear();
										JSON.put("msg", "Data Saved Successfully.");
								}else {
									JSON.put("msg", "Data already Exist.");
								}
							}else {
								ed.setAcademic(academic.toUpperCase());
								ed.setPassing_year(Integer.parseInt(passing_year));
								ed.setBoard_or_university(board_or_university);
								ed.setSchool_or_collage(school_or_collage);
								ed.setSubject(subject);
								
								//ed.setInstitute_name(institute_name);
								//ed.setTotal_marks(Integer.parseInt(data.get("total_marks")));
								ed.setObtain_marks(obtain_marks);
								ed.setGrade(grade);
								ed.setP_id(Integer.parseInt(p_id));
									ed.setModified_by(username);
									ed.setModified_date(date);
									ed.setId(id1);
									String msg = edao1.updateNcism_SubCategory(ed);
									if (msg == "Data Updated Successfully") {
										JSON.put("msg", msg);
									//	model.put("msg", msg);
									} else {
										JSON.put("msg", msg);
									}
							}
									tx.commit();
								//	ra.addAttribute("msg", "Data Saved Successfully.");
							
						} catch (RuntimeException e) {
							try {
								JSON.put("msg", "roll back transaction");
							} catch (RuntimeException rbe) {
								JSON.put("msg", "Couldn�t roll back transaction " + rbe);
							}
							throw e;
						} finally {
							if (sessionHQL != null) {
								sessionHQL.close();
							}
						}
						return JSON;
					}
					
					//------NCH Educational Details Save------
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/Edu_Det_Nch_Action_API", method = RequestMethod.POST)
					public @ResponseBody JSONObject Edu_Det_Nch_Action_API(MultipartFile mul,String academic,String passing_year,
							String obtain_marks,String grade,String board_or_university,String school_or_collage,String subject,
							String doc_path_hid,
							String p_id,String id_org,String username,HttpServletRequest request, HttpSession session) throws IOException {

						JSONObject JSON =  new JSONObject();
						TB_PRE_EDUCATION_DETAILS ed = new TB_PRE_EDUCATION_DETAILS();
						int id = ed.getId() > 0 ? ed.getId() : 0;
						System.err.println("school_or_collage----"+school_or_collage);
						Date date = new Date();
						int id1 = Integer.parseInt(id_org);
						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
						DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
						
						
							String extension=""; //add line
							String fname = ""; //add line
							
							//request.getSession().setAttribute("doc_path", "/srv"+ File.separator + "Document");
							
							//MultipartFile file = mul.getFile("doc_path");
							
//							if (file.getOriginalFilename().isEmpty()) {
//								ra.addAttribute("msg", "Please Upload File");
//								return new ModelAndView("redirect:Edu_Det_Ncism_Url");
//							}
							if(mul != null) {
							if (!mul.getOriginalFilename().isEmpty()) {
								
								byte[] bytes = mul.getBytes();
								String  mnhFilePath = "/srv"+ File.separator + "Document";
								
					            File dir = new File(mnhFilePath);
								if (!dir.exists())
									dir.mkdirs();
								String filename = mul.getOriginalFilename();
								System.out.println("dir "+!dir.exists());		
								int j = filename.lastIndexOf('.');
								if (j >= 0) {
									extension = filename.substring(j+1);
								}
								java.util.Date date1= new java.util.Date();
								fname = dir.getAbsolutePath() + File.separator + (new Timestamp(date1.getTime())).toString().replace(":", "").toString().replace(".", ".").toString().replace(" ","").toString().replace("-","").toString()+ "."+extension;
								
								File serverFile = new File(fname);	               
								BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
								stream.write(bytes);	                
								stream.close();
								ed.setDoc_path(fname);
							}}else {
								ed.setDoc_path(doc_path_hid);
							}
							
							try {
							
								
								if (Integer.parseInt(academic) > 12) {
									Query qry = sessionHQL.createQuery(
											"select count(id) from TB_PRE_EDUCATION_DETAILS where academic=:academic and p_id=:p_id");
									qry.setParameter("academic", "12");
									qry.setParameter("p_id", Integer.parseInt(p_id));
									Long v = (Long) qry.uniqueResult();

									if (v > 0) {
										Query qrypy = sessionHQL.createQuery(
												"select passing_year from TB_PRE_EDUCATION_DETAILS where academic=:academic and p_id=:p_id");
										qrypy.setParameter("academic", "12");
										qrypy.setParameter("p_id", Integer.parseInt(p_id));
										List<TB_PRE_EDUCATION_DETAILS> x = (List<TB_PRE_EDUCATION_DETAILS>) qrypy.list();
										String py = String.valueOf(x.get(0));

										if ((Integer.parseInt(passing_year) - Integer.parseInt(py)) < 2) {
											JSON.put("msg", "Please Enter the Passing Year graterthen 2 year");
											//return new ModelAndView("redirect:Edu_Det_Ncism_Url");
										}
									}
								}
								
							if (id1 == 0) {
							

								Query q0 = sessionHQL.createQuery(
										"select count(id) from TB_PRE_EDUCATION_DETAILS where upper(academic)=:academic and p_id=:p_id"
										//+ " and passing_year=:passing_year \n"
//										+ "and upper(institute_name)=:institute_name \n"
//												+ "and obtain_marks=:obtain_marks "
//												+ "and total_marks=:total_marks "
//												+ "and upper(grade)=:grade "
										);
								
								q0.setParameter("academic", academic.toUpperCase());
								q0.setParameter("p_id", Integer.parseInt(p_id));
								//q0.setParameter("passing_year", (Integer.parseInt(passing_year)));
								//q0.setParameter("institute_name", institute_name.toUpperCase());
							//	q0.setParameter("obtain_marks", (Integer.parseInt(obtain_marks)));
//								q0.setParameter("total_marks", (Integer.parseInt(total_marks)));
								//q0.setParameter("grade", grade.toUpperCase());
								
								Long c = (Long) q0.uniqueResult();
								
								ed.setCreated_by(username);
								ed.setCreated_date(date);
									if (c == 0) {
										
										ed.setAcademic(academic.toUpperCase());
										ed.setPassing_year(Integer.parseInt(passing_year));
										ed.setBoard_or_university(board_or_university);
										ed.setSchool_or_collage(school_or_collage);
										ed.setSubject(subject);
										
										//ed.setInstitute_name(institute_name);
										//ed.setTotal_marks(Integer.parseInt(data.get("total_marks")));
										ed.setObtain_marks(obtain_marks);
										ed.setGrade(grade);
										ed.setP_id(Integer.parseInt(p_id));
										sessionHQL.save(ed);
										sessionHQL.flush();
										sessionHQL.clear();
										JSON.put("msg", "Data Saved Successfully.");
								}else {
									JSON.put("msg", "Data already Exist.");
								}
							}else {
								ed.setAcademic(academic.toUpperCase());
								ed.setPassing_year(Integer.parseInt(passing_year));
								ed.setBoard_or_university(board_or_university);
								ed.setSchool_or_collage(school_or_collage);
								ed.setSubject(subject);
								
								//ed.setInstitute_name(institute_name);
								//ed.setTotal_marks(Integer.parseInt(data.get("total_marks")));
								ed.setObtain_marks(obtain_marks);
								ed.setGrade(grade);
								ed.setP_id(Integer.parseInt(p_id));
									ed.setModified_by(username);
									ed.setModified_date(date);
									ed.setId(id1);
									String msg = edao1NCH.updateSubCategory(ed);
									if (msg == "Data Updated Successfully") {
										JSON.put("msg", msg);
									//	model.put("msg", msg);
									} else {
										JSON.put("msg", msg);
									}
							}
									tx.commit();
								//	ra.addAttribute("msg", "Data Saved Successfully.");
							
						} catch (RuntimeException e) {
							try {
								JSON.put("msg", "roll back transaction");
							} catch (RuntimeException rbe) {
								JSON.put("msg", "Couldn�t roll back transaction " + rbe);
							}
							throw e;
						} finally {
							if (sessionHQL != null) {
								sessionHQL.close();
							}
						}
						return JSON;
					}
					
					//------PG Educational Details Save------
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/Edu_Det_PG_Action_API", method = RequestMethod.POST)
					public @ResponseBody JSONObject Edu_Det_PG_Action_API(MultipartFile mul,String name_of_exam,
							String board_or_university,String school_or_collage,String subject,String passing_year,String percentage_of_marks,
							String division,String doc_path_hid,
							String p_id,String id_org,String username,HttpServletRequest request, HttpSession session) throws IOException {

						
							
						
						JSONObject JSON =  new JSONObject();
						EDU_PG_PRE_EDUCATION_DETAILS ed = new EDU_PG_PRE_EDUCATION_DETAILS();
						int id = ed.getId() > 0 ? ed.getId() : 0;
						System.err.println("school_or_collage----"+school_or_collage);
						Date date = new Date();
						int id1 = Integer.parseInt(id_org);
						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
						DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
						
						
							String extension=""; //add line
							String fname = ""; //add line
							
							if(mul != null) {
							if (!mul.getOriginalFilename().isEmpty()) {
								
								byte[] bytes = mul.getBytes();
								String  mnhFilePath = session.getAttribute("doc_path").toString();
								
					            File dir = new File(mnhFilePath);
								if (!dir.exists())
									dir.mkdirs();
								String filename = mul.getOriginalFilename();
								System.out.println("dir "+!dir.exists());		
								int j = filename.lastIndexOf('.');
								if (j >= 0) {
									extension = filename.substring(j+1);
								}
								java.util.Date date1= new java.util.Date();
								fname = dir.getAbsolutePath() + File.separator + (new Timestamp(date1.getTime())).toString().replace(":", "").toString().replace(".", ".").toString().replace(" ","").toString().replace("-","").toString()+ "."+extension;
								
								File serverFile = new File(fname);	               
								BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
								stream.write(bytes);	                
								stream.close();
							}}else {
								ed.setDoc_path(doc_path_hid);
							}
							
							try {
							

								if (Integer.parseInt(name_of_exam) > 1) {
									Query qry = sessionHQL.createQuery(
											"select count(id) from EDU_PG_PRE_EDUCATION_DETAILS where name_of_exam=:name_of_exam and p_id=:p_id");
									qry.setParameter("name_of_exam", "1");
									qry.setParameter("p_id", Integer.parseInt(p_id));
									Long v = (Long) qry.uniqueResult();

									if (v > 0) {
										Query qrypy = sessionHQL.createQuery(
												"select passing_year from EDU_PG_PRE_EDUCATION_DETAILS where name_of_exam=:name_of_exam and p_id=:p_id");
										qrypy.setParameter("name_of_exam", "1");
										qrypy.setParameter("p_id", Integer.parseInt(p_id));
										List<EDU_PG_PRE_EDUCATION_DETAILS> x = (List<EDU_PG_PRE_EDUCATION_DETAILS>) qrypy.list();
										String py = String.valueOf(x.get(0));

										if ((Integer.parseInt(passing_year) - Integer.parseInt(py)) < 2) {
											JSON.put("msg", "Please Enter the Passing Year greater than 2 Year");
											//return new ModelAndView("redirect:Edu_Det_PG_Url");
										}
									}
								}

								Query q0 = sessionHQL.createQuery(
										"select count(id) from EDU_PG_PRE_EDUCATION_DETAILS where upper(name_of_exam)=:name_of_exam and p_id=:p_id"
//										+ "and passing_year=:passing_year \n"
//										+ "and upper(institute_name)=:institute_name \n"
//												+ "and percentage_of_marks=:percentage_of_marks "
//												+ "and total_marks=:total_marks "
//												+ "and upper(division)=:division "
										);
								
								q0.setParameter("name_of_exam", name_of_exam.toUpperCase());
								q0.setParameter("p_id", Integer.parseInt(p_id));
								//q0.setParameter("passing_year", (Integer.parseInt(passing_year)));
								//q0.setParameter("institute_name", institute_name.toUpperCase());
							//	q0.setParameter("percentage_of_marks", (Integer.parseInt(percentage_of_marks)));
//								q0.setParameter("total_marks", (Integer.parseInt(total_marks)));
								//q0.setParameter("division", division.toUpperCase());
								
								Long c = (Long) q0.uniqueResult();
								
							if (id1 == 0) {
								ed.setCreated_by(username);
								ed.setCreated_date(date);
									if (c == 0) {
										
										ed.setDoc_path(fname);
										ed.setName_of_exam(name_of_exam);
										ed.setPassing_year(Integer.parseInt(passing_year));
										//ed.setInstitute_name(institute_name);
										//ed.setTotal_marks(Integer.parseInt(data.get("total_marks")));
										ed.setPercentage_of_marks(percentage_of_marks);
										ed.setDivision(division);
										ed.setP_id(Integer.parseInt(p_id));
										sessionHQL.save(ed);
										sessionHQL.flush();
										sessionHQL.clear();
										JSON.put("msg", "Data Saved Successfully.");
								}else {
									JSON.put("msg", "Data already Exist.");
								}
							}else {
									ed.setModified_by(username);
									ed.setModified_date(date);
								
									ed.setId(id1);
									String msg = edao1PG.update_PGSubCategory(ed);
									if (msg == "Data Updated Successfully") {
										JSON.put("msg", msg);
									//	model.put("msg", msg);
									} else {
										JSON.put("msg", msg);
									}
							}
									tx.commit();
								//	ra.addAttribute("msg", "Data Saved Successfully.");
							
						} catch (RuntimeException e) {
							try {
								JSON.put("msg", "roll back transaction");
							} catch (RuntimeException rbe) {
								JSON.put("msg", "Couldn�t roll back transaction " + rbe);
							}
							throw e;
						} finally {
							if (sessionHQL != null) {
								sessionHQL.close();
							}
						}
						return JSON;
					}
					
					//----get p_id in educational details save----
					@CrossOrigin( origins = "*" )
					 @RequestMapping(value = "/get_p_id_Ncism_pers_info_ctrl_API", method = RequestMethod.POST)
					 	public @ResponseBody ArrayList<ArrayList<String>> get_p_id_Ncism_pers_info_ctrl_API(@RequestBody Map<String, String> data) {
						int userid = Integer.parseInt(data.get("userid"));	
						
						ArrayList<ArrayList<String>> list = da.get_p_id_Ncism_pers_info_data(userid);
					    	return list;
					 	}
					
					//----NCH get  p_id in educational details save----
					@CrossOrigin( origins = "*" )
					 @RequestMapping(value = "/get_p_id_pers_info_ctrl_API", method = RequestMethod.POST)
					 	public @ResponseBody ArrayList<ArrayList<String>> get_p_id_pers_info_ctrl_API(@RequestBody Map<String, String> data) {
						int userid = Integer.parseInt(data.get("userid"));	
						
						ArrayList<ArrayList<String>> list =daNCH.get_p_id_pers_info_data(userid);
					    	return list;
					 	}
					
					//----PG get  p_id in educational details save----
					@CrossOrigin( origins = "*" )
					 @RequestMapping(value = "/get_p_id_pers_info_ctrl_PG_API", method = RequestMethod.POST)
					 	public @ResponseBody ArrayList<ArrayList<String>> get_p_id_pers_info_ctrl_PG_API(@RequestBody Map<String, String> data) {
						int userid = Integer.parseInt(data.get("userid"));	
						
						ArrayList<ArrayList<String>> list =daPG.get_p_id_pers_info_data_pg(userid);
					    	return list;
					 	}
					
					
					 
					//--------search educational details------
					@CrossOrigin( origins = "*" )		
					@RequestMapping(value = "/getFilterEducation_Ncism_data_API", method = RequestMethod.POST)	
					public @ResponseBody ArrayList<ArrayList<String>> getFilterEducation_Ncism_data_API(@RequestBody Map<String, String> data) {
						
						int startPage = 0;
						int pageLength = 1000;
						String Search = data.get("Search");
						String orderColunm = "";
						String orderType = "ASC";
						String academic = data.get("academic");
						Integer passing_year = Integer.parseInt(data.get("passing_year"));
						String institute_name = data.get("institute_name");
						Integer obtain_marks = Integer.parseInt(data.get("obtain_marks"));
						Integer total_marks = Integer.parseInt(data.get("total_marks"));
						String grade = data.get("grade");
						String doc_path = data.get("doc_path");
						String userId  = data.get("userId");
						String username = data.get("username");
							
							int userid = da.getNcism_Username(username);

						return edao1.DataTableEducationNcism_DataList(startPage, pageLength, Search, orderColunm, orderType,academic,0,institute_name,0,
								0,grade,doc_path,userid);

					}
					
					//--------NCH search educational details------
					@CrossOrigin( origins = "*" )		
					@RequestMapping(value = "/getFilterEducation_Nch_data_API", method = RequestMethod.POST)	
					public @ResponseBody ArrayList<ArrayList<String>> getFilterEducation_Nch_data_API(@RequestBody Map<String, String> data) {
						
						int startPage = 0;
						int pageLength = 1000;
						String Search = data.get("Search");
						String orderColunm = "";
						String orderType = "ASC";
						String academic = data.get("academic");
						Integer passing_year = Integer.parseInt(data.get("passing_year"));
						String institute_name = data.get("institute_name");
						Integer obtain_marks = Integer.parseInt(data.get("obtain_marks"));
						Integer total_marks = Integer.parseInt(data.get("total_marks"));
						String grade = data.get("grade");
						String doc_path = data.get("doc_path");
						String userId  = data.get("userId");
						String username = data.get("username");
							 
							int userid = daNCH.getUsername(username);

						return edao1NCH.DataTableEducationDataList(startPage, pageLength, Search, orderColunm, orderType,academic,0,institute_name,0,
								0,grade,doc_path,userid);

					}
					
					//--------PG search educational details------
					@CrossOrigin( origins = "*" )		
					@RequestMapping(value = "/getFilterEducation_PG_data_API", method = RequestMethod.POST)	
					public @ResponseBody ArrayList<ArrayList<String>> getFilterEducation_PG_data_API(@RequestBody Map<String, String> data) {
						
						int startPage = 0;
						int pageLength = 1000;
						String Search = data.get("Search");
						String orderColunm = "";
						String orderType = "ASC";
						String name_of_exam = data.get("name_of_exam");
						String division = data.get("division");
						String institute_name = data.get("institute_name");
						String doc_path = data.get("doc_path");
						String username = data.get("username");
							 
						
						int userid = daPG.getUsername_pg(username);

					return edao1PG.DataTableEducation_PGDataList(startPage, pageLength, Search, orderColunm, orderType,name_of_exam,0,institute_name,0,
							0,division,doc_path,userid);

					}
					
					//----------educational file download-----------
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getDownloadPdfUrlforeduDocNcism_API", method = RequestMethod.GET)
					public @ResponseBody String getDownloadPdfUrlforeduDocNcism__API(String doc_id1,
							 HttpServletRequest request, HttpSession session, HttpServletResponse response)
							throws IOException {
						String msg="";
						//String url = pageUrl;
						String EXTERNAL_FILE_PATH = "";

						EXTERNAL_FILE_PATH = edao1.getFilePathQueryNcism_(Integer.parseInt(doc_id1));
						
						System.err.println("EXTERNAL_FILE_PATH---->   "+EXTERNAL_FILE_PATH);
						
						if (EXTERNAL_FILE_PATH != "" && EXTERNAL_FILE_PATH != null) {
							File file = null;
							file = new File(EXTERNAL_FILE_PATH);
							try {
								if (!file.exists()) {
									msg = "Sorry.The file you are looking for does not exist!";
									//return new ModelAndView(url);
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
								msg = "Downloaded Successfully";
								//return new ModelAndView(url);
							} catch (FileNotFoundException e) {
								//e.printStackTrace();
							}
						} 
						
						else {
							msg = "Sorry.The file you are looking for does not exist!";
						}
						
						return msg;
					}
					
					//----------NCH educational file download-----------
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getDownloadPdfUrlforeduDocNch_API", method = RequestMethod.GET)
					public @ResponseBody String getDownloadPdfUrlforeduDocNch__API(String doc_id1,
							 HttpServletRequest request, HttpSession session, HttpServletResponse response)
							throws IOException {
						String msg="";
						//String url = pageUrl;
						String EXTERNAL_FILE_PATH = "";

						EXTERNAL_FILE_PATH = edao1NCH.getFilePathQuery(Integer.parseInt(doc_id1));
						
						if (EXTERNAL_FILE_PATH != "" && EXTERNAL_FILE_PATH != null) {
							File file = null;
							file = new File(EXTERNAL_FILE_PATH);
							try {
								if (!file.exists()) {
									msg = "Sorry.The file you are looking for does not exist!";
									//return new ModelAndView(url);
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
								msg = "Downloaded Successfully";
								//return new ModelAndView(url);
							} catch (FileNotFoundException e) {
								//e.printStackTrace();
							}
						} 
						
						
						else {
							msg = "Sorry.The file you are looking for does not exist!";
						}
						return msg;
					}
					
					//----------PG educational file download-----------
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getDownloadPdfUrlforeduDocPG_API", method = RequestMethod.GET)
					public @ResponseBody String getDownloadPdfUrlforeduDocPG__API(String doc_id1,
							 HttpServletRequest request, HttpSession session, HttpServletResponse response)
							throws IOException {
						String msg="";
						//String url = pageUrl;
						String EXTERNAL_FILE_PATH = "";

						EXTERNAL_FILE_PATH = edao1PG.getFilePathQuery(Integer.parseInt(doc_id1));
						
						if (EXTERNAL_FILE_PATH != "" && EXTERNAL_FILE_PATH != null) {
							File file = null;
							file = new File(EXTERNAL_FILE_PATH);
							try {
								if (!file.exists()) {
									msg = "Sorry.The file you are looking for does not exist!";
									//return new ModelAndView(url);
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
								msg = "Downloaded Successfully";
								//return new ModelAndView(url);
							} catch (FileNotFoundException e) {
								//e.printStackTrace();
							}
						} 
						
						
						else {
							msg = "Sorry.The file you are looking for does not exist!";
						}
						return msg;
					}
					
					@CrossOrigin( origins = "*" )		
					@RequestMapping(value = "/delete_Ncism_education_API", method = RequestMethod.POST)
						public @ResponseBody String delete_Ncism_education_API(@RequestBody Map<String, String> data,
								BindingResult result, HttpServletRequest request, HttpSession session,
								HttpSession sessionA) {
							
						String msg = "";
						int id = Integer.parseInt(data.get("id"));
							List<String> liststr = new ArrayList<String>();
							String username = data.get("username").toString();
							
							try {
								Session sessionHQL = this.sessionFactory.openSession();
								 Transaction tx = sessionHQL.beginTransaction();
								 
								 String hqlUpdate = "delete from  EDU_NCISM_REG_GRADU_PRE_EDU_DTLS_TBL where id=:id";
								 
							@SuppressWarnings({ "rawtypes", "deprecation" })
								int app = sessionHQL.createQuery(hqlUpdate)
								.setParameter("id", id).executeUpdate();
//								.setString("modified_by", username)
//								.setDate("modified_date", new Date()).
								tx.commit();
								sessionHQL.close();
								if (app > 0) {
									liststr.add("Delete Successfully.");
								} else {
									liststr.add("Delete Unsuccessfull");
								}
								msg = liststr.get(0);

							} catch (Exception e) {
								liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
								msg = liststr.get(0);
							}
							return msg;
						}
					
					//---NCH delete education data---
					@CrossOrigin( origins = "*" )		
					@RequestMapping(value = "/delete_Nch_education_API", method = RequestMethod.POST)
						public @ResponseBody String delete_Nch_education_API(@RequestBody Map<String, String> data,
								BindingResult result, HttpServletRequest request, HttpSession session,
								HttpSession sessionA) {
							
						String msg = "";
						int id = Integer.parseInt(data.get("id"));
							List<String> liststr = new ArrayList<String>();
							String username = data.get("username").toString();
							
							try {
								Session sessionHQL = this.sessionFactory.openSession();
								 Transaction tx = sessionHQL.beginTransaction();
								 
								 String hqlUpdate = "delete from  TB_PRE_EDUCATION_DETAILS where id=:id";
								 
							@SuppressWarnings({ "rawtypes", "deprecation" })
								int app = sessionHQL.createQuery(hqlUpdate)
								.setParameter("id", id).executeUpdate();
//								.setString("modified_by", username)
//								.setDate("modified_date", new Date()).
								tx.commit();
								sessionHQL.close();
								if (app > 0) {
									liststr.add("Delete Successfully.");
								} else {
									liststr.add("Delete Unsuccessfull");
								}
								msg = liststr.get(0);

							} catch (Exception e) {
								liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
								msg = liststr.get(0);
							}
							return msg;
						}
					
					//---PG delete education data---
					@CrossOrigin( origins = "*" )		
					@RequestMapping(value = "/delete_PG_education_API", method = RequestMethod.POST)
						public @ResponseBody String delete_PG_education_API(@RequestBody Map<String, String> data,
								BindingResult result, HttpServletRequest request, HttpSession session,
								HttpSession sessionA) {
							
						String msg = "";
						int id = Integer.parseInt(data.get("id"));
							List<String> liststr = new ArrayList<String>();
							String username = data.get("username").toString();
							
							try {
								Session sessionHQL = this.sessionFactory.openSession();
								 Transaction tx = sessionHQL.beginTransaction();
								 
								 String hqlUpdate = "delete from  EDU_PG_PRE_EDUCATION_DETAILS where id=:id";
								 
							@SuppressWarnings({ "rawtypes", "deprecation" })
								int app = sessionHQL.createQuery(hqlUpdate)
								.setParameter("id", id).executeUpdate();
//								.setString("modified_by", username)
//								.setDate("modified_date", new Date()).
								tx.commit();
								sessionHQL.close();
								if (app > 0) {
									liststr.add("Delete Successfully.");
								} else {
									liststr.add("Delete Unsuccessfull");
								}
								msg = liststr.get(0);

							} catch (Exception e) {
								liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
								msg = liststr.get(0);
							}
							return msg;
						}
					
					//----get edit data-----
					@CrossOrigin( origins = "*" )		
					@RequestMapping(value = "/geteditEducation_Ncism_data_ctrl_API", method = RequestMethod.POST)
				 	public @ResponseBody ArrayList<ArrayList<String>> geteditEducation_Ncism_data_ctrl_API(@RequestBody Map<String, String> data
				 			) {
						String id = data.get("id");
				    	ArrayList<ArrayList<String>> list = edao1.geteditEducation_Ncism_data(id);
				    	return list;
				 	}
					
					//----NCH get edit data-----
					@CrossOrigin( origins = "*" )		
					@RequestMapping(value = "/geteditEducation_Nch_data_ctrl_API", method = RequestMethod.POST)
				 	public @ResponseBody ArrayList<ArrayList<String>> geteditEducation_Nch_data_ctrl_API(@RequestBody Map<String, String> data
				 			) {
						String id = data.get("id");
				    	ArrayList<ArrayList<String>> list =edao1NCH.geteditEducation_data(id);
				    	return list;
				 	}
					
					//----PG get edit data-----
					@CrossOrigin( origins = "*" )		
					@RequestMapping(value = "/geteditEducation_PG_data_ctrl_API", method = RequestMethod.POST)
				 	public @ResponseBody ArrayList<ArrayList<String>> geteditEducation_PG_data_ctrl_API(@RequestBody Map<String, String> data
				 			) {
						String id = data.get("id");
				    	ArrayList<ArrayList<String>> list = edao1PG.geteditEducation_PG_data(id);
				    	return list;
				 	}
					
					//----check year in education-----
					@CrossOrigin( origins = "*" )		
				 @RequestMapping(value = "/Getedu_chekyear_ncism_ctrl_API", method = RequestMethod.POST)
				 	public @ResponseBody ArrayList<ArrayList<String>> Getedu_chekyear_ncism_ctrl_API(@RequestBody Map<String, String> data) {
						String p_id = data.get("p_id");
				    	ArrayList<ArrayList<String>> list = edao1.Getedu_chekyear_ncism_data(p_id);
				    	return list;
				 	}
					
					
					//----NCH check year in education-----
					@CrossOrigin( origins = "*" )		
				 @RequestMapping(value = "/Getedu_chekyear_nch_ctrl_API", method = RequestMethod.POST)
				 	public @ResponseBody ArrayList<ArrayList<String>> Getedu_chekyear_nch_ctrl_API(@RequestBody Map<String, String> data) {
						String p_id = data.get("p_id");
				    	ArrayList<ArrayList<String>> list = edao1NCH.Getedu_chekyear_nch_data(p_id);
				    	return list;
				 	}
					
					//---------upload document save -------
					@CrossOrigin( origins = "*" )		
					@RequestMapping(value = "/Doc_Upload_Ncism_Action_API", method = RequestMethod.POST)
					public @ResponseBody JSONObject Doc_Upload_Action_API(MultipartFile mulSign,MultipartFile mulPhoto,MultipartFile mulCourt,MultipartFile mulDocument,String username,String p_id,String signature_hid,String photograph_hid,
							String court_order_hid,String late_admission_status, String doc_name,String doc_type,
							String edit_id_photo,
							HttpServletRequest request,HttpSession session) throws IOException {

						JSONObject json = new JSONObject();
						EDU_NCISM_REG_GRADU_DOCUMENT_UPLOAD rd = new EDU_NCISM_REG_GRADU_DOCUMENT_UPLOAD();
						System.err.println("rd.getP_id()---"+rd.getId());
						int id = rd.getId() > 0 ? rd.getId() : 0;
						Date date = new Date();
						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
//						DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
						String name="";
						System.err.println("mulSign---"+mulSign+"mulPhoto---"+mulPhoto+"signature_hid--"+signature_hid);
						
						
							try {
								
							//MultipartFile file2 = mul.getFile("signatureimg");
							if(mulSign != null) {
							if (!mulSign.getOriginalFilename().isEmpty()) {
								String signature = common.fileupload(mulSign.getBytes(), mulSign.getOriginalFilename(),
										1, "signature" + "1");
								rd.setSignature(signature);
							}}
							if (mulSign == null && !signature_hid.equals("")) {
								
								rd.setSignature(signature_hid);
							}
							
							//MultipartFile file3 = mul.getFile("photographimg");
							if(mulPhoto != null) {
							if (!mulPhoto.getOriginalFilename().isEmpty()) {
								
								String photograph = common.fileupload(mulPhoto.getBytes(), mulPhoto.getOriginalFilename(),
										3, "photograph"+3 + "1");
								rd.setPhotograph(photograph);
							}}
							
							if(mulPhoto == null  && !photograph_hid.equals("")) {
								rd.setPhotograph(photograph_hid);
							}
							
							
							//MultipartFile file4 = mul.getFile("court_order");
							if(mulCourt!=null) {
							if (!mulCourt.getOriginalFilename().isEmpty()) {
								String courtorder = common.fileupload(mulCourt.getBytes(), mulCourt.getOriginalFilename(),
										4, "courtorder");
								rd.setCourt_order(courtorder);
							}}
							
					System.err.println("court_order_hid---------->    "+court_order_hid);
					if(mulCourt==null && !court_order_hid.equals("")) {
								rd.setCourt_order(court_order_hid);
							}
					if (mulCourt == null && court_order_hid.equals("")) {	
						rd.setCourt_order("");
					}
					
					/*
					 * if (mulCourt.getOriginalFilename().isEmpty() && court_order_hid.equals("0"))
					 * { rd.setCourt_order(""); }
					 */
							
							
							
							if (id == 0) {							
							
							
							rd.setReg_id(1);
							rd.setCreated_by(username);
							rd.setCreated_date(date);
							rd.setP_id(Integer.parseInt(p_id));
							
							int sid = (int) sessionHQL.save(rd);
							sessionHQL.flush();
							sessionHQL.clear();
							}
							else {					
								
								
								
								EDU_NCISM_REG_GRADU_DOCUMENT_UPLOAD pda = (EDU_NCISM_REG_GRADU_DOCUMENT_UPLOAD)sessionHQL.get(EDU_NCISM_REG_GRADU_DOCUMENT_UPLOAD.class, id);
								pda.setModified_by(username);
								pda.setModified_date(date);
								pda.setPhotograph(rd.getPhotograph());
								pda.setSignature(rd.getSignature());
								pda.setCourt_order(rd.getCourt_order());
								
								String msg = getUpdatedocNcism_Details(pda);
							}
							
							//int count_hidden_att = Integer.parseInt(data.get("count_hidden_att"));
							//System.out.println("count_hidden_att "+count_hidden_att);
							for(int i=1; i <=1; i++) {
								
								//String doc_name = data.get("doc_name"+i);
								//String doc_type = data.get("doc_type"+i);
								
								Query q0 = sessionHQL.createQuery("select count(id) from EDU_NCISM_REG_GRADU_OTHER_DOC_UPLOAD where doc_name=:doc_name and p_id=:p_id");
								q0.setParameter("doc_name", Integer.parseInt(doc_name));
								q0.setParameter("p_id", Integer.parseInt(p_id));
								Long c = (Long) q0.uniqueResult();
								
								if (c == 0) {
								if(!doc_name.equals("0")) {
									
									//MultipartFile file = mul.getFile("upload_document"+i);
									if (!mulDocument.getOriginalFilename().isEmpty()) {
										 name = common.fileupload(mulDocument.getBytes(), mulDocument.getOriginalFilename(),
												i, "upload_document"+i + "1");
									}
								
								EDU_NCISM_REG_GRADU_OTHER_DOC_UPLOAD od = new EDU_NCISM_REG_GRADU_OTHER_DOC_UPLOAD();
								
								//String du = fname;
								od.setReg_id(1);
								od.setDoc_name(Integer.parseInt(doc_name));
								od.setDoc_type(Integer.parseInt(doc_type));
								od.setUpload_document(name);
								
								od.setP_id(Integer.parseInt(p_id));
								od.setCreated_by(username);
								od.setCreated_date(date);
									
								sessionHQL.save(od);
								sessionHQL.flush();
								sessionHQL.clear();
									}
								json.put("msg", "Data Saved Successfully.");
								}
								else {
									json.put("msg", "Data already Exist.");
								}
								
							}
									tx.commit();
									json.put("doc_eid", p_id);
							
						} catch (RuntimeException e) {
							e.printStackTrace();
							try {
								tx.rollback();
								json.put("msg", "roll back transaction");
							} catch (RuntimeException rbe) {
								json.put("msg", "Couldn't roll back transaction " + rbe);
							}
							throw e;
						} finally {
							if (sessionHQL != null) {
								sessionHQL.close();
							}
						}
						return json;
					}
					
					
					 public String getUpdatedocNcism_Details(EDU_NCISM_REG_GRADU_DOCUMENT_UPLOAD obj){
							Session sessionHQL = this.sessionFactory.openSession();
							Transaction tx = sessionHQL.beginTransaction();
							
							 String msg = "";
							 
							 sessionHQL.update(obj);
							 msg = "Data Updated Successfully";
								sessionHQL.flush();
								sessionHQL.clear();
								tx.commit();
//							}
//							catch (Exception e) {
//								msg = "Data Not Updated";
//								tx.rollback();
//							}
//							finally {
//								sessionHQL.close();
//							}
							return msg;
						}
					 
					 
					//---------NCH upload document save -------
						@CrossOrigin( origins = "*" )		
						@RequestMapping(value = "/Doc_Upload_Nch_Action_API", method = RequestMethod.POST)
						public @ResponseBody JSONObject Doc_Upload_nch_Action_API(MultipartFile mulSign,MultipartFile mulPhoto,MultipartFile mulCourt,MultipartFile mulDocument,String username,String p_id,String signature_hid,String photograph_hid,
								String court_order_hid,String late_admission_status, String doc_name,String doc_type,String edit_id_photo,
								HttpServletRequest request,HttpSession session) throws IOException {

							JSONObject json = new JSONObject();
							TB_DOCUMENT_UPLOAD rd = new TB_DOCUMENT_UPLOAD();
							int id = rd.getId() > 0 ? rd.getId() : 0;

							System.err.println("edit_id_photo---"+edit_id_photo);
							Session sessionHQL = this.sessionFactory.openSession();
							
							Query q01 = sessionHQL.createQuery("select count(id) from TB_DOCUMENT_UPLOAD where p_id=:p_id");
								q01.setParameter("p_id", Integer.parseInt(p_id));
							Long c1 = (Long) q01.uniqueResult();
							
							Date date = new Date();
							Transaction tx = sessionHQL.beginTransaction();
//							DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
							String name="";
							
							System.err.println("id1---"+id+"c1---"+c1);
							
								try {
									
								//MultipartFile file2 = mul.getFile("signatureimg");
								if(mulSign != null) {
								if (!mulSign.getOriginalFilename().isEmpty()) {
									String signature = common.fileupload(mulSign.getBytes(), mulSign.getOriginalFilename(),
											1, "signature" + "1");
									rd.setSignature(signature);
								}}
								
								if(mulSign == null) {
									if (/* mulSign.getOriginalFilename().isEmpty() && */!signature_hid.equals("")) {
									
									rd.setSignature(signature_hid);
								}}
								
								//MultipartFile file3 = mul.getFile("photographimg");
								if(mulPhoto != null) {
								if (!mulPhoto.getOriginalFilename().isEmpty()) {
									
									String photograph = common.fileupload(mulPhoto.getBytes(), mulPhoto.getOriginalFilename(),
											3, "photograph"+3 + "1");
									rd.setPhotograph(photograph);
								}}
								
								if(mulPhoto == null) {
									if (/* mulPhoto.getOriginalFilename().isEmpty() && */ !photograph_hid.equals("")) {
									rd.setPhotograph(photograph_hid);
								}}
								
								
								//MultipartFile file4 = mul.getFile("court_order");
								if(mulCourt!=null) {
								if (!mulCourt.getOriginalFilename().isEmpty()) {
									String courtorder = common.fileupload(mulCourt.getBytes(), mulCourt.getOriginalFilename(),
											4, "courtorder");
									rd.setCourt_order(courtorder);
								}}
								
						System.err.println("court_order_hid---------->    "+court_order_hid);
						if(mulCourt!=null) {
								if (mulCourt.getOriginalFilename().isEmpty()  && !court_order_hid.equals("0")) {
									rd.setCourt_order(court_order_hid);
								}
						}if (mulCourt == null && court_order_hid.equals("0")) {
							rd.setCourt_order("");
						}
						
						/*
						 * if (mulCourt.getOriginalFilename().isEmpty() && court_order_hid.equals("0"))
						 * { rd.setCourt_order(""); }
						 */
								
								
								
								if (c1 == 0) {							
									System.err.println("id1save---"+id);
								
								rd.setReg_id(1);
								rd.setCreated_by(username);
								rd.setCreated_date(date);
								rd.setP_id(Integer.parseInt(p_id));
								
								int sid = (int) sessionHQL.save(rd);
								sessionHQL.flush();
								sessionHQL.clear();
								}
								else {					
									System.err.println("id1updateelse---"+id+"userName---"+username);
									
									
									TB_DOCUMENT_UPLOAD pda = (TB_DOCUMENT_UPLOAD)sessionHQL.get(TB_DOCUMENT_UPLOAD.class, Integer.parseInt(edit_id_photo));
									pda.setModified_by(username);
									pda.setModified_date(date);
									pda.setPhotograph(rd.getPhotograph());
									pda.setSignature(rd.getSignature());
									pda.setCourt_order(rd.getCourt_order());
									String msg = getUpdatedocDetails(pda);
								}
								
								//int count_hidden_att = Integer.parseInt(data.get("count_hidden_att"));
								//System.out.println("count_hidden_att "+count_hidden_att);
								for(int i=1; i <=1; i++) {
									
									//String doc_name = data.get("doc_name"+i);
									//String doc_type = data.get("doc_type"+i);
									
									Query q0 = sessionHQL.createQuery("select count(id) from TB_OTHER_DOCCUMENT_UPLOAD where doc_name=:doc_name and p_id=:p_id");
									q0.setParameter("doc_name", Integer.parseInt(doc_name));
									q0.setParameter("p_id", Integer.parseInt(p_id));
									Long c = (Long) q0.uniqueResult();
									
									if (c == 0) {
										System.err.println("id2save---"+id);
									if(!doc_name.equals("0")) {
										
										//MultipartFile file = mul.getFile("upload_document"+i);
										if (!mulDocument.getOriginalFilename().isEmpty()) {
											 name = common.fileupload(mulDocument.getBytes(), mulDocument.getOriginalFilename(),
													i, "upload_document"+i + "1");
										}
									
										TB_OTHER_DOCCUMENT_UPLOAD od = new TB_OTHER_DOCCUMENT_UPLOAD();
									
									//String du = fname;
									od.setReg_id(1);
									od.setDoc_name(Integer.parseInt(doc_name));
									od.setDoc_type(Integer.parseInt(doc_type));
									od.setUpload_document(name);
									
									od.setP_id(Integer.parseInt(p_id));
									od.setCreated_by(username);
									od.setCreated_date(date);
										
									sessionHQL.save(od);
									sessionHQL.flush();
									sessionHQL.clear();
										}
									json.put("msg", "Data Saved Successfully.");
									}
									else {
										System.err.println("id2up---"+id);
										json.put("msg", "Data already Exist.");
									}
									
								}
										tx.commit();
										json.put("doc_eid", p_id);
								
							} catch (RuntimeException e) {
								e.printStackTrace();
								try {
									tx.rollback();
									json.put("msg", "roll back transaction");
								} catch (RuntimeException rbe) {
									json.put("msg", "Couldn't roll back transaction " + rbe);
								}
								throw e;
							} finally {
								if (sessionHQL != null) {
									sessionHQL.close();
								}
							}
							return json;
						}
						
						 public String getUpdatedocDetails(TB_DOCUMENT_UPLOAD obj){
								Session sessionHQL = this.sessionFactory.openSession();
								Transaction tx = sessionHQL.beginTransaction();
								
								 String msg = "";
								 
								 sessionHQL.update(obj);
								 msg = "Data Updated Successfully";
									sessionHQL.flush();
									sessionHQL.clear();
									tx.commit();
//								}
//								catch (Exception e) {
//									msg = "Data Not Updated";
//									tx.rollback();
//								}
//								finally {
//									sessionHQL.close();
//								}
								return msg;
							}
						 
						//---------PG upload document save -------
						@CrossOrigin( origins = "*" )		
						@RequestMapping(value = "/Doc_Upload_PG_Action_API", method = RequestMethod.POST)
						public @ResponseBody JSONObject Doc_Upload_PG_Action_API(MultipartFile mulSign,MultipartFile mulPhoto,MultipartFile mulDocument,String username,String p_id,String signature_hid,String photograph_hid,
								String late_admission_status, String doc_name,String doc_type,String edit_id_photo,
								HttpServletRequest request,HttpSession session) throws IOException {

							JSONObject json = new JSONObject();
							EDU_PG_REG_DOCUMENT_UPLOAD rd = new EDU_PG_REG_DOCUMENT_UPLOAD();
							int id = rd.getId() > 0 ? rd.getId() : 0;

							System.err.println("edit_id_photo---"+edit_id_photo);
							Session sessionHQL = this.sessionFactory.openSession();
							
							Query q01 = sessionHQL.createQuery("select count(id) from EDU_PG_REG_DOCUMENT_UPLOAD where p_id=:p_id");
								q01.setParameter("p_id", Integer.parseInt(p_id));
							Long c1 = (Long) q01.uniqueResult();
							
							Date date = new Date();
							Transaction tx = sessionHQL.beginTransaction();
//							DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
							String name="";
							
							System.err.println("id1---"+id+"c1---"+c1);
							
								try {
									
								//MultipartFile file2 = mul.getFile("signatureimg");
								if(mulSign != null) {
								if (!mulSign.getOriginalFilename().isEmpty()) {
									String signature = common.fileupload(mulSign.getBytes(), mulSign.getOriginalFilename(),
											1, "signature" + "1");
									rd.setSignature(signature);
								}}
								
								if(mulSign == null) {
									if (/* mulSign.getOriginalFilename().isEmpty() && */!signature_hid.equals("")) {
									
									rd.setSignature(signature_hid);
								}}
								
								//MultipartFile file3 = mul.getFile("photographimg");
								if(mulPhoto != null) {
								if (!mulPhoto.getOriginalFilename().isEmpty()) {
									
									String photograph = common.fileupload(mulPhoto.getBytes(), mulPhoto.getOriginalFilename(),
											3, "photograph"+3 + "1");
									rd.setPhotograph(photograph);
								}}
								
								if(mulPhoto == null) {
									if (/* mulPhoto.getOriginalFilename().isEmpty() && */ !photograph_hid.equals("")) {
									rd.setPhotograph(photograph_hid);
								}}
								
								
								
								
								
								if (c1 == 0) {							
									System.err.println("id1save---"+id);
								
								rd.setCreated_by(username);
								rd.setCreated_date(date);
								rd.setP_id(Integer.parseInt(p_id));
								
								int sid = (int) sessionHQL.save(rd);
								sessionHQL.flush();
								sessionHQL.clear();
								}
								else {					
									System.err.println("id1updateelse---"+id+"userName---"+username);
									
									

									EDU_PG_REG_DOCUMENT_UPLOAD pda = (EDU_PG_REG_DOCUMENT_UPLOAD)sessionHQL.get(EDU_PG_REG_DOCUMENT_UPLOAD.class, id);
									pda.setModified_by(username);
									pda.setModified_date(date);
									pda.setPhotograph(rd.getPhotograph());
									pda.setSignature(rd.getSignature());
									
									String msg = getUpdatedocDetails(pda);
								}
								
								//int count_hidden_att = Integer.parseInt(data.get("count_hidden_att"));
								//System.out.println("count_hidden_att "+count_hidden_att);
								for(int i=1; i <=1; i++) {
									
									//String doc_name = data.get("doc_name"+i);
									//String doc_type = data.get("doc_type"+i);
									
									Query q0 = sessionHQL.createQuery("select count(id) from EDU_PG_REG_OTHER_DOC_UPLOAD where doc_name=:doc_name and p_id=:p_id");
									q0.setParameter("doc_name", doc_name);
									q0.setParameter("p_id", Integer.parseInt(p_id));
									Long c = (Long) q0.uniqueResult();
									
									if (c == 0) {
									if(!doc_name.equals("0")) {
										
										if (!mulDocument.getOriginalFilename().isEmpty()) {
											 name = common.fileupload(mulDocument.getBytes(), mulDocument.getOriginalFilename(),
													i, "upload_document"+i + "1");
										}
									
									EDU_PG_REG_OTHER_DOC_UPLOAD od = new EDU_PG_REG_OTHER_DOC_UPLOAD();
									
									//String du = fname;
									od.setDoc_name(doc_name);
									od.setUpload_document(name);
									od.setP_id(Integer.parseInt(p_id));
									od.setCreated_by(username);
									od.setCreated_date(date);
										
									sessionHQL.save(od);
									sessionHQL.flush();
									sessionHQL.clear();
										}
									json.put("msg", "Data Saved Successfully.");
									}
									else {
										System.err.println("id2up---"+id);
										json.put("msg", "Data already Exist.");
									}
									
								}
										tx.commit();
										json.put("doc_eid", p_id);
								
							} catch (RuntimeException e) {
								e.printStackTrace();
								try {
									tx.rollback();
									json.put("msg", "roll back transaction");
								} catch (RuntimeException rbe) {
									json.put("msg", "Couldn't roll back transaction " + rbe);
								}
								throw e;
							} finally {
								if (sessionHQL != null) {
									sessionHQL.close();
								}
							}
							return json;
						}
						
						 public String getUpdatedocDetails(EDU_PG_REG_DOCUMENT_UPLOAD obj){
								Session sessionHQL = this.sessionFactory.openSession();
								Transaction tx = sessionHQL.beginTransaction();
								
								 String msg = "";
								 
								 sessionHQL.update(obj);
								 msg = "Data Updated Successfully";
									sessionHQL.flush();
									sessionHQL.clear();
									tx.commit();
//								}
//								catch (Exception e) {
//									msg = "Data Not Updated";
//									tx.rollback();
//								}
//								finally {
//									sessionHQL.close();
//								}
								return msg;
							}

						
						
					 //-----get late admission status api---
					 @CrossOrigin( origins = "*" )	
					 @RequestMapping(value = "/get_ayush_id_Ncism_ctrl_API", method = RequestMethod.POST)
					 	public @ResponseBody ArrayList<ArrayList<String>> get_ayush_id_Ncism_ctrl_API(@RequestBody Map<String, String> data) {
						 String userid = data.get("userid");
					    	ArrayList<ArrayList<String>> list = da.get_ayush_id_Ncism_data(userid);
					    	return list;
					 	}
					 
					 //-----NCH get late admission status api---
					 @CrossOrigin( origins = "*" )	
					 @RequestMapping(value = "/get_ayush_id_Nch_ctrl_API", method = RequestMethod.POST)
					 	public @ResponseBody ArrayList<ArrayList<String>> get_ayush_id_Nch_ctrl_API(@RequestBody Map<String, String> data) {
						 String userid = data.get("userid");
					    	ArrayList<ArrayList<String>> list = daNCH.get_ayush_id_data(userid);
					    	return list;
					 	}
					 
					 //-----PG get late admission status api---
					 @CrossOrigin( origins = "*" )	
					 @RequestMapping(value = "/get_ayush_id_PG_ctrl_API", method = RequestMethod.POST)
					 	public @ResponseBody ArrayList<ArrayList<String>> get_ayush_id_PG_ctrl_API(@RequestBody Map<String, String> data) {
						 
						 String userid = data.get("userid").toString();
						 String roleid = data.get("roleid").toString();
						 
							String staff_lvl = cdao.getStaffLvlfromRoleid(roleid);
						 
					    	ArrayList<ArrayList<String>> list = daPG.get_ayush_id_data_pg(userid,staff_lvl);
					    	return list;
					 	}
					 
					 //----get p_id upload document---
					 //get_p_id_Ncism_pers_info_ctrl_API---
					 
					 //----NCH get p_id upload document---
					 //get_p_id_pers_info_ctrl_API---
					 
					 //----PG get p_id upload document---
					 //get_p_id_pers_info_ctrl_PG_API
					 
					 //---get upload document like signature_hid,photo_hid_court_hid----
					 @CrossOrigin( origins = "*" )	
					 @RequestMapping(value = "/get_uploded_imgthumb_Ncism_ctrl_API", method = RequestMethod.POST)
					 	public @ResponseBody ArrayList<ArrayList<String>> get_uploded_imgthumb_Ncism_ctrl_API(@RequestBody Map<String, String> data) {
						 String p_id = data.get("p_id");
					    	ArrayList<ArrayList<String>> list = docdao.get_uploded_imgthumb_Ncism_data(p_id);
					    	return list;
					 	}
					 
					 //---NCH get upload document like signature_hid,photo_hid_court_hid----
					 @CrossOrigin( origins = "*" )	
					 @RequestMapping(value = "/get_uploded_imgthumb_Nch_ctrl_API", method = RequestMethod.POST)
					 	public @ResponseBody ArrayList<ArrayList<String>> get_uploded_imgh_Ncism_ctrl_API(@RequestBody Map<String, String> data) {
						 String p_id = data.get("p_id");
					    	ArrayList<ArrayList<String>> list = docdaoNCH.get_uploded_imgthumb_data(p_id);
					    	return list;
					 	}
					
					 
					 //---PG get upload document like signature_hid,photo_hid_court_hid----
					 @CrossOrigin( origins = "*" )	
					 @RequestMapping(value = "/get_uploded_imgthumb_PG_ctrl_API", method = RequestMethod.POST)
					 	public @ResponseBody ArrayList<ArrayList<String>> get_uploded_imgh_PG_ctrl_API(@RequestBody Map<String, String> data) {
						
						 String p_id = data.get("p_id");
						 
					    	ArrayList<ArrayList<String>> list = docdaoPG.get_uploded_imgthumb_data_PG(p_id);
					    	return list;
					 	}
					
					 
					 //-----search document upload data------
					 @CrossOrigin( origins = "*" )		
						@RequestMapping(value = "/getFilterDoc_Ncism_data_API", method = RequestMethod.POST)
						public @ResponseBody ArrayList<ArrayList<String>> getFilterDoc_Ncism_data_API(@RequestBody Map<String, String> data,HttpSession sessionUserId) {
							
							 int startPage = 0;
							 int pageLength = 1000;
							String Search = data.get("Search"); 
							String orderColunm = "";
							String orderType = "ASC";
							String upload_document  = data.get("upload_document");
							String username = data.get("username");
							
							int userid = da.getNcism_Username(username);

							return docdao.DataTableDocNcism_DataList(startPage, pageLength, Search, orderColunm, orderType,0, userid);

						}
					 
					 //-----NCH search document upload data------
					 @CrossOrigin( origins = "*" )		
						@RequestMapping(value = "/getFilterDoc_Nch_data_API", method = RequestMethod.POST)
						public @ResponseBody ArrayList<ArrayList<String>> getFilterDoc_Nch_data_API(@RequestBody Map<String, String> data,HttpSession sessionUserId) {
							
							 int startPage = 0;
							 int pageLength = 1000;
							String Search = data.get("Search"); 
							String orderColunm = "";
							String orderType = "ASC";
							String upload_document  = data.get("upload_document");
							String username = data.get("username");
							
							int userid =daNCH.getUsername(username);

							return docdaoNCH.DataTableDocDataList(startPage, pageLength, Search, orderColunm, orderType,0, userid);

						}
					 
					 //-----PG search document upload data------
					 @CrossOrigin( origins = "*" )		
						@RequestMapping(value = "/getFilterDoc_PG_data_API", method = RequestMethod.POST)
						public @ResponseBody ArrayList<ArrayList<String>> getFilterDoc_PG_data_API(@RequestBody Map<String, String> data,HttpSession sessionUserId) {
							
							 int startPage = 0;
							 int pageLength = 1000;
							String Search = data.get("Search"); 
							String orderColunm = "";
							String orderType = "ASC";
							String username = data.get("username");
							
							int userid = daPG.getUsername_pg(username);

							return docdaoPG.DataTableDocData_PGList(startPage, pageLength, Search, orderColunm, orderType,0, userid);

						}
					 
					 //----upload documents in download document-----
					 @CrossOrigin( origins = "*" )		
						@RequestMapping(value = "/getDownloadPdfUrlfor_Ncism_Doc_API", method = RequestMethod.GET)
						public @ResponseBody String getDownloadPdfUrlfor_Ncism_Doc_API(String doc_id1,  HttpServletRequest request, HttpSession session, HttpServletResponse response)
								throws IOException {
						 	
						 String msg = "";
							//String url = pageUrl;
							String EXTERNAL_FILE_PATH = "";

							EXTERNAL_FILE_PATH = docdao.getFilePathNcism_Query(Integer.parseInt(doc_id1));
							
							if (EXTERNAL_FILE_PATH != "") {
								File file = null;
								file = new File(EXTERNAL_FILE_PATH);

								System.err.println("file---------->     "+file);
								
								try {
									if (!file.exists()) {
										msg = "Sorry.The file you are looking for does not exist!";
										//return new ModelAndView(url);
									}
									
								} catch (Exception exception) {
									msg = "Sorry.The file you are looking for does not exist!";
									//return new ModelAndView(url);
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
									msg = "Downloaded Successfully";
									//return new ModelAndView(url);
								} catch (FileNotFoundException e) {
									//e.printStackTrace();
								}
							} 
							return msg;
						}
					 
					//----NCH upload documntes in download document-----
					 @CrossOrigin( origins = "*" )		
						@RequestMapping(value = "/getDownloadPdfUrlfor_Nch_Doc_API", method = RequestMethod.GET)
						public @ResponseBody String getDownloadPdfUrlfor_Nch_Doc_API(String doc_id1,  HttpServletRequest request, HttpSession session, HttpServletResponse response)
								throws IOException {
						 	
						 String msg = "";
							//String url = pageUrl;
							String EXTERNAL_FILE_PATH = "";

							EXTERNAL_FILE_PATH = docdaoNCH.getFilePathQuery(Integer.parseInt(doc_id1));
							
							if (EXTERNAL_FILE_PATH != "") {
								File file = null;
								file = new File(EXTERNAL_FILE_PATH);

								System.err.println("file---------->     "+file);
								
								try {
									if (!file.exists()) {
										msg = "Sorry.The file you are looking for does not exist!";
										//return new ModelAndView(url);
									}
									
								} catch (Exception exception) {
									msg = "Sorry.The file you are looking for does not exist!";
									//return new ModelAndView(url);
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
									msg = "Downloaded Successfully";
									//return new ModelAndView(url);
								} catch (FileNotFoundException e) {
									//e.printStackTrace();
								}
							} 
							return msg;
						}
					 
					//----PG upload documntes in download document-----
					 @CrossOrigin( origins = "*" )		
						@RequestMapping(value = "/getDownloadPdfUrlfor_PG_Doc_API", method = RequestMethod.GET)
						public @ResponseBody String getDownloadPdfUrlfor_PG_Doc_API(String doc_id1,  HttpServletRequest request, HttpSession session, HttpServletResponse response)
								throws IOException {
						 	
						 String msg = "";
							//String url = pageUrl;
							String EXTERNAL_FILE_PATH = "";

							EXTERNAL_FILE_PATH = docdaoPG.getFilePathQuery_PG(Integer.parseInt(doc_id1));
							
							if (EXTERNAL_FILE_PATH != "") {
								File file = null;
								file = new File(EXTERNAL_FILE_PATH);

								System.err.println("file---------->     "+file);
								
								try {
									if (!file.exists()) {
										msg = "Sorry.The file you are looking for does not exist!";
										//return new ModelAndView(url);
									}
									
								} catch (Exception exception) {
									msg = "Sorry.The file you are looking for does not exist!";
									//return new ModelAndView(url);
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
									msg = "Downloaded Successfully";
									//return new ModelAndView(url);
								} catch (FileNotFoundException e) {
									//e.printStackTrace();
								}
							} 
							return msg;
						}
						
					 //------documnet upload delete api---
						@CrossOrigin( origins = "*" )		
						@RequestMapping(value = "/delete_Ncism_document_API", method = RequestMethod.POST)	
						public @ResponseBody JSONObject delete_Ncism_document_API(@RequestBody Map<String, String> data,HttpServletRequest request, HttpSession session,
								HttpSession sessionA) {
							JSONObject json = new JSONObject();
							int id = Integer.parseInt(data.get("id"));
							int p_id = Integer.parseInt(data.get("doc_ch_p_id")); //---p_id---
							String username = data.get("username").toString();
							List<String> liststr = new ArrayList<String>();
							
							
							try {
								Session sessionHQL = this.sessionFactory.openSession();
								 Transaction tx = sessionHQL.beginTransaction();
								 
								 String hqlUpdate = "delete from EDU_NCISM_REG_GRADU_OTHER_DOC_UPLOAD where id=:id";
								 
							@SuppressWarnings({ "rawtypes", "deprecation" })
								int app = sessionHQL.createQuery(hqlUpdate)
								.setParameter("id", id).executeUpdate();
//								.setString("modified_by", username)
//								.setDate("modified_date", new Date()).
								tx.commit();
								sessionHQL.close();

								if (app > 0) {
									liststr.add("Delete Successfully.");
								} else {
									liststr.add("Delete Unsuccessfull");
								}
								json.put("msg",liststr.get(0));

							} catch (Exception e) {
								liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
								json.put("msg",liststr.get(0));
							}
							json.put("doc_eid",p_id);
							return json;
						}
						
						//------NCH documnet upload delete api---
						@CrossOrigin( origins = "*" )		
						@RequestMapping(value = "/delete_Nch_document_API", method = RequestMethod.POST)	
						public @ResponseBody JSONObject delete_Nch_document_API(@RequestBody Map<String, String> data,HttpServletRequest request, HttpSession session,
								HttpSession sessionA) {
							JSONObject json = new JSONObject();
							int id = Integer.parseInt(data.get("id"));
							int p_id = Integer.parseInt(data.get("doc_ch_p_id")); //---p_id---
							String username = data.get("username").toString();
							List<String> liststr = new ArrayList<String>();
							
							
							try {
								Session sessionHQL = this.sessionFactory.openSession();
								 Transaction tx = sessionHQL.beginTransaction();
								 
								 String hqlUpdate = "delete from TB_OTHER_DOCCUMENT_UPLOAD where id=:id";
								 
							@SuppressWarnings({ "rawtypes", "deprecation" })
								int app = sessionHQL.createQuery(hqlUpdate)
								.setParameter("id", id).executeUpdate();
//								.setString("modified_by", username)
//								.setDate("modified_date", new Date()).
								tx.commit();
								sessionHQL.close();

								if (app > 0) {
									liststr.add("Delete Successfully.");
								} else {
									liststr.add("Delete Unsuccessfull");
								}
								json.put("msg",liststr.get(0));

							} catch (Exception e) {
								liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
								json.put("msg",liststr.get(0));
							}
							json.put("doc_eid",p_id);
							return json;
						}
						
						
						//------PG documnet upload delete api---
						@CrossOrigin( origins = "*" )		
						@RequestMapping(value = "/delete_PG_document_API", method = RequestMethod.POST)	
						public @ResponseBody JSONObject delete_PG_document_API(@RequestBody Map<String, String> data,HttpServletRequest request, HttpSession session,
								HttpSession sessionA) {
							JSONObject json = new JSONObject();
							int id = Integer.parseInt(data.get("id"));
							int p_id = Integer.parseInt(data.get("doc_ch_p_id")); //---p_id---
							String username = data.get("username").toString();
							List<String> liststr = new ArrayList<String>();
							
							
							try {
								Session sessionHQL = this.sessionFactory.openSession();
								 Transaction tx = sessionHQL.beginTransaction();
								 
								 String hqlUpdate = "delete from EDU_PG_REG_OTHER_DOC_UPLOAD where id=:id";
								 
							@SuppressWarnings({ "rawtypes", "deprecation" })
								int app = sessionHQL.createQuery(hqlUpdate)
								.setParameter("id", id).executeUpdate();
//								.setString("modified_by", username)
//								.setDate("modified_date", new Date()).
								tx.commit();
								sessionHQL.close();

								if (app > 0) {
									liststr.add("Delete Successfully.");
								} else {
									liststr.add("Delete Unsuccessfull");
								}
								json.put("msg",liststr.get(0));

							} catch (Exception e) {
								liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
								json.put("msg",liststr.get(0));
							}
							json.put("doc_eid",p_id);
							return json;
						}
						
						//document upload common method for view sign and Pic
						@CrossOrigin( origins = "*" )		
						@RequestMapping(value = "/ViewRefImageFileNcism_Download5_API", method = RequestMethod.GET)
						public @ResponseBody void ViewRefImageFileNcism_Download5_API(String id, String fildname,HttpServletRequest request,HttpServletResponse response/*,ModelMap model1*/) throws IOException{
							
							final int BUFFER_SIZE = 4096;
//						    String filePath = c.getRefImageFilePath(Integer.parseInt(id),Integer.parseInt(doc_id));	
							String filePath = "";
							if(fildname.equals("signature")) {
								filePath = docdao.getSignatureImagePath_Ncisam(id);
								System.err.println("getSignatureImagePath----------->   "+filePath);
							}
							
							if(fildname.equals("photograph")) {
								filePath = docdao.getPhotographImagePath_Ncisam(id);
								System.err.println("getPhotographImagePath------------>   "+filePath);
							}
//							String filePath = docdao.getSignatureImagePath(id);
						    //model.put("filePath",filePath);
						    ServletContext context = request.getSession().getServletContext();
					       try{
					    	   
					        if(filePath==null && filePath.isEmpty()  &&  filePath=="" && filePath=="null") 
					        {
					            @SuppressWarnings("deprecation")
								String fullPath =  request.getRealPath("/")+"assets\\db_img\\userphoto.png";
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
					        else
					        {
					        	    String fullPath =  filePath; 
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
					        }catch(Exception ex)
					        {
					        	@SuppressWarnings("deprecation")
								String fullPath =  request.getRealPath("/")+"admin/assets/db_img/noimage.jpeg";
					              File downloadFile = new File(fullPath);
					              FileInputStream inputStream = new FileInputStream(downloadFile);
					              String mimeType = context.getMimeType(fullPath);
					              if (mimeType == null) {
					                  mimeType = "application/octet-stream";
					              }
					              response.setContentType(mimeType);
					              response.setContentLength((int) downloadFile.length());
					              String headerKey = "Content-Disposition";
					              String headerValue = String.format("attachment; filename=\"%s\"",
					                      downloadFile.getName());
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
						
						//---NCH document upload common method for view sign and Pic----
						@CrossOrigin( origins = "*" )		
						@RequestMapping(value = "/ViewRefImageFileNch_Download5_API", method = RequestMethod.GET)
						public @ResponseBody void ViewRefImageFileNch_Download5_API(String id, String fildname,HttpServletRequest request,HttpServletResponse response/*,ModelMap model1*/) throws IOException{
							System.err.println("fildname"+fildname);
							final int BUFFER_SIZE = 4096;
//						    String filePath = c.getRefImageFilePath(Integer.parseInt(id),Integer.parseInt(doc_id));	
							String filePath = "";
							if(fildname.equals("signature")) {
								filePath = docdaoNCH.getSignatureImagePath(id);
								
								System.err.println("getSignatureImagePath------------>   "+filePath);
								
							}
							if(fildname.equals("photograph")) {
								
								filePath = docdaoNCH.getPhotographImagePath(id);
								System.err.println("getPhotographImagePathNCH------------>   "+filePath);
								
							}
//							String filePath = docdao.getSignatureImagePath(id);
						   // model.put("filePath",filePath);
						    ServletContext context = request.getSession().getServletContext();
					       try{
					        if(filePath==null && filePath.isEmpty()  &&  filePath=="" && filePath=="null") 
					        {
					            @SuppressWarnings("deprecation")
								String fullPath =  request.getRealPath("/")+"assets\\db_img\\userphoto.png";
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
					        else
					        {
					        	
					        	    String fullPath =  filePath; 
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
					        }catch(Exception ex)
					        {
					        	@SuppressWarnings("deprecation")
								String fullPath =  request.getRealPath("/")+"admin/assets/db_img/noimage.jpeg";
					              File downloadFile = new File(fullPath);
					              FileInputStream inputStream = new FileInputStream(downloadFile);
					              String mimeType = context.getMimeType(fullPath);
					              if (mimeType == null) {
					                  mimeType = "application/octet-stream";
					              }
					              response.setContentType(mimeType);
					              response.setContentLength((int) downloadFile.length());
					              String headerKey = "Content-Disposition";
					              String headerValue = String.format("attachment; filename=\"%s\"",
					                      downloadFile.getName());
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
						
						//---PG document upload common method for view sign and Pic----
						@CrossOrigin( origins = "*" )		
						@RequestMapping(value = "/ViewRefImageFilePG_Download5_API", method = RequestMethod.GET)
						public @ResponseBody void ViewRefImageFilePG_Download5_API(String id, String fildname,HttpServletRequest request,HttpServletResponse response/*,ModelMap model1*/) throws IOException{
							System.err.println("fildname"+fildname);
							final int BUFFER_SIZE = 4096;
//						    String filePath = c.getRefImageFilePath(Integer.parseInt(id),Integer.parseInt(doc_id));	
							String filePath = "";
							if(fildname.equals("signature")) {
								filePath = docdaoPG.getSignatureImagePath_PG(id);
								
								System.err.println("getSignatureImagePath------------>   "+filePath);
								
							}
							if(fildname.equals("photograph")) {
								
								filePath = docdaoPG.getPhotographImagePath_PG(id);
								System.err.println("getPhotographImagePathNCH------------>   "+filePath);
								
							}
//							String filePath = docdao.getSignatureImagePath(id);
						   // model.put("filePath",filePath);
						    ServletContext context = request.getSession().getServletContext();
					       try{
					        if(filePath==null && filePath.isEmpty()  &&  filePath=="" && filePath=="null") 
					        {
					            @SuppressWarnings("deprecation")
								String fullPath =  request.getRealPath("/")+"assets\\db_img\\userphoto.png";
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
					        else
					        {
					        	
					        	    String fullPath =  filePath; 
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
					        }catch(Exception ex)
					        {
					        	@SuppressWarnings("deprecation")
								String fullPath =  request.getRealPath("/")+"admin/assets/db_img/noimage.jpeg";
					              File downloadFile = new File(fullPath);
					              FileInputStream inputStream = new FileInputStream(downloadFile);
					              String mimeType = context.getMimeType(fullPath);
					              if (mimeType == null) {
					                  mimeType = "application/octet-stream";
					              }
					              response.setContentType(mimeType);
					              response.setContentLength((int) downloadFile.length());
					              String headerKey = "Content-Disposition";
					              String headerValue = String.format("attachment; filename=\"%s\"",
					                      downloadFile.getName());
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
						
						//-----declaration personal details----
						@CrossOrigin( origins = "*" )		
						@RequestMapping(value = "/getDeclarationPersonalDetails_API", method = RequestMethod.POST)					
						public @ResponseBody EDU_NCISM_REG_GRADU_PERSONAL_DTLS getDeclarationPersonalDetails_API(@RequestBody Map<String, String> data,HttpSession session) {
							
						Session sessiont = sessionFactory.openSession();
						String ch_eid = data.get("ch_eid");
							
						EDU_NCISM_REG_GRADU_PERSONAL_DTLS disDetails = pdd.getStudDetView_NcismByid(Integer.parseInt(ch_eid));
						
						return disDetails;
						}
						
						//-----NCH declaration personal details----
						@CrossOrigin( origins = "*" )		
						@RequestMapping(value = "/getDeclarationPersonalNCHDetails_API", method = RequestMethod.POST)					
						public @ResponseBody TB_PERSONAL_DETAILS getDeclarationPersonalNCHDetails_API(@RequestBody Map<String, String> data,HttpSession session) {
							
						Session sessiont = sessionFactory.openSession();
						String ch_eid = data.get("ch_eid");
					
						
						return pddNCH.getStudDetViewByid(Integer.parseInt(ch_eid));
						}
						
						
						//-----PG declaration personal details----
						@CrossOrigin( origins = "*" )		
						@RequestMapping(value = "/getDeclarationPersonalPGDetails_API", method = RequestMethod.POST)					
						public @ResponseBody EDU_PG_REG_PERSONAL_DETAILS getDeclarationPersonalPGDetails_API(@RequestBody Map<String, String> data,HttpSession session) {
							
						Session sessiont = sessionFactory.openSession();
						String ch_eid = data.get("ch_eid");
					
						
						return pddPG.getStudDetView_PG_Byid(Integer.parseInt(ch_eid));
						}
						
						//----declaration educational details-----
						@CrossOrigin( origins = "*" )		
						@RequestMapping(value = "/getEdu_Detail_Ncism_Ctrl_API", method = RequestMethod.POST)
						public @ResponseBody ArrayList<ArrayList<String>> getEdu_Detail_Ncism_Ctrl_API(@RequestBody Map<String, String> data ) {
							String  p_id = data.get("p_id");
							System.err.println("p_id---"+p_id);
							return pdd.getEdu_Detail_Ncism_data(p_id);
						}
						
						//----NCH declaration educational details-----
						@CrossOrigin( origins = "*" )		
						@RequestMapping(value = "/getEdu_Detail_Nch_Ctrl_API", method = RequestMethod.POST)
						public @ResponseBody ArrayList<ArrayList<String>> getEdu_Detail_Nch_Ctrl_API(@RequestBody Map<String, String> data ) {
							String  p_id = data.get("p_id");
							System.err.println("p_id---"+p_id);
							return pddNCH.getEdu_Detail_data(p_id);
						}
						
						//----PG declaration Graduation details-----
						@CrossOrigin( origins = "*" )		
						@RequestMapping(value = "/getGrdu_pg_Detail_Ctrl_API", method = RequestMethod.POST)
						public @ResponseBody ArrayList<ArrayList<String>> getGrdu_pg_Detail_Ctrl_API(@RequestBody Map<String, String> data) {
							String  p_id = data.get("p_id");
							
							return pddPG.getGrdu_pg_Detail_Data(p_id);
						}
						
						//----NCH declaration admission details---
						//---get_ayush_id_Nch_ctrl_API call
						
						//----declaration submit -----
						@CrossOrigin(origins = "*")
						@RequestMapping(value = "/Student_Personal_Ncism_Details_Action_API", method=RequestMethod.POST)
						public @ResponseBody JSONObject Student_Personal_Ncism_Details_Action_API(@RequestBody Map<String, String> data,
								HttpServletRequest request, HttpSession sessionEdit) {

							String eid3 = data.get("p_id");
							JSONObject json = new JSONObject();
							System.err.println("--------------"+eid3+"=================");
							try {
								
								
									 Session sessionHQL = this.sessionFactory.openSession();
									 Transaction tx = sessionHQL.beginTransaction();
									 
									 String hqlUpdate = "update EDU_NCISM_REG_GRADU_PERSONAL_DTLS set status=:status where id=:id";
									 
									 @SuppressWarnings({ "rawtypes", "deprecation" })
									 int app = sessionHQL.createQuery(hqlUpdate)
									.setParameter("id", Integer.parseInt(eid3))
									.setParameter("status", 1).executeUpdate();
									tx.commit();
									sessionHQL.close();
						
									if (app > 0) {
										json.put("msg","Form Submitted Successfully");
										json.put("ch_eid", eid3);
									} else {
										json.put("msg","Form Not Submitted");
										//return new ModelAndView("redirect:Std_pers_details_Ncism_Url");
									}
							} catch (Exception e) {
								e.printStackTrace();
								json.put("msg","Form Not Submitted");
								//return new ModelAndView("redirect:Std_pers_details_Ncism_Url");
							}
							return json;
						}
						
						//----NCH declaration submit -----
						@CrossOrigin(origins = "*")
						@RequestMapping(value = "/Student_Personal_Nch_Details_Action_API", method=RequestMethod.POST)
						public @ResponseBody JSONObject Student_Personal_Nch_Details_Action_API(@RequestBody Map<String, String> data,
								HttpServletRequest request, HttpSession sessionEdit) {

							String eid3 = data.get("p_id");
							JSONObject json = new JSONObject();
							System.err.println("--------------"+eid3+"=================");
							try {
								
								
									 Session sessionHQL = this.sessionFactory.openSession();
									 Transaction tx = sessionHQL.beginTransaction();
									 
									 String hqlUpdate = "update TB_PERSONAL_DETAILS set status=:status where id=:id";
									 
									 @SuppressWarnings({ "rawtypes", "deprecation" })
									 int app = sessionHQL.createQuery(hqlUpdate)
									.setParameter("id", Integer.parseInt(eid3))
									.setParameter("status", 1).executeUpdate();
									tx.commit();
									sessionHQL.close();
						
									if (app > 0) {
										json.put("msg","Form Submitted Successfully");
										json.put("ch_eid", eid3);
									} else {
										json.put("msg","Form Not Submitted");
										//return new ModelAndView("redirect:Std_pers_details_Ncism_Url");
									}
							} catch (Exception e) {
								e.printStackTrace();
								json.put("msg","Form Not Submitted");
								//return new ModelAndView("redirect:Std_pers_details_Ncism_Url");
							}
							return json;
						}
						
						//----PG declaration submit -----
						@CrossOrigin(origins = "*")
						@RequestMapping(value = "/Student_Personal_PG_Details_Action_API", method=RequestMethod.POST)
						public @ResponseBody JSONObject Student_Personal_PG_Details_Action_API(@RequestBody Map<String, String> data,
								HttpServletRequest request, HttpSession sessionEdit) {

							String eid3 = data.get("p_id");
							JSONObject json = new JSONObject();
							System.err.println("--------------"+eid3+"=================");
							try {
								
								
									 Session sessionHQL = this.sessionFactory.openSession();
									 Transaction tx = sessionHQL.beginTransaction();
									 
									 String hqlUpdate = "update EDU_PG_REG_PERSONAL_DETAILS set status=:status where id=:id";
									
									 
									 @SuppressWarnings({ "rawtypes", "deprecation" })
									 int app = sessionHQL.createQuery(hqlUpdate)
									.setParameter("id", Integer.parseInt(eid3))
									.setParameter("status", 1).executeUpdate();
									tx.commit();
									sessionHQL.close();
						
									if (app > 0) {
										json.put("msg","Form Submitted Successfully");
										json.put("ch_eid", eid3);
									} else {
										json.put("msg","Form Not Submitted");
										//return new ModelAndView("redirect:Std_pers_details_Ncism_Url");
									}
							} catch (Exception e) {
								e.printStackTrace();
								json.put("msg","Form Not Submitted");
								//return new ModelAndView("redirect:Std_pers_details_Ncism_Url");
							}
							return json;
						}
						
						//---- NCISM and NCH and PG view document for student view form
						

							@CrossOrigin(origins = "*")
							@RequestMapping(value = "/kmlFileDownloadFinalDynemic44_API",method = RequestMethod.GET)
							public @ResponseBody void kmlFileDownload44_API(String id,String val, String fildname,
							 HttpServletRequest request,HttpServletResponse response,
								HttpSession session) throws IOException{
								
							final int BUFFER_SIZE = 4096;
							System.out.println("fildname==============="+fildname);
									
							//String base64EncodedDcryptedad2 = "";
							//String enckey2 = "commonPwdEncKeys";
							//id = id.replace(" ", "+");
							//base64EncodedDcryptedad2 = hex_asciiDao.decrypt(String.valueOf(id), enckey2, session);
							
							//String id2 = (base64EncodedDcryptedad2);	
							
							String filePath = cdao.getFilePath_DynemicQueryForDoc(id,val,fildname);
							
							
							//model.put("filePath",filePath);
							
							
							ServletContext context = request.getSession().getServletContext();
							try{
							if(filePath==null && filePath.isEmpty()  &&  filePath=="" && filePath=="null") 
							{
							    @SuppressWarnings("deprecation")
								String fullPath =  request.getRealPath("/")+"admin/js/img/No_Image.jpg";
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
							else
							{
								
								    String fullPath =  filePath; 
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
							}catch(Exception ex)
							{
								@SuppressWarnings("deprecation")
								String fullPath =  request.getRealPath("/")+"admin/js/images/No_Image.jpg";
							      File downloadFile = new File(fullPath);
							      FileInputStream inputStream = new FileInputStream(downloadFile);
							      String mimeType = context.getMimeType(fullPath);
							      if (mimeType == null) {
							          mimeType = "application/octet-stream";
							      }
							      response.setContentType(mimeType);
							      response.setContentLength((int) downloadFile.length());
							      String headerKey = "Content-Disposition";
							      String headerValue = String.format("attachment; filename=\"%s\"",
							              downloadFile.getName());
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
							
							//-----get uploaded document for view page
							@CrossOrigin( origins = "*" )	
							@RequestMapping(value = "/getuploaded_doc_Ncism_Ctrl_API", method = RequestMethod.POST)
						 	public @ResponseBody ArrayList<ArrayList<String>> getuploaded_doc_Ncism_Ctrl_API(String p_id) {
						    	ArrayList<ArrayList<String>> data = pdd.getuploaded_doc_Ncism_data(p_id);
						    	return data;
						 	}
							
							//-----NCH get uploaded document for view page
							@CrossOrigin( origins = "*" )	
							@RequestMapping(value = "/getuploaded_doc_Nch_Ctrl_API", method = RequestMethod.POST)
						 	public @ResponseBody ArrayList<ArrayList<String>> getuploaded_doc_Nch_Ctrl_API(String p_id) {
						    	ArrayList<ArrayList<String>> data = pddNCH.getuploaded_doc_NCH_data(p_id);
						    	return data;
						 	}
							
							//-----get uploaded court order for view page
							@CrossOrigin( origins = "*" )	
							 @RequestMapping(value = "/getuploaded_Court_Order_NCISM_Ctrl_API", method = RequestMethod.POST)
							 	public @ResponseBody ArrayList<ArrayList<String>> getuploaded_Court_Order_NCISM_Ctrl_API(String p_id) {
							    	ArrayList<ArrayList<String>> data = pdd.getuploaded_Court_Order_NCISM_data(p_id);
							    	return data;
							 	} 
							
							//-----NCH get uploaded court order for view page
							@CrossOrigin( origins = "*" )	
							 @RequestMapping(value = "/getuploaded_Court_Order_NCH_Ctrl_API", method = RequestMethod.POST)
							 	public @ResponseBody ArrayList<ArrayList<String>> getuploaded_Court_Order_NCH_Ctrl_API(String p_id) {
							    	ArrayList<ArrayList<String>> data =  pddNCH.getuploaded_Court_Order_NCH_data(p_id);
							    	return data;
							 	} 
							
							//---get page redirection----
							@CrossOrigin( origins = "*" )		
							@RequestMapping(value = "/getRedirectPage_API", method = RequestMethod.POST)					
							public @ResponseBody Integer getRedirectPage_API(@RequestBody Map<String, String> data,HttpSession session) {
								
							Session sessionHQL = sessionFactory.openSession();
							Integer check = 0;
							String userId = data.get("userId");
							ArrayList<ArrayList<String>> listp = da.get_p_id_Ncism_pers_info_data(Integer.parseInt(userId));
							System.err.println(listp);
							if (listp.size() > 0) {
								Query qry = sessionHQL.createQuery("select status from EDU_NCISM_REG_GRADU_PERSONAL_DTLS where p_id=:p_id");
								qry.setParameter("p_id", Integer.parseInt(userId));
								check = (Integer) qry.uniqueResult();	
							}
							return check;
							}
							
							//---NCH get page redirection----
							@CrossOrigin( origins = "*" )		
							@RequestMapping(value = "/getRedirectPageNCH_API", method = RequestMethod.POST)					
							public @ResponseBody Integer getRedirectPageNCH_API(@RequestBody Map<String, String> data,HttpSession session) {
								
							Session sessionHQL = sessionFactory.openSession();
							Integer check = 0;
							String userId = data.get("userId");
							ArrayList<ArrayList<String>> listp =  daNCH.get_p_id_pers_info_data(Integer.parseInt(userId));
							System.err.println(listp);
							if (listp.size() > 0) {
								Query qry = sessionHQL.createQuery("select status from TB_PERSONAL_DETAILS where p_id=:p_id");
								qry.setParameter("p_id", Integer.parseInt(userId));
								System.err.println("qry23423---"+qry);
								check = (Integer) qry.uniqueResult();	
							}
								System.err.println("check---"+check);
							return check;
							}
							
							//---PG get page redirection----
							@CrossOrigin( origins = "*" )		
							@RequestMapping(value = "/getRedirectPagePG_API", method = RequestMethod.POST)					
							public @ResponseBody Integer getRedirectPagePG_API(@RequestBody Map<String, String> data,HttpSession session) {
								
							Session sessionHQL = sessionFactory.openSession();
							Integer check = 0;
							String userId = data.get("userId");
							
							ArrayList<ArrayList<String>> listp= daPG.get_p_id_pers_info_data_pg(Integer.parseInt(userId));
							System.err.println(listp);
							
							if (listp.size() > 0) {
								Query qry = sessionHQL.createQuery("select status from EDU_PG_REG_PERSONAL_DETAILS where p_id=:p_id");
								qry.setParameter("p_id", Integer.parseInt(userId));
								check = (Integer) qry.uniqueResult();	
							}									
							
							return check;
							}
							
							
							
							//-------PG Graduation Data save----
							@CrossOrigin(origins = "*")
							@RequestMapping(value = "/Graduation_Det_PG_Action_API", method = RequestMethod.POST)
							public @ResponseBody String Graduation_Det_PG_Action_API( @RequestBody Map<String, String> data,HttpServletRequest request,  HttpSession session 
								) throws ParseException {
								String msg = "";
								EDU_PG_REG_GRADU_DTLS td = new EDU_PG_REG_GRADU_DTLS();
								String username = data.get("username");
								Date date = new Date();
								
								Session sessionHQL = this.sessionFactory.openSession();
								Transaction tx = sessionHQL.beginTransaction();
								
								try {
								String p_id = data.get("p_id");
								
									int temp = 0;
									
											
											String editid = data.get("editid");
											
											if(editid.toString() == "0") {
											
												EDU_PG_REG_GRADU_DTLS xray = new EDU_PG_REG_GRADU_DTLS();
											
											String name_of_exam = data.get("name_of_exam");
											String month_year = data.get("month_year");
											String university = data.get("university");
											String no_of_attempts = data.get("no_of_attempts");
											String universityother = data.get("universityother");
											

											xray.setName_of_exam(name_of_exam);
										
												System.err.println("universityother-------"+universityother);
												
												xray.setName_of_exam(name_of_exam);
												xray.setMonth_year(month_year);
												xray.setUniversity(university);
												xray.setNo_of_attempts(no_of_attempts);
												xray.setUniversityother(universityother);
												xray.setCreated_by(username);
												xray.setCreated_date(date);
												xray.setP_id(Integer.parseInt(p_id));
												sessionHQL.save(xray);
												sessionHQL.flush();
												sessionHQL.clear();
											}else {
												
												EDU_PG_REG_GRADU_DTLS add = (EDU_PG_REG_GRADU_DTLS) sessionHQL
														.get(EDU_PG_REG_GRADU_DTLS.class, Integer.parseInt(editid));
												
												String name_of_exam = data.get("name_of_exam");
												String month_year = data.get("month_year");
												String university = data.get("university");
												String no_of_attempts = data.get("no_of_attempts");
												String universityother = data.get("universityother");
												
												
												add.setName_of_exam(name_of_exam);
												add.setMonth_year(month_year);
												add.setUniversity(university);
												add.setNo_of_attempts(no_of_attempts);
												add.setUniversityother(universityother);
												add.setCreated_by(username);
												add.setCreated_date(date);
												add.setId(Integer.parseInt(editid));
												sessionHQL.update(add);
												sessionHQL.flush();
												sessionHQL.clear();
											}
											
											
											String semwiseeditid = data.get("semwiseeditid");
											
									System.err.println("obtainmark_y1_2----dsfhfbuifhuhfi->     ");
							
										System.err.println("obtainmark_y1_2---in-->     ");
										
										if(semwiseeditid.toString() == "0") {
										EDU_PG_REG_GRADU_SEMWISE_DTLS semwise = new EDU_PG_REG_GRADU_SEMWISE_DTLS();
											
												
												String subject = data.get("subject");
												String semwise_no_of_atmpts = data.get("semwise_no_of_atmpts");
												String maxmark_y1_ = data.get("maxmark_y1");
												String obtainmark_y1_ = data.get("obtainmark_y1");
												String maxmark_y2_ = data.get("maxmark_y2");
												String obtainmark_y2_ = data.get("obtainmark_y2");
												String maxmark_y3_ = data.get("maxmark_y3");
												String obtainmark_y3_ = data.get("obtainmark_y3");
												String maxmark_y4_ = data.get("maxmark_y4");
												String obtainmark_y4_ = data.get("obtainmark_y4");
												
												
												System.err.println("obtainmark_y1_2----->     "+ obtainmark_y1_);
												
												
												
												String percentage_of_marks = data.get("percentage_of_marks");
												String remarks = data.get("remarks");
												
												semwise.setSubject(subject);
												semwise.setSemwise_no_of_atmpts(semwise_no_of_atmpts);
												semwise.setMaxmark_y1_(Integer.parseInt(maxmark_y1_));
												semwise.setObtainmark_y1_(Integer.parseInt(obtainmark_y1_));
												semwise.setMaxmark_y2_(Integer.parseInt(maxmark_y2_));
												semwise.setObtainmark_y2_(Integer.parseInt(obtainmark_y2_));
												semwise.setMaxmark_y3_(Integer.parseInt(maxmark_y3_));
												semwise.setObtainmark_y3_(Integer.parseInt(obtainmark_y3_));
												semwise.setMaxmark_y4_(Integer.parseInt(maxmark_y4_));
												semwise.setObtainmark_y4_(Integer.parseInt(obtainmark_y4_));
												semwise.setPercentage_of_marks(percentage_of_marks);
												semwise.setRemarks(remarks);
												semwise.setCreated_by(username);
												semwise.setCreated_date(date);
												semwise.setP_id(Integer.parseInt(p_id));
												sessionHQL.save(semwise);
												sessionHQL.flush();
												sessionHQL.clear();
										}else {
											
											EDU_PG_REG_GRADU_SEMWISE_DTLS semwiseadd = (EDU_PG_REG_GRADU_SEMWISE_DTLS) sessionHQL
													.get(EDU_PG_REG_GRADU_SEMWISE_DTLS.class, Integer.parseInt(semwiseeditid));
											
											String subject = data.get("subject");
											String semwise_no_of_atmpts = data.get("semwise_no_of_atmpts");
											String maxmark_y1_ = data.get("maxmark_y1_");
											String obtainmark_y1_ = data.get("obtainmark_y1_");
											String maxmark_y2_ = data.get("maxmark_y2_");
											String obtainmark_y2_ = data.get("obtainmark_y2_");
											String maxmark_y3_ = data.get("maxmark_y3_");
											String obtainmark_y3_ = data.get("obtainmark_y3_");
											String maxmark_y4_ = data.get("maxmark_y4_");
											String obtainmark_y4_ = data.get("obtainmark_y4_");
											
											String percentage_of_marks = data.get("percentage_of_marks");
											String remarks = data.get("remarks");
											
											
											
											System.err.println("obtainmark_y1_2----->     "+ obtainmark_y1_);
											
											semwiseadd.setSubject(subject);
											semwiseadd.setSemwise_no_of_atmpts(semwise_no_of_atmpts);
											semwiseadd.setMaxmark_y1_(Integer.parseInt(maxmark_y1_));
											semwiseadd.setObtainmark_y1_(Integer.parseInt(obtainmark_y1_));
											semwiseadd.setMaxmark_y2_(Integer.parseInt(maxmark_y2_));
											semwiseadd.setObtainmark_y2_(Integer.parseInt(obtainmark_y2_));
											semwiseadd.setMaxmark_y3_(Integer.parseInt(maxmark_y3_));
											semwiseadd.setObtainmark_y3_(Integer.parseInt(obtainmark_y3_));
											semwiseadd.setMaxmark_y4_(Integer.parseInt(maxmark_y4_));
											semwiseadd.setObtainmark_y4_(Integer.parseInt(obtainmark_y4_));
											semwiseadd.setPercentage_of_marks(percentage_of_marks);
											semwiseadd.setRemarks(remarks);
											semwiseadd.setModified_by(username);
											semwiseadd.setModified_date(date);
											semwiseadd.setId(Integer.parseInt(semwiseeditid));
											sessionHQL.update(semwiseadd);
											sessionHQL.flush();
											sessionHQL.clear();
										}
											
								
								tx.commit();
							 msg = "Data saved Successfully";
								
							} catch (RuntimeException e) {
								e.printStackTrace();
								try {
									tx.rollback();
									msg = "roll back transaction";
								} catch (RuntimeException rbe) {
									msg = "Couldn't roll back transaction " + rbe;
								}
								throw e;
							} finally {
								if (sessionHQL != null) {
									sessionHQL.close();
								}
							}
								
								return msg;
							}
					
							
							//------ PG Name of Exam list----
							@CrossOrigin( origins = "*" )
							@RequestMapping(value = "/getname_of_examList_API", method = RequestMethod.POST)
							public @ResponseBody List<EDU_PG_GRADU_EXAMNAME>  getname_of_examList_API(@RequestBody Map<String, String> data1) throws Exception{
								
								String roleid = data1.get("roleid");
								String username = data1.get("username");
								
								int user = daPG.getUsername_pg(username);
								String staff_lvl = cdao.getStaffLvlfromRoleid(roleid);
								
								ArrayList<ArrayList<String>> data = daPG.get_ayush_id_data_pg(String.valueOf(user),staff_lvl);
								String sys_id = data.get(0).get(5);
								String deg_id = data.get(0).get(6);
								
								List<EDU_PG_GRADU_EXAMNAME>  list =common.getnameofexam_pg_graduformList(sessionFactory,sys_id,deg_id);
								 System.out.println("getAllNcism_PersdetailsList---"+list);
									return list;

							}
							
							//------ PG Name of Exam list----
							@CrossOrigin( origins = "*" )
							@RequestMapping(value = "/getuniversitylist_API", method = RequestMethod.POST)
							public @ResponseBody List<String>  getuniversitylist_API(@RequestBody Map<String, String> data) throws Exception{
								
								String roleid = data.get("roleid");
								
								String staff_lvl = cdao.getStaffLvlfromRoleid(roleid);
								
								List<String>  list =common.getuniversitylistbyStafflevel(sessionFactory,staff_lvl);
								 System.out.println("getAllNcism_PersdetailsList---"+list);
									return list;

							}
							
							//------ PG Name of Exam list----
							@CrossOrigin( origins = "*" )
							@RequestMapping(value = "/getSubjectList_API", method = RequestMethod.POST)
							public @ResponseBody ArrayList<ArrayList<String>>  getSubjectList_API(@RequestBody Map<String, String> data1) throws Exception{

								String roleid = data1.get("roleid");
								String username = data1.get("username");
								
								int user = daPG.getUsername_pg(username);
								String staff_lvl = cdao.getStaffLvlfromRoleid(roleid);
								
								ArrayList<ArrayList<String>> data = daPG.get_ayush_id_data_pg(String.valueOf(user),staff_lvl);
								String sys_id = data.get(0).get(5);
								String deg_id = data.get(0).get(6);
								
								ArrayList<ArrayList<String>> list = cdao.getSubjectForpg_graduform(sys_id,deg_id);
								 System.out.println("getAllNcism_PersdetailsList---"+list);
									return list;

							}
							
							
							//------ PG Edit Graduation details----
							@CrossOrigin( origins = "*" )
							@RequestMapping(value = "/edit_gradu_det_API", method = RequestMethod.POST)
							public @ResponseBody ArrayList<ArrayList<String>>  edit_gradu_det_API(@RequestBody Map<String, String> data) throws Exception{

								String username = data.get("username");
								
								int user = daPG.getUsername_pg(username);
								ArrayList<ArrayList<String>> listp=daPG.get_p_id_pers_info_data_pg(user);
								
								String p_id = listp.get(0).get(0);
								
								ArrayList<ArrayList<String>> list =  gddao.getaddmoredata1(p_id);
								 System.out.println("getAllNcism_PersdetailsListPG---"+list);
									return list;

							}
							
							//------ PG Edit Graduation Same Wise details----
							@CrossOrigin( origins = "*" )
							@RequestMapping(value = "/edit_gradu_semwise_det_API", method = RequestMethod.POST)
							public @ResponseBody ArrayList<ArrayList<String>>  edit_gradu_semwise_det_API(@RequestBody Map<String, String> data) throws Exception{

								String username = data.get("username");
								
								int user = daPG.getUsername_pg(username);
								ArrayList<ArrayList<String>> listp=daPG.get_p_id_pers_info_data_pg(user);
								
								String p_id = listp.get(0).get(0);
								
								ArrayList<ArrayList<String>> list =  gddao.getaddmoredata2(p_id);
								 System.out.println("getAllNcism_PersdetailsListPG---"+list);
									return list;

							}
							
							//-----PG search graduation data----
							@CrossOrigin( origins = "*" )
							@RequestMapping(value = "/getFilterGraduation_PG_data_API", method = RequestMethod.POST)
							public @ResponseBody ArrayList<ArrayList<String>> getFilterGraduation_PG_data_API(@RequestBody Map<String, String> data) {
								
								int startPage = 0;
								int pageLength = 1000;
								String Search = data.get("");
								String orderColunm = "";
								String orderType = "ASC";
								String name_of_exam = data.get("name_of_exam");
								String month_year = data.get("month_year");
								String university = data.get("university");
								String no_of_attempts = data.get("no_of_attempts");
								String username = data.get("username");
									
									int userid = daPG.getUsername_pg(username);

								return gddao.DataTableGraduation_PGDataList(startPage, pageLength, Search, orderColunm, orderType,name_of_exam,month_year,university,no_of_attempts);
							}
							
							//-----------------ADMISSION MNGT--------- API---END------(REGISTRATION)--------
					
					
					//------------------LECTURE API START---------------------
					
					////////////////////////////////////////STUDENT LECTURE PLAN DATA FETCH FROM TIME TABLE BY SELECTING DATE////////////////////////////////////////
					@CrossOrigin( origins = "*" )		
					@RequestMapping(value = "/getLecturedetailsbyDate_API", method = RequestMethod.POST)					
					public @ResponseBody ArrayList<ArrayList<String>> LectureDataByDate_API(@RequestBody Map<String, String> data,HttpSession session) {
						
					Session sessiont = sessionFactory.openSession();
					String ldate = data.get("ldate");
					String professional = data.get("professional");
					String userid = data.get("userId");
					System.out.println("ldate---"+ldate+" professional---"+professional+" userid---"+userid);
					Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", (Integer.parseInt(userid)));
					List<UserLogin> institute_idList = q1.list();
					sessiont.close();
					
					int institute_id = institute_idList.get(0).getInstitute_id();
					System.err.println("professional---------------->"+professional);
					
					
					return Sdao.LectureDataByDate(institute_id,ldate, Integer.parseInt(professional));
					}
					
					
					//------ course list----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getCourceList_API", method = RequestMethod.GET)
					public @ResponseBody List<EDU_LMS_COURSE_MASTER>  getCourceList_API() throws Exception{							
						
						 Session session = sessionFactory.openSession();
						 Transaction tx = session.beginTransaction();
						 Query q0 = session.createQuery("from EDU_LMS_COURSE_MASTER where type_of_content_id = 5");
						 
						 List<EDU_LMS_COURSE_MASTER> CourceList = (List<EDU_LMS_COURSE_MASTER>) q0.list();
					        session.getTransaction().commit();
					        session.close();                
					       return CourceList;

					}
					
					//------ faculty list----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getFacultyData_API", method = RequestMethod.POST)
					public @ResponseBody List<ArrayList<String>>  getFacultyData_API(@RequestBody Map<String, String> data) throws Exception{
						
						String userid = data.get("userid");
						
						List<ArrayList<String>>  list =Sdao.getFacultyData(userid);// da.getBesicNcism_details(Integer.parseInt(userid));
						 System.out.println("getAllNcism_PersdetailsList---"+list);
							return list;

					}
					//------ faculty list for lecture----
					  @RequestMapping(value = "/getfacutlydetails1_API", method = RequestMethod.POST)
					  public @ResponseBody ArrayList<ArrayList<String>> getfacutlydetails1_API(@RequestBody Map<String, String> data) {
						  String course_id = data.get("course_id");
					         return Sdao.getfacultydetailsDao1(course_id);

					  }
					  
					//------ topic list----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getTopicList_API", method = RequestMethod.GET)
					public @ResponseBody List<CC_TB_TOPICS_MSTR>  getTopicList_API() throws Exception{							
						
						 Session session = sessionFactory.openSession();
						 Transaction tx = session.beginTransaction();
						 Query q0 = session.createQuery("from CC_TB_TOPICS_MSTR where status='1'");
						 
						 List<CC_TB_TOPICS_MSTR> getTopicList = (List<CC_TB_TOPICS_MSTR>) q0.list();
					        session.getTransaction().commit();
					        session.close();                
					       return getTopicList;

					}
					

					//------ Learning Object list----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getSubTopicList_API", method = RequestMethod.GET)
					public @ResponseBody List<CC_TB_SUB_TOPICS_MSTR>  getSubTopicList_API() throws Exception{							
						               
						 Session session = sessionFactory.openSession();
						 Transaction tx = session.beginTransaction();
						 Query q0 = session.createQuery("from CC_TB_SUB_TOPICS_MSTR where status='1'");
						 
						 @SuppressWarnings("unchecked")
						List<CC_TB_SUB_TOPICS_MSTR> getSubTopicList = (List<CC_TB_SUB_TOPICS_MSTR>) q0.list();
					        session.getTransaction().commit();
					        session.close();                
					       return getSubTopicList;
					}
					//------sub topic list----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getTopic_name1_API", method = RequestMethod.POST)
				  	public @ResponseBody ArrayList<ArrayList<String>> getTopic_name1_API(@RequestBody Map<String, String> data,
				  			HttpSession session) {
						String userid = data.get("userid");
						int course_id =  Integer.parseInt(data.get("course_id"));
						 return Sdao.getTopic_name1(course_id,userid);
				  	}
					
					//------sub topic list----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getSubTopic_name1_API", method = RequestMethod.POST)
				  	public @ResponseBody ArrayList<ArrayList<String>> getSubTopic_name_API(@RequestBody Map<String, String> data,
				  			HttpSession session) {
						String userid = data.get("userid");
						int topic_id =  Integer.parseInt(data.get("topic_id"));
				  	       return Sdao.getSubTopic_name1(topic_id,userid);
				  	}
					
					//------sub topic list----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getLearning_Objective1_API", method = RequestMethod.POST)
				  	public @ResponseBody ArrayList<ArrayList<String>> getLearning_Objective1_API(@RequestBody Map<String, String> data,
				  			HttpSession session) {
						String userid = data.get("userid");
						String topic_id =  data.get("topic_id");
						 return Sdao.getLearning_Objective1(topic_id,userid);
				  	}
					
					//------ Learning Object list----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getLearningObject_API", method = RequestMethod.GET)
					public @ResponseBody List<CC_TB_T3_LEARNING_OBJECT_CHILD>  getLearningObject_API() throws Exception{							
						
						 Session session = sessionFactory.openSession();
						 Transaction tx = session.beginTransaction();
						 Query q0 = session.createQuery("from CC_TB_T3_LEARNING_OBJECT_CHILD where status=0");
						 
						 List<CC_TB_T3_LEARNING_OBJECT_CHILD> LearningList = (List<CC_TB_T3_LEARNING_OBJECT_CHILD>) q0.list();
					        session.getTransaction().commit();
					        session.close();                
					       return LearningList;
					}
					
					//------ Learning Object list----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getInstructionalList_API", method = RequestMethod.GET)
					public @ResponseBody List<EDU_LEC_INSTRUCTION_METHOD_MSTR>  getInstructionalList_API() throws Exception{							
						
						 Session session = sessionFactory.openSession();
				  		 Transaction tx = session.beginTransaction();
				  		 Query q0 = session.createQuery("from EDU_LEC_INSTRUCTION_METHOD_MSTR");
				  		 
				  		 List<EDU_LEC_INSTRUCTION_METHOD_MSTR> InstructionalList = q0.list();
				  	      session.getTransaction().commit();
				  	      session.close();                
				  	     return InstructionalList;		 

					}
					
					//------save lecture----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getStudent_Details_API", method = RequestMethod.POST)
					public @ResponseBody String getStudent_Details_API(@RequestBody Map<String, String> data,HttpSession httpsession) throws ParseException { 
						
						String msg = "";
						
						Session sessiont = sessionFactory.openSession();
						String userid = data.get("userId");
						Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", (Integer.parseInt(userid)));
						List<UserLogin> institute_idList = q1.list();
						sessiont.close();
						int institute_id = institute_idList.get(0).getInstitute_id();
						
						 Date date = new Date();
						 					

						SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");
     						
					
						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
						EDU_LEC_PLAN_FOR_STUDENTS td =new EDU_LEC_PLAN_FOR_STUDENTS();
						int id = td.getId() > 0 ? td.getId() : 0;
						//int datacounthId =Integer.parseInt(data.get("datacounthId"));
//						System.err.println("datdacount hiddent----------->"+datacounthId);
						String role = data.get("role");
						 String username = data.get("username");
						String professional = data.get("professional");
					    String sdate = data.get("sdate");
						
						
						try {
							
//							for (int i = 1; i <= datacounthId; i++){
								
//							    String faculty = data.get("faculty"+i);
//								String course_name = data.get("course_name"+i);
//								String topic = data.get("topic"+i);
//								String learning_objective = data.get("learning_objective"+i);
//								String instructional_method = data.get("instructional_method"+i);
//								String btnradio = data.get("btnradio"+i);
							String start_time=data.get("start_time");
							String end_time=data.get("end_time");
						    	String faculty = data.get("faculty");
								String course_name = data.get("course_name");
								String topic = data.get("topic");
								String sub_topic = data.get("sub_topic");
								String learning_objective = data.get("learning_objective");
								String instructional_method = data.get("instructional_method");
								String btnradio = data.get("btnradio");
								
		
								
							System.err.println(sdate);
							Long c = (Long) sessionHQL.createQuery(
									"select count(*) from  EDU_LEC_PLAN_FOR_STUDENTS where professional=:professional and sdate=:sdate and "
									+ "start_time=:start_time and end_time=:end_time and faculty=:faculty and "
									+ "course_name=:course_name and student_id=:student_id and institute_id =: institute_id")
									.setParameter("professional", Integer.parseInt(professional))
									.setParameter("sdate", formate.parse(sdate))
									.setParameter("start_time",start_time)
									.setParameter("end_time",end_time)
									.setParameter("faculty", Integer.parseInt(faculty))
									.setParameter("course_name", Integer.parseInt(course_name))
									.setParameter("student_id",Integer.parseInt(userid))
									.setParameter("institute_id", institute_id)
									.uniqueResult();
						
							if (c == 0) {
								
								td.setProfessional(Integer.parseInt(professional));
								td.setSdate(formate.parse(sdate));
								td.setStart_time(start_time);
								td.setEnd_time(end_time);
								td.setFaculty(Integer.parseInt(faculty));
								td.setCourse_name(Integer.parseInt(course_name));
								td.setTopic(Integer.parseInt(topic));
								td.setSub_topic(Integer.parseInt(sub_topic));
								td.setLearning_objective(Integer.parseInt(learning_objective));
								td.setInstructional_method(Integer.parseInt(instructional_method));
								td.setStudent_id(Integer.parseInt(userid));
								td.setAttended(Integer.parseInt(btnradio));
								td.setInstitute_id(institute_id);
								
								td.setCreated_by(username);
								td.setCreated_role(role);
								td.setCreated_dt(date);
							
									sessionHQL.save(td);
									sessionHQL.flush();
									sessionHQL.clear();
									
									msg = "Data Saved Successfully.";
							
								} else {
									msg =  "Data Already Exist";
								}
							
						//}
							tx.commit();
						}catch (RuntimeException e) {
							
							try {
							msg = "roll back transaction";
								}
							catch (RuntimeException rbe) {
								msg = "Couldnot roll back transaction " + rbe;
							}
							throw e;
						} finally {
							if (sessionHQL != null) {
								sessionHQL.close();
							}
						}
					

						return msg;
					}
					
					//------search lecture----
					@PostMapping("/getFilter_Student_Lecturer_data_API")
					public @ResponseBody List<Map<String, Object>> getFilter_Student_Lecturer_data_API(@RequestBody Map<String, String> data,
							 HttpSession session)
					{
						int startPage = 0; int pageLength = 1000;
						String Search = data.get("Search");
						String orderColunm = "";
						String orderType  = "ASC";
						String professional  = data.get("professional");
						String course_name  = data.get("course_name");
						String faculty  = data.get("faculty");
						String topic  = data.get("topic");
						String learning_objective  = data.get("learning_objective"); 
						String instructional_method  = data.get("instructional_method"); 
						String Sdate = data.get("Sdate");
						String userId = data.get("userId").toString();
						String start_time= data.get("start_time");
						String end_time= data.get("end_time");
						String sub_topic= data.get("sub_topic");
						
						return Sdao.DataTableStudentLecturerDataList(startPage, pageLength, Search, orderColunm, orderType, professional,start_time,end_time,course_name, faculty, topic,sub_topic, learning_objective,  
								 instructional_method, Sdate,  userId);
					}
					
					
					//---get lecture data for edit----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value="/getLecturedetailsbyDateforEdit_API", method = RequestMethod.POST)
					public @ResponseBody ArrayList<ArrayList<String>> LectureDataByDateforEdit_API(@RequestBody Map<String, String> data,HttpSession session) {
					
					Session sessiont = sessionFactory.openSession();
					String userid = data.get("userId").toString();
					String sdate = data.get("sdate").toString();
					String professional = data.get("professional").toString();
					System.err.println("user---"+userid+"professional--"+professional);
					Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", Integer.parseInt(userid));
					List<UserLogin> institute_idList = q1.list();
					sessiont.close();
					int institute_id = institute_idList.get(0).getInstitute_id();
					int student_id = Integer.parseInt(userid);
					
					return Sdao.LectureDataByDateforEdit(institute_id,student_id,sdate,Integer.parseInt(professional));
					}
					
					//------edit lecture-------
					@RequestMapping(value = "/getStudent_DetailsforEdit_API", method = RequestMethod.POST)
					public @ResponseBody String getStudent_DetailsforEdit_API(@RequestBody Map<String, String> data,HttpSession httpsession) throws ParseException { 
						String msg="";
						
						
						Session sessiont = sessionFactory.openSession();
						String userid = data.get("userId");
						 String username = data.get("username");
						 String role =data.get("role");
						 
						Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", (Integer.parseInt(userid)));
						List<UserLogin> institute_idList = q1.list();
						sessiont.close();
						int institute_id = institute_idList.get(0).getInstitute_id();
						
						 Date date = new Date();
						 

						SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");
					
						
						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
						
						
						String professional = data.get("professional");
					    String sdate = data.get("sdate");
						
						try {
							
//							for (int i = 1; i <= datacounthId; i++){
								String start_time = data.get("start_time");
								String end_time = data.get("end_time");
							    String faculty = data.get("faculty");
								String course_name = data.get("course_name");
								String topic = data.get("topic");
								String sub_topic = data.get("sub_topic");
								String learning_objective = data.get("learning_objective");
								String instructional_method = data.get("instructional_method");
								String btnradio = data.get("btnradio");
								String EditHid = data.get("EditHid");
							

								EDU_LEC_PLAN_FOR_STUDENTS td =  (EDU_LEC_PLAN_FOR_STUDENTS)sessionHQL.get(EDU_LEC_PLAN_FOR_STUDENTS.class, Integer.parseInt(EditHid));
								
								
								td.setProfessional(Integer.parseInt(professional));
								td.setSdate(formate.parse(sdate));
								td.setStart_time(start_time);
								td.setEnd_time(end_time);
								td.setFaculty(Integer.parseInt(faculty));
								td.setCourse_name(Integer.parseInt(course_name));
								td.setTopic(Integer.parseInt(topic));
								td.setSub_topic(Integer.parseInt(sub_topic));
								td.setLearning_objective(Integer.parseInt(learning_objective));
								td.setInstructional_method(Integer.parseInt(instructional_method));
								td.setStudent_id(Integer.parseInt(userid));
								td.setAttended(Integer.parseInt(btnradio));
								td.setInstitute_id(institute_id);
							
								
								
								td.setModified_by(username);
								td.setCreated_role(role);
								td.setModified_dt(date);
								
							
									sessionHQL.update(td);
									sessionHQL.flush();
									sessionHQL.clear();
									
									
								
							
//						}
							tx.commit();
							msg="Data Updated Successfully";
						}catch (RuntimeException e) {
							
							try {
								msg = "roll back transaction";
								}
							catch (RuntimeException rbe) {
								msg = "Couldnot roll back transaction " + rbe;
							}
							throw e;
						} finally {
							if (sessionHQL != null) {
								sessionHQL.close();
							}
						}
					

						return msg;
					}
					
					
					 @CrossOrigin( origins = "*" )	
					 @RequestMapping(value = "/TotalLecture_API", method = RequestMethod.POST)
					 	public @ResponseBody String TotalLecture_API(@RequestBody Map<String, String> data) {
						 
							Session sessiont = sessionFactory.openSession();
						 String userid =  data.get("userId");
							Query q1 = sessiont.createQuery("from UserLogin where userId=:id").setParameter("id", (Integer.parseInt(userid)));
							List<UserLogin> institute_idList = q1.list();
							sessiont.close();
							int institute_id = institute_idList.get(0).getInstitute_id();
							
							
						 String professional ="0";
						 if(IAM.Getdegreeid_fetch(userid).get(0).get(2).equals("1")){
							 professional = "15";
				     	}else if(IAM.Getdegreeid_fetch(userid).get(0).get(2).equals("2")){
				     		 professional = "16";
				     	}else if(IAM.Getdegreeid_fetch(userid).get(0).get(2).equals("3")){
				     		 professional = "17";
				     	}
						 
						 String list = Sdao.TotalLecture(professional,institute_id);
					    	return list;
					 	}
					 
					 @CrossOrigin( origins = "*" )	
					 @RequestMapping(value = "/TotalAttendedLecture_API", method = RequestMethod.POST)
					 	public @ResponseBody String TotalAttendedLecture_API(@RequestBody Map<String, String> data) {
						 String userId = data.get("userId");
						 
						 String list = Sdao.TotalAttendedLecture(userId);
					    	return list;
					 	}
					 
					 @CrossOrigin( origins = "*" )	
					 @RequestMapping(value = "/TotalNotAttendedLecture_API", method = RequestMethod.POST)
					 	public @ResponseBody String TotalNotAttendedLecture_API(@RequestBody Map<String, String> data) {
						 String userId = data.get("userId");
						 
						 String list = Sdao.TotalNotAttendedLecture(userId);
					    	return list;
					 	}
					
					//------------------LECTURE API END---------------------
					
					//------------------STAFF ATTENDANCE API START---------------------
					@CrossOrigin( origins = "*" )
					@RequestMapping(value="/Faculty_Attend_Report_url_API", method = RequestMethod.POST)
					public @ResponseBody ArrayList<ArrayList<String>> Faculty_Attend_Report_url_API(@RequestBody Map<String, String> data, HttpSession session,
							@RequestParam(value = "msg", required = false) String msg) {
						
						ArrayList<ArrayList<String>> list = new ArrayList<>();
						String role = data.get("rolename").toString();
						String userid = data.get("userId").toString();
						System.err.println("\n \n userid---------------"+userid+"role-----"+role+"\n \n");

						try {
							
							String TCorAID = "";
							if(role.contains("NCH")) {
								Session sessionHQL = sessionFactory.openSession();
								Transaction tx = sessionHQL.beginTransaction();
								Query q = sessionHQL
										.createQuery("select ayush_id from EDU_LMS_TEACHER_DTL where user_id=:userid");
								q.setParameter("userid", userid);
								@SuppressWarnings("unchecked")
								List<String> clist = (List<String>) q.list();
								TCorAID = clist.get(0).toString();
								tx.commit();
								sessionHQL.close();
							}
							if(role.contains("NCISM")) {
								System.out.println("ncism9888");
								Session sessionHQL = sessionFactory.openSession();
								Transaction tx = sessionHQL.beginTransaction();
								Query q = sessionHQL
										.createQuery("select teacher_code from EDU_LMS_FACULTY_NCH where userid=:userid ");
								q.setParameter("userid", userid);
								@SuppressWarnings("unchecked")
								List<String> clist = (List<String>) q.list();
								TCorAID = clist.get(0).toString();
								tx.commit();
								sessionHQL.close();
							}
							
//							System.err.println("\n \n TCorAID---------------"+TCorAID+"\n \n");
							
							Calendar calendar = Calendar.getInstance();
							//int month = calendar.get(Calendar.MONTH) + 1;
							//int year = calendar.get(Calendar.YEAR);
							int month = Integer.parseInt(data.get("month"));
							int year =  Integer.parseInt(data.get("year"));
							String mo_yr = String.valueOf(year)+"-"+String.valueOf(month);
							if (month > 9) {
								//list = FARdao.faculty_attendance_report(String.valueOf(month), String.valueOf(year),TCorAID,role);
							}
							if (month < 10) {
								//list = FARdao.faculty_attendance_report("0" + String.valueOf(month), String.valueOf(year),TCorAID,role);
							}
							//model.put("msg", msg);
							//model.put("month", month);
							//model.put("mo_yr", mo_yr);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						return list;
					}
					

					public static String genrateOTP1(final int lengthOfOTP) {

						StringBuilder generatedOTP = new StringBuilder();
						SecureRandom secureRandom = new SecureRandom();

						try {

							secureRandom = SecureRandom.getInstance(secureRandom.getAlgorithm());

							for (int i = 0; i < lengthOfOTP; i++) {
								generatedOTP.append(secureRandom.nextInt(9));
							}
						} catch (NoSuchAlgorithmException e) {
							e.printStackTrace();
						}

						return generatedOTP.toString();
					}
					
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/genrateOTP_API", method = RequestMethod.POST)
					public @ResponseBody String genrateOTP_API(@RequestBody Map<String, String> data,HttpServletRequest request ) {
						String aadhar_no = data.get("aadhar_no");
						String practfactnch = "25";
				//	String OTP = genrateOTP1(6);
					String OTP = "110599";
						
//						if (practfactnch.toUpperCase().equals("25")) {
//							OTP = genrateOTP1(6);
//						}
						
						

						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
						request.getSession().setAttribute("login_role", practfactnch);
						String modifydate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
						BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

						Query	query = sessionHQL.createQuery("select userId from UserLogin where username=:username")
										.setParameter("username", aadhar_no);
								
								System.err.println("===sss====="+aadhar_no.replace("-", ""));
								if(query.uniqueResult() == null) {
									OTP = "0";
								}else {
								@SuppressWarnings("unchecked")
								int data2 = (int) query.uniqueResult();
								
			 					sessionHQL.flush();
								sessionHQL.clear();
							UserLogin INF= (UserLogin)sessionHQL.get(UserLogin.class,data2);
							
//							System.err.println("======"+aadhar_no+"==========="+aadhar_no.length()+"======="+hashedaadhar_no+"========"+hashedaadhar_no.length());

							INF.setUserName(aadhar_no);
//							System.err.println("========"+aadhar_no+"=========="+aadhar_no);

							String hashedPassword = passwordEncoder.encode(OTP);
							String role="";
							
							Query	query1 = sessionHQL.createQuery("select roleId from UserRole where userId=:user_id")
									.setParameter("user_id", data2);
							
							@SuppressWarnings("unchecked")
							int role_id = (int) query1.uniqueResult();
							
							if(role_id == 25 || role_id == 26 ||  role_id == 35  || role_id == 40 || role_id == 23 || role_id == 24|| role_id == 27 || role_id == 28 || role_id == 42 || role_id == 44|| role_id == 45|| role_id == 46) {
							INF.setPassword(hashedPassword);
							}
							INF.setEnabled(1);
							INF.setAccountNonExpired(1);
							INF.setAccountNonLocked(1);
							INF.setCredentialsNonExpired(1);
							INF.setAc_dc_date(modifydate);
//							if (!practfactnch.equals("25")) {
//								INF.setMobile_no(mobile_no);
//							}
							INF.setCreated_on(new Date());
							// sessionHQL.beginTransaction();

							sessionHQL.update(INF);
							sessionHQL.flush();
							sessionHQL.clear();
//						}
						
						tx.commit();
						
						if (practfactnch.equals("25")) {
							String text = "Your OTP is "+OTP+" for AYUSH Portal."; 
							try {
								
								
								System.err.println("OTP------->        "+OTP);
								
								emailsend.SendMail(request,INF.getEmail_id(),INF.getLogin_name(),"MOA Portal OTP",text,"","","");
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
								}
						return OTP;
					}
					//------------------STAFF ATTENDANCE API END---------------------
					
					// ------Notification----
					@CrossOrigin( origins = "*" )
					@PostMapping("/getnotifilist_API")
					public @ResponseBody List<Map<String, Object>> getnotifilist_API(
							@RequestBody Map<String, String> data, HttpSession session) {
						
						String role = data.get("role");
						String userid = data.get("userid");
						
						 //List<Map<String, Object>> list= notiDao.notiboxlist("", "", "", "", "",userid,role,"","","");

						return notiDao.notiboxlist("", "", "", "", "",userid,role,"","","");
					}

					
					//---------------MENTOR-MENTEE API START----------------
					
					//---search mentor-----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getSearchMentor_API", method = RequestMethod.POST) 
					public @ResponseBody List<Map<String, Object>> getSearchMentor_API(@RequestBody Map<String, String> data,HttpSession session) {
						
					   String a = data.get("a");
				       String userid = data.get("userid");				       
				       String role = data.get("role");
				       
				       String system = mdao.getSystemofStudent(userid,role).get(0).get("system").toString();

				       System.err.println("============="+system);

				       if (a != null && !a.trim().equals("")) {
				    	   return mdao.getSearchMentorDetails(a,system);
				       }else {
				    	   return null;
				       }
					}
					
					//----send request-----------
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getmentor_mentee_name_API", method = RequestMethod.POST)
					public @ResponseBody String getmentor_mentee_name_API(@RequestBody Map<String, String> data,SessionFactory sessionFactory,
							HttpServletRequest request) {
						EDU_MEN_MENTOR_MENTEE_REQUEST td = new EDU_MEN_MENTOR_MENTEE_REQUEST();
						
						String msg="";
						String faculty_user_id = data.get("faculty_user_id");						
						String userid = data.get("userId").toString();
						int id = td.getId() > 0 ? td.getId() : 0;
						Date date = new Date();
						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
						
						
						try {
							Long c = (Long) sessionHQL.createQuery(
									"select count(id) from  EDU_MEN_MENTOR_MENTEE_REQUEST where faculty_user_id=:faculty_user_id and student_user_id=:student_user_id  and status=:status and id !=:id")
									.setParameter("faculty_user_id", Integer.parseInt(faculty_user_id))
									.setParameter("student_user_id", Integer.parseInt(userid))
									.setParameter("status", 0)
									.setParameter("id", id).uniqueResult();
								
//							System.err.println("c------"+c+"---id---"+id);
									
							if (id == 0) {
								td.setFaculty_user_id(Integer.parseInt(faculty_user_id));
								td.setStudent_user_id(Integer.parseInt(userid));
								td.setStatus(0);
								td.setCreated_by(userid);
//								td.setCreated_role(role);
								td.setCreated_date(date);
								if (c == 0) {
									sessionHQL.save(td);
									sessionHQL.flush();
									sessionHQL.clear();
									msg="Request Sent Successfully.";
									
								} else {
									msg="You have Already Requested to this Mentor";
								}

								
							} 
							tx.commit();
							sessionHQL.close();
							System.err.println("check msg"+msg);
							return msg;	
					
						
						
						
					} catch (RuntimeException e) {
						try {

							msg = "roll back transaction";
						} catch (RuntimeException rbe) {
							msg = "Couldn�t roll back transaction " + rbe;
						}
						throw e;
					} finally {
						if (sessionHQL != null) {
							sessionHQL.close();
						}
					}
					
					}
					
					//----table search data-----
					@CrossOrigin( origins = "*" )
					@PostMapping("/getsearch_Mentor_Mentee_data_API")
					public @ResponseBody List<Map<String, Object>> getsearch_Mentor_Mentee_data_API(@RequestBody Map<String, String> data,HttpSession Session) {
						
						String stu_user_id = data.get("userId");
						String role_id = data.get("roleid");
						int startPage=0;
						int pageLength=100000;
						String Search = data.get("Search");
						String orderColunm = "";
						String orderType = "ASC"; 
						String login_name = data.get("login_name");
						
						return mdao.DataTableMentorDataList(startPage, pageLength, Search, orderColunm, orderType, login_name,stu_user_id,role_id);

					}
					
					//----get messages------
					@CrossOrigin( origins = "*" )
					@PostMapping("/getMessagesformenters_API")
					public @ResponseBody List<Map<String, Object>> getMessagesformenters_API(@RequestBody Map<String, String> data,HttpSession Session) throws ParseException {
						
						String faculty_user_id = data.get("faculty_user_id");
						String student_user_id = data.get("userId");
						String role_id = data.get("roleid");
						
						return mdao.getMesgsformenties(faculty_user_id,student_user_id,role_id);        
					}

					//----download pdf mentor mentee-----
					@CrossOrigin( origins = "*" )
					@SuppressWarnings("null")
					@RequestMapping(value = "/getDownloadPdfUrlforMMfile_API")
					public @ResponseBody String getDownloadPdfUrlforMMfile_API(String doc_id1, HttpServletRequest request, HttpSession session, HttpServletResponse response)
							throws IOException {

						String EXTERNAL_FILE_PATH = "";
						String msg = "";

						EXTERNAL_FILE_PATH = mdao.getFilePathQuery(Integer.parseInt(doc_id1));
						System.err.println("path----"+EXTERNAL_FILE_PATH);
						if (EXTERNAL_FILE_PATH != "" && EXTERNAL_FILE_PATH != null) {
							File file = null;
							file = new File(EXTERNAL_FILE_PATH);
							try {
								if (!file.exists()) {
									msg = "Sorry.The file you are looking for does not exist!";
									return msg;
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
								msg = "Downloaded Successfully";
								return msg;
							} catch (FileNotFoundException e) {
								//e.printStackTrace();
							}
						} 
						
						
						else {
							msg = "Sorry.The file you are looking for does not exist!";
							return msg;
						}
						return msg;
					}
					
					//---ask query save-----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getAskQueryMethodforcommunication_API" , method = RequestMethod.POST)
					public @ResponseBody String getAskQueryMethodforcommunication_API(MultipartFile file_input,String faculty_user_id,
							String student_user_id,String message,String role_id,String from_role_id,String username,HttpSession session) throws ParseException, IOException {
						
						
						
						String to_role_id = role_id;
						 
						
						String msg = "";
						int sid = 0;
						 
						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
						System.err.println("faculty_user_id-"+faculty_user_id+"\n-student_user_id-"+student_user_id+"\n-message-"+message);
						
						//SECURITY-----
							long filesize = file_input.getSize() / 1024;
							if (filesize > 200) {
								msg = "File size should be 200 kb or less";
								return msg;
							}
						//SECURITY-----
						
						try {
							EDU_Mentor_Mentee_communication sd =  new EDU_Mentor_Mentee_communication();
							
							sd.setCreated_by(username);
							sd.setCreated_date(new Date());
							sd.setFaculty_user_id(Integer.parseInt(faculty_user_id));
							sd.setStudent_user_id(Integer.parseInt(student_user_id));
							sd.setMessage(message);
							sd.setStatus(0);
							sd.setFrom_msg(Integer.parseInt(from_role_id));
							sd.setTo_msg(Integer.parseInt(to_role_id));
							if(!file_input.getOriginalFilename().equals("") && file_input.getOriginalFilename() != null) {
								
								System.out.println("fileName------"+file_input.getOriginalFilename());
								//System.out.println("fileNameSplit------"+file_input.getOriginalFilename().split("[.]"));
								System.out.println("fileNameSplitLenght------"+file_input.getOriginalFilename().split("[.]").length);
								
								 if (file_input.getOriginalFilename().split("[.]").length > 2) {
										msg = "Invalid file extension for Document";
										System.out.println("fileName_msg------"+msg);
										return msg;
								}
								
								
								String mmfile = common.fileupload(file_input.getBytes(), file_input.getOriginalFilename(),1, "mmfile");
								sd.setFile(mmfile);
							}
							
							sid = (int)sessionHQL.save(sd);
							sessionHQL.flush();
							sessionHQL.clear();
					
							if(sid > 0) {
								msg="Query Sent Successfully";
							}
							
							tx.commit();
							return msg;
						} catch (RuntimeException e) {
							try {
								msg="roll back transaction";
							} catch (RuntimeException rbe) {
								msg="Couldn't roll back transaction";
							}
							throw e;
						} finally {
							if (sessionHQL != null) {
								sessionHQL.close();
							}
						}
					}
					
					//------change msg status----
					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/ChangeMessagesStatus_API" , method = RequestMethod.POST)
					public @ResponseBody String ChangeMessagesStatus_API(@RequestBody Map<String, String> data,HttpSession session) throws ParseException {	
						 
						String msg = "";
						String msgIds = data.get("msgIds");
						String username = data.get("username").toString();
						Session sessionHQL = this.sessionFactory.openSession();
						Transaction tx = sessionHQL.beginTransaction();
						System.err.println("MsgIds---"+msgIds);
						
						String msgidsArr[] = msgIds.split(",");
						try {
							
							for(int i=0;i<msgidsArr.length;i++) {
								EDU_Mentor_Mentee_communication sd =  (EDU_Mentor_Mentee_communication) 
										sessionHQL.get(EDU_Mentor_Mentee_communication.class, Integer.parseInt(msgidsArr[i]));
								
								sd.setStatus(1);
								sd.setModified_by(username);
								sd.setModified_date(new Date());
								
								sessionHQL.update(sd);
								sessionHQL.flush();
								sessionHQL.clear();
							}
							tx.commit();
							return "Status Updated";
						} catch (RuntimeException e) {
							try {
								msg="roll back transaction";
							} catch (RuntimeException rbe) {
								msg="Couldn't roll back transaction";
							}
							throw e;
						} finally {
							if (sessionHQL != null) {
								sessionHQL.close();
							}
						}
					}
					//---------------MENTOR-MENTEE API END----------------
					
					
					//------VC SYSTEM API START---------------
					

					@CrossOrigin( origins = "*" )
					@RequestMapping(value = "/getMeeting_Link_API",method = RequestMethod.POST)
					public @ResponseBody String getMeeting_Link_API(@RequestBody Map<String, String> data) throws ParseException {
						String meet_id = data.get("meet_id");
						String join_name = data.get("join_name");
						String join_pass = data.get("join_pass");
//						String Link = "";
						meet_id = meet_id.split("id")[0];
						
						String uribase = "https://bisag.co.in/bigbluebutton/api/join?";
						String uripara = "";
						try {
							uripara = "fullName="+URLEncoder.encode(join_name, "UTF-8")
									+ "&meetingID="+meet_id
									+ "&password="+URLEncoder.encode(join_pass, "UTF-8")
									+ "&redirect=true";
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						char ch = '+';   
						uripara = uripara.replace(' ', ch);
						String key = "102kHTt9AHbPlJ8MfiZMvx9uGdcyMiJbtvNnlue8";
						String checksum = SHA1_encrypt("join"+uripara+key);
						System.err.println("-----checksum----"+checksum);
						String checksumpara = "&checksum="+checksum;
						String uri = uribase+uripara+checksumpara;
						System.err.println("-----uri----"+uri);
						return uri;
					}
					
					//---------SHA1_encrypt----------
					public static String SHA1_encrypt(String input)
				    {
				        try {
				            // getInstance() method is called with algorithm SHA-1
				            MessageDigest md = MessageDigest.getInstance("SHA-1");
				 
				            // digest() method is called
				            // to calculate message digest of the input string
				            // returned as array of byte
				            byte[] messageDigest = md.digest(input.getBytes());
				 
				            // Convert byte array into signum representation
				            BigInteger no = new BigInteger(1, messageDigest);
				 
				            // Convert message digest into hex value
				            String hashtext = no.toString(16);
				 
				            // Add preceding 0s to make it 32 bit
				            while (hashtext.length() < 32) {
				                hashtext = "0" + hashtext;
				            }
				 
				            // return the HashText
				            return hashtext;
				        }
				 
				        // For specifying wrong message digest algorithms
				        catch (NoSuchAlgorithmException e) {
				            throw new RuntimeException(e);
				        }
				    }
					
					//------VC SYSTEM API END----------------
					
			
	}

