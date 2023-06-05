package com.AyushEdu.controller.Regulation;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
import com.AyushEdu.Models.UserLogin;
import com.AyushEdu.Models.UserRole;
import com.AyushEdu.Models.LMS_Institute.EDU_LMS_INSTITUTE_REGISTRATION;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_DEGREE_MASTER;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_SYSTEM_MSTR;
import com.AyushEdu.Models.LMS_Master.EDU_LMS_UNIVERSITY_MSTR;
import com.AyushEdu.Models.Regulation.REG_NCH_DETAILS_A;
import com.AyushEdu.Models.Regulation.REG_NCH_PRACTITIONER_Signup_Details;
import com.AyushEdu.controller.Exp_Excel.GenExcel_Contoller;
import com.AyushEdu.dao.RoleBaseMenuDAO;
import com.AyushEdu.dao.CommonDao.Commondao;
import com.AyushEdu.dao.LMS_Institute.Institute_Registration_Dao;
import com.AyushEdu.dao.LMS_Master.Type_of_Degree_MstrDao;
import com.AyushEdu.dao.Registration.Search_Student_RegistrationDao;
import com.AyushEdu.dao.Regulation.Exp_ExcelDAO;
import com.AyushEdu.validation.ValidationController;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Exp_Excel_Practitioner_Controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	Type_of_Degree_MstrDao TDdao;
	@Autowired
	private Commondao cd;
	@Autowired
	CommonController common;
	@Autowired
	Exp_ExcelDAO  Edao;
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	Institute_Registration_Dao IRdao;
	@Autowired
	Search_Student_RegistrationDao SSRDao;
	@Autowired
	ValidationController validation;
	
	@GetMapping(value = "/exp_excel_practitioner_url")
	public ModelAndView exp_excel_practitioner_url(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		try {
//			SECURITY -- RIDDHI 
			if(request.getHeader("Referer") == null ) { 
//				session.invalidate();
				model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
				return new ModelAndView("redirect:/landingpage");
			 }
			String roleid1 = session.getAttribute("roleid").toString();
			 Boolean val = roledao.ScreenRedirect("exp_excel_practitioner_url", roleid1);		
				if(val == false) {
					return new ModelAndView("AccessTiles");
			}
			String userid = session.getAttribute("userId_for_jnlp").toString();
			System.err.println("checking userid"+userid);
			String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
			System.err.println("institute idddddd"+institute_id);
			model.put("msg", msg);
			Date date = new Date();
			model.put("getinstitute_system", cd.getinstitute_system(Integer.parseInt(institute_id)));
			model.put("getstu_DegreeList", cd.getstu_DegreeList(Integer.parseInt(institute_id),"15,16"));
			String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
			model.put("date", date1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Exp_practitioner_ExcelTiles");
	}
//	
//	@GetMapping(value = "/signup_practitionner_Url")
//	public ModelAndView signup_practitionner_Url(ModelMap model, HttpSession session,
//			@RequestParam(value = "msg", required = false) String msg) {
//		
//		try {
//			String userid = session.getAttribute("userId_for_jnlp").toString();
//			
//			String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
//			model.put("msg", msg);
//			Date date = new Date();
//			model.put("getgenderList", common.getgenderList(sessionFactory));
//			model.put("getinstitute_system", cd.getinstitute_system(Integer.parseInt(institute_id)));
//			model.put("getstu_DegreeList", cd.getstu_DegreeList(Integer.parseInt(institute_id)));
//			model.put("TypeOfDegree", common.gettype_of_degree(sessionFactory));
//			String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
//			model.put("date", date1);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return new ModelAndView("signup_pract_tiles");
//	}
//	
	
	@RequestMapping(value = "/signup_practitionner_Url", method = RequestMethod.GET)
	public ModelAndView signup_practitionner_Url(ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request, RedirectAttributes ra) {
System.err.println("sig - -");
		try {
//			SECURITY -- RIDDHI 
//			if(request.getHeader("Referer") == null ) { 
////				session.invalidate();
//				ra.addAttribute("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				return new ModelAndView("redirect:/landingpage");
//			 }
//			
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("signup_practitionner_Url", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
//			if(request.getHeader("Referer") == null ) { 
//				 session.invalidate();
//				 Mmap.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//				 return new ModelAndView("redirect:/landingpage");
//			 }
//			
//			String roleid1 = session.getAttribute("roleid").toString();
//			 Boolean val = roledao.ScreenRedirect("University_Url", roleid1);		
//				if(val == false) {
//					return new ModelAndView("AccessTiles");
//			}
				
//			String userid = session.getAttribute("userId_for_jnlp").toString();
//			System.err.println("checking for userid"+userid);
			model.put("msg", msg);
			model.addAttribute("langugae", "english");
			model.addAttribute("degreelist", TDdao.getDataListofdegSysToD("NCH"));
//			String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
//			System.err.println("checking for institute iddddd"+institute_id);
//			model.put("msg", msg);
			Date date = new Date();
			model.put("getgenderList", common.getgenderList(sessionFactory));
//			model.put("getdegreeList", common.getdegreeList(sessionFactory));
			model.put("MedStateName", common.getMedStateName(sessionFactory));
//			model.put("getinstitute_system", cd.getinstitute_system(Integer.parseInt(institute_id)));
//			model.put("getstu_DegreeList", cd.getstu_DegreeList(Integer.parseInt(institute_id)));
//			model.put("TypeOfDegree", common.gettype_of_degree(sessionFactory));
//			model.put("getSystemForAll", common.getSystemForAll(sessionFactory));
			model.put("getUniversityList", common.getUniversityList(sessionFactory));
			model.put("getInstituteList", common.getInstituteList(sessionFactory));
			String date1 = new SimpleDateFormat("dd/MM/yyyy").format(date);
			model.put("date", date1);
			model.put("getSystemForHomeopathy", common.getSystemForHomeopathy(sessionFactory));
			
	} catch (Exception e) {
		e.printStackTrace();
	}

		return new ModelAndView("signup_pract_tiles");
	}
	
	
	@RequestMapping(value = "/Exp_practitioner_Excel", method = RequestMethod.POST)
	public ModelAndView Exp_practitioner_Excel(HttpServletRequest request, ModelMap model, HttpSession session, String typeReport1) {
		
//		List<recr_category_mst> cat= edu_com.getCategoryNameList();
//		String category="";
//		
//		for (int i = 0; i < cat.size(); i++) {
//			if (i == 0) {
//				category += cat.get(i).getCategory();
//
//			} else {
//				category += "," + cat.get(i).getCategory();
//			}
//		}
		
		
		
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("exp_excel_practitioner_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		String userid = session.getAttribute("userId_for_jnlp").toString();
		String institute_id = cd.getinstIDfromuserID(Integer.parseInt(userid)).get(0).get(0);
		
		model.put("getinstitute_system", cd.getinstitute_system(Integer.parseInt(institute_id)));
		model.put("getstu_DegreeList", cd.getstu_DegreeList(Integer.parseInt(institute_id),"15,16"));
		
		ArrayList<ArrayList<String>> listSystem =  cd.getinstitute_system(Integer.parseInt(institute_id));
		ArrayList<ArrayList<String>> listDegree =  cd.getstu_DegreeList(Integer.parseInt(institute_id),"15,16");
		
		ArrayList<ArrayList<String>> listexport = new ArrayList<ArrayList<String>>();
		List<String> TH = new ArrayList<String>();
		
//		TH.add("Ayush_id");
		TH.add("Name");
		TH.add("Date_of_Birth");
		TH.add("Aadhar_Card");
		TH.add("Email");
		TH.add("Mobile_No");
		TH.add("Admission_Date");
		
//		if(!listSystem.isEmpty()) {
//			String Mst="";
//			for(int i=0; i<listSystem.size(); i++ ) {
//				if(i==listSystem.size()) {
//					Mst+=listSystem.get(i).get(1);
//				}
//				else if (i==0) {
//					Mst+=listSystem.get(i).get(1);
//				}
//				else {
//					Mst+=","+listSystem.get(i).get(1);
//				}
//				
//			}
//			TH.add("System:Drop:" + Mst);
//		}
//		else {
//			TH.add("System:Drop:" + "System not available");
//		}
//		if(!listDegree.isEmpty()) {
//			String Mst2="";
//			for(int j=0; j<listDegree.size(); j++ ) {
//				if(j==listDegree.size()) {
//					Mst2+=listDegree.get(j).get(1);
//				}
//				else if (j==0) {
//					Mst2+=listDegree.get(j).get(1);
//				}
//				else {
//					Mst2+=","+listDegree.get(j).get(1);
//				}
//				
//			}
//			TH.add("Degree:Drop:" + Mst2);
//		}
//		else {
//			TH.add("Degree:Drop:" + "Degree not available");
//		}
		
		
		TH.add("System:Drop:" + "HOMOEOPATHY");
		TH.add("Degree:Drop:" + "BHMS,BSMS,BUMS");
		
		TH.add("Enrollment_No");
		TH.add("upload_date");
		TH.add("Gender:Drop:" + "Male,Female");
//		TH.add("Semester:Drop:" + "Sem_1,Sem_2,Sem_3,Sem_4,Sem_5,Sem_6,Sem_6,Sem_8,Sem_9,Sem_10,Sem_11,Sem_12,Sem_13,Sem_14,Sem_15");
		TH.add("Internship_completion_date");
		
		String Heading = "\nCope Code";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new GenExcel_Contoller("L", TH, Heading, username), "userList", listexport);
	}
	
	
	@SuppressWarnings("deprecation")
	@PostMapping(value = "/Exp_Excel_practitioner_action")
	public ModelAndView Exp_Excel_practitioner_action(@Validated @ModelAttribute("Exp_Excel_practitioner_cmd") REG_NCH_DETAILS_A skl,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session, Principal principal,
			RedirectAttributes ra, @RequestParam(value = "u_file1", required = false) MultipartFile upload, 
			@RequestParam(value = "msg", required = false) String msg) throws IOException, ParseException {
		
		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		Session sessionHQL = this.sessionFactory.openSession();
//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String roleid1 = session.getAttribute("roleid").toString();
		 Boolean val = roledao.ScreenRedirect("exp_excel_practitioner_url", roleid1);		
			if(val == false) {
				return new ModelAndView("AccessTiles");
		}
		// check for list empty
		UserLogin p = new UserLogin();

		try {

			int count_duplicate = 0;
			String board_id = "";
			int id = skl.getId() > 0 ? skl.getId() : 0;
			String username = session.getAttribute("username").toString();
			String userid = session.getAttribute("userId_for_jnlp").toString();
			
			
			if (upload.getOriginalFilename().isEmpty()) {
				ra.addAttribute("msg", "Please Upload File");
				return new ModelAndView("redirect:exp_excel_practitioner_url");
			}
			if (!upload.getOriginalFilename().isEmpty()) {

				if (upload.getOriginalFilename().split("[.]").length > 2) {
					ra.addAttribute("msg", "Invalid file extension for Document");
					return new ModelAndView("redirect:exp_excel_practitioner_url");
				}

			}
			File file = new File(fileupload(upload.getBytes(), upload.getOriginalFilename(), 0, "doc_contract"));
			FileInputStream fis = new FileInputStream(file);
			HSSFWorkbook wb = new HSSFWorkbook(fis);
			HSSFSheet sheet = wb.getSheetAt(0);
			Row row_head = sheet.getRow(0);
			
			String date_of_upload=request.getParameter("upload_date");
			String role_type=request.getParameter("role_type");
			
//			if (!row_head.getCell(0).getStringCellValue().equals("Ayush_id")) {
//				ra.addAttribute("msg", "Please Enter Correct File Header for Ayush_id");
//				return new ModelAndView("redirect:exp_excel_url");
//			}
//			if (!row_head.getCell(0).getStringCellValue().equals("Name")) {
//				ra.addAttribute("msg", "Please Enter Correct File Header for Name");
//				return new ModelAndView("redirect:exp_excel_url");
//			}
//			if (!row_head.getCell(1).getStringCellValue().equals("System")) {
//			ra.addAttribute("msg", "Please Enter Correct File Header for System");
//			return new ModelAndView("redirect:exp_excel_url");
//			}
//			if (!row_head.getCell(2).getStringCellValue().equals("Degree")) {
//				ra.addAttribute("msg", "Please Enter Correct File Header for Degree");
//				return new ModelAndView("redirect:exp_excel_url");
//			}
//				if (!row_head.getCell(3).getStringCellValue().equals("Enrollment_No")) {
//				ra.addAttribute("msg", "Please Enter Correct File Header for Eno");
//				return new ModelAndView("redirect:exp_excel_url");
//			}
//			if (!row_head.getCell(4).getStringCellValue().equals("Date_of_Birth")) {
//				ra.addAttribute("msg", "Please Enter Correct File Header for DOB");
//				return new ModelAndView("redirect:exp_excel_url");
//			}
////			if (!row_head.getCell(5).getStringCellValue().equals("Aadhar_Card")) {
////				ra.addAttribute("msg", "Please Enter Correct File Header for AC");
////				return new ModelAndView("redirect:exp_excel_url");
////			}
//			if (!row_head.getCell(5).getStringCellValue().equals("Email")) {
//				ra.addAttribute("msg", "Please Enter Correct File Header for Email");
//				return new ModelAndView("redirect:exp_excel_url");
//			}
//			if (!row_head.getCell(6).getStringCellValue().equals("Mobile_No")) {
//				ra.addAttribute("msg", "Please Enter Correct File Header for Mo No");
//				return new ModelAndView("redirect:exp_excel_url");
//			}
//			if (!row_head.getCell(7).getStringCellValue().equals("Gender")) {
//			ra.addAttribute("msg", "Please Enter Correct File Header for Gender");
//			return new ModelAndView("redirect:exp_excel_url");
//			}
//			if (!row_head.getCell(8).getStringCellValue().equals("Admission_Date")) {
//				ra.addAttribute("msg", "Please Enter Correct File Header FOR Adm Date");
//				return new ModelAndView("redirect:exp_excel_url");
//			}
//
			int increemt=0;
			String abr="";
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				
				Transaction tx = sessionHQL.beginTransaction();

				Row row = sheet.getRow(i);
				if (row.getCell(0) == null) {
					break;
				}
				String maxAID="";
				if(i==1) {
					maxAID = getMaxAID(userid);
					increemt=Integer.parseInt(maxAID.substring(12));
		
					abr=maxAID.substring(0,10);
			
				}else {

					maxAID=abr+String.format("%5s", increemt).replace(' ', '0');
					
				}
				int i1_length=10;
				if(role_type.equals("25")) {
					i1_length=10;
				}
				for (int i1 = 0; i1 <= i1_length; i1++) {

					Cell cell = row.getCell(i1);
					String value = "";
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						value = cell.getStringCellValue();
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (HSSFDateUtil.isCellDateFormatted(cell)) {
							value = String.valueOf(cell.getDateCellValue());
						} else {
							value = String.valueOf((long) cell.getNumericCellValue());
						}
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						value = String.valueOf(cell.getBooleanCellValue());
						break;
					default:
					}
					
//					if (reg_no != null)
//					{
//						int newn = Integer.parseInt(reg_no);
//						newn++;
//						String abc = String.format("%10s", newn).replace(' ', '0');
//						 testold = depart + abc;
//						rt.setRecr_reg_no(testold);
//
//					} else {
//						testold="i0000000001";
//						rt.setRecr_reg_no(testold);
//					}
					
					
//						if(maxAID != null) {
//							 newn = Integer.parseInt(maxAID);
//							 System.err.println("-----new---->"+newn);
//						}
//						newn++;
//					
//						String abc = String.format("%14s", newn).replace(' ', '0');
//						 System.err.println("-----abc---->"+abc);
					//	abc = "AU"+abc;
						
						skl.setAyush_id(maxAID);
//					}
						
					if (row_head.getCell(i1).getStringCellValue().equals("Name")) {
						skl.setName(value.toUpperCase());
						p.setLogin_name(value);
					}
					if (row_head.getCell(i1).getStringCellValue().equals("Date_of_Birth")) {
						Date date = (Date) formatter.parse(value);
						System.err.println("check the date"+date);
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						String formatedDate = (String.format("%2s", cal.get(Calendar.DATE)) + "/" + String.format("%2s", (cal.get(Calendar.MONTH) + 1)) + "/"
								+ String.format("%4s", cal.get(Calendar.YEAR))).replace(" ", "0");

						skl.setDob(formatedDate);
					}
					if (row_head.getCell(i1).getStringCellValue().equals("Email")) {
						skl.setEmail(value);
						p.setEmail_id(value);
					}
					if (row_head.getCell(i1).getStringCellValue().equals("Mobile_No")) {
						skl.setMobile_no(value);
						p.setMobile_no(value);
					}
					if (row_head.getCell(i1).getStringCellValue().equals("Gender")) {
						skl.setGender(value);
					}
					
					if (row_head.getCell(i1).getStringCellValue().equals("Aadhar_Card")) {
						skl.setAadhar_card(value);
						p.setAadhar_no(value);
						p.setUserName(value);
					}
					
					if (row_head.getCell(i1).getStringCellValue().equals("Admission_Date")) {
						Date date = (Date) formatter.parse(value);

						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/"
								+ cal.get(Calendar.YEAR);
						
						skl.setAdmission_date(formatedDate);
					}
					
///////////////////31_05						
					if (row_head.getCell(i1).getStringCellValue().equals("System")) {
						EDU_LMS_SYSTEM_MSTR es = common.getSystem_nametoid(value).get(0);
						String sys = String.valueOf(es.getId());
						skl.setSystem(Integer.parseInt(sys));
					}
					if (row_head.getCell(i1).getStringCellValue().equals("Degree")) {
						EDU_LMS_DEGREE_MASTER ed = common.getDegree_nametoid(value).get(0);
						String deg = String.valueOf(ed.getId());
						skl.setDegree(Integer.parseInt(deg));
					}
					if (row_head.getCell(i1).getStringCellValue().equals("Enrollment_No")) {
						skl.setEnrollment_no(value);
					}
					
					if (row_head.getCell(i1).getStringCellValue().equals("Internship_completion_date")) {
						Date date = (Date) formatter.parse(value);

						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/"
								+ cal.get(Calendar.YEAR);
						
						skl.setInternship_completion_date(formatedDate);
					}
					
//                    if (row_head.getCell(i1).getStringCellValue().equals("Semester")) {
//						
//						skl.setSemester(value.split("_")[1]);
//					}
				}
				
				if (!userid.equals("")) {
                	UserLogin ea = common.getAllInfoLogin(sessionFactory, userid).get(0);
                	String university = String.valueOf(ea.getUniversity_id());
					skl.setUniversity_userid(Integer.parseInt(university));
					
					String institude = String.valueOf(ea.getInstitute_id());
					skl.setInstitude_userid(Integer.parseInt(institude));
					p.setInstitute_id(Integer.parseInt(institude));
					p.setUniversity_id(Integer.parseInt(university));
					
					String state = String.valueOf(ea.getState_id());
					p.setState_id(Integer.parseInt(state));
				}
				
					skl.setInstitude_id(Integer.parseInt(userid));
					skl.setUpload_date(date_of_upload);
					skl.setCreated_date(new Date());
					skl.setCreated_by(username);
				
				Query q0 = sessionHQL.createQuery("select count(*) from REG_NCH_DETAILS_A where email=:email ");
				q0.setParameter("email", skl.getEmail());  
				Long c = (Long) q0.uniqueResult();
				
				Query q1 = sessionHQL.createQuery("select count(*) from UserLogin where upper(userName)=:userName ");
				q1.setParameter("userName", skl.getAadhar_card().toUpperCase());  
				Long c2 = (Long) q1.uniqueResult();
				
				if(c==0 && c2==0) {
					
					 sessionHQL.save(skl);
					BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
					String hashedPassword = passwordEncoder.encode("Bisag@123");
					
					p.setPassword(hashedPassword);
					p.setEnabled(1);
					p.setAccountNonExpired(1);
					p.setAccountNonLocked(1);
					p.setCredentialsNonExpired(1);
//					p.setInstitute_id(Integer.parseInt(userid));
//					p.setCountry_id(Integer.parseInt("5"));
//					p.setState_id(Integer.parseInt("12"));
					p.setCreated_on(new Date());
					p.setCreated_by(username);
					UserRole role_tbl = new UserRole();

					int did = (Integer) sessionHQL.save(p);
					if(role_type!=null && !role_type.equals("")) {
						role_tbl.setRoleId(Integer.parseInt(role_type));

					}else {
						role_tbl.setRoleId(0);

					}
					role_tbl.setUserId(did);
					sessionHQL.save(role_tbl);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Data Save Successfully");
				}
				else if(c2!=0){
					ra.addAttribute("msg", "Aadhar No Is Already Exist");
				}
				else if(c!=0){
					ra.addAttribute("msg", "Email id Already Exist");
				}else {
					ra.addAttribute("msg", "Data Not Save Successfully");
				}
				// for mail send
				//SendRegMail(p.getEmail_id());
				tx.commit();
			}
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			try {

				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				e.printStackTrace();
				ra.addAttribute("msg", "Couldn�t roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
	 	return new ModelAndView("redirect:exp_excel_practitioner_url");
	}
	
	@PostMapping(value = "/practitionner_signup_Action")
	public ModelAndView practitionner_signup_Action(@Validated @ModelAttribute("practitionner_signup_CMD") REG_NCH_PRACTITIONER_Signup_Details td, BindingResult result,
			HttpServletRequest request, ModelMap model,  MultipartHttpServletRequest mul,MultipartFile files,
			RedirectAttributes ra, HttpSession session) throws Exception {

//		SECURITY -- RIDDHI 
		if(request.getHeader("Referer") == null ) { 
//			session.invalidate();
			ra.addAttribute("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
			return new ModelAndView("redirect:/landingpage");
		 }
		String dob1 = request.getParameter("dob");
		String date_of_admission1 = request.getParameter("date_of_admission");
		date_of_admission1 = "01/01/"+ date_of_admission1;
		String internship_completion_date1 = request.getParameter("internship_completion_date");
		
		String name = request.getParameter("name");
		String dob2 = request.getParameter("dob");
		String aadhar_card = request.getParameter("aadhar_card");
		String email = request.getParameter("email");
		String mobile_no = request.getParameter("mobile_no");
		String internship_completion_date2 = request.getParameter("internship_completion_date");
		String reg_state2= request.getParameter("reg_state");
		String state_reg_num= request.getParameter("state_reg_num");
		String system_id2 = request.getParameter("system_id");
		String university_id2 = request.getParameter("university_id");
		String institute_id2 = request.getParameter("institute_id");
//		String date_of_admission2 = request.getParameter("date_of_admission"); 
		
		String date_of_admission2 = date_of_admission1;
		//		if(request.getHeader("Referer") == null ) { 
//			 session.invalidate();
//			 model.put("msg", "Suspicious Activity Detected,You have been logged out by Administrator");
//			 return new ModelAndView("redirect:/login");
//		 }
//		String role = session.getAttribute("role").toString();
//		System.out.println("Dr=======================>>>"+role);
//		String roleid1 = session.getAttribute("roleid").toString();
//		 Boolean val = roledao.ScreenRedirect("academic_detailsUrl", roleid1);		
//			if(val == false) {
//				return new ModelAndView("AccessTiles");
//		}
		
		
		if (name == null || name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter First Name");
			return new ModelAndView("redirect:signup_practitionner_Url");
		}
		if (validation.maxlengthcheck50(name) == false) {
			ra.addAttribute("msg", "First Name " + validation.MaxlengthcheckMSG50);
			return new ModelAndView("redirect:signup_practitionner_Url");
		}
		if (validation.isOnlyAlphabetDASH(name) == false) {
			ra.addAttribute("msg", "First Name " + validation.isOnlyAlphabetMSGDASH);
			return new ModelAndView("redirect:signup_practitionner_Url");
		}

		if (name == null || name.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Father Name");
			return new ModelAndView("redirect:signup_practitionner_Url");
		}
		if (dob2 == null || dob2.equals("0")) {
			ra.addAttribute("msg", "Please Enter Date Of Birth");
			return new ModelAndView("redirect:signup_practitionner_Url");
		}
		if (dob2 == null || dob2.equals("DD/MM/YYYY")) {
			ra.addAttribute("msg", "Please Enter The Date Of Birth");
			return new ModelAndView("redirect:signup_practitionner_Url");
		}
		if (aadhar_card == null || aadhar_card.trim().equals("")) {
			ra.addAttribute( "msg" ,  "Please Enter Aadhar No");
			return new ModelAndView("redirect:signup_practitionner_Url");
		}
		if (aadhar_card.length() != 12) {
			ra.addAttribute( "err" ,  "Please Enter 12 digit Aadhar No");
			return new ModelAndView("redirect:signup_practitionner_Url");
		}
		if (validation.isOnlyNumer(aadhar_card) == true) {
			ra.addAttribute("msg", "Aadhar No " + validation.isOnlyNumerMSG);
			return new ModelAndView("redirect:signup_practitionner_Url");
		}
		if (email.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter Email Id.");
			return new ModelAndView("redirect:signup_practitionner_Url");
		}
		if (validation.maxlengthcheck70(email) == false) {
			ra.addAttribute("msg", "Email Address " + validation.MaxlengthcheckMSG70);
			return new ModelAndView("redirect:signup_practitionner_Url");
		}
		if (mobile_no.equals("0") || mobile_no == null) {
			ra.addAttribute("msg", "Please Enter Mobile Number");
			return new ModelAndView("redirect:signup_practitionner_Url");
		}
		if (internship_completion_date2 == null || internship_completion_date2.equals("0")) {
			ra.addAttribute("msg", "Please Enter Internship Completion Date");
			return new ModelAndView("redirect:signup_practitionner_Url");
		}
		if (internship_completion_date2 == null || internship_completion_date2.equals("DD/MM/YYYY")) {
			ra.addAttribute("msg", "Please Enter Internship Completion Date");
			return new ModelAndView("redirect:signup_practitionner_Url");
		}
		if (reg_state2 != null && reg_state2.equals("0") && reg_state2 != "0") {
			ra.addAttribute("msg", "Please Select Registration State");
			return new ModelAndView("redirect:signup_practitionner_Url");
		}
		if (state_reg_num == null || state_reg_num.trim().equals("")) {
			ra.addAttribute("msg", "Please Enter State Reg Number");
			return new ModelAndView("redirect:signup_practitionner_Url");
		}
		if (validation.isOnlyAlphabetMSGDASHforregStateNum(state_reg_num) == false) {
			ra.addAttribute("msg", "State Reg Number " + validation.isOnlyAlphabetMSGDASHforregStateNum);
			return new ModelAndView("redirect:signup_practitionner_Url");
		}
//		if (system_id2 != null && system_id2.equals("0") && system_id2 != "0") {
//			ra.addAttribute("msg", "Please Select System");
//			return new ModelAndView("redirect:signup_practitionner_Url");
//		}
		if (university_id2 != null && university_id2.equals("0") && university_id2 != "0") {
			ra.addAttribute("msg", "Please Select University");
			return new ModelAndView("redirect:signup_practitionner_Url");
		}
		if (institute_id2 != null && institute_id2.equals("0") && institute_id2 != "0") {
			ra.addAttribute("msg", "Please Select Institute");
			return new ModelAndView("redirect:signup_practitionner_Url");
		}
		if (date_of_admission2 == null || date_of_admission2.equals("DD/MM/YYYY")) {
			ra.addAttribute("msg", "Please Enter Year Of Admission");
			return new ModelAndView("redirect:signup_practitionner_Url");
		}
		
		Date date = new Date();
	     DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		String university_id= request.getParameter("university_id");
		
//		--01-03-2023
		Session sessionHQL4 = this.sessionFactory.openSession();
		Query q_country = sessionHQL4.createQuery("select id from TB_COUNTRY where name=:name ");
		q_country.setParameter("name","OTHER");  
		int c_conutry = (int) q_country.uniqueResult();
		

		Query q_district = sessionHQL4.createQuery("select id from EDU_LMS_DISTRICT_MSTR where district_name=:district_name ");
		q_district.setParameter("district_name","OTHER");  
		int c_district = (int) q_district.uniqueResult();
		

		Query q_state = sessionHQL4.createQuery("select id from TB_STATE where state_name=:state_name ");
		q_state.setParameter("state_name","OTHER");  
		int c_state = (int) q_state.uniqueResult();
		sessionHQL4.close();
		
		if (university_id.equals("OTHER")) {
			String university_name_other= request.getParameter("university_name_other");
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			
			EDU_LMS_UNIVERSITY_MSTR um = new EDU_LMS_UNIVERSITY_MSTR();
			um.setUniversity_name(university_name_other);
			um.setUniversity_type(2);
			um.setOrganization_id(2);
//			--01-03-2023
			um.setCountry_id(c_conutry);
			um.setDistrict_id(c_district);
			um.setState_id(c_state);
			um.setSystem_id(td.getSystem_id());
			um.setStatus("1");
			um.setCreated_by(name);
			um.setCreated_date(date);
			int u_id = (int)sessionHQL.save(um);
			td.setUniversity_id(u_id);
			sessionHQL.flush();
			sessionHQL.clear();
			tx.commit();
			sessionHQL.close();
		}
		
		String institute_id= request.getParameter("institute_id");
		if (institute_id.equals("OTHER")) {
			String institute_name_other= request.getParameter("institute_name_other");
			Session sessionHQL = this.sessionFactory.openSession();
			Transaction tx = sessionHQL.beginTransaction();
			EDU_LMS_INSTITUTE_REGISTRATION im = new EDU_LMS_INSTITUTE_REGISTRATION();
			im.setInstitute_name(institute_name_other);
			im.setUniversity_id(td.getUniversity_id());
//			--01-03-2023
			im.setCountry_id(c_conutry);
			im.setDistrict_id(c_district);
			im.setState_id(c_state);
			im.setSystem_id(td.getSystem_id());
			im.setStatus("1");
			im.setApp_status("1");
			im.setCreated_by(name);
			im.setCreated_date(date);
			int i_id = (int)sessionHQL.save(im);
			td.setInstitute_id(i_id);
			sessionHQL.flush();
			sessionHQL.clear();
			tx.commit();
			sessionHQL.close();
		}
		
		String reg_state= request.getParameter("reg_state");
		 
			
 
			int id = td.getId() > 0 ? td.getId() : 0;
			UserLogin p = new UserLogin();
//		String system_name = principal.getName();
		Session sessionHQL = this.sessionFactory.openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
//			Long c = (Long) sessionHQL.createQuery(
//					"select count(id) from  REG_NCH_PRACTITIONER_Signup_Details where name=:name and id !=:id")
//					.setParameter("name", name)
//					.setParameter("id", id).uniqueResult();
			
			Query q0 = sessionHQL.createQuery("select count(*) from REG_NCH_PRACTITIONER_Signup_Details where email=:email and status != 2");
			q0.setParameter("email", td.getEmail());  
			Long c1 = (Long) q0.uniqueResult();
			
			Query q1 = sessionHQL.createQuery("select count(*) from REG_NCH_PRACTITIONER_Signup_Details where upper(aadhar_card)=:aadhar_card and status != 2");
			q1.setParameter("aadhar_card", td.getAadhar_card().toUpperCase());  
			Long c2 = (Long) q1.uniqueResult();
			
			Query q2 = sessionHQL.createQuery("select count(*) from UserLogin where  mobile_no=:mobile_no");
			q2.setParameter("mobile_no", td.getMobile_no());  
			Long c3 = (Long) q2.uniqueResult();
			
			Query q3 = sessionHQL.createQuery("select count(*) from REG_NCH_PRACTITIONER_Signup_Details where mobile_no=:mobile_no and status != 2");
			q3.setParameter("mobile_no", td.getMobile_no());  
			Long c4 = (Long) q3.uniqueResult();
			
			if (id == 0) {
				td.setCreated_by(name);
//				td.setCreated_role(role);
				td.setCreated_date(date);
				td.setUpload_date(date);
				td.setSystem_id(45);
				SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");
				Date dob = null;
				if (!dob1.equals("DD/MM/YYYY") && !dob1.equals("DD/MM/YYYY")) {
					dob = formate.parse(dob1);
				}
				if (dob != null && !dob.equals(""))
					td.setDob(dob);
				Date date_of_admission = null;
				if (!date_of_admission1.equals("DD/MM/YYYY") && !date_of_admission1.equals("DD/MM/YYYY")) {
					date_of_admission = formate.parse(date_of_admission1);
				}
				if (date_of_admission != null && !date_of_admission.equals(""))
					td.setDate_of_admission(date_of_admission);
				
//				--10-04-2023
				Date internship_completion_date = null;
				if (!internship_completion_date1.equals("") && !internship_completion_date1.equals("DD/MM/YYYY")) {
					internship_completion_date = formate.parse(internship_completion_date1);
				}
				if (internship_completion_date != null && !internship_completion_date.equals(""))
					td.setInternship_completion_date(internship_completion_date); 
				
				
				
//				Date internship_completion_date = null;
//				if (!internship_completion_date1.equals("DD/MM/YYYY") && !internship_completion_date1.equals("DD/MM/YYYY")) {
//					internship_completion_date = formate.parse(internship_completion_date1);
//				}
				 
				td.setReg_state(Integer.parseInt(reg_state));
				if ( c1==0 && c2==0 && c3==0 && c4==0) {
					sessionHQL.save(td);
					sessionHQL.flush();
					sessionHQL.clear();
					ra.addAttribute("msg", "Sign Up Successfully.");
				}else if(c1!=0){
					ra.addAttribute("msg", "Email id Already Registered");
				}
				else if(c2!=0){
					ra.addAttribute("msg", "Aadhaar No Already Registered");
				}else if(c3!=0){
					ra.addAttribute("msg", "Mobile No Already Registered");
				}else if(c4!=0){
					ra.addAttribute("msg", "Mobile No Already Registered");
				}else{
			     	ra.addAttribute("msg", "Data already Exist.");
				}
			}
			tx.commit();
			}
		 catch (RuntimeException e) {
			try {

				ra.addAttribute("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				ra.addAttribute("msg", "Couldnot roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:signup_practitionner_Url");
	}	
	
	
	
	public String fileupload(byte[] b, String name, int id, String type) {
		String extension = "";
		String fname = "";
		try {
			byte[] bytes = b; // file.getBytes();
			// Creating the directory to store file
			String rootPath = System.getProperty("catalina.home");
			File dir = new File(rootPath + File.separator + "TEMPEXCEL");
			// File dir = new File("/srv/BRO_REC_Documents/"+id);
			if (!dir.exists())
				dir.mkdirs();

			String filename = name; // file.getOriginalFilename();

			int i = filename.lastIndexOf('.');
			if (i >= 0) {
				extension = filename.substring(i + 1);
			}

			fname = dir.getAbsolutePath() + File.separator + "tempinterview" + "." + extension;
			File serverFile = new File(fname);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return fname;
	}
	
	
	
//	// FOR EMAIL
//		public void SendRegMail(String email) throws ParseException {
////			String code = randomString(5);
//			MailHTML html = new MailHTML();
//			try {
//				MimeMessage mimeMessage = mailSender.createMimeMessage();
//				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
//				try {
//					String text = "Your Registration is Successfull ... Thank you for Registrating with us! Now , you can Login with your credentials.";
//					String note = "";
//					html.setHTML(text, note);
//					String htmlMsg = html.getHTML();
//					helper.setText(htmlMsg, true);
//					helper.setTo(email);
//					helper.setSubject("MOA Registration is Successfull");
//					/* helper.setFrom("abc@gmail.com"); */
//					mailSender.send(mimeMessage);
//				} catch (MessagingException e) {
//					e.printStackTrace();
//				}
//			} catch (Exception e) {
//			}
//		}
		
	
	@Autowired
	private DataSource dataSource;	
	
	public String getMaxAID(String userid) {

		Connection conn = null;
		String reg_no = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String query = "";
//			query = "select Substring(st.state_name,1,2)||Substring(code,1,4)||EXTRACT(YEAR FROM now())||lpad((select case when (select max(Substring(ayush_id,11,14))\n"
//					+ "from reg_nch_details_a)='' or (select max(Substring(ayush_id,11,14))\n"
//					+ "from reg_nch_details_a) is null  then '0' else (select max(Substring(ayush_id,11,14))\n"
//					+ "from reg_nch_details_a) end::int+1)::text, 4, '0')as max from logininformation l\n"
//					+ "inner join edu_lms_institute_reg ir on ir.id=l.institute_id and userid = ?   \n"
//					+ "inner join edu_lms_state_mstr st on st.state_id=l.state_id\n"
//					+ "";
//			
			
			query = "     select to_char(CURRENT_TIMESTAMP,'yy')||code||'HOM'||lpad((select case when (select max(Substring(ayush_id,12,5))\n"
					+ "    from reg_nch_details_a)='' or (select max(Substring(ayush_id,12,5))\n"
					+ "    from reg_nch_details_a) is null  then '0' else (select max(Substring(ayush_id,12,5))\n"
					+ "    from reg_nch_details_a) end::int+1)::text, 5, '0')as max from logininformation l\n"
					+ "    inner join edu_lms_institute_reg ir on ir.id=l.institute_id and userid = ? \n"
					+ "    inner join edu_lms_state_mstr st on st.state_id=l.state_id ";
			
			stmt = conn.prepareStatement(query);
			
			stmt.setInt(1, Integer.parseInt(userid));
			System.err.println("-----ayushhhh--->   "+stmt);
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
	
	@PostMapping("/getFilter_Exp_Excel_data")
	public @ResponseBody List<Map<String, Object>> getFilter_Exp_Excel_data(int startPage, int pageLength,
	String Search,String orderColunm,String orderType,String role_type,String upload_date) throws ParseException {
		return Edao.DataTableEdu_Exp_excel(startPage, pageLength, Search, orderColunm, orderType,role_type,upload_date);
	}

	@PostMapping("/getTotalEdu_Exp_Excel_dataCount")

	public @ResponseBody long getTotalEdu_Exp_Excel_dataCount(HttpSession sessionUserId,String Search,String role_type,String upload_date ) throws ParseException {
		return Edao.DataTableEdu_Exp_Excel_Count(Search, role_type, upload_date );
	}
	
	@RequestMapping(value = "/getUniversitylistbySystemforpractitioner", method = RequestMethod.POST)
	public @ResponseBody List<EDU_LMS_UNIVERSITY_MSTR> getUniversitylistbySystem(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "selval", required = false) Integer selval, HttpServletRequest request) {
		List<EDU_LMS_UNIVERSITY_MSTR> list = IRdao.getUniversitylistUrl(selval);
		System.err.println("cjeck list "+list);

		return list;
	}
	
	 @RequestMapping(value = "/get_inst_by_uni_nch_ctrl_practitioneer", method = RequestMethod.POST)
	 	public @ResponseBody ArrayList<ArrayList<String>> get_inst_by_uni_nch_ctrl(String university_id) {
	    	ArrayList<ArrayList<String>> data = SSRDao.get_inst_by_uni_nch_data(university_id);
	    	return data;
	 	}
 
}
