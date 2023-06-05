package com.AyushEdu.controller.LMS_Institute;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AyushEdu.Common_Controller.CommonController;
import com.AyushEdu.Models.TB_STATE;
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.UserRole;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_INSTITUTE_REGISTRATION;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DISTRICT_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_UNIVERSITY_MSTR;
import com.AyushEdu.Models.LMS_Master.TB_COUNTRY;
import com.AyushEdu.config.EmailConfig;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.LMS_Institute.Institute_Registration_Dao;
import com.AyushEdu.dao.LMS_Master.Type_of_Degree_MstrDao;
import com.AyushEdu.validation.ValidationController;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Institute_Registration_Controller {
	@Autowired
	//@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	CommonController common;
	
	@Autowired
	Type_of_Degree_MstrDao  TDdao;
	
	@Autowired
	Institute_Registration_Dao IRdao;
	
	@Autowired
	 ValidationController validation;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	EmailConfig emailsend;

	@GetMapping(value = "/institute_registration_url")
	public ModelAndView institute_registration_url(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request, RedirectAttributes ra) {

		//SECURITY RAHUL
		if(request.getHeader("Referer") == null ) { 
			 return new ModelAndView("redirect:/landingpage");
		 }
		
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("institute_registration_url", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		
		model.put("msg", msg);
		model.addAttribute("langugae", "english");
		model.put("getUniversityList", common.getUniversityList(sessionFactory));
		model.put("MedCountryName", common.getMedCountryName(sessionFactory));
		model.put("MedStateName", common.getMedStateName(sessionFactory));
		model.put("MedDistrictName", common.getMedDistrictName(sessionFactory));
		model.put("ActiveInActiveList", common.getActiveInActiveList(sessionFactory));
		model.put("getSystemForAll", common.getSystemForAll(sessionFactory));
		model.addAttribute("degreelist",TDdao.getDataListofdegSysToD("ALL"));
//		HttpSession session1 = request.getSession(false);
//		SecurityContextHolder.clearContext();
//		session1 = request.getSession(false);
//			        if(session1.equals( "Suspicious Activity Detected,You have been logged out by Administrator")) {

//		session1.invalidate();
//			        }
//		for (Cookie cookie : request.getCookies()) {
//			cookie.setMaxAge(0);
//		}
		return new ModelAndView("Institute_Registration_Tiles");

	}
	

	@PostMapping(value = "/institute_registration_url_action")
	public ModelAndView institute_registration_url_action(
			@Validated @ModelAttribute("Category_cmd") EDU_LMS_INSTITUTE_REGISTRATION td, BindingResult result,
			HttpServletRequest request, ModelMap model, RedirectAttributes ra, HttpSession session,
			MultipartHttpServletRequest mul) throws IOException, ParseException {

		int id = td.getId() > 0 ? td.getId() : 0;
		Date date = new Date();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		try {

			if (request.getHeader("Referer") == null) {
				return new ModelAndView("landingpage");
			}
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("institute_registration_url", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}

			String txtInput = request.getParameter("txtInput").replaceAll("\\s", "").toString();
			String capcha = request.getSession().getAttribute("capcha").toString();
			System.err.println(txtInput + "--" + capcha);

			System.err.println("------method in ");

			String institute_name = request.getParameter("institute_name");
			String institute_email = request.getParameter("institute_email");
			String institute_mob_no = request.getParameter("institute_mob_no");
			String university_id = request.getParameter("university_id");
			String country_id = request.getParameter("country_id");

			String state_id = request.getParameter("state_id");
			String district_id = request.getParameter("district_id");
			String system_id = request.getParameter("system_id");
			String code = request.getParameter("code");
			String address = request.getParameter("address");
			String upload_image = dm(request, mul, session, "upload_image");
			MultipartFile upload_image_FILE = mul.getFile("upload_image");
			String college_abbr = request.getParameter("college_abbr");
			String total_sanction_seat =request.getParameter("total_sanction_seat");
			
			if (institute_name.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Institute Name.");
				return new ModelAndView("redirect:institute_registration_url");
			}
			if (validation.maxlengthcheck100(institute_name) == false) {
				ra.addAttribute("msg", "Institute Name " + validation.MaxlengthcheckMSG100);
				return new ModelAndView("redirect:institute_registration_url");
			}
			if (validation.isOnlyAlphabetDASH(institute_name) == false) {
				ra.addAttribute("msg", "Institute Name " + validation.isOnlyAlphabetMSGDASH);
				return new ModelAndView("redirect:institute_registration_url");
			}
			if (college_abbr.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter College Abbreviation.");
				return new ModelAndView("redirect:institute_registration_url");
			}
//			if (validation.maxlength10(institute_name) == false) {
//				ra.addAttribute("msg","College Abbreviation "+ validation.MaxlengthMSG10);
//				return new ModelAndView("redirect:institute_registration_url");
//			}
			if (code.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Code");
				return new ModelAndView("redirect:institute_registration_url");
			}

			if (validation.isOnlyAlphabetNumeric(code) == false) {
				ra.addAttribute("msg", "Code " + validation.isOnlyAlphabetNumericMSG);
				return new ModelAndView("redirect:institute_registration_url");
			}

			if (validation.maxlengthcheckneetindiarank(code) == false) {
				ra.addAttribute("msg", " code " + validation.MaxlengthcheckMSGneetallindiarank7);
				return new ModelAndView("redirect:institute_registration_url");
			}

			if (institute_email.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Email Id.");
				return new ModelAndView("redirect:institute_registration_url");
			}
			if (institute_mob_no.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Mobile No.");
				return new ModelAndView("redirect:institute_registration_url");
			}
		
			if (validation.isOnlyNumerLib(institute_mob_no) == false) {
				ra.addAttribute("msg", " Mobile No. " + validation.isOnlyNumerMSGLib);
				return new ModelAndView("redirect:institute_registration_url");
			}
			if (validation.isOnlyNumerLib(institute_mob_no) == false) {
				ra.addAttribute("msg", " Mobile No. " + validation.isOnlyNumerMSGLib);
				return new ModelAndView("redirect:institute_registration_url");
			}
			
			if (total_sanction_seat.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Total Sanction Seat");
				return new ModelAndView("redirect:institute_registration_url");
			}
			if (validation.isOnlyNumerLib(total_sanction_seat) == false) {
				ra.addAttribute("msg", " Total Sanction Seat " + validation.isOnlyNumerMSGLib);
				return new ModelAndView("redirect:institute_registration_url");
			}
			
			if (validation.maxlengthcheck3digit(total_sanction_seat) == false) {
				ra.addAttribute("msg", "Total Sanction Seat" + validation.Maxlengthcheck3MSG);
				return new ModelAndView("redirect:institute_registration_url");
			}

			if (system_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select System");
				return new ModelAndView("redirect:institute_registration_url");
			}

			if (university_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select University");
				return new ModelAndView("redirect:institute_registration_url");
			}

//			if (app_status.trim().equals("")) {
//				ra.addAttribute("msg", "Please Enter Approval Status");
//				return new ModelAndView("redirect:institute_registration_url");
//			}

			if (country_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select Country");
				return new ModelAndView("redirect:institute_registration_url");
			}

			if (state_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select State");
				return new ModelAndView("redirect:institute_registration_url");
			}

			if (district_id.trim().equals("0")) {
				ra.addAttribute("msg", "Please Select District");
				return new ModelAndView("redirect:institute_registration_url");
			}
			// SECURITY
//			if (upload_image.trim().equals("")) {
//				ra.addAttribute("msg", "Please Upload College Logo");
//				return new ModelAndView("redirect:institute_registration_url");
//			}
			if (upload_image_FILE.getOriginalFilename().isEmpty()) {
				ra.addAttribute("msg", "Please Upload File");
				return new ModelAndView("redirect:Create_event_Url");
			}
			if (!upload_image_FILE.getOriginalFilename().isEmpty()) {

				if (upload_image_FILE.getOriginalFilename().split("[.]").length > 2) {
					ra.addAttribute("msg", "Invalid file extension for Document");
					return new ModelAndView("redirect:exp_attendance_excel_url");
				}

				String upload_fileEXt = FilenameUtils.getExtension(upload_image_FILE.getOriginalFilename())
						.toLowerCase();
				if (!upload_fileEXt.equals("jpg") && !upload_fileEXt.equals("jpeg") && !upload_fileEXt.equals("png")) {
					ra.addAttribute("msg", "Only *.jpg, *.jpeg and *.png file extensions allowed");
					return new ModelAndView("redirect:institute_registration_url");
				}
				long filesize = upload_image_FILE.getSize() / 1024;
				if (filesize > 50) {
					ra.addAttribute("msg", "File size should be 50 kb or less");
					return new ModelAndView("redirect:institute_registration_url");
				}
				// String file_ext_name= upload_image_FILE.getName().split(".").toString();
				if (upload_image_FILE.getName().split(".").length > 2) {
					ra.addAttribute("msg", "Improper File Extention");
					return new ModelAndView("redirect:institute_registration_url");
				}
			}

			if (address.trim().equals("")) {
				ra.addAttribute("msg", "Please Enter Address");
				return new ModelAndView("redirect:institute_registration_url");
			}

			if (!txtInput.equals(capcha)) {
				ra.addAttribute("msg", "Enter valid Captcha!");
				return new ModelAndView("redirect:/institute_registration_url");
			}

//			if (status.trim().equals("0")) {
//				ra.addAttribute("msg", "Please Enter Status");
//				return new ModelAndView("redirect:institute_registration_url");
//			}

			Query q0 = sessionHQL.createQuery(
					"select count(id) from EDU_LMS_INSTITUTE_REGISTRATION where upper(institute_name)=:institute_name and country_id=:country_id "
							+ "and state_id=:state_id and district_id=:district_id and system_id=:system_id and upper(code)=:code and upper(college_abbr)=:college_abbr and upper(address)=:address "
							+ " and upper(status)=:status and university_id=:university_id"); // and id !=:id

			q0.setParameter("institute_name", institute_name.toUpperCase());
			q0.setParameter("university_id", Integer.parseInt(university_id));
			q0.setParameter("country_id", Integer.parseInt(country_id));
			q0.setParameter("state_id", Integer.parseInt(state_id));
			q0.setParameter("district_id", Integer.parseInt(district_id));
			q0.setParameter("system_id", Integer.parseInt(system_id));
			q0.setParameter("code", code.toUpperCase());
			q0.setParameter("college_abbr", college_abbr.toUpperCase());
			q0.setParameter("address", address.toUpperCase());
//			q0.setParameter("upload_image", upload_image.toUpperCase());
			q0.setParameter("status", "0");
//			q0.setParameter("college_unique_id", college_unique_id);
			// q0.setParameter("id", id).uniqueResult();

//			if(role.equals("ADMIN")) {
//				td.setApp_status("1");
//			}else {
			// }

			Long c = (Long) q0.uniqueResult();

			if (id == 0) {

				td.setApp_status("0");
				td.setCreated_by(institute_name);
				td.setUpload_image(upload_image);
				td.setCreated_date(date);
				td.setStatus("0");
//				String system_abbr=IRdao.getSyatemAbbr(system_id).get(0).get(0);
//				 
//				String col_unique_id=IRdao.getSerialCollegeNumber(system_id).isEmpty()?system_abbr+"0001":IRdao.getSerialCollegeNumber(system_id).get(0).get(0);
//				

				td.setCollege_unique_id(code);
				td.setTotal_sanctioned_seat(Integer.parseInt(total_sanction_seat));
				td.setDashboard_status(0);
//				td.setCollege_unique_id(IRdao.getSerialCollegeNumber(system_id).get(0).get(0));

//				td.setCollege_unique_id(getstate_name(state_id) + "/" + college_abbr.toUpperCase() + "/"
//						+ IRdao.getSerialCollegeNumber().get(0).get(0));

				if (c == 0) {
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();

//					 for mail send
					// SendRegMail(td.getInstitute_email());

					ra.addAttribute("msg", "College Sign Up Successfully.");
				} else {
					ra.addAttribute("msg", "Data already Exist.");
				}
				tx.commit();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();

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
		return new ModelAndView("redirect:landingpage");
	}
	

	@GetMapping(value = "/Search_institute_report_url")
	public ModelAndView Search_institute_report_url(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		if (request.getHeader("Referer") == null) {
			//session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/login");
		}
		String roleid1 = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_institute_report_url", roleid1);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		model.put("msg", msg);
		model.put("MedCountryName", common.getMedCountryName(sessionFactory));

		return new ModelAndView("Search_Institute_Report_Tiles");
	}

//==========================================image view========================================== 	
	public String dm(HttpServletRequest request, MultipartHttpServletRequest mul, HttpSession session, String id)
			throws IOException {

		String extension = ""; // add line
		String fname = ""; // add line

		request.getSession().setAttribute(id, "/srv" + File.separator + "Image");
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
	
	@PostMapping("/getFilterinstitute_reg_data")
	public @ResponseBody List<Map<String, Object>> getFilterinstitute_reg_data(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String institute_name, String country_id,String state_id,String district_id, String app_status,HttpSession session) {
		String role = session.getAttribute("role").toString();	
		return IRdao.DataTableinstitute_regDataList(startPage, pageLength, Search, orderColunm, orderType, institute_name, country_id,state_id,district_id, app_status,role);
	}
	@PostMapping("/getTotalinstitute_reg_dataCount")
	public @ResponseBody long getTotalmodule_dataCount(HttpSession sessionUserId, String Search, String institute_name, String country_id,String state_id,String district_id,String app_status,HttpSession session) {
		String role = session.getAttribute("role").toString();	
		return IRdao.DataTableinstitute_regDataTotalCount(Search,institute_name, country_id,state_id,district_id,app_status,role);
	}
	
	@RequestMapping(value = "Search_institute_Approve_url", method = RequestMethod.POST)
	public ModelAndView Search_institute_Approve_url(@ModelAttribute("Acceptid") int id,
			@ModelAttribute("inst_email") String email,@ModelAttribute("code") String code,HttpServletRequest request,
			HttpSession session,
			ModelMap model, EDU_LMS_INSTITUTE_REGISTRATION obj) {
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();		

		List<String> liststr = new ArrayList<String>();

		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();

		Date date = new Date();

		Long c = (Long) sessionHQL.createQuery(
				"select count(pr.id) from  UserLogin pr where userName=:userName")
				.setParameter("userName",code)
				.uniqueResult();
		
		if(c==0) {
		
		String username = session.getAttribute("username").toString();
		String hqlUpdate2 = "update from EDU_LMS_INSTITUTE_REGISTRATION set status='1', app_status='1',modified_by=:modified_by,modified_date=:modified_date"
				+ " where id=:id  ";
		int app2 = sessionHQL.createQuery(hqlUpdate2)
				.setParameter("id", id)
				.setParameter("modified_by", username)
				.setParameter("modified_date", new Date())
				.executeUpdate();

		ArrayList<ArrayList<String>> list = IRdao.getinstitute_system(id);

		UserLogin ul = new UserLogin();

		ul.setUserName(list.get(0).get(1));
		ul.setLogin_name(list.get(0).get(3));

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode("Bisag@123");

		ul.setPassword(hashedPassword);
		ul.setCreated_by(username);
		ul.setCreated_on(date);
		ul.setState_id(Integer.parseInt(list.get(0).get(2)));
		ul.setUniversity_id(Integer.parseInt(list.get(0).get(4)));
		ul.setInstitute_id(Integer.parseInt(list.get(0).get(0)));
		ul.setEmail_id(list.get(0).get(5));
		ul.setMobile_no(list.get(0).get(6));
		ul.setEnabled(1);
		ul.setAccountNonExpired(1);
		ul.setAccountNonLocked(1);
		ul.setCredentialsNonExpired(1);
		String roleid1 = session.getAttribute("roleid").toString();
		
		UserRole role_tbl = new UserRole();

		int did = (Integer) sessionHQL.save(ul);
		
		if (roleid1.equals("16")) {
			role_tbl.setRoleId(22);
		} else if (roleid1.equals("17")) {
			
			role_tbl.setRoleId(21);
		}
		
		
		
		role_tbl.setUserId(did);
		sessionHQL.save(role_tbl);
		sessionHQL.flush();
		sessionHQL.clear();
		
		
		if (did>0) {
//			 for mail sending
			try {
				if (roleid1.equals("16")) {
					emailsend.SendMail(request,email,roleSubAccess,"Congratulation! Your Institute is successfully registered on AYUSH EDUCATION PORTAL,NCISM,MOA","Now its time to active you account ... \n You can Login with your credentials<br><br> Username: "+ username+  "<br><br> Password: Bisag@123","","","");

				} 
				if (roleid1.equals("17")) {
					
					emailsend.SendMail(request,email,roleSubAccess,"Congratulation! Your Institute is successfully registered on AYUSH EDUCATION PORTAL,NCH,MOA","Now its time to active you account ... \n You can Login with your credentials<br><br> Username: "+ username+  "<br><br> Password: Bisag@123","","","");

				}
//				
////				SendapproveMail(list.get(0).get(1),email,"Bisag@123");
////				SendapproveMsg(email,"Bisag@123");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		tx.commit();
		sessionHQL.close();
		if (app2 > 0) {
			liststr.add("Approved Successfully.");
		} else {
			liststr.add("Not Approved.");
		}
		}else {
			liststr.add("Institute Code Already Exist");

		}
		model.put("msg", liststr.get(0));

		return new ModelAndView("Search_Institute_Report_Tiles");
	}

//	//  EMAIL Registration
//			public void SendRegMail(String email) throws ParseException {
//				
//				MailHTML html = new MailHTML();
//				try {
//					MimeMessage mimeMessage = mailSender.createMimeMessage();
//					MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
//					try {
//						String text = "Your Registration is Successfull ... Thank you for Registrating with us!";
//						String note = "";
//						html.setHTML(text, note);
//						String htmlMsg = html.getHTML();
//						helper.setText(htmlMsg, true);
//						helper.setTo(email);
//						helper.setSubject("Institute Registration is Successfull");
//						/* helper.setFrom("abc@gmail.com"); */
//						mailSender.send(mimeMessage);
//					} catch (MessagingException e) {
//						e.printStackTrace();
//					}
//				} catch (Exception e) {
//				}
//			}
			
	
//	//  EMAIL Credentials
//		public void SendapproveMail(String username,String email,String Password) throws ParseException {
////			String code = randomString(5);
//			MailHTML html = new MailHTML();
//			try {
////				MimeMessage mimeMessage = mailSender.createMimeMessage();
////				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
////				try {
////					String text = "Your Registration is approved by NCISM ... \n You can Login with your credentials<br><br> Username: "+ username+  "<br><br> Password: "+Password;
////					String note = "";
////					html.setHTML(text, note);
////					String htmlMsg = html.getHTML();
////					helper.setText(htmlMsg, true);
////					helper.setTo(email);
////					helper.setSubject("Institute Registration is Approved");
//////					helper.setFrom("abc@gmail.com"); 
////					mailSender.send(mimeMessage);
//				} catch (MessagingException e) {
//					e.printStackTrace();
//				}
//			} catch (Exception e) {
//			}
//		}
	
	// FOR WHATSAPP
	
			public static void SendapproveMsg(String email,String Password) throws ParseException {
				
				final String ACCOUNT_SID = "AC76781bb1c6c8c4d871a664b55a19da36";
			    final String AUTH_TOKEN = "74cd413e0fbe76bb1d52ddf2730b1c52";
			    
				Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
				
		        Message message = Message.creator(
		                new com.twilio.type.PhoneNumber("whatsapp:+918200818918"),
		                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
		                "Your Registration is approved by NCISM ..., you can Login with your credentials Email id:"+ email+" Password:"+Password+"")
		          
		        		.create();
		        
			}
	
			@RequestMapping(value = "Search_institute_Reject_url", method = RequestMethod.POST)
			public ModelAndView Search_institute_Reject_url(@ModelAttribute("Rejectid") int id, HttpSession session,
					ModelMap model, EDU_LMS_INSTITUTE_REGISTRATION obj) {

				List<String> liststr = new ArrayList<String>();
				Session sessionHQL = this.sessionFactory.openSession();
				Transaction tx = sessionHQL.beginTransaction();

				Date date = new Date();

				String username = session.getAttribute("username").toString();
				String hqlUpdate2 = "update from EDU_LMS_INSTITUTE_REGISTRATION set app_status='2',modified_by=:modified_by,modified_date=:modified_date"
						+ " where id=:id  ";
				int app2 = sessionHQL.createQuery(hqlUpdate2).setParameter("id", id)
						.setParameter("modified_by", username).setParameter("modified_date", new Date())
						.executeUpdate();

				tx.commit();
				sessionHQL.close();
				if (app2 > 0) {
					liststr.add("Reject Successfully.");
				} else {
					liststr.add("Not Reject.");
				}
				model.put("msg", liststr.get(0));

				return new ModelAndView("Search_Institute_Report_Tiles");

			}

			@RequestMapping(value = "/getStateUrl", method = RequestMethod.POST)
			public @ResponseBody List<TB_STATE> getStateUrl(ModelMap Mmap, HttpSession session,
					@RequestParam(value = "selval", required = false) Integer selval, HttpServletRequest request) {

				List<TB_STATE> list = IRdao.getcountrylistUrl(selval);

				return list;
			}

			@RequestMapping(value = "/getDistrictUrl", method = RequestMethod.POST)
			public @ResponseBody List<EDU_LMS_DISTRICT_MSTR> getDistrictUrl(ModelMap Mmap, HttpSession session,
					@RequestParam(value = "selval", required = false) Integer selval, HttpServletRequest request) {

				List<EDU_LMS_DISTRICT_MSTR> list = IRdao.getStatelistUrl(selval);

				return list;
			}
			
			@RequestMapping(value = "/getUniversitylistbySystem", method = RequestMethod.POST)
			public @ResponseBody List<EDU_LMS_UNIVERSITY_MSTR> getUniversitylistbySystem(ModelMap Mmap, HttpSession session,
					@RequestParam(value = "selval", required = false) Integer selval, HttpServletRequest request) {
				List<EDU_LMS_UNIVERSITY_MSTR> list = IRdao.getUniversitylistUrl(selval);

				return list;
			}
			@RequestMapping(value = "/getCountrylistbyUniversity", method = RequestMethod.POST)
			public @ResponseBody ArrayList<ArrayList<String>> getCountrylistbyUniversity(ModelMap Mmap, HttpSession session,
					@RequestParam(value = "selval", required = false) Integer selval, HttpServletRequest request) {
				ArrayList<ArrayList<String>> list = IRdao.getCountrylistbyUniversity(selval);

				return list;
			}


			public String getstate_name(String state_id) {

				Connection conn = null;
				String reg_no = "";

				try {
					conn = dataSource.getConnection();
					PreparedStatement stmt = null;
					String query = "";
					query = "select max(st.state_abbr) from edu_lms_institute_reg ir\n"
							+ "inner join edu_lms_state_mstr st on st.state_id=ir.state_id where st.state_id=?  ";

					stmt = conn.prepareStatement(query);

					stmt.setInt(1, Integer.parseInt(state_id));
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

			public String getcollege_name(String state_id) {

				Connection conn = null;
				String reg_no = "";

				try {
					conn = dataSource.getConnection();
					PreparedStatement stmt = null;
					String query = "";
					query = "select max(st.state_abbr) from edu_lms_institute_reg ir\n"
							+ "inner join edu_lms_state_mstr st on st.state_id=ir.state_id where st.state_id=?  ";

					stmt = conn.prepareStatement(query);

					stmt.setInt(1, Integer.parseInt(state_id));
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
}
